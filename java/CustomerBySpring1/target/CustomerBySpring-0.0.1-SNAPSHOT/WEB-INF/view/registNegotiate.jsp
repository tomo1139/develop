<!DOCTYPE html>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<%@ include file="head_content.jsp" %>
	</head> 

	<body onload="checkRegistInput();">
		<header>
			<%@ include file="header_content.jsp" %>
		</header>

		<div id="loginUser">
			<i class="fa fa-user fa-icon"></i><b>login: ${loginUser.name}</b>
		</div>


		<div id="todayVisitListPrintText">
			<i class="fa fa-pencil fa-icon"></i>交渉記録登録
		</div>

		<div id="registItemBase">
		<div id="registItemText"> ${customer.id} </div>
		<div id="registItemText2">${customer.name}</div>
		<div id="registItemText3">契約期間 ${contractTerm}ヶ月</div>
		</div>

		<div id="detailNegotiateContractContents">
			<div id="detailContractContentsText"> 交渉記録追加</div>

			<div id="registContractFormMain">
			
				<div id="registContractFormText">
				<div id="registContractText"> 日時 </div>
				<div id="registContractText"> 交渉手段 </div>
				<div id="registContractText"> 交渉相手 </div>
				<div id="registContractText"> 交渉結果 </div>
				<div id="registContractText"> 交渉内容 </div>
				</div>
			
				<spring:url value="CustomerDetail" var="action" />
				<form:form modelAttribute="formRegist" action="${action}" name="registForm" id="registNegoFormId">
					<div id="registContractForm">

					<div id="registContractDateInput"> 
						<input style="width:150px" id="datetime" type="datetime" name="datetime" value="${nowdatetime}" readonly class="registContractDateInputClass">
						<script>
							$(function(){
								$('#datetime').datetimepicker({ step:1 });
							});
						</script>
					</div>

					<div id="registContractInput">
						<select name="means" class="selectclass">
							<c:forEach var="obj" items="${customerMeanslist}" varStatus="status">
 								<option value="${obj.id}">${obj.coms}</option>
 							</c:forEach>
 						</select>
					</div>

					<div id="registContractInput">
						<select name="opponent" class="selectclass">
							<c:forEach var="obj" items="${customerOpponentlist}" varStatus="status">
 								<option value="${obj.id}">${obj.coms}</option>
 							</c:forEach>
 						</select>
					</div>

					<div id="registContractInput"> 
						<select name="status" class="selectclass">
							<c:forEach var="obj" items="${customerStatuslist}" varStatus="status">
 								<option value="${obj.id}">${obj.coms}</option>
 							</c:forEach>
 						</select>
					</div>

					<div id="registContractInput">
						<form:textarea name="detail" path="detail" placeholder="input detail" cols="40" rows="6" style="overflow:auto;" maxlength="32" cssClass="detailformclass" onInput="checkRegistInput();" required="true" />
					</div>

					<div id="countDetailCharNum"></div>
					<div id="registDetailErrMsgJS"></div>
					<c:if test="${loginErrorMsg != null}" >
						<div id="errorMsg">
							<c:out value="${loginErrorMsg}"/>
						</div>
					</c:if>

					</div>
					<input type="submit" class="registButton" value="追加" id="registButtonId" name="registButtonName">
					<input type="hidden" name="id" value="${customer.id}">
					<input type="hidden" name="todayId" value="${today.id}">
					<input type="hidden" name="count" value="${count}">
					<input type="button" class="clearButton" value="クリア" onClick="cancelRegist();checkRegistInput();" >
				</form:form>


			</div>
			
			<div id="blankBottom"></div>
	    </div>
	    
	</body>
</html>
