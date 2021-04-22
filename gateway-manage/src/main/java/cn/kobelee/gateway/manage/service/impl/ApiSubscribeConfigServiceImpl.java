package cn.kobelee.gateway.manage.service.impl;

import cn.kobelee.gateway.manage.entity.ApiSubscribeConfig;
import cn.kobelee.gateway.manage.repository.ApiSubscribeConfigRepository;
import cn.kobelee.gateway.manage.service.ApiSubscribeConfigService;
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
 * @date 4/22/2021 11:27 PM
 */
@Service
@RequiredArgsConstructor
public class ApiSubscribeConfigServiceImpl implements ApiSubscribeConfigService {
    private ApiSubscribeConfigRepository apiSubscribeConfigRepository;

    @Override
    public ApiSubscribeConfig getById(Long id) {
        return apiSubscribeConfigRepository.findById(id).orElse(null);
    }

    @Override
    public ApiSubscribeConfig saveOrUpdate(ApiSubscribeConfig apiSubscribeConfig) {
        return apiSubscribeConfigRepository.save(apiSubscribeConfig);
    }

    @Override
    public ApiSubscribeConfig deleteById(Long id) {
        ApiSubscribeConfig apiSubscribeConfig = getById(id);
        apiSubscribeConfigRepository.deleteById(id);
        return apiSubscribeConfig;
    }

    @Override
    public Page<ApiSubscribeConfig> pageQuery(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.DESC, "id");
        return apiSubscribeConfigRepository.findAll(pageable);
    }
}
