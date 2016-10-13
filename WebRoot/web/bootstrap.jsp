<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../web/common/headjsp.jsp" %>
<html>
<body>
<table class="table table-striped">
	
	<thead>
		<tr>
				<th>作者</th>
				<th>网址</th>
				<th>readme</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${resultList}" var="rList">
			<tr>
				<td>${rList.f1}</td>
				<td>${rList.f2}</td>
				<td>${rList.f3}</td>
			</tr>
		<%-- <tr>
			<c:forEach items="${resultList}" var="rList">
				<td>${rList.oid}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${resultList}" var="rList">
				<td>${rList.f1}</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${resultList}" var="rList">
				<td>${rList.f3}</td>
			</c:forEach>
		</tr> --%>
		</c:forEach>
	</tbody>
</table>
</body>
</html>