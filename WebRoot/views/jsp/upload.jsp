<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/jsp/common/headjsp.jsp" %>
<!-- 上传插件 -->
<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.uploadify.js"></script>
<!-- 兼容H5上传 -->
<script src="http://cdn.bootcss.com/plupload/3.1.0/plupload.min.js"></script>
<!-- 表单验证 -->
<script type="text/javascript" src="<%=path%>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/validate/messages_zh.js"></script>
<script>
$(function() {
		//无法兼容H5,需要更换上传插件
		 $('#pictureUp').uploadify({
			'height'        : 27,   
	        'width'         : 200,    
	        'buttonText'    : '选择照片',
			'swf'      : '../js/uploadify/uploadify.swf',
			'uploader' : '../user/imgfile',
			'fileObjName'   : 'file',
			'onUploadSuccess':function(file,data,response){
                        /* alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);*/ 
                        $("#picture").attr("src",data);  
                        $("#picture").show();
                    },
		}); 
		
		var uploader = new plupload.Uploader({
			runtimes : 'html5,flash',//官网上默认是gears,html5,flash,silverlight,browserplus
			browse_button : 'pickfiles', //这个是点击上传的html标签的id,可以a,button等等
			container: 'container',  //这个是容器的地址，
			url : '../user/imgfile',//上传的地址
			multi_selection:false,//实现大附件分段上传的功能
			auto_start : true,//自动上传
			flash_swf_url : '/js/plugs/plupload/Moxie.swf',
			max_file_size : '10mb',//这是文件的最大上传大小，得跟IIS结合使用
			resize : {width : 320, height : 240, quality : 90},
			/* filters : [  //拦截 比较卡
							{title : "Image files", extensions : "jpg,gif,png"},
							{title : "Zip files", extensions : "zip"}
			], */

			init: {
				// Init事件发生后触发
				PostInit: function() {
					document.getElementById('filelist').innerHTML = '';

					document.getElementById('uploadfiles').onclick = function() {
						uploader.start();
						return false;
					};
				},
				// 当文件添加到上传队列后触发
				FilesAdded: function(up, files) {
					plupload.each(files, function(file) {
						document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
						alert("上传到队列")
					});
				},
				// 会在文件上传过程中不断触发，可以用此事件来显示上传进度
				UploadProgress: function(up, file) {
					document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
				},
				// 当队列中的某一个文件上传完成后触发
				FileUploaded:function(uploader,file,responseObject) {
					alert(responseObject['response']);
					$("#picture").attr("src",responseObject['response']);  
                    $("#picture").show();
				}

				
			}
		});

		uploader.init();
		
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
	<div id="container" style="font: 13px Verdana; background: #eee; color: #333">
	    <a id="pickfiles" href="javascript:;">[Select files]</a> 
	    <a id="uploadfiles" href="javascript:;">[Upload files]</a>
	</div>
	<div id="filelist"></div>
  	
  	<br>
  
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
