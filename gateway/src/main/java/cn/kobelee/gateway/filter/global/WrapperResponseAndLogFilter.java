package cn.kobelee.gateway.filter.global;


import cn.kobelee.gateway.domain.ExchangeLog;
import cn.kobelee.gateway.util.ExchangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

import static cn.kobelee.gateway.common.Constant.FilterOrder.WRAPPER_RESPONSE;


/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 12/23/2020
 */
@Component
@Slf4j
public class WrapperResponseAndLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String bodyStr = new String(content, Charset.forName("UTF-8"));
                        //cache response in attribute
                        ExchangeLog exchangeLog = new ExchangeLog();
                        exchangeLog.setResponseBody(bodyStr);
                        exchangeLog.setResponseHeaders(exchange.getResponse().getHeaders().toSingleValueMap());
                        exchangeLog.setRequestHeaders(exchange.getRequest().getHeaders().toSingleValueMap());
                        exchangeLog.setRequestBody(ExchangeUtils.resolveBodyFromRequest(exchange.getRequest()));
                        log.info("response:{}",exchangeLog);
                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };
        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return WRAPPER_RESPONSE;
    }
}
