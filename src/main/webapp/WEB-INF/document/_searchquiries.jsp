
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">      
<div class="modal-body">
	<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
<thead>
<tr>
<th st-sort="id">ID</th>
<th st-sort="query">Query</th>
<th st-sort="created">Created</th>
<th style=" width: 250px;" class="text-center">Action
</th>					
</tr>
</thead>
<tbody>
<tr ng-repeat="data in searchqueries">
<td>{{data.id}}</td>
<td>{{data.query}}</td>
<td>{{ data.created|date:'dd/MM/yyyy'}}</td>
<td>
<a class="btn btn-info btn-xs"    ng-click="setQuery(data)"><span class="glyphicon" aria-hidden="true"></span> Select</a>
<a class="btn btn-info btn-xs"    ng-click="deleteQuery(data)"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</a>
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
<div class="modal-footer"> 
	<div>
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  	
	</div>	     
</div>

</form>

 