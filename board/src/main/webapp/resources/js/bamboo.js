window.onload = function() {

	// join js
	
	
	
	
	
	
	
	



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
