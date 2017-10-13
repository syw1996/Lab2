<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function noNumbers(e)//让label只接收数字
{
var keynum
var keychar
var numcheck

if(window.event) // IE
  {
  keynum = e.keyCode
  }
else if(e.which) // Netscape/Firefox/Opera
  {
  keynum = e.which
  }

keychar = String.fromCharCode(keynum)
numcheck = /\d/
return numcheck.test(keychar)
}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Author From</title>
</head>
<body>
	<%int flag = (Integer)session.getAttribute("isbnExist"); 
	  if(flag == 1){
		  out.println("<script>alert(\"ISBN码已存在!\")</script>");
	  }%>
    <form action="add" method="post">
        <fieldset>
        <legend>添加书籍</legend>
            <p>
                <label>ISBN：</label> <input type="text" id="isbn" name="isbn" pattern="^[0-9]{1,15}$" oninvalid="setCustomValidity('格式不正确，正确格式为：1-15位数字');"
                    tabindex="1" value="" required />
            </p>
            <p>
                <label>书名：</label> <input type="text" id="title" name="title"
                    tabindex="2" value="" required />
            </p>
            <p>
                <label>作者：</label> <input type="text" id="id" name="id" pattern="^[0-9]{1,15}$" oninvalid="setCustomValidity('格式不正确，正确格式为：1-15位数字');"
                    tabindex="3" value="" required />
            </p>
            <p>
                <label>出版社：</label> <input type="text" id="publisher" name="publisher"
                    tabindex="4" value="" required />
            </p>
            <p>
                <label>出版日期：</label> <input type="text" id="publishdate" name="publishdate"  pattern="^[0-9]{7,8}$" oninvalid="setCustomValidity('格式不正确，正确格式为：8位数字');"
                    tabindex="5" value="" required />
            </p>
            <p>
                <label>价格：</label> <input type="text" id="price" name="price" pattern="^\d+(\.\d+)?$" oninvalid="setCustomValidity('格式不正确，正确格式为：小数或整数');"
                    tabindex="6" value="" required />
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="7" value="取消"> <input
                    id="submit" type="submit" tabindex="8" value="添加">
            </p>
        </fieldset>
    </form>
    
    <form action="home">
    	<p></p>
    	<button>返回</button>
    </form>
</body>
</html>