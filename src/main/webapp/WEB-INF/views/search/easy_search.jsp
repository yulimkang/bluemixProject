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
<html>
<body id="htmlBody">
	
	<div class="container">

		
		<div id="searchResultList" class="col-lg-12 table-responsive" style="margin-top:3%;">
			<table class="table table-hover text-center" style="text-align:left;text-size:90%">
				<thead>
					<tr>
						<th width="15%" style="text-align:left;">회의날짜</th>
						<th width="12%" style="text-align:left;">회의시간</th>
						<th width="15%" style="text-align:left;">회의실</th>
						<th width="25%" style="text-align:left;">회의제목</th>		
						<th width="10%" style="text-align:left;">예약자</th>
						<th width="13%" style="text-align:left;">전화번호</th>
						<th width="10%" style="text-align:left;">반복예약</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.searchResultListA}" var="searchResultList">
						<c:choose>
							<%-- 일반예약내역 --%>
							<c:when test="${searchResultList.	RSV_REPEAT_NO eq 0}">
							
								<tr>
									<td width="15%">${searchResultList.RSV_DATE} ${searchResultList.DAYOFTHEWEEK}</td>
									<td width="12%"><c:out value="${fn:substring(searchResultList.RSV_START_TIME,0,5)}"/> ~
									<c:out value="${fn:substring(searchResultList.RSV_END_TIME,0,5)}"/></td>
									<td width="15%">${searchResultList.CONF_NM}</td>
									
									<c:choose>
										<c:when test="${searchResultList.	RSV_CONFIRM_STATE ne 'N'}">
											<td width="25%"><a onclick="searchToCal(${searchResultList.RSV_NO});">${searchResultList.RSV_TITLE}</a></td>
										</c:when>
										<c:otherwise>
											<td width="25%"><a onclick="searchToCal(${searchResultList.RSV_NO});">(승인대기중)${searchResultList.RSV_TITLE}</a></td>
										</c:otherwise>
									</c:choose>
									
									<td width="10%">${searchResultList.RSV_MEM_NM}</td>
									<td width="13%">${searchResultList.RSV_MEM_PN}</td>
									<td width="10%"></td>
								</tr>
							
							</c:when>
							
							<%-- 반복예약내역 --%> 
							<c:otherwise>
								
								<tr>
									<td width="15%">${searchResultList.RSV_DATE} ${searchResultList.DAYOFTHEWEEK}</td>
									<td width="12%"><c:out value="${fn:substring(searchResultList.RSV_START_TIME,0,5)}"/> ~
									<c:out value="${fn:substring(searchResultList.RSV_END_TIME,0,5)}"/></td>
									<td width="15%">${searchResultList.CONF_NM}</td>
									
									<c:choose>
										<c:when test="${searchResultList.	RSV_CONFIRM_STATE ne 'N'}">
											<td width="25%"><a onclick="searchToCal(${searchResultList.RSV_NO});">${searchResultList.RSV_TITLE}</a></td>
										</c:when>
										<c:otherwise>
											<td width="25%"><a onclick="searchToCal(${searchResultList.RSV_NO});">(승인대기중)${searchResultList.RSV_TITLE}</a></td>
										</c:otherwise>
									</c:choose>
									
									<td width="10%">${searchResultList.RSV_MEM_NM}</td>
									<td width="13%">${searchResultList.RSV_MEM_PN}</td>
									<td width="10%"><button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#reservDetail" onClick="showRepeatDetail(${searchResultList.RSV_REPEAT_NO} )">상세보기</button></td>
								</tr>
							
							</c:otherwise>
						</c:choose>
					
					</c:forEach>
				</tbody>
			</table>
		</div>
			

	<%-- ################### 페이징 ################ --%>
	<%-- page, sort 이름 확인하기 --%>

	<%-- 첫 페이지로 이동 --%>
	<p align="center">
		<a href="/Search/EasySearch?page=1&easySelectSearchOpt=${requestScope.selectSearchOpt }&easyInputSearchCont=${requestScope.inputSearchCont }">첫 페이지</a>

		<%-- 이전 페이지 그룹 처리 --%>
		<c:choose>
			<c:when test="${requestScope.pageBean.previousPageGroup }">
				<%-- 이전 페이지 그룹이 있다면 isPreviousPageGroup() 호출 --%>
				<a href="/Search/EasySearch?page=${requestScope.pageBean.beginPage - 1 }&easySelectSearchOpt=${requestScope.selectSearchOpt }&easyInputSearchCont=${requestScope.inputSearchCont }">◀</a>
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
					<a href="/Search/EasySearch?page=${page}&easySelectSearchOpt=${requestScope.selectSearchOpt }&easyInputSearchCont=${requestScope.inputSearchCont }">${page }&nbsp;&nbsp;</a>
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
					href="/Search/EasySearch?page=${requestScope.pageBean.endPage + 1 }&easySelectSearchOpt=${requestScope.selectSearchOpt }&easyInputSearchCont=${requestScope.inputSearchCont }">▶</a>
				<%-- getEndPage()에서 리턴된 값 넣기 --%>
			</c:when>
			<c:otherwise>
			▶
		</c:otherwise>
		</c:choose>

		<!-- 마지막 페이지로 이동 -->
		<a href="/Search/EasySearch?page=${requestScope.pageBean.totalPage}&easySelectSearchOpt=${requestScope.selectSearchOpt }&easyInputSearchCont=${requestScope.inputSearchCont }">마지막
			페이지</a>
	</p>
	
	</div>
	
	<form action="/Reservation/SearchToCalendar" id="testForm">
		<input type="hidden" id="rsvNo" name="rsvNo">
	</form>
	
		
		
