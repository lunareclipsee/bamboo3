window.onload = function() {

	// postAdd js

	$('#addConfirmBtn').click(function() {
		addConfirm();
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
					if (data >= 10) {
						alert("동일 IP로 작성된 게시글은 10분 이내 3건까지만 작성 가능합니다.");
					} else {
						alert("게시글 작성이 완료되었습니다.");
						theForm.action = "postAddCtr.do"
					}
					theForm.submit();
				},
				error: function() {
					alert("문제야 문제");
				}
			});
		}
	}



	//board revise페이지에서 pwdCheck

	$('#addPostCancelBtn').click(function() {
		addCancel();
	});

	addCancel = function() {
		var addSelect = confirm("게시글 작성을 취소할까요?");
		if (addSelect) {
			location.href = 'postList'
		}
	}

	$('#content').keyup(function(e) {
		var content = $(this).val();
		$('#counter').val(200 - content.length);
	});



	// postRevise js


	$('#postEditBtn').click(function() {
		var theForm = document.modify_confirm;
		var edit = confirm("게시글을 수정하시겠습니까?");
		if (edit) {
			theForm.action = "postReviseCtr.do";
			theForm.submit();
		} else {
			return false;
		}
	});


	$('#postDeleteBtn').click(function() {
		var theForm = document.selectForm;
		var edit = confirm("게시글을 삭제하시겠습니까?");
		if (edit) {
			theForm.action = "postDeleteCtr.do";
			theForm.submit();
		} else {
			return false;
		}
	});
	
	$('#postAnswerBtn').click(function() {
		var theForm = document.selectForm;
		var edit = confirm("답변글을 작성하시겠습니까?");
		if (edit) {
			theForm.action = "postAdd.do";
			theForm.submit();
		} else {
			return false;
		}
	});


	// userRevise js

	$('#editUserInfoBtn').click(function() {
		var theForm = document.selectForm;
		theForm.action = "pwdCheck.do";
		theForm.submit();
	});


	$('#withdrawBtn').click(function() {
		var theForm = document.selectForm;
		var edit = confirm("탈퇴할까요?");
		if (edit) {
			theForm.action = "withdrawCtr.do";
			theForm.submit();
		} else {
			return false;
		}
	});


	//비밀번호 변경 관련 

	$('#pwdSwichBtn').click(function() {
		if (confirm("비밀번호를 변경하시겠습니까?")) {
			$('#password').attr('readonly', false);
			$('#pwdCheck').val('false');
			$('#password').focus();
			$('#pwdSwichBtn').attr('type', 'hidden');
			$('#pwdSwichCancelBtn').attr('type', 'button');
			$('#pwdOkBtn').attr('type', 'button');
		}
	});

	$('#pwdOkBtn').click(function() {
		if ($('#password').val().length < 5) {
			alert("비밀번호는 5자 이상 입력해주셔야합니다.");
		} else {
			$('#pwdCheck').val('true');
			$('#password').attr('readonly', true);
			$('#pwdSwichBtn').attr('type', 'button');
			$('#pwdSwichCancelBtn').attr('type', 'hidden');
			$('#pwdOkBtn').attr('type', 'hidden');
		}
		$('#password').focus();
	});

	$('#pwdSwichCancelBtn').click(function() {
		$('#password').val('');
		$('#pwdCheck').val('true');
		$('#password').attr('readonly', true);
		$('#pwdSwichBtn').attr('type', 'button');
		$('#pwdSwichCancelBtn').attr('type', 'hidden');
	});

	$('#reviseConfirmBtn').click(function() {
		reviseConfirm();
	});


	reviseConfirm = function() {
		var theForm = document.selectForm;

		if ($('#name').val() == "") {
			alert("닉네임을 입력해주세요.");
			$('#name').focus();
		} else if ($('#nameCheck').val() == 'false') {
			alert("닉네임 중복확인을 진행해주세요");
		} else if ($('#pwdCheck').val() == 'false') {
			alert("비밀번호 입력완료 버튼을 눌러주세요");
		} else {
			var edit = confirm("회원정보를 수정할까요?");
			if (edit) {
				theForm.action = "./reviseUserCtr.do"
				alert("회원정보 수정이 완료되었습니다.");
			} else {
				$('#name').focus();
				return false;
			}
			theForm.submit();
		}
	}
	
	$('#reviseCancelBtn').click(function() {
		reviseCancel();
	});

	reviseCancel = function() {
		var reviseSelect = confirm("회원정보 수정을 취소할까요?");
		if (reviseSelect) {
			location.href = './myPage.do';
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
		var theForm = document.formName;
		
		if (password == "") {
			alert("비밀번호를 입력해주세요.");
			$('input[type=password]').focus();
		} else {
			$.ajax({
				type: "POST",
				url: "pwdCheckCtr.do",
				data: { password: $("#password").val(), id: $("#id").val() },
				success: function(data) {
					console.log("1 = 중복o / 0 = 중복x : " + data);
					if (data) {
						theForm.action = "reviseUser.do"
					} else {
						alert("비밀번호가 틀렸습니다.");
						$('input[type=password]').val('');
						$('input[type=password]').focus();
					}
					theForm.submit();
				},
				error: function() {
					alert("문제야 문제");
				}
			});
		}
	}
}

