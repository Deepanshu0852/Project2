<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	background-color: blueviolet;
	color: brown;
	text-align: center;
}
</style>
<body>
	<%
	String s1 = request.getParameter("first");
	String s2 = request.getParameter("second");
	int x = Integer.parseInt(s1);
	int y = Integer.parseInt(s2);
	out.println("Division:" + x / y);
	%>
</body>
</html>