<div class="form-group">
			<label class="col-md-4 control-label">Search Type<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<label class="radio-inline">
						  <input type="radio" name="gender" ng-change="getsearchform()" ng-required="true"  ng-model="searchcriteria.type" value="metafields">Metafields
						</label>
						<label class="radio-inline">
						  <input type="radio" name="gender" ng-change="getsearchform()" ng-required="true"  ng-model="searchcriteria.type" value="searchincontent"> Content
						</label>
			</div>		   
</div>
							  			      		
<div class="form-group" id="metafieldid">
			<label class="col-md-4  control-label" for="metafield">Meta Field<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<select class="form-control" id="metafield" name="metafield" ng-model="searchcriteria.metafield"  ng-options="metafield.id as metafield.mf_lable for metafield in metafields">
					<option value="">Select Meta Field</option>
				</select>
			</div>		   
</div>
						  			      		
<div class="form-group">
			<label class="col-md-4  control-label" for="criteria">Criteria<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<select class="form-control" id="criteria" name="criteria" ng-model="searchcriteria.criteria" ng-options="criteria.id as criteria.value for criteria in criterias">
					<option value="">Select Criteria</option>
				</select>
			</div>		   
</div>
						  			      		
<div class="form-group" ng-show="searchcriteria.metafield!=12 || searchcriteria.criteria!='between'" >
			<label class="col-md-4  control-label" for="searchtext">Data To Search<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<textarea name="searchtext" class="form-control"  id="searchtext" ng-model="searchcriteria.searchtext"></textarea>
			</div>		   
</div>
<div class="form-group" ng-show="searchcriteria.metafield==12 && searchcriteria.criteria=='between'">
			<label class="col-md-4  control-label" for="searchtext">Date To Search<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<input type='text' class="form-control" ng-model='date1' mask='39/19/9999' id="searchtext"/>
	      		<br>
	      		<input type='text' class="form-control" ng-model='date2' mask='39/19/9999' />
			</div>		   
</div>

<div class="form-group">
<div class="col-md-offset-5">
<input type="submit" value="AND" ng-click="createquery(searchcriteria,'AND')" class="btn btn-success"/>
<input type="submit" value="OR" ng-click="createquery(searchcriteria,'OR')" class="btn btn-success"/>
</div>
</div>
<div class="row">
<table id="searchformdisplay" class="table table-striped table-bordered table-hover">
	<tr>
		<th>Operator</th>
		<th>Field</th>
		<th>Criteria</th>
		<th>Type</th>
		<th>Text To Search</th>
		<th>Action</th>
	</tr>
	<tr ng-repeat="data in searchcriterias">
	<td>{{data.operator}}</td>
	<td>{{data.metafieldname}}</td>
	<td>{{data.criteria}}</td>
	<td>{{data.type}}</td>
	<td>{{data.searchtext}}</td>
	<td>
		<a  class="btn btn-info btn-xs"  ng-click="editCondition(data)"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>
		<a  class="btn btn-info btn-xs"  ng-click="deleteCondition(data)"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</a>
	</td>
	</tr>
</table>
</div>
<div class="row">
<input type="button" value="Search" ng-click="searchdocument()" class="btn btn-success"/>
<input type="submit" value="Save Query" ng-click="savequery()" class="btn btn-success"/>
<input type="submit" value="New Query" ng-click="newquery()" class="btn btn-success"/>
<input type="submit" value="Load Query" data-toggle="modal" data-target="#queriesModal" class="btn btn-success"/>
</div>