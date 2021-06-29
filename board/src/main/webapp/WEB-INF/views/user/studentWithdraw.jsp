<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>회원탈퇴</title>
<script type="text/javascript"
	src="/englishvillage/resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$('.layoutUl').children().eq(2).addClass('on');
 

});


</script>
<style type="text/css">

#editBtn, #backBtn {
	margin-left: 68px;

}

</style>
</head>

<body>
	<header><jsp:include page="/WEB-INF/views/common/Header.jsp" /></header>
	<div class="container bs-docs-container contentBox">
		<jsp:include page="/WEB-INF/views/common/memberLayoutEx.jsp" />
		<div class="col-md-9" style="margin-top: 40px">

			<div id="pageName">회원탈퇴</div>

			<div id="pwdCheckBox" style="padding-top: 25px;">
				<h4 style="font-weight: bold; text-align: center;">회원탈퇴 시 보유 포인트가 사라집니다.</h4>
				<h4 style="font-weight: bold; text-align: center;">동의 하시면 회원탈퇴 버튼을 눌러주세요.</h4>

<h3 style="font-weight: bold; text-align: center; color: #FA6980; margin-top: 30px;">보유 포인트</h3>
<h3 style="font-weight: bold; text-align: center; margin-bottom: 40px;">${member.memberPoint} 포인트</h3>

				<form action="deleteCtr.do" method="post">
					<input id="editBtn" class="btn btn-default btn-lg" type='submit' value='회원탈퇴'>
					<input id="backBtn" class="btn btn-success btn-lg" type='button' value='뒤로가기' onClick="location.href='/englishvillage/student/update.do'">
				</form>
				
				
			</div>
		</div>
	</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>