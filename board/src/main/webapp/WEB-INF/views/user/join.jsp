<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script>
			// 2. 아이디 입력 창에서 포커스가 이동하면 아이디 중복 체크 결과에 따라 메세지를 idDiv에 출력하는 메소드
			function confirmId() {
				// 1) 요청 주소
				var addr = 'idCheck';

				// 2) 요청에 넘겨줄 파라미터 값 가져오기
				// -> form에서 id가 id인 개체의 value값(즉, 사용자가 입력한 id값) 가져와서 저장
				var id = $('#id').val();
				// 3) JQuery의 AJAX로 요청 주소의 결과 가져오기
				// [AJAX]
				// url - 요청 주소
				// date - {}를 이용하여 파라미터 전달 (없는 경우 생략)
				// dataType - return해서 받아오는 데이터 타입 지정 (JSON,XML,CSV 등등)
				// success - 성공했을 때 호출될 함수 설정
				// error - 실패했을 때 호출될 함수 설정

				// ** /myboard/user/idCheck의 요청에 파라미터로 id를 넘겨주고 결과는 JSON으로 가져옴
				$.ajax({
					url : addr,
					data : {
						'id' : id
					},
					dataType : 'json',
					success : function(data) {
						// 일단 테스트 
						// alert(data); // -> [Object objec] 이렇게 뜨면 ok
						// alert(data.result); // -> false 또는 true로 뜨면 ok
						if (data == true) {
							// data == true 아이디 중복 X
							// (1)idDiv에 사용가능한 아이디 입니다 출력
							$('#idDiv').html('사용가능한 아이디입니다.');
							// (2)성공 문자 색은 파란색
							$('#idDiv').css('color', 'blue');
							// (3) 아이디 성공 여부를 저장하는 hidden 태그의 value값을 true 로 저장
							// <input type="hidden" id="idCheck" value="false" /> 
							$('#idCheck').val('true');

						} else {
							// 아이디가 중복 된 경우
							// (1)idDiv에 사용할 수 없는 아이디입니다 출력
							$('#idDiv').html('이미 사용 중인 아이디입니다.');
							// (2)문자 색은 빨강
							$('#idDiv').css('color', 'red');
							// (3) 아이디 성공 여부를 저장하는 hidden 태그의 value값을 false 로 저장
							// <input type="hidden" id="idCheck" value="false" /> 
							$('#idCheck').val('false');
						}

					}

				});
			}

	// 3. form에서 submit 버튼 클릭 시 실행할 check() 함수 생성
	// <form id="joinform" enctype="multipart/form-data" method="post" onsubmit="return check()">
	function check() {
		var joinform = $('#joinform');
		// 아이디가 중복된 상태에서 회원 가입 버튼을 누르면 다음페이지로 넘어갈 수 없도록! 
		// id가 idCheck인 객체의 value값이 false이면 아이디 중복상태
		if ($('#idCheck').val() == 'false') {
			alert("아이디를 확인해주세요");
			$('#idDiv').html('이미 사용 중인 아이디입니다.');
			$('#idDiv').css('color', 'red');
			return false; // 다음 페이지로 넘어갈 수 없도록 설정 

		}

	}
</script>
</head>
<body>

	<!-- form에서 지정한 함수들 설정할 스크립트 코드 ! 중요 !  -->
	<!-- script내에서 $가 들어가는 문장은 모두 Jquery문 -->


	<section class="content">
		<div class="box">
			<div class="box-body">
				<div class="col-md-2"></div>
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
							<p align="center">
							<table class="table table-striped centered">

								<!-- 헤드 -->
								<tr>
									<td colspan="2" align="center"><h2>회원 가입</h2></td>
								</tr>

								<!-- 아이디 입력 -->
								<tr>
									<!-- &nbsp; : html기초, 태그나 문자 사이에 공백 주는 기능!!-->
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp; <label
										for="id">아이디</label> <!-- onblur : 아이디 입력란에서 포커스가 다른 곳으로 이동할 때 호출할 함수 설정 -->
										<input type="text" class="form-control" name="id" id="id"
										size="20" maxlength="30" onblur="confirmId()"
										required="required" placeholder="아이디를 입력하세요" /> <!-- 아이디 중복검사 후 중복 여부를 출력할 영역 설정 -->
										<div id="idDiv"></div>
									</td>

								</tr>
								<!-- 비밀번호 입력 -->
								<tr>
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp; <label
										for="pw">비밀번호</label> <input type="password"
										class="form-control" name="password" id="password" size="20"
										maxlength="30" required="required" placeholder="비밀번호를 입력하세요" />
									</td>
								</tr>
								<!-- 이름 입력 -->
								<tr>
									<td bgcolor="#f5f5f5">&nbsp;&nbsp;&nbsp;&nbsp; <label
										for="name">닉네임</label> <!-- pattern : 알파벳 대소문자, 한글로 입력해야하고 2글자 이상 입력해야함-->
										<input type="text" class="form-control" name="name" id="name"
										size="20" maxlength="30" pattern="([a-z,A-Z,가-힣]){2,}"
										required="required" placeholder="닉네임을 입력하세요" />
									</td>

								</tr>

								<tr>
									<td colspan="2" align="center"><br /> <input
										type="submit" value="회원가입" class="btn btn-success" /> <input
										type="button" value="메인으로" class="btn btn-primary"
										onclick="javascript:window.location='/'" /></td>
								</tr>
							</table>
						</form>

					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</section>


</body>


</html>