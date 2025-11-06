<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html ng-app="EDMSApp">
<!--<![endif]-->

<head>

<meta charset="utf-8" />
<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico" />
<title>Login Page</title>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<!-- 	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet"> -->
<link
	href="${pageContext.request.contextPath}/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/animate.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/css/style.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/style-responsive.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/theme/default.css"
	rel="stylesheet" id="theme" />
<!-- ================== END BASE CSS STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->
<script
	src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js"></script>
<style>
.link:focus, .link:hover {
	color: #23527c;
	text-decoration: underline;
	cursor: pointer;
}

.switch {
	top: 0px;
	margin: 0rem 0rem;
	position: relative;
	border-bottom: 1px solid #ddd;
}

.switch>ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}

.switch>ul>li {
	float: left;
	display: block;
	color: white;
	text-align: center;
	text-decoration: none;
	margin: 2px;
}

.switch>ul>li a {
	display: block;
	color: white;
	text-align: center;
	padding: 16px;
	text-decoration: none;
}

.switch>ul>li:hover {
	background-color: #fff;
}

.inner-switch-dark {
	cursor: pointer;
	background: #000000;
	font-weight: bold;
	color: #fff;
}

.flex-end {
	float: right;
	/* padding: 10px 0; */
	display: flex;
	justify-content: flex-end;
}

[data-tip] {
	position: relative;
}

[data-tip]:before {
	content: '';
	/* hides the tooltip when not hovered */
	display: none;
	content: '';
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
	border-bottom: 5px solid #1a1a1a;
	position: absolute;
	top: 30px;
	left: 35px;
	z-index: 8;
	font-size: 0;
	line-height: 0;
	width: 0;
	height: 0;
}

[data-tip]:after {
	display: none;
	content: attr(data-tip);
	position: absolute;
	top: 35px;
	left: 0px;
	padding: 5px 8px;
	background: #45d6bd;
	color: #fff;
	z-index: 9;
	font-size: 1em;
	height: 28px;
	line-height: 28px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	white-space: nowrap;
	word-wrap: normal;
}

[data-tip]:hover:before, [data-tip]:hover:after {
	display: block;
}

html.dark-mode {
	filter: invert(100%) hue-rotate(180deg);
}

html.dark-mode img {
	filter: invert(100%) hue-rotate(180deg);
}

input[type=text] {
	padding: 12px 20px;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 12px 30px;
	text-decoration: none;
	margin: 4px 2px;
	cursor: pointer;
}

.speakerImage {
	appearance: none;
	-webkit-appearance: none;
	background: transparent;
	border: none;
	outline: none;
	background-image:
		url(${pageContext.request.contextPath}/assets/img/speaker.png);
	background-size: 40px;
	padding: 20px 20px;
	/* padding-top:2px;
    padding-bottom:2px;
    padding-left:5px;
    padding-right:5px; */
	background-repeat: no-repeat;
	cursor: pointer;
}

canvas {
	/*prevent interaction with the canvas*/
	pointer-events: none;
}
</style>
<!-- ================== END BASE JS ================== -->
</head>

