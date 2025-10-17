
var EDMSApp = angular.module('EDMSApp', ['ui.bootstrap']);
EDMSApp.directive('loading', ['$http', function ($http) {
    return {
        restrict: 'A',
        link: function (scope, elm, attrs) {
            scope.isLoading = function () {
                return $http.pendingRequests.length > 0;
            };
            scope.$watch(scope.isLoading, function (v) {
                if (v) {
                    elm.show();
                } else {
                    elm.hide();
                }
            });
        }
    };
}]);


EDMSApp.directive('numbersOnly', function() {
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











EDMSApp.controller('searchCaseFileController',['$scope','$http',function ($scope, $http) {
	var urlBase="/onlinecasefiling/";
	
	getCaseTypes();
	$scope.model={};
	
	 function getCaseTypes()
	 {
			$http.get(urlBase+'ecourt/getCaseTypes').
	        success(function (data) {
	        	$scope.caseTypeList=data.modelList;
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
	 };
	 
	 
	 $scope.validateCode=function(validate){
		 validate.ae_code= Number(validate.ae_code);
			window.open(urlBase+"searchcasefile/validateApplicationCode/"+validate.ae_code+","+validate.ae_appno,'_self');
				/*var response =$http.post(urlBase+'ecourt_add_case/validateCode',code);
				response.success(function(data, status, headers, config){
					   if(data.response=="TRUE"){
						   $scope.ct_id=data.modelData.ae_case_type;
						window.open("/onlinecasefiling/ecourt/addNewCase","_self");
						//$scope.registerCase.rcd_ct_id=data.modelData;
					   }
					   else if(data.data=="Update"){
						   
						alert(" caseDetails Updated Successfully!");
					   }
			
					
				});	*/
			}
	 
	 
	 $scope.searchCaseFile=function(){
		 $http.get(urlBase+'searchcasefile/searchCaseFile', {params : {'case_year' :$scope.model.fd_case_year,'case_type' :$scope.model.fd_case_type,'case_no' :$scope.model.fd_case_no}}).
	        success(function (data) {
	        	
	        	if(data.response=="TRUE"){
	        	$scope.caseFileList=data.modelList;
	        	}else{
	        		//window.open(urlBase+"/ecourt/addApplication",'_self');
	        		alert("This Case is not Efiled");
	        		
	        	}
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
		 
	 }
	 
	  $scope.createApplication=function(data){
			window.open(urlBase+"application/add?fd_id="+data.fd_id,'_self');
		  
	}
	
}]);