<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>

<link rel="stylesheet"	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" 	type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script>  

<script>
	$(document).ready(function(){
		$("#noShowTab").attr("class","active");
	});
</script>


<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>

<html>
<head>
<title>관리자 setting</title>

</head>
<body>
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	<div class="container" id="ddddd">
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">NO SHOW 사용자 관리</div>
		</div>
		
		<div id="noShowMemberList" class="col-lg-12">
				<table class="table table-striped table-hover text-center">
					<thead>
						<tr>
							<th width="5%" style="text-align: center;"> - </th>
							<th width="15%" style="text-align: center;">사용자 이름</th>
							<th width="20%" style="text-align: center;">이메일</th>
							<th width="20%" style="text-align: center;">핸드폰 번호</th>
							<th width="10%" style="text-align: center;">No-Show</th>
							<th width="30%" style="text-align: center;">비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${noShowUserList}" var="noShowUserList" varStatus="count">
							<tr>
							
							<td width="5%" style="text-align: center;"> ${count.count}</td>
							<td width="15%" style="text-align: center;">${noShowUserList.MEM_NAME}</td>
							<td width="20%" style="text-align: center;">${noShowUserList.MEM_EM}</td>
							<td width="20%" style="text-align: center;">${noShowUserList.MEM_PN}</td>
							<td width="10%" style="text-align: center;">
								<i class="fa fa-arrow-up" id="plus" style="color:red" aria-hidden="true" onClick="noShowValueSetting(this,${noShowUserList.MEM_NO})"></i>
								${noShowUserList.COUNT_WARN}번
								<i class="fa fa-arrow-down" id="minus" style="color:navy" aria-hidden="true" onClick="noShowValueSetting(this,${noShowUserList.MEM_NO})"></i>
							</td>
							<td width="30%" style="text-align: center;">
									<Button type="button" class="btn btn-primary btn-sm"  id= "init" onClick="noShowValueSetting(this,${noShowUserList.MEM_NO})" value="init">초기화</Button>
									<Button type="button" class="btn btn-danger btn-sm"  onClick="memberBlock(${noShowUserList.MEM_NO})">차단</Button>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		
		<button type="button" onclick="check()">aa</button>
		</div>
	</div>
	

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">

function check(){
	$("#tab1").attr("class","active");
}

function noShowValueSetting(btn,seq){
	
	var noShowBtnType = btn.id;
	var noShowUserSeq = seq;
	
	$.ajax({
		url : "/AdminManagement/NoShowValueSetting",
		dataType : "text",
		type : "POST",
		data : { "noShowBtnType" : noShowBtnType, "noShowUserSeq":noShowUserSeq},
		success : function(data) {
			$("#noShowMemberList").load(window.location.href + " #noShowMemberList");
		},
		error : function(request, status, error) {
			alert("code:" + request.status + "\n" + "error:" + error);
		}
	});
	
}

function memberBlock(seq){
	
	$.ajax({
		url : "/AdminBoarding/MemberBan",
		dataType : "text",
		type : "POST",
		data : {"memberSeq":seq},
		success : function(data) {
			$("#noShowMemberList").load(window.location.href + " #noShowMemberList");
		},
		error : function(request, status, error) {
			alert("code:" + request.status + "\n" + "error:" + error);
		}
	});
	
}
</script>






