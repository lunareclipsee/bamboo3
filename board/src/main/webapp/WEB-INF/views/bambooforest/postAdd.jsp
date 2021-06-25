<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 작성</title>
</head>
<body>
<header><jsp:include page="/WEB-INF/views/common/Header.jsp" /></header>
	<div class="container outer">
		<div class="inner">
			<form id="post_frm" name="post_frm" class="form-inline center-block" action="" method="post">
				<div class="form-group pull-left input_infotxt ">
					<label for="name" class="sr-only" >닉네임</label> 
					<input type="text" id="name" name="name" class="form-control" placeholder="닉네임" maxlength="10" value=""/>
				</div>
	
				<div class="form-group pull-left input_infotxt2">
					<label for="password" class="sr-only">비밀번호</label> 
					<input type="password" id="password" name="password" class="form-control"
						placeholder="비밀번호" maxlength="10" value=""/>
				</div>
				
				<div class="form-group input_write">
					<label for="title" class="sr-only">제목</label> 
					<input type="text" id="title" name="title" class="form-control" placeholder="제목" maxlength="50" value=""/>
				</div>
	
				<div class="form-group input_write">
					<label for="content" class="sr-only">내용</label>
					<textarea id="content" name="content" class="form-control" rows="10" cols="10" maxlength="200" placeholder="내용"></textarea>
				</div>
	
				<div class="input_write pull-right">
					남은글자: <input type="text" id="counter" name="counter" style="width: 33px" readonly value="200">
					<input type="button" id="addPostCancelBtn" class="btn btn-default tempBtnOpt" value="취소">  <!-- 여기선 굳이? -->
					<input type="button" id="addConfirmBtn" class="btn btn-primary tempBtnOpt2 pull-right" value="저장"> 
				</div>		
			</form>
		</div>
	</div>
</body>
</html>