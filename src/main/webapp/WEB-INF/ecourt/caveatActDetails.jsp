	<div class="smooth container w-xxxl w-auto-xs">
		<form name="leadStage" class="form-validation">
		<h3></h3>
			<fieldset class="scheduler-border">
				<div class="col-sm-10">
					<div class="panel panel-default" style="margin-left: 140px;">
						<div class="panel-heading font-bold">Act Details</div>
						<div class="panel-body">
							<div class="form-group pull-in clearfix">
								<div class="col-sm-6">
                                  <div ng-init="actDetails.cact_type=1">
									<label>Belongs To:&emsp; 
									<input type="radio"  id="central"  data-ng-click="changeAct($event)" ng-model="actDetails.cact_type"
										name="actType" value="1">Central&emsp; 
										<input type="radio"  id="state" data-ng-click="changeAct($event)" name="actType"
										ng-model="actDetails.cact_type" value="state">State
									</label>
								</div>
								</div>

							</div>
							<div class="form-group pull-in clearfix" ng-show="centralShow">
								<div class="col-sm-6">
									<label>Central Act (Title)</label> <select
										class="js-example-basic-single" select2=""  style="width: 332px;"
										ng-model="actDetails.cact_code"
										ng-options="actMaster.act_id as actMaster.act_name for actMaster in centralActList">
										<option value="">Select Act Title</option>
										</select>
										
										<!-- <select  class="js-example-basic-single" select2=""  ng-model="cavApi.caseDist" ng-options="district.dt_id as district.dt_name for district in districtList">
                                                        <option value="">Select District</option>
                                                    </select>
 -->
								</div>
								</div>
								
								
								
								
								<div class="form-group pull-in clearfix"  ng-show="stateShow">
								
								<div class="col-sm-6">
									<label>State Act(Title)</label> <!-- <select
										class="form-control" 
										ng-model="actDetails.act_code"
										ng-options="actMaster.act_id as actMaster.act_name for actMaster in stateActList"></select> -->
										
										 <select
										class="js-example-basic-single" select2=""  style="width: 332px;"
										ng-model="act_code2" ng-change="stateAct(act_code2)"
										ng-options="actMaster.act_id as actMaster.act_name for actMaster in stateActList">
										<option value="">Select Act Title</option>
										</select>
								</div>
								
																		
							
                           

						
						</div>
						
						 <div class="row pull-right">
                                                      <div class="col-sm-12">
						                  <button ng-show="centralShow" class="btn btn-success"  ng-disabled="!actDetails.cact_code" ng-click="addActDetails(actDetails)">Add ActDetails</button>
						                   <button ng-show="stateShow" class="btn btn-success"  ng-disabled="!act_code2" ng-click="addActDetails(actDetails)">Add ActDetails</button>
						                     </div>
                                                           
                                             </div>
                                             
                                             <!-- <div class="form-group pull-in clearfix">
                           <font size="2"
          face="verdana"
          color="red"> 
          * Please fed exact Act Details as per memo of petition.
        </font>           
        </div>    -->     


						
						
						
					</div>
				
				 <div class="form-group pull-in clearfix">
                           <font size="2"
          face="verdana"
          color="red"> 
          * Please fed exact Act Details as per memo of petition.
        </font>           
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
                                                                        <th>Act Belongs To</th>
                                                                           <th>Act (Title)</th>
                                                                        <th class="text-center">Action</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in actDataList" class="odd gradeX">
                                                                                        <td>{{row.cact_type}}</td>
                                                                                        <td>{{row.actMaster.act_name}}</td>
                                                                                          <td class="text-center">
                                                                                    <a  ng-click="editAct(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a>
                                                                          <a  ng-click="deleteActDetails(row.cact_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
                                                                            
                                                                             
                                                                             
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
		
	
