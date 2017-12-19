
<div class="smooth container w-xxxl w-auto-xs" >
						<form name="petitioner" class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10" style="float:centre">
									<div class="panel panel-default" style="margin-left: 140px;">
										<div class="panel-heading font-bold">Petitioner</div>
										<div class="panel-body">
											<div class="form-group pull-in clearfix">
												
												<div class="col-sm-4"></div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Name <span class="text-danger">
															* </span></label> <input type="text" maxlength=60 class="form-control" id="caseDetail" name="name"
														ng-model="petitionerDetails.cpt_name" required>

												</div>

												<div class="col-sm-6">

													<label class="control-label">Address
													</label> <textarea class="form-control" rows="4" col="50" maxlength=100
														ng-model="petitionerDetails.cpt_address" name="address" placeholder="Address Line 1">
														</textarea>
												</div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Email Id</label><input type="email" class="form-control" maxlength=50
														id="caseDetail" name="email" ng-model="petitionerDetails.cpt_email">
														<span style="color:red" ng-show="petitioner.email.$dirty && petitioner.email.$invalid">
                                                           <span ng-show="petitioner.email.$error.email && petitioner.email.$touched">Invalid email address.</span>
                                                                </span>
														</div>
                                                 
												<div class="col-sm-6">
													<label>City</label> <input  type="text" class="form-control" id="caseDetail" maxlength=30
														name="caseDetail" ng-model="petitionerDetails.cpt_city">
												</div>
											</div>

											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Mobile No</label><input numbers-only type="text"
														class="form-control" id="mobile" name="mobile" maxlength=10  ng-model="petitionerDetails.cpt_mobile"/>
							             <span style="color: red">	
							         <!--  <span class="error" ng-show="petitioner.mobile.$error.maxlength"><br> mobile number not more than ten digits</span> -->
							          <span class="error"ng-show="petitioner.mobile.$error.pattern"><br>Enter the valid Mobile Number</span>
                                          </span>
                                              </div>

                                         	<div class="col-sm-6">
													<label>State</label>  <select
									         class="form-control" id="s_id" name="s_id"
									       ng-model="petitionerDetails.cpt_s_id"
									        ng-options="state.s_id as state.s_name for state in stateList">
									            <option value="">Select State</option>
								         </select>
                                           </div>
												
											</div>


											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Other Contact No's</label> 
														 <input	class="form-control"  max-length=50 id="caseDetail" ng-model="petitionerDetails.cpt_other_contact" type="text"/>
												</div>
												
												<div class="col-sm-6">
													<label>Pincode</label> 
														 <input	 numbers-only class="form-control"  name="pincode"  ng-pattern="/^[0-9 \\\-,]*$/" maxlength=6 id="pincode" ng-model="petitionerDetails.cpt_pincode" type="text">
														  <span style="color: red">	
							              <!--  <span class="error" ng-show="petitioner.pincode.$error.maxlength"><br>Pincode not more than six digits</span> -->
							                 <span class="error"ng-show="petitioner.pincode.$error.pattern"><br>Enter the valid Mobile Number</span>
                                          </span>
														
														
														
												</div>
												

											</div>
											
											 <div class="row pull-right">
                                                      <div class="col-sm-12">
						     <button class="btn btn-success" ng-disabled="!petitionerDetails.cpt_name" ng-click="petitioner.$valid && addPetitioner(petitionerDetails)">Add Petitioner</button>
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
                                                                  <tr ng-repeat="row in petitionerDataList" class="odd gradeX">
                                                                                        <td>{{row.cpt_name}}</td>
                                                                                        <td>{{row.cpt_email}}</td>
                                                                                        <td>{{row.cpt_address}}</td>   
                                                                                          <td>{{row.cpt_mobile}}</td> 
                                                                                          <td class="text-center">
                                                                                    <a  ng-click="editPet(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          <a  ng-click="deletePetitioner(row.cpt_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
                                                                            
                                                                             
                                                                             
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

					
					
					
					
