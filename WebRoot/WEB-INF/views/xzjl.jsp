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
<!--美化样式-->
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" media="all" />
<!-- 消息样式 -->
<link type="text/css" rel="stylesheet" href="ale/showBo.css">
<script type="text/javascript" src="ale/showBo.js"></script>
<!--JQ插件引用-->
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<!--弹窗插件-->
<script type="text/javascript" src="layer/layer.js"></script>
<!--JQ美化插件引用-->
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<!--下拉美化引用-->
<script type="text/javascript" src="js/drop.js"></script>	
	
<script type="text/javascript" src="laydate/laydate.js"></script>
<!-- 自己写的JS -->
<script type="text/javascript" src="js/user.js"></script>
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
							<span id="" style=" color: #FF8C00;font-size: 14px">用户:  ${userinfo.username } </span><a href="/hjsoft_zp/user/logout" style="font-size: 14px">退出登录</a>
						</ul><br/>
						<c:if test="${userinfo.pkid =='1'}">
						<a href="/hjsoft_zp/sync/zw" style="font-size: 14px">同步职位</a>
						<a href="/hjsoft_zp/sync/jl" style="font-size: 14px">同步简历</a>
						<a href="/hjsoft_zp/sync/sq" style="font-size: 14px">同步申请</a>
						</c:if>
						<div class="user-login-message">
						地址：广州市黄埔大道西613号<br />
						电话：020-38688821<br />
						<span style="font-family:'Times New Roman'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		    				  020-38688822<br />
		    			<span style="font-family:'Times New Roman'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							  020-38688823<br />
						邮编：510630
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
							<div class="title">新增简历</div>
						</ul>
						<!--标题样式2结束-->
					</div>
					
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
						<!-- <form id="uploadForm">  
                                &nbsp;&nbsp;&nbsp;&nbsp;上传简历附件：<input type="file" name="file"/>  
                 			    <input type="button" value="提交" onclick="doUpload('/hjsoft_zp/user/onefile')"/>  
   						     </form>  -->
						<form id="uploadForm">
							<li style="height: auto;text-align: center;">
								<div style="position:relative;width: 90px;height: 110px;margin: 0 auto; background: url(images/photo.jpg);">
									<img src="" width="100%" height="100%"/><!--此处img标签应该在上传头像后出现-->
									<input type="file" name="file" style="position: absolute;width: 90px;height: 110px;top: 0;left: 0;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;"/>
								</div>
								<input class="submit" type="button" value="上传照片" style="margin: 20px auto;" onclick="doUpload('/hjsoft_zp/user/imgfile')"/>
							</li>
						</form>
						<ul class="basic-message">
							
							<form id="userData">
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<li>
								<span class="name" >姓名：</span>
								<input class="inputbox" name="xm" type="text"/>
								<!--<img alt="提交头像" src="img/2666/tx.jpg" width="90px" height="110px" style="position: relative; left: 60px"> -->
								<span class="name">联系电话：</span>
								 <input class="inputbox" name="lxdh" type="text" value=" "/>
							</li>
							<li>
								<span class="name">性别：</span>
								<div class="dropbox">
									<div class="drop" id="xb">
										<input class="hid" name="xb" type="text"  value="选择性别" style="display: none;"/>
			                            <div class="title" id="title">选择性别</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.xb }" var="xb">
			                                <a class="item ellipsis" title="${xb }">${xb }</a>
			                            </c:forEach>
			                            </label>
			                        </div>
								</div>
								<span class="name">电子邮件:</span>
								<input class="inputbox" name="dzyj" type="text" value=" "/>
							</li>
							<li>
								<span class="name">出生日期：</span>
								<input type="text" class="inputbox date-icon" name="csrq" id="startdate1" value=" ">
								<!-- <input class="inputbox" name="csrq" type="text" /> -->
								<span class="name">身份证号：</span>
								<input class="inputbox" name="sfzh" type="text" value=" "/>
							</li>
							<li>
								<span class="name">年龄：</span>
								<input class="inputbox" name="nl" type="text" />
								<span class="name">政治面貌：</span>
								<div class="dropbox">
									<div class="drop" id="zzmm">
										<input class="hid" name="zzmm" type="text" value="选择政治面貌" style="display: none;" />
			                            <div class="title" id="title">选择政治面貌</div>
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
								<input class="inputbox" name="gl" type="text" />
								<span class="name">籍贯：</span>
								<input class="inputbox" name="jg" type="text" />
							</li>
							<li>
								<span class="name">婚姻：</span>
								<div class="dropbox" name="hy" >
									<div class="drop" id="hy">
										<input class="hid" name="hy" type="text" value="选择婚姻状况" style="display: none;"/>
			                            <div class="title" id="title">选择婚姻状况</div>
			                            <div class="arrow" id="arrow"></div>
			                            <label>
			                                <c:forEach items="${option.hyzz }" var="hyzz">
			                                <a class="item ellipsis" title="${hyzz }">${hyzz }</a>
			                            	</c:forEach>
			                            </label>
			                        </div>
								</div>
								<span class="name">执业资格：</span>
								<div class="dropbox">
									<div class="drop" id="sfyzyzg">
										<input class="hid" name="sfyzyzg" type="text" value="是否有执业资格" style="display: none;"/>
			                            <div class="title" id="title">是否有执业资格</div>
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
								<input class="inputbox" name="mz"  type="text" />
								<span class="name">英语水平：</span>
								<div class="dropbox">
									<div class="drop" id="yysp">
										<input class="hid" name="yysp" type="text" value="英语水平" style="display: none;"/>
			                            <div class="title" id="title">英语水平</div>
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
								<input class="inputbox" name="xzz"  type="text" />
								<span class="name">应届生：</span>
								<div class="dropbox">
									<div class="drop" id="ssyj">
										<input class="hid" name="ssyj" type="text" value="是否应届生" style="display: none;"/>
			                            <div class="title" id="title">是否应届生</div>
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
								<span class="name">身高：</span>
								<input class="inputbox" name="sg" type="text" value=" "/>
							</li>
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
							
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="1" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" name="rxsj" style="width: 37%"; id="startdate1" value=" ">
									至
									<input type="text" class="inputbox date-icon" name="bysj" style="width: 37%;" id="enddate1" value=" ">
								</div>
								<div class="item" style="width: 15%;">
									<input type="text" class="inputbox" name="byxx" style="width: 85%;" value=" ">
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="syzy" value="专业" style="display: none;">
				                            <div class="title" id="title">专业</div>
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
										<div class="drop">
											<input type="text" class="hid" name="xl" value="学历" style="display: none;">
				                            <div class="title" id="title">学历</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xl }" var="xl">
			                                <a class="item" title="${xl }">${xl }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xw" value="学位" style="display: none;">
				                            <div class="title" id="title">学位</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xw }" var="xw">
			                                <a class="item ellipsis" title="${xw }">${xw }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="xxxs" value="办学形式" style="display: none;">
				                            <div class="title" id="title">办学形式</div>
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
							
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="2" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" name="rssj" style="width: 37%;"id="startdate1" value=" ">
									至
									<input type="text" class="inputbox date-icon" name="bysj" style="width: 37%;"id="enddate1" value=" ">
								</div>
								<div class="item" style="width: 15%;">
									<input type="text" class="inputbox" name="byxx" style="width: 85%;" value=" ">
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="syzy" value="专业" style="display: none;">
				                            <div class="title" id="title">专业</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.zy }" var="zy">
			                                <a class="item ellipsis" title="${zy }">${zy }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xl" value="学历" style="display: none;">
				                            <div class="title" id="title">学历</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xl }" var="xl">
			                                <a class="item ellipsis" title="${xl }">${xl }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xw" value="学位" style="display: none;">
				                            <div class="title" id="title">学位</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xw }" var="xw">
			                                <a class="item ellipsis" title="${xw }">${xw }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="xxxs" value="办学形式" style="display: none;">
				                            <div class="title" id="title">办学形式</div>
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
							
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="3" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" name="rssj" style="width: 37%;" id="startdate1" value=" ">
									至
									<input type="text" class="inputbox date-icon" name="bysj" style="width: 37%;" id="enddate1" value=" ">
								</div>
								<div class="item" style="width: 15%;">
									<input type="text" class="inputbox" name="byxx" style="width: 85%;" value=" ">
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="syzy" value="专业" style="display: none;">
				                            <div class="title" id="title">专业</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.zy }" var="zy">
			                                <a class="item ellipsis" title="${zy }">${zy }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xl" value="学历" style="display: none;">
				                            <div class="title" id="title">学历</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xl }" var="xl">
			                                <a class="item ellipsis" title="${xl }">${xl }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xw" value="学位" style="display: none;">
				                            <div class="title" id="title">学位</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xw }" var="xw">
			                                <a class="item ellipsis" title="${xw }">${xw }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="xxxs" value="办学形式" style="display: none;">
				                            <div class="title" id="title">办学形式</div>
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
							
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="4" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" name="rssj" style="width: 37%;" id="startdate1" value=" ">
									至
									<input type="text" class="inputbox date-icon" name="bysj" style="width: 37%;" id="enddate1" value=" ">
								</div>
								<div class="item" style="width: 15%;">
									<input type="text" class="inputbox" name="byxx" style="width: 85%;" value=" ">
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="syzy" value="专业" style="display: none;">
				                            <div class="title" id="title">专业</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.zy }" var="zy">
			                                <a class="item ellipsis" title="${zy }">${zy }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xl" value="学历" style="display: none;">
				                            <div class="title" id="title">学历</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xl }" var="xl">
			                                <a class="item ellipsis" title="${xl }">${xl }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xw" value="学位" style="display: none;">
				                            <div class="title" id="title">学位</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label>
				                            <c:forEach items="${option.xw }" var="xw">
			                                <a class="item ellipsis" title="${xw }">${xw }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 15%">
									<div class="dropbox" style="width: 80%;">
										<div class="drop">
											<input type="text" class="hid" name="xxxs" value="办学形式" style="display: none;">
				                            <div class="title" id="title">办学形式</div>
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
						</dl>
					</div>
					</form>
					<!--学历及学位结束-->
					
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
								<div class="item" style="width: 32.5%;">担任职务</div>
								<div class="item" style="width: 20%">职称</div>
								<div class="item" style="width: 20%">证明人</div>
							</dt>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="1" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" name="qssj" style="width: 37%;" id="startdate1" value=" ">
									至
									<input type="text" class="inputbox date-icon" name="zzsj" style="width: 37%;" id="enddate1" value=" ">
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" name="csgz" style="width: 88%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="zc" value="职称" style="display: none;">
				                            <div class="title" id="title">职称</div>
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
									<input type="text" class="inputbox" name="zmr" style="width: 80%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="2" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" style="width: 37%;" name="qssj" id="startdate1" value=" ">
									至
									<input type="text" class="inputbox date-icon" style="width: 37%;" name="zzsj" id="enddate1" value=" ">
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" style="width: 88%;" name="csgz" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="zc" value="职称" style="display: none;">
				                            <div class="title" id="title">职称</div>
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
									<input type="text" class="inputbox" style="width: 80%;" name="zmr" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="3" style="display: none;"/>
								<div class="item" style="width: 25%;">
									<input type="text" class="inputbox date-icon" style="width: 37%;" name="qssj" id="startdate1" value=" ">
									至
									<input type="text" class="inputbox date-icon" style="width: 37%;" name="zzsj" id="enddate1" value=" ">
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" style="width: 88%;" name="csgz" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="zc" value="职称" style="display: none;">
				                            <div class="title" id="title">职称</div>
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
									<input type="text" class="inputbox" style="width: 80%;" name="zmr" value=" "/>
								</div>
							</dd>
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
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="1" style="display: none;"/>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="cyxm"  style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="gx" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" name="gzdw" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" name="cydz" style="width: 88%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="cydh" style="width: 80%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="2" style="display: none;"/>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="cyxm" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="gx" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" name="gzdw" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" name="cydz" style="width: 88%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="cydh" style="width: 80%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="3" style="display: none;"/>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="cyxm" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="gx" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" name="gzdw" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 35%;">
									<input type="text" class="inputbox" name="cydz" style="width: 88%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="cydh" style="width: 80%;" value=" "/>
								</div>
							</dd>
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
								近五年学术论文
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
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="1" style="display: none;"/>
								<div class="item" style="width: 5%;">
									<input type="text" class="inputbox" name="lwnc" style="width: 70%;text-align: center;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox date-icon" name="fbrq" style="width: 70%;"id="startdate1" value=" ">
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="zzpm" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="qkh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="jh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="issnh" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="qkdj" style="width: 85%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="2" style="display: none;"/>
								<div class="item" style="width: 5%;">
									<input type="text" class="inputbox" name="lwnc" style="width: 70%;text-align: center;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox date-icon" name="fbrq" style="width: 70%;"id="startdate1" value=" ">
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="zzpm" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="qkh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="jh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="issnh" style="width: 80%;"value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="qkdj" style="width: 85%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="3" style="display: none;"/>
								<div class="item" style="width: 5%;">
									<input type="text" class="inputbox" name="lwnc" style="width: 70%;text-align: center;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox date-icon" name="fbrq" style="width: 70%;"id="startdate1" value=" ">
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="zzpm" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="qkh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="jh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="issnh" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="qkdj" style="width: 85%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="4" style="display: none;"/>
								<div class="item" style="width: 5%;">
									<input type="text" class="inputbox" name="lwnc" style="width: 70%;text-align: center;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox date-icon" name="fbrq" style="width: 70%;"id="startdate1" value=" ">
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="zzpm" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 12.5%;">
									<input type="text" class="inputbox" name="qkh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 10%;">
									<input type="text" class="inputbox" name="jh" style="width: 70%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="issnh" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="qkdj" style="width: 85%;" value=" "/>
								</div>
							</dd>
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
								近五年科研项目
							</div>
						</div>
						
						<dl class="resume-table">
							<dt>
								<div class="item" style="width: 30%;">科研项目</div>
								<div class="item" style="width: 20%;">项目级别</div>
								<div class="item" style="width: 20%;">经费数量</div>
								<div class="item" style="width: 30%;">本人项目中排名</div>
							</dt>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="1" style="display: none;"/>
								<div class="item" style="width: 30%;">
									<input type="text" class="inputbox" name="hjkk" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xmjj" value="项目级别" style="display: none;">
				                            <div class="title" id="title">项目级别</div>
				                            <div class="arrow" id="arrow"></div>
				                            <label style="display: none;">
				                            <c:forEach items="${option.xmjj }" var="xmjj">
			                                <a class="item ellipsis" title="${xmjj }">${xmjj }</a>
			                            	</c:forEach>
				                            </label>
				                        </div>
									</div>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" name="xmjf" style="width: 85%;" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="pm" style="width: 85%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="2" style="display: none;"/>
								<div class="item" style="width: 30%;">
									<input type="text" class="inputbox" name="hjkk" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xmjj" value="项目级别" style="display: none;">
				                            <div class="title" id="title">项目级别</div>
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
									<input type="text" class="inputbox" name="xmjf" style="width: 85%;" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="pm" style="width: 85%;" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="3" style="display: none;"/>
								<div class="item" style="width: 30%;">
									<input type="text" class="inputbox" name="hjkk" style="width: 80%;" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<div class="dropbox" style="width: 90%;">
										<div class="drop">
											<input type="text" class="hid" name="xmjj" value="项目级别" style="display: none;">
				                            <div class="title" id="title">项目级别</div>
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
									<input type="text" class="inputbox" name="xmjf" style="width: 85%;" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" name="pm" style="width: 85%;" value=" "/>
								</div>
							</dd>
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
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="1" style="display: none;"/>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" style="width: 80%;" name="rclb" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" style="width: 80%;" name="pzdwmc" value=" "/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzdwjb" value=" "/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzsj" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" style="width: 85%;" name="ssdy" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="2" style="display: none;"/>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" style="width: 80%;" name="rclb" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" style="width: 80%;" name="pzdwmc" value=" "/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzdwjb" value=" "/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzsj" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" style="width: 85%;" name="ssdy" value=" "/>
								</div>
							</dd>
							<dd>
							<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
							<input name="kid" type="text" value="3" style="display: none;"/>
								<div class="item" style="width: 20%;">
									<input type="text" class="inputbox" style="width: 80%;" name="rclb" value=" "/>
								</div>
								<div class="item" style="width: 20%">
									<input type="text" class="inputbox" style="width: 80%;" name="pzdwmc" value=" "/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzdwjb" value=" "/>
								</div>
								<div class="item" style="width: 15%">
									<input type="text" class="inputbox" style="width: 75%;" name="pzsj" value=" "/>
								</div>
								<div class="item" style="width: 30%">
									<input type="text" class="inputbox" style="width: 85%;" name="ssdy" value=" "/>
								</div>
							</dd>
						</dl>
						</div>
					</form>
					
					<!--人才工程结束-->
					</form>
					<!--获奖情况开始-->
					<form id="userHJ">
					<div class="form-type-2">
						
						<div class="titlebar" style="border-bottom: none;">
							<div class="title-icon">
								<img src="images/icon-i7.png"/>
							</div>
							<div class="title-content">
								获奖情况
							</div>
						</div>
						<input name="pkid" type="text" value="${pkid}" style="display: none;"/>
						<textarea id="textarea" placeholder="请在此填写您的获奖日期和所获奖项" name="hjkk"></textarea>
						
					</div>
					</form>
					
					<form id="uploadForm">  
           &nbsp;&nbsp;&nbsp;&nbsp;上传简历附件：<input type="file" name="file"/>  
                 			<input type="button" value="提交" onclick="doUpload('/hjsoft_zp/user/onefile')"/>  
   					</form> 
					<!--获奖情况结束-->
					
					<div class="buttonbox">
						<input class="submit" type="button" value="提交简历" onclick="resumesubmit()"/>
						
						<!-- <input class="submit" type="button" value="测试获取用户简历" onclick="submit()"/> -->
						
						<!-- <input class="submit" type="button" value="测试选择" onclick="replace()"/> -->
					</div>
					
				</div>
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

