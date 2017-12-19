<div class="panel panel-inverse">

	<div class="panel-body">
		<div class="table-responsive">	
			<table id="data-table" class="table table-striped table-bordered nowrap table-hover" width="100%">
				<thead>
				<tr>
					<th style="text-align: center;">Type</th>
					<th style="text-align: center;">Application Name</th>
					<th style="text-align: center;">User name</th>
					<th style="text-align: center;">Full name</th>
					<th style="text-align: center;">Status</th>
					<th style="text-align: center;">Date</th>
				</tr>
				</thead>
				<tbody>
					<tr ng-repeat="data in amendmentshistory" class="odd gradeX">
						<td align="center"><span ng-show="data.am_type=='P'">Petition</span><span ng-show="data.am_type=='A'">Application</span></td>
						<td align="center"><span>{{data.applicationType.at_name}}</span></td>
						<td align="center">{{data.userMaster.username}}</td>
						<td align="center">{{data.userMaster.um_fullname}}</td>
						<td align="center">{{data.lkStatus.lk_longname}}</td>
						<td align="center">{{data.am_uploaded_date  | date:"dd/MM/yyyy"}}</td>
					</tr>
				</tbody>
			 </table>
		</div>
	</div>
</div>
