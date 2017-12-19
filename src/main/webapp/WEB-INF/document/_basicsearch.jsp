<div class="form-group" >
			<label class="col-md-4 control-label" for="benchCodeId">Bench Type<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<select class="form-control" id="benchCodeId" name="benchCodeId" ng-model="basicsearch.benchCodeId" ng-options="benchCode.lk_id as benchCode.lk_longname for benchCode in benchCodes">
					<option value="">Select Bench Type</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="caseTypeId">Case Type</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="caseTypeId" name="caseTypeId" ng-model="basicsearch.caseTypeId" ng-options="caseType.lk_id as caseType.lk_longname for caseType in caseTypes">
					<option value="">Select Case Type</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="judgeId">Judge Name</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="judgeId" name="judgeId" ng-model="basicsearch.judgeId" ng-options="judge.jg_id as judge.jg_name for judge in judges">
					<option value="">Select Judge</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="year">Year</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="year" name="year" ng-model="basicsearch.year" ng-options="year as year for year in years">
					<option value="">Select Year</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="year">Case Number</label>
		    <div class="col-md-7">
	      		<input type="text" class="form-control" id="caseNumber" name="caseNumber" ng-model="basicsearch.caseNumber"/>
					
			</div>		   
</div>
<div class="form-group">
<div class="col-md-offset-5">
<input type="submit" value="Search" ng-click="getBasicSearchData()" class="btn btn-success"/>
</div>
</div>