<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Author From</title>
</head>
<body>
    <form action="save" method="post">
        <fieldset>
        <legend>添加作者</legend>
            <p>
                <label>AuthorID：</label> <input type="text" id="id" name="id" value="" required /
                    tabindex="1">
            </p>
            <p>
                <label>Name：</label> <input type="text" id="name" name="name" value="" required /
                    tabindex="2">
            </p>
            <p>
                <label>Age：</label> <input type="text" id="age" name="age" value="" required /
                    tabindex="3">
            </p>
            <p>
                <label>Country：</label> <input type="text" id="country" name="country" value="" required /
                    tabindex="4">
            </p>
            <p id="buttons">
                <input id="reset" type="reset" tabindex="5" value="取消"> <input
                    id="submit" type="submit" tabindex="6" value="添加">
            </p>
        </fieldset>
    </form>
</body>
</html>