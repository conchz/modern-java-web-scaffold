package com.github.dolphineor.web;

import com.github.dolphineor.config.RootConfig;
import com.github.dolphineor.config.WebMvcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.*;
import java.util.Set;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
public class WebAppServletContainerInitializer implements ServletContainerInitializer {

    private final Logger logger = LoggerFactory.getLogger(WebAppServletContainerInitializer.class);


    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
        // Create the root appContext
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        // Since we registered RootConfig instead of passing it to the constructor
        rootContext.refresh();

        // Manage the lifecycle of the root appContext
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.addListener(IntrospectorCleanupListener.class);
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        // Load config for the Dispatcher servlet
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebMvcConfig.class);

        // The main Spring MVC servlet
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("modern-java-web-scaffold", new DispatcherServlet(mvcContext));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/*");

        // Setting EncodingFilter
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");


        Set<String> mappingConflicts = appServlet.addMapping("/");
        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                logger.error("Mapping conflict: " + s);
            }
        }
    }
}
