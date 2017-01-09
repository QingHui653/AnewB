<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../web/common/headjsp.jsp" %>
<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.uploadify.js"></script>
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
</script>

</head>
  
  <body>
	<form id="uploadForm">
		<tr>
			<td><input id="pictureUp" type="file" name="file" style="left: 10%" /></td>
			<td><img id="picture" alt="头像" src="/AnewB/images/Loginimage/avtar.png"></td>
		</tr>
	</form>
	
	<c:forEach var="me" items="${fileNameMap}">  
        <c:url value="/user/downFile" var="downurl">  
        <c:param name="filename" value="${me.key}"></c:param>  
        </c:url>${me.value}<a href="${downurl}">下载</a>  
    <br/>  
    </c:forEach>
	
</body>
</html>
