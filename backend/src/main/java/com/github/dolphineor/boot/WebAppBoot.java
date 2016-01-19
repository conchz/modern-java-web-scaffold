package com.github.dolphineor.boot;

import com.github.dolphineor.web.WebAppServer;
import org.h2.tools.Server;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
public class WebAppBoot {

    public static void main(String[] args) {
        try {
            // Start H2Database
            Server h2dbServer = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();

            // Web App Settings
            Resource resource = new PathResource("/");
            WebAppServer webAppServer = new WebAppServer("modern-java-web-scaffold", resource, 8080).start();

            // Add shutdownHook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    webAppServer.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Close H2DB
                h2dbServer.stop();
            }));

            Thread.currentThread().join();
        } catch (SQLException | IOException | ServletException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
