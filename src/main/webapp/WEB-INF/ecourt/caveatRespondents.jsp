
<div class="smooth container w-xxxl w-auto-xs" >
						<form name="respondent" class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10" style="float:centre">
									<div class="panel panel-default" style="margin-left: 140px;">
										<div class="panel-heading font-bold">Respondent</div>
										<div class="panel-body">
											<div class="form-group pull-in clearfix">
												
												<div class="col-sm-4"></div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Name <span class="text-danger">
															* </span></label> <input type="text" maxlength=60 class="form-control" id="caseDetail" name="name" oninput="this.value = this.value.toUpperCase()"
														ng-model="respondentDetails.crt_name" required>
														<!-- <span class="text-danger" ng-show="respondent.name.$touched && respondent.name.$invalid">The name is required.</span> -->

												</div>

												<div class="col-sm-6">

													<label class="control-label">Address
													</label> <textarea class="form-control" rows="4" col="50" maxlength=100
														ng-model="respondentDetails.crt_address" name="address" placeholder="Address Line 1">
														</textarea>
														
												</div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Email Id</label> <input type="email" class="form-control" maxlength=50
														id="caseDetail" name="email" ng-model="respondentDetails.crt_email">
														<span style="color:red" ng-show="respondent.email.$dirty && respondent.email.$invalid">
                                                           <span ng-show="respondent.email.$error.email">Invalid email address.</span>
                                                                </span>
														</div>
                                                       
												<div class="col-sm-6">
													<label>City</label> <input type="text" class="form-control" id="caseDetail" maxlength=30
														name="caseDetail" ng-model="respondentDetails.crt_city">
												</div>
													

											</div>

											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Mobile No</label> <input numbers-only
														class="form-control" id="mobile" name="mobile"  maxlength=10   ng-model="respondentDetails.crt_mobile">
												
												</div>


                                                      <div class="col-sm-6">
													<label>State </label> <select
									              class="form-control" id="s_id" name="s_id"
									                ng-model="respondentDetails.crt_s_id"
									         ng-options="state.s_id as state.s_name for state in stateList">
									             <option value="">Select State</option>
								             </select>

												</div>

											</div>


											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Other Contact No's</label> 
														 <input	class="form-control" id="caseDetail"  maxlength=50 ng-model="respondentDetails.crt_other_contact" type="text"/>
												</div>
												
												<div class="col-sm-6">
													<label>Pincode</label> 
														 <input	 numbers-only class="form-control" id="pincode" maxlength=6 name="pincode" ng-model="respondentDetails.crt_pincode" type="text">
												</div>
												

											</div>
											
											 <div class="row pull-right">
                                                      <div class="col-sm-12">
						<button class="btn btn-success" ng-disabled="!respondentDetails.crt_name" ng-click="respondent.$valid && addRespondent(respondentDetails)">Add respondent</button>
						</div>
                                                           
                                                 </div>
                                                </div>
		

											
										</div>
									</div>
									</fieldset>
									</form>
								</div>
							
		                			
		
		
		
		
                  <div class="panel-body no-padder">
                        <div class="col-md-12 no-padder">
                              <div class="box-div no-padder">
                                    <div class=" col-md-12 pull-right">
                                          <div class="table-container">
                                                <div class="panel-body">
                                        <div class="table-responsive">
                                                          <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>Name</th>
                                                                           <th>Email Id</th>
                                                                           <th>Address</th>
                                                                           <th>Mobile No.</th>
                                                                        <th class="text-center">Action</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in respondentDataList" class="odd gradeX">
                                                                                        <td>{{row.crt_name}}</td>
                                                                                        <td>{{row.crt_email}}</td>
                                                                                        <td>{{row.crt_address}}</td>   
                                                                                          <td>{{row.crt_mobile}}</td> 
                                                                                          <td class="text-center">
                                                                       	  <a  ng-click="editResp(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                         <span ng-show=row.crt_sequence!=1>
                                                                     	   <a  ng-click="deleteRespondent(row.crt_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
                                                                         </span>
                                                                             
                                                                             
                                                                        </td>
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

					
					
					
					
					
