<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>login3登录页面</title>
    <script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/MyMvc/Utils.js"></script>
    <script type="text/javascript">
    
        function login(){
             Ajax.request({
                 url : "${pageContext.request.contextPath}/ajaxLogin/handle.do",
                 data : {
                     "usename" : document.getElementById("usename").value,
                     "pwd" : document.getElementById("pwd").value
                 },
                 success : function(xhr) {
                     onData(xhr.responseText);
                 },
                 error : function(xhr) {
                     
                 }
             });
        }
        
        function loginajax(){
        	$.ajax({
        		type:"GET",
        		url:"${pageContext.request.contextPath}/ajaxLogin/handle.do",
        		data : {
                     "usename" : document.getElementById("usename").value,
                     "pwd" : document.getElementById("pwd").value
                 },
                 success : function(xhr) {
                     onData(xhr);
                 },
                 error : function(xhr) {
                     
                 }
        	});
        }
        
        function onData(responseText){
            if(responseText=="success"){
                //window.location.href="index.jsp";//改变url地址
                /*
                window.location.replace("url")：将地址替换成新url，
                该方法通过指定URL替换当前缓存在历史里（客户端）的项目，因此当使用replace方法之后，
                你不能通过“前进”和“后 退”来访问已经被替换的URL，这个特点对于做一些过渡页面非常有用！
                */
                location.replace(g_basePath+"/index.jsp");
            }else{
                alert("用户名和密码错误");
            }
        }
    </script>
  </head>
  
  <body>
    <fieldset>
        <legend>用户登录</legend>
        <form>
            用户名：<input type="text" name="usename" id="usename">
            <br/>
            密码：<input type="text" name="pwd" id="pwd">
            <br/>
            <input type="button" value="登录" onclick="loginajax()"/>
        </form>
    </fieldset>
  </body>
</html>