</body>
</html>

<script>

 /**
 * 작성자 : 최문정
 * 내용 : 결과 값 출력 이후에도 옵션과 검색 내용이 보이도록 함
 */
//  $(function() {
// 	 $("#selectSearchOpt").val("${selectSearchOptBack}");
// 	 $("#inputSearchCont").val("${inputSearchContBack}");
//  });

/**
 * 작성자 : 최문정
 * 내용 : 반복예약 상세내용 리스트를 모달로 띄움
 */
// function showRepeatDetail(repeatNo) {
	
// 	var table = document.getElementById('modalTable');
// 	var detailViewArray = new Array();
	
// 	$.ajax({
// 		url : "/Search/ShowRepeatDetail",
// 		dataType : "json",
// 		type : "POST",
// 		data : { "repeatNo" : repeatNo },
// 		success : function(data) {
// 			detailViewArray = data;

// 			for(var i = table.rows.length - 1; i > 0; i--)
// 	         {
// 	             table.deleteRow(i);
// 	         }
			
// 			//회의날짜, 회의 시간, 회의실, 회의제목, 예약자, 예약자 번호
// 			for(var i =0; i<data.length; i++){
			
// 				var startTime= detailViewArray[i].rsv_start_time;
// 				var endTime= detailViewArray[i].rsv_end_time;
				
// 				var st = startTime.substring(0, 5);
// 				var et = endTime.substring(0, 5);

// 				var row = table.insertRow();
// 				row.insertCell(0).innerHTML = detailViewArray[i].rsv_date;
// 				row.insertCell(1).innerHTML = st + "~" + et;
// 				row.insertCell(2).innerHTML = detailViewArray[i].conf_nm;
// 				row.insertCell(3).innerHTML = '<a onclick="searchToCal(' + detailViewArray[i].rsv_no + ');">' + detailViewArray[i].rsv_title + '</a>';
// 				row.insertCell(4).innerHTML = detailViewArray[i].rsv_mem_nm;
// 				row.insertCell(5).innerHTML = detailViewArray[i].rsv_mem_pn;
				
				
				
// 			}
// 		},
// 		error : function(request, status, error) {
// 			alert("code : "+request.status + "\n" + "error : " + error);
// 		}
		
// 	});

// }

/* 작성자 : 박세연 
 * 제목 클릭시 날짜 페이지로 이동
 */
function searchToCal(rsvNo){
	
	rsvNoInt = parseInt(rsvNo);

	$("#rsvNo").val(rsvNoInt);
	$("#testForm").submit();
	
}


</script>
