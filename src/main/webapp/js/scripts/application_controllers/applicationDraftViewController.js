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





EDMSApp.controller('applicationDraftViewController',['$scope','$http',function ($scope, $http) {
 
 
  var urlBase="/onlinecasefiling/"
  

	
    $scope.show=false;
  

  $scope.count='';
 $scope.registerCase={};
 
 $scope.radioSelect="draft";

  $scope.courtFeeList=[];

  $scope.applicationList=[];
 
 
  $scope.caseId= $('#caseId').val();
 
              getApplicationDetails();
  
      $scope.submitForm=function(application){
    	 var confirmbox = confirm("Do you really want to submit this application");
    	 if (confirmbox) 
    	 {
    		 var response =$http.post(urlBase+'application/submitApplication',application);
    		 response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){					   
					alert(" Application Submitted Successfully!");
					window.location.reload();
					$scope.application=data.modelData;
				   }else if(data.response=="FALSE"){					   
					   alert(data.data);
					   }				
			});	
    	   }
	  }
      
      $scope.changeCase=function(selected){
    	  console.log(selected);
    	  $scope.applicationList=[];
    	  if(selected=="passed"){
    			$http.get(urlBase+'application/getPassedApplicationDetails').
    		      success(function (data) {
    		    	  
    		      	$scope.count=data.data;
    		      	$scope.applicationList=data.modelList;
    		      	console.log($scope.applicationList);
    		    	  
    		      }).
    		      error(function(data, status, headers, config) {
    		      	console.log("Error in getting tree data");
    		      });
    	  }
    	  else if(selected=="defect"){
    		  $http.get(urlBase+'application/getDefectedApplicationDetails').
		      success(function (data) {
		    	  
		      	$scope.count=data.data;
		      	$scope.applicationList=data.modelList;
		      	console.log($scope.applicationList);
		    	  
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
    	  }
    	  else{
    		  getApplicationDetails();
    	  }
      }
  
  function getApplicationDetails(){
	  	$http.get(urlBase+'application/getApplicationDetails').
      success(function (data) {
    	  
      	$scope.count=data.data;
      	$scope.applicationList=data.modelList;
      	console.log($scope.applicationList);
    	  
      }).
      error(function(data, status, headers, config) {
      	console.log("Error in getting tree data");
      });
	}
  
  
  
  $scope.viewDetails=function(id){
	  debugger
		window.open(urlBase+"application/applicationDraftView/"+id,'_blank');
	  
}


$scope.previewDetails=function(id){
window.open(urlBase+"application/previewList/"+id,'_blank');
}


	
	
if($scope.caseId!=null && $scope.caseId!=undefined){
	getRegisterCase($scope.caseId);
    getCourtFee($scope.caseId);
    getUploadedDocuments($scope.caseId);
}

function getRegisterCase(id){
$http.get(urlBase+ 'ecourt_add_case/getRegisterCase', {
	params : {
		'docId' : id
	}
}).success(function(data, status, headers, config) {
            debugger
          $scope.registerCase = data.modelData;
            var date=new Date(parseInt($scope.registerCase.rcd_date_of_section4));
			$scope.registerCase.rcd_date_of_section4=("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear();
			

}).error(function(data, status, headers, config) {
});

}


























	function getPetitioner(id){
    $http.get(urlBase+ 'ecourt/getPetitioner', {
		params : {
			'docId' : id
		}
	}).success(function(data, status, headers, config) {
                debugger
              $scope.petitionerDataList = data.modelList;
               
}).error(function(data, status, headers, config) {
	});

}
	
	
	function getRespondent(id){
	    $http.get(urlBase+ 'ecourt/getRespondent', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	                debugger
	              $scope.respondentDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}




	function getActDetails(id){
	    $http.get(urlBase+ 'ecourt/getActDetails', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	                debugger
	              $scope.actDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}


	
	function getImpugnedOrder(id){
	    $http.get(urlBase+ 'ecourt/getImpugnedOrder', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.impugnedDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}

     
	
	function getTrialCourt(id){
	    $http.get(urlBase+ 'ecourt/getTrialCourt', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.trialDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
	
	
	
	function getCourtFee(id){
	    $http.get(urlBase+ 'ecourt/getCourtFee', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.courtFeeList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
	
	function getUploadedDocuments(id){
	   	 
		$http.get(urlBase+'ecourt_add_case/getUploadedDocuments', {params : {'rcd_id' : id}}).
        success(function (data) {
        	$scope.uploadedDocumentsList=data.modelList;
        	console.log($scope.uploadedDocumentsList);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	
	
	$scope.showDocument=function(selectedfile){
		var response = $http.get(urlBase+'scrutiny/copyFile',{params: {'if_id': selectedfile.indexField.if_id,'dsp_name': selectedfile.dsp_document_name}});
		response.success(function(data, status, headers, config) {		
			if(data.data != null)
			{
				window.open(urlBase+"/uploads/"+data.data,'_blank');
			}
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	};


	$scope.getStages = function(application) {
		$scope.application = application;
		console.log("jhdbfjsbjf sj fjs",application.ap_id);
		$http.get(urlBase + 'application/getApplicationStages', {
			params : {
				'docId' : application.ap_id
			}
		}).success(function(data, status, headers, config) {
			$scope.applicationHistory = data.modelList;
		}).error(function(data, status, headers, config) {
		});
	}













	
 /* $scope.viewDetails=function(id){
		window.open(urlBase+"ecourt_add_case/draftView/"+id,'_blank');
	  
  }
  
  
  $scope.previewDetails=function(id){
  window.open(urlBase+"ecourt/previewList/"+id,'_blank');
  }
  
  
  
  
 
  
 
  
 
  alert(caseId);

  
	function getRegisterCase(id){
		debugger
    $http.get(urlBase+ 'ecourt_add_case/getRegisterCase', {
		params : {
			'docId' : id
		}
	}).success(function(data, status, headers, config) {
                debugger
              $scope.registerCase = data.modelData;
                var date=new Date(parseInt($scope.registerCase.rcd_date_of_section4));
    			$scope.registerCase.rcd_date_of_section4=("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear();
    			

}).error(function(data, status, headers, config) {
	});

}
	
	
	
	
  
  
  */
  
  
  
  
  
  
  
}]);