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


//자동완성 넣기!!!!!!!!!!!!!!

/**
 * 작성자 : 최문정
 * 내용 : 반복예약 상세내용 리스트를 모달로 띄움
 */

//전체 페이지
var totalPage = 0;

function easySearchCheck() {
	
	var eSearchCont = $("#easyInputSearchCont").val();
	
	if(eSearchCont.length > 0) {
		$("#modalBtn").attr("data-toggle", "modal");
		$("#modalBtn").attr("data-target", "#easySearchResult");
		easySearchForGeneral(1);
		
	}else {
		alert("내용을 입력해 주세요!");
	}
}


function easySearchForGeneral(curPage) {
	var eSearchCont = $("#easyInputSearchCont").val();
	var eSeachOpt = $("#easySelectSearchOpt").val();
	
	var detailViewArray = new Array();
	

	$.ajax({
		url : "/Search/EasySearch",
		dataType : "json",
		type : "POST",
		data : { "easyInputSearchCont" : eSearchCont, "easySelectSearchOpt" : eSeachOpt },
		success : function(data) {
			detailViewArray = data;

			$("#result").empty();
			
			var appendHtml = document.getElementById('result');
            var results_length = data.length;
            
            var countPage = 5; //한 화면에 출력될 페이지 수
            
            totalPage = Math.ceil(results_length / 10.0);
            
            var startPage = ((Math.ceil(curPage/countPage))-1)*countPage+1;
          
            
            var endPage;
            if(totalPage <= countPage) endPage = totalPage;
            else endPage = startPage + countPage -1;
            
            if (endPage > totalPage) endPage = totalPage;
            
            var start_point = (curPage-1)*10;
            var end_point;
            if(start_point+10 > results_length) end_point = results_length;
            else end_point = start_point +10;
		
            var tableHeader ="<div class='table-responsive'>" + "<table id='easySearchTable' class='table table-hover text-center' style='text-align:left;text-size:90%'>" +
            					   "<thead><tr>" +
            					   "<th width='15%' style='text-align:left;'>회의날짜</th>" +
            					   "<th width='12%' style='text-align:left;'>회의시간</th>" +
            					   "<th width='13%' style='text-align:left;'>회의실</th>" +
            					   "<th width='30%' style='text-align:left;'>회의제목</th>" +
            					   "<th width='10%' style='text-align:left;'>예약자</th>" +
            					   "<th width='13%' style='text-align:left;'>전화번호</th>" +
            					   "<th width='7%' style='text-align:left;'>반복예약</th>" +
            					   "</tr></thead><tbody>";
			
			//회의날짜, 회의 시간, 회의실, 회의제목, 예약자, 예약자 번호, 반복예약시 상세보기 버튼
			for(var i =start_point; i<end_point; i++){
				
				var startTime= detailViewArray[i].RSV_START_TIME;
				var endTime= detailViewArray[i].RSV_END_TIME;
				
				var st = startTime.substring(0, 5);
				var et = endTime.substring(0, 5);
				
				if(detailViewArray[i].RSV_REPEAT_NO == 0) {
					
					tableHeader = tableHeader  + "<tr> <td style='width:15%'>" +detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK + "</td>"
													 +"<td style='width:12%'>"+ st + "~" + et +"</td>"
													 + "<td style='width:13%'>" + detailViewArray[i].CONF_NM +"</td>";
					
					if(detailViewArray[i].RSV_CONFIRM_STATE != 'N') {
						tableHeader = tableHeader + "<td style='width:30%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>" + detailViewArray[i].RSV_TITLE + "</a></td>"
					}else {
						tableHeader = tableHeader + "<td style='width:30%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>(승인대기중)" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}
					
					tableHeader = tableHeader + "<td style='width:10%'>" + detailViewArray[i].RSV_MEM_NM + "</td>"
													+ "<td style='width:13%'>" + detailViewArray[i].RSV_MEM_PN + "</td>"
													+ "<td style='width:7%'>" + " " + "</td>";

				}else {
					
					tableHeader = tableHeader + "<tr style='height:39px;'> <td style='width:15%'>" +detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK + "</td>"
													  + "<td style='width:12%'>"+ st + "~" + et +"</td>"
													  + "<td style='width:13%'>" + detailViewArray[i].CONF_NM +"</td>";
					
					if(detailViewArray[i].RSV_CONFIRM_STATE != 'N') {
						tableHeader = tableHeader +  "<td style='width:30%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}else {
						tableHeader = tableHeader +  "<td style='width:30%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>(승인대기중)" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}
					
					tableHeader = tableHeader + "<td style='width:10%'>" + detailViewArray[i].RSV_MEM_NM + "</td>"
														+ "<td style='width:13%'>" + detailViewArray[i].RSV_MEM_PN + "</td>"
														+ "<td style='width:7%;height:39px;padding-top:2px;padding-bottom:0px;'>"
														+ "<button type='button' class='btn btn-default btn-sm' onclick='showEasyRepeatDetail("
														+ detailViewArray[i].RSV_REPEAT_NO + ");'>상세보기</button>" + "</td></tr>";			
				}		
				
			}
			 tableHeader = tableHeader + "</tbody></table></div>";
			
            var pagingStart = "<br><br><div class='pagination pagination-lg' id='pagingDiv' style='margin:0 auto;'>"
                +"<button class='btn btn-default btn-sm' id='pre' onclick='pre("+curPage+")'>&laquo;</button>";
			var pagingnum='';
			
			for(var j = startPage; j <= endPage; j++){		
			 pagingnum = pagingnum+"<button class='btn btn-default btn-sm' id='num"+j+"' onclick=move("+j+")>"+j+"</button>";
			}
			var pagingEnd = "<button class='btn btn-default btn-sm' id='next' onclick='next("+curPage+")'>&raquo;</button></div><br><br>";
			
			var wholeHtml = tableHeader+pagingStart+pagingnum+pagingEnd;
			
			appendHtml.innerHTML = wholeHtml;
			

			$("#num"+curPage).addClass('active');
			
			if(curPage == 1)  $("#pre").remove();
			if(totalPage == 1) $("#next").remove();
			
			if(curPage == totalPage) $("#next").remove();				

		},
		error : function(request, status, error) {
			alert("code : "+request.status + "\n" + "error : " + error);
		}
		
	});
}

	function pre(page){
		page = page-1;
	   $("#result").empty();
	   
	   easySearchForGeneral(page);
	}

	function move(num){
	   $("#result").empty();
	   easySearchForGeneral(num);	   
	}
	
	function next(page){
	   page = page+1;
	   $("#result").empty();
	   
	   easySearchForGeneral(page);
	}
	
	/* 작성자 : 박세연 
	 * 제목 클릭시 날짜 페이지로 이동
	 */
	function searchToCal(rsvNo){
		
		rsvNo = parseInt(rsvNo);
		
		$("#rsvNo").val(rsvNo);
		$("#testForm").submit();
		
	}
	/**
	 * 작성자 : 최문정
	 * 내용 : 자동완성기능
	 */
	$(function(){
	    $( "#easyInputSearchCont" ).autocomplete({
	        source : function( request, response ) {
	             $.ajax({
	                    type: "POST",
	                    url: "/Search/EasySearchAutoComplete",
	                    dataType: "JSON",
	                    data: { "selectSearchOpt" : $("#easySelectSearchOpt").val(), "value" : request.term },
	                    success: function(data) {
	                        //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
	                        response(
	                           $.map(data, function(item) {
	                                return {
	                                    label: item.data,
	                                    value: item.data
	                              }
	                          })
	                        );                    },
	                    error : function(data) {

	                    },
	                    
	               });
	            },
	            //자동완성 기능에서 클릭했을 때, 일반예약을 기본으로 함
	            select:function(event, id) {
	            }
	        
	    });
	});
	
	
	function showEasyRepeatDetail(repeatNo) {
		
		$.ajax({
			url : "/Search/ShowEasySearchRepeatDetail",
			dataType : "json",
			type : "POST",
			data : { "repeatNo" : repeatNo },
			success : function(data) {
				detailViewArray = data;
				
				$("#detailShow").remove();	
				
				var appendDetailHtml = document.getElementById('detail');
				
				var reservDetail =  "<div id='detailShow'><div class='table-responsive'>" +
				   "<table id='easySearchTable' class='table table-hover text-center' style='text-align:left;text-size:90%'>" + "<thead><tr>" +
				   "<th width='15%' style='text-align:left;'>회의날짜</th>" +
				   "<th width='15%' style='text-align:left;'>회의시간</th>" +
				   "<th width='15%' style='text-align:left;'>회의실</th>" +
				   "<th width='30%' style='text-align:left;'>회의제목</th>" +
				   "<th width='10%' style='text-align:left;'>예약자</th>" +
				   "<th width='15%' style='text-align:left;'>전화번호</th>" +
				   "</tr></thead><tbody>";
				
				//회의날짜, 회의 시간, 회의실, 회의제목, 예약자, 예약자 번호
				for(var i =0; i<data.length; i++){
					
					var startTime= detailViewArray[i].RSV_START_TIME;
					var endTime= detailViewArray[i].RSV_END_TIME;
					
					var st = startTime.substring(0, 5);
					var et = endTime.substring(0, 5);
	
					
					reservDetail = reservDetail + "<tr> <td style='width:15%'>" +detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK + "</td>"
					+"<td style='width:15%'>"+ st + "~" + et +"</td>"
					+ "<td style='width:15%'>" + detailViewArray[i].CONF_NM +"</td>";

					if(detailViewArray[i].RSV_CONFIRM_STATE != 'N') {
						reservDetail = reservDetail +  "<td style='width:30%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}else {
						reservDetail = reservDetail +  "<td style='width:30%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>(승인대기중)" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}
					
					reservDetail = reservDetail + "<td style='width:10%'>" + detailViewArray[i].RSV_MEM_NM + "</td>"
														+ "<td style='width:15%'>" + detailViewArray[i].RSV_MEM_PN + "</td>"
														+ "</tr>";
					
					
				}
				
				reservDetail = reservDetail + "</tbody></table></div>";
				var returnBtn = "<button type='button' class='btn btn-default btn-sm btn-right' onclick='returnToResult()'><span class='fa fa-angle-up' aria-hidden='true'></span>접기</button></div>";
				
				var wholeDetail = reservDetail+returnBtn;				
				appendDetailHtml.innerHTML = wholeDetail;
				
			}
		})
	}
	
	function returnToResult() {
		$("#detailShow").remove();
	}
	
	
				
				
