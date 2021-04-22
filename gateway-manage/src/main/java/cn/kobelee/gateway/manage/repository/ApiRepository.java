package cn.kobelee.gateway.manage.repository;

import cn.kobelee.gateway.manage.entity.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiRepository extends JpaRepository<Api, Long>, PagingAndSortingRepository<Api,Long> {
    Api getApiById(Long id);
}