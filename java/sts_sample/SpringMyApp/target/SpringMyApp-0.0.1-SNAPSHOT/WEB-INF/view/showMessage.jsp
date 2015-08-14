<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${title}</title>
	<style>
	h1 { font-size:16pt; background-color:#CCCCFF; padding:3px; }
	p { color: #000066; }
	</style>
</head> 
<body>
	<h1>${title}</h1>
	<p>${message}</p>
	<table>
	<form:form modelAttribute="myData">
		<form:errors path="*" element="div" />
		<form:hidden path="id"/>
		<tr><td><form:label path="name">名前</form:label></td>
			<td><form:input path="name" size="20" /></td></tr>
		<tr><td><form:label path="age">年齢</form:label></td>
			<td><form:input path="age" size="20" /></td></tr>
		<tr><td><form:label path="mail">メール</form:label></td>
			<td><form:input path="mail" size="20" /></td></tr>
		<tr><td><form:label path="memo">メモ</form:label></td>
			<td><form:textarea path="memo" cols="20" rows="5" /></td></tr>
		<tr><td></td><td><input type="submit"></td></tr>
	</form:form>
	</table>
	<hr>
	<c:if test="${datalist != null}" >
	<table border="1">
	<tr><th>ID</th><th>名前</th></tr>
	<c:forEach var="obj" items="${datalist}" varStatus="status">
		<tr>
		<td><c:out value="${obj.id}"/></td>
		<td><c:out value="${obj.name}"/></td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
</body>
</html>
