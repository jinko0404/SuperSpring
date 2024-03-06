<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시물 작성</title>
	</head>
	<body>
		<h1>게시물 등록</h1>
		<form action="/board/register.kh" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>게시물 등록</legend>
				제목 <input type="text" name="boardTitle"><br>
				내용 <textarea rows="4" cols="50" name="boardContent"></textarea><br>
				첨부 파일 <input type="file" name="uploadFile"><br>
				<input type="submit" value="작성">
				<a href="#">취소</a>	
			</fieldset>	
		</form>
	</body>
</html>