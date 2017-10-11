<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" 	type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script>  


<html>
<head>
<title>관리자 setting</title>

</head>
<body>
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	<div class="container" id="ddddd">
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">설정 관리</div>
		</div>
		
		<div id="settingFormDiv">
			<div class="col-sm-6">
				<div class="panel panel-default">
				  <div class="panel-heading">비밀번호 변경</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">비밀번호 </label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-key"></i></span>
						    <input class="form-control input-sm" type="text" id="changePw">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">비밀번호 재 입력</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-key"></i></span>
						    <input class="form-control input-sm" type="text" id="changePwCheck">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <Button type="button" class="btn btn-primary btn-sm" onclick="changePwBtn()">변경</Button>
					</div>
				  </div>
				</div>
			</div>
			
			<div class="col-sm-6">
				<div class="panel panel-default">
				  <div class="panel-heading">예약가능 기간(월)</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">현재 예약 가능 기간</label>
						  <div class="input-group">
						    <input class="form-control input-sm" type="text" id="reservationMaxMonth" disabled="" value="${settingValue.SET_REV_MAX_MONTH}개월">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">변경 예약 가능 기간</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
						    <input class="form-control input-sm" type="text" id="changeReservationMaxMonth">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <a href="#" class="btn btn-primary btn-sm" onclick="reservationMaxMonthBtn()">변경</a>
					</div>
				  </div>
				</div>
			</div>
		</div>
		
		<div>
			<div class="col-sm-6">
				<div class="panel panel-primary">
				  <div class="panel-heading">사용자 최대 예약가능 시간</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">현재 최대 예약가능 시간</label>
						  <div class="input-group">
						    <input class="form-control input-sm" type="text" id="reservationMaxTime" disabled="" value="${settingValue.SET_REV_MAX_TIME}분">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">변경 최대 예약 가능 시간</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
						    <input class="form-control input-sm" type="text" id="changeReservationMaxTime">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <Button type="button" class="btn btn-primary btn-sm" onclick="reservationMaxTimeBtn()">변경</Button>
					</div>
				  </div>
				</div>
			</div>
			
			<div class="col-sm-6">
				<div class="panel panel-primary">
				  <div class="panel-heading">이메일 전송 시간</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">현재 이메일 전송 시간</label>
						  <div class="input-group">
						    <input class="form-control input-sm" type="text" id="reservationEmailTime" disabled="" value="${settingValue.SET_EMAIL_TIME}분">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">변경 이메일 전송시간</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
						    <input class="form-control input-sm" type="text" id="changeReservationEmailTime">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <a href="#" class="btn btn-primary btn-sm" onclick="reservationEmailTimeBtn()">변경</a>
					</div>
				  </div>
				</div>
			</div>
			
			<div>
				<div class="col-sm-6">
					<div class="panel panel-danger">
					  <div class="panel-heading">No Show 미준수 횟수</div>
					  <div class="panel-body">
					    <div class="form-group col-sm-5">
							  <label class="control-label">현재 NoShow 미준수 횟수</label>
							  <div class="input-group">
							    <input class="form-control input-sm" type="text" id="reservationNoShowCount()" disabled="" value="${settingValue.SET_NO_SHOW_COUNT}번">							  
							    </div>
						</div>
						
						<div class="form-group col-sm-5">
							  <label class="control-label">변경 최대 예약 가능 시간</label>
							  <div class="input-group">
							    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
							    <input class="form-control input-sm" type="text" id="changeReservationNoShowCount">
							  </div>
						</div>
						
						<div class="col-sm-2" style="margin-top:25px">
							  <a href="#" class="btn btn-primary btn-sm" onclick="reservationNoShowCountBtn()">변경</a>
						</div>
					  </div>
					</div>
				</div>
				
				<div class="col-sm-6">
					<div class="panel panel-danger">
					  <div class="panel-heading">No Show 회원 정지</div>
					  <div class="panel-body">
					    <div class="form-group col-sm-5">
							  <label class="control-label">현재 정지 일</label>
							  <div class="input-group">
							    <input class="form-control input-sm" type="text" id="reservationNoShowBanDay" disabled="" value="${settingValue.SET_NO_SHOW_BAN_DAY}일">							  
							   </div>
						</div>
												
						<div class="form-group col-sm-5">
							  <label class="control-label">변경 정지 일</label>
							  <div class="input-group">
							    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
							    <input class="form-control input-sm" type="text" id="changeReservationNoShowBanDay">
							  </div>
						</div>
						
						<div class="col-sm-2" style="margin-top:25px">
							  <a href="#" class="btn btn-primary btn-sm" onclick="reservationNoShowBanDayBtn()">변경</a>
						</div>
					  </div>
					</div>
				</div>
			
				<div class="col-sm-6" >
					<div class="panel panel-warning">
					  <div class="panel-heading">독점방지</div>
					  <div class="panel-body">
					    <div class="form-group col-sm-5">
							  <label class="control-label">현재 독점방지 시간</label>
							  <div class="input-group">
							    <input class="form-control input-sm" type="text" id="reservationMaxMonopoly" disabled="" value="${settingValue.SET_REV_MAX_MONOPOLY}시간">							  </div>
						</div>
						
						<div class="form-group col-sm-5" >
							  <label class="control-label">변경 독점방지 기간</label>
							  <div class="input-group">
							    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
							    <input class="form-control input-sm" type="text" id="ChangeReservationMaxMonopoly" onKeyPress="return numkeyCheck(event)">
							  </div>
						</div>
						
						<div class="col-sm-2" style="margin-top:25px">
							  <Button type="button" class="btn btn-primary btn-sm" onclick="reservationMaxMonopolyBtn()">변경</Button>
						</div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form action="" id="settingSubmitForm">
		<input type="hidden" id="selectSetting" name="selectSetting">
		<input type="hidden" id="settingValue" name="settingValue">
	</form>

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">

