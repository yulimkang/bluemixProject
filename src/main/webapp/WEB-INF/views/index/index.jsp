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
<script type="text/javascript" src="/resources/scheduler/scheduler.min.js"></script>
<script type="text/javascript" src="/resources/fullcalander/locale-all.js"></script>
<script type="text/javascript" src="/resources/fullcalander/gcal.js"></script>
<link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>  

    
<html>
<head>
<title>메인</title>

</head>
	<body>
		<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
		
		<div class="container">
			<div id="calendar"></div>
			<div class="jumbotron" style="height:350px">
				<div class="col-sm-12">
					<div class="form-group col-sm-3">
					  <label class="control-label">날짜</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
					    <input class="form-control input-sm" type="text" id="rsvDate">
					  </div>
					</div>
					
					<div class="form-group col-sm-3">
					  <label class="control-label">시작 시간</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-clock-o"> </i></span>
 		 		 		<select id="rsvStartTime" class="form-control input-sm">
							<option value="09:00:00">오전 09:00</option>
							<option value="09:30:00">오전 09:30</option>
							<option value="10:00:00">오전 10:00</option>
							<option value="10:30:00">오전 10:30</option>
							<option value="11:00:00">오전 11:00</option>
							<option value="11:30:00">오전 11:30</option>
							<option value="12:00:00">오후 12:00</option>
							<option value="12:30:00">오후 12:30</option>
							<option value="13:00:00">오후 01:00</option>
							<option value="13:30:00">오후 01:30</option>
							<option value="14:00:00">오후 02:00</option>
							<option value="14:30:00">오후 02:30</option>
							<option value="15:00:00">오후 03:00</option>
							<option value="15:30:00">오후 03:30</option>
							<option value="16:00:00">오후 04:00</option>
							<option value="16:30:00">오후 04:30</option>
							<option value="17:00:00">오후 05:00</option>
							<option value="17:30:00">오후 05:30</option>
							<option value="18:00:00">오후 06:00</option>
							<option value="18:30:00">오후 06:30</option>
						</select>   
					  </div>
					</div>
					
					<div class="form-group col-sm-3">
					  <label class="control-label">끝 시간</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-clock-o"> </i></span>
					    <select id="rsvEndTime" class="form-control input-sm">
							<option value="09:30:00">오전 09:30</option>
							<option value="10:00:00">오전 10:00</option>
							<option value="10:30:00">오전 10:30</option>
							<option value="11:00:00">오전 11:00</option>
							<option value="11:30:00">오전 11:30</option>
							<option value="12:00:00">오후 12:00</option>
							<option value="12:30:00">오후 12:30</option>
							<option value="13:00:00">오후 01:00</option>
							<option value="13:30:00">오후 01:30</option>
							<option value="14:00:00">오후 02:00</option>
							<option value="14:30:00">오후 02:30</option>
							<option value="15:00:00">오후 03:00</option>
							<option value="15:30:00">오후 03:30</option>
							<option value="16:00:00">오후 04:00</option>
							<option value="16:30:00">오후 04:30</option>
							<option value="17:00:00">오후 05:00</option>
							<option value="17:30:00">오후 05:30</option>
							<option value="18:00:00">오후 06:00</option>
							<option value="18:30:00">오후 06:30</option>
							<option value="19:00:00">오후 07:00</option>
						</select> 
					  </div>
					</div>
					
					<div class="form-group col-sm-3">
					  <label class="control-label">색상</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-eye"></i></span>
					    <select id="rsvColor" class="form-control input-sm">
					    	<option value="red">빨강</option>
					    	<option value="yellow">노랑</option>
					    	<option value="green">초록</option>
					    	<option value="blue">파랑</option>
					    	<option value="purple">보라</option>
					    </select>
					  </div>
					</div>
				</div>
				
				<div class="col-sm-12">
					<div class="form-group col-sm-4">
					  <label class="control-label">회의실</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-laptop"></i></span>
					    <input class="form-control input-sm" type="text" id="rsvConfNm">
					  </div>
					</div>
					
					<div class="form-group col-sm-4">
					  <label class="control-label">회의제목</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-comments"></i></span>
					    <input class="form-control input-sm" type="text" id="rsvTitle">
					  </div>
					</div>
					
					<div class="form-group col-sm-4">
					  <label class="control-label">비밀번호</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-key"></i></span>
					    <input class="form-control input-sm" type="text" id="rsvDelPwd">
					  </div>
					</div>
				</div>
				
				<div class="col-sm-12">
					<div class="form-group col-sm-4">
					  <label class="control-label">전화번호</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-phone"></i></span>
					    <input class="form-control input-sm" type="text" id="rsvMemPn">
					  </div>
					</div>
					
					<div class="form-group col-sm-4">
					  <label class="control-label">이름</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-user-plus"></i></span>
					    <input class="form-control input-sm" type="text" id="rsvMemNm">
					  </div>
					</div>
					
					
					<div class="form-group col-sm-4">
					  <label class="control-label">이메일</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-envelope"></i></span>
					    <input class="form-control input-sm" type="text" id="rsvMemEm">
					  </div>
					</div>
				</div>
				
					<div class="form-group col-sm-4">
					  <label class="control-label">메일 수신
					    <input type="checkbox" id="rsvEmailCheck" checked></label>
					</div>
					
				
				<button type="submit" class="btn btn-primary" style="float:right; margin-right:30px; margin-left:0px">Submit</button>
				 <button type="reset" class="btn btn-default" style="float:right; ">Cancel</button>
			</div>	
		</div>
		
		<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
	</body>
