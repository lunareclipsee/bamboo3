<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style type="text/css">
.page_wrap {
	text-align:center;
	font-size:0;
 }
.page_nation {
	display:inline-block;
}
.page_nation .none {
	display:none;
}
.page_nation a {
	display:block;
	margin:0 3px;
	float:left;
	border:1px solid #e6e6e6;
	width:28px;
	height:28px;
	line-height:28px;
	text-align:center;
	background-color:#fff;
	font-size:13px;
	color:#999999;
	text-decoration:none;
}
.page_nation .arrow {
	border:1px solid #ccc;
}
.page_nation .pprev {
	background:#f8f8f8 url('/resources/imgs/page_pprev.png') no-repeat center center;
	margin-left:0;
}
.page_nation .prev {
	background:#f8f8f8 url('/resources/imgs/page_prev.png') no-repeat center center;
	margin-right:7px;
}
.page_nation .next {
	background:#f8f8f8 url('/resources/imgs/page_next.png') no-repeat center center;
	margin-left:7px;
}
.page_nation .nnext {
	background:#f8f8f8 url('/resources/imgs/page_nnext.png') no-repeat center center;
	margin-right:0;
}
.page_nation a.active {
	background-color:#42454c;
	color:#fff;
	border:1px solid #42454c;
}
</style>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">

	function goPageFnc(pageNumber){

		var curPage = $('#curPage');
		
		curPage.val(pageNumber);
		
		var pagingForm = $('#pagingForm');
		pagingForm.submit();
		
	};
	
	window.onload = function(){
		var curPage = document.getElementById('curPage');
		var pageButtonId = 'pageButton' + curPage.value;
		
		document.getElementById(pageButtonId)
			.setAttribute('class', 'active');
	};
</script>

<div class="page_wrap">
	<div class="page_nation">
		<c:if test="${pagingMap.postPaging.blockBegin ne 1 || pagingMap.postPaging.blockEnd ne 0}">
			<a class="arrow pprev" href="#" onclick="goPageFnc(${pagingMap.postPaging.beginPage});"></a>
			<a class="arrow prev" href="#" onclick="goPageFnc(${pagingMap.postPaging.prevPage});"></a>
				<c:forEach var="num" begin="${pagingMap.postPaging.blockBegin}" 
					end="${pagingMap.postPaging.blockEnd}">	
					<a id="pageButton${num}" href="#" onclick="goPageFnc(${num});">
						<c:out value="${num}"/>
					</a>
				</c:forEach>
			<a class="arrow next" href="#" onclick="goPageFnc(${pagingMap.postPaging.nextPage});"></a>
			<a class="arrow nnext" href="#" onclick="goPageFnc(${pagingMap.postPaging.endPage});"></a>
		</c:if>	
	</div>
</div>

