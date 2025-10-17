<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="searchCaseFileController">
				<div class="row">
			
				
				<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<!-- <button type="button" class="btn btn-primary btn-sm pull-right"
							ng-click="resetMasterdata()" data-toggle="modal"
							data-target="#casefile_Modal">
							<span class="glyphicon glyphicon-plus-sign"></span> Add New
							Record
						</button> -->
					</div>
					<h4 class="panel-title">Urgency Code Form</h4>
				</div> 
				<div class="panel-body">
					<div class="table-responsive">	
						<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover" width="100%">
								<thead>					
										
										
									
										<th>
										 <input  numbers-only id="fd_case_no"  type="text" name="fd_case_no"  class="form-control" ng-pattern="/^[a-zA-Z0-9]*$/"  ng-model="model.ae_code" placeholder="Enter Urgency Code" />
										</th>
										
										<th>
										 <input  type="text" name="fd_app_no"  class="form-control" ng-pattern="/^[a-zA-Z0-9]*$/"  ng-model="model.ae_appno" placeholder="Enter Application No" />
										</th>
										
														  
 										<th>
										
										<button id="search" type="submit" class="btn btn-primary btn-sm pull-center"
												ng-click="validateCode(model)" data-toggle="modal" style="size: 2px">
										<span class="glyphicon glyphicon-plus-sign"></span>Search</button>
										</th>
									</tr>
				 
								</thead>
								</table>	
					
						 </div>
					
				</div>
					
				</div>
					
				<!-- end panel -->
			
			<!-- end col-10 -->
						
				</div>
	</div>
</div>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/searchCaseFile.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
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

