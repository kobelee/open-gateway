package cn.kobelee.gateway.filter.global;


import cn.kobelee.gateway.common.Constant;
import cn.kobelee.gateway.domain.ExchangeLog;
import cn.kobelee.gateway.util.ExchangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Description: 日志filter
 *
 * @author Yuxiang Li
 * @date 12/14/2020
 */
@Component
@Slf4j
public class PreLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String body = ExchangeUtils.resolveBodyFromRequest(exchange.getRequest());
        log.info("this is pre log filter!!!");
        ExchangeLog exchangeLog = new ExchangeLog();
        exchangeLog.setRequestHeaders(exchange.getRequest().getHeaders().toSingleValueMap());
        exchangeLog.setRequestBody(body);
        exchange.getResponse();
        log.info("exchangeLog :{}",exchangeLog);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Constant.FilterOrder.PRE_LOG;
    }
}
