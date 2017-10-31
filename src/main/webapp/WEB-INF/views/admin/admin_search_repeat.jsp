<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>


<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script>   

<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 검색</title>
</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
	
	<div class="container">
	    <div class="panel panel-default">
				<div class="panel-body">관리자 예약내역검색</div>
		</div>
		
		<br>
		
		<!--  예약 내역 검색 Form -->
		<div class="col-lg-12">
		  <form name="searchForm" id="searchForm" method="post" action="">
			<div class="col-lg-3">
				<select class="form-control" name="selectSearchOpt" id="selectSearchOpt">
					<option value="all">전체</option>
					<option value="title">회의제목</option>
					<option value="mem_nm">예약자</option>
					<option value="mem_pn">전화번호</option>
				</select>
			</div>
			<div class="col-lg-4">
				<input class="form-control" type="text" size="50" id="inputSearchCont" name="inputSearchCont" OnKeyDown="if(event.keyCode==13){repeatSearchFormSubmit();}"  />
			</div>
			<div class="col-lg-3">
				<div class="radio">
          			<label>
						<input type="radio" name="searchKind" value="general" >일반예약
						<input type="radio" name="searchKind" value="repeat" checked="checked">반복예약
					</label>
				</div>
			</div>
			<div class="col-lg-2">
				<button type="button" class="pull-right btn btn-primary" onclick="searchBtn()">검색</button>
			</div>

		<br><br><br><br><br>
		
		<!-- 일반예역 / 예약내역 선택 탭 -->
		<ul class="nav nav-tabs">
			  <li id="tab1" class="" onclick="searchFormSubmit()"><a href="#" data-toggle="tab" aria-expanded="false" >일반예약</a></li>
			  <li id="tab2" class="active" onclick="repeatSearchFormSubmit()" ><a href="#" data-toggle="tab" aria-expanded="false">반복예약</a></li>
		</ul> <br>
			

			<div class="panel panel-default">
				<div class="panel-body">관리자 반복예약 검색 결과</div>
			</div>
		
		<br>
		 <div class="col-lg-3">
			<select class="form-control"  name="sort" id="sort" onchange="repeatSearchFormSubmit()">
						<option value="new">최신순</option>
						<option value="old">오래된 순</option>
			</select>
		</div>
		
			</form>
		</div>
		 
		
		
		
		<br><br><br><br><br>
		<!-- 반복예약 결과 데이터 테이블 출력 -->
		<div id="repeatSearchResultList" class="col-lg-12 table-responsive" style="margin-top:3%;">
			<table class="table table-hover text-center" style="text-align:center;">
				<thead>
					<tr>
						<th width="10%" style="text-align: center;">반복시작일</th>
						<th width="20%" style="text-align: center;">회의시간</th>
						<th width="15%" style="text-align: center;">회의실</th>
						<th width="20%" style="text-align: center;">회의제목</th>
						<th width="10%" style="text-align: center;">예약자</th>
						<th width="15%" style="text-align: center;">전화번호</th>
						<th width="10%" style="text-align: center;">상세보기</th>
						
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.repeatSearchResultListA}" var="repeatSearchResultListA">
						<tr>
							<td width="10%">${repeatSearchResultListA.rsv_date}</td>
							<td width="20%"><c:out value="${fn:substring(repeatSearchResultListA.rsv_start_time,0,5)}"/> ~
							<c:out value="${fn:substring(repeatSearchResultListA.rsv_end_time,0,5)}"/></td>
							<td width="15%">${repeatSearchResultListA.conf_nm}</td>
							<td width="20%">${repeatSearchResultListA.rsv_title}</td>
							<td width="10%">${repeatSearchResultListA.rsv_mem_nm}</td>
							<td width="15%">${repeatSearchResultListA.rsv_mem_pn}</td>
							<td width="10%"><button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#reservDetail" onClick="showRepeatDetail(${repeatSearchResultListA.rsv_repeat_no} )">상세보기</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<!--  모달로 반복내역 상세보기 띄우기 -->
		<div class="modal fade" id="reservDetail">
			<div class="modal-dialog" style="width:80%">
				<div class="modal-content">

					<div class="modal-header">
						<h4 class="modal-title">상세내역</h4>
					</div>

					<div class="modal-body">
						<div class="table-responsive">
							<table class="table table-hover text-center"
								style="text-align: center;" id="modalTable">
								<thead>
									<tr>
										<!-- 회의 종료일, 반복 주기 넣어 놓기-->
										<th width="10%" style="text-align: center">회의날짜</th>
										<th width="30%" style="text-align: center">회의시간</th>
										<th width="10%" style="text-align: center">회의실</th>
										<th width="20%" style="text-align: center">회의제목</th>
										<th width="10%" style="text-align: center">예약자</th>
										<th width="20%" style="text-align: center">전화번호</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		

	<%-- ################### 페이징 ################ --%>
	<%-- page, sort 이름 확인하기 --%>

	<%-- 첫 페이지로 이동 --%>
	<p align="center">
		<a href="/AdminSearch/AdminRepeatSearchPage?page=1&sort=${requestScope.sort }&selectSearchOpt=${requestScope.selectSearchOpt }&inputSearchCont=${requestScope.inputSearchCont }">첫 페이지</a>

		<%-- 이전 페이지 그룹 처리 --%>
		<c:choose>
			<c:when test="${requestScope.pageBean.previousPageGroup }">
				<%-- 이전 페이지 그룹이 있다면 isPreviousPageGroup() 호출 --%>
				<a href="/AdminSearch/AdminRepeatSearchPage?page=${requestScope.pageBean.beginPage - 1 }&sort=${requestScope.sort }&selectSearchOpt=${requestScope.selectSearchOpt }&inputSearchCont=${requestScope.inputSearchCont }">◀</a>
			</c:when>
			<c:otherwise>
			◀
		</c:otherwise>
		</c:choose>

		<%-- 현재 page가 속한 page 그룹 내의 페이지들 링크 --%>
		<c:forEach begin="${requestScope.pageBean.beginPage }"
			end="${requestScope.pageBean.endPage }" var="page">
			<c:choose>
				<c:when test="${requestScope.pageBean.page != page }">
					<!-- 현재 페이지가 아니라면 -->
					<a href="/AdminSearch/AdminRepeatSearchPage?page=${page}&sort=${requestScope.sort }&selectSearchOpt=${requestScope.selectSearchOpt }&inputSearchCont=${requestScope.inputSearchCont }">${page }&nbsp;&nbsp;</a>
				</c:when>
				<c:otherwise>
				[${page }]  <%-- &nbsp;는 공백을 나타냄 --%>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!-- 다음페이지 그룹으로 이동. 만약에 다음페이지 그룹이 있으면 링크 처리 없으면 화살표만 나오도록 처리 -->
		<c:choose>
			<c:when test="${requestScope.pageBean.nextPageGroup }">
				<%-- isNextPageGroup() 호출 --%>
				<a
					href="/AdminSearch/AdminRepeatSearchPage?page=${requestScope.pageBean.endPage + 1 }&sort=${requestScope.sort }&selectSearchOpt=${requestScope.selectSearchOpt }&inputSearchCont=${requestScope.inputSearchCont }">▶</a>
				<%-- getEndPage()에서 리턴된 값 넣기 --%>
			</c:when>
			<c:otherwise>
			▶
		</c:otherwise>
		</c:choose>

		<!-- 마지막 페이지로 이동 -->
		<a href="/AdminSearch/AdminRepeatSearchPage?page=${requestScope.pageBean.totalPage}&sort=${requestScope.sort }&selectSearchOpt=${requestScope.selectSearchOpt }&inputSearchCont=${requestScope.inputSearchCont }">마지막
			페이지</a>
	</p>
	
	
	<form action="/Reservation/SearchToCalendar" id="testForm">
		<input type="hidden" id="rsvNo" name="rsvNo">
	</form>
	
	</div>
	
	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
	
		
		
