<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
	<%@ include file="head_content.jsp" %>
	</head> 
	<body onload="nowloadingOff(); createBomb('canvasId');">
		<div id="loginloading">
		</div>
		<div id="loginContainer">
		<header>
			<div id="systemname"> </div>
			<div id="systemnameSub">顧客管理アプリ</div>
			<div id="header_right" align="right">
				<a href="NewUser" class="loginLink"><i class="fa fa-user-plus fa-icon"></i><b>新規登録</b></a>
			</div>
		</header>

		<div id="todayVisitListPrintText">
			<i class="fa fa-key fa-icon"></i>ログイン
		</div>
		
		<div id="loginform">
			<form:form modelAttribute="formLogin" id="loginFormId">
			
				<div id="loginIdText">
				ID <form:input path="name" id="name" name="name" class="nameformclass" onInput="checkLogin();" placeholder="name" type="text" pattern="^[0-9a-zA-Z]+$" title="※半角英数" required="true" />
				</div>

				<div id="nameErrorLoginJS"></div>

				<div id="loginPassText">
				PASS <form:password path="pass" placeholder="password" cssClass="passformclass" pattern="^[0-9a-zA-Z]+$" title="※半角英数" onInput="checkLoginPass();" name="loginPass" required="true"/>
				</div>

				<div id="passErrorJS"></div>

				<c:if test="${loginErrorMsg != null}" >
					<div id="errorMsg">
						<c:out value="${loginErrorMsg}"/>
					</div>
				</c:if>
				<input type="submit" class="submitButton" value="login" onClick="nowloading();" >
			</form:form>
		</div>
		<!-- <c:import url="showDataBaseAll.jsp"/> -->
		</div>
		
		<!--
		<canvas id="canvasId" width="64px" height="64px">
		</canvas>
  		-->

	</body>
</html>
