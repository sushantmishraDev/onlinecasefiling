
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

EDMSApp.directive("select2", function($timeout, $parse) {
	  return {
	    restrict: 'AC',
	    require: 'ngModel',
	    link: function(scope, element, attrs) {
	      console.log(attrs);
	      $timeout(function() {
	        element.select2();
	        element.select2Initialized = true;
	      });

	      var refreshSelect = function() {
	        if (!element.select2Initialized) return;
	        $timeout(function() {
	          element.trigger('change');
	        });
	      };
	      
	      var recreateSelect = function () {
	        if (!element.select2Initialized) return;
	        $timeout(function() {
	          element.select2('destroy');
	          element.select2();
	        });
	      };

	      scope.$watch(attrs.ngModel, refreshSelect);

	      if (attrs.ngOptions) {
	        var list = attrs.ngOptions.match(/ in ([^ ]*)/)[1];
	        // watch for option list change
	        scope.$watch(list, recreateSelect);
	      }

	      if (attrs.ngDisabled) {
	        scope.$watch(attrs.ngDisabled, refreshSelect);
	      }
	    }
	  };
	});

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
	
	$scope.criminal={};
	
	$scope.extraCaveatImpugnedOrder={};
	$scope.extraImpugnedDataList=[];
	$scope.petitionerDataList=[];
	$scope.respondentDataList=[];
	$scope.extraCaveatorDataList=[];
	$scope.courtFeeList=[];
	$scope.formShow=true;
	$scope.petitionerDetails={};
	$scope.extraCaveatorDetails={};
	$scope.registerCase={};
	
	$scope.caveat={};
	
	$scope.caseDetail={};
	$scope.tabShow1=true;
	$scope.tabShow2=false;
	$scope.tabShow3=false;
	$scope.tabShow4=false;
	$scope.tabShow5=false;
	$scope.tabShow6=false;
	$scope.tabShow8=false;
	$scope.tabShow9=false;
	$scope.tabShow10=false;
	$scope.tabShow11=false;
	$scope.tabShow12=false;
	$scope.tabShow13=false;
	
	
	$scope.stateList=[];
	$scope.districtList=[];
	
	
	$scope.centralShow=true;
	$scope.stateShow=false;
	$scope.changeAct=function(event){
		if(event.target.id=="central"){
			$scope.centralShow=true;
			$scope.stateShow=false;
	
			
		}
		else{
			$scope.stateShow=true;
			$scope.centralShow=false;
		}
	};
	
	getPoliceStationList();
	
	 $scope.filterLct = function(court) {
     	if(court.lct_id==151 && court.lct_dt_mid==81){
     		/*console.log(court);*/
     	}
         return (court.lct_dt_mid === $scope.extraCaveatImpugnedOrder.ec_io_district);
     };
     
     $scope.filterLctCav = function(court) {
      	if(court.lct_id==151 && court.lct_dt_mid==81){
      		/*console.log(court);*/
      	}
          return (court.lct_dt_mid === $scope.caveat.cav_ord_dist);
      };
	
	 function getPoliceStationList()
	 {
			$http.get(urlBase+'police_station_master/getpoliceStationNewDetails').
	        success(function (data) {
	        	$scope.policeStationList=data;
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
		};
		
		$scope.editAct=function(data){
			$scope.actDetails=data;
			
		}
		
		
		$scope.deleteActDetails=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'caveat/deleteActDetails/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("  actDetails Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getActDetails($scope.draftId);	
					}
					else{
						getActDetails($scope.registerCase.rcd_id);
					}
					
				 	
				   }
		
				
			});	
		}
		
		}
	
	$scope.defaultDist=function(){
		$scope.caveat.cav_ord_dist=$scope.caveat.cav_dist_mid;
		$scope.extraCaveatImpugnedOrder.ec_io_district=$scope.caveat.cav_dist_mid;
	}
	
	$scope.deleteExtraImpugnedOrder=function(id){
		  var result=confirm("Are you really want to Remove");
			if (result) {
		   $http({
			method : 'DELETE',
			url : urlBase + 'caveat/deleteExtraImpugnedOrder/' + id + '/'
		}).success(function(res) {
			   if(res.response=="TRUE"){
		     alert("  Extra ImpugnedOrder Deleted Successfully!");
		     if($scope.draftId!=null && $scope.draftId!=undefined){
		    	 getExtraCaveatImpugnedOrder($scope.draftId);	
				}
				else{
					getImpugnedOrder($scope.registerCase.rcd_id);
				}
			   }
	
			
		});	
	}
	
	}
	
	$scope.editExtraImp=function(data){
		$scope.extraCaveatImpugnedOrder=data;
		
	}
	
	$scope.editExtraAuth=function(data){
		$scope.caveatAuthority=data;
		
	}
	
	function getExtraCaveatImpugnedOrder(id){
	    $http.get(urlBase+ 'caveat/getExtraCaveatImpugnedOrder', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.extraImpugnedDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}

	$scope.addExtraCaveatImpugnedOrder=function(extraCaveatImpugnedOrder){
		if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
		{
			alert("First add Caveat details");
			return false;
		}
		$scope.extraCaveatImpugnedOrder.ec_io_cav_mid=$scope.caveat.cav_id;
		var response =$http.post(urlBase+'caveat/addExtraCaveatImpugnedOrder',$scope.extraCaveatImpugnedOrder);
		response.success(function(data, status, headers, config){
			   if(data.response=="TRUE"){
				alert("Extra ImpugnedOrder Added Successfully!");
				$scope.impugnedOrder="";
				if($scope.draftId!=null && $scope.draftId!=undefined){
					getExtraCaveatImpugnedOrder($scope.draftId);	
				}
				else{
					getExtraCaveatImpugnedOrder($scope.caveat.cav_id);
				}
			   }
      		 // $scope.lookupdataList.unshift(data);
			   else if(data.data=="Update"){
					  alert(" impugnedOrder Updated Successfully!");
						$scope.extraCaveatImpugnedOrder="";
						if($scope.draftId!=null && $scope.draftId!=undefined){
							getExtraCaveatImpugnedOrder($scope.draftId);	
						}
						else{
							getExtraCaveatImpugnedOrder($scope.caveat.cav_id);
						}
						
			   }
	
			
		});	
	}
	
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
		
		$scope.getAvocateDetails=function(adv){
			 $http.get(urlBase+ 'caveat/getAdvocate', {
					params : {
						'docId' : adv
					}
				}).success(function(data, status, headers, config) {
			                
			                $scope.extraAdovate = data.modelData;
			               
			}).error(function(data, status, headers, config) {
				});
		}
		
		
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
			
			$scope.caveatCrimeDetails={};
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
	
	
	
				$scope.filterExpression = function(station) {
		            return (station.pt_dist_mid === $scope.caveatCrimeDetails.ccd_district);
		        };	
		        
		        $scope.crimeDetail={};
		        
		        $scope.addCrimeDetails=function(crimedetils){
					if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
					{
						alert("First add Caveat details");
						return false;
					}
					$scope.crimeDetail=crimedetils;
					$scope.crimeDetail.ccd_cav_mid=$scope.caveat.cav_id;
					var response =$http.post(urlBase+'caveat/addCrimeDetail',$scope.crimeDetail);
					response.success(function(data, status, headers, config){
						   if(data.response=="TRUE"){
							alert("Crime details Added Successfully!");
							$scope.crimeDetail={};
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCrimeDetail($scope.caveat.cav_id);	
							}
							else{
								getCrimeDetail($scope.caveat.cav_id);
							}
						   }
			      		 // $scope.lookupdataList.unshift(data);
						   else if(data.data=="Update"){
								  alert(" Crime details Updated Successfully!");
									$scope.crimeDetail="";
									if($scope.draftId!=null && $scope.draftId!=undefined){
										getCrimeDetail($scope.caveat.cav_id);	
									}
									else{
										getCrimeDetail($scope.caveat.cav_id);
									}
									
						   }
				
						
					});	
				}
		        
		    	$scope.deleteCrimeDetails=function(id){
					  var result=confirm("Are you really want to Remove");
						if (result) {
					   $http({
						method : 'DELETE',
						url : urlBase + 'caveat/deleteCrimeDetail/' + id + '/'
					}).success(function(res) {
						   if(res.response=="TRUE"){
					     alert("  Crime Details Deleted Successfully!");
					     if($scope.draftId!=null && $scope.draftId!=undefined){
					    	 getCrimeDetail($scope.caveat.cav_id);	
							}
							else{
								getCrimeDetail($scope.caveat.cav_id);
							}
						   }
				
						
					});	
				}
				
				}
		    	
		    	function getCrimeDetail(id){
				    $http.get(urlBase+ 'caveat/getCrimeDetail', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				              $scope.crimeDetailList = data.modelList;
				               
				}).error(function(data, status, headers, config) {
					});

				}
		    	
		    	function getAuthority(id){
				    $http.get(urlBase+ 'caveat/getAuthority', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				              $scope.authorityList = data.modelList;
				              console.log($scope.authorityList[0]);
				              $scope.caveatAuthority1=$scope.authorityList[0];
				              $scope.authorityList.splice(0,1);
				               
				}).error(function(data, status, headers, config) {
					});

				}
	
	
	
	
	
	
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
		                $scope.caveat.cav_ord_dist=$scope.caveat.cav_dist_mid;
		        		$scope.extraCaveatImpugnedOrder.ec_io_district=$scope.caveat.cav_dist_mid;
		                getEditData();
		}).error(function(data, status, headers, config) {
			});

		}

			function getEditData(){
			if($scope.draftId!=null && $scope.draftId!=undefined){
			getCaveatPetitioner($scope.draftId);
			getCrimeDetail($scope.draftId);
			getAuthority($scope.draftId);
			getCaveatRespondent($scope.draftId);
			getCaveatExtraCaveator($scope.draftId);
			getExtraCaveatImpugnedOrder($scope.draftId);
			getCourtFee($scope.draftId);
			getUploadedDocuments($scope.draftId);
			getCaveatExtraAdvocate($scope.draftId);
			getActDetails($scope.draftId);
			}
			}
			
			
			function getActDetails(id){
			    $http.get(urlBase+ 'caveat/getActDetails', {
					params : {
						'docId' : id
					}
				}).success(function(data, status, headers, config) {
			                
			              $scope.actDataList = data.modelList;
			              
			              cosnole.log("act "+$scope.actDataList);
			               
			}).error(function(data, status, headers, config) {
				});

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

				
				  
	$scope.caveatAuthority={};	
	$scope.caveatAuthority1={};
	
	$scope.addCaveatAuthority=function(caveatAuthority){
		$scope.caveatAuthority.ca_cav_mid=$scope.draftId;
		   var response =$http.post(urlBase+'caveat/addCaveatAuthority',$scope.caveatAuthority);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					   
					   alert("Added Successfully!");
					
				   }
				   else if(data.response=="UPDATE"){
					   alert("Updated Successfully!");
					   console.log("auth updated");
				   }
		
				
			});	
	}
	
	$scope.addCaveat=function(caveat){
		
		var response =$http.post(urlBase+'caveat/addCaveat',caveat);
		response.success(function(data, status, headers, config){
			   if(data.response=="ADD"){
				   $scope.caveat=data.modelData;
				   $scope.caveatAuthority1.ca_cav_mid= $scope.caveat.cav_id;
				   
				   if($scope.caveat.cav_court==4){
				   var response =$http.post(urlBase+'caveat/addCaveatAuthority',$scope.caveatAuthority1);
					response.success(function(data, status, headers, config){
						   if(data.response=="ADD"){
							   
							console.log("auth added");
							
						   }
						   else if(data.response=="UPDATE"){
							   
							   console.log("auth updated");
						   }
				
						
					});	
				   }
				   
				   
				alert("Caveat Added Successfully!");
				$scope.caveat=data.modelData;
			   }
			   else if(data.response=="UPDATE"){
				  // $scope.caveatAuthority1.ca_cav_mid= $scope.caveat.cav_id;
				   var response =$http.post(urlBase+'caveat/addCaveatAuthority',$scope.caveatAuthority1);
					response.success(function(data, status, headers, config){
						   if(data.response=="ADD"){
							   
							console.log("auth added");
							
						   }
						   else if(data.response=="UPDATE"){
							   
							   console.log("auth updated");
						   }
				
						
					});	
				alert("Caveat Updated Successfully!");
			   }
	
			
		});	
	}

	$scope.rollno=null;

