<div class="smooth container w-xxxl w-auto-xs" >
   <form name="petitioner" class="form-validation">
      <fieldset class="scheduler-border">
         <div class="col-sm-10" style="float:centre">
            <div class="panel panel-default" style="margin-left: 140px;">
               <div class="panel-heading font-bold">Document Upload</div>
               <div class="panel-body">
                 
                  <div class="form-group pull-in clearfix">
                  <div class="col-sm-3">
                    <div>
					 <label class=" control-label" for="file">! Upload File !<span class="star">*</span></label>
                     <input type="file" id="fileCheck" ngf-select ng-model="picFile" name="file"  >
                     <ul>
                        <li ng-repeat="file in files">{{file.name}}</li>
                     </ul>
					</div>
				  </div>
				  
				<!--   <div class="col-sm-6">
                    <div>
						<input type="checkbox" ng-model="registerCase.rcd_sc_st">
                        <label>Application In Petition </label>
					</div>
					
				  </div>
				  <div class="col-md-6">
				  <div class="col-md-6">
						
						<button  class="addfields" ng-click="addNewColumn()" class="form-control col-md-2" />Application In Petition
					</div>
				  
                  </div> -->
                  
                  <div class="col-md-12"></div>
                  <div class="col-md-9">
                   <div class="row pull-right">
                     <input type="submit" value="Save" ng-disabled="uploadedDocumentsList.length>0 || fileButton" ng-click="uploadFile()"   class="btn btn-success"/>
                  </div>
                 
               </div>
            </div>
         </div>
      </fieldset>
   </form>
   
    <div class="form-group pull-in clearfix">
                           <font size="2"
          face="verdana"
          color="red"> 
          * Please upload petition below 75 MB.
        </font>           
        </div> 
   
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
                              <td>{{row.pu_document_name}}</td>
                              <td>{{row.pu_no_of_pages}}</td>
                              <td class="text-center">
                                 <a  ng-click="deleteDocument(row.pu_id)"> <i  style="cursor:pointer; font-size: 16px;" class="fa fa-trash-o"></i> </a>                                                                            
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