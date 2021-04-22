package cn.kobelee.gateway.manage.service.impl;

import cn.kobelee.gateway.manage.entity.ApiSubscribe;
import cn.kobelee.gateway.manage.repository.ApiSubscribeRepository;
import cn.kobelee.gateway.manage.service.ApiSubscribeService;
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
 * @date 4/22/2021 11:22 PM
 */
@Service
@RequiredArgsConstructor
public class ApiSubscribeServiceImpl implements ApiSubscribeService {
    private final ApiSubscribeRepository apiSubscribeRepository;

    @Override
    public ApiSubscribe getById(Long id) {
        return apiSubscribeRepository.findById(id).orElse(null);
    }

    @Override
    public ApiSubscribe saveOrUpdate(ApiSubscribe apiSubscribe) {
        return apiSubscribeRepository.save(apiSubscribe);
    }

    @Override
    public ApiSubscribe deleteById(Long id) {
        ApiSubscribe apiSubscribe = getById(id);
        apiSubscribeRepository.deleteById(id);
        return apiSubscribe;
    }

    @Override
    public Page<ApiSubscribe> pageQuery(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.DESC, "id");
        return apiSubscribeRepository.findAll(pageable);
    }
}
