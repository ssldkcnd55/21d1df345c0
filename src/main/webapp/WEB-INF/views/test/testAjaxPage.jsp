<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/second/resources/css/testPage.css">
<script type="text/javascript" src="/second/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#test1").on("click", function() {
			$.ajax({
				url: "test1.do",
				data: {
					name: "신사임당",
					age: 47
				},
				type: "post",
				success: function(result){
					if(result == "ok"){
						alert("전송 성공!");
						$("#d1").html("<h5>"+result+"</h5>");
					}
					else{
						alert("전송 실패!");
					}
				},
				error: function(request, status, errorData){
					alert("error code : " + request.status + "\n"
						+ "message : " + request.responseText + "\n"
						+ "error : " + errorData);
				}
			}); 
		});

		$("#test2").on("click", function() {
			
		});

		$("#test3").on("click", function() {
		});

		$("#test4").on("click", function() {
		});

		$("#test5").on("click", function() {
		});

		$("#test6").on("click", function() {
		});
	});
</script>
</head>
<body>
	<h1>스프링 Ajax 사용 테스트 페이지</h1>
	<ol>
		<li>서버쪽 컨트롤러로 값 보내기
			<button id="test1">테스트1</button>
		</li>
		<p>
		<div id="d1"></div>
		</p>
		<li>컨트롤러에서 리턴하는 JSON 객체 받아서 출력하기
			<button id="test2">테스트2</button>
		</li>
		<p>
		<div id="d2"></div>
		</p>
		<li>컨트롤러에서 리턴하는 JSON 배열 받아서 출력하기
			<button id="test3">테스트3</button>
		</li>
		<p>
		<div id="d3"></div>
		</p>
		<li>컨트롤러에서 맵(Map) 객체를 리턴받아서 출력하기
			<button id="test4">테스트4</button>
		</li>
		<p>
		<div id="d4"></div>
		</p>
		<li>뷰에서 JSON 객체를 컨트롤러로 보내기
			<button id="test5">테스트5</button>
		</li>
		<p>
		<div id="d5"></div>
		</p>
		<li>뷰에서 JSON 배열을 컨트롤러로 보내기
			<button id="test6">테스트6</button>
		</li>
		<p>
		<div id="d6"></div>
		</p>
	</ol>


</body>
</html>