function numkeyCheck(e) { 
	var keyValue = event.keyCode; 
	if( ((keyValue >= 48) && (keyValue <= 57)) ) 
		return true; 
	else return false; 
}

function checkMonth(value){
	if(value<1 || value>12)
		return false;
	else
		return true;
}

function checkDay(value){
	if(value<1 || value>365)
		return false;
	else
		return true;
}

function checkHour(value){
	if(value<1 || value>24)
		return false;
	else
		return true;
}

function checkMin(value){
	if(value<1 || value>60)
		return false;
	else
		return true;
}


function reservationMaxMonopolyBtn(){
	$("#selectSetting").val("SET_REV_MAX_MONOPOLY");
	var changeValue = $("#ChangeReservationMaxMonopoly").val();
	
	if(checkHour(changeValue) == true){
		$("#settingValue").val(changeValue);
		settingSubmit();
	}
	else{
		alert("잘못된 입력");
	}
}

function reservationNoShowBanDayBtn(){
	$("#selectSetting").val("SET_NO_SHOW_BAN_DAY");
	var changeValue = $("#changeReservationNoShowBanDay").val();
	
	if(checkDay(changeValue) == true){
		$("#settingValue").val(changeValue);
		settingSubmit();
	}
	else{
		alert("잘못된 입력");
	}
}

function reservationNoShowCountBtn(){
	$("#selectSetting").val("SET_NO_SHOW_COUNT");
	var changeValue = $("#changeReservationNoShowCount").val();
	$("#settingValue").val(changeValue);
	settingSubmit();
}

function reservationEmailTimeBtn(){
	$("#selectSetting").val("SET_EMAIL_TIME");
	var changeValue = $("#changeReservationEmailTime").val();
	
	if(checkMin(changeValue) == true){
		$("#settingValue").val(changeValue);
		settingSubmit();
	}
	else{
		alert("잘못된 입력");
	}
}

function reservationMaxTimeBtn(){
	$("#selectSetting").val("SET_REV_MAX_TIME");
	var changeValue = $("#changeReservationMaxTime").val();
	
	if(checkMin(changeValue) == true){
		$("#settingValue").val(changeValue);
		settingSubmit();
	}
	else{
		alert("잘못된 입력");
	}
}

function reservationMaxMonthBtn(){
	$("#selectSetting").val("SET_REV_MAX_MONTH");
	var changeValue = $("#changeReservationMaxMonth").val();
	
	if(checkMonth(changeValue) == true){
		$("#settingValue").val(changeValue);
		settingSubmit();
	}
	else{
		alert("잘못된 입력");
	}
	
}

function settingSubmit(){
	$.ajax({
        url : "/AdminManagement/SettingSubmit",
        dataType : "text",
        async : false,
        type : "POST",
        data : $('#settingSubmitForm').serializeArray(),
        success: function(data) {
        	$("#ddddd").load(window.location.href + " #ddddd");
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"error:"+error);
        }
    });
}

</script>






