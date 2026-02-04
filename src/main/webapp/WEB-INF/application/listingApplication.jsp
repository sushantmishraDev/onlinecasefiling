<div id=page style="background-color: white;">
	<h4 class="panel-title" align="center"
		style="font-weight: bold; font-size: large;">LISTING APPLICATION
		FORM</h4>
	<br>
	<%-- <link
	href="${pageContext.request.contextPath}/assets/listingStyle.css"
	rel="stylesheet" /> --%>

	<div id="pdfPrep"
		style="background: white; width: 210mm; min-height: 297mm; padding: 20mm; margin: auto; font-family: 'Times New Roman', serif;">

		<div align="left">
			<h4 align="center">IN THE HIGH COURT OF JUDICATURE AT ALLAHABAD</h4>
			<h4 align="center">****</h4>
			<div id=page2>
				<h4 style="padding-left: 28%">LISTING APPLICATION NO
					---------OF 2023</h4>
				<h4 align="center">IN</h4>
				<h4 style="padding-left: 20%">{{caseDetailsCIS[1]}} NO.
					{{caseDetailsCIS[2]}} OF {{caseDetailsCIS[3]}}</h4>
				<h4 style="padding-left: 25%">(U/s 24 of the Civil Procedure
					Code)</h4>
				<h4 align="center" style="padding-right: 12%">
					<U style="text-transform: uppercase">DISTRICT-{{pdestrict.pt_city}}</U>
				</h4>
			</div>

			<div id=page3>
				<h4 align="left">
					1. <span>{{caseDetailsCIS[4]}}</span>
				</h4>
				<h4 align="left">
					<span>{{petitioner[0]}}</span>
				</h4>
				<h4 align="left">
					<span>{{petitioner[1]}}</span>
				</h4>
				<h4 align="left">&nbsp;&nbsp; All are resident of House no. 27
					model town p.s model town delhi</h4>
				<h4 align="left">
					4. <span>Manoj son of sri nand lal r/o house no 1712
						jahangeerpuri P.S jahangeerpuri delhi</span>
				</h4>
				<h4 align="right" style="padding-right: 12%">.............Applicants</h4>
			</div>

			<div id=page4>
				<h4 align="left">
					1. <span>{{caseDetailsCIS[5]}}</span>
				</h4>
				<h4 align="left">
					<span>{{respondent[0]}}</span>
				</h4>
				<h4 align="left">
					<span>{{respondent[1]}}</span>
				</h4>
				<h4 align="left">
					<span>{{respondent[2]}}</span>
				</h4>
				<h4 align="left">&nbsp;&nbsp;residet of 1/2234 police line P.s
					kavi nagar</h4>
				<h4 align="left">&nbsp;&nbsp;district Ghaziabad</h4>
				<h4 align="right" style="padding-right: 12%">.........Opposite
					Parties</h4>
			</div>

			<div id=page5>
				<h4 style="padding-left: 0%">To,</h4>
				<h4 style="word-spacing: 8px; letter-spacing: 2px">&nbsp;&nbsp;&nbsp;&nbsp;The
					Hon'ble Chief Justice and his companion judges of</h4>
				<h4 style="word-spacing: 8px; letter-spacing: 2px">the
					aforesaid court</h4>
				<h4 style="word-spacing: 8px; letter-spacing: 2px">&nbsp;&nbsp;&nbsp;&nbsp;The
					humble application of the applicant, above named,</h4>
				<h4 style="word-spacing: 8px; letter-spacing: 2px">most
					respectfully showeth as under.</h4>
				<br>


				<div>
					<!-- EDIT MODE -->
					<div ng-if="!isDownloading">
						<textarea ng-model="application.body1"
							style="width: 100%; border: none; font-family: 'Times New Roman', serif; font-size: 11pt; line-height: 1.5; resize: none;"
							placeholder="That this Hon'ble Court may graciously be pleased to list the above noted case at an early date..."
							ng-trim="false">
                         </textarea>
					</div>

					<!-- PDF MODE -->
					<div ng-if="isDownloading"
						style="white-space: pre-wrap; font-family: 'Times New Roman', serif; font-size: 11pt; line-height: 1.5;">
						{{application.body1}}</div>

				</div>

				<h4 style="word-spacing: 8px; letter-spacing: 2px">5.That
					therefor the matter may be listed as early as possible by fixing
					any date.</h4>

				<h4 style="word-spacing: 8px; letter-spacing: 2px">5.That, this
					application is being filed on 16.1.2026 and it is to be taken up on
					20.1.2026.</h4>

			</div>

			<div>
				<h4 style="padding-left: 30%; font-weight: 10px;">PRAYER</h4>





			</div>
			<div ng-if="!isDownloading">
				<textarea ng-model="application.prayer"
					style="width: 100%; border: none; font-family: 'Times New Roman', serif; font-size: 11pt; line-height: 1.5; resize: none;"
					placeholder="That this Hon'able Court may graciously be pleased to list the above noted case at an early date..."
					ng-trim="false">
    </textarea>
			</div>

			<div ng-if="isDownloading"
				style="white-space: pre-wrap; font-family: 'Times New Roman', serif; font-size: 11pt; line-height: 1.5;">
				{{application.prayer}}</div>

		</div>

		<div>
			<h4 align="left">Dated:</h4>
		</div>

		<div align="right" style="margin-right: 2%">
			<h5>
				<span class="danger"><b>({{advocates.name}})</b></span>
			</h5>
			<h5>
				<b>Adv. Roll {{advocates.rollNo}}</b>
			</h5>
			<h5>Advocate</h5>
			<h5>Counsels for the Applicants</h5>
			<h5>{{advocates.address1}}</h5>
			<h5>Mob:{{advocates.mobile}}</h5>
			<!-- <h5>PH:{{}}</h5> -->
		</div>

	</div>

</div>
</div>

<button class="btn btn-success" ng-click="saveApplication()">Save
	Application</button>
<button class="btn btn-success" ng-click="downloadApplication()">Download
	Application</button>