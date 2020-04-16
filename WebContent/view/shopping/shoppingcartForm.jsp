<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.js"></script>
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
	function check_jjim(){
		alert('찜목록에 저장되었습니다.')
	}
	
	$(function(){ //전체선택 체크박스 클릭 
		$("#cart_all").click(function(){ //만약 전체 선택 체크박스가 체크된상태일경우 
			if($("#cart_all").prop("checked")) { //해당화면에 전체 checkbox들을 체크해준다 
				$("input[type=checkbox]").prop("checked",true); // 전체선택 체크박스가 해제된 경우 
			} else { //해당화면에 모든 checkbox들의 체크를해제시킨다. 
				$("input[type=checkbox]").prop("checked",false); 
			} 
		})
			// 한개의 체크박스 선택 해제시 전체선택 체크박스도 해제
		$(".chk").click(function(){
		    if($("input[name='cart']:checked").length == ${fn:length(cartlist)}){
		        $("#cart_all").prop("checked", true);
		    }else{
		        $("#cart_all").prop("checked", false);
		    }
		});

	})

	


</script>

<div>
	<div>
		<form name="cartlist" method="post" >
		<table border="1">
			<thead>
				<tr>
					<td><input type="checkbox" id="cart_all" checked ></td>
					<td>상품명</td> <td>수량</td> <td>가격</td> <td>배송비</td>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="0"/>
				<c:set var="totalpay" value="0"/>
				<c:forEach var="list" items="${cartlist }">
					<c:set var="count" value="${count+1}"/>
					<tr>
						<td><input type="checkbox" class="chk" name="cart" value="${list.cartNum }" checked ></td>
						<td>${list.productName }</td>
						<td>${list.qty }</td>
						<td>${list.price * list.qty}</td>
						<c:set var="totalpay" value="${totalpay+ list.price * list.qty }"/>
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
			<button type="submit" formaction="${pageContext.request.contextPath}/shopping/regist_jjim" onclick="return check_jjim()" > 선택항목 찜하기 </button>
		</div>
		</form>
	</div>
	<div>
		<form name="amount" method="post" action="${pageContext.request.contextPath}/shopping/order">
		<div id="pay_detail">
			<div id="price_detail">
				 + 
			</div>
			<div id="price_total">
				총 주문 금액 
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