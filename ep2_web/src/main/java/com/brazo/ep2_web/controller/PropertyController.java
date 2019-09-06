package com.brazo.ep2_web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@PropertySource("classpath:bravoz.properties")
public class PropertyController {

    @Value("${bravoz.title}")
    public String title;

    @Value("${bravoz.description}")
    public String description;

    @RequestMapping("/getProperties")
    public Map getProperties() throws UnsupportedEncodingException {
        System.out.println(title);

        //
        String tilteStr = new String(title.getBytes("ISO8859-1"), "UTF-8");
        String descriptionStr = new String(description.getBytes("ISO8859-1"), "UTF-8");
        System.out.println(tilteStr);
        Map map = new HashMap();
        map.put("title", tilteStr);
        map.put("description", descriptionStr);
        return map;
    }
}
