package cn.kobelee.gateway.filter.global;

import cn.kobelee.gateway.util.ExchangeUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static cn.kobelee.gateway.common.Constant.FilterOrder.CACHE_BODY;


/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 12/23/2020
 */
//@Component
public class WrapperRequestFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MediaType contentType = exchange.getRequest().getHeaders().getContentType();
        HttpMethod method = exchange.getRequest().getMethod();
        if (contentType == null) {
            return chain.filter(exchange);
        } else {
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        DataBufferUtils.retain(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux
                                .defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        if(Strings.isEmpty(ExchangeUtils.resolveBodyFromRequest(mutatedRequest))){
                            return chain.filter(exchange);
                        }else{
                            return chain.filter(exchange.mutate().request(mutatedRequest).build());
                        }
                    });
        }
    }

    @Override
    public int getOrder() {
        return CACHE_BODY;
    }
}
