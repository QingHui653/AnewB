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
    
<script type="text/javascript">
	alert("");
 	function login(){
 		var form = document.forms[0]; 
 		form.action = "${pageContext.request.contextPath}/user/login.cc"; 
 		form.method="post";
 		form.submit();
 	}
</script>
	

</head>
  
  <body>
  	<form>
  	<table>
    <tr>username:<input type="text" name="username"></></tr>
    <tr>password:<input type="password" name="password"></></tr>
    <tr><input type="button" value="login" onclick="login()"></></tr>
    </table>
    </form>
  </body>
</html>
