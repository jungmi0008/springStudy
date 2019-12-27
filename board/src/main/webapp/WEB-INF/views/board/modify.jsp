<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 수정</title>
	<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<script src="/resources/js/jquery-3.4.1.js"></script>
	<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h1>게시물 수정</h1><hr>
	
	<form method="post">
		<label>제  목</label>
		<input type="text" name="title" value="${view.title }"/><br/>
		<label>작성자</label>
		<input type="text" name="writer" value="${view.writer }"/><br/>
		<label>내  용</label>
		<textarea cols="80" rows="10" name="content">${view.content }</textarea><br/>
		
		<button type="submit">수정</button>
		
	</form>
</div>
</body>
</html>














