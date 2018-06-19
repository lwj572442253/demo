package com.lwj.example.controller;

import com.lwj.example.entity.Student;
import com.lwj.example.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @RequestMapping("/returnGrid")
    public ModelAndView returnGrid(ModelAndView model, HttpSession session, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) throws ParseException {
        //查询是否含此用户
        Student student = loginService.checkLogin(username, password);

        if (student != null) {
            //保存cookie、sessionId
            Cookie studentCookie = new Cookie(student.getId().toString(), student.toString());
            studentCookie.setPath("/");
            studentCookie.setMaxAge(2*60);
            response.addCookie(studentCookie);

            Cookie sessionIdCookie = new Cookie("sessionId", session.getId());
            sessionIdCookie.setPath("/");
            sessionIdCookie.setMaxAge(-1);
            response.addCookie(sessionIdCookie);

            //保存登录信息
            session.setAttribute("currentStudent", student);
            model = new ModelAndView("redirect:/student/getStudentInfo?pageNo=1");
        } else {
            model.setViewName("login");
        }
        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView logout(ModelAndView model, HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            String key = cookie.getName();
            if(id.equals(key) || "sessionId".equals(key)){
                cookie.setMaxAge(0);//即时过期cookie
                response.addCookie(cookie);
            }
        }
        model.setViewName("login");
        model.addObject("msg", "退出成功");
        return model;
    }
}
