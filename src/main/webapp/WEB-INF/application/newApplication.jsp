
<div class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<h3></h3>
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">

					<div class="panel-heading font-bold">Application Details</div>
					<div class="panel-body">
						
						<div class="form-group pull-in clearfix">
							<div class="col-sm-6">
								<label>Application/Document Type <span class="text-danger"> * </span></label> <select
									class="form-control" id="ct_id" name="ct_id"
									ng-model="application.ap_at_mid"
									 ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList | orderBy:'at_name'">
									<option value="">Select Application Type</option>
								</select>

							</div>
							

						</div>

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>

<div  ng-show="application.ap_at_mid==50" class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">
					<div class="panel-heading font-bold">OLR Details</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
						
							<div class="col-sm-4">
								<label class="control-label">OLR Number <span class="text-danger"> * </span> </label> 
								<input type="text" max-length=60 class="form-control" id="caseDetail" name="name"
														ng-model="application.ap_olr_no" required>
							</div>
							<div class="col-sm-3">
							
								<label>OLR Year <span class="text-danger"> * </span></label> 
								
								<input type="text" max-length=60 class="form-control" id="caseDetail" name="name"
														ng-model="application.ap_olr_year" required>
								
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
					<div class="panel-heading font-bold">Applicant Details</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
						
							<div class="col-sm-3">
								<label class="control-label">Filled By <span class="text-danger"> * </span> </label> <select
									class="form-control" id="dt_id" name="dt_id"
									ng-model="application.ap_filed_by"
									 ng-options="filledBy.id as filledBy.value for filledBy in filledByOptions">
									<option value="">Select Filled By</option>
								</select>
							</div>
							<div class="col-sm-6">
							
								<label>Applicant Name <span class="text-danger"> * </span></label> 
								
								<input type="text" max-length=60 class="form-control" id="caseDetail" name="name"
														ng-model="application.ap_applicant_name" required>
								
							</div>
						

						</div>
						

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>

					

<div class="row">
	<div class="col-md-12">

		<div class="col-md-4"></div>
		<div class="col-md-4">
			<button class="btn btn-success" ng-disabled="!application.ap_at_mid || !application.ap_filed_by || !application.ap_applicant_name"
				ng-click="addApplication(application)">Save</button>
		</div>


	</div>
</div>





