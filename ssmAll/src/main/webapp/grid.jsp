<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/14
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <title>个人信息</title>
    <script>
        function del() {
            var msg = "您真的确定要删除吗？\n\n请确认！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        function addStudentInfo() {
            $("#add").click(function () {
                window.location.href = "<%=request.getContextPath()%>/save.jsp"
            });
        }

        $(addStudentInfo);


        var result = false;

        function checkName() {
            $("#name").blur(function () {
                var name = $("#name").val();
                var reg = /^[\u4e00-\u9fa5]{2,4}$/;
                var str = name.replace(/(^\s+)|(\s+$)/g, "");//去除前后的空格
                if (!reg.test(str)) {
                    $("#nameMsg").html("名字不合法");
                    result = false;
                } else {
                    $("#nameMsg").html("");
                    result = true;
                }
            });
        }

        function checkAge() {
            $("#age").blur(function () {
                var age = $("#age").val();
                var reg = /^[0-9]{1,3}$/;
                if(!reg.test(age)){
                    $("#ageMsg").html("年龄不合法");
                    result = false;
                }else {
                    $("#ageMsg").html("");
                    result = true;
                }
            });
        }

        function check() {
            if(result){
                return true;
            }else{
                return false;
            }
        }

        $(function () {
            checkName();
            checkAge();
        });
    </script>
</head>
<body>
    <div id="loginInfo">
        登录信息：${sessionScope.get("currentStudent").name}<a href="<%=request.getContextPath()%>/login/logout?id=${sessionScope.get("currentStudent").id}">&nbsp 注销</a>
    </div>
    <br>
    <form id="search" action="<%=request.getContextPath()%>/student/getStudentInfo" accept-charset="UTF-8" onsubmit="return check()" >
        姓名：<input type="text" name="name" id="name"/><span id="nameMsg"></span>
        年龄：<input type="text" name="age" id="age"/><span id="ageMsg"></span>
        <input type="hidden" name="pageNo" value="1"/>
        <input type="submit" value="搜索"/>
    </form>

    <table border="1px">
        <tr>
            <td width="50">编号</td>
            <td width="50">姓名</td>
            <td width="50">年龄</td>
            <td width="100">注册时间</td>
            <td width="150">更新时间</td>
            <td width="150">操作</td>
        </tr>
        <c:forEach items="${studentInfoList}" var="per">
            <tr>
                <td><c:out value="${per.id}"/></td>
                <td><c:out value="${per.name}"/></td>
                <td><c:out value="${per.age}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${per.createDate}"/>
                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${per.updateDate}"/>
                <td>
                    <a href="<%=request.getContextPath()%>/student/getStudentById?id=${per.id}">修改</a>
                    <a href="<%=request.getContextPath()%>/student/deleteStudentInfo?id=${per.id}" onclick="return del()">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <input type="button" value="添加学生信息" id="add"/>
    共${recordTotal}条记录，共${pageTotal}页，第
    <c:forEach var="i" begin="1" end="${pageTotal}">
        <a href="<%=request.getContextPath()%>/student/getStudentInfo?pageNo=${i}">[${i}]</a>
    </c:forEach>
    页
</body>
</html>
