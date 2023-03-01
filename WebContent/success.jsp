<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.techpalle.model.Success" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<%
	Success spJsp=(Success)request.getAttribute("succesDetails");

%>
<header>
	<h1><%=spJsp.h1 %></h1>
</header>

	<p><%=spJsp.p %></p>
<footer>
	
	<h3><%=spJsp.h3 %></h3>
</footer>
</body>
</html>