	<div class="smooth container w-xxxl w-auto-xs">
    <form name="leadStage" class="form-validation">
        <h3></h3>
        <fieldset class="scheduler-border">
            <div class="col-sm-10">
                <div class="panel panel-default" style="margin-left: 140px;">

                    <div class="panel-heading font-bold">Case Details</div>
                    <div class="panel-body">
                        <div class="form-group pull-in clearfix">
                           <div class="col-sm-3">
                                <div ng-init="civil=1">
                                    <input type="radio" id="civil" data-ng-click="changeCase($event)" name="caseType" ng-model="registerCase.rcd_case_type" value="civil">
                                    <label>Civil&emsp;
                                        <input type="radio"  id="criminal" data-ng-click="changeCase($event)" 
														ng-model="registerCase.rcd_case_type" value='criminal' name="caseType" >
														Criminal
                                    </label>
                                </div>
                            </div>

                        </div>
                        <div class="form-group pull-in clearfix">
                            <div class="col-sm-6">
                                <label>Case Type <span class="text-danger"> * </span></label>
                                <select class="form-control" id="ct_id" name="ct_id" ng-model="registerCase.rcd_ct_id" ng-options="caseType.ct_id as (caseType.ct_label+'-'+caseType.ct_name+'') for caseType in caseTypeList  | orderBy:'ct_label'">
                                    <option value="">Select Case Type</option>
                                </select>

                            </div>
                            <div class="col-sm-3">
                                <label class="control-label">District <span class="text-danger"> * </span>
                                </label>
                                <select class="form-control" id="dt_id" name="dt_id" ng-change="defaultDist()" ng-model="registerCase.rcd_dt_id" ng-options="district.dt_id as district.dt_name for district in districtList">
                                    <option value="">Select District</option>
                                </select>

                                <!-- <select class="form-control" id="district"
														name="distrcit" ng-model="registerCase.rcd_dt_id"
														   ng-options="district.dt_name for district in districtList track by district.id">
													</select> -->

                            </div>
                            
                            <div class="col-sm-3">
                                                    <label class="control-label">Notice No</label>
                                                    <input type="text" id="notice"  ng-model="registerCase.rcd_notice_no"/>
                                                    <button  ng-hide="verifiedNotice || !registerCase.rcd_notice_no" class="btn btn-success btn-sm" ng-click="verifyNotice()">Validate</button>
                                                </div>
                                               <!--  <p><b>Please enter in year/No format</b></p> -->

                        </div>
                        
                        <!-- <div class="col-sm-6" ng-show="registerCase.rcd_ct_id==42">
                                <div ng-init="civil=1">
                                    <input type="radio" id="civil" name="caseType" ng-model="registerCase.rcd_allow_cd" value="true">
                                    <label>Against FIR&emsp;
                                        <input type="radio"  id="criminal" 
														ng-model="registerCase.rcd_allow_cd" value='false' name="caseType" >
														Case/Complaint
                                    </label>
                                </div>
                            </div> -->

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
                    <div class="panel-heading font-bold">Special Category(first petitioner only)</div>
                    <div class="panel-body">
                        <div class="form-group pull-in clearfix">
                            <div class="col-sm-3">
                                <input type="checkbox" ng-model="registerCase.rcd_sen_ctz">
                                <label>Senior citizen >65</label>

                            </div>

                            <div class="col-sm-3">
                                <input type="checkbox" ng-model="registerCase.rcd_sc_st">
                                <label>SC/ST</label>
                            </div>

                            <div class="col-sm-3">
                                <input type="checkbox" ng-model="registerCase.rcd_wm_ch">
                                <label>Woman/Child </label>
                            </div>

                        </div>
                        <div class="form-group pull-in clearfix">
                            <div class="col-sm-3">
                                <input type="checkbox" ng-model="registerCase.rcd_dvg">
                                <label>Divyang </label>

                            </div>

                            <div class="col-sm-3">
                                <input type="checkbox" ng-model="registerCase.rcd_lg_ad">
                                <label>Legal Aid Case</label>
                            </div>

                            <div class="col-sm-3">
                                <input type="checkbox" ng-model="registerCase.rcd_cust">
                                <label>In Custody</label>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<div class="row" ng-show="addShow">
    <div class="col-md-12">

        <div class="col-md-4"></div>
        <div class="col-md-4">
            <button class="btn btn-success" ng-disabled="!registerCase.rcd_case_type || !registerCase.rcd_ct_id || !registerCase.rcd_dt_id || (!verifiedNotice && registerCase.rcd_notice_no) " ng-click="addRegisterCase(registerCase)">Save</button>
            <%-- <a
				ng-href="${pageContext.request.contextPath}/ecourt_add_case/downloadExcel/{{registerCase.rcd_id}}/">
				<button class="btn btn-success" ng-click="downloadExcel()">Download
					Excel</button>
			</a> --%>
        </div>

        <!--  RAMENDRA.... Start-->

        <!--RAMENDRA.... End -->

        <%-- 	<div class="col-md-2">
			<div class="col-sm-6">
			<a ng-href="${pageContext.request.contextPath}/ecourt_add_case/downloadExcel/{{registerCase.rcd_id}}/">DownloadExcel</a>
			</div> 
			 <label class=" control-label" for="file">Upload File<span class="star">*</span></label> 

				<input type="file"  ngf-select ng-model="picFile" name="file">
				<li ng-repeat="file in files">{{file.name}}</li><br> 
					<input type="submit" value="Upload Excel"  ng-click="uploadFileExcel()"   class="btn btn-success"/>

			<!-- <button  type="button" class="btn btn-success btn-sm"data-toggle="modal" ng-click="setModel(registerCase.rcd_id)"
					data-target="#uploadDocument">Upload
				</button> -->
		</div> --%>

            <!-- <div class="col-md-4">
			<div class="col-sm-6" style="float:right">
			<button class="btn btn-primary" ng-click="nextTab($event)">Next</button>
		</div> -->

    </div>
</div>