</body>
</html>

<script>

/**
 * 작성자 : 최문정
 * 내용 : 자동완성 기능
 */
$(function(){
    $( "#inputSearchCont" ).autocomplete({
        source : function( request, response ) {
             $.ajax({
                    type: "POST",
                    url: "/AdminSearch/AdminSearchAutoComplete",
                    dataType: "JSON",
                    data: { "selectSearchOpt" : $("#selectSearchOpt").val(), "value" : request.term },
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
                    	//alert("ajax 에러가 발생하였습니다");
                    },
                    
               });
            },
            //자동완성 기능에서 클릭했을 때
            select:function(event, id) {
    			$("#inputSearchCont").val(id.item.label);
    			//반복내역 페이지에서 자동완성으로 검색된 결과를 누르면 반복내역 페이지에서 확인하도록 함
            	$("#searchForm").attr("action","/AdminSearch/AdminRepeatSearchPage"); 
        		$("#searchForm").submit();
            }
        
    });
});


 /**
 * 작성자 : 최문정
 * 내용 : 결과 값 출력 이후에도 옵션과 검색 내용이 보이도록 함
 */
 $(function() {
	 $("#selectSearchOpt").val("${selectSearchOptBack}");
	 $("#inputSearchCont").val("${inputSearchContBack}");
	 $("#sort").val("${repeatSortTypeBack}");
 });
 
 

