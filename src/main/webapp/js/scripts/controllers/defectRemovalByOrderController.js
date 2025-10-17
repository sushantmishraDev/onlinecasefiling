
var EDMSApp = angular.module('EDMSApp', ['ngFileUpload','ngMask','ui.bootstrap']);

EDMSApp.controller('DefectRemovalByOrderController',['$scope','$http','Upload',function ($scope,$http,Upload) {
	var urlBase="/onlinecasefiling/";
	$scope.users=[];
	$scope.searchedusers=[];
	
	$scope.model={};
	$scope.user_id= $('#user_id').val();
	getAmendments();
	getAmendmentsHistory();
	function getAmendments(){		
	  var response =$http.get(urlBase+'defectRemovalByOrder/getCreated/'+$scope.user_id);
	  response.success(function(data, status, headers, config){				
		   if(data.response=="TRUE"){
					$scope.defectRemovalByOrder=data.modelList;
		   }
		   console.log($scope.amendments);
	  });	  			
	}
	
	function getAmendmentsHistory(){
		  var response =$http.get(urlBase+'defectRemovalByOrder/gethistory/'+$scope.user_id);
			response.success(function(data, status, headers, config){				
				   if(data.response=="TRUE"){
					$scope.amendmentshistory=data.modelList;
				   }
	  });	  
	 }
	$scope.setModel=function(drp){
		
			$scope.drp_id=drp.drp_id;
		
		
}
	$scope.save=function(){
		$scope.amendment={'drp_id':$scope.drp_id};
		var file=$scope.picFile;
		  var flag=confirm("Are you sure");
		  if (flag)
		  {
			  if(file!="")
			  {				  
			    file.upload = Upload.upload({
			      /*url: urlBase + 'amendmenthistory/upload',*/
			    	
			      url: urlBase + 'defectRemovalByOrder/uploadPetition',
			      headers: {
			    	  'optional-header': 'header-value'
			        },
	  		   file:file,
	  		   fields:$scope.amendment,
			    });
	
			    file.upload.then(function (response) {
			        if(response.data.response=="TRUE"){
			        	$scope.errorlist =null;
			        	alert("Successfully uploaded document");
			        	$("#uploadModel").modal("hide");
			        	window.location.reload();
			        	$scope.picFile='';	        	
			        }else{
			        	alert(response.data.data);
			        }
			      }, function (response) {
			        
			      }, function (evt) {
			        // Math.min is to fix IE which reports 200% sometimes
			        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			      });
	
			      file.upload.xhr(function (xhr) {
			        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
			      });
			  }
		  }else{
			  alert("Please select valid PDF file for upload")
		  }
		}
}]);