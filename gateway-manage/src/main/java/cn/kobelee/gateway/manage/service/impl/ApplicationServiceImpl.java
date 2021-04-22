package cn.kobelee.gateway.manage.service.impl;

import cn.kobelee.gateway.manage.entity.Application;
import cn.kobelee.gateway.manage.repository.ApplicationRepository;
import cn.kobelee.gateway.manage.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/22/2021 10:46 PM
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    public Application getById(Long id) {
        return applicationRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Application saveOrUpdate(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application deleteById(Long id) {
        Application toDelete = getById(id);
        applicationRepository.deleteById(id);
        return toDelete;
    }

    @Override
    public Page<Application> pageQuery(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.DESC, "id");
        return applicationRepository.findAll(pageable);
    }
}
