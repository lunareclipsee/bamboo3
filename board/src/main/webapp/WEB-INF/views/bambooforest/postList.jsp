<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>


<style type="text/css">
tr>th {
	text-align: center;
}
</style>
</head>
<body>
	<h2>대나무숲</h2>
	<div class="container">
		<form id="frm" name="frm" method="post">
			<table class="table table-hover text-center">
				<colgroup>
				 	<col width="10%" />
					<col width="30%" />
					<col width="20%" />
					<col width="20%" />
					<col width="15%" />
				</colgroup>
				<tbody>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>비밀번호</th>
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
						<td><c:out value="${ post.indate }"/></td>
						<td><c:out value="${ post.password }"/></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="pull-right">
			<button type="button" class="btn btn-primary" onclick="postAddFnc()">글작성</button>
		</div>
	</div>



</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript">
	var postAddFnc = function() {
		location.href = 'postAdd.do'
	}
	
	function fn_view(idx){
	    
	  	  var form = document.getElementById("frm");
	  	  var url = "<c:url value='/bambooforest/postSelect.do'/>";
	  	  url = url + "?idx=" + idx;
	    
	 	   form.action = url;    
	 	   form.submit(); 
		}
	
</script>
</html>