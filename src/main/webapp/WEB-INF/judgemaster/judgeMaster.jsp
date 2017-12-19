<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="../content/header.jsp"></jsp:include>

<div id="content" class="content" ng-controller="judgeMstrCtrl">
	<!-- begin row -->
	<div class="row">
		<!-- begin col-10 -->
		<div class="col-md-10">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<button type="button" class="btn btn-primary btn-sm pull-right"
							ng-click="resetModel()" data-toggle="modal"
							data-target="#judge_Modal">
							<span class="glyphicon glyphicon-plus-sign"></span> Add New Judge
						</button>
					</div>
					<h4 class="panel-title">Judge</h4>
				</div>
				<div class="panel-body">
					<table id="data-table" st-table="displayedCollection"
						st-safe-src="masterdata"
						class="table table-striped table-bordered nowrap table-hover"
						width="100%">
						<thead>
							<tr>
								<th st-sort="jg_name">Judge Name</th>
								<th st-sort="jg_code">Judge Code</th>
								<th st-sort="jg_type">Judge Type</th>
								<th style="width: 250px;" class="text-center">Action</th>
							</tr>
							<tr>
								<th colspan="6"><input st-search
									placeholder="global search" class="input-sm form-control"
									type="search" /></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="data in displayedCollection" class="odd gradeX">
								<td>{{data.jg_name}}</td>
								<td>{{data.jg_code}}</td>
								<td>{{data.lk_jgtype.lk_longname}}</td>
								<td><a class="btn btn-info btn-xs"
									ng-click="setMasterdata(data)" data-toggle="modal"
									data-target="#judge_Modal"> <span
										class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										Edit
								</a></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6" class="text-center">
									<div st-pagination="" st-items-by-page="10"
										st-displayed-pages="8"></div>
								</td>
							</tr>
						</tfoot>
					</table>

				</div>
			</div>
			<!-- end panel -->
		</div>
		<!-- end col-10 -->
	</div>
	<!-- end row -->

	<div class="modal fade" id="judge_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="background-color: black;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<strong style="color: #FBFCFD;">Judge Management </strong>
					</h4>
				</div>
				<%@ include file="judgeMstrForm.jsp"%>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/judgeMstrController.js"></script>
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