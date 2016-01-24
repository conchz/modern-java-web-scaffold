package com.github.dolphineor.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@RestController
@Scope("prototype")
public class UserController extends ApiBaseController {

    @RequestMapping(path = "/user/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAllUser() {
        return "";
    }
}
