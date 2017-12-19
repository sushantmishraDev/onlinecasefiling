
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





















EDMSApp.controller('addCaveatlController',['$scope','$http','Upload',function ($scope, $http,Upload) {

	var urlBase="/onlinecasefiling/";
	
	$scope.petitionerDataList=[];
	$scope.respondentDataList=[];
	$scope.courtFeeList=[];
	$scope.formShow=true;
	$scope.petitionerDetails={};
	$scope.registerCase={};
	
	$scope.caveat={};
	
	$scope.caseDetail={};
	$scope.tabShow1=true;
	$scope.tabShow2=false;
	$scope.tabShow3=false;
	$scope.tabShow4=false;
	$scope.tabShow5=false;
	$scope.tabShow6=false;

	
	
	
	
	
	
	
	$scope.stateList=[];
	$scope.districtList=[];
	
	
	getStateList();
	
	 function getStateList()
	 {
			$http.get(urlBase+'ecourt/getStateList').
	        success(function (data) {
	        	$scope.stateList=data.modelList;
	        	console.log($scope.petitionerDataList);
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
		};
		
		
		getDistrictList();
		
		 function getDistrictList()
		 {
				$http.get(urlBase+'ecourt/getDistrictList').
		        success(function (data) {
		        	$scope.districtList=data.modelList;
		        	
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
			};
			getActMasters();
			$scope.stateActList=[];
			$scope.centralActList=[];
			function getActMasters()
			{
					$http.get(urlBase+'ecourt/getActMasterList').
			        success(function (data) {
			        	$scope.actDetailsList=data.modelList;
			        	
			        	angular.forEach($scope.actDetailsList, function(value, key) {
			        		  if(value.act_type=="C"){
			        			  $scope.centralActList.push(value);
			        		  }
			        		  if(value.act_type=="S"){
			        			  $scope.stateActList.push(value);
			        		  }
			        		});
			        	
			        }).
			        error(function(data, status, headers, config) {
			        	console.log("Error in getting tree data");
			        });
			};
			
	
			
			getCaseTypes();
			
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
				
				getLowerCourtCaseTypes();
				
				 function getLowerCourtCaseTypes()
				 {
						$http.get(urlBase+'ecourt/getLowerCourtCaseTypes').
				        success(function (data) {
				        	$scope.lowerCourtCaseTypes=data.modelList;
				        	
				        }).
				        error(function(data, status, headers, config) {
				        	console.log("Error in getting tree data");
				        });
					};
					getLowerCourtList();
					
					 function getLowerCourtList()
					 {
							$http.get(urlBase+'ecourt/getLowerCourtList').
					        success(function (data) {
					        	$scope.lowerCourtList=data.modelList;
					        	
					        }).
					        error(function(data, status, headers, config) {
					        	console.log("Error in getting tree data");
					        });
						};
				
				
				
				
				
				
				$scope.open1 = function($event,type) {
				    $event.preventDefault();
				    $event.stopPropagation();
				    
				    if(type=="fromDate1")
				    	$scope.fromDate1= true;
				    if(type=="toDate1")
				    	$scope.toDate= true;
				};
				
				
				
							
				
				
				
			
				$scope.toggleMax = function() {
				    //$scope.minDate = $scope.minDate ? null : new Date();
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
				
				/*function convertDate(inputFormat) 
				{
					  function pad(s) { return (s < 10) ? '0' + s : s; }
					  var d = new Date(inputFormat);
					  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
				}
	*/
	
	
	
	
	
	
	
	
	
	
	/** ***********NEW CASE STARTS HERE*********************************** */
	
	
	$scope.draftId= $('#draftId').val();
	
	
	   
	if($scope.draftId!=null && $scope.draftId!=undefined)
	getRegisterCaveat($scope.draftId);

			function getRegisterCaveat(id){
				
		    $http.get(urlBase+ 'caveat/getRegisterCaveat', {
				params : {
					'docId' : id
				}
			}).success(function(data, status, headers, config) {
		                
		                $scope.caveat = data.modelData;
		                getEditData();
		}).error(function(data, status, headers, config) {
			});

		}

			function getEditData(){
			if($scope.draftId!=null && $scope.draftId!=undefined){
			getCaveatPetitioner($scope.draftId);
			getCaveatRespondent($scope.draftId);
			getCourtFee($scope.draftId);
			getUploadedDocuments($scope.draftId);
			}
			}
			
			
			function getCaveatPetitioner(id){
				
			    $http.get(urlBase+ 'caveat/getCaveatPetitioner', {
					params : {
						'docId' : id
					}
				}).success(function(data, status, headers, config) {
			                
			              $scope.petitionerDataList = data.modelList;
			               
			}).error(function(data, status, headers, config) {
				});

			}
				
			
				function getCaveatRespondent(id){
				    $http.get(urlBase+ 'caveat/getCaveatRespondent', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				                
				              $scope.respondentDataList = data.modelList;
				               
				}).error(function(data, status, headers, config) {
					});

				}

				
				
				function getActDetails(id){
				    $http.get(urlBase+ 'caveat/getActDetails', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				                
				              $scope.actDataList = data.modelList;
				               
				}).error(function(data, status, headers, config) {
					});

				}


				
				function getCourtFee(id){
				    $http.get(urlBase+ 'caveat/getCourtFee', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				              $scope.courtFeeList = data.modelList;
				        
				              
				              
				}).error(function(data, status, headers, config) {
					});

				}

				
				  
		
	
	$scope.addCaveat=function(caveat){
		
		var response =$http.post(urlBase+'caveat/addCaveat',caveat);
		response.success(function(data, status, headers, config){
			   if(data.response=="ADD"){
				   
				alert("Caveat Added Successfully!");
				$scope.caveat=data.modelData;
			   }
			   else if(data.response=="UPDATE"){
				   
				alert("Caveat Updated Successfully!");
			   }
	
			
		});	
	}



$scope.ShowId = function(event)
{
	
	$scope.tabShow1=true;
	$scope.tabShow2=true;
	$scope.tabShow3=true;
	$scope.tabShow4=true;
	$scope.tabShow5=true;
	$scope.tabShow6=true;
	$scope.tabShow7=true;
	$scope.tabShow=true;
};
	
$scope.nextTab=function(event){
}


	
	
$scope.addShow=true;
	
	

		
		$scope.editPet=function(row){
			$scope.petitionerDetails=row;
		}
		$scope.registerCase.rcd_id;
		
		$scope.addPetitioner=function(PetitionerDetails){
			if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
			{
				alert("First add Caveat details");
				return false;
			}
			PetitionerDetails.cpt_cav_mid=$scope.caveat.cav_id;
			var response =$http.post(urlBase+'caveat/addPetitioner',PetitionerDetails);
			response.success(function(data, status, headers, config){
				
				   if(data.response=="CREATE"){
					alert("Petitioner Added Successfully!");
					$scope.petitionerDetails={};
					 if($scope.draftId!=null && $scope.draftId!=undefined)
				     {
						 getCaveatPetitioner($scope.draftId);
				     }
					 else{
					getCaveatPetitioner($scope.caveat.cav_id);
					 }
					
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.response=="UPDATE"){
						  alert("Petitioner Updated Successfully!");
						  $scope.petitionerDetails={};
							if($scope.draftId!=null && $scope.draftId!=undefined){
							getCaveatPetitioner($scope.draftId);
							}
							else
								{
							getCaveatPetitioner($scope.caveat.cav_id);
								}
				   }
		
				
			});	
		}
		
		
		$scope.deletePetitioner=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'caveat/deletePetitioner/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("Petitioner Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
					getCaveatPetitioner($scope.draftId);
					}
					else{
					getCaveatPetitioner($scope.caveat.cav_id);
				 	
				   }
				   }
				
			});	
		}
		
		}
	
		
		
	
		
		
		
		/**
		 * *************************PETITIONER DETAILS ENDED
		 * HERE****************************
		 */
		
		
		
		
	/**
	 * *************************** Respondent code starts
	 * here***************************************
	 */	
		
		
		$scope.respondentDetails={};
		
		$scope.editResp=function(row){
			$scope.respondentDetails=row;
			
		}
		

		$scope.addRespondent=function(respondentDetails){
			if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
			{
				alert("First add Caveat details");
				return false;
			}
			respondentDetails.crt_cav_mid=$scope.caveat.cav_id;
			var response =$http.post(urlBase+'caveat/addRespondent',respondentDetails);
			response.success(function(data, status, headers, config){
				
				   if(data.response=="CREATE"){
					alert("Respondent Added Successfully!");
					$scope.respondentDetails={};
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCaveatRespondent($scope.draftId);	
					}
					else{
					getCaveatRespondent($scope.caveat.cav_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.response=="UPDATE"){
						 alert("Respondent Updated Successfully!");
						 $scope.respondentDetails={};
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCaveatRespondent($scope.draftId);	
							}
							else{
							getCaveatRespondent($scope.caveat.cav_id);
							}
							
				   }
		
				
			});	
		}

		$scope.deleteRespondent=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'caveat/deleteRespondent/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("Respondent Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCaveatRespondent($scope.draftId);	
					}
					else{
					getCaveatRespondent($scope.caveat.cav_id);
					}
				 	
				   }
				
			});	
		}
			 
		}
		
		
		
		/**
		 * ***********************RESPONDENT DETAILS ENDED
		 * HERE******************
		 */
		
		
		
		/** ************************court fee start********************** */
		
		
		$scope.caveatFee={};

		 $scope.editCourtFee=function(row){
			 $scope.caveatFee=row;
			 
		 }
		$scope.addCourtFee=function(){
			if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
			{
				alert("First add Caveat details");
				return false;
			}
			
			/*$scope.model={};
			$scope.model.cf_date=$scope.courtFee.cf_date;
			$scope.model.cf_amount=$scope.cocaveatFeef_amount;
			$scope.model.cf_receipt_no=$scope.courtFee.cf_receipt_no;
			$scope.model.cf_rcd_mid=$scope.caveat.cav_id;*/
			
			$scope.caveatFee.ccf_cav_mid=$scope.caveat.cav_id;
			
			var response =$http.post(urlBase+'caveat/addCourtFee',$scope.caveatFee);
			response.success(function(data, status, headers, config){
				   if(data.response=="CREATE"){
					alert("CourtFee Added Successfully!");
					$scope.caveatFee={};
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCourtFee($scope.draftId);	
					}
					else{
						getCourtFee($scope.caveat.cav_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.response=="UPDATE"){
						  alert("CourtFee Updated Successfully!");
							$scope.caveatFee={};
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCourtFee($scope.draftId);	
							}
							else{
								getCourtFee($scope.caveat.cav_id);
							}
				   }
		
				
			});	
		}
		
		
		$scope.lowerCourtShow=true;
		$scope.highCourtShow=false;
		$scope.changeCourt=function(event){
			if(event.target.id=="lowerCourt"){
				$scope.lowerCourtShow=true;
				$scope.highCourtShow=false;
		
				
			}
			else{
				$scope.highCourtShow=true;
				$scope.lowerCourtShow=false;
			}
		};
		
		
		
	$scope.criminalShow=false;
	$scope.changeCase=function(event)
{
		if(event.target.id=="civil"){
			
			$scope.criminalShow=false;
			
		}
		else{
			
			$scope.criminalShow=true;
			
		}
		
};



	
	
	 $scope.caseTypeOptions= [
			      {id: '1', value: 'District  & Session Judge'},
			      {id: '2', value: 'pecial Judge SC & ST Act'},
			      {id: '3', value: 'Add. Distt. Judge'}
			    ];
	 
		$scope.caseDetail= $scope.caseTypeOptions[0].value;
		
		////////////////////////////////////////////Document Upload Start//////////////////////////////////////////////
		
		$scope.indexField={};
		$scope.picFile='';
	
		
		$scope.selectedNode="";
		
		$scope.showSelected = function(sel)
	    {
	        $scope.selectedNode = sel;
	    };
	    getLowerCourtList();
		
		 function getLowerCourtList()
		 {
				$http.get(urlBase+'ecourt/getLowerCourtList').
		        success(function (data) {
		        	$scope.lowerCourtList=data.modelList;
		        	
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
			};    
	
			function getUploadedDocuments(id){
		    	 
					$http.get(urlBase+'caveat/getUploadedDocuments', {params : {'rcd_id' :id}}).
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
				if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
				{
					alert("First add Caveat details");
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
				      url: urlBase + 'caveat/upload_file',
				      params:{rcd_id: $scope.caveat.cav_id,if_id:$scope.indexField.if_id},
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
				        //	$scope.errorlist =null;
				        	$scope.errorlist = response.data.dataList;
				        	console.log($scope.errorlist);
				        	//bootbox.alert("Uploading Done");
				        //	$("#documentCreate").modal("hide");
				        	$scope.document={};
				        					        			alert("Files Uploaded successfully...");
				        			getUploadedDocuments($scope.caveat.cav_id);
				        		
				        			$scope.picFile="";
				        }else{
				        	$scope.errorlist = response.data.dataMapList;
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
					url : urlBase + 'caveat/deleteDocument/' + id + '/'
				}).success(function(res) {
					   if(res.response=="TRUE"){
						   alert(" Document Deleted Successfully!");
						if($scope.caveat.cav_id!=null && $scope.caveat.cav_id!=undefined){
							if($scope.draftId!=null && $scope.draftId!=undefined)
						     {
								getUploadedDocuments($scope.draftId);
						     }
							 else{
								 getUploadedDocuments($scope.caveat.cav_id);
							 }
						}
						
					   }
					
				});	
			}
			
			}
		
		
		/////////////////Document Upload Close///////////////
	
	 
	 
	
}]);