/**
 * 작성자 : 최문정
 * 내용 : Form에서 입력받은 값 Contorller로 전송(일반예약 내역 검색)
 */
 function searchFormSubmit(){
 	var searchOpt = $("#selectSearchOpt").val();
 	var searchContent=$("#inputSearchCont").val();
 		
 	if(searchContent.length>0){		
 				
 		$("#searchForm").attr("action","/AdminSearch/AdminGeneralSearchPage");
 		$("#searchForm").submit();
 		
 	}
 	else{
 		alert("내용을 입력해 주세요.");
 	}
 	
 }

 
 /**
 * 작성자 : 최문정
 * 내용 : Form에서 입력받은 값 Contorller로 전송(반복예약 내역 검색)
 */
function repeatSearchFormSubmit(){
	var searchOpt = $("#selectSearchOpt").val();
	var searchContent=$("#inputSearchCont").val();
		
	if(searchContent.length>0){		
				
		$("#searchForm").attr("action","/AdminSearch/AdminRepeatSearchPage");
		$("#searchForm").submit();
		
	}
	else{
		alert("내용을 입력해 주세요.");
	}
	
}

 /**
 * 작성자 : 최문정
 * 내용 : 반복예약 상세내역 모달 띄우기
 */
function showRepeatDetail(repeatNo) {
	
	var table = document.getElementById('modalTable');

	
	var detailViewArray = new Array();
	
	$.ajax({
		url : "/AdminSearch/ShowAdminRepeatDetail",
		dataType : "json",
		type : "POST",
		data : { "repeatNo" : repeatNo },
		success : function(data) {
			detailViewArray = data;

			for(var i = table.rows.length - 1; i > 0; i--)
	         {
	             table.deleteRow(i);
	         }
			
			//회의날짜, 회의 시간, 회의실, 회의제목, 예약자, 예약자 번호
			for(var i =0; i<data.length; i++){
				//var row = table.insertRow(rowlen-1);
				var row = table.insertRow();
				
				
				var startTime= detailViewArray[i].rsv_start_time;
				var endTime= detailViewArray[i].rsv_end_time;
				
				var st = startTime.substring(0, 5);
				var et = endTime.substring(0, 5);
				
				row.insertCell(0).innerHTML =detailViewArray[i].rsv_date;
				row.insertCell(1).innerHTML = st + "~" + et;
				row.insertCell(2).innerHTML = detailViewArray[i].conf_nm;
				row.insertCell(3).innerHTML = '<a onclick="searchToCal(' + detailViewArray[i].rsv_no + ');">' + detailViewArray[i].rsv_title + '</a>';
				row.insertCell(4).innerHTML = detailViewArray[i].rsv_mem_nm;
				row.insertCell(5).innerHTML = detailViewArray[i].rsv_mem_pn;
				
			}
		},
		error : function(request, status, error) {
			alert("code : "+request.status + "\n" + "error : " + error);
		}
		
	});
	
	
}
 
/**
 * 작성자 : 최문정
 * 내용 : Form에서 입력받은 값 검색 결과 선택에 따라 Contorller로 전송
 */
function searchBtn() {
	var searchOpt = $("#selectSearchOpt").val();
	var searchContent=$("#inputSearchCont").val();
	var searchKind = $(":input:radio[name=searchKind]:checked").val();
	
	if(searchContent.length>0){
		
		if(searchKind == "general") {
			//일반예약 검색일 때
			$("#searchForm").attr("action","/Search/GeneralSearchPage");
			$("#searchForm").submit();
			
		}else if(searchKind == "repeat") {
			//반복예야 검색일 때
			$("#searchForm").attr("action","/Search/RepeatSearchPage");
			$("#searchForm").submit();
			
		}
		
	}
	else{
		alert("내용을 입력해 주세요.");
	}
}

 
 

/* 작성자 : 박세연 
 * 제목 클릭시 날짜 페이지로 이동
 */
function searchToCal(rsvNo){
	
	rsvNo = parseInt(rsvNo);
	
	$("#rsvNo").val(rsvNo);
	$("#testForm").submit();
	
}



</script>
