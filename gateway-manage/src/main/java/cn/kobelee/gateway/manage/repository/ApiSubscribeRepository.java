package cn.kobelee.gateway.manage.repository;

import cn.kobelee.gateway.manage.entity.ApiSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiSubscribeRepository extends JpaRepository<ApiSubscribe, Long>, JpaSpecificationExecutor<ApiSubscribe> {

}