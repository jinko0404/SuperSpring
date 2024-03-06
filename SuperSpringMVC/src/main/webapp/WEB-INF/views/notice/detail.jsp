<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세 조회</title>
		<link rel="stylesheet" href="../../resources/css/member/main.css">
		<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	</head>
	<body>
		<h1>공지사항 상세 조회</h1>
		<ul>
			<li>
				<label>제목 : </label>
				<span>${notice.noticeSubject}</span>
			</li>
			<li>
				<label>작성자 : </label>
				<span>${notice.noticeWriter}</span>
			</li>
			<li>
				<label>내용 : </label>
				<span>${notice.noticeContent}</span>
			</li>
			<li>
				<label>첨부파일 : </label>
				<span>
					<a href="../../../../resources/nuploadFiles/${notice.noticeFileRename}">
						${notice.noticeFileName}
					</a>
				</span>
			</li>
		</ul>
		<br>
		<br>
		<div>
			<button type="button" onclick="showModifyPage();">수정하기</button>
			<button type="button" onclick="showNoticeList();">목록으로</button>
			<button type="button" onclick="deleteNotice(${notice.noticeNo});">삭제하기</button>	
		</div>
		<hr>
		<table width="500" border="1">
	 		<tr>
	 			<td>
	 				<input type="text" name="replyContent" size="50" id="replyContent">
	 			</td>
	 			<td>
					<button id="rSubmit">등록하기</button>
	 			</td>
	 		</tr>
	 	</table>
		<table id="replyTable">
			<tbody></tbody>
		</table>
		<script>
		$("#rSubmit").on("click", function() {
 			var refBoardNo = $("#refBoardNo").val();
 			var replyContent = $("#replyContent").val();
			$.ajax({
				url : "/nreply/add.kh",
				data : {"refBoardNo": refBoardNo,
						"replyContent": replyContent},
				type : "POST",
				success : function(result) {
					alert("서비스 결과 : " + result);
					location.href = "/notice/detail.kh?boardNo=" + refBoardNo;
					//getReplyList();
					//$("#replyContent").val("");
				},
				error : function() {
					alert("Ajax 통신 실패 ! 관리자에게 문의 바랍니다.");
				}
			});
		});
		
		
			function showModifyPage() {
				var noticeNo = "${notice.noticeNo}";
				location.href = "/notice/modify.kh?noticeNo=" + noticeNo;
			}
			function showNoticeList() {
				location.href="/notice/list.kh";
			}
			function deleteNotice(noticeNo) {
				if(confirm("삭제하시겠습니까?")){
					location.href="/notice/delete.kh?noticeNo=" + noticeNo;
				}
			}
		</script>
	</body>
</html>