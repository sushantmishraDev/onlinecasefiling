
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
															* </span></label> <input type="text" class="form-control" id="caseDetail" name="name" max-length=60
														ng-model="respondentDetails.rt_name" required>
														<!-- <span class="text-danger" ng-show="respondent.name.$touched && respondent.name.$invalid">The name is required.</span> -->

												</div>

												<div class="col-sm-6">

													<label class="control-label">Address
													</label> <textarea class="form-control" rows="4" col="50" max-length=100
														ng-model="respondentDetails.rt_address" name="address" placeholder="Address Line 1">
														</textarea>
														
												</div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Email Id</label> <input type="email" class="form-control" max-length=50
														id="caseDetail" name="email" ng-model="respondentDetails.rt_email">
														<span style="color:red" ng-show="respondent.email.$dirty && respondent.email.$invalid">
                                                           <span ng-show="respondent.email.$error.email">Invalid email address.</span>
                                                                </span>
														</div>
                                                       
												<div class="col-sm-6">
													<label>City</label> <input type="text" class="form-control" max-length=30 id="caseDetail"
														name="caseDetail" ng-model="respondentDetails.rt_city">
												</div>
													

											</div>

											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Mobile No</label> <input numbers-only
														class="form-control" id="mobile" name="mobile"  maxlength=10  ng-model="respondentDetails.rt_mobile">
												<!-- <span style="color: red">	
							          <span class="error" ng-show="respondent.mobile.$error.maxlength"><br> mobile number not more than ten digits</span>
							          <span class="error"ng-show="respondent.mobile.$error.pattern"><br>Enter the valid Mobile Number</span>
                                          </span> -->
												
												</div>


                                                      <div class="col-sm-6">
													<label>State </label> <select
									              class="form-control" id="s_id" name="s_id"
									                ng-model="respondentDetails.rt_s_id"
									         ng-options="state.s_id as state.s_name for state in stateList">
									             <option value="">Select State</option>
								             </select>

												</div>

											</div>


											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Other Contact No's</label> 
														 <input	class="form-control" id="caseDetail"  max-length=50 ng-model="respondentDetails.rt_other_contact" type="text"/>
												</div>
												
												<div class="col-sm-6">
													<label>Pincode</label> 
														 <input	numbers-only class="form-control" id="pincode"  maxlength=6 name="pincode" ng-model="respondentDetails.rt_pincode" type="text">
														
														 <!--  <span style="color: red">	
							               <span class="error" ng-show="respondent.pincode.$error.maxlength"><br>Pincode not more than six digits</span>
							                 <span class="error"ng-show="respondent.pincode.$error.pattern"><br>Enter the valid pincode</span>
                                          </span> -->
														
												</div>
												

											</div>
											
											 <div class="row pull-right">
                                                      <div class="col-sm-12">
						<button class="btn btn-success" ng-disabled="!respondentDetails.rt_name" ng-click="respondent.$valid && addRespondent(respondentDetails)">Add Respondent</button>
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
                                                                                        <td>{{row.rt_name}}</td>
                                                                                        <td>{{row.rt_email}}</td>
                                                                                        <td>{{row.rt_address}}</td>   
                                                                                          <td>{{row.rt_mobile}}</td> 
                                                                                          <td class="text-center">
                                                                                    <a  ng-click="editResp(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          <a  ng-click="deleteRespondent(row.rt_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
                                                                            
                                                                             
                                                                             
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

					
					
					
					
					
