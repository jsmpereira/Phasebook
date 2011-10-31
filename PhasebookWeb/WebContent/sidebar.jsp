<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="jsmp.is.phasebook.db.Board" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.naming.*, jsmp.is.phasebook.lottery.TheLottery" %>

<%
	InitialContext ic = new InitialContext();
	TheLottery lotteryBean = (TheLottery) ic.lookup("Phasebook/TheLotteryBean/remote");	
%>

<ul id="menu">
  <c:forEach items="${current_user.boards}" var="board">
  	<a href="Boards?id=${board.id}">
	  	<c:choose>
	  		<c:when test="${board.private}">
	  			Private Board
	  		</c:when>
	  		<c:otherwise>
	  			Public Board
	  		</c:otherwise>
	  	</c:choose>
	  </a>
	  &nbsp;
  </c:forEach>
  <li><a href="Users">Users</a></li>
  <li><a href="Photos">Photos</a></li>
  <li>Welcome, <a href="Profile"><c:out value="${current_user.name}" /></a></li>
  <li><a href="Logout">Logout</a></li>
</ul>

<div>
	Current Lottery Number:
	<% if (lotteryBean.getNext_run() != null) { %>
		<h3><%= lotteryBean.getLucky_number() %></h3>
		<br /><small>(next number at <%= lotteryBean.getNext_run() %>)</small>
	<% } else { %>
		Not available.
	<% } %>
</div>