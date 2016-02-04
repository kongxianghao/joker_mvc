<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<title>first</title>
	</head>
	<body>
		<form action="/joker/test/second.jk" method="post">
			<input type="text" name="name" value="<%=request.getAttribute("name")==null ? "": request.getAttribute("name")%>">
			<input type="text" name="age" value="<%=request.getAttribute("age")==null ? "": request.getAttribute("age")%>">
			<input type="submit" value="提交">
		</form>
	</body>
</html>
