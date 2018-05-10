<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testFileUpload</title>
</head>
<body>
<h1>파일 업로드 테스트 페이지</h1>
<form action="tinsert.do" method="post" enctype="multipart/form-data">
이름 : <input name="name" type="text" required> <br>
나이 : <input type="number" name="age" required> <br>
첨부파일 : <input type="file" name="file"> <br>
<input type="submit" value="전송하기">
</form>
</body>
</html>