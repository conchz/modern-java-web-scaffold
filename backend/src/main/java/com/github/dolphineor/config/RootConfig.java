package com.github.dolphineor.config;

import org.springframework.context.annotation.*;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
@ComponentScan(basePackages = {"com.github.dolphineor.service"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Repository.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.web.bind.annotation.RestController.class)}
)
public class RootConfig {
}
