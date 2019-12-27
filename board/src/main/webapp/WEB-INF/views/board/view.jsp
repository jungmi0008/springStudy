<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 조회</title>
	<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<script src="/resources/js/jquery-3.4.1.js"></script>
	<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h1>게시물 조회</h1><hr>
	
		<label>제  목</label>${view.title }<br/>
		<label>작성자</label>${view.writer }<br/>
		<label>내  용</label>${view.content }<br/>
		
		<div>
			<a href="/board/modify?bno=${view.bno }">게시물 수정</a>
			<a href="/board/delete?bno=${view.bno }">게시물 삭제</a>
		</div>
		
</div>
</body>
</html>














