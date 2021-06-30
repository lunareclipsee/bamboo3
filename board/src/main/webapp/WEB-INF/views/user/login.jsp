<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<div>
		<div>
			<b>bamboo</b>
		</div>
		<div id="loginfail" align="center"></div>
		<div>
			<p>아이디와 비밀번호를 입력하세요</p>

			<!-- 로그인 form에 action값 주기 -->
			<form action="loginCtr" name="formName" method="post">
				<div>
					<input type="text" name="id" class="form-control"
						placeholder="아이디를 입력하세요" required="required" autofocus="autofocus" onkeypress="JavaScript:press(this.form)"/>
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control"
						placeholder="비밀번호를 입력하세요" required="required" onkeypress="JavaScript:press(this.form)"/>
				</div>
				<div>
					<div>
						<button type="submit" class="btn btn-primary">로그인</button>
					</div>
					<div>
						<!-- 회원가입 링크 추가 -->
						<!-- 링크가 헷갈리면 /프로젝트URL/Controller 요청 포맷/요청 이렇게 만들기-->
						<!-- 프로젝트 URL 앞에 / 슬래쉬 추가하기 -->
						<a href="join" class="btn btn-success">회원가입</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>