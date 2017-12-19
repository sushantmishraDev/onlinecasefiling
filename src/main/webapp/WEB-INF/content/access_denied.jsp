<jsp:include page="../content/header2.jsp"></jsp:include>
<%@ page import="com.dms.model.User"%>	

	<div id="content" class="content">
		<div class="row">
		<div class="col-md-10">
			<!-- begin panel -->
			<div class="panel panel-inverse" data-sortable-id="form-stuff-5">
                    <div class="panel-heading">
                        <h4 class="panel-title">Unauthorized Access</h4>
                    </div>
                    <div class="panel-body">


			        <div class="col-lg-12">
			            <h4>${message}</h4>			            
			        </div>
				
					</div>
		</div>
</div>
</div>
</div>
</div>	
	</body>
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/scripts/controllers/ecourtHomeController.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</html>