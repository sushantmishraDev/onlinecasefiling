
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
									<input type="radio" id="civil"
										data-ng-click="changeCase($event)" name="caseType"
										ng-model="civil" value="1"> <label>Civil&emsp;
										<!-- <input type="radio"  id="criminal" data-ng-click="changeCase($event)" 
														ng-model="criminal" name="caseType" value="">
														Criminal -->
									</label>
								</div>
							</div>



						</div>
						<div class="form-group pull-in clearfix">
							<div class="col-sm-6">
								<label>Case Type <span class="text-danger"> * </span></label> <select
									class="form-control" id="ct_id" name="ct_id"
									ng-model="registerCase.rcd_ct_id"
									 ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypeList">
									<option value="">Select Case Type</option>
								</select>

							</div>
							<div class="col-sm-3">
								<label class="control-label">District <span class="text-danger"> * </span> </label> <select
									class="form-control" id="dt_id" name="dt_id"
									ng-model="registerCase.rcd_dt_id"
									 ng-options="district.dt_id as district.dt_name for district in districtList">
									<option value="">Select District</option>
								</select>




								<!-- <select class="form-control" id="district"
														name="distrcit" ng-model="registerCase.rcd_dt_id"
														   ng-options="district.dt_name for district in districtList track by district.id">
													</select> -->

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
					<div class="panel-heading font-bold">Special Category(first
						petitioner only)</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
							<div class="col-sm-3">
								<input type="checkbox" ng-model="registerCase.rcd_sen_ctz"> <label>Senior
									citizen >65</label>

							</div>

							<div class="col-sm-3">
								<input type="checkbox" ng-model="registerCase.rcd_sc_st"> <label>SC/ST</label>
							</div>

							<div class="col-sm-3">
								<input type="checkbox" ng-model="registerCase.rcd_wm_ch"> <label>Woman/Child
								</label>
							</div>

						</div>
						<div class="form-group pull-in clearfix">
							<div class="col-sm-3">
								<input type="checkbox" ng-model="registerCase.rcd_dvg"> <label>Divyang
								</label>

							</div>

							<div class="col-sm-3">
								<input type="checkbox" ng-model="registerCase.rcd_lg_ad">
								<label>Legal Ald Case</label>
							</div>

							<div class="col-sm-3">
								<input type="checkbox" ng-model="registerCase.rcd_cust"> <label>In
									Custody</label>
							</div>

						</div>

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
			<button class="btn btn-success" ng-disabled="!registerCase.rcd_ct_id || !registerCase.rcd_dt_id"
				ng-click="addRegisterCase(registerCase)">Save</button>
		</div>

		<!-- <div class="col-md-4">
			<div class="col-sm-6" style="float:right">
			<button class="btn btn-primary" ng-click="nextTab($event)">Next</button>
		</div> -->

	</div>
</div>



