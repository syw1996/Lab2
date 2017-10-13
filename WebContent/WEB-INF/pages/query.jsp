<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询作者</title>
</head>
<body>
	<%int flag = (Integer)session.getAttribute("exist"); 
	  if(flag == 0){
		  out.println("<script>alert(\"作者不存在!\")</script>");
	  }%>
    <form action="getlist" method="post">
        <fieldset>
        <legend>查询作者</legend>
            <p>
                <label>Name：</label> <input type="text" id="name" name="name"
                    tabindex="1">
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="2" value="取消"> 
                <input id="submit" type="submit" tabindex="3" value="查询">
            </p>
        </fieldset>
    </form>
    
    <form action="home">
    	<p></p>
    	<button>返回</button>
    </form>
</body>
</html>