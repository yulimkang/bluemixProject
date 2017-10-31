<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />
<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script>   

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>


<html>
<head>
<title>관리자 회의실 관리</title>
</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	<div class="container">

		<jsp:include page="admin_footer.jsp"></jsp:include>

		<div>
			<div class="panel panel-default">
				<div class="panel-body">회의실 관리</div>
			</div>

			<div id="meetingRoomList" class="col-lg-12 ">
				<table class="table table-hover text-center">
					<thead>
						<tr>
							<th width="20%" style="text-align: center;">번호</th>
							<th width="50%" style="text-align: center;">회의실 이름</th>
							<th width="30%" style="text-align: center;">비고</th>
						</tr>
					</thead>
					<!--  작성자 : 박성준 -->
					<!--  JSTL이용한 미팅룸 리스트 출력 -->
					<tbody>
						<c:forEach items="${meetingRoomList}" var="meetingRoomList">
							<tr>
								<td width="20%">${meetingRoomList.CONF_ORDER}</td>
								<td width="50%">${meetingRoomList.CONF_NM}</td>
								<td width="30%">
									<Button type="button" class="btn btn-primary btn-sm" onClick="meetingRoomUpdateBtnClick('${meetingRoomList.CONF_NO}','${meetingRoomList.CONF_NM}', '${meetingRoomList.CONF_ORDER}')">수정</Button>
									<Button type="button" class="btn btn-danger btn-sm" onClick="meetingRoomDelete(${meetingRoomList.CONF_NO})">삭제</Button>
									
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<form action="" id="meetingRoomAddForm">
			<div class="col-lg-12" >
				<div class="col-lg-10">
					<div id="meetingRoomAddInputBox">
						<div class="col-lg-12 well">
							<div class="col-lg-4">
								<p>회의실 이름</p>
								<input type="text" class="form-control" id="addMeetingRoomName" name="addMeetingRoomName">
							</div>

							<div class="col-lg-4">
								<p>회의실 번호</p>
								<select class="form-control" id="addMeetingRoomNumber" name="addMeetingRoomNumber">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>

							<div class="col-lg-2">
								<p>　</p>
								<Button type="button" class="form-control btn btn-default"
									onclick="meetingRoomAddSubmit()">추가</Button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<Button type="button" class="pull-right btn btn-default"
						onclick="meetingRoomAddBtnClick()" id="meetingRoomAddBtn">회의실 추가</Button>
				</div>
			</div>
		</form>
		
		<br><br><br>
		<form action="" id="meetingRoomUpdateForm">
			<div class="col-lg-12" >
				<div class="col-lg-10">
					<div id="meetingRoomUpdateInputBox">
						<div class="col-lg-12 well">
							<div class="col-lg-4">
								<p>회의실 이름</p>
								<input type="text" class="form-control" id="updateMeetingRoomName" name="updateMeetingRoomName">
							</div>

							<div class="col-lg-4">
								<p>회의실 번호</p>
								<select class="form-control" id="updateMeetingRoomNumber" name="updateMeetingRoomNumber">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>

							<div class="col-lg-2">
								<p>　</p>
								<Button type="button" class="form-control btn btn-default"
									onclick="meetingRoomUpdateSubmit()">수정</Button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="updateMeetingRoomSeq" name="updateMeetingRoomSeq">
		</form>
	</div>
	

	<input type="hidden" id="addBtnClickState" value="false">
	
	

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">

$("#meetingRoomAddInputBox").hide();
$("#meetingRoomUpdateInputBox").hide();
var addBtnstate = $("#addBtnClickState").val();

// 추가버튼
	function meetingRoomAddBtnClick() {

		if (addBtnstate == false) {
			$("#meetingRoomAddInputBox").show();
			$("#addBtnClickState").val("true");
			$("#meetingRoomAddBtn").html("추가 취소");
			$("#meetingRoomUpdateInputBox").hide();
			addBtnstate=true;

		} else {
			$("#meetingRoomAddInputBox").hide();
			$("#addBtnClickState").val("false");
			$("#meetingRoomAddBtn").html("회의실 추가");
			addBtnstate=false;
		}
	}

// 수정버튼
	function meetingRoomUpdateBtnClick(seq,name,order) {
		$("#meetingRoomUpdateInputBox").show();
		$("#updateMeetingRoomSeq").val(seq);
		$("#updateMeetingRoomName").val(name);
		$("#updateMeetingRoomNumber").val(order);
		$("#meetingRoomAddInputBox").hide();
		addBtnstate=true;
		
		meetingRoomAddBtnClick();
	}


// 추가 완료
	function meetingRoomAddSubmit() {
		var check = $("#addMeetingRoomNumber").val();

		$.ajax({
			url : "/MeetingRoom/MeetingRoomAdd",
			dataType : "text",
			async : false,
			type : "POST",
			data : $('#meetingRoomAddForm').serializeArray(),
			success : function(data) {
				$("#meetingRoomList").load(window.location.href + " #meetingRoomList");
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "error:" + error);
			}
		});
	}
	
// 업데이트 완료
	function meetingRoomUpdateSubmit() {
		
		$.ajax({
			url : "/MeetingRoom/MeetingRoomUpdate",
			dataType : "text",
			async : false,
			type : "POST",
			data : $("#meetingRoomUpdateForm").serializeArray(),
			success : function(data) {
				$("#meetingRoomList").load(window.location.href + " #meetingRoomList");

			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "error:" + error);
			}
		});
	}
	
// 삭제 완료
	function meetingRoomDelete(seq){
		
		var confirmCheck = confirm("정말로 회의실을 삭제하시겠습니까?");
		if (confirmCheck == true) {
		
			$.ajax({
				url : "/MeetingRoom/MeetingRoomDelete",
				dataType : "text",
				async : false,
				type : "POST",
				data : { "meetingRoomSeq" : seq},
				success : function(data) {
					$("#meetingRoomList").load(window.location.href + " #meetingRoomList");
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "error:" + error);
				}
			});
		}
	}
	

</script>






