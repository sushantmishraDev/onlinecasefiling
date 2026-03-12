<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="addApplicationController">
		<div class="row">
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
					<h4 class="panel-title">Documents Filed</h4>
				</div> 
				
				   <div class="panel-body">
                            <div class="table-responsive" ng-show="petDoc || draftList.length>0">
 						<input type="hidden" class="form-control" value=${fd_id} id="fd_id" name="fd_id">    
                                <table id="data-table" class="table table-striped table-bordered" ng-init="getPetDoc()" >
                                    <thead>
                                        <tr>
                                        	<th>Sr.<br>No</th>
 	                                        <!-- <th>Filed By</th> -->
                                            <th>Document Types</th>
                                            <th>Number</th>
                                            <th>Year</th>
                                            <th>Filed By</th>
                                            <th>Action</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-show="petDoc!=null">
                                    <td>1</td>
                                     <!-- <td>Petitioner</td> -->
                                     <td>{{petDoc.caseType.ct_name}} </td>
                                     <td>{{petDoc.rcd_case_no}}</td>
                                     <td>{{petDoc.rcd_case_year}}</td>
                                      <td>{{petDoc.userFiled.um_fullname}}({{petDoc.userFiled.username}})</td>
                                     <td><button  class="btn btn-success" ng-click="showDocument(petDoc)">Preview</button></td>
                                    </tr>
                                     </tbody>
                                </table>
                                <table id="data-table" class="table table-striped table-bordered" ng-init="getDocList()" >
                                        <tr   ng-repeat="row in draftList" class="odd gradeX">
                                        	<td>{{$index+2}}</td>
                                        	<!--  <td>{{row.ap_filed_by ==1 ?'Petitioner' : 'Respondent'}}</td> -->
                                             <td>{{row.applicationType.at_description}}</td> 
                                             <td>{{row.ap_no}}</td>
                                             <td>{{row.ap_year}}</td>
                                             <td>{{row.userFiled.um_fullname}} ({{row.userFiled.username}})</td>
                                             <td><button  class="btn btn-success" ng-click="showDocuments(row)">Preview</button></td>
                                          </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div ng-hide="petDoc || draftList.length>0">
                           <h2 style="color:red;text-align:center;"> You Are Not Authorized To View The Document </h2>
                            </div>
                        </div>
                       
		</div>
	</div>
</div>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/addApplicationController.js?v=1"></script>
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

