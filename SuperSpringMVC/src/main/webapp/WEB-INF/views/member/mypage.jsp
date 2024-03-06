<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>마이페이지</title>
		<link rel="stylesheet" href="../../resources/css/member/main.css">
	</head>
	<body>
		<fieldset>
			<legend>마이페이지</legend>
			<ul>
				<li>
					<label>아이디</label>
					<span>${memberId}</span>
				</li>
				<li>
					<label>이름</label>
					<span>${memberName}</span>
				</li>
				<li>
					<label>나이</label>
					<span>${memberAge}</span>
				</li>
				<li>
					<label>성별</label>
					<span>${memberGender}</span>
				</li>
				<li>
					<label>이메일</label>
					<span>${memberEmail}</span>
				</li>
				<li>
					<label>전화번호</label>
					<span>${memberPhone}</span>
				</li>
				<li>
					<label>주소</label>
					<span>${memberAddress}</span>
				</li>
				<li>
					<label>취미</label>
					<span>${memberHobby}</span>
				</li>
			</ul>
		</fieldset>
		<a href="/">메인으로</a>
		<a href="/member/update.kh">수정하기</a>
		<a href="/member/delete.kh?memberId=${memberId}">탈퇴하기</a>
	</body>
</html>