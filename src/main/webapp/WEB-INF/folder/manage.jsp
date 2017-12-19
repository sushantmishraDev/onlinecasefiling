<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../content/header.jsp"%>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/site/site.css'>
<div id="content" class="content" ng-controller="folderController">
	<div class="row">
		<div class="col-md-10 ">
			 <div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">Manage Folders</h4>
		       </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 ">
							<button type="button" class="btn btn-primary btn-sm pull-right" ng-click="getBenchCodes()" data-toggle="modal" data-target="#folderCreate">
								<span class="glyphicon glyphicon-plus-sign"></span> Create New Folder
							</button>
						</div>
					</div>
					<div class="row">
						<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th st-sort="display_name">Display Name</th>
									<th st-sort="folder_name">Folder Name</th>
									<th st-sort="description">Description</th>
									<th st-sort="status">Status</th>
									<th style=" width: 53px;" class="text-center">Action</th>					
								</tr>
								<tr>
									<th><input st-search="name" placeholder="search for folder_name" class="input-sm form-control" type="search"/></th>
									<th><input st-search="description" placeholder="search for address" class="input-sm form-control" type="search"/></th>
									<th><input st-search="status" placeholder="search for status" class="input-sm form-control" type="search"/></th>		
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="data in displayedCollection">
									<td>{{data.display_name}}</td>
									<td>{{data.folder_name}}</td>
									<td>{{data.description}}</td>
									<td>{{ data.status=='1' ? "Active" : "Inactive" }}</td>
									<td>
									<a class="btn btn-info btn-xs" ng-click="setMasterdata(data)" data-toggle="modal" data-target="#folderEdit" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>
									</td>
								</tr>
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
					<div class="modal fade" id="folderCreate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-lg">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel"><strong> Add New Folder</strong></h4>
					      </div>	     
				  			<%@ include file="../folder/_master_form.jsp"%>
					    </div>
					  </div>
					</div>
					<div class="modal fade" id="folderEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					  <div class="modal-dialog modal-lg">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel"><strong>Update Folder</strong></h4>
					      </div>	     
				  			<%@ include file="../folder/_update_form.jsp"%>
					    </div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/FolderController.js"></script>
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