<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jsmp.is.phasebook.db.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>

<jsp:include page="sidebar.jsp" />

<h1>
	<c:out value="${board.owner.name}" />'s
	<c:if test="${board.private}">
		Private
	</c:if>
	Board
</h1>
	<c:forEach items="${board.topics}" var="topic" >
		<h3><c:out value="${topic.title}" /></h3>
		<small><c:out value="${topic.created_at}" /> <c:out value="${topic.creator.name}" /></small>
		<blockquote><c:out value="${topic.body}" /></blockquote>
	</c:forEach>

<h2>Create topic </h2>
<form action="Boards" method="post">
	<dl>
		<dd><label>Title</label></dd>
		<dt><input type="text" name="title" /></dt>
	
		<dd><label>Body</label></dd>
		<dt><textarea name="body" cols="25" rows="5"></textarea></dt>
	
		<dt><input type="hidden" value="${param.id}" name="board_id"/></dt>
		
		<dd></dd>
		<dt><input type="submit" value="Post"/></dt>
	</dl>
</form>

</body>
</html>