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
					<col width="40%" />
					<col width="20%" />
					<col width="30%" />
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
								<c:forEach var="i" begin="1" end="${ post.depth }">
								&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${ post.depth != 0 }">
								ㄴ
								</c:if>
							<a href='#' onClick='fn_view(${ post.idx })'>
								${ post.title }
							</a>
							<c:if test="${ post.replycount != 0}">
								<a href='#' onClick='fn_view(${ post.idx })'>
									<span id="replyCnt" style="color: gray"><c:out value="[${ post.replycount }]"/></span>
								</a>
							</c:if>
						</td>
						<td>${ post.name }</td>
						<td>
							<fmt:formatDate value="${ post.indate }" pattern="yyyy-MM-dd hh:mm:ss" />
						<c:if test="${ login != null }">
								<input type="hidden" value="${ post.groupno }">
								<input type="hidden" value="${ post.groupord }">
								<input type="hidden" value="${ post.depth }">
						</c:if>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="pull-right">
			<button type="button" class="btn btn-primary" onclick="goUrl('postAdd.do')">글작성</button>
		</div>
	</div>
	</div>

</body>
</html>