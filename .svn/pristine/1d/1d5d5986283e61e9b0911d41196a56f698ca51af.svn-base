
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">      
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

<table id="searchformdisplay" class="table table-striped table-bordered table-hover">
	<tr>
		<th>Document Name</th>
		<th>Status</th>
		<th>Action</th>
		
	</tr>

	
	<tr ng-repeat="data in subdocuments">
	<td><span ng-if="doc_type==2">{{data.jfd_document_name}}</span>
	<span ng-if="doc_type==3">{{data.document_name}}</span>
	<span ng-if="doc_type==4">{{data.mf_file_name}}</span>
	</td>
	<td><span ng-if="doc_type==2"><span ng-if="data.jfd_rec_status==0">Deleted</span><span ng-if="data.jfd_rec_status==1">Active</span></span>
	<span ng-if="doc_type==3"><span ng-if="data.fd_rec_status==0">Deleted</span><span ng-if="data.fd_rec_status==1">Active</span></span>
	<span ng-if="doc_type==4"><span ng-if="data.mf_rec_status==0">Deleted</span><span ng-if="data.mf_rec_status==1">Active</span></span>
	</td>
	<td>
		<button type="button" class="btn btn-primary btn-sm" ng-click="deleteSubDocument(data,doc_type)" data-toggle="modal" data-target="#subDocument">
			<span class="glyphicon"></span> Delete
		</button>
		
	</td>
	</tr>
</table>

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
