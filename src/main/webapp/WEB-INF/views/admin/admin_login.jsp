<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script src="https://code.jquery.com/jquery-2.1.1.min.js"
	type="text/javascript"></script>
	
<link rel="stylesheet" href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="https://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>  
<link rel="stylesheet" type="text/css"	href="/resources/bootstrap/bootswatch.css" />
<link rel="stylesheet/less" type="text/css"	href="/resources/bootstrap/bootswatch.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/bootstrap/variables.less" />

<script type="text/javascript" src="/resources/js/headerLocation.js"></script>  

<style>
.wrapper {    
	margin-top: 80px;
	margin-bottom: 20px;
}

.form-signin {
  max-width: 420px;
  padding: 30px 38px 66px;
  margin: 0 auto;
  background-color: #eee;
  border: 3px dotted rgba(0,0,0,0.1);  
  }

.form-signin-heading {
  text-align:center;
  margin-bottom: 30px;
}

.form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
}

input[type="text"] {
  margin-bottom: 0px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}

input[type="password"] {
  margin-bottom: 20px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.colorgraph {
  height: 7px;
  border-top: 0;
  background: #c4e17f;
  border-radius: 5px;
  background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
  background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
  background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
  background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
}
</style>

<html>
<head>
<title>관리자 로그인</title>

</head>
	<body>
		<jsp:include page="../headerAndFooter/header.jsp"></jsp:include>
		
		<div class="container" style="height:70%">
		
		<div class="wrapper">
			<form action="" method="post" name="adminLogin" id="adminLogin" class="form-signin">       
			    <h3 class="form-signin-heading">관리자 로그인</h3>
				  <hr class="colorgraph"><br>
				  
				  <input type="text" class="form-control" name="id" id="id" placeholder="Username" required="" autofocus="" />
				  <input type="password" class="form-control" name="pw" id="pw" placeholder="Password" required=""/>     		  
				 
				  <button type="button" class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" onClick="loginBtn()">Login</button>  			
			</form>			
		</div>
	</div>
		
	<jsp:include page="../headerAndFooter/footer.jsp"></jsp:include>
	</body>
</html>

<script type="text/javascript">

function loginBtn(){
    $.ajax({
        url : "/AdminLogin/LoginCheck",
        dataType : "text",
        async : false,
        type : "POST",
        data : $('#adminLogin').serializeArray(),
        success: function(data) {
            if(data=="1") {
                $("#adminLogin").attr("action","/AdminManagement/DashBoard");
        		$("#adminLogin").submit();
            }
            else{
                alert("loginFail");
            }
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"error:"+error);
        }
    });
}
</script>






