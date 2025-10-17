
<div class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<h3></h3>
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">

					<div class="panel-heading font-bold">Case Details</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
							<div class="col-sm-3">
								<div ng-init="civil=1">
									<input type="radio" id="civil" data-ng-click="changeCase($event)" name="caseType" ng-model="caveat.cav_case_type" value="civil"> Civil&emsp;
									 <input type="radio"  id="criminal" data-ng-click="changeCase($event)" name="caseType" ng-model="caveat.cav_case_type" value="criminal">Criminal  
								</div>
								
							</div>



						</div>
						<div class="form-group pull-in clearfix">
							<div class="col-sm-6">
								<label>Case Type <span class="text-danger"> * </span></label> <select
									class="form-control" id="ct_id" name="ct_id"
									ng-model="caveat.cav_type"
									 ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypeList | orderBy:'ct_name'">
									<option value="">Select Case Type</option>
								</select>

							</div>
							<div class="col-sm-3">
								<label class="control-label">District <span class="text-danger"> * </span> </label> <select
									class="form-control" id="dt_id" name="dt_id"
									ng-model="caveat.cav_dist_mid"
									 ng-options="district.dt_id as district.dt_name for district in districtList" ng-change="defaultDist()">
									<option value="">Select District</option>
								</select>



							</div>

						</div>

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>

<div class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">
					<div class="panel-heading font-bold">Caveator</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
							<div class="col-sm-6">
							
								<label>Caveator Name <span class="text-danger"> * </span></label> 
								
								<input type="text" max-length=60 class="form-control" id="caseDetail" name="name"
														ng-model="caveat.cav_caveator_name" required>

							</div>
							<div class="col-sm-6">
							
								<label>Email</label> 
								
								<input type="text"  max-length=50 class="form-control" id="email" name="email"
														ng-model="caveat.cav_email" required>

							</div>
							<div class="col-sm-6">
							
								<label>Caveator Mobile</label> 
								
								<input numbers-only maxlength=10 type="text" class="form-control" id="caseDetail" name="mobile"
														ng-model="caveat.cav_mobile" required>

							</div>

						</div>
						

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>




