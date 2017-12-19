
var EDMSApp = angular.module('EDMSApp', ['ngFileUpload','ui.bootstrap']);
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

EDMSApp.controller('addApplicationController',['$scope','$http','Upload',function ($scope, $http,Upload) {

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

	
	
	$scope.draftId= $('#draftId').val();
	$scope.fd_id= $('#fd_id').val();
	
	
	

	$scope.open1 = function($event,type) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    
	    if(type=="fromDate1")
	    	$scope.fromDate1= true;
	    if(type=="toDate1")
	    	$scope.toDate= true;
	};
	

	$scope.toggleMax = function() {
		$scope.maxDate = new Date();
	};
	$scope.toggleMax();				
	$scope.open = function($event,type) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    
	    if(type=="fromDate")
	    	$scope.fromDate= true;
	    if(type=="toDate")
	    	$scope.toDate= true;
	};
	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	    
	};
	
	$scope.formats = ['dd-MMMM-yyyy','dd-mm-yyyy', 'yyyy/MM/dd', 'dd-MM-yyyy', 'shortDate'];
	$scope.format = $scope.formats[3];
	
	$scope.filledByOptions= [
		       			      {id: 1, value: 'Petitioner'},
		       			      {id: 2, value: 'Respondent'},
		       			      {id: 3, value: 'Other'}
		       			    ];
	
	
	
	
				
				getApplicationTypes();
				
				 function getApplicationTypes()
				 {
						$http.get(urlBase+'application/getApplicationTypes').
				        success(function (data) {
				        	$scope.applicationTypeList=data.modelList;
				        }).
				        error(function(data, status, headers, config) {
				        	console.log("Error in getting ApplicationTypes data");
				        });
					};
				

	
	
	
	/** ***********NEW CASE STARTS HERE*********************************** */
	
	
	
	
	
	   
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
				}
			}

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

				
				  
		
	
	$scope.addApplication=function(application){
		if(application.ap_fd_mid==null){
			application.ap_fd_mid=$scope.fd_id;	
		}
		if(application.ap_fd_mid==null){
				alert("Case File Details not found");
				return false;
		}
		var response =$http.post(urlBase+'application/addApplication',application);
		response.success(function(data, status, headers, config){
			   if(data.response=="ADD"){
				   
				alert("Application Added Successfully!");
				$scope.application=data.modelData;
			   }
			   else if(data.response=="UPDATE"){
				   
				alert("Application Updated Successfully!");
			   }
	
			
		});	
	}



$scope.ShowId = function(event)
{
	$scope.tabShow1=true;
	$scope.tabShow2=true;
	$scope.tabShow3=true;
};
	
$scope.nextTab=function(event){
}



		
		
		
		/** ************************court fee start********************** */
		
		
		$scope.applicationFee={};

		 $scope.editCourtFee=function(row){
			 $scope.applicationFee=row;
			 
		 }
		$scope.addCourtFee=function(){
			if($scope.application.ap_id==null || angular.isUndefined($scope.application.ap_id))
			{
				alert("First add Application details");
				return false;
			}
			
			$scope.applicationFee.acf_ap_mid=$scope.application.ap_id;
			
			var response =$http.post(urlBase+'application/addCourtFee',$scope.applicationFee);
			response.success(function(data, status, headers, config){
				   if(data.response=="CREATE"){
					alert("CourtFee Added Successfully!");
					$scope.applicationFee={};
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCourtFee($scope.draftId);	
					}
					else{
						getCourtFee($scope.application.ap_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.response=="UPDATE"){
						  alert("CourtFee Updated Successfully!");
							$scope.applicationFee={};
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCourtFee($scope.draftId);	
							}
							else{
								getCourtFee($scope.application.ap_id);
							}
				   }
		
				
			});	
		}
		

		
		////////////////////////////////////////////Document Upload Start//////////////////////////////////////////////
		
		$scope.indexField={};
		$scope.picFile='';
	
		
		$scope.selectedNode="";
		
		$scope.showSelected = function(sel)
	    {
	        $scope.selectedNode = sel;
	    };
	    
	
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
			
			$scope.onFileSelect = function ($files) {
		        $scope.uploadProgress = 0;
		        $scope.selectedFile = $files;
		    };
		    $scope.files = [];

		    //listen for the file selected event
		    $scope.$on("fileSelected", function (event, args) {
		    	$scope.$apply(function () {            
		            $scope.files.push(args.file);
		        });
		    });
		    
			$scope.uploadFile=function() 
			{
					if($scope.application.ap_id==null || angular.isUndefined($scope.application.ap_id))
			{
				alert("First add Application details");
				return false;
			}
					var str=$scope.picFile.name;
					 var extn = str.split(".").pop();
					 extn=extn.toLowerCase();
					 if(extn!="pdf"){
						 alert("Only pdf format are allowed");
						 return false;
					 }	
				$scope.buttonDisabled=true;
				  var file=$scope.picFile;
				  if($scope.selectedNode!=""){
						$scope.document.folder_id=$scope.selectedNode.id;
					}
				    file.upload = Upload.upload({
				      url: urlBase + 'application/upload_file',
				      params:{ap_id: $scope.application.ap_id,if_id:$scope.indexField.if_id},
				      headers: {
				    	  'optional-header': 'header-value'
				        },
				       fields:$scope.document,
		    		   file:file,
				    });
		if(file.upload){
				    file.upload.then(function (response) {
				    	$scope.buttonDisabled=false;
				        if(response.data.response=="TRUE"){
				       
				        	$scope.document={};
				        	
				        
				        			alert("Files Uploaded successfully...");
				        			$scope.picFile='';
				        			getUploadedDocuments($scope.application.ap_id);
				        
				        }else{
				        	alert("Please reUpload file....");
				        }
				      }, function (response) {
				        
				      }, function (evt) {
				        // Math.min is to fix IE which reports 200% sometimes
				        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
				      });
				    
				    file.upload.xhr(function (xhr) {
				        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
				      });
			}else{$scope.buttonDisabled=false;}

				      
				}
		
			
			$scope.deleteDocument=function(id){
				  var result=confirm("Are you really want to Remove");
					if (result) {
				   $http({
					method : 'DELETE',
					url : urlBase + 'application/deleteDocument/' + id + '/'
				}).success(function(res) {
					   if(res.response=="TRUE"){
						   alert(" Document Deleted Successfully!");
						if($scope.application.ap_id!=null && $scope.application.ap_id!=undefined){
							getUploadedDocuments($scope.application.ap_id);
						}
						
					   }
					
				});	
			}
			
			}
		
		
		/////////////////Document Upload Close///////////////
	
	 
	 
	
}]);