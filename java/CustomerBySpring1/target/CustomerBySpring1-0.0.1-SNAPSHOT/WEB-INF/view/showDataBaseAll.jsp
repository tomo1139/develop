<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<c:if test="${userlist != null}" >
		<table border="1">
		<tr><th>ID</th><th>name</th><th>pass</th></tr>
		<c:forEach var="obj" items="${userlist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.name}"/></td>
			<td><c:out value="${obj.pass}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>
		
		<br>

		<c:if test="${customerlist != null}" >
		<table border="1">
		<tr><th>ID</th><th>name</th><th>address</th><th>postal</th><th>home-phone</th><th>mobile-phone</th><th>email</th><th>management_type</th><th>contract_date</th><th>payment_method</th><th>payment_course</th></tr>
		<c:forEach var="obj" items="${customerlist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.name}"/></td>
			<td><c:out value="${obj.address}"/></td>
			<td><c:out value="${obj.postal}"/></td>
			<td><c:out value="${obj.home_phone}"/></td>
			<td><c:out value="${obj.mobile_phone}"/></td>
			<td><c:out value="${obj.email}"/></td>
			<td><c:out value="${obj.management_type}"/></td>
			<td><c:out value="${obj.contract_date}"/></td>
			<td><c:out value="${obj.payment_method}"/></td>
			<td><c:out value="${obj.payment_course}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>

		<br>
		
		<c:if test="${negotiationlist != null}" >
		<table border="1">
		<tr><th>ID</th><th>customer_id</th><th>user_id</th><th>date</th><th>means</th><th>opponent</th><th>status</th><th>detail</th></tr>
		<c:forEach var="obj" items="${negotiationlist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.customer_id}"/></td>
			<td><c:out value="${obj.user_id}"/></td>
			<td><c:out value="${obj.date}"/></td>
			<td><c:out value="${obj.means}"/></td>
			<td><c:out value="${obj.opponent}"/></td>
			<td><c:out value="${obj.status}"/></td>
			<td><c:out value="${obj.detail}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>

		<br>
		
		<c:if test="${todaylist != null}" >
		<table border="1">
		<tr><th>ID</th><th>customer_id</th><th>user_id</th><th>date</th><th>m_flg</th></tr>
		<c:forEach var="obj" items="${todaylist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.customer_id}"/></td>
			<td><c:out value="${obj.user_id}"/></td>
			<td><c:out value="${obj.date}"/></td>
			<td><c:out value="${obj.m_flg}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>

		<br>
		
		<c:if test="${customerMeanslist != null}" >
		<table border="1">
		<tr><th>ID</th><th>coms</th></tr>
		<c:forEach var="obj" items="${customerMeanslist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.coms}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>

		<br>
		
		<c:if test="${customerOpponentlist != null}" >
		<table border="1">
		<tr><th>ID</th><th>coms</th></tr>
		<c:forEach var="obj" items="${customerOpponentlist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.coms}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>

		<br>
		
		<c:if test="${customerStatuslist != null}" >
		<table border="1">
		<tr><th>ID</th><th>coms</th></tr>
		<c:forEach var="obj" items="${customerStatuslist}" varStatus="status">
			<tr>
			<td><c:out value="${obj.id}"/></td>
			<td><c:out value="${obj.coms}"/></td>
			</tr>
		</c:forEach>
		</table>
		</c:if>