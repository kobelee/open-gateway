package cn.kobelee.gateway.manage.service;

import cn.kobelee.gateway.manage.entity.Application;
import org.springframework.data.domain.Page;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/22/2021 10:46 PM
 */
public interface ApplicationService {

    Application getById(Long id);

    Application saveOrUpdate(Application application);

    Application deleteById(Long id);

    Page<Application> pageQuery(Integer pageIndex, Integer pageSize);
}
