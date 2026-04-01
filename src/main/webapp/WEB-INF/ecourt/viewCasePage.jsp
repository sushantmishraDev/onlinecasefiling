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


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/ApplicationViewController.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>

<style>
.no-padding {
	padding-left: 0 !important;
	padding-right: 0 !important;
}

/* ------------------------------ */
</style>



<script>
	$(document).ready(function() {
		var a = 10006;
		$(document.body).on('mousedown', '.modal-header', function() {
			$(".modal-content").resizable().find('.close').click(function(e) {
				$(this).parent().parent().remove();
			});
		});

	});
	//,stack: ".myform div"
</script>


<!--========================================== VIJAY CHAURASIYA  =========================== -->

<div ng-app="EDMSApp" ng-controller="ApplicationViewController">

	<div class="row">

		<div class="col-md-2 no-padding"">
			<!-- PDF Bookmarks will appear here -->
			<div id="" style="margin-top: 10px;"></div>
		</div>

		<div class="col-md-3 no-padding"">
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

				<div ng-show="List.length > 0"
					class="panel panel-inverse overflow-hidden">

					<div class="panel-heading" style="padding: 6px 10px;">
						<h3 class="panel-title" style="font-size: 14px; margin: 0;">
							<a class="accordion-toggle accordion-toggle-styled"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"> <i
								class="fa fa-plus-circle pull-right"></i> Misc. Applications
							</a>
						</h3>
					</div>

					<div id="collapseThree" class="panel-collapse collapse in">
						<div class="panel-body"
							style="padding: 2px; max-height: 450px; overflow: auto; font-size: 12px;">

							<div class="table-responsive">

								<table class="table table-bordered table-condensed"
									style="margin-bottom: 0;">

									<thead style="font-size: 12px;">
										<tr>
											<th style="width: 5%; padding: 4px;">Sr.</th>
											<th style="padding: 4px;">Type</th>
											<th style="padding: 4px;">Name</th>
											<th style="padding: 4px;">Counsel</th>
										</tr>
									</thead>

									<tbody>
										<tr ng-repeat="data in List"
											ng-style="data.checked==true ? personColour : ''"
											style="line-height: 1.2;">

											<!-- Sr No -->
											<td style="padding: 3px;">{{$index + 1}}</td>

											<!-- Type -->
											<td style="padding: 3px; width: 35%;"><span
												ng-click="showSubDocument(data.ap_draft_no)"
												style="text-decoration: underline; cursor: pointer; font-size: 12px;">
													<b> {{data.applicationType.at_name}}<br />
														{{data.ap_draft_no}}
												</b>
											</span> <!-- Sub apps -->
												<div ng-repeat="sb in data.subApplications"
													style="font-size: 11px; line-height: 1.1;"
													ng-click="showSubDocument(data.ap_draft_no)">
													{{sb.applicationType.at_name}}<br />
													{{sb.sb_ap_no}}/{{sb.sb_ap_year}}
												</div> <!-- Icons -->
												<div style="margin-top: 2px;">
													<span ng-if="data.ap_draft_no"
														class="glyphicon glyphicon-folder-open"
														style="cursor: pointer; font-size: 12px;"
														ng-click="showdemo(data)"> </span> <span
														ng-if="data.ap_draft_no"
														class="glyphicon glyphicon-new-window"
														style="cursor: pointer; float: right; font-size: 12px;"
														ng-click="caseTab(data.ap_draft_no, $index, List)">
													</span> <span style="float: right; margin-right: 6px;"> <input
														type="checkbox" ng-model="data.checkBoxValue"
														ng-click="checkHighlight(data)">
													</span>
												</div></td>

											<!-- Name -->
											<td style="padding: 3px; font-size: 12px;">
												{{data.ap_applicant_name}}</td>

											<!-- Counsel -->
											<td style="padding: 3px; font-size: 12px;">
												{{data.userFiled.um_fullname}}</td>

										</tr>
									</tbody>

								</table>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

		<div class="col-md-7 no-padding"">


			<jsp:include page="viewer2.jsp"></jsp:include>


		</div>



	</div>


</div>



<!--========================================== VIJAY CHAURASIYA  =========================== -->

</body>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css'>





<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>



<script>
	$(document).ready(function() {
		App.init();

	});

	$(function() {
		$("#mySidenav").dialog({
			autoOpen : false,
			height : 200,
			width : 350,
			resizable : true,
			position : {
				my : "left top",
				at : "right bottom"
			},
			dialogClass : 'no-close sidenav-dialog'
		})
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



<script>
	
</script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();

	});
</script>


</html>