<div class="smooth container w-xxxl w-auto-xs" >
						<form  class="form-validation">
						<h3></h3>
							<fieldset class="scheduler-border">
								<div class="col-sm-10">
								
									<div class="panel panel-default" style="margin-left: 140px;">
						            <div class="panel-heading font-bold">Impugned Order Details</div>
										<div class="panel-body">
										
								<div ng-init="caveat.cav_court=1">
									<label>Belongs To:&emsp; 
									<input type="radio"  id="lowerCourt"  data-ng-click="changeCourt($event)" ng-model="caveat.cav_court"
										name="actType" value="1">Lower Court&emsp; 
										<input type="radio"  id="highCourt" data-ng-click="changeCourt($event)" name="actType"
										ng-model="caveat.cav_court" value="2">High Court&emsp;
										<!-- <input type="radio"  id="crimeDetails" data-ng-click="changeCourt($event)" name="actType"
										 value="3">Crime Details&emsp; -->
										<input type="radio"  id="authority" data-ng-click="changeCourt($event)" name="actType" ng-model="caveat.cav_court"
										 value="4">Authority/Other Details&emsp;
										<!--  <input type="radio"  id="mandamus" data-ng-click="changeCourt($event)" name="actType"
										value="5">Mandamus -->
									</label>
								</div>
										
										
										
                                          <div class="form-group pull-in clearfix">
											<div class="col-sm-4" ng-show="highCourtShow">
													<label class="control-label">State </label> <select
									          class="form-control" id="s_id" name="s_id"
									         ng-model="caveat.cav_state_mid"
									           ng-options="state.s_id as state.s_name for state in stateList">
									              <option value="">Select State</option>
								              </select>

												</div>

                                                <div class="col-sm-4" ng-show="lowerCourtShow">
													<label class="control-label">District </label>
													 <select class="form-control" id="dt_id" name="dt_id" ng-model="caveat.cav_ord_dist"
									      				ng-options="district.dt_id as district.dt_name for district in districtList" ng-disabled="caveat.cav_ord_dist!=null">
														<option value="">Select District</option>
													</select>
												</div>
                                                     
                                                <div class="col-sm-4" ng-show="lowerCourtShow">
													<label class="control-label">Subordinate Court Name <span class="text-danger"> * </span></label> 
													<select class="form-control" id="caseDetail"
														name="caseDetail" ng-model="caveat.cav_court_mid"
														ng-options="court.lct_id as court.lct_name for court in lowerCourtList | filter: filterLctCav ">
														<option value=""></option>
													</select>
												</div>
												
												<!-- <div class="col-sm-4" ng-show="lowerCourtShow">{{caveat.cav_mandamus_order}}
													<label class="control-label">Mandamus/Quashing Order</label><br><br>
													 <input type="radio" name="Mandamus/Quashing Order" id="mandamus" ng-model="caveat.cav_mandamus_order" value="M">Mandamus Order<br>
													 <input type="radio" name="Mandamus/Quashing Order" id="mandamus" ng-model="caveat.cav_mandamus_order" value="Q"> Quashing Order
												</div> -->
											</div>
											
									<div class="form-group pull-in clearfix">
												
										<div class="col-sm-4" ng-show="lowerCourtShow">
											<label class="control-label">Case Type <span class="text-danger"> * </span> </label> <select
											class="form-control" id="ct_id" name="ct_id"
											ng-model="caveat.cav_lc_case_type"
											 ng-options="caseType.ct_id as caseType.ct_name for caseType in lowerCourtCaseTypes">
											<option value="">Select Case Type</option>
										</select>

									</div>
									
								<div class="col-sm-4" ng-show="highCourtShow">
									<label>Case Type <span class="text-danger"> * </span>  </label> <select
										class="form-control" id="ct_id" name="ct_id"
										ng-model="caveat.cav_hc_case_type"
										 ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypeList">
										<option value="">Select Case Type</option>
									</select>
								</div>
							<div ng-show ="highCourtShow || lowerCourtShow">
												<div class="col-sm-3" >
													<label class="control-label">Case No. <span class="text-danger"> * </span>
													</label> <input   type="number" class="form-control"
														ng-model="caveat.cav_lc_case_no">
												</div>
												
												
												<div class="col-sm-3">

													<label class="control-label">Year <span class="text-danger"> * </span>
													</label> <input numbers-only maxlength=4 type="text" class="form-control"
														ng-model="caveat.cav_lc_case_year">
												</div>
												
                                          <div class="form-group pull-in clearfix">
												
												
												 <div class="col-sm-3">
													<label class="control-label">Judge Name
													</label> <input type="text" max-length=60 class="form-control"
														ng-model="caveat.cav_ord_psd_by">
												</div>
												<div class="col-sm-3">
													<label class="control-label">CNR No.
													</label> <input type="text" maxlength=16 class="form-control"
														ng-model="caveat.cav_cnr_no">
												</div>
											

                                                
											    <div class="col-sm-4">
													<label class="control-label">Date of Decision<span class="text-danger"> * </span> </label>
											<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="caveat.cav_judgmnt_date" required is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			          <span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
												</div>

											</div>
											</div>
											
                                       

										
                                                
										</div>
										   <div class="form-group pull-in clearfix" ng-show ="crimeDetailShow">

                                                <div class="col-sm-6" >
													<label class="control-label">District </label> <select
									           class="form-control" id="dt_id" name="dt_id"
									          ng-model="caveat.caveatCrimeDetails[0].ccd_district"
									      ng-options="district.dt_id as district.dt_name for district in districtList">
									<option value="">Select District</option>
								</select>
												</div>
                                                     
                                                <div class="col-sm-6">
													<label class="control-label">Police Station</label> <select class="form-control" id="caseDetail"
														name="caseDetail" ng-model="caveat.caveatCrimeDetails[0].ccd_police_stn"
														ng-options="station.pt_id as station.pt_name for station in policeStationList | filter: filterExpression">
														<option value=""></option>
													</select>

												</div>
											</div>
											<div class="form-group pull-in clearfix" ng-show ="crimeDetailShow">
												
										
							
												<div class="col-sm-6">
													<label class="control-label">Crime No. <span class="text-danger"> * </span>
													</label> <input  type="text" class="form-control" maxlength=18
														ng-model="caveat.caveatCrimeDetails[0].ccd_crime_number">
												</div>
												
												
												<div class="col-sm-6">

													<label class="control-label">Crime Year <span class="text-danger"> * </span>
													</label> <input numbers-only  maxlength=4 type="text" class="form-control"
														ng-model="caveat.caveatCrimeDetails[0].ccd_crime_year">
												</div>
												</div>
											<!-- 	<div class="row pull-right" ng-show ="crimeDetailShow">
                                                      <div class="col-sm-12">
                                                           <div class="col-sm-6" style="float:right">
						                  <button class="btn btn-success" ng-disabled="!crimeDetail.cd_district || !crimeDetail.cd_police_stn || !crimeDetail.cd_crime_no || !crimeDetail.cd_crime_year" ng-click="addCrimeDetails()">Save</button>
						                     </div>
                                                           
                                             </div> -->
                                             
                                             
                                               <div class="form-group pull-in clearfix" ng-show ="authorityShow">

                                              <div class="col-sm-3" >
													<label class="control-label">Order No. <span class="text-danger"> * </span>
													</label> <input   type="number" class="form-control"
														ng-model="caveatAuthority1.ca_order_no">
												</div>
												
												
												<div class="col-sm-3">

													<label class="control-label">Order Year <span class="text-danger"> * </span>
													</label> <input numbers-only maxlength=4 type="text" class="form-control"
														ng-model="caveatAuthority1.ca_order_year">
												</div>
												
												<div class="col-sm-4">
													<label class="control-label">Date of Order<span class="text-danger"> * </span> </label>
											<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-model="caveatAuthority1.ca_order_date" required is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			          <span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
												</div>
											</div>
											<div class="form-group pull-in clearfix" ng-show ="authorityShow">
												
										<div class="col-sm-6">
													<label class="control-label">Details Of Authority
													</label> <input type="text" max-length=60 class="form-control"
														ng-model="caveatAuthority1.ca_authority_name">
												</div>
												</div>
											<!-- 	<div class="row pull-right" ng-show ="authorityShow">
                                                      <div class="col-sm-12">
                                                           <div class="col-sm-6" style="float:right">
						                  <button class="btn btn-success" ng-disabled="!crimeDetail.cd_district || !crimeDetail.cd_police_stn || !crimeDetail.cd_crime_no || !crimeDetail.cd_crime_year" ng-click="addCrimeDetails()">Save</button>
						                     </div>
                                                           
                                             </div> -->
									
								
									
						          
										
										
								
										
										
										
                                       
									
                                        
										
                                             </div>

										
                                                
										
								
								
								</div>
								</div>
						
								
								
								
						
								</fieldset>
						</form>
					</div>
					



