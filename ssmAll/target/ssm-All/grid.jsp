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
        function addStudentInfo() {
            $("#add").click(function () {
                window.location.href = "<%=request.getContextPath()%>/save.jsp"
            });
        }

        $(addStudentInfo);
    </script>
</head>
<body>
    <input type="button" value="添加学生信息" id="add"/>
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
                    <a href="<%=request.getContextPath()%>/student/deleteStudentInfo?id=${per.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    共${recordTotal}条记录，共${pageTotal}页，第
    <c:forEach var="i" begin="1" end="${pageTotal}">
        <a href="<%=request.getContextPath()%>/student/getStudentInfo?pageNo=${i}">[${i}]</a>
    </c:forEach>
    页
</body>
</html>
