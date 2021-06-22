<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 수정</title>
<style type="text/css">
#title, #content {
	width: 403px;
}
</style>

<style type="text/css">
th {
	text-align: center;
}
</style>
</head>

<body>
	<div class="container">
		<div class="col-md-9" style="margin-top: 40px">

			<form method="post">

				<table class="table table-bordered">

					<tr>
						<th class="textCenter success">제목</th>
						<td colspan="3">
							<input type="text" id="title" name="title" maxlength="30" value="${ boardDto.title }">
						</td>
					</tr>
					<tr>
						<th class="textCenter success">작성자</th>
						<td class="textCenter">${ boardDto.name } (${ boardDto.inip })
							<input type="hidden" name="idx" value="${ boardDto.idx }">
							<input type="hidden" name="name" value="${ boardDto.name }">
						
						</td>
						<th class="textCenter success" scope="row">작성일</th>
						<td><fmt:formatDate value="${ boardDto.indate }"
							pattern="yyyy-MM-dd a hh:mm:ss" /></td>
					</tr>

					<tr>
						<th class="textCenter success">내용</th>
						<td colspan="3"><textarea id="content" name="content" maxlength="300" rows="5">${boardDto.content}</textarea>
						</td>
					</tr>
				</table>
				<input id="okBtn" class="btn btn-success" type="submit" value="변경하기">
				<input id="cancelBtn" class="btn btn-default" type="button"
					value="취소"
					onclick="editMoveFnc()">

			</form>
		</div>


	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
<script type="text/javascript">
	var editMoveFnc = function() {
		location.href = 'postSelect.do?idx=${boardDto.idx}'
	}
	
	$('#title').focus();
	
	$('#okBtn').on('click', function() {
	
		if ($('#title').val() == "") {
			alert("제목을 입력해주세요.");
			$('#title').focus();
			return false;
		} else if ($('#content').val() == "") {
			alert("내용을 입력해주세요.");
			$('#content').focus();
			return false;
		}
		alert("게시글 수정이 완료되었습니다.");
		$('form').attr('action', './postReviseCtr.do');
	});

</script>
</html>