<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css" />
<title>Phasebook Profile</title>
</head>
<body>
<jsp:include page="sidebar.jsp" />

<h1><c:out value="${user.name}'s Profile" /></h1>

<ul>
	<li><strong>Email:</strong> <c:out value="${user.email}" /></li>
</ul>

<h1>Friends</h1>
	<ul>
		<c:forEach items="${friends}" var="friendship" >
			<c:choose>
				<c:when test="${friendship.friend.id == user.id}">
					<li><a href="Profile?id=${friendship.user.id}"><c:out value="${friendship.user.name}" /></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Profile?id=${friendship.friend.id}"><c:out value="${friendship.friend.name}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	
	
	
</body>
</html>