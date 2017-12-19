<form class="form-horizontal reduce-gap" name="addpolicestationForm"
	novalidate role="form">

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
			<div class="col-md-6 ui-sortable">
				<div data-sortable-id="form-validation-1">
					<div class="panel-body panel-form">

						<div class="form-group">
						
							<label class="control-label col-md-4 col-sm-4" for="usr">Police
								Station name :</label>
							<div class="col-md-6 col-sm-6">
								<input class="form-control" type="text" id="ps_name "
									name="ps_name " ng-model="addpolicestation.ps_name "
									placeholder="Police Station Name" ng-minlength=1
									ng-maxlength=20 ng-pattern="/^[a-zA-Z0-9 ]*$/" required
									data-parsley-required="true" data-parsley-id="5819">
								
								 <span style="color: red"
									ng-show="addpolicestationForm.ps_name.$dirty && addpolicestationForm.ps_name.$invalid && addpolicestationForm.ps_name.$error ">
									<span ng-show="addpolicestationForm.ps_name.$error.required">Police
										Station name is required.</span> 
									<small class="error"
									ng-show="addpolicestationForm.ps_name.$error.pattern"> 
									Enter valid Police Station Name </small> 
									<small class="error"
									ng-show="addpolicestationForm.ps_name.$error.maxlength">Police
										Station name cannot be longer than 20 characters</small>
								</span>

								<ul class="parsley-errors-list" id="parsley-id-5819"></ul>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4">Police Station Address
							:</label>
				<!-- 			
			 			<div class="col-md-6 col-sm-6">
							<input class="form-control" type="text" id="ps_address"
								name="ps_address" ng-model="addpolicestation.ps_address"
								placeholder="Address" ng-maxlength=10
								ng-pattern="/^[a-zA-Z0-9]*$/">
						</div>   -->
						<div class="col-md-6 col-sm-6">
		 					<textarea rows="4" cols="25" name="ps_address"
									id="ps_address" ng-paste="true" ng-pattern="/^[a-zA-Z0-9 ]*$/"
									placeholder="Address" ng-maxlength="1000"
									ng-model="addpolicestation.ps_address">
								</textarea>
								
								 <span style="color: red"
									ng-show="addpolicestationForm.ps_address.$dirty && addpolicestationForm.ps_address.$invalid && addpolicestationForm.ps_address.$error">
								<small class="error"
									ng-show="addpolicestationForm.ps_address.$error.pattern"> 
									Enter valid Police Station Address </small> 
									<small class="error"
									ng-show="addpolicestationForm.ps_address.$error.maxlength">Police
										Station Address cannot be longer than 1000 characters</small>
								</span>
							</div> 
						</div>
					<div class="form-group">
						<label class="control-label col-md-4 col-sm-4" for="usr">District:</label>
						<div class="col-md-6 col-sm-6">
							<select ng-model="addpolicestation.ps_location" class="form-control"
								id="ps_location" required name="ps_location"
								ng-options="policestationtype.lk_id as policestationtype.lk_longname for policestationtype in getpoliceStationLocationList | orderBy: 'lk_longname'">
							</select>
						</div>
					</div>
					<div class="form-group">
							<label class="control-label col-md-4 col-sm-4" for="usr">Police
							Station Status:</label>
							<div class="col-md-6 col-sm-6">
								<input type="radio" name="Active" value="1"
									ng-model="addpolicestation.ps_rec_status" /> Active <input
									type="radio" name="InActive" value="2"
									ng-model="addpolicestation.ps_rec_status" /> InActive
							</div>
						</div>

				</div>
			<div class="form-group">
						<label class="control-label col-md-4 col-sm-4"></label>
						<!--<div ng-if="!acceptanceOfBundlesForm.ib_id" style="padding-left: 107px;">-->

						<input type="submit" id="submitbtn" data-loading-text="Loading..."
							value="Submit" ng-click="save(addpolicestation)" class="btn btn-success" />
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
						<!--</div>-->
						<!--<div ng-if="acceptanceOfBundlesForm.ib_id" style="padding-left: 107px;">
										<input type="submit" id="updatebtn" value="Update" data-loading-text="Loading..."  ng-click="save(data)" class="btn btn-success"/>      
											<button type="button"  class="btn btn-danger" data-dismiss="modal">Cancel</button>  	
									<!--</div>	-->
					</div>
			</div>
		</div>
	</div>
	</div>



</form>