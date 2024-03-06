<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 조회</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h1>게시글 상세</h1>
	<ul>
		<li>
			<label>제목</label>
			<span>${board.boardTitle}</span>
		</li>
		<li>
			<label>작성자</label>
			<span>${board.boardWriter}</span>
		</li>
		<li>
			<label>내용</label>
			<span>${board.boardContent}</span>
		</li>
		<li>
			<label>첨부파일</label>
			<a href="#">${board.boardFileName}</a>
		</li>
	</ul>
		 	<!-- 댓글 등록 -->
	 	<hr>
<!-- 	 	<form action="/reply/add.kh" method="post"> -->
	 		<input type="hidden" name="refBoardNo" value="${board.boardNo }" id="refBoardNo">
	 		<table width="500" border="1">
	 			<tr>
	 				<td>
	 					<input type="text" name="replyContent" size="50" id="replyContent">
	 				</td>
	 				<td>
<!-- 	 					<input type="submit" value="완료"> -->
							<button id="rSubmit">등록하기</button>
	 				</td>
	 			</tr>
	 		</table>
<!-- 	 	</form> -->
	 	<!-- 댓글 목록 -->
		 <table width="550" border="1" id="replyTable">
		 	<tbody></tbody>
<!--	 	<c:forEach items="${rList }" var="reply">
		 		<tr>
		 			<td>${reply.replyWriter }</td>
		 			<td>${reply.replyContent }</td>
		 			<td>${reply.rCreateDate }</td>
		 			<td>
		 				<a href="#">수정</a>
		 				<a href="#">삭제</a>
		 			</td>
		 		</tr>
	 		</c:forEach>-->
	 	</table>  
	 	<script>
	 		getReplyList();
	 		function getReplyList(){
	 			var refBoardNo = $("#refBoardNo").val();
	 			$.ajax({
	 				url : "/reply/list.kh",
	 				data : {"refBoardNo" : refBoardNo},
	 				type : "GET",
	 				success : function(result){
	 					var tableBody = $("#replyTable tbody");
	 					tableBody.empty();
	 					var tr;
	 					var replyNo;
	 					var replyWriter;
	 					var replyContent;
	 					var rCreateDate;
	 					var btnArea;
	 					if(result.length > 0){
	 						for(var i in result){
	 							var replyWriterVal	= result[i].replyWriter;
	 							var replyContentVal	= result[i].replyContent;
	 							var rCreateDateVal	= result[i].rCreateDate;
	 							var replyNoVal		= result[i].replyNo;
	 							tr = $("<tr>");
	 							replyNo = $("<td>").text(replyNoVal);
	 							replyWriter = $("<td>").text(replyWriterVal);
	 							replyContent = $("<td>").text(replyContentVal);
	 							rCreateDate = $("<td>").text(rCreateDateVal);
	 							btnArea = $("<td width='80'>").append("<a href='javascript:void(0)' onclick='modifyViewReply(this, "+replyNoVal+" ,\""+replyContentVal+"\");'>수정</a> ")
	 															.append("<a href='javascript:void(0)' onclick='removeReply("+replyNoVal+");'>삭제</a>")
	 							tr.append(replyNo);
	 							tr.append(replyWriter);
	 							tr.append(replyContent);
	 							tr.append(rCreateDate);
	 							tr.append(btnArea);
	 							tableBody.append(tr);
	 						}
	 					}
	 				},
	 				error : function() {
						alert("Ajax 통신 실패 ! 관리자에게 문의 바랍니다.");
					}
	 			});
	 		}
	 		
	 		function modifyViewReply(obj, replyNo, replyContent){
	 			var tr = $("<tr>");
	 			tr.append("<td colspan='3'><input type='text' size='50' value='"+replyContent+"'></td>");
	 			$("#replyTable tbody").append(tr);
	 			tr.append("<td colspan='2'><button type=button onclick='modifyReply("+replyNo+", this);'>수정완료</button></td>");
	 			$(obj).parent().parent().after(tr);
	 		}
	 		
	 		function removeReply(replyNo){
	 			$.ajax({
	 				url : "/reply/delete.kh",
	 				data : {"replyNo" : replyNo},
	 				type : "POST",
	 				success : function(result) {
						alert("서비스 결과 : " + result);
						getReplyList();
					},
					error : function() {
						alert("Ajax 통신 실패 ! 관리자에게 문의 바랍니다.");
					}
	 			});
	 		}
	 		
	 		function modifyReply(replyNo, obj){
	 			var inputTag = $(obj).parent().prev().children();
	 			var replyContent = inputTag.val();
	 			$.ajax({
	 				url : "/reply/update.kh",
	 				data : {
	 					"replyNo" : replyNo,
	 					"replyContent" : replyContent
	 				},
	 				type : "POST",
	 				success : function(data) {
	 					alert("서비스 결과 : " + data);
	 					getReplyList();
					},
					error : function() {
						alert("Ajax 통신 실패 ! 관리자에게 문의 바랍니다.");
					}
	 			});
	 		}
	 	
	 		$("#rSubmit").on("click", function() {
	 			var refBoardNo = $("#refBoardNo").val();
	 			var replyContent = $("#replyContent").val();
				$.ajax({
					url : "/reply/add.kh",
					data : {"refBoardNo": refBoardNo,
							"replyContent": replyContent},
					type : "POST",
					success : function(result) {
						alert("서비스 결과 : " + result);
//						location.href = "/board/detail.kh?boardNo=" + refBoardNo;
						getReplyList();
						$("#replyContent").val("");
					},
					error : function() {
						alert("Ajax 통신 실패 ! 관리자에게 문의 바랍니다.");
					}
				});
			});
	 	</script>
</body>
</html>