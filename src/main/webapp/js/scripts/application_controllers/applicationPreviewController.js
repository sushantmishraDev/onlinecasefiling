
var EDMSApp = angular.module('EDMSApp', []);
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

EDMSApp.controller('applicationViewController',['$scope','$http',function ($scope, $http) {

	var urlBase="/onlinecasefiling/";
	
	$scope.petitionerDataList=[];
	$scope.respondentDataList=[];
	$scope.courtFeeList=[];
	$scope.formShow=true;
	$scope.petitionerDetails={};
	$scope.registerCase={};
	
	$scope.application={};
	
	$scope.caseDetail={};
	$scope.tabShow1=true;
	$scope.tabShow2=false;
	$scope.tabShow3=false;
	$scope.tabShow4=false;
	$scope.tabShow5=false;
	$scope.tabShow6=false;

	
	
	$scope.draftId= $('#caseId').val();

	
	   
	if($scope.draftId!=null && $scope.draftId!=undefined){
	getRegisterApplication($scope.draftId);
	getDrafdData();
}

			function getRegisterApplication(id){
				
		    $http.get(urlBase+ 'application/getRegisterApplication', {
				params : {
					'docId' : id
				}
			}).success(function(data, status, headers, config) {
		                
		                $scope.application = data.modelData;

		    			
		}).error(function(data, status, headers, config) {
			});

		}
			
			
			function getDrafdData(){
			if($scope.draftId!=null && $scope.draftId!=undefined){
				getCourtFee($scope.draftId);
				getUploadedDocuments($scope.draftId);
				getCheckList($scope.draftId);
				}
			}

			
	
		/** ************************court fee start********************** */
		
		
			function getCourtFee(id){
			    $http.get(urlBase+ 'application/getCourtFee', {
					params : {
						'docId' : id
					}
				}).success(function(data, status, headers, config) {
			              $scope.courtFeeList = data.modelList;
			              
			}).error(function(data, status, headers, config) {
				});

			}

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		////////////////////////////////////////////Document Upload Start//////////////////////////////////////////////
		
		$scope.indexField={};
		$scope.picFile='';
	
		
		
	    
	
			function getUploadedDocuments(id){
	    	 
					$http.get(urlBase+'application/getUploadedDocuments', {params : {'ap_id' :id}}).
			        success(function (data) {
			        	$scope.uploadedDocumentsList=data.modelList;
			        	console.log($scope.uploadedDocumentsList);
			        }).
			        error(function(data, status, headers, config) {
			        	console.log("Error in getting tree data");
			        });
				};
				$scope.checkList=[];
				function getCheckList(id){
				    $http.get(urlBase+ 'application/getCheckList', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				                
				              $scope.checkList = data.modelList;
				               
				}).error(function(data, status, headers, config) {
					});

				}
		/////////////////Document Upload Close///////////////
				$scope.showDocument=function(selectedfile){
					var response = $http.get(urlBase+'application/copyApplicationFile',{params: {'au_document_name': selectedfile.au_document_name}});
					response.success(function(data, status, headers, config) {		
						console.log(data);
						if(data.data != null)
						{
							window.open(urlBase+"/uploads/"+data.data,'_blank');
						}
					});
					response.error(function(data, status, headers, config) {
						bootbox.alert("Error");
					});
				};
	 
	 
	
}]);