//login js

// 엔터누르면 폼 실행 메소드  //javascript에서는 13이 enter키를 의미함 
function press(f) {
	if (f.keyCode == 13) {
alert("asd");
//		formName.submit();
	}
}

// join js

// 외전 공백방지 메소드

function noSpaceForm(obj) {
	var str_space = /\s/;               // 공백 체크
	if (str_space.exec(obj.value)) {     // 공백 체크
		alert("해당 항목에는 공백을 사용할 수 없습니다.\n\n공백 제거됩니다.");
		obj.focus();
		obj.value = obj.value.replace(' ', ''); // 공백제거
		return false;
	}
}

// 1. 아이디 입력 창에서 포커스가 이동하면 아이디 중복 체크 결과에 따라 메세지를 idDiv에 출력하는 메소드

//결함 아이디 최소글자수 
//비밀번호 공백제거 및 유효성
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
				// (4) 아이디 닉네임 성공시 회원가입 버튼 활성화
				if ($('#nameCheck').val() == 'true' && $('#idCheck').val() == 'true') {
					$("#submitBtn").attr("disabled", false);
				}

			} else {
				$('#idDiv').html('이미 사용 중인 아이디입니다.');
				$('#idDiv').css('color', 'red');
				$('#idCheck').val('false');
				if ($('#nameCheck').val() == 'false' || $('#idCheck').val() == 'false') {
					$("#submitBtn").attr("disabled", true);
				}
			}

		}

	});
}


// 2. 닉네임 입력 창에서 포커스가 이동하면 닉네임 중복 체크 결과에 따라 메세지를 idDiv에 출력하는 메소드

function reviseNameFnc() {

	if (confirm("닉네임을 변경하시겠습니까?")) {

		$('#name').attr('readonly', false);
		$('#name').focus();
		$('#nameCheck').val('false');
		$('#nameCheckBtn').attr('type', 'button');
		$('#nameReviceBtn').attr('type', 'hidden');
	}
}

function confirmNameFnc() {

	// 1) 요청 주소
	var addr = 'nameCheck';
	var name = $('#name').val();

	if (name.length < 2) {

		alert("닉네임은 2글자 이상입니다.")
		$('#name').focus();

	} else {

		$.ajax({
			url: addr,
			data: {
				'name': name
			},
			dataType: 'json',
			success: function(data) {
				if (data == true) {
					var select = confirm("사용가능한 닉네임입니다. 사용하시겠습니까? 변경할 수 없습니다.");
					if (select) {
						$('#nameCheck').val('true');
						$('#name').attr('readonly', true);
						$("#nameCheckBtn").attr("disabled", true);
						if ($('#nameCheck').val() == 'true'
							&& $('#idCheck').val() == 'true') {
							$("#submitBtn").attr("disabled", false);
						}
					} else {
						$('#name').focus();
						return false;
					}
				} else {
					alert("이미 사용중인 닉네임입니다.")
					$('#nameCheck').val('false');
					$('#name').val('');
					$('#name').focus();
				}

			}

		});

	}
}


// 3. form에서 submit 버튼 클릭 시 실행할 check() 함수 생성
// <form id="joinform" enctype="multipart/form-data" method="post" onsubmit="return check()">

function check() {
	// 아이디가 중복된 상태에서 회원 가입 버튼을 누르면 다음페이지로 넘어갈 수 없도록! 
	// id가 idCheck인 객체의 value값이 false이면 아이디 중복상태

	if ($('#idCheck').val() == 'false') {
		alert("아이디를 확인해주세요");
		$('#idDiv').html('이미 사용 중인 아이디입니다.');
		$('#idDiv').css('color', 'red');
		return false; // 다음 페이지로 넘어갈 수 없도록 설정 
	} else if ($('#idCheck').val() == 'false') {
		alert("아이디를 확인해주세요");
		$('#nameDiv').html('이미 사용 중인 닉네임입니다.');
		$('#nameDiv').css('color', 'red');
		return false; // 다음 페이지로 넘어갈 수 없도록 설정 
	}
}

//board list js
//게시글 선택
function fn_view(idx) {

	var form = document.getElementById("frm");
	var url = "postSelect.do";
	url = url + "?idx=" + idx;

	form.action = url;
	form.submit();
}

//글쓰기 etc..
var goUrl = function(url) {
	location.href = url;
};

