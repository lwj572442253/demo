package com.lwj.example.dao;

import com.lwj.example.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class LoginDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> checkLogin(String username, String password){
        StringBuilder sql = new StringBuilder();
        sql.append("Select * From student Where name = ? And password = ?");
        List<Map<String, Object>> student = jdbcTemplate.queryForList(sql.toString(), username, password);
        return student;
    }
}
