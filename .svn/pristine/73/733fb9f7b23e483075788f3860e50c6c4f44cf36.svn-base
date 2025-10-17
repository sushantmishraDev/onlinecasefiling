 <div class="modal-header">
   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
   <h4 class="modal-title" id="myModalLabel"><strong> Manage Meta Fields</strong></h4>
 </div>
 
<div class="modal-body">
<div class="row" ng-show="!fieldManage">
<%@ include file="../metatemplate/_field_form.jsp"%>
</div>
<div class="row" ng-show="fieldManage">
<div class="col-md-12 ">
	<div class="col-md-12 ">
		<button type="button" class="btn btn-primary btn-sm pull-right" ng-click="resetFieldData()" data-toggle="modal" data-target="#create">
			<span class="glyphicon glyphicon-plus-sign"></span> Create New Meta Field
		</button>
	</div>

<div class="col-md-12 voffset1">

<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
<thead>
<tr>
<th st-sort="name">Name</th>
<th st-sort="address">Description</th>
<th st-sort="status">Status</th>
<th style=" width: 250px;" class="text-center">Action
</th>					
</tr>
<tr>
<th><input st-search="name" placeholder="search for name" class="input-sm form-control" type="search"/></th>
<th><input st-search="description" placeholder="search for address" class="input-sm form-control" type="search"/></th>
<th><input st-search="status" placeholder="search for status" class="input-sm form-control" type="search"/></th>		
<th></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="data in displayedCollection">
<td>{{data.name}}</td>
<td>{{data.description}}</td>
<td>{{ data.status=='1' ? "Active" : "Inactive" }}</td>
<td>
<a  class="btn btn-info btn-xs"    ng-click="setMasterdata(data,$index)" data-toggle="modal" data-target="#create" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>
</td>
</tr>

</tbody>
<tfoot>
			<tr>
				<td colspan="5" class="text-center">
					<div st-pagination="" st-items-by-page="10"  st-displayed-pages="4" ></div>
				</td>
			</tr>
</tfoot>
</table>
</div>
</div>
 </div>

 