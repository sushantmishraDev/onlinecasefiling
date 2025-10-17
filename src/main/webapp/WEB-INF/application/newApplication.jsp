
<div class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<h3></h3>
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">

					
					<%--  <div class="panel-heading font-bold">Case Details : ${caseType} /${caseNo} /${caseYear}</div> --%>
					 <div class="panel-heading font-bold">Application Details</div>
					<div class="panel-body">
						
						<div class="form-group pull-in clearfix">
							<div class="col-sm-6">
								<label>Application Type <span class="text-danger"> * </span></label> <select
									class="form-control" id="ct_id" name="ct_id"
									ng-model="application.ap_at_mid"
									 ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList | orderBy:'at_name'">
									<option value="">Select Application Type</option>
								</select>							
								

							</div>
							

						</div>
						
						<fieldset ng-hide="application.ap_at_mid==9" ng-repeat="subapplication in application.subApplication">
						<div class="form-group pull-in clearfix">
							<div class="col-sm-6">
								<label> With Application </label>
								 <!-- <select
									class="form-control" id="ct_id" name="ct_id"
									ng-model="application.subApplication.sb_ap_at_mid"
									 ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList | orderBy:'at_name'"
									 ng-selected="application.subApplication.sb_ap_at_mid == applicationType.at_id"> -->
									 <select ng-model="subapplication.sb_ap_at_mid">
                                     <option value="">Select Application Type</option>
                                     <option ng-repeat=" applicationType in applicationTypeList | orderBy:'at_name'" 
                                     ng-selected="subapplication.sb_ap_at_mid == applicationType.at_id" ng-value="{{applicationType.at_id}}">{{applicationType.at_name}} </option>
                                     </select>
									<!-- <option value="">Select Application Type</option> -->
									
								</select>
								
								<button class="remove" ng-click="removeColumn($index)">x</button>

							<button class="addfields" ng-click="addNewColumn()">AddColumn</button>

							</div>
							

						</div>
						</fieldset>

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>


<div  ng-show="application.ap_at_mid==9" class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">
					<div class="panel-heading font-bold">Listing Application Details</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
						
							<div class="col-sm-10">
								<label class="control-label">Description <span class="text-danger"> * </span> </label> 
								<textarea id="txtEditor1" style="height: 250px;" name="name"ng-model="application.ap_lstng_desc" required></textarea>
							</div>
							<div class="col-sm-10">
							
								<label>Prayer <span class="text-danger"> * </span></label> 
								
								<textarea id="txtEditor"  style="height: 250px;"  name="name"ng-model="application.ap_lstng_prayer" required></textarea>
								
							</div>
						

						</div>
						

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>

<div  ng-show="application.ap_at_mid==50" class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">
					<div class="panel-heading font-bold">OLR Details</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
						
							<div class="col-sm-4">
								<label class="control-label">OLR Number <span class="text-danger"> * </span> </label> 
								<input type="text" max-length=60 class="form-control" id="caseDetail" name="name"
														ng-model="application.ap_olr_no" required>
							</div>
							<div class="col-sm-3">
							
								<label>OLR Year <span class="text-danger"> * </span></label> 
								
								<input type="text" max-length=60 class="form-control" id="caseDetail" name="name"
														ng-model="application.ap_olr_year" required>
								
							</div>
						

						</div>
						

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>


<div class="smooth container w-xxxl w-auto-xs">
	<form name="leadStage" class="form-validation">
		<fieldset class="scheduler-border">
			<div class="col-sm-10">
				<div class="panel panel-default" style="margin-left: 140px;">
					<div class="panel-heading font-bold">Applicant Details</div>
					<div class="panel-body">
						<div class="form-group pull-in clearfix">
						
							<div class="col-sm-3">
								<label class="control-label">Filled By <span class="text-danger"> * </span> </label> <select
									class="form-control" id="dt_id" name="dt_id"
									ng-model="application.ap_filed_by"
									 ng-options="filledBy.id as filledBy.value for filledBy in filledByOptions">
									<option value="">Select Filled By</option>
								</select>
							</div>
							<div class="col-sm-6">
							
								<label>Applicant Name <span class="text-danger"> * </span></label> 
								
								<input type="text" max-length=60 class="form-control" id="caseDetail" name="name"ng-model="application.ap_applicant_name" required>
								
							</div>
						

						</div>
						

					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>				

<div class="row">
	<div class="col-md-12">

		<div class="col-md-4"></div>
		<div class="col-md-4">
			<button class="btn btn-success" ng-disabled="!application.ap_at_mid || !application.ap_filed_by || !application.ap_applicant_name"
				ng-click="addApplication(application,application.subApplication)">Save Or Update</button>
		</div>


	</div>
</div>

<div class="panel-body no-padder">
  <div class="col-md-12 no-padder">
   <div class="box-div no-padder">
    <div class=" col-md-12 pull-right">
     <div class="table-container">
      <div class="panel-body">
       <div class="table-responsive">  
        <table id="data-table" class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>S.No.</th>
              <th>Application Type</th>
              <th>Date</th>
              <th class="text-center">Action</th>
           </tr>
         </thead>
           <tbody>
           
           <td >1</td>
              <td>{{application.applicationType.at_name}}</td>
              <td>{{application.ap_cr_date |date:"dd/MM/yyyy"}}</td>  
              <td class="text-center">
               <!--  <a  ng-click="editSubApplication(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a> -->
                <a  ng-click="deleteSubApplication(application._ap_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
			  </td>
            </tr>
            
            <tr ng-show="subAppStatus" ng-repeat="row in application.subApplication" class="odd gradeX">
            
              <td >{{$index+2}}</td>
              <td>{{row.applicationType.at_name}}</td>
              <td>{{row.sb_ap_cr_date |date:"dd/MM/yyyy"}}</td>  
              <td class="text-center">
               <!--  <a  ng-click="editSubApplication(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a> -->
                <a  ng-click="deleteSubApplication(row.sb_ap_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
			  </td>
            </tr>
          </tbody>
      </table>
      </div>
     </div>
   </div>
  </div>
 </div>
</div>
            </div>




