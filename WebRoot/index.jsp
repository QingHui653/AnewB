<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
</head>
  
  <body>
  	<form>
  	<table>
    <tr>username:<input type="text" name="username"></></tr>
    <tr>password:<input type="password" name="password"></></tr>
    <tr><input type="button" value="login" onclick="login()"></></tr>
    <tr><input type="button" value="loginFor" onclick="loginFor()"></></tr>
    </table>
    </form>
    
    
    <script type="text/javascript">
 	function login(){
 		alert("进入login")
 		var form = document.forms[0]; 
 		form.action = "${pageContext.request.contextPath}/user/login"; 
 		form.method="post";
 		form.submit();
 	}
 	
 	function loginFor(){
 		alert("进入loginFor")
 		var form = document.forms[0]; 
 		form.action = "${pageContext.request.contextPath}/user/loginFor"; 
 		form.method="post";
 		form.submit();
 	}
</script>
  </body>
</html>
