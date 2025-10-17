<div  class="smooth container w-xxxl w-auto-xs" >


<script>
window.onload = function() {
  var spans = document.getElementsByTagName("span"),
      index,
      span;
  
  for (index = 0; index < spans.length; ++index) {
    span = spans[index];
    if (span.contentEditable) {
      span.onblur = function() {
        var text = this.innerHTML;
        text = text.replace(/&/g, "&amp").replace(/</g, "&lt;");
        /* display("Content committed, span " +
                (this.id || "anonymous") +
                ": '" +
                text + "'"); */
      };
    }
  }
  
  function onClick() {
	    /* html2canvas(document.getElementById("pdfPrep"), {
	      onrendered: function(canvas) {

	        var img = canvas.toDataURL("image/jpg");
	        var doc = new jsPDF();

	        var width = doc.internal.pageSize.width;
	        var height = doc.internal.pageSize.height;
	        doc.addImage(img, 'JPEG', 0, 0, width, height);
	        doc.save('test.pdf');
	      }

	    }); */
	    
	    
	  let HTML_Width = $("#target").width();
      let HTML_Height = $("#target").height();
      let top_left_margin = 1;
      let PDF_Width = HTML_Width + (top_left_margin * 2);
      let PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
      let canvas_image_width = HTML_Width;
      let canvas_image_height = HTML_Height;
      let totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;
      let user = 'test';
      html2canvas($("#target")[0], { onrendered :function (canvas) {
          canvas.getContext('2d');
          let imgData = canvas.toDataURL("image/png", 1.0);
          let pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);

          pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, canvas_image_height);

          let counter = 0;

          for (let i = 1; i <= totalPDFPages; i++) {
              counter++;
              pdf.addPage(PDF_Width, PDF_Height);
              pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height * i) + (top_left_margin * 4), canvas_image_width, canvas_image_height);
          }
          pdf.save(user + ".pdf");
      }
      });
	  };
	  
	  const ps = Array.from(document.getElementsByTagName('p'));
	  
	  for (let i = 0; i < ps.length; i++) {
		  ps[i].style.color = 'black'
		}

	  var element = document.getElementById("clickbind");
	  element.addEventListener("click", onClick);
  
 /*  function display(msg) {
    var p = document.createElement('p');
    p.innerHTML = msg;
    document.body.appendChild(p);
  } */
};
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>

<div id="target">
 <div id="pdfPrep" style="margin-left: 80px;width: 800px;">
 
         <div  align="center">
          <h3><b>IN THE HIGH COURT OF JUDICATURE AT ALLAHABAD</b><h3/>
                                  <b> **********</b>
          <h3><b>CIVIL MISC. CAVEAT APPLICATION NO.......OF 2022</b></h3>
          <p><b>(Under Section 148-A C.P.C. Read With Chapter XXII Rule 5 of High Court Rules)</b></p>
                                        <h4><b>On behalf of</b></h4>
                                        </div>
          <p>{{caveat.cav_caveator_name}},</p>
          <p>Resident of House  No. 276 Behjoi Road, Mohalla Vikas Nager,</p>
          <p>Post Offuce Chandausi, Tessil and District Sambhal. Pin Code 244412.</p>
         <p align="right"><b>-------Caveator/Applicant</b></p>
            <div align="center">
            <h3><b>IN</b></h3>
          <h3><b>{{caveat.caseType.ct_name}} No.....OF 2022</b></h3>
          <p><b>(Under Article 226 of the Constitution of India)</b></p>
          </div>
          <h4 align="right">DISTRICT- {{caveat.district.dt_name}}</h4>
          <div  ng-repeat="row in petitionerDataList">
          <p style="text-align: left;">{{row.cpt_name}},<br/>
           {{row.cpt_address}}.<br/>
          Pin Code {{row.cpt_pincode}}.</p>
          </div>
         <p align="right"><b>-------Praposed Petitioner</b></p>
          <h3 align="center">--Versus---</h3>
          <div  ng-repeat="row in respondentDataList">
          {{$index+1}} - {{row.crt_name}}
          </div>
          <p align="right"><b>-------Praposed Respondent</b></p>
          <p style="width: 900px;">To,<br/>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
         
         The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          v
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
          
          The Hon'ble the Chief Justice and his other comption judge of the aforesaid court.
          The humble application of the above named Application /Caveator
          Most Respectfully showeth as under:  <span style="background-color: #ff6;" contenteditable="true">editable</span>. </p>
         
         </div>
         </div>
         <button id="clickbind">Click</button>
         </div>
          
							
		                			
		
		
		
		             
					
          

					
					
					
					
