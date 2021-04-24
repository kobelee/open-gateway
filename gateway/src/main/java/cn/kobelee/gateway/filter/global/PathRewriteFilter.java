package cn.kobelee.gateway.filter.global;

import cn.kobelee.gateway.common.Constant;
import cn.kobelee.gateway.domain.RouteConfig;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * Description: 重写path的filter，请求打到网关全部都是请求同一地址，
 * 需要根据匹配的目标接口配置（已在路由匹配的时候写入attribute），重写request.path
 *
 * @author Yuxiang Li
 * @date 12/15/2020
 */
@Component
public class PathRewriteFilter implements GlobalFilter, Ordered {

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        RouteConfig routeConfig = exchange.getAttribute(Constant.Attribute.ROUTE_CONFIG);
        if(Objects.isNull(routeConfig.getTargetRoute())){
            return chain.filter(exchange);
        }else{
            ServerWebExchange newExchange = buildNewExchange(exchange, routeConfig);
            return  chain.filter(newExchange);
        }
    }

    private ServerWebExchange buildNewExchange(ServerWebExchange exchange, RouteConfig routeConfig) throws URISyntaxException {
        ServerHttpRequest.Builder requestBuilder = exchange.getRequest()
                .mutate();
        if(Constant.AccessWay.SAME_REGISTRY_CENTER.equals(routeConfig.getAccessWay())){
            requestBuilder.path(routeConfig.getTargetRoute());
        }else if(Constant.AccessWay.HTTP.equals(routeConfig.getAccessWay())){
            requestBuilder.uri(new URI(routeConfig.getTargetHttpUri()));
        }else{
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,"AccessWay不支持"+routeConfig.getAccessWay());
        }
        ServerWebExchange newExchange = exchange.mutate()
                .request(requestBuilder.build())
                .build();
        return newExchange;
    }

    @Override
    public int getOrder() {
        return Constant.FilterOrder.PATH_REWRITE;
    }
}
