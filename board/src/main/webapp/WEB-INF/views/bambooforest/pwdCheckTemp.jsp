<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js" ></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href="${path}/resources/css/bamboo.css" rel="stylesheet"/> 	

<meta charset="UTF-8">
<title>비밀번호 확인</title>

</head>

<body>
	<div class="container outer">
		<div class="col-md-9 inner" style="margin-top: 40px; width: 500px;">
			<div id="pwdCheckBox" class="col-md-9 inner" style="padding-top: 25px;">
				<h3 style="font-weight: bold; text-align: center;">비밀번호를 입력해주세요</h3>
				<form action="" id="password_confirm" name="password_confirm" method="post">
				
					<div class="pwdBox">
						<input id="password" type="password" class="form-control" value="" maxlength="10" name="password">
						<label for="password" class="error"></label>
					</div>
					
					<input type="hidden" id="idx" name="idx" value="${boardDto.idx}"> 
					<input class="btn btn-default btn-lg btnmargin pull-left" type='button' value='뒤로가기' onClick="location.href = '../postSelect.do?idx='+${boardDto.idx}">
					<input id="okBtn" class="btn btn-primary btn-lg btnmargin pull-right" type="submit" value='입력완료'>
					
				</form>
				<div id="warning" style="color: red;">${message}</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

	$('input[type=password]').focus();
			
	$('#password_confirm').validate({
		onkeyup:false, //비밀번호 지웠을때 안나오게
		
		rules: {
			password: { required:true }
		},
		messages: {
			password: { required: '비밀번호를 입력하십시오.' }
		},
		submitHandler: function() {
			var theForm = document.password_confirm;
			var url = "${URL}";
			
			$.ajax({
				type : "POST",
				url : "pwdCheckCtr.do",
				data : { password: $("#password").val(), idx: $("#idx").val() },
				success : function(data) {
					console.log("1 = 중복o / 0 = 중복x : "+ data);							
					if (data == 1) {
							// 1 : 비밀번호 통과
						 if (url.indexOf("delete") != -1) {
							 // url값에 delete가 포함안되어있으면 -1 반환 
							 var del = confirm("게시글을 삭제할까요?");
							 
							 if(del) {
					 			theForm.action = "postDeleteCtr.do";
					 			alert("게시글 삭제가 완료되었습니다.");
							 } else {
							 alert("게시글로 돌아갑니다.");
				 			theForm.action = "../postSelect.do?idx="+${boardDto.idx}
							 }
				 		} else {
				 			// url값에 딜리트가 포함안되서 수정으로 진행
				 			theForm.action = "postRevise.do"
				 		}
					} else {
						// 0 : 비밀번호 통과못함
						alert("비밀번호가 틀렸습니다.");
					}
					theForm.submit();
				},
				error : function(data) {
					alert("문제야 문제");
				}
			});
		}
	});
</script>
</html>