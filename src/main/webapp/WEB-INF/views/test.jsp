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


<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/resources/loadingBar/ajaxLoading.js"></script>


<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>


</head>
<body>

날짜 : <input type="text" id="wantDate" name="wantDate"> <button type="button" onclick="excelDownload()">다운로드</button>



</body>
</html>

<script>

function excelDownload(){
	
	
	var wantDate = $("#wantDate").val();
	
	$.ajax({
		type:"POST",
		url:"Test/a",
		dataType:"text",
		data:{"wantDate" : wantDate}
	})
	
}
</script>




