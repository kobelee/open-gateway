package cn.kobelee.gateway.manage.repository;

import cn.kobelee.gateway.manage.entity.ApiSubscribeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiSubscribeConfigRepository extends JpaRepository<ApiSubscribeConfig, Long>, JpaSpecificationExecutor<ApiSubscribeConfig> {

}