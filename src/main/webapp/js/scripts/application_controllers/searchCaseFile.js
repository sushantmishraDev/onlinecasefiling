
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
	 
	 $scope.searchCaseFile=function(){
		 $http.get(urlBase+'searchcasefile/searchCaseFile', {params : {'case_year' :$scope.model.fd_case_year,'case_type' :$scope.model.fd_case_type,'case_no' :$scope.model.fd_case_no}}).
	        success(function (data) {
	        	
	        	if(data.modelList.length>0){
	        	$scope.caseFileList=data.modelList;
	        	}else{
	        		alert("Case not found...!");
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