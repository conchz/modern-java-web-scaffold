package com.github.dolphineor.boot;

import io.undertow.Undertow;
import io.undertow.util.Headers;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created on 2016-01-16.
 *
 * @author dolphineor
 */
public class WebAppBootstrap {

    public static void main(String[] args) {
        final Logger log = LoggerFactory.getLogger(WebAppBootstrap.class);

        try {
            // Start H2Database
            Server h2dbServer = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();

            final Undertow server = Undertow.builder()
                    .addHttpListener(8080, "localhost")
                    .setHandler(exchange -> {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Hello World");
                    }).build();

            // Start web service
            server.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                server.stop();
                h2dbServer.stop();
            }));
        } catch (SQLException e) {
            log.error("H2 数据库启动失败!");
            e.printStackTrace();
        }

    }
}
