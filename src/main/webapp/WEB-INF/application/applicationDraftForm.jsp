<jsp:include page="../content/header2.jsp"></jsp:include>  
<div id="content" class="content">
  <div class="container-fluid" ng-controller="applicationDraftViewController" >
  
  
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>                                
                            </div>
                            <h4 class="panel-title">View Application Drafts</h4>
                        </div>
                        
                     
                           
                                <div align="center">
                                    <input type="radio" height="30px" width="30pax" id="civil" data-ng-click="changeCase(radioSelect)" checked name="caseType" ng-model="radioSelect" value="draft">
                                    <label><b>Appplication In-Process</b>&emsp;
                                        <input type="radio"  id="criminal" data-ng-click="changeCase(radioSelect)" 
														ng-model="radioSelect" value="passed" name="caseType" >
														<b>Passed Application</b>
										<input type="radio"  id="criminal" data-ng-click="changeCase(radioSelect)" 
														ng-model="radioSelect" value="defect" name="caseType" >
														<b>Defect Expired Cases</b>				
                                    </label>
                                </div>
                       
                        
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                        	<th style="width: 2%;">Sr.<br>No.</th>
                                        	<th>Draft No</th>
                                            <th>Diary No</th>
                                            <th>Registered Date</th>
                                            <th>Case Details</th>
                                            <th>Applicant Name</th>
                                            <th>Application Type</th>
                                            <th>Stage</th>                                           
                                            <th>Action</th>                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr  ng-repeat="row in applicationList"  class="odd gradeX">
                                        	 <td>{{$index+1}}</td>
                                             <td>{{row.ap_draft_no}}</td>
                                             <td>{{row.ap_diary_no}}</td>
                                             <td>{{row.ap_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
                                             <td>{{row.caseFileDetail.caseType.ct_label}}/{{row.caseFileDetail.fd_case_no}}/{{row.caseFileDetail.fd_case_year}}</td>
                                             <td>{{row.ap_applicant_name}}</td>                                        
                                              <td>1.&nbsp {{row.applicationType.at_name}}
                                              <table>
                                               <tr  ng-repeat="data in row.subApplication" >
                                              <td>{{$index+2}}. &nbsp {{data.applicationType.at_name}}</td>
                                              </tr></table>
                                              </td>                                             
                                            <td>{{row.caseStage.lk_longname}}</td>
                                            <td>                               
				                                 <button class="btn btn-success" ng-if="row.caseStage.lk_longname=='DRAFT' || row.caseStage.lk_id==1000041" ng-click="viewDetails(row.ap_id)">Edit</button>
				                                 <button class="btn btn-success" ng-click="previewDetails(row.ap_id)">Preview</button>
				                                 <button class="btn btn-success" ng-if="row.caseStage.lk_longname=='DRAFT' || row.caseStage.lk_id==1000041" ng-click="submitForm(row)">Submit</button>
				                                 <!-- <button class="btn btn-success" class="btn btn-primary btn-sm" data-toggle="modal" ng-click="getStages(row)" data-target="#stageHistory">History</button> -->
                                        	</td>
                                          </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal fade" id="stageHistory" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  		<div class="modal-dialog modal-lg">
							    	<div class="modal-content">
							      	<div class="modal-header">
							        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        	<h4 class="modal-title" id="myModalLabel"><strong>Stage History</strong></h4>
							      	</div>	     
						  			<%@ include file="../application/stage_history.jsp"%>
							    	</div>
						  		</div>
							</div>
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
           </div>
           </div>
            <!-- end row -->
    	</body>
   

	
	
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/applicationDraftViewController.js?v=1"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script> --%>
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