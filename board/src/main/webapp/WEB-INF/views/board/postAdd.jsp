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
				<input type="hidden" id="name" name="name" value="${userName}"/>
				<input type="hidden" id="id" name="id" value="${userId}"/>
				<input type="hidden" id="idx" name="idx" value="${boardDto.idx}"> 
				<input type="hidden" id="groupno" name="groupno" value="${groupno}"> 
				<input type="hidden" id="groupord" name="groupord" value="${groupord}"> 
				<input type="hidden" id="depth" name="depth" value="${depth}"> 
				<input type="hidden" id="parentno" name="parentno" value="${parentno}"> 
				<input type="hidden" id="parentno" name="answerno" value="${answerno}"> 
				
				<div class="form-group input_write">
					<label for="title" class="sr-only">제목</label> 
					<input type="text" id="title" name="title" class="form-control" placeholder="제목" maxlength="50" value="" autofocus="autofocus"/>
				</div>
	
				<div class="form-group input_write">
					<label for="content" class="sr-only">내용</label>
					<textarea id="content" name="content" class="form-control" rows="20" cols="10" maxlength="200" placeholder="내용"></textarea>
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