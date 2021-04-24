package cn.kobelee.gateway.domain;

import lombok.Data;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @date 12/14/2020
 */
@Data
public class RouteConfig {
    private String targetRoute;
    private String targetApplication;
    private String targetHttpUri;
    private String accessWay;
    private String method;

    public static RouteConfig from(Api cogApi){
        RouteConfig routeConfig = new RouteConfig();
        routeConfig.setAccessWay(cogApi.getAccessWay());
        routeConfig.setTargetRoute(cogApi.getRoutePath());
        routeConfig.setTargetApplication(cogApi.getApplicationCode());
        routeConfig.setTargetHttpUri(cogApi.getHttpUrl());
        routeConfig.setMethod(cogApi.getMethod());
        return routeConfig;
    }
}
