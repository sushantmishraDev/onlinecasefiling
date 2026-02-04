<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="addApplicationController">
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#nav-tab-1" id="1" data-toggle="tab" data-ng-click="ShowId($event)">Application Details</a></li>
					<li class="">	   <a href="#nav-tab-2" id="2" data-toggle="tab" data-ng-click="ShowId($event)">E-Court Fee </a></li>
					<li class="">	   <a href="#nav-tab-3" id="3" data-toggle="tab" data-ng-click="ShowId($event)">Upload Application </a></li>
					<!-- <li class="">	   <a href="#nav-tab-4" id="4" data-toggle="tab" data-ng-click="ShowId($event)">Listing Application </a></li> --> 
				</ul>
				<div class="tab-content">
				
					<div class="tab-pane fade active in" id="nav-tab-1">
 						<jsp:include page="../application/newApplication.jsp"></jsp:include>
 						<input type="hidden" class="form-control" value=${draftId} id="draftId" name="draftId">
 						<input type="hidden" class="form-control" value=${fd_id} id="fd_id" name="fd_id">  
 						<input type="hidden" class="form-control"value=${code} id="code" name="code">
 						<input type="hidden" class="form-control"value=${appno} id="appno" name="appno">    
					</div>
					
					<div class="tab-pane fade active in" id="nav-tab-2" ng-show="tabShow2">
						<jsp:include page="../application/applicationFees.jsp"></jsp:include>
					</div>
					
					<div class="tab-pane fade active in" id="nav-tab-3" ng-show="tabShow3">
						<jsp:include page="../application/applicationUpload.jsp"></jsp:include>
					</div>	
					
					<div class="tab-pane fade active in" id="nav-tab-4" ng-show="tabShow4">
						<jsp:include page="../application/listingApplication.jsp"></jsp:include>
					</div>	
					
						
				</div>
			</div>
		</div>
	</div>
</div>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/addApplicationController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>

<script>
	$(document).ready(function() {
		$("#txtEditor").Editor();
		$("#txtEditor1").Editor();
		App.init();

	});
</script>
	
	

	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<!-- <script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>	 -->
	



</html>

