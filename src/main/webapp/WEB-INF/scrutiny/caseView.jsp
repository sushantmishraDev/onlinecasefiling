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
                                            <th>Diary No</th>
                                            <th>First Petitioner</th>
                                            <th>First Respondent</th>
                                            <th>Case Type</th>
                                         </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="odd gradeX">
                                            <td>{{registerCase.rcd_diary_no}}</td>
                                            <td>{{registerCase.petitionerDetails.pt_name}}</td>
                                            <td>{{registerCase.respondentDetails.rt_name}}</td>
                                            <td>{{registerCase.caseType.ct_name}}</td>
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

                    <div id="collapseOne" class="panel-collapse collapse in">
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
                                        <tr ng-repeat="row in petitionerDataList" class="odd gradeX">
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
                    <div id="collapseTwo" class="panel-collapse collapse in">
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
                                        <tr ng-repeat="row in respondentDataList" class="odd gradeX">
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
                    <div id="collapseFour" class="panel-collapse collapse in">
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
                    <div id="collapseFive" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                                                 <th>Court Details<br/>
                                                                        Court Type<br/>
                                                                        District<br/>
                                                                        Sub Court Name</th>
                                                                        <th>CNR No.</th>
                                                                        <th>Case Type</th>
                                                                        <th>Judge Name</th>
                                                                        <th>Case No</th>
                                                                        <th>Case Year</th>
                                                                        <th>Date of Decision</th>                          
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="row in impugnedDataList" class="odd gradeX">
                                                            <td><span ng-if="row.io_court_type==1">Lower Court</span><span ng-if="row.io_court_type==2">High Court</span>
                                                                         <br/>{{row.district.dt_name}}
                                                                        <br/>{{row.courtType.lct_name}}
                                                                        </td>
                                                                        <td>{{row.io_cnr_no}}</td>
                                                                        <td><span ng-if="row.io_hc_case_type!=null">{{row.hcCaseType.ct_name}}</span><span ng-if="row.io_lc_case_type!=null">{{row.lcCaseType.ct_name}}</span></td>
                                                                        <td>{{row.io_judge_name}}</td>   
                                                                        <td>{{row.io_case_no}}</td>
                                                                        <td>{{row.io_case_year}}</td>
                                                                        <td>{{row.io_decision_date | date:"dd/MM/yyyy"}}</td>
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
                                            <!-- <td>{{row.indexField.if_lable}}</td> -->
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
                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTength">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Caveat Search
							</a>
                        </h3>
                    </div>
                    <div id="collapseTength" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="row pull-right">
                               <div class="col-sm-12">
     								<button class="btn btn-success" ng-click="searchCaveat()">Search</button>
      							</div>
                            </div>
              				<div class="table-responsive">
                                   <table id="data-table" class="table table-striped table-bordered">
                                       <thead>
                                             <tr>
                                                                         <th>Caveat No</th>
                                                                        <th>Caveator Name<br/>
                                                                         Email<br/>
                                                                        Mobile</th>
                                                                        <th>Case Type</th>
                                                                        <th>Case Number</th>
                                                                        <th>Case Year</th>
                                                                        <th>Order Passed By</th>
                                                                        <!-- <th>District</th> -->
                                                                        <th>Judgement Date</th>
                                                                        
                                                                  </tr>
                                       </thead>
                                       <tbody>
                                             <tr ng-repeat="row in caveatDataList" class="odd gradeX">
                                                                   <td>{{row.cav_no}}</td>
                                                                       <td>{{row.cav_caveator_name}}<br/>
                                                                         {{row.cav_email}}<br/>
                                                                        {{row.cav_mobile}}</td>                                                                  
                                                                   <td><span ng-if="row.cav_hc_case_type!=null">{{row.hcCaseType.ct_name}}</span><span ng-if="row.cav_lc_case_type">{{row.lcCaseType.ct_name}}</span></td>
                                                                   <td>{{row.cav_lc_case_no}}</td>
                                                                   <td>{{row.cav_lc_case_year}}</td>
                                                                   <td>{{row.cav_ord_psd_by}}</td>
                                                                   <!-- <td>{{row.district.dt_name}}</td> -->
                                                                   <td>{{row.cav_judgmnt_date | date:"dd/MM/yyyy"}}</td>
                                             </tr>
                                             <tr ng-repeat="row in oldcaveatDataList" class="odd gradeX">
                                                                   <td>{{row.cav_no}}</td>
                                                                       <td>{{row.cav_caveator_name}}<br/>
                                                                         {{row.cav_email}}<br/>
                                                                        {{row.cav_mobile}}</td> 
                                                                   <td>{{row.cav_lc_case_type}}</td>
                                                                   <td>{{row.cav_lc_case_no}}</td>
                                                                   <td>{{row.cav_lc_case_year}}</td>
                                                                   <td>{{row.cav_ord_psd_by}}</td>
                                                                  <!--  <td>{{row.district.dt_name}}</td> -->
                                                                   <td>{{row.cav_judgmnt_date | date:"dd/MM/yyyy"}}</td>
                                                                      
                                                                     
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
									<a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseEleven">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Defects History
									</a>
								</h3>
							</div>
							<div id="collapseEleven" class="panel-collapse collapse in">
								<div class="panel-body">
									    <div class="table-responsive">
                                                        <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>Defect</th>
                                                                         <th>Remark</th>
                                                                         <th>Date</th>                                                                        
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in checkListHistory" class="odd gradeX">
                                                                                        <td>{{row.checklist.name}}</td>
                                                                                        <td>{{row.cm_remark}}</td>  
                                                                                        <td>{{row.cm_cr_date | date:"dd/MM/yyyy"}}</td>
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
                                            <th>Select All Remarks
                                                <input id="{{row.c_id}}" type="checkbox" value="{{row.c_id}}" name="c_id" ng-click="checkAll()" ng-model="selectedAll" />
                                            </th>
                                            <th>Remark</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="row in checkList  | orderBy : 'name'" class="odd gradeX">
                                            <td>{{$index+1}}</td>
                                            <td>{{row.name}}</td>
                                            <td>
                                                <input type="checkbox" name="remark_id" id="remark_id" value={{row.c_id}} ng-click="checkedIndex(row)" ng-model="row.checkbox" />
                                            </td>
                                            <td>
