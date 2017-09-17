package com.acupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by liujie on 2017/8/12.
 */
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@ServletComponentScan
public class HospApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospApplication.class, args);
    }
}
