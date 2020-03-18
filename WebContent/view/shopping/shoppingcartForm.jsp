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
<script>
	function check_delete(){
		var result = confirm('선택한 상품을 삭제하겠습니까?')
		console.log(result);
	}
	function checkAll() {
	    var obj = cartlist.cart;
	    if(obj.length){
		    for(var i=0; i<obj.length; i++) {
		        obj[i].checked = cartlist.cart_all.checked;
		    }
	    }else{
	    	obj.checked = cartlist.cart_all.checked;
	    }
	}


</script>

<div>
	<div>
		<form name="cartlist" method="post" >
		<table border="1">
			<thead>
				<tr>
					<td><input type="checkbox" name="cart_all" onClick="javascript:checkAll();"></td>
					<td>상품명</td> <td>수량</td> <td>가격</td> <td>배송비</td>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="0"/>
				<c:forEach var="list" items="${cartlist }">
					<c:set var="count" value="${count+1}"/>
					<tr>
						<td><input type="checkbox" name="cart" value="${list.cartNum }"></td>
						<td>${list.productName }</td>
						<td>${list.qty }</td>
						<td>${list.price * list.qty}</td>
						<c:if test="${count == 1}">
							<td rowspan="${fn:length(cartlist)} "> 2,500원 <br>
								(3만원 이상 구매시 배송비 무료)</td>
						</c:if>
						
					</tr>
				</c:forEach>	
			</tbody>			
		</table>
		<div>
			<button type="submit" style="margin-right: 20px;" formaction="${pageContext.request.contextPath}/shopping/cart_delete" onclick="return check_delete()"> 선택항목 삭제 </button>
			<button type="submit" formaction="" > 선택항목 찜하기 </button>
		</div>
		</form>
	</div>
	<div>
		<form name="amount" method="post" action="${pageContext.request.contextPath}/shopping/order">
		<div id="pay_detail">
			<div id="price_detail">
				
			</div>
			<div id="price_total">
				
			</div>
		</div>
		<div>			
			<input type="submit" value="주문하기">
			<button onclick="javascript:history.back()">이전페이지</button>
		</div>		
		</form>
	</div>
</div>



</body>
</html>