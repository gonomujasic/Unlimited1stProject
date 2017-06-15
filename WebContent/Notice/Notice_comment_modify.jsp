<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8"); %>
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>

<script>
$(".modifycancel").click(function(event){
	var d="no="+$(".no").val();
		alert(d);
	$.ajax({
		url:"NoticeCommentListService.notice",
		type:"get",
		data:d,
		success:function(result){
			$("#comments").html(result);
		}
		})
})
$(".modifybtn").click(function(event){
	var d="comment_content="+$(this).prev().children("textarea").val()
		+ "&comment_no="+$(this).prev().prev().prev().prev().prev().val()
		+ "&no="+$(this).prev().prev().prev().prev().prev().prev().val();
		alert(d);
	$.ajax({
		url:"NoticeCommentModifyService.notice",
		type:"get",
		data:d,
		success:function(result){
			$("#comments").html(result);
		}
		})
})
</script>

			<fieldset>
			<legend>댓글</legend>
			<c:forEach var="comment" items="${commentList}">
			<fieldset>
			<c:choose>
			<c:when test="${comment_no == comment.comment_No}">
			<form action="NoticeCommentModifyService.notice">
			<input type="hidden" class="no" name="no" value="${no}">
			<input type="hidden" id="comment_no" name="comment_no" value="${comment.comment_No}">
			<p>${comment.writer} ${comment.write_date}</p>
			<button id="commentdelete" class="btn btn-default">댓글삭제</button>
			<button id="commentmodify" class="btn btn-default">댓글수정</button>
			<!-- 아이디가 같거나, 관리자일경우 삭제 버튼 있게끔 -->
			<p><textarea rows="8" cols="100" class = "comment_content" name="comment_content">${comment.content}</textarea></p>
			<button type="button" class="modifybtn btn btn-default">수정</button>
			<button type="button" class="modifycancel btn btn-default">수정취소</button>
			<hr>
			</form>
			</c:when>
			<c:otherwise>
			<input type="hidden" id="comment_no" name="comment_no" value="${comment.comment_No}">
			<p>${comment.writer} ${comment.write_date}</p>
			<c:if test="${id =='admin' || id==comment.writer}">
			<button type="button" class="btn btn-default" id="commentdelete">댓글삭제</button>
			<button type="button" class="btn btn-default" id="commentmodify">댓글수정</button>
			</c:if>
			<!-- 아이디가 같거나, 관리자일경우 삭제 버튼 있게끔 -->
			<p>${comment.content}</p>
			<hr>
			</c:otherwise>
			</c:choose>
			</fieldset>
			</c:forEach>
			<fieldset>
			<legend>댓글작성</legend>
			<form action="./NoticeCommentAddService.notice">
			<input type="hidden" id="writer" name="writer"> 
<%-- 			<input type="hidden" name="writer" value="${session.id}">  --%>
			<input type="hidden" name="no" id="no" value="${noticeDTO.no}">
			<textarea rows="8" cols="100" name="content"></textarea>
			<input type="submit" value="작성">
			
			</form>
			</fieldset>
			
			</fieldset>