package com.lwj.example.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class LoginDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> checkLogin(String username, String password){
        List<Map<String, Object>> student;
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("Select * From student Where name = ? And password = ?");
            student = jdbcTemplate.queryForList(sql.toString(), username, password);
        }catch (InvalidResultSetAccessException e){
            throw new RuntimeException(e);
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
        return student;
    }
}
