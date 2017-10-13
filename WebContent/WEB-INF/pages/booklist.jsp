<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.zjn.entity.Book"%>
<%@page import="com.zjn.entity.User"%>
<%@page import="com.zjn.controller.QueryController"%>
<%@page import="com.zjn.controller.BookController"%>
<%@page import="java.util.List" %>
<%@page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js">
</script>
<script>
$(document).ready(function(){
	  $(".show").click(function(){
	    var isbn = $(this).attr("id");
	    var dot = '.';
	    dot = dot + isbn;
	    $(dot).toggle()
	  });
	});
</script>

<script>
$(document).ready(function(){
	$(".del").click(function(){
	var isbn = $(this).attr("id");
	//alert(isbn.toString());
	$.ajax({
	    	type:"POST",
	    	url:"del",
	    	data:{"isbn":isbn.toString()},
	    	datatype:"html",
	    	success:function(data){
	    		location.reload();//重新加载页面
	    		alert('删除成功');
	    	},
	    	error:function(data){
	    		//location.reload();
	    		alert('删除成功');
	    	}
	    })
	  });
});
</script>

<script>
$(document).ready(function(){
	$(".change").click(function(){
	var isbn = $(this).attr("id");
	$.ajax({
	    	type:"POST",
	    	url:"getISBN",
	    	data:{"isbn":isbn.toString()},
	    	datatype:"html",
	    	success:function(data){
	    		//alert(success);
	    		window.location.href = "http://libraryonline.duapp.com/modify";
	    	},
	    	error:function(data){
	    		//alert("fail");
	    		window.location.href = "http://libraryonline.duapp.com/modify";
	    	}
	    })
	  });
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书清单</title>
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form>
<table class="table table-striped">
       <tr>
           <td>序号</td>
           <td>图书题目</td>
           <td>选择</td>
       </tr>
         <%
         	List<Book> list = (List<Book>)session.getAttribute("list");
            User author = (User)session.getAttribute("author");
            session.setAttribute("author", author.getName());
            for(int i=0;i<list.size();i++)
            {
            Book bk = list.get(i);
            %>
           <tr>
			   <td><%=i+1 %></td>
               <td><p class="show" id="<%=bk.getIsbn() %>"><%=bk.getTitle() %></p></td>
               <td><button class="del" id="<%=bk.getIsbn() %>">删除</button>
                   <button class="change" id="<%=bk.getIsbn() %>">修改</button>
               </td>
           </tr>
           <tr class="<%=bk.getIsbn() %>" style="display:none;">
			   <td></td>
			   <td><p>书籍详情:</p></td>
			   <td><p>ISBN:<%=bk.getIsbn() %></p>
			   	   <p>Title:<%=bk.getTitle() %></p>
			       <p>Publisher:<%=bk.getPublisher() %></p>
			       <p>PublishDate:<%=bk.getPublishdate() %></p>
			       <p>Price:<%=bk.getPrice() %></p></td>
		   </tr>
		   
		   <tr class="<%=bk.getIsbn() %>" style="display:none;">
			   <td></td>
			   <td><p>作者详情:</p></td>
			   <td><p>Name:<%=author.getName() %></p>
			       <p>Age:<%=author.getAge() %></p>
			       <p>Country:<%=author.getCountry() %></p></td>
		   </tr>
           
             <%}
        %>
</table>
</form>

<form action="query">
	<p></p>
	<button>返回</button>
</form>

</body>
</html>