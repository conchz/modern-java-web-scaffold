package com.github.lavenderx.web;

import com.github.lavenderx.config.RootConfig;
import com.github.lavenderx.config.WebMvcConfig;
import com.github.lavenderx.web.filter.StatelessJwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@Slf4j
public class WebAppServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
        // Create the root appContext
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        // Since we registered RootConfig instead of passing it to the constructor
        rootContext.refresh();

        // Manage the lifecycle of the root appContext
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.addListener(new IntrospectorCleanupListener());
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        // Load config for the Dispatcher servlet
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebMvcConfig.class);

        // The main Spring MVC servlet
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("modern-java-web-scaffold", new DispatcherServlet(mvcContext));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/*");

        // Setting EncodingFilter
        FilterRegistration.Dynamic encodingFilterRegistration = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilterRegistration.setInitParameter("encoding", "UTF-8");
        encodingFilterRegistration.setInitParameter("forceEncoding", "true");
        encodingFilterRegistration.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic statelessJwtFilterRegistration =
                servletContext.addFilter("statelessJwtFilter", new StatelessJwtFilter());
        statelessJwtFilterRegistration.addMappingForUrlPatterns(null, true, "/*");


        Set<String> mappingConflicts = appServlet.addMapping("/");
        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                log.error("Mapping conflict: " + s);
            }
        }
    }
}
