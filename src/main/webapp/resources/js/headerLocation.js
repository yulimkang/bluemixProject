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



//function easySearchForGeneral(){
//	var searchOpt = $("#easySelectSearchOpt").val();
//	var searchContent=$("#easyInputSearchCont").val();
//		
//	if(searchContent.length>0){		
//		$("#easySearchForm").attr("action","/Search/EasySearch");
//		$("#easySearchForm").submit();
//		
//	}
//	else{
//		alert("내용을 입력해 주세요.");
//	}
//	
//}

function easySearchForAdmin(){
	var searchOpt = $("#easySelectSearchOpt").val();
	var searchContent=$("#easyInputSearchCont").val();
		
	if(searchContent.length>0){		
		$("#easySearchForm").attr("action","/AdminSearchPage/AdminEasySearch");
		$("#easySearchForm").submit();
		
	}
	else{
		alert("내용을 입력해 주세요.");
	}
	
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
		

//			for(var i = table.rows.length - 1; i > 0; i--)
//	         {
//	             table.deleteRow(i);
//	         }
            
//			for(var i = end_point ; i > start_point ; i--)
//	         {
//	             table.deleteRow(i);
//	         }
			
//			alert("테이블 Row Delete 확인");
            
            var tableHeader = "<div class='table-responsive'>" +
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
					tableHeader=  tableHeader + "<tr class='table table-hover text-left' style='width:auto'> <td style='width:15%'>" +detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK + "</td>"
											+"<td style='width:12%'>"+ st + "~" + et +"</td>"
											+ "<td style='width:15%'>" + detailViewArray[i].CONF_NM +"</td>"
											+ "<td style='width:25%'>" + "<a onclick='searchToCal('" + detailViewArray[i].RSV_NO + ");'>" +  detailViewArray[i].RSV_TITLE + "</a></td>"
											+ "<td style='width:10%'>" + detailViewArray[i].RSV_MEM_NM + "</td>"
											+ "<td style='width:13%'>" + detailViewArray[i].RSV_MEM_PN + "</td>"
											+ "<td>" + " " + "</td>";
//					var row = table.insertRow();
//					row.insertCell(0).innerHTML = detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK;
//					row.insertCell(1).innerHTML = st + "~" + et;
//					row.insertCell(2).innerHTML = detailViewArray[i].CONF_NM;
//					row.insertCell(3).innerHTML = '<a onclick="searchToCal(' + detailViewArray[i].RSV_NO + ');">' + detailViewArray[i].RSV_TITLE + '</a>';
//					row.insertCell(4).innerHTML = detailViewArray[i].RSV_MEM_NM;
//					row.insertCell(5).innerHTML = detailViewArray[i].RSV_MEM_PN;
//					row.insertCell(6).innerHTML = "";
				
//				$("#result").append(generalResrvStr);
					
				}else {
					
					tableHeader = tableHeader + "<tr class='table table-hover text-left' style='width:auto'> <td style='width:15%'>" +detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK + "</td>"
											+"<td style='width:12%'>"+ st + "~" + et +"</td>"
											+ "<td style='width:15%'>" + detailViewArray[i].CONF_NM +"</td>"
											+ "<td style='width:25%'>" + "<a onclick='searchToCal('" + detailViewArray[i].RSV_NO + ");'>" +  detailViewArray[i].RSV_TITLE + "</a></td>"
											+ "<td style='width:10%'>" + detailViewArray[i].RSV_MEM_NM + "</td>"
											+ "<td style='width:13%'>" + detailViewArray[i].RSV_MEM_PN + "</td>"
											+ "<td>" + "<button type='button' class='btn btn-default btn-sm'>상세보기</button>" + "</td></tr>";
					
//					var row = table.insertRow();
//					row.insertCell(0).innerHTML = detailViewArray[i].RSV_DATE + " "+ detailViewArray[i].DAYOFTHEWEEK;
//					row.insertCell(1).innerHTML = st + "~" + et;
//					row.insertCell(2).innerHTML = detailViewArray[i].CONF_NM;
//					row.insertCell(3).innerHTML = '<a onclick="searchToCal(' + detailViewArray[i].RSV_NO + ');">' + detailViewArray[i].RSV_TITLE + '</a>';
//					row.insertCell(4).innerHTML = detailViewArray[i].RSV_MEM_NM;
//					row.insertCell(5).innerHTML = detailViewArray[i].RSV_MEM_PN;
//					row.insertCell(6).innerHTML = '<button type="button" class="btn btn-default btn-sm">상세보기</button>';
					
//					$("#result").append(repeatResrvStr);
					
				}		
				
			}
			 tableHeader = tableHeader + "</tbody></table></div>";
			 $("#result").append(tableHeader);
			
            var pagingStart = "<br><br><div class='pagination' id='pagingDiv'>"
                +"<button class='btn btn-default btn-sm' id='pre' onclick='pre("+curPage+")'>&laquo;</button>";
			var pagingnum='';
			
			for(var j = startPage; j <= endPage; j++){		
			 pagingnum = pagingnum+"<button class='btn btn-default btn-sm' id='num"+j+"' onclick=move("+j+")>"+j+"</button>";
			}
			var pagingEnd = "<button class='btn btn-default btn-sm' id='next' onclick='next("+curPage+")'>&raquo;</button></div>";
			$("#result").append(pagingStart+pagingnum+pagingEnd);
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




