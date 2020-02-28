<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kwon.notice.model.vo.*, java.util.*"%>
<% 
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list"); 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.outer{
		width:900px;
		height:600px;
		background:white;
		color:black;
		margin-left:auto;
		margin-right:auto;
		margin-top:50px;
	}
	table {
		padding : 20px;
		border:1px solid gray;
		text-align:center;
	}
	.tableArea {
		width:750px;
		height:350px;
		margin-left:auto;
		margin-right:auto;
	}
	.searchArea {
		width:650px;
		margin-left:auto;
		margin-right:auto;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="outer">
		<br>
		<h2 align="center">공 지 사 항</h2>
		<div class="tableArea">
			<table align="center" id="listArea">
			<tr>
				<th width="100px">글번호</th>
				<th width="300px">글제목</th>
				<th width="100px">작성자</th>
				<th width="150px">작성일</th>
				<th width="100px">조회수</th>
			</tr>
			<% for(Notice n : list){ %>
			<tr>
				<input type="hidden" value="<%= n.getNno() %>"/>
				<td><%= n.getNno() %></td>
				<td><%= n.getnTitle() %></td>
				<td><%= n.getnWriter() %></td>
				<td><%= n.getnDate() %></td>
				<td><%= n.getnCount() %></td>
			</tr>
			<% } %>
		</table>
		</div>
		
		<%-- 페이지 처리 --%>
		<div class="pagingArea" align="center">
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.no?currentPage=1'"><<</button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.no?currentPage=<%=currentPage - 1 %>'"><</button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/selectList.no?currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.no?currentPage=<%=currentPage + 1 %>'">></button>
			<%  } %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.no?currentPage=<%= maxPage %>'">>></button>
			
		</div>
		
		<div class="searchArea" align="center">
			<select id="searchCondition" name="searchCondition">
				<option>---</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search">
			<button type="submit">검색하기</button>
			<% if(m != null && m.getUserRoles() == 777){ %>
				<button onclick="location.href='views/board/boardInsertForm.jsp'">작성하기</button>
			<% } %>
			
		</div>
	</div>
	
	<script>
		$(function(){
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"darkgray", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"white"});
			}).click(function(){
				var nno = $(this).parent().find("input").val();
				location.href="<%=request.getContextPath()%>/selectOne.no?nno=" + nno;
			});
		});
	</script>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>