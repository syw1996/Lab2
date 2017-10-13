package com.zjn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zjn.entity.Book;
import com.zjn.entity.Name;
import com.zjn.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class QueryController {
	
	@RequestMapping("/query")
    public String Create(Model model, HttpSession session) {
        session.setAttribute("exist", 1);
		return "query";
    }
	
	@RequestMapping("/getlist")
	public String Query(String name, HttpSession session) {
		int id = 0;
		String author;
		if(name!=null) {
			author = name;
		}
		else {
			author = (String)session.getAttribute("author");
		}
		System.out.println(author);
		List<Book> list =new ArrayList<Book>();
		User Author = new User();
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
			  String sql = "select id,name,age,country from author";
			  ResultSet rs = statement.executeQuery(sql);  
			  // 搜索和author匹配的id
			  int flag = 0;
			  while(rs.next()) {
				  if(rs.getString("name").equals(author)) {
					  flag = 1;
					  id = rs.getInt("id");
					  Author.setId(id);
					  Author.setName(rs.getString("name"));
					  Author.setAge(rs.getInt("age"));
					  Author.setCountry(rs.getString("country"));				  
					  break;
				  }
			  }
			  if(flag == 0)
			  {
				  session.setAttribute("exist", 0);
				  return "query";
			  }
			  rs.close(); 
			  conn.close();  
			  } catch(ClassNotFoundException e) {  
			   System.out.println("sorry, can't find the driver!");  
			   e.printStackTrace();  
			  } catch(SQLException e) {
			   e.printStackTrace();
			  } catch(Exception e){
			   e.printStackTrace();
			  }
		session.setAttribute("author", Author);
		  
		//Url = "jdbc:mysql://localhost:3306/book";
        try {
        	  Class.forName(Driver);
        	  Connection conn = DriverManager.getConnection(Url, User, Password);
        	  if(!conn.isClosed())
        	   System.out.println("connecting to the database successfully!");
        	  Statement statement = conn.createStatement();
        	  String sql = "select isbn,title,id,publisher,publishdate,price from book";
        	  ResultSet rs = statement.executeQuery(sql);
        	  while(rs.next()) {
        		   if(id == rs.getInt("id")) {
        			   Integer isbn = rs.getInt("isbn");
        			   String title = rs.getString("title");
        			   String publisher = rs.getString("publisher");
        			   Integer publishdate = rs.getInt("publishdate");
        			   float price = rs.getFloat("price");
        			   Book bk = new Book();
        			   bk.setIsbn(isbn);
        			   bk.setTitle(title);
        			   bk.setId(id);
        			   bk.setPublisher(publisher);
        			   bk.setPublishdate(publishdate);
        			   bk.setPrice(price);
        			   list.add(bk);
        		   }
        	  } 
        	  
        	  conn.close();  
        	  } catch(ClassNotFoundException e) {  
        	   System.out.println("sorry, can't find the driver!");  
        	   e.printStackTrace();  
        	  } catch(SQLException e) {
        	   e.printStackTrace();
        	  } catch(Exception e){
        	   e.printStackTrace();
        	  }
        session.setAttribute("list", list);
		return "booklist";
	}
	
	@RequestMapping("/del")
	public void bookDeletes(HttpSession session,HttpServletRequest request) {
		int isbn =  Integer.parseInt(request.getParameter("isbn"));
		List<Book> list =new ArrayList<Book>();
		User author = new User();
		String Driver = "com.mysql.jdbc.Driver";
		String Url = "jdbc:mysql://sqld.duapp.com:4050/ASYfRMhSzMRvPgeULTKc";
		String User = "f10451fb6bf64722a8d889c5e6f701a0";
        String Password = "f3b3ab00d5db422288387f5b80b3994b";
        
        try {
			  Class.forName(Driver);
			  Connection conn = DriverManager.getConnection(Url, User, Password);
			  if(!conn.isClosed())
			  System.out.println("connecting to the database book successfully!");
			  Statement statement = conn.createStatement();
			  //查询书作者id
			  String sql = "select isbn,title,id,publisher,publishdate,price from book";
			  ResultSet rs = statement.executeQuery(sql);
			  int id = 0;
			  while(rs.next()) {
				  if(rs.getInt("isbn")==isbn) {
					  id = rs.getInt("id");
					  author.setId(id);
					  break;
				  }
			  }			  
			  
			  //将选中书籍从数据库中删除
			  sql = "delete from book where isbn='"+isbn+"'";
			  statement.execute(sql);
			  //找出所有该id的书
			  sql = "select isbn,title,id,publisher,publishdate,price from book";
			  rs = statement.executeQuery(sql);
			  while(rs.next()) {
       		   if(id == rs.getInt("id")) {
       			   isbn = rs.getInt("isbn");
       			   String title = rs.getString("title");
       			   String publisher = rs.getString("publisher");
       			   Integer publishdate = rs.getInt("publishdate");
       			   float price = rs.getFloat("price");
       			   Book bk = new Book();
       			   bk.setIsbn(isbn);
       			   bk.setTitle(title);
       			   bk.setId(id);
       			   bk.setPublisher(publisher);
       			   bk.setPublishdate(publishdate);
       			   bk.setPrice(price);
       			   list.add(bk);
       		   }
       	      }
			  //获取书的作者信息
			  //Url = "jdbc:mysql://localhost:3306/author";
			  conn = DriverManager.getConnection(Url, User, Password);
			  statement = conn.createStatement();
			  sql = "select id,name,age,country from author";
			  rs = statement.executeQuery(sql);
			  while(rs.next()) {
				  if(rs.getInt("id")==id) {
					  author.setName(rs.getString("name"));
					  author.setAge(rs.getInt("age"));
					  author.setCountry(rs.getString("country"));
					  break;
				  }
			  }
			  conn.close();
			  } catch(ClassNotFoundException e) {  
			   System.out.println("sorry, can't find the driver!");  
			   e.printStackTrace();  
			  } catch(SQLException e) {
			   e.printStackTrace();
			  } catch(Exception e){
			   e.printStackTrace();
			  }
        //session.setAttribute("author", author.getName());
        //session.setAttribute("list", list);
		//return "booklist";
	}
}
