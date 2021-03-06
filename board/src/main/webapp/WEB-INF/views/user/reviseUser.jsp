<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>회원정보 수정</title>

</head>

<body>
	<header><jsp:include page="/WEB-INF/views/common/Header.jsp" /></header>
	<div class="container bs-docs-container contentBox" style="margin-top: 150px;">
		<div class="col-md-9">
			<form name="selectForm" method='post'>
				<div id="tableBox">
					<table class="table table-hover tableFont">

						<tr>
							<th class='tCenter'><span>아이디</span></th>
							<td><input type="text" class="form-control" name="id" value="${userInfo.id}" readonly="readonly"></td>
						</tr>
						<tr>
 							<th class='tCenter'><span>닉네임</span></th>																				<!--한글때문에 오류나는 경우 있어 길이조정 -->
							<td>
								<input type="text" id="name" class="form-control" name="name" value="${userInfo.name}" onkeyup="noSpaceForm(this);" maxlength="10" readonly="readonly">
							</td>
							<td>
								<input type="button" id="nameReviceBtn" class="btn btn-default" value="닉네임 변경" onclick="reviseNameFnc()">
								<input type="hidden" id="nameCheckBtn" class="btn btn-default" value="중복확인" onclick="confirmNameFnc()">
							</td>
							
						</tr>

						<tr>
							<th class='tCenter'><span>비밀번호</span></th>
							<td>
								<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력해주세요"
									maxlength="12" readonly="readonly">
							</td>
							<td>
								<input type="button" id="pwdSwichBtn" class="btn btn-default" value="비밀번호 변경">
								<input type="hidden" id="pwdSwichCancelBtn" class="btn btn-default" value="변경취소">
								<input type="hidden" id="pwdOkBtn" class="btn btn-default" value="입력완료">
							</td>
						</tr>
					</table>
				</div>
				<input type="hidden" id="nameCheck" value="true" />
				<input type="hidden" id="pwdCheck" value="true" />
				<input type='button' id="reviseConfirmBtn" class="btn btn-primary btn-lg pull-right" value='변경하기'> 
			 	<input type='button' id="reviseCancelBtn" class="btn btn-default btn-lg pull-right" value='돌아가기'>
				<input type='button' id="withdrawBtn" class="btn btn-danger btn-lg" value='회원탈퇴'>
			</form>
		</div>
	</div>
</body>
</html>