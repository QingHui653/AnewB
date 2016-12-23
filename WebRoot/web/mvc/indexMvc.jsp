<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>

<html>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    <title>演示如果访问MVC所提供的url</title>
  </head>
  <body>
    <a href="user/newb/2">REST</a><br/>
    <a href="user/newb?userId=2">HTTP</a><br/>
    <a href="" onclick="morebean()">MoreBean</a><br>
    <a href="" onclick="bean()">Bean</a>
    
    
  </body>
<script>
	function morebean(){
		var user={
					oid:5,
					password:"123456"
				};
		$.ajax({
			type:"POST",
			url:"/AnewB/user/morebean?userId=3&json="+JSON.stringify(user),
			data:user,
			dataType:"json",
			traditional:true,
			success: function(data){
				console.info(data);
				alert(data);
			}
		})
	}
	
	function bean(){
		var user={
					oid:5,
					username:"wangwu",
					password:"123456"
				};
		$.ajax({
			type:"POST",
			url:"/AnewB/user/bean",
			data:user,
			dataType:"json",
			traditional:true,
			success: function(data){
				console.info(data);
				alert(data);
			}
		})
	}
</script>
</html>
