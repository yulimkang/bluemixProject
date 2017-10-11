<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="http://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>검색기능</title>
</head>
<body>
 <jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
  <div class="container">

 <h3>검색기능</h3>
 <form name="search" method="post" action="">
  <select name="searchOpt">
  	<option value="all">전체</option>
  	<option value="title">회의제목</option>
  	<option value="mem_nm">예약자</option>
  	<option value="mem_pn">전화번호</option>
  </select>
  <input type="text" size="70"/>
  <button type="button">검색</button>
 </form>
 <br><br>
 
 <div id="allReservList">
 <table class="table table-striped table-hover ">
  <thead>
 	<tr>
 	  <th>   프로젝트     </th>
 	  <th>   회의날짜      </th>
 	  <th>    회의시간    </th>
 	  <th>    회의실        </th>
 	  <th>    회의제목     </th>
 	  <th>    예약자        </th>
 	  <th>    전화번호     </th>
 	</tr>
   </thead>
   <tbody>
 	 <c:forEach items="${allReservList}" var="allReservList">
	    <tr>
	     <td>${allReservList.rsv_comp}</td>
	     <td>${allReservList.rsv_date}</td>
	     <td>${allReservList.rsv_start_time} ~ ${allReservList.rsv_end_time}</td>
	     <td>${allReservList.rsv_conf_nm}</td>
	     <td>${allReservList.rsv_title}</td>
	     <td>${allReservList.rsv_mem_nm}</td>
	     <td>${allReservList.rsv_mem_pn}</td>
	    </tr>
	   </c:forEach>
	 </tbody>
 </table>
</div>

   
  </div>

  
 <jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>