package org.dandelion.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
@ComponentScan(basePackages = {"org.dandelion.service"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Repository.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.web.bind.annotation.RestController.class)}
)
public class RootConfig {

    private static Resource[] propertyLocations = {new ClassPathResource("jdbc-mysql.properties")};


    // 加载*.properties文件
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer =
                new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocations(propertyLocations);

        return propertyPlaceholderConfigurer;
    }
}
