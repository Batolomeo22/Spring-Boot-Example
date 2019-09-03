package com.bravoz.ep3redis.controller;

import com.bravoz.ep3redis.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachableController {
    /**
     * 第一个未指定key，第二第三个指定了key为传入参数的id
     * 127.0.0.1:6379> keys user-key*
     * 1) "user-key::com.bravoz.ep3redis.controller.cachableControllergetUser"
     * 2) "user-key::10"
     * 3) "user-key::11"     *
     */

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser(){
        User user = new User("aaa",15,"aaa@email.com");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");

        return user;
    }

    @RequestMapping("/getUser2")
    @Cacheable(value="user-key",key = "#id + ''")
    public User getUser2(int id){
        User user = new User("bbb",15,"bbb@email.com");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");

        return user;
    }

    @RequestMapping("/getUser3")
    @Cacheable(value="user-key")
    public User getUser3(int id){
        User user = new User("ccc",id,"ccc@email.com");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");

        return user;
    }
}
