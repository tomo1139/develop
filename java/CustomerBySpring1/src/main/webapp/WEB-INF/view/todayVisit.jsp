<!DOCTYPE html>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<%@ include file="head_content.jsp" %>
	</head> 
	<body onload="nowloadingOff();" onUnload="nowloadingOff();">
		<header>
			<%@ include file="header_content.jsp" %>
		</header>
		
		<div id="todayVisitContents">

			<div id="todayVisitListPrint">
				<div id="loginUser">
					<i class="fa fa-user fa-icon"></i><b>login: ${loginUser.name}</b>
				</div>
				<div id="todayVisitListPrintText">
					<i class="fa fa-automobile fa-icon"></i>本日の訪問先：<c:out value="${printTodayList.size()}"/>件
				</div>
				
				<div id="todayVisitListPrintMain">
					<div id="todayVisitPullDownBtn">
						<input onclick="clickTodayPulldownBtn()" type="image" class="todayVisitPullDownBtnCls" src="./css/img/hidePulldown.gif" alt="送信する" width="50px" height="50px" name="todayVisitPullDownBtnName" >
					</div>

					<div id="todayMainContents">
					<c:forEach var="obj" items="${printTodayList}" varStatus="status">
						<div id="todayVisitContents">
							<div id="todayVisitDeleteBtn">
								<spring:url value="TodayDelete" var="action" />
								<form:form modelAttribute="formDelete" action="${action}">
									<input type="image" src="./css/img/minus.png" alt="送信する" class="todayVisitDelBtnCls">
									<input type="hidden" name="deleteToday" value="${obj.todayPk}">
								</form:form>
							</div>
							<a href="CustomerDetail?id=${obj.id}" class="TocustomerDetail">
							<div id="todayVisitCustomerInfo">
								<div id="todayId"><c:out value="${obj.id}"/></div>
								<div id="todayName"><c:out value="${obj.name}"/></div>
								<div id="todayAddress">
									<c:out value="${obj.address}"/>
								</div>
							</div>
							</a>
							<spring:url value="RegistNegotiate" var="action" />
							<form:form action="${action}">
								<c:if test="${obj.m_flg == 0}" >
									<input type="image" src="./css/img/notregist.gif" alt="送信する" class="todayVisitRecordBtnCls">
								</c:if>
								<c:if test="${obj.m_flg == 1}" >
									<input type="image" src="./css/img/regist.gif" alt="送信する" class="todayVisitRecordBtnCls">
								</c:if>
								<input type="hidden" name="id" value="${obj.id}">
								<input type="hidden" name="todayId" value="${obj.todayPk}">
							</form:form>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
			
			<hr>
			
			<div id="todayVisitUnregistered">
				<div id="todayVisitListPrintText">
					<i class="fa fa-check fa-icon"></i>交渉記録未記入：<c:out value="${printTodayUnregList.size()}"/>件
				</div>

				<div id="todayVisitListPrintMain">
					<div id="todayVisitPullDownBtn">
						<input onclick="clickTodayPulldownBtnUnreg()" type="image" class="todayVisitPullDownBtnCls" src="./css/img/hidePulldown.gif" alt="送信する" width="50px" height="50px" name="todayVisitPullDownBtnUnregName" >
					</div>

					<div id="todayUnregMainContents">
					<c:forEach var="obj" items="${printTodayUnregList}" varStatus="status">
						<div id="todayVisitContents">
							<div id="todayVisitDeleteBtn">
							<spring:url value="TodayDelete" var="action" />
							<form:form modelAttribute="formDelete" action="${action}">
								<input type="image" src="./css/img/minus.png" alt="送信する" class="todayVisitDelBtnCls">
								<input type="hidden" name="deleteToday" value="${obj.todayPk}">
							</form:form>
							</div>
							<a href="CustomerDetail?id=${obj.id}" class="TocustomerDetail">
								<div id="todayVisitCustomerInfo">
									<div id="todayId"><c:out value="${obj.id}"/></div>
									<div id="todayName"><c:out value="${obj.name}"/></div>
									<div id="todayAddress"><c:out value="${obj.address}"/></div>
								</div>
							</a>
							<spring:url value="RegistNegotiate" var="action" />
							<form:form action="${action}">
								<c:if test="${obj.m_flg == 0}" >
									<input type="image" src="./css/img/notregist.gif" alt="送信する" class="todayVisitRecordBtnCls">
								</c:if>
								<c:if test="${obj.m_flg == 1}" >
									<input type="image" src="./css/img/regist.gif" alt="送信する" class="todayVisitRecordBtnCls">
								</c:if>
								<input type="hidden" name="id" value="${obj.id}">
								<input type="hidden" name="todayId" value="${obj.todayPk}">
							</form:form>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>

			<div id="jumpButton">
			    <img src="./css/img/totop.png" width="32" height="32" alt="TOPに戻る" onclick="jumpToTop();" class="jumpButton"/>
			</div>
		</div>

	    <div id="blankBottom"> </div>
	</body>
</html>
