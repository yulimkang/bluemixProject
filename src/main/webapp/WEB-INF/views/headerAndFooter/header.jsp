<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
	
<link rel="stylesheet" href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  
<link rel="stylesheet" type="text/css"	href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css"	href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />
<link href='/resources/fullcalander/fullcalendar.css' rel='stylesheet' />
<link href='/resources/fullcalander/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<link href='/resources/scheduler/scheduler.min.css' rel='stylesheet' />
<script type="text/javascript" src="/resources/fullcalander/moment.min.js"></script>
<script type="text/javascript" src="/resources/fullcalander/fullcalendar.min.js"></script>
<script type="text/javascript" src="/resources/fullcalander/locale-all.js"></script>
<script type="text/javascript" src="/resources/scheduler/scheduler.min.js"></script>
<link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>  

<html>
<head>
<title>Insert title here</title>
</head>

<body>
	<div class="page-header">
		<div class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a onclick="main()" class="navbar-brand">IBM 회의실 예약 시스템</a>
				</div>
				<div class="navbar-collapse collapse" id="navbar-main">
					<ul class="nav navbar-nav">

					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a onclick="notice()">공지사항</a></li>
						<li><a onClick="searchPage()">검색</a></li>
						<li><a target="_blank" onclick="admin()">관리자</a></li>
						
						<c:if test="${sessionScope.id ne null}">
						   <li><a target="_blank" onclick="logout()">로그아웃</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>