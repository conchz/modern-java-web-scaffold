package com.github.dolphineor.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@PropertySource(value = {"classpath:jdbc-h2.properties"})
@ComponentScan(basePackages = {"com.github.dolphineor.model.dao", "com.github.dolphineor.mapper", "com.github.dolphineor.service"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.web.bind.annotation.RestController.class)}
)
public class RootConfig {
}
