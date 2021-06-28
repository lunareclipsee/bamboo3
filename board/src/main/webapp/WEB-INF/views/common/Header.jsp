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
<link href="${path}/resources/css/bamboo.css" rel="stylesheet" />
<script src="${path}/resources/js/bamboo.js"></script>

</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/bambooforest/postList.do">대나무숲</a>
			</div>
		</div>

		<div>
			<c:if test="${login == null}">
				<li><a href="/bamboo/user/login.do"><span>로그인</span></a></li>
				<li><a href="/bamboo/user/join.do"><span>회원가입</span></a></li>
			</c:if>
			<!-- session으로 전달한 login 값이 null이 아니면 로그인 된 상태 -> 로그아웃 출력 -->
			<c:if test="${login != null}">
				<li><a href="/bamboo/user/logout.do"><i
						class="fa fa-circle-o text-red"></i> <span>로그아웃</span></a></li>
			</c:if>
		</div>

	</nav>
</body>
</html>