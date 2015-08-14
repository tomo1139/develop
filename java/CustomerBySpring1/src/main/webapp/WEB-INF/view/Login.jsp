<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Login</title>
	</head> 
	<body>
		<h1>ログイン画面</h1>
		
		<form:form modelAttribute="formLogin">
			name<form:input path="name" placeholder="input name"/><br>
			pass<form:password path="pass" placeholder="input password"/><br>
			<input type="submit">
		</form:form>
	</body>
</html>
