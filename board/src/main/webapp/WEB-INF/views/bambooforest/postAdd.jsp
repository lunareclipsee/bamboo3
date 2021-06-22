<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<style type="text/css">
.input_infotxt {
	float: left;
	margin: 10px 10px 0 0;
}

.input_write {
	clear: both;
	float: left;
	margin: 10px 0 0 0PX;
	float: left;
}

#title, #content {
	width: 403px;
}
</style>
</head>
<body>
	<div class="container">
			<!-- 나중에 파일 등록을 위해 enctype="multipart/form-data" 을 넣어둠 -->
			<form class="form-inline center-block" action="postAddCtr.do"
				id="frm" name="frm" method="post">

				<div class="form-group pull-left input_infotxt">
					<label class="sr-only" for="name">닉네임</label> <input
						class="form-control" id="name" name="name" placeholder="닉네임"
						type="text" value="" />
				</div>

				<div class="form-group pull-left input_infotxt">
					<label class="sr-only" for="password">비밀번호</label> <input
						id="password" class="form-control" name="password"
						placeholder="비밀번호" type="password" value="" />
				</div>

				<div class="form-group input_write">
					<label class="sr-only" for="title">제목</label> <input id="title"
						class="form-control" name="title" placeholder="제목" type="text"
						value="" />
				</div>

				<div class="form-group input_write">
					<label class="sr-only" for="content">내용</label>
					<textarea class="form-control" rows="10" cols="10" id="content" name="content"
						placeholder="내용"></textarea>
				</div>

				<div class="input_write">
					<input type="submit" class="btn btn-primary" value="저장"> <input
						type="button" class="btn btn-default" value="취소"
						onclick="postListMoveFnc()">
				</div>
			</form>
	</div>

	<script type="text/javascript">
		var postListMoveFnc = function() {
			location.href = 'postList.do'
		}

		$('input[type=submit]').click(
				function() {

					if ($('#title').val() == "") {
						alert("제목을 입력해주세요");
						$('#title').focus()
						return false;
					} else if ($('#name').val() == "") {
						alert("닉네임을 입력해주세요");
						$('#name').focus()
						return false;
					} else if ($('#password').val() == "") {
						alert("비밀번호를 입력해주세요");
						$('#password').focus()
						return false;
					} else if ($('#content').val() == "") {
						alert("내용을 입력해주세요");
						$('#content').focus()
						return false;
					} else if ($('#title').val() && $('#name').val()
							&& $('#password').val()
							&& $('#content').val() != "") {

						alert("게시글 작성이 완료되었습니다!");
						$('form').attr('action', './postAddCtr.do');
					}

				});
	</script>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</html>