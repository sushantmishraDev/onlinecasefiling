var edmsApp = angular.module('EDMSApp', []);


edmsApp.directive('numbersOnly', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attr, ngModelCtrl) {
			function fromUser(text)
			{
				if (text)
				{
					var transformedInput = text.replace(/[^0-9]/g, '');

					if (transformedInput !== text)
					{
						ngModelCtrl.$setViewValue(transformedInput);
						ngModelCtrl.$render();
					}
					return transformedInput;
				}
				return undefined;
			}
			ngModelCtrl.$parsers.push(fromUser);
		}
	};
});





edmsApp.controller('loginController', ['$scope','$http','$controller', function ($scope,$http,$controller) {
	
	
	
	$(document).ready(function(){
		createCaptcha();
		});
	var urlBase="/onlinecasefiling/";
	$scope.flattypes=[];
	$scope.flatcats=[];
	$scope.loginform={};
	$scope.forgotpwd={};
	$scope.userRoles=[];
	$scope.errorlist = '';
	//$scope.errorlist={"name":["Already Exist"]};
	$('.register').hide();
	//$('.login').hide();
	$scope.register={};
	$scope.user={};
	$scope.register.type='aor';
	$scope.isadvocate=false;
	$scope.registershow=false;
	$scope.loginshow=true;
	$scope.forgotshow=false;
	$scope.registerForm=function(){
		//$('.register').show();
		//$('.login').hide();
		$scope.registershow=true;
		$scope.loginshow=false;
		$scope.forgotshow=false;
		this.refresh();
	}
	
	$scope.afterOtpForm=function(){
		//$('.register').show();
		//$('.login').hide();
		$scope.registershow=true;
		$scope.loginshow=false;
		$scope.forgotshow=false;
		$scope.validateotp=true;
		$scope.otptext=false;
		$scope.isadvocate=true;
	}
	$scope.loginForm=function(){
		
		/*$scope.registershow=false;
		$scope.loginshow=true;
		createCaptcha();
		$scope.forgotshow=false;*/
		window.location.reload();
//		$('.login').show();
//		$('.register').hide();		
	}
	$scope.forgotPwdForm=function(){
		$scope.registershow=false;
		$scope.loginshow=false;
		$scope.forgotshow=true;	
		$scope.validateotp=false;
		$scope.otptext=false;
		$scope.sendotp=true;	
	}
	
	sampleVoice();
	function sampleVoice() {
		
		var synth = window.speechSynthesis;

		var inputForm = document.querySelector('form');
		/*var inputTxt = document.querySelector('input');*/

		var inputTxt = "1 2 3 4";
		var voiceSelect = document.querySelector('select');

		function populateVoiceList() {
		  voices = synth.getVoices();

		  for(i = 0; i < voices.length ; i++) {
		    var option = document.createElement('option');
		    option.textContent = voices[i].name + ' (' + voices[i].lang + ')';

		    if(voices[i].default) {
		      option.textContent += ' -- DEFAULT';
		    }

		    option.setAttribute('data-lang', voices[i].lang);
		    option.setAttribute('data-name', voices[i].name);
		    voiceSelect.appendChild(option);
		  }
		}

		populateVoiceList();
		if (speechSynthesis.onvoiceschanged !== undefined) {
		  speechSynthesis.onvoiceschanged = populateVoiceList;
		}

		inputForm.onsubmit = function(event) {
		  event.preventDefault();

		  var utterThis = new SpeechSynthesisUtterance(inputTxt.value);
		  var selectedOption = voiceSelect.selectedOptions[0].getAttribute('data-name');
		  for(i = 0; i < voices.length ; i++) {
		    if(voices[i].name === selectedOption) {
		      utterThis.voice = voices[i];
		    }
		  }
		  synth.speak(utterThis);
		  inputTxt.blur();
		}
		
	}
	

	
	function registraionFormValidateotpform(){
		$scope.registershow=false;
		$scope.forgotshow=true;	
		$scope.validateotp=true;
		$scope.otptext=true;
		$scope.sendotp=false;	
	}
	
	function validateotpform(){
		$scope.registershow=false;
		$scope.validateotp=true;
		$scope.otptext=true;
		$scope.sendotp=false;	
	}

	$scope.refresh=function(){
		$scope.registertemp=$scope.register;
		$scope.register={};
		$scope.isadvocate=false;
		$scope.register.type=$scope.registertemp.type;
	}
	$scope.validateAOR=function(){
		if(AORFormValidate()=="TRUE"){
		$(".form-group").removeClass('has-error');
		$(".msg_div").html(" ");
		$http.post(urlBase+'user/validateAdvocate',$scope.register).
        success(function (data) {
        	if(data.response=="TRUE"){
        		$scope.isadvocate=false;
        		$scope.register.username=data.modelData.rollNo;
        		$scope.register.name=data.modelData.name;
        		$scope.register.email=data.modelData.email;
        		$scope.register.mobile=data.modelData.mobile;
        		
        		console.log($scope.register.email);
        		
        		$scope.forgotpwd.username=data.modelData.rollNo;
        		 $scope.sendOTP();
        		
    			//window.location.href=urlBase+"ecourt/ecourtHome";
        	}else{
        		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
        		 setTimeout(function() {
        			 $(".msg_div").html("");
        		    }, 5000);
        		        		
        	}    
        	           	
        }).
        error(function(data, status, headers, config) {
    		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
        	console.log("Error in getting Login details");
        });
		}
	}
	$scope.registration=function(){
		$(".form-group").removeClass('has-error');
		$(".msg_div").html(" ");
		if(RegisterFormValidate()=="TRUE"){
			
		$scope.errorlist='';
		$http.post(urlBase+'user/register',$scope.register).
        success(function (data) {
        	if(data.response=="TRUE"){
        		if($scope.register.type=='aor'){
        		$scope.loginForm();
        		}
        		else{
        			$scope.isadvocate=false;
        			$scope.forgotpwd.username=$scope.register.username;
           		 $scope.sendOTP();
        		}
        		
        		$(".msg_div").html("<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
        		setTimeout(function() {
       			 $(".msg_div").html("");
       		    }, 5000);
        		$scope.registertemp=$scope.register;
        		$scope.register={};
        		$scope.register.type=$scope.registertemp.type;	
    			$('.login').show();
    			$('.register').hide();
    			$("#userid").parent().removeClass('has-error');
    			$("#name").parent().removeClass('has-error');
    			$("#email").parent().removeClass('has-error');
    			$("#mobile").parent().removeClass('has-error');
    			$("#confirmPassword").parent().removeClass('has-error');
    			$("#username").parent().removeClass('has-error');
    			$("#password").parent().removeClass('has-error');
    			//window.location.href=urlBase+"ecourt/ecourtHome";
        		//$scope.register={'type':$scope.register.type,'username':$scope.register.username,'name':$scope.register.name,'mobile':$scope.register.mobile,'email':$scope.register.email,'password':$scope.register.password,'confirmPassword':$scope.register.confirmPassword};
        	}else{
        		$scope.errorlist=data.dataMapLists;
        	}           	
        }).
        error(function(data, status, headers, config) {
    		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
        	console.log("Error in getting Login details");
        });
		}
	}
	function AORFormValidate(){
		$scope.validate = "TRUE";
		
			if($scope.register.hasOwnProperty("rollNo") == false){
				$("#rollNo").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#rollNo").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("enrollNo") == false){
				$("#enrollNo").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#enrollNo").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("enrollYear") == false){
				$("#enrollYear").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#enrollYear").parent().removeClass('has-error');
			}
		return $scope.validate;
		
	}
	
	//////////captcha
	
	
	var code;
	function createCaptcha() {
	  //clear the contents of captcha div first 
	  document.getElementById('captcha').innerHTML = "";
	  var charsArray =
	  "0123456789";
	  var lengthOtp = 6;
	  var captcha = [];
	  
	  $http.get(urlBase+'dms/generateCaptcha').
      success(function (data) {	      
  		
    	  //captcha = data.modelData;    
    	  if(data.response=="TRUE"){
    	  var canv = document.createElement("canvas");
    	  canv.id = "captcha";
    	  canv.width = 100;
    	  canv.height = 50;
    	  var ctx = canv.getContext("2d");
    	  ctx.font = "25px Georgia";
    	  ctx.strokeText(data.modelData, 0, 30);
    	  //storing captcha so that can validate you can save it somewhere else according to your specific requirements
    	  code = data.modelData;
    	  document.getElementById("captcha").appendChild(canv); // adds the canvas to the body element
      }
      }).
      error(function(data, status, headers, config) {
    	  console.log("Error in getting Captcha");
      });
	  /*for (var i = 0; i < lengthOtp; i++) {
	    //below code will not allow Repetition of Characters
	    var index = Math.floor(Math.random() * charsArray.length + 1); //get the next character from the array
	    if (captcha.indexOf(charsArray[index]) == -1)
	      captcha.push(charsArray[index]);
	    else i--;
	  }*/
	 /* var canv = document.createElement("canvas");
	  canv.id = "captcha";
	  canv.width = 100;
	  canv.height = 50;
	  var ctx = canv.getContext("2d");
	  ctx.font = "25px Georgia";
	  ctx.strokeText(captcha.join(""), 0, 30);
	  //storing captcha so that can validate you can save it somewhere else according to your specific requirements
	  code = captcha.join("");
	  document.getElementById("captcha").appendChild(canv); // adds the canvas to the body element
*/	}
	function validateCaptcha() {
	  event.preventDefault();
	  debugger
	  if (document.getElementById("cpatchaTextBox").value == code) {
	    //alert("Valid Captcha")
	  }else{
	    alert("Invalid Captcha. try Again");
	    createCaptcha();
	  }
	}
	
	/////////captcha
	
	
	
	
	function RegisterFormValidate(){
		$scope.validate = "TRUE";
			if($scope.register.hasOwnProperty("username") == false){
				$("#userid").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#userid").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("name") == false){
				$("#name").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#name").parent().removeClass('has-error');
			}
			
			if($scope.register.hasOwnProperty("email") == false){
				$("#email").parent().addClass('has-error');
				$scope.validate = "FALSE";
				
			}else{
				if(isValidEmailAddress($scope.register.email)){
					$("#email").parent().removeClass('has-error');					
				}else
				{
					$scope.validate = "FALSE";
					$("#email").parent().addClass('has-error');
				}
					
			}

			if($scope.register.hasOwnProperty("mobile") == false){
				$("#personmobile").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#personmobile").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("mobile") == false){
				$("#mobile").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#mobile").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("adhar") == false){
				$("#adhar").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#adhar").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("password") == false){
				$("#registerpassword").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#registerpassword").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("confirmPassword") == false){
				$("#confirmPassword").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#confirmPassword").parent().removeClass('has-error');
			}
			var msg="Please update latest Email/Mobile in advocate role.";
		/*	if($scope.register.type=="aor" &&( $scope.register.email=='' || $scope.register.mobile=="")){
				$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+msg+"</div>");
				
			}else{
				$(".msg_div").html("");
			}*/
		return $scope.validate;
		
	}
	function isValidEmailAddress(emailAddress) {
	    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	    return pattern.test(emailAddress);
	};

	function loginFormValidate(action){
		
		$scope.validate = "TRUE";
		$(".form-group").removeClass('has-error');  
		$("#msg_div").html(" ");
		
		if(action=="login"){
		
			if($scope.loginform.hasOwnProperty("username") == false){	
		
				 $("#username").parent().addClass('has-error');
				 $scope.validate = "FALSE";
		
			}else
			{
		
				$("#username").parent().removeClass('has-error');
			}
			if($scope.loginform.hasOwnProperty("password")==false){
				$("#password").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#password").parent().removeClass('has-error');
			}
		}
		if(action=="fgpwd"){
			
			if($scope.loginform.hasOwnProperty("username") == false){	
		
				 $("#username").parent().addClass('has-error');
				 $scope.validate = "FALSE";
		
			}else
			{
		
				$("#username").parent().removeClass('has-error');
			}
			
		}
		
		return $scope.validate;
	}
	
	$scope.login= function() {
		
		event.preventDefault();
		  debugger
		  if (document.getElementById("cpatchaTextBox").value == code) {
		  /*  alert("Valid Captcha")*/
		    if(loginFormValidate("login")=="TRUE"){	
				console.log($scope.loginform); 
		        $http.post(urlBase+'dms/userlogin',$scope.loginform).
		            success(function (data) {
		            	console.log("Success got Login details");
		            	console.log(data);  	            	
		            	if(data.response=="TRUE"){	
	            			window.location.href=urlBase+"ecourt/ecourtHome";
	            			
		            	}
		            	else{
		            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
		            		setTimeout(function() {
		           			 $(".msg_div").html("");
		           		    }, 5000);
		            	}           	
		            }).
		            error(function(data, status, headers, config) {
	            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
		            	console.log("Error in getting Login details");
		            });
			}	
		  }else{
		    alert("Invalid Captcha. try Again");
		    createCaptcha();
		  }
		
		     
    };
    
    
    $scope.accountActivation= function() {
    	
		if(loginFormValidate("fgpwd")=="TRUE"){	
			console.log($scope.loginform); 
	
	        $http.post(urlBase+'dms/userFagPass',$scope.loginform). 
	            success(function (data) 
	            		{
	            	 if(data.response=="TRUE")
	            	{	
	            		
            			window.location.href=urlBase+"views/accountActivation";
	            	}else if(data.response=="FALSE"){
	            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            	}           	
	            }).
	            error(function(data, status, headers, config) {
            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		}
    }; 
    
    $scope.loading = true;
    
    $scope.sendOTP= function(){
	    $scope.loading = false;
	    
		var response = $http.post(urlBase+'genearteOTP',$scope.forgotpwd); 
		response.success(function (data, status, headers, config) 
	            		{
	            	 if(data.response=="TRUE")
	            	{	
	            		 if(data.modelData.adv_id!=null){
	            			 alert("otp send to your ********"+$scope.register.mobile.charAt($scope.register.mobile.length-2)+""+$scope.register.mobile.charAt($scope.register.mobile.length-1)  + " mobile number");
	            		 registraionFormValidateotpform();
	            	}
	            		 else if($scope.register.type=='inperson'){
	            			 //$scope.forgotshow=false;
		            			 //alert("otp send to your ********"+$scope.register.mobile.charAt($scope.register.mobile.length-2)+""+$scope.register.mobile.charAt($scope.register.mobile.length-1)  + " mobile number");
		            		 registraionFormValidateotpform();
	            		 }
	            	 else{
	            		 validateotpform();
	            		}
	            		 $scope.loading = true;
            			//window.location.href=urlBase+"views/forgetPassword";
	            	}else if(data.response=="FALSE"){
	            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            		setTimeout(function() {
	           			 $(".msg_div").html("");
	           		    }, 5000);
	            		 $scope.loading = true;
	            	}           	
	            });
	            response.error(function(data, status, headers, config) {
            		$("#msg_div_otp").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            	 $scope.loading = true;
	            });
		/*}*/
    };
    
    
$scope.updatepassword= function() {
	var response = $http.post(urlBase+'updatepassword',$scope.user); 
	response.success(function(data, status, headers, config) {	
		 if(data.response=="TRUE")
     	{	
			 alert("Password Updated Successfully Try to login");
			 location.reload(true);
     	}
		 else
			 {
			 alert("Password Mismatch");
			 }
	});
	response.error(function(data, status, headers, config) {
		alert("Error");
	});
};
    
	function newPasswordForm(user){
		$scope.validOTP=true;
		$scope.otptext=false;
		$scope.validateotp=false;
		$scope.user=user;
	}
    $scope.validateOtp= function() {
    	
		/*if(loginFormValidate("fgpwd")=="TRUE"){	*/
			console.log($scope.forgotpwd); 
	
	var response = $http.post(urlBase+'validateOtp',$scope.forgotpwd); 
	response.success(function (data, status, headers, config) 
	            		{
	            	 if(data.response=="TRUE")
	            	{	
	            		 if(data.data.adv_id!=null){
	            			 $scope.afterOtpForm();
	            		 }
	            		
	            		 else if($scope.register.type=='inperson'){
	            			 $scope.loginForm();
	            		 }
	            		 else{
	            		 newPasswordForm(data.data);
	            		 }
	            		 
	             		/*$(".msg_div").html("<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	             		setTimeout(function() {
	            			 $(".msg_div").html("");
	            		    }, 5000);
	             		
	         			$('.login').show();
	         			$('.register').hide();*/
	         			
            			//window.location.href=urlBase+"views/forgetPassword";
	            	}else if(data.response=="FALSE"){
	            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            		setTimeout(function() {
	           			 $(".msg_div").html("");
	           		    }, 5000);
	            	}           	
	            });
	            response.error(function(data, status, headers, config) {
            		$("#msg_div_otp").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		/*}*/
    };
    
}]);
