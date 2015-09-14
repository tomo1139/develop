<!DOCTYPE html>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
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
			<c:if test="${pageInfo == null}" >
				<i class="fa fa-search fa-icon"></i>顧客データ検索：0件
			</c:if>
			<c:if test="${pageInfo != null}" >
				<i class="fa fa-search fa-icon"></i>顧客データ検索：<c:out value="${pageInfo.dataNum}"/>件
			</c:if>
		</div>
		
		<div id="searchFormMain">
			<spring:url value="SearchPrint" var="action" />
		<form:form modelAttribute="formSearch" action="${action}" id="searchFormId">

			<div id="textMargin1">
				<div id="idText">契約番号</div>
				<div id="idForm">
					<form:input path="id" type="number" min="0" value="${oldFormData.id}" pattern="^[0-9]+$" title="※数字" />
				</div>
			</div>
			<div id="textMargin1">
				<div id="idText">契約者氏名</div>
				<div id="idForm">
					<form:input path="name" value="${oldFormData.name}" />
					<br>
				</div>
			</div>
			<div id="textMargin1">
				<div id="idText">契約期間(月数)</div>
				<div id="idForm">
					<form:input path="period" type="number" min="0" maxlength="10"
						value="${oldFormData.period}" pattern="^[0-9]+$" title="※数字" />
					<br>
				</div>
			</div>
			<div id="textMargin1">
				<div id="idText">住所</div>
				<div id="idForm">
					<form:input path="address" value="${oldFormData.address}" />
					<br>
				</div>
			</div>
			<div id="textMargin1">
				<div id="idText">郵便番号</div>
				<div id="idForm">
					<form:input path="postal" type="tel" maxlength="8"
						value="${oldFormData.postal}" pattern="^[0-9-]+$" title="※数字とハイフン" />
					<br>
				</div>
			</div>

			<div id="searchButton">
				<input type="submit" class="SearchButton" value="Search">
			</div>

		</form:form>
	</div>
		
		<c:if test="${pageInfo != null}" >
		<c:if test="${pageInfo.dataNum != 0}" >

		<br>
		<br>
		<div id="hrCenter"> <hr> </div>

		<div id="searchListPrintMain">
			<c:forEach var="obj" items="${printList}" varStatus="status">
				<div id="todayVisitContents">
				<a href="CustomerDetail?id=${obj.id}" class="TocustomerDetail">
					<div id="searchVisitCustomerInfo">
					<div id="todayId"><c:out value="${obj.id}"/></div>
					<div id="todayName"><c:out value="${obj.name}"/></div>
					<c:if test="${obj.negotiateDay == null}" >
						<div id="negotiateName"><c:out value="交渉履歴はありません"/></div>
					</c:if>
					<c:if test="${obj.negotiateDay != null}" >
						<div id="negotiateName"><c:out value="担当営業: ${obj.negotiateName}"/></div>
						<div id="negotiateDay"><c:out value="最新交渉日: ${obj.negotiateDay}"/></div>
					</c:if>
					<div id="todayAddress"><c:out value="${obj.address}"/></div>
					</div>
				</a>
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
		
		<div id="pagingContent">
			<div id="pagingText">&nbsp</div>
			<div id="pagingMain">
				<c:if test="${5 < pageInfo.getMaxPage()}" >
				<c:if test="${1 < pageInfo.nowPage}" >
					<a href="SearchTarget?page=1" class="pagingLink"><i class="fa fa-angle-double-left fa-icon"></i></a>
				<c:if test="${pageInfo.nowPage <= 1}" >
					<a href="SearchTarget?page=1" class="pagingLink"><i class="fa fa-angle-left fa-icon"></i></a>
				</c:if>
				<c:if test="${pageInfo.nowPage > 1}" >
					<a href="SearchTarget?page=${pageInfo.nowPage-1}" class="pagingLink"><i class="fa fa-angle-left fa-icon"></i></a>
				</c:if>
				</c:if>
				</c:if>

				<c:if test="${pageInfo.getMaxPage() != 1}" >
				<div id="nowToMax">
					${pageInfo.nowPage} to ${pageInfo.maxPage}
				</div>

				<c:forEach var="obj" items="${pageInfo.printPageList}" varStatus="status">
				<c:if test="${obj == pageInfo.nowPage}" >
					<a href="SearchTarget?page=${obj}" class="pageNumSelect">${obj}</a>
				</c:if>

				<c:if test="${obj != pageInfo.nowPage}" >
					<a href="SearchTarget?page=${obj}" class="pageNum">${obj}</a>
				</c:if>
				</c:forEach>

				<c:if test="${5 < pageInfo.getMaxPage()}" >
				<c:if test="${pageInfo.nowPage != pageInfo.getMaxPage()}" >
				<c:if test="${pageInfo.nowPage < pageInfo.getMaxPage()}" >
					<a href="SearchTarget?page=${pageInfo.nowPage+1}" class="pagingLink"><i class="fa fa-angle-right fa-icon"></i></a>
				</c:if>
				<c:if test="${pageInfo.nowPage >= pageInfo.getMaxPage()}" >
					<a href="SearchTarget?page=${pageInfo.getMaxPage()}" class="pagingLink"><i class="fa fa-angle-right fa-icon"></i></a>
				</c:if>

				<a href="SearchTarget?page=${pageInfo.getMaxPage()}" class="pagingLink"><i class="fa fa-angle-double-right fa-icon"></i></a>
				</c:if>
				</c:if>
				</c:if>
			</div>

			<div id="jumpButton">
			    <img src="./css/img/totop.png" width="32" height="32" alt="TOPに戻る" onclick="jumpToTop();" class="jumpButton"/>
			</div>
		</div>
		</c:if>
		</c:if>

	    <div id="blankBottom">  </div>

	</body>
</html>