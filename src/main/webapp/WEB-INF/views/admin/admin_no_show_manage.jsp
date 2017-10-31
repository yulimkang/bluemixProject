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

	<div class="container" id="noShowForm">
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">No-Show </div>
		</div>

		<form action="/AdminManagement/NoShowManagement" id="dateChangeForm">
			<div class="col-lg-12 col-xs-12">
				<input type="text" id="datepicker" name="datepicker" style="float:right; text-align: center;" onChange="dateChange()"> 
				<span style="float:right; margin-right:10px"> 날짜 : </span>
					 <br><br>
			</div>
		</form>
		<div id="noShowMemberList" class="col-lg-12 table-responsive">
				<table class="table table-hover text-center">
					<thead>
						<tr>
							<th width="10%" style="text-align: center;"> 회의날짜</th>
							<th width="15%" style="text-align: center;">회의시간</th>
							<th width="20%" style="text-align: center;">회의제목</th>
							<th width="15%" style="text-align: center;">회의실</th>
							<th width="15%" style="text-align: center;">핸드폰 번호</th>
							<th width="10%" style="text-align: center;">예약자</th>
							<th width="15%" style="text-align: center;">No-Show</th>
						</tr>
					</thead>
					<tbody>
					<!-- 작성자 : 박성준 -->
					<!--JSTL이용한 전체 예약 내역 노출 -->
						<c:forEach items="${allReservation}" var="allReservation" varStatus="count">
							<tr>
							
							<td width="10%" style="text-align: center;">${allReservation.RSV_DATE}</td>
							<td width="15%" style="text-align: center;">${fn:substring(allReservation.RSV_START_TIME,0,5)} ~ ${fn:substring(allReservation.RSV_END_TIME,0,5)}</td>
							<td width="20%" style="text-align: center;">${allReservation.RSV_TITLE}</td>
							<td width="15%" style="text-align: center;">${allReservation.CONF_NM}</td>
							<td width="15%" style="text-align: center;">${allReservation.RSV_MEM_PN}</td>
							<td width="10%" style="text-align: center;">${allReservation.RSV_MEM_NM}</td>
							
							<td width="15%" style="text-align: center;">
								<Button type="button" class="btn btn-danger btn-sm"  onClick="noShowBtn('${allReservation.RSV_NO}','${allReservation.RSV_TITLE}')">No-Show</Button>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">
	window.onload = function() {
		$("#onBoardingUpadateInputBox").hide();
	}

	var date = new Date();

	<fmt:parseDate var="dateString" value="${nowDate}" pattern="yyyy/MM/dd" />
	<fmt:formatDate value="${dateString}" var="dateChangeFormat" pattern="yyyy/MM/dd" />

	function description() {
		$("#onBoardingUpadateInputBox").show();
	}

	// 변경한 포맷으로 날짜 설정
	$(function() {
		$("#datepicker").datepicker();
		$("#datepicker").datepicker("option", "dateFormat", "yy/mm/dd");
		$("#datepicker").datepicker().datepicker("setDate",
				"${dateChangeFormat}"); //defaultDate 설정

	});

	function dateChange() {
		var datePickerValue = $("#datepicker").val();
		dateChangeForm.submit();
	}

	// 노쇼버튼 ajax
	function noShowBtn(rsvNo, rsvTitle) {

		var confirmCheck = confirm(rsvTitle + "를 No-Show 하시겠습니까?");

		if (confirmCheck == true) {

			$.ajax({
				url : "/AdminManagement/NoShowSubmit",
				dataType : "text",
				type : "POST",
				data : {
					"rsvNo" : rsvNo
				},
				success : function() {
					$("#noShowMemberList").load(
							window.location.href + " #noShowMemberList");
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "error:" + error);
				}
			});

		}

	}
</script>






