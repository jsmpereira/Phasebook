<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css" />
<title>PhaseBook Login</title>
</head>
<body>

<%
	if (request.getAttribute("registered") != null) {
		out.println("Congratulations. You can now Login.");
	} 
	if (request.getAttribute("logged") != null) {
		
		Boolean logged = (Boolean) request.getAttribute("logged");
		
		if (logged) {
		 out.println("You are now logged in.");
		} else {
		 out.println("Login unsuccessful.");
		}
	}
	if (request.getAttribute("quit") != null) {
		
		Boolean quit = (Boolean) request.getAttribute("quit");
		
		if (quit) {
			out.println("Logout successful.");
		}
	}
%>

<div id="login">
	<h1>Login to Phasebook</h1>
	<form action="Login" method="post">
	
		<dl>
			<dt><label>Email</label></dt>
			<dd><input type="text" name="email"/></dd>
			
			<dt><label>Password</label></dt>
			<dd><input type="password" name="password"/></dd>
		</dl>
		<input type="submit" value="Submit"/>
	
	</form>
	
	<p>Don't have an account yet? <br/> <a href="Register">Create one</a>.</p>
</div>

</body>
</html>