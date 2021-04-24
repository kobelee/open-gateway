package cn.kobelee.gateway.filter.global;


import cn.kobelee.gateway.cache.RouteConfigHolder;
import cn.kobelee.gateway.common.Constant;
import cn.kobelee.gateway.domain.RouteConfig;
import cn.kobelee.gateway.util.ExchangeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static cn.kobelee.gateway.common.Constant.FilterOrder.DETERMINE_ROUTE_CONFIG;


/**
 * Description: 决定此次请求采用哪个RouteConfig的filter
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 12/23/2020
 */
@RequiredArgsConstructor
@Component
public class DetermineRouteConfigFilter implements GlobalFilter, Ordered {
    @Autowired
    private RouteConfigHolder routeConfigHolder;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String method = ExchangeUtils.getHeader(exchange, Constant.Header.METHOD);
        RouteConfig routeConfig = routeConfigHolder.get(method);
        if(Objects.isNull(routeConfig)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"根据method未找到路由");
        }
        ExchangeUtils.putAttribute(exchange, Constant.Attribute.ROUTE_CONFIG,routeConfig);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return DETERMINE_ROUTE_CONFIG;
    }
}
