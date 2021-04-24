package cn.kobelee.gateway.domain;

import lombok.Data;

import java.util.Map;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 12/23/2020
 */
@Data
public class ExchangeLog {
    private Map<String,String> requestHeaders;
    private String requestBody;

    private Map<String,String> responseHeaders;
    private String responseBody;
}
