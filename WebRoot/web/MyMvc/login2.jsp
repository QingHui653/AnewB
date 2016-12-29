<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>login2.jsp登录页面</title>
  </head>
  
  <body>
    <fieldset>
        <legend>用户登录</legend>
        <form action="${pageContext.request.contextPath}/login/handle.do" method="post">
            用户名：<input type="text" value="${param.usename}" name="usename">
            <br/>
            密码：<input type="text" value="${param.pwd}" name="pwd">
            <br/>
            <input type="submit" value="登录"/>
        </form>
    </fieldset>
    <hr/>
    <label style="color: red;">${msg}</label>
  </body>
</html>