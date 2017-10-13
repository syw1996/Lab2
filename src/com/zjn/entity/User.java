package com.zjn.entity;

import java.io.Serializable;
//import java.util.Date;

public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer id; // id
    private String name; // name
    private Integer age; // age
    private String country; // creatTime

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}