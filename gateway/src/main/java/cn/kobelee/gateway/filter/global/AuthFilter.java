package cn.kobelee.gateway.filter.global;


import cn.kobelee.gateway.common.Constant;
import cn.kobelee.gateway.domain.ApiConsumer;
import cn.kobelee.gateway.feign.OpenGatewayManageFeignClient;
import cn.kobelee.gateway.util.ShaHashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Description: 校验请求权限相关的filter
 *
 * @author Yuxiang Li
 * @date 12/14/2020
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    @Value("${open-gateway.max-duration-from-request:120000}")
    private Long maxDurationFromRequest;
    private final OpenGatewayManageFeignClient openGatewayManageFeignClient;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(doAuth(exchange)){
            return chain.filter(exchange);
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"调用未经授权");
        }
    }

    private boolean doAuth(ServerWebExchange exchange) {
        Map<String, String> singleValueMap = exchange.getRequest().getHeaders().toSingleValueMap();
        Map<String, String> lowerCaseMap = toLowerCaseKeyMap(singleValueMap);


        String secretId = lowerCaseMap.get("secretid");
        String headers = lowerCaseMap.get("headers");
        R<ApiConsumer> result = openGatewayManageFeignClient.getOneBySecretId(secretId);
        ApiConsumer cogApiConsumer = result.getData();

        chargeIfNeedThrows(secretId, headers, result,lowerCaseMap);

        String signatureFromRequest = lowerCaseMap.get("signature");
        String[] headersArray = headers.split(",");
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : headersArray) {
            stringBuilder.append(lowerCaseMap.get(key.toLowerCase()));
        }

        String signature = ShaHashUtil.calculateShaHash(stringBuilder.toString(), cogApiConsumer.getSecretKey());
        if(!Objects.equals(signature,signatureFromRequest)){
            log.error("校验不通过,signature:{},signatureFromRequest:{}",signature,signatureFromRequest);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"签名校验失败");
        }
        return true;


    }

    private Map<String, String> toLowerCaseKeyMap(Map<String, String> singleValueMap) {
        Map<String,String> lowerCaseMap = new HashMap<>();
        singleValueMap.entrySet().forEach(entry-> lowerCaseMap.put(entry.getKey().toLowerCase(),entry.getValue()));
        return lowerCaseMap;
    }

    private void chargeIfNeedThrows( String secretId, String headers, R<ApiConsumer> result,Map<String,String> lowerCaseMap) {
        chargeDate(lowerCaseMap);

        if(!result.isSuccess()|| Objects.isNull(result.getData())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"未找到secretId对应的调用方配置:"+ secretId);
        }
        if(Strings.isEmpty(headers)){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"需要验签的headers不能为空");
        }
    }

    private void chargeDate(Map<String, String> lowerCaseMap) {
        //时间校验
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dateStr = lowerCaseMap.get("date");
        try {
            long requestTimestamp = simpleDateFormat.parse(dateStr).getTime();
            long currentTimestamp = Calendar.getInstance().getTime().getTime();
            long duration = currentTimestamp - requestTimestamp;
            if( duration > maxDurationFromRequest){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请求中的date超期");
            }else if(duration < -1*maxDurationFromRequest){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"请求日期为未来时间");
            }
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"日期需要满足格式:yyyy-MM-dd'T'HH:mm:ssZ");
        }
    }

    @Override
    public int getOrder() {
        return Constant.FilterOrder.AUTH;
    }
}
