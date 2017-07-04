<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="x-ua-compatible" content="IE=Edge" >
    <title>暨南大学附属第一医院招聘平台新增简历</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--公用样式-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="all" />
<!--页面样式-->
<link rel="stylesheet" type="text/css" href="css/subpage.css" media="all" />
<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css" media="all" />
<!--美化样式-->
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" media="all" />
<!--JQ插件引用-->
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery.PrintArea.js"></script>
<!-- 消息样式 -->
<link type="text/css" rel="stylesheet" href="ale/showBo.css">
<script type="text/javascript" src="ale/showBo.js"></script>
<!--弹窗插件-->
<script type="text/javascript" src="layer/layer.js"></script>
<!--JQ美化插件引用-->
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<!--下拉美化引用-->
<script type="text/javascript" src="js/drop.js"></script>
<!--时间插件引用-->
<script type="text/javascript" src="laydate/laydate.js"></script>
<!-- 表单验证 -->
<script type="text/javascript" src="js/user.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/messages_zh.js"></script>
<!-- 自己写的JS -->
<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>

<script>
$(function() {
		$('#pictureUp').uploadify({
			'height'        : 27,   
	        'width'         : 90,    
	        'buttonText'    : '选择照片',
			'swf'      : 'uploadify/uploadify.swf',
			'uploader' : '/hjsoft_zp/user/imgfile',
			'fileObjName'   : 'file',
			'onUploadSuccess':function(file,data,response){
                        /* alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data); */
                        $("#picture").attr("src",data);  
                        $("#picture").show();
                    },
		});
		
		$('#fileUp').uploadify({
			'height'        : 27,   
	        'width'         : 90,    
	        'buttonText'    : '选择附件上传',
			'swf'      : 'uploadify/uploadify.swf',
			'uploader' : '/hjsoft_zp/user/onefile',
			'fileObjName'   : 'file',
		});
		
		$.validator.setDefaults({
    	submitHandler: function() {
      		resumesubmit();
    		}
		});
		$().ready(function() {
    		/* $("#userData").validate();
			$("#userXL").validate();
    		$("#userGZ").validate();
    		$("#userJT").validate();
    		$("#userXS").validate();
    		$("#userKY").validate();
    		$("#userRC").validate();
    		$("#userHJ").validate(); */
		});
		
		
		jQuery.validator.addMethod("isPositiveInteger", function(value, element) {
            var isPositiveInteger= /^([0-9]*[1-9][0-9]*)$/;
            return this.optional(element) || isPositiveInteger.test(value);
        }, "请输入正整数");

        jQuery.validator.addMethod("isDecimals", function(value, element) {
            /* var isPositiveInteger= /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/; */
            //未限制小数点
            var isPositiveInteger= /^(?:[1-9][0-9]*(?:\.[0-9]+)?|0(?:\.[0-9]+)?)$/;
            return this.optional(element) || isPositiveInteger.test(value);
        }, "请输入正确的数值,最多两位小数");


        $("#spuInfoSaveForm").validate({
            submitHandler : function(form) {  //验证通过后的执行方法
                //当前的form通过ajax方式提交（用到jQuery.Form文件）
                $(form).ajaxSubmit({
                    type: "post",
                    url: baseURL + "/spuInfo/save",
                    success: function(result){
                        if(result.status==0){
                            promptAgain("保存成功");
                            $(document).on('click','#again_true',function(){
                                $(this).closest('.prompt_modal2').remove();
                                location.href=baseURL +"/skuInfo/index";
                            });
                        }else {
                            promptAgain(result.errors)
                        }
                    }
                });
            },
            meta: "validate"
        });
	});
</script>


<style>
label.error{
	position:relative;
    top: 9px;
    left:-60px;
	color:red;
}
</style>

</head>
  
  <body>

<!--头部开始-->
<div class="public-head">
	
	<!--中间开始-->
	<div class="public-middle">
		
		<!--logo开始-->
		<div class="logo">
			<a herf="#"><img src="images/logo.png" alt="logo"/></a>
		</div>
		<!--logo结束-->
		
		<span class="greet">欢迎来到暨南大学附属第一医院招聘平台！</span>
		
	</div>
	<!--中间结束-->
	
</div>
<!--头部结束-->

