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
<title>Phasebook Boards</title>
</head>
<body>

<jsp:include page="sidebar.jsp" />

<div id="wrapper">

<h1>
	<c:out value="${board.owner.name}" />'s
	<c:if test="${board.private}">
		Private
	</c:if>
	Board
</h1>

	<c:forEach items="${board.topics}" var="topic" >
		<div class="topic">
			<h3><c:out value="${topic.title}" /></h3>
			<small><c:out value="${topic.created_at}" /> <c:out value="${topic.creator.name}" /></small>
			<blockquote><c:out value="${topic.body}" /></blockquote>
			
			<c:forEach items="${topic.assets}" var="asset">
				<img src="/images/${asset.path}" class="image"/>
			</c:forEach>
		</div>
	</c:forEach>

<div id="new_topic">
	<h2>Create topic </h2>
	<form action="Boards" method="post" enctype="multipart/form-data">
		<dl>
			<dd><label>Title</label></dd>
			<dt><input type="text" name="title" /></dt>
		
			<dd><label>Body</label></dd>
			<dt><textarea name="body" cols="25" rows="5"></textarea></dt>
		
			<dd><label>Photo</label></dd>
			<dt><input type="file" name="photo"/></dt>
			<dt><input type="hidden" value="${param.id}" name="board_id"/></dt>
			
			<dd></dd>
			<dt><input type="submit" value="Post"/></dt>
		</dl>
	</form>
</div>
</div>
</body>
</html>