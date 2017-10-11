<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <title>관리자 : 예약내역 페이지</title>
</head>
<body>
 <jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
 <button>예약 현황 삭제</button> <button>삭제</button>
 <br>
 <p>시작날짜 끝날짜</p>
 <br><br>
 
 <table class="rsvHistory">
 	<thead>
 	 <tr>
 	  <th>회의날짜</th>
 	  <th>회의시작시간</th>
 	  <th>회의종료시간</th>
 	  <th>예약자</th>
 	  <th>전화번호</th>
 	  <th>회의실</th>
 	  <th>회의제목</th>
 	  <th>상태</th> <!-- 삭제된 상태까지 나타낼것 -->
 	  <th>예약변경날짜</th> <!-- 변경 안됐으면 그냥 예약날짜로 출력 -->
 	 </tr>
 	</thead>
 	<tbody>
 	 <c:choose>
 	  <c:when test="${fn:length(list)} > 0">
 	   <c:forEach items="${list}" var="row">
 	    <tr>
 	     <td>${row.HST_DATE}</td>
 	     <td>${row.HST_START_TIME}</td>
 	     <td>${row.HST_END_TIME}</td>
 	     <td>${row.HST_RSV_MEM_NM}</td>
 	     <td>${row.HST_RSV_MEN_PN}</td>
 	     <td>${row.HST_CONF_NM}</td>
 	     <td>${row.HST_RSV_TITLE}</td>
 	     <td>${row.HST_REG_DATE}</td>
 	    </tr>
 	   </c:forEach>
 	  </c:when>
 	  <c:otherwise>
 	   <tr>
 	    <td>조회된 결과가 없습니다.</td>
 	   </tr>
 	  </c:otherwise>
 	 </c:choose>
 	</tbody>
 </table>
 
 <jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>

</body>
</html>