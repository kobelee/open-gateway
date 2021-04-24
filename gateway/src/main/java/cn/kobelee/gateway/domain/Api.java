package cn.kobelee.gateway.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 接口编码
     */
    private String apiCode;

    /**
     * 接口名称
     */
    private String apiName;


    private String apiDesc;

    /**
     * 应用名称
     */
    private Long applicationId;

    /**
     * 应用编码
     */
    private String applicationCode;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 请求方式，same_registry_center：同一注册中心;http_proxy:http代理
     */
    private String accessWay;

    /**
     * 方法名（对应前端传入的标识）
     */
    private String method;

    /**
     * 目标路由路径(目标应用的路径)
     */
    private String routePath;

    /**
     * 目标url（http代理方式时生效）
     */
    private String httpUrl;

    /**
     * 访问目标的httpmethod  get post put delete等
     */
    private String httpMethod;

    /**
     * 接口版本
     */
    private String apiVersion;

}
