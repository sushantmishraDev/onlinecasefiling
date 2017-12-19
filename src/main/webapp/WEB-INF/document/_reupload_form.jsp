
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">      
<div class="modal-body">
<div ng-if="errorlist" class="alert alert-block alert-danger">
		<ul>
			<span ng-repeat="errors in errorlist "> 
				<span ng-repeat="n in errors track by $index">
						<li>{{(n)}}</li>
				</span>
			</span>
		</ul>
</div>
<div class="row">							  			      		
<div class="form-group">
			<label class="col-md-4  control-label" for="file">Files<span class="star">*</span></label>
<!-- 			<input type="file" file-upload multiple/> -->
			<input type="file" multiple="multiple" ngf-select ng-model="picFile" name="file"  >
    <ul>
        <li ng-repeat="file in files">{{file.name}}</li>
    </ul>
</div>
</div>

</div>
<div class="modal-footer"> 
	<div ng-if="!masterentity.id">
	<input type="submit" value="Submit" ng-disabled="masterForm.$invalid" ng-click="save()"   class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
	</div>
	<div ng-if="masterentity.id">
			<input type="submit" ng-disabled="masterForm.$invalid" value="Update" ng-disabled="masterentity.$invalid" ng-click="update(document)" class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  	
	</div>	     
</div>

</form>
