<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.dms.model.User"%>

<%
User user = null;
if (session.getAttribute("USER") != null)
	user = (User) session.getAttribute("USER");
%>
<style>
.placeholder-text::placeholder {
	color: blue;
}
</style>

<style>
.placeholder-text::placeholder {
	color: blue;
}

/* Prevent blocks from splitting */
#pdfPrep p, #pdfPrep div, #pdfPrep h4 {
	page-break-inside: avoid;
	break-inside: avoid;
}

/* Manual page break before PRAYER */
.page-break {
	page-break-before: always;
}

.prayer-title {
	display: block;
	text-align: center;
	font-weight: bold;
	font-size: 14pt;
	margin-bottom: 10pt;
}

.prayer-section {
	page-break-inside: avoid;
	break-inside: avoid;
	margin-top: 20pt;
	min-height: 120pt;
}

#pdfPrep h4 {
	page-break-after: avoid;
}

.signature-block{
	page-break-inside: avoid;
}

#pdfPrep {
	width: 700px;
	margin: auto;
	font-family: 'Arial', 'Times New Roman', serif;
}
</style>
<html ng-app="EDMSApp">

<body>
	<div id="content" class="content">
		<div class="container-fluid" ng-controller="searchCaseFileController"
			oncontextmenu="return false;" ng-init="listingParty()">
			
			
			
			<input type="hidden" id="advocateName" value="<%=user.getUm_fullname()%>">
            <input type="hidden" id="rollNo" value="<%=user.getUsername()%>">
            <input type="hidden" id="caseType" value="${caseData.caseType}">
<input type="hidden" id="caseNo" value="${caseSplit[1]}">
<input type="hidden" id="caseYear" value="${caseSplit[2]}">
<input type="hidden" id="district" value="${caseData.district}">
<input type="hidden" id="categoryText"
       value="${fn:split(caseData.category, '(')[0]}">
            
            

			<!-- <input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id">   ---- caseDetalby ccms data in ${caseData} ${caseSplit} -->
			<h3>
				<marquee style="color: red">. . . . is required to be
					filled by Applicant. </marquee>
			</h3>

			<div class="row">
				<!-- begin col-12 -->
				<!-- begin panel -->
				<div class="panel panel-inverse">
					<div class="panel-heading">
						<div class="panel-heading-btn">
							<a href="javascript:;"
								class="btn btn-xs btn-icon btn-circle btn-default"
								data-click="panel-expand"><i class="fa fa-expand"></i></a>



							<div id="pdfPrep"
								style="margin-left: 10%; margin-right: 10%; width: 80%; padding-left: 10pt; text-align: justify; font-family: 'Arial', 'Times New Roman', serif; font-size: 14pt; page-break-inside: auto; overflow: hidden;">

								<div align="left">
									<h4 align="center">IN THE HON'BLE HIGH COURT OF JUDICATURE
										AT ALLAHABAD</h4>
									<h4 align="center">************</h4>
									<div id=page2 style="text-align: center">
										<h4>LISTING APPLICATION NO. --------- OF 2026</h4>
										<!-- <h4>(Under order XXII Rule 1 Of High Court Rules)</h4> -->
										<p style="line-height:1.5;">
    On behalf of
    <span contenteditable="true"
    	ng-focus="onFocusText()"
          ng-keyup="onBehalfTyping($event)"
          ng-blur="updateOnBehalf($event)"
          style="min-width:150px;
                 display:inline-block;
                 color:{{!onBehalfOf ? 'blue' : 'black'}};">
        {{ onBehalfOf || 'Enter text' }}
    </span>
</p>
										<h4 align="center">IN</h4>
										<h4>${caseData.caseType}NO.${caseSplit[1]}OF
											${caseSplit[2]}</h4>
										<h4>(${fn:split(caseData.category, '(')[0]})</h4>
										<!-- <h4 style="padding-left: 25%">(U/s 24 of the Civil Procedure Code)</h4> -->
										<h4>
											<U style="text-transform: uppercase">DISTRICT-${caseData.district}</U>
										</h4>
									</div>


									<div id=page3 style="padding-left: 10%">
										<ol style="padding-left: 20px;">
											<li ng-repeat="party in firstPrint">{{party}}</li>
										</ol>

										<div contenteditable="true"
											style="margin-left: 30pt; color: blue; font-size: 14pt; line-height: 1.5; font-family: 'Arial', 'Times New Roman', serif;"
											onfocus="if(this.innerText.trim()=='Enter address'){this.innerText=''; this.style.color='black';}"
											onblur="if(this.innerText.trim()==''){this.innerText='Enter address'; this.style.color='blue';}">
											Enter address</div>

										<h4 align="right" style="padding-right: 12%">.............Petitioner</h4>
									</div>

									<div>
										<h4 align="center">Versus</h4>
									</div>

									<div id=page4 style="padding-left: 10%">
										<ol style="padding-left: 20px;">
											<li ng-repeat="party in secondPrint">{{party}}</li>
										</ol>

										<p contenteditable="true"
											style="margin-left: 30pt; word-spacing: 6pt; letter-spacing: 1pt; font-family: 'Arial', 'Times New Roman', serif; font-size: 14pt; line-height: 1.5; text-align: justify; color: blue;"
											onfocus="if(this.innerText.trim()=='Enter address'){this.innerText=''; this.style.color='black';}"
											onblur="if(this.innerText.trim()==''){this.innerText='Enter address'; this.style.color='blue';}">
											Enter address</p>

										<h4 align="right" style="padding-right: 12%">.........Respondent
											/ Opposite Party</h4>
									</div>

									<div id=page5
										style="page-break-inside: auto; padding-left: 10%"
										contenteditable="true">
										<p style="padding-left: 0%">To,</p>
										<p style="word-spacing: 6pt; letter-spacing: 1pt">&nbsp;&nbsp;&nbsp;&nbsp;The
											Hon'ble Chief Justice and his other companion Judges of the
											aforesaid Court.</p>

										<p style="word-spacing: 6pt; letter-spacing: 1pt">&nbsp;&nbsp;&nbsp;&nbsp;The
											humble application of the above named my client most
											respectfully showeth as under:</p>


										<div style="margin: 10pt 0; line-height: 1.5;page-break-inside: auto;">
											<textarea class="placeholder-text" ng-model="text"
