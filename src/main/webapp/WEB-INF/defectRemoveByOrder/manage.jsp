<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="DefectRemovalByOrderController">
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#nav-tab-1" data-toggle="tab" >New</a></li>
					<li class="">	   <a href="#nav-tab-2" data-toggle="tab" >History</a></li>
				</ul>
				<div class="tab-content">
				
					<div class="tab-pane fade active in" id="nav-tab-1">
 						<jsp:include page="../defectRemoveByOrder/defectRemoval.jsp"></jsp:include>
 						<input type="hidden" class="form-control" value=${user_id} id="user_id" name="user_id">  
					</div>
					
					<div class="tab-pane fade" id="nav-tab-2" >
						<jsp:include page="../defectRemoveByOrder/history.jsp"></jsp:include>
					</div>
					
						
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/defectRemovalByOrderController.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>	
	



</html>

