package cn.kobelee.gateway.manage.controller;

import cn.kobelee.gateway.manage.entity.ApiConsumer;
import cn.kobelee.gateway.manage.service.ApiConsumerService;
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
 * @date 4/23/2021 12:06 AM
 */
@RestController
@RequestMapping("/api-consumer")
@RequiredArgsConstructor
@Api(tags = "接口调用方管理")
public class ApiConsumerController implements IController {
    private final ApiConsumerService apiConsumerService;

    @ApiOperation("根据id查询应用")
    @GetMapping("/{id}")
    public R<ApiConsumer> getById(@PathVariable Long id) {
        ApiConsumer apiConsumer = apiConsumerService.getById(id);
        return success(apiConsumer);
    }


    @PostMapping
    @ApiOperation("保存")
    public R<ApiConsumer> save(@RequestBody ApiConsumer apiConsumer) {
        ApiConsumer saved = apiConsumerService.saveOrUpdate(apiConsumer);
        return success(saved);
    }

    @PutMapping
    @ApiOperation("更新")
    public R<ApiConsumer> update(@RequestBody ApiConsumer apiConsumer) {
        ApiConsumer updated = apiConsumerService.saveOrUpdate(apiConsumer);
        return success(updated);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除")
    public R<ApiConsumer> delete(@PathVariable Long id) {
        ApiConsumer deleted = apiConsumerService.deleteById(id);
        return success(deleted);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<Page<ApiConsumer>> page(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        Page<ApiConsumer> page = apiConsumerService.pageQuery(pageIndex, pageSize);
        return success(page);
    }
}
