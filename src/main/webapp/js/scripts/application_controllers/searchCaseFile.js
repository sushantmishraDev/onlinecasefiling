
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











EDMSApp.controller('searchCaseFileController',['$scope','$http','$q',function ($scope, $http,$q) {
	var urlBase="/onlinecasefiling/";
	$scope.petDoc=null;  $scope.draftList=[];
	getCaseTypes();
	$scope.model={};
	$scope.fd_id= null;
	$scope.divShow=true;
	
	
	 function getCaseTypes()
	 {
		 console.log("petDoc",$scope.petDoc);
     	console.log("appDoc",$scope.draftList.length);
			$http.get(urlBase+'ecourt/getCaseTypes').
	        success(function (data) {
	        	$scope.caseTypeList=data.modelList;
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
	 };
	 
	 
	 $scope.validateCode=function(validate){
		 validate.ae_code= Number(validate.ae_code);
			window.open(urlBase+"searchcasefile/validateApplicationCode/"+validate.ae_code+","+validate.ae_appno,'_self');
				/*var response =$http.post(urlBase+'ecourt_add_case/validateCode',code);
				response.success(function(data, status, headers, config){
					   if(data.response=="TRUE"){
						   $scope.ct_id=data.modelData.ae_case_type;
						window.open("/onlinecasefiling/ecourt/addNewCase","_self");
						//$scope.registerCase.rcd_ct_id=data.modelData;
					   }
					   else if(data.data=="Update"){
						   
						alert(" caseDetails Updated Successfully!");
					   }
			
					
				});	*/
			}
	 
	 ///////////View all docs
	 
	 $scope.getDocList=function(){
			//getPetDoc($scope.fd_id);
	  		$http.get(urlBase+'application/getDocList/'+$scope.fd_id).success(function (data) {
	    	$scope.count=data.data;
	      	$scope.draftList=data.modelList;
	      	if($scope.draftList==null){
	      		$scope.divShow=false;
	      	}
	      	
	      	console.log("pet",$scope.petDoc);
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting tree data");
	      });
		};
		
		/*function getPetDoc(){*/
		
			$scope.getPetDoc=function(){
			
	  		$http.get(urlBase+'application/getPetDoc/'+$scope.fd_id).success(function (data) {
		    	//$scope.count=data.data;
		      	$scope.petDoc=data.modelData;
		      	if($scope.petDoc==null){
		      		$scope.divShow=false;
		      	}
		      	
		      //	getDocList();
		      	
		    	  
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
			};
			
			$scope.showDocument=function(selectedfile){
				var response = $http.get(urlBase+'scrutiny/copyFile',{params: {'pu_document_name': selectedfile.rcd_draft_no+".pdf"}});
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
			
			$scope.showDocuments=function(selectedfile){
				var response = $http.get(urlBase+'application/copyApplicationFile',{params: {'au_document_name': selectedfile.ap_draft_no+".pdf"}});
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
///////////////////view all docs
	 
	 
	 $scope.caseFileList = {}; 
	 $scope.searchCaseFileNew=function(){
		 $http.get(urlBase+'searchcasefile/searchCaseFileList', {params : {'case_year' :$scope.model.fd_case_year,'case_type' :$scope.model.fd_case_type,'case_no' :$scope.model.fd_case_no}}).
	        success(function (data) {
	        	
	        	if(data.Status == "Disposed"){
	        	  return alert("Filing of listing application is allowed in pending cases.This case is Disposed off. ");
	        	}
	        	
	        	
	        	if(data!=null && data.Case_id != 0){
	        //	$scope.caseFileList=data;
	        		$scope.caseFileList.caseId = data.Case_id;
	        		const caseParts = data.Case_number.split("/");
	        		$scope.caseFileList.caseType = caseParts[0];
	        		$scope.caseFileList.caseNo = caseParts[1];
	        		$scope.caseFileList.caseYear = caseParts[2];
	        		
	        		const partyParts = data.Party_Name.split(/\s+VS\s+/i);
	        		$scope.caseFileList.petitioner = partyParts[0].trim();
	        		$scope.caseFileList.respondent = partyParts[1].trim();
	        		
	        		 $http.get(urlBase+'application/getCheckListingAdv/'+$scope.caseFileList.caseId).
	     	        success(function (data) {
	     	        	$scope.isListingAllow=data.response;
	     	        	if(data.response=="TRUE"){
	     	        		/*alert("This Case is not Efiled");*/
	     	        	}else{
	     	        		//window.open(urlBase+"/ecourt/addApplication",'_self');
	     	        		
	     	        		alert("You are not registered advocate in this case");
	     	        	}
	     	        	
	     	        }).
	     	        error(function(data, status, headers, config) {
	     	        	console.log("Error in getting tree data");
	     	        });
	        		
	        	console.log("cseelist detail ===="+$scope.caseFileList);
	        	}else{
	        		//window.open(urlBase+"/ecourt/addApplication",'_self');
	        		alert("This Case is not found");
	        		
	        	}
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
		 
	 }
	 
	  $scope.applicationListing = function(Caseid) {
		   /* window.location.href =
		        urlBase + "application/listingApplication/" + Caseid;*/
		    
		    window.open( urlBase + "application/listingApplication/" + Caseid,'_balnk');
		};
		
		 $scope.part1=[];
		 $scope.part2=[];
		
		$scope.listingParty=function(){
			
			var fullURL = window.location.href;
			
			const url = new URL(fullURL);

			// Get the full path: "/folder/subfolder/value"
			const pathname = url.pathname; 
			
			const parts = pathname.split("/");
			const valueAfterLastSlash = parts.pop();
			
			var Caseid=Number(valueAfterLastSlash);
			
			 var partyApi = $http({
			        method: 'POST',
			        url: "http://192.168.0.114/testapi/API/CaseStatus/PartyDetailsByCaseId",
			        data: "Caseid=" + encodeURIComponent(Caseid),
			        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
			    });
			 
			 $q.all([partyApi])
		        .then(function (responses) {

		            // responses[0] -> Party API
		            $scope.allParty = responses[0].data;
		            $scope.part1=$scope.allParty.PetitionerList;
					 $scope.part2=$scope.allParty.RespondentList;
		            console.log("All Party:", $scope.allParty);
		        })
		        .catch(function (error) {
		            console.error("API Error:", error);
		            newTab.close();
		        });
			 
			
			 console.log("Party1:", $scope.part1);
			 console.log("Party2:", $scope.part2);
			 
			 
			 $http.get(urlBase+'application/getCheckListingAdv/'+Caseid).
  	        success(function (data) {
  	        	$scope.isListingAllow=data.response;
  	        	if(data.response=="TRUE"){
  	        		if(false){
  	        			$scope.firstPrint=$scope.part2;
  	  	        		$scope.secondPrint=$scope.part1;
  	        		}
  	        		else{
  	        			$scope.firstPrint=$scope.part1;
  	  	        		$scope.secondPrint=$scope.part2;
  	        		}
  	        		
  	        	}else{
  	        		//window.open(urlBase+"/ecourt/addApplication",'_self');
  	        		
  	        		alert("You are not registered advocate in this case");
  	        	}
  	        	
  	        }).
  	        error(function(data, status, headers, config) {
  	        	console.log("Error in getting tree data");
  	        });
		}


$scope.generatePDF = function() {
	//  $timeout(function () {

    var element = document.getElementById('pdfPrep');

    var textareas = element.querySelectorAll("textarea");
    var backup = [];

    angular.forEach(textareas, function (ta) {
        var div = document.createElement("div");
        div.innerText = ta.value || ta.placeholder;
        div.style.whiteSpace = "pre-wrap";
        div.style.fontFamily = "Arial,Times New Roman, serif";
        div.style.wordSpacing = "6pt";
        div.style.letterSpacing="1pt";
        div.style.fontSize = "14pt";
        div.style.lineHeight = "1.5";
        div.style.pageBreakInside = "avoid";
        div.style.display = "block";
        div.style.textAlign= "justify";
      //  div.style.pageBreakInside = "auto";

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

//  }, 300); // ⬅ wait for Angular DOM
};
$scope.generateODT1 = function()
{

	var req = new XMLHttpRequest();
	req.open('GET', 'res/empty.odt');
	req.responseType = 'arraybuffer';
	req.addEventListener('load', function() {
		var empty = req.response;
		
		var odtdoc = new ODTDocument(empty);
		try {
			odtdoc.setHTML(document.getElementById('pdfPrep'));
		} catch(e) {
			alert("Couldn't generate odt document.");
			throw e;
		}
		var odt = odtdoc.getODT();
	});
	req.send();
	
}

$scope.generateODT = function () {

    var element = document.getElementById("pdfPrep").cloneNode(true);

    /* -------- REMOVE ANGULAR COMMENT NODES -------- */
    var walker = document.createTreeWalker(
        element,
        NodeFilter.SHOW_COMMENT,
        null,
        false
    );

    var node;
    var comments = [];

    while (node = walker.nextNode()) {
        comments.push(node);
    }

    comments.forEach(function(comment) {
        comment.parentNode.removeChild(comment);
    });

    /* -------- REMOVE ANGULAR ATTRIBUTES -------- */
    var allElements = element.querySelectorAll("*");

    angular.forEach(allElements, function(el){

        [].slice.call(el.attributes).forEach(function(attr){

            if(attr.name.startsWith("ng-") ||
               attr.name.startsWith("data-ng-") ||
               attr.name.startsWith("x-ng-")){
                el.removeAttribute(attr.name);
            }

        });

        /* Remove Angular classes */
        el.classList.remove("ng-scope","ng-binding","ng-isolate-scope");

    });

	/* -------- CONVERT TEXTAREA TO TEXT -------- */
	var textareas = element.querySelectorAll("textarea");

	angular.forEach(textareas, function (ta) {

	    var div = document.createElement("div");

	    var text = ta.value || ta.placeholder;

	    div.innerText = text;

	    div.style.whiteSpace = "pre-wrap";
	    div.style.fontFamily = "Times New Roman, serif";
	    div.style.fontSize = "14pt";
	    div.style.lineHeight = "1.5";
	    div.style.textAlign = "left";
	    div.style.display = "block";

	    /* Align with party name text */
	    div.style.paddingLeft = "200px"; 
		div.style.marginRight="50px";
		  // adjust slightly if needed

	    ta.parentNode.replaceChild(div, ta);

	});

    /* -------- REMOVE CONTENTEDITABLE -------- */
    element.querySelectorAll("[contenteditable]").forEach(function(e){
        e.removeAttribute("contenteditable");
    });

    /* -------- CLEAN INVISIBLE CHARACTERS -------- */
    function cleanText(node){

        node.innerHTML = node.innerHTML
            .replace(/&nbsp;/g," ")
            .replace(/\u00A0/g," ")
            .replace(/\t/g," ")
            .replace(/\u200B/g,"")
            .replace(/\uFEFF/g,"");

    }

    cleanText(element);

    /* -------- CREATE HTML FOR ODT -------- */
    var html =
        "<html><head><meta charset='utf-8'>" +
        "<style>" +
        "body{font-family:'Times New Roman',serif;font-size:14pt;line-height:1.5;}" +
        "p{margin-bottom:10pt;margin-left:8pt;text-align:justify;}" +
        ".center{text-align:center;}" +
        ".right{text-align:right;}" +
        ".page{page-break-before:always;}" +
        ".no-break{page-break-inside:avoid;}" +
        "</style>" +
        "</head><body>" +
        element.innerHTML +
        "</body></html>";

    /* -------- DOWNLOAD FILE -------- */
    var blob = new Blob([html], {
        type: "application/vnd.oasis.opendocument.text"
    });

    var link = document.createElement("a");

    link.href = URL.createObjectURL(blob);
    link.download = "Listing_Application.odt";

    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);

};

$scope.searchCaseFile = function () {

    $http.get(urlBase + 'searchcasefile/searchCaseFile', {
        params: {
            'case_year': $scope.model.fd_case_year,
            'case_type': $scope.model.fd_case_type,
            'case_no': $scope.model.fd_case_no
        }
    })
    .success(function (data) {

        if (data.response == "TRUE") {
            $scope.caseFileList = data.modelList;
        } else {
            // window.open(urlBase+"/ecourt/addApplication",'_self');
            alert("This Case is not Efiled");
        }

    })
    .error(function (data, status, headers, config) {
        console.log("Error in getting tree data");
    });

};
	 
	 $scope.searchAlreadyCaseFile=function(){
		 $http.get(urlBase+'searchcasefile/searchCaseFile', {params : {'case_year' :$scope.model.fd_case_year,'case_type' :$scope.model.fd_case_type,'case_no' :$scope.model.fd_case_no}}).
	        success(function (data) {
	        	
	        	if(data.response=="TRUE"){
	        	$scope.caseFileList=data.modelList;
	        	$scope.fd_id=$scope.caseFileList[0].fd_id;
	        	console.log("petDoc",$scope.petDoc);
	        	console.log("appDoc",$scope.draftList);
	        	$scope.getPetDoc();
	        	$scope.getDocList();
	        	}else{
	        		//window.open(urlBase+"/ecourt/addApplication",'_self');
	        		alert("This Case is not Efiled");
	        		
	        	}
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
		 
	 }
	 
	 $scope.viewApplications=function(data){
			window.open(urlBase+"application/viewDocs?fd_id="+data.fd_id,'_blank');
		  
	}
	 
	  $scope.createApplication=function(data){
			window.open(urlBase+"application/add?fd_id="+data.fd_id,'_self');
		  
	}
	
}]);