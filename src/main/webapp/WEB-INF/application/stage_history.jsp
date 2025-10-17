<div class="modal-body">
		<div class="panel-body">
			<div class="table-responsive">
                <table id="data-table" class="table table-striped table-bordered">
	                <thead>
	                      <tr>
	                            <th>Diary No</th>
	                             <th>Stage</th>
	                             <th>Date</th>  
	                             <th>Reporting By</th>                                                                      
	                      </tr>
	                </thead>
	                <tbody>
	                      <tr ng-repeat="row in applicationHistory" class="odd gradeX">
                             <td>{{application.ap_diary_no}}</td>
                             <td>{{row.lkStage.lk_longname}}</td>  
                             <td>{{row.as_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
                             <td ng-if ="row.userMaster.um_id!=110">{{row.userMaster.um_fullname}}</td>
                             <td ng-if ="row.userMaster.um_id==110"></td>
	                      </tr>
	                </tbody>
            	</table>
        	</div>
		</div>
</div>
