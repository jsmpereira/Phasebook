<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css" />
<title>Phasebook Lottery</title>
</head>
<body>
<jsp:include page="sidebar.jsp" />

<h1>Lottery</h1>


Round <c:out value="${lotteryBean.round-1}"></c:out> winning number:
<h2><c:out value="${lotteryBean.lucky_number}"></c:out></h2>
<h3>Next run at <c:out value="${lotteryBean.next_run}"></c:out></h3>

<c:if test="${not empty lotteryBean.results}">
	<h2>Winners for round <c:out value="${lotteryBean.round-1}"></c:out></h2>
	<ul>
		<c:forEach items="${lotteryBean.results}" var="result">
			<li><c:out value="${result.user.name}" /></li>
		</c:forEach>
	</ul>
	
</c:if>

<form action="Lottery" method="post">
	<dl>
		<dd><label>Pick a number (1-100)</label></dd>
		<dt><input type="text" name="number" /></dt>
		
		<dt><input type="submit" value="Play"/></dt>
	</dl>
</form>
</body>
</html>