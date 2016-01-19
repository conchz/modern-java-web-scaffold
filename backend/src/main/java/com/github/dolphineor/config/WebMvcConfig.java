package com.github.dolphineor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created on 2016-01-19.
 *
 * @author dolphineor
 */
@Configuration
@ImportResource("classpath:spring-mvc.xml")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
}
