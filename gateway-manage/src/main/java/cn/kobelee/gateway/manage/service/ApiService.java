package cn.kobelee.gateway.manage.service;

import cn.kobelee.gateway.manage.entity.Api;
import org.springframework.data.domain.Page;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/21/2021 10:06 PM
 */
public interface ApiService {

    Api getById(Long id);

    Page<Api> page(int pageIndex, int pageSize);

    Api saveOne(Api api);
}
