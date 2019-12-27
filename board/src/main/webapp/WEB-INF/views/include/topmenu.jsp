<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" 
				data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${path}">Home</a>
		</div>
		<div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							회원관리 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${path}/user/login.do">로그인</a></li>
							<li><a href="${path}/member/insertMember.do">회원가입</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							게시물관리 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${path}/board/list">게시물 목록</a></li>
							<li><a href="${path}/board/listPage?num=1">게시물 목록(페이징 처리)</a></li>
							<li><a href="${path}/board/write">게시물 작성</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							로그인 관리 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${path}/member/login.do">로그인 (Session)</a></li>
							<li><a href="${path}/member/logout.do">로그아웃 (Session)</a></li>
						</ul>
					</li>
					<li>
						<c:choose>
							<c:when test="${sessionScope.userid == null}">
								<a href="${path}/member/login.do">로그인</a>
							</c:when>
							<c:otherwise>
								<a href="${path}/member/logout.do">로그아웃</a>
							</c:otherwise>
						</c:choose>
					</li>
				</ul>
				
				<form class="navbar-form navbar-right" name="form1" method="post">
					<div class="form-group">
						<input type="text" class="form-control" name="id" id="id"
								size="12" maxlength="12" placeholder="아이디">
						<input type="password" class="form-control" name="passwd" id="passwd"
								size="12" maxlength="12" placeholder="비밀번호">
					</div>
					<button type="button" class="btn btn-primary" id="btnLogin">
						<c:choose>
							<c:when test="${sessionScope.userid == null}">
							
								<span class="glyphicon glyphicon-log-in"></span>로그인
							</c:when>
							
							<c:otherwise>
								<span class="glyphicon glyphicon-log-out"></span>로그아웃
							</c:otherwise>
						</c:choose>
					</button>
				</form>
			</div>
		</div>
	</div>
</nav>

<script>
$(function() {
	$("#btnLogin").click(function() {
		//폼 데이터를 서버로 제출
		if(${sessionScope.userid == null}) { 
			var id 		= $("#id").val();	//태그의 value 속성값. 즉, 태그에 입력한 값
			var passwd 	= $("#passwd").val();
	
			if(id == "") {
				alert("아이디를 입력하십시오.");
				$("#id").focus();	//입력 포커스 이동
				return; //함수 종료
			}
			if(passwd == "") {
				alert("비밀번호를 입력하십시오.");
				$("#passwd").focus();
				return;
			}
			//폼 데이터를 서버로 제출
			document.form1.action = "${path}/member/login_check.do";
		} else {
			document.form1.action = "${path}/member/logout.do";
		}
		document.form1.submit();
	});
});
</script>





















