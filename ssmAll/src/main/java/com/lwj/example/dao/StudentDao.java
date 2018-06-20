package com.lwj.example.dao;

import com.lwj.example.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    //分页查询
    public List<Map<String, Object>> getStudentInfoList(Integer pageNo, Integer pageSize, Map<String, String> searchMap){
        List<Map<String, Object>> studentInfoList = new ArrayList<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
        sql.append("Select * From student Where 1=1");
        try{
            if(searchMap != null){
                for(String key : searchMap.keySet()){
                    String value = searchMap.get(key);
                    sql.append(" And "+key+" Like '%"+value+"%'");

                }
            }

            if(pageNo != null && pageSize != null){
                int from = (pageNo-1) * pageSize;
                int offset = pageSize;
                sql.append(" limit ?, ?");
                studentInfoList = jdbcTemplate.queryForList(sql.toString(), from, offset);
            }else{
                studentInfoList = jdbcTemplate.queryForList(sql.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return studentInfoList;
    }

    //单个查询
    public Student getStudentById(Integer id){
        Student student = new Student();
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("Select * From student Where id = ?");
            student = jdbcTemplate.queryForObject(sql.toString(), new Object[]{id}, new RowMapper<Student>() {
                @Override
                public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                    Student s = new Student();
                    s.setName(resultSet.getString("name"));
                    s.setPassword(resultSet.getString("password"));
                    s.setAge(resultSet.getInt("age"));
                    return s;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

        return student;
    }

    //条件查询
    public List<Map<String, Object>> getStudentByCondition(String name){
        List<Map<String, Object>> studentInfoList = new ArrayList<Map<String, Object>>();
        StringBuilder sql = new StringBuilder();
        sql.append("Select * From student Where ");
        sql.append(" name = ?");
        try{
            studentInfoList = jdbcTemplate.queryForList(sql.toString(),name);
        }catch (Exception e){
            e.printStackTrace();
        }

        return studentInfoList;
    }


    //增加
    public int addStudentInfo(Student student) {
        int result = 0;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("Insert Into student(name, password, age, createDate, updateDate) ");
            sql.append(" Values(?, ?, ?, ?, ?)");

            String name = student.getName();
            String password = student.getPassword();
            int age = student.getAge();
            Date createDate = new Date();
            Date updateDate = new Date();

            result = jdbcTemplate.update(sql.toString(), name, password, age, createDate, updateDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //修改
    public int updateStudentInfo(Student student){
        int result = 0;
        try{
            Integer id = student.getId();
            Student student1 = getStudentById(id);
            if(student1 == null){
                return 0;
            }

            String name = student.getName();
            String password = student.getPassword();
            Integer age = student.getAge();
            Date updateDate = new Date();
            StringBuilder sql = new StringBuilder();
            sql.append("Update student Set name = ?, password = ?, age = ?, updateDate = ? ");
            sql.append("Where id = ? ");
            result = jdbcTemplate.update(sql.toString(), name, password, age, updateDate, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //删除
    public int deleteStudentInfo(Integer id){
        int result = 0;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("Delete From student Where id = ? ");
            result = jdbcTemplate.update(sql.toString(),id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
