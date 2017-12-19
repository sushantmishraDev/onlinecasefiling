<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../content/header.jsp"%>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/site/site.css'>
<div id="content" class="content" ng-controller="caseFileCtrl">
	<!-- begin row -->
	<div class="row">
		<!-- begin col-10 -->
		<div class="col-md-10">
			<!-- begin panel -->
			<div class="panel panel-inverse" data-sortable-id="form-stuff-5">
                    <div class="panel-heading">
                        <h4 class="panel-title">Unauthorized Access</h4>
                    </div>
                    <div class="panel-body">


        <div class="col-lg-12">
            <h4>File Not Found</h4>
            <p>Document that you are looking for is not present in your bucket. If you think this is a mistake, please report this to your System Administrator.</p>
        </div>
    </div>
</div>
			<!-- end panel -->
		</div>
		<!-- end col-10 -->
	</div>
	<!-- end row -->
	
</div>

<!-- begin theme-panel -->

<!-- end theme-panel -->

<!-- begin scroll to top btn -->
<a href="javascript:;"
	class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
	data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
<!-- end scroll to top btn -->

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