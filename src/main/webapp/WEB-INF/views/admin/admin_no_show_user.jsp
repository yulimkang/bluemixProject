<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta name="viewport" content="width=device-width, initial-scale=1">

<html>
<head>
<body>
	<!-- 작성자 : 박성준 -->
	<!-- noShow를 한번이상이라도 한 회원 출력-->
	<div id="noShowMemberList" class="col-lg-12 table-responsive">
		<table class="table table-hover text-center">
			<thead>
				<tr>
					<th width="10%" style="text-align: center;">사용자 이름</th>
					<th width="15%" style="text-align: center;">이메일</th>
					<th width="15%" style="text-align: center;">핸드폰 번호</th>
					<th width="10%" style="text-align: center;">No-Show</th>
					<th width="20%" style="text-align: center;">상태</th>
					<th width="30%" style="text-align: center;">비고</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${noShowUserList}" var="noShowUserList"
					varStatus="count">
					<tr>

						<td width="10%" style="text-align: center;">${noShowUserList.MEM_NAME}</td>
						<td width="15%" style="text-align: center;">${noShowUserList.MEM_EM}</td>
						<td width="15%" style="text-align: center;">${noShowUserList.MEM_PN}</td>
						<td width="10%"
							style="text-align: center; color: #585858; font-weight: bold">
							${noShowUserList.COUNT_WARN}번</td>

						<!--  정상이라면 정상, 차단이라면 관리자 차단, noShow차단이라면 차단된 날짜 노출 -->
						<c:choose>
							<c:when test="${noShowUserList.MEM_STATE eq '정상'}">
								<td width="20%" style="text-align: center;">정상</td>
							</c:when>
							
							<c:when test="${noShowUserList.MEM_STATE eq '차단'}">
								<td width="20%" style="text-align: center;">관리자 차단</td>
							</c:when>
							<c:otherwise>
								<td width="20%" style="text-align: center;">~
									${noShowUserList.MEM_BANDAY} 차단</td>
							</c:otherwise>
						</c:choose>

						<td width="30%" style="text-align: center;">
							<Button type="button" class="btn btn-primary btn-sm " id="init"
								onClick="noShowValueSetting(this,${noShowUserList.MEM_NO})"
								value="init">Count 초기화</Button>
							<button type="button" class="btn btn-default btn-sm "
								data-toggle="modal" data-target="#myModal"
								onClick="detailView('${noShowUserList.MEM_NAME}','${noShowUserList.MEM_PN}')">
								이번 달 No-Show</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true" id="myModal"
		onclick="">
		<div class="modal-dialog modal-lg">
			<div class="modal-content table-responsive">
				<table class="table table-hover text-center" id="detailTable"
					name="detailTable">
					<thead>
						<tr>
							<th width="10%" style="text-align: center;">회의날짜</th>
							<th width="20%" style="text-align: center;">회의시간</th>
							<th width="25%" style="text-align: center;">회의제목</th>
							<th width="20%" style="text-align: center;">회의실</th>
							<th width="15%" style="text-align: center;">예약자</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="10%" style="text-align: center;">${noShowDetail.HST_DATE}</td>
							<td width="20%" style="text-align: center;">${fn:substring(noShowDetail.HST_START_TIME,0,5)}
								${fn:substring(noShowDetail.HST_END_TIME,0,5)}</td>
							<td width="25%" style="text-align: center;">${noShowDetail.HST_RSV_TITLE}</td>
							<td width="20%" style="text-align: center;">${noShowDetail.CONF_NM}</td>
							<td width="15%" style="text-align: center;">${noShowDetail.HST_RSV_MEM_NM}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<form action="" id="detailPopUpForm" name="detailPopUpForm">
		<input type="hidden" id="detailMemberName" name="detailMemberName">
		<input type="hidden" id="detailMemberPhone" name="detailMemberPhone">
	</form>


</body>
</head>
</html>

<script type="text/javascript">

// 카운트 초기화
function noShowValueSetting(btn,seq){
	
	var confirmCheck = confirm("Count는 0으로 초기화되며, 사용자는 정상상태가 됩니다. \n초기화 하시겠습니까?");

	if (confirmCheck == true) {
	
		var noShowBtnType = btn.id;
		var noShowUserSeq = seq;
		
		$.ajax({
			url : "/AdminManagement/NoShowValueSetting",
			dataType : "text",
			type : "POST",
			data : { "noShowBtnType" : noShowBtnType, "noShowUserSeq":noShowUserSeq},
			success : function(data) {
				$("#noShowMemberList").load(window.location.href + " #noShowMemberList");
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "error:" + error);
			}
		});
	}
}

// 노쇼내역 DetailView
function detailView(memName, memPhone){
	
	$("#detailMemberName").val(memName);
	$("#detailMemberPhone").val(memPhone);
	
	var detailViewArray = new Array();
	
	var table = document.getElementById('detailTable');
	
	$.ajax({
		url : "/AdminManagement/NoShowDetailPopUp",
		dataType : "json",
		async : false,
		type : "POST",
		data : $('#detailPopUpForm').serializeArray(),		
		success : function(data) {
			detailViewArray = data;
			
			for(var i = table.rows.length - 1; i > 0; i--)
			{
			    table.deleteRow(i);
			}
			
			for (var i =0; i<detailViewArray.length; i++){
				var row = table.insertRow();		// IE와 Chrome 동작을 달리함.
					// HTML에서의 권장 표준 문법
				
				var historyStartTime= detailViewArray[i].HST_START_TIME +"";
				var historyEndTime= detailViewArray[i].HST_END_TIME +"";
				
				row.insertCell(0).innerHTML = detailViewArray[i].HST_DATE;
				row.insertCell(1).innerHTML = historyStartTime.substring(0,5) +"~" + historyEndTime.substring(0,5);
				row.insertCell(2).innerHTML = detailViewArray[i].HST_RSV_TITLE;
				row.insertCell(3).innerHTML = detailViewArray[i].CONF_NM;
				row.insertCell(4).innerHTML = detailViewArray[i].HST_RSV_MEM_NM;
			}
			
		},
		error : function(request, status, error) {
		}
	});
	
	
	
}
</script>