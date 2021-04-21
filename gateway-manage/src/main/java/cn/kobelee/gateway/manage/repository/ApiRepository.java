package cn.kobelee.gateway.manage.repository;

import cn.kobelee.gateway.manage.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiRepository extends JpaRepository<Api, Long>, JpaSpecificationExecutor<Api> {
    Api getApiById(Long id);
}