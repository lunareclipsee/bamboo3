window.onload = function() {

	// join js

	// 2. 아이디 입력 창에서 포커스가 이동하면 아이디 중복 체크 결과에 따라 메세지를 idDiv에 출력하는 메소드
	function confirmId() {
		// 1) 요청 주소
		var addr = 'idCheck';

		var id = $('#id').val();
		$.ajax({
			url: addr,
			data: {
				'id': id
			},
			dataType: 'json',
			success: function(data) {
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
					$('#idDiv').html('이미 사용 중인 아이디입니다.');
					$('#idDiv').css('color', 'red');
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

	$('#confirmName').click(function() {
		confirmName();
	});

	confirmName = function() {
		alert("asdasd");
		// 1) 요청 주소
		var addr = 'nameCheck';
		var name = $('#name').val();
		$.ajax({
			url: addr,
			data: {
				'name': name
			},
			dataType: 'json',
			success: function(data) {
				if (data == true) {
					// (1)nameDiv에 사용가능한 닉네임 입니다 출력
					$('#nameDiv').html('사용가능한 닉네임 입니다.');
					// (2)성공 문자 색은 파란색
					$('#nameDiv').css('color', 'blue');
					// (3)닉네임 성공 여부를 저장하는 hidden 태그의 value값을 true 로 저장
					// <input type="hidden" id="nameCheck" value="false" /> 
					$('#nameCheck').val('true');

				} else {
					$('#nameDiv').html('이미 사용 중인 닉네임 입니다.');
					$('#nameDiv').css('color', 'red');
					$('#nameCheck').val('false');
				}

			}

		});


	}


	// postAdd js

	$('#addConfirmBtn').click(function() {
		addConfirm();
	});


	//revise페이지에서 pwdCheck
	$('#editUserInfoBtn').click(function() {
		var theForm = document.selectForm;
		theForm.action = "pwdCheck";
		theForm.submit();
	});

	$('#addPostCancelBtn').click(function() {
		addCancel();
	});

	$('#content').keyup(function(e) {
		var content = $(this).val();
		$('#counter').val(200 - content.length);
	});

	addConfirm = function() {

		var password = $('input[type=password]').val();
		var theForm = document.post_frm;

		var name = $("#name").val();
		var password = $("#password").val();
		var title = $("#title").val();
		var content = $("#content").val();

		var form = {
			name, password, title, content
		}

		if (name == "") {
			alert("닉네임을 입력해주세요.");
			$("#name").focus();
		} else if (password == "") {
			alert("비밀번호를 입력해주세요.");
			$("#password").focus();
		} else if (title == "") {
			alert("제목을 입력해주세요.");
			$("#title").focus();
		} else if (content == "") {
			alert("내용을 입력해주세요.");
			$("#content").focus();
		} else {
			$.ajax({
				type: "POST",
				url: "postCnt",
				data: form,
				success: function(data) {
					if (data >= 3) {
						alert("동일 IP로 작성된 게시글은 10분 이내 3건까지만 작성 가능합니다.");
					} else {
						alert("게시글 작성이 완료되었습니다.");
						theForm.action = "postAddCtr"
					}
					theForm.submit();
				},
				error: function() {
					alert("문제야 문제");
				}
			});
		}
	}

	addCancel = function() {
		var addSelect = confirm("게시글 작성을 취소할까요?");
		if (addSelect) {
			location.href = 'postList'
		}
	}

	// userRevise js

	$('#withdrawBtn').click(function() {
		var theForm = document.selectForm;
		var edit = confirm("탈퇴할까요?");
		if (edit) {
			theForm.action = "withdrawCtr";
			theForm.submit();
		} else {
			return false;
		}
	});


	$('#pwdSwich').click(function() {
		$('#password').attr('readonly', false);
	});

	$('#password').focus();
	$('#title').focus();
	$('#name').focus();

	$('#reviseConfirmBtn').click(function() {
		reviseConfirm();
	});

	$('#reviseCancelBtn').click(function() {
		reviseCancel();
	});

	reviseConfirm = function() {
		var theForm = document.selectForm;

		if ($('#name').val() == "") {
			alert("닉네임을 입력해주세요.");
			$('#name').focus();
		} else {
			var edit = confirm("회원정보를 수정할까요?");
			if (edit) {
				theForm.action = "./reviseUserCtr"
				alert("회원정보 수정이 완료되었습니다.");
			} else {
				$('#name').focus();
				return false;
			}
			theForm.submit();
		}
	}

	reviseCancel = function() {
		var reviseSelect = confirm("회원정보 수정을 취소할까요?");
		if (reviseSelect) {
			location.href = './myPage';
		} else {
			return false;
		}
	}

	// pwdCheck js

	$('#pwdConfirmBtn').click(function() {
		confirmPassword();
	});

	confirmPassword = function() {

		var password = $('input[type=password]').val();
		var theForm = document.selectForm;
		var url = $('#url').val();
		//		var id = $('#id').val();

		if (password == "") {
			alert("비밀번호를 입력해주세요.");
			$('input[type=password]').focus();
		} else {
			$.ajax({
				type: "POST",
				url: "pwdCheckCtr",
				data: { password: $("#password").val(), id: $("#id").val() },
				success: function(data) {
					console.log("1 = 중복o / 0 = 중복x : " + data);
					if (data) {
						theForm.action = "reviseUser"
					} else {
						// 0 : 비밀번호 통과못함
						alert("비밀번호가 틀렸습니다.");
						//						$('input[type=password]').focus()
					}
					theForm.submit();
				},
				error: function() {
					alert("문제야 문제");
				}
			});
		}
	}


	// PostList js
	postAddFnc = function() {
		location.href = 'postAdd'
	}

	function fn_view(idx) {

		var form = document.getElementById("frm");
		var url = "postSelect";
		url = url + "?idx=" + idx;

		form.action = url;
		form.submit();
	}
}
