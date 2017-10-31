<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>

<link rel="stylesheet"	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" 	type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script> 
<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script>   

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 세션확인 -->
<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>

<html>
<head>
<title>관리자 No-Show 관리</title>

</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	
		<div class="container" id="ddddd">
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">No-Show 관리</div>
		</div>
		
		<!-- 작성자 : 박성준 -->
		<!-- 탭 선택에 따른 nav 노출 -->
		<ul class="nav nav-tabs">
		  <li class="active"><a href="#userNoShow" data-toggle="tab">사용자 No-Show 내역</a></li>
		  <li><a href="#reservationNoShow" data-toggle="tab">예약 No-Show 내역</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
		  <div class="tab-pane fade active in" id="userNoShow">
		  	<jsp:include page="admin_no_show_user.jsp"></jsp:include>
		  </div>
		  <div class="tab-pane fade" id="reservationNoShow">
		  	<jsp:include page="admin_no_show_reservation.jsp"></jsp:include>
		  </div>
		</div>
		
		<br><br>
		
	</div>
	
	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>










