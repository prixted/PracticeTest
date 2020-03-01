<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kwon.notice.model.vo.*"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.outer{
		width:800px;
		height:500px;
		background:white;
		color:black;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	.tableArea > table {
		width: 650px;
		padding: 20px;
		color: black;
		border:1px solid black;
	}

	.tableArea {
		background:white;
		width:650px;
		height:350px;
		margin:auto;
	}
	span {
		font-size:21pt;
		background: black;
		color: white;
	}
	.td1{
		border-top: 1px solid gray;
		margin-top: 10px;
	}
</style>
<title>공지 사항 내용</title>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">공지 사항 내용</h2>
		<div class="tableArea">
				<table>
					<tr>
						<td>제목 : </td>
						<td colspan="3">${ notice.nTitle } <%-- <%= n.getnTitle() %> --%></td>
					</tr>
					<tr>
						<td>작성자 :</td>
						<td>
							${ notice.nUserName }
							<%-- <%= n.getnUserName() %> --%>
						</td>
						<td>작성일 : </td>
						<td>
							${ notice.nDate }
							<%-- <%= n.getnDate() %> --%>
						</td>
					</tr>
					<tr>
						<td style="padding:5px;">내용 </td>
					</tr>
					<tr>
						<td class="td1" colspan="4"><br>
							${ notice.nContent }
							<%-- <%= n.getnContent() %> --%>
							<%-- <span><%= n.getnContent().charAt(0) %></span><%= n.getnContent().substring(1) %> --%>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					
					<button onclick="location.href='selectList.no'">메뉴로 돌아가기</button>

					<% if( m != null && m.getUserRoles() == 777 ) { %>
						<button onclick="location.href='nUpdateView.no?nno=${ notice.getNno() }'">수정하기</button>
					<% } %>
				</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>







