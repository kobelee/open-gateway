package cn.kobelee.gateway.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Description:
 *
 * @author Yuxiang Li
 * @email kobe663@gmail.com
 * @date 4/21/2021 10:20 PM
 */
@SpringBootApplication
@EnableJpaAuditing
public class GatewayManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayManageApplication.class);
        System.out.println("started !!!");
    }
}
