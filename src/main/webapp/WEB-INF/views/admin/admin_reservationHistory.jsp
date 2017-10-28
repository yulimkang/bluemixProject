<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" 	type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script>
<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script> 

<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>
<html>
<head>
 <title>ReservationHistory</title>
</head>
<body id="htmlBody">
 <jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
 	<div class="container" >
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">예약관리</div>
		</div>
		
		
	<form id="listFilter" name="listFilter" method="post" action="">
				<div class="col-lg-2 col-xs-7">
					<select class="form-control" id="searchOption" name="searchOption" onchange="selectOption()">
						<option>조회옵션</option>
						<option value="allList">전체</option>
						<option value="repeatList">반복예약목록</option>
						<option value="overtimeList">5시간이상예약목록</option>
					</select>
				</div><br><br>
			</form>
	
	<form id="fakeRsvListAll">
	<div class="col-lg-12 table-responsive">
				<table class="table  table-hover text-center" align="center">
					<thead>
						<tr>
							<td><b>예약종류</b></td>
							<td><b>회의날짜</b></td>
							<td><b>회의시간</b></td>
							<td><b>회의실</b></td>
							<td><b>회의제목</b></td>
							<td><b>예약자</b></td>
							<td><b>비고</b></td>
							<td><b>상세보기</b></td>
						</tr>
					</thead>
					
					<tbody >
						<c:forEach items="${fakeReservation}" var="fakeReservation" >
							<tr>
						 	<c:choose>
								<c:when test="${fakeReservation.RSV_REPEAT_NO ne 0 }"><td>반복예약</td>								
								<td>${fn:substring(fakeReservation.RSV_DATE,5,10)} ~ ${fn:substring(fakeReservation.LAST_DATE,5,10)}</td>
								</c:when>
								
								<c:otherwise>
								<td>5시간이상예약</td>
								<td>${fn:substring(fakeReservation.RSV_DATE,5,10)}</td>
								
								</c:otherwise>
							</c:choose> 
								<td>
								<c:out value="${fn:substring(fakeReservation.RSV_START_TIME,0,5)}"/> ~ 
								<c:out value="${fn:substring(fakeReservation.RSV_END_TIME,0,5)}"/>
								</td>
								<td>${fakeReservation.CONF_NM}</td>
								<td>${fakeReservation.RSV_TITLE}</td>
								<td>${fakeReservation.RSV_MEM_NM}</td>
								
								
								<td>
									<button type="button" class="btn btn-primary btn-sm" onClick="updateBtnClick('${fakeReservation.RSV_NO}','${fakeReservation.RSV_REPEAT_NO}')">승인</button>
									<button type="button" class="btn btn-danger btn-sm" onClick="deleteBtnClick('${fakeReservation.RSV_NO}','${fakeReservation.RSV_REPEAT_NO}')">반려</button>
								<td>
									<c:if test="${fakeReservation.RSV_REPEAT_NO ne 0}">
									<button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-yellow btn-sm" onClick="lookInside(${fakeReservation.RSV_REPEAT_NO})">상세보기</button>
									
									
									<div id="myModal" class="modal fade"  style="width:100%">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<div class="col-lg-12 col-md-12 col-sm-6 col-xs-12" >
								
													<div id="onBoardingUpadateInputBox" class="table-responsive">
														<table id="tmp" class="table table-hover" style="text-align: center;">
															<thead>
															<tr>
																		<td style="text-align: center; width:calc(100%-200px);"><b>회의실</b></td>
																		<td style="text-align: center;"><b>회의날짜</b></td>
																		<td style="text-align: center; "><b>회의시간</b></td>
																		<td style="text-align: center;"><b>회의제목</b></td>
																		
															</tr>
															</thead>
															<tbody>
																<tr>
																</tr>
															</tbody>
														</table>
													</div>
											</div>
										</div>
									</div>
								</div>
									</c:if>
								</td>
									
									
								
							</tr>
						</c:forEach>
					</tbody>
	
				</table>
				</div>
			</div>
			</form>
			<br><br><br><br>

 
 <jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>

</body>
</html>

<script type="text/javascript">
	
	//console.log($("#tmp").attr('id'));
/* 	$(function() {
		$('#accordion').accordion();
	}) */
	function lookInside(repeatNo){
 		   var table = document.getElementById("tmp");
  		   var rowlen = table.rows.length;
		   
 		   var insideArray = new Array();
		   
		   console.log("hi");
			$.ajax({
			url : "/AdminBoarding/LookInside",
			dataType : "json",
			async : false,
			type : "POST",
			data : { "repeatNo" : repeatNo},
			success : function(data) {
				
				
 				insideArray = data;
				
			
				
				
 				for(var i = table.rows.length - 1; i > 0; i--)
 		         {
 		             table.deleteRow(i);
 		         }

 		         for(var i =0; i<data.length; i++){
 		        	 
 		        	var startTime = insideArray[i].RSV_START_TIME.substring(0,5);
 		        	var endTime = data[i].RSV_END_TIME.substring(0,5);
 		        	var date = insideArray[i].RSV_DATE.substring(5,10);
 		        	
 		        	var row = table.insertRow();
		             row.insertCell(0).innerHTML = insideArray[i].CONF_NM;
 		             row.insertCell(1).innerHTML = date;
 		             row.insertCell(2).innerHTML = startTime + "~" + endTime;
 		             row.insertCell(3).innerHTML = insideArray[i].RSV_TITLE;
		             
 		          }
			
			},
			error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   }


		});
	}

	function updateBtnClick(reservationSeq,repeatNo){
		$.ajax({
			url : "/AdminBoarding/ReservationUpdate",
			dataType : "text",
			type : "POST",
			data : {"reservationSeq":reservationSeq,"repeatNo":repeatNo},
			success : function() {
				location.reload();
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "error:" + error);
			}
		});
	
	
 	}
	function deleteBtnClick(reservationSeq,repeatNo){
		$.ajax({
			url : "/AdminBoarding/ReservationDelete",
			dataType : "text",
			type : "POST",
			data : {"reservationSeq":reservationSeq,"repeatNo":repeatNo},
			success : function() {
				location.reload();
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "error:" + error);
			}
		});
	
	
	}
	
	function selectOption(){
		
			$("#listFilter").attr("action","/AdminBoarding/Filtering")
			$("#listFilter").submit();
	
	}

	

</script>