<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세 조회</title>
		<link rel="stylesheet" href="../../resources/css/member/main.css">
	</head>
	<body>
		<h1>공지사항 상세 조회</h1>
		<form action="/notice/modify.kh" method="post" enctype="multipart/form-data">
		<!-- 1. 파일이 있으면 삭제할 수 있도록 한다. -->
		<!-- 2. 첨부 파일을 선택하지 않고 수정할 때 null로 입력되는 것을 방지할 수 있다. -->
			<input type="hidden" name="noticeNo"			value="${notice.noticeNo}">
			<input type="hidden" name="noticeFileName"		value="${notice.noticeFileName}">
			<input type="hidden" name="noticeFileRename"	value="${notice.noticeFileRename}">
			<input type="hidden" name="noticeFilePath"		value="${notice.noticeFilePath}">
			<input type="hidden" name="noticeFileLength"	value="${notice.noticeFileLength}">
			<ul>
				<li>
					<label>제목 : </label>
					<input type="text" value="${notice.noticeSubject}" name="noticeSubject">
				</li>
				<li>
					<label>작성자 : </label>
					<span>${notice.noticeWriter}</span>
				</li>
				<li>
					<label>내용 : </label>
					<textarea rows="4" cols="50" name="noticeContent">
						${notice.noticeContent}
					</textarea>
				</li>
				<li>
					<label>첨부파일 : </label>
					<span>
						<a href="../../../../resources/nuploadFiles/${notice.noticeFileRename}">
							${notice.noticeFileName}
						</a>
						<input type="file" name="reloadFile">
					</span>
				</li>
			</ul>
			<br>
			<br>
			<div>
				<input type="submit" value="수정하기">
				<button type="button" onclick="goBack();">수정 취소</button>		
			</div>
		</form>
		<script>
			function showModifyPage() {
				var noticeNo = "${notice.noticeNo}";
				location.href = "/notice/modify.kh?noticeNo=" + noticeNo;
			}
			function goBack() {
				history.back();
			}
		</script>
	</body>
</html>