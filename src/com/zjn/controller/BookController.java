package com.zjn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zjn.entity.Book;
import com.zjn.entity.Modify;
import com.zjn.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BookController {
	
	@RequestMapping("/addbook")
    public String Create(Model model, HttpSession session) {
        session.setAttribute("isbnExist", 0);
		return "addBook";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)//book:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
    public String AddBook(@ModelAttribute("SpringWeb")Book book, Model model, HttpSession session) {
		model.addAttribute("book", book);
        int flag = 0;
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
        	  int isbn = book.getIsbn();  //System.out.println(isbn);        	  
        	  //判断isbn是否已经存在
        	  String sql = "select isbn,title,id,publisher,publishdate,price from book";
        	  ResultSet rs = statement.executeQuery(sql);
        	  while(rs.next()) {
        		  if(rs.getInt("isbn")==isbn) {
        			  session.setAttribute("isbnExist", 1);
        			  return "addBook";
        		  }
        	  }
        	  
        	  int id = book.getId();  //System.out.println(id);
        	  float price = book.getPrice();  //System.out.println(price);
        	  int date = book.getPublishdate();  //System.out.println(date);
        	  String title = book.getTitle();  //System.out.println(title);
        	  String publisher = book.getPublisher();  //System.out.println(publisher);
        	  sql = "insert into book(isbn,title,id,publisher,publishdate,price) value("+isbn+",'"+title+"','"+id+"','"+publisher+"','"+date+"','"+price+"')";
        	  System.out.println(sql);
        	  statement.execute(sql);
        	  //判断作者是否在author数据库中
        	  //Url = "jdbc:mysql://localhost:3306/author";
        	  conn = DriverManager.getConnection(Url, User, Password);
        	  statement = conn.createStatement();
        	  sql = "select id,name,age,country from author";
        	  rs = statement.executeQuery(sql);
        	  while(rs.next()) {
        		  if(rs.getInt("id")==id) {
        			  flag = 1;
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
        if(flag == 1) {
        	return "bookdetail";
        }
        else {
        	return "create";//添加作者信息
        }
    }
	
	@RequestMapping("/getISBN")
	public void getIsbn(HttpSession session,HttpServletRequest request) {
		System.out.println(request.getParameter("isbn"));
		int isbn =  Integer.parseInt(request.getParameter("isbn"));
		session.setAttribute("ISBN", isbn);
	}
	
	
	@RequestMapping("/modify")
	public String Modify(HttpSession session,HttpServletRequest request) {
		return "modifybook";
	}
	
	@RequestMapping("/commit")
	public String CommitModify(@ModelAttribute Modify book,HttpSession session) {
		int isbn = (Integer)session.getAttribute("ISBN");
		int id = 0;
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
			  //获取作者id
			  String sql = "select isbn,title,id,publisher,publishdate,price from book";
        	  ResultSet rs = statement.executeQuery(sql);
        	  while(rs.next()) {
        		  if(rs.getInt("isbn")==isbn) {
        			  id = rs.getInt("id");
        		  }
        	  }
			  //修改书
			  if(!book.getId().equals("")) {
				  sql = "update book set id='"+book.getId()+"' where isbn='"+isbn+"'";
				  statement.execute(sql);
			  }
			  if(!book.getPublisher().equals("")) {
				  sql = "update book set publisher='"+book.getPublisher()+"' where isbn='"+isbn+"'";
				  statement.execute(sql);  
			  }
			  if(!book.getPublishdate().equals("")) {
				  sql = "update book set publishdate='"+book.getPublishdate()+"' where isbn='"+isbn+"'";
				  statement.execute(sql);
			  }
			  if(!book.getPrice().equals("")) {
				  sql = "update book set price='"+book.getPrice()+"' where isbn='"+isbn+"'";
				  statement.execute(sql);
			  }
			  
			  System.out.println("success");
			  //获取前面书列表
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
        	  //获取作者
        	  //Url = "jdbc:mysql://localhost:3306/author";
        	  //conn = DriverManager.getConnection(Url, User, Password);
        	  //statement = conn.createStatement();
        	  sql = "select id,name,age,country from author";
        	  rs = statement.executeQuery(sql); //System.out.println(id);
        	  while(rs.next()) {
				  if(rs.getInt("id")==id) {
					  author.setId(id);
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
        System.out.println(list.get(0).getTitle());
        session.setAttribute("list", list);
        session.setAttribute("author", author);
		return "booklist";
	}
}
