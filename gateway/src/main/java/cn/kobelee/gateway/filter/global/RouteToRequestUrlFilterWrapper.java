package cn.kobelee.gateway.filter.global;

import cn.kobelee.gateway.common.Constant;
import cn.kobelee.gateway.domain.RouteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.regex.Pattern;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 * Description: 重写了RouteToRequestUrlFilter，用来改变在发送给负载均衡filter前的url.
 * 扩展的内容为：如果attribute.RouteConfig.accessWay不是同一注册中心，
 * 则将url改为http://开头的全路径(配置在routeConfig中)
 *
 * @author Yuxiang Li
 * @date 12/15/2020
 */
@Slf4j
public class RouteToRequestUrlFilterWrapper implements GlobalFilter, Ordered, BeanPostProcessor {


    private static final String SCHEME_REGEX = "[a-zA-Z]([a-zA-Z]|\\d|\\+|\\.|-)*:.*";
    static final Pattern schemePattern = Pattern.compile(SCHEME_REGEX);



    @Override
    public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
        return bean;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {
        if(bean instanceof RouteToRequestUrlFilter){
            return this;
        }else{
            return bean;
        }

    }

    /* for testing */
    static boolean hasAnotherScheme(URI uri) {
        return schemePattern.matcher(uri.getSchemeSpecificPart()).matches()
                && uri.getHost() == null && uri.getRawPath() == null;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        if (route == null) {
            return chain.filter(exchange);
        }
        log.trace("RouteToRequestUrlFilter start");
        URI uri = exchange.getRequest().getURI();
        boolean encoded = containsEncodedParts(uri);
        URI routeUri = route.getUri();

        if (hasAnotherScheme(routeUri)) {
            // this is a special url, save scheme to special attribute
            // replace routeUri with schemeSpecificPart
            exchange.getAttributes().put(GATEWAY_SCHEME_PREFIX_ATTR,
                    routeUri.getScheme());
            routeUri = URI.create(routeUri.getSchemeSpecificPart());
        }

        if ("lb".equalsIgnoreCase(routeUri.getScheme()) && routeUri.getHost() == null) {
            // Load balanced URIs should always have a host. If the host is null it is
            // most
            // likely because the host name was invalid (for example included an
            // underscore)
            throw new IllegalStateException("Invalid host: " + routeUri.toString());
        }

        RouteConfig routeConfig = exchange.getAttribute(Constant.Attribute.ROUTE_CONFIG);

        URI mergedUrl = null;
        if(Constant.AccessWay.SAME_REGISTRY_CENTER.equals(routeConfig.getAccessWay())){
            mergedUrl = UriComponentsBuilder.fromUri(uri)
                    // .uri(routeUri)
                    .scheme(routeUri.getScheme()).host(routeConfig.getTargetApplication())
                    .port(routeUri.getPort()).build(encoded).toUri();
        }else if(Constant.AccessWay.HTTP.equals(routeConfig.getAccessWay())){
            mergedUrl = UriComponentsBuilder.fromHttpUrl(routeConfig.getTargetHttpUri()).build(encoded).toUri();
        }

        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, mergedUrl);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Constant.FilterOrder.ROUTE_TO_REQUEST_URL;
    }
}
