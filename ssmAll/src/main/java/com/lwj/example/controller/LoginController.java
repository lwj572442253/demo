package com.lwj.example.controller;

import com.lwj.example.entity.Student;
import com.lwj.example.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @RequestMapping("/returnGrid")
    public ModelAndView returnGrid(ModelAndView model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) throws ParseException {
        //查询是否含此用户
        Student student = loginService.checkLogin(username, password);

        if (student != null) {
            //保存cookie、sessionId


            //保存登录信息
            session.setAttribute("currentStudent", student);

            model = new ModelAndView("redirect:/student/getStudentInfo?pageNo=1");
        } else {
            model.setViewName("login");
        }
        return model;
    }

    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(ModelAndView model, @RequestParam("username") String username, @RequestParam("password") String password){
        String result = "true";


        return result;
    }
}
