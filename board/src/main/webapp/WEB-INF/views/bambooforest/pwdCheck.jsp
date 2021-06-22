<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>비밀번호 확인</title>


<style type="text/css">
input[type=button] {
	margin-left: 68px;
}
</style>
</head>

<body>
	<div class="container bs-docs-container contentBox">
		<div class="col-md-7" style="margin-top: 40px">

			<div id="pageName">본인인증</div>

			<div id="pwdCheckBox" style="padding-top: 25px;">
				<h3 style="font-weight: bold; text-align: center;">비밀번호를 입력해주세요</h3>
				<div style="color: red;">${message}</div>
				<form action="postRevise.do" method="post">
					<input type="password" class="form-control pwdInputBox" value=""
						maxlength="12" name="password"> 
						<input type="hidden" name="idx" value="${boardDto.idx}"> 
						
						<input class="btn btn-default btn-lg"
						type='button' value='뒤로가기'
						onClick="location.href='../postSelect.do?idx=${boardDto.idx}'">
					<input id="editBtn" class="btn btn-primary btn-lg" type="submit"
						value='입력완료'>
				</form>

			</div>
		</div>
		
${URL}
	</div>



</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript">
	$('input[type=password]').focus();
</script>
</html>