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
						<fmt:formatDate value="${ boardDto.indate }" pattern="yyyy-MM-dd hh:mm:ss" />
					</td>
				</tr>

				<tr>
					<td colspan="4" style="word-break:break-all"><div style="white-space:pre; margin: 20px">${ boardDto.content }</div></td>
				</tr>
			</table>
			
			<form method="post" id="selectForm" name="selectForm">
				<input type="hidden" id="idx" name="idx" value="${boardDto.idx}"> 
				<input type="hidden" id="id" name="id" value="${boardDto.id}"> 
				<input type="hidden" id="name" name="name" value="${boardDto.name}"> 
			</form>
			<input type="button" onClick="location.href = 'postList?idx=${boardDto.idx}'" id="backBtn" class="btn btn-default" value="목록"> 
			<c:if test="${login.id == boardDto.id}">
				<input type="button" id="editBtn" class="btn btn-primary pull-right" value="수정" onclick="boardEditBtn()" > 
				<input type="button" id="postDeleteBtn" class="btn btn-danger input_margin pull-right" value='삭제'>
				<input type="button" class="btn btn-default" value="답글달기">
			</c:if>
		</div>
	</div>
		<jsp:include page="/WEB-INF/views/board/reply.jsp" />
</body>


<script type="text/javascript">

function boardEditBtn() {
	var theForm = document.selectForm;
	theForm.action = "postRevise.do";
	theForm.submit();
}

</script>
</html>