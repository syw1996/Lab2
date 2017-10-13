package com.zjn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;

import com.zjn.entity.User;

@Controller
public class UserController {

	@RequestMapping("/home")//对应网址后缀名
	public String Homepage() {
        return "home";//jsp文件名
    }
	
    @RequestMapping("/addAuthor")
    public String Create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String Save(@ModelAttribute User user, Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
        model.addAttribute("user", user);
        
        String Driver = "com.mysql.jdbc.Driver";
		String Url = "jdbc:mysql://sqld.duapp.com:4050/ASYfRMhSzMRvPgeULTKc";
		String User = "f10451fb6bf64722a8d889c5e6f701a0";
        String Password = "f3b3ab00d5db422288387f5b80b3994b";
        try {
        	  Class.forName(Driver);
        	  Connection conn = DriverManager.getConnection(Url, User, Password);
        	  if(!conn.isClosed())
        	   System.out.println("connecting to the database successfully!");
        	  Statement statement = conn.createStatement();
        	  int n1 = user.getId();
        	  int n2 = user.getAge();
        	  String s1 = user.getName();
        	  String s2 = user.getCountry();
        	  String sql = "insert into author(id,name,age,country) value("+n1+",'"+s1+"','"+n2+"','"+s2+"')";
        	  System.out.println(sql);
        	  statement.execute(sql);
        	  conn.close();  
        	  } catch(ClassNotFoundException e) {  
        	   System.out.println("sorry, can't find the driver!");  
        	   e.printStackTrace();  
        	  } catch(SQLException e) {
        	   e.printStackTrace();
        	  } catch(Exception e){
        	   e.printStackTrace();
        	  }
        
        return "authordetail";
    }
}