package com.brazo.ep2_web.controller;

import com.brazo.ep2_web.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 注解中，该类中所有的方法都会以json格式返回
 */
@RestController
public class RestfulController {

    @RequestMapping("/getUser")
    public User getUser(){
        User user = new User();
        user.setUserName("明");
        user.setPassWord("1qaz");

        return user;
    }
}
