package com.example.controller.web;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    // 重定向到home
    public String charon(){
        return "redirect:/home";
    }

    @RequestMapping("home")
    // 首页
    public String home(ModelMap map, HttpServletRequest request){

        map.addAttribute("name", "Home");

        return "web/home";
    }

    @RequestMapping("loginout")
    // 登出
    public String loginOut(HttpServletRequest request){
        request.getSession().invalidate();

        return "redirect:/home";
    }

    @RequestMapping("/test")
    public String Test(){
        return "test";
    }

    @RequestMapping("/admin")
    public String Admin(){
        return "web/admin";
    }
}
