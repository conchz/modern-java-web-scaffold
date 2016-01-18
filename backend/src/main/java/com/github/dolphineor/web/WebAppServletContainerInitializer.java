package com.github.dolphineor.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
public class WebAppServletContainerInitializer implements ServletContainerInitializer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
//        XmlWebApplicationContext rootWebAppContext = new XmlWebApplicationContext();
//        rootWebAppContext.setConfigLocation("classpath:spring-context.xml");
//        rootWebAppContext.setParent(applicationContext);
//        ctx.addListener(new ContextLoaderListener(rootWebAppContext));
    }
}
