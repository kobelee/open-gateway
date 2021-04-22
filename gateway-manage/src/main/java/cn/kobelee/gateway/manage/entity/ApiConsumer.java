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
@Table(name = "api_consumer")
@EqualsAndHashCode(callSuper = true)
public class ApiConsumer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口消费者编码
     */
    @ApiModelProperty("接口消费者编码")
    @Column(name = "consumer_code")
    private String consumerCode;

    /**
     * 接口消费者名称
     */
    @ApiModelProperty("接口消费者名称")
    @Column(name = "consumer_name")
    private String consumerName;

    /**
     * 消费者描述
     */
    @ApiModelProperty("消费者描述")
    @Column(name = "consumer_desc")
    private String consumerDesc;

    /**
     * 公钥
     */
    @ApiModelProperty("公钥")
    @Column(name = "public_key")
    private String publicKey;

    /**
     * 私钥
     */
    @ApiModelProperty("私钥")
    @Column(name = "private_key")
    private String privateKey;

    /**
     * 验签密钥id
     */
    @Column(name = "secret_id")
    @ApiModelProperty("验签密钥id")
    private String secretId;

    /**
     * 验签密钥
     */
    @ApiModelProperty("验签密钥")
    @Column(name = "secret_key")
    private String secretKey;

}