<div class="row" ng-show="addShow">
	<div class="col-md-12">

		<div class="col-md-4"></div>
		<div class="col-md-4">
			<button ng-show ="!authorityShow && !mandmusShow" class="btn btn-success" ng-disabled="!caveat.cav_court_mid || !caveat.cav_case_type || !caveat.cav_type || !caveat.cav_dist_mid || !caveat.cav_caveator_name  || !caveat.cav_lc_case_no || !caveat.cav_lc_case_year || !caveat.cav_judgmnt_date"
				ng-click="addCaveat(caveat)">Save</button>
			
				<button ng-show ="authorityShow" class="btn btn-success" ng-disabled="!caveat.cav_case_type || !caveat.cav_type || !caveat.cav_dist_mid || !caveat.cav_caveator_name || !caveatAuthority1.ca_order_no || !caveatAuthority1.ca_order_year || !caveatAuthority1.ca_order_date || !caveatAuthority1.ca_authority_name"
				ng-click="addCaveat(caveat)">Save</button>
				<button ng-show ="mandmusShow && !authorityShow" class="btn btn-success" ng-disabled="!caveat.cav_case_type || !caveat.cav_type || !caveat.cav_dist_mid || !caveat.cav_caveator_name" 
				ng-click="addCaveat(caveat)">Save</button>
		</div>


	</div>
</div>



