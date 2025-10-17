
<div class="smooth container w-xxxl w-auto-xs" >
						<form name="extraCaveator" class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10" style="float:centre">
									<div class="panel panel-default" style="margin-left: 140px;">
										<div class="panel-heading font-bold">Extra Caveator</div>
										<div class="panel-body">
											<div class="form-group pull-in clearfix">
												
												<div class="col-sm-4"></div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Name <span class="text-danger">
															* </span></label> <input type="text" maxlength=60 class="form-control" id="caseDetail" name="name"
														ng-model="extraCaveatorDetails.ect_name" required>

												</div>

												<div class="col-sm-6">

													<label class="control-label">Address
													</label> <textarea class="form-control" rows="4" col="50" maxlength=100
														ng-model="extraCaveatorDetails.ect_address" name="address" placeholder="Address Line 1">
														</textarea>
												</div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Email Id</label><input type="email" class="form-control" maxlength=50
														id="caseDetail" name="email" ng-model="extraCaveatorDetails.ect_email">
														<span style="color:red" ng-show="extraCaveator.email.$dirty && petitioner.email.$invalid">
                                                           <span ng-show="extraCaveator.email.$error.email && extraCaveator.email.$touched">Invalid email address.</span>
                                                                </span>
														</div>
                                                 
												<div class="col-sm-6">
													<label>City</label> <input  type="text" class="form-control" id="caseDetail" maxlength=30
														name="caseDetail" ng-model="extraCaveatorDetails.ect_city">
												</div>
											</div>

											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Mobile No</label><input numbers-only type="text"
														class="form-control" id="mobile" name="mobile" maxlength=10  ng-model="extraCaveatorDetails.ect_mobile"/>
							             <span style="color: red">	
							         <!--  <span class="error" ng-show="petitioner.mobile.$error.maxlength"><br> mobile number not more than ten digits</span> -->
							          <span class="error"ng-show="extraCaveator.mobile.$error.pattern"><br>Enter the valid Mobile Number</span>
                                          </span>
                                              </div>

                                         	<div class="col-sm-6">
													<label>State</label>  <select
									         class="form-control" id="s_id" name="s_id"
									       ng-model="extraCaveatorDetails.ect_s_id"
									        ng-options="state.s_id as state.s_name for state in stateList">
									            <option value="">Select State</option>
								         </select>
                                           </div>
												
											</div>


											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Other Contact No's</label> 
														 <input	class="form-control"  max-length=50 id="caseDetail" ng-model="extraCaveatorDetails.ect_other_contact" type="text"/>
												</div>
												
												<div class="col-sm-6">
													<label>Pincode</label> 
														 <input	 numbers-only class="form-control"  name="pincode"  ng-pattern="/^[0-9 \\\-,]*$/" maxlength=6 id="pincode" ng-model="extraCaveatorDetails.ect_pincode" type="text">
														  <span style="color: red">	
							              <!--  <span class="error" ng-show="petitioner.pincode.$error.maxlength"><br>Pincode not more than six digits</span> -->
							                 <span class="error"ng-show="extraCaveator.pincode.$error.pattern"><br>Enter the valid Mobile Number</span>
                                          </span>
														
														
														
												</div>
												

											</div>
											
											 <div class="row pull-right">
                                                      <div class="col-sm-12">
						     <button class="btn btn-success" ng-disabled="!extraCaveatorDetails.ect_name" ng-click="extraCaveator.$valid && addExtraCaveator(extraCaveatorDetails)">Add Extra Caveator</button>
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
                                                                  <tr ng-repeat="row in extraCaveatorDataList" class="odd gradeX">
                                                                                        <td>{{row.ect_name}}</td>
                                                                                        <td>{{row.ect_email}}</td>
                                                                                        <td>{{row.ect_address}}</td>   
                                                                                          <td>{{row.ect_mobile}}</td> 
                                                                                          <td class="text-center">
                                                                          <a  ng-click="editEct(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          <span ng-show=row.ect_sequence!=1>
                                                                         	 <a  ng-click="deleteExtraCvaeator(row.ect_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
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

					
					
					
					
