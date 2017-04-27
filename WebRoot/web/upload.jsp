<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../web/common/headjsp.jsp" %>
<!-- 上传插件 -->
<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.uploadify.js"></script>
<!-- 表单验证 -->
<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script>
<script>
$(function() {
		$('#pictureUp').uploadify({
			'height'        : 27,   
	        'width'         : 200,    
	        'buttonText'    : '选择照片',
			'swf'      : '../js/uploadify/uploadify.swf',
			'uploader' : '../user/imgfile',
			'fileObjName'   : 'file',
			'onUploadSuccess':function(file,data,response){
                        /* alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data); */
                        $("#picture").attr("src",data);  
                        $("#picture").show();
                    },
		});
	});
//未兼容ie8
function doUpload(url) {  
     var formData = new FormData($("#uploadForm2")[0]);  
     $.ajax({  
          url: url ,  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (returndata) {  
              alert(returndata); 
          },  
          error: function (returndata) {  
              alert(returndata); 
          }  
     });  
}

function allsub(){
		/* vali(); */
		$("#userData").validate();
		$("#userData").submit();
	};

function vali() {
    	$("#userData").validate();
	};	
 
</script>

</head>
  
  <body>
	<form id="uploadForm">
		<tr>
			<td><input id="pictureUp" type="file" name="file" style="left: 10%" /></td>
			<td><img id="picture" alt="头像" src="/AnewB/images/Loginimage/avtar.png"></td>
		</tr>
	</form>
	
	<form id="uploadForm2">  
           上传简历：<input type="file" name="file"/>  
      <input type="button" value="提交" onclick="doUpload('../user/imgfile')"/>  
   	</form>
	
	<form id="userData" action="../user/bean" method="post">
		<span class="name">账号：</span>
		<input name="username" type="text" required/>
		<br/>
		<span class="password">密码：</span>
		<input name="password"/>${ERR_password }
		<br/>
		<input class="submit" type="submit" value="提交简历" onclick="allsub()"/>
	</form>
	
	<c:forEach var="me" items="${fileNameMap}">  
        <c:url value="/user/downFile" var="downurl">  
        <c:param name="filename" value="${me.key}"></c:param>  
        </c:url>${me.value}<a href="${downurl}">下载</a>  
    <br/>  
    </c:forEach>
	
</body>
</html>
