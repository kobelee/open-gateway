package cn.kobelee.gateway.manage.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "api_subscribe_config")
public class ApiSubscribeConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置编码
     */
    @ApiModelProperty("配置编码")
    @Column(name = "config_code")
    private String configCode;

    /**
     * 配置名
     */
    @ApiModelProperty("配置名")
    @Column(name = "config_name")
    private String configName;

    /**
     * 配置描述
     */
    @ApiModelProperty("配置描述")
    @Column(name = "config_desc")
    private String configDesc;

    /**
     * 是否启用限流
     */
    @ApiModelProperty("是否启用限流")
    @Column(name = "rate_limit_on")
    private Integer rateLimitOn;

    /**
     * 限流配置
     */
    @ApiModelProperty("限流配置")
    @Column(name = "rate_limiter_config")
    private String rateLimiterConfig;

    /**
     * 是否启用黑白名单
     */
    @ApiModelProperty("是否启用黑白名单")
    @Column(name = "black_white_list_on")
    private Integer blackWhiteListOn;

    /**
     * 黑白名单
     */
    @ApiModelProperty("黑白名单")
    @Column(name = "black_white_list")
    private String blackWhiteList;

    /**
     * 是否需要验签
     */
    @ApiModelProperty("是否需要验签")
    @Column(name = "need_sign_charge")
    private Integer needSignCharge;

    /**
     * 入参是否需要解密
     */
    @ApiModelProperty("入参是否需要解密")
    @Column(name = "in_param_decrypt")
    private Integer inParamDecrypt;

    /**
     * 出参是否需要加密
     */
    @ApiModelProperty("出参是否需要加密")
    @Column(name = "out_param_encrypt")
    private Integer outParamEncrypt;

}
