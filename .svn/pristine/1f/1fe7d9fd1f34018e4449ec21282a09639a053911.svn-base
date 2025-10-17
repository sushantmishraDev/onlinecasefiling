
<form class="form-horizontal reduce-gap" name="masterForm" novalidate role="form">      
<div class="modal-body">
{{errorlist}}
<div ng-show="sizeOf(errorlist)" class="alert alert-block alert-danger">
	<ul>
    <span ng-repeat="errors in errorlist"  >
	        <span ng-repeat="n in errors track by $index">
	        	<li>{{(n)}}</li>
	        </span>
	    </span>
    </ul>
</div>
<div class="row">							  			      		
<div class="form-group">
			<label class="col-md-4  control-label" for="name">Repository<span class="star">*</span></label>
		    <div class="col-md-7">{{folder.repository.name}}</div>		   
</div>
</div>
<div class="row">							  			      		
<div class="form-group">
			<label class="col-md-4  control-label" for="name">Name <span class="star">*</span></label>
		    <div class="col-md-7">
	      		<input type="text" class="form-control" required ng-model="folder.name" id="name" ng-class="{'error' : errorName}" placeholder="Enter Name" required name="name"  />
			</div>		   
</div>
</div>	
<div class="row">	
<div class="form-group">
		   <label for="address" class="col-md-4  control-label">Description<span class="star">*</span></label>
		    <div class="col-md-7">	    
	      		<textarea class="form-control" id="description" required name="address" ng-model="folder.description" ></textarea>
		    </div>
		</div>
</div>

<div class="row">							  			      		
 	<div class="form-group">
			    <label for="status" class="col-md-4 control-label">Status <span class="star">*</span></label>
			    <div class="col-md-7">
				   <select class="form-control" id="status" name="status" required ng-model="folder.status">
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
	<input type="submit" value="Submit" ng-disabled="masterForm.$invalid" ng-click="update(folder)"   class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
	</div>
	<div ng-if="masterentity.id">
			<input type="submit" ng-disabled="masterForm.$invalid" value="Update" ng-disabled="masterentity.$invalid" ng-click="update(folder)" class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  	
	</div>	     
</div>

</form>

 