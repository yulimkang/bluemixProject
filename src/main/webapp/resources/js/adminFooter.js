var selectNumber;



function selectTab(seq){
	
	var tempId = seq.id;
	selectNumber = tempId.charAt(tempId.length-1);
	
	var reservationManagement=1;
	var reservationList = 2;
	var onBoarding = 3;
	var offBoarding = 4;
	var meetingRoomManagement = 5;
	var noShow = 6;
	var setting = 7;
	
	if(selectNumber==reservationManagement){
		location.href = "/main/main";
	}
	else if(selectNumber==reservationList){
		location.href = "/main/main";
	}
	else if(selectNumber==onBoarding){
		location.href = "/AdminBoarding/Onboarding";
	}
	else if(selectNumber==offBoarding){
		location.href = "/AdminBoarding/Onboarding";
	}
	else if(selectNumber==meetingRoomManagement){
		location.href = "/MeetingRoom/RoomManagementList";
	}
	else if(selectNumber==noShow){
		location.href = "/AdminManagement/NoShow";
	}
	else if(selectNumber==setting){
		location.href = "/AdminManagement/Setting";
	}
	else{
		
	}
}