<body class="pace-top bg-white">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container" class="fade" ng-controller="loginController">
		<!-- begin login -->
		<div class="login login-with-news-feed" ng-if="loginshow">
			<!-- begin news-feed -->
			<div class="news-feed">
				<div class="news-image">
					<img
						src="${pageContext.request.contextPath}/assets/img/login-bg/bg-7.jpg"
						data-id="login-cover-image" alt="" />
				</div>
			</div>
			<!-- end news-feed -->
			<!-- begin right-content -->
			<div class="right-content">

				<!-- <section class="text-center">
            <em class="switch flex-end"> -->
				<button class="inner-switch-dark" title="Enable Contrast Mode">A</button>
				<button id="btn-decrease">A-</button>
				<button id="btn-orig">A</button>
				<button id="btn-increase">A+</button>

				<!--   </em>
         </section> -->
				<!-- begin login-header -->

				<div class="login-header">


					<div class="brand">

						<span class="logo"></span>Allahabad High Court <small></small>
					</div>
				</div>

				<!-- end login-header -->
				<!-- begin login-content -->
				<div class="login-content">

					<form class="margin-bottom-0" name="loginFrm" novalidate
						role="form">
						<span class="msg_div"></span>
						<div class="form-group"
							ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
							<label for="username" class="control-label ">Username <span
								class="star"> *</span></label>
							<div>
								<input type="text" class="form-control input-lg" id="username"
									name="username" ng-model="loginform.username" required
									placeholder="User Name/Roll No." />
							</div>
						</div>
						<div class="form-group m-b-15" id="password"
							ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
							<label for="password" class="control-label ">Password <span
								class="star"> *</span></label> <input type="password"
								class="form-control input-lg" id="password" name="password"
								ng-model="loginform.password" required placeholder="Password" />
						</div>
						<div class="col-md-12">
							<div class="col-md-3" id="captcha"></div>
							<div class="col-md-9 ">
								<button type="button" ng-click="speakCaptcha()"
									class="speakerImage"></button>
								<input type="text" placeholder="Enter Captcha"
									id="cpatchaTextBox" class="form-control"
									style="max-width: 173px;" /> <br>
							</div>
						</div>

					<div class="form-group d-flex align-items-center">
    <!-- Send OTP -->
   <div class="row">
    <div class="col-md-12">
     <button type="button"
        ng-click="sendOtp()"
        ng-hide="timerRunning"
        class="btn btn-primary me-3"
        style="margin-right: 65px; max-width: 180px;">
        Send OTP
    </button>

    <!-- Countdown display -->
    <span
        ng-show="timerRunning"
        class=" me-3"
        style="margin-right: 65px; max-width: 180px; font-weight:bold;color:red;"
        >
        Resend in {{ formatTime(timeLeft) }}
    </span>

    <!-- OTP input -->
    <input type="text"
        placeholder="Enter Valid OTP"
        required
        ng-model="loginform.um_otp"
        id="otp"
        class="form-control w-auto"
        style="max-width: 173px;"
        numbers-only />
    </div>
   
   </div>
