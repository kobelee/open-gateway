package cn.kobelee.gateway.common;

import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter;
import org.springframework.core.Ordered;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @date 12/14/2020
 */
public class Constant {
    public static final class FilterOrder{
        public static final int CACHE_BODY = Integer.MIN_VALUE;
        //在AdaptCachedBodyGlobalFilter之后
        public static final int PRE_LOG = Ordered.HIGHEST_PRECEDENCE + 1001;
        public static final int DETERMINE_ROUTE_CONFIG = PRE_LOG + 1;
        public static final int PATH_REWRITE = DETERMINE_ROUTE_CONFIG + 1;
        public static final int AUTH = PATH_REWRITE + 1;
        public static final int RATE_LIMIT = AUTH + 1;
        public static final int ROUTE_TO_REQUEST_URL = RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER;

        public static final int WRAPPER_RESPONSE = NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
        public static final int RESPONSE_LOG = Integer.MAX_VALUE;
    }

    public static final class AccessWay{
        public static final String HTTP = "HTTP_PROXY";
        public static final String SAME_REGISTRY_CENTER = "SAME_REGISTRY_CENTER";
    }

    public static final class Attribute{
        public static final String ROUTE_CONFIG = "route-config";
    }

    public static final class Header{
        public static final String METHOD = "method";
    }
}
