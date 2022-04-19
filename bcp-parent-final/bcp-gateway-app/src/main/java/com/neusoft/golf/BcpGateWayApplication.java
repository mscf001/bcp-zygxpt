package com.neusoft.golf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : y_zhang.neu
 * @description :
 * @since : 2020-01-19, 星期日
 **/
@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableJpaRepositories
public class BcpGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BcpGateWayApplication.class, args);
    }
}
