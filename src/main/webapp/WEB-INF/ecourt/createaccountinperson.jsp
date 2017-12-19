<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="EDMSApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create username</title>
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
<!-- 	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet"> -->
	<link href="../assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
	<link href="../assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

	<!-- ================== END BASE CSS STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	 <!-- <script src="../assets/plugins/pace/pace.min.js"></script>  -->
	<!-- ================== END BASE JS ================== -->
</head>
<body> --%>

<jsp:include page="../content/header.jsp"></jsp:include>
  		<!-- <form class="form-horizontal reduce-gap" name="caseFileForm" role="form" novalidate> -->		
 <form class="form-horizontal reduce-gap" style="margin-top:70px; width:90%" name="userCreationForm" role="form" novalidate>
 <div id="page-container"  ng-controller="ecourtCreateUserController">
 	<div class="col-md-8 col-md-offset-3">
		<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">User Account Creation</h3>
    </div>
    <div class="panel-body">
    	 <div class="form-group">
    	 <div class="row">
    	 <ng-form name="subform1">
      		<div class="col-md-6 ">
      			
					<label class="control-label col-md-5 col-sm-offset-3">User Name * :</label>
				</div>
      		
      		<div class="col-md-6">
      			<input  style="width: 68%" class="form-control" required type="text" id="username" name="username" 
      					data-type="alphanum" placeholder="User Name" ng-maxlength=8
						ng-model="userCreationForm.username" on-change="checkusername()">
				<span style="color: red"
				ng-show="userCreationForm.username.$dirty && userCreationForm.username.$invalid && userCreationForm.username.$error ">
		 		<span ng-show="userCreationForm.username.$error.required && subform1.username.$touched">User name is Requird</span>
				<span class="error" ng-show="subform1.username.$error.required && subform1.username.touched"></span>
				<small class="error" ng-show="subform1.username.$error.maxlength">Enter Username</small>
			</span>
      		</div>
      		</ng-form>
      		</div>
      		<!--  Field Change-->
      		<br>
      		  <div class="row">
    	 		<ng-form name="subform7">
      				<div class="col-md-6 ">
      					<label class="control-label col-md-5 col-sm-offset-3">First Name * :</label>
					</div>
      		
      		<div class="col-md-6">
      			<input  style="width: 68%" class="form-control" required type="text" id="firstname" name="firstname" 
      					data-type="alphanum" placeholder="First Name" ng-maxlength=30
						ng-model="firstname">
				<span style="color: red"
				ng-show="userCreationForm.firstname.$dirty && userCreationForm.firstname.$invalid && userCreationForm.firstname.$error ">
		 		<span ng-show="userCreationForm.firstname.$error.required && subform7.firstname.$touched">First name is Requird</span>
				<span class="error" ng-show="subform7.firstname.$error.required && subform7.firstname.touched"></span>
				<small class="error" ng-show="subform7.firstname.$error.maxlength">Check the FirstName</small>
			</span>
      		</div>
      		</ng-form>
      		</div>
      		<!--Field change  -->
      		<br>
      		<div class="row">
    	 		<ng-form name="subform8">
      				<div class="col-md-6 ">
      					<label class="control-label col-md-5 col-sm-offset-3">Last Name * :</label>
					</div>
      		
      		<div class="col-md-6">
      			<input  style="width: 68%" class="form-control" required type="text" id="lastname" name="lastname" 
      					data-type="alphanum" placeholder="Last Name" ng-maxlength=20
						ng-model="lastname">
				<span style="color: red"
				ng-show="userCreationForm.firstname.$dirty && userCreationForm.firstname.$invalid && userCreationForm.firstname.$error ">
		 		<span ng-show="userCreationForm.firstname.$error.required && subform8.firstname.$touched">Last name is Requird</span>
				<span class="error" ng-show="subform8.firstname.$error.required && subform8.firstname.touched"></span>
				<small class="error" ng-show="subform8.firstname.$error.maxlength">Check the Last Name</small>
			</span>
      		</div>
      		</ng-form>
      		</div>
      		<!--Field Change  -->
      		<br>
      	<div class="row">
    	 <ng-form name="subform2">
      		<div class="col-md-6 ">
      			
					<label class="control-label col-md-5 col-sm-offset-3">Password * :</label>
				</div>
      		
      		<div class="col-md-6">
      			<input  style="width: 68%" class="form-control" required type="password" id="password" name="password" 
      					 placeholder="Password" ng-maxlength=8 ng-pattern="/^[1-9][0-9]{0,5}[0-9A-Z]{0,1}$/"
 						ng-model="userCreationForm.password"  >
				<span style="color: red"
				ng-show="userCreationForm.password.$dirty && userCreationForm.password.$invalid && userCreationForm.password.$error ">
		 		<span ng-show="userCreationForm.password.$error.required">User name is Requird</span>
				<span class="error" ng-show="subform2.password.$error.pattern">Enter valid password</span>
				<small class="error" ng-show="subform2.password.$error.maxlength">Enter Password</small>
			</span>
      		</div>
      		</ng-form>
      	</div>
      	<!--  -->
      	<br>
      	<div class="row">
    	 <ng-form name="subform3">
      		<div class="col-md-6 ">
      			
					<label class="control-label col-md-5 col-sm-offset-3">Mobile Number * :</label>
				</div>
      		
      		<div class="col-md-6">
      			<input  numbers-only style="width: 68%" class="form-control"  type="text" id="mobileno" name="mobileno" 
      					 placeholder="Mobile No"  ng-pattern="/^[0-9]$/" ng-maxlength=10
 						ng-model="userCreationForm.mobileno"  >
				<span style="color: red"
				ng-show="userCreationForm.mobileno.$dirty && userCreationForm.mobileno.$invalid && userCreationForm.mobileno.$error ">
		 		<span ng-show="userCreationForm.mobileno.$error.required">Password is Requird</span>
				<span class="error" ng-show="subform3.mobileno.$error.pattern">Enter valid Mon=bile no</span>
				<small class="error" ng-show="subform3.mobileno.$error.maxlength">Please check the Mobile no</small>
			</span>
      		</div>
      		</ng-form>
      	</div>
      	<!--  -->
      	<br>
      	<div class="row">
    	 <ng-form name="subform4">
      		<div class="col-md-6 ">
      			<label class="control-label col-md-5 col-sm-offset-3"> Address * :</label>
			</div>
      		
      		<div class="col-md-6">
      			<textarea  style="width: 68%" class="form-control" required type="text" id="address" name="address" 
      					 placeholder="Address" ng-maxlength=100 
 						ng-model="userCreationForm.address"  ></textarea>
				<span style="color: red"
				ng-show="userCreationForm.address.$dirty && userCreationForm.address.$invalid && userCreationForm.address.$error ">
		 		<span ng-show="userCreationForm.address.$error.required">User name is Requird</span>
				<small class="error" ng-show="subform4.address.$error.maxlength">Enter Password</small>
			</span>
      		</div>
      		</ng-form>
      	</div>
      	<!--  -->
      	<br>
      	<div class="row">
    	 <ng-form name="subform5">
      		<div class="col-md-6 ">
      			<label class="control-label col-md-5 col-sm-offset-3"> State * :</label>
			</div>
      		
      		<div class="col-md-6">
      		<select required style="width: 68%" class="form-control" ng-model="userCreationForm.state"
					name="state" id="state"
				ng-options="id as state for state in states">
			</select>
      		<span style="color: red"
				ng-show="userCreationForm.state.$dirty && userCreationForm.state.$invalid && userCreationForm.state.$error ">
		 		
			</span>
      		</div>
      		</ng-form>
      	</div>
      	<!--  -->
      	<br>
      	<div class="row">
    	 <ng-form name="subform6">
      		<div class="col-md-6 ">
      			<label class="control-label col-md-5 col-sm-offset-3"> City * :</label>
			</div>
      		
      		<div class="col-md-6">
      		<select required style="width: 68%" class="form-control" ng-model="userCreationForm.city"
					name="city" id="city"
				ng-options="id as city for city in cities">
			</select>
      		<span style="color: red"
				ng-show="userCreationForm.city.$dirty && userCreationForm.city.$invalid && userCreationForm.city.$error ">
		 		
			</span>
      		</div>
      		</ng-form>
      	</div>
      	<!--  -->
      	<br>
      	<div class="row">
      	<div class="form-group">
    	 <ng-form name="subform7">
      		<div class="col-md-6 ">
      			
					<label class="control-label col-md-5 col-sm-offset-3">Pin code * :</label>
				</div>
      		
      		<div class="col-md-6">
      			<input style="width: 68%" class="form-control"  type="text" id="pincode" name="pincode" 
      					 placeholder="Pin Code"  ng-pattern="/^[0-9]$/" ng-maxlength=6
 						ng-model="userCreationForm.pincode"  >
				<span style="color: red"
				ng-show="userCreationForm.pincode.$dirty && userCreationForm.pincode.$invalid && userCreationForm.pincode.$error ">
		 		<span ng-show="userCreationFormsubform7.subform7.pincode.$error.required">Pin code is Requird</span>
				<span class="error" ng-show="subform7.pincode.$error.pattern"><br>Enter valid Pin code no</span>
				<small class="error" ng-show="subform7.pincode.$error.maxlength"><br>Please check the Mobile no</small>
			</span>
      		</div>
      		</ng-form>
      	</div>
      	</div>
      	
      	<!--end form  -->
      	<br>
      	<hr>
      	<div class="row">	
    	 	<div class="col-md-5  col-md-offset-5">
								<div class="form-group">
									<div class="col-md-6 col-sm-6">
										<input type="submit" value="Create Account" id="save" ng-click="saveUserDetail(userCreationForm)" class="btn btn-success"/>
									</div>
								</div>
			</div>
    	</div> 
      	
    	</div>
    	
    	 
    
    </div>
	</div>
    </div>
    
</div>


<!-- begin scroll to top btn -->
<!--  -->

<a href="javascript:;"
	class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
	data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
<!-- end scroll to top btn -->

 <script  src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/ecourtCreateUserController.js"></script>



<script>
	/* $(function() {
		$(".btn").click(function() {
			$(this).button('loading').delay(1000).queue(function() {
				$(this).button('reset');
				$(this).dequeue();
			});
		});
	}); */

	/* $(document).ready(function() {
		App.init();

	}); */
</script>
</body>
</html>


