
<div class="smooth container w-xxxl w-auto-xs" >
						<form name="courtFee" class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10" style="float:centre">
									<div class="panel panel-default" style="margin-left: 140px;">
										<div class="panel-heading font-bold">E-Court Fee</div>
										<div class="panel-body">
											<div class="form-group pull-in clearfix">
												<div class="col-sm-4">
													<label>Receipt No:<span class="text-danger">
															* </span></label> <input type="text" class="form-control" id="caseDetail" name="receipt"  maxlength=17 	ng-model="applicationFee.acf_receipt_no" required>
														<span style="color: red">	
							                <span class="error" ng-show="applicationFee.acf_receipt_no.$error.maxlength"><br> receipt no not more than sixteen digits</span>
							                <span class="error" ng-show="applicationFee.acf_receipt_no.$error.minlength"><br> receipt number not less than sixteen digits</span>
							                 
                                          </span>

												</div>

												<div class="col-sm-4">
													<label>Amount</label> <input numbers-only type="text" class="form-control" id="caseDetail"
														name="amount" ng-model="applicationFee.acf_amount"  required>
														<!-- <span class="error"ng-show="applicationFee.acf_amount.$error.pattern"><br>Enter the valid amount</span> -->
												</div>
                                             </div>
											
	                                            <div class="col-sm-4">
	                                            <label>Date</label>
	                                            <input type="text" ng-disabled="true" class="form-control" datepicker-popup="{{format}}" name="fromDate" ng-model="applicationFee.acf_date" required is-open="fromDate" max-date="maxDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			          <span class="input-group-addon" ng-click="open($event,'fromDate')"><i class="glyphicon glyphicon-calendar"></i></span>
				            			         
				            			         </div>

											</div>
											
											 <div class="row pull-right">
                                                      <div class="col-sm-12">
						     <button class="btn btn-success" ng-disabled="!applicationFee.acf_receipt_no || !applicationFee.acf_amount || !applicationFee.acf_date" ng-click="addCourtFee()">Add Court Fee</button>
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
                                                                        <th>Receipt No</th>
                                                                           <th>Amount</th>
                                                                           <th>Date</th>
                                                                        <th class="text-center">Action</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in courtFeeList" class="odd gradeX">
                                                                                        <td>{{row.acf_receipt_no}}</td>
                                                                                        <td>{{row.acf_amount}}</td>
                                                                                        <td>{{row.acf_date|date:"dd/MM/yyyy" }} </td>   
                                                                                          <td class="text-center">
                                                                                    <a  ng-click="editCourtFee(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                         <!--  <a  ng-click="deleteCourtFee(row.acf_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a> -->
                                                                            
                                                                             
                                                                             
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

					
					
					
					
