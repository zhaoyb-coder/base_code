<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP '404.jsp' starting page</title>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

</head>

<link rel="stylesheet" href="<%=basePath%>pages/error/css/main.css"
	type="text/css" media="screen, projection" />
<!-- main stylesheet -->
<link rel="stylesheet" type="text/css" media="all"
	href="<%=basePath%>pages/error/css/tipsy.css" />
<!-- Tipsy implementation -->

<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="css/ie8.css" />
<![endif]-->

<script type="text/javascript"
	src="<%=basePath%>pages/error/scripts/jquery-1.7.2.min.js"></script>
<!-- uiToTop implementation -->
<script type="text/javascript"
	src="<%=basePath%>pages/error/scripts/custom-scripts.js"></script>
<script type="text/javascript"
	src="<%=basePath%>pages/error/scripts/jquery.tipsy.js"></script>
<!-- Tipsy -->

<script type="text/javascript">
	$(document).ready(function() {

		universalPreloader();

	});

	$(window).load(function() {

		//remove Universal Preloader
		universalPreloaderRemove();

		rotate();
		dogRun();

		//Tipsy implementation
		$('.with-tooltip').tipsy({
			gravity : $.fn.tipsy.autoNS
		});

	});
</script>

<body>

	<div id="wrapper">
		<!-- 404 graphic -->
		<div class="graphic"></div>
		<!-- Not found text -->
		<div class="not-found-text">
			<h1 class="not-found-text">页面跑丢了</h1>
		</div>

		<div class="dog-wrapper">
			<!-- dog running -->
			<div class="dog"></div>
			<!-- dog running -->

			<div class="dog-bubble"></div>

		</div>

		<!-- planet at the bottom -->
		<div class="planet"></div>
		<!-- planet at the bottom -->
	</div>


</body>
</html>