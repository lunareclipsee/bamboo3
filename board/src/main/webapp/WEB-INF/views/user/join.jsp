<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="${path}/resources/js/bamboo.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>

</head>
<body>

	<!-- form에서 지정한 함수들 설정할 스크립트 코드 ! 중요 !  -->
	<!-- script내에서 $가 들어가는 문장은 모두 Jquery문 -->


	<section class="content">
		<div class="box">
			<div class="box-body">
				<div class="col-md-8">
					<div class="box-header with-border">
						<!-- 회원 가입 양식 form -->
						<!-- form에 action을 생략했기 때문에 user/join 요청 생성 -->
						<!-- 파일 업로드 form의 경우, enctype을 설정 & 전송 방식은 post  -->
						<!-- onsubmit : 전송 버튼을 누를 때 작동하는 메소드 설정 -->
						<!-- -> 아이디 중복 검사를 하지 않으면 전송하지 못하도록 설정하기 -->
						<form id="joinform" action="joinCtr" method="post" onsubmit="return check()">
							<!-- hidden : 아이디 중복검사 성공 여부를 저장하기 위한 변수 -->
							<!-- 기본 값은 false -->
							<input type="hidden" id="idCheck" value="false" /> 
							<input type="hidden" id="nameCheck" value="false" />
							<p align="center">
							<table class="table table-striped centered">

								<!-- 헤드 -->
								<tr>
									<td colspan="2" align="center"><h2>회원 가입</h2></td>
								</tr>

								<!-- 아이디 입력 -->
								<tr>
									<!-- &nbsp; : html기초, 태그나 문자 사이에 공백 주는 기능!!-->
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp; 
										<label for="id">아이디</label> <!-- onblur : 아이디 입력란에서 포커스가 다른 곳으로 이동할 때 호출할 함수 설정 -->
										<input type="text" class="form-control" name="id" id="id" size="20" maxlength="15" onblur="confirmId()"
											onkeyup="noSpaceForm(this);" required="required" placeholder="아이디를 입력하세요" autofocus="autofocus"/>
										<div id="idDiv"></div>
									</td>

								</tr>
								<!-- 비밀번호 입력 -->
								<tr>
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp; 
									<label for="password">비밀번호</label> 
									<input type="password" class="form-control" name="password" id="password" size="20"
										maxlength="12" required="required" placeholder="비밀번호를 입력하세요"/>
									</td>
								</tr>
								<!-- 이름 입력 -->
								<tr>
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp; 
										<label for="name">닉네임</label> <!-- pattern : 알파벳 대소문자, 한글로 입력해야하고 2글자 이상 입력해야함-->
										<input type="text" class="form-control" name="name" id="name" size="20" maxlength="10"
											required="required" placeholder="닉네임을 입력하세요" onkeyup="noSpaceForm(this);"/>
										<div id="nameDiv"></div>
										<input type="button" class="btn btn-default" id="nameCheckBtn" onclick="confirmNameFnc()" value="중복확인">
									</td>
								</tr>

								<tr>
									<td colspan="2" align="center"><br /> 
										<input type="button" value="메인으로" class="btn btn-primary"	onclick="javascript:window.location='/'" />
										<input type="submit" value="회원가입" id="submitBtn" disabled class="btn btn-success"/> 
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">







</script>
</html>