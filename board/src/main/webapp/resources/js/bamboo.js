window.onload = function () {

	// postAdd js

	$('#addConfirmBtn').click(function () {
		addConfirm();
	});

	$('#addPostCancelBtn').click(function () {
		addCancel();
	});
	
	 $('#content').keyup(function (e){
      var content = $(this).val();       
      $('#counter').val(200-content.length);
      });

	addConfirm = function () {

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
				url: "postCnt.do",
				data: form,
				success: function (data) {
					if (data >= 3) {
						alert("동일 IP로 작성된 게시글은 10분 이내 3건까지만 작성 가능합니다.");
					} else {
						alert("게시글 작성이 완료되었습니다.");
						theForm.action = "postAddCtr.do"
					}
					theForm.submit();
				},
				error: function (data) {
					alert("문제야 문제");
				}
			});
		}
	}

	addCancel = function () {
		var addSelect = confirm("게시글 작성을 취소할까요?");
		if (addSelect) {
			location.href = 'postList.do'
		}
	}




	// postRevise js

	var idx = $('#idx').val();

	$('#password').focus();
	$('#title').focus();
	$('#name').focus();

	$('#reviseConfirmBtn').click(function () {
		reviseConfirm();
	});

	$('#reviseCancelBtn').click(function () {
		reviseCancel();
	});

	reviseConfirm = function () {
		var theForm = document.modify_confirm;

		if ($('#title').val() == "") {
			alert("제목을 입력해주세요.");
			$('#title').focus();
		} else if ($('#content').val() == "") {
			alert("내용을 입력해주세요.");
			$('#content').focus();
		} else {
			var edit = confirm("게시글을 수정할까요?");
			if (edit) {
				alert("게시글 수정이 완료되었습니다.");
				theForm.action = "../postReviseCtr.do"
			} else {
				return false;
			}
			theForm.submit();
		}
	}

	reviseCancel = function () {
		var reviseSelect = confirm("게시글 수정을 취소할까요?");
		if (reviseSelect) {
			location.href = '../postSelect.do?idx=' + idx;
		} else {
			return false;
		}
	}


	// pwdCheck js

	$('#confirmBtn').click(function () {
		confirmPassword();
	});

	confirmPassword = function () {

		var password = $('input[type=password]').val();
		var theForm = document.password_confirm;
		var url = $('#url').val();
		var idx = $('#idx').val();

		if (password == "") {
			alert("비밀번호를 입력해주세요.");
			$('input[type=password]').focus();
		} else {
			$.ajax({
				type: "POST",
				url: "pwdCheckCtr.do",
				data: { password: $("#password").val(), idx: $("#idx").val() },
				success: function (data) {
					console.log("1 = 중복o / 0 = 중복x : " + data);
					if (data == 1) {
						// 1 : 비밀번호 통과
						if (url.indexOf("delete") != -1) {
							// url값에 delete가 포함안되어있으면 -1 반환 
							var del = confirm("게시글을 삭제할까요?");

							if (del) {
								theForm.action = "postDeleteCtr.do";
								alert("게시글 삭제가 완료되었습니다.");
							} else {
								alert("게시글로 돌아갑니다.");
								theForm.action = "../postSelect.do?idx=" + idx
							}
						} else {
							// url값에 딜리트가 포함안되서 수정으로 진행
							theForm.action = "postRevise.do"
						}
					} else {
						// 0 : 비밀번호 통과못함
						alert("비밀번호가 틀렸습니다.");
					}
					theForm.submit();
				},
				error: function (data) {
					alert("문제야 문제");
				}
			});
		}
	}
}


// PostList js
postAddFnc = function () {
	location.href = 'postAdd.do'
}

function fn_view(idx) {

	var form = document.getElementById("frm");
	var url = "/bambooforest/postSelect.do";
	url = url + "?idx=" + idx;

	form.action = url;
	form.submit();
}
