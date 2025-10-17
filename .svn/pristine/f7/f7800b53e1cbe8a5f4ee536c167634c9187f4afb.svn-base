
<form class="form-horizontal reduce-gap" name="masterForm" novalidate role="form">      
<div class="modal-body">
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
			<label class="col-md-4  control-label" for="name">Name <span class="star">*</span></label>
		    <div class="col-md-7">
	      		<input type="text" class="form-control" required id="name" ng-class="{'error' : errorName}" placeholder="Enter Name" placeholder="Name" required name="name" ng-model="masterentity.name" />
			</div>		   
</div>
</div>	
<div class="row">	
<div class="form-group">
		   <label for="description" class="col-md-4  control-label">Description</label>
		    <div class="col-md-7">	    
	      		<textarea class="form-control" id="description" name="description" ng-model="masterentity.description" ></textarea>
		    </div>
		</div>
</div>


<div class="row">							  			      		
 	<div class="form-group">
			    <label for="isActive" class="col-md-4 control-label">Active <span class="star">*</span></label>
			    <div class="col-md-7">
				   <select class="form-control" id="isActive" name="isActive"  ng-model="masterentity.isActive">
			      		<option value="">Select Status</option>
			      		<option value=1>Active</option>
			      		<option value=0>Inactive</option>
		      		</select>
			    </div>
	</div>
</div>
</div>
<div class="modal-footer"> 
	<div ng-if="!masterentity.id">
	<input type="submit" value="Create" ng-click="save()"   class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
	</div>
	<div ng-if="masterentity.id">
			<input type="submit"  value="Update" ng-disabled="masterentity.$invalid" ng-click="save()" class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  	
	</div>	     
</div>

</form>

 