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
			<form id="post_frm" class="form-inline center-block" name="post_frm" action="" method="post">
				<div class="form-group pull-left input_infotxt ">
					<label class="sr-only" for="name">닉네임</label> 
					<input id="name" class="form-control" name="name" placeholder="닉네임" type="text" maxlength="10" value=""/>
				</div>
	
				<div class="form-group pull-left input_infotxt">
					<label for="password" class="sr-only">비밀번호</label> 
					<input id="password" class="form-control" name="password"
						placeholder="비밀번호" type="password" maxlength="10" value=""/>
				</div>
				
				<div class="form-group input_write">
					<label for="inip" class="sr-only">제목</label> 
					<input id="title" class="form-control" name="title" placeholder="제목" type="text" value=""/>
				</div>
	
				<div class="form-group input_write">
					<label for="content" class="sr-only">내용</label>
					<textarea id="content" class="form-control" rows="10" cols="10" name="content"
						placeholder="내용"></textarea>
				</div>
	
				<div class="input_write pull-right">
					<input id="addPostCancelBtn" type="button" class="btn btn-default" value="취소">  <!-- 여기선 굳이? -->
					<input id="addConfirmBtn" type="button" class="btn btn-primary tempBtnOpt pull-right" value="저장"> 
				</div>		
			</form>
		</div>
	</div>
</body>
</html>