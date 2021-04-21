package cn.kobelee.gateway.manage.controller;

import cn.kobelee.gateway.manage.service.ApiService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/21/2021 10:05 PM
 */
@Api(tags = "接口相关")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final ApiService apiService;

    @GetMapping("/{id}")
    public cn.kobelee.gateway.manage.entity.Api testQueryById(@PathVariable Long id){
        return apiService.getById(id);
    }
}
