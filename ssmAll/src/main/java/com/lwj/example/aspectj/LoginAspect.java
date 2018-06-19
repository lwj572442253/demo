package com.lwj.example.aspectj;

import com.lwj.example.entity.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Aspect    //该标签把LoggerAspect类声明为一个切面
@Order(1)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把LoggerAspect类放到IOC容器中
public class LoginAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoginAspect.class);
    private HttpSession session;

    @Pointcut(value = "execution(public * com.lwj.example.controller.StudentController.*(..))")
    public void pointcut(){ }

    //务必验证成功才可跳转到页面
//    @Around("pointcut()")
//    public Object beforeLogin(ProceedingJoinPoint pjp) throws Throwable {
//        String method = pjp.getSignature().getName();//调用方法名
//        logger.info("开始调用方法：{}", method);
//
//        //验证是否有登录cookie、sessionId，一切请求拦截到登录页
//        Object student = session.getAttribute("currentStudent");
//        if(student == null){
//            return new ModelAndView("login");
//        }else{
//            return pjp.proceed();//继续执行
//        }
//
//    }

}
