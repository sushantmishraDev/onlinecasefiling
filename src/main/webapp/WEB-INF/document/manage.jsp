<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../content/header.jsp"%>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/site/site.css'>
<div id="content" class="content" ng-controller="documentController">
	<div class="row">
		<div class="col-md-10 ">
			 <div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">Manage Documents</h4>
		       </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-primary btn-sm pull-right" ng-click="refreshModel()" data-toggle="modal" data-target="#documentCreate">
								<span class="glyphicon glyphicon-plus-sign"></span> Upload New Document
							</button>
						</div>
					</div>
					<div class="row">
						<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th st-sort="document_name">Document Name</th>
						<th st-sort="created">Created</th>
						<th st-sort="updated">Updated</th>
						</tr>
						<tr>
						<th><input st-search="document_name" placeholder="search for Document Name" class="input-sm form-control" type="search"/></th>
						<th><input st-search="created" placeholder="search for created" class="input-sm form-control" type="search"/></th>
						<th><input st-search="updated" placeholder="search for updated" class="input-sm form-control" type="search"/></th>		
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="data in displayedCollection">
						<td>{{data.document_name}}</td>
						<td>{{data.created|date : 'dd/MM/yyyy'}}</td>
						<td>{{data.updated}}</td>
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
					<div class="modal fade" id="documentCreate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"><strong> Upload Document </strong></h4>
						      </div>	     
					  			<%@ include file="../document/_master_form.jsp"%>
						    </div>
						  </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload-shim.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/DocumentController.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>


<script>
// 	$(function() {
// 		$(".btn").click(function() {
// 			$(this).button('loading').delay(1000).queue(function() {
// 				$(this).button('reset');
// 				$(this).dequeue();
// 			});
// 		});
// 	});

// 	$(document).ready(function() {
// 		App.init();

// 	});
</script>
	
</body>
</html>