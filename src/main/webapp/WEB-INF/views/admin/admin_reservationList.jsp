<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

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
<title>관리자 메인</title>

</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	<div class="container" >
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">예약내역</div>
		</div>
		
		<div id="deleteDiv" >
		<div class="col-lg-3">
		  	<button type="button" onClick="showInputBtn()" class="pull-left btn btn-primary">이전예약현황 삭제</button> <br><br>
		  </div>
		   	<form name="deleteResevAndHistForm" id="deleteResevAndHistForm" method="post" action="">
		   		<div class="col-lg-2">
					<input type="text" id="histDeleteDate" name="histDeleteDate" class="form-control" readonly/> 
				</div>
				<div class="col-lg-3">이전의 내용을 삭제합니다.</div>
				<div class="col-lg-2">
					<button type="button" onClick="storeResevAndHistBtn()" class="pull-right btn btn-primary">저장</button>
				</div>
				<div class="col-lg-2">
					<button type="button" onClick="deleteResevAndHistBtn()" class="pull-right btn btn-primary">삭제</button>
				</div>
		   	</form>
		</div>
		
		<br><br>
		
		<div style="margin-top:3%;">
			<form name="searchHistByDateForm" id="searchHistByDateForm" method="post" action="">
				<div class="col-lg-1">
					시작일 
				</div>
				<div class="col-lg-3">
					<input type="text" id="selectStartDate" name="selectStartDate" class="form-control" placeholder="yyyy-mm-dd"/>  
				</div>
				<div class="col-lg-1">
					종료일 
				</div>
				<div class="col-lg-3">
					 <input type="text" id="selectEndDate" name = "selectEndDate" class="form-control" placeholder="yyyy-mm-dd"/>
				</div>
				
				<div class="col-lg-2">
					<button type="submit" onClick="serachHistClickBtn()" class="pull-right btn btn-primary">검색</button>
				</div>			
				
				
			</form>

		</div>
		 
	</div>

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script>
	
	$("#deleteResevAndHistForm").hide();
	
	function showInputBtn() {
		$("#deleteResevAndHistForm").show();
	}
	
	$ ( function() {
		
		$("#histDeleteDate").datepicker({
			dateFormat : "yy-mm-dd",
		});
		
		
		$("#selectStartDate").datepicker({
			dateFormat : "yy-mm-dd",
			onClose : function(selectedDate) {
				$("#selectEndDate").datepicker("option", "minDate", selectedDate);
			}
		});
		
		$("#selectEndDate").datepicker({
			dateFormat : "yy-mm-dd",
				onClose : function(selectedDate) {
					$("#selectStartDate").datepicker("option", "maxDate", selectedDate);
				}
		});
		
	});
	
	//시작일과 종료일 계속 보이도록
	$(function() {
		$("#selectStartDate").val("${startDateBack}");
		$("#selectEndDate").val("${endDateBack}");
		$("#sort").val("${sortTypeBack}");
		
	});
	
	
	//시작일과 종료일로 검색
// 	function searchHistBtn() {
		
// 		var selectStartDate = $("#selectStartDate").val();
// 		var selectEndDate = $("#selectEndDate").val();
			
// 		if( selectStartDate.length == 0 || selectEndDate.length == 0) {
			
// 			alert("시작일 또는 종료일을 입력해주세요.");
// 		}
		
		
// 	} 
	
// 	function searchHistClickBtn() {
		
// 		var selectStartDate = $("#selectStartDate").val();
// 		var selectEndDate = $("#selectEndDate").val();
		
// 		if( selectStartDate.length == 0 || selectEndDate.length == 0) {
// 				alert("시작일 또는 종료일을 입력해주세요.");
// 			}

		
// 		$.ajax({
// 			url : "/AdminReservCheckAndDelete/ReservHistory",
// 			dataType : "text",
// 			async : false,
// 			type : "POST",
// 			data : {
// 				"selectStartDate" : selectStartDate,
// 				"selectEndDate" : selectEndDate
// 				},
// 			sucess : function(data) {
// 					//alert("확인");
// 			},
// 			error : function(request, status, error) {
// 				alert("code : "+request.status + "\n" + "error : " + error);
// 			}
			
// 		});
		
// 	}
	
	//입력한 날짜 이전의 내용을 삭제
	function deleteResevAndHistBtn() {
		var histDeleteDate = $("#histDeleteDate").val();
		
		//alert("histDeleteDate : "+histDeleteDate);
		
		alert("정말 삭제하시겠습니까?");
		
		//$("#deleteResevAndHistForm").attr("action", "/AdminReservCheckAndDelete/DeleteReservAndHistory");
		//$("#deleteResevAndHistForm").submit();
		
		
		$.ajax({
			url : "/AdminReservCheckAndDelete/DeleteReservAndHistory",
			dataType : "text",
			async : false,
			type : "POST",
			data : { "deleteDate" : histDeleteDate },
			success : function(data) {
				//alert("sucess check");
				
				$('<div/>').html(data).find('deleteDiv');	
				
				alert("삭제되었습니다");
			},
			error : function(request, status, error) {
				alert("code : "+request.status + "\n" + "error : " + error);
			}
		});
		
		
	}
	
	//입력한 날 이전의 내역을 저장
	function storeResevAndHistBtn() {
		
		
		
	}
	
	//검색
	function serachHistClickBtn(){
		
		var selectStartDate = $("#selectStartDate").val();
 		var selectEndDate = $("#selectEndDate").val();
		
 		if( selectStartDate.length == 0 || selectEndDate.length == 0) {
 			alert("시작일 또는 종료일을 입력해주세요.");
 		}else {
 			$("#searchHistByDateForm").attr("action","/AdminReservCheckAndDelete/SearchGeneralHistory");
			$("#searchHistByDateForm").submit();
 		}

	}
	

</script>

