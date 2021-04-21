package cn.kobelee.gateway.manage.service.impl;

import cn.kobelee.gateway.manage.entity.Api;
import cn.kobelee.gateway.manage.repository.ApiRepository;
import cn.kobelee.gateway.manage.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/21/2021 10:14 PM
 */
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final ApiRepository repository;

    @Override
    public Api getById(Long id) {
        return repository.getApiById(id);
    }
}
