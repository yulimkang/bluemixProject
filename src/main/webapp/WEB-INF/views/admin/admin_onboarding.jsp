<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"	href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" 	type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script>

<%
	if(session.getAttribute("id")==null){
		response.sendRedirect("/");  
	}
%>

<html>
<head>
<title>on_boarding</title>

</head>
<body>
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

<form>
	<div class="col-md-2 col-sm-3 col-xs-3">
		<select class="form-control" id="option" name="option">
			<option value="all">전체</option>
			<option value="mem_name">이름</option>
			<option value="mem_no">번호</option>
		</select>
	</div>

	<div class="col-sm-6 col-md-6 col-sm-5 col-xs-6">
		<input type="text" class="form-control" name="context">
	</div>

	<div class="col-sm-2 col-md-2 col-sm-2 col-xs-2">
		<button action="" type="submit" class="btn btn-info">

			검색 <span class='glyphicon glyphicon-search'></span>
		</button>
	</div>
	
	</form>
	
	<div class="container" style="height: 70%">

		<jsp:include page="admin_footer.jsp"></jsp:include>

		<div class="col-md-10 col-sm-8 col-xs-12 col-centered">
			<table class="table table-hover" align="center">
				<thead>
					<tr>
						<td><b>번호</b></td>
						<td><b>이름</b></td>
						<td><b>전화번호</b></td>
						<td><b>이메일</b></td>
						<td><b>근무지</b></td>
						<td><b>회원상태</b></td>
						<td><b>노쇼카운팅</b></td>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${BoardingListAll}" var="BoardingListAll">
						<tr>
							<td>${BoardingListAll.MEM_NO}</td>
							<td>${BoardingListAll.MEM_NAME}</td>
							<td>${BoardingListAll.MEM_PN}</td>
							<td>${BoardingListAll.MEM_EM}</td>
							<td>${BoardingListAll.MEM_COMP}</td>
							<td>${BoardingListAll.MEM_STATE}</td>
							<td>${BoardingListAll.COUNT_WARN}</td>
							
							
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



</script>