$scope.ShowId = function(event)
{
	
	$scope.tabShow1=true;
	$scope.tabShow2=true;
	$scope.tabShow3=true;
	$scope.tabShow4=true;
	$scope.tabShow5=true;
	$scope.tabShow6=true;
	$scope.tabShow7=true;
	$scope.tabShow8=true;
	$scope.tabShow9=true;
	$scope.tabShow10=true;
	$scope.tabShow11=true;
	$scope.tabShow=true;
	$scope.tabShow12=true;
	$scope.tabShow13=true;
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
		$scope.actDetails={};
		
		$scope.addActDetails=function(actDetails){
			
			if($scope.act_code2!=null || !angular.isUndefined($scope.act_code2)){
			$scope.actDetails.cact_code=$scope.act_code2;
			}
			if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
			{
				alert("First add Caveat details");
				return false;
			}
			if(actDetails.act_type==1)
				actDetails.act_type='central';
			actDetails.cact_cav_mid=$scope.caveat.cav_id;
			var response =$http.post(urlBase+'caveat/addActDetails',actDetails);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("ActDetails Added Successfully!");
					$scope.actDetails="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getActDetails($scope.draftId);	
					}
					else{
						getActDetails($scope.registerCase.rcd_id);
					}
					
					
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
					   alert(" actDetails Updated Successfully!");
							$scope.actDetails="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getActDetails($scope.draftId);	
							}
							else{
								getActDetails($scope.registerCase.rcd_id);
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
		
		
		
		/**
		 * ***********************RESPONDENT DETAILS ENDED
		 * HERE******************
		 */
		
		/*  ********Extra Advocate Details Start *****      */
		
		$scope.addAdvocate={};
		$scope.extraAdovate={};
		
		/*$scope.editExtraAdvocate=function(row){
			$scope.addAdvocate=row;
			
		}*/
		
		$scope.addExtraAdvocate=function(extraAdvocateDetails){
			if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
			{
				alert("First add Caveat details");
				return false;
			}
			$scope.addAdvocate.cea_cav_mid=$scope.caveat.cav_id;
			$scope.addAdvocate.cea_adv_mid=$scope.extraAdovate.adv_id;
			$scope.addAdvocate.cea_rec_status=1;
			
			
			var response =$http.post(urlBase+'caveat/addExtraAdvocate',$scope.addAdvocate);
			response.success(function(data, status, headers, config){
				
				   if(data.response=="CREATE"){
					alert("Extra Advocate Added Successfully!");
					$scope.extraAdovate={};
					
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCaveatExtraAdvocate($scope.draftId);	
					}
					else{
						getCaveatExtraAdvocate($scope.caveat.cav_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.response=="UPDATE"){
						 alert("Extra Advocate Updated Successfully!");		
						 $scope.extraAdovate={};
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCaveatExtraAdvocate($scope.draftId);	
							}
							else{
								getCaveatExtraAdvocate($scope.caveat.cav_id);
							}
							
				   }
		
				
			});	
		}
		
		function getCaveatExtraAdvocate(id){
		    $http.get(urlBase+ 'caveat/getCaveatExtraAdvocate', {
				params : {
					'docId' : id
				}
			}).success(function(data, status, headers, config) {
		                
		              $scope.extraAdvocateDataList = data.modelList;
		              console.log($scope.extraAdvocateDataList);
		               
		}).error(function(data, status, headers, config) {
			});

		}

		
	

		$scope.deleteExtraAdvocate=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'caveat/deleteExtraAdvocate/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("Respondent Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCaveatExtraAdvocate($scope.draftId);	
					}
					else{
						getCaveatExtraAdvocate($scope.caveat.cav_id);
					}
				 	
				   }
				
			});	
		}
			 
		}
		

		
