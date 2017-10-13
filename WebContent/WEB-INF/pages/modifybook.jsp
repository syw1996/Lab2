<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改书籍</title>
</head>
<body>
    <form action="commit" method="post">
        <fieldset>
        <legend>修改书籍</legend>
            <p>
                <label>作者：</label> <input type="text" id="id" name="id"
                    tabindex="1">
            </p>
            <p>
                <label>出版社：</label> <input type="text" id="publisher" name="publisher"
                    tabindex="2">
            </p>
            <p>
                <label>出版日期：</label> <input type="text" id="publishdate" name="publishdate"
                    tabindex="3">
            </p>
            <p>
                <label>价格：</label> <input type="text" id="price" name="price"
                    tabindex="4">
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="5" value="取消"> <input
                    id="submit" type="submit" tabindex="6" value="修改">
            </p>
        </fieldset>
    </form>
</body>
</html>