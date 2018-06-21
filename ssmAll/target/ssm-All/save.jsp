<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/14
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑学生信息</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/jq/jquery-3.2.1.min.js"></script>
    <script>
        var checkNameResult = false;
        var checkAgeResult = false;
        var checkPwdResult = false;

        $(document).ready(function () {
            var work = $("#work").val();
            if(work == "update"){
                checkNameResult = true;
                checkAgeResult = true;
                checkPwdResult = true;
            }

            function checkName() {
                if(work != "update"){
                    $("#name").blur(function () {
                        var name = $("#name").val();
                        var reg = /^[\u4e00-\u9fa5]{2,4}$/;
                        var str= name.replace(/(^\s+)|(\s+$)/g, "");//去除前后的空格
                        if (!reg.test(str)) {
                            $("#nameMsg").html("名字不合法");
                            checkNameResult = false;
                        }else {
                            $.ajax({
                                url:"<%=request.getContextPath()%>/student/getStudentByCondition",
                                type:"post",
                                data:{
                                    "name":name
                                },
                                dataType:"json",
                                success:function (result1) {
                                    var data = result1.data;
                                    if(data != null && data != ""){
                                        $("#nameMsg").html("用户名已存在");
                                        checkNameResult = false;
                                    }else{
                                        $("#nameMsg").html("");
                                        checkNameResult = true;
                                    }
                                }
                            });

                        }
                    });
                }
            }

            function checkAge() {
                $("#age").blur(function () {
                    var age = $("#age").val();
                    var reg = /^[0-9]{1,3}$/;
                    if(!reg.test(age)){
                        $("#ageMsg").html("年龄不合法");
                        checkAgeResult = false;
                    }else {
                        $("#ageMsg").html("");
                        checkAgeResult = true;
                    }
                });
            }

            function checkPwd() {
                $("#password").blur(function () {
                    var password = $("#password").val();
                    var reg = /^[0-9]{6}$/;
                    if(!reg.test(password)){
                        $("#pwdMsg").html("请输入六位数字密码");
                        checkPwdResult = false;
                    }else{
                        $("#pwdMsg").html("");
                        checkPwdResult = true;
                    }
                });
            }

            function token(){
                var token = Math.floor(Math.random()*100000);
                $("#token").val(token);
                // document.getElementById("token").value = token;
            }

            $(function () {
                checkName();
                checkAge();
                checkPwd();
                token();
            });
        });

        function check() {
            debugger;
            if(checkNameResult && checkAgeResult && checkPwdResult){
                return true;
            }else{
                return false;
            }
        }
    </script>
</head>
<body>
    <input type="hidden" id="work" value="${work}"/>
    <form action="<%=request.getContextPath()%>/student/saveStudentInfo" method="post" onsubmit="return check()">
        <input type="hidden" id="token" name="token" value="${token}"/>
        <input type="hidden" id="id" name="id" value="${id}"/>
        <c:choose>
            <c:when test="${not empty id}">
                姓    名：<input type="text" id="name" name="name" value="${name}" readonly/><span id="nameMsg"></span>
            </c:when>
            <c:otherwise>
                姓    名：<input type="text" id="name" name="name" value="${name}"/><span id="nameMsg"></span>
            </c:otherwise>
        </c:choose>
        <br>
        年    龄：<input type="text" id="age" name="age" value="${age}"/><span id="ageMsg"></span>
        <br>
        密    码：<input type="text" id="password" name="password" value="${password}"/><span id="pwdMsg"></span>
        <br>
        <input type="submit" value="提交"/>
    </form>
    <div><a href="<%=request.getContextPath()%>/student/getStudentInfo?pageNo=1">返回首页</a></div>
    <div style="font-size: large">${msg}</div>
</body>
</html>
