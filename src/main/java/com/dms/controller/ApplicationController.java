package com.dms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ccms.model.CaseDetailsCcms;
import com.ccms.model.CcmsAdvoates;
import com.dms.model.ActionResponse;
import com.dms.model.AllowEfiling;
import com.dms.model.Application;
import com.dms.model.ApplicationCheckListMapping;
import com.dms.model.ApplicationCourtFee;
import com.dms.model.ApplicationStage;
import com.dms.model.ApplicationTypes;
import com.dms.model.ApplicationUploaded;
import com.dms.model.BSApplicationCheckListMapping;
import com.dms.model.BindedEntity;
import com.dms.model.CaseConversionCCmc;
import com.dms.model.CaseFileDetail;
import com.dms.model.CaseType;
import com.dms.model.CcmsCaseDetails;
import com.dms.model.IndexField;
import com.dms.model.Lookup;
import com.dms.model.PetitionerDetails;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.SmsLog;
import com.dms.model.SubApplication;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.ApplicationService;
import com.dms.service.CaseFileStageService;
import com.dms.service.EcourtAddCaseService;
import com.dms.service.LookupService;
import com.dms.service.ScrutinyService;
import com.dms.service.UserRoleService;
import com.dms.service.UserService;
import com.dms.utility.GlobalFunction;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

@Controller
@RequestMapping("/application")
public class ApplicationController 
{
	private GlobalFunction cm;
	@Autowired
	ServletContext context;
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ScrutinyService scrutinyService;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private CaseFileStageService caseFileStageService;
	
	@Autowired
	private EcourtAddCaseService ecourtAddCaseService;
	
