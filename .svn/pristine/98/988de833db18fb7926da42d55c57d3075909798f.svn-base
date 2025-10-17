<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../content/header.jsp"%>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/site/site.css'>
<script src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<div id="content" class="content" ng-controller="searchController"> 	
<div class="row">
	<div class="col-md-10 ">
	 <div class="panel panel-inverse">
       <div class="panel-heading">
          <h4 class="panel-title">Basic Search</h4>
       </div>
		<div class="panel-body">
			<div class="fill">
<form class="form-horizontal reduce-gap ng-pristine ng-invalid ng-invalid-required" role="form" novalidate="" name="masterForm">
<div class="row">
<div class="col-md-6">
							  			      		
 <div class="form-group">
		<label class="col-md-4  control-label" for="name">Repository<span class="star">*</span></label>
	    <div class="col-md-7">
      		<select class="form-control" id="rep_id" name="rep_id" ng-change="getFolderStructure()" ng-model="folder.rep_id" required ng-options="repository.id as repository.name for repository in repositories">
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

</div>


<div class="col-md-6" id="advanced_search">
<div class="col-md-12" ng-if="error">			
		<div class="alert alert-danger alert-dismissible" role="alert" >
		<div class="row text-center"> 
			{{error}}
		</div>
		</div>
</div>
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#basic">Basic Search</a></li>
</ul>
<div class="tab-content">
  <div id="basic" class="tab-pane fade in active">
    <%@ include file="../document/_basicsearch.jsp"%>
  </div>
  </div>
</div>
</div>

</form>


<div class="row">
<table id="searchformdisplay" class="table table-striped table-bordered table-hover">
	<tr>

		<th>Case No.</th>
		<th>Case Year</th>
		<th>First Petitioner</th>
		<th>First Respondent</th>
		<th>Case Type</th>
		<th>Judgement Date</th>
		<th>Action</th>
		
	</tr>

	<tr ng-repeat="data in documentlist">
	<td>{{data.fd_case_no}}</td>
	<td>{{data.fd_case_year}}</td>
	<td>{{data.fd_first_petitioner}}</td>
	<td>{{data.fd_first_respondent}}</td>
	<td>{{data.caseType.lk_longname}}</td>
	<td>{{data.fd_judgement_date| date:'dd/MM/yyyy'}}</td>
	
	
	<td>
		<a target="_new" href="${pageContext.request.contextPath}/casefile/dataentry/{{data.fd_id}}" class="btn btn-info btn-xs" ><span class="glyphicon" aria-hidden="true"></span>View</a>
	</td>
	</tr>
	
	<tr ng-repeat="data in displayedCollection">
	<td>{{data.caseNo}}</td>
	 <td>{{data.caseYear}}</td>
	<td>{{data.firstPetitioner}}</td>
	<td>{{data.firstRespondent}}</td>
	<td>{{data.caseType}}</td>
	<td>{{data.judgementDate | date:'dd/MM/yyyy'}}</td>		
	<td>
		<a target="_new" href="${pageContext.request.contextPath}/casefile/dataentry/{{data.fileId}}" class="btn btn-info btn-xs" ><span class="glyphicon" aria-hidden="true"></span>View</a>
	</td>
	</tr>
</table>
</div>

    
    <div class="modal fade" id="queriesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>	     
  			<%@ include file="../document/_searchquiries.jsp"%>
	    </div>
	  </div>
	</div>

</div>
		</div>
	</div>
	</div>
</div>



</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/SearchController.js"></script>
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

