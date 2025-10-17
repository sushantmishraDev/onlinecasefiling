<div class="panel panel-inverse">

	<div class="panel-body">
		<div class="table-responsive">	
			<table id="data-table" class="table table-striped table-bordered nowrap table-hover" width="100%">
				<thead>
				<tr>
					<th style="text-align: center;">Case Type</th>
					<th style="text-align: center;">Case No</th>
					<th style="text-align: center;">Case Year</th>
					<!-- <th style="text-align: center;">Status</th> -->
					<th style="text-align: center;">Date</th>
					<th style="text-align: center;">Document Status</th>
					<th style="text-align: center;">Action</th>
				</tr>
				</thead>
				<tbody>
					<tr ng-repeat="data in defectRemovalByOrder" class="odd gradeX">
						<td align="center">{{data.caseFileDetail.caseType.ct_name}}</td>
						<td align="center">{{data.caseFileDetail.fd_case_no}}</td>
						<td align="center">{{data.caseFileDetail.fd_case_year}}</td>
						<td align="center">{{data.drp_cr_date  | date:"dd/MM/yyyy"}}</td>
						<td align="center">{{data.lkStatus.lk_longname}}</td>
						<td align="center">
						<button id="Submit" type="submit" class="btn btn-success" data-target="#uploadModel" data-toggle="modal" ng-click="setModel(data)" >
						Upload</button>
						</td>
					</tr>
				</tbody>
			 </table>
		</div>
		<div class="modal fade" id="uploadModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<strong>PLEASE UPLOAD DIGITALLY SIGNED AND UPDATED PETITION/ DOCUMENT AS PER HON'BLE COURT'S ORDER</strong>
						</h4>
					</div>
					<%@ include file="../defectRemoveByOrder/upload.jsp"%>
				</div>
			</div>
		</div>		
	</div>
</div>
