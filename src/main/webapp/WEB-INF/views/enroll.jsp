<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="/first/js/jquery-3.3.1.min.js"></script>
<style type="text/css">
section form table th {
	background: orange;
	color: navy;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function checkId() {

		$.ajax({
			url : "/first/idchk",
			type : "post",
			data : {
				userid : $('#userid').val()
			},
			success : function(data) {
				console.log("success : " + data);

				if (data == "ok") {
					alert("사용 가능한 아이디입니다.");
					$('#username').focus();
				} else {
					alert("이미 존재하는 아이디입니다.\n" + "다시 입력하십시오.");
					$('#userid').select();
				}
			},
			error : function(jqXHR, textstatus, errorthrown) {
				console.log("error : " + jqXHR + ", " + textstatus + ", "
						+ errorthrown);
			}
		});

		return false;
	}

	$(function() {

		$('input[type=password]').blur(function() {
			console.log("포커스 벗어남.");
			var pwd1 = $("#upwd").val();
			var pwd2 = $("#upwd2").val();

			if (pwd1 == pwd2) {
				$("#confirm").css("display", "true");
				$("#confirm").val("일치");
			} else {
				$("#confirm").css("display", "block");
				$("#confirm").val("불일치");
			}

		});

	});
</script>
</head>
<body>
	<section>
		<h2 align="center">회원가입페이지</h2>
		<br>
		
		<form action="enroll.do" method="post">
			
			<table width="650" align="center">
				<tr height="40">
					<th width="150">아이디</th>
					<td><input type="text" id="userid" name="userid" required>
						&nbsp;
						<button onclick="return checkId();">중복확인</button></td>
				</tr>
				<tr height="40">
					<th width="150">이름</th>
					<td><input type="text" id="username" name="username" required></td>
				</tr>
				<tr height="40">
					<th width="150">비밀번호</th>
					<td><input type="password" name="userpwd" id="upwd" required></td>
				</tr>
				<tr height="40">
					<th width="150">비밀번호확인</th>
					<td><input type="password" id="upwd2" required></td>

				</tr>
				<tr height="40">
					<th width="150">암호확인메시지</th>
					<td><input type="text" id="confirm" style="display: none"
						readonly></td>
				</tr>
				<tr height="40">
					<th width="150">성별</th>
					<td><input type="radio" name="gender" value="M">남자
						&nbsp; <input type="radio" name="gender" value="F">여자</td>
				</tr>
				<tr height="40">
					<th width="150">나이</th>
					<td><input type="number" name="age" min="20" max="100"></td>
				</tr>
				<tr height="40">
					<th width="150">이메일</th>
					<td><input type="email" name="email"></td>
				</tr>
				<tr height="40">
					<th width="150">전화번호</th>
					<td><input type="tel" name="phone"></td>
				</tr>
				<tr height="40">
					<th width="150">취미</th>
					<td>

						<table>
							<tr>
								<td width="150"><input type="checkbox" name="hobby"
									value="game">게임</td>
								<td width="150"><input type="checkbox" name="hobby"
									value="reding">독서</td>
								<td width="150"><input type="checkbox" name="hobby"
									value="music">음악</td>
							</tr>
							<tr>
								<td width="150"><input type="checkbox" name="hobby"
									value="climb">등산</td>
								<td width="150"><input type="checkbox" name="hobby"
									value="sport">운동</td>
								<td width="150"><input type="checkbox" name="hobby"
									value="movie">영화</td>
							</tr>
							<tr>
								<td width="150"><input type="checkbox" name="hobby"
									value="travel">여행</td>
								<td width="150"><input type="checkbox" name="hobby"
									value="cook">요리</td>
								<td width="150"><input type="checkbox" name="hobby"
									value="etc">기타</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr height="40">
					<th width="150">특이사항</th>
					<td><textarea name="etc" rows="3" cols="30"></textarea></td>
				</tr>

				<tr height="40">
					<th width="150" colspan="2"><input type="submit"
						value="가입하기"> &nbsp; <input type="reset"
						value="작성취소"> &nbsp; <a href="enrollForm.do">시작페이지로</a>
					</th>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>