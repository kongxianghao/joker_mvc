<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<title>second</title>
	</head>
	<body>
		<%=request.getAttribute("name")%><%=request.getAttribute("age")%><%=request.getAttribute("message")%>
		<a href="/joker/test/first.jk">链接</a>
	</body>
</html>
