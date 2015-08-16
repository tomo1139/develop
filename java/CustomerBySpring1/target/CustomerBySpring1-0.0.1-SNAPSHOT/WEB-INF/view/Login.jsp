<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		<c:if test="${userlist != null}" >
		<table border="1">
		<tr><th>ID</th><th>name</th><th>pass</th></tr>
		<c:forEach var="obj" items="${userlist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.name}"/></td>
			<td><c:out value="${obj.pass}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>
	</body>
</html>
