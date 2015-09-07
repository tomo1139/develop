<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
	<%@ include file="head_content.jsp" %>
	</head> 
	<body>
		<header>
			<div id="systemname"> </div>
			<div id="systemnameSub">顧客管理アプリ</div>
			<div id="header_right" align="right">
				<a href="Login" class="loginLink"><i class="fa fa-key fa-icon"></i><b>ログイン</b></a>
			</div>
		</header>

		<div id="todayVisitListPrintText">
			<i class="fa fa-user-plus fa-icon"></i><b>新規登録</b>
		</div>
		
		<div id="loginform">
			<form:form modelAttribute="formNewUser" id="loginFormId">
			
					<div id="newUserIdText">
						ID <form:input path="name" placeholder="name" cssClass="nameformclass" onInput="checkRegist();" maxlength="32" pattern="^[a-zA-Z]+$" title="※半角英字" required="true"/>
					</div>

					<div id="nameErrorJS"></div>

					<div id="newUserPassText">
						PASS <form:password path="pass" placeholder="password" cssClass="passformclass" onInput="checkRegist();" maxlength="8" pattern="^[0-9a-zA-Z]+$" title="※半角英数" required="true"/>
					</div>

					<div id="passErrorNewJS"></div>

					<div id="newUserConfirmPassText">
						Confirm PASS <form:password path="pass2" placeholder="confirm password" cssClass="passformclass2" maxlength="8" onInput="checkRegist();" pattern="^[0-9a-zA-Z]+$" title="※半角英数" required="true"/>
					</div>
				
					<div id="passErrorJS2"></div>

				<div id="errorMsg">
					<c:if test="${registErrorMsg != null}" >
						<c:out value="${registErrorMsg}"/>
					</c:if>
				</div>

				<div id="successMsg">
					<c:if test="${registSuccessMsg != null}" >
						<c:out value="${registSuccessMsg}"/>
					</c:if>
				</div>

				<input type="submit" class="newUserButton" value="Regist" >

			</form:form>
		</div>

	    <div id="blankBottom">  </div>
		
	</body>
</html>