<form class="form-horizontal reduce-gap" name="addlookupForm" novalidate
	role="form">

	<div ng-show="errorlist" class="alert alert-block alert-danger">
		<ul>
			<span ng-repeat="errors in errorlist"> <span
				ng-repeat="n in errors track by $index">
					<li>{{(n)}}</li>
			</span>
			</span>
		</ul>
	</div>

	<!-- begin #content -->
	<div id="content" class="content" style="width: 154%;">
		<div class="row">
			<div class="col-md-5 ui-sortable">
				<div data-sortable-id="form-validation-1">
					<div class="panel-body panel-form">
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" for="lk_setname">Lookup
								SetName:</label>
							<div class="col-md-6 col-sm-6">
								<select ng-model="addlookup.lk_setname" class="form-control"
									id="lk_setname" required name="lk_setname"
									ng-change="getLookupparentChange()"
									ng-options="bcd.lk_setname as bcd.lk_setname for bcd in setNameList  | unique:'lk_setname'| orderBy: 'lk_setname'">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" for="lk_longname">Lookup
								Name:</label>
							<div class="col-md-6 col-sm-6">
								<ng-form name="addlookupForm"> <input required
									type="text" name="lk_longname" id="lk_longname"
									class="form-control" placeholder="Lookup Name"
									ng-pattern="/^[ a-zA-Z0-9]*$/" ng-maxlength=20
									ng-model="addlookup.lk_longname" /> 
									
									<span style="color: red"
									ng-show="addlookupForm.lk_longname.$dirty && addlookupForm.lk_longname.$invalid && addlookupForm.lk_longname.$error ">
									<span ng-show="addlookupForm.lk_longname.$error.required">Lookup
										name is required.</span> <small class="error"
									ng-show="addlookupForm.lk_longname.$error.pattern">Enter Valid Lookup Name</small>
									 <small
									class="error"
									ng-show="addlookupForm.lk_longname.$error.maxlength">Lookup
										Name cannot be longer than 20 characters</small>

								</span> </ng-form>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" for="lk_value">Lookup Value :</label>
							<div class="col-md-6 col-sm-6">
								<ng-form name="addlookupForm2">
								<input class="form-control" type="text" id="lk_value"
									name="lk_value" ng-model="addlookup.lk_value"
									placeholder="Lookup value" ng-maxlength=10
									ng-pattern="/^[0-9]*$/" >
									
									<span style="color: red"
									ng-show="addlookupForm2.lk_value.$dirty && addlookupForm2.lk_value.$invalid && addlookupForm2.lk_value.$error">
									
									 <small  class="error" ng-show="addlookupForm2.lk_value.$error.pattern">Enter valid Lookup Value 
										</small>
									 <small
									class="error"
									ng-show="addlookupForm2.lk_value.$error.maxlength">
									Lookup value  cannot be longer than 10 characters</small>

								</span>
									
									
									</ng-form>
							</div>
						</div>
						<div class="form-group">

							<label class="control-label col-md-4 col-sm-4" for="usr">Lookup
								Parent:</label>
							<div class="col-md-6 col-sm-6">
								<select ng-model="addlookup.lk_parent" class="form-control"
									id="lk_parent" required name="lk_parent"
									ng-options="bcd.lk_id as bcd.lk_longname for bcd in getparentList | orderBy: 'lk_longname'">
						
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" for="usr">Lookup
								Status:</label>
							<div class="col-md-6 col-sm-6">
								<input type="radio" name="Active" value="1"
									ng-model="addlookup.lk_rec_status" /> Active <input
									type="radio" name="InActive" value="2"
									ng-model="addlookup.lk_rec_status" /> InActive
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" for="lk_serial_no">Lookup
								Serial Number :</label>
							<div class="col-md-6 col-sm-6">
								 <input type="text"
									ng-pattern="^(0|[1-9][0-9]*)$" class="form-control"
									id="lk_serial_no" ng-model="addlookup.lk_serial_no"
									placeholder="Lookup Number"  name="lk_serial_no" ng-value="$index"
									ng-required="required "/>
									 <span class="text-danger"
									ng-show="addlookupForm.lk_serial_no.$error.pattern">Lookup
									no is not correct</span>
									
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<!--<div ng-if="!acceptanceOfBundlesForm.ib_id" style="padding-left: 107px;">-->

						<input type="submit" id="submitbtn" data-loading-text="Loading..."
							value="Submit" ng-click="save(addlookup)" class="btn btn-success" />
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>

					</div>
				</div>
			</div>
		</div>
	</div>
</form>