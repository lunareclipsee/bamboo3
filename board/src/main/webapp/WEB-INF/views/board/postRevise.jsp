<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
</head>
<body>
<header><jsp:include page="/WEB-INF/views/common/Header.jsp" /></header>
	<div class="container outer">
		<div class="col-md-9 inner">
			<form method="post" id="modify_confirm" name="modify_confirm">
				<table class="table table-bordered">
					<tr>
						<th class="textCenter success">제목</th>
						<td colspan="3">
							<input type="text" id="title" name="title" class="form-control" maxlength="50" value="${ boardDto.title }">
						</td>
					</tr>
					
					<tr>
						<th class="textCenter success">작성자</th>
						<td class="textCenter">${ boardDto.name } (${ boardDto.inip })</td>
						<th class="textCenter success" scope="row">작성일</th>
						<td>
							<fmt:formatDate value="${ boardDto.indate }" pattern="yyyy-MM-dd a hh:mm:ss"/>
						</td>
					</tr>

					<tr>
						<th class="textCenter success">내용</th>
						<td colspan="3">
							<textarea id="content" class="form-control" name="content" rows="5" maxlength="200">${boardDto.content}</textarea>
							남은글자: <input type="text" id="counter" name="counter" style="width: 33px; margin-top: 10px" readonly value="200">
						</td>
					</tr>
				</table>
				<div class="pull-right">
					<input type="button" id="reviseCancelBtn" class="btn btn-default" value="취소">
					<input type="button" id="reviseConfirmBtn" class="btn btn-primary" value="수정하기">
					<input type="hidden" id="idx" name="idx" value="${ boardDto.idx }">
					<input type="hidden" id="name" name="name" value="${ boardDto.name }">
				</div>
			</form>
		</div>
	</div>
</body>
</html>