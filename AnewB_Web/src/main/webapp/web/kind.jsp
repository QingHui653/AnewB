<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/jsp/common/headjsp.jsp" %>
<!-- 上传插件 -->
<script type="text/javascript"
	src="<%=path%>/js/uploadify/jquery.uploadify.js"></script>
<!-- 兼容H5上传 -->
<script src="http://cdn.bootcss.com/plupload/3.1.0/plupload.min.js"></script>
<!-- 表单验证 -->
<script type="text/javascript"
	src="<%=path%>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/validate/messages_zh.js"></script>

<script type="text/javascript"
	src="<%=path%>/js/kindeditor/kindeditor-min.js"></script>

</head>

<script type="text/javascript">
	/* 与kindeditor冲突 只能
	function callback(json) {
		var data = eval('(' + json + ')');
		alert(data.result + data.key + data.msg);
	}; */
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="spuDesc"]', {
			filePostName:'file',
			formatUploadUrl:false,
			uploadJson : 'http://127.0.0.1:8087/File/file/kindUpload?companyId=233&redirectUrl=http://127.0.0.1:8083/AnewB/js/kindeditor/redirect.html',
			allowFileManager : false
		});
	});
</script>
</head>
<body>
	<form id="postForm1" method="post"
		action="http://127.0.0.1:8087/File/file/kindUpload?companyId=233&redirectUrl=http://127.0.0.1:8083/AnewB/js/kindeditor/redirect.html"
		enctype="multipart/form-data" target="hidden_frame">
		<input type="text" name="type" value="pictorialPic" /> 
		<input type="file" name="file" id="upload1" />
		<input type="submit" value="提交"/>
	</form>
	<iframe name='hidden_frame' id="hidden_frame" style='display: none'></iframe>
	<br/>
	<div class="kindor_con">
		<p>商品描述：</p>
		<textarea id="spuDesc" name="spuDesc"style="width: 100%; height: 400px; visibility: hidden;"></textarea>
	</div>
</body>
</html>
