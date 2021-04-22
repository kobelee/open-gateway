package cn.kobelee.gateway.manage.controller;

import cn.kobelee.gateway.manage.entity.ApiSubscribe;
import cn.kobelee.gateway.manage.service.ApiSubscribeService;
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
 * @date 4/23/2021 12:07 AM
 */
@RestController
@RequestMapping("/api-subscribe")
@RequiredArgsConstructor
@Api(tags = "接口订阅管理")
public class ApiSubscribeController implements IController {
    private final ApiSubscribeService apiSubscribeService;

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public R<ApiSubscribe> getById(@PathVariable Long id) {
        ApiSubscribe apiSubscribe = apiSubscribeService.getById(id);
        return success(apiSubscribe);
    }


    @PostMapping
    @ApiOperation("保存")
    public R<ApiSubscribe> save(@RequestBody ApiSubscribe apiSubscribe) {
        ApiSubscribe saved = apiSubscribeService.saveOrUpdate(apiSubscribe);
        return success(saved);
    }

    @PutMapping
    @ApiOperation("更新")
    public R<ApiSubscribe> update(@RequestBody ApiSubscribe apiSubscribe) {
        ApiSubscribe updated = apiSubscribeService.saveOrUpdate(apiSubscribe);
        return success(updated);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除")
    public R<ApiSubscribe> delete(@PathVariable Long id) {
        ApiSubscribe deleted = apiSubscribeService.deleteById(id);
        return success(deleted);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<Page<ApiSubscribe>> page(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        Page<ApiSubscribe> page = apiSubscribeService.pageQuery(pageIndex, pageSize);
        return success(page);
    }
}
