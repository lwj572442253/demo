package com.lwj.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.text.ParseException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/checkLogin")
    public ModelAndView checkLogin(ModelAndView model, @RequestParam("username") String username, @RequestParam("password") String password) throws ParseException {
        boolean result = true;
        if(result){
            model = new ModelAndView("redirect:/student/getStudentInfo?pageNo=1");
        }else{
            model.setViewName("login");
        }
        return model;
    }
}
