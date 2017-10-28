function main(){
	location.href="/";
}
	
function admin(){
	$.ajax({
		url : "/Common/SessionCheck",
		dataType : "text",
		type : "POST",
		success : function(data) {
			if(data=="SUCCESS"){
				location.href="/AdminBoarding/ReservationHistory";
			}
			else{
				location.href="/AdminLogin/LoginPage";
			}
		},
		error : function(request, status, error) {
			alert("code:" + request.status + "\n" + "error:" + error);
		}
	});
}

function notice(){
	location.href= "/notice/";
}

function searchPage() {
	location.href="/Search/SearchPage";
}

function logout(){
	location.href="/Common/Logout"
}

function adminSearchPage() {
	location.href="/AdminSearch/AdminSearchPage";
}

function repeatReservation(){
	location.href= "/RepeatReservation/";
}