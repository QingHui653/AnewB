<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/views/jsp/common/headjsp.jsp" %>

<html>
  
  <body>
    <div>
		<div class="line"></div>
		<dl class="custom">
			<dt onClick="changeImage()">系统管理<img src="<%= path%>/images//select_xl01.png"></dt>
			<dd class="first_dd"><a href="listfeek.action?pageNo=1" target="body">建议反馈</a></dd>
            <dd><a href="push.jsp" target="body">推送通知</a></dd>
            <dd><a href="cityManage.action?pageNo=1" target="body">城市管理</a></dd>
            <dd><a href="companyManage.action?pageNo=1" target="body">快递公司管理</a></dd>
            <dd><a href="backsetPrice.action?pageNo=1" target="body">快递价格管理</a></dd>
            <dd><a href="listImageL.action" target="body">轮播图片管理</a></dd>
            <dd><a href="listImageG.action" target="body">广告图片管理</a></dd>
            <dd><a href="listImageSIMG.action" target="body">社区轮播图管理</a></dd>
            <dd><a href="findJuli.action?pageNo=1" target="body">附近距离</a></dd>
            <dd><a href="houtaiListPost.action?pageNo=1" target="body">社区审核</a></dd>
		</dl>
		
		<dl class="custom">
			<dt onClick="changeImage()">管理员管理<img src="<%= path%>/images//select_xl01.png"></dt>
			<dd class="first_dd"><a href="findAdmin.action?type=N&pageNo=1" target="body">管理员信息</a></dd>
        </dl>
        
		<dl class="custom">
			<dt onClick="changeImage()">会员管理<img src="<%= path%>/images//select_xl01.png"></dt>
			<dd class="first_dd"><a href="findAllUser.action?type=N&pageNo=1" target="body">会员信息</a></dd>
			<dd class="first_dd"><a href="findCityAllUser.action?type=N&pageNo=1" target="body">会员信息</a></dd>
        </dl>
        <dl>
			<dt onClick="changeImage()">接单人信息表<img src="<%= path%>/images//select_xl01.png"></dt>
		</dl>
	</div>
  </body>
</html>
