<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container outer text-center" style="margin-top: 150px;">
		<div id="loginfail" align="center"></div>
		<div class="inner">
			<b style="font-size: 160px">Bamboo</b>
			<p>아이디와 비밀번호를 입력해주세요</p>

			<!-- 로그인 form에 action값 주기 -->
			<div>
			<form action="loginCtr" name="formName" method="post">
				<div class="form-group">
					<input type="text" name="id" class="form-control" style="width: 300px; margin: auto;"
						placeholder="아이디를 입력해주세요" required="required" autofocus="autofocus" onkeypress="JavaScript:press(this.form)"/>
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control" style="width: 300px; margin: auto;"
						placeholder="비밀번호를 입력해주세요" required="required" onkeypress="JavaScript:press(this.form)"/>
				</div>
				<div class="">
					<div>
						<a href="join" class="btn btn-success">회원가입</a>
						<button type="submit" class="btn btn-primary">로그인</button>
					</div>
					<input type="hidden" id="msgg" value="${msg}">
				</div>
			</form>
			</div>
		</div>
	</div>
</body>

<c:if test="${msg != null}">
<script type="text/javascript">
var msgg = $('#msgg').val();
alert(msgg);
</script>
</c:if>


</html>