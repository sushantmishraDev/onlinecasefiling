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
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                        	<th>Draft No</th>
                                            <th>Diary No</th>
                                            <th>Case Type/Case No/Year</th>
                                            <th>Applicant Name</th>
                                            <th>Application Type</th>
                                            <th>Stage</th>                                           
                                            <th>Action</th>                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr  ng-repeat="row in applicationList"  class="odd gradeX">
                                             <td>{{row.ap_draft_no}}</td>
                                             <td>{{row.ap_diary_no}}</td>
                                             <td>{{row.caseFileDetail.caseType.ct_label}}/{{row.caseFileDetail.fd_case_no}}/{{row.caseFileDetail.fd_case_year}}</td>
                                             <td>{{row.ap_applicant_name}}</td>                                        
                                              <td>{{row.applicationType.at_name}}</td>
                                            <td>{{row.caseStage.lk_longname}}</td>
                                            <td>                               
				                                 <button class="btn btn-success" ng-if="row.caseStage.lk_longname=='DRAFT' || row.caseStage.lk_longname=='SUPERVISIOR_DEFECTS' " ng-click="viewDetails(row.ap_id)">Edit</button>
				                                 <button class="btn btn-success" ng-click="previewDetails(row.ap_id)">Preview</button>
				                                 <button class="btn btn-success" ng-if="row.caseStage.lk_longname=='DRAFT' || row.caseStage.lk_longname=='SUPERVISIOR_DEFECTS' " ng-click="submitForm(row)">Submit</button>
                                        	</td>
                                          </tr>
                                    </tbody>
                                </table>
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
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/applicationDraftViewController.js"></script>
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