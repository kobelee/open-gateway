package cn.kobelee.gateway.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiConsumer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 接口消费者编码
     */
    private String consumerCode;

    /**
     * 接口消费者名称
     */
    private String consumerName;

    /**
     * 消费者描述
     */
    private String consumerDesc;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 验签密钥id
     */
    private String secretId;

    /**
     * 验签密钥
     */
    private String secretKey;

}
