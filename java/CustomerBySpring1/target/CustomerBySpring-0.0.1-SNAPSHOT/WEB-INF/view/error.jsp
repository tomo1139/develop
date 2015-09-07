<!DOCTYPE html>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<%@ include file="head_content.jsp" %>
	</head> 
	<body onload="createBomb('canvasId');">
		<header>
			<%@ include file="header_content.jsp" %>
		</header>
		
		<div id="loginUser">
			<i class="fa fa-user fa-icon"></i><b>login: ${loginUser.name}</b>
		</div>

		<div id="errorPrintText">
			<i class="fa fa-medkit fa-iconError"></i>エラー
		</div>

		<canvas id="canvasId" width="64px" height="64px">
		</canvas>

	</body>
</html>
		