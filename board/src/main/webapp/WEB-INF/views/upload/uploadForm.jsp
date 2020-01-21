<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!-- 세션 사용여부 옵션 -->
<!-- page 디렉티브의 session속성의 기본값은 true이므로 false로 지정하지 않으면 자동 생성된다. -->
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 올리기(Form)</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>

<%@ include file="../include/topmenu.jsp" %><br><br><br>

<div class="container">

	<!-- 세션변수가 존재하면
	if(session.getAttribute("userid") != null)
	sessionScope : 세션 영역에 저장된 변수를 말한다.
	세션에 저장된 변수는 세션이 종료될 때 까지는 유지된다.
	기본 15분이나 연장 시킬수 있다.
	자바코드로는 아래와 같다.
	<% // session.getAttribue("userid"); %>
	-->
	<c:if test="${sessionScope.userid != null}">
		<h2>${sessionScope.name}(${sessionScope.userid})님의 방문을 환영합니다.</h2>
	</c:if>
	<hr>
	<h2>path : ${pageContext.request.contextPath}</h2><hr>
	<h2>path : ${request.getContextPath()}</h2><hr>
	<h2>path : <%=request.getContextPath() %></h2><hr>
	<h2>path : ${path }</h2><hr>
	

	
	<!-- 파일 업로드를 위한 필수 속성
	     enctype="multipart/form-data" -->
	<!-- target="iframe1" -->
	<!-- form에 target이 없으면 페이지가 넘어가게 된다. -->     
	<!-- 파일을 올리면 원래는 action="${path}/upload/uploadForm"으로 넘어가야 하는데
	     페이지를 넘어가지 않게 하기위해서 제출된 결과를 iframe으로 보낸다. -->
	<form action="${path}/upload/uploadForm" method="post" enctype="multipart/form-data"
		target="iframe1">
		<!-- name="file"의 변수명과 컨트롤러의 MultipartFile file 변수명과 일치해야 한다. -->
		<input type="file" name="file">
		<input type="submit" value="업로드">
	</form>

	<!-- iframe에 업로드된 결과를 출력한다. -->
	<iframe name="iframe1"></iframe>

</div>

</body>
</html>























