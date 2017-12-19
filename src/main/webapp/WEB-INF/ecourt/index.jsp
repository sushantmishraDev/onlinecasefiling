<jsp:include page="../content/header2.jsp"></jsp:include>
	<div id="content" class="content">
		<div class="container-fluid" ng-controller="DemoCtrl" >
		<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        
                        <div class="panel-body">
                            <div class="table-responsive">
                                
                            </div>
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
		</div>

	</div>
	
	</div>
	</body>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/TestController.js"></script>
	
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</html>