/*  ********Extra Caveator Details Start *****      */
		
		function getCaveatExtraCaveator(id){
			
		    $http.get(urlBase+ 'caveat/getCaveatExtraCaveator', {
				params : {
					'docId' : id
				}
			}).success(function(data, status, headers, config) {
		                
		              $scope.extraCaveatorDataList = data.modelList;
		               
		}).error(function(data, status, headers, config) {
			});

		}
		
		$scope.editEct=function(row){
			$scope.extraCaveatorDetails=row;
		}
		
		$scope.addExtraCaveator=function(ExtraCaveatorDetails){
			if($scope.caveat.cav_id==null || angular.isUndefined($scope.caveat.cav_id))
			{
				alert("First add Extra Caveator details");
				return false;
			}
			ExtraCaveatorDetails.ect_cav_mid=$scope.caveat.cav_id;
			var response =$http.post(urlBase+'caveat/addExtraCaveatorDetails',ExtraCaveatorDetails);
			response.success(function(data, status, headers, config){
				
				   if(data.response=="CREATE"){
					alert("Extra Caveator Added Successfully!");
					$scope.extraCaveatorDetails={};
					 if($scope.draftId!=null && $scope.draftId!=undefined)
				     {
						 getCaveatExtraCaveator($scope.draftId);
				     }
					 else{
						 getCaveatExtraCaveator($scope.caveat.cav_id);
					 }
					
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.response=="UPDATE"){
						  alert("Extra Caveator Updated Successfully!");
						  $scope.extraCaveatorDetails={};
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCaveatExtraCaveator($scope.draftId);
							}
							else
								{
								getCaveatExtraCaveator($scope.caveat.cav_id);
								}
				   }
		
				
			});	
		}
	
		$scope.deleteExtraCvaeator=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'caveat/deleteExtraCvaeator/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("Extra Caveator Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCaveatExtraCaveator($scope.draftId);
					}
					else{
						getCaveatExtraCaveator($scope.caveat.cav_id);
				 	
				   }
				   }
				
			});	
		}
		
		}

