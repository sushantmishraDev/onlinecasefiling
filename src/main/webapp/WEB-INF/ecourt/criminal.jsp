<div class="row">
<div class="col-md-6">

<div class="smooth container w-xxxl w-auto-xs" >
						<form  class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10">
								
									<div class="panel panel-default" style="margin-left: 140px;">
						            <div class="panel-heading font-bold">Crime Details</div>
										<div class="panel-body">
										
								
										
										
										
                                          <div class="form-group pull-in clearfix">

                                                <div class="col-sm-6" >
													<label class="control-label">District </label> <select
									           class="form-control" id="dt_id" name="dt_id"
									          ng-model="crimeDetail.cd_district"
									      ng-options="district.dt_id as district.dt_name for district in districtList">
									<option value="">Select District</option>
								</select>
												</div>
                                                     
                                                <div class="col-sm-6" ng-show="impugnedOrder.io_court_type==1">
													<label class="control-label">Police Station</label> <select class="form-control" id="caseDetail"
														name="caseDetail" ng-model="crimeDetail.cd_police_stn"
														ng-options="station.pt_id as station.pt_name for station in policeStationList | filter: filterExpression">
														<option value=""></option>
													</select>

												</div>
											</div>
											
									<div class="form-group pull-in clearfix">
												
										
							
												<div class="col-sm-6">
													<label class="control-label">Crime No. <span class="text-danger"> * </span>
													</label> <input  type="text" class="form-control" maxlength=18
														ng-model="crimeDetail.cd_crime_no">
												</div>
												
												
												<div class="col-sm-6">

													<label class="control-label">Crime Year <span class="text-danger"> * </span>
													</label> <input numbers-only  maxlength=4 type="text" class="form-control"
														ng-model="crimeDetail.cd_crime_year">
												</div>
												</div>
                                        
											<div class="row pull-right">
                                                      <div class="col-sm-12">
                                                           <!-- <div class="col-sm-6" style="float:right"> -->
						                  <button class="btn btn-success" ng-disabled="!crimeDetail.cd_district || !crimeDetail.cd_police_stn || !crimeDetail.cd_crime_no || !crimeDetail.cd_crime_year" ng-click="addCrimeDetails()">Save</button>
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
                            <h4 class="panel-title">Crime Details Details</h4>
                        </div>
                        <div class="panel-body">
                             <div class="table-responsive">
                                                      <table id="data-table" class="table table-striped table-bordered">
                                                            <thead>
                                                                  <tr>
                                                                        <th>District</th>
                                                                        <th>Police Station</th>
                                                                        <th>Crime No</th>
                                                                        <th>Crime Year</th>
                                                                        <th class="text-center">Action</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in crimeDetailList" class="odd gradeX">
                                                                        <td>{{row.district.dt_name}}</td>
                                                                        <td>{{row.policeStation.pt_name}}</td>                                                                          
                                                                        <td>{{row.cd_crime_no}}</td>
                                                                        <td>{{row.cd_crime_year}}</td>
                                                                        <td class="text-center">
                                                                        	<a  ng-click="editCrimeDetals(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          	<a  ng-click="deleteCrimeDetails(row.cd_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
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
            