package com.neusoft.golf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author : y_zhang.neu
 * @description : 业务协同平台管理端
 * @since : 2020-01-19, 星期日
 **/
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
//@ComponentScan(basePackages = {"com.neusoft.golf.piles.bcp", "com.neusoft.golf.license.client"})
public class BcpWebApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BcpWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BcpWebApplication.class, args);
    }
}
