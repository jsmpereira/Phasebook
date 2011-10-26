<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="styles.css">
<title>PhaseBook Registration</title>
</head>
<body>

<h1>Register to Phasebook</h1>

<form action="Register" method="post">
	<dl>
		<dd><label>Name</label></dd>
		<dt><input type="text" name="name" /></dt>
	
		<dd><label>Email</label></dd>
		<dt><input type="text" name="email" /></dt>
	
		<dd><label>Password</label></dd>
		<dt><input type="password" name="password" /></dt>
	
		<dd></dd>
		<dt><input type="submit" value="Register"/></dt>
	</dl>
</form>

</body>
</html>