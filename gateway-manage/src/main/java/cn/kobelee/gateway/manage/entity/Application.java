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
@Table(name = "application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * pk
     */
    @Id
    @ApiModelProperty("pk")
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 应用编码
     */
    @ApiModelProperty("应用编码")
    @Column(name = "application_code")
    private String applicationCode;

    /**
     * 应用名
     */
    @ApiModelProperty("应用名")
    @Column(name = "application_name")
    private String applicationName;

    /**
     * 入口url,例: http://a.com/service
     */
    @Column(name = "entry_url")
    @ApiModelProperty("入口url,例: http://a.com/service")
    private String entryUrl;

    @Column(name = "application_desc")
    private String applicationDesc;

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
