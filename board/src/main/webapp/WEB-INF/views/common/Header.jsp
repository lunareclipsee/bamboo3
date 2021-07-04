<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>header</title>
<!-- 제이쿼리 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 시간관리 -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<!-- CSS -->
<link href="/resources/css/bamboo.css" rel="stylesheet" />
<script src="/resources/js/bamboo.js"></script>

</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/board/postList">게시판 바로가기</a>
			</div>
			<div class='pull-right'>
				<c:if test="${login == null}">
					<span><a class="navbar-brand" href="/user/login">로그인</a></span>
					<span><a class="navbar-brand" href="/user/join">회원가입</a></span>
				</c:if>
				<!-- session으로 전달한 login 값이 null이 아니면 로그인 된 상태 -> 로그아웃 출력 -->
				<c:if test="${login != null}">
					<span class="navbar-brand"><strong>${login.name} 님의 방문을 환영합니다.</strong></span>
					<span><a class="navbar-brand" href="/user/myPage.do">내정보</a></span>
					<span><a class="navbar-brand" href="/user/logout">로그아웃</a></span>
				</c:if>
			</div>
		</div>
	</nav>
</body>
</html>