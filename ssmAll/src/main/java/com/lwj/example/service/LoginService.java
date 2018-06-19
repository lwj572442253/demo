package com.lwj.example.service;

import com.lwj.example.dao.LoginDao;
import com.lwj.example.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    @Resource
    private LoginDao loginDao;

    public Student checkLogin(String username, String password){
        List<Map<String, Object>> list = loginDao.checkLogin(username, password);
        Map<String, Object> map = list.get(0);
        Student student = new Student();
        for(String key : map.keySet()){
            if(key.equals("id")){
                String id = map.get(key).toString();
                student.setId(Integer.valueOf(id));
            }else if(key.equals("name")){
                String name = map.get("name").toString();
                student.setName(name);
            }
        }
        return student;
    }
}
