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

		<div id="detailId"> <i class="fa fa-car fa-icon"></i><c:out value="${customer.id}"/> </div>
		<div id="detailName"> <c:out value="${customer.name}"/> </div>
		<div id="detailNameSama"> 様: </div>
		
		<div id="detailItem">
		<div id="detailItemText"> 契約期間 </div>
		<div id="detailItemData">10ヶ月</div>
		</div>
		
		<div id="detailContractContents">
		<div id="detailContractContentsText"> 契約情報 </div>
	    </div>

		<div id="detailItem2">
		<div id="detailItemText2"> 住所 </div>
		<div id="detailItemData2"><c:out value="${customer.address}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 郵便番号 </div>
		<div id="detailItemData2"><c:out value="${customer.postal}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 電話番号 </div>
		<div id="detailItemData2"><c:out value="${customer.home_phone}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 携帯番号 </div>
		<div id="detailItemData2"><c:out value="${customer.mobile_phone}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> メールアドレス </div>
		<div id="detailItemData2"><c:out value="${customer.email}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 契約種別</div>
		<div id="detailItemData2"><c:out value="${customer.management_type}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 契約月</div>
		<div id="detailItemData2"><c:out value="${customer.contract_date}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> お支払い方法</div>
		<div id="detailItemData2"><c:out value="${customer.payment_method}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> お支払いコース</div>
		<div id="detailItemData2"><c:out value="${customer.payment_course}"/></div>
		</div>
		
		<c:if test="${printNegoList.size()!=0}" >
		<hr>
		
		<div id="detailContractContents">
		<div id="detailContractContentsText"> 交渉記録 </div>
	    </div>

		<c:forEach var="obj" items="${printNegoList}" varStatus="status">

		<c:if test="${obj != printNegoList.get(0)}" >
		<hr class="hrsub">
		</c:if>

		<div id="detailItem2">
		<div id="detailItemText2"> 日付</div>
		<div id="detailItemData2"><c:out value="${obj.date}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 時間</div>
		<div id="detailItemData2"><c:out value="${obj.time}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 交渉手段</div>
		<div id="detailItemData2"><c:out value="${obj.method}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 交渉相手</div>
		<div id="detailItemData2"><c:out value="${obj.opponent}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 交渉結果</div>
		<div id="detailItemData2"><c:out value="${obj.result}"/></div>
		</div>

		<div id="detailItem2">
		<div id="detailItemText2"> 交渉内容</div>
		<div id="detailItemData2"><c:out value="${obj.detail}"/></div>
		</div>

		</c:forEach>

	    </c:if>
	    
	    <div id="blankBottom"> &nbsp </div>

	</body>
</html>
