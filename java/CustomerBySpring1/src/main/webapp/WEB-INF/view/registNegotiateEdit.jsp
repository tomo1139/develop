<!DOCTYPE html>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<%@ include file="head_content.jsp" %>
	</head> 

	<body onload="checkDetailInput();">
		<header>
			<%@ include file="header_content.jsp" %>
		</header>

		<div id="loginUser">
			<i class="fa fa-user fa-icon"></i><b>login: ${loginUser.name}</b>
		</div>

		<div id="todayVisitListPrintText">
			<i class="fa fa-pencil fa-icon"></i>交渉記録編集
		</div>

		<div id="registItemBase">
		<div id="registItemText"> ${customer.id} </div>
		<div id="registItemText2">${customer.name}</div>
		<div id="registItemText3">契約期間&nbsp${contractTerm}ヶ月</div>
		</div>

		<div id="detailContractContents">
			<div id="detailContractContentsText"> 交渉記録編集</div>

			<div id="registContractFormMain">
			
				<div id="registContractFormText">
				<div id="registContractText"> 日時 </div>
				<div id="registContractText"> 交渉手段 </div>
				<div id="registContractText"> 交渉相手 </div>
				<div id="registContractText"> 交渉結果 </div>
				<div id="registContractText"> 交渉内容 </div>
				</div>
			
				<spring:url value="CustomerDetailEdit" var="action" />
				<form:form modelAttribute="formEdit" action="${action}" name="registForm" id="registFormId">
					<div id="registContractForm">

					<div id="registContractInput"> 
						<input id="datetime" type="datetime" name="datetime" value="${datetime}" readonly>
						<script>
							$(function(){
								$('#datetime').datetimepicker({ step:1 });
							});
						</script>
					</div>

					<div id="registContractInput">
						<select name="means" class="selectclass">
							<c:forEach var="obj" items="${customerMeanslist}" varStatus="status">
								<c:if test="${obj.coms == method}" >
	 								<option value="${obj.id}" selected>${obj.coms}</option>
								</c:if>
								<c:if test="${obj.coms != method}" >
	 								<option value="${obj.id}">${obj.coms}</option>
								</c:if>
 							</c:forEach>
 						</select>
					</div>

					<div id="registContractInput">
						<select name="opponent" class="selectclass">
							<c:forEach var="obj" items="${customerOpponentlist}" varStatus="status">
								<c:if test="${obj.coms == opponent}" >
	 								<option value="${obj.id}" selected>${obj.coms}</option>
								</c:if>
								<c:if test="${obj.coms != opponent}" >
	 								<option value="${obj.id}">${obj.coms}</option>
								</c:if>
 							</c:forEach>
 						</select>
					</div>

					<div id="registContractInput"> 
						<select name="status" class="selectclass">
							<c:forEach var="obj" items="${customerStatuslist}" varStatus="status">
								<c:if test="${obj.coms == result}" >
	 								<option value="${obj.id}" selected>${obj.coms}</option>
								</c:if>
								<c:if test="${obj.coms != result}" >
	 								<option value="${obj.id}">${obj.coms}</option>
								</c:if>
 							</c:forEach>
 						</select>
					</div>

					<div id="registContractInput">
						<form:textarea name="detailEdit" path="detailEdit" placeholder="input detail" cols="40" rows="6" style="overflow:auto;" maxlength="32" cssClass="detailformclass" onInput="checkDetailInput();" required="true" />
					</div>
					
					<div id="countDetailCharNum"></div>
					<div id="registDetailErrMsgJS"></div>
					<c:if test="${loginErrorMsg != null}" >
						<div id="errorMsg">
							<c:out value="${loginErrorMsg}"/>
						</div>
					</c:if>

					</div>
					<input type="submit" class="registEditButton" value="更新" id="registEditButtonId" name="registEditButtonName">
					<input type="hidden" name="negoId" value="${negoId}">
					<input type="hidden" name="id" value="${customer.id}">
					<input type="hidden" name="count" value="${count}">
				</form:form>


			</div>
	    </div>

	</body>
</html>