//수연-header_search
//function header_search(curPage){
//     var $layer = $('.js-layer');
//     $layer.removeClass('hidden');
//     
//     var select_option = $('#search_option option:selected').val();
//     $('#search_select').val(select_option);
//     var select_context = $('#search_context').val();
//     //alert("뭘 선택함?"+select_option+"내용은?"+select_context);
//   
//   $.ajax({
//      async : false,
//      type: "post",
//      url : "SearchRsv.do",
//      dataType : 'json',
//      data: {
//        search_select : select_option,
//         search_context : select_context
//         },
//         success : function(data) {
//          $layer.addClass('hidden');
//          $('#result').empty();
//          
//            var results_length = data.results.length;
//            
//            var countPage = 5; //한 화면에 출력될 페이지 수
//            
//            totalPage = Math.ceil(results_length / 10.0);
//           // alert("총페이지 수!!!"+totalPage);
//            
//            var startPage;
//       
//          startPage = ((Math.ceil(curPage/countPage))-1)*countPage+1;
//          
//            
//            var endPage;
//            if(totalPage <= countPage)
//               endPage = totalPage;
//            else
//               endPage = startPage + countPage -1;
//            
//            if (endPage > totalPage)
//                 endPage = totalPage;
//            
//            var start_point = (curPage-1)*10;
//            var end_point;
//            if(start_point+10 > results_length)
//               end_point = results_length;
//            else
//               end_point = start_point +10;
//            
//            if(results_length == 0)
//               $('#result').append("<div>검색 결과가 없습니다.</div>");
//            
//            else{
//               
//            for(var i = start_point ; i < end_point ; i++){
//               
//               var select_date = data.results[i].date;
//               var start_time = data.results[i].start.substring(0,2)+":"+data.results[i].start.substring(2,4);
//               var end_time = data.results[i].end.substring(0,2)+":"+data.results[i].end.substring(2,4);
//               var phone = data.results[i].phone.substring(0,3)+"-"+data.results[i].phone.substring(3,7)+"-"+data.results[i].phone.substring(7,11)
//               $('#result').append("<div class='row' id='search_list' style='cursor:pointer;' onClick='search_link("+Number(data.results[i].seq)+");'>"+
//                                       "<div id='body_title' class='col-xs-2'>"+data.results[i].title+"</div>"+
//                                       "<div id='body_date' class='col-xs-2'>"+data.results[i].date+"</div>"+
//                                       "<div id='body_time' class='col-xs-2'>"+start_time+" ~ "+end_time+"</div>"+
//                                       "<div id='body_conf' class='col-xs-2'>"+data.results[i].confer_nm+"</div>"+
//                                       "<div id='body_name' class='col-xs-2'>"+data.results[i].name+"</div>"+
//                                       "<div id='body_phone' class='col-xs-2'>"+phone+"</div>"+
//                                    "</div>");
//                           }
//            
//            var pagingStart = "<br><div class='pagingGroup' align='center'>"
//                              +"<button class='btn btn-default btn-sm' id='pre' onclick='pre("+curPage+")'>&#60;</button>";
//            var pagingnum='';
//            for(var j = startPage; j <= endPage; j++){
//               pagingnum = pagingnum+"<button class='btn btn-default btn-sm' id='num"+j+"' onclick=move("+j+")>"+j+"</button>";
//            }
//            var pagingEnd = "<button class='btn btn-default btn-sm' id='next' onclick='next("+curPage+")'>&#62;</button></div>";
//            
//            $('#result').append(pagingStart+pagingnum+pagingEnd);
//            $('#num'+curPage).addClass('active');
//            
//            if(curPage == 1)
//               $("pre").addClass('disabled');
//            if(totalPage == 1)
//               $("#next").addClass('disabled');
//            }
//          $('#modal_search').modal('show');
//
//           },   
//         error : function() {
//           alert("에러~ header search");
//            console.log("error");
//         }
//      });
//}