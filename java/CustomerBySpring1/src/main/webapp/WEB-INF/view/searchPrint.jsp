<!DOCTYPE html>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<link href="<c:url value="/css/common.css" />" rel="stylesheet">
		<link href="./css/font-awesome.css" rel="stylesheet">
		<title>顧客管理システム</title>
		<script src="<c:url value="/js/function.js" />" type="text/javascript"></script>
		<script src="<c:url value="/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
	</head> 
	<body>
		<header>
			<div id="systemname">顧客管理アプリ</div>
			<a href="TodayVisit" class="todayVisitLink"><i class="fa fa-automobile fa-icon"></i>本日の訪問先</a>
			<a href="SearchPrint" class="searchPrintLink"><i class="fa fa-search fa-icon"></i>顧客データ検索／詳細</a>
			<a href="Login" class="loginLink"><i class="fa fa-home fa-icon"></i>logout</a>
		</header>
		
		<div id="todayVisitListPrintText">
			<c:if test="${searchList == null}" >
				<i class="fa fa-search fa-icon"></i>顧客データ検索：0件
			</c:if>
			<c:if test="${searchList != null}" >
				<i class="fa fa-search fa-icon"></i>顧客データ検索：<c:out value="${searchList.size()}"/>件
			</c:if>
		</div>
		
		<div id="searchFormMain">
			<spring:url value="SearchPrint" var="action" />
			<form:form modelAttribute="formSearch" action="${action}">
			
			<div id="searchInfoText">
			契約番号<br>
			契約者氏名<br>
			契約期間<br>
			住所<br>
			郵便番号<br>
			</div>
			
			<div id="searchInfoForm">
			<form:input path="id"/><br>
			<form:input path="name"/><br>
			<form:input path="period"/><br>
			<form:input path="address"/><br>
			<form:input path="postal"/><br>
			</div>

			<div id="searchButton">
				<input type="submit" class="SearchButton" value="Search">
			</div>
			</form:form>
		</div>
		
		<hr>

		<div id="searchListPrintMain">
			<c:forEach var="obj" items="${searchList}" varStatus="status">
				<div id="todayVisitContents">
				<div id="searchVisitCustomerInfo">
					<a href="CustomerDetail" class="TocustomerDetail">
					<div id="todayId"><c:out value="${obj.id}"/></div>
					<div id="todayName"><c:out value="${obj.name}"/></div>
					<div id="todayAddress"><c:out value="${obj.address}"/></div>
					</a>
				</div>
				<spring:url value="SearchUpdate" var="action" />
				<form:form action="${action}">
					<c:if test="${obj.isToday == 0}" >
						<input type="image" src="./css/img/uncheck.gif" alt="送信する" class="searchVisitRecordBtnCls">
					</c:if>
					<c:if test="${obj.isToday == 1}" >
						<input type="image" src="./css/img/check.gif" alt="送信する" class="searchVisitRecordBtnCls">
					</c:if>
					<input type="hidden" name="customerId" value="${obj.id}">
					<input type="hidden" name="isToday" value="${obj.isToday}">
				</form:form>
				</div>
			</c:forEach>
		</div>
		</div>
	</body>
</html>