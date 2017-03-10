package org.lavenderx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

/**
 * Created on 2016-01-18.
 *
 * @author lavenderx
 */
@ComponentScan("com.github.lavenderx.config")
@SpringBootApplication
public class WebAppBoot {

    private static final String PROFILE = "spring.profiles.active";

    public static void main(String[] args) {
        if (StringUtils.isEmpty(System.getProperty(PROFILE))) {
            System.setProperty(PROFILE, "dev");
        }

        SpringApplication.run(WebAppBoot.class, args);
    }
}