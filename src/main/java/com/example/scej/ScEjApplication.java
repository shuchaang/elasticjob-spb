package com.example.scej;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shuchang
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
public class ScEjApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScEjApplication.class, args);
    }

}
