package org.lavenderx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;

/**
 * Created on 2016-01-18.
 *
 * @author lavenderx
 */
@Order(-1)
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.github.lavenderx.service"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,
                        value = org.springframework.stereotype.Repository.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION,
                        value = org.springframework.stereotype.Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION,
                        value = org.springframework.web.bind.annotation.RestController.class)}
)
@ImportResource({"spring-mybatis.xml"})
@PropertySource({"classpath:common-config.properties", "classpath:${spring.profiles.active}.properties"})
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
