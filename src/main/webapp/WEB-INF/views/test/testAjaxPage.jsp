<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title> 
<link rel="stylesheet" type="text/css" href="/second/resources/css/testPage.css">
<script src="webjars/jquery/3.3.1/dist/jquery.min.js"></script>
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

		$("#test2").on("click", function(){
			//test2.do 로 부터 json 객체를 리턴받아, div에 출력
			$.ajax({
				url: "test2.do",
				type: "post",
				dataType: "json",
				success: function(jsonData){
					console.log("jsonData : " + jsonData);
					$("#d2").html("번호 : " + jsonData.no
							+ "<br>제목 : " + jsonData.title
							+ "<br>작성자 : " + jsonData.writer
							+ "<br>내용 : " + jsonData.content.replace(/\+/g, " "));
				},
				error: function(request, status, errorData){
					alert("error code : " + request.status + "\n"
						+ "message : " + request.responseText + "\n"
						+ "error : " + errorData);
				}
			});
		});


		$("#test3").on("click", function(){
			//컨트롤러로 부터 list를 json 배열로 바꾸어 리턴한 경우
			$.ajax({
				url: "test3.do",
				type: "post",
				dataType: "json",
				success: function(obj){
					console.log(obj);  //object 라고 출력됨
					//리턴된 객체 하나를 문자열로 변환 처리
					var objStr = JSON.stringify(obj);
					//문자열을 json 객체로 바꿈
					var jsonObj = JSON.parse(objStr);
					
					//문자열 변수 준비
					var outValues = $("#d3").html("");
					
					for(var i in jsonObj.list){
						outValues += jsonObj.list[i].userid + ", "
							+ jsonObj.list[i].userpwd + ", "
							+ jsonObj.list[i].username + ", "
							+ jsonObj.list[i].age + ", "
							+ jsonObj.list[i].email + ", "
							+ jsonObj.list[i].phone + "<br>";
					}
					
					$("#d3").html(outValues);
				},
				error: function(request, status, errorData){
					alert("error code : " + request.status + "\n"
							+ "message : " + request.responseText + "\n"
							+ "error : " + errorData);
					}
			});
		});

		$("#test4").on("click", function(){
			//컨트롤러에서 Map 객체를 jsonView를 이용해 json 객체로 리턴된 결과 출력
			$.ajax({
				url: "test4.do",
				type: "post",
				dataType: "json",
				success: function(jsonObj){
					$("#d4").html("받은 Map 안의 객체 정보 확인<br>"
							+ "이름 : " + jsonObj.samp.name
							+ ", 나이 : " + jsonObj.samp.age);
				},
				error: function(request, status, errorData){
					alert("error code : " + request.status + "\n"
							+ "message : " + request.responseText + "\n"
							+ "error : " + errorData);
					}
			});
		});

		$("#test5").on("click", function(){
			//자바스크립트 | 제이쿼리에서 json 객체 만들어서
			//서버 컨트롤러로 보내기
			var job = new Object();
			job.name = "강감찬";
			job.age = 33;
			
			//var job = {name : "강감찬", age : 33};
			console.log("job : " + job);
			
			$.ajax({
				url: "test5.do",
				data: JSON.stringify(job),
				type: "post",
				contentType: "application/json; charset=utf-8",
				success: function(result){
					alert("전송 성공!" + result);
					$("#d5").html("전송한 json 객체의 값 : "
							+ job.name + ", " + job.age);
				},
				error: function(request, status, errorData){
					alert("error code : " + request.status + "\n"
							+ "message : " + request.responseText + "\n"
							+ "error : " + errorData);
					}
			});
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