style="width:100%;word-spacing:6pt;letter-spacing:1pt;border:none;
font-family:'Arial','Times New Roman',serif;font-size:14pt;
line-height:1.5;resize:none;overflow:visible;text-align:justify;"
oninput="this.style.height='auto';this.style.height=this.scrollHeight+'px';"
placeholder="text to be filled ......."></textarea>
										</div>

										<div
											style="text-align: justify; letter-spacing: 1pt; margin: 10pt 0; line-height: 1.5; break-inside: avoid;">4.That
											therefore, the matter may be listed as early as possible by
											fixing any date.</div>



<div style="text-align: justify; letter-spacing: 1pt; line-height: 1.5; margin: 10pt 0;">
    5. That this application is being filed on 

   <!--  <span contenteditable="true"
          ng-init="filingDate=''"
          ng-blur="updateField($event, 'filingDate')"
          style="border-bottom:1px dotted black; min-width:80px; display:inline-block; color:blue;">
        .....
    </span>  -->
    <input type="text" ng-model="filingDate" placeholder="....................." style="border:none; width:100px;font-family:'Arial','Times New Roman',serif;font-size:12pt;">

    and it is to be taken up on 

    <!-- <span contenteditable="true"
          ng-init="hearingDate=''"
          ng-blur="updateField($event, 'hearingDate')"
          style="border-bottom:1px dotted black; min-width:80px; display:inline-block; color:blue;">
        .....
    </span> -->
    <input type="text" ng-model="hearingDate" placeholder="....................." style="border:none; width:100px;font-family:'Arial','Times New Roman',serif;font-size:12pt;">
</div>

									</div>


									<!-- <div style="page-break-before: always;"></div> -->

									<!-- <div class="page-break"></div> -->
									<!-- <div style="page-break-before: always;"></div> -->
									<div class="prayer-block" contenteditable="true"
										style="font-family: 'Arial', 'Times New Roman', serif; text-align: justify; line-height: 1.6; word-spacing: 6pt; letter-spacing: 1pt; padding-left: 10%; margin-right: 10pt;">

										<div
											style="text-align: center; font-weight: bold; font-size: 14pt; margin-bottom: 12pt;">
											PRAYER</div>

									<p>	&nbsp;&nbsp;&nbsp;&nbsp;It is therefore, most respectfully
										prayed that this Hon'ble Court may graciously be pleased to
										allow the present Application and to direct the office to list
										the present case as early as possible by fixing any date.
										And/or to pass any suitable order or direction to which this
										Hon'ble Court may deem fit and proper in the interest of
										justice.
</p>
									</div>



									<div>
										<h4 style="padding-left: 10%;" contenteditable="true"
											onfocus="if(this.innerText.trim()=='Dated: ....'){this.innerText='Dated: ';}"
											onblur="if(this.innerText.trim()=='Dated:'){this.innerText='Dated: ....';}">
											Dated: ....</h4>
									</div>
									<div class="signature-block">
										<div align="right"
											style="margin-right: 2%; page-break-inside: auto;">
											<p>
												<span class="danger"><b>(<%=user.getUm_fullname()%>)
												</b></span>
											</p>
											<p>Advocate</p>
											<p>Counsels for the Applicants</p>
											<p>
												Advocate Roll No.<%=user.getUsername()%></p>

										</div>

									</div>

								</div>
							</div>

							<!-- <button class="btn btn-success" ng-click="saveApplication()">Save
					Application</button> -->
							<div align="center">
								<button
									style="background-color: blue; color: white; padding: 5px; border-radius: 5%;"
									type="button" ; ng-hide="!text || text.trim() === 0"
									ng-click="generatePDF()">Download Pdf</button>
								<button
									style="background-color: green; color: white; padding: 5px; border-radius: 5%;"
									ng-hide="!text || text.trim() === 0" type="button"
									; 
							ng-click="generateODT()">Download ODT</button>

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


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/odf.js/0.9.0/odf.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/application_controllers/searchCaseFile.js?v=4"></script>

<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script> --%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>

<script
	src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.6.1/jszip.min.js"></script>

<script
	src="${pageContext.request.contextPath}/js/odt.js-master/lib/odt.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
	
	

</html>