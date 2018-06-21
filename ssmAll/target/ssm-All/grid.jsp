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
    <title>个人信息</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/b.dialog.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/b.dialog.bootstrap3.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/jq/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jq/jquery-ui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jq/b.dialog.min.js"></script>
    <style>
        .right{
            float:left; width:100px; border:1px solid #000;
            margin-left: 1px;
        }
        .left{
            float:left; width:700px; border:1px solid #000
        }
    </style>

    <script>
        function del() {
            var msg = "您真的确定要删除吗？\n\n请确认！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        function add(){
            $("#add").click(function () {
                bDialog.open({
                    title : '新增学生信息',
                    width : 500,
                    height : 200,
                    url : '<%=request.getContextPath()%>/save.jsp',
                    callback: function (result) {
                        window.location.reload();
                    }
                });
            });
        }

        function update(obj) {
            var parentNode = obj.parentNode;
            var siblingNode = $(parentNode).siblings();
            var id = siblingNode.eq(0).html();

            bDialog.open({
                title: "修改学生信息",
                width: 500,
                height: 200,
                url:"<%=request.getContextPath()%>/student/getStudentById?id="+id+"&work=update",
                callback:function (result) {
                    window.location.reload();
                }
            });
        }

        $(function () {
            add();
            update(obj);
        });

    </script>

</head>
<body>
    <div style="width: 1000px">
        <div class="left">
            <form id="search" action="<%=request.getContextPath()%>/student/getStudentInfo">
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
                            <input type="button" value="修改" onclick="update(this)"/>
                            <a href="<%=request.getContextPath()%>/student/deleteStudentInfo?id=${per.id}" onclick="return del()">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            共${recordTotal}条记录，共${pageTotal}页，第
            <c:forEach var="i" begin="1" end="${pageTotal}">
                <a href="<%=request.getContextPath()%>/student/getStudentInfo?pageNo=${i}">[${i}]</a>
            </c:forEach>
            页
            <input type="button" value="添加学生信息" id="add"/>
        </div>

        <div id="loginInfo" class="right">
            登录信息：<br>
            ${sessionScope.get("currentStudent").name}<a href="<%=request.getContextPath()%>/login/logout?id=${sessionScope.get("currentStudent").id}">&nbsp 注销</a>
        </div>
    </div>
</body>
</html>