<!--主体开始-->
<div class="public-content">
	
	<!--第一行开始-->
	<div class="public-line">
		
		<!--导航条开始-->
		<div class="basicbox size-short">
			<div class="main-nav">
				<li>
					<a href="/hjsoft_zp/user/zpdt?pagenum=1">招聘动态</a>
				</li>
				<li>
					<a href="/hjsoft_zp/user/zpzw?pagenum=1">招聘职位</a>
				</li>
				<li>
					<a href="/hjsoft_zp/user/rzzn2?pagenum=1">入职指南</a>
				</li>
				<li>
					<!-- <a href="/hjsoft_zp/user/rczc">人才政策</a> -->
					<a href="/hjsoft_zp/user/rczc2?pagenum=1">人才政策</a>
				</li>
				<li class="active">
					<a href="/hjsoft_zp/user/wdjl">简历系统</a>
				</li>
			</div>
		</div>
		<!--导航条结束-->
		
		<!--banner开始-->
		<div class="basicbox size-long">
			<div class="main-banner">
				<img src="images/banner.jpg" alt="banner">
			</div>
		</div>
		<!--banner结束-->
		
	</div>
	<!--第一行结束-->
	
	<!--第二行开始-->
	<div class="public-line">
		
		<!--用户登录开始-->
		<form id="userInfo">
		<div class="basicbox size-short">
			<div class="user-login">
				
				<div class="user-login-head">在线用户</div>
				
				<div class="user-login-body">
					<ul class="user-login-area">
							<img src="images/head.png" alt="head" style="display:block;margin: 0 auto"/><br/>
							<span id="" style=" color: #FF8C00;font-size: 14px">用户:  ${userinfo.username }  </span><a href="/hjsoft_zp/user/logout" style="font-size: 14px">退出登录</a>
					</ul>
						<div class="user-login-message">
						地址：广州市黄埔大道西613号<br />
						电话：020-38688821<br />
						<span style="font-family:'Times New Roman'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		    				  020-38688822<br />
		    			<span style="font-family:'Times New Roman'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							  020-38688823<br />
							邮编：510630<br />
                        	技术支持：<br />广州宏景软件科技有限公司<br />
                        	QQ：1492318870<br />
                        	电话：020-85549399<br />
                        <span style="font-family:'Times New Roman'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        		  020-82352092<br />
					</div>
				</div>
				
			</div>
		</div>
		</form>
		
		<!--用户登录结束-->
		
		<!--内容展示开始-->
		<div class="basicbox size-long">
			
			<div class="showbox">
				
				<!--标题条开始-->
				<div class="showbox-head">
					
					<div class="showtitle-left">
						
						<!--标题样式2开始-->
						<ul class="show-title-2">
							<div class="title">简历系统</div>
						</ul>
						<!--标题样式2结束-->
					</div>
					
				<!--startprint--> 
					<div class="showtitle-right">
						
						<!--面包屑导航开始-->
						<div class="crumbs">
							
							<span>您当前所在位置：</span>
							<a href="#">首页</a>
							<em>></em>
							<a href="#">人才招聘</a>
							<em>></em>
							<a href="#">简历系统</a>
						</div>
						<!--面包屑导航结束-->
						
					</div>
					
				</div>
				<!--标题条结束-->
				
				<!--内容框开始-->
				<div class="showbox-tabel" id="resumetable">
					
					<!--基本信息开始-->
					<div class="form-type-2">
						
						<div class="titlebar">
							<div class="title-icon">
								<img src="images/icon-i1.png"/>
							</div>
							<div class="title-content">
								基本信息
							</div>
						</div>
						<%-- <form id="uploadForm">
							<li style="height: auto;text-align: center;">
								<div style="position:relative;width: 90px;height: 110px;margin: 0 auto; background: url(images/photo.jpg);">
									<c:if test="${userjl.userData.zp=='1' }">
									<img id="picture" src="img/${userjl.userData.pkid }/${userjl.userData.zp }" width="100%" height="100%"/><!--此处img标签应该在上传头像后出现-->
									<input id="pictureUp" type="file" name="file" style="position: absolute;width: 90px;height: 110px;top: 0;left: 0;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;"/>
								</div>
								<!-- <input class="submit" type="button" value="上传照片" style="margin: 20px auto;" onclick="doUp('/hjsoft_zp/user/imgfile')"/> -->
							</li>
						</form> --%>
						<ul class="basic-message">
							<form id="userData">
								<input name="pkid" type="text" value="${userjl.userData.pkid}" style="display: none;"/>
							<li>
								<span class="name" >姓名：</span>
								<input class="inputbox" name="xm" value="${userjl.userData.xm}" type="text" required/>
								<span class="name">身高：</span>
								<input class="inputbox" name="sg" type="text" value="${userjl.userData.sg}" required/>
								<span class="name" style="display: none;">电话：</span>
								<input class="inputbox" name="lxdh" value="${userjl.userData.lxdh}" type="text" style="display: none;"/>
							</li>
							<li>
								<span class="name">性别：</span>
								<div class="dropbox">
									<div class="drop">
										<input class="hid" name="xb" type="text" value="${userjl.userData.xb}" style="display: none;"/>
			                            <div class="title" id="title" id="xb">${userjl.userData.xb}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label name="xb" value="0">
			                                <c:forEach items="${option.xb }" var="xb">
			                                <a class="item ellipsis" title="${xb }">${xb }</a>
			                            </c:forEach>
			                            </label>
			                        </div>
								</div>
								<span class="name">电子邮件:</span>
								<input class="inputbox" name="dzyj" type="text" value="${userjl.userData.dzyj}" required/>
							</li>
							<li>
								<span class="name">出生日期：</span>
								<input type="text" class="inputbox date-icon" name="csrq" id="startdate1" value="${userjl.userData.csrq}">
								<%-- <input class="inputbox" name="csrq" type="text" value="${userjl.userData.csrq}" /> --%>
								<span class="name">身份证号：</span>
								<input class="inputbox" name="sfzh" type="text" value="${userjl.userData.sfzh}" />
							</li>
							<li>
								<span class="name">年龄：</span>
								<input class="inputbox" name="nl" type="text" value="${userjl.userData.nl}" required/>
								<span class="name">政治面貌：</span>
								<div class="dropbox" name="zzmm">
									<div class="drop" >
										<input class="hid" name="zzmm" type="text" value="${userjl.userData.zzmm}" style="display: none;"/>
			                            <div class="title" id="title">${userjl.userData.zzmm}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.zzmm }" var="zzmm">
			                                <a class="item ellipsis" title="${zzmm }">${zzmm }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
							</li>
							<li>
								<span class="name">工龄：</span>
								<input class="inputbox" name="gl" type="text" value="${userjl.userData.gl}" required/>
								<span class="name">籍贯：</span>
								<div class="dropbox" name="jg" >
									<div class="drop" id="jg">
										<input class="hid" name="jg" type="text" value="${userjl.userData.jg}" style="display: none;"/>
			                            <div class="title" id="title">${userjl.userData.jg}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.jg }" var="jg">
			                                <a class="item ellipsis" title="${jg }">${jg }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
							</li>
							<li>
								<span class="name">婚姻：</span>
								<div class="dropbox" name="hy" >
									<div class="drop" >
										<input class="hid" name="hy" type="text" value="${userjl.userData.hy}" style="display: none;"/>
			                            <div class="title" id="title">${userjl.userData.hy}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.hyzz }" var="hyzz">
			                                <a class="item ellipsis" title="${hyzz }">${hyzz }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
								<span class="name">是否有执业资格：</span>
								<div class="dropbox" name="sfyzyzg" >
									<div class="drop" >
										<input class="hid" name="sfyzyzg" type="text" value="${userjl.userData.sfyzyzg}" style="display: none;"/>
			                            <div class="title" id="title">${userjl.userData.sfyzyzg}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.sf }" var="sf">
			                                <a class="item ellipsis" title="${sf }">${sf }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
							</li>
							<li>
								<span class="name">民族：</span>
								<div class="dropbox" name="mz" >
									<div class="drop" id="mz">
										<input class="hid" name="mz" type="text" value="${userjl.userData.mz}" style="display: none;"/>
			                            <div class="title" id="title">${userjl.userData.mz}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.mz }" var="mz">
			                                <a class="item ellipsis" title="${mz }">${mz }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
								<span class="name">英语水平：</span>
								<div class="dropbox">
									<div class="drop">
										<input class="hid" name="yysp" type="text" value="${userjl.userData.yysp}" style="display: none;"/>
			                            <div class="title" id="title">${userjl.userData.yysp}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.yysp }" var="yysp">
			                                <a class="item ellipsis" title="${yysp }">${yysp }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
							</li>
							<li>
								<span class="name">现住址：</span>
								<input class="inputbox" name="xzz"  type="text" value="${userjl.userData.xzz}" required/>
								<span class="name">应届生：</span>
								<div class="dropbox" name="yjs" >
									<div class="drop" >
										<input class="hid" name="ssyj" type="text" value="${userjl.userData.ssyj}" style="display: none;"/>
			                            <div class="title" id="title">${userjl.userData.ssyj}</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.sf }" var="sf">
			                                <a class="item ellipsis" title="${sf }">${sf }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
							</li>
							<%-- <li>
								<span class="name">身高：</span>
								<input class="inputbox" name="sg" type="text" value="${userjl.userData.sg}"/>
							</li> --%>
					   		</form>
						</ul>
						
					</div>
					<!--基本信息结束-->
					
					<!--学历及学位开始-->
					<form id="userXL">
					<div class="form-type-2">
						
						<div class="titlebar">
							<div class="title-icon">
								<img src="images/icon-i2.png"/>
							</div>
							<div class="title-content">
								学历及学位
							</div>
						</div>
						
						<dl class="resume-table">
							<dt>
								<div class="item" style="width: 12.5%;">入学时间</div>
								<div class="item" style="width: 12.5%;">毕业时间</div>
								<div class="item" style="width: 15%;">毕业院校及单位</div>
								<div class="item" style="width: 13.2%">所学专业</div>
								<div class="item" style="width: 15%">学历</div>
								<div class="item" style="width: 15%">学位</div>
								<div class="item" style="width: 15%">办学形式</div>
							</dt>
							<c:forEach items="${userjl.userXL}" var="userxlData" varStatus="i">
							<dd>
							<input name="pkid" type="text" value="${userxlData.pkid}" style="display: none;"/>	
							<input name="kid" type="text" value="${userxlData.kid}" style="display: none;"/>	
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" name="rxsj" style="width: 37%;" id="startdate1" value="${userxlData.rxsj}" />
									至
									<input type="text" class="inputbox date-icon" name="bysj" style="width: 37%;" id="enddate1" value="${userxlData.bysj}"  />
								</div>
								<div class="item" style="width: 15%;">
									<input type="text" class="inputbox" name="byxx" style="width: 85%;" value="${userxlData.byxx}" />
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="syzy" value="${userxlData.syzy}" style="display: none;">
				                            <div class="title" id="title">${userxlData.syzy}</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.zy }" var="zy">
			                                <a class="item" title="${zy }">${zy }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop" >
											<input type="text" class="hid" name="xl" value="${userxlData.xl}" style="display: none;">
				                            <div class="title" id="title">${userxlData.xl}</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label >
				                            <c:forEach items="${option.xl }" var="xl">
			                                <a class="item ellipsis" title="${xl }">${xl }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop" >
											<input type="text" class="hid" name="xw" value="${userxlData.xw}" style="display: none;">
				                            <div class="title" id="title">${userxlData.xw}</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label >
				                            <c:forEach items="${option.xw }" var="xw">
			                                <a class="item ellipsis" title="${xw }">${xw }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop" >
											<input type="text" class="hid" name="xxxs" value="${userxlData.xxxs}" style="display: none;"/>
				                            <div class="title" id="title" name="xxxs">${userxlData.xxxs}</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                                <c:forEach items="${option.xxxs }" var="xxxs">
			                                	<a class="item ellipsis" title="${xxxs }">${xxxs }</a>
			                            		</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
							</dd>
							</c:forEach>
						</dl>
					</div>
					<!--学历及学位结束-->
					</form>
					
					<!--工作经历开始-->
					<form id="userGZ">
					<div class="form-type-2">
						<div class="titlebar">
							<div class="title-icon">
								<img src="images/icon-i3.png"/>
							</div>
							<div class="title-content">
								工作经历
							</div>
						</div>
						
						<dl class="resume-table">
							<dt>
								<div class="item" style="width: 12.5%;">入职时间</div>
								<div class="item" style="width: 12.5%;">离职时间</div>
								<div class="item" style="width: 35%;">担任职务</div>
								<div class="item" style="width: 20%">职称</div>
								<div class="item" style="width: 20%">证明人</div>
							</dt>
						<c:forEach items="${userjl.userGZ}" var="usergzData" varStatus="i">
							<dd>
							<input name="pkid" type="text" value="${usergzData.pkid}" style="display: none;"/>
							<input name="kid" type="text" value="${usergzData.kid}" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" name="qssj" style="width: 37%;" value="${usergzData.qssj}"/>
									至
									<input type="text" class="inputbox date-icon" name="zzsj" style="width: 37%;"value="${usergzData.zzsj}"/>
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" name="csgz" style="width: 88%;" value="${usergzData.csgz}"/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="zc" value="${usergzData.zc}" style="display: none;">
				                            <div class="title" id="title">${usergzData.zc}</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.zc }" var="zc">
			                                <a class="item ellipsis" title="${zc }">${zc }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="zmr" style="width: 80%;" value="${usergzData.zmr}"/>
								</div>
							</dd>
						</c:forEach>
							
						</dl>
						
					</div>
					<!--工作经历结束-->
					</form>
					
					<!--家庭成员及社会关系开始-->
					<form id="userJT">
					<div class="form-type-2">
						
						<div class="titlebar">
							<div class="title-icon">
								<img src="images/icon-i4.png"/>
							</div>
							<div class="title-content">
								家庭成员及社会关系
							</div>
						</div>
						
						<dl class="resume-table">
							<dt>
								<div class="item" style="width: 12.5%;">成员名称</div>
								<div class="item" style="width: 12.5%;">与本人关系</div>
								<div class="item" style="width: 20%;">工作单位及职务</div>
								<div class="item" style="width: 35%">成员联系地址</div>
								<div class="item" style="width: 20%">成员联系电话</div>
							</dt>
							<c:forEach items="${userjl.userJT }" var="userjtData" varStatus="i">
							<dd>
							<input name="pkid" type="text" value="${userjtData.pkid}" style="display: none;"/>
							<input name="kid" type="text" value="${userjtData.kid}" style="display: none;"/>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="cyxm"  style="width: 70%;" value="${userjtData.cyxm }"/>
								</div>
								<div class="item" style="width: 12.5%;">
										<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="gx" value="${userjtData.gx}" style="display: none;">
				                            <div class="title" id="title">${userjtData.gx}</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label style="display: none;">
				                            <c:forEach items="${option.gx }" var="gx">
			                                <a class="item ellipsis" title="${gx }">${gx }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
										</div>
								</div>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" name="gzdw" style="width: 80%;" value="${userjtData.gzdw }"/>
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" name="cydz" style="width: 88%;" value="${userjtData.cydz }"/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="cydh" style="width: 80%;" value="${userjtData.cydh }"/>
								</div>
							</dd>
							</c:forEach>
						</dl>
						
					</div>
					<!--家庭成员及社会关系结束-->
					</form>
					
					<!--学术论文开始-->
					<form id="userXS">
					<div class="form-type-2">
						
						<div class="titlebar">
							<div class="title-icon">
								<img src="images/icon-i5.png"/>
							</div>
							<div class="title-content">
								近5年学术论文
							</div>
						</div>
						
						<dl class="resume-table">
							<dt>
								<div class="item" style="width: 5%;">篇数</div>
								<div class="item" style="width: 12.5%;">发表日期</div>
								<div class="item" style="width: 10%;">作者排名</div>
								<div class="item" style="width: 12.5%">期刊名</div>
								<div class="item" style="width: 10%">期刊等级</div>
								<div class="item" style="width: 20%">最高影响因子</div>
								<div class="item" style="width: 30%">论文著作完成标识</div>
							</dt>
							
							<c:forEach items="${userjl.userXS }" var="userxsData" varStatus="i">
							<dd>
							<input name="pkid" type="text" value="${userxsData.pkid}" style="display: none;"/>
							<input name="kid" type="text" value="${userxsData.kid}" style="display: none;"/>
								<div class="item" style="width: 5%;">
									<input type="text" class="inputbox" name="lwnc" style="width: 70%;text-align: center;" value="${userxsData.lwnc }"/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox date-icon" name="fbrq" style="width: 70%;"id="startdate1" value="${userxsData.fbrq }">
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="zzpm" style="width: 70%;" value="${userxsData.zzpm }"/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="qkh" style="width: 70%;" 	value="${userxsData.qkh }"/>
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="jh" style="width: 70%;" value="${userxsData.jh }"/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="issnh" style="width: 80%;" value="${userxsData.issnh }"/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="qkdj" style="width: 85%;" value="${userxsData.qkdj }"/>
								</div>
							</dd>
							</c:forEach>
						
						</dl>
					</div>
					</form>
					<!--学术论文结束-->
					
					<!--科研项目开始-->
					<form id="userKY">
					<div class="form-type-2">
						
						<div class="titlebar">
							<div class="title-icon">
								<img src="images/icon-i6.png"/>
							</div>
							<div class="title-content">
								近5年科研项目
							</div>
						</div>
						
						<dl class="resume-table">
							<dt>
								<div class="item" style="width: 30%;">科研项目</div>
								<div class="item" style="width: 20%;">项目级别</div>
								<div class="item" style="width: 20%;">经费数量</div>
								<div class="item" style="width: 30%;">本人项目中排名</div>
							</dt>
							<c:forEach items="${userjl.userKY }" var="userkyData" varStatus="i">
							<dd>
							<input name="pkid" type="text" value="${userkyData.pkid}" style="display: none;"/>
							<input name="kid" type="text" value="${userkyData.kid}" style="display: none;"/>
								<div class="item" style="width: 30%;">
									<input type="text" class="inputbox" name="hjkk" style="width: 80%;" value="${userkyData.hjkk}"/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xmjj" value="${userkyData.xmjj}" style="display: none;">
				                            <div class="title" id="title">${userkyData.xmjj}</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xmjj }" var="xmjj">
			                                <a class="item ellipsis" title="${xmjj }">${xmjj }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="xmjf" style="width: 85%;" value="${userkyData.xmjf}"/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="pm" style="width: 85%;" value="${userkyData.pm}"/>
								</div>
							</dd>
							</c:forEach>
						
						</dl>
					</div>
					<!--科研项目结束-->
					</form>
					
					<form id="userRC">
					<!--人才工程开始-->
					<div class="form-type-2">
						
						<div class="titlebar">
							<div class="title-icon">
								<img src="images/icon-i7.png"/>
							</div>
							<div class="title-content">
								人才工程
							</div>
						</div>
						
						<dl class="resume-table">
							<dt>
								<div class="item" style="width: 20%;">人才类别</div>
								<div class="item" style="width: 20%;">批准单位名称</div>
								<div class="item" style="width: 15%;">批准单位级别</div>
								<div class="item" style="width: 15%;">批准时间</div>
								<div class="item" style="width: 30%;">享受待遇</div>
							</dt>
							<c:forEach items="${userjl.userRC }" var="userrcData" varStatus="i">
							<dd>
								<input name="pkid" type="text" value="${userrcData.pkid}" style="display: none;"/>
								<input name="kid" type="text" value="${userrcData.kid}" style="display: none;"/>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" style="width: 80%;" name="rclb" value="${userrcData.rclb}"/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" style="width: 80%;" name="pzdwmc" value="${userrcData.pzdwmc}"/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzdwjb" value="${userrcData.pzdwjb}"/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzsj" value="${userrcData.pzsj}"/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" style="width: 85%;" name="ssdy" value="${userrcData.ssdy}"/>
								</div>
							</dd>
							</c:forEach>
						</dl>
						
					</div>
					<!--人才工程结束-->
					</form>
					<!--获奖情况开始-->
					<form id="userHJ">
					<div class="form-type-2">
						
						<input name="pkid" type="text" value="${userjl.userHJ.pkid}" style="display: none;"/>
						<div class="titlebar" style="border-bottom: none;">
							<div class="title-icon">
								<img src="images/icon-i7.png"/>
							</div>
							<div class="title-content">
								获奖情况
							</div>
						</div>
						
						<textarea placeholder="请在此填写您的获奖日期和所获奖项" name="hjkk" >${userjl.userHJ.hjkk}</textarea>
						
					</div>
					</form>
					
					<!-- <form id="uploadForm">  
           &nbsp;&nbsp;&nbsp;&nbsp;上传简历附件：<input type="file" name="file"/>  
                 			<input type="button" value="提交" onclick="doUpload('/hjsoft_zp/user/onefile')"/>  
   					</form>  -->
   					<input id="fileUp" type="file" name="file"/> 
					<!--获奖情况结束-->
					
					<c:forEach var="me" items="${fileNameMap}">  
        				<c:url value="/user/downFile" var="downurl">  
            			<c:param name="filename" value="${me.key}"></c:param>  
        				</c:url>  
        	&nbsp;&nbsp;&nbsp;&nbsp;${me.value}<a href="${downurl}">下载</a>  
        			<br/>  
    				</c:forEach>
					<!--获奖情况结束-->
					
					<div class="buttonbox">
						<input class="submit" type="submit" value="提交简历" onclick="resumesubmit()"/> <!-- onclick="resumesubmit()" -->
						<input class="submit" type="submit" value="打印" onclick="preview2()"/>
					</div>
				</div>
				<!--endprint-->
				<!--内容框开结束-->
				
			</div>
			
		</div>
		<!--内容展示结束-->
		
	</div>
	<!--第二行结束-->
	
