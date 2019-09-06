package com.bravoz.thymeleaf.controller;

import com.bravoz.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("index")
    public String goIndex(ModelMap map){
        //测试string
        map.addAttribute("userName","BravoZ");

        //测试if
        map.addAttribute("flag","yes");

        //测试list
        map.addAttribute("users",getUserList());

        //测试url
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "bangumi/play/ep250611");//https://www.bilibili.com/bangumi/play/ep250611
        map.addAttribute("img", "https://desk-fd.zol-img.com.cn/t_s1024x768c5/g5/M00/01/0E/ChMkJlbKwbeIUnXFABrhwMLNqp0AALGcgO1l9QAGuHY212.jpg");

        //测试eq
        map.addAttribute("name", "neo");
        map.addAttribute("age", 30);

        //测试switch

        map.addAttribute("sex", "woman");


        return "index";
    }

    @RequestMapping("indexLayui")
    public String goIndexByLayUi(Map<String,Object> param){

        return "indexLayui";
    }



    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("大牛",11,"123456");
        User user2=new User("小牛",22,"123563");
        User user3=new User("7的意志",33,"77777");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}
