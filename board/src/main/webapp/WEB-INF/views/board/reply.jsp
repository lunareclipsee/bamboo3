<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div class="container">
	<form id="contentForm" name="contentForm" method="post">
		<br> <br>
		<div>
			<div>
				<span><strong>Reply</strong></span> <span id="replyCnt"></span>
			</div>
			<div>
				<table class="table">
					<tr>
						<td>   																				<!-- 한줄에 안쓰면 플레이스 홀더 안됨; -->
							<c:if test="${login.id != null}">
							<textarea style="width: 1100px" rows="3" cols="30" id="reply_content" name="reply_content" placeholder="텍스트를 입력해주세요."></textarea>
							<br>
							<div>
								<a href='#' onClick="fn_comment()"
									class="btn pull-right btn-success">등록</a>
							</div>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<input type="hidden" id="board_idx" name="board_idx" value="${boardDto.idx}" />
		<input type="hidden" id="user_id" name="user_id" value="${login.name}" />
	</form>
</div>

<div class="container">
	<form id="commentListForm" name="commentListForm" method="post">
		<div id="commentList"></div>
	</form>
	
</div>

<script>

/* 초기 페이지 로딩시 댓글 불러오기 */
$(function() {

	getCommentList();

});

/* 댓글 불러오기(Ajax) */
function getCommentList() {
	var addr = 'replyList.do';
	var user_id = $('#user_id').val();
	var paramData = {
		"board_idx" : "${boardDto.idx}"
	};

	$.ajax({
		type : 'POST',
		url : addr,
		data : paramData,
		dataType : "json",
		success : function(data) {
			var html = "<div><ul>";
			var replyCnt = data.length;
 
			if (data.length > 0) {

				for (i = 0; i < data.length; i++) {
					
					if(data[i].reply_name === user_id){
					html += "<li class='reply_li'><div class='reply_box'><div class='reply_nameBox'><span><strong> "
							+ data[i].reply_name
							+ "</strong></span></div>";
					html += "<div class='pull-left replyTextBox'><span id='"+data[i].reply_idx+"'>"
							+ data[i].reply_content
							+ "</span></div>";
					html += "<div class='pull-right replyBtn'><input type='button' class='btn replyBtn' onclick='reviseComentFnc("+data[i].reply_idx+")' value='수정'>";
					html += "<input type='button' class='btn replyBtn' onclick='deleteComentFnc("+data[i].reply_idx+")' value='삭제'></div>";
					html += "<div class='pull-right'><span>"+ moment(data[i].reply_indate).format("YYYY-MM-DD HH:mm:ss")+"</span></div></div>";
					html += "</li>";
				} else {
					html += "<li class='reply_li'><div class='reply_box'><div class='reply_nameBox'><span><strong> "
							+ data[i].reply_name
							+ "</strong></span></div>";
					html += "<div id='editDiv' class='pull-left replyTextBox'><span id='asd"+data[i].reply_idx+"'>"
							+ data[i].reply_content
							+ "</span></div>";
					html += "<div class='pull-right replyBtn'></div>";
					html += "<div class='pull-right'><span>"+ moment(data[i].reply_indate).format("YYYY-MM-DD HH:mm:ss")+"</span></div></div>";
					html += "</li>";
				}
					
				}
			} else {
				html += "<li>";
				html += "<div><strong>등록된 댓글이 없습니다.</strong></div>";
				html += "</li>";
			}
				html += "</ul></div>"
			$("#replyCnt").html(replyCnt);
			$("#commentList").html(html);

		},
		error : function(request, status, error) {

		}

	});
}

/* 댓글 등록하기(Ajax) */

function fn_comment() {

	// 1) 요청 주소
	var addr = 'replyAdd.do';
	var reply_content = $('#reply_content').val();
	var board_idx = $('#board_idx').val();
	if (reply_content.length < 5) {
		alert("댓글은 5자 이상 작성해주셔야합니다.")
		$('#reply_content').focus();
	} else {
		$.ajax({
			url: addr,
			type: "POST",
			data: {
				'reply_content': reply_content,
				'board_idx' : board_idx
			},
			dataType: 'json',
			success: function(data) {
				if (data == 1){
					getCommentList();
					$('#reply_content').val('');
					$('#reply_content').focus();
			}
			}

		});

	}
}

/* 댓글 수정(Ajax) */

function reviseComentFnc(e) {
	var reviseComentView = "";
	var originComent = $('#'+e).html();
	
	reviseComentView += "<textarea id='replyEditArea' class='reviseReplyArea'></textarea>";
	reviseComentView += "<input type='button' class='btn btn-danger replyReviseBtn' onclick='reviseCancelBtn("+e+")' value='수정취소'>";
	reviseComentView += "<input type='button' class='btn btn-primary replyReviseBtn' onclick='reviseSubmitBtn("+e+")' value='수정완료'>";
	$('#'+e).replaceWith(reviseComentView);
	$('#replyEditArea').val(originComent);
	$('#replyEditArea').focus();
}

function reviseSubmitBtn(e) {
	
	// 1) 요청 주소
	var addr = 'replyRevise.do';
	var reply_content = $('#replyEditArea').val();
	var reply_idx = e;
	var board_idx = $('#board_idx').val();
	var okmag = confirm("수정하시겠습니까?");
	
	if (reply_content.length < 5) {
		alert("댓글은 5자 이상 작성해주셔야합니다.")
		$('#replyEditArea').focus();
	} else if (okmag) {
		$.ajax({
			url: addr,
			type: "POST",
			data: {
				'reply_content': reply_content,
				'reply_idx' : reply_idx,
				'board_idx' : board_idx
			},
			dataType: 'json',
			success: function(data) {
				if (data == 1){
					getCommentList();
			}
				return false;
			}

		});
	} else{
		$('#replyEditArea').focus()
	}
}

function reviseCancelBtn(e) {
	getCommentList();
	
}


function deleteComentFnc(e) {
	// 1) 요청 주소
	var addr = 'replyDelete.do';
	var reply_idx = e;
	var okmag = confirm("작성하신 댓글을 삭제하시겠습니까?");

	if (okmag) {
		$.ajax({
			url : addr,
			type : "POST",
			data : {
				'reply_idx' : reply_idx
			},
			dataType : 'json',
			success : function(data) {
				alert("작성하신 댓글의 삭제가 완료되었습니다.");
				if (data == 1) {
					getCommentList();
				}
				return false;
			}

		});

	}
}
</script>

</body>
</html>
