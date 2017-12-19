 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="EDMSApp">
<head>

	<meta charset="utf-8" />
	<title>Login Page</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
<!-- 	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet"> -->
	<link href="../assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="../assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="../assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="../assets/css/animate.min.css" rel="stylesheet" />
	<link href="../assets/css/style.min.css" rel="stylesheet" />
	<link href="../assets/css/style-responsive.min.css" rel="stylesheet" />
	<link href="../assets/css/theme/default.css" rel="stylesheet" id="theme" />
	<!-- ================== END BASE CSS STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	 <script src="../assets/plugins/pace/pace.min.js"></script> 
	<!-- ================== END BASE JS ================== -->
</head>
<body class="pace-top bg-white">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="fade" ng-controller="loginController">
	<!--  Name -->
	
	<%-- <div class="row>
	
	</div>
	<nav class="navbar navbar-default navbar-fixed-top">fdgdf</nav> --%>
	
<!--   </div> -->
  
	 <div class="row" style="border-color:balck">  
	<!-- 
	<div class="col-md-12 sm-8" >
		 --> <div class="col-sm-4"><img  class="navbar-brand" style="width: 220px; height: 80px; margin-left: 10px; padding: 0px 18px;"  alt="" src="${pageContext.request.contextPath}/assets/img/highcourt.png"></div>
      <div class="col-sm-8"><h2 class="caption-title">Allahabad High Court e-filing </h2></div>
		 <div class="news-feed" style="margin-top:10px" >
	</div>
	<!-- </div>  --> </div>
<%-- <div id="loader_gif" style="display:none;">
    <img src="${pageContext.request.contextPath}/images/loader.gif" class="loader"/>
</div> --%>
	
	<!-- </div> -->
	
	<!-- begin login -->
	
        
<hr style="border-bottom-color: black">
<div class="content with-margin">
<div class="container-fluid ">
   		<div class="col-lg-5 col-lg-offset-3 text-center" style="background-color: #F5F5F5;margin-top:100px" > 
   	
   		<div class="login-content" >
<!-- <div class="container">
  <div class="row">
    <div class="Absolute-Center is-Responsive">
      <div id="logo-container"></div> -->
      <div class="col-sm-12 col-md-10 col-md-offset-1" style="margin-top:20px">
        <form class="form-horizontal"  name="loginFrm" action="" id="loginForm" novalidate role="form">
          <div class="form-group input-group" ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input class="form-control" type="text" name='username' placeholder="username" ng-model="loginform.username" required/>          
          </div>
          <div class="form-group input-group" ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" type="password" name='password' placeholder="password" ng-model="loginform.password"/>     
          </div>
          <div class="checkbox">
            <label>
              <input type="checkbox"> I agree to the <a href="#">Terms and Conditions</a>
            </label>
          </div>
          <div class="form-group">
            <button type="button" class="btn btn-def btn-block" ng-click="login()">Login</button>
          </div>
          <div class="form-group text-center">
            <a href="#">Forgot Password</a>&nbsp;|&nbsp;<a href="#" ng-click="createaccount()">Create an Account</a>
          </div>
        </form>        
      </div>  
    </div>    
  </div>
</div>
</div>
<!--  -->

    
<!--     <div class="container-fluid ">
   		<div class="col-lg-5 col-lg-offset-3 text-center" style="background-color: #F5F5F5"> 
   	
   		
      			<div class="login-content">
      			 
      				     <form class="margin-bottom-0" 	name="loginFrm" novalidate role="form" >
                    <form class="form-horizontal" 	name="loginFrm" novalidate role="form" >
                    <span id="msg_div"></span>
                    login-content
                
                        <div class="form-group" ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
                        <label for="username" class="control-label col-xs-3 ">Username <span class="star"> *</span></label>			    
                        <div class="col-xs-10">
                        <div class="col-sm-6">
                            <input type="text" class="form-control col-xs-3" id="username" name="username" ng-model="loginform.username" required placeholder="User Name" />
                        </div>
                        </div>
                      
                       
                       
                        <div class="form-group" ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
                        <label for="password" class="control-label col-xs-3 ">Password <span class="star"> *</span></label>
                        <div class="col-md-6 col-sm-6">			    
                            <input type="password" class="form-control col-xs-3 " id="password" name="password" ng-model="loginform.password" required placeholder="Password" />
                        </div>
                        </div>
                       
                        <div>
                            
                            <label class="col-sm-6">
                                <input type="checkbox" /> Remember Me
                            </label>
                        </div>
                        <br>
                        <br>
                        
                          <div class="row">
    							<div class="col-sm-2 col-xs-offset-3" style="background-color:lavenderblush;">
    							<div class="login-buttons">
    							<button type="submit" ng-click="login()" class="btn btn-success ">Sign me in</button>
    							</div>	   							
    							</div>
    							
    							<div class="col-sm-1 col-xs-offset-1" style="background-color:lavenderblush;">
    							<div class="login-buttons">
    							<button type="submit" ng-click="fagpass()" class="btn btn-success">Forget Password</button>
    							</div>	   							
    							</div>
    						</div>
    						
    					<br><br>
    					<div class="row">
    							<div class="col-md-1 col-xs-offset-4" style="background-color:lavenderblush;">
  									<div class="login-buttons">
                            			<button type="submit" ng-click="accountActivation()" class="btn btn-success">Create an Account</button>
                            		</div>
                        		</div>
    					</div>
                        
              
                        
                    </form>
      			
      			 </div>
       	
	
            

                        
                        
        
          <!--  end right-container-->
        </div>
       <!-- end login --> 
        


	<!-- end page container -->
	
	
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="../assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="../assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="../assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="../assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="../assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="../assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="../assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	
	

	
	<script  src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/loginController.js"></script>
	 <script>
		$(document).ready(function() {
			App.init();
		});
	</script>
</body>
</html>
 