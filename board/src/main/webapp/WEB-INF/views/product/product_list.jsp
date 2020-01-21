<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 목록</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>

	<%@ include file="../include/topmenu.jsp" %><br><br>
	
	<h2>상품 목록</h2><hr>
	
		<table class="table table-bordered table-strpied nanum table-hover">
			<tr>
				<th>상품코드${path}</th>
				<th>상품이미지</th>
				<th>상품명</th>
				<th>상품가격</th>
			</tr>
			<c:forEach var="row" items="${list}">
			<tr>
				<td>${row.product_id}</td>
				<td><img src="${path}/images/${row.picture_url}" width="100px" height="100px"></td>
				<td><a href="${path}/product/detail/${row.product_id}">${row.product_name}</a></td>
				<td><fmt:formatNumber value="${row.price}" pattern="#,###" /></td>
			</tr>
			</c:forEach>
			
		</table>
</body>
</html>





















