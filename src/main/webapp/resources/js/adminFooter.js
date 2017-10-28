var selectNumber;

function selectTab(seq){
	
	var tempId = seq.id;
	selectNumber = tempId.charAt(tempId.length-1);
	
	var reservationManagement=1;
	var reservationList = 2;
	var onBoarding = 3;
	var offBoarding = 4;
	var meetingRoomManagement = 5;
	var noShowManagement = 6;
	var noShowUser = 7
	var setting = 8;
	
	if(selectNumber==reservationManagement){
		location.href = "/AdminBoarding/ReservationHistory";
	}
	else if(selectNumber==reservationList){
		location.href = "/AdminReservCheckAndDelete/ReservHistory";
	}
	else if(selectNumber==onBoarding){
		location.href = "/AdminBoarding/Onboarding";
	}
	else if(selectNumber==offBoarding){
		location.href = "/AdminBoarding/Offboarding";
	}
	else if(selectNumber==meetingRoomManagement){
		location.href = "/MeetingRoom/RoomManagementList";
	}
	else if(selectNumber==noShowManagement){
		location.href = "/AdminManagement/NoShowManagement";
	}
	else if(selectNumber==noShowUser){
		location.href = "/AdminManagement/NoShowAllManage";
	}
	else if(selectNumber==setting){
		location.href = "/AdminManagement/Setting";
	}
	else{
		
	}
}
