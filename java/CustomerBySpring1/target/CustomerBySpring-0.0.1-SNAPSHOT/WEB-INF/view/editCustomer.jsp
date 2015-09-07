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

		<div id="addCustomerTitle">
			<i class="fa fa-file-text fa-icon"></i>顧客データ編集
		</div>

		<div id="addCustomerFormMain">
			<spring:url value="EditCustomer" var="action" />
			<form:form commandName="formAddCustomer" modelAttributes="formAddCustomer" action="${action}" id="addCustomerId">
			
			<div id="textMargin1">
				<div id="idText">契約番号 </div>
				<div id="customerIdText">${formAddCustomer.id}</div>
				<div id="idForm">
					<form:hidden required="true" path="id" readonly="true" />
				</div>
			</div>

			<div id="textMargin1">
			<div id="idText">名前</div> 
			<div id="idForm">
				<form:input required="true" path="name" placeholder="テスト顧客"/><br>
			</div>
			</div>

			<div id="textMargin1">
			<div id="idText">住所</div> 
			<div id="idForm">
				<form:input required="true" path="address" placeholder="東京都渋谷区代々木1-21-10"/><br>
			</div>
			</div>
			
			<div id="textMargin1">
			<div id="idText">郵便番号</div>
			<div id="idForm">
				<form:input required="true" path="postal" placeholder="000-0000" pattern="\d{3}-\d{4}" title="3桁の数字、ハイフン（-）、4桁の数字の順で入力して下さい" /><br>
			</div>
			</div>
		
			<div id="textMargin1">
			<div id="idText">電話番号</div>
			<div id="idForm">
				<form:input required="true" path="home_phone" placeholder="0011112222" pattern="\d{10}" title="10桁の数字で入力して下さい"/><br>
			</div>
			</div>
		
			<div id="textMargin1">
			<div id="idText">携帯番号</div>
			<div id="idForm">
				<form:input required="true" path="mobile_phone" placeholder="00011112222" pattern="\d{11}" title="11桁の数字で入力して下さい"/><br>
			</div>
			</div>
		
			<div id="textMargin1">
			<div id="idText">メールアドレス</div>
			<div id="idForm">
				<form:input required="true" path="email" placeholder="aaa@aaa.com" pattern="^[a-zA-Z0-9]+@[a-zA-Z0-9.]+$" title="正しいメールアドレスを入力して下さい"/><br>
			</div>
			</div>
		
		
			<div id="textMargin1">
			<div id="idText">契約種別</div>
			<div id="idForm">
				<form:select path="management_type" items="${managementTypeList}" itemLabel="text" itemValue="text" />
			</div>
			</div>
		
			<div id="textMargin1">
			<div id="idText">契約日</div>
			<div id="idForm">
				<form:input pattern="[0-9]{4}/[0-9]{2}/[0-9]{2}" title="次の形式で入力して下さい。4桁の数字/2桁の数字/2桁の数字" type="datetime" id="contract_date" required="true" path="contract_date" readonly="true" value="${contract_date_print}"/><br>
				<script>
					$(function(){
						$.datepicker.regional['en'] = {
							closeText: 'Done',
							prevText: 'Prev',
							nextText: 'Next',
							currentText: 'Today',
							monthNames: ['January','February','March','April','May','June',
							'July','August','September','October','November','December'],
							monthNamesShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
							'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
							dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
							dayNamesShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
							dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa'],
							weekHeader: 'Wk',
							dateFormat: 'yy/mm/dd',
							firstDay: 1,
							isRTL: false,
							showMonthAfterYear: false,
							yearSuffix: ''};
						$.datepicker.setDefaults($.datepicker.regional['en']);
						$('#contract_date').datepicker().datepicker({ dateFormat: 'yy/mm/dd' });
					});
				</script>
			</div>
			</div>
		
			<div id="textMargin1">
			<div id="idText">お支払い方法</div>
			<div id="idForm">
				<form:select path="payment_method" items="${paymentMethodList}" itemLabel="text" itemValue="text" />
			</div>
			</div>
		
			<div id="textMargin1">
			<div id="idText">お支払いコース</div>
			<div id="idForm">
				<form:select path="payment_course" items="${paymentCourseList}" itemLabel="text" itemValue="text" />
			</div>
			</div>
			
			<c:if test="${addSuccessMsg != null}" >
				<div id="successMsg">
					<c:out value="${addSuccessMsg}"/>
				</div>
			</c:if>

			<c:if test="${addFailMsg != null}" >
				<div id="errorMsg">
					<c:out value="${addFailMsg}"/>
				</div>
			</c:if>

			<div id="searchButton">
				<input type="submit" class="SearchButton" value="更新">
			</div>

			</form:form>

		</div>
	</body>
</html>
	