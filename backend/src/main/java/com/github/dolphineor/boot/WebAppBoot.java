package com.github.dolphineor.boot;

import com.github.dolphineor.web.WebAppConfig;
import org.h2.tools.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(WebAppConfig.class);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                ctx.close();
                h2dbServer.stop();
            }));

            Thread.currentThread().join();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }
}
