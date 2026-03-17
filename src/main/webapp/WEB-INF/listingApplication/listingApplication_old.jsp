<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.dms.model.User"%>	

<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");
%>
<style>
.placeholder-text::placeholder {
    color: blue;
}
</style>
<html ng-app="EDMSApp">

<body>
	<div id="content" class="content">
		<div class="container-fluid" ng-controller="searchCaseFileController"
			oncontextmenu="return false;" ng-init="listingParty()">

			<!-- <input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id">   ---- caseDetalby ccms data in ${caseData} ${caseSplit} -->
<h3><marquee style="color:red" >. . . . is required to be filled by Applicant. </marquee></h3>

			<div class="row">
				<!-- begin col-12 -->
				<!-- begin panel -->
				<div class="panel panel-inverse">
					<div class="panel-heading">
						<div class="panel-heading-btn">
							<a href="javascript:;"
								class="btn btn-xs btn-icon btn-circle btn-default"
								data-click="panel-expand"><i class="fa fa-expand"></i></a>
							<!-- </div>
						<h4 class="panel-title" align="center">LISTING APPLICATION
							FORM</h4>
					</div> -->
							<!-- 	
					<div id=page style="background-color: white;">
		<h4 class="panel-title" align="center" style ="font-weight: bold;font-size: large;">LISTING APPLICATION FORM </h4> -->

                         
							<div id="pdfPrep"
								style="margin-left: 10%; margin-right: 10%; width: 80%; padding-left: 10pt; text-align: justify; font-family: 'Arial', 'Times New Roman', serif; font-size: 14pt; page-break-inside: auto; overflow: hidden;">

								<div align="left" >
									<h4 align="center">IN THE HON'BLE HIGH COURT OF JUDICATURE AT
										ALLAHABAD</h4>  
									<h4 align="center">************</h4>
									<div id=page2 style="text-align: center">
										<h4>LISTING APPLICATION NO. --------- OF 2026</h4>
										<!-- <h4>(Under order XXII Rule 1 Of High Court Rules)</h4> -->
										<p style="line-height: 0pt;" contenteditable="true">On behalf of.....<i style="color:blue;">Enter text</i></p>
										<h4 align="center">IN</h4>
										<h4>${caseData.caseType} NO.${caseSplit[1]} OF
											${caseSplit[2]}</h4>
										<h4>(${fn:split(caseData.category, '(')[0]})</h4>
										<!-- <h4 style="padding-left: 25%">(U/s 24 of the Civil Procedure Code)</h4> -->
										<h4>
											<U style="text-transform: uppercase">DISTRICT-${caseData.district}</U>
										</h4>
									</div>


									<div id=page3 style="padding-left: 10%">
										<p align="left">
											<span ng-repeat="party in firstPrint">{{party}}<br/></span>
											
										</p>

										<p align="left" contenteditable="true">&nbsp;&nbsp; <i style="color:blue;">Enter Address</i>
											 </p>

										</p>
										<h4 align="right" style="padding-right: 12%">.............Petitioner</h4>
									</div>

									<div>
										<h4 align="center">Versus</h4>
									</div>

									<div id=page4 style="padding-left: 10%">
										<p align="left">
											<span ng-repeat="party in secondPrint">{{party}}<br/></span>
										</p>

										<p align="left" contenteditable="true">&nbsp;&nbsp;<i style="color:blue;">Enter address</i>
											</p>

										<h4 align="right" style="padding-right: 12%">.........Respondent / Opposite Party
											</h4>
									</div>

									<div id=page5 style="page-break-inside: auto;padding-left: 10%" contenteditable="true">
										<p style="padding-left: 0%">To,</p>
										<p style="word-spacing: 6pt; letter-spacing: 1pt">&nbsp;&nbsp;&nbsp;&nbsp;The
											Hon'ble Chief Justice and his other companion Judges of the
											aforesaid Court.</p>
										<!-- <p style="word-spacing: 6pt; letter-spacing: 1pt">the
									aforesaid court.</p> -->
										<p style="word-spacing: 6pt; letter-spacing: 1pt">&nbsp;&nbsp;&nbsp;&nbsp;The
											humble application of the above named my client most
											respectfully showeth as under:</p>
										<!-- <p style="word-spacing: 6pt; letter-spacing: 1pt">most
									respectfully showeth as under:</p> -->

										<!-- <h4 style="word-spacing: 8px; letter-spacing: 2px">That the above noted case was filed seeking transfer the record
					 of Case No 5 of 2025 (Smt. Mannu Dilawar Vs. Rajeev) U/s 7 & 25 of Guardinas & Wards Act, pending in the court
					 of learned Principal Judge, Family Court, Shamli at Kairana to any other Court Of District Judgeship Meerut.
					 </h4>
					<h4 style="word-spacing: 8px; letter-spacing: 2px">Section-397/401 Cr.P.C was taken up on 20.11.2014 Where by
						the Hon'ble Court stayed the proceeding of the case.</h4> 
					<h4 style="word-spacing: 8px; letter-spacing: 2px">That the pleading have been exchanged between the parties</h4>

					<h4 style="word-spacing: 8px; letter-spacing: 2px">3. That by now the compromise has been arrived between the
						parties and they do not went to parsed the instant case on the
						basic of compromise.</h4>

					<h4 style="word-spacing: 8px; letter-spacing: 2px">4. That the revisionist have filed several listing
						application but matter could not are listed up</h4> -->

										<div style="margin: 10pt 0; line-height: 1.5;">
											<textarea class="placeholder-text" ng-model="text"
												style="width: 100%; word-spacing: 6pt; letter-spacing: 1pt; border: none; font-family: 'Arial', 'Times New Roman', serif; font-size: 14pt; line-height: 1.5; resize: none; page-break-inside: auto; overflow: hidden; text-align: justify;"
												oninput="this.style.height='auto'; this.style.height=(this.scrollHeight > 300 ? 300 : this.scrollHeight)+'pt';"
												placeholder="text to be filled ......."></textarea>
										</div>

										<div
											style="text-align: justify; letter-spacing: 1pt; margin: 10pt 0; line-height: 1.5; break-inside: avoid;">4.That
											therefore, the matter may be listed as early as possible by
											fixing any date.</div>

										
										<!-- <p
									style="word-spacing: 5pt; letter-spacing: 1pt;margin: 10pt 0; line-height: 1.5; break-inside: avoid;">5.That
									this application is being filed on 16.1.2026 and it is to be taken up on 20.1.2026. </p> -->
										<div
											style="text-align: justify;letter-spacing: 1pt; line-height: 1.5; margin: 10pt 0;break-inside: avoid;">
											5.That this application is being filed on <span
												style="white-space: nowrap;">.....</span> and it is to
											be taken up on <span style="white-space: nowrap;">.....</span>.
										</div>

									</div>

									<!-- <div style="padding-left: 20%">
								<h4 style="padding-left: 30%; font-weight: 10pt;">PRAYER</h4>

								<p
									style="word-spacing: 8pt; letter-spacing: 3pt; padding-left: 10%;">It
									is therefore, most respectfully prayed that this Hon'ble</p>

								<p
									style="letter-spacing: 3pt; text-align: justify; word-spacing: 8pt;">Court
									may graciously be pleased to allow the present Application</p>
								<p
									style="padding-left: 0%; letter-spacing: 3pt; word-spacing: 6pt;">and
									to direct the office to list the present case as early as
									possible</p>

								<p style="letter-spacing: 3pt; word-spacing: 6pt;">by fixing
									any date. And/or to pass any suitable order or direction to</p>
								<p style="letter-spacing: 3pt">which this Hon'ble Court may
									deem fit and proper in the interest of justice.</p>

							</div> -->

									<div contenteditable="true" 
										style="page-break-inside: auto;font-family: 'Arial', 'Times New Roman', serif; text-align: justify; line-height: 1.6; word-spacing: 6pt; letter-spacing: 1pt; padding-left: 10%; margin-right: 10pt;">
										<span
											style="display: block; text-align: center; font-weight: bold; font-size: 14pt; margin-bottom: 12px;">PRAYER</span>
										&nbsp;&nbsp;&nbsp;&nbsp;It is therefore, most respectfully
										prayed that this Hon'ble Court may graciously be pleased to
										allow the present Application and to direct the office to list
										the present case as early as possible by fixing any date.
										And/or to pass any suitable order or direction to which this
										Hon'ble Court may deem fit and proper in the interest of
										justice.
									</div>



									<div>
										<h4 style="padding-left: 10%" contenteditable="true">Dated: ....</h4>
									</div>

									<div align="right" style="margin-right: 2%;page-break-inside: auto;">
										<p>
											<span class="danger"><b>(<%=user.getUm_fullname() %>)</b></span>
										</p>
										<p>Advocate</p>
										<p>Counsels for the Applicants</p>
										<p>Advocate Roll No.<%=user.getUsername() %></p>

									</div>

								</div>

							</div>
						</div>

						<!-- <button class="btn btn-success" ng-click="saveApplication()">Save
					Application</button> -->
						<div align="center"><button style="background-color: blue; color: white; padding:5px;border-radius:5%; " type="button"; ng-hide="!text || text.trim() === 0"
							ng-click="generatePDF()">Download Pdf</button>
							<button
								style="background-color: green; color: white; padding: 5px; border-radius: 5%;" ng-hide="!text || text.trim() === 0" 
								type="button" ; 
							ng-click="generateODT()">Download
								ODT</button>
							
							</div>

					</div>
				</div>



			</div>
</body>

<!-- ================== END PAGE LEVEL JS ================== -->

<!-- <script src="/onlinecasefiling/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="/onlinecasefiling/assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="/onlinecasefiling/assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script> -->

<!-- <link href="/onlinecasefiling/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
	
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/odt.js-master/lib/odt.js"></script>

<!-- 	<script type="text/javascript" src="/onlinecasefiling/js/bootstrap/bootstrap.min.js"></script> -->

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
	
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/odf.js/0.9.0/odf.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/searchCaseFile.js?v=2"></script>

<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script> --%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>




</html>