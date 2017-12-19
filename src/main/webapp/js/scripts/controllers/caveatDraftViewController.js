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





EDMSApp.controller('caveatDraftViewController',['$scope','$http',function ($scope, $http) {
 
 
  var urlBase="/onlinecasefiling/"
  



	    $scope.show = false;


	    $scope.count = '';
	    $scope.registerCase = {};


	    $scope.petitionerDataList = [];
	    $scope.respondentDataList = [];
	    $scope.courtFeeList = [];
	    $scope.trialDataList = [];
	    $scope.impugnedDataList = [];
	    $scope.draftList = [];
	    $scope.uploadedDocumentsList = [];
	    $scope.checkList = [];
	    $scope.checkListObj = [];
	    $scope.chckedIndexs = [];
	    
	    $scope.submitForm=function(caveat)
	    {
	    	var confirmbox = confirm("Do you really want to submit this caveat");
	        if (confirmbox) 
	        {
			  var response =$http.post(urlBase+'caveat/submitCaveatCase',caveat);
				response.success(function(data, status, headers, config){
					   if(data.response=="TRUE"){					   
						alert("Caveat Submitted Successfully!");
						window.location.reload();
						$scope.caveat=data.modelData;
					   }else{
						   alert(data.data);  
					   }		
				});	
	        }
		 }
	    
	    $scope.getCaveatList=function(){
		  	$http.get(urlBase+'caveat/getCaveatList').success(function (data) {
	    	$scope.count=data.data;
	      	$scope.caveatList=data.modelList;    	  
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting tree data");
	      });
		}
	    $scope.viewDetails=function(id){
			window.open(urlBase+"caveat/caveatDraftView/"+id,'_blank');
		  
	    }
	    $scope.caseId = $('#caseId').val();
	    $scope.previewDetails=function(id){
			window.open(urlBase+"caveat/previewList/"+id,'_blank');		  
	    }
	    
	    if ($scope.caseId != null && $scope.caseId != undefined)
	        getRegisterCaveat($scope.caseId);

	    function getRegisterCaveat(id) {

	        $http.get(urlBase + 'caveat/getRegisterCaveat', {
	            params: {
	                'docId': id
	            }
	        }).success(function(data, status, headers, config) {

	            $scope.caveat = data.modelData;
	            getEditData();
	        }).error(function(data, status, headers, config) {});

	    }

	    function getEditData() {
	        getCaveatPetitioner($scope.caseId);
	        getCaveatRespondent($scope.caseId);
	        getCourtFee($scope.caseId);
	        getUploadedDocuments($scope.caseId);
	        getRemarkList();
	    }


	    function getCaveatPetitioner(id) {

	        $http.get(urlBase + 'caveat/getCaveatPetitioner', {
	            params: {
	                'docId': id
	            }
	        }).success(function(data, status, headers, config) {

	            $scope.petitionerDataList = data.modelList;

	        }).error(function(data, status, headers, config) {});

	    }


	    function getCaveatRespondent(id) {
	        $http.get(urlBase + 'caveat/getCaveatRespondent', {
	            params: {
	                'docId': id
	            }
	        }).success(function(data, status, headers, config) {

	            $scope.respondentDataList = data.modelList;

	        }).error(function(data, status, headers, config) {});

	    }



	    function getActDetails(id) {
	        $http.get(urlBase + 'caveat/getActDetails', {
	            params: {
	                'docId': id
	            }
	        }).success(function(data, status, headers, config) {

	            $scope.actDataList = data.modelList;

	        }).error(function(data, status, headers, config) {});

	    }



	    function getCourtFee(id) {
	        $http.get(urlBase + 'caveat/getCourtFee', {
	            params: {
	                'docId': id
	            }
	        }).success(function(data, status, headers, config) {
	            $scope.courtFeeList = data.modelList;



	        }).error(function(data, status, headers, config) {});

	    }

	    function getUploadedDocuments(id) {

	        $http.get(urlBase + 'caveat/getUploadedDocuments', {
	            params: {
	                'rcd_id': id
	            }
	        }).
	        success(function(data) {
	            $scope.uploadedDocumentsList = data.modelList;
	            console.log($scope.uploadedDocumentsList);
	        }).
	        error(function(data, status, headers, config) {
	            console.log("Error in getting tree data");
	        });
	    };

	    $scope.onFileSelect = function($files) {
	        $scope.uploadProgress = 0;
	        $scope.selectedFile = $files;
	    };

	    function getRemarkList() {
	        $http.get(urlBase + 'scrutiny/getCheckList').success(function(data, status, headers, config) {
	            $scope.checkList = data.modelList;

	        }).error(function(data, status, headers, config) {});

	    }


	}]);