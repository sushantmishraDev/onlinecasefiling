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





edmsApp.controller('noticeController', ['$scope','$http','$controller', function ($scope,$http,$controller) {
	
	
	
	$(document).ready(function(){
		createCaptcha();
		});
	var urlBase="/onlinecasefiling/";
	$scope.notice={};
	$scope.flatcats=[];
	$scope.loginform={};
	$scope.forgotpwd={};
	$scope.userRoles=[];
	
	// for audio captcha
	var synth = window.speechSynthesis;
	$scope.captcha1="";
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
	$scope.captcha1;
	$scope.registerForm=function(){
		//$('.register').show();
		//$('.login').hide();
		$scope.registershow=true;
		$scope.loginshow=false;
		$scope.forgotshow=false;
		this.refresh();
	}
	
	
	
	
	

	
	//////////captcha
	
	
	var code;
	function createCaptcha() {
		console.log("location",window.location.search.params);
		
	var url=window.location.search;
	
	console.log(url.split('=').pop());
	$scope.notice.nt_id=url.split('=').pop();
	}
	
	
	/////////captcha
	
	
	
	$scope.login= function() {
		
		event.preventDefault();
		  debugger
		 
		  /*  alert("Valid Captcha")*/
		        $http.post(urlBase+'noticelogin',$scope.notice).
		            success(function (data) {
		            	console.log("Success got Login details");
		            	console.log(data);  	            	
		            	if(data.response=="TRUE"){	
		            		window.open(urlBase+"/uploads/"+data.modelData.doc_name,'_self');
	            			
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
			
		
		
		     
    };
	
	
	
	
	
    
}]);
