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
					<td colspan="4" style="word-break:break-all"><div>${ boardDto.content }</div></td>
				</tr>
			</table>
			<input type="button" onClick="location.href = 'postList.do?idx=${boardDto.idx}'" id="backBtn" class="btn btn-default" value="목록"> 
			<input type="button" onclick="location.href = 'edit/pwdCheck.do?idx=${boardDto.idx}'" id="editBtn" class="btn btn-primary pull-right" value="수정" > 
			<input type="button" onclick="location.href = 'delete/pwdCheck.do?idx=${boardDto.idx}'" id="deleteBtn" class="btn btn-danger input_margin pull-right" value='삭제'>
		</div>
	</div>
</body>
</html>