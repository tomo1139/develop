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
			<%@ include file="header_content.jsp" %>
		</header>

		<div id="loginUser">
			<i class="fa fa-user fa-icon"></i><b>login: ${loginUser.name}</b>
		</div>

		<div id="todayVisitListPrintText">
			<i class="fa fa-user-secret fa-icon"></i>パスワード変更
		</div>
		
		<div id="loginform">
			<form:form modelAttribute="formChangePass" id="changePassFormId">

				<div id="changePassOldPass">
				OLD PASS <form:input path="oldPass" placeholder="old password" cssClass="nameformclass" onInput="checkChangePass();" pattern="^[0-9a-zA-Z]+$" title="※半角英数"  required="true"/>
				</div>

				<div id="oldPassErrorJS"></div>

				<div id="changePassNewPass">
				NEW PASS <form:password path="newPass" placeholder="new password" cssClass="passformclass" onInput="checkChangePass();" pattern="^[0-9a-zA-Z]+$" title="※半角英数"  required="true"/>
				</div>

				<div id="newPassErrorJS"></div>

				<div id="passform2">
				Comfirm PASS <form:password path="newPass2" placeholder="confirm new password" cssClass="passformclass" onInput="checkChangePass();" pattern="^[0-9a-zA-Z]+$" title="※半角英数"  required="true"/>
				</div>

				<div id="newPassErrorJS2"></div>

				<div id="errorChangePassMsg">
					<c:if test="${changePassErrorMsg != null}" >
						<c:out value="${changePassErrorMsg}"/>
					</c:if>
				</div>

				<div id="successMsg">
					<c:if test="${changePassSuccessMsg != null}" >
						<c:out value="${changePassSuccessMsg}"/>
					</c:if>
				</div>

				<input type="submit" class="newUserButton" value="Change" >

			</form:form>
		</div>

	    <div id="blankBottom">  </div>
		
	</body>
</html>
