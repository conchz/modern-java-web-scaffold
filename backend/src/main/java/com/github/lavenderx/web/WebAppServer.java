package com.github.lavenderx.web;

import com.google.common.collect.Lists;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.ServletContainerInitializerInfo;
import io.undertow.servlet.api.ServletInfo;
import io.undertow.servlet.handlers.DefaultServlet;
import io.undertow.servlet.util.ImmediateInstanceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Created on 2016-01-16.
 *
 * @author dolphineor
 */
@Slf4j
public class WebAppServer implements DisposableBean {
    private final List<String> staticResourceMappings =
            Lists.newArrayList("*.css", "*.js", "*.ico", "*.gif", "*.jpg", "*.jpeg", "*.png");

    private final String webAppName;
    private final int port;

    private Undertow undertowServer;
    private DeploymentManager deploymentManager;

    public WebAppServer(String webAppName, int port) {
        this.webAppName = webAppName;
        this.port = port;
    }

    public WebAppServer start() throws IOException, ServletException {
        InstanceFactory<WebAppServletContainerInitializer> instanceFactory =
                new ImmediateInstanceFactory<>(new WebAppServletContainerInitializer());
        ServletContainerInitializerInfo sciInfo = new ServletContainerInitializerInfo(
                WebAppServletContainerInitializer.class, instanceFactory, new HashSet<>()
        );

        ServletInfo defaultServlet = Servlets.servlet("default", DefaultServlet.class)
                .addMappings(staticResourceMappings);
        DeploymentInfo deploymentInfo = Servlets.deployment()
                .addServletContainerInitalizer(sciInfo)
                .setClassLoader(this.getClass().getClassLoader())
                .setContextPath("/")
                .setDefaultEncoding("UTF-8")
                .setDeploymentName(webAppName)
                .setResourceManager(new ClassPathResourceManager(this.getClass().getClassLoader()))
                .addServlet(defaultServlet);
        deploymentManager = Servlets.defaultContainer().addDeployment(deploymentInfo);
        deploymentManager.deploy();

        HttpHandler httpHandler = deploymentManager.start();

        undertowServer = Undertow.builder()
                .addHttpListener(port, "localhost")
                .setHandler(httpHandler)
                .build();

        undertowServer.start();

        return this;
    }

    @Override
    public void destroy() throws Exception {
        log.info("Stopping Undertow web server on port " + port);
        undertowServer.stop();
        deploymentManager.stop();
        deploymentManager.undeploy();
        log.info("Undertow web server on port " + port + " stopped");
    }
}
