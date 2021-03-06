package com.example.controller.web;

import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hongjiayong on 2016/10/21.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private StudentService studentService = new StudentService();

    @Autowired
    private TeacherService teacherService = new TeacherService();

    @RequestMapping("")
    // 注册页
    public String register(ModelMap map){
        map.addAttribute("name", "RegisterView");
        return "registerStudent";
    }


    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    // 处理注册
    public @ResponseBody String dealRegister(@RequestParam String value, HttpServletRequest request){

/*
        String mId = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();
        String name = request.getParameter("name").trim();
        String grade = request.getParameter("grade").trim();
        String major = request.getParameter("major").trim();
        String contact = request.getParameter("contact").trim();
        String type = request.getParameter("select").trim();


        System.out.print("aaa");
        //JSONObject jsonObject = JSONObject.fromObject(value);
       // System.out.print(jsonObject);


       //studentService.setPersonalInfo(mId,name,grade,major,contact,password);
        String contact = request.getParameter("contact").trim();*/


        System.out.print(value);

        /*
        if(id.equals("student")){
            studentService.setPersonalInfo(mId,name,grade,major,contact,password);
        }else if(id.equals("teacher")){
        }*/


        return "redirect:/home";
    }
}
