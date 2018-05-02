<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style type="text/css">
nav {
	float: left;
}

nav ul li {
	float: left;
	display: block;
	width: 120px;
	height: 35px;
	background: orange;
	color: navy;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li><a>home</a></li>
				<li><a>공지사항</a></li>
				<li><a>게시글</a></li>
				<li><a>QnA</a></li>
				<li><a>자료실</a></li>
			</ul>
		</nav>
		<table id="loginFrm">
			<c:choose>
				<c:when test="${member != null }">
					<tr>
						<th>${member.userid}님 하이!!</th>
					</tr>
					<tr>
						<th colspan="2"><a>로그아웃</a></th>
					</tr>
				</c:when>
				<c:otherwise>
					<form action="login.do" method="post">
						<tr>
							<th><input type="text" name="userid"></th>
							<th rowspan="2"><input type="submit" value="로그인"></th>
						</tr>
						<tr>
							<th><input type="password" name="userpwd"></th>
						</tr>
						<tr>
							<th colspan="2"><a>아이디/암호분실시</a> &nbsp; <a
								href="enrollForm.do">회원가입</a></th>
						</tr>
					</form>
				</c:otherwise>
			</c:choose>
		</table>
	</header>
</body>
</html>


