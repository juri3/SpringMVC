<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판</title>
</head>
<body>
<p class="w3-left" style="padding-lift:30px"></p>
<div class="w3-container">
<b>레시피 작성</b><br>
<br><br><br>

<form method="post" name="writeform"
		action="${pageContext.request.contextPath}/rcp/writePro">
	<input type="hidden" name="rcpNum" value=1>
	<input type="hidden" name="memNum" value=1>
	
[카테고리]
	<!-- <select name="nation">
    	<option value="korean">한식</option>
    	<option value="chinese">중식</option>
    	<option value="japanese">일식</option>
    	<option value="western">서양식</option>
    	<option value="east-asian">동남아요리</option>
    	<option value="ect">기타</option>
	</select> -->
	
	<!-- <select name="ingredient">
    	<option value="meat">육류</option>
    	<option value="seaFood">해산물</option>
    	<option value="fruit/vege">과일/채소</option>
    	<option value="ect">기타</option>
	</select> -->
	<select name="howToCook">
    	<option value="fry">볶음/부침</option>
    	<option value="fry2">부침</option>
    	<option value="boil">끓이기</option>
    	<option value="brew">조림</option>
    	<option value="oilfry">튀김</option>
    	<option value="ect">기타</option>
	</select>
	<br><br>
	<tr>
		<td width="100" align="center">음식에 들어가는 재료들</td>
		<td width="330"><input type="text" size="5" maxlength="100" name="ingredient"></td>
	</tr>
	
	<br><br>
	<tr>
		<td width="70" align="center">조리시간</td>
		<td width="330"><input type="text" size="10" maxlength="30" name="cookingTime"></td>
	</tr>
	
	
	
	
	<table class="w3-table-all" style="width:70%;">
		<tr>
			<td align="right" colspan="2"><a href="${pageContext.request.contextPath}/rcp/list">글목록</a></td>
		</tr>
		<tr>
			<td width="70" align="center">레시피 제목</td>
			<td width="330"><input type="text" size="40" maxlength="30" name="title"></td>
		</tr>
		
		<tr>
			<td width="70" align="center">음식 이름</td>
			<td width="330"><input type="text" size="40" maxlength="30" name="foodName"></td>
		</tr>
		
		<tr>
			<td width="70" align="center">한줄 소개</td>
			<td width="330"><input type="text" size="40" maxlength="30" name="subtitle"></td>
			
		</tr>
		
		<tr>
			<td width="70" align="center">레시피 내용<br>(사진 첨부)</td>
			<td width="330"><textarea name="content" rows="13" cols="40" style="resize: none;"></textarea></td>
		</tr>
		
		<tr>
			<td width="70" align="center">해시태그</td>
			<td width="330"><textarea name="hashTag" rows="3" cols="40" style="resize: none;"></textarea></td>
		</tr>
		
		<tr>
			<td width="70" align="center">file</td>
			<td width="330"><input type="file" size="40" maxlength="30" name="thumbNail"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글쓰기">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onclick="window.location='${pageContext.request.contextPath}/rcp/list'">
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>