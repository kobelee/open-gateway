package cn.kobelee.gateway.manage.service;

import cn.kobelee.gateway.manage.entity.ApiSubscribe;
import org.springframework.data.domain.Page;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/22/2021 11:21 PM
 */
public interface ApiSubscribeService {
    ApiSubscribe getById(Long id);

    ApiSubscribe saveOrUpdate(ApiSubscribe apiSubscribe);

    ApiSubscribe deleteById(Long id);

    Page<ApiSubscribe> pageQuery(Integer pageIndex, Integer pageSize);
}
