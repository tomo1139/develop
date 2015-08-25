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
		<link href="./css/jquery.datetimepicker.css" rel="stylesheet">
		<title>顧客管理システム</title>
		<script src="<c:url value="/js/function.js" />" type="text/javascript"></script>
		<script src="<c:url value="/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
  		<script src="<c:url value="/js/jquery.datetimepicker.js" />"  type="text/javascript"></script>
	</head> 

	<body>
		<header>
			<div id="systemname">顧客管理アプリ</div>
			<a href="TodayVisit" class="todayVisitLink"><i class="fa fa-automobile fa-icon"></i>本日の訪問先</a>
			<a href="SearchPrint" class="searchPrintLink"><i class="fa fa-search fa-icon"></i>顧客データ検索／詳細</a>
			<a href="Login" class="loginLink"><i class="fa fa-home fa-icon"></i>logout</a>
		</header>

		<div id="todayVisitListPrintText">
			<i class="fa fa-search fa-icon"></i>交渉記録登録
		</div>

		<div id="registItemBase">
		<div id="registItemText"> ${customer.id} </div>
		<div id="registItemText2">${customer.name}</div>
		<div id="registItemText3">${customer.contract_date}</div>
		</div>

		<div id="detailContractContents">
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
				<form:form modelAttribute="formRegist" action="${action}" name="registForm">
					<div id="registContractForm">

					<div id="registContractInput"> 
						<input id="datetime" type="text" name="datetime" value="${nowdatetime}">
						<script>
							$(function(){
								$('#datetime').datetimepicker({
									step:5
								});
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
						<form:textarea name="detail" path="detail" placeholder="input detail" cols="40" rows="7" resize="none" cssClass="detailformclass"/>
					</div>

					</div>
					<input type="submit" class="registButton" value="追加">
					<input type="hidden" name="id" value="${customer.id}">
					<input type="hidden" name="todayId" value="${today.id}">
					<input type="hidden" name="count" value="${count}">
					<input type="button" class="clearButton" value="クリア" onClick="cancelRegist()">
				</form:form>


			</div>
	    </div>

	</body>
</html>
