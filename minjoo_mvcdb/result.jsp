<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/register.css" type="text/css">
</head>
<body>
	<header class="header">
		<h1>TUKOREA 2023</h1>
		<h2>Web Service Programming Community</h2>
	</header>
	<br>
	<p id="sect"><%=request.getAttribute("greetings")%></p>
	<div>
		<%
		StudentVO student = (StudentVO) request.getAttribute("student");
		%>
		<table>
			<tr>
				<th>계정</th>
				<td><%=student.getId()%></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=student.getUsername()%></td>
			</tr>
			<tr>
				<th>학번</th>
				<td><%=student.getSnum()%></td>
			</tr>
			<tr>
				<th>전공</th>
				<td><%=student.getDepart()%></td>
			</tr>
			<tr>
				<th>폰번호</th>
				<td><%=student.getMobile()%></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=student.getEmail()%></td>
			</tr>
		</table>
		<p>
			<br>
			<br>
			<br> <a href="http://localhost:8080/minjoo_mvcdb/StudentServlet?cmd=list" target="_self">전체 회원 목록 보기</a>
		</p>
	</div>
</body>
</html>
