<div class="modal-body">
		<div class="panel-body">
			<div class="table-responsive">
                <table id="data-table" class="table table-striped table-bordered">
	                <thead>
	                      <tr>
                            <th>Application name</th>
                            <th>Action</th>                                                                        
	                      </tr>
	                </thead>
	                <tbody>
	                      <tr ng-repeat="row in applicationTypeList | orderBy:'at_name'" class="odd gradeX">
                             <td>{{row.at_name}}</td>
                             <td align="center">
								<button id="Submit" type="submit" class="btn btn-success"
								data-toggle="modal"	ng-click="addAmendment(row.at_id)" >
								Allow</button>
							</td>
	                      </tr>
	                </tbody>
            	</table>
        	</div>
		</div>
</div>
