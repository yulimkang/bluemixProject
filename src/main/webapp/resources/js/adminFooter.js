var selectNumber;

function selectTab(seq){
	
	var reservationManagement=1;
	var reservationList = 2;
	var onBoarding = 3;
	var offBoarding = 4;
	var meetingRoomManagement = 5;
	var setting = 6;
	
	
	var tempId = seq.id;
	selectNumber = tempId.charAt(tempId.length-1);
	
	if(selectNumber==reservationManagement){
		location.href = "/main/main";
		temp();
	}
	else if(selectNumber==reservationList){
		location.href = "/main/main";
	}
	else if(selectNumber==onBoarding){
		location.href = "/main/main";
	}
	else if(selectNumber==offBoarding){
		location.href = "/main/main";
	}
	else if(selectNumber==meetingRoomManagement){
		location.href = "/MeetingRoom/RoomManagementList";
	}
	else if(selectNumber==setting){
		location.href = "/AdminManagement/Setting";
	}
	else{
		
	}
}

function temp(){
	for(var i=1; i<=6; i++){
		if(i!=selectNumber){
			$("#tab"+i).attr("class","");
		}
		else{
			$("#tab"+i).attr("class","active");
		}
	}
}