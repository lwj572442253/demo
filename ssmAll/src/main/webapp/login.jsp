<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/14
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/login/returnGrid" method="post">
        用户名：<input type="text" id="username" name="username"/>
        密  码：<input type="password" id="password" name="password"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
