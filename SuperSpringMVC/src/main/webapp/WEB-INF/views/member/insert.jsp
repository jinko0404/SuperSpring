<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>회원 가입 홈</title>
        <link rel="stylesheet" href="../../resources/css/member/main.css">
    </head>
    <body>
        <form action="/member/register.kh" method="post">
            <fieldset>
                <legend>
                    회원가입
                </legend>
                <ul>
                    <li>
                        <label for="">아이디</label>
                        <input type="text" name="memberId" id="memberId">
                    </li>
                    <li>
                        <label for="">비밀번호</label>
                        <input type="password" name="memberPw" id="memberPw">
                    </li>
                    <li>
                        <label for="">이름</label>
                        <input type="text" name="memberName" id="memberName">
                    </li>
                    <li>
                        <label for="">나이</label>
                        <input type="number" name="memberAge" id="memberAge">
                    </li>
                    <li>
                        <label for="">성별</label>
                        남<input type="radio" name="memberGender" id="memberGender" value="남">
                        여<input type="radio" name="memberGender" id="memberGender" value="여">
                    </li>
                    <li>
                        <label for="">이메일</label>
                        <input type="text" name="memberEmail" id="memberEmail">
                    </li>
                    <li>
                        <label for="">연락처</label>
                        <input type="text" name="memberPhone" id="memberPhone">
                    </li>
                    <li>
                        <label for="">주소</label>
                        <input type="text" name="memberAddress" id="memberAddress" size="36">
                    	<input type="button" onclick="execDaumPostcode();" value="우편번호 찾기">
                    </li>
                    <li>
                        <label for="">취미</label>
                        <input type="text" name="memberHobby" id="memberHobby">
                    </li>
                </ul>
            </fieldset>
            <input type="submit" value="회원가입">
			<input type="reset" value="초기화">
        </form>
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script>
        	function execDaumPostcode() {
        		 new daum.Postcode({
        		        oncomplete: function(data) {
        		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
        		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
        		            console.log(data.roadAddress + ", " + data.zonecode + ", " + data.buildingName);
        		        	document.querySelector("#memberAddress").value = data.roadAddress + ", " + data.zonecode + ", " + data.buildingName;
        		        }
        		    }).open();
			}
        </script>
    </body>
</html>