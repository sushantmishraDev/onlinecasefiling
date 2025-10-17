<jsp:include page="../content/header2.jsp"></jsp:include>  
<div id="content" class="content">
  <div class="container-fluid" ng-controller="draftViewController" >
  
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>                                
                            </div>
                            <h4 class="panel-title">View Draft Details</h4>
                        </div>
					<div style="position: absolute;">
							<font size="2" face="verdana" color="red"> Note : Please click on submit button for generation of Diary No.</font><br/>
							<font size="2" face="verdana" color="purple"> Remark : After generation of Diary No.,further reporting process should be started at High Court.</font>
					</div><br/><br/><br/>

					<div align="center">
                                    <input type="radio" height="30px" width="30pax" id="civil" data-ng-click="changeCase(radioSelect)" checked name="caseType" ng-model="radioSelect" value="draft">
                                    <label><b>Case In-Process</b>&emsp;
                                        <input type="radio"  id="criminal" data-ng-click="changeCase(radioSelect)" 
														ng-model="radioSelect" value="passed" name="caseType" >
														<b>Passed Cases</b>
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
                                            <th>First Petitioner</th>
                                            <th>First Respondent</th>
                                            <th>Case Type</th>
                                            <th>Case Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="row in draftList"  class="odd gradeX">
                                        	 <td>{{$index+1}}</td>
                                             <td>{{row.rcd_draft_no}}</td>
                                             <td>{{row.rcd_diary_no}}</td>
                                             <td>{{row.rcd_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
                                             <td>{{row.petitionerDetails.pt_name}}</td>
                                             <td>{{row.respondentDetails.rt_name}}</td>                                          
                                             <td>{{row.caseType.ct_name}}</td>
                                              <td ng-hide ="row.caseStage.lk_longname == 'RETURN_TO_COUNCIL'">{{row.caseStage.lk_longname}}</td>
                                             <td class="col-md-2" ng-show ="row.caseStage.lk_longname == 'RETURN_TO_COUNCIL'">{{row.scrutionRemark.sr_remrk}}</td>
                                             <td>                               
				                                 <button class="btn btn-success" ng-if="row.caseStage.lk_longname=='DRAFT' || row.caseStage.lk_id==1000041 " ng-click="viewDetails(row.rcd_id)">Edit</button>
				                                 <button class="btn btn-success" ng-click="previewDetails(row.rcd_id)">Preview</button>
				                                 <button class="btn btn-success" ng-if="row.caseStage.lk_longname=='DRAFT'|| row.caseStage.lk_id==1000041" ng-click="submitForm(row)">Submit</button>
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/draftViewController.js?v=3"></script>
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