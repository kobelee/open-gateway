package cn.kobelee.gateway.cache;

import cn.kobelee.gateway.domain.Api;
import cn.kobelee.gateway.domain.RouteConfig;
import cn.kobelee.gateway.feign.OpenGatewayManageFeignClient;
import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Description: 保存RouteConfig的缓存
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 2021/4/24
 */
@Slf4j
public class RouteConfigHolder implements InitializingBean {
    @Value("${open-gateway.route-config.max-cache-size:1024}")
    private Integer maxCacheSize;
    @Value("${open-gateway.route-config.expire-secs:60}")
    private Integer expireSecs;
    private Cache<String, RouteConfig> routeConfigCache;
    private OpenGatewayManageFeignClient openGatewayManageFeignClient;

    public void setOpenGatewayManageFeignClient(OpenGatewayManageFeignClient openGatewayManageFeignClient){
        this.openGatewayManageFeignClient = openGatewayManageFeignClient;
    }

    public RouteConfig get(String method) {
        return routeConfigCache.get(method, s -> {
            R<Api> result = openGatewayManageFeignClient.getOneByMethod(method);
            return RouteConfig.from(result.getData());
        });
    }

    /**
     * 初始化缓存
     */
    private void init(){
        routeConfigCache = Caffeine.newBuilder()
                .maximumSize(maxCacheSize)
                .expireAfterWrite(expireSecs, TimeUnit.SECONDS)
                .build();
        log.info("ready to fetch apis from open-gateway-manage");
        R<List<Api>> result = openGatewayManageFeignClient.queryCogApis(null, null, null);
        List<Api> cogApis = result.getData();
        if(!result.isSuccess() || StringUtils.isEmpty(cogApis)){
            log.error("failed to fetch apis from open-gateway-manage:{}", JSON.toJSONString(result));
            return;
        }
        log.info("fetched apis count:{}",cogApis.size());
        cogApis.stream().forEach(cogApi -> routeConfigCache.put(cogApi.getMethod(),RouteConfig.from(cogApi)));
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
