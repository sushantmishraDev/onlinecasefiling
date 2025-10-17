<jsp:include page="../content/header2.jsp"></jsp:include>

<div id="content" class="content" ng-controller="applicationViewController">
   <div class="row">
<div class="col-md-12">
			        <div class="panel-group" id="accordion">
			        <div class="panel panel-inverse overflow-hidden">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Application Details
									</a>
								</h3>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Draft No</th>
                                            <th>Diary No</th>
                                            <th>Application Type</th>
                                            <th>App No./Year</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="odd gradeX">
                                             <td>{{application.ap_draft_no}}</td>
                                             <td>{{application.ap_diary_no}}</td>
                                            <td>{{application.applicationType.at_name}}</td>
                                             <td>{{application.ap_no}}/{{application.ap_year}}</td>
                                          </tr>
                                    </tbody>
                                </table>
                            </div>
								</div>
							</div>
						</div>
		       		<div class="panel panel-inverse overflow-hidden">
					<input type="hidden" class="form-control" value=${caseId } id="caseId" name="caseId">
						<div class="panel-heading">
							<h3 class="panel-title">
								<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven">
								    <i class="fa fa-plus-circle pull-right"></i> 
									E-Court Fee Details
								</a>
							</h3>
						</div>
					<div id="collapseSeven" class="panel-collapse collapse in">
						<div class="panel-body">
							<div class="table-responsive">
	                             <table id="data-table" class="table table-striped table-bordered">
	                                 <thead>
	                                       <tr>
	                                             <th>Receipt No</th>
	                                                <th>Amount</th>
	                                                <th>Date</th>
	                                             
	                                       </tr>
	                                 	</thead>
	                                 	<tbody>
	                                       <tr ng-repeat="row in courtFeeList" class="odd gradeX">
	                                                             <td>{{row.acf_receipt_no}}</td>
	                                                             <td>{{row.acf_amount}}</td>
	                                                             <td>{{row.acf_date|date:"dd/MM/yyyy"}}</td>                   
	                                       </tr>
	                                 	</tbody>
	                           		</table>
                               </div>
							</div>
						</div>
					</div>
					<div class="panel panel-inverse overflow-hidden">
						<div class="panel-heading">
							<h3 class="panel-title">
								<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEight">
								    <i class="fa fa-plus-circle pull-right"></i> 
									Uploaded Documents
								</a>
							</h3>
						</div>
						<div id="collapseEight" class="panel-collapse collapse in">
							<div class="panel-body">
							    <div class="table-responsive">
	                                <table id="data-table" class="table table-striped table-bordered">
	                                    <thead>
	                                          <tr>
	                                                 <th>Name</th>
	                                                 <th>Pages</th>
	                                                 <th>view</th>
	                                                
	                                          </tr>
	                                    </thead>
	                                    <tbody>
	                                          <tr ng-repeat="row in uploadedDocumentsList" class="odd gradeX">
	                                                          <td>{{row.au_document_name}}</td>
	                                                          <td>{{row.au_no_of_pages}}</td>  
	                                                          <td>
	                                                          <button class="btn btn-success" ng-click="showDocument(row)">View</button>
	                                                          </td> 
	                                                                  
	                                          	</tr>
	                                    	</tbody>
	                              		</table>
	                            	</div>
								</div>
							</div>
						</div>
						
						<div class="panel panel-inverse overflow-hidden"  ng-show="application.caseStage.lk_id = 1000041">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseNine">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Reject Remark
									</a>
								</h3>
							</div>
							<div id="collapseNine" class="panel-collapse collapse in">
								<div class="panel-body">
									    <div class="table-responsive">
									                  <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>No.</th>
                                                                        <th>Name</th>
                                                                           <th>Remark</th>
                                                                           
                                                                        
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in checkList" class="odd gradeX">
                                                                     <td>{{$index+1}}</td>
                                                                     <td>{{row.checklist.name}}</td>
                                                                     <td>{{row.cm_remark}}</td>                  
                                                                  </tr>
                                                            </tbody>
                                                      </table>
                                                      
                                                </div>
								</div>
							</div>
						</div>
						
					</div>
					
					
					
			    </div>
			    </div>
			    </div>
 </body>
  <link rel='stylesheet'
	href='${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css'>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/applicationPreviewController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
            
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>


</html>