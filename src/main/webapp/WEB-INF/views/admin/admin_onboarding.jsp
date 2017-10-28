<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" 	type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script>

<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script>   

<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>
<html>
<head>

<title>on_boarding</title>

</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	
	<div class="container" id="onBoardingDiv">

		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">On-Boarding</div>
		</div>
		
		<div class="col-lg-12 col-xs-12">
			<form id="searchForm" name="searchForm" method="post" action="">
				<div class="col-lg-3 col-xs-4">
					<select class="form-control" id="searchOption" name="searchOption">
						<option value="all">검색조건</option>
						<option value="mem_name">이름</option>
						<option value="mem_pn">전화번호</option>
					</select>
				</div>
			
				<div class="col-lg-7 col-xs-6">
					<input type="text" OnKeyDown="if(event.keyCode==13){searchBtnClick();}" class="form-control" id="searchInputBox" name="searchInputBox">
				</div>
			
				<div class="col-lg-2 col-xs-2">
					<button type="button" onClick="searchBtnClick()" class="pull-right btn btn-primary">검색</button>
				</div>
				
			</form>
			<br><br><br>
			
		</div>			
		
	
			<div class="col-lg-12 table-responsive">
				<table class="table table-hover text-center" align="center">
					<thead>
						<tr>
							<td style="text-align: center;"><b>회원정보</b></td>
							<td style="text-align: center;"><b>전화번호</b></td>
							<td style="text-align: center;"><b>이메일</b></td>
							<td style="text-align: center;"><b>회원상태</b></td>
							<td style="text-align: center;"><b>노쇼카운팅</b></td>
							<td style="text-align: center;"><b>비고</b></td>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${BoardingListAll}" var="BoardingListAll">
							<tr>
								<td style="text-align: center;">${BoardingListAll.MEM_NAME}</td>
								<td style="text-align: center;">${BoardingListAll.MEM_PN}</td>
								<td style="text-align: center;">${BoardingListAll.MEM_EM}</td>
								<td style="text-align: center;">${BoardingListAll.MEM_STATE}</td>
								<td style="text-align: center;">${BoardingListAll.COUNT_WARN}</td>
								<td>
								<button type="button" data-toggle="modal" data-target="#myModal"
									id="popbutton" class="btn btn-primary btn-sm"
									onClick="updateBtnClick('${BoardingListAll.MEM_NO}','${BoardingListAll.MEM_NAME}','${BoardingListAll.MEM_PN}','${BoardingListAll.MEM_EM}')">수정</button>
								<div id="myModal" class="modal fade" role="dialog" ">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<div class="col-lg-3 col-md-12 col-sm-6 col-xs-12" >
								
											<form id="updateSubmitForm" action="">
													<div id="onBoardingUpadateInputBox" >
														<table class="table table-hover" align="center">
															<tbody>
																<tr>

																	<td class = "col-lg-2 col-md-2"><input type="text" class="form-control"
																		id="updateName" value="name" name="updateName"></td></tr><tr>
																	<td><input type="text" class="form-control"
																		id="updatePhone" value="phone" name="updatePhone"></td></tr><tr>
																	<td><input type="text" class="form-control"
																		id="updateEmail" value="email" name="updateEmail"></td></tr><tr>
																	<td><select class="form-control" id="updateState"
																		name="updateState">
																			<option>정상</option>
																			<option>차단</option>
																	</select></td></tr>
																	<td>
																		<button type="button" onClick="updateStateBtnClick()" class="pull-right btn btn-primary btn-sm">수정</button>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
								
								
								
								
								</td>
							</tr>
							<tr>
							
							</tr>
						
						</c:forEach>
					</tbody>
	
				</table>
			</div>
			
			<form id="updateSubmitForm" action="">
			<div id="modal" style="display:none" class="col-md-10 col-sm-8 col-xs-12 col-centered">
				<div id="onBoardingUpadateInputBox">
				<table class="table table-hover " align-item="center">
					<tbody>
							<tr>
								<td><input type="text" class="form-control" id="updateNo" name="updateNo"></td>
								<td><input type="text" class="form-control" id="updateName" value="name" name="updateName"></td>
								<td><input type="text" class="form-control" id="updatePhone" value="phone" name="updatePhone"></td>
								<td><input type="text" class="form-control" id="updateEmail" value="email" name="updateEmail"></td>
								<td><select class="form-control" id="updateState" name="updateState">
									<option>정상</option>	
									<option>차단</option>
									</select>
								</td>
								<td>
									<button type="button" onClick="updateStateBtnClick()">update</button>
								</td>
							</tr>
					</tbody>
				</table>
				</div>
			</div>
		</form>
			
			
			<br><br><br>


	<%-- ################### 페이징 ################ --%>
	<%-- page, sort 이름 확인하기 --%>

	<%-- 첫 페이지로 이동 --%>
	<p align="center">
		<a href="/AdminBoarding/Onboarding?page=1">첫 페이지</a>

		<%-- 이전 페이지 그룹 처리 --%>
		<c:choose>
			<c:when test="${requestScope.pageBean.previousPageGroup }">
				<%-- 이전 페이지 그룹이 있다면 isPreviousPageGroup() 호출 --%>
				<a href="/AdminBoarding/Onboarding?page=${requestScope.pageBean.beginPage - 1 }">◀</a>
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
					<a href="/AdminBoarding/Onboarding?page=${page}">${page }&nbsp;&nbsp;</a>
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
					href="/AdminBoarding/Onboarding?page=${requestScope.pageBean.endPage + 1 }">▶</a>
				<%-- getEndPage()에서 리턴된 값 넣기 --%>
			</c:when>
			<c:otherwise>
			▶
		</c:otherwise>
		</c:choose>

		<!-- 마지막 페이지로 이동 -->
		<a href="/AdminBoarding/Onboarding?page=${requestScope.pageBean.totalPage }">마지막	페이지</a>
	</p>



	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">
	window.onload = function() {
		$("#onBoardingUpadateInputBox").hide();
	}

	function updateBtnClick(number, name, phone, email) {
		document.getElementById("updateNo").value = number;
		document.getElementById("updateName").value = name;
		document.getElementById("updatePhone").value = phone;
		document.getElementById("updateEmail").value = email;

		$("#onBoardingUpadateInputBox").show();
		//$(#boardingList).prepend($(#updateSubmitForm)).fadeIn('slow');

		//css('visibility','visible');

	}

	function updateStateBtnClick() {

		$.ajax({
			url : "/AdminBoarding/OnBoardingUpdate",
			dataType : "text",
			type : "POST",
			data : $("#updateSubmitForm").serializeArray(),
			success : function() {
				location.reload();
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "error:" + error);
			}
		});
	}

	function searchBtnClick() {

		var searchForm = $("#searchInputBox").val();
		var searchOpt = $("#searchOption").val();

		if (searchForm.length > 0 && searchOpt != "all") {
			$("#searchForm").attr("action", "/AdminBoarding/OnSearching")
			$("#searchForm").submit();

		} else {
			alert("검색조건과 내용을 확인하세요")
		}

	}
</script>






