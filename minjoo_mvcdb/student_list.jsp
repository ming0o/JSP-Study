<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/register.css" type="text/css">
<title>Student List</title>
</head>
<body>
	<header class="header">
		<h1>Member List</h1>
	</header>
	<br>
	<br>
	<a href="http://localhost:8080/minjoo_mvcdb/welcome.html"
		target="_self">메인 페이지 이동</a>
	<br>
	<br>
	<table class = "listtable">
		<tr>
			<td>계정</td>
			<td>이름</td>
			<td>학번</td>
			<td>전공</td>
			<td>핸드폰</td>
			<td>이메일</td>
			<td>관리</td>
		</tr>
		<%
			List<StudentVO> studentList = (List<StudentVO>) request.getAttribute("studentList");
			for (StudentVO vo : studentList) {
		%>
		<tr>
			<td><%=vo.getId()%></td>
			<td><%=vo.getUsername()%></td>
			<td><%=vo.getSnum()%></td>
			<td><%=vo.getDepart()%></td>
			<td><%=vo.getMobile()%></td>
			<td><%=vo.getEmail()%></td>
			<td><a href="http://localhost:8080/minjoo_mvcdb/StudentServlet?cmd=update&id=<%=vo.getId() %>" target="_self"> 수정</a>
				<a href="http://localhost:8080/minjoo_mvcdb/StudentServlet?cmd=delete&id=<%=vo.getId() %>" target="_self"> 삭제</a>
			</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>
