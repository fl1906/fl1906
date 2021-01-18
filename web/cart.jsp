<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物车</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function delCart(c_id) {
		if(confirm("是否要删除该商品？？"))
		{
			location.href="cart?method=delete&c_id="+c_id;
		}
	}
	function sub (cid,cnum,price) {
		if(cnum==1)
		{
			if(confirm("是否要删除该商品？？"))
			{
				location.href="cart?method=delete&c_id="+cid;
			}
		}else {
			cnum--;
			location.href="cart?method=update&c_id="+cid+"&c_num="+cnum+"&price="+price;
		}
	}
	function add(cid,cnum,price) {
		cnum++;
		location.href="cart?method=update&c_id="+cid+"&c_num="+cnum+"&price="+price;
	}
	function clearCart(uid) {
		if(confirm("是否要清空购物车？？"))
		{
			location.href="cart?method=clear&uid="+uid;
		}

	}
</script>
</head>
<body style="background-color:#f5f5f5">
<%@ include file="header.jsp"%>
<div class="container" style="background-color: white;">
	<div class="row" style="margin-left: 40px">
		<h3>我的购物车<small>温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</small></h3>
	</div>
	<div class="row" style="margin-top: 40px;">
		<c:if test="${empty list}">
            <h3>购物车啥也没有哦~</h3>
		</c:if>
		<c:if test="${!empty list}">


		<div class="col-md-10 col-md-offset-1">
			<table class="table table-bordered table-striped table-hover">
 				<tr>
 					<th>序号</th>
 					<th>商品名称</th>
 					<th>价格</th>
 					<th>数量</th>
 					<th>小计</th>
 					<th>操作</th>
 				</tr>
 				<c:set value="0" var="sum"></c:set>
 				<c:forEach items="${list}" var="c" varStatus="i">
	 				<tr>
	 					<th>${i.count}</th>
	 					<th>${c.product.p_name}</th>
	 					<th>${c.product.p_price}</th>
	 					<th width="100px">
		 					<div class="input-group">
		 						<span class="input-group-btn">
		 							<button class="btn btn-default" type="button" onclick="sub(${c.c_id},${c.c_num},${c.product.p_price})">-</button>
		 						</span>
		 						<input type="text" class="form-control" id="num_count${i.count}" value="${c.c_num}" readonly="readonly" style="width:40px">
		 						<span class="input-group-btn">
		 							<button class="btn btn-default" type="button" onclick="add(${c.c_id},${c.c_num},${c.product.p_price})">+</button>
		 						</span>
	 						</div>
	 					</th>
	 					<th>¥&nbsp;${c.c_count }</th>
	 					<th>
	 						<button type="button" class="btn btn-default" onclick="delCart(${c.c_id})">删除</button>
	 					</th>
	 				</tr>
	 				<c:set var="sum" value="${sum+c.c_count}"></c:set>
 				</c:forEach>
			</table>
		</div>
		</c:if>
	</div>
	<hr>
	<div class="row">
		<div class="pull-right" style="margin-right: 40px;">
			
	            <div>
	            	<a id="removeAllProduct" href="javascript:clearCart(0)" onclick="clearCart(${loginUser.uid})" class="btn btn-default btn-lg">清空购物车</a>
	            	&nbsp;&nbsp;
	            	<a href="${pageContext.request.contextPath}/orders?method=preView&uid=${loginUser.uid}" class="btn  btn-danger btn-lg">添加收货地址</a>
	            	
	            </div>
	            <br><br>
	            <div style="margin-bottom: 20px;">        		  
	            	商品金额总计：<span id="total" class="text-danger"><b>￥&nbsp;&nbsp;${sum}</b></span>
	            </div>
		</div>
	</div>
</div>
	
    
<!-- 底部 -->
<%@ include file="footer.jsp"%>

</body>
</html>