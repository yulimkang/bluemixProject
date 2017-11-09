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

//전체 페이지   ====> 남은 것 :  하이퍼링크(관리자일때와 아닐때), 상세내역 출력, 자동완성
var totalPage = 0;


function easySearchForGeneral(curPage) {
	
	//내용 입력하라고 뜨게 하기
//	var table = document.getElementById('easySearchTable');
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
//			$("#detailShow").empty();	
			
			
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
		
            var tableHeader = "<div id='wholeModal'>"+"<div class='table-responsive'>" +
            					   "<table id='easySearchTable' class='table table-hover text-center' style='text-align:left;text-size:90%'>" +
            					   "<thead><tr>" +
            					   "<th width='15%' style='text-align:left;'>회의날짜</th>" +
            					   "<th width='12%' style='text-align:left;'>회의시간</th>" +
            					   "<th width='15%' style='text-align:left;'>회의실</th>" +
            					   "<th width='25%' style='text-align:left;'>회의제목</th>" +
            					   "<th width='10%' style='text-align:left;'>예약자</th>" +
            					   "<th width='13%' style='text-align:left;'>전화번호</th>" +
            					   "<th colspan='2' style='text-align:left;'>반복예약</th>" +
            					   "</tr></thead><tbody>";
            
//            $("#result").append(tableHeader);
			
			//회의날짜, 회의 시간, 회의실, 회의제목, 예약자, 예약자 번호, 반복예약시 상세보기 버튼
			//for(var i =0; i<data.length; i++){
			for(var i =start_point; i<end_point; i++){
				
				var startTime= detailViewArray[i].RSV_START_TIME;
				var endTime= detailViewArray[i].RSV_END_TIME;
				
				var st = startTime.substring(0, 5);
				var et = endTime.substring(0, 5);
				
				if(detailViewArray[i].RSV_REPEAT_NO == 0) {
					
					tableHeader = tableHeader  + "<tr> <td style='width:15%'>" +detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK + "</td>"
													 +"<td style='width:12%'>"+ st + "~" + et +"</td>"
													 + "<td style='width:15%'>" + detailViewArray[i].CONF_NM +"</td>";
					
					if(detailViewArray[i].RSV_CONFIRM_STATE != 'N') {
						tableHeader = tableHeader + "<td style='width:25%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>" + detailViewArray[i].RSV_TITLE + "</a></td>"
					}else {
						tableHeader = tableHeader + "<td style='width:25%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>(승인대기중)" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}
					
					tableHeader = tableHeader + "<td style='width:10%'>" + detailViewArray[i].RSV_MEM_NM + "</td>"
													+ "<td style='width:13%'>" + detailViewArray[i].RSV_MEM_PN + "</td>"
													+ "<td style='width:10%'>" + " " + "</td>";

				}else {
					
					tableHeader = tableHeader + "<tr> <td style='width:15%'>" +detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK + "</td>"
													  + "<td style='width:12%'>"+ st + "~" + et +"</td>"
													  + "<td style='width:15%'>" + detailViewArray[i].CONF_NM +"</td>";
					
					if(detailViewArray[i].RSV_CONFIRM_STATE != 'N') {
						tableHeader = tableHeader +  "<td style='width:25%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}else {
						tableHeader = tableHeader +  "<td style='width:25%'>" + "<a onclick='searchToCal(" + detailViewArray[i].RSV_NO + ");'>(승인대기중)" +  detailViewArray[i].RSV_TITLE + "</a></td>";
					}
					
					tableHeader = tableHeader + "<td style='width:10%'>" + detailViewArray[i].RSV_MEM_NM + "</td>"
														+ "<td style='width:13%'>" + detailViewArray[i].RSV_MEM_PN + "</td>"
														+ "<td style='width:10%'>" + "<button type='button' class='btn btn-default btn-sm' onclick='showEasyRepeatDetail(" + detailViewArray[i].RSV_REPEAT_NO + ");'>상세보기</button>" + "</td></tr>";							
					
				}		
				
			}
			 tableHeader = tableHeader + "</tbody></table></div>";
			
            var pagingStart = "<br><br><div class='pagination' id='pagingDiv'>"
                +"<button class='btn btn-default btn-sm' id='pre' onclick='pre("+curPage+")'>&laquo;</button>";
			var pagingnum='';
			
			for(var j = startPage; j <= endPage; j++){		
			 pagingnum = pagingnum+"<button class='btn btn-default btn-sm' id='num"+j+"' onclick=move("+j+")>"+j+"</button>";
			}
			var pagingEnd = "<button class='btn btn-default btn-sm' id='next' onclick='next("+curPage+")'>&raquo;</button></div></div>";
			$("#result").append(tableHeader+pagingStart+pagingnum+pagingEnd);
			$("#num"+curPage).addClass('active');
			
			if(curPage == 1)  $("#pre").addClass('disabled');
			if(totalPage == 1) $("#next").addClass('disabled');
			
			if(curPage == totalPage) $("#next").addClass('disabled'); 

			 //$('#easySearchResult').modal('show');
			
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
	   
	   $("#pre").addClass("disabled");
	}

	function move(num){
	   $("#result").empty();
	   easySearchForGeneral(num);
	   
	   if(num ==1)
	      $("#pre").addClass("disabled");
	   else if(num == totalPage){
	      $("#pre").removeClass("disabled");
	      $("#next").addClass("disabled");
	   }
	   else
	      $("#pre").removeClass("disabled");
	   
	}
	
	function next(page){
	   page = page+1;
	   $("#result").empty();
	   
	   $("#pre").removeClass("disabled");

	   if(page == totalPage)
	      $("#next").addClass("disabled");
	   
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
	    			$("#inputSearchCont").val(id.item.label);
	           		$("#searchForm").attr("action","/Search/GeneralUserSearchPage");
	        		$("#searchForm").submit();
	            }
	        
	    });
	});
	
	
	function showEasyRepeatDetail(repeatNo) {
		
		$.ajax({
			url : "/Search/ShowRepeatDetail",
			dataType : "json",
			type : "POST",
			data : { "repeatNo" : repeatNo },
			success : function(data) {
				detailViewArray = data;
				
				$("#detailShow").empty();	

				
				var reservDetail =  "<div id='detailShow'><div class='table-responsive'>" +
				   "<table id='easySearchTable' class='table table-hover text-center' style='text-align:left;text-size:90%'>" + "<thead><tr>" +
				   "<th width='15%' style='text-align:left;'>회의날짜</th>" +
				   "<th width='12%' style='text-align:left;'>회의시간</th>" +
				   "<th width='15%' style='text-align:left;'>회의실</th>" +
				   "<th width='25%' style='text-align:left;'>회의제목</th>" +
				   "<th width='10%' style='text-align:left;'>예약자</th>" +
				   "<th width='13%' style='text-align:left;'>전화번호</th>" +
				   "</tr></thead><tbody>";
				
				//회의날짜, 회의 시간, 회의실, 회의제목, 예약자, 예약자 번호
				for(var i =0; i<data.length; i++){
					
					var startTime= detailViewArray[i].rsv_start_time;
					var endTime= detailViewArray[i].rsv_end_time;
					
					var st = startTime.substring(0, 5);
					var et = endTime.substring(0, 5);
	
					
					reservDetail = reservDetail + "<tr> <td style='width:15%'>" +detailViewArray[i].rsv_date + " "+ detailViewArray[i].dayoftheweek + "</td>"
					+"<td style='width:12%'>"+ st + "~" + et +"</td>"
					+ "<td style='width:15%'>" + detailViewArray[i].conf_nm +"</td>";

					if(detailViewArray[i].RSV_CONFIRM_STATE != 'N') {
						reservDetail = reservDetail +  "<td style='width:25%'>" + "<a onclick='searchToCal(" + detailViewArray[i].rsv_no + ");'>" +  detailViewArray[i].rsv_title + "</a></td>";
					}else {
						reservDetail = reservDetail +  "<td style='width:25%'>" + "<a onclick='searchToCal(" + detailViewArray[i].rsv_no + ");'>(승인대기중)" +  detailViewArray[i].rsv_title + "</a></td>";
					}
					
					reservDetail = reservDetail + "<td style='width:10%'>" + detailViewArray[i].rsv_mem_nm + "</td>"
														+ "<td style='width:13%'>" + detailViewArray[i].rsv_mem_pn + "</td>"
														+ "</tr>";
					
					
				}
				
				reservDetail = reservDetail + "</tbody></table></div>";
				var returnBtn = "<button type='button' onclick='returnToResult()'><span class='fa fa-undo' aria-hidden='true'></span></button></div>";
				
				$("#result").append(reservDetail+returnBtn);
				
				
			}
		})
	}
	
	function returnToResult() {
		$("#detailShow").empty();	
	}
	
	
				
				
