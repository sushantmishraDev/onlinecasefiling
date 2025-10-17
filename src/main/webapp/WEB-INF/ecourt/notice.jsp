<div class="row">
	<div class="col-md-6">

		<div class="smooth container w-xxxl w-auto-xs">
			<form class="form-validation">
				<h3></h3>
				<fieldset class="scheduler-border">
					<div class="col-sm-12">

						<div class="panel panel-default" >
					<!-- 	<div class="panel panel-default" style="margin-left: 140px;"> -->
							<div class="panel-heading font-bold">Advance Notice</div>
							<div class="panel-body">

								<!-- <div class="col-sm-4">
									<label class="control-label">District </label> <select
										class="form-control" id="dt_id" name="dt_id"
										ng-model="sessionTrack.snd_district"
										ng-options="district.dt_id as district.dt_name for district in districtList">
										<option value="">Select District</option>
									</select>
								</div> -->
								<div>

									<div class="col-sm-10">
										<div class="panel-body">
											<div class="table-responsive">
												<table id="data-table"
													class="table table-striped table-bordered">
													<thead>
														<tr>
															<!-- <th>Serial</th> -->
															<th>Respondent Name</th>
															<th width="60%">Repondent Council Name</th>
															<!-- <th width="60%" ng-show="searchAor">AOR</th> -->
															<!-- <th width="60%" ng-show="searchInPerson">In-Person</th> -->
															<th width="60%" ng-show="noticeList[$index].allowEdit">Depatment Name</th>
															<th>Mobile</th>
															<th>Email</th>


														</tr>
													</thead>
													<tbody>
														<tr class="odd gradeX" ng-repeat="row in respondentDataList">
														<!-- <tr class="odd gradeX"> -->
															<!-- <td ng-model="">{{$index+1}}</td> -->
															<!-- <td>{{respondentDataList.rt_name}}</td> -->
															<td >{{row.rt_name}}</td>
															<td width="60%"> <select ng-hide="noticeList[$index].allowOther || noticeList[$index].allowInperson"	class="form-control" id="dt_id" name="dt_id" ng-model="noticeList[$index].nt_adv_id" 
															ng-options="cnm.cnm_id as (cnm.cnm_name+'-'+cnm.cnm_department) for cnm in cnmList | filter: filterCNM" 
															ng-change="cnmChange(noticeList[$index].nt_adv_id,$index,row.rt_name)">
																			<option value="">Select Council</option>
									                           </select>
									                               <input type="text" style="width: 350px;" ng-show="noticeList[$index].allowOther" class="check_class" ng-model="noticeList[$index].advName" 
									                           ng-change="findAdvocate(noticeList[$index].advName,$index)" ng-model-options="{debounce:1000}" 
									                           placeholder="enter AOR">
									                           <input ng-show="noticeList[$index].allowInperson" type="text" style="width: 350px;" class="check_class"
									                            ng-model="noticeList[$index].advName" placeholder="enter In-person Name" />
									                           <br/>
									                           <select	class="form-control" id="dt_id" name="dt_id" ng-model="noticeList[$index].nt_dept_mid"
									                           ng-hide="noticeList[$index].allowDepartment"  ng-change="cnmChange(noticeList[$index].nt_dept_mid,$index,row.rt_name)"
															ng-options="ndm.ndm_id as ndm.ndm_department_name for ndm in ndmList " ng-change="cnmChange(noticeList[$index].nt_adv_id,$index,row.rt_name)">
																			<option value="">Select Depatment</option>
									                           </select>
									                           
									                            <input ng-show="noticeList[$index].allowDepartment" type="text" style="width: 350px;" class="check_class"
									                            placeholder="enter Department Name"
									                            ng-model="noticeList[$index].deptName" />
									                           
									                           
									                       </td><!-- 
															<td ng-show="searchInPerson"><input type="text"  class="check_class" ng-model="noticeList[$index].advName" >
									                           <td width="60%"> </td></td> -->
															<td><input id="ntMobile" type="text" class="check_class" style="margin-bottom: 30px;"  
															ng-model="noticeList[$index].nt_res_adv_mobile" ng-disabled="true" >
															<br><input  type="text" class="check_class"   ng-model="noticeList[$index].nt_res_dept_mobile"></td>
															<td><input id="ntEmail"
															type="email" pattern="^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)+$" required
															class="check_class" style="margin-bottom: 30px;"  ng-model="noticeList[$index].nt_res_adv_email" ng-disabled="!noticeList[$index].allowEdit">
															<br><input type="email" class="check_class"   ng-model="noticeList[$index].nt_res_dept_email"
															></td>


														</tr>
													</tbody>
												</table>
												<span id="result"></span>
											</div>
										</div>

										<div class="row pull-right">
											<!-- <div class="col-sm-12">
												<div class="col-sm-6" style="float:right">
												<button class="btn btn-success"
													ng-disabled="!sessionTrack.snd_case_year || !sessionTrack.snd_case_no || !sessionTrack.snd_decision_date"
													ng-click="addSessionTrack()">Save</button>
											</div> -->
											
											<div class="col-sm-12">
												<!-- <div class="col-sm-6" style="float:right"> -->
												<button class="btn btn-success"
													ng-click="addAdvanceNotice()">Save</button>
											</div>

										</div>



									</div>
								</div>
							</div>
				</fieldset>
			</form>
		</div>
	</div>
</div>


<!-- <div class="row">
	begin col-12
	<div class="col-md-12">
		begin panel
		<div class="panel panel-inverse">
			<div class="panel-heading">
				<div class="panel-heading-btn">
					<a href="javascript:;"
						class="btn btn-xs btn-icon btn-circle btn-default"
						data-click="panel-expand"><i class="fa fa-expand"></i></a> <a
						href="javascript:;"
						class="btn btn-xs btn-icon btn-circle btn-warning"
						data-click="panel-collapse"><i class="fa fa-minus"></i></a>
				</div>
				<h4 class="panel-title">Session Track Details</h4>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table id="data-table" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Sub Court Name</th>
								<th>CNR No.</th>
								<th>Case Type</th>
								<th>Judge Name</th>
								<th>Case No</th>
								<th>Case Year</th>
								<th>Date of Decision</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="row in sessionTrackDataList" class="odd gradeX">
								<td><span ng-if="row.snd_hc_case_type!=null">High
										Court</span><span ng-if="row.snd_lc_case_type!=null">{{row.courtType.lct_name}}</span></td>
								<td>{{row.snd_cnr_no}}</td>
								<td><span ng-if="row.snd_hc_case_type!=null">{{row.hcCaseType.ct_name}}</span><span
									ng-if="row.snd_lc_case_type!=null">{{row.lcCaseType.ct_name}}</span></td>
								<td>{{row.snd_judge_name}}</td>
								<td>{{row.snd_case_no}}</td>
								<td>{{row.snd_case_year}}</td>
								<td>{{row.snd_decision_date | date:"dd/MM/yyyy"}}</td>
								<td class="text-center"><a ng-click="editSessionTrack(row)">
										<i style="cursor: pointer; font-size: 16px;"
										class="fa fa-pencil-square-o"></i>
								</a> <a ng-click="deleteSessionTrack(row.snd_id)"> <i
										style="cursor: pointer; font-size: 16px;"
										class="fa fa-trash-o"></i>
								</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		end panel
	</div>
	end col-12
</div> -->
