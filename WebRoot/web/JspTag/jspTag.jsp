<%@ page language="java" import="java.util.*" pageEncoding="Utf-8" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/custom.tld" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>A sample custom tag</title>
</head>
<body>
<ex:Hello/>
</body>
</html>
