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
			<div class="col-lg-5">
				<input class="form-control" type="text" size="50" id="inputSearchCont" name="inputSearchCont" OnKeyDown="if(event.keyCode==13){searchFormSubmit();}"/>
			</div>
			<div class="col-lg-2">
				<div class="radio">
          			<label>
						<input type="radio" name="searchKind" value="general" checked="checked">일반예약
					</label>
					<label>
						<input type="radio" name="searchKind" value="repeat" >반복예약
					</label>
				</div>
			</div>
			
			<div class="col-lg-2">
				<button type="button" class="pull-right btn btn-primary" onclick="searchFormSubmit()">검색</button>
			</div>
			
			
			</form>
		</div>
		
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
            //자동완성 기능에서 클릭했을 때, 일반예약을 기본으로 함
            select:function(event, id) {
    			$("#inputSearchCont").val(id.item.label);
            	$("#searchForm").attr("action","/AdminSearch/AdminGeneralSearchPage");
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
 });

/**
 * 작성자 : 최문정
 * 내용 : Form에서 입력받은 값 Contorller로 전송
 */
function searchFormSubmit(){
	var searchOpt = $("#selectSearchOpt").val();
	var searchContent=$("#inputSearchCont").val();
	var searchKind = $(":input:radio[name=searchKind]:checked").val();
	
	if(searchContent.length>0){
		
		if(searchKind == "general") {
			//일반예약 검색일 때
			$("#searchForm").attr("action","/AdminSearch/AdminGeneralSearchPage");
			$("#searchForm").submit();
			
		}else if(searchKind == "repeat") {
			//반복예야 검색일 때
			$("#searchForm").attr("action","/AdminSearch/AdminRepeatSearchPage");
			$("#searchForm").submit();
			
		}

	}
	else{
		alert("내용을 입력해 주세요.");
	}
	
}

</script>