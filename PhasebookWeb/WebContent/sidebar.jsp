<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="jsmp.is.phasebook.db.User, jsmp.is.phasebook.db.Board" %>    
<% User current_user = (User) session.getAttribute("user"); %>

<ul id="menu">
  <li><a href="Register">Register</a></li>
  <li><a href="Login">Login</a></li>
  <li><a href="Logout">Logout</a></li>
  <% for (Board board : current_user.getBoards()) { %>
  		<li><a href="Boards?id=<%= board.getId() %>">
  			<%= board.isPrivate() ? "Private Board" : "Public Board" %>
  		</a></li>
  <% } %>
  <li><a href="Users">Users</a></li>
  <li><a href="Photos">Photos</a></li>
  <li><%= new java.util.Date() %></li>
  <li>Welcome, <%= current_user.getName() %></li>
</ul>