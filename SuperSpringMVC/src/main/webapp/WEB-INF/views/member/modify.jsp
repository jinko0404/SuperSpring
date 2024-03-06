<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>정보수정</title>
		<link rel="stylesheet" href="../../resources/css/member/main.css">
	</head>
	<body>
	<form action="/member/update.kh?memberId=${memberId}" method="post">
            <fieldset>
                <legend>
                    정보수정
                </legend>
                <ul>
                	<li>
                		<label>아이디</label>
                		<span>${memberId}</span>
                	</li>
                    <li>
                        <label for="">비밀번호</label>
                        <input type="password" name="memberPw" id="memberPw" value="${memberPw}">
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
                        <label for="">이메일</label>
                        <input type="text" name="memberEmail" id="memberEmail" value="${memberEmail}">
                    </li>
                    <li>
                        <label for="">연락처</label>
                        <input type="text" name="memberPhone" id="memberPhone" value="${memberPhone}">
                    </li>
                    <li>
                        <label for="">주소</label>
                        <input type="text" name="memberAddress" id="memberAddress" value="${memberAddress}">
                    	
                    </li>
                    <li>
                        <label for="">취미</label>
                        <input type="text" name="memberHobby" id="memberHobby" value="${memberHobby}">
                    </li>
                </ul>
            </fieldset>
            <div>
            <input type="submit" value="정보수정">
            <a href="/member/mypage.kh">취소하기</a>           
            </div>
        </form>
	</body>
</html>