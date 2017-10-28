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

<meta name="viewport" content="width=device-width, initial-scale=1">

<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>

<html>
<head>
<title>관리자 No-Show </title>

</head>
<body id="htmlBody">

	<div class="container" id="noShowForm">
	
	<div class="panel panel-default">
				<div class="panel-body">이번달 No - Show 내역</div>
		</div>
		<div id="noShowMemberList" class="col-lg-12 table-responsive">
				<table class="table table-striped table-hover text-center">
					<thead>
						<tr>
							<th width="10%" style="text-align: center;"> 회의날짜</th>
							<th width="20%" style="text-align: center;">회의시간</th>
							<th width="25%" style="text-align: center;">회의제목</th>
							<th width="20%" style="text-align: center;">회의실</th>
							<th width="15%" style="text-align: center;">예약자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${noShowDetail}" var="noShowDetail" varStatus="count">
							<tr>
							
							<td width="10%" style="text-align: center;">${noShowDetail.HST_DATE}</td>
							<td width="20%" style="text-align: center;">${fn:substring(noShowDetail.HST_START_TIME,0,5)} ~ ${fn:substring(noShowDetail.HST_END_TIME,0,5)}</td>
							<td width="25%" style="text-align: center;">${noShowDetail.HST_RSV_TITLE}</td>
							<td width="20%" style="text-align: center;">${noShowDetail.CONF_NM}</td>
							<td width="15%" style="text-align: center;">${noShowDetail.HST_RSV_MEM_NM}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

</body>
</html>

<script type="text/javascript">
</script>






