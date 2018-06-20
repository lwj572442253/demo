package com.lwj.example.service;

import com.lwj.example.dao.StudentDao;
import com.lwj.example.entity.Student;
import com.lwj.example.util.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class StudentService {
    @Resource
    private StudentDao studentDao;

    //分页查询
    public List<Student> getStudentInfoList(Integer pageNo, Integer pageSize, Map<String, String> searchMap) throws ParseException {
        List<Student> studentInfoList = new ArrayList<Student>();

        List<Map<String, Object>> list = studentDao.getStudentInfoList(pageNo, pageSize, searchMap);
        Iterator<Map<String, Object>> iterator = list.iterator();
        while (iterator.hasNext()){
            Map<String, Object> map = iterator.next();
            String id = map.get("id").toString();
            String name = map.get("name").toString();
            String password = map.get("password").toString();
            String age = map.get("age").toString();
            String createTime = map.get("createDate").toString();
            String updateTime = map.get("updateDate").toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createDate = sdf.parse(createTime);
            Date updateDate = sdf.parse(updateTime);

            Student student = new Student(Integer.valueOf(id), name, password, Integer.valueOf(age), createDate, updateDate);
            studentInfoList.add(student);
        }

        return studentInfoList;
    }

    //获取学生信息
    public Student getStudentById(Integer id){
        Student student = studentDao.getStudentById(id);
        return student;
    }

    //获取学生信息
    public List<Student> getStudentByCondition(String name){
        List<Map<String, Object>> list = studentDao.getStudentByCondition(name);
        List<Student> studentList = new ArrayList<Student>();
        if(list.size()!=0){
            Iterator<Map<String, Object>> iterator = list.iterator();
            while (iterator.hasNext()){
                Map<String, Object> studentMap = iterator.next();
                Student student = new Student();
                student.setId(Integer.valueOf(studentMap.get("id").toString()));
                student.setName(studentMap.get("name").toString());
                student.setAge(Integer.valueOf(studentMap.get("age").toString()));
                student.setPassword(studentMap.get("password").toString());
                studentList.add(student);
            }
        }
        return studentList;
    }

    //保存学生信息
    @Transactional
    public JsonResult saveStudentInfo(Student student){
        Integer id = student.getId();
        Integer age = student.getAge();
        String name = student.getName();
        String password = student.getPassword();

        //校验
        name = name.replace("/(^\\s+)|(\\s+$)/g", "");
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]{2,4}$");
        Matcher matcher = pattern.matcher(name);
        boolean match = matcher.matches();
        if(!match){
            return new JsonResult("名字不合法");
        }

        pattern = Pattern.compile("^[0-9]{1,3}$");
        matcher = pattern.matcher(age.toString());
        match = matcher.matches();
        if(!match){
            return new JsonResult("年龄不合法");
        }

        pattern = Pattern.compile("^[0-9]{6}$");
        matcher = pattern.matcher(password);
        match = matcher.matches();
        if(!match){
            return new JsonResult("密码不合法");
        }

        int num = 0;
        if(id == null){//添加
            num = studentDao.addStudentInfo(student);
        }else{//更新
            num = studentDao.updateStudentInfo(student);
        }

        return new JsonResult(num, "响应成功");
    }

    //删除学生信息
    public int deleteStudentInfo(Integer id){
        int reseult = studentDao.deleteStudentInfo(id);
        return reseult;
    }
}
