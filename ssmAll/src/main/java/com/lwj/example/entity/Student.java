package com.lwj.example.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "student")
public class Student {
    @Id
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private Date createDate;
    private Date updateDate;

    public Student() {
    }

    public Student(Integer id, String name, String password, Integer age, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Student(Integer id, String name, String password, Integer age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
