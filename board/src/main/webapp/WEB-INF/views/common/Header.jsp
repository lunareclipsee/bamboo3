<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>header</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<link href="${path}/resources/css/bamboo.css" rel="stylesheet" />
<script src="${path}/resources/js/bamboo.js"></script>

</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/board/postList">대나무숲</a>
			</div>
		</div>

		<div>
			<c:if test="${login == null}">
				<li><a href="/user/login"><span>로그인</span></a></li>
				<li><a href="/user/join"><span>회원가입</span></a></li>
			</c:if>
			<!-- session으로 전달한 login 값이 null이 아니면 로그인 된 상태 -> 로그아웃 출력 -->
			<c:if test="${login != null}">
			<li><a href="/user/myPage"><span>내정보</span></a></li>
				<li><a href="/user/logout"><span>로그아웃</span></a></li>
			</c:if>
		</div>

	</nav>
</body>
</html>