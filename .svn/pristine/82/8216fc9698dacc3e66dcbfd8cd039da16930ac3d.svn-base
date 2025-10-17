<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../content/header.jsp"%>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/site/site.css'>

<div id="content" class="content" ng-controller="documentlistController"> 	
<div class="row">
	<div class="col-md-10 ">
	 <div class="panel panel-inverse">
       <div class="panel-heading">
          <h4 class="panel-title">Document Search</h4>
       </div>
		<div class="panel-body">
			<div class="fill">
<form class="form-horizontal reduce-gap ng-pristine ng-invalid ng-invalid-required" role="form" novalidate="" name="masterForm">
<div class="row">
<div class="col-md-12">
							  			      		
 <div class="form-group">
		<label class="col-md-4  control-label" for="name">Repository<span class="star">*</span></label>
	    <div class="col-md-7">
      		<select class="form-control" id="rep_id" name="rep_id" ng-change="getFolderStructure()" ng-model="document.rep_id" required ng-options="repository.id as repository.name for repository in repositories">
					    <option value="">Select Repository</option>
			</select>
		</div>		   
</div>

<div class="form-group">
			<label class="col-md-4  control-label" for="name">Folder<span class="star">*</span></label>
			<div class="col-md-7">
			<div id="treedata">
			<treecontrol class="tree-classic" tree-model="treedata" options="opts" on-selection="showSelected(node)">
			                            {{node.folder_name}}
			</treecontrol>	
			</div>
			</div>
</div>	  
<div class="form-group" >
			<label class="col-md-4 control-label" for="benchCodeId">Bench Type<span class="star">*</span></label>
		    <div class="col-md-7">
	      		<select class="form-control" id="benchCodeId" name="benchCodeId" ng-model="document.benchCodeId" ng-options="benchCode.lk_id as benchCode.lk_longname for benchCode in benchCodes">
					<option value="">Select Bench Type</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="caseTypeId">Case Type</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="caseTypeId" name="caseTypeId" ng-model="document.caseTypeId" ng-options="caseType.lk_id as caseType.lk_longname for caseType in caseTypes">
					<option value="">Select Case Type</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="judgeId">Judge Name</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="judgeId" name="judgeId" ng-model="document.judgeId" ng-options="judge.jg_id as judge.jg_name for judge in judges">
					<option value="">Select Judge</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="year">Year</label>
		    <div class="col-md-7">
	      		<select class="form-control" id="year" name="year" ng-model="document.year" ng-options="year as year for year in years">
					<option value="">Select Year</option>
				</select>
			</div>		   
</div>
<div class="form-group" >
			<label class="col-md-4 control-label" for="year">Case Number</label>
		    <div class="col-md-7">
	      		<input type="text" class="form-control" id="caseNumber" name="caseNumber" ng-model="document.caseNumber"/>
					
			</div>		   
</div>
<div class="form-group">
		
	    <div class="col-md-7 col-md-offset-4">
      		<input type="submit" class="btn btn-primary" ng-click="getSearchData(document)" value="Search">
		</div>		   
</div>

</div>


</div>

</form>


<div class="row">
<table id="searchformdisplay" class="table table-striped table-bordered table-hover">
	<tr>

		<th>Document Name</th>
		
		<th>Action</th>
		
	</tr>

	<tr ng-repeat="data in documentlist">
	<td>{{data.document_name}}</td>
	
	<td>
	<button type="button" class="btn btn-primary btn-sm" ng-click="getsubdocuments(data,2)" data-toggle="modal" data-target="#subDocument">
			<span class="glyphicon"></span> Judgement Files
	</button>
	<a target="_new" href="${pageContext.request.contextPath}/casefile/dataentry/{{data.id}}" >
		<button type="button" class="btn btn-primary btn-sm"><span class="glyphicon"></span> View</button>
	</a>
		
	</td>
	</tr>
</table>
</div>
<div class="modal fade" id="subDocument" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"><strong> Judgement File List</strong></h4>
						      </div>	     
					  			<%@ include file="../document/_judgementfiles_list.jsp"%>
						    </div>
						  </div>
					</div>
    </div>
		</div>
	</div>
	</div>
</div>



</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/DocumentlistController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>

		
		<script>
	$(function() {
		$(".btn").click(function() {
			$(this).button('loading').delay(1000).queue(function() {
				$(this).button('reset');
				$(this).dequeue();
			});
		});
	});

	$(document).ready(function() {
		App.init();

	});
</script>
	
</body>
</html>

