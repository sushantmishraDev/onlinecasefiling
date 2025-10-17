
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
															* </span></label> <input type="text" class="form-control" id="caseDetail" name="receipt"  maxlength=17
														ng-model="caveatFee.ccf_receipt_no" required>
																								</div>

												<div class="col-sm-4">
													<label>Amount</label> <input numbers-only type="text" class="form-control" id="caseDetail"
														name="amount" ng-model="caveatFee.ccf_amount" required>
														<!-- <span class="error"ng-show="courtFee.amount.$error.pattern"><br>Enter the valid amount</span> -->
												</div>
                                             </div>
											
	                                            <div class="col-sm-4">
	                                            <label>Date</label>
	                                            <input type="text" class="form-control"  ng-disabled="true" datepicker-popup="{{format}}" name="fromDate" ng-model="caveatFee.ccf_date" required is-open="fromDate" max-date="maxDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			          <span class="input-group-addon" ng-click="open($event,'fromDate')"><i class="glyphicon glyphicon-calendar"></i></span>
				            			         
				            			         </div>

											</div>
											
											 <div class="row pull-right">
                                                      <div class="col-sm-12">
						     <button class="btn btn-success" ng-disabled="!caveatFee.ccf_date || !caveatFee.ccf_receipt_no || !caveatFee.ccf_receipt_no" ng-click="addCourtFee()">Add Court Fee</button>
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
                                                                                        <td>{{row.ccf_receipt_no}}</td>
                                                                                        <td>{{row.ccf_amount}}</td>
                                                                                        <td>{{row.ccf_date|date:"dd/MM/yyyy" }} </td>   
                                                                                          <td class="text-center">
                                                                                    <a  ng-click="editCourtFee(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          <a  ng-click="deleteCourtFee(row.ccf_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
                                                                            
                                                                             
                                                                             
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

					
					
					
					
