<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
    <!--[if !IE]><!-->
    <html ng-app="EDMSApp">
    <!--<![endif]-->

    <head>

        <meta charset="utf-8" />
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
        <title>Login Page</title>
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />

        <!-- ================== BEGIN BASE CSS STYLE ================== -->
        <!-- 	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet"> -->
        <link href="${pageContext.request.contextPath}/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/assets/css/animate.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/assets/css/style.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/assets/css/style-responsive.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/assets/css/theme/default.css" rel="stylesheet" id="theme" />
        <!-- ================== END BASE CSS STYLE ================== -->

        <!-- ================== BEGIN BASE JS ================== -->
        <script src="${pageContext.request.contextPath}/assets/plugins/pace/pace.min.js"></script>
        <style>
            .link:focus,
            .link:hover {
                color: #23527c;
                text-decoration: underline;
                cursor: pointer;
            }
            
            
input[type=text] {
    padding: 12px 20px;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
button{
  background-color: #4CAF50;
    border: none;
    color: white;
    padding: 12px 30px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
}
.speakerImage {
    appearance:none;
    -webkit-appearance:none;
    background:transparent;
    border:none;
    outline:none;
    background-image:url(${pageContext.request.contextPath}/assets/img/speaker.png);
    background-size:40px;
   
     padding:20px 20px;
    /* padding-top:2px;
    padding-bottom:2px;
    padding-left:5px;
    padding-right:5px; */
    background-repeat:no-repeat;
    cursor:pointer;
}
canvas{
  /*prevent interaction with the canvas*/
  pointer-events:none;
}
        </style>
        <!-- ================== END BASE JS ================== -->
    </head>

    <body class="pace-top bg-white" >
        <!-- begin #page-loader -->
        <div id="page-loader" class="fade in"><span class="spinner"></span></div>
        <!-- end #page-loader -->

        <!-- begin #page-container -->
        <div id="page-container" class="fade" ng-controller="noticeController">
            <!-- begin login -->
            <div class="login login-with-news-feed" ng-if="loginshow">
                <!-- begin news-feed -->
                <div class="news-feed">
                    <div class="news-image">
                        <img src="${pageContext.request.contextPath}/assets/img/login-bg/bg-7.jpg" data-id="login-cover-image" alt="" />
                    </div>
                </div>
                <!-- end news-feed -->
                <!-- begin right-content -->
                <div class="right-content">
                    <!-- begin login-header -->
                    <div class="login-header">
                        <div class="brand">
                            <span class="logo"></span>Allahabad High Court
                            <small></small>
                        </div>
                    </div>

                    <!-- end login-header -->
                    <!-- begin login-content -->
                    <div class="login-content"  >

                        <form class="margin-bottom-0" name="loginFrm" novalidate role="form">
                            <span class="msg_div"></span>
                           
                            <div class="form-group" >
                                <label for="otp" class="control-label ">OTP <span class="star"> *</span></label>
                                <div>
                                    <input type="text" class="form-control input-lg" id="otp" name="otp" ng-model="notice.nt_otp" required placeholder="OTP" />
                                </div>
                            </div>
                            <div class="login-buttons" id="signin">
                                <button type="submit" ng-click="login()" class="btn btn-success btn-block btn-lg">Sign in</button>                                 
                            </div>
                        </form>
                    </div>

                    <!-- end login-content -->
                </div>
                <!-- end right-container -->
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

        <script src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/noticeServeController.js?v=1"></script>
        <script>
            $(document).ready(function() {
                App.init();
            });
        </script>
    </body>

    </html>