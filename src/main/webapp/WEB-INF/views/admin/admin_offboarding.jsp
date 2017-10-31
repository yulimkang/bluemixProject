<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css"
	href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css"
	href="/resources/bootstrap/variables.less" />

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
<title>관리자 Off-Boarding 관리</title>

</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>


	<div class="container" id="onBoardingDiv">

		<jsp:include page="admin_footer.jsp"></jsp:include>

		<div class="panel panel-default">
			<div class="panel-body">Off-Boarding</div>
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
						<td style="text-align: center;"><b>이름</b></td>
						<td style="text-align: center;"><b>전화번호</b></td>
						<td style="text-align: center;"><b>이메일</b></td>
						<td style="text-align: center;"><b>회원상태</b></td>
						<td style="text-align: center;"><b>노쇼카운팅</b></td>
						<td style="text-align: center;"><b>비고</b></td>
						
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${offBoardingListAll}" var="offBoardingListAll">
						<tr>
							<td style="text-align: center;">${offBoardingListAll.MEM_NAME}</td>
							<td style="text-align: center;">${offBoardingListAll.MEM_PN}</td>
							<td style="text-align: center;">${offBoardingListAll.MEM_EM}</td>

							<c:choose>
								<c:when test="${offBoardingListAll.MEM_STATE eq 'NOSHOWBAN'}">
									<td style="text-align: center;">~ ${offBoardingListAll.MEM_BANDAY} 차단</td>

								</c:when>

								<c:otherwise>
									<td style="text-align: center;">${offBoardingListAll.MEM_STATE}</td>

								</c:otherwise>

							</c:choose>

							<td style="text-align: center;">${offBoardingListAll.COUNT_WARN}</td>
							<td>
								<button type="button" data-toggle="modal" data-target="#myModal"
									id="popbutton" class="btn btn-primary btn-sm"
									onClick="updateBtnClick('${offBoardingListAll.MEM_NO}','${offBoardingListAll.MEM_NAME}','${offBoardingListAll.MEM_PN}','${offBoardingListAll.MEM_EM}')">수정</button>

								<div id="myModal" class="modal fade" role="dialog">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<div class="col-lg-3 col-md-12 col-sm-6 col-xs-12">
											
												<form id="updateSubmitForm" action="">
													<div id="onBoardingUpadateInputBox">
														<table class="table table-hover" align="center">
															<tbody>
																<tr>
																	<td style='display:none'><input type="text" class="form-control"
																		id="updateNo" name="updateNo"></td>
																</tr>
																<tr>
																	<td><input type="text" class="form-control"
																		id="updateName" value="name" name="updateName"></td>
																</tr>
																<tr>
																	<td><input type="text" class="form-control"
																		id="updatePhone" value="phone" name="updatePhone"></td>
																		</tr><tr>
																	<td><input type="text" class="form-control"
																		id="updateEmail" value="email" name="updateEmail"></td></tr><tr>
																	<td><select class="form-control" id="updateState"
																		name="updateState">
																			<option>정상</option>
																			<option selected="selected">차단</option>
																	</select></td></tr><tr>
																	<td>
																		<button type="button" onClick="updateStateBtnClick()" class="pull-right btn btn-primary btn-sm">수정</button>
																	</td></tr>
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

					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	
	
	
	
	
	
	

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">
	
	$('#')
	
	//수정 인풋 박스 숨기기
	window.onload= function(){
		$("#onBoardingUpadateInputBox").hide();
	}

	//디비값 가져와서 수정시에 디폴트값 주기, 클릭시 hide->show
	function updateBtnClick(number,name,phone,email)
	{
		document.getElementById("updateNo").value  = number;
		document.getElementById("updateName").value  = name;
		document.getElementById("updatePhone").value  = phone;
		document.getElementById("updateEmail").value  = email;

		$("#onBoardingUpadateInputBox").show();
	

		//document.body.scrollTop = document.body.scrollHeight;

		
	}
	//입력값 받아 업데이트
	function updateStateBtnClick(){
		
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
	//검색조건 받아,해당키워드로 검색
	function searchBtnClick(){
		
		var searchForm=$("#searchInputBox").val();
		var searchOpt=$("#searchOption").val();
		
		if(searchForm.length>0&&searchOpt!="all"){
			$("#searchForm").attr("action","/AdminBoarding/OffSearching")
			$("#searchForm").submit();
			
		}
		else{
			alert("검색조건과 내용을 확인하세요")
		}
	}


</script>






