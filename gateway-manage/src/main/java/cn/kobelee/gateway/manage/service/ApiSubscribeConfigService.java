package cn.kobelee.gateway.manage.service;

import cn.kobelee.gateway.manage.entity.ApiSubscribeConfig;
import org.springframework.data.domain.Page;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/22/2021 11:21 PM
 */
public interface ApiSubscribeConfigService {
    ApiSubscribeConfig getById(Long id);

    ApiSubscribeConfig saveOrUpdate(ApiSubscribeConfig apiSubscribeConfig);

    ApiSubscribeConfig deleteById(Long id);

    Page<ApiSubscribeConfig> pageQuery(Integer pageIndex, Integer pageSize);
}
