package cn.kobelee.gateway.manage.controller;

import cn.kobelee.gateway.manage.entity.Application;
import cn.kobelee.gateway.manage.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/22/2021 10:43 PM
 */
@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@Api(tags = "应用管理")
public class ApplicationController implements IController {
    private final ApplicationService applicationService;

    @ApiOperation("根据id查询应用")
    @GetMapping("/{id}")
    public R<Application> getById(@PathVariable Long id) {
        Application application = applicationService.getById(id);
        return success(application);
    }


    @PostMapping
    @ApiOperation("保存")
    public R<Application> save(@RequestBody Application application) {
        Application saved = applicationService.saveOrUpdate(application);
        return success(saved);
    }

    @PutMapping
    @ApiOperation("更新")
    public R<Application> update(@RequestBody Application application) {
        Application updated = applicationService.saveOrUpdate(application);
        return success(updated);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除")
    public R<Application> delete(@PathVariable Long id) {
        Application deleted = applicationService.deleteById(id);
        return success(deleted);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<Page<Application>> page(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        Page<Application> page = applicationService.pageQuery(pageIndex, pageSize);
        return success(page);
    }

}
