<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content">

	<!-- 회원 가입 성공, 회원탈퇴 시 메세지 출력 부분 삽입 -->
	<p>${msg}</p>

	<div class="box">
		<!-- 로그인 화면으로 이동하는 링크 생성 -->
		<div class="box-header with-border">

			<!-- session으로 전달한 login 값이 null이면 로그인이 필요한 상태 -> 로그인 출력  -->
			<c:if test="${login == null}">
				<a href="user/login"><h1>로그인</h1></a>
			</c:if>
			<!-- session으로 전달한 login 값이 null이 아니면 로그인 된 상태 -> 로그아웃 출력 -->
			<c:if test="${login != null}">
				<a href="user/logout"><h1>로그아웃</h1></a>
			</c:if>
			
			<a href="board/postList"><h1>게시판목록</h1></a>

		</div>

	</div>



</section>