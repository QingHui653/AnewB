<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../web/common/headjsp.jsp"%>

<html>
<body>
	<table class="table" align="center">
		<tr style="background: rgb(78,78,78); height: 120px; ">
			<td colspan="2"><iframe frameborder="0" width="100%"
					src="top.jsp" name="top"></iframe></td>
		</tr>
		<tr>
			<td width="12%" style="padding:0px;" align="center" valign="top" height="700px">
				<iframe frameborder="0" width="100% src="left.jsp" name="left" height="100%"></iframe></td>
			<td width="88%" style="padding:0px;" align="center" height="700px">
				<iframe frameborder="0" src="" name="body" width="100%"
					height="100%"></iframe>
			</td>
		</tr>
	</table>
</body>

</html>
