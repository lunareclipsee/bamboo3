<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>${ boardDto.title }</title>
<style type="text/css">
th {
	text-align: center;
}

.input_margin {
	margin: 0px 10px 0px 0px;
}
</style>

</head>

<body>
	<div class="container">
		<div class="col-md-9" style="margin-top: 40px">

			<table class="table table-bordered">

				<tr>
					<th class="textCenter success">제목</th>
					<td colspan="3">${ boardDto.title }</td>
				</tr>
				<tr>
					<th class="textCenter success">작성자</th>
					<td class="textCenter">${ boardDto.name }(${ boardDto.inip })</td>
					<th class="textCenter success">작성일</th>
					<td><fmt:formatDate value="${ boardDto.indate }"
							pattern="yyyy-MM-dd a hh:mm:ss" /></td>
				</tr>

				<tr>
					<th class="textCenter success">내용</th>
					<td colspan="3">${ boardDto.content }</td>
				</tr>

			</table>

				<input id="backBtn" class="btn btn-default" type='button' value='목록'
					onClick="postListMoveFnc()"> <input id="Btn"
					class="btn btn-danger pull-right" type="button" onclick="deleteMoveFnc()" value='삭제'>
				<input id="edittBtn" class="btn btn-primary pull-right input_margin"
					type="button" value="수정" onclick="editMoveFnc()"> 
		</div>

	</div>

</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script type="text/javascript">
	var postListMoveFnc = function() {
		location.href = 'postList.do?idx=${boardDto.idx}'
	}
	var editMoveFnc = function() {
		location.href = 'edit/pwdCheck.do?idx=${boardDto.idx}'
	}
	var deleteMoveFnc = function() {
		location.href = 'delete/pwdCheck.do?idx=${boardDto.idx}'
	}
</script>

</html>