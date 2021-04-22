package cn.kobelee.gateway.manage.controller;

import cn.kobelee.gateway.manage.service.ApiService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import cn.kobelee.gateway.manage.entity.Api;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/21/2021 10:05 PM
 */
@io.swagger.annotations.Api(tags = "接口相关")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController implements IController {
    private final ApiService apiService;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询接口")
    public R<Api> queryById(@PathVariable Long id){
        return success(apiService.getById(id));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<Page<Api>> pageQuery(@RequestParam int pageIndex,@RequestParam int pageSize){
        return success(apiService.page(pageIndex,pageSize));
    }

    @PostMapping
    @ApiOperation("新增")
    public R<Api> saveOne(@RequestBody Api api){
        return success(apiService.saveOne(api));
    }

    @PutMapping
    @ApiOperation("更新")
    public R<Api> update(@RequestBody Api api){
        return success(apiService.update(api));
    }
}
