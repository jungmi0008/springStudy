<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 세션 사용여부 옵션 -->
<!-- page 디렉티브의 session 속성의 기본 값은 ture이므로 
	 false로 지정하지 않으면 자동 생성이 된다. -->
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인 프로그램</title>
	<%@ include file="include/header.jsp" %>
</head>

<body data-spy="scroll" data-target=".navbar" data-offset="50">

	<%@ include file="include/topmenu.jsp" %><br><br><br>
	
	<!-- 세션변수가 존재하면
		 if(session.getAttriubte("userid") != null)
		 sessionScope : 세션 영역에 저장된 변수를 말한다.
		 세션에 저장된 변수는 세션이 종료될 때 까지는 유지된다.
		 기본 15분이나 연장시킬 수 있다.
		 자바코드로는 아래와 같이 사용한다.
		 <% // session.getAttribute("userid"); %>
	 -->
	 <c:if test="${sessionScope.userid != null }">
	 	<h2>
	 		${sessionScope.name}(${sessionScope.userid})님의 방문을 환영합니다.
	 	</h2>
	 </c:if>

<hr>
<h1>${message}</h1>

</body>
</html>



















