<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书查询系统</title>
</head>
<%String s = request.getContextPath();%>
<body style="background: url(../images/background1)">
<center>
<div class="bodypage">

<div class="div1">
<h3>图书查询系统</h3>
</div>

<table>
	<tr>
	<form action="query">
		<button>查询</button>
	</form>
	</tr>
	
	<tr>
	<form action="addbook">
		<button>添加图书</button>
	</form>
	</tr>
	
</table>

</div>
</center>
</body>
</html>