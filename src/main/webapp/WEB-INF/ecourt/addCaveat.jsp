<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="addCaveatlController">
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#nav-tab-1"  id ="1"data-toggle="tab" data-ng-click="ShowId($event)">Caveat Details</a></li>
					<li class=""><a href="#nav-tab-2" id="2" data-toggle="tab" data-ng-click="ShowId($event)">Petitioner</a></li>
					<li class=""><a href="#nav-tab-3" data-toggle="tab" id="3" data-ng-click="ShowId($event)">Respondents</a></li>
					<li class=""><a href="#nav-tab-13" data-toggle="tab" id="13" data-ng-click="ShowId($event)">Act Details</a></li>
					<li class=""><a href="#nav-tab-8" data-toggle="tab" id="8" data-ng-click="ShowId($event)">Extra caveator</a></li>
					<li class=""><a href="#nav-tab-9" id="9" data-toggle="tab" data-ng-click="ShowId($event)">Extra Impugned Order </a></li>
					<li class=""><a href="#nav-tab-10" id="10" data-toggle="tab" data-ng-click="ShowId($event)">Crime Details</a></li>
					<li class=""><a href="#nav-tab-11" id="12" data-toggle="tab" data-ng-click="ShowId($event)">ST Number Deatils</a></li>
					<li class="" ><a href="#nav-tab-11" id="11" data-toggle="tab" data-ng-click="ShowId($event)">Extra Advocate</a></li>					
					<li class=""><a href="#nav-tab-5" id="5" data-toggle="tab" data-ng-click="ShowId($event)">E-Court Fee </a></li>
					<!-- <li class="" ><a href="#nav-tab-12" id="12" data-toggle="tab" data-ng-click="ShowId($event)">Prepare Document</a></li> -->
					<li class=""><a href="#nav-tab-6" id="6" data-toggle="tab" data-ng-click="ShowId($event)">Upload Caveat </a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="nav-tab-1">
 						<jsp:include page="../ecourt/newCaveat.jsp"></jsp:include>
 						<input type="hidden" class="form-control" value=${draftId } id="draftId" name="draftId"> 
					</div>
					<div class="tab-pane fade active in" id="nav-tab-2" ng-show="tabShow2">
						<jsp:include page="../ecourt/caveatPetitioners.jsp"></jsp:include>
					</div>
           			<div class="tab-pane fade active in" id="nav-tab-3" ng-show="tabShow3">
                  		<jsp:include page="../ecourt/caveatRespondents.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-13" ng-show="tabShow13">
                  		<jsp:include page="../ecourt/caveatActDetails.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-8" ng-show="tabShow8">
                  		<jsp:include page="../ecourt/extraCaveator.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-9" ng-show="tabShow9">
						<jsp:include page="../ecourt/extraCaveatImpugnedOrder.jsp"></jsp:include>
					</div>
             		<div class="tab-pane fade active in" id="nav-tab-5" ng-show="tabShow6">
						<jsp:include page="../ecourt/caveatFees.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-6" ng-show="tabShow7">
						<jsp:include page="../ecourt/caveatUpload.jsp"></jsp:include>
					</div>	
					<div class="tab-pane fade active in" id="nav-tab-10" ng-show="tabShow10">
						<jsp:include page="../ecourt/caveatCrimeDetails.jsp"></jsp:include>
					</div>	
					<div class="tab-pane fade active in" id="nav-tab-11" ng-show="tabShow11">
						<jsp:include page="../ecourt/stNumber.jsp"></jsp:include>
					</div>	
					<div class="tab-pane fade active in" id="nav-tab-12" ng-show="tabShow12">
						<jsp:include page="../ecourt/documentPrepare.jsp"></jsp:include>
					</div>	
					
					
						
				</div>
			</div>
		</div>
	</div>
</div>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/addCaveatController.js?v=1"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2.full.min.js"></script>
	
	<link rel='stylesheet' href='${pageContext.request.contextPath}/css/select2.min.css'>
	
	

	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>	
	



</html>

