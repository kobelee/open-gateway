package cn.kobelee.gateway.manage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "api")
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * pk
     */
    @Id
    @ApiModelProperty("pk")
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 接口编码
     */
    @ApiModelProperty("接口编码")
    @Column(name = "api_code")
    private String apiCode;

    /**
     * 接口名称
     */
    @ApiModelProperty("接口名称")
    @Column(name = "api_name")
    private String apiName;

    @Column(name = "api_desc")
    private String apiDesc;

    /**
     * 应用名称
     */
    @ApiModelProperty("应用名称")
    @Column(name = "application_id")
    private Long applicationId;

    /**
     * 应用编码
     */
    @ApiModelProperty("应用编码")
    @Column(name = "application_code")
    private String applicationCode;

    /**
     * 应用名称
     */
    @ApiModelProperty("应用名称")
    @Column(name = "application_name")
    private String applicationName;

    /**
     * 请求方式，same_registry_center：同一注册中心;http_proxy:http代理
     */
    @Column(name = "access_way")
    @ApiModelProperty("请求方式，same_registry_center：同一注册中心;http_proxy:http代理")
    private String accessWay;

    /**
     * 方法名（对应前端传入的标识）
     */
    @Column(name = "method")
    @ApiModelProperty("方法名（对应前端传入的标识）")
    private String method;

    /**
     * 目标路由路径(目标应用的路径)
     */
    @Column(name = "route_path")
    @ApiModelProperty("目标路由路径(目标应用的路径)")
    private String routePath;

    /**
     * 目标url（http代理方式时生效）
     */
    @Column(name = "http_url")
    @ApiModelProperty("目标url（http代理方式时生效）")
    private String httpUrl;

    /**
     * 访问目标的httpmethod  get post put delete等
     */
    @Column(name = "http_method")
    @ApiModelProperty("访问目标的httpmethod  get post put delete等")
    private String httpMethod;

    /**
     * 接口版本
     */
    @ApiModelProperty("接口版本")
    @Column(name = "api_version")
    private String apiVersion;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "record_version")
    private Integer recordVersion;

    @Column(name = "creator")
    private String creator;

    @Column(name = "updater")
    private String updater;

}
