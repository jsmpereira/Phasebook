<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="jsmp.is.phasebook.db.Board" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul id="menu">
  <li><a href="Register">Register</a></li>
  <li><a href="Login">Login</a></li>
  <li><a href="Logout">Logout</a></li>
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
  </c:forEach>
  <li><a href="Users">Users</a></li>
  <li><a href="Friends">Friends</a></li>
  <li><a href="Photos">Photos</a></li>
  <li>Welcome, <c:out value="${current_user.name}" /></li>
</ul>