<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.naming.*, java.util.List, jsmp.is.phasebook.db.User, jsmp.is.phasebook.ejb.Users" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css" />
<title>Phasebook User Search</title>
</head>
<body>
<%
	InitialContext ic = new InitialContext();
	Users theusers = (Users) ic.lookup("Phasebook/UsersBean/remote");
	
	List<User> users = (List<User>) request.getAttribute("users");
	int user_id = (Integer) session.getAttribute("user_id");
%>

<jsp:include page="sidebar.jsp" />

<h1>Users</h1>

	<form action="Users" method="get">
		<input type="text" name="user_search" />
		<input type="submit" value="Search" />
	</form>

	<% for (User user: users) { %>
		<h3><%= user.getName() %></h3>
		<small><%= user.getEmail() %></small>
		
		<% if (user_id == user.getId()) { %>
			This is you.
		<% } else { %>
		
			<% if (theusers.isPendingFriendsWith(user.getId(), user_id, false)) { %>
				
				<form action="Users" method="post">
					<input type="hidden" value="<%= user.getId() %>" name="accept_friend_id" />
					<input type="submit" value="Accept Friendship" />
				</form>
			<% } else if (!theusers.isFriendsWith(user_id, user.getId()) && !theusers.isPendingFriendsWith(user_id, user.getId(), true)) { %>
				<form action="Users" method="post">
					<input type="hidden" value="<%= user.getId() %>" name="request_friend_id" />
					<input type="submit" value="Request Friendship" />
				</form>		
			<% } else if (theusers.isPendingFriendsWith(user_id, user.getId(), false)) { %>
				(Request sent. Awaiting confirmation.)
			<% } else { %>
				(Friend)
			<% } %>
		<% } %>
	<% } %>
</body>
</html>