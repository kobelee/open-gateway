package cn.kobelee.gateway.manage.service.impl;

import cn.kobelee.gateway.manage.entity.ApiConsumer;
import cn.kobelee.gateway.manage.repository.ApiConsumerRepository;
import cn.kobelee.gateway.manage.service.ApiConsumerService;
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
public class ApiConsumerServiceImpl implements ApiConsumerService {
    private ApiConsumerRepository apiConsumerRepository;

    @Override
    public ApiConsumer getById(Long id) {
        return apiConsumerRepository.findById(id).orElse(null);
    }

    @Override
    public ApiConsumer saveOrUpdate(ApiConsumer apiConsumer) {
        return apiConsumerRepository.save(apiConsumer);
    }

    @Override
    public ApiConsumer deleteById(Long id) {
        ApiConsumer apiConsumer = getById(id);
        apiConsumerRepository.deleteById(id);
        return apiConsumer;
    }

    @Override
    public Page<ApiConsumer> pageQuery(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.DESC, "id");
        return apiConsumerRepository.findAll(pageable);
    }
}
