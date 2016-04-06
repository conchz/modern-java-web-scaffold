package com.github.lavenderx;

import com.github.lavenderx.web.WebAppServer;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
public class WebAppBoot {

    public static void main(String[] args) {
        try {
            WebAppServer webAppServer = new WebAppServer("modern-java-web-scaffold", 8081).start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    webAppServer.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}