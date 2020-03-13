<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title>Insert title here</title>
</head>
<style>
i {
	font-size: 30px;
}

a{
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>

<body>
	<form name="select_ingredient" method="post" action="${pageContext.request.contextPath}/shopping/cart" >
	<!-- 임의로 만든 페이지 이 부분 지영ㅇ언니꺼로 교체d -->
	<table>
		<thead>
		<tr>
			<td>${recipt.rcpNum }. ${recipt.title }  </td>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>오늘의 요리</td>
			<td>${recipt.foodName }</td>
		</tr>
		<tr>
			<td>재료</td>
		</tr>
		<c:forEach var="ingredient" items="${ingredients}">
		<tr>
			<td><input type="checkbox" name="select" value="${ingredient}" >${ingredient}</td>
		</tr>
		</c:forEach>
		<tr>
			<td>해시태그</td>
			<td>${recipt.hashtag }</td>
		</tr>
		</tbody>
		</table>
		<button onclick=""><span id="all">전체선택</span></button>
		<input type="submit"><i class="material-icons" style="font-size: 30px;">add_shopping_cart</i>장바구니담기
	</form>
</body>
</html>