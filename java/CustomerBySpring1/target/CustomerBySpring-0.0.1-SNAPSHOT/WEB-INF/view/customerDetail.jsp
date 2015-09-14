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

		<div id="detailId"> <i class="fa fa-user fa-icon-user"></i><c:out value="${customer.id}"/> </div>
		<div id="detailName"> <c:out value="${customer.name}"/> </div>
		<div id="detailNameSama"><b> 様: </b></div>
		
		<div id="detailItem">
		<div id="detailItemText"> 契約期間 </div>
		<div id="detailItemData">${contractTerm}ヶ月</div>
		</div>
		
		<div id="detailContractContents">
		<div id="detailContractContentsText"> 契約情報 </div>
	    </div>

		<div id="detailItem2">
		<div id="detailItemText2"> 住所 </div>
		<div id="detailItemData2"><c:out value="${customer.address}"/>
			<a href="javascript:void(0);" onClick="createMap('${customer.address}');">
				<i class="fa fa-google fa-google-icon"></i>
			</a>
		</div>
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
		
		<div id="customerDetailBtn">
			<div id="editButton">
				<spring:url value="UpdateCustomer" var="action" />
				<form:form commandName="formAddCustomer" modelAttributes="formAddCustomer" action="${action}">
					<form:hidden path="id" value="${customer.id}"/>
					<form:hidden path="name" value="${customer.name}"/>
					<form:hidden path="address" value="${customer.address}"/>
					<form:hidden path="postal" value="${customer.postal}"/>
					<form:hidden path="home_phone" value="${customer.home_phone}"/>
					<form:hidden path="mobile_phone" value="${customer.mobile_phone}"/>
					<form:hidden path="email" value="${customer.email}"/>
					<form:hidden path="management_type" value="${customer.management_type}"/>
					<form:hidden path="contract_date" value="${customer.contract_date}"/>
					<form:hidden path="payment_method" value="${customer.payment_method}"/>
					<form:hidden path="payment_course" value="${customer.payment_course}"/>
					<input type="submit" class="eraseButton" value="編集">
				</form:form>
			</div>
		</div>

		<c:if test="${printNegoList.size()!=0}" >

		<div id="hrCenter"> <hr> </div>
		
		<div id="detailContractContents">
		<div id="detailContractContentsText"> 交渉記録 </div>
	    </div>

		<c:forEach var="obj" items="${printNegoList}" varStatus="status">

		<div id="detailItem2">
		<div id="detailItemText2"> 登録者</div>
		<div id="detailItemData2"><c:out value="${obj.userName}"/></div>
		</div>

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

		<div id="detailItem2Detail" style="height:${50+(obj.lineNum)*19}px;">
		<div id="detailItemText2"> 交渉内容</div>
		<div id="detailItemData2">${obj.detail}</div>
		</div>

		<c:if test="${obj.userId == loginUser.id}" >
			
			<div id="customerDetailBtn">
				<div id="editButton">
					<spring:url value="RegistNegotiateEdit" var="action" />
					<form:form action="${action}">
						<input type="hidden" name="id" value="${customer.id}">
						<input type="hidden" name="date" value="${obj.date}">
						<input type="hidden" name="time" value="${obj.time}">
						<input type="hidden" name="method" value="${obj.method}">
						<input type="hidden" name="opponent" value="${obj.opponent}">
						<input type="hidden" name="result" value="${obj.result}">
						<input type="hidden" name="detail" value="${obj.detail}">
						<input type="hidden" name="negoId" value="${obj.negoId}">
						<input type="submit" class="eraseButton" value="編集">
					</form:form>
				</div>
			</div>
		</c:if>

		<div id="hrSub"> <hr> </div>

		</c:forEach>

		<div id="pagingContent2">
			<div id="pagingText">
			</div>
			<c:if test="${pageInfo.maxPage != 1}" >
				<div id="pagingMain">
				<c:if test="${5 < pageInfo.getMaxPage()}" >
				<c:if test="${1 < pageInfo.nowPage}" >
					<a href="detailTarget?page=1" class="pagingLink"><i class="fa fa-angle-double-left fa-icon"></i></a>
					<c:if test="${pageInfo.nowPage <= 1}" >
						<a href="detailTarget?page=1" class="pagingLink"><i class="fa fa-angle-left fa-icon"></i></a>
					</c:if>
					<c:if test="${pageInfo.nowPage > 1}" >
						<a href="detailTarget?page=${pageInfo.nowPage-1}" class="pagingLink"><i class="fa fa-angle-left fa-icon"></i></a>
					</c:if>
				</c:if>
				</c:if>

				<div id="nowToMax">
					${pageInfo.nowPage} to ${pageInfo.maxPage}
				</div>

				<c:forEach var="obj" items="${pageInfo.printPageList}" varStatus="status">
					<c:if test="${obj == pageInfo.nowPage}" >
						<a href="detailTarget?page=${obj}" class="pageNumSelect">${obj}</a>
					</c:if>

					<c:if test="${obj != pageInfo.nowPage}" >
						<a href="detailTarget?page=${obj}" class="pageNum">${obj}</a>
					</c:if>
				</c:forEach>

				<c:if test="${5 < pageInfo.getMaxPage()}" >
					<c:if test="${pageInfo.nowPage != pageInfo.getMaxPage()}" >
						<c:if test="${pageInfo.nowPage < pageInfo.getMaxPage()}" >
							<a href="detailTarget?page=${pageInfo.nowPage+1}" class="pagingLink"><i class="fa fa-angle-right fa-icon"></i></a>
						</c:if>
						<c:if test="${pageInfo.nowPage >= pageInfo.getMaxPage()}" >
							<a href="detailTarget?page=${pageInfo.getMaxPage()}" class="pagingLink"><i class="fa fa-angle-right fa-icon"></i></a>
						</c:if>
						<a href="detailTarget?page=${pageInfo.getMaxPage()}" class="pagingLink"><i class="fa fa-angle-double-right fa-icon"></i></a>
					</c:if>
				</c:if>
				</div>
				
			</c:if>
		</div>

	    </c:if>
	    
		<div id="jumpButton">
		    <img src="./css/img/totop.png" width="32" height="32" alt="TOPに戻る" onclick="jumpToTop();" class="jumpButton"/>
		</div>
				
	    <div id="blankBottom">  </div>

	</body>
</html>
