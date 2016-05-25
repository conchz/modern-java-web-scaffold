package com.github.lavenderx.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created on 2016-05-25.
 *
 * @author lavenderx
 */
@Order(0)
@Component
@Slf4j
public class SimpleStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info(">>>>>>>>>>>>>>>> Spring Boot extra startup is running <<<<<<<<<<<<<<<<");
    }
}
