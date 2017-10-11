<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" 	type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>
<script type="text/javascript" src="/resources/js/adminFooter.js"></script>  


<html>
<head>
<title>관리자 setting</title>

</head>
<body>
	<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>

	<div class="container">
	
		<jsp:include page="admin_footer.jsp"></jsp:include>
		
		<div class="panel panel-default">
				<div class="panel-body">설정 관리</div>
		</div>
		
		<div>
			<div class="col-sm-6">
				<div class="panel panel-default">
				  <div class="panel-heading">비밀번호 변경</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">비밀번호 </label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-key"></i></span>
						    <input class="form-control input-sm" type="text" id="selectDate">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">비밀번호 재 입력</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-key"></i></span>
						    <input class="form-control input-sm" type="text" id="selectDate">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <a href="#" class="btn btn-primary btn-sm">변경</a>
					</div>
				  </div>
				</div>
			</div>
			
			<div class="col-sm-6">
				<div class="panel panel-default">
				  <div class="panel-heading">예약가능 기간(월)</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">현재 예약 가능 기간</label>
						  <div class="input-group">
						    <input class="form-control input-sm" type="text" id="selectDate" disabled="" value="2달">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">변경 예약 가능 기간</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
						    <input class="form-control input-sm" type="text" id="selectDate">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <a href="#" class="btn btn-primary btn-sm">변경</a>
					</div>
				  </div>
				</div>
			</div>
		</div>
		
		<div>
			<div class="col-sm-6">
				<div class="panel panel-primary">
				  <div class="panel-heading">최대 사용자 예약가능 시간</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">현재 최대 예약가능 시간</label>
						  <div class="input-group">
						    <input class="form-control input-sm" type="text" id="selectDate" disabled="" value="2달">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">변경 최대 예약 가능 시간</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
						    <input class="form-control input-sm" type="text" id="selectDate">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <a href="#" class="btn btn-primary btn-sm">변경</a>
					</div>
				  </div>
				</div>
			</div>
			
			<div class="col-sm-6">
				<div class="panel panel-primary">
				  <div class="panel-heading">이메일 전송 시간</div>
				  <div class="panel-body">
				    <div class="form-group col-sm-5">
						  <label class="control-label">현재 이메일 전송 시간</label>
						  <div class="input-group">
						    <input class="form-control input-sm" type="text" id="selectDate" disabled="" value="2달">
						  </div>
					</div>
					
					<div class="form-group col-sm-5">
						  <label class="control-label">변경 이메일 전송시간</label>
						  <div class="input-group">
						    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
						    <input class="form-control input-sm" type="text" id="selectDate">
						  </div>
					</div>
					
					<div class="col-sm-2" style="margin-top:25px">
						  <a href="#" class="btn btn-primary btn-sm">변경</a>
					</div>
				  </div>
				</div>
			</div>
			
			<div>
				<div class="col-sm-6">
					<div class="panel panel-danger">
					  <div class="panel-heading">No Show 미준수 횟수</div>
					  <div class="panel-body">
					    <div class="form-group col-sm-5">
							  <label class="control-label">현재 NoShow 미준수 횟수</label>
							  <div class="input-group">
							    <input class="form-control input-sm" type="text" id="selectDate" disabled="" value="2달">							  </div>
						</div>
						
						<div class="form-group col-sm-5">
							  <label class="control-label">변경 최대 예약 가능 시간</label>
							  <div class="input-group">
							    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
							    <input class="form-control input-sm" type="text" id="selectDate">
							  </div>
						</div>
						
						<div class="col-sm-2" style="margin-top:25px">
							  <a href="#" class="btn btn-primary btn-sm">변경</a>
						</div>
					  </div>
					</div>
				</div>
				
				<div class="col-sm-6">
					<div class="panel panel-danger">
					  <div class="panel-heading">No Show 미준수 횟수</div>
					  <div class="panel-body">
					    <div class="form-group col-sm-5">
							  <label class="control-label">현재 NoShow 미준수</label>
							  <div class="input-group">
							    <input class="form-control input-sm" type="text" id="selectDate" disabled="" value="2달">							  </div>
						</div>
						
						<div class="form-group col-sm-5">
							  <label class="control-label">변경noShow 미준수</label>
							  <div class="input-group">
							    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
							    <input class="form-control input-sm" type="text" id="selectDate">
							  </div>
						</div>
						
						<div class="col-sm-2" style="margin-top:25px">
							  <a href="#" class="btn btn-primary btn-sm">변경</a>
						</div>
					  </div>
					</div>
				</div>
				
				
			
				<div class="col-sm-6">
					<div class="panel panel-warning">
					  <div class="panel-heading">독점방지</div>
					  <div class="panel-body">
					    <div class="form-group col-sm-5">
							  <label class="control-label">현재 독점방지 시간</label>
							  <div class="input-group">
							    <input class="form-control input-sm" type="text" id="selectDate" disabled="" value="2달">							  </div>
						</div>
						
						<div class="form-group col-sm-5">
							  <label class="control-label">변경 독점방지 기간</label>
							  <div class="input-group">
							    <span class="input-group-addon input-sm"><i class="fa fa-calendar-check-o"></i></span>
							    <input class="form-control input-sm" type="text" id="selectDate">
							  </div>
						</div>
						
						<div class="col-sm-2" style="margin-top:25px">
							  <a href="#" class="btn btn-primary btn-sm">변경</a>
						</div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">

</script>






