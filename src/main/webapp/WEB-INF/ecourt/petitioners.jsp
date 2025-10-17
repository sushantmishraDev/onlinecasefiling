<div class="smooth container w-xxxl w-auto-xs">
   <form name="petitioner1" class="form-validation">
      <h3></h3>
      <fieldset class="scheduler-border">
         <div class="col-sm-10" style="float: centre">
            <div class="panel panel-default" style="margin-left: 140px;">
               <div class="panel-heading font-bold">Petitioner</div>
               <div class="panel-body">
               
                  <div class="form-group pull-in clearfix">
                           <div class="col-sm-6">
                                <div ng-init="civil=1">
                                    <input type="radio" id="civil" data-ng-click="changeCase($event)" name="caseType" ng-model="registerCase.rcd_case_type" value="civil">
                                    <label>Individual&emsp;
                                        <input type="radio"  id="criminal" data-ng-click="changeCase($event)" 
														ng-model="registerCase.rcd_case_type" value='criminal' name="caseType" >
														Organization
                                    </label>
                                </div>
                            </div>

                        </div>
               
                  <div class="form-group pull-in clearfix">
                     <div class="col-sm-4"></div>
                  </div>
                  <div class="form-group pull-in clearfix">
                  <div class="col-sm-4">
                        <label>Salutation</label>
                        <select class="form-control" id="pt_title" name="pt_title" ng-model="petitionerDetails.pt_title" ng-options="item for item in title">
                           <option value="">Select Title</option>
                        </select>
                     </div>
                     <div class="col-sm-4">
                        <!-- {{petitionerDetails.pt_sequence}}
                           <div>
                                                    <input type="checkbox" ng-model="petitionerDetails.pt_sequence" name="pt_seq" ng-true-value="1" ng-false-value="0"><label>First Petitioner <span class="text-danger"> * </span></label> 
                             </div>
                              -->
                        <label>Name <span class="text-danger"> * </span></label>
                        <!-- <input type="text" maxlength=60 ng-disabled="ptDisable ||(petitionerDetails.pt_name==registerCase.ptName) ||ptAdvDisable" class="form-control" id="caseDetail" name="name" ng-model="petitionerDetails.pt_name" required>
                     -->
                    <input type="text" maxlength=100  class="form-control" id="caseDetail"  oninput="this.value = this.value.toUpperCase()"
                     name="name" ng-disabled="ptDisable" ng-model="petitionerDetails.pt_name" required>
                    
                     </div>
                     <div class="col-sm-4">
                        <label class="control-label">Address <span class="text-danger"> * </span> </label>
                        <textarea class="form-control" rows="4" col="50" maxlength=100 ng-model="petitionerDetails.pt_address" name="address" placeholder="Address Line 1">
                        </textarea>
                        <span style="color: red">
                             <span class="error" ng-show="petitionerDetails.pt_address.length < 11"><br> Please enter atleast 10 character</span>
                             <span class="error" ng-show="petitionerDetails.pt_address.length > 91"><br> Please enter not more than 100 character</span>
                        </span>
                     </div>
                  </div>
                  <div class="form-group pull-in clearfix">
                  
                     <div class="col-sm-4">
                        <label>Petitioner's Age <span class="text-danger"> * </span></label>
                        <input numbers-only type="text" class="form-control" id="age" name="age" maxlength=3  minlength=1 ng-model="petitionerDetails.pt_age" /> 
                        <span style="color: red">
                             <span class="error" ng-show="petitioner1.age.$error.maxlength"><br> age  not more than three digits</span>
                            <!--  <span class="error" ng-show="petitioner1.age.$error.minlength"><br> age not less than one digits</span> -->
                             <span class="error" ng-show="petitionerDetails.pt_age==0"><br> not start with 0</span>
                        </span>
                        {{petitionerDetails.pt_age}}
                     </div>
                  <div class="col-sm-4">
                        <label>Parantage</label>
                        <select class="form-control" id="pt_parantage"  oninput="this.value = this.value.toUpperCase()"
                         name="pt_parantage" ng-model="petitionerDetails.pt_parantage" ng-options="item for item in parantage">
                           <option value="">Select Title</option>
                        </select>
                     </div>
                     <div class="col-sm-4">
                        <!-- {{petitionerDetails.pt_sequence}}
                           <div>
                                                    <input type="checkbox" ng-model="petitionerDetails.pt_sequence" name="pt_seq" ng-true-value="1" ng-false-value="0"><label>First Petitioner <span class="text-danger"> * </span></label> 
                             </div>
                              -->
                        <label>Parantage Name </label>
                        <input type="text" maxlength=60  class="form-control" id="caseDetail" name="name" ng-model="petitionerDetails.pt_parantage_name" required>
                     </div>
                  </div>
                  <div class="form-group pull-in clearfix">
                     <div class="col-sm-6">
                        <label>Email Id</label>
                        <input type="email" class="form-control" maxlength=50 id="caseDetail" name="email"  ng-model="petitionerDetails.pt_email"> 
                        <span style="color: red" ng-show="petitioner1.email.$dirty && petitioner1.email.$invalid">
                        <span ng-show="petitioner1.email.$error.email">Invalid email address.</span>
                        </span>
                     </div>
                     <div class="col-sm-6">
                        <label>District</label>
                      <!--   <input type="text" maxlength=30 class="form-control" id="caseDetail" name="caseDetail" ng-model="petitionerDetails.pt_city"> -->
                        
                         <select class="form-control" id="dt_id" name="dt_id" ng-model="petitionerDetails.pt_city" 
                         ng-options="district.dt_name as district.dt_name for district in districtList">
                                    <option value="">Select District</option>
                                </select>
                     </div>
                  </div>
                  <div class="form-group pull-in clearfix">
                  <div class="col-sm-4">
                        <label>Aadhaar No</label>
                        <input numbers-only type="text" class="form-control" id="adhar" name="adhar" maxlength=12 minlength=12 ng-model="petitionerDetails.pt_adhar" /> 
                        <span style="color: red">
                             <span class="error" ng-show="petitioner1.adhar.$error.maxlength"><br> adhar number not more than twelve digits</span>
                             <span class="error" ng-show="petitioner1.pt_adhar.$error.minlength"><br> adhar number not less than twelve digits</span>
                            <span class="error"ng-show="petitioner1.pt_adhar.$error.pattern"><br>Enter the valid Mobile Number</span>
                        </span>
                     </div>
                     <div class="col-sm-4">
                        <label>Mobile No</label>
                        <input numbers-only type="text" class="form-control" id="mobile" name="mobile" maxlength=10  minlength=10 ng-model="petitionerDetails.pt_mobile" /> 
                        <span style="color: red">
                             <span class="error" ng-show="petitionerDetails.mobile.$error.maxlength"><br> mobile number not more than ten digits</span>
                              <span class="error" ng-show="petitionerDetails.mobile.$error.minlength"><br> mobile number not less than ten digits</span>
                            <span class="error"ng-show="petitionerDetails.mobile.$error.pattern"><br>Enter the valid Mobile Number</span>
                        </span>
                     </div>
                     <div class="col-sm-4">
                        <label>State</label>
                        <select class="form-control" id="s_id" name="s_id" ng-model="petitionerDetails.pt_s_id" ng-options="state.s_id as state.s_name for state in stateList">
                           <option value="">Select State</option>
                        </select>
                     </div>
                  </div>
                  <div class="form-group pull-in clearfix">
                     <div class="col-sm-6">
                        <label>Other Contact No's</label>
                        <input class="form-control" maxlength=50 id="caseDetail" ng-model="petitionerDetails.pt_other_contact" type="text" />
                     </div>
                     <div class="col-sm-6">
                        <label>Pincode</label>
                        <input numbers-only class="form-control" name="pincode" maxlength=6 id="pincode" ng-model="petitionerDetails.pt_pincode" type="text"> 
                        <span style="color: red">
                           <!--  <span class="error" ng-show="petitioner.pincode.$error.maxlength"><br>Pincode not more than six digits</span>
                              <span class="error" ng-show="petitioner.pincode.$error.pattern"><br>Enter the valid Mobile Number</span> -->
                        </span>
                     </div>
                  </div>
               </div>
               <div class="row pull-right">
                  <div class="col-sm-12">
                     <button class="btn btn-success" ng-disabled="petitionerDetails.pt_address.length<10  || !petitionerDetails.pt_address || !petitionerDetails.pt_name || !petitionerDetails.pt_age" ng-click="petitioner.$valid && addPetitioner(petitionerDetails)">AddPetitioner</button>
                  </div>
               </div>
            </div>
         </div>
      </fieldset>
   </form>
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
                              <th style="width: 10%">Petitioner Seq</th>
                              <th>Name</th>
                              <th>Email Id</th>
                              <th>Address</th>
                              <th>Mobile No.</th>
                              <th class="text-center">Action</th>
                           </tr>
                        </thead>
                        <tbody>
                           <tr ng-repeat="row in petitionerDataList" class="odd gradeX">
                              <td>{{row.pt_sequence}}</td>
                              <td>{{row.pt_name}}</td>
                              <td>{{row.pt_email}}</td>
                              <td>{{row.pt_address}}</td>
                              <td>{{row.pt_mobile}}</td>
                              <td class="text-center">
                                 <a ng-click="editPet(row,$index)">
                                 <i style="cursor: pointer; font-size: 16px;" class="fa fa-pencil-square-o"></i>
                                 </a>
                                 <span ng-show=row.pt_sequence!=1>
                                 <a ng-click="deletePetitioner(row.pt_id)"> 
                                 <i style="cursor: pointer; font-size: 16px;" class="fa fa-trash-o"></i>
                                 </a> 
                                 </span>
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