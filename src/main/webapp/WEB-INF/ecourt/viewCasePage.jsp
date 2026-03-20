<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dms.model.ObjectMaster"%>
<%@ page import="com.dms.model.User"%>
<jsp:include page="../content/header2.jsp"></jsp:include>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/pdfjs-3.4.120/web/viewer.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/pdfjs-3.4.120/web/debugger.css" />

<!-- This snippet is used in production (included from viewer.html) -->
<link rel="resource" type="application/l10n"
	href="${pageContext.request.contextPath}/js/pdfjs-3.4.120/web/locale/locale.properties" />
<script
	src="${pageContext.request.contextPath}/js/pdfjs-3.4.120/build/pdf.js"></script>
<script
	src="${pageContext.request.contextPath}/js/pdfjs-3.4.120/web/debugger.js"></script>
<script
	src="${pageContext.request.contextPath}/js/pdfjs-3.4.120/web/viewer.js?v=3"></script>


<div ng-app="EDMSApp" ng-controller="ApplicationViewController">

	<div class="row">
         
         <div class="col-md-2"></div>
		<div class="col-md-3">
			<div class="panel-group" id="accordion">
				<div class="panel panel-inverse overflow-hidden">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a class="accordion-toggle accordion-toggle-styled"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"> <i class="fa fa-plus-circle pull-right"></i>
								Petition
							</a>
						</h3>

					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body"
							style="padding: 2px; max-height: 450px; overflow: auto;">
							<div class="table-responsive">
								<table id="data-table" st-table="petitions"
									st-safe-src="petitionsData"
									class="table table-striped table-bordered">
									<!--    <table id="data-table" st-table="petitions" st-safe-src="petitionsData" class="table table-striped table-bordered"> -->
									<thead>
										<tr>
											<th style="width: 2%;">Sr.<br>No.
											</th>
											<th style="width: 56%" st-sort="sd_submitted_date">Type</th>
											<th st-sort="sd_description">Name</th>
											<th st-sort="sd_counsel">Counsel</th>
										</tr>

										<tr>
											<!-- <td ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.indexField.if_label}} <br/>  {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                  -->
											<td>{{$index+1}}</td>
											<td style="padding: 10px 5px; width: 35%">
												<div ng-style="data.checked==true?personColour:''"
													style="margin-bottom: 10px">


													<span ng-click="showSubDocument('case_' + Petition)"
														style="text-decoration: underline; cursor: pointer;">
														<b>PETITION <br /></b><br>
													</span>



												</div>
											</td>


										</tr>
									</thead>

								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- ============================== Misc Application============================== -->

				<div class="panel panel-inverse overflow-hidden">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a class="accordion-toggle accordion-toggle-styled"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"> <i
								class="fa fa-plus-circle pull-right"></i> Misc. Applications
							</a>
						</h3>
					</div>
					<div id="collapseThree" class="panel-collapse collapse in">
						<div class="panel-body"
							style="padding: 2px; max-height: 450px; overflow: auto;">
							<div class="table-responsive">
								<table class="table table-bordered">

									<thead>
										<tr>
											<th>Sr No</th>
											<th>Type</th>
											<th>Name</th>
											<th>Counsel</th>
										</tr>
									</thead>

									<tbody>
										<tr ng-repeat="data in List">

											<td>{{$index+1}}</td>

											<td><span ng-click="showSubDocument('appl_' + data.ap_draft_no)"
												style="text-decoration: underline; cursor: pointer;">
													<b>{{data.applicationType.at_name}} <br />

												</b>
											</span></td>

											<td>{{data.ap_applicant_name}}</td>

											<td>{{data.userFiled.um_fullname}}</td>

										</tr>

									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

		<div class="col-md-7">


			<jsp:include page="viewer2.jsp"></jsp:include>


		</div>



	</div>


</div>





</body>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css'>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/ApplicationViewController.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>


<script
	src="${pageContext.request.contextPath}/js/pdfjs-3.4.120/build/pdf.js"></script>
<script
	src="${pageContext.request.contextPath}/js/pdfjs-3.4.120/web/viewer.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>

<style>
.st-sort-ascent:before {
	content: '\25B2';
}

.st-sort-descent:before {
	content: '\25BC';
}
/* 
/* 	     */
.sidenav {
	height: 100%;
	width: 100%;
	position: fixed;
}

textarea {
	height: 100%;
	width: 100%;
	font-size: 28px;
	border-style: none;
	border-color: Transparent;
}

.ui-widget.sidenav-dialog {
	font-family: Verdana, Arial, sans-serif;
	font-size: 1em;
}

.ui-widget-content.sidenav-dialog {
	background: #F9F9F9;
	border: 1px solid #90d93f;
	color: #222222;
}

.ui-dialog.sidenav-dialog {
	left: 0;
	outline: 0 none;
	padding: 0 !important;
	position: absolute;
	top: 0;
}

.ui-dialog.sidenav-dialog .ui-dialog-content {
	background: none repeat scroll 0 0 transparent;
	border: 0 none;
	overflow: auto;
	position: relative;
	padding: 0 !important;
	margin: 0;
}

.ui-dialog.sidenav-dialog .ui-widget-header {
	background: #b0de78;
	border: 0;
	color: #fff;
	font-weight: normal;
}

.ui-dialog.sidenav-dialog .ui-dialog-titlebar {
	padding: 0.1em .5em;
	position: relative;
	font-size: 1em;
	color: #191919 !important;
}
/*Sushant  */
/* ------------------------------ */
</style>

<script>
	$(document).ready(function() {
		App.init();

	});

	$(function() {
		$("#mySidenav").dialog({
		    autoOpen: false, //  IMPORTANT
		    height: 200,
		    width: 350,
		    resizable: true,
		    position: {
		        my: "left top",
		        at: "right bottom"
		    },
		    dialogClass: 'no-close sidenav-dialog'
		});
		/* 
					.parent().draggable({
		             containment: '#content'
		           }) */
		;
		$("#btn_click").click(function() {
			$("#mySidenav").dialog("open");
		});
		$("#mySidenav").on('click', '#closebtn', function() {
			setTimeout(function() {
				$("#mySidenav").dialog("close");
			}, 1000);
		});

	});
	//$(document).bind('keydown', 'ctrl+s', function(e){ alert('save'); return false;});
	/*   $(document).bind('keydown', function(e) {
		  if(e.ctrlKey && (e.which == 83)) {
		    e.preventDefault();
		    alert('Ctrl+S');
		    return false;
		  }
		}); */

	$('#btnPresent').click(function(e) {
		if (getCookie("slide") == "1") {
			$('#myDiv').toggleClass('fullscreenRight');
		} else {
			$('#myDiv').toggleClass('fullscreen');
		}
	});

	$(function() {
		document.getElementById("sidebarContent").hidden = true;
		$("#sidebarToggle").click(function() {
			console.log(document.getElementById("sidebarContent").hidden);
			if (document.getElementById("sidebarContent").hidden == true) {
				document.getElementById("sidebarContent").hidden = false;
				document.getElementById("sidebar").hidden = true;

			} else {
				document.getElementById("sidebarContent").hidden = true;
				document.getElementById("sidebar").hidden = false;

			}

		});

	});
</script>














<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/ApplicationViewController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();

	});
</script>


</html>