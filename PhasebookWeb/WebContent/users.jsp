<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.naming.*, java.util.List, jsmp.is.phasebook.db.User, jsmp.is.phasebook.db.Board, jsmp.is.phasebook.ejb.Users" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css" />
<title>Phasebook User Search</title>
</head>
<body>
<%
	Users usersBean = (Users) request.getAttribute("users_bean");
	
	List<User> users = (List<User>) request.getAttribute("users");
	User current_user  = (User) session.getAttribute("current_user");
%>

<jsp:include page="sidebar.jsp" />

<% 
	if (request.getParameter("oops") != null && request.getParameter("oops").equals("true")) {
		out.println("You are not allowed to access that page.");
	}
%>

<h1>Users</h1>

	<form action="Users" method="get" id="search">
		<input type="text" name="user_search" />
		<input type="submit" value="Search" />
	</form>
	
	<% if (request.getParameter("user_search") != null) { %>
		<h3><u>Search results for: <%= request.getParameter("user_search") %></u></h3>
	<% } %>

	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Boards</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<% for (User user: users) { %>
				<tr>
					<td><h3><%= user.getName() %></h3></td>
					<td><%= user.getEmail() %></td>
					<td>
						<% for (Board board : user.getBoards()) { %>
							
							<% if (!board.isPrivate()) { %>
								<a href="Boards?id=<%= board.getId() %>">Public</a>
							<% } else if (user.getId() == current_user.getId() || (board.isPrivate() && usersBean.isFriendsWith(current_user.getId(), user.getId()))) { %>							
								<a href="Boards?id=<%= board.getId() %>">Private</a>
							<% } %>
							&nbsp;
						<% } %>
					</td>
					<td>
						<% if (current_user.getId() == user.getId()) { %>
							This is you.
						<% } else { %>
		
							<% if (usersBean.isPendingFriendsWith(user.getId(), current_user.getId(), false)) { %>
								
								<form action="Users" method="post">
									<input type="hidden" value="<%= user.getId() %>" name="accept_friend_id" />
									<input type="submit" value="Accept Friendship" />
								</form>
							<% } else if (!usersBean.isFriendsWith(current_user.getId(), user.getId()) && !usersBean.isPendingFriendsWith(current_user.getId(), user.getId(), true)) { %>
								<form action="Users" method="post">
									<input type="hidden" value="<%= user.getId() %>" name="request_friend_id" />
									<input type="submit" value="Request Friendship" />
								</form>		
							<% } else if (usersBean.isPendingFriendsWith(current_user.getId(), user.getId(), false)) { %>
								(Request sent. Awaiting confirmation.)
							<% } else { %>
								(Friend)
							<% } %>
						<% } %>
					</td>
				</tr>
			<% } %>			
		</tbody>
	</table>
</body>
</html>