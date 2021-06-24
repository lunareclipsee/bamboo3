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
							<input type="text" id="title" class="form-control" name="title" maxlength="30" value="${ boardDto.title }">
						</td>
					</tr>
					<tr>
						<th class="textCenter success">작성자</th>
						<td class="textCenter">${ boardDto.name } (${ boardDto.inip })</td>
						<th class="textCenter success" scope="row">작성일</th>
						<td><fmt:formatDate value="${ boardDto.indate }"
							pattern="yyyy-MM-dd a hh:mm:ss" /></td>
					</tr>

					<tr>
						<th class="textCenter success">내용</th>
						<td colspan="3">
							<textarea id="content" class="form-control" name="content" maxlength="300" rows="5">${boardDto.content}</textarea>
						</td>
					</tr>
				</table>
				<div class="pull-right">
					<input id="reviseCancelBtn" class="btn btn-default" type="button" value="취소">
					<input id="reviseConfirmBtn" class="btn btn-primary" type="button" value="수정하기">
					<input type="hidden" id="idx" name="idx" value="${ boardDto.idx }">
					<input type="hidden" id="name" name="name" value="${ boardDto.name }">
				</div>
			</form>
		</div>
	</div>
</body>
</html>