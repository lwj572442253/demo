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
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>
        function getCookie(name){
            var strcookie = document.cookie;//获取cookie字符串
            var arrcookie = strcookie.split("; ");//分割
            //遍历匹配
            for ( var i = 0; i < arrcookie.length; i++) {
                var arr = arrcookie[i].split("=");
                if (arr[0] == name){
                    return arr[1];
                }
            }
            return "";
        }

        window.onload = function () {
            var result = getCookie(${sessionScope.get("currentStudent").id});
            debugger;
            var username = "${sessionScope.get("currentStudent").name}";
            var password = "${sessionScope.get("currentStudent").password}";
            if(result != null && result != ""){
                <%--window.location.href="<%=request.getContextPath()%>/student/getStudentInfo?pageNo=1";--%>
                <%--$.ajax({--%>
                    <%--url:"<%=request.getContextPath()%>/login/returnGrid",--%>
                    <%--type:"post",--%>
                    <%--data:{--%>
                        <%--"username" : username,--%>
                        <%--"password" : password--%>
                    <%--},--%>
                    <%--dataType:"json",--%>
                    <%--success:function (data) {--%>
                        <%--window.location.href="<%=request.getContextPath()%>/student/getStudentInfo?pageNo=1";--%>
                    <%--}--%>
                <%--});--%>

            }

        }
    </script>
</head>
<body>
    <form action="<%=request.getContextPath()%>/login/returnGrid" method="post">
        用户名：<input type="text" id="username" name="username"/>
        密  码：<input type="password" id="password" name="password"/>
        <input type="submit" value="提交"/>
    </form>
    <div>${msg}</div>
    <br>
</body>
</html>
