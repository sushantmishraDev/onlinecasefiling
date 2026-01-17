
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




















EDMSApp.controller('addCaseDetailController',['$scope','$http','Upload',function ($scope, $http,Upload) {

	var urlBase="/onlinecasefiling/";
	
	$scope.petitionerDataList=[];
	$scope.respondentDataList=[];
	$scope.courtFeeList=[];
	$scope.formShow=true;
	$scope.petitionerDetails={};
	$scope.registerCase={};
	
	$scope.caseDetail={};
	 $scope.showAor=false;
	$scope.tabShow1=true;
	$scope.tabShow2=false;
	$scope.tabShow3=false;
	$scope.tabShow4=false;
	$scope.tabShow5=false;
	$scope.tabShow=false;
	$scope.fileButton=false;
	$scope.fileSizeObject={};
	$scope.searchAor=null;
	
	$scope.petitionerDetails.pt_s_id=34;
	
	

	
	
	
	
	
	
	
	$scope.stateList=[];
	$scope.districtList=[];
	
	$scope.cnmList=[];
	$scope.ndmList=[];
	$scope.noticeList=[];
	$scope.establishmentList=[];
	
	$scope.caseAuthority={};
	
	
	
	
	
	
	
	getStateList();
	$scope.draftId= $('#draftId').val();
	$scope.caseType= $('#caseType').val();
	$scope.codeNo= $('#code').val();
	// $scope.name= $('#name').val();
	 getResetData();
	 
	/*
	 * $scope.onlyOne=function(checkbox) { console.log("in side me"); var
	 * checkboxes = document.getElementsByName('check')
	 * checkboxes.forEach((item) => { if (item !== checkbox) item.checked =
	 * false }) }
	 */
	 
	 function cnmByFilter(id){
		  return $scope.cnmList.filter(x => x.cnm_id === id);
		}
	 
	 $scope.cnmChange=function(cnm,index,resName){
		
		 $scope.noticeList[index].allowOther=false;
		 
		 $scope.noticeList[index].allowInperson=false;
		 
		 $scope.noticeList[index].allowDepartment=false;
		 
         if(cnm==9999){
        	 $scope.noticeList[index].allowOther=true;
        	 $scope.searchAor=true;
        	 $scope.searchInPerson=false;
		 }
         else if(cnm==9998){
        	 $scope.noticeList[index].allowInperson=true;
        	 $scope.searchInPerson=true;
        	 $scope.searchAor=false;
        	 document.getElementById("ntMobile").disabled=false;
        	 document.getElementById("ntEmail").disabled=false;
		 }
         
         else if(cnm==99991){
        	 $scope.noticeList[index].allowDepartment=true;
		 }
         
         else{
	 	var test=cnmByFilter(cnm);
		 console.log("cn",$scope.noticeList);
		 console.log(index);
		 
		 $scope.searchAor=true;
    	 $scope.searchInPerson=false;
		 
		 $scope.noticeList[index].nt_adv_name=test[0].cnm_name;
		 $scope.noticeList[index].nt_res_adv_email=test[0].cnm_email;
		 $scope.noticeList[index].nt_res_adv_mobile=test[0].cnm_mobile;
		 $scope.noticeList[index].nt_rcd_mid=$scope.registerCase.rcd_id;
		 $scope.noticeList[index].nt_cr_by=$scope.registerCase.rcd_cr_by;
		 $scope.noticeList[index].nt_res_name=resName;
	 }
		/* $scope.noticeList[index].nt_cr_by=$scope.registerCase.rcd_cr_by; */
	 }
	 
	 $scope.findAdvocate =function(aor,index){
		 if(aor!=null){
		 var response =$http.post(urlBase+'user/searchAdvocate',aor);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					   $scope.noticeList[index].nt_adv_name=data.modelData.name;
					   $scope.noticeList[index].nt_res_adv_email=data.modelData.email;
						 $scope.noticeList[index].nt_res_adv_mobile=data.modelData.mobile;
						 $scope.noticeList[index].nt_rcd_mid=$scope.registerCase.rcd_id;
						 $scope.noticeList[index].nt_cr_by=$scope.registerCase.rcd_cr_by;
					
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else{
						  alert(" Aor Not Found");
							
							
				   }
				   
		
				
			});	
			
			}
	 }
	 $scope.check= function(input)
	    {
	    	
	    	var checkboxes = document.getElementsByClassName("radioCheck");
	    	
	    	for(var i = 0; i < checkboxes.length; i++)
	    	{
	    		// uncheck all
	    		if(checkboxes[i].checked == true)
	    		{
	    			checkboxes[i].checked = false;
	    		}
	    	}
	    	
	    	// set checked of clicked object
	    	if(input.checked == true)
	    	{
	    		input.checked = false;
	    	}
	    	else
	    	{
	    		input.checked = true;
	    	}	
	    }
	
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
			
			
			//getCNMList();
			
			 function getCNMList(caseType)
			 {
					$http.post(urlBase+'ecourt/getCNMList',caseType).
			        success(function (data) {
			        	$scope.cnmList=data.modelList;
			        	
			        }).
			        error(function(data, status, headers, config) {
			        	console.log("Error in getting tree data");
			        });
				};
				
				
				getNDMList();
				
				 function getNDMList()
				 {
						$http.get(urlBase+'ecourt/getNDMList').
				        success(function (data) {
				        	$scope.ndmList=data.modelList;
				        	
				        }).
				        error(function(data, status, headers, config) {
				        	console.log("Error in getting tree data");
				        });
					};
			
			/*
			 * getEstablishmentList();
			 * 
			 * function getEstablishmentList() {
			 * $http.get(urlBase+'ecourt/getEstablishmentList').
			 * success(function (data) {
			 * $scope.establishmentList=data.modelList;
			 * 
			 * }). error(function(data, status, headers, config) {
			 * console.log("Error in getting tree data"); }); };
			 */
		
			
			
			
	
			
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
						
						
						getPoliceStationList();
						
						 function getPoliceStationList()
						 {
								$http.get(urlBase+'police_station_master/getpoliceStationNewDetails2024').
						        success(function (data) {
						        	$scope.policeStationList=data;
						        	
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
				    // $scope.minDate = $scope.minDate ? null : new Date();
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
				
				/*
				 * function convertDate(inputFormat) { function pad(s) { return
				 * (s < 10) ? '0' + s : s; } var d = new Date(inputFormat);
				 * return [ d.getFullYear(),
				 * pad(d.getMonth()+1),pad(d.getDate())].join('-'); }
				 */
	
	
	
	
	
	
	
	
	
	
	/** ***********NEW CASE STARTS HERE*********************************** */
	
	
				if($scope.caseType!=null && $scope.caseType!=undefined)
					 $scope.registerCase.rcd_ct_id = Number($scope.caseType);
	
	   
	if($scope.draftId!=null && $scope.draftId!=undefined)
	getRegisterCase($scope.draftId);
	
		
			function getRegisterCase(id){
				
		    $http.get(urlBase+ 'ecourt_add_case/getRegisterCase', {
				params : {
					'docId' : id
				}
			}).success(function(data, status, headers, config) {
				
				// $scope.registerCase.rcd_ct_id= data.modelData.rcd_ct_id;
		                $scope.registerCase = data.modelData;
		                $scope.ptDisable=false;
		                $scope.ptAdvDisable=false;
		                getCNMList($scope.registerCase.caseType);
		               // console.log($scope.name);
		                if($scope.registerCase.userFiled.userroles[0].lk.lk_longname=='InPerson' && $scope.petitionerDataList.length==0){
		                	 $scope.ptDisable=true;
		                	 $scope.crimeDetail.cd_district=$scope.registerCase.rcd_dt_id;
							$scope.petitionerDetails.pt_name="In-Person";
							$scope.petitionerDetails.pt_email=$scope.registerCase.userFiled.um_email;
							$scope.petitionerDetails.pt_adhar=$scope.registerCase.userFiled.um_adhar;
							$scope.petitionerDetails.pt_mobile=$scope.registerCase.userFiled.um_mobile;
						}
		                if($scope.registerCase.userFiled.userroles[0].lk.lk_longname=='Advocate' && $scope.petitionerDataList.length==0 && $scope.registerCase.ptName!='NA'){
		                	$scope.ptAdvDisable=true;
		                	$scope.petitionerDetails.pt_name=$scope.registerCase.ptName;
		                }
		               /* 
		                if( $scope.registerCase.caseType.ct_type=="civil"){
		                	
		        			$scope.stateActList=[];
		        			$scope.centralActList=[];
		        			
		        			getActMastersOld();
		                }
		                else{
		                	getActMasters();
		                	 * getSecMasters();
		                }*/
		                
		                
	                 /*
						 * $scope.registerCase = data.modelData; var date=new
						 * Date(parseInt($scope.registerCase.rcd_cr_date));
						 * $scope.registerCase.rcd_cr_date=("0" +
						 * date.getDate()).slice(-2) + '/' + ("0" +
						 * (date.getMonth() + 1)).slice(-2) + '/' +
						 * date.getFullYear();
						 * 
						 */
		
		}).error(function(data, status, headers, config) {
			});

		}

			function getResetData(){
			if($scope.draftId!=null && $scope.draftId!=undefined){
			getPetitioner($scope.draftId);
			getAdvanceNotice($scope.draftId);
			getRespondent($scope.draftId);
			getActDetails($scope.draftId);
			getImpugnedOrder($scope.draftId);
			getCrimeDetail($scope.draftId);
			getTrialCourt($scope.draftId);
			getCourtFee($scope.draftId);
			getSessionTrack($scope.draftId);
			
			getAuthority($scope.draftId);
			
			getUploadedDocuments();
			}}
			
			
			function getPetitioner(id){
				
			    $http.get(urlBase+ 'ecourt/getPetitioner', {
					params : {
						'docId' : id
					}
				}).success(function(data, status, headers, config) {
			                
			              $scope.petitionerDataList = data.modelList;
			               
			}).error(function(data, status, headers, config) {
				});

			}
			
function getAdvanceNotice(id){
				
			    $http.get(urlBase+ 'ecourt/getAdvanceNotice', {
					params : {
						'docId' : id
					}
				}).success(function(data, status, headers, config) {
			                
			              $scope.noticeList = data.modelList;
			               
			}).error(function(data, status, headers, config) {
				});

			}
			
			
				
			
				function getRespondent(id){
				    $http.get(urlBase+ 'ecourt/getRespondent', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				                
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
				
				
				function getCrimeDetail(id){
				    $http.get(urlBase+ 'ecourt/getCrimeDetail', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				              $scope.crimeDetailList = data.modelList;
				               
				}).error(function(data, status, headers, config) {
					});

				}
				
				
				function getSessionTrack(id){
				    $http.get(urlBase+ 'ecourt/getSessionTrack', {
						params : {
							'docId' : id
						}
					}).success(function(data, status, headers, config) {
				              $scope.sessionTrackDataList = data.modelList;
				               
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
				        /*
						 * for(var i=0; i<courtFeeList.length; i++){
						 * 
						 * var date(parseInt($scope.courtFeeList[i].cf_date));
						 * $scope.$scope.courtFeeList[i].cf_date=("0" +
						 * date.getDate()).slice(-2) + '/' + ("0" +
						 * (date.getMonth() + 1)).slice(-2) + '/' +
						 * date.getFullYear();
						 *  }
						 */
				              
				              
				              
				}).error(function(data, status, headers, config) {
					});

				}

				
				  
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		  
			
		
		
			/*
			 * var ddate=registerCase.rcd_cr_date;
			 * 
			 * ddate=ddate.split("/"); var
			 * newDate=ddate[0]+"/"+ddate[1]+"/"+ddate[2];
			 * 
			 * registerCase.rcd_date_of_section4=new Date(newDate).getTime();
			 */

	
	$scope.addRegisterCase=function(registerCase){
		registerCase.code=Number($scope.codeNo);
		var response =$http.post(urlBase+'ecourt_add_case/addRegisterCase',registerCase);
		response.success(function(data, status, headers, config){
			   if(data.response=="TRUE"){
				   
				alert("CaseDetails Added Successfully!");
				$scope.registerCase=data.modelData;
				getCNMList($scope.registerCase.caseType);
				  if( $scope.registerCase.caseType.ct_type=="civil"){
	                	
	        			$scope.stateActList=[];
	        			$scope.centralActList=[];
	        			
	        			getActMastersOld();
	                }
	                else{
	                	//getActMasters();getSecMasters();
	                }
				if($scope.registerCase.userFiled.userroles[0].lk.lk_longname=='InPerson'){
					$scope.ptDisable=true;
					$scope.crimeDetail.cd_district=$scope.registerCase.rcd_ct_id;
					$scope.petitionerDetails.pt_name=$scope.registerCase.userFiled.um_fullname;
					$scope.petitionerDetails.pt_email=$scope.registerCase.userFiled.um_email;
					$scope.petitionerDetails.pt_adhar=$scope.registerCase.userFiled.um_adhar;
					$scope.petitionerDetails.pt_mobile=$scope.registerCase.userFiled.um_mobile;
				}
				if($scope.registerCase.userFiled.userroles[0].lk.lk_longname=='Advocate' && $scope.petitionerDataList.length==0 && $scope.registerCase.ptName!='NA'){
                	$scope.ptAdvDisable=true;					
					$scope.petitionerDetails.pt_name=$scope.registerCase.ptName;
				}
			   }
			   else if(data.data=="Update"){
					getCNMList($scope.registerCase.caseType);
				alert(" caseDetails Updated Successfully!");
			   }
	
			
		});	
	}
	
	
$scope.validateCode=function(validate){
	validate.ae_code= Number(validate.ae_code)
	window.open(urlBase+"ecourt_add_case/validateCode/"+validate.ae_code+","+validate.ae_appno,'_self');
		/*
		 * var response
		 * =$http.post(urlBase+'ecourt_add_case/validateCode',code);
		 * response.success(function(data, status, headers, config){
		 * if(data.response=="TRUE"){ $scope.ct_id=data.modelData.ae_case_type;
		 * window.open("/onlinecasefiling/ecourt/addNewCase","_self");
		 * //$scope.registerCase.rcd_ct_id=data.modelData; } else
		 * if(data.data=="Update"){
		 * 
		 * alert(" caseDetails Updated Successfully!"); }
		 * 
		 * 
		 * });
		 */
	}

  /**
	 * ************************** add case Ends
	 * here*******************************
	 */

$scope.ShowId = function(event)
{
	console.log(event.target.hash);
	if(event.target.hash=='#nav-tab-4'){
		 
        if( $scope.registerCase.caseType.ct_type=="civil" && $scope.registerCase.caseType.ct_label !="CRLP"){
        	
			$scope.stateActList=[];
			$scope.centralActList=[];
			
			getActMastersOld();
        }
        else{
        	getActMasters();
        	 //getSecMasters();
        }
	}
	else{
		$scope.actDetailsList=[];
	}
	
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
	$scope.tabShow12=true;
	$scope.tabShow=true;
};
	
$scope.nextTab=function(event){
}


	
	
$scope.addShow=true;
	
	
	
		
	/*
	 * getPetitionerDetails();
	 * 
	 * function getPetitionerDetails(){
	 * $http.get(urlBase+'ecourt_add_case/getPetitionerDetails').
	 * success(function (data) { $scope.petitionerDataList=data.modelList;
	 * console.log($scope.petitionerDataList); }). error(function(data, status,
	 * headers, config) { console.log("Error in getting tree data"); }); };
	 */
		
		$scope.editPet=function(row,index){
			console.log("index",index)
			if($scope.registerCase.userFiled.userroles[0].lk.lk_longname=='InPerson' && index==0){
		                	 $scope.ptDisable=true;
			}
			else{
				$scope.ptDisable=false;
			}
			$scope.petitionerDetails=row;
		}
		$scope.registerCase.rcd_id;
		$scope.addPetitioner=function(PetitionerDetails){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			PetitionerDetails.pt_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addPetitioner',PetitionerDetails);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("Petitioner Added Successfully!");
					$scope.petitionerDetails="";
					 $scope.ptDisable=false;
		                $scope.ptAdvDisable=false;
					 if($scope.draftId!=null && $scope.draftId!=undefined)
				     {
						 getPetitioner($scope.draftId);
				     }
					 else{
					getPetitioner($scope.registerCase.rcd_id);
					 }
					
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" petitioner Updated Successfully!");
							$scope.petitionerDetails="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
							getPetitioner($scope.draftId);
							}
							else
								{
							getPetitioner($scope.registerCase.rcd_id);
								}
				   }
		
				
			});	
		}
		
		
		
		
		
		
		
		$scope.deletePetitioner=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deletePetitioner/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert(" petitioner Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
					getPetitioner($scope.draftId);
					}
					else{
					getPetitioner($scope.registerCase.rcd_id);
				 	
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
		$scope.respondentDetails.rt_s_id=34;
		
		$scope.editResp=function(row){
			$scope.respondentDetails=row;
			
		}
			
		
		/*
		 * getRespondentDetails();
		 * 
		 * function getRespondentDetails(){
		 * $http.get(urlBase+'ecourt_add_case/getRespondentDetails').
		 * success(function (data) { $scope.respondentDataList=data.modelList;
		 * console.log($scope.respondentDataList); }). error(function(data,
		 * status, headers, config) { console.log("Error in getting tree data");
		 * }); };
		 */
			
			
		
		
		
		

		$scope.addRespondent=function(respondentDetails){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			respondentDetails.rt_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addRespondent',respondentDetails);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("Respondent Added Successfully!");
					$scope.respondentDetails="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getRespondent($scope.draftId);	
					}
					else{
					getRespondent($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						 alert(" respondent Updated Successfully!");
							$scope.respondentDetails="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getRespondent($scope.draftId);	
							}
							else{
							getRespondent($scope.registerCase.rcd_id);
							}
							
				   }
		
				
			});	
		}
		
		
		
		
		
		$scope.deleteRespondent=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteRespondent/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("  respondent Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getRespondent($scope.draftId);	
					}
					else{
					getRespondent($scope.registerCase.rcd_id);
					}
				 	
				   }
				
			});	
		}
			 
		}
		
		
		
		/**
		 * ***********************RESPONDENT DETAILS ENDED
		 * HERE******************
		 */
		
		
		
		/** ******************impugned Order starts here************** */
		
		
		
		
		
		
		$scope.impugnedOrder={};
		
		$scope.sessionTrack={};
		
		$scope.crimeDetail={};
		
		
		
		$scope.editImp=function(data){
			$scope.impugnedOrder=data;
			
		}
		
		$scope.editCrimeDetals=function(data){
			$scope.crimeDetail=data;
			
		}
		
		
		$scope.editSessionTrack=function(data){
			$scope.sessionTrack=data;
			
		}
		
		

