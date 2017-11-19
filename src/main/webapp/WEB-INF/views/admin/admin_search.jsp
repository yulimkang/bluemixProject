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

<style>

	.radio label {
		margin-top:-3%;
	}
	
	#calSearch {
		padding-left:30px;
		padding-right:15px;
	}
	
	#sDate {
		width:14%;
		text-align:center;
	}
	#eDate {
		width:14%;
		text-align:center;
	}
	
	#todayDate {
		padding-top:5px;
		font-size:medium;
	}
	
	#DateAfterAMonth {
		padding-top:5px;
		font-size:medium;
	}
	
	@media (max-width: 768px) {
		.radio label {
			margin-top:0;
		}
		
		#sDate {
			width:22%;
			text-align:center;
		}
		#eDate {
			width:22%;
			text-align:center;
		}
	}

</style>

<title>관리자 검색</title>
</head>
<body id="htmlBody">
	<jsp:include page="../headerAndFooter/header.jsp">
		<jsp:param name="page" value="removeEasySearch" />
	</jsp:include>
	
	<div class="container">
	    <div class="panel panel-default">
				<div class="panel-body">관리자 예약내역검색</div>
		</div>
		
		<br>
		
		
		
		  <form name="searchForm" id="searchForm" method="post" action="">
		  
		  
		  	<div id="calSearch">
			
		  		검색기간 : 
		  		<span class="fa-stack fa-1x" style="padding:0px;">
			    		<i class="fa fa-calendar-o fa-stack-2x"></i>
			    		<strong class="fa-stack-2x calendar-text"  id="todayDate"></strong>
			 	 </span>
		  		<input type="text" id="sDate" style="cursor:pointer" name="sDate" /> ~
		  		
		  		<span class="fa-stack fa-1x" style="padding:0px;">
			    		<i class="fa fa-calendar-o fa-stack-2x"></i>
			    		<strong class="fa-stack-2x calendar-text" id="DateAfterAMonth"></strong>
			 	 </span>
		  		<input type="text" id="eDate" style="cursor:pointer" name="eDate" />
		  		
			 </div>
		  
			  <div class="col-lg-12" style="margin-top:4%;">
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
							<input type="radio" name="searchKind" value="general" checked="checked">일반예약<br/>
							<input type="radio" name="searchKind" value="repeat" >반복예약
						</label>
					</div>
				</div>
				
				<div class="col-lg-2">
					<button type="button" class="pull-right btn btn-primary" onclick="searchFormSubmit()">검색</button>
				</div>
				
			</div>
			
			
			</form>
		</div>


	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script>


//날짜 검색
var today = new Date();
var todayDate = new Date().getDate();
var monthLater = new Date((Date.parse(today) + 30 * 1000 * 60 * 60 * 24));
var monthLaterDate = new Date((Date.parse(today) + 30 * 1000 * 60 * 60 * 24)).getDate();

//달력 이미지에서 오늘, 한달 후 날짜 미리 설정
$("#todayDate").text(todayDate);
$("#DateAfterAMonth").text(monthLaterDate);

$(function(){	

	 $("#sDate").datepicker({
		 dateFormat:"yy-mm-dd",
		 onClose : function(selectedDate) {
				$("#eDate").datepicker("option", "minDate", selectedDate);
			},
			onSelect : function(dateText, inst) {
				
				//날짜를 새로 선택했을 때 달력 이미지의 날짜가 바뀌도록 함(시작일)
				var sDateDate = $("#sDate").datepicker("getDate").getDate();
				 $("#todayDate").text(sDateDate);
			}
	 });
	
	 //시작일을 오늘로 설정
	 $("#sDate").datepicker().datepicker("setDate", today);

	 $("#eDate").datepicker({
		 dateFormat:"yy-mm-dd",
		 onClose : function(selectedDate) {
				$("#sDate").datepicker("option", "maxDate", selectedDate);
			},
			onSelect : function(dateText, inst) {
				
				//날짜를 새로 선택했을 때 달력 이미지의 날짜가 바뀌도록 함(종료일)
				var eDateDate = $("#eDate").datepicker("getDate").getDate();
				 $("#DateAfterAMonth").text(eDateDate);
			}
	 });
	 
	 //종료일을 오늘로부터 한 달 후로 설정
	 $("#eDate").datepicker().datepicker("setDate", monthLater);

});



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