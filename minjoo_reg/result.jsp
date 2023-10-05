<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="resources/register.css" type="text/css">
</head>
<body>
    <header class = "header">
    	<h1>TUKOREA 2023</h1>
		<h2>Web Service Programming Community</h2>
    </header>
    <br>
    <p id="sect"> 커뮤니티 가입을 축하합니다. <br>

    <%-- 서블릿에서 전달한 학생 정보 표시 --%>
    <table>
        <tr>
            <th>계정</th>
            <td><%=request.getAttribute("id") %></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><%=request.getAttribute("username") %></td>
        </tr>
        <tr>
            <th>학번</th>
            <td><%=request.getAttribute("snum") %></td>
        </tr>
        <tr>
            <th>전공</th>
            <td><%=request.getAttribute("depart") %></td>
        </tr>
        <tr>
            <th>폰번호</th>
            <td><%=request.getAttribute("mobile") %></td>
        </tr>
        <tr>
            <th>이메일</th>
            <td><%=request.getAttribute("email") %></td>
        </tr>
    </table>
    
    <%-- 메인 페이지로 이동하는 하이퍼링크 --%>
    <p><br><br><br>
    <a href="welcome.html">메인 페이지 이동</a></p>
</body>
</html>
