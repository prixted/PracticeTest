<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kwon.member.model.vo.*"%>
    
<%
	Member m = (Member)session.getAttribute("Member");
	/* session.setMaxInactiveInterval(1000); */
	/* System.out.println("[Header] 세션 회원 : " + m); */
%>
<!DOCTYPE html>
<html>
<head>
<title>KWON PRACTICE</title>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
	body {
		background: #e7fcff/* url("/test/resources/images/background.jpg") no-repeat */;
		background-size: cover;
		
	}
	 
	.wrap { 
		background: lightgray; 
		width: 100%;
		height: 50px;
	}
	.menu {
		background: lightgray; 
		color:black;
		text-align:center;
		vertical-align:middle;
		width:150px;
		height:50px;
		display:table-cell;
	}
	.nav {
		width: 600px;
		margin-left: auto;
		margin-right: auto;
	}
	.menu:hover {
		background: lightgoldenrodyellow;
		color: orangered;
		font-weight: bold;
		cursor: pointer;
	}
	
	.btns {
		align: center;
	}
	
	#loginBtn, #memberJoinBtn, #logoutBtn, #changeInfo {
		display: inline-block;
		vertical-align: middel;
		text-align: center;
		background: orangered;
		color: white;
		height: 25px;
		width: 100px;
		border-radius: 5px;
	}
	
	#memberJoinBtn {
		background:yellowgreen;
	}
	
	#loginBtn:hover, #changeInfo:hover, #logoutBtn:hover, #memberJoinBtn:hover{
		cursor:pointer;
	}
	.loginArea > form, #userInfo {
		float:right;
		margin-right:40px;
	}
	#logout, #changeInfo {
		background:orangered;
		color:white;
		text-decoration:none;
		border-radius:5px;
	}
	#changeInfo {
		background:yellowgreen;
	}
	
</style>
</head>
<body>
	<h1 align="center">테스트</h1>
	
	<div class="loginArea">
	<%if ( m == null ) { %>
		<form id="loginForm" action="/test/login.me" method="post">
			<table>
				<tr>
					<td>
						<label class="text">ID : </label>
					</td>
					<td>
						<input type="text" name="userId">
					</td>
				</tr>
					<tr>
					<td>
						<label class="text">PWD : </label>
					</td>
					<td>
						<input type="password" name="userPwd">
					</td>
				</tr>
			</table><br>
			<div class="btns" align="center">
				<div id="memberJoinBtn" onclick="memberJoin()">회원가입</div>
				<div id="loginBtn" onclick='login()'>로그인</div> 
			</div>
			
		</form>
		<% } else { %>
		<div id="userInfo">
			<label><%= m.getUserName() %>님의 방문을 환영합니다.</label>
			<div class="btns" align="right">
				<div id="changeInfo" onclick="changeInfo()">정보수정</div>
				<div id="logoutBtn" onclick='logout()'>로그아웃</div> 
			</div>
			
		</div>
	<% } %>
	</div>
	<script>
		function login(){
			$('#loginForm').submit();
		}
		
		function logout(){
			location.href="/test/logout.me";
		}
		
		function memberJoin(){
			location.href="/test/views/member/memberJoinForm.jsp";
		}
		
		function changeInfo(){
			location.href="/test/views/member/memberUpdateForm.jsp";
		}
	</script>
	
	<br clear="both">
	<br>
	
	<div class="wrap">
		<div class="nav">
			<div class="menu" onclick="goHome()">HOME</div>
			<div class="menu" onclick="goNotice()">공지사항</div>
			<div class="menu" onclick="goBoard()">게시판</div>
			<div class="menu" onclick="goThumbnail()">사진 게시판</div>
		</div>
	</div>
	<script>
		function goHome(){
			location.href="/test/index.jsp";
		}
		function goNotice(){
			location.href="/test/selectList.no";
		}
		function goBoard(){
			location.href="/test/selectList.bo";
		}
		function goThumbnail(){
			location.href="/test/selectList.tn";
		}
	</script>
	<br><br>
</body>
</html>