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
@Table(name = "application")
@EqualsAndHashCode(callSuper = true)
public class Application extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

}
