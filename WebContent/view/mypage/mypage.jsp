<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>

<title>Insert title here</title>
</head>

<style>
.user_information {
    background: #f5f5f5;
    width: 100%;
    margin-top: 43px;
    position: relative;
}
.user_information .inner {
    min-height: 167px;
    box-sizing: border-box;
    padding: 50px 0 50px 150px;
}
.inner {
    position: relative;
    margin: 0px 200px;
}
.user_information img {
    display: block;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    position: absolute;
    top: 30px;
    left: 30px;
}
.user_information>.inner>strong {
    display: inline-block;
    font-size: 22px;
    color: #3b3b3b;
    font-family: Microsoft YaHei,'NST';
}
.user_information .follow {
    display: inline-block;
    color: #979797;
    font-size: 16px;
    font-family: Microsoft YaHei,'NSL';
    white-space: nowrap;
    word-break: keep-all;
}
.user_information .dsc {
    width: 500px;
    font-size: 13px;
    color: #676767;
    margin: 7px 0 0 2px;
}
.user_information .btn_area {
    position: absolute;
    right: 0;
    bottom: 20px;
}
.data-tab {
    width: 100%;
    height: 60px;
    border-top: 1px solid #e8e9e9;
    border-bottom: 1px solid #e8e9e9;
    margin-bottom: 55px;
}
ol, ul {
    list-style: none;
}
.tab-list { 
	padding-inline-start: 0px;
	overflow: hidden;
	margin-block-end: 0px;
	margin-bottom: -1px;
}
.tab-list li {
	float: left; 
	width: 33%; 
	text-align: center; 
	margin-right: -1px;
}
.tab-list li a {
	text-decoration: none; 
	display: block; 
	padding: 10px 0px; 
	font-size: 15px; 
	color: #666; 
	font-weight: 700;
}
.tab-list li.active a {
	color: #3b3b3b; 
	border-bottom: 3px solid #ff6d00;
}
</style>

<body>

<div id="content">
	<div class="user_information">
		<div class="inner">
			<img src="<%=request.getContextPath()%>/uploadFile/${member.profile}">
			<strong>${member.name}</strong>
			<span>0명 팔로잉</span>
			<p class="dsc">
				<br><br>
				<strong>${member.selfIntroduction}</strong>
				<br>
			</p>
			<div class="btn_area">
				<c:if test="${session==member.memNum}">
				<button onclick="javascript:window:location='<%=request.getContextPath()%>/member/modifyForm?memNum=${member.memNum}'">
				프로필 수정
				</button>
				</c:if>
				<c:if test="${session!=member.memNum}">
				<button onclick="javascript:window:location='<%=request.getContextPath()%>/member/follow?memNum=${member.memNum}'">팔로우</button>
				</c:if>
			</div>
		</div>
	</div>
	
	<div class="data-tab">
		<div class="inner">
		<ul class = "tab-list">
			<li class = "active" onclick = "openTitle('myRecipe')"><a href = "#">마이레시피</a></li>
			<li onclick = "openTitle('scrap')"><a href = "#">스크랩</a></li>
			<li onclick = "openTitle('following')"><a href = "#">팔로잉</a></li>
		</ul>
		</div>
	</div>
	
	<div class="title" id = "myRecipe" style="width: 1024px; margin: 0px 200px;">
		myRecipe
	</div>
	
	<div class="title" id = "scrap" style = "display:none; width: 1024px; margin: 0px 200px;">
		scrap
	</div>
	
	<div class="title" id = "following" style = "display:none; width: 1024px; margin: 0px 200px;">
		following
	</div>
	
	
</div>

<script>
var tabBtn = $(".data-tab > .inner > ul > li"); 

tabBtn.click(function(e) {
    e.preventDefault();
    var target = $(this); 
    var index = target.index(); 
    tabBtn.removeClass("active");   
    target.addClass("active");   
		    
}); 

function openTitle(titleName) {
	   var i;
	   var x = document.getElementsByClassName("title");
	   for (i = 0; i < x.length; i++) {
	      x[i].style.display = "none";  
	   }
	   document.getElementById(titleName).style.display = "block";
}

</script>

</body>
</html>