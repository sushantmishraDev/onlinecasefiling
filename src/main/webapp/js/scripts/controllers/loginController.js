var edmsApp = angular.module('EDMSApp', []);


edmsApp.directive('numbersOnly', function() {
	return {
		require: 'ngModel',
		link: function(scope, element, attr, ngModelCtrl) {
			function fromUser(text) {
				if (text) {
					var transformedInput = text.replace(/[^0-9]/g, '');

					if (transformedInput !== text) {
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





edmsApp.controller('loginController', ['$scope', '$http', '$controller', '$interval', function($scope, $http, $controller, $interval) {


	$scope.name = "vijay"

	$(document).ready(function() {
		createCaptcha();
	});
	var urlBase = "/onlinecasefiling/";
	$scope.flattypes = [];
	$scope.flatcats = [];
	$scope.loginform = {};
	$scope.forgotpwd = {};
	
	$scope.showSendOtp = false;   // renamed flag for controlling form visibility
	$scope.otptext = false;
	$scope.validOTP = false;

	
	
	
	$scope.userRoles = [];

	// for audio captcha
	var synth = window.speechSynthesis;
	$scope.captcha1 = "";
	$scope.errorlist = '';
	//$scope.errorlist={"name":["Already Exist"]};
	$('.register').hide();
	//$('.login').hide();
	$scope.register = {};
	$scope.user = {};
	$scope.register.type = 'aor';
	$scope.isadvocate = false;
	$scope.registershow = false;
	$scope.loginshow = true;
	$scope.forgotshow = false;
	$scope.captcha1;
	$scope.registerForm = function() {
		//$('.register').show();
		//$('.login').hide();
		$scope.registershow = true;
		$scope.loginshow = false;
		$scope.forgotshow = false;
		this.refresh();
	}

	$scope.afterOtpForm = function() {
		//$('.register').show();
		//$('.login').hide();
		$scope.registershow = true;
		$scope.loginshow = false;
		$scope.forgotshow = false;
		$scope.validateotp = true;
		$scope.otptext = false;
		$scope.isadvocate = true;
		var voices = [];

	}
	$scope.loginForm = function() {

		/*$scope.registershow=false;
		$scope.loginshow=true;
		createCaptcha();
		$scope.forgotshow=false;*/
		window.location.reload();
		//		$('.login').show();
		//		$('.register').hide();		
	}
/*	$scope.forgotPwdForm = function() {
		$scope.registershow = false;
		$scope.loginshow = false;
		$scope.forgotshow = true;
		$scope.validateotp = false;
		$scope.otptext = false;
		$scope.sendotp = true;
	}*/
	
	$scope.forgotPwdForm = function() {
	    $scope.registershow = false;
	    $scope.loginshow = false;
	    $scope.forgotshow = true;
	    $scope.validateotp = false;
	    $scope.otptext = false;
	    $scope.showSendOtp = true; // changed here
		$scope.sendotp = true;
	};



	function populateVoiceList() {
		voices = synth.getVoices().sort(function(a, b) {
			const aname = a.name.toUpperCase(), bname = b.name.toUpperCase();
			if (aname < bname) return -1;
			else if (aname == bname) return 0;
			else return +1;
		});

	}

	/*function speak(msg){
		console.log("captchaaaaaaaa",$scope.captcha1);
		console.log("captchaaaaaaaa",voices);
		if (synth.speaking) {
			console.error('speechSynthesis.speaking');
			return;
		}
		if ($scope.captcha1 !== '') {
		var utterThis = new SpeechSynthesisUtterance(msg);
		utterThis.onend = function (event) {
			console.log('SpeechSynthesisUtterance.onend');
		}
		utterThis.onerror = function (event) {
			console.error('SpeechSynthesisUtterance.onerror');
		}
	   // var selectedOption = voiceSelect.selectedOptions[0].getAttribute('data-name');
		for(i = 0; i < voices.length ; i++) {
			//hi-IN
		  if(voices[i].lang == "hi-IN") {
			utterThis.voice = voices[i];
			break;
		  }
		}
		utterThis.pitch = 0.9;
		utterThis.rate = 1;
		synth.speak(utterThis);
	  }
	}*/


	$scope.speakCaptcha = function() {



		try {
			const messageParts = $scope.captcha1.split(' ')

			let currentIndex = 0
			const speak = (textToSpeak) => {
				const msg = new SpeechSynthesisUtterance();
				const voices = window.speechSynthesis.getVoices();
				msg.voice = voices[0];
				msg.volume = 1; // 0 to 1
				msg.rate = 1; // 0.1 to 10
				msg.pitch = .1; // 0 to 2
				msg.text = textToSpeak;
				msg.lang = 'en-US';

				msg.onend = function() {
					currentIndex++;
					if (currentIndex < messageParts.length) {
						setTimeout(() => {
							speak(messageParts[currentIndex])
						}, 500)
					}
				};
				speechSynthesis.speak(msg);
			}
			speak(messageParts[0])
		} catch (e) {
			console.error(e)
		}




		console.log("function calleddddddddd");









		populateVoiceList();
		if (speechSynthesis.onvoiceschanged !== undefined) {
			speechSynthesis.onvoiceschanged = populateVoiceList;
		}



		//speak();

		/*pitch.onchange = function() {
		  pitchValue.textContent = pitch.value;
		}
	
		rate.onchange = function() {
		  rateValue.textContent = rate.value;
		}
	
		voiceSelect.onchange = function(){
		  speak();
		}*/
	}







	function registraionFormValidateotpform() {
		$scope.registershow = false;
		$scope.forgotshow = true;
		$scope.validateotp = true;
		$scope.otptext = true;
		$scope.sendOtp = false;
	}

	function validateotpform() {
		$scope.registershow = false;
		$scope.validateotp = true;
		$scope.otptext = true;
		$scope.sendOtp = false;
	}

	$scope.refresh = function() {
		$scope.registertemp = $scope.register;
		$scope.register = {};
		$scope.isadvocate = false;
		$scope.register.type = $scope.registertemp.type;
	}
	$scope.validateAOR = function() {
		if (AORFormValidate() == "TRUE") {
			$(".form-group").removeClass('has-error');
			$(".msg_div").html(" ");
			$http.post(urlBase + 'user/validateAdvocate', $scope.register).
				success(function(data) {
					if (data.response == "TRUE") {
						$scope.isadvocate = false;
						$scope.register.username = data.modelData.rollNo;
						$scope.register.name = data.modelData.name;
						$scope.register.email = data.modelData.email;
						$scope.register.mobile = data.modelData.mobile;

						console.log($scope.register.email);
						console.log($scope.register.username);

						$scope.forgotpwd.username = data.modelData.rollNo;
						$scope.sendOtp();

						//window.location.href=urlBase+"ecourt/ecourtHome";
					} else {
						$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" + data.data + "</div>");
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
	$scope.registration = function() {
		$(".form-group").removeClass('has-error');
		$(".msg_div").html(" ");
		if (RegisterFormValidate() == "TRUE") {

			$scope.errorlist = '';
			$http.post(urlBase + 'user/register', $scope.register).
				success(function(data) {
					if (data.response == "TRUE") {
						if ($scope.register.type == 'aor') {
							alert(data.data);
							$scope.loginForm();
						}
						else {
							$scope.isadvocate = false;
							$scope.forgotpwd.username = $scope.register.username;
							$scope.sendOtp();
						}

						/*$(".msg_div").html("<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
						setTimeout(function() {
								 $(".msg_div").html("");
								}, 5000);*/
						$scope.registertemp = $scope.register;
						$scope.register = {};
						$scope.register.type = $scope.registertemp.type;
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
					} else {
						$scope.errorlist = data.dataMapLists;
					}
				}).
				error(function(data, status, headers, config) {
					$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
					console.log("Error in getting Login details");
				});
		}
	}
	function AORFormValidate() {
		$scope.validate = "TRUE";

		if ($scope.register.hasOwnProperty("rollNo") == false) {
			$("#rollNo").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
			$("#rollNo").parent().removeClass('has-error');
		}
		if ($scope.register.hasOwnProperty("enrollNo") == false) {
			$("#enrollNo").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
			$("#enrollNo").parent().removeClass('has-error');
		}
		if ($scope.register.hasOwnProperty("enrollYear") == false) {
			$("#enrollYear").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
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


		$http.get(urlBase + 'dms/generateCaptcha').
			success(function(data) {
				$scope.captcha1 = "";

				//	$scope.captcha1 =data.modelData;
				for (var i = 0; i < data.modelData.length; i++) {
					var j = data.modelData[i];
					console.log("aaaaaa", j);
					$scope.captcha1 = $scope.captcha1 + j + " ";
				}

				//captcha = data.modelData;    
				if (data.response == "TRUE") {
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
		} else {
			alert("Invalid Captcha. try Again");
			createCaptcha();
		}
	}

	/////////captcha

	function validatePassword(password) {
		const regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;
		return regex.test(password);
	}

	// Example usage:






	function RegisterFormValidate() {
		$scope.validate = "TRUE";
		if ($scope.register.hasOwnProperty("username") == false) {
			$("#userid").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
			$("#userid").parent().removeClass('has-error');
		}
		if ($scope.register.hasOwnProperty("name") == false) {
			$("#name").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
			$("#name").parent().removeClass('has-error');
		}

		if ($scope.register.hasOwnProperty("email") == false) {
			$("#email").parent().addClass('has-error');
			$scope.validate = "FALSE";

		} else {
			if (isValidEmailAddress($scope.register.email)) {
				$("#email").parent().removeClass('has-error');
			} else {
				$scope.validate = "FALSE";
				$("#email").parent().addClass('has-error');
			}

		}

		if ($scope.register.hasOwnProperty("mobile") == false) {
			$("#personmobile").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
			$("#personmobile").parent().removeClass('has-error');
		}
		if ($scope.register.hasOwnProperty("mobile") == false) {
			$("#mobile").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
			$("#mobile").parent().removeClass('has-error');
		}
		/*if($scope.register.hasOwnProperty("adhar") == false){
			$("#adhar").parent().addClass('has-error');
			$scope.validate = "FALSE";
		}else{
			$("#adhar").parent().removeClass('has-error');
		}*/
		if ($scope.register.hasOwnProperty("password") == false) {
			$("#registerpassword").parent().addClass('has-error');
			$scope.validate = "FALSE";

		} else {
			const isValid = validatePassword($scope.register.password);
			if (isValid) {
				console.log("Password is valid.");
			} else {
				$("#registerpassword").parent().addClass('has-error');
				$scope.validate = "FALSE";
				alert("Password is not valid.");
				console.log("Password is not valid.");
			}
			$("#registerpassword").parent().removeClass('has-error');
		}
		if ($scope.register.hasOwnProperty("confirmPassword") == false) {
			$("#confirmPassword").parent().addClass('has-error');
			$scope.validate = "FALSE";
		} else {
			$("#confirmPassword").parent().removeClass('has-error');
		}
		var msg = "Please update latest Email/Mobile in advocate role.";
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

	function loginFormValidate(action) {

		$scope.validate = "TRUE";
		$(".form-group").removeClass('has-error');
		$("#msg_div").html(" ");

		if (action == "login") {

			if ($scope.loginform.hasOwnProperty("username") == false) {

				$("#username").parent().addClass('has-error');
				$scope.validate = "FALSE";

			} else {

				$("#username").parent().removeClass('has-error');
			}
			if ($scope.loginform.hasOwnProperty("password") == false) {
				$("#password").parent().addClass('has-error');
				$scope.validate = "FALSE";
			} else {
				$("#password").parent().removeClass('has-error');
			}
			if (!$scope.loginform.um_otp || $scope.loginform.um_otp.trim() === "") {
				$("#otp").parent().addClass('has-error');
				alert("Please enter a valid OTP before logging in.");
				$scope.validate = "FALSE";
			} else {
				$("#otp").parent().removeClass('has-error');
			}

		}
		if (action == "fgpwd") {

			if ($scope.loginform.hasOwnProperty("username") == false) {

				$("#username").parent().addClass('has-error');
				$scope.validate = "FALSE";

			} else {

				$("#username").parent().removeClass('has-error');
			}

		}

		return $scope.validate;
	}

	$scope.login = function(event) {

		debugger;

		// Step 1: Validate Captcha
		if (document.getElementById("cpatchaTextBox").value != code) {
			alert("Invalid Captcha. Try again.");
			createCaptcha();
			return;
		}

		// Step 2: Validate form fields
		if (loginFormValidate("login") != "TRUE") {
			return;
		}

		// Step 3: Check if OTP is entered
		if (!$scope.loginform.um_otp || $scope.loginform.um_otp.trim() === "") {
			alert("Please enter the OTP sent to your registered mobile number.");
			$("#otp").parent().addClass('has-error');
			return;
		}

		// Step 4: Validate OTP with backend


		$http.post(urlBase + 'validateOtp', $scope.loginform)
			.success(function(otpResponse) {
				console.log("OTP Validation Response:", otpResponse);

				if (otpResponse.response === "TRUE") {
					console.log(" OTP validated successfully. Proceeding to login...");

					// Step 5: Now proceed to actual login
					$http.post(urlBase + 'dms/userlogin', $scope.loginform)
						.success(function(data) {
							console.log("Login Response:", data);

							if (data.response === "TRUE") {
								window.location.href = urlBase + "ecourt/ecourtHome";
							} else {
								$(".msg_div").html(
									"<div class='alert alert-danger alert-dismissible' role='alert'>" +
									"<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
									"<span aria-hidden='true'>&times;</span></button>" +
									data.data + "</div>"
								);
								setTimeout(function() { $(".msg_div").html(""); }, 5000);
							}
						})
						.error(function() {
							$(".msg_div").html(
								"<div class='alert alert-danger alert-dismissible' role='alert'>" +
								"<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
								"<span aria-hidden='true'>&times;</span></button>" +
								"<strong>Oops!</strong> Error during login." +
								"</div>"
							);
						});

				} else {
					alert("Invalid OTP. Please enter the correct OTP sent to your registered mobile number.");
					$("#otp").parent().addClass('has-error');
				}
			})
			.error(function() {
				alert("Error validating OTP. Please try again later.");
			});
	};


	/*$scope.login = function() {

		event.preventDefault();
		debugger
		if (document.getElementById("cpatchaTextBox").value == code) {
			  alert("Valid Captcha")
			if (loginFormValidate("login") == "TRUE") {
				console.log($scope.loginform);
				$http.post(urlBase + 'dms/userlogin', $scope.loginform).
					success(function(data) {
						console.log("Success got Login details");
						console.log(data);
						if (data.response == "TRUE") {
							window.location.href = urlBase + "ecourt/ecourtHome";

						}
						else {
							$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" + data.data + "</div>");
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
		} else {
			alert("Invalid Captcha. try Again");
			createCaptcha();
		}


	};
*/

	$scope.accountActivation = function() {

		if (loginFormValidate("fgpwd") == "TRUE") {
			console.log($scope.loginform);

			$http.post(urlBase + 'dms/userFagPass', $scope.loginform).
				success(function(data) {
					if (data.response == "TRUE") {

						window.location.href = urlBase + "views/accountActivation";
					} else if (data.response == "FALSE") {
						$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" + data.data + "</div>");
					}
				}).
				error(function(data, status, headers, config) {
					$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
					console.log("Error in getting Login details");
				});
		}
	};

	$scope.loading = true;

	// Initialize scope variables
/*	$scope.loading = false;*/
	$scope.otpSent = false;
	$scope.timerRunning = false;
	$scope.timeLeft = 0;

	let otpTimer = null;
	

	// Send OTP
	$scope.sendOtp = function() {
		
	    if ($scope.timerRunning) return; // prevent multiple clicks

	    $scope.loading = true;
		
		let requestData = null;

		  if ($scope.loginshow && $scope.loginform && $scope.loginform.username) {
		      requestData = $scope.loginform;
			  console.log("****************from login form************"+requestData);
		  } 
		  else if ( $scope.forgotpwd && $scope.forgotpwd.username) {
		      requestData = $scope.forgotpwd;
			  console.log("--------------from forgot form------------"+requestData);
		  } 
		  else {
		      showError("Please enter your username first!");
		      $scope.loading = false;
		      return;
		  }
		

	    var response = $http.post(urlBase + 'genearteOTP', requestData);
	    response.success(function(data) {
	        if (data.response === "TRUE") {
				if (data.modelData && data.modelData.um_mobile) {
				    const mob = data.modelData.um_mobile;
				    const masked =
				        mob.length >= 2
				            ? "********" + mob.slice(-2)
				            : "********";
				    alert("OTP has been sent to your registered mobile number " + masked);
				} else {
				    alert("OTP sent successfully!");
				}

	            if (data.modelData.adv_id != null) {
	                $scope.register.mobile = data.modelData.mobile;
	                registraionFormValidateotpform();
	            } else if ($scope.register.type === 'inperson') {
	                registraionFormValidateotpform();
	            } else {
	                validateotpform();
	            }

	            // Start timer for 1 minute (60 seconds)
	            startOTPTimer(600);
	        } else {
	            showError(data.data);
	        }

	        $scope.loading = false;
	    }).error(function() {
	        showError("Oops! Something went wrong.");
	        $scope.loading = false;
	    });
	};

	// Timer function
	function startOTPTimer(seconds) {
	    if (otpTimer) {
	        $interval.cancel(otpTimer);
	    }

	    $scope.timeLeft = seconds;
	    $scope.timerRunning = true;
	    $scope.otpSent = true;

	    otpTimer = $interval(function() {
	        if ($scope.timeLeft > 0) {
	            $scope.timeLeft--;
	        } else {
	            $interval.cancel(otpTimer);
	            otpTimer = null;
	            $scope.timerRunning = false;
	        }
	    }, 1000);
	}

	// Format time as mm:ss
	$scope.formatTime = function(seconds) {
	    const minutes = Math.floor(seconds / 60);
	    const remainingSeconds = seconds % 60;
	    return (
	        (minutes < 10 ? '0' + minutes : minutes) +
	        ':' +
	        (remainingSeconds < 10 ? '0' + remainingSeconds : remainingSeconds)
	    );
	};

	// Show error helper
	function showError(message) {
	    $(".msg_div").html(
	        "<div class='alert alert-danger alert-dismissible' role='alert'>" +
	        "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
	        "<span aria-hidden='true'>&times;</span></button>" + message + "</div>"
	    );
	    setTimeout(() => $(".msg_div").html(""), 5000);
	}



	$scope.updatepassword = function() {
		const isValid = validatePassword($scope.user.newpassword);
		if (isValid) {
			console.log("Password must contains atleast one alphabet,special characters and number");

			var response = $http.post(urlBase + 'updatepassword', $scope.user);
			response.success(function(data, status, headers, config) {
				if (data.response == "TRUE") {
					alert("Password Updated Successfully Try to login");
					location.reload(true);
				}
				else {
					alert("Password Mismatch");
				}
			});
			response.error(function(data, status, headers, config) {
				alert("Error");
			});
		} else {
			$("#registerpassword").parent().addClass('has-error');
			$scope.validate = "FALSE";
			alert("Password must contains atleast one alphabet,special characters and number.");
			console.log("Password is not valid.");
		}

	};

	function newPasswordForm(user) {
		$scope.validOTP = true;
		$scope.otptext = false;
		$scope.validateotp = false;
		$scope.user = user;
	}
	$scope.validateOtp = function() {

		/*if(loginFormValidate("fgpwd")=="TRUE"){	*/
		console.log($scope.forgotpwd);

		var response = $http.post(urlBase + 'validateOtp', $scope.forgotpwd);
		response.success(function(data, status, headers, config) {
			if (data.response == "TRUE") {
				
				/*if (data.advData.adv_id != null) {
					$scope.afterOtpForm();
				}*/
				
				if(data.advData!=null){
					$scope.afterOtpForm();
				}
				

				else if ($scope.register.type == 'inperson') {
					alert("Successfully Registered");
					$scope.loginForm();
				}
				else {
					newPasswordForm(data.data);
				}

				/*$(".msg_div").html("<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
				setTimeout(function() {
				 $(".msg_div").html("");
				}, 5000);
		  	
				$('.login').show();
				$('.register').hide();*/

				//window.location.href=urlBase+"views/forgetPassword";
			} else if (data.response == "FALSE") {
				$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" + data.data + "</div>");
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