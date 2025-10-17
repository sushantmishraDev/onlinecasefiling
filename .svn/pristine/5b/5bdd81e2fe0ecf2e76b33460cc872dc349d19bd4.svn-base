<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../site/_header.jsp"%>

<!--       menu.jsp for navigation bar -->     
<%@ include file="../site/_menu.jsp"%> 	
<div class="container fill" ng-controller="metaTemplateController">
<div class="row">
<div class="col-md-12 ">
	<button type="button" class="btn btn-primary btn-sm pull-right" ng-click="resetMasterdata()" data-toggle="modal" data-target="#create">
		<span class="glyphicon glyphicon-plus-sign"></span> Create New Meta Template
	</button>
</div>
</div>
<div class="row voffset1">
<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
<thead>
<tr>
<th st-sort="name">Name</th>
<th st-sort="address">Description</th>
<th st-sort="status">Status</th>
<th style=" width: 250px;" class="text-center">Action
</th>					
</tr>
<tr>
<th><input st-search="name" placeholder="search for name" class="input-sm form-control" type="search"/></th>
<th><input st-search="description" placeholder="search for address" class="input-sm form-control" type="search"/></th>
<th><input st-search="status" placeholder="search for status" class="input-sm form-control" type="search"/></th>		
<th></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="data in displayedCollection">
<td>{{data.name}}</td>
<td>{{data.description}}</td>
<td>{{ data.status=='1' ? "Active" : "Inactive" }}</td>
<td>
<a  class="btn btn-info btn-xs"    ng-click="setMasterdata(data,$index)" data-toggle="modal" data-target="#manageField" ><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Manage Fields</a>

<a  class="btn btn-info btn-xs"    ng-click="setMasterdata(data,$index)" data-toggle="modal" data-target="#create" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>
</td>
</tr>
<tr>

<tr/>
</tbody>
<tfoot>
			<tr>
				<td colspan="5" class="text-center">
					<div st-pagination="" st-items-by-page="10"  st-displayed-pages="4" ></div>
				</td>
			</tr>
</tfoot>
</table>

</div>

<div class="modal fade" id="create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel"><strong> New Meta Template</strong></h4>
	      </div>	     
  <%@ include file="../metatemplate/_master_form.jsp"%>
	    </div>
	  </div>
</div>
<div class="modal fade" id="manageField" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">	     	     
  			<%@ include file="../metatemplate/_manage_fields.jsp"%>
	    </div>
	  </div>
</div>

</div>
<%@ include file="../site/_scripts.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/MetaTemplateController.js"></script>
<%@ include file="../site/_footer.jsp"%>
