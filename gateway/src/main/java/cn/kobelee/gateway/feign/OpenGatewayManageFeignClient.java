package cn.kobelee.gateway.feign;

import cn.kobelee.gateway.domain.Api;
import cn.kobelee.gateway.domain.ApiConsumer;
import net.dreamlu.mica.core.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 12/23/2020
 */
@FeignClient(name = "open-gateway-manage")
public interface OpenGatewayManageFeignClient {
    @GetMapping("/api/method/{method}")
    R<Api> getOneByMethod(@PathVariable(value = "method") String method);

    @GetMapping("/api")
    R<List<Api>> queryCogApis(@RequestParam(value = "apiCode", required = false) String apiCode,
                                 @RequestParam(value = "apiName", required = false) String apiName,
                                 @RequestParam(value = "method", required = false) String method);

    @GetMapping("/api-consumer")
    R<ApiConsumer> getOneBySecretId(@RequestParam(value = "secretId") String secretId);
}
