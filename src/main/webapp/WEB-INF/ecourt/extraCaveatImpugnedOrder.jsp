<div class="row">
<div class="col-md-6">

<div class="smooth container w-xxxl w-auto-xs" >
						<form  class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10">
								
									<div class="panel panel-default" style="margin-left: 140px;">
						            <div class="panel-heading font-bold">Extra Impunged Order Details</div>
										<div class="panel-body">
										
								<div ng-init="extraCaveatImpugnedOrder.ec_io_court_type=1">
									<label>Belongs To:&emsp; 
									<input type="radio"  id="lowerCourt"  data-ng-click="changeCourt($event)" ng-model="extraCaveatImpugnedOrder.ec_io_court_type"
										name="actType" value="1">Lower Court&emsp; 
										<input type="radio"  id="highCourt" data-ng-click="changeCourt($event)" name="actType"
										ng-model="extraCaveatImpugnedOrder.ec_io_court_type" value="2">High Court&emsp;
										<input type="radio"  id="authority" data-ng-click="changeCourt($event)" name="actType" ng-model="extraCaveatImpugnedOrder.ec_io_court_type"
										 value="4">Authority/Other Details
									</label>
								</div>
										
										
										
                                          <div class="form-group pull-in clearfix">
											<div class="col-sm-4" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==2">
													<label class="control-label">State </label> <select
									          class="form-control" id="s_id" name="s_id"
									         ng-model="extraCaveatImpugnedOrder.ec_io_s_id"
									           ng-options="state.s_id as state.s_name for state in stateList">
									              <option value="">Select State</option>
								              </select>

												</div>

                                                <div class="col-sm-4" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==1">
													<label class="control-label">District </label> <select
									           class="form-control" id="dt_id" name="dt_id"
									          ng-model="extraCaveatImpugnedOrder.ec_io_district"
									      ng-options="district.dt_id as district.dt_name for district in districtList"  ng-disabled="extraCaveatImpugnedOrder.ec_io_district">
									<option value="">Select District</option>
								</select>
												</div>
                                                     
                                                <div class="col-sm-4" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==1">
													<label class="control-label">Subordinate Court Name <span class="text-danger"> * </span></label> <select class="form-control" id="caseDetail"
														name="caseDetail" ng-model="extraCaveatImpugnedOrder.ec_io_lct_mid"
														ng-options="court.lct_id as court.lct_name for court in lowerCourtList | filter: filterLct ">
														<option value=""></option>
													</select>

												</div>
												<!-- <div class="col-sm-4" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==1">
													 <br>
													 <input type="radio" name="Mandamus/Quashing Order" id="mandamus" ng-model="extraCaveatImpugnedOrder.ec_io_mandamus_order"  value="M">Mandamus Order<br>
													 <input type="radio" name="Mandamus/Quashing Order" id="mandamus" ng-model="extraCaveatImpugnedOrder.ec_io_mandamus_order" value="Q"> Quashing Order
												</div> -->
											</div>
											
											
											     <div class="form-group pull-in clearfix" ng-show ="extraCaveatImpugnedOrder.ec_io_court_type==4">

                                              <div class="col-sm-3" >
													<label class="control-label">Order No. <span class="text-danger"> * </span>
													</label> <input   type="text" class="form-control"
														ng-model="caveatAuthority.ca_order_no">
												</div>
												
												
												<div class="col-sm-3">

													<label class="control-label">Order Year <span class="text-danger"> * </span>
													</label> <input numbers-only maxlength=4 type="text" class="form-control"
														ng-model="caveatAuthority.ca_order_year">
												</div>
												
												<div class="col-sm-4">
													<label class="control-label">Date of Order<span class="text-danger"> * </span> </label>
											<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="caveatAuthority.ca_order_date" required is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			          <span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
												</div>
											</div>
											<div class="form-group pull-in clearfix" ng-show ="extraCaveatImpugnedOrder.ec_io_court_type==4">
												
										<div class="col-sm-6">
													<label class="control-label">Details Of Authority
													</label> <input type="text" max-length=60 class="form-control"
														ng-model="caveatAuthority.ca_authority_name">
												</div>
												</div>
											
									<div class="form-group pull-in clearfix">
												
										<div class="col-sm-4" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==1">
											<label class="control-label">Case Type <span class="text-danger"> * </span> </label> <select
											class="form-control" id="ct_id" name="ct_id"
											ng-model="extraCaveatImpugnedOrder.ec_io_lc_case_type"
											 ng-options="caseType.ct_id as caseType.ct_name for caseType in lowerCourtCaseTypes">
											<option value="">Select Case Type</option>
										</select>

									</div>
									
								<div class="col-sm-4" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==2">
									<label>Case Type <span class="text-danger"> * </span>  </label>
									<select
										class="form-control" id="ct_id" name="ct_id"
										ng-model="extraCaveatImpugnedOrder.ec_io_hc_case_type"
										 ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypeList">
										<option value="">Select Case Type</option>
									</select>
								</div>
							
												<div class="col-sm-3" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==2 || extraCaveatImpugnedOrder.ec_io_court_type==1">
													<label class="control-label">Case No. <span class="text-danger"> * </span>
													</label> <input  type="numeric" class="form-control"
														ng-model="extraCaveatImpugnedOrder.ec_io_case_no">
												</div>
												
												
												<div class="col-sm-3" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==2 || extraCaveatImpugnedOrder.ec_io_court_type==1">

													<label class="control-label">Year <span class="text-danger"> * </span>
													</label> <input numbers-only  maxlength=4 type="text" class="form-control"
														ng-model="extraCaveatImpugnedOrder.ec_io_case_year">
												</div>
												</div>
                                          <div class="form-group pull-in clearfix" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==2 || extraCaveatImpugnedOrder.ec_io_court_type==1">
												
												
												 <div class="col-sm-3">
													<label class="control-label">Judge Name
													</label> <input type="text"  max-length=60 class="form-control"
														ng-model="extraCaveatImpugnedOrder.ec_io_judge_name">
												</div>
											<div class="col-sm-3">
													<label class="control-label">CNR No.
													</label> <input type="text" maxlength=16 class="form-control"
														ng-model="extraCaveatImpugnedOrder.ec_io_cnr_no">
												</div>

                                                
											    <div class="col-sm-4">
													<label class="control-label">Date of Decision<span class="text-danger"> * </span></label>
													<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="extraCaveatImpugnedOrder.ec_io_decision_date" is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			          <span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
												</div>

											</div>
											<div class="row pull-right">
                                             	<div class="col-sm-12" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==1">
						                 			 <button class="btn btn-success" ng-disabled="!extraCaveatImpugnedOrder.ec_io_lct_mid || !extraCaveatImpugnedOrder.ec_io_lc_case_type || !extraCaveatImpugnedOrder.ec_io_case_no || !extraCaveatImpugnedOrder.ec_io_case_year || !extraCaveatImpugnedOrder.ec_io_decision_date" ng-click="addExtraCaveatImpugnedOrder()">Save</button>
						                     	</div>
						                     	
						                     	<div class="col-sm-12" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==2">
						                 			 <button class="btn btn-success" ng-disabled="!extraCaveatImpugnedOrder.ec_io_hc_case_type || !extraCaveatImpugnedOrder.ec_io_case_no || !extraCaveatImpugnedOrder.ec_io_case_year || !extraCaveatImpugnedOrder.ec_io_decision_date" ng-click="addExtraCaveatImpugnedOrder()">Save</button>
						                     	</div>
						                     	
						                     	<div class="col-sm-12" ng-show="extraCaveatImpugnedOrder.ec_io_court_type==4">
						                 			 <button class="btn btn-success" ng-disabled="!caveatAuthority.ca_order_no || !caveatAuthority.ca_order_year || !caveatAuthority.ca_order_date || !caveatAuthority.ca_authority_name" ng-click="addCaveatAuthority()">Save</button>
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
                            <h4 class="panel-title">Extra Impunged Order Details</h4>
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
                                                                  <tr ng-repeat="row in extraImpugnedDataList" class="odd gradeX">
                                                                        <td>{{row.courtType.lct_name}}</td>
                                                                        <td>{{row.ec_io_cnr_no}}</td>
                                                                        <td><span ng-if="row.ec_io_hc_case_type!=null">{{row.hcCaseType.ct_name}}</span><span ng-if="row.ec_io_lc_case_type!=null">{{row.lcCaseType.ct_name}}</span></td>
                                                                        <td>{{row.ec_io_judge_name}}</td>   
                                                                        <td>{{row.ec_io_case_no}}</td>
                                                                        <td>{{row.ec_io_case_year}}</td>
                                                                        <td>{{row.ec_io_decision_date | date:"MM/dd/yyyy"}}</td> 
                                                                        <td class="text-center">
                                                                        	<a  ng-click="editExtraImp(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          	<a  ng-click="deleteExtraImpugnedOrder(row.ec_io_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
                                                                        </td>
                                                                  </tr>
                                                                  <tr ng-repeat="row in authorityList" class="odd gradeX">
                                                                        <td>Authority</td>
                                                                        <td></td>
                                                                        <td></td>
                                                                        <td>{{row.ca_authority_name}}</td>   
                                                                        <td>{{row.ca_order_no}}</td>
                                                                        <td>{{row.ca_order_year}}</td>
                                                                        <td>{{row.ca_order_date | date:"MM/dd/yyyy"}}</td> 
                                                                        <td class="text-center">
                                                                        	<a  ng-click="editExtraAuth(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          	<a  ng-click="deleteExtraImpugnedOrder(row.ec_io_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
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
            