</html>

<script>


//예약하기위한 slot 선택
function regRsv(date, resourceObj){
	
	var startTime = moment(date).format('kk:mm:ss');

	var end = moment(date).add(1, 'hours');
	var endTime = moment(end).format('kk:mm:ss');

	$("#rsvStartTime option[value='"+startTime +"']").attr("selected", true);  //시작시간 자동 select
	$("#rsvEndTime option[value='"+endTime +"']").attr("selected", true);  //종료시간 자동 select
	
	$("#rsvConfNm").val(resourceObj.title); //회의실 자동 입력

}

$(document).ready(function(){// document ready
	

	 //달력 첫 조회시 오늘 날짜로 조회
	  var date = new Date();
	
	  $( function() {
		    $("#rsvDate").datepicker();
		    $("#rsvDate").datepicker("option", "dateFormat", "yy-mm-dd");
		    $("#rsvDate").datepicker().datepicker("setDate", new Date()); //defaultDate 설정
		} );

	  //datepicker 이용해 사용자가 원하는 날짜로 이동
	  $("#rsvDate").datepicker({ 
		  minDate : date, //오늘 이전 날짜는 선택 불가 -> 예약 불가
		  onSelect: function(dateText) {  
			  $('#calendar').fullCalendar('gotoDate', dateText);
		  }
	   }); 
		  
	
	$('#calendar').fullCalendar({
		locale:"ko", //한글로 변환
		
//		timezone: 'Asia/Seoul', ///*****************************고칠것 -> 필요?
		navLinks: false, // can click day/week names to navigate views
		eventOverlap: false, // will cause the event to take up entire resource height , 이벤트 겹치기 불가
		contentHeight: 'auto',
		minTime: "09:00:00", //하루의 시작 시간
		maxTime: "19:00:00", //하루의 종료 시간
		schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
		defaultView: 'agendaDay', //하루 기준
		defaultDate: date, //오늘 날짜가 default
		editable: true, // events can be dragged and resizedevents can be dragged and resized

		//	eventStartEditable : true, //이거는 resize는 어느정도 막을 수 있는데 드래깅 제한있음

		slotEventOverlap: false, //같은 slot위에 오버랩불가
		selectable: true, //slot select true (칸 한칸씩 선택 가능)
		eventLimit: true, // allow "more" link when too many events
		businessHours: [ // specify an array instead
		 			    {
		 			        dow: [ 1, 2, 3, 4, 5], // Monday, Tuesday, Wednesday, Thursday, Friday
		 			        start: '09:00', // a start time
		 			 	    end: '19:00' // an end time 
		 			    }
		 			],
		 			
		 /* 			
		//구글 캘린더 연동
		googleCalendarApiKey: 'AIzaSyApQ5kq_N3tI_LwPI6sEoXyH8NCPhef7Nc',  
			eventSources : { 
				googleCalendarId : 'ko.south_korea#holiday@group.v.calendar.google.com', // 구글과 연동해 휴일 가져오기
				className : "koHolidays",
				color : "#DB7093",
				textColor : "#FFFFFF"
			},
		*/	 
					
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'agendaDay,month'
		},
		views: {
			agendaTwoDay: {
				type: 'agenda',
				duration: { days: 2 },

				// views that are more than a day will NOT do this behavior by default
				// so, we need to explicitly enable it
				groupByResource: true

				//// uncomment this line to group by day FIRST with resources underneath
				//groupByDateAndResource: true
			}
		},

		//// uncomment this line to hide the all-day slot
		allDaySlot:false,
		
		eventClick: function(calEvent, jsEvent, view) { //이미 등록된 이벤트 클릭시 예약 내역 조회
			$.ajax({
				url:"/Reservation/GetReservationInfo",
				type:"post",
				dataType:"JSON",
				data:{"no":calEvent.id},
				success:function(info){
					var date = new Date(info[0].rsvDate);
					$("#rsvDate").val(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
					$("#rsvStartTime option[value='"+info[0].rsvStartTime+"']").attr("selected", true);
					$("#rsvEndTime option[value='"+info[0].rsvEndTime+"']").attr("selected", true);
					$("#rsvColor option[value='"+info[0].rsvColor+"']").attr("selected", true);
					$("#rsvConfNm").val(info[0].rsvConfNm);
					$("#rsvTitle").val(info[0].rsvTitle);
					$("#rsvDelPwd").val(info[0].rsvDelPwd);
					$("#rsvMemPn").val(info[0].rsvMemPn);
					$("#rsvMemNm").val(info[0].rsvMemNm);
					$("#rsvMemEm").val(info[0].rsvMemEm);
					if(info[0].rsvEmailCheck == "Y"){
						$('input:checkbox[id="rsvEmailCheck"]').attr("checked", true); 
					}else{
						$('input:checkbox[id="rsvEmailCheck"]').attr("checked", false); 
					}
				},
				error:function(request,status,error){
					alert("EventClick Error");
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
	    },
	    dayClick: function(date, jsEvent, view, resourceObj) {
				regRsv(date, resourceObj);
	    },
		resources: function(callback, start, end, timezone) {
			$.ajax({
					url:"/MeetingRoom/GetResources",
					type:"post",
					dataType:"JSON",
					success:function(list){
						var resources = new Array();
						
						$.each(list, function(i){
							resources[i] = {"id":this.confNo,
												"title":this.confNm		
							};
						});
						
						callback(resources);
					},
					error:function(){
						alert("Resource Error");
					}
			});
		},
		events:function(start, end, timezone, callback){
			$.ajax({
				url:"/Reservation/GetReservation",
				type:"post",
				dataType:"JSON",
				success:function(list){
					var events = new Array();
					var date = null;
					
					$.each(list, function(i){
						date = new Date(this.rsvDate);
						events[i] = {
										"title":this.rsvTitle,
										"id":this.rsvNo,
										"start":new Date(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+this.rsvStartTime),
										"end":new Date(date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+this.rsvEndTime),
										"memNm":this.rsvMemNm,
										"color":this.rsvColor,
										"confirm":this.rsvConfirmState,
										"resourceId":this.conference.confNo,
										eventDurationEditable:false
										};
					});
					
					for(var j=0; j<events.length; j++){
						
						if(events[j].color == 'red'){ //빨강
							if(events[j].confirm == 'Y'){ //관리자로부터 승인된 가예약
								events[j].color="#ff3333";
							}else{ //아직 관리자로부터 승인받지 못한 가예약
								events[j].color="#ffcccc";
							}
						}else if(events[j].color == 'yellow'){ //노랑
							if(events[j].confirm == 'Y'){ //관리자로부터 승인된 가예약
								events[j].color="#ffff1a";
							}else{ //아직 관리자로부터 승인받지 못한 가예약
								events[j].color="#ffff80";
							}
						}else if(events[j].color == 'green'){ //초록
							if(events[j].confirm == 'Y'){ //관리자로부터 승인된 가예약
								events[j].color="#39ac39";
							}else{ //아직 관리자로부터 승인받지 못한 가예약
								events[j].color="#c4edc4";
							}
						}else if(events[j].color == 'blue'){ //파랑
							if(events[j].confirm == 'Y'){ //관리자로부터 승인된 가예약
								events[j].color="#0000e6";
							}else{ //아직 관리자로부터 승인받지 못한 가예약
								events[j].color="#ccccff";
							}
						}else if(events[j].color == 'purple'){ //보라
							if(events[j].confirm == 'Y'){ //관리자로부터 승인된 가예약
								events[j].color="#6600cc";
							}else{ //아직 관리자로부터 승인받지 못한 가예약
								events[j].color="#e5ccff";
							}
						}
						
					}
					
					callback(events);
				},
				error:function(request,status,error){
					alert("Event Error");
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});	
		},

		select: function(start, end, jsEvent, view, resource) {
			console.log(
				'select',
				start.format(),
				end.format(),
				resource ? resource.id : '(no resource)'
			);
		}
	});

});

/*  $('.fc-event').attr('style', 'font-size: 1.85em !important');
  */

</script>
<style>

	body {	
		margin: 0;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 15px;
	}

	#calendar {
		max-width: 1200px;
		margin: 50px auto;
	}
	
	.fc-widget-content {
    position: relative;
}

.fc-day-number {
    position: fixed;
    right: 0;
    bottom: 0;
}
	.typeColor{
		display: inline;
		width: 100px;
	}

</style>


 