	@Autowired
	private UserService userService;
	
	
	public ApplicationController()
	{
		cm = new GlobalFunction();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addApplication(Model model,HttpServletRequest request) 
	{
		String fd_id=request.getParameter("fd_id");
		model.addAttribute("fd_id", fd_id);	

		return "/application/addApplication";
	}
	
	
	@RequestMapping(value = "/viewDocs", method = RequestMethod.GET)
	public String viewDocs(Model model,HttpServletRequest request) 
	{
		String fd_id=request.getParameter("fd_id");
		model.addAttribute("fd_id", fd_id);	

		return "/application/viewDocuments";
	}
	
	@RequestMapping(value = "/applicationDraftForm", method = RequestMethod.GET)
	public String applicationDraftForm() {

		return "/application/applicationDraftForm";
	}
	
	@RequestMapping(value = "/applicationDraftView/{id}", method = RequestMethod.GET)
	public String draftView(Model model, @PathVariable Long id,HttpSession session) {

		User u=(User) session.getAttribute("USER");
		
		String viewname="/content/access_denied";
		String message="";
		boolean flag=false;
		Application app = applicationService.getByPk(id);
		if(app!=null){
			if(u.getUm_id().longValue()==app.getAp_cr_by().longValue())
			{
				if(app.getCaseStage().getLk_longname().equals("DRAFT"))
				{
					viewname="/application/addApplication";					
				}
				else if(app.getCaseStage().getLk_id().equals(1000041L))
				{
					/* if(app.getAp_at_mid()==3L || app.getAp_at_mid()==25L || app.getAp_at_mid()==42L || app.getAp_at_mid()==46L){
					          flag=applicationService.checkBsDateValidity(app.getAp_id());
					 }*/
					if (false) {
						
					}
					 else{
						   flag=applicationService.checkDateValidity(app.getAp_id());
					 }
					if(flag)
					{
						viewname="/application/addApplication";
					}
					else
					{
						message="Permission Denied";
						viewname="/content/access_denied";
					}											
				}
				else
				{
					message="This file is not available for editing";
					viewname="/content/access_denied";
				}
			}
			else
			{
				message="Record does not exist";				
			}
		}
		else{
			message="Record does not exist";				
		}
		model.addAttribute("draftId", id);
		model.addAttribute("message", message);

		return viewname;

	}
	
	@RequestMapping(value = "/getDocList/{id}", method = RequestMethod.GET)
	public @ResponseBody String getDocList(@PathVariable("id") Long docId,HttpSession session) 
	{
		User user = (User) session.getAttribute("USER");
		ActionResponse<Application> response = new ActionResponse<Application>();
		CaseFileDetail fd=applicationService.getCaseFile(docId);
		String jsonData = "";
		/*Object[] types = noticeService.getCaseDetails("WRIC", 1, 2022);*/
		
		List<Application> apDocs=new ArrayList<>();
		
		List<Long> u=applicationService.getDocUsers(docId);
		
		List<Long> uOldCase=applicationService.getOldCaseDocUsers(fd.getFd_id_old());
		u.addAll(uOldCase);
		
		Long petU=applicationService.getPetUser(fd);
		if(petU != null && fd.getFd_id_old()!=null) {
			petU=applicationService.getPetUserOldCase(fd);
		}
		u.add(petU);
		
		 RestTemplate restTemplate1 = new RestTemplate();
		  
		  ObjectMapper mapper = new ObjectMapper();

		     // Option 1: ignore unknown fields
		     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        


	        HttpHeaders headers1 = new HttpHeaders();
	        headers1.setContentType(MediaType.APPLICATION_JSON);
	        
	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("Caseid", String.valueOf(fd.getFd_ccms_case_id())); // send as string if API expects string
	        
	        HttpEntity<Map<String, Object>> entity1 = new HttpEntity<>(requestBody, headers1);
		
		String externalApi="http://192.168.0.114/testapi/API/CaseStatus/AdvocateDetailsByCaseId";
	     
		ResponseEntity<Object> response2 = restTemplate1.postForEntity(
	             externalApi,
	             entity1,
	             Object.class
	             
	     );     
	     
	     Object t= response2.getBody();
	     
	     CcmsAdvoates cAdv=mapper.convertValue(t, CcmsAdvoates.class);
	     Object[] oPet=cAdv.getPetAdvocate().split(",");
	     Object[] oRes=cAdv.getResAdvocate().split(",");
	     String advParty=null;
	     
	     String  yourValue=user.getUm_fullname()+"("+user.getUsername()+" )";
	     
	     boolean containsAdv=Arrays.asList(oPet).contains(yourValue);
	     if(!containsAdv) {
	    	 containsAdv=Arrays.asList(oRes).contains(yourValue);
	    	 advParty="Res";
	     }
	     else {
	    	 advParty="Pet";
	     }
	     
	     if(containsAdv) {
	    	 
	    	 apDocs= applicationService.getDocList(docId);
				apDocs.addAll(applicationService.getOldCaseDocList(fd.getFd_id_old()));
				response.setData("TRUE");
				//response.setModelData(types);
				response.setModelList(apDocs);
	     }
	     else {
	    			if(u.contains(user.getUm_id())) {
	    				apDocs= applicationService.getDocList(docId);
	    				apDocs.addAll(applicationService.getOldCaseDocList(fd.getFd_id_old()));
	    				response.setData("TRUE");
	    				//response.setModelData(types);
	    				response.setModelList(apDocs);
	    				
	    			}
	    			else {
	    				response.setData("false");
	    				//response.setModelData(types);
	    				//response.setModelList(apDocs);
	    			}
	    			
				//response.setData("false");
				//response.setModelData(types);
				//response.setModelList(apDocs);
			}

		
		
		
		/*List<Object> jgNmae = noticeService.getJudgeName(types[8].toString());*/
		
		
		
		
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/getCheckListingAdv/{Caseid}", method = RequestMethod.GET)
	public @ResponseBody String getCheckListingAdv(@PathVariable("Caseid") Integer CaseId,HttpSession session) 
	{
		User user = (User) session.getAttribute("USER");
		
		ActionResponse<CcmsAdvoates> response1 = new ActionResponse<CcmsAdvoates>();
		
		  RestTemplate restTemplate = new RestTemplate();
		  
		  ObjectMapper mapper = new ObjectMapper();

		     // Option 1: ignore unknown fields
		     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        


	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("Caseid", String.valueOf(CaseId)); // send as string if API expects string
	        
	        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
		
		String externalApi="http://192.168.0.114/testapi/API/CaseStatus/AdvocateDetailsByCaseId";
	     
		ResponseEntity<Object> response = restTemplate.postForEntity(
	             externalApi,
	             entity,
	             Object.class
	             
	     );     
	     
	     Object t= response.getBody();
	     
	     CcmsAdvoates cAdv=mapper.convertValue(t, CcmsAdvoates.class);
	     Object[] oPet=cAdv.getPetAdvocate().split(",");
	     Object[] oRes=cAdv.getResAdvocate().split(",");
	     String advParty=null;
	     
	     String  yourValue=user.getUm_fullname()+"("+user.getUsername()+" )";
	     
	     boolean containsAdv=Arrays.asList(oPet).contains(yourValue);
	     if(!containsAdv) {
	    	 containsAdv=Arrays.asList(oRes).contains(yourValue);
	    	 advParty="Res";
	     }
	     else {
	    	 advParty="Pet";
	     }
	     if(containsAdv) {
	    	 response1.setResponse("TRUE");
	    	 response1.setData(advParty);
	    	 response1.setModelData(cAdv);	    	 
	    	 
	     }
	     else {
	    	 response1.setResponse("FALSE");
	     }
	     
	     String jsonData = cm.convert_to_json(response1);
			return jsonData;
		
	}
	
	@RequestMapping(
		    value = "/listingApplication/{Caseid}",
		    method = RequestMethod.GET
		)
		public String listingApplication(
		        @PathVariable("Caseid") Integer CaseId,
		        HttpServletRequest request,HttpSession session) {
		User user = (User) session.getAttribute("USER");
		System.out.println("User==========" +user.getUm_fullname());
		/*request.setAttribute("user", user.getUm_fullname());
		request.setAttribute("role", user.getUsername());*/
		

        RestTemplate restTemplate = new RestTemplate();
        

        String externalApi =
            "http://192.168.0.114/testapi/API/CaseStatus/CaseDetailsByCaseId";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("Caseid", String.valueOf(CaseId)); // send as string if API expects string
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(
                externalApi,
                entity,
                String.class
                
        );       
        
        ObjectMapper mapper = new ObjectMapper();

     // Option 1: ignore unknown fields
     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
     	
     	CaseDetailsCcms caseDetails = null;
     try {
    	 String json = response.getBody();
		caseDetails = mapper.readValue(json, CaseDetailsCcms.class);
	
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     	
        // 5. Print response for debugging
        System.out.println("API Response: " + caseDetails);  
        String caseSplit[] = caseDetails.getDisplayCaseno().split("/");      
               
        request.setAttribute("caseData", caseDetails);
        request.setAttribute("caseSplit", caseSplit);
       // model.addAttribute("caseData", caseDetails);

        return "/listingApplication/listingApplication";
    }

	
	
	@RequestMapping(value = "/getPetDoc/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPetDoc(@PathVariable("id") Long docId,HttpSession session) 
	{
		User user = (User) session.getAttribute("USER");
		ActionResponse<RegisteredCaseDetails> response = new ActionResponse<RegisteredCaseDetails>();
		CaseFileDetail fd=applicationService.getCaseFile(docId);
		String jsonData = "";
		/*Object[] types = noticeService.getCaseDetails("WRIC", 1, 2022);*/
		
		
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		 MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		 
		 map.add("CaseType",fd.getCaseType().getCt_ccms_id().toString());
		 map.add("CaseNumber", fd.getFd_case_no());
		 map.add("CaseYear",fd.getFd_case_year().toString());
		 
		 HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		 
			

		 
		 HttpEntity<Object> response1 =
		     restTemplate.exchange("http://192.168.0.114/testapi/API/CaseStatus/BriefCaseDetailsByTypeNoYear",
		                           HttpMethod.POST,
		                           entity,
		                           Object.class);
		 
		 Object caseIntial=(Object) response1.getBody();
			
		 ObjectMapper m = new ObjectMapper();
		 m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		 CcmsCaseDetails props = m.convertValue(caseIntial, CcmsCaseDetails.class);
		 
		 if(props.getCase_id()!="" && fd.getFd_first_petitioner()!="") {
			 String[] parties = props.getParty_Name().split("\\s+VS");
			 fd.setFd_first_petitioner(parties[0]);
			 fd.setFd_first_respondent(parties[1]);
			 fd.setFd_cino(props.getCINO().trim());
			 fd.setFd_ccms_case_id(Long.parseLong(props.getCase_id().trim()));
			 fd =applicationService.save(fd);
		 }
		 
		 /*if(props.getIsCaseConverted().trim().equals("Y")) {*/
		 if(true) {
			 HttpHeaders headers2 = new HttpHeaders();
			 headers2.setContentType(MediaType.APPLICATION_JSON);
			 
			 String reqString="{\"Cino\":\""+props.getCINO().trim()+"\"}";
			 HttpEntity<String> reqEntity =new HttpEntity<>(reqString,headers2);
			 ResponseEntity<Object> response2 =
				     restTemplate.exchange("http://192.168.0.114/ccmsapi/api/DisplayBoard/GetCaseConversionDetail",
				                           HttpMethod.POST,
				                           reqEntity,
				                           Object.class);
			 /*if(response2.getStatusCode()) {
				 
			 }*/
			 
			 List<Object> caseConv=(List<Object>)response2.getBody();
				
			 ObjectMapper m2 = new ObjectMapper();
			 m2.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			 if(caseConv.size()!=0) {
			 CaseConversionCCmc props2 = m2.convertValue(caseConv.get(0), CaseConversionCCmc.class);
			 CaseType ct=applicationService.getCaseType(Integer.parseInt(props2.getOldregcase_type()));
			 fd.setFd_old_case_type(ct.getCt_id());
			 fd.setFd_old_case_no(props2.getOldreg_no());
			 fd.setFd_old_case_year(Integer.parseInt(props2.getOldreg_year()));
			 
			 
			 CaseFileDetail fdOld=applicationService.getCaseFileOld(fd);
			 
			 if(fdOld!=null) {
				 fd.setFd_id_old(fdOld.getFd_id());
			 }
			 
			 fd=applicationService.save(fd);
			 }
			 
			 
		 }
		
		RegisteredCaseDetails rcd=new RegisteredCaseDetails();
		
		
		 RestTemplate restTemplate1 = new RestTemplate();
		  
		  ObjectMapper mapper = new ObjectMapper();

		     // Option 1: ignore unknown fields
		     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        


	        HttpHeaders headers1 = new HttpHeaders();
	        headers1.setContentType(MediaType.APPLICATION_JSON);
	        
	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("Caseid", String.valueOf(fd.getFd_ccms_case_id())); // send as string if API expects string
	        
	        HttpEntity<Map<String, Object>> entity1 = new HttpEntity<>(requestBody, headers1);
		
		String externalApi="http://192.168.0.114/testapi/API/CaseStatus/AdvocateDetailsByCaseId";
	     
		ResponseEntity<Object> response2 = restTemplate1.postForEntity(
	             externalApi,
	             entity1,
	             Object.class
	             
	     );     
	     
	     Object t= response2.getBody();
	     
	     CcmsAdvoates cAdv=mapper.convertValue(t, CcmsAdvoates.class);
	     Object[] oPet=cAdv.getPetAdvocate().split(",");
	     Object[] oRes=cAdv.getResAdvocate().split(",");
	     String advParty=null;
	     
	     String  yourValue=user.getUm_fullname()+"("+user.getUsername()+" )";
	     
	     boolean containsAdv=Arrays.asList(oPet).contains(yourValue);
	     if(!containsAdv) {
	    	 containsAdv=Arrays.asList(oRes).contains(yourValue);
	    	 advParty="Res";
	     }
	     else {
	    	 advParty="Pet";
	     }
	     if(containsAdv) {
	    	 rcd=applicationService.getPetFile(fd);
				response.setData("TRUE");
				response.setModelData(rcd);
	     }
	     else {
				response.setData("False");
				//response.setModelData(rcd);
			}

		
		

		/*List<Long> u=applicationService.getDocUsers(docId);
		u.addAll(applicationService.getOldCaseDocUsers(fd.getFd_id_old()));
		
		Long petU=applicationService.getPetUser(fd);
		if(petU==null) {
			petU=applicationService.getPetUserOldCase(fd);
		}
		u.add(petU);
		
		if(u.contains(user.getUm_id())) {
			rcd=applicationService.getPetFile(fd);
			response.setData("TRUE");
			response.setModelData(rcd);
		}
		else {
			response.setData("False");
			//response.setModelData(rcd);
		}
		 */
		
		/*List<Object> jgNmae = noticeService.getJudgeName(types[8].toString());*/
		
		
		//response.setModelList(apDocs);
		
		
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/getCaseDetails/{id}", method = RequestMethod.GET)
	public @ResponseBody String getCaseDetails(@PathVariable("id") Long docId) {
		ActionResponse<Object[]> response = new ActionResponse<Object[]>();
		CaseFileDetail fd=applicationService.getCaseFile(docId);
		String jsonData = "";
		/*Object[] types = noticeService.getCaseDetails("WRIC", 1, 2022);*/
		
		Object[] types = applicationService.getCaseDetails(fd.getCaseType().getCt_label(),Integer.parseInt(fd.getFd_case_no()),fd.getFd_case_year());
		
		/*List<Object> jgNmae = noticeService.getJudgeName(types[8].toString());*/
		
		response.setData("TRUE");
		response.setModelData(types);
		/*response.setDataList(jgNmae);*/
		
		
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/getPet/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPet(@PathVariable("id") String cino) {
		ActionResponse<List<Object[]>> response = new ActionResponse<List<Object[]>>();
		String jsonData = "";
		List<Object[]> types = applicationService.getPetCivic(cino);
		
		response.setData("TRUE");
		response.setModelData(types);
		
		
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/getRes/{id}", method = RequestMethod.GET)
	public @ResponseBody String getRes(@PathVariable("id") String cino) {
		ActionResponse<List<Object[]>> response = new ActionResponse<List<Object[]>>();
		String jsonData = "";
		List<Object[]> types = applicationService.getResCivic(cino);
		
		response.setData("TRUE");
		response.setModelData(types);
		
		
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/previewList/{id}", method = RequestMethod.GET)
	public String previewList(Model model, @PathVariable Integer id) {

		model.addAttribute("caseId", id);

		return "/application/applicationpreviewList";
	}

	@RequestMapping(value = "/getRegisterApplication", method = RequestMethod.GET)
	@ResponseBody
	public String getRegisterApplication(HttpServletRequest request) {

		String id = request.getParameter("docId");
		Application cav = null;

		Long doc = new Long(id);
		ActionResponse<Application> response = new ActionResponse<Application>();
		String jsonData = null;

		cav = applicationService.getRegisterApplication(doc);

		if (cav != null) {
			response.setResponse("TRUE");
			response.setModelData(cav);
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	
	public void createNoticePdf(Application officeRpt,String path ) throws IOException {
		 Document doc=new Document();
		 
		 HTMLWorker htmlWorker = new HTMLWorker(doc);
		 
			Font underlin =new Font(Font.FontFamily.HELVETICA  , 16, Font.BOLDITALIC);
			 
			 PdfWriter writer;
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
			
			 
			 doc.open();
			/* Paragraph title=new Paragraph(Font.BOLDITALIC,"Office Report");
			 title.setFont(underlin);
			
			 title.setAlignment(Element.ALIGN_CENTER);*/
			 
			 Chunk title1=new Chunk("IN THE HIGH COURT OF JUDICATURE AT ALLAHABAD");
	          
			 //title1.setUnderline(0.1f, -2f);
			 
			 Paragraph title=new Paragraph(title1);
			 title.setAlignment(Element.ALIGN_CENTER);
			 
			 
			 
			 Chunk title2=new Chunk("*******************");
	          
			 //title1.setUnderline(0.1f, -2f);
			 
			 Paragraph title3=new Paragraph(title2);
			 title3.setAlignment(Element.ALIGN_CENTER);
			 //title.setFont(underlin);
			 
			 
			doc.add(title);		
			doc.add(title3);	
			 Paragraph pr=new Paragraph("LISTING APPLICATION NO ---- OF 2023");
			 pr.setAlignment(Element.ALIGN_CENTER);
			 title3.setAlignment(Element.ALIGN_CENTER);
			doc.add(pr);
			
			/*doc.add(Chunk.NEWLINE);
			doc.add(Chunk.NEWLINE);*/
			
			 Paragraph desc=new Paragraph("To,");
			 desc.setAlignment(Element.ALIGN_LEFT);
			 
			 doc.add(desc);		
			
			htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getAp_lstng_desc()));
			
			
			 Chunk prayer=new Chunk("Prayer");
	          
			 //title1.setUnderline(0.1f, -2f);
			 
			 Paragraph prayer1=new Paragraph(prayer);
			 prayer1.setAlignment(Element.ALIGN_CENTER);
			 //title.setFont(underlin);
			 
			 
			doc.add(prayer1);		
			
			htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getAp_lstng_prayer()));
			 
			// doc.add(new Paragraph("\n\n"+officeRpt.getOrd_remark()));
			 
			 doc.add(Chunk.NEWLINE);
			 doc.add(new Chunk().NEWLINE);
			 doc.add(new Chunk().NEWLINE);
/*				 Paragraph dated=new Paragraph(Font.BOLDITALIC,"Dated");
			 dated.add("\n\n\n"+officeRpt.getOrd_created());
			 dated.setAlignment(Element.ALIGN_LEFT);
			 
			 doc.add(dated);
			 
			
			
			 
			 
			 User createdUser=usermaster.getByuserid(officeRpt.getOrd_created_by());
			 Paragraph createdBy=new Paragraph(Font.BOLDITALIC,"Author");
			 createdBy.add("\n\n\n"+createdUser.getUm_fullname());
			 createdBy.setAlignment(Element.ALIGN_RIGHT);
			 
			 doc.add(createdBy);*/
			 
			/* User createdUser=usermaster.getByuserid(officeRpt.getOrd_created_by());*/
			 Chunk glue = new Chunk(new VerticalPositionMark());
			 Paragraph p = new Paragraph(Font.BOLDITALIC,"Dated");
			 //p.add("\n\n\n"+officeRpt.getOrd_created());
			 
			 
			 
			 
			 p.add(new Chunk(glue));
			 p.add("Submitted By");
			 doc.add(p);
			 //String createdDate=DateFormat.getDateInstance().format(officeRpt.getAp_lstng_desc());
			 Paragraph p1 = new Paragraph("25-05-2023");
			 p1.add(new Chunk(glue));
			 p1.add("sushant");
			
			 doc.add(p1);
			 
			 
			 
			 doc.close();
			 writer.close();
			 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	@RequestMapping(value = "/addApplication", method = RequestMethod.POST)
	@ResponseBody
	/*public String addRegisterCase(@RequestBody Application application,HttpSession session) 
	{*/
	public String addRegisterApplication(@RequestBody Application bindedEntity,HttpSession session) 
	{
		User user = (User) session.getAttribute("USER");
		ActionResponse<Application> rd = new ActionResponse<Application>();
		String jsonData = null;
		Application application;
		
		Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DRAFT");
		
		if(bindedEntity.getAp_id()==null)
		{			
			bindedEntity.setAp_cr_by(user.getUm_id());
			bindedEntity.setAp_assign_to(user.getUm_id());
			bindedEntity.setAp_cr_date(new Date());
			bindedEntity.setAp_stage_lid(lkStage.getLk_id());
			
			/* application=applicationService.addApplication(bindedEntity);*/
			 
		/*	 for (int i = 0; i < bindedEntity.getOtherAppNos().size(); i++) 
				{
					if (bindedEntity.getOtherAppNos().get(i).getAt_id() != null) 
					{
						applicationService.saveSubApplication(application,
								bindedEntity.getOtherAppNos().get(i));
					}
				}*/
			application=applicationService.addApplication(bindedEntity);
			 
			 for (int i = 0; i < bindedEntity.getSubApplication().size(); i++) 
				{
					if (bindedEntity.getSubApplication().get(i).getSb_ap_at_mid()!= null) 
					{
						application.getSubApplication().get(i).setSb_ap_mid(application.getAp_id());
						application.getSubApplication().get(i).setSb_ap_cr_date(new Date());
						/*applicationService.saveSubApplication(application,
								bindedEntity.getSubApplication().get(i))*/;
					}
				}
			 
			Application application1=applicationService.save(application);
			
			application1=applicationService.getRegisterApplication(application1.getAp_id());
			
           /* AllowEfiling allowEfiling =ecourtAddCaseService.codeValidation(bindedEntity.getCode(),bindedEntity.getAppno(),'A');
            
            allowEfiling.setAe_fd_mid(application1.getAp_fd_mid());
			
			allowEfiling.setAe_reference_mid(application1.getAp_id());
			ecourtAddCaseService.addAllowEfiling(allowEfiling);*/
			
			rd.setResponse("ADD");	
			rd.setModelData(application1);
			
			ApplicationStage as=new ApplicationStage();
			as.setAs_ap_mid(application.getAp_id());
			as.setAs_stage_lid(lkStage.getLk_id());
			as.setAs_cr_by(user.getUm_id());
			as.setAs_cr_date(new Date());
			
			try {
				createNoticePdf(application,"D://notice.pdf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			applicationService.saveApplicationStage(as);
		}else
			{
			rd.setResponse("UPDATE");
			 for (int i = 0; i < bindedEntity.getSubApplication().size(); i++) 
				{
					if (bindedEntity.getSubApplication().get(i).getSb_ap_at_mid()!= null) 
					{
						bindedEntity.getSubApplication().get(i).setSb_ap_cr_date(new Date());
					/*	SubApplication subapp=new SubApplication();
						
						subapp.setSb_ap_mid(bindedEntity.getAp_id());
						subapp.setSb_ap_cr_date(new Date());
						subapp.setSb_ap_rec_status(1);
						subapp.setSb_ap_at_mid(bindedEntity.getSubApplication().get(i).getSb_ap_at_mid());*/
						//applicationService.saveSubApp(bindedEntity.getSubApplication().get(i));
						/*applicationService.saveSubApplication(bindedEntity,
								bindedEntity.getSubApplication().get(i));*/
					}
					
				}
			 
				application=applicationService.save(bindedEntity);
				applicationService.deleteNullSubApplication(application.getAp_id());
				rd.setModelData(application);
				try {
					createNoticePdf(application,"D://notice.pdf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		jsonData = cm.convert_to_json(rd);

		return jsonData;
	}
	
	
	@RequestMapping(value = "/deleteSubApplication/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteSubApplication(@PathVariable Long id, HttpSession session) 
	{
		ActionResponse<Application> response = new ActionResponse<>();
		
		boolean sb= applicationService.deleteSubApplication(id);
		
		String jsonData = null;

		if (sb != false) {
			
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	
	
	@RequestMapping(value = "/getApplicationDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getApplicationDetails(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<Application> newApplicationList= applicationService.getApplicationDetails(user.getUm_id());
		ActionResponse<Application> response = new ActionResponse<Application>();


		if (newApplicationList != null && newApplicationList.size() != 0)
			response.setResponse("TRUE");
		//response.setData(draftcount);
		response.setModelList(newApplicationList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/getPassedApplicationDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getPassedApplicationDetails(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<Application> newApplicationList= applicationService.getPassedApplicationDetails(user.getUm_id());
		ActionResponse<Application> response = new ActionResponse<Application>();


		if (newApplicationList != null && newApplicationList.size() != 0)
			response.setResponse("TRUE");
		//response.setData(draftcount);
		response.setModelList(newApplicationList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/getDefectedApplicationDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getDefectedApplicationDetails(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<Application> newApplicationList= applicationService.getDefectApplicationDetails(user.getUm_id());
		ActionResponse<Application> response = new ActionResponse<Application>();


		if (newApplicationList != null && newApplicationList.size() != 0)
			response.setResponse("TRUE");
		//response.setData(draftcount);
		response.setModelList(newApplicationList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/addCourtFee", method = RequestMethod.POST)
	@ResponseBody
	public String addCourtFee(@RequestBody ApplicationCourtFee courtFee,HttpSession session) {

		ApplicationCourtFee ccf = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<ApplicationCourtFee> response = new ActionResponse<ApplicationCourtFee>();
		String jsonData = null;

		if (courtFee.getAcf_id() != null) {
			response.setResponse("UPDATE");
		} else {
			courtFee.setAcf_cr_date((new Date()));
			courtFee.setAcf_rec_status(1);
			response.setResponse("CREATE");
		}
		ccf = applicationService.addCourtFee(courtFee);

		if (ccf != null)
			jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/getCourtFee", method = RequestMethod.GET)
	@ResponseBody
	public String getCourtFee(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<ApplicationCourtFee> response = null;

		Long doc = new Long(id);
		ActionResponse<ApplicationCourtFee> pd = new ActionResponse<ApplicationCourtFee>();
		String jsonData = null;

		response = applicationService.getApplicationCourtFee(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	@RequestMapping(value="/getUploadedDocuments", method=RequestMethod.GET)
	@ResponseBody
	public String getUploadedDocuments(HttpServletRequest request)
	{
		String jsonData=null;
		String file_id=request.getParameter("ap_id");
		Long rcd_id=Long.valueOf(file_id);
		
		List<ApplicationUploaded> documentList=applicationService.getUploadedApplications(rcd_id);
		ActionResponse<ApplicationUploaded> response=new ActionResponse<ApplicationUploaded>();
		
		response.setModelList(documentList);
		response.setResponse("TRUE");
		
	     jsonData=cm.convert_to_json(response);
	     return jsonData;
	}

	@RequestMapping(value = "/upload_file",method = RequestMethod.POST)
	public @ResponseBody String create(MultipartHttpServletRequest request,HttpSession session,HttpServletRequest req) throws DocumentException 
	{
		
		String jsonData="";

		ActionResponse<ApplicationUploaded>response=new ActionResponse<ApplicationUploaded>();
		response.setResponse("TRUE");
		String ipaddress = request.getRemoteAddr();
		Application app= new Application();
		
		
		User user=new User();
		user=(User) session.getAttribute("USER");
		
		String file_id=req.getParameter("ap_id");
		Long app_id=Long.valueOf(file_id);
		
		app=applicationService.getByPk(app_id);
		
		String documentname = app.getAp_draft_no();
		
		 MultipartFile mpf = null;
		 Iterator<String> itr = request.getFileNames();
		     
		     
		 String basePath="";
		 List<Lookup> lookupForRaw = lookupService.getAll("APPLICATION_PATH");
		 String applicationBasepath =lookupForRaw.get(0).getLk_longname();	
			
		List <Object> errorList=new ArrayList();

				while (itr.hasNext()) 
				{
					try
					{
					mpf = request.getFile(itr.next());
			        
					
					
					ApplicationUploaded au = new ApplicationUploaded();
				
					String filename = mpf.getOriginalFilename();  
					
			        String temppath=applicationBasepath + File.separator+documentname+".pdf";
	                
					String ext = FilenameUtils.getExtension(filename);
					
			
					  if(ext.equalsIgnoreCase("pdf"))
						{	
						  	FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(temppath));
						    
						    PdfReader readernewFile = new PdfReader(temppath);
							Integer newPageCount =readernewFile.getNumberOfPages();
							readernewFile.close();
					    
					        au.setAu_ap_mid(app.getAp_id());
					       	au.setAu_document_name(documentname+".pdf");
					       	au.setAu_no_of_pages(newPageCount);
					       	au.setAu_uploaded_date(new Date());
					       	au.setAu_rec_status(1);
					       	
					       	
					       	au=applicationService.saveApplicationDocument(au);
						     
					       	response.setResponse("TRUE");
					       	response.setData(au);

						}
						 else 
						 {
							errorList.add(" Please Upload PDF file...!");
							response.setResponse("FALSE");
						 }	
				}
					catch (IOException e) {
					e.printStackTrace();
				}
			 			
		 }
		  
				//response.setDataMapList(error);
				response.setDataList(errorList);
				if(response != null)
				{
					jsonData = cm.convert_to_json(response);
				}

		
		return jsonData;

	}
	@RequestMapping(value = "/submitApplication", method = RequestMethod.POST)
	@ResponseBody
	public String submitRegisterApplication(@RequestBody Application application,HttpSession session,HttpServletRequest request) {
		String jsonData="";
		ActionResponse<Application> response=new ActionResponse<Application>();
		User user = (User) session.getAttribute("USER");
		boolean submit=true;
		boolean flag=false;
		UserRole ur=null;
		Long appUser=null;
		List<ApplicationCourtFee> courtFees = null;
		courtFees = applicationService.getCourtFee(application.getAp_id());
		List<ApplicationUploaded> applicationUploaded = null;
		applicationUploaded = applicationService.getUploadedApplications(application.getAp_id());
		
		if(applicationUploaded.isEmpty())
		{
			submit=false;
		}
		if(submit)
		{
			if(application.getAp_diary_no()==null){
			Long diary=applicationService.getDiarySequence();
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			
			application.setAp_diary_no(diary+"_"+year);
			}
			
			 
			if(application.getAp_at_mid()==3L || application.getAp_at_mid()==25L || application.getAp_at_mid()==42L || application.getAp_at_mid()==46L)
			
			{
				  ur=userRoleService.getByUserRole("BSApplicationScrutiny");
				  appUser=ur.getUr_um_mid();
			  }
			/*if (false) {
				
			}*/
			else{
				
				// ur=userRoleService.getByUserRole("ApplicationScrutiny");
				appUser=440L;
				
			}
			if(appUser!=null)
			{
				if(application.getCaseStage().getLk_longname().equals("DRAFT"))
				 {
					Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DIARY_NO_GENERATED");
								
					ApplicationStage cs=new ApplicationStage();
					cs.setAs_ap_mid(application.getAp_id());
					cs.setAs_stage_lid(lkStage.getLk_id());
					cs.setAs_cr_by(user.getUm_id());
					cs.setAs_cr_date(new Date());
					
					applicationService.saveApplicationStage(cs);
					
					application.setAp_assign_to(appUser);
					application.setAp_stage_lid(lkStage.getLk_id());
					application=applicationService.save(application);
					
					
					InetAddress ip;
					 String hostname;
					 String  extraLko="";
					 
					 String otpTmpId="";
					 
				        try {
				            ip = InetAddress.getLocalHost();
				            hostname = ip.getHostAddress();
				            System.out.println("Your current IP address : " + ip);
				            System.out.println("Your current Hostname : " + hostname);
				            
				            if(hostname.equals("172.16.0.6")) {
				            	otpTmpId ="1107163706957402432";
				            }
				            else if(hostname.equals("127.0.0.1")) {
				            	/*otpTmpId ="1107160793982323688";
				            	 extraLko="-Lko. Bench ";*/
				            	otpTmpId ="1107163706957402432";
					            }
				            else {
				            	System.out.println("In Local");
				            	otpTmpId ="1107163706957402432";
				            	/* extraLko="-Lko. Bench ";*/
				            }
				 
				        } catch (UnknownHostException e) {
				 
				            e.printStackTrace();
				        }
				        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				        if(user.getUm_id() != null)
						{
						/*	 Integer otp=cm.generateOTP();
							 user.setUm_otp(otp);
							 user=userService.save(user);*/
							 Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
							 String sms_url=urlLookup.getLk_longname();
							 String mob_no=user.getUm_mobile();
							 String smstext="Your e-Filed Application having Diary_No  "+application.getAp_diary_no()+", Applicant name - "
							 +(application.getAp_applicant_name().length() >=30 ? application.getAp_applicant_name().substring(0,26)+"..." :application.getAp_applicant_name())+" "
							 		+ "has been received on  "+formatter.format(cs.getAs_cr_date())+" ";
							 smstext=smstext.replace("&", "and");
							 String otpresponse=cm.sendBSNLSMS(sms_url, mob_no, smstext,otpTmpId);
						     String otpresponse1=cm.sendSMS(sms_url, mob_no, smstext,otpTmpId);
							// String otpresponse="1";
							 if(otpresponse.equals("1"))
							 {
								 user.setUm_otp(null);
								 
								
								 
								 SmsLog smslog = new SmsLog();
								 smslog.setSl_mobile_no(mob_no);
								 smslog.setSl_um_mid(user.getUm_id());
								 smslog.setSl_text(smstext+" BSNL");
								 smslog.setSl_cr_date(new Date());
								 smslog.setSl_status(1);
								 smslog.setSl_ip_address(request.getRemoteAddr());
								 userService.saveSMSlog(smslog);
								 
							 }else{
									response.setResponse("FALSE");
									response.setData("Unable to send OTP, please try again");
								}
									
						}
					
					
					response.setResponse("TRUE");
					response.setModelData(application);
					jsonData=cm.convert_to_json(response);
				 }
				 else if(application.getCaseStage().getLk_id().equals(1000041L))
				 {
					 /*if(application.getAp_at_mid()==3L || application.getAp_at_mid()==25L || application.getAp_at_mid()==42L || application.getAp_at_mid()==46L)
					 {
						 flag=applicationService.checkBsDateValidity(application.getAp_id());
					 }*/
					 if (false) {
						
					}
					 else
					 {
						 flag=applicationService.checkDateValidity(application.getAp_id()); 
						 
					 }
						if(flag)
						{
							Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DIARY_NO_GENERATED");
							
							ApplicationStage cs=new ApplicationStage();
							cs.setAs_ap_mid(application.getAp_id());
							cs.setAs_stage_lid(lkStage.getLk_id());
							cs.setAs_cr_by(user.getUm_id());
							cs.setAs_cr_date(new Date());
							
							applicationService.saveApplicationStage(cs);
							
							application.setAp_assign_to(appUser);
							application.setAp_stage_lid(lkStage.getLk_id());
							application=applicationService.save(application);
							
							response.setResponse("TRUE");
							response.setModelData(application);
							jsonData=cm.convert_to_json(response);
						 }
						else
						{
							response.setResponse("FALSE");
							response.setData("Permission Denied");
							jsonData=cm.convert_to_json(response);
						}	
					
				 }
			}
			else
			{
				response.setResponse("FALSE");
				response.setData("Scrutiny user is not available");
				jsonData=cm.convert_to_json(response);
			}
		}
		else
		{
			response.setResponse("FALSE");
			response.setData("Please submit all the required fields");
			jsonData=cm.convert_to_json(response);
		}
		return jsonData;
	}
	
	
	
	
	
	
	@RequestMapping(value ="/deleteDocument/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public  String deleteDocument(@PathVariable Long id,HttpSession session)
	{	
			ActionResponse<ApplicationUploaded> pd =new ActionResponse<ApplicationUploaded>();
			String jsonData=null;

				ApplicationUploaded au=applicationService.getApplicationUploaded(id);
						
				List<Lookup> lookupForRaw = lookupService.getAll("APPLICATION_PATH");
				String applicationBasepath =lookupForRaw.get(0).getLk_longname();
				
				SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
				String currentDate=sdf.format(new Date());
				
				String oldpath=applicationBasepath + File.separator+au.getAu_document_name();
				File oldfile=new File(oldpath);
				String newname=FilenameUtils.getBaseName(oldpath);
				String extension=FilenameUtils.getExtension(oldpath);
				String newpath=applicationBasepath + File.separator+newname+"_"+currentDate+"."+extension;
				File newfile=new File(newpath);
				boolean response=false; 
				
				if(oldfile.renameTo(newfile)){
					response=applicationService.deleteApplication(id);
				}
				
			
			
			if(response)
			{
				pd.setResponse("TRUE");
			}
			else
			{
				pd.setResponse("FALSE");
			}			
			jsonData= cm.convert_to_json(pd);
					
			return jsonData;
	}
	
	@RequestMapping(value = "/getApplicationTypes", method = RequestMethod.GET)
	@ResponseBody
	public String getApplicationTypes(HttpServletRequest request) {

		List<ApplicationTypes> app = null;

		ActionResponse<ApplicationTypes> response = new ActionResponse<ApplicationTypes>();
		String jsonData = null;

		app = applicationService.getApplicationTypes();

		if (app != null) {
			response.setResponse("TRUE");
			response.setModelList(app);
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	@RequestMapping(value = "/getApplicationCheckList", method = RequestMethod.GET)
	@ResponseBody
	public String getApplicationCheckList(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<ApplicationCheckListMapping> mapping = null;

		Long doc = new Long(id);
		ActionResponse<ApplicationCheckListMapping> response = new ActionResponse<ApplicationCheckListMapping>();
		String jsonData = null;

		mapping = applicationService.getApplicationCheckList(doc);

		if (mapping != null) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	@RequestMapping(value = "/getCheckList", method = RequestMethod.GET)
	@ResponseBody
	public String getCheckList(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<ApplicationCheckListMapping> mapping = null;
		List<BSApplicationCheckListMapping> bsmapping = null;

		Long doc = new Long(id);
		ActionResponse<ApplicationCheckListMapping> response = new ActionResponse<ApplicationCheckListMapping>();
		ActionResponse<BSApplicationCheckListMapping> bsresponse = new ActionResponse<BSApplicationCheckListMapping>();
		String jsonData = null;

		mapping = applicationService.getApplicationCheckList(doc);
		 if(mapping.isEmpty()){
			 
			 bsmapping=applicationService.getBSApplicationCheckList(doc);
		 }
		

		if (!mapping.isEmpty()) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		else{
			bsresponse.setModelList(bsmapping);
			bsresponse.setResponse("TRUE");
			jsonData = cm.convert_to_json(bsresponse);

			
		}
		return jsonData;

	}
	@RequestMapping(value="/copyApplicationFile",method=RequestMethod.GET)
	@ResponseBody
	public String copyApplicationFile(HttpServletRequest request) throws ParseException
	{
		/*String jsonData = null;
		
		String doc_name=request.getParameter("au_document_name");
		
		ActionResponse<IndexField> response= new ActionResponse<IndexField>();
		
	
		Lookup lookUp=lookupService.getLookUpObject("APPLICATION_PATH");
		
		
		String draft_path=lookUp.getLk_longname();
		System.out.println("pppppppppppppppppppppp--"+draft_path);
		
		File source = new File(draft_path+File.separator+doc_name);
		
		
		String uploadPath = context.getRealPath("");
		
		
		
		doc_name="appl_"+doc_name;
		File dest = new File(uploadPath+"/uploads/"+doc_name);

		try {
			    FileUtils.copyFile(source, dest);
			    response.setResponse("TRUE");
			    response.setData(doc_name);
			} 
			catch (IOException e) {
			    e.printStackTrace();
			    response.setResponse("FALSE");
			}
		jsonData = cm.convert_to_json(response);
		return jsonData;*/
		

		String jsonData = null;
		
		String doc_name=request.getParameter("au_document_name");
		
		ActionResponse<IndexField> response= new ActionResponse<IndexField>();
		
		ApplicationUploaded au=scrutinyService.getApplicationUploaded(doc_name);
	
		/*Lookup lookUp=lookupService.getLookUpObject("APPLICATION_PATH");	
		String draft_path=lookUp.getLk_longname();

		File source = new File(draft_path+File.separator+doc_name);	*/
		String uploadPath = context.getRealPath("");
		
		
		
		Lookup lookUp=lookupService.getLookUpObject("APPLICATION_PATH");	
		String draft_path=lookUp.getLk_longname();	
		
		Lookup lookUpBck=lookupService.getLookUpObject("APPLICATION_BCKUP_PATH");	
		
		String draft_path_bck=lookUpBck.getLk_longname();

		File source =null;
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//	 String bef=sdf.format("2020-01-01 00:00:00");
		        
		        Date dateBef = sdf.parse("2020-07-01 00:00:00");
			
			if(au.getAu_uploaded_date().before(dateBef)) {
				source = new File(draft_path_bck+File.separator+doc_name);	
			}
			else {
				source = new File(draft_path+File.separator+doc_name);	
			}
		
			doc_name="appl_"+doc_name;
		File dest = new File(uploadPath+"/uploads/"+doc_name);

		try {
			    FileUtils.copyFile(source, dest);
			    response.setResponse("TRUE");
			    response.setData(doc_name);
			} 
			catch (IOException e) {
			    e.printStackTrace();
			    response.setResponse("FALSE");
			}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	
	}
	
	@RequestMapping(value = "/getApplicationStages", method = RequestMethod.GET)
	public @ResponseBody String getCaseStages(HttpServletRequest request) {
		String jsonData="";
		String id = request.getParameter("docId");
		Long docId = new Long(id);
		ActionResponse<ApplicationStage> response=new ActionResponse<ApplicationStage>();
		List<ApplicationStage> stages=applicationService.getStages(docId);
		response.setData("TRUE");
		response.setModelList(stages);
		jsonData=cm.convert_to_json(response);
		return jsonData;	
	}

}