<!--                                                 <input type="text" class="form-control" placeholder="remark" id="remark" name="remark" ng-model="row.c_remark" required> -->
													<textarea class="form-control" ng-model="row.c_remark" rows="2"></textarea>
                                            </td>

                                        </tr>
                                    </tbody>
                                </table>
                                
                                    <div class="col-md-12">
                                        <div class="panel panel-default">

                                            <div class="panel-body">

                                                <div>
                                                    <label>Application:&emsp;
                                                        <input type="radio" ng-model="stampReporterData.srd_application" name="actType" value="Y">Yes&emsp;
                                                        <input type="radio" ng-model="stampReporterData.srd_application" value="N">No
                                                    </label>
                                                </div>

                                                <div class="form-group pull-in clearfix">
                                                    <div class="col-sm-4">
                                                        <label class="control-label">Application Type 1</label>
                                                        <select class="form-control" ng-model="stampReporterData.srd_app_type1" ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList | orderBy:'at_name'">
                                                            <option value="">Select Application Type</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="control-label">Application Type 2</label>
                                                        <select class="form-control" ng-model="stampReporterData.srd_app_type2" ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList | orderBy:'at_name'">
                                                            <option value="">Select Application Type</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="control-label">Application Type 3</label>
                                                        <select class="form-control" ng-model="stampReporterData.srd_app_type3" ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList  | orderBy:'at_name'">
                                                            <option value="">Select Application Type</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group pull-in clearfix">
													<div class="col-sm-4">
                                                        <label class="control-label">Application Type 4</label>
                                                        <select class="form-control" ng-model="stampReporterData.srd_app_type4" ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList  | orderBy:'at_name'">
                                                            <option value="">Select Application Type</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="control-label">Application Type 5</label>
                                                        <select class="form-control" ng-model="stampReporterData.srd_app_type5" ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList  | orderBy:'at_name'">
                                                            <option value="">Select Application Type</option>
                                                        </select>
                                                    </div>
													<div class="col-sm-4">
                                                        <label class="control-label">Category</label>
                                                        <input type="text" class="form-control" ng-model="stampReporterData.srd_category">
                                                    </div>
                                                    
                                                </div>
                                                <div class="form-group pull-in clearfix">
													<div class="col-sm-4">
                                                        <label class="control-label">In Time Upto<span class="text-danger"> * </span></label>
                                                        <input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="stampReporterData.srd_intime" required is-open="fromDate1"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
                                                        <span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="control-label">Remark</label>
                                                        <input type="text" class="form-control" ng-model="stampReporterData.srd_intime_remark">
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label>Cognizable:&emsp;
                                                        <input type="radio" ng-model="stampReporterData.srd_cognizable" value="SB">SB&emsp;
                                                        <input type="radio" ng-model="stampReporterData.srd_cognizable" value="DB">DB
                                                    	</label>                                                    
                                                    </div>
													                                                    
                                                </div>
                                                <div class="form-group pull-in clearfix">
													<div class="col-sm-4">
                                                        <label class="control-label">Caveat No.</label>
                                                        <input type="text" class="form-control" ng-model="stampReporterData.srd_cav_no">
                                                    </div>
													<div class="col-sm-4">
                                                        <label class="control-label">Caveat Year</label>
                                                        <input type="text" class="form-control" ng-model="stampReporterData.srd_cav_year">
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label>Cause of Action:&emsp;
                                                        <input type="radio" ng-model="stampReporterData.srd_bench_code" value=8>Allahabad&emsp;
                                                        <input type="radio" ng-model="stampReporterData.srd_bench_code" value=9>Lucknow
                                                    	</label>                                                    
                                                   </div>
												</div>
                                                 <div class="form-group pull-in clearfix">
                                                 	<div class="col-sm-4">
                                                        <label class="control-label">Remarks</label>
                                                        <textarea class="form-control" ng-model="stampReporterData.srd_remark"></textarea>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <label class="control-label">Defective</label>
                                                        <input type="checkbox" value="Y" ng-model="stampReporterData.srd_defective"/>
                                                    </div>
                                                    <div class="col-sm-4">
                                						<input type="submit" value="Submit" id="Submit" ng-click="submit_file(checkList)" ng-disabled="buttonDisabled" data-loading-text="Loading..." class="btn btn-success" data-toggle="modal" />                    
                                                    </div>
 
                                                 </div>                                              
                                            </div>
                                        </div>
                                    </div>
                                
                                
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
</div>

</body>
<link rel='stylesheet' href='${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css'>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/scrutinyViewController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
    $(document).ready(function() {
        App.init();

    });
</script>

</html>