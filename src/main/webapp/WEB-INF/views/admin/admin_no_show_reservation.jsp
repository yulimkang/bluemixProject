<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">

<html>
<head>
<body>

		<form action="/AdminManagement/NoShowManagement" id="dateChangeForm">
		<br>
			<div class="col-lg-12 col-xs-12">
				<input type="text" id="datepicker" name="datepicker" style="float:right; text-align: center;" onChange="dateChange()"> 
				<span style="float:right; margin-right:10px"> 회의 날짜 : </span>
					 <br><br>
			</div>
		<br>
		</form>
	
	<div class="col-lg-12 table-responsive" id="reservationTable">
		<table class="table table-hover text-center" id="noShwReservationListTable" >
			<thead>
				<tr>
					<th width="10%" style="text-align: center;">회의 날짜</th>
					<th width="10%" style="text-align: center;">회의 시간</th>
					<th width="25%" style="text-align: center;">회의 제목</th>
					<th width="15%" style="text-align: center;">회의실</th>
					<th width="10%" style="text-align: center;">예약자</th>
					<th width="15%" style="text-align: center;">핸드폰</th>
					<th width="10%" style="text-align: center;">비고</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${noShowReservationList}" var="noShowReservationList"
					varStatus="count">
					<tr>

						<td width="10%" style="text-align: center;">${noShowReservationList.HST_DATE}</td>
						<td width="10%" style="text-align: center;">${fn:substring(noShowReservationList.HST_START_TIME,0,5)} ~ ${fn:substring(noShowReservationList.HST_END_TIME,0,5)}</td>
						<td width="25%" style="text-align: center;">${noShowReservationList.HST_RSV_TITLE}</td>
						<td width="15%" style="text-align: center;">${noShowReservationList.CONF_NM}</td>
						<td width="10%" style="text-align: center;">${noShowReservationList.HST_RSV_MEM_NM}</td>
						<td width="15%" style="text-align: center;">${noShowReservationList.HST_RSV_MEM_PN}</td>

						<td width="10%" style="text-align: center;">
							<Button type="button" class="btn btn-primary btn-sm " onClick="noShowReservationCancel(${noShowReservationList.HST_NO})" > No-Show 취소</Button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


	<form action="" id="detailPopUpForm" name="detailPopUpForm">
		<input type="hidden" id="detailMemberName" name="detailMemberName">
		<input type="hidden" id="detailMemberPhone" name="detailMemberPhone">
	</form>

</div>
</body>
</head>
</html>

<script>

var date = new Date();
$(function() {
	datePickerChange();
});

function datePickerChange(){
	$("#datepicker").datepicker();
	$("#datepicker").datepicker("option", "dateFormat", "yy/mm/dd");
	$("#datepicker").datepicker().datepicker("setDate",	date); //defaultDate 설정
}

function dateChange(){
	
	var noShowReservationList = new Array();
	
	var wantDate=  $("#datepicker").val();
	
	var reservationTable = document.getElementById('noShwReservationListTable');
	
	$.ajax({
		url : "/AdminManagement/noShowReservationDateChange",
		dataType : "json",
		async : false,
		type : "POST",
		data : {"wantDate":wantDate},		
		success : function(data) {
			noShowReservationList = data;
			
			for(var i = reservationTable.rows.length - 1; i > 0; i--)
			{
				reservationTable.deleteRow(i);
			}
			
			for (var i =0; i<noShowReservationList.length; i++){
				var row = reservationTable.insertRow();		// IE와 Chrome 동작을 달리함.
					// HTML에서의 권장 표준 문법
				
				var historyStartTime= noShowReservationList[i].HST_START_TIME +"";
				var historyEndTime= noShowReservationList[i].HST_END_TIME +"";
				
				row.insertCell(0).innerHTML = noShowReservationList[i].HST_DATE;
				row.insertCell(1).innerHTML = historyStartTime.substring(0,5) +"~" + historyEndTime.substring(0,5);
				row.insertCell(2).innerHTML = noShowReservationList[i].HST_RSV_TITLE;
				row.insertCell(3).innerHTML = noShowReservationList[i].CONF_NM;
				row.insertCell(4).innerHTML = noShowReservationList[i].HST_RSV_MEM_NM;
				row.insertCell(5).innerHTML = noShowReservationList[i].HST_RSV_MEM_PN;
				row.insertCell(6).innerHTML = '<Button type="button" class="btn btn-primary btn-sm " onClick="noShowReservationCancel('+noShowReservationList[i].HST_NO+')"> No-Show 취소</Button>'
				
			}
			
		},
		error : function(request, status, error) {
		}
	});
	
	
}

function noShowReservationCancel(hstNo){
	
	var confirmCheck = confirm("noShow를 취소하시겠습니까?");
	if(confirmCheck == true){
		
		$.ajax({
			
			url:"/AdminManagement/noShowReservationCancel",
			dataType:"text",
			data : {"hstNo":hstNo},
			success: function(data){
				
				$("#reservationTable").load(window.location.href + " #reservationTable");
				datePickerChange();
			},
			error : {
				
				
			}
			
		})
		
	}
	
}



</script>