/*    **********Extra Caveator details End************    /
		
		
		
		
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
		
		$scope.authorityShow=false;
		$scope.lowerCourtShow=true;
		$scope.highCourtShow=false;
		$scope.changeCourt=function(event){
			if(event.target.id=="lowerCourt"){
				$scope.lowerCourtShow=true;
				$scope.highCourtShow=false;
				$scope.crimeDetailShow=false;
				$scope.authorityShow=false;
		
				
			}
			else if(event.target.id=="crimeDetails"){
				$scope.crimeDetailShow=true;
				$scope.lowerCourtShow=false;
				$scope.highCourtShow=false;
				$scope.authorityShow=false;
				
			}
			else if(event.target.id=="authority"){
				$scope.authorityShow=true;
				$scope.crimeDetailShow=false;
				$scope.lowerCourtShow=false;
				$scope.highCourtShow=false;
				
				
			}
			else if(event.target.id=="mandamus"){
				$scope.authorityShow=false;
				$scope.crimeDetailShow=false;
				$scope.lowerCourtShow=false;
				$scope.highCourtShow=false;
				$scope.mandmusShow=true;
				
				
			}
			else{
				$scope.highCourtShow=true;
				$scope.lowerCourtShow=false;
				$scope.crimeDetailShow=false;
				$scope.authorityShow=false;
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