<script>
function doUpload(url) {  
     var formData = new FormData($( "#uploadForm" )[0]);  
     $.ajax({  
          url: url ,  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (returndata) {  
              Showbo.Msg.alert("提交成功"); 
          },  
          error: function (returndata) {  
              Showbo.Msg.alert("提交失败");  
          }  
     });  
} 
</script>

<!--css3样式控制-->
<script>
	$(document).ready(function(){
	$(".resume-table dd:first-of-type").css("border","none");
});
</script>

<!--登录验证-->

<script>
	function resumesubmit() {
		/* var userxl3 = jsc(userxl); */
		var userdata = jsa("#userData");
		var userxl 	= jsb("#userXL");
		var usergz 	= jsb("#userGZ");
		var userjt 	= jsb("#userJT");
		var userxs 	= jsb("#userXS");
		var userky 	= jsb("#userKY");
		var userrc 	= jsb("#userRC");
		if($("#textarea").val()==""){
			$("#textarea").val("空");
		}
		var userhj 	= jsa("#userHJ");
		var userJL={userData:userdata,userXL:userxl,userGZ:usergz,userJT:userjt,userXS:userxs,userKY:userky,userHJ:userhj,userRC:userrc};
		
		$.ajax({
			type:"post",
			url:"/hjsoft_zp/user/xzjl",
			contentType : "application/json",
			data : JSON.stringify(userJL),
			success : function(data) {
				if(data=="success"){
				Showbo.Msg.alert("修改成功");
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
				b[field.name] = field.value;
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
