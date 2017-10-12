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
					    <input class="form-control input-sm" type="text" id="selectDate">
					  </div>
					</div>
					
					<div class="form-group col-sm-3">
					  <label class="control-label">시작 시간</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-clock-o"> </i></span>
					    <input class="form-control input-sm" type="text" id="startDate">
					  </div>
					</div>
					
					<div class="form-group col-sm-3">
					  <label class="control-label">끝 시간</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-clock-o"> </i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
					
					<div class="form-group col-sm-3">
					  <label class="control-label">색상</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-eye"></i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
				</div>
				
				<div class="col-sm-12">
					<div class="form-group col-sm-4">
					  <label class="control-label">회의실</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-laptop"></i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
					
					<div class="form-group col-sm-4">
					  <label class="control-label">회의제목</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-comments"></i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
					
					<div class="form-group col-sm-4">
					  <label class="control-label">비밀번호</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-key"></i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
				</div>
				
				<div class="col-sm-12">
					<div class="form-group col-sm-4">
					  <label class="control-label">전화번호</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-phone"></i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
					
					<div class="form-group col-sm-4">
					  <label class="control-label">이름</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-user-plus"></i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
					
					
					<div class="form-group col-sm-4">
					  <label class="control-label">이메일</label>
					  <div class="input-group">
					    <span class="input-group-addon input-sm"><i class="fa fa-envelope"></i></span>
					    <input class="form-control input-sm" type="text" id="inputSmall">
					  </div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary" style="float:right; margin-right:30px; margin-left:0px">Submit</button>
				 <button type="reset" class="btn btn-default" style="float:right; ">Cancel</button>
			</div>	
		</div>
		
		<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
	</body>
</html>

<script>


function dateInput(calEvent){
	
	var allStringDate = calEvent.start;
	var allChangeDate = Date.parse(allStringDate);
	var startTime = new Date(allChangeDate);
	
	var startTimeInput = startTime.getHours() +":" + startTime.getMinutes();
	alert(startTimeInput);
	
	$("#startDate").val(startTimeInput);
}


$(function() { // document ready
	
	  $( function() {
		    $( "#selectDate" ).datepicker();
		    $( "#selectDate" ).datepicker( "option", "dateFormat", "yy-mm-dd"	 );
		} );
	
	$('#calendar').fullCalendar({
		locale:"ko",
		timezone: 'Asia/Seoul',
		contentHeight: 'auto',
		minTime: "09:00:00",
		maxTime: "19:00:00",
		schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
		defaultView: 'agendaDay',
		defaultDate: '2017-09-07',
		editable: true,
		selectable: true,
		eventLimit: true, // allow "more" link when too many events
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
		//allDaySlot: false,
		allDaySlot:false,
		
		eventClick: function(calEvent, jsEvent, view) {
			
			dateInput(calEvent);

	        // change the border color just for fun
	        $(this).css('border-color', 'red');

	    },
	    
	    
		resources: [
			{ id: 'a', title: 'IBM 회의실1' },
			{ id: 'b', title: 'IBM 회의실2'},
			{ id: 'c', title: 'IBM 회의실3'  },
			{ id: 'd', title: 'IBM 회의실4'}
		],
		events: [
			{ id: '2', resourceId: 'a', start: '2017-09-07T09:00:00', end: '2017-09-07T14:00:00', title: 'bb', color:'red' },
			{ id: '3', resourceId: 'c', start: '2017-09-07T12:00:00', end: '2017-09-07T13:00:00', title: 'event 3' },
			{ id: '3', resourceId: 'b', start: '2017-09-07T12:00:00', end: '2017-09-08T06:00:00', title: 'event 3' },
			{ id: '4', resourceId: 'c', start: '2017-09-07T07:30:00', end: '2017-09-07T09:30:00', title: 'event 4' },
			{ id: '5', resourceId: 'd', start: '2017-09-07T10:00:00', end: '2017-09-07T15:00:00', title: 'event 5' }
		],

		select: function(start, end, jsEvent, view, resource) {
			console.log(
				'select',
				start.format(),
				end.format(),
				resource ? resource.id : '(no resource)'
			);
		},
		dayClick: function(date, jsEvent, view, resource) {
			console.log(
				'dayClick',
				date.format(),
				resource ? resource.id : '(no resource)'
			);
		}
	});

});

$('.fc-event').attr('style', 'font-size: 1.85em !important');


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

</style>


