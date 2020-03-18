<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container w3-display-middle">
		<br> <br>
		<p align="center">
			<b>글 내용 보기</b>
		</p>
		<table class="w3-table-all">
			<tr>
				<td align="center">글번호</td>
				<td>${article.rcpNum}</td>
			</tr>


			<tr>
				<td>조리방식</td>
				<td>${article.howToCook}</td>
			</tr>

			<tr>
				<td>레시피</td>
				<td>${article.title}</td>
			</tr>
			<tr>
				<td>음식 이름</td>
				<td>${article.foodName}</td>
			</tr>

			<tr>
				<td>필요한 재료</td>
				<td>${article.ingredient}</td>
			</tr>

			<tr>
				<td>작성자(일단 회원번호로 해둠)</td>
				<td>${article.memNum}</td>
			</tr>

			<tr>
				<td>한줄 소개</td>
				<td>${article.subtitle}</td>
			</tr>

			<tr>
				<td>조리시간</td>
				<td>${article.cookingTime}</td>
			</tr>

			<tr>
				<td>작성일</td>
				<td>${article.reg_date}</td>
			</tr>

			<tr>
				<td>해시 태그</td>
				<td>${article.hashTag}</td>
			</tr>

			<tr>
				<td>이미지</td>
				<td><img
					src="<%=request.getContextPath() %>/uploadFile/${article.fileName}"></td>
			</tr>
			<tr>
				<td colspan="4" class="w3-center"><input type="button"
					value="글수정"
					onclick="document.location.href='updateForm?num=${article.rcpNum}'">&nbsp;&nbsp;&nbsp;
					<input type="button" value="글삭제"
					onclick="document.location.href='deleteForm?num=${article.rcpNum}'">&nbsp;&nbsp;&nbsp;
					<input type="button" value="글목록"
					onclick="document.location.href='list'">&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
</body>
</html>