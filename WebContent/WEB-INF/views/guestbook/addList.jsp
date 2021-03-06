<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>

<%
System.out.println("addList.jsp");
//guestbook List 가져오기
List<GuestbookVo> guestbookList = (List<GuestbookVo>)request.getAttribute("gList");

System.out.println(guestbookList);

//http://localhost:8088/MySite/guestbook?action=addList
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/MySite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/MySite/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header" class="clearfix">
			<h1>
				<a href="">MySite</a>
			</h1>

			<!-- 
			<ul>
				<li>황일영 님 안녕하세요^^</li>
				<li><a href="" class="btn_s">로그아웃</a></li>
				<li><a href="" class="btn_s">회원정보수정</a></li>
			</ul>
			-->	
			<ul>
				<li><a href="" class="btn_s">로그인</a></li>
				<li><a href="" class="btn_s">회원가입</a></li>
			</ul>
			
		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="/MySite/guest" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
									
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
	
						<input type="hidden" name="action" value="write">
						
					</form>	

					
							<% 
							for (int i = 0; i < guestbookList.size() ; i++) {
								// for(GuestbookVo vo:gList) { 향상된 포문
								// GuestbookVo guestbookVo = guestbookList.get(i); 
							%>
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td><%=guestbookList.get(i).getNo()%></td>
							<td><%=guestbookList.get(i).getName()%></td>
							<td><%=guestbookList.get(i).getRegDate()%></td>
							<td><a href="/MySite/guest?action=deleteForm&no=<%=guestbookList.get(i).getNo()%>">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left"><%=guestbookList.get(i).getContent()%></td>
						</tr>
					</table>
					<!-- //guestRead -->
					
						<%
						}
						%>
	
	
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<div id="footer">
			Copyright ⓒ 2020 황일영. All right reserved
		</div>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>