<!-- master form -->
<form class="form-horizontal reduce-gap" name="masterForm" novalidate role="form">      
<div class="modal-body">

<!-- <div ng-show="sizeOf(errorlist)" class="alert alert-block alert-danger"> -->
<!-- 	<ul> -->
<!--     <span ng-repeat="errors in errorlist"  > -->
<!-- 	        <span ng-repeat="n in errors track by $index"> -->
<!-- 	        	<li>{{(n)}}</li> -->
<!-- 	        </span> -->
<!-- 	    </span> -->
<!--     </ul> -->
<!-- </div> -->

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
		   <label for="address" class="col-md-4  control-label">Description<span class="star">*</span></label>
		    <div class="col-md-7">	    
	      		<textarea class="form-control" id="description" required name="address" ng-model="masterentity.description" ></textarea>
		    </div>
		</div>
</div>
<div class="row">							  			      		
 	<div class="form-group">
			    <label for="basepath" class="col-md-4 control-label">Basepath<span class="star">*</span></label>
			    <div class="col-md-7">
	     			<input type="text" id="basepath" required class="form-control" placeholder="Enter Basepath" name="basepath" ng-model="masterentity.basepath" />
	     		</div>				   
	</div>
</div>

<div class="row">							  			      		
 	<div class="form-group">
			    <label for="status" class="col-md-4 control-label">Status <span class="star">*</span></label>
			    <div class="col-md-7">
				   <select class="form-control" id="status" name="status" required ng-model="masterentity.status">
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
	<input type="submit" value="Submit" ng-click="create(masterentity)"   class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
	</div>
</div>

</form>

 