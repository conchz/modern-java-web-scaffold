package com.github.lavenderx.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2016-01-24.
 *
 * @author dolphineor
 */
@RestController
@RequestMapping(path = "/api")
public class ApiBaseController {

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public final String api(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\"></head>" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "</head>" +
                "<body>" +
                "<h3>Hello, Welcome to <a href=\"https://github.com/dolphineor/modern-java-web-scaffold\">modern-java-web-scaffold</a></h3>" +
                "</body>" +
                "</html>";
    }
}
