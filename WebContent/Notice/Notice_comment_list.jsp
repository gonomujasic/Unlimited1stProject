<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8"); %>

<!-- bootstrap css 설정 -->
<link href="./boot/css/bootstrap.min.css" rel="stylesheet"/>
<!-- jquery.js 설정 -->
<script type="text/javascript" src="./boot/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap.js 설정 -->
<script type="text/javascript" src="./boot/js/bootstrap.min.js"></script>
	
<script>
$(".commentdelete").click(function(event){
	var d="no="+$("#no").val() + "&comment_no="+$(this).prev().prev().prev().val();
// 	alert(d);
		$.ajax({
		url:"NoticeCommentDeleteService.notice",
		type:"get",
		data:d,
		success:function(result){
			$("#comments").html(result);
		}
		})
})

$(".commentmodify").click(function(event){
	var d="no="+$("#no").val() + "&comment_no="+$(this).prev().prev().prev().prev().val();
			//공지번호,댓글번호 갖고 폼으로감
// 	alert(d);
		$.ajax({
			url:"NoticeCommentModifyForm.notice",
			type:"get",
			data:d,
			success:function(result){
				$("#comments").html(result);
			}
			})
})
	
	
</script>
<div class="container">
			<fieldset >
			<legend>댓글</legend>
			<c:forEach var="comment" items="${commentList}">
			<form>
			<fieldset>
			<input type="hidden" id="no" name="no" value="${noticeDTO.no}">
			<input type="hidden" id="comment_no" name="comment_no" value="${comment.comment_No}">
			<p>${comment.writer} ${comment.write_date}</p>
			
			<!-- 아이디가 같거나, 관리자일경우 삭제 버튼 있게끔 -->
			<p>${comment.content}</p>
<!-- 			<div style="float:right"> -->
			<c:if test="${id =='admin' || id==comment.writer}">
			<button type="button" class="commentdelete btn btn-default">댓글삭제</button>
			<button type="button" class="commentmodify btn btn-default">댓글수정</button>
<!-- 			</div> -->
			</c:if>
			</fieldset>
			</form>
			<hr>
			</c:forEach>
			<fieldset>
			<legend>댓글작성</legend>
			<form action="./NoticeCommentAddService.notice">
			<input type="hidden" id="writer" name="writer" value="${id}"> 
<%-- 			<input type="hidden" name="writer" value="${session.id}">  --%>
			<input type="hidden" name="no" id="no" value="${noticeDTO.no}">
			<div class="input-group"><textarea rows="8" cols="100" name="content"></textarea></div>
			<div class="input-group-addon"><input type="submit" value="작성"></div>
			</form>
			</fieldset>
			
			</fieldset>
</div>