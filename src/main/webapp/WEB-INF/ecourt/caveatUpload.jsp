
<div class="smooth container w-xxxl w-auto-xs" >
						<form name="petitioner" class="form-validation">
							<fieldset class="scheduler-border">
								<div class="col-sm-10" style="float:centre">
									<div class="panel panel-default" style="margin-left: 140px;">
										<div class="panel-heading font-bold">Document Upload</div>
										<div class="panel-body">
											<div class="form-group pull-in clearfix">
												
												<div class="col-sm-4"></div>

											</div>
											
											<div class="form-group pull-in clearfix">
												
											<label class=" control-label" for="file">Upload File<span class="star">*</span></label>

										<input type="file"  ngf-select ng-model="picFile" name="file"  >
									 		   <ul>
									  		      <li ng-repeat="file in files">{{file.name}}</li>
									 		   </ul>
	

											</div>

											<div class="form-group pull-in clearfix">
												
								<input type="submit" value="Save" ng-disabled="uploadedDocumentsList.length>0" ng-click="uploadFile()"   class="btn btn-success"/>
											</div>


											<div class="form-group pull-in clearfix">
												
											</div>
											
											
                                                </div>
		

											
										</div>
									</div>
									</fieldset>
									</form>
								</div>
							
		                			
		
		
		
		
					<div class=" panel panel-default noborder">
                  <div class="panel-body no-padder">
                        <div class="col-md-12 no-padder">
                              <div class="box-div no-padder">
                                    <div class=" col-md-12 pull-right">
                                          <div class="table-container">
                                                <div class="row">
                                                     
                                                      <table st-table="petitionerDataList" class="table">
                                                            <thead
                                                                  style="font-size: 14px; font-weight: normal; text-transform: capitalize;">
                                                                  <tr>
                                                                        <th>Document Name</th>
                                                                           
                                                                           <th>Pages</th>
                                                                           <th class="text-center">Action</th>
                                                                  </tr>
                                                            </thead>
                                                            <tbody>
                                                                  <tr ng-repeat="row in uploadedDocumentsList" class="trScroll">
                                                                                        <td>{{row.cd_document_name}}</td>
                                                                                       
                                                                                        <td>{{row.cd_no_of_pages}}</td>   
                                                                                         <td class="text-center">
                                                                          <!-- <a  ng-click="editRow(row)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-pencil-square-o" ></i></a> -->
                                                                          <a  ng-click="deleteDocument(row.cd_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>
                                                                            
                                                                             
                                                                             
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

					
					
					
					
					
