<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	 td {
	 	text-align: right;
	 	margin: 5px
	 }
	 .joinTd{
	 	display: inline-block;
	 	width:100px;
	 	border: 1px solid black;
	 	background-color: lightgreen;
	 	border-radius: 5px;
	 	padding:5px;
	 	margin:5px;
	 }
	 .cancleTd{
	 	background-color: hotpink;
	 }
	 #idCheck {
	 	border:1px solid black;
	 	border-radius: 5px;
	 }
	 
	
		#idCheck:hover, #ckZip:hover, .joinTd:hover, .cancleTd:hover {
		cursor:pointer;
	}
	
	

</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	
	
	<div style="margin-left:200px; margin-right:200px;">
		<h2 style="text-align:center;">회 원 가 입</h2>
		<div style="margin-left:200px; margin-right:200px;">
			<div style="border: 1px solid black; padding:20px; text-align:center;">
				
				<form id="joinForm" action="/test/mInsert.me" method="POST">
					
					<table align="center">
						<tr>
							<td width="200px">아이디</td>
							<td><input type="text" id="userId" name="userId"></td>
							<td><div id="idCheck">중복확인</div></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" id="userPwd" name="userPwd"></td>
						</tr>
						<tr>
							<td>비밀번호 확인</td>
							<td><input type="password" id="userPwd2" name="userPwd2"></td>
						</tr>
						<tr>
							<td>이름</td>
							<td><input type="text" id="userName" name="userName"></td>
						</tr>
						<tr>
							<td>이메일</td>
							<td><input type="email" id="userEmail" name="userEmail"></td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td><input type="text" id="userPhone" name="userPhone"></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input type="text" id="userAddress" name="userAddress"></td>
						</tr>
					</table><Br><br>
						
						<div style="align:center">
						
							<div class="joinTd" onclick="joinBtn();">가입하기</div>
							<div class="joinTd cancleTd" onclick="cancleBtn();">취소</div>
						</div>
						
					
				
				</form>
			</div>
		</div>
	</div>
	<script>
	/* $('#idCheck').click(function(){
		$.ajax({
			url:"/test/idDup.me",
			type:"post",
			data:{userId: $('#userId').val()},
			success:function(data){
				console.log(data);
				
				if(data=='ok'){
					alert("사용 가능한 아이디입니다.");
				}else{
					alert('이미 사용중인 아이디입니다.');
					$('#userId').select();
				}
			},error:function(){
				console.log("Error 입니다.");
			}
			
		});
	}); */
	
	function joinBtn() {
		$("#joinForm").submit();
	}
	
	$("#joinForm").submit(function(event){
		if($("#userPwd").val() == "" || $("#userId").val() == "") alert("아이디나 비밀번호는 필수 값입니다.");
		else if($('#userPwd').val() != $('#userPwd2').val()) alert("비밀번호 확인 값과 다릅니다.");
		else return;
		event.preventDefault();
	});

	</script>
	

</body>
</html>