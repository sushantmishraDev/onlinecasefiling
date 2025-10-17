<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../content/header.jsp"%>

<!--       menu.jsp for navigation bar -->     
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/site/site.css'>
<div id="content" class="content" ng-controller="UserMasterCtrl">
	<div class="row">
		<div class="col-md-10">
			 <div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">Manage Users</h4>
		       </div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 ">
							<button type="button" class="btn btn-primary btn-sm pull-right" ng-click="resetModel()" data-toggle="modal" data-target="#user_Modal">
								<span class="glyphicon glyphicon-plus-sign"></span> Create New User
							</button>
						</div>
					</div>
					<div class="row">
						<table st-table="displayedCollection" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								<th st-sort="username">User Name</th>
								<th st-sort="um_pass_validity_date">Password Validity</th>
								<th st-sort="um_account_activation">Account Activation</th>
								<th style=" width: 300px;" class="text-center">Action</th>					
								</tr>
								<tr>
								<th><input st-search="username" placeholder="search for username" class="input-sm form-control" type="search"/></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="data in displayedCollection">
								<td>{{data.username}}</td>
								<td>{{data.um_pass_validity_date | date : 'dd/MM/yyyy'}}</td>
								<td><span ng-if="data.um_account_activation==0">
								Pending for Activation</span>
								</td>
								<td>
								<a  class="btn btn-info btn-xs" ng-click="setMasterdata(data)" data-toggle="modal" data-target="#user_Modal" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>
								<a  class="btn btn-info btn-xs" ng-click="getUserPermissions(data)" data-toggle="modal" data-target="#permission_Modal" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Permission</a>
								<a  class="btn btn-info btn-xs" ng-click="Refresherrorlist()" data-toggle="modal" data-target="#pass_Modal" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Change Password</a>
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
					<div class="modal fade" id="user_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"> <span ng-if="!masterentity.um_id"><strong> Add New User</strong></span>
						        <span ng-if="masterentity.um_id"><Strong>Update User</Strong></span></h4>
						      </div>	     
					  <%@ include file="../user/_master_form.jsp"%>
						    </div>
						  </div>
					</div>
					
					<div class="modal fade" id="permission_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"><strong> Add Permission</strong></h4>
						      </div>	     
					  <%@ include file="../user/_permission_form.jsp"%>
						    </div>
						  </div>
					</div>
					
							<div class="modal fade" id="pass_Modal" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header" style="background-color: black;">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												<strong style="color: #FBFCFD;">Change Password </strong>
											</h4>
										</div>
										<%@ include file="../user/passwordPage.jsp"%>
									</div>
								</div>
							</div>
					
				</div>
			</div>
		</div>
	</div>
</div>  	

<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/UserMasterController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
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
