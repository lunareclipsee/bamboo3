<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>${ boardDto.title }</title>
</head>
<body>
<header><jsp:include page="/WEB-INF/views/common/Header.jsp" /></header>
	<div class="container outer">
		<div class="col-md-9 inner">

			<table class="table table-bordered">

				<tr>
					<th class="textCenter success">제목</th>
					<td colspan="3">${ boardDto.title }</td>
				</tr>
				
				<tr>
					<th class="textCenter success">작성자</th>
					<td class="textCenter">${ boardDto.name } (${ boardDto.inip })</td>
					<th class="textCenter success">작성일</th>
					<td>
						<fmt:formatDate value="${ boardDto.indate }" pattern="yyyy-MM-dd a hh:mm:ss" />
					</td>
				</tr>

				<tr>
					<th class="textCenter success">내용</th>
					<td colspan="3">${ boardDto.content }</td>
				</tr>

			</table>

				<input id="backBtn" class="btn btn-default" type='button' value='목록'
					onClick="location.href = 'postList.do?idx=${boardDto.idx}'"> 
				<input id="edittBtn" class="btn btn-primary pull-right"
					type="button" value="수정" onclick="location.href = 'edit/pwdCheck.do?idx=${boardDto.idx}'"> 
				<input id="Btn"	class="btn btn-danger input_margin pull-right" type="button" onclick="location.href = 'delete/pwdCheck.do?idx=${boardDto.idx}'" value='삭제'>
		</div>
	</div>
</body>
</html>