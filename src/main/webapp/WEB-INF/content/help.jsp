<jsp:include page="../content/header2.jsp"></jsp:include>  
<div id="content" class="content">
  <div class="container-fluid" ng-controller="draftViewController" >
  
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>                                
                            </div>
                            <h4 class="panel-title">Help</h4>
                        </div>
					<div style="position: absolute;">
							<!-- <font size="2" face="verdana" color="red"> Note : Please click on submit button for generation of Diary No.</font><br/> -->
							<font size="2" face="verdana" color="#3489eb"> Instruction of e-Filing, bookmarking and linking of case files,Digital signature, workflow of e-Filing
							,e-Court fee/e-Stamping and workflow of online E-court fee payment system.<br/> Please visit the official 
							<a href ="https://www.allahabadhighcourt.in/" color="red">website </a> of Allahabad High Court under the tab of e_filing.
							</font>
					</div><br/><br/><br/>
					
                        
                        
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
           </div>
           </div>
            <!-- end row -->
    	</body>
   

	
	
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/draftViewController.js?v=3"></script>
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