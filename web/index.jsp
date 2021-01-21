<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/login2.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>

<title>小米商城首页</title>
</head>
<body>

<%@ include file="header.jsp"%>
<!--网站中间内容开始-->
<div id="thred">
    <img src="image/login_bg.jpg" width="1230" height="450" />
</div>

   <div id="forth">
        <a href="" id="a_left"><img src="image/down.jpg" width="170" height="170" /></a>
        <a href="" id="a_left"><img src="image/sj01.png" width="332" height="170" /></a>
        <a href="" id="a_left"><img src="image/sj02.png" width="332" height="170" /></a>
    	<a href="" id="a_left"><img src="image/sj03.png" width="332" height="170" /></a>
   </div>
   <div id="fifth">
   		<span id="fif_text">小米最新商品</span>
   </div>
    <div id="sixth">
            <span style="margin-left:0px; border-top:#ffa500 1px solid">
            	<img src="image/1.png" width="218" height="260" />
            </span>
            <span style=" border-top:#008000 1px solid">
            	<img src="image/2.png" width="218" height="260" />


            </span>
            <span style="border-top:#0000ff 1px solid">
            	<img src="image/3.png" width="218" height="260" />


            </span>
            <span style="border-top:#ff0000 1px solid">
            <img src="image/4.png" width="218" height="260" />


            </span>
            <span style="border-top:#008080 1px solid">
            <img src="image/6.png" width="218" height="260" />
            </span>
    </div>
   <!-- 底部 -->
   <%@ include file="footer.jsp"%>
</body>
</html>