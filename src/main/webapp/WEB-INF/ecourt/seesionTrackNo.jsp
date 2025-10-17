<div class="row">
<div class="col-md-6">

<div class="smooth container w-xxxl w-auto-xs" >
						<form  class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10">
								
									<div class="panel panel-default" style="margin-left: 140px;">
						            <div class="panel-heading font-bold">Session Trial Details</div>
										<div class="panel-body">
										
								<div ng-init="sessionTrack.snd_court_type=1">
									<label>Belongs To:&emsp; 
									<input type="radio"  id="lowerCourt"  data-ng-click="changeCourt($event)" ng-model="sessionTrack.snd_court_type"
										name="actType" value="1">Lower Court&emsp; 
										<!-- <input type="radio"  id="highCourt" data-ng-click="changeCourt($event)" name="actType"
										ng-model="sessionTrack.snd_court_type" value="2">High Court -->
									</label>
								</div>
										
										
										
                                          <div class="form-group pull-in clearfix">
											<div class="col-sm-4" ng-show="sessionTrack.snd_court_type==2">
													<label class="control-label">State </label> <select
									          class="form-control" id="s_id" name="s_id"
									         ng-model="sessionTrack.snd_s_id"
									           ng-options="state.s_id as state.s_name for state in stateList">
									              <option value="">Select State</option>
								              </select>

												</div>

                                                <div class="col-sm-4" ng-show="sessionTrack.snd_court_type==1">
													<label class="control-label">District </label> <select
									           class="form-control" id="dt_id" name="dt_id"
									          ng-model="sessionTrack.snd_district"
									      ng-options="district.dt_id as district.dt_name for district in districtList">
									<option value="">Select District</option>
								</select>
												</div>
												
												 <!-- <div class="col-sm-4" ng-show="impugnedOrder.io_court_type==1">
													<label class="control-label">Establisment </label> <select
									           class="form-control" id="dt_id" name="dt_id"
									          ng-model="sessionTrack.snd_establishment"
									      ng-options="establishment.est_id as establishment.est_name for establishment in establishmentList | filter: filterEstablishment1">
									<option value="">Select Establisment</option>
								</select>
												</div> -->
                                                     
                                                <div class="col-sm-4" ng-show="impugnedOrder.io_court_type==1">
													<label class="control-label">Subordinate Court Name</label> <select class="form-control" id="caseDetail"
														name="caseDetail" ng-model="sessionTrack.snd_lct_mid"
														ng-options="court.lct_id as court.lct_name for court in lowerCourtList | filter: filterLct1">
														<option value=""></option>
													</select>

												</div>
											</div>
											
									<div class="form-group pull-in clearfix">
												
										<div class="col-sm-4" ng-show="sessionTrack.snd_court_type==1">
											<label class="control-label">Case Type <span class="text-danger"> * </span> </label> <select
											class="form-control" id="ct_id" name="ct_id"
											ng-model="sessionTrack.snd_lc_case_type"
											 ng-options="caseType.ct_id as caseType.ct_name for caseType in lowerCourtCaseTypes">
											<option value="">Select Case Type</option>
										</select>

									</div>
									
								<div class="col-sm-4" ng-show="sessionTrack.snd_court_type==2">
									<label>Case Type <span class="text-danger"> * </span>  </label> <select
										class="form-control" id="ct_id" name="ct_id"
										ng-model="sessionTrack.snd_hc_case_type"
										 ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypeList">
										<option value="">Select Case Type</option>
									</select>
								</div>
							
												<div class="col-sm-3">
													<label class="control-label">Case No. <span class="text-danger"> * </span>
													</label> <input  type="text" class="form-control"
														ng-model="sessionTrack.snd_case_no">
												</div>
												
												
												<div class="col-sm-3">

													<label class="control-label">Year <span class="text-danger"> * </span>
													</label> <input numbers-only  maxlength=4 type="text" class="form-control"
														ng-model="sessionTrack.snd_case_year">
												</div>
												</div>
                                          <div class="form-group pull-in clearfix">
												
												
												 <div class="col-sm-3">
													<label class="control-label">Judge Name
													</label> <input type="text"  max-length=60 class="form-control"
														ng-model="sessionTrack.snd_judge_name">
												</div>
											<div class="col-sm-3">
													<label class="control-label">CNR No.
													</label> <input type="text" maxlength=16 class="form-control"
														ng-model="sessionTrack.snd_cnr_no">
												</div>

                                                
											    <div class="col-sm-4">
													<label class="control-label">Date of Decision:</label> <span class="text-danger"> * </span>
													<input type="text" class="form-control" datepicker-popup="{{format}}" name="fromDate" ng-model="sessionTrack.snd_decision_date" is-open="fromDate" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" required />
				            			          <span class="input-group-addon" ng-click="open($event,'fromDate')"><i class="glyphicon glyphicon-calendar"></i></span>
												</div>

											</div>
											<div class="row pull-right">
                                                      <div class="col-sm-12">
                                                           <!-- <div class="col-sm-6" style="float:right"> -->
						                  <button class="btn btn-success" ng-disabled="!sessionTrack.snd_case_year || !sessionTrack.snd_case_no || !sessionTrack.snd_decision_date" ng-click="addSessionTrack()">Save</button>
						                     </div>
                                                           
                                             </div>

										
                                                
										</div>
								</div>
								</div>
								</fieldset>
						</form>
					</div>
					</div>
					</div>
					
					
                <div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>                                
                              <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                            </div>
                            <h4 class="panel-title">Session Track Details</h4>
                        </div>
                        <div class="panel-body">
                             <div class="table-responsive">
                                                      <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>Sub Court Name</th>
                                                                        <th>CNR No.</th>
                                                                        <th>Case Type</th>
                                                                        <th>Judge Name</th>
                                                                        <th>Case No</th>
                                                                        <th>Case Year</th>
                                                                        <th>Date of Decision</th>
                                                                        <th class="text-center">Action</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in sessionTrackDataList" class="odd gradeX">
                                                                        <td><span ng-if="row.snd_hc_case_type!=null">High Court</span><span ng-if="row.snd_lc_case_type!=null">{{row.courtType.lct_name}}</span></td>
                                                                        <td>{{row.snd_cnr_no}}</td>
                                                                        <td><span ng-if="row.snd_hc_case_type!=null">{{row.hcCaseType.ct_name}}</span><span ng-if="row.snd_lc_case_type!=null">{{row.lcCaseType.ct_name}}</span></td>
                                                                        <td>{{row.snd_judge_name}}</td>   
                                                                        <td>{{row.snd_case_no}}</td>
                                                                        <td>{{row.snd_case_year}}</td>
                                                                        <td>{{row.snd_decision_date | date:"dd/MM/yyyy"}}</td> 
                                                                        <td class="text-center">
                                                                        	<a  ng-click="editSessionTrack(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          	<a  ng-click="deleteSessionTrack(row.snd_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
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
            