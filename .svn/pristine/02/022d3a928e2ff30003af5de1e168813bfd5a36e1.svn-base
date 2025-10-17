
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">      
<div class="modal-body">
<div ng-if="errorlist" class="alert alert-block alert-danger">
		<ul>
			<span ng-repeat="errors in errorlist "> <span
				ng-repeat="n in errors track by $index">
					<li>{{(n)}}</li>
			</span>
			</span>
		</ul>
		</div>
<div class="row">
<div class="form-group">
	<label class="col-md-4  control-label" for="name">Document Type<span class="star">*</span></label>
    <div class="col-md-7">
     		<select class="form-control" id="doc_type" name="doc_type" ng-change="showUploadForm()" ng-model="document.doc_type" required>
				    <option value="">Select Document Type</option>
				    <option value="1">New Document</option>
				    <option value="2">Judgement File</option>
				    <option value="3">Case Reopen</option>
				    <option value="4">Media File</option>
		</select>
	</div>
</div>	
</div>
<div class="row" ng-show="document.doc_type==1">		  			      		
<div class="form-group">
			<label class="col-md-4  control-label" for="name">Repository<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<select class="form-control" id="rep_id" name="rep_id" ng-change="getFolderStructure()" ng-model="document.rep_id"  ng-options="repository.id as repository.name for repository in repositories">
						    <option value="">Select Repository</option>
				</select>
			</div>		   
</div>
</div>
<!-- <div class="row"  ng-show="document.doc_type==2 || document.doc_type==3">							  			      		 -->
<!-- <div class="form-group"> -->
<!-- 			<label class="col-md-4  control-label" for="name">Filter Casefiles<span class="star">*</span></label> -->
<!-- 			<div class="col-md-7"> -->
<!-- 			<div id="treedata"> -->
<!-- 			<treecontrol class="tree-classic" tree-model="treedata" options="opts" on-selection="getCasefiles(node)"> -->
<!-- 			                            {{node.folder_name}} -->
<!-- 			</treecontrol>	 -->
<!-- 			</div> -->
<!-- 			</div> -->
<!-- </div>		    -->
<!-- </div> -->

<!-- <div class="row" ng-show="document.doc_type==2 || document.doc_type==3">							  			      		 -->
<!-- <div class="form-group" > -->
<!-- 			<label class="col-md-4  control-label" for="name">Case Number<span class="star">*</span></label> -->
<!-- 		    <div class="col-md-7"> -->
<!-- 	      		<select class="form-control" id="parent_id" name="parent_id" ng-model="document.parent_id" ng-options="parentdocument.md_fd_mid as parentdocument.md_value for parentdocument in parentdocuments"> -->
<!-- 					<option value="">Select Case Number</option> -->
<!-- 				</select> -->
<!-- 			</div>		    -->
<!-- </div> -->
<!-- </div> -->
<div class="row" ng-show="document.doc_type==2 || document.doc_type==3 || document.doc_type==4">							  			      		
<div class="form-group" >
			<label class="col-md-4 control-label" for="benchCode">Bench Type<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<select class="form-control" id="benchCode" name="benchCode" ng-model="document.benchCode" ng-options="benchCode.lk_id as benchCode.lk_longname for benchCode in benchCodes">
					<option value="">Select Bench Type</option>
				</select>
			</div>		   
</div>

</div>
<div class="row" ng-show="document.doc_type==2 || document.doc_type==3 || document.doc_type==4">							  			      		
<div class="form-group" >
			<label class="col-md-4 control-label" for="caseType">Case Type</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="caseType" name="caseType" ng-model="document.caseType" ng-options="caseType.lk_id as caseType.lk_longname for caseType in caseTypes">
					<option value="">Select Case Type</option>
				</select>
			</div>		   
</div>
</div>
<div class="row" ng-show="document.doc_type==2 || document.doc_type==3 || document.doc_type==4">							  			      		
<div class="form-group" >
			<label class="col-md-4 control-label" for="year">Year</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="year" name="year" ng-model="document.caseYear" ng-options="year as year for year in years">
					<option value="">Select Year</option>
				</select>
			</div>		   
</div>
</div>
<div class="row" ng-show="document.doc_type==2 || document.doc_type==3 || document.doc_type==4">							  			      		
<div class="form-group">
			<label class="col-md-4 control-label" for="caseNo">Case Number<span class="star">*</span></label>
		    <div class="col-md-4">
	      		<input type="text" class="form-control"
						ng-model="document.caseNo" id="caseNo"
						ng-class="{'error' : errorName}" placeholder="Enter Case No."
						name="caseNo" />
			</div>
			<div class="col-md-3">
			<input type="button" value="Search" ng-click="getCasefilesUsingCaseNo()" class="btn btn-primary"/>
			</div>		   
</div>
</div>


<div class="row" ng-show="document.doc_type==2 || document.doc_type==3 || document.doc_type==4">							  			      		
	<label class="col-md-4 control-label" for="caseNo">Select Associated Document<span class="star">*</span></label>
	<div class="col-md-7">
		<table st-table="displayedCollection" id="parentdocuments" class="table table-striped table-bordered table-hover">
			<thead>
			<tr>
			<th st-sort="">Document Name</th>
			<th st-sort="">Action</th> 
			</tr>
			</thead>
			<tbody>
			<tr ng-repeat="data in parentdocuments">
			<td>{{data.document_name}}</td>
			<td><input type="button" id="{{data.id}}" value="Select" ng-click="setParent(data)" class="btn btn-primary"/></td>
			</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="row" ng-show="document.doc_type==2">							  			      		
<div class="form-group" >
			<label class="col-md-4  control-label" for="name">Judgement Date<span class="star">*</span></label>
		    <div class="col-md-7">
			<input title="Date" type="text" style="width: 75%;" class="form-control"   
			ng-pattern='/^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[8-9]|[2-9]\d)?\d{2})$/'
			mask="39/19/9999" restrict="reject" clean="false"
			model-view-value="true" name="judgement_date"
			id="fd_judgement_date" ng-model="document.judgement_date"
			placeholder="DD/MM/YYYY" />		
			</div>		   
</div>
</div>
												
<div class="row" ng-show="document.doc_type==1">							  			      		
<div class="form-group">
			<label class="col-md-4  control-label" for="name">Folder<span class="star">*</span></label>
			<div class="col-md-7">
			<div id="treedata">
			<treecontrol class="tree-classic" tree-model="treedata" options="opts2" on-selection="showSelected(node)">
			                            {{node.folder_name}}
			</treecontrol>	
			</div>
			</div>
</div>		   
</div>

<div class="row">							  			      		
<div class="form-group">
			<label class="col-md-4  control-label" for="file">Files<span class="star">*</span></label>
<!-- 			<input type="file" file-upload multiple/> -->
			<input type="file" multiple="multiple" ngf-select ng-model="picFile" name="file"  >
    <ul>
        <li ng-repeat="file in files">{{file.name}}</li>
    </ul>
 </div>
</div>

</div>
<div class="modal-footer"> 
	<div ng-if="!masterentity.id">
	<input type="submit" value="Submit" ng-disabled="masterForm.$invalid" ng-click="save()"   class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
	</div>
	<div ng-if="masterentity.id">
			<input type="submit" ng-disabled="masterForm.$invalid" value="Update" ng-disabled="masterentity.$invalid" ng-click="update(document)" class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  	
	</div>	     
</div>
</form>
