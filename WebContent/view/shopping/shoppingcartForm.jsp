<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	shopping cart<br>
</div>

<div>
	<div>
		<form name="cartlist" method="post" >
		<table border="1">
			<thead>
				<tr>
					<td><input type="checkbox" name="cart_all" checked ></td>
					<td>상품명</td> <td>수량</td> <td>가격</td> <td>배송비</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${cartlist }">
					<tr>
						<td><input type="checkbox" name="cart"></td>
						<td>${list.productName }</td>
						<td>${list.qty }</td>
						<td>${list.price }</td>
						
					</tr>
				</c:forEach>
					<tr>
					<td rowspan="${fn:length(cartlist)} "> 2,500원 <br>
								(3만원 이상 구매시 배송비 무료)</td>
					</tr>	
			</tbody>			
		</table>
		<div>
			<button type="submit" style="margin-right: 20px;" formaction="" > 선택항목 삭제 </button>
			<button type="submit" formaction="" > 선택항목 찜하기 </button>
		</div>
		</form>
	</div>
	<div>
		<form name="amount" method="post" action="${pageContext.request.contextPath}/shopping/order">
			
			바뀌는 것이 되는 것인가 
			
			<input type="submit" value="주문하기">
			<button onclick="javascript:history.back()">이전페이지</button>		
		</form>
	</div>
</div>



</body>
</html>