</div>
<!--主体结束-->


<!--脚部开始-->
<div class="public-footer">
       广州华侨医院 </a>  地址：广州市天河区黄埔大道西613号，邮编：510630，电话：(020)38688021 (020)38688022 (020)38688023<br>
    技术支持：<a href="#">广州宏景软件科技有限公司</a>
</div>

<!--脚部结束-->

<!--css3样式控制-->
<script>
	$(document).ready(function(){
	$(".resume-table dd:first-of-type").css("border","none");
});
</script>

<!--登录验证-->
<script>
	function login() {
	var userinfo={pkid:$("#pkid").val(),username:$("#username").val(),passwodr:$("#password").val()}
	$.ajax({
			type : "post",
			url : "/hjsoft_zp/user/loginB",
			data : userinfo,
			success : function(data) {
			if(data=="success"){
			alert('登陆成功');
			layer.msg('登录成功，欢迎回来!',{
				skin:'layer-ext-espresso',
				extend:'skin/espresso/style.css',
				icon:1,
				shade:0.5,
				time:2000//提示窗存在时间2秒
					});
			$("#pkid").hide("slow");
			}else if(data=="exist"){
				layer.msg('账号不存在,请注册!',{
				skin:'layer-ext-espresso',
				extend:'skin/espresso/style.css',
				icon:1,
				shade:0.5,
				time:2000//提示窗存在时间2秒
				});
			}	
			
			},
			error: function(jqXHR){
				alert("发生错误"+jqXHR.status)
			}
			});
	}
