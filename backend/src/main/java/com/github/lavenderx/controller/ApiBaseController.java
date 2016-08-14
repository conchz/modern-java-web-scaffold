package com.github.lavenderx.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2016-01-24.
 *
 * @author lavenderx
 */
@RestController
@RequestMapping(path = "/api")
public class ApiBaseController {

    @GetMapping(path = {"", "/"}, produces = MediaType.TEXT_HTML_VALUE)
    public final String apiBaseResponse(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\"></head>" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge, chrome=1\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "</head>" +
                "<body>" +
                "<h3>" +
                "Hello, Welcome to " +
                "<a href=\"https://github.com/lavenderx/modern-java-web-scaffold\">" +
                "modern-java-web-scaffold" +
                "</a>" +
                "</h3>" +
                "</body>" +
                "</html>";
    }
}
