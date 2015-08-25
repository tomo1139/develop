<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<link href="<c:url value="/css/common.css" />" rel="stylesheet">
		<title>顧客管理システム</title>
	</head> 
	<body>
		<header>
			<div id="systemname">顧客管理アプリ</div>
		</header>
		
		<div id="loginform">
			<form:form modelAttribute="formLogin">
				<div id="nameform">
				&nbsp&nbsp&nbsp&nbspID&nbsp<form:input path="name" placeholder="name" cssClass="nameformclass"/>
				</div>

				<div id="passform">
				PASS&nbsp<form:password path="pass" placeholder="password" cssClass="passformclass"/>
				</div>

				<c:if test="${loginErrorMsg != null}" >
					<div id="errorMsg">
						<c:out value="${loginErrorMsg}"/>
					</div>
				</c:if>

				<input type="submit" class="submitButton" value="login">
			</form:form>
		</div>
		
		<!--
		<c:import url="showDataBaseAll.jsp"/>
		-->

	</body>
</html>
