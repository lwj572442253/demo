<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/15
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
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
            debugger;
            var result = getCookie("user");
            if(result != null && result != ""){
                window.location.href = "<%=request.getContextPath()%>/login";
            }
        }
    </script>
  </head>
  <body>
      <form action="login">
        用户：<input type="text" name="name" id="name"/>
        密码：<input type="password" name="password" id="password"/>
        <input type="submit" value="提交"/>
      </form>
  </body>
</html>