// getImpugnedOrder();
//		
// function getImpugnedOrder(){
// $http.get(urlBase+'ecourt_add_case/getImpugnedOrder').
// success(function (data) {
// $scope.impugnedDataList=data.modelList;
// }).
// error(function(data, status, headers, config) {
// console.log("Error in getting tree data");
// });
// };
//			
//			
		$scope.filterExpression = function(station) {
            return (station.dist_code === $scope.crimeDetail.cd_district);
        };
        
        
        
        $scope.filterCNM = function(cnm) {
            return (cnm.cnm_dt_mid === $scope.registerCase.rcd_dt_id  || cnm.cnm_dt_mid ===null);
        };
        
        
        $scope.filterLct = function(court) {
        	if(court.lct_id==151 && court.lct_dt_mid==81){
        		/*console.log(court);*/
        	}
            return (court.lct_dt_mid === $scope.impugnedOrder.io_district);
        };
        
        $scope.filterEstablishment = function(establishment) {
            return (establishment.est_dt_mid === $scope.impugnedOrder.io_district);
        };
        
        $scope.filterLct1 = function(court) {
            return (court.lct_dt_mid === $scope.sessionTrack.snd_district);
        };
        
        $scope.filterEstablishment1 = function(establishment) {
            return (establishment.est_dt_mid === $scope.sessionTrack.snd_district);
        };
		
		$scope.addCrimeDetails=function(crimedetils){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			$scope.crimeDetail.cd_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addCrimeDetail',$scope.crimeDetail);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("Crime details Added Successfully!");
					$scope.crimeDetail={};
					$scope.crimeDetail.cd_district=$scope.registerCase.rcd_dt_id;
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCrimeDetail($scope.draftId);	
					}
					else{
						getCrimeDetail($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" Crime details Updated Successfully!");
							$scope.crimeDetail="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCrimeDetail($scope.draftId);	
							}
							else{
								getCrimeDetail($scope.registerCase.rcd_id);
							}
							
				   }
		
				
			});	
		}
		
		$scope.title = ["Mr.", "Mrs.", "Ms.","Dr."];
		
		$scope.parantage = ["s/o", "d/o", "w/o","h/o"];
		
		
		$scope.addCaveatAuthority=function(caseAuthority){
			$scope.caseAuthority.cau_rcd_mid=$scope.registerCase.rcd_id;
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			   var response =$http.post(urlBase+'ecourt_add_case/addCaseAuthority',$scope.caseAuthority);
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
		$scope.verifiedNotice=false;
		
		$scope.verifyNotice1 =	function(){
		    $http.get('https://e-manu.up.gov.in/api/get-details-by-notice-number?api_key=4MAiH53dpHXQ2RFNMKanaXuOITVRakKKh7BA0aBQ2g1gDh64Itl1hzgje3PG&noticeno='+$scope.registerCase.rcd_notice_no+'&est_code=UPHC01', {			
			}).success(function(data, status, headers, config) {
				console.log(data);
				if(data.flag==true){
				$scope.verifiedNotice=true;
				$scope.petitionerDetails.pt_name=data.notice.petitioners[0].pet_name;
				$scope.respondentDetails.rt_name=data.notice.respondents[0].res_name;
				alert("Validated successfully !");
				}
				else{
					alert("Given notice number not validated");
				}
		               
		}).error(function(data, status, headers, config) {
			});

		}
		
		$scope.verifyNotice =	function(){
		    $http.get(urlBase+ 'ecourt_add_case/validateNotice', {
				params : {
					'docId' : $scope.registerCase.rcd_notice_no
				}
			}).success(function(data, status, headers, config) {
				console.log(data.data);
				
				if(data.data.flag==true){
					$scope.verifiedNotice=true;
					$scope.petitionerDetails.pt_name=data.data.notice.petitioners[0].pet_name;
					$scope.respondentDetails.rt_name=data.data.notice.respondents[0].res_name;
					alert("Validated successfully !");
					}
					else{
						alert("Given notice number not validated");
					}
			}).error(function(data, status, headers, config) {
			});

		}
		
		function getAuthority(id){
		    $http.get(urlBase+ 'ecourt_add_case/getAuthority', {
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

		$scope.addImpugnedOrder=function(impugnedOrder){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			/*
			 * $scope.model={}; $scope.model.io_lct_mid
			 * =$scope.impugnedOrder.io_lct_mid ; $scope.model.io_s_id
			 * =$scope.impugnedOrder.io_s_id;
			 * $scope.model.io_judge_name=$scope.impugnedOrder.io_judge_name;
			 * $scope.model.io_case_no=$scope.impugnedOrder.io_case_no;
			 * $scope.model.io_district=$scope.impugnedOrder.io_district;
			 * $scope.model.io_decision_date=$scope.impugnedOrder.io_decision_date;
			 * $scope.model.io_ct_mid=$scope.impugnedOrder.io_ct_mid;
			 * $scope.model.io_rcd_mid=$scope.registerCase.rcd_id;
			 */
			/* impugnedOrder.io_rcd_mid=$scope.registerCase.rcd_id; */
			$scope.impugnedOrder.io_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addImpugnedOrder',$scope.impugnedOrder);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("ImpugnedOrder Added Successfully!");
					$scope.impugnedOrder="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getImpugnedOrder($scope.draftId);	
					}
					else{
						getImpugnedOrder($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" impugnedOrder Updated Successfully!");
							$scope.impugnedOrder="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getImpugnedOrder($scope.draftId);	
							}
							else{
								getImpugnedOrder($scope.registerCase.rcd_id);
							}
							
				   }
		
				
			});	
		}
		
		
		$scope.addAdvanceNotice=function(){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			$scope.sessionTrack.snd_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addAdvanceNotice',$scope.noticeList);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("Advance Notice Successfully!");
					$scope.sessionTrack="";
					/*
					 * if($scope.draftId!=null && $scope.draftId!=undefined){
					 * getSessionTrack($scope.draftId); } else{
					 * getSessionTrack($scope.registerCase.rcd_id); }
					 */
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" Advance Notice Updated Successfully!");
							$scope.sessionTrack="";
							/*
							 * if($scope.draftId!=null &&
							 * $scope.draftId!=undefined){
							 * getSessionTrack($scope.draftId); } else{
							 * getSessionTrack($scope.registerCase.rcd_id); }
							 */
							
				   }
		
				
			});	
		}
		
		
		$scope.addSessionTrack=function(impugnedOrder){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			/*
			 * $scope.model={}; $scope.model.io_lct_mid
			 * =$scope.impugnedOrder.io_lct_mid ; $scope.model.io_s_id
			 * =$scope.impugnedOrder.io_s_id;
			 * $scope.model.io_judge_name=$scope.impugnedOrder.io_judge_name;
			 * $scope.model.io_case_no=$scope.impugnedOrder.io_case_no;
			 * $scope.model.io_district=$scope.impugnedOrder.io_district;
			 * $scope.model.io_decision_date=$scope.impugnedOrder.io_decision_date;
			 * $scope.model.io_ct_mid=$scope.impugnedOrder.io_ct_mid;
			 * $scope.model.io_rcd_mid=$scope.registerCase.rcd_id;
			 */
			/* impugnedOrder.io_rcd_mid=$scope.registerCase.rcd_id; */
			$scope.sessionTrack.snd_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addSessionTrack',$scope.sessionTrack);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("Session Added Successfully!");
					$scope.sessionTrack="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getSessionTrack($scope.draftId);	
					}
					else{
						getSessionTrack($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" impugnedOrder Updated Successfully!");
							$scope.sessionTrack="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getSessionTrack($scope.draftId);	
							}
							else{
								getSessionTrack($scope.registerCase.rcd_id);
							}
							
				   }
		
				
			});	
		}
		
		$scope.deleteCrimeDetails=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteCrimeDetail/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
			     alert("  St Details Deleted Successfully!");
			     if($scope.draftId!=null && $scope.draftId!=undefined){
			    	 getCrimeDetail($scope.draftId);	
					}
					else{
						getCrimeDetail($scope.registerCase.rcd_id);
					}
				   }
		
				
			});	
		}
		
		}
		
		
		$scope.deleteSessionTrack=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteSessionTrack/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
			     alert("  St Details Deleted Successfully!");
			     if($scope.draftId!=null && $scope.draftId!=undefined){
			    	 getSessionTrack($scope.draftId);	
					}
					else{
						getSessionTrack($scope.registerCase.rcd_id);
					}
				   }
		
				
			});	
		}
		
		}
		
		
		
		$scope.deleteImpugnedOrder=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteImpugnedOrder/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
			     alert("  ImpugnedOrder Deleted Successfully!");
			     if($scope.draftId!=null && $scope.draftId!=undefined){
						getImpugnedOrder($scope.draftId);	
					}
					else{
						getImpugnedOrder($scope.registerCase.rcd_id);
					}
				   }
		
				
			});	
		}
		
		}
		

		
		
		/**
		 * **********************************IMPUGNED ORDER ENDED
		 * HERE*******************************************************
		 */
		
		
		
		/**
		 * ************************************* ACT DETAILS CODE STARTS HERE
		 * ********************************************
		 */
		
		
		$scope.actDetails={
				
				
		}; 
		
		$scope.filterExpressionSec = function(act) {
            return (act.act_code === $scope.actDetails.act_code);
        };
		
		
		$scope.centralShow=true;
		$scope.stateShow=false;
		$scope.changeAct=function(){
			$scope.actDetailsList=[];
			if($scope.registerCase.caseType.ct_type=="civil" && $scope.registerCase.caseType.ct_label !="CRLP"){
				if($scope.actDetails.act_type=="central"){
					$scope.centralShow=true;
					$scope.stateShow=false;
			
					
				}
				else{
					$scope.stateShow=true;
					$scope.centralShow=false;
				}
				
			}
			else{
				getActMasters();
			}
			
		};
		
		
		$scope.editAct=function(data){
			$scope.actDetails=data;
			
		}
		
		
		function getActMastersOld()
		{
				$http.get(urlBase+'ecourt/getActMasterList').
		        success(function (data) {
		        	$scope.actDetailsList1=data.modelList;
		        	
		        	angular.forEach($scope.actDetailsList1, function(value, key) {
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
		
		$scope.newAct_type=1;
		
		function getActMasters()
		{
			$scope.actDetailsList=[];
			$scope.actId=null;
			
			if($scope.newAct_type==1){
				$scope.actDetails.act_type=$scope.newAct_type;
				$scope.actId=1;
				
			}
			else{
				$scope.actDetails.act_type=$scope.newAct_type;
				$scope.actId=2;
			}
				$http.get(urlBase+'ecourt/getActMasterList2024/'+$scope.actId).
		        success(function (data) {
		        	$scope.actDetailsList=data.modelList;
		        	
		        	
		        	
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
		};
		
		
		
		$scope.getSecMasters =function(actCode)
		{
			
           $scope.actId=null;
			
			if($scope.actDetails.act_type==1){
				
				$scope.actId=1;
				
			}
			else{
				$scope.actId=2;
			}
			
				$http.get(urlBase+'ecourt/getSecMasterList2024/'+actCode).
		        success(function (data) {
		        	$scope.sectionList=data.modelList;
		        	//$scope.sectionList=$scope.actDetailsList;
		        	
		        	
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
		};
		
		
		

		/*
		 * getActDetails();
		 * 
		 * function getActDetails(){
		 * $http.get(urlBase+'ecourt_add_case/getActDetails'). success(function
		 * (data) { $scope.actDataList=data.modelList; }). error(function(data,
		 * status, headers, config) { console.log("Error in getting tree data");
		 * }); };
		 * 
		 */
		
		
/*		$scope.stateAct= function(actcode){
			$scope.actDetails.act_code=actcode;
		}
		*/

		$scope.addActDetails=function(actDetails){
			
			if($scope.act_code2!=null || !angular.isUndefined($scope.act_code2)){
			$scope.actDetails.act_code=$scope.act_code2;
			}
			if($scope.act_section2!=null || !angular.isUndefined($scope.act_section2)){
				$scope.actDetails.act_section=$scope.act_section2;
				}
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			if(actDetails.act_type==1)
				actDetails.act_type='central';
			actDetails.act_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addActDetails',actDetails);
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
		
		
		
		$scope.deleteActDetails=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteActDetails/' + id + '/'
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
		
/** ***************************ACT DETAILS ENDED HERE*************************** */
		
		
	
		
		
		/** ******************Trial court Code******************************** */
		
		
    $scope.trialCourt={};
		
		
		
		$scope.editTrial=function(data){
			$scope.trialCourt=data;
			
		}
		
		

		/*
		 * getTrialCourt();
		 * 
		 * function getTrialCourt(){
		 * $http.get(urlBase+'ecourt_add_case/getTrialCourt'). success(function
		 * (data) { $scope.trialDataList=data.modelList; }).
		 * error(function(data, status, headers, config) { console.log("Error in
		 * getting tree data"); }); };
		 */
			
		
		

		$scope.addTrialCourt=function(){
			
			$scope.model={};
			// $scope.model.tr_sub_courtname =$scope.trialCourt.tr_sub_courtname
			// ;
			/*
			 * $scope.model.tr_judge_name=$scope.trialCourt.tr_judge_name;
			 * $scope.model.tr_case_no=$scope.trialCourt.tr_case_no;
			 * $scope.model.tr_district=$scope.trialCourt.tr_district;
			 * $scope.model.tr_decision_date=$scope.trialCourt.tr_decision_date;
			 * $scope.model.tr_rcd_mid=$scope.registerCase.rcd_id;
			 * $scope.model.tr_ct_mid=$scope.trialCourt.tr_ct_mid;
			 * $scope.model.tr_lct_mid=$scope.trialCourt.tr_lct_mid;
			 * $scope.model.tr_s_id=$scope.trialCourt.tr_s_id;
			 */
			$scope.trialCourt.tr_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addTrialCourt',$scope.trialCourt);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("TrialCourt Added Successfully!");
					$scope.trialCourt="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getTrialCourt($scope.draftId);	
					}
					else{
						getTrialCourt($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" trialCourt Updated Successfully!");
							$scope.trialCourt="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getTrialCourt($scope.draftId);	
							}
							else{
								getTrialCourt($scope.registerCase.rcd_id);
							}
				   }
		
				
			});	
		}
		
		
		
		
		
		$scope.deleteTrialCourt=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteTrialCourt/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
				    alert("  trial Court Deleted Successfully!");
				    if($scope.draftId!=null && $scope.draftId!=undefined){
						getTrialCourt($scope.draftId);	
					}
					else{
						getTrialCourt($scope.registerCase.rcd_id);
					}
				 	
				   }
		
				
			});	
		}
		
		}
		
		/** ************************End of Trial court********************** */
		
		
		$scope.courtFee={};

		 $scope.editCourtFee=function(row){
			 $scope.courtFee=row;
			 
		 }
		$scope.addCourtFee=function(){debugger
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
		
			/*
			 * $scope.model={}; $scope.model.cf_date=$scope.courtFee.cf_date;
			 * $scope.model.cf_amount=$scope.courtFee.cf_amount;
			 * $scope.model.cf_receipt_no=$scope.courtFee.cf_receipt_no;
			 * 
			 * $scope.model.cf_rcd_mid=$scope.registerCase.rcd_id;
			 */
			
			$scope.courtFee.cf_rcd_mid=$scope.registerCase.rcd_id;
			
			var response =$http.post(urlBase+'ecourt_add_case/addCourtFee',$scope.courtFee);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("CourtFee Added Successfully!");
					$scope.courtFee="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCourtFee($scope.draftId);	
					}
					else{
						getCourtFee($scope.registerCase.rcd_id);
					}
				   }
				   
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" courtFee Updated Successfully!");
							$scope.courtFee="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCourtFee($scope.draftId);	
							}
							else{
								getCourtFee($scope.registerCase.rcd_id);
							}
				   }
				   else if(data.data=="Exists"){
					   alert("This Receipt no. has already been consumed with any other case. Please Enter any other receipt no.");
					   if($scope.draftId!=null && $scope.draftId!=undefined){
							getCourtFee($scope.draftId);	
						}
						else{
							getCourtFee($scope.registerCase.rcd_id);
						}
				   }
		
				
			});	
		}
		
		
		
		
		
		$scope.deleteCourtFee=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteCourtFee/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
				    alert("  Court fees Deleted Successfully!");
				    if($scope.draftId!=null && $scope.draftId!=undefined){
						getCourtFee($scope.draftId);	
					}
					else{
						getCourtFee($scope.registerCase.rcd_id);
					}
				 	
				   }
		
				
			});	
		}
		
		}
		
		
		
		
		
		/** ********************** Court fees Code Ends********************** */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		$scope.defaultDist=function(){
			$scope.crimeDetail.cd_district=$scope.registerCase.rcd_dt_id;
			// $scope.extraCaveatImpugnedOrder.ec_io_district=$scope.caveat.cav_dist_mid;
		}
		
		
		
		
		
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




        $scope.motorShow=false;
          $scope.taxShow=false;
          $scope.landShow=true;
		$scope.changeMatters=function(event){
			if(event.target.id=="land"){
				$scope.motorShow=false;
				$scope.taxShow=false;
				$scope.landShow=true;
			
			}else if(event.target.id=="motor"){
				$scope.motorShow=true;
				$scope.landShow=false;
				$scope.taxShow=false;
		}
			else{
				$scope.taxShow=true;
				$scope.motorShow=false;
				$scope.landShow=false;
				
				
			}
					
			};
		
			
	
	 $scope.caseTypeOptions= [
			      {id: '1', value: 'Option A'},
			      {id: '2', value: 'Option B'},
			      {id: '3', value: 'Option C'}
			    ];
	 
		$scope.caseDetail= $scope.caseTypeOptions[0].value;
		
		// //////////////////////////////////////////Document Upload
		// Start//////////////////////////////////////////////
		
		$scope.documentTypes={};
		$scope.indexField={};
		$scope.picFile='';
		getDocumentTypes();
		
		$scope.selectedNode="";
		
		$scope.showSelected = function(sel)
	    {
	        $scope.selectedNode = sel;
	    };
		
	     function getDocumentTypes(){
	    	 
				$http.get(urlBase+'ecourt_add_case/getDocumentTypes').
		        success(function (data) {
		        	$scope.documentTypesList=data.modelList;
		        	console.log($scope.documentTypesList);
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
			};
			
			function getUploadedDocuments(){
				var id;
				if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
				{id=$scope.draftId;
				}else{id=$scope.registerCase.rcd_id}
			
		    	 
				$http.get(urlBase+'ecourt_add_case/getUploadedDocuments', {params : {'rcd_id' : id}}).
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

		    // listen for the file selected event
		    $scope.$on("fileSelected", function (event, args) {
		    	$scope.$apply(function () {            
		            $scope.files.push(args.file);
		        });
		    });
		    
		    function getFileSize()
		    {
		    $http.get(urlBase+'ecourt_add_case/getFileSize').
		    success(function (data) {
		    $scope.fileSizeObject=data.modelData;
		    console.log("File size------------------------",$scope.fileSizeObject);
		    }).
		    error(function(data, status, headers, config) {
		    console.log("Error in getting tree data");
		    });
		    };

		     getFileSize();
		    
		     
		     
		    $scope.uploadFile=function()
		    {

		    	console.log($scope.picFile.name);
		    var fileSizeFlag=false;
		    console.log("In Upload File");
		    $scope.fileSizeObject.lk_value;
		    var a = parseInt($scope.fileSizeObject.lk_value);
		    a= a*1024;
		    var fi = document.getElementById('fileCheck');
		    
		    $scope.picFile.name=fi.files.item(i).name;
		    
		    if (fi.files.length > 0) {
		    for (var i = 0; i <= fi.files.length - 1; i++) {

		    var fsize = fi.files.item(i).size;
		    var file = Math.round((fsize / 1024));
		    // The size of the file.
		    if (file <= a) {
		    fileSizeFlag =true;
		    /*
			 * alert( "File Size is Correct "+$scope.fileSizeObject.lk_value+"
			 * mb");
			 */
		    } else {
		    console.log("In else condition")
		    alert(
		    "File too Big, please select a file less than"+$scope.fileSizeObject.lk_value+" mb");
		    $scope.picFile='';
		    }
		    }
		    }
		    if(fileSizeFlag){

		    if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
		    {
		    alert("First add Casefile details");
		    return false;
		    }
		    $scope.fileButton=true;
		    var str=$scope.picFile.name;
		    var extn = str.split(".").pop();
		    extn=extn.toLowerCase();
		    if(extn!="pdf"){
		    alert("Only pdf format are allowed");
		    return false;
		    }
		    $scope.buttonDisabled=true;
		    $scope.fileButton=true;
		    var file=$scope.picFile;
		    if($scope.selectedNode!=""){
		    $scope.document.folder_id=$scope.selectedNode.id;
		    }
		    file.upload = Upload.upload({
		    url: urlBase + 'ecourt_add_case/upload_file',
		    params:{rcd_id: $scope.registerCase.rcd_id},
		    headers: {
		    'optional-header': 'header-value'
		    },
		    fields:$scope.document,
		    file:file,
		    });
		    if(file.upload){
		    file.upload.then(function (response) {
		    $scope.buttonDisabled=false;
		    $scope.fileButton=false;
		    if(response.data.response=="TRUE"){
		    // $scope.errorlist =null;
		    $scope.errorlist = response.data.dataList;
		    console.log($scope.errorlist);
		    // bootbox.alert("Uploading Done");
		    // $("#documentCreate").modal("hide");
		    $scope.document={};
		    // window.location.reload();

		    alert("Files Uploaded successfully...");
		    getUploadedDocuments();
		    $scope.picFile='';
		    fileSizeFlag =false;

		    }else{
		    $scope.errorlist = response.data.dataMapList;
		    }
		    }, function (response) {

		    }, function (evt) {
		    // Math.min is to fix IE which reports 200% sometimes
		    // file.progress = Math.min(100, parseInt(100.0 * evt.loaded /
			// evt.total));
		    });

		    file.upload.xhr(function (xhr) {
		    // xhr.upload.addEventListener('abort',
			// function(){console.log('abort complete')}, false);
		    });
		    }else{$scope.buttonDisabled=false;}
		    }

		    }
		    
/*
 * $scope.uploadFile=function() {
 * 
 * if($scope.registerCase.rcd_id==null ||
 * angular.isUndefined($scope.registerCase.rcd_id)) { alert("First add Casefile
 * details"); return false; } var str=$scope.picFile.name; var extn =
 * str.split(".").pop(); extn=extn.toLowerCase(); if(extn!="pdf"){ alert("Only
 * pdf format are allowed"); return false; } $scope.fileButton=true;
 * $scope.buttonDisabled=true; var file=$scope.picFile;
 * if($scope.selectedNode!=""){
 * $scope.document.folder_id=$scope.selectedNode.id; } file.upload =
 * Upload.upload({ url: urlBase + 'ecourt_add_case/upload_file', params:{rcd_id:
 * $scope.registerCase.rcd_id}, headers: { 'optional-header': 'header-value' },
 * fields:$scope.document, file:file, }); if(file.upload){
 * file.upload.then(function (response) { $scope.buttonDisabled=false;
 * $scope.fileButton=false; if(response.data.response=="TRUE"){ //
 * $scope.errorlist =null; $scope.errorlist = response.data.dataList;
 * console.log($scope.errorlist); //bootbox.alert("Uploading Done"); //
 * $("#documentCreate").modal("hide"); $scope.document={};
 * //window.location.reload();
 * 
 * alert("Files Uploaded successfully..."); getUploadedDocuments();
 * $scope.picFile='';
 * 
 * }else{ $scope.errorlist = response.data.dataMapList; } }, function (response) {
 *  }, function (evt) { // Math.min is to fix IE which reports 200% sometimes
 * //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
 * });
 * 
 * file.upload.xhr(function (xhr) { // xhr.upload.addEventListener('abort',
 * function(){console.log('abort complete')}, false); });
 * }else{$scope.buttonDisabled=false;}
 * 
 *  }
 */
		
			
			$scope.deleteDocument=function(id){
				  var result=confirm("Are you really want to Remove");
					if (result) {
				   $http({
					method : 'DELETE',
					url : urlBase + 'ecourt_add_case/deleteDocument/' + id + '/'
				}).success(function(res) {
					   if(res.response=="TRUE"){
						alert(" Document Deleted Successfully!");
						if($scope.registerCase.rcd_id!=null && $scope.registerCase.rcd_id!=undefined){
						// getDocument($scope.registerCase.rcd_id);
							getUploadedDocuments();
						}
						
					   }
					
				});	
			}
			
			}
		
		$scope.searchCaveat=function(){
			 $http.get(urlBase+ 'ecourt/searchCaveat', {
					params : {
						'docId' : $scope.registerCase.rcd_id
					}
				}).success(function(data, status, headers, config) {
			                
			              $scope.caveatDataList = data.modelList;
			             
			              // searchCaveatold();
			}).error(function(data, status, headers, config) {
				});

		}
		 // unuesed code old caveat
		
		/*
		 * function searchCaveatold(){ $http.get(urlBase+
		 * 'ecourt/searchCaveatold', { params : { 'docId' :
		 * $scope.registerCase.rcd_id } }).success(function(data, status,
		 * headers, config) {
		 * 
		 * $scope.oldcaveatDataList = data.modelList;
		 * 
		 * 
		 * }).error(function(data, status, headers, config) { });
		 *  }
		 */
		
		
		// ///////////////Document Upload Close///////////////
	
	 
		/** **********************link Case ******************************** */
		
		
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
		
		 getLinkedCase($scope.draftId);
		 
			$scope.LinkedCaseList=[]; 
			function getLinkedCase(id){
			    $http.get(urlBase+ 'ecourt_add_case/getLinkedCase', {
					params : {
						'rcd_id' : id
					}
				}).success(function(data, status, headers, config) {
			              $scope.LinkedCaseList = data.modelList;
			               
			}).error(function(data, status, headers, config) {
				});

			}
		
		 $scope.case_link=function(data){
			 $scope.LinkedCaseDetails.lcd_case_year=data.fd_case_year;
			 $scope.LinkedCaseDetails.lcd_case_type=data.fd_case_type;
			 $scope.LinkedCaseDetails.lcd_case_no=data.fd_case_no;
			 $scope.LinkedCaseDetails.lcd_first_petitioner=data.fd_first_petitioner;
			 $scope.LinkedCaseDetails.lcd_first_respondent=data.fd_first_respondent;
			 $scope.LinkedCaseDetails.lcd_rcd_mid=$scope.registerCase.rcd_id;
			 
			 var response =$http.post(urlBase+'ecourt_add_case/addLinkedCase',$scope.LinkedCaseDetails);
				response.success(function(data, status, headers, config){
					   if(data.response=="TRUE"){
						alert("case Linked Successfully!");
						getLinkedCase($scope.registerCase.rcd_id);
					
						
						
					   }
					   });
						 
			 
		 };
		 
		 $scope.delete_linkCase=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/delete_linkCase/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert(" case Removed Successfully!");
					if($scope.registerCase.rcd_id!=null && $scope.registerCase.rcd_id!=undefined){
					// getDocument($scope.registerCase.rcd_id);
						getLinkedCase($scope.registerCase.rcd_id);
					}
					
				   }
				
			});	
		}
		 }
		 
		 
			$scope.uploadFileExcel=function() 
			{
				
				if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
				var str=$scope.picFile.name;
				 var extn = str.split(".").pop();
				 extn=extn.toLowerCase();
				 if(extn!="xlsm"){
					 alert("Only xlsm format are allowed");
					 return false;
				 }
				 
				$scope.buttonDisabled=true;
				  var file=$scope.picFile;
				/*
				 * if($scope.selectedNode!=""){
				 * $scope.document.folder_id=$scope.selectedNode.id; }
				 */
				    file.upload = Upload.upload({
				      url: urlBase + 'ecourt_add_case/upload_fileExcel',
				      params:{rcd_id: $scope.registerCase.rcd_id},
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
				        // $scope.errorlist =null;
				        	$scope.errorlist = response.data.dataList;
				        	console.log($scope.errorlist);
				        	// bootbox.alert("Uploading Done");
				        // $("#documentCreate").modal("hide");
				        	
				        	// window.location.reload();
				        	
				        			alert("Files Uploaded successfully...");
				        			// getUploadedDocuments();
				        			$scope.picFile='';	
				        }
				        else{
				        	$scope.errorlist = response.data.dataMapList;
				        }
				      }/*
						 * , function (response) {
						 *  }, function (evt) { // Math.min is to fix IE which
						 * reports 200% sometimes //file.progress =
						 * Math.min(100, parseInt(100.0 * evt.loaded /
						 * evt.total)); }
						 */
				    );
				    
				    file.upload.xhr(function (xhr) {
				        // xhr.upload.addEventListener('abort',
						// function(){console.log('abort complete')}, false);
				      });
			}else{$scope.buttonDisabled=false;}

			alert("Files Uploaded successfully...");
				} 
			
			
			 
			
}]);