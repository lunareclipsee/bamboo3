<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>

</head>

<body>
	<header><jsp:include page="/WEB-INF/views/common/Header.jsp" /></header>
	<div class="container bs-docs-container contentBox"
		style="margin-top: 150px">
		<div class="col-md-9">
			<form action="pwdCheck" id="selectForm" name="selectForm" method="post">
				<div id="pageName">회원정보</div>

				<div id="tableBox">
					<table class="table table-hover tableFont">
						<tr>
							<th class='tCenter'><span>아이디</span></th>
							<td><span>${userInfo.id}</span></td>
						</tr>
						<tr>
							<th class='tCenter'><span>닉네임</span></th>
							<td><span>${userInfo.name}</span></td>
						</tr>
					</table>
					<!-- 					onclick="location.href = 'edit/pwdCheck'"  -->
					<input type="hidden" id="id" name="id" value="${userInfo.id}"> <input
						type="hidden" id="name" name="name" value="${userInfo.name}"> <input
						type="button" id="editUserInfoBtn" value="회원정보 수정"> 
				</div>
			</form>
		</div>
	</div>
</body>
</html>