</div>





						<div class="login-buttons mt-4" id="signin">
							<button type="submit" ng-click="login()"
								class="btn btn-success btn-block btn-lg">Sign in</button>

						</div>
						<hr />
						<div class="form-group text-center">
							<span class="link" ng-click="forgotPwdForm()">Forgot
								Password</span> &nbsp; | Click <span class="link"
								ng-click="registerForm()">here</span> to register.
							<!-- 						    <span>Forgot Password</span> &nbsp;|&nbsp;<span class="link" ng-click="registerForm()">Create an Account</span>-->
						</div>


					</form>
				</div>

				<!-- end login-content -->
			</div>
			<!-- end right-container -->
		</div>
		<div class="login login-with-news-feed" ng-if="forgotshow">
			<!-- begin news-feed -->
			<div class="news-feed">
				<div class="news-image">
					<img
						src="${pageContext.request.contextPath}/assets/img/login-bg/bg-7.jpg"
						data-id="login-cover-image" alt="" />
				</div>
			</div>
			<!-- end news-feed -->
			<!-- begin right-content -->
			<div class="right-content">
				<!-- begin login-header -->
				<div class="login-header">
					<div class="brand">
						<span class="logo"></span>Allahabad High Court <small></small>
					</div>
				</div>

				<!-- end login-header -->
				<!-- begin login-content -->
				<div class="login-content">

					<form class="margin-bottom-0" name="fgtpwdFrm" novalidate
						role="form">
						<span class="msg_div"></span>
						<div class="form-group" ng-show="sendotp"
							ng-class="{ 'has-error' : fgtpwdFrm.username.$invalid && !fgtpwdFrm.username.$pristine , 'has-success' : fgtpwdFrm.username.$valid  }">
							<label for="username" class="control-label ">Username <span
								class="star"> *</span></label>
							<div>
								<input type="text" class="form-control input-lg" id="username"
									name="username" ng-model="forgotpwd.username" required
									placeholder="User Name/Roll No." />
							</div>
						</div>
						<div class="form-group" ng-show="otptext"
							ng-class="{ 'has-error' : fgtpwdFrm.otp.$invalid && !fgtpwdFrm.otp.$pristine , 'has-success' : fgtpwdFrm.otp.$valid  }">
							<label for="otp" class="control-label ">OTP <span
								class="star"> *</span></label>
							<div>
								<input type="text" class="form-control input-lg" id="otp"
									name="otp" ng-model="forgotpwd.um_otp" required
									placeholder="OTP" />
							</div>
						</div>
						<div class="form-group" ng-show="validOTP">
							<label for="password" class="control-label ">New Password
								<span class="star"> *</span>
							</label>
							<div
								data-tip="Password must contains atleast one alphabet,special characters and number">
								<input type="password" class="form-control" id="newPassword"
									ng-model="user.newpassword" placeholder="New Password" />
							</div>
						</div>
						<div class="form-group" ng-show="validOTP">
							<label for="confirmPassword" class="control-label ">Confirm
								Password <span class="star"> *</span>
							</label>
							<div>
								<input type="password" class="form-control"
									id="confirmNewPassword" ng-model="user.confirmpassword"
									placeholder="Confirm Password" />
							</div>
						</div>
						<div class="login-buttons" ng-show="sendotp">
							<button type="submit" ng-disabled="loading==false"
								ng-click="sendOtp()" class="btn btn-success btn-block btn-lg">Send
								OTP *</button>
						</div>
						<div class="login-buttons" ng-show="validateotp">
							<button type="submit" ng-click="validateOtp()"
								class="btn btn-success btn-block btn-lg">Validate OTP!</button>
						</div>
						<div class="form-group" ng-show="validOTP">
							<button type="button" ng-click="updatepassword()"
								class="btn btn-success btn-block btn-lg">Update
								Password</button>
						</div>


						<hr />
						<div class="form-group text-center">
							<span class="link" ng-click="loginForm()">Login</span> &nbsp; |
							Click <span class="link" ng-click="registerForm()">here</span> to
							register.
							<!-- 						    <span>Forgot Password</span> &nbsp;|&nbsp;<span class="link" ng-click="registerForm()">Create an Account</span>-->
						</div>
					</form>
				</div>

				<!-- end login-content -->
			</div>
			<!-- end right-container -->
		</div>
		<!-- end login -->
		<div class="register register-with-news-feed" ng-if="registershow">
			<!-- begin news-feed -->
			<div class="news-feed">
				<div class="news-image">
					<img src="assets/img/login-bg/bg-7.jpg" alt="" />
				</div>
			</div>
			<!-- end news-feed -->
			<!-- begin right-content -->
			<div class="right-content">
				<!-- begin register-header -->
				<h1 class="register-header">Register</h1>
				<!-- end register-header -->
				<!-- begin register-content -->
				<div class="register-content">
					<form method="POST" class="form-horizontal">
						<div ng-if="errorlist!=''" class="alert alert-block alert-danger">
							<ul>
								<span ng-repeat="errors in errorlist "> <span
									ng-repeat="n in errors track by $index">
										<li>{{(n)}}</li>
								</span>
								</span>
							</ul>
						</div>
						<span class="msg_div"></span>
						<div class="form-group">
							<label class="col-md-3 control-label">Login as</label>
							<div class="col-md-9">
								<label class="radio-inline"> <input type="radio"
									ng-model="register.type" ng-change="refresh()" value="aor" />
									AOR{{registerType}}
								</label> <label class="radio-inline"> <input type="radio"
									ng-model="register.type" ng-change="refresh()" value="inperson" />
									In Person
								</label>
							</div>
						</div>

						<div class="form-group"
							ng-show="register.type=='inperson' || isadvocate">
							<label class="col-md-3 control-label">Full Name</label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									oninput="this.value = this.value.toUpperCase()" id="name"
									ng-model="register.name" maxlength="60" placeholder="Full Name" />
							</div>
						</div>
						<div class="form-group"
							ng-show="register.type=='inperson' || isadvocate">
							<label class="col-md-3 control-label">Gender</label>
							<div class="col-md-9">
								<label class="radio-inline"> <input type="radio"
									ng-model="register.gender" value="M" /> Male
								</label> <label class="radio-inline"> <input type="radio"
									ng-model="register.gender" value="F" /> Female
								</label> <label class="radio-inline"> <input type="radio"
									ng-model="register.gender" value="O" /> Other
								</label>
							</div>
						</div>
						<div class="form-group"
							ng-show="register.type=='inperson' || isadvocate">
							<label class="col-md-3 control-label">Email</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="email"
									ng-model="register.email" placeholder="Email" />
							</div>
						</div>
						<div class="form-group" ng-show="register.type==isadvocate">
							<label class="col-md-3 control-label">Mobile</label>
							<div class="col-md-9">
								<input numbers-only type="text" maxlength="10"
									class="form-control" id="mobile" ng-model="register.mobile"
									placeholder="Mobile" readonly />
							</div>
						</div>

						<div class="form-group" ng-show="register.type=='inperson'">
							<label class="col-md-3 control-label">Mobile</label>
							<div class="col-md-9">
								<input numbers-only type="text" maxlength="10"
									class="form-control" id="personmobile"
									ng-model="register.mobile" placeholder="Mobile" />
							</div>
						</div>
						<!--   <div class="form-group" ng-show="register.type=='inperson' || isadvocate">
                                <label class="col-md-3 control-label">Aadhaar No.</label>
                                <div class="col-md-9">
                                    <input numbers-only type="text" maxlength="12" class="form-control" id="adhar" ng-model="register.adhar" placeholder="Adhar" />
                                </div>
                            </div> -->
						<div class="form-group"
							ng-show="register.type=='inperson' || isadvocate">
							<label class="col-md-3 control-label">Username</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="userid"
									ng-disabled="isadvocate" ng-model="register.username"
									placeholder="Username" />
							</div>
						</div>
						<div class="form-group"
							ng-show="register.type=='inperson' || isadvocate">
							<label class="col-md-3 control-label">Password</label>
							<div class="col-md-9"
								data-tip="Password must contains atleast one alphabet,special characters and number">
								<input type="password" class="form-control"
									id="registerpassword" ng-model="register.password"
									placeholder="Password" />
							</div>
						</div>
						<div class="form-group"
							ng-show="register.type=='inperson' || isadvocate">
							<label class="col-md-3 control-label">Confirm Password</label>
							<div class="col-md-9">
								<input type="password" class="form-control" id="confirmPassword"
									ng-model="register.confirmPassword"
									placeholder="Confirm Password" />
							</div>
						</div>
						<div class="form-group"
							ng-show="register.type=='aor' && !isadvocate">
							<label class="col-md-3 control-label">Roll No.</label>
							<div class="col-md-9">
								<input type="text" ng-model="register.rollNo" id="rollNo"
									class="form-control" placeholder="Roll No" />
							</div>
						</div>
						<div class="form-group"
							ng-show="register.type=='aor' && !isadvocate">
							<label class="col-md-3 control-label">Bar Enroll No/Year</label>
							<div class="col-md-6">
								<input numbers-only type="text" ng-model="register.enrollNo"
									id="enrollNo" class="form-control" placeholder="Enroll No" />
							</div>
							<div class="col-md-3">
								<input numbers-only type="text" maxlength="4"
									ng-model="register.enrollYear" id="enrollYear"
									class="form-control" placeholder="Year" />
							</div>
						</div>

						<div class="form-group"
							ng-show="register.type!='aor' || isadvocate">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-9">
								<div class="register-buttons">
									<button type="button" ng-click="registration()"
										class="btn btn-success btn-block btn-lg">Register!</button>
								</div>
							</div>
						</div>

						<div class="form-group"
							ng-show="register.type=='aor' && !isadvocate">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-9">
								<div class="register-buttons">
									<button type="button" ng-click="validateAOR()"
										class="btn btn-success btn-block btn-lg">Validate</button>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-9">
								Already a member? Click <span class="link"
									ng-click="loginForm()">here</span> to login.
							</div>
						</div>
					</form>
				</div>
				<!-- end register-content -->
			</div>
			<!-- end right-content -->
		</div>

		<!-- end theme-panel -->
	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script
		src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/scripts/controllers/loginController.js?v=5"></script>
	<script>
            $(document).ready(function() {
                App.init();
                
                var $affectedElements = $("div,p,a,table"); 

                $affectedElements.each( function(){
                var $this = $(this);
                $this.data("orig-size", $this.css("font-size") );
                });
                
                $("#btn-orig").click(function(){
                	$affectedElements.each( function(){
                	        var $this = $(this);
                	        $this.css( "font-size" , $this.data("orig-size") );
                	});
                	});

                $("#btn-increase").click(function(){
                	changeFontSize(1);
                	});
                
                $("#btn-decrease").click(function(){
                	changeFontSize(-1);
                	});
                
                $(".inner-switch-dark").click(function(){
                	   var dark = localStorage.getItem("mode");
                	   if(dark==''){
                	      localStorage.setItem("mode", 'dark-mode');
                	      $('.inner-switch-dark').prop('title', 'Disable Contrast Mode');
                	   }else{
                	      localStorage.setItem("mode", '');
                	      $('.inner-switch-dark').prop( "title","Enable Contrast Mode");
                	   }
                	   document.documentElement.classList.toggle('dark-mode');
                	})
                	function changeFontSize(direction){
                    $affectedElements.each( function(){
                        var $this = $(this);
                	var fontSize=parseInt($this.css("font-size"));
                	if(direction > 0 && fontSize<=14){
                        	$this.css( "font-size" , parseInt($this.css("font-size"))+direction );
                	}
                	if(direction < 0 &&  fontSize>=10){
                        	$this.css( "font-size" , parseInt($this.css("font-size"))+direction );
                	}
                    });
                }  
            });
            
            
            
           
        </script>
</body>

</html>