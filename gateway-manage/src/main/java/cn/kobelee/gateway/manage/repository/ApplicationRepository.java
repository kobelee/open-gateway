package cn.kobelee.gateway.manage.repository;

import cn.kobelee.gateway.manage.entity.Application;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long>, JpaSpecificationExecutor<Application> {

}