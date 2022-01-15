<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.UserVo"%>

<%
UserVo authUser = (UserVo) session.getAttribute("authUser");
%>

<div id="header" class="clearfix">
	<h1>
		<a href="/MySite/main">MySite</a>
	</h1>

	<%
	if (authUser == null) {
	%>
	<!-- login Failed -->
	<ul>
		<li><a href="/MySite/user?action=loginForm" class="btn_s">로그인</a></li>
		<li><a href="" class="btn_s">회원가입</a></li>
	</ul>

	<%
	} else {
	%>
	<!-- login success -->
	<ul>
		<li><%=authUser.getName()%>님 안녕하세요^^</li>
		<li><a href="/MySite/user?action=logout" class="btn_s">로그아웃</a></li>
		<li><a href="" class="btn_s">회원정보수정</a></li>
	</ul>
	<%
	}
	%>

</div>
<!-- header -->

<div id="nav">
	<ul class="clearfix">
		<li><a href="">입사지원서</a></li>
		<li><a href="">게시판</a></li>
		<li><a href="">갤러리</a></li>
		<li><a href="">방명록</a></li>
	</ul>
</div>

<!-- //nav -->
