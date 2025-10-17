
<div class="smooth container w-xxxl w-auto-xs" >
						<form name="stNumber" class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10" style="float:centre">
									<div class="panel panel-default" style="margin-left: 140px;">
										<div class="panel-heading font-bold">Extra Advocate Details</div>
										<div class="panel-body">
											<div class="form-group pull-in clearfix">
												
												<div class="col-sm-4"></div>

											</div>
											
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Roll No <span class="text-danger">
															* </span></label> <input type="text" maxlength=60 class="form-control" id="caseDetail" name="name"
														ng-model="rollno"  ng-blur='getAvocateDetails(rollno)' required>
														<!-- <span class="text-danger" ng-show="respondent.name.$touched && respondent.name.$invalid">The name is required.</span> -->

												</div>

											</div>
											
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Name <span class="text-danger">
															* </span></label> <input type="text" maxlength=60 class="form-control" id="caseDetail" name="name"
														ng-model="extraAdovate.name" required  readonly="readonly">
														<!-- <span class="text-danger" ng-show="respondent.name.$touched && respondent.name.$invalid">The name is required.</span> -->

												</div>

												<div class="col-sm-6">

													<label class="control-label">Address
													</label> <textarea class="form-control" rows="4" col="50" maxlength=100
														ng-model="extraAdovate.address1" name="address" placeholder="Address Line 1" readonly="readonly">
														</textarea>
														
												</div>

											</div>
											<div class="form-group pull-in clearfix">
												<div class="col-sm-6">
													<label>Email Id</label> <input type="email" class="form-control" maxlength=50
														id="caseDetail" name="email" ng-model="extraAdovate.email" readonly="readonly">
														<span style="color:red" ng-show="extraAdovate.email.$dirty && extraAdovate.email.$invalid">
                                                           <span ng-show="extraAdovate.email.$error.email">Invalid email address.</span>
                                                                </span>
														</div>
														
														<div class="col-sm-6">
													<label>Mobile No</label> <input numbers-only
														class="form-control" id="mobile" name="mobile"  maxlength=10   ng-model="extraAdovate.mobile" readonly="readonly">
												
												</div>
                                                       
												

											</div>

											


											
											
											 <div class="row pull-right">
                                                      <div class="col-sm-12">
						<button class="btn btn-success" ng-disabled="!extraAdovate.name" ng-click="addExtraAdvocate(extraAdovate)">Add Extra Advocate</button>
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
                                                                  <tr ng-repeat="row in extraAdvocateDataList" class="odd gradeX">
                                                                                        <td>{{row.advocate.name}}</td>
                                                                                        <td>{{row.advocate.email}}</td>
                                                                                        <td>{{row.advocate.address1}}</td>   
                                                                                          <td>{{row.advocate.mobile}}</td> 
                                                                                          <td class="text-center">
                                                                       	  
                                                                         <span >
                                                                     	   <a  ng-click="deleteExtraAdvocate(row.cea_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
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

					
					
					
					
					
