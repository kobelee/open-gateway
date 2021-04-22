package cn.kobelee.gateway.manage.service;

import cn.kobelee.gateway.manage.entity.ApiConsumer;
import org.springframework.data.domain.Page;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/22/2021 11:19 PM
 */
public interface ApiConsumerService {
    ApiConsumer getById(Long id);

    ApiConsumer saveOrUpdate(ApiConsumer apiConsumer);

    ApiConsumer deleteById(Long id);

    Page<ApiConsumer> pageQuery(Integer pageIndex, Integer pageSize);
}
