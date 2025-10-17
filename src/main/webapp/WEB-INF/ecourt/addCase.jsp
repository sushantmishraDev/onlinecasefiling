<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content" ng-controller="addCaseDetailController">
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#nav-tab-1"  id ="1"data-toggle="tab" data-ng-click="ShowId($event)">New Case Details</a></li>
					<li class=""><a href="#nav-tab-2" id="2" data-toggle="tab" data-ng-click="ShowId($event)">Petitioner</a></li>
					<li class=""><a href="#nav-tab-3" data-toggle="tab" id="3" data-ng-click="ShowId($event)">Respondents</a></li>
					<li class=""><a href="#nav-tab-4"  id="4" data-toggle="tab" data-ng-click="ShowId($event)">Act Details</a></li>
					<li class=""><a href="#nav-tab-5" id="5" data-toggle="tab" data-ng-click="ShowId($event)">Impunged Order Details</a></li>
					<!-- <li class=""><a href="#nav-tab-8" id="8" data-toggle="tab" data-ng-click="ShowId($event)">Caveat Serach</a></li> -->
					<li class=""><a href="#nav-tab-6" id="6" data-toggle="tab" data-ng-click="ShowId($event)">E-Court Fee </a></li>
					<!-- <li class=""><a href="#nav-tab-9" id="9" data-toggle="tab" data-ng-click="ShowId($event)">LinkCase</a></li> -->
					<li class=""><a href="#nav-tab-10" id="10" data-toggle="tab" data-ng-click="ShowId($event)">Crime Details</a></li>					
					<li class=""><a href="#nav-tab-11" id="11" data-toggle="tab" data-ng-click="ShowId($event)">ST details</a></li>
					<li class=""><a href="#nav-tab-7" id="7" data-toggle="tab" data-ng-click="ShowId($event)">Upload Document </a></li>
					<!-- <li class=""><a href="#nav-tab-12" id="12" data-toggle="tab" data-ng-click="ShowId($event)">Advance Notice </a></li> -->
					
					
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade active in" id="nav-tab-1">
 						<jsp:include page="../ecourt/newCase.jsp"></jsp:include>
 						<input type="hidden" class="form-control" value=${draftId } id="draftId" name="draftId"> 
 						<input type="hidden" class="form-control" value=${caseType } id="caseType" name="caseType">
 						<input type="hidden" class="form-control" value=${code } id="code" name="code">
					</div>
					<div class="tab-pane fade active in" id="nav-tab-2" ng-show="tabShow2">
						<jsp:include page="../ecourt/petitioners.jsp"></jsp:include>
					</div>
           			<div class="tab-pane fade active in" id="nav-tab-3" ng-show="tabShow3">
                  		<jsp:include page="../ecourt/respondent.jsp"></jsp:include>
					</div>
             		<div class="tab-pane fade active in" id="nav-tab-4" ng-show="tabShow4">
             			<jsp:include page="../ecourt/actDetails.jsp"></jsp:include>
					</div>
				 	<div class="tab-pane fade active in" id="nav-tab-5" ng-show="tabShow5">
						<jsp:include page="../ecourt/impugnedOrder.jsp"></jsp:include>
					</div>
					<%-- <div class="tab-pane fade active in" id="nav-tab-8" ng-show="tabShow8">
						<jsp:include page="../ecourt/documentPrepare.jsp"></jsp:include>
					</div>	 --%>
					<div class="tab-pane fade active in" id="nav-tab-6" ng-show="tabShow6">
						<jsp:include page="../ecourt/courtFee.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-9" ng-show="tabShow9">
						<jsp:include page="../ecourt/caseLink.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-10" ng-show="tabShow10">
						<jsp:include page="../ecourt/crimeDetail.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-11" ng-show="tabShow11">
						<jsp:include page="../ecourt/seesionTrackNo.jsp"></jsp:include>
					</div>
					<div class="tab-pane fade active in" id="nav-tab-7" ng-show="tabShow7">
						<jsp:include page="../ecourt/uploadDocument.jsp"></jsp:include>
					</div>	
					
					<div class="tab-pane fade active in" id="nav-tab-12" ng-show="tabShow12">
						<jsp:include page="../ecourt/notice.jsp"></jsp:include>
					</div>
					
					
						
				</div>
			</div>
		</div>
	</div>
</div>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/ecourtAddCaseDetailController.js?v=13"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui/0.4.0/angular-ui.min.js"></script> -->
	
	<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/unique.js"></script> --%>
	
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/select2.full.min.js"></script>
	
	<link rel='stylesheet' href='${pageContext.request.contextPath}/css/select2.min.css'>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
	
	

	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
			 $(document).on('change', ".check_class", function () {
				    $(".check_class").prop("checked", false);
				    $(this).prop("checked", true);
				  });
			 
			/*  var noticeEl = $('#notice');
			 noticeEl.bind('keydown', function onkeydown(e) {
					console.log('abc');
				    if (e.key !== 'Backspace' && isNaN(parseInt(e.key))) { e.preventDefault(); return; }
				});
			 noticeEl.bind('keyup', function onkeyup(e) {		    
				    if (isNaN(parseInt(e.key)) || e.key === 'Backspace') { 
				        return;
				    }
				    
				    var inputValue = e.srcElement.value;
				    if (String(inputValue).length === 4) {
				        e.srcElement.value += '/';
				    }
				}); */
			
		});
	</script>	
	



</html>

