
<form class="form-horizontal reduce-gap" name="masterForm" novalidate
	role="form">
	<div class="modal-body">
		<div ng-if="errorlist" class="alert alert-block alert-danger">
			<ul>
				<span ng-repeat="errors in errorlist "> <span
					ng-repeat="n in errors track by $index">
						<li>{{(n)}}</li>
				</span>
				</span>
			</ul>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-md-4 control-label" for="benchCodeId">Bench
					Code<span class="star">*</span>
				</label>
				<div class="col-md-7">
					<select class="form-control" id="benchCodeId" name="benchCodeId"
						ng-change="getCasetypes()"
						ng-model="folder.benchCodeId" required
						ng-options="lookup.lk_id as lookup.lk_longname for lookup in benchCodes">
						<option value="">Select Bench code</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-md-4 control-label" for="caseTypeId">Case Type<span class="star">*</span>
				</label>
				<div class="col-md-7">
					<select class="form-control" id="caseTypeId" name="caseTypeId"
						ng-change="getFolderStructure()"
						ng-model="folder.caseTypeId" required
						ng-options="lookup.lk_id as lookup.lk_longname for lookup in caseTypes">
						<option value="">Select Case Type</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-md-4  control-label" for="display_name">Display Name <span class="star">*</span>
				</label>
				<div class="col-md-7">
					<input type="text" class="form-control"
						ng-model="folder.display_name" id="display_name"
						ng-class="{'error' : errorName}" placeholder="Enter Name"
						name="display_name" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-md-4  control-label" for="folder_name">Folder
					Name <span class="star">*</span>
				</label>
				<div class="col-md-7">
					<input type="text" class="form-control"
						ng-model="folder.folder_name" id="folder_name"
						ng-class="{'error' : errorName}" placeholder="Enter Name"
						name="folder_name" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-md-4  control-label" for="level">Level<span
					class="star">*</span></label>
				<div class="col-md-7">
					<input type="text" class="form-control" required
						ng-model="folder.level" id="level"
						ng-class="{'error' : errorName}" placeholder="Enter Level"
						name="level" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="col-md-4  control-label" for="name">Folder<span
					class="star">*</span></label>
				<div class="col-md-7">
					<div id="treedata">
						<treecontrol class="tree-classic" tree-model="treedata"
							options="opts" on-selection="showSelected(node)">
						{{node.folder_name}} </treecontrol>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label for="address" class="col-md-4  control-label">Description<span
					class="star">*</span></label>
				<div class="col-md-7">
					<textarea class="form-control" id="description" required
						name="address" ng-model="folder.description"></textarea>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label for="status" class="col-md-4 control-label">Status <span
					class="star">*</span></label>
				<div class="col-md-7">
					<select class="form-control" id="status" name="status" required
						ng-model="folder.status">
						<option value="">Select Status</option>
						<option value="1">Active</option>
						<option value="0">Inactive</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<div ng-if="!masterentity.id">
			<input type="submit" value="Submit" ng-click="create(folder)"
				class="btn btn-success" />
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
		</div>
		<div ng-if="masterentity.id">
			<input type="submit" ng-disabled="masterForm.$invalid" value="Update"
				ng-disabled="masterentity.$invalid" ng-click="update(folder)"
				class="btn btn-success" />
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
		</div>
	</div>

</form>

