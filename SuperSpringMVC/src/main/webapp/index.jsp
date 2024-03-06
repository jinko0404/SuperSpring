<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
    <c:if test="${memberId eq null }">
        <H1>환영합니다~</H1>
        <H2>로그인 후 서비스 이용이 가능합니다.</H2>
        <form action="/member/login.kh" method="post">
            ID : <input type="text" name="memberId" placeholder="아이디를 입력해주세요."><br>
            PW : <input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요"><br>
            <input type="submit" value="로그인">
            <a href="/member/register.kh">회원가입</a>
        </form>
        </c:if>
        <c:if test="${memberId ne null }">
        ${memberName}님 환영합니다!
        <a href="/member/logout.kh">로그아웃</a><br>
        <a href="/member/mypage.kh">마이페이지</a>
        <a href="/board/list.kh">게시판이동</a>
        <a href="/notice/list.kh">공지사항이동</a>
        </c:if>
    </body>
</html>