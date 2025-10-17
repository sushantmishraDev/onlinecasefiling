
var EDMSApp = angular.module('EDMSApp', ['ui.bootstrap']);

EDMSApp.controller('AmendmentController',['$scope','$http',function ($scope, $http) {
	var urlBase="/onlinecasefiling/";
	$scope.users=[];
	$scope.searchedusers=[];
	getApplicationTypes();
	$scope.model={};
	$scope.fd_id= $('#fd_id').val();
	getAdvocates();
	getAmendments();
	$scope.searchuser=false;
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
	 
	 function getAdvocates(){
		  var response =$http.get(urlBase+'amendment/getadvocates/'+$scope.fd_id);
			response.success(function(data, status, headers, config){				
				   if(data.response=="TRUE"){
					$scope.users=data.modelList;
				   }
				   else{					   
					alert(data.data);
				   }  
	  });
	  
	  }
	 function getAmendments(){
		  var response =$http.get(urlBase+'amendment/getamendments/'+$scope.fd_id);
			response.success(function(data, status, headers, config){				
				   if(data.response=="TRUE"){
					$scope.amendments=data.modelList;
				   }
			});	  
	  }
	 $scope.searchCaseFile=function(){
		 $scope.searchuser=true;
		 $scope.searchedusers=[];
		 $http.get(urlBase+'amendment/searchuser', {params : {'name' :$scope.search.name}}).
	        success(function (data) {
	        	if(data.response=="TRUE"){
	        		$scope.searchedusers=data.modelList;
	        	}else{
	        		alert("No Users found");
	        	}	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });		 
	 }
	 $scope.setUser=function(user){
		 $scope.user=user;
	 }
	 $scope.addUser=function(user,index){
		 $scope.userObj=user;
		 $scope.userObj.flag=true;
		 $scope.users.push($scope.userObj);
		 $scope.searchedusers.splice(index,1);
	 }
	 $scope.removeUser=function(user,index){
		 $scope.userObj=user;
		 $scope.searchedusers.push($scope.userObj);
		 $scope.users.splice(index,1);
	 }
	 $scope.addAmendment=function(at_id){
		 $scope.amendment={'am_type':'A','am_at_mid':at_id,'am_fd_mid':$scope.fd_id,'am_um_mid':$scope.user.um_id};
		 
		 	$http.post(urlBase+'amendment/addamendment',$scope.amendment).success(function (data) {
		    	if(data.response=="TRUE"){
		    		$scope.amendments.push(data.modelData);
		    	}
		    	alert(data.data);
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in adding amendment applicaion");
		      });
	 }
	 $scope.allowPetition=function(user){
		 $scope.user=user;
		 $scope.amendment={'am_type':'P','am_fd_mid':$scope.fd_id,'am_um_mid':$scope.user.um_id};
		 
		 	$http.post(urlBase+'amendment/addamendment',$scope.amendment).success(function (data) {
		    	if(data.response=="TRUE"){
		    		$scope.amendments.push(data.modelData);
		    		
		    	}
		    	alert(data.data);
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in adding amendment applicaion");
		      });
	 }
	 $scope.deleteAmendment=function(am_id){
		  var response =$http.get(urlBase+'amendment/deleteamendment/'+am_id);
			response.success(function(data, status, headers, config){				
				   if(data.response=="TRUE"){
					alert(data.data);
				   }
				   else{					   
					alert(data.data);
				   }  
			});	  
	  }
}]);