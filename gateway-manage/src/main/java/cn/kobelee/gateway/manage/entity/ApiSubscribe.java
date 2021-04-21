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
@Table(name = "api_subscribe")
public class ApiSubscribe implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * pk
     */
    @Id
    @ApiModelProperty("pk")
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 接口id,api.id关联
     */
    @Column(name = "api_id")
    @ApiModelProperty("接口id,api.id关联")
    private Long apiId;

    /**
     * 应用id,application.id关联
     */
    @Column(name = "application_id")
    @ApiModelProperty("应用id,application.id关联")
    private Long applicationId;

    /**
     * 消费者id, api_consumer.id关联
     */
    @Column(name = "api_consumer_id")
    @ApiModelProperty("消费者id, api_consumer.id关联")
    private Long apiConsumerId;

    /**
     * 订阅配置id,subscribe_config.id关联
     */
    @Column(name = "subscribe_config_id")
    @ApiModelProperty("订阅配置id,subscribe_config.id关联")
    private Long subscribeConfigId;

    /**
     * 订阅描述
     */
    @ApiModelProperty("订阅描述")
    @Column(name = "subscribe_desc")
    private String subscribeDesc;

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
