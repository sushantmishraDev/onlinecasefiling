<div class="panel panel-inverse">

	<div class="panel-body">
		<div class="table-responsive">	
			<table id="data-table" class="table table-striped table-bordered nowrap table-hover" width="100%">
				<thead>
				<tr>
					<th style="text-align: center;">Document Type</th>
					<th style="text-align: center;">Case Type</th>
					<th style="text-align: center;">Case No</th>
					<th style="text-align: center;">Case Year</th>
					<th style="text-align: center;">Status</th>
					<th style="text-align: center;">Date</th>
				</tr>
				</thead>
				<tbody>
					<tr ng-repeat="data in amendmentshistory" class="odd gradeX">
						<td align="center"><span ng-show="data.am_type=='P'">Petition</span><span ng-show="data.am_type=='A'">{{data.applicationType.at_name}}</span></td>
						<td align="center">{{data.lkStatus.lk_longname}}</td>
						<td align="center">{{data.caseFileDetail.caseType.ct_name}}</td>
						<td align="center">{{data.caseFileDetail.fd_case_no}}</td>
						<td align="center">{{data.caseFileDetail.fd_case_year}}</td>
						<td align="center">{{data.am_uploaded_date  | date:"dd/MM/yyyy"}}</td>
					</tr>
				</tbody>
			 </table>
		</div>
	</div>
</div>
