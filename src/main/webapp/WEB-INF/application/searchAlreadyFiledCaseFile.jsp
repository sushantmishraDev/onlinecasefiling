<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="searchCaseFileController">
		<div class="row">
			<div class="col-md-12">
				
				<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<!-- <button type="button" class="btn btn-primary btn-sm pull-right"
							ng-click="resetMasterdata()" data-toggle="modal"
							data-target="#casefile_Modal">
							<span class="glyphicon glyphicon-plus-sign"></span> Add New
							Record
						</button> -->
					</div>
					<h4 class="panel-title">Case File Search</h4>
				</div> 
				<div class="panel-body">
					<div>
						<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover" width="100%">
								<thead>
									<tr>
										
										<th style="text-align: center;" width="20%" st-sort="fd_case_type">Case Type</th>
										<th style="text-align: center;" width="20%"  st-sort="fd_case_no">Case Number</th>
										<th style="text-align: center;" width="20%" st-sort="fd_case_year">Case Year</th>
										<th style="text-align: center;" width="20%">Search</th>
									</tr>
									<tr>
										
										
										<th>
											<select ng-options="caseType.ct_id as (caseType.ct_label+'-'+caseType.ct_name+'') for caseType in caseTypeList  | orderBy:'ct_label'"
											class="form-control" id="caseTypeData" 
											name="fd_case_type" ng-model="model.fd_case_type" required>
											<option value="">Select Case Type</option>
											</select>
										</th>
									
										<th>
										 <input  numbers-only id="fd_case_no"  type="text" name="fd_case_no"  class="form-control" ng-pattern="/^[a-zA-Z0-9]*$/"  ng-model="model.fd_case_no" placeholder="Enter Case No." />
										</th>
										<th>
										 <input numbers-only  id="fd_case_year"  type="text" name="fd_case_year"  class="form-control" ng-pattern="/^[a-zA-Z0-9]*$/"  maxlength=4 ng-model="model.fd_case_year" placeholder="Enter Case Year" />
										</th>
														  
 										<th>
										
										<button id="search" type="submit" class="btn btn-primary btn-sm pull-left"
												ng-click="searchAlreadyCaseFile()" data-toggle="modal" style="size: 2px">
										<span class="glyphicon glyphicon-plus-sign"></span>Search</button>
										</th>
									</tr>
				 
								</thead>
								</table>
								</div>
								</div>
								
					      <div class="panel-body">
                            <div class="table-responsive" ng-show="petDoc || draftList.length>0">
 						<input type="hidden" class="form-control" value=${fd_id} id="fd_id" name="fd_id">    
                                <table id="data-table" class="table table-striped table-bordered" >
                                    <thead>
                                        <tr>
                                        	<th>Sr.<br>No</th>
 	                                        <!-- <th>Filed By</th> -->
                                            <th>Document Types</th>
                                            <th>Number</th>
                                            <th>Year</th>
                                            <th>Filed By</th>
                                            <th>Action</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <tr ng-show="petDoc!=null">
                                    <td>1</td>
                                     <!-- <td>Petitioner</td> -->
                                     <td>{{petDoc.caseType.ct_name}} </td>
                                     <td>{{petDoc.rcd_case_no}}</td>
                                     <td>{{petDoc.rcd_case_year}}</td>
                                      <td>{{petDoc.userFiled.um_fullname}}({{petDoc.userFiled.username}})</td>
                                     <td><button  class="btn btn-success" ng-click="showDocument(petDoc)">Preview</button></td>
                                    </tr>
                                     </tbody>
                                </table>
                                <table id="data-table" class="table table-striped table-bordered" >
                                        <tr   ng-repeat="row in draftList" class="odd gradeX">
                                        	<td>{{$index+2}}</td>
                                        	<!--  <td>{{row.ap_filed_by ==1 ?'Petitioner' : 'Respondent'}}</td> -->
                                             <td>{{row.applicationType.at_description}}</td> 
                                             <td>{{row.ap_no}}</td>
                                             <td>{{row.ap_year}}</td>
                                             <td>{{row.userFiled.um_fullname}} ({{row.userFiled.username}})</td>
                                             <td><button  class="btn btn-success" ng-click="showDocuments(row)">Preview</button></td>
                                          </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div ng-hide="divShow">
                           <h2 style="color:red;text-align:center;"> You Are Not Authorized To View The Document </h2>
                            </div>
                        </div>
				</div>
				<!-- end panel -->
			</div>
			<!-- end col-10 -->
						
				</div>
			</div>
		</div>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/searchCaseFile.js?v=1"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>


	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>	
	



</html>

