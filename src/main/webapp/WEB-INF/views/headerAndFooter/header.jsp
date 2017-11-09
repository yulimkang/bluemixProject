<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/headerLocation.js"></script>  
 
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" onclick="main()" style="cursor:pointer;"><strong>IBM 회의실 예약 시스템</strong></a>
    </div>

    <div class="collapse navbar-collapse" id="myNavbar">
    
    <!-- 여기서부터 간편검색 시작, 모바일 화면은 수정 필요 -->
    	<div id="navSearch">
	    	<form class="nav navbar-nav" name="easySearchForm" id="easySearchForm" style="width:50%; margin-top:1%; margin-bottom:1%;margin-left:8%;">
	    		<div class="col-xs-2" style="padding:0px;">
		    		<select name="easySelectSearchOpt" id="easySelectSearchOpt" class="form-control" style="height:5%;font-size:80%;padding:0px">
							<option value="e_all">전체</option>
							<option value="e_title">회의제목</option>
							<option value="e_mem_nm">예약자</option>
							<option value="e_mem_pn">전화번호</option>
					</select>
				</div>
				
				<div class="col-xs-8" style="padding:0px;">
		    		<input type="text" name="easyInputSearchCont" id="easyInputSearchCont" placeholder="간편검색" class="form-control" style="height:5%;font-size:80%;padding:0px">
		    	</div>
		    	
		    	<div class="col-xs-2" style="padding:0px;">

	      			<button type="button" data-toggle="modal" data-target="#easySearchResult" onclick="easySearchForGeneral(1)" OnKeyDown="if(event.keyCode==13){easySearchForGeneral(1);}"class="btn btn-secondary my-2 my-sm-0" style="height:5%;background-color:#2c3e50;'">
    				<span class="fa fa-search" aria-hidden="true" style="color:white;background-color:#2c3e50;"></span></button>

		      	</div>
		      	
	    	</form>
    	</div>
    	
    	
    	<!--  간편검색 모달 -->
    	<div class="modal fade" id="easySearchResult" >
	    	<div class="modal-dialog" style="width:80%;background-color:white;'">
	    		<div class="modal-content">
	    		
	    			<div class="modal-header">
						<h4 class="modal-title">간편검색 상세내역</h4>
					</div>
					
					<div class="modal-body" id="result">

				</div> 
	    		</div>
	    	</div>
    	</div>
    	<!-- 여기까지 간편검색 -->

        <ul class="nav navbar-nav navbar-right">
        <li><a onClick="repeatReservation()" style="cursor:pointer"><strong>반복예약신청</strong></a></li>
        <c:choose>
        	<c:when test="${sessionScope.id ne null}">
        		<li><a onClick="adminSearchPage()" style="cursor:pointer"><strong>검색(관리자용)</strong></a></li>
        	</c:when>
        	
        	<c:otherwise>
        		<li><a onClick="searchPage()" style="cursor:pointer"><strong>검색</strong></a></li>
        	</c:otherwise>
        </c:choose>
		<li><a target="_blank" onclick="admin()" style="cursor:pointer"><strong>관리자</strong></a></li>
		
		<c:if test="${sessionScope.id ne null}">
		   <li><a target="_blank" onclick="logout()" style="color:red; cursor:pointer"><strong>로그아웃</strong></a></li>
		</c:if>
      </ul>
    </div>
  </div>
</nav>

<script>
</script>