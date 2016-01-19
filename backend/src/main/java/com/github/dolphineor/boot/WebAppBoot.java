package com.github.dolphineor.boot;

import com.github.dolphineor.web.WebAppServer;
import org.h2.tools.Server;
import org.springframework.core.io.PathResource;

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

            WebAppServer webAppServer = new WebAppServer("modern-java-web-scaffold", new PathResource("/"), 8080).start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    webAppServer.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                h2dbServer.stop();
            }));
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
