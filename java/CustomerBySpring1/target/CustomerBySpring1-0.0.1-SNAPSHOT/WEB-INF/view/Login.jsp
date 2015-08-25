<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<link href="css/common.css" rel="stylesheet" type="text/css" media="all">
		<title>顧客管理システム</title>
	</head> 
	<body>
		<div id="header">
		aaa
		</div>
		
		<form:form modelAttribute="formLogin">
			name<form:input path="name" placeholder="input name"/><br>
			pass<form:password path="pass" placeholder="input password"/><br>
			<input type="submit">
		</form:form>
		
		<!--
		<c:import url="showDataBaseAll.jsp"/>
		-->

	</body>
</html>
