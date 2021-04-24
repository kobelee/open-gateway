package cn.kobelee.gateway.util;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 12/23/2020
 */
public class ExchangeUtils {
    public static String getHeader(ServerWebExchange serverWebExchange, String headerKey){
        List<String> values = serverWebExchange.getRequest().getHeaders().get(headerKey);
        if(CollectionUtils.isEmpty(values)){
            return null;
        }else{
            return values.get(0);
        }
    }

    public static ServerWebExchange addHeader(ServerWebExchange exchange,String key,String value){
        return exchange.mutate()
                .request(exchange.getRequest()
                        .mutate()
                        .header(key, value)
                        .build())
                .build();
    }

    public static ServerWebExchange putAttribute(ServerWebExchange exchange, String key, Object value){
        exchange.getAttributes().put(key,value);
        return exchange;
    }

    public static <T> T getAttribute(ServerWebExchange exchange,String key){
        return (T) exchange.getAttribute(key);
    }

    /**
     * 读取body内容
     * @param serverHttpRequest
     * @return
     */
    public static String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest){
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        StringBuilder sb = new StringBuilder();

        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
//            DataBufferUtils.release(buffer);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            sb.append(bodyString);
        });
        return formatStr(sb.toString());
    }

    /**
     * 去掉空格,换行和制表符
     * @param str
     * @return
     */
    private static String formatStr(String str){
        if (str != null && str.length() > 0) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        }
        return str;
    }
}
