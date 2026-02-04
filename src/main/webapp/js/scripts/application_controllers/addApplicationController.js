
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

EDMSApp.controller('addApplicationController',
['$scope','$http','Upload','$window','$timeout',
 function ($scope, $http, Upload, $window, $timeout) {


	var urlBase="/onlinecasefiling/";
	
	$scope.petitionerDataList=[];
	$scope.respondentDataList=[];
	$scope.courtFeeList=[];
	$scope.formShow=true;
	$scope.petitionerDetails={};
	$scope.registerCase={};
	
	$scope.application={};
	
	
	$scope.application.subApplication = [ {
		'sb_ap_rec_status':1
	} ];
	
	$scope.caseDetail={};
	$scope.tabShow1 = true;
	$scope.tabShow2 = true;
	$scope.tabShow3 = true;
	$scope.tabShow4 = true;
	$scope.tabShow5 = true;
	$scope.tabShow6 = true;


	
	
	$scope.draftId= $('#draftId').val();
	$scope.fd_id= $('#fd_id').val();
	$scope.codeNo= $('#code').val();
	$scope.appno= $('#appno').val();
	
	
	

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
	
	
	
	
	$scope.subAppStatus=false;
	   
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
		                $scope.subAppStatus=true;
		                if($scope.application.subApplication.length==0) {
		                	$scope.subAppStatus=false;
			                $scope.application.subApplication = [ {
			                	'sb_ap_mid' : $scope.application.ap_id,
			            		'sb_ap_rec_status':1
			            	} ]; 
		                }
		                
		                console.log($scope.application);
		               
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

				
				  
		
	
	$scope.addApplication=function(application,otherApp){
		console.log($scope.subapplications);
		 $scope.newString = $("#txtEditor1").Editor("getText");
		 $scope.newString1 = $("#txtEditor").Editor("getText");
		 application.ap_lstng_desc= $scope.newString;
		 application.ap_lstng_prayer= $scope.newString1;
		console.log(application);
		application.code=Number($scope.codeNo);
		application.appno =	$scope.appno
		
		
		$scope.entity = {
				'application' : application,
				'otherAppNos' : otherApp
			};
		
		console.log($scope.entity);
		
		if(application.ap_fd_mid==null){
			application.ap_fd_mid=$scope.fd_id;	
			$scope.subAppStatus=true;
		}
		if(application.ap_fd_mid==null){
				alert("Case File Details not found");
				return false;
		}
		
		$scope.validSubApp=true;
		for(var i=1;i<$scope.entity.otherAppNos.length;i++) 
		{
			if($scope.entity.otherAppNos[i].sb_ap_at_mid!=null){
			}
			else {
					$scope.validSubApp=false;
					break;
			}
		}
		if($scope.validSubApp==true)
			{

			var response =$http.post(urlBase+'application/addApplication',application);
			response.success(function(data, status, headers, config){
				   if(data.response=="ADD"){
					   
					alert("Application Added Successfully!");
					$scope.application=data.modelData;
					
				   }
				   else if(data.response=="UPDATE"){
					   
					alert("Application Updated Successfully!");
					 window.location.reload(true); 
				   }
				   
			});	
			
			
		}
		else
			{
			alert("Please Select Applications Type");
			}
		/*
		var response =$http.post(urlBase+'application/addApplication',application);
		response.success(function(data, status, headers, config){
			   if(data.response=="ADD"){
				   
				alert("Application Added Successfully!");
				window.location.reload(true); 
				$scope.application=data.modelData;
			   }
			   else if(data.response=="UPDATE"){
				   
				alert("Application Updated Successfully!");
				 window.location.reload(true); 
			   }
	
			
		});	*/
		
		
		
	}



	$scope.deleteSubApplication=function(id){
		  var result=confirm("Do you want to Remove this Application");
			if (result) {
		   $http({
			method : 'DELETE',
			url : urlBase + 'application/deleteSubApplication/' + id + '/'
		}).success(function(res) {
			   if(res.response=="TRUE"){
				   window.location.reload(true); 
				   //alert(" Application Deleted Successfully!");
				if($scope.application.ap_id!=null && $scope.application.ap_id!=undefined){
					
				}
				
			   }
			
		});	
	}
	
	}

$scope.ShowId = function(event)
{
	$scope.tabShow1=true;
	$scope.tabShow2=true;
	$scope.tabShow3=true;
	$scope.tabShow4=true;
};

getCaseDetails();

function getCaseDetails(){
	  $http.get(urlBase+'application/getCaseDetails/'+$scope.fd_id).success(function (data) {
	    		$scope.caseDetailsCIS=data.modelData;	
	    	
	    		
	    		
	    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS[0]);
	    		getPet();
	    		  getRes();
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting casetypes");
	      });
}

function getPet(){
	  $http.get(urlBase+'application/getPet/'+$scope.caseDetailsCIS[0]).success(function (data) {
	    		$scope.petitioner=data.modelData;	
	    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS[0]);
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting casetypes");
	      });
}

$scope.caseDetailsCIS=[];
function getRes(){
	  $http.get(urlBase+'application/getRes/'+$scope.caseDetailsCIS[0]).success(function (data) {
	    		$scope.respondent=data.modelData;	
	    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS);
	    		
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting casetypes");
	      });
}

	
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
			
			

			$scope.addNewColumn = function() {
				$scope.counter++;

				var newItemNo = $scope.application.subApplication.length + 1;
				$scope.application.subApplication.push({/*
					'colId' : 'col' + newItemNo*/
					'sb_ap_mid' : $scope.application.ap_id,
					'sb_ap_rec_status':1
				});

			};
			
			$scope.removeFlag = false;
			$scope.removeColumn = function(index) {
				$scope.counter--;

				console.log($scope.application.subApplication);
				if (index != 0) {
					$scope.application.subApplication.splice(index, 1);

					$scope.addFieldFlag = false;
				}

				console.log(index.length);

				$scope.removeFlag = true;

			};
			
			$scope.downloadApplication = function () {

			    $timeout(function () {

			        var element = document.getElementById('pdfPrep');

			        var textareas = element.querySelectorAll("textarea");
			        var backup = [];

			        angular.forEach(textareas, function (ta) {
			            var div = document.createElement("div");
			            div.innerText = ta.value || ta.placeholder;
			            div.style.whiteSpace = "pre-wrap";
			            div.style.fontFamily = "Times New Roman, serif";
			            div.style.fontSize = "11pt";
			            div.style.lineHeight = "1.5";

			            backup.push({ parent: ta.parentNode, ta: ta, div: div });
			            ta.parentNode.replaceChild(div, ta);
			        });

			        var opt = {
			            margin: 10,
			            filename: 'Listing_Application.pdf',
			            image: { type: 'jpeg', quality: 1 },
			            html2canvas: {
			                scale: 3,
			                useCORS: true,
			                scrollY: 0
			            },
			            jsPDF: {
			                unit: 'mm',
			                format: 'a4',
			                orientation: 'portrait'
			            },
			            pagebreak: { mode: ['css', 'legacy'] }
			        };

			        html2pdf()
			            .set(opt)
			            .from(element)
			            .save()
			            .then(function () {
			                angular.forEach(backup, function (b) {
			                    b.parent.replaceChild(b.ta, b.div);
			                });
			            });

			    }, 300); // ⬅ wait for Angular DOM
			};

	
}]);