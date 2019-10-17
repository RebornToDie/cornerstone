package com.example.cornerstone;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("com.example.mybatisplus")
public class CornerstoneApplication {

    private static Logger log = LoggerFactory.getLogger(CornerstoneApplication.class);

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        log.info("CornerstoneApplication workdir : {}",properties.get("user.dir"));
        SpringApplication.run(CornerstoneApplication.class, args);
    }

}
