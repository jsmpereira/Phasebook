<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Phasebook User Search</title>
</head>
<body>

<h1>Users</h1>

	<c:forEach items="${users}" var="user" >
		<h3><c:out value="${user.name}" /></h3>
		<small><c:out value="${user.email}" /></small>
		<form action="Users" method="post">
			<input type="hidden" value="${user.id}" name="friend_id" />
			<input type="submit" value="Request Friendship" />
		</form>
	</c:forEach>

</body>
</html>