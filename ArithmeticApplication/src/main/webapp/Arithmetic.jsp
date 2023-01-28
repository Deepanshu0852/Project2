<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String s = request.getParameter("operation");
	if (s.equals("Addition")) {
	%><jsp:forward page="add.jsp" />
	<%
	} else if (s.equals("Subtraction")) {
	%><jsp:forward page="sub.jsp" />
	<%
	} else if (s.equals("Multiplication")) {
	%><jsp:forward page="mult.jsp" />
	<%
	} else {
	%><jsp:forward page="div.jsp" />
	<%
	}
	%>

</body>
</html>