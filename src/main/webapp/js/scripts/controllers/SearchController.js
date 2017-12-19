/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['smart-table','ui.bootstrap','treeControl','ngMask']);

edmsApp.directive('loading', ['$http', function ($http) {
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
/**
 * Controller in index.jsp
 */

edmsApp.controller("searchController",['$scope','$http', function($scope,$http) {
	var baseUrl="/dms/";
	$scope.folder={};
	$scope.search={};
	$scope.searchrowid=0;
	//getFoldersdata();
	getRepositories();
	getMetafields();
	getSearchQueries();
	$scope.selectedNode="";
    $scope.treedata=[];
    $scope.error;
    $scope.searchcriterias=[];
    $scope.searchquery={};
    $scope.searchqueries=[];

    $scope.documentlist=[];
    $scope.metafields=[];
    $scope.usedmetafields=[];
    $scope.searchcriteria={};
    $scope.caseTypes=[];
    $scope.benchCodes=[];
    $scope.basicsearch={};
    $scope.years=[];
    $scope.documentlist=[];
    $scope.metafields=[];
    $scope.usedmetafields=[];
    $scope.searchcriteria={};
    $scope.date1='';
    $scope.date2='';
    
    $scope.criterias=[{"id":"%like%","value":"%Like%"},{"id":"%like","value":"%Like"},{"id":"like%","value":"Like%"},{"id":"equalto","value":"Equal To"},{"id":"in","value":"IN"},{"id":"notin","value":"Not In"},{"id":"between","value":"Between"}];
    $scope.opts = {
        nodeChildren: "childrens"
    };
    $scope.showSelected = function(sel)
    {
        $scope.selectedNode = sel;
    };
    getBenchdata();
    getCaseTypedata();
    getYears();
    getJudgeNamedata();
	function getBenchdata() {
		var response = $http.get(baseUrl+'report/getbenchcode');
		response.success(function(data, status, headers, config) {
			$scope.benchCodes = data.branchData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getCaseTypedata() {
		var response = $http.get(baseUrl+'report/getcasetypedata');
		response.success(function(data, status, headers, config) {
			$scope.caseTypes = data.casetypeData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getFoldersdata()
	{
		$http.get(baseUrl+'folder/getallfolders').
        success(function (data) {
        	$scope.masterdata = data;
        	$scope.displayedCollection = [].concat($scope.masterdata);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	}
	function getYears() {
		var currentYear = new Date().getFullYear();
		for (var year = 1950; year <= currentYear; year++) {
			$scope.years.push(year);
			
		}
	}
	function getJudgeNamedata() {
		var response = $http.get(baseUrl+'report/getjudgeDetails');
		response.success(function(data, status, headers, config) {
			$scope.judges = data.JudgeName;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	};
	
	function getRepositories(){
		$http.get(baseUrl+'repository/getrepositories').
        success(function (data) {
        	$scope.repositories = data;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	function getMetafields(){
		$http.get(baseUrl+'metatemplate/getmetafields').
        success(function (data) {
        	$scope.metafields = data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	function getSearchQueries(){
		$http.get(baseUrl+'document/getsearchqueries').
        success(function (data) {
        	$scope.searchqueries = data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	function getSearchQueries(){
		$http.get(baseUrl+'document/getsearchqueries').
        success(function (data) {
        	$scope.searchqueries = data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	}
//	$scope.changeTextField=function(metafieldid){
//		if(metafieldid!=null && metafieldid==12){
//			// if metafields is disposal date
//		}else{
//			
//		}
//	}
	$scope.getsearchform=function(){
		if($scope.searchcriteria.type=="metafields"){
			$("#metafieldid").show();
		}else{
			$("#metafieldid").hide();
		}
		$scope.searchcriteria.metafield='';
		$scope.searchcriteria.operator='';
		$scope.searchcriteria.searchtext='';
		$scope.searchcriteria.criteria='';
	};
	$scope.searchdocument=function(){
		$scope.entity={"searchlist":$scope.searchcriterias};
		$http.post(baseUrl+'document/searchdocument',$scope.entity).
        success(function (data) {
        	$scope.documentlist=data.modelList;
        	$scope.displayedCollection=[];
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
		
	};
	
	$scope.searchdocument=function(){
		$scope.entity={"searchlist":$scope.searchcriterias};
		$http.post(baseUrl+'document/searchdocument',$scope.entity).
        success(function (data) {
        	$scope.documentlist=data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
		
	};
	
	$scope.createquery=function(searchcriteria,operator){
		$scope.searchcriteria=searchcriteria;
		$scope.error="";
		if($scope.searchcriteria.type=="" || $scope.searchcriteria.type== null){
			$scope.error="Please select search field type";
			return false;
		}else{
			
			if($scope.searchcriteria.type=="metafields"){
				if($scope.searchcriteria.metafield=="" || $scope.searchcriteria.criteria=="" || $scope.searchcriteria.searchtext==""){
					$scope.error="Please select required fields";
					return false;
				}
			}
			if($scope.searchcriteria.type=="searchincontent"){
				if($scope.searchcriteria.criteria=="" || $scope.searchcriteria.searchtext==""){
					$scope.error="Please select required fields";
					return false;
				}
			}
		}
		var update=false;
		
		$scope.searchcriteria.operator=operator;
		$scope.search.metafieldname="";
		angular.forEach($scope.metafields,function(value,index){
			if(value.mf_id == $scope.searchcriteria.metafield){
				$scope.searchcriteria.metafieldname=value.mf_lable;	
			}
        });
		if(!update)
		{
			if($scope.searchcriteria.metafield==12){
				$scope.searchcriteria.searchtext=$scope.date1+","+$scope.date2;
			}
			$scope.searchcriterias.push($scope.searchcriteria);
		}
		else
		{
			for (i in $scope.searchcriterias) {
				if ($scope.searchcriterias[i].searchrowid == $scope.searchcriteria.searchrowid) {
                	$scope.searchcriterias[i]=$scope.searchcriteria;
                	//data = angular.copy($scope.sellers[i]);
                }
			}
		}
		$scope.searchcriteria='';
	};
	$scope.newquery=function(){
		$scope.searchcriterias=[];
	};
	$scope.savequery=function()
	{
		$scope.entity={"criterias":$scope.searchcriterias};
		$scope.searchquery.criterias=$scope.searchcriterias;
		$http.post(baseUrl+'document/savequery',$scope.searchquery).
        success(function (data) {
        	$scope.searchquery=data.modelData;
        	$scope.searchcriterias=data.modelData.criterias;
        	$scope.addQuery();
        	bootbox.alert("Query Saved Successfully", function() {});
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	
	$scope.addQuery=function(){
		
		var queryexist=false;
		angular.forEach($scope.searchqueries,function(value,index){
			if(value.id == $scope.searchquery.id){
				queryexist=true;	
			}
        });
		if(queryexist){
			var index = -1;
			angular.forEach($scope.searchqueries,function(value,i){
				if(value.id==$scope.searchquery.id){
					index=i;
				}
	        });
			if(index!=-1){
				$scope.searchqueries.splice(index,1);
			}
		}
		
		$scope.searchqueries.push($scope.searchquery);
		
	};
	$scope.setQuery=function(searchquery){
		$scope.searchquery=searchquery;
		$scope.searchcriterias=$scope.searchquery.criterias;
		$('#queriesModal').modal('hide');
	};
	$scope.deleteQuery=function(searchquery){
		$scope.entity=searchquery;
		bootbox.confirm({className:'delete',message: "Are you sure ?", 
		    buttons:{
		    	confirm: {   
		    		 label: "Delete",
		    		 className: "btn-success",
		    		 callback: function(result) {}
		    	 },
		    	 cancel: {
		    		 label: "Cancel",
				      className: "btn-danger",
				      callback: function(result) {}
				    },
		    },
		    callback: function(result){ /* your callback code */ 		
		    	if(result) {
		    		var response = $http.post(baseUrl+'document/deletequery',$scope.entity);
					response.success(function(data, status, headers, config) {					
						bootbox.alert("Query Successfully Deleted.", function() {});
						var index = -1;
						for( var i = 0; i < $scope.searchqueries.length; i++ ) {
							if( $scope.searchqueries[i].id === searchquery.id ) {					
								index = i;
								break;
							}
						}
						
						if(index!=-1)
						{
							$scope.searchqueries.splice(index,1);
						}
						if($scope.searchquery.id==searchquery.id)
						{
							$scope.searchquery={};
							$scope.searchcriterias=[];
						}
					});
					response.error(function(data, status, headers, config) {
						alert( "Angular Post Error");
					});
				}
		    }
		});
		
		
	};
	$scope.editCondition=function(searchcriteria){
		$scope.searchcriteria=searchcriteria;
	};
	$scope.deleteCondition=function(searchcriteria){
		$scope.searchcriteria=searchcriteria;
		var index = -1;
		for( var i = 0; i < $scope.searchqueries.length; i++ ) {
			if( $scope.searchqueries[i].id === searchcriteria.id ) {					
				index = i;
				break;
			}
		}
		
		if(index!=-1)
		{
			$scope.searchqueries.splice(index,1);
		}
		if($scope.searchquery.id==searchcriteria.id)
		{
			$scope.searchcriterias=[];
		}
		$scope.searchcriteria={};
	};
	$scope.setQuery=function(searchquery){
		$scope.searchquery=searchquery;
		$scope.searchcriterias=$scope.searchquery.criterias;
		$('#queriesModal').modal('hide');
	};
	$scope.deleteQuery=function(searchquery){
		$scope.entity=searchquery;
		bootbox.confirm({className:'delete',message: "Are you sure ?", 
		    buttons:{
		    	confirm: {   
		    		 label: "Delete",
		    		 className: "btn-success",
		    		 callback: function(result) {}
		    	 },
		    	 cancel: {
		    		 label: "Cancel",
				      className: "btn-danger",
				      callback: function(result) {}
				    },
		    },
		    callback: function(result){ /* your callback code */ 		
		    	if(result) {
		    		var response = $http.post(baseUrl+'document/deletequery',$scope.entity);
					response.success(function(data, status, headers, config) {					
						bootbox.alert("Query Successfully Deleted.", function() {});
						var index = -1;
						for( var i = 0; i < $scope.searchqueries.length; i++ ) {
							if( $scope.searchqueries[i].id === searchquery.id ) {					
								index = i;
								break;
							}
						}
						
						if(index!=-1)
						{
							$scope.searchqueries.splice(index,1);
						}
						if($scope.searchquery.id==searchquery.id)
						{
							$scope.searchquery={};
							$scope.searchcriterias=[];
						}
					});
					response.error(function(data, status, headers, config) {
						alert( "Angular Post Error");
					});
				}
		    }
		});
		
		
	};
	$scope.editCondition=function(searchcriteria){
		$scope.searchcriteria=searchcriteria;
	};
	$scope.deleteCondition=function(searchcriteria){
		$scope.searchcriteria=searchcriteria;
		var index = -1;
		for( var i = 0; i < $scope.searchqueries.length; i++ ) {
			if( $scope.searchqueries[i].id === searchcriteria.id ) {					
				index = i;
				break;
			}
		}
		
		if(index!=-1)
		{
			$scope.searchqueries.splice(index,1);
		}
		if($scope.searchquery.id==searchcriteria.id)
		{
			$scope.searchcriterias=[];
		}
		$scope.searchcriteria={};
	};
	$scope.getFolderStructure=function(){
		$scope.entity={"rep_id":$scope.folder.rep_id};
		console.log($scope.entity);
		$http.post(baseUrl+'folder/gettree',$scope.entity).
        success(function (data) {
        	$scope.treedata=data.treedata;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	$scope.create=function(folder){
		$scope.entity=folder;
		if($scope.selectedNode!=""){
			$scope.entity.parent_id=$scope.selectedNode.id;
		}
		$http.post(baseUrl+'folder/create',$scope.entity).
        success(function (data) {
        	if(data.response=="FALSE"){
				$scope.errorlist=data.dataMapList;
			}else{
			bootbox.alert("Successfully created folder");	
			$scope.masterdata.push(data.modelData);
			$scope.displayedCollection = [].concat($scope.masterdata);
			$("#folderCreate").modal('hide');	
			}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	$scope.getBasicSearchData= function() {
		
		$http.get(baseUrl+'report/getBasicSearchData', {
			params : {
				'caseTypeId' : $scope.basicsearch.caseTypeId,
				'benchCodeId' : $scope.basicsearch.benchCodeId,
				'judgeId' : $scope.basicsearch.judgeId,
				'year' : $scope.basicsearch.year,
				'caseNumber' : $scope.basicsearch.caseNumber
			}
		}).success(function(data) {
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			$scope.documentlist=[];
			
		}).error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});

	};
}]);