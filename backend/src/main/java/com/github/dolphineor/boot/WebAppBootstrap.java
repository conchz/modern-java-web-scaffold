package com.github.dolphineor.boot;

import io.undertow.Undertow;
import io.undertow.util.Headers;

/**
 * Created on 2016-01-16.
 *
 * @author dolphineor
 */
public class WebAppBootstrap {

    public static void main(String[] args) {
        final Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello World");
                }).build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
    }
}
