<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시물 목록</title>
		<link rel="stylesheet" href="../../resources/css/member/main.css">
	</head>
	<body>
		<h1>게시물 목록</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성날짜</th>
					<th>첨부파일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bList}" var="board" varStatus="i">
					<tr>
						<td>${i.count}</td>
						<td><a href="/board/detail.kh?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
						<td>${board.boardWriter}</td>
						<td>${board.bCreateDate}</td>
						<td>0</td>
						<td>${board.boardCount}</td>
					</tr>				
				</c:forEach>
			</tbody>
			<tfoot>
				<tr align="center">
					<td colspan="6">
						<c:if test="${pInfo.startNavi ne '1'}">
							<a href="/board/list.kh?page=${pInfo.startNavi - 1}">[이전]</a>
						</c:if>
						<c:forEach begin="${pInfo.startNavi}" end="${pInfo.endNavi}" var="p">
               				<a href="/board/list.kh?page=${p}">${p}</a>
            			</c:forEach>
            			<c:if test="${pInfo.endNavi ne pInfo.naviTotalCount}">
							<a href="/board/list.kh?page=${pInfo.endNavi + 1}">[다음]</a>
						</c:if>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>