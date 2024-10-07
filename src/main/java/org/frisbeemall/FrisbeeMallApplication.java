package org.frisbeemall;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.frisbeemall.dao")
public class FrisbeeMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrisbeeMallApplication.class, args);
    }

}