/* 	$.ajax({
			type : "post",
			url : "/hjsoft_zp/user/loginB",
			data : {
					username : $("#username").val(),
					password : $("#username").val()
					},
			success : function() {
			alert('登陆成功');
			layer.msg('登录成功，欢迎回来!',{
				skin:'layer-ext-espresso',
				extend:'skin/espresso/style.css',
				icon:1,
				shade:0.5,
				time:2000//提示窗存在时间2秒
											});
					}
			});
	} */
	
	function allsub(){
		$("#userData").submit();
	}
	
	function resumesubmit() {
		/* var userxl3 = jsc(userxl); */
		var userdata = jsa("#userData");
		var userxl 	= jsb("#userXL");
		var usergz 	= jsb("#userGZ");
		var userjt 	= jsb("#userJT");
		var userxs 	= jsb("#userXS");
		var userky 	= jsb("#userKY");
		var userrc 	= jsb("#userRC");
		var userhj 	= jsa("#userHJ");
		
		var userJL={userData:userdata,userXL:userxl,userGZ:usergz,userJT:userjt,userXS:userxs,userKY:userky,userHJ:userhj,userRC:userrc};
		
		$.ajax({
			type:"post",
			url:"/hjsoft_zp/user/xzjl",
			contentType : "application/json",
			data : JSON.stringify(userJL),
			success : function(data) {
				if(data=="success"){
				Showbo.Msg.alert("修改简历成功!");
				}
			},
			error: function(jqXHR){
				alert("发生错误"+jqXHR.status)
			}
		})
		//成功提交
}
</script>

