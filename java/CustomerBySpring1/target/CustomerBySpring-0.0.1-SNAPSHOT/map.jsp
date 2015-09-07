<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<script src="<c:url value="/js/function.js" />" type="text/javascript"></script>
		<script type='text/javascript' src='http://maps.google.com/maps/api/js?sensor=false'></script>
	</head> 
	<body onLoad="showMap('${customer.address}');" class="mapclass">
		<div id="gmap" style="width: 590px; height: 390px;"></div>
	</body>
</html>
	