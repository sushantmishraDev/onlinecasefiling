<jsp:include page="../content/header2.jsp"></jsp:include>

<div id="content" class="content" ng-controller="scrutinyViewController">
   <div class="row">
<div class="col-md-12">
			        <div class="panel-group" id="accordion">
			        <div class="panel panel-inverse overflow-hidden">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Case Details
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
                                            <th>First Petitioner</th>
                                            <th>First Respondent</th>
                                            <th>Case Type</th>
                                            <th>Case Year</th>
                                           
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="odd gradeX">
                                             <td>{{registerCase.rcd_draft_no}}</td>
                                             <td>{{registerCase.petitionerDetails.pt_name}}</td>
                                             <td>{{registerCase.respondentDetails.rt_name}}</td> 
                                              <td>{{registerCase.caseType.ct_name}}</td>
                                                  <td>{{registerCase.rcd_case_year}}</td>                             
                                    
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
									<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Petitioner Details
									</a>
								</h3>
							</div>
							   
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered" st-table="petitionerDataList">
                                    <thead>
                                        <tr>
                                        <th>Petitioner Name</th>
                                       <th>Email Id</th>
                                        <th>Address</th>
                                         <th>Mobile No.</th>
                                       </tr>
                                    </thead>
                                    <tbody>
                                        <tr  ng-repeat="row in petitionerDataList"  class="odd gradeX">
                                              <td>{{row.pt_name}}</td>
                                              <td>{{row.pt_email}}</td>
                                               <td>{{row.pt_address}}</td>   
                                               <td>{{row.pt_mobile}}</td>
                                          </tr>
                                    </tbody>
                                </table>
                            </div>
								</div>
							</div>
							<input type="hidden" class="form-control" value=${caseId } id="caseId" name="caseId">
						</div>
						
						<div class="panel panel-inverse overflow-hidden">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Respondent Details
									</a>
								</h3>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered" st-table="respondentDataList">
                                    <thead>
                                        <tr>
                                        <th>Respondent Name</th>
                                       <th>Email Id</th>
                                        <th>Address</th>
                                         <th>Mobile No.</th>
                                       </tr>
                                    </thead>
                                    <tbody>
                                        <tr  ng-repeat="row in respondentDataList"  class="odd gradeX">
                                              <td>{{row.rt_name}}</td>
                                              <td>{{row.rt_email}}</td>
                                               <td>{{row.rt_address}}</td>   
                                               <td>{{row.rt_mobile}}</td>
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
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Act Details
									</a>
								</h3>
							</div>
							<div id="collapseFour" class="panel-collapse collapse">
								 <div class="panel-body">
                                        <div class="table-responsive">
                                                        <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>

                                                                  <tr>
                                                                        <th>Act Belongs To</th>
                                                                           <th>Act (Title)</th>
                                                                           <th>Section</th>
                                                                           <th>Rule (Title)</th>
                                                                           <th>Rule Number</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in actDataList" class="odd gradeX">
                                                                                        <td>{{row.act_type}}</td>
                                                                                        <td>{{row.actMaster.act_name}}</td>
                                                                                        <td>{{row.act_section}}</td>   
                                                                                          <td>{{row.act_rule}}</td> 
                                                                                          <td>{{row.act_rule_no}}</td>
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
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
									    <i class="fa fa-plus-circle pull-right"></i> 
                                      Impugned Order Details
									</a>
								</h3>
							</div>
							<div id="collapseFive" class="panel-collapse collapse">
								<div class="panel-body">
									  <div class="table-responsive">
                                                      <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>Sub Court Name</th>
                                                                           <th>Judge Name</th>
                                                                           <th>Case No</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in impugnedDataList" class="odd gradeX">
                                                                      
                                                                                        <td>{{row.io_sub_courtname}}</td>
                                                                                        <td>{{row.io_judge_name}}</td>   
                                                                                          <td>{{row.io_case_no}}</td> 
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
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
									    <i class="fa fa-plus-circle pull-right"></i> 
									Trial Court Details
									</a>
								</h3>
							</div>
							<div id="collapseSix" class="panel-collapse collapse">
								<div class="panel-body">
									 <div class="table-responsive">
                                                      <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>Sub Court Name</th>
                                                                           <th>Judge Name</th>
                                                                           <th>Case No</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in trialDataList" class="odd gradeX">
                                                                      
                                                                                        <td>{{row.tr_sub_courtname}}</td>
                                                                                        <td>{{row.tr_judge_name}}</td>   
                                                                                          <td>{{row.tr_case_no}}</td> 
                                                                             
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
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven">
									    <i class="fa fa-plus-circle pull-right"></i> 
										E-Court Fee Details
									</a>
								</h3>
							</div>
							<div id="collapseSeven" class="panel-collapse collapse">
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
                                                                                        <td>{{row.cf_receipt_no}}</td>
                                                                                        <td>{{row.cf_amount}}</td>
                                                                                        <td>{{row.cf_date|date:"dd/MM/yyyy"}}</td>   
                                                                                          
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
							<div id="collapseEight" class="panel-collapse collapse">
								<div class="panel-body">
									    <div class="table-responsive">
                                                        <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>Document</th>
                                                                         <th>Name</th>
                                                                         <th>Pages</th>
                                                                         <th>view</th>
                                                                        
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in uploadedDocumentsList" class="odd gradeX">
                                                                                  <td>{{row.indexField.if_lable}}</td>
                                                                                  <td>{{row.pu_document_name}}</td>
                                                                                  <td>{{row.pu_no_of_pages}}</td>  
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
						
						<div class="panel panel-inverse overflow-hidden">
							<div class="panel-heading">
								<h3 class="panel-title">
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseNine">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Reject Remark
									</a>
								</h3>
							</div>
							<div id="collapseNine" class="panel-collapse collapse">
								<div class="panel-body">
									    <div class="table-responsive">
									    
                                                        <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>No.</th>
                                                                        <th>Rejection Reason</th>
                                                                        <th>Select All Remarks <input id="{{row.c_id}}" type="checkbox" value="{{row.c_id}}" name="c_id"  
									ng-click="checkAll()"  ng-model="selectedAll"/></th>
																		<th>Remark</th>  
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in checkList" class="odd gradeX">
                                                                     <td>{{$index+1}}</td>
                                                                     <td>{{row.name}}</td>
                                                                     <td>
                                                                        <input type="checkbox" name="remark_id" id="remark_id" value={{row.c_id}} ng-click="checkedIndex(row)"	ng-model="row.checkbox" />
                                                                     </td> 
                                                                     <td>
                                                                     <input type="text" class="form-control" placeholder="remark" id="remark" name="remark" ng-model="row.c_remark" required>
																	</td>  
                                                                                          
                                                                  </tr>
                                                            </tbody>
                                                      </table>
                                                      <input type="submit" value="Submit" id="Submit" ng-click="submit_file(checkList)" ng-disabled="buttonDisabled"
									data-loading-text="Loading..." class="btn btn-success"
									data-toggle="modal" />
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/scrutinyViewController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>

            
<script type="text/javascript"
	 src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>


</html>