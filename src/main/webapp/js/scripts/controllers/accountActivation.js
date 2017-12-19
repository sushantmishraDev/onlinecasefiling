var pdmsApp = angular.module("pdmsApp", [ 'smart-table', 'ui.bootstrap',
		'ng-bootstrap-datepicker', 'ui.bootstrap.modal']);



pdmsApp.controller("securityQuestionCtrl", function($scope, $http) {

	$scope.securityquestionData = [];
	$scope.errorlist = null;
	$scope.answerDetail = {};
	$scope.answerDetails = [];
	$scope.ansDetail = {};
	$scope.ansDetails = [];
	$scope.questDetail = {};
	$scope.passDetails;
	$scope.questDetails = [];
	$scope.questDetails2 = [];
	$scope.qs1;
	$scope.qs2;
	$scope.password;
	$scope.passwordc;
	

	getSecurityQuestionDD();

	function getSecurityQuestionDD() {
		$http.get('/dms/getquestionList').success(
				function(data) {
					$scope.securityquestionData = data.securityquestionData;
					console.log($scope.securityquestionData);
					console.log("***********bbbb");
					console.log(data.securityquestionData);
					$.each($scope.securityquestionData, function(k, v) {
						$scope.answerDetail = {
							'pra_question_lid' : v.lk_id,
							'qest' : v.lk_longname,
							'pra_answer' : ''
						};
						$scope.answerDetails.push($scope.answerDetail);
						console.log($scope.answerDetails);
					});

					/*console.log($scope.answerDetails[Math.floor(Math.random() * ($scope.answerDetails.length))] + $scope.answerDetails[Math.floor(Math.random() * ($scope.answerDetails.length))]);*/
					console.log($scope.answerDetails);

					
					
					$scope.qs1=(Math.floor(Math.random() * ($scope.answerDetails.length)));
					$scope.qs2=(Math.floor(Math.random() * ($scope.answerDetails.length)));
					
					if($scope.qs1 == $scope.qs2)
						{
							if($scope.qs1==0)
								{
								$scope.qs1=1;
								}
							else
								{
								$scope.qs1=0;
								}
						
						}
					
					
				/*	$scope.questDetails2.push($scope.answerDetails[Math
							.floor(Math.random()
									* ($scope.answerDetails.length))]);
					$scope.questDetails2.push($scope.answerDetails[Math
							.floor(Math.random()
									* ($scope.answerDetails.length))]);*/
					
					$scope.questDetails2.push($scope.answerDetails[$scope.qs1]);
					$scope.questDetails2.push($scope.answerDetails[$scope.qs2]);
					/*$scope.questDetails2.push($scope.answerDetails[0]);
					$scope.questDetails2.push($scope.answerDetails[1]);
					$scope.questDetails2.push($scope.answerDetails[2]);*/
					

				}).error(function(data, status, headers, config) {
			console.log("Error in getting property details");
		});

	}

	$scope.login = function() {
		window.location.href = "/dms/dms/logout";
	};

	console.log("********aaa");

	
	
	$scope.compareAnswer = function(data) {
		//alert(1);
		console.log(data);
		console.log("********");
/*		bootbox.alert(data+"as");
 * 
*/
	
		$.each(data, function(k, v) {
			$scope.ansDetail = {
				'pra_question_lid' : v.pra_question_lid,

				'pra_answer' : v.pra_answer,
			};
			$scope.ansDetails.push($scope.ansDetail);
			console.log($scope.ansDetails);
		});
		console.log($scope.ansDetails);

/*		bootbox.alert($scope.ansDetails+"as");
*/		var response = $http.post('/dms/accountActivation/compareAnswer',
				$scope.ansDetails);
		response.success(function(data, status, headers, config) {

			if (data.response == "TRUE") {

				//bootbox.alert("Your password is : "+data.data);
				$scope.passDetails = data.data;
				console.log($scope.passDetails);
				$scope.errorlist = [];
				bootbox.alert("Request Sent Successfully!");

			//	$('#pass_Modal').modal('show');

				
	
			} else {

				$scope.ansDetails = [];
				/*bootbox.alert("Enter correct answer");*/
				$scope.errorlist = {
					"pra_answer" : [ "Enter correct answer" ]
				};

			}

		});
		response.error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});

	}
});