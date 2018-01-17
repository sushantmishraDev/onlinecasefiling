<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="addCaseDetailController">
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#nav-tab-1"  id ="1"data-toggle="tab" data-ng-click="ShowId($event)">New Case Details</a></li>
					<li class=""><a href="#nav-tab-2" id="2" data-toggle="tab" data-ng-click="ShowId($event)">Petitioner</a></li>
					<li class=""><a href="#nav-tab-3" data-toggle="tab" id="3" data-ng-click="ShowId($event)">Respondents</a></li>
					<li class=""><a href="#nav-tab-4"  id="4" data-toggle="tab" data-ng-click="ShowId($event)">Act Details</a></li>
					<li class=""><a href="#nav-tab-5" id="5" data-toggle="tab" data-ng-click="ShowId($event)">Impunged Order Details</a></li>
					<li class=""><a href="#nav-tab-8" id="8" data-toggle="tab" data-ng-click="ShowId($event)">Caveat Serach</a></li>
					<li class=""><a href="#nav-tab-6" id="6" data-toggle="tab" data-ng-click="ShowId($event)">E-Court Fee </a></li>
					<li class=""><a href="#nav-tab-9" id="9" data-toggle="tab" data-ng-click="ShowId($event)">LinkCase</a></li>
					<li class=""><a href="#nav-tab-7" id="7" data-toggle="tab" data-ng-click="ShowId($event)">Upload Document </a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="nav-tab-1">
 						<jsp:include page="../ecourt/newCase.jsp"></jsp:include>
 						<input type="hidden" class="form-control" value=${draftId } id="draftId" name="draftId"> 
					</div>
					<div class="tab-pane fade active in" id="nav-tab-2" ng-show="tabShow2">
						<jsp:include page="../ecourt/petitioners.jsp"></jsp:include>
					</div>
           			<div class="tab-pane fade active in" id="nav-tab-3" ng-show="tabShow3">
                  		<jsp:include page="../ecourt/respondent.jsp"></jsp:include>
					</div>
             		<div class="tab-pane fade active in" id="nav-tab-4" ng-show="tabShow4">
             			<jsp:include page="../ecourt/actDetails.jsp"></jsp:include>
					</div>
				 	<div class="tab-pane fade active in" id="nav-tab-5" ng-show="tabShow5">
						<jsp:include page="../ecourt/impugnedOrder.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-8" ng-show="tabShow8">
						<jsp:include page="../ecourt/caveatSearch.jsp"></jsp:include>
					</div>	
					<div class="tab-pane fade active in" id="nav-tab-6" ng-show="tabShow6">
						<jsp:include page="../ecourt/courtFee.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-9" ng-show="tabShow9">
						<jsp:include page="../ecourt/caseLink.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-7" ng-show="tabShow7">
						<jsp:include page="../ecourt/uploadDocument.jsp"></jsp:include>
					</div>	
					
					
						
				</div>
			</div>
		</div>
	</div>
</div>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/ecourtAddCaseDetailController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
	
	

	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>	
	



</html>

