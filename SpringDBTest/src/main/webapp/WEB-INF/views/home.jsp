<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>회원리스트 </h1>
<table>
	<thead>
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이	름</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${memberList}" var="member">
		<tr>
			<td>${member.id }</td>
			<td>${member.pw }</td>
			<td>${member.name }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>
