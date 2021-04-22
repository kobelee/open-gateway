package cn.kobelee.gateway.manage.controller;

import cn.kobelee.gateway.manage.entity.ApiSubscribe;
import cn.kobelee.gateway.manage.entity.ApiSubscribeConfig;
import cn.kobelee.gateway.manage.service.ApiSubscribeConfigService;
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
@RequestMapping("/api-subscribe-config")
@RequiredArgsConstructor
@Api(tags = "接口订阅配置管理")
public class ApiSubscribeConfigController implements IController {
    private final ApiSubscribeConfigService apiSubscribeConfigService;

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public R<ApiSubscribeConfig> getById(@PathVariable Long id) {
        ApiSubscribeConfig apiSubscribeConfig = apiSubscribeConfigService.getById(id);
        return success(apiSubscribeConfig);
    }


    @PostMapping
    @ApiOperation("保存")
    public R<ApiSubscribeConfig> save(@RequestBody ApiSubscribeConfig apiSubscribeConfig) {
        ApiSubscribeConfig saved = apiSubscribeConfigService.saveOrUpdate(apiSubscribeConfig);
        return success(saved);
    }

    @PutMapping
    @ApiOperation("更新")
    public R<ApiSubscribeConfig> update(@RequestBody ApiSubscribeConfig apiSubscribeConfig) {
        ApiSubscribeConfig updated = apiSubscribeConfigService.saveOrUpdate(apiSubscribeConfig);
        return success(updated);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除")
    public R<ApiSubscribeConfig> delete(@PathVariable Long id) {
        ApiSubscribeConfig deleted = apiSubscribeConfigService.deleteById(id);
        return success(deleted);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<Page<ApiSubscribeConfig>> page(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        Page<ApiSubscribeConfig> page = apiSubscribeConfigService.pageQuery(pageIndex, pageSize);
        return success(page);
    }
}
