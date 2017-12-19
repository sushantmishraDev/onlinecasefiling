
<div class="modal-body">
<div class="row">

<table class="table table-striped table-bordered table-hover">
	<tr>
		<th>Document Name</th>
		<th>Status</th>
		<th>Action</th>
		
	</tr>
	<tr ng-repeat="data in subdocuments">
	<td><span ng-if="doc_type==2">{{data.jfd_document_name}}</span></td>
	<td><span ng-if="doc_type==2"><span ng-if="data.jfd_rec_status==0">Deleted</span><span ng-if="data.jfd_rec_status==1">Active</span></span></td>
	
	<td>
		<button type="button" class="btn btn-primary btn-sm" ng-click="rejectForScanning(data,doc_type)">
			<span class="glyphicon"></span> Reject For Scanning
		</button>
		
	</td>
	</tr>
</table>

</div>

</div>

