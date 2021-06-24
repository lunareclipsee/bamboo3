<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>대나무숲</title>
</head>
<body>
<header><jsp:include page="/WEB-INF/views/common/Header.jsp" /></header>
	<div class="container outer">
	<div class="col-md-9 inner">
		<form id="frm" name="frm" method="post">
			<table class="table table-hover text-center">
				<colgroup>
				 	<col width="10%" />
					<col width="30%" />
					<col width="30%" />
					<col width="20%" />
				</colgroup>
				<tbody>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
					</tr>
				</thead>
					
				<c:forEach items="${ postList }" var="post" varStatus="status">
					<tr>
						<td>${ post.idx }</td>
						<td class="text-left">
							<a href='#' onClick='fn_view(${ post.idx })'>
								<c:out value="${ post.title }"/>
							</a>
						</td>
						<td><c:out value="${ post.name } (${ post.inip })"/></td>
						<td>
							<fmt:formatDate value="${ post.indate }" pattern="yyyy-MM-dd a hh:mm:ss" />
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="pull-right">
			<button type="button" class="btn btn-primary" onclick="postAddFnc()">글작성</button>
		</div>
	</div>
	</div>

</body>
</html>