<script>
	function submit(){
		$.ajax({
			type : "post",
			url : "/hjsoft_zp/user/wdjl",
			data : {pkid:3},
			success : function() {
			if(data=="success"){
				alert("获取简历成功")
			}
		}
		})
	}
	
</script>

	<script>
		function preview1() {
			bdhtml = window.document.body.innerHTML;
			sprnstr = "<!--startprint-->"; //开始打印的地方
			eprnstr = "<!--endprint-->"; //结束打印标记
			prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
			prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML = prnhtml;
			window.resizeTo(2000,2000);
			window.print();
		}
		
		function preview2() {
		$("#resumetable").printArea(
					{	
						extraCss : "../css/subpage.css,../css/style.css,../css/jquery-ui.min.css,../ale/showBo.css",
					});
		}
	</script>
	<script>
	function jso(c){
		var a=$(c).serializeArray();
		var b={};
		$.each(a,function(index,field){
			b[field.name] = field.value;//通过变量，将属性值，属性一起放到对象中
		});
		return JSON.stringify(b);
	}
	
	function jsa(c){
		var a=$(c).serializeArray();
		var b={};
		$.each(a,function(index,field){
			if(b[field.name]){
				if($.isArray(b[field.name])){
				b[field.name].push(field.value);
				}else{
				b[field.name]=[b[field.name],field.value];
				}
			} else{
				if(field.value==null){
					b[field.name] ="";
				}
				b[field.name] = field.value.replace(" ",",");
			}
		});
		return b;
	}
	
	function jsb(c){
		var a=$(c).serializeArray();
		var jsonData1={};
		$.each(a,function(index,field){
			if(jsonData1[field.name]){
				if($.isArray(jsonData1[field.name])){
				jsonData1[field.name].push(field.value);
				}else{
				jsonData1[field.name]=[jsonData1[field.name],field.value];
				}
			} else{
				jsonData1[field.name] = field.value;
			}
		});
				var vCount = 0;
                // 计算json内部的数组最大长度
                for(var item in jsonData1){
                    var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
                    vCount = (tmp > vCount) ? tmp : vCount;
                }

                if(vCount > 1) {
                    var jsonData2 = new Array();
                    for(var i = 0; i < vCount; i++){
                        var jsonObj = {};
                        for(var item in jsonData1) {
                            jsonObj[item] = jsonData1[item][i];
                        }
                        jsonData2.push(jsonObj);
                    }
                    return JSON.stringify(jsonData2);
                }else{
                    return "{" + JSON.stringify(jsonData1) + "}";
                }
	}
	
	function jsc(jsonData1){
				var vCount = 0;
                // 计算json内部的数组最大长度
                for(var item in jsonData1){
                    var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
                    vCount = (tmp > vCount) ? tmp : vCount;
                }

                if(vCount > 1) {
                    var jsonData2 = new Array();
                    for(var i = 0; i < vCount; i++){
                        var jsonObj = {};
                        for(var item in jsonData1) {
                            jsonObj[item] = jsonData1[item][i];
                        }
                        jsonData2.push(jsonObj);
                    }
                    return JSON.stringify(jsonData2);
                }else{
                    return "{" + JSON.stringify(jsonData1) + "}";
                }
	}
</script>


<!--日期选择调用--> 
<script>
!function(){
laydate.skin('danlan');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo'});//绑定元素
}();

//自定义日期格式
laydate({
    elem: '#resumetable .date-icon',
    format: 'YYYY-MM-DD',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
    }
});

</script>
<!--标签切换-->
<script>
$(document).ready(function(){
	$(".show-title-1 .item").click(function(){
		var titlenum=$(this).index()+2;
		$(".show-title-1 .item").removeClass("active");
		$(this).addClass("active");
		$(".showbox-body").hide();
		$(".showbox-body:nth-child("+titlenum+")").show();
	});
});
</script>
<!--title美化--> 
<script>
  $(function() {
    $( document ).tooltip();
  });
</script>

</body>
</html>
