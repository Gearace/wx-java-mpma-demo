package com.binarywang.demo.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author binary wang
 */

@SpringBootApplication
@MapperScan("com.binarywang.demo.*.mapper")
public class MpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpDemoApplication.class, args);
    }

}
