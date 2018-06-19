package com.lwj.example.controller;

import com.lwj.example.entity.Student;
import com.lwj.example.service.StudentService;
import com.lwj.example.util.JsonResult;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StudentService studentService;
    private static Logger logger = Logger.getLogger(StudentController.class);

    @RequestMapping("/log")
    public void getLogInfo(){
        logger.debug("debug");
        logger.error("error");
        logger.info("info");
        logger.warn("warn");
    }

    //分页查询
    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.GET )
    public ModelAndView gridStudentInfoList(ModelAndView model, @RequestParam("pageNo") Integer pageNo) throws ParseException {
        //列表信息
        List<Student> studentInfoList = studentService.getStudentInfoList(pageNo, 2);
        //分页信息
        List<Student> studentPageInfo = studentService.getStudentInfoList(null,null);
        int recordTotal = studentPageInfo.size();
        int pageTotal = (int)Math.ceil(recordTotal/2.0);

        model.addObject("studentInfoList", studentInfoList);
        model.addObject("recordTotal",recordTotal);
        model.addObject("pageTotal",pageTotal);
        model.setViewName("grid");
        return model;
    }

    //获取学生信息
    @RequestMapping("/getStudentById")
    @ResponseBody
    public ModelAndView getStudentById(ModelAndView model, @RequestParam("id") Integer id){
        Student student = studentService.getStudentById(id);
        String name = student.getName();
        String password = student.getPassword();
        Integer age =  student.getAge();

        model.addObject("id", id);
        model.addObject("name", name);
        model.addObject("password", password);
        model.addObject("age", age);
        model.setViewName("save");
        return model;
    }

    //修改学生信息
    @RequestMapping(value = "/saveStudentInfo", method = RequestMethod.POST )
    @ResponseBody
    public ModelAndView saveStudentInfo(ModelAndView model, Student student, @RequestParam("token")String token){
        model.setViewName("save");

        //用redis防止表单重复提交
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        String key = "token_"+student.getName();
        String value = (String)redisTemplate.opsForValue().get(key);
        if(value != null){
            model.addObject("msg","重复提交");
        }else{
            operations.set(key, token);
            redisTemplate.expire(key, 30*60, TimeUnit.SECONDS);

            JsonResult jsonResult = studentService.saveStudentInfo(student);
            model.addObject("msg", jsonResult.getMessage());
        }

        return model;
    }

    //删除学生信息
    @RequestMapping(value = "/deleteStudentInfo", method = RequestMethod.GET)
    public ModelAndView deleteStudentInfo(ModelAndView model, @RequestParam("id") Integer id){
        int result = studentService.deleteStudentInfo(id);
        model = new ModelAndView("redirect:/student/getStudentInfo?pageNo=1");
        return model;
    }
}
