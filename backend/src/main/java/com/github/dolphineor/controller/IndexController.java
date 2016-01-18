package com.github.dolphineor.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2016-01-18.
 * <p>For TEST
 *
 * @author dolphineor
 */
@RestController
@Scope("prototype")
public class IndexController {

    @RequestMapping(path = {"/", "/index"})
    public ModelAndView index() {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> attributes = new HashMap<String, Object>() {{
            put("Title", "modern-java-web-scaffold-backend");
        }};
        jsonView.setAttributesMap(attributes);

        return new ModelAndView(jsonView);
    }
}
