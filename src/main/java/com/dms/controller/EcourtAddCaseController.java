package com.dms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dms.model.ActDetails;
import com.dms.model.ActionResponse;
import com.dms.model.AllowEfiling;
import com.dms.model.AllowEfiling1;
import com.dms.model.ApplicationCourtFee;
import com.dms.model.CaseAuthority;
import com.dms.model.CaseFileStage;
import com.dms.model.CaseType;
import com.dms.model.CaveatAuthority;
import com.dms.model.CaveatCourtFee;
import com.dms.model.CourtFee;
import com.dms.model.CrimeDetails;
import com.dms.model.ImpugnedOrder;
import com.dms.model.IndexField;
import com.dms.model.LinkedCaseDetails;
import com.dms.model.Lookup;
import com.dms.model.LowerCourtCaseType;
import com.dms.model.Notice;
import com.dms.model.NoticeDepartmentMaster;
import com.dms.model.NoticeValidation;
import com.dms.model.PetitionUploaded;
import com.dms.model.PetitionerDetails;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.RespondentDetails;
import com.dms.model.SmsLog;
import com.dms.model.StNoDetails;
import com.dms.model.StampReporterData;
import com.dms.model.TrialCourt;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.CaseFileService;
import com.dms.service.CaseFileStageService;
import com.dms.service.EcourtAddCaseService;
import com.dms.service.EcourtHomeService;
import com.dms.service.LookupService;
import com.dms.service.TmpDocumentSubPartsService;
import com.dms.service.UserRoleService;
import com.dms.service.UserService;
import com.dms.utility.GlobalFunction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
@Controller
@RequestMapping("/ecourt_add_case")
public class EcourtAddCaseController {
	private GlobalFunction cm;
	static int seq = 0;

	@Autowired
	private EcourtHomeService ecourtHomeService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CaseFileStageService caseFileStageService;
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private EcourtAddCaseService ecourtAddCaseService;
	
	@Autowired
	private CaseFileService caseFileService;
	
	@Autowired
	private LookupService lkservice;
	
	@Autowired
	private TmpDocumentSubPartsService tmpDocumentSubPartsService;
	
	public EcourtAddCaseController() {
		cm = new GlobalFunction();
	}

	@RequestMapping(value = "/addCaseDetail", method = RequestMethod.GET)
	public String ecourtHome() {

		return "/ecourt/addCase";
		/*return "/ecourt/allowCase";*/
	}
	
	@RequestMapping(value = "/getFileSize", method = RequestMethod.GET)
	@ResponseBody
	public String getFileSize() {
	String jsonData = null;

	Lookup fileSizeObject =lkservice.getLookUpObject("FILE_SIZE");
	ActionResponse<Lookup> response = new ActionResponse<Lookup>();

	response.setModelData(fileSizeObject);

	jsonData = cm.convert_to_json(response);

	return jsonData;
	}

	@RequestMapping(value = "/draftView/{id}", method = RequestMethod.GET)
	public String draftView(Model model, @PathVariable Long id,HttpSession session) {
		User u=(User) session.getAttribute("USER");
		
		String viewname="/content/access_denied";
		String message="";
		RegisteredCaseDetails rcd = ecourtAddCaseService.getRegisterCase(id);
		if(rcd!=null){
			if(u.getUm_id().longValue()==rcd.getRcd_cr_by().longValue()){
				if(rcd.getCaseStage().getLk_longname().equals("DRAFT"))
				{
					viewname="/ecourt/addCase";					
				}else if(rcd.getCaseStage().getLk_id().equals(1000041L))
				{
					boolean flag=caseFileService.checkDateValidity(rcd.getRcd_id());
					if(flag)
					{
						viewname="/ecourt/addCase";
					}
					else
					{
						message="Correction Period expired.";
						viewname="/content/access_denied";
					}											
				}
				else{
					message="This file is not available for editing";
					viewname="/content/access_denied";
				}
			}else{
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
	
	@RequestMapping(value = "/validateNotice", method = RequestMethod.GET)
	@ResponseBody
	public String validateNotice(HttpServletRequest request,HttpSession session) {
		User user = (User) session.getAttribute("USER");
		String jsonData = "";
		String id = request.getParameter("docId");
		Object test=null;
		
		RestTemplate restTemp=new RestTemplate();
		 
		// create headers
		
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		 
		 NoticeValidation nv=new NoticeValidation();
		 nv.setNv_req(id);
		 nv.setNv_cr_by(user.getUm_id());
		 nv.setNv_cr_date(new Date());
		 
		 String result=null;
		 ActionResponse<Object> response = new ActionResponse<Object>();
		
		String url=
				"https://e-manu.up.gov.in/api/get-details-by-notice-number?api_key=4MAiH53dpHXQ2RFNMKanaXuOITVRakKKh7BA0aBQ2g1gDh64Itl1hzgje3PG&noticeno="+id+"&est_code=UPHC01";
		
		 try {
			 
			 
		        HttpEntity<String> entity = new HttpEntity<String>(headers);
		        /*ResponseEntity< com.pdms.model.CaseFileDetail> res = restTemp.exchange(url, HttpMethod.GET, entity,  com.pdms.model.CaseFileDetail.class);*/
		        ResponseEntity< Object> res = restTemp.exchange(url, HttpMethod.GET, entity,  Object.class);
		       /* cfdPdms=res.getBody();*/
		         test=res.getBody();
		        
		         
		         ObjectMapper m1 = new ObjectMapper();
		 }
		 catch (Exception e) {
			result="api fail";
			response.setData(e);
			jsonData = cm.convert_to_json(response);
		}
		
		
		if (test != null) {
			response.setResponse("TRUE");
			response.setData(test);
			jsonData = cm.convert_to_json(response);
			 nv.setNv_res(test.toString());
			 nv=caseFileService.save(nv);
			 

		}
		return jsonData;
	}
	
	
	@RequestMapping(value = "/getAuthority", method = RequestMethod.GET)
	@ResponseBody
	public String getAuthority(HttpServletRequest request) {
		String jsonData = "";
		String id = request.getParameter("docId");
		Long doc = new Long(id);
		List<CaseAuthority> impugnedOrderList = caseFileService.getAuthorityDetails(doc);
		ActionResponse<CaseAuthority> response = new ActionResponse<CaseAuthority>();
		
		if (impugnedOrderList != null) {
			response.setResponse("TRUE");
			response.setModelList(impugnedOrderList);
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;
	}
	
	@RequestMapping(value = "/addCaseAuthority", method = RequestMethod.POST)
	@ResponseBody
	public String addCaseAuthority(@RequestBody CaseAuthority caseAuthority,
			HttpSession session) {

		CaseAuthority response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<CaseAuthority> rd = new ActionResponse<CaseAuthority>();
		String jsonData = null;

		if (caseAuthority.getCau_id() != null) {
			rd.setData("Update");
			caseAuthority.setCau_mod_by(user.getUm_id());
			caseAuthority.setCau_mod_date((new Date()));
		} else {
			caseAuthority.setCau_cr_by(user.getUm_id());
			caseAuthority.setCau_cr_date((new Date()));
			caseAuthority.setCau_rec_status(1);
			rd.setResponse("TRUE");

		}
		response = caseFileService.addCaseAuthority(caseAuthority);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}
	

	@RequestMapping(value = "/getPetitionerDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getPetitionerDetails() {
		String jsonData = null;

		List<PetitionerDetails> petitionerList = ecourtAddCaseService
				.getPetionerDetails();
		ActionResponse<PetitionerDetails> response = new ActionResponse<PetitionerDetails>();

		response.setModelList(petitionerList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/addPetitioner", method = RequestMethod.POST)
	@ResponseBody
	public String addPetitioner(@RequestBody PetitionerDetails pDetails,
			HttpSession session) {

		PetitionerDetails response = null;
		String result = "false";
		User user = (User) session.getAttribute("USER");
		ActionResponse<PetitionerDetails> pd = new ActionResponse<PetitionerDetails>();
		Long seqcount = ecourtAddCaseService.getSequenceCount(pDetails.getPt_rcd_mid());
						ecourtHomeService.getFirstPetitioner(pDetails.getPt_rcd_mid());
		String jsonData = null;
		if (pDetails.getPt_id() != null) 
		{
			pd.setData("Update");
			pDetails.setPt_mod_by(user.getUm_id());
			pDetails.setPt_mod_date((new Date()));
		}
		else 
		{
			pDetails.setPt_cr_by(user.getUm_id());
			pDetails.setPt_cr_date((new Date()));
			pDetails.setPt_rec_status(1);
			seqcount++;
			pDetails.setPt_sequence(seqcount);
			pd.setResponse("TRUE");

		}
			response = ecourtAddCaseService.addPetitioner(pDetails);
		if (response != null)
			jsonData = cm.convert_to_json(pd);

		return jsonData;
	}

	@RequestMapping(value = "/deletePetitioner/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deletePetitioner(@PathVariable Long id, HttpSession session) {

		PetitionerDetails response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<PetitionerDetails> pd = new ActionResponse<PetitionerDetails>();
		String jsonData = null;

		response = ecourtAddCaseService.deletePetitioner(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getRespondentDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getRespondentDetails() {
		String jsonData = null;

		List<RespondentDetails> respondentList = ecourtAddCaseService.getRespondentDetails();
		ActionResponse<RespondentDetails> response = new ActionResponse<RespondentDetails>();
		response.setModelList(respondentList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/addRespondent", method = RequestMethod.POST)
	@ResponseBody
	public String addRespondent(@RequestBody RespondentDetails rDetails,
			HttpSession session) {

		RespondentDetails response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<RespondentDetails> rd = new ActionResponse<RespondentDetails>();
		Long seq = ecourtAddCaseService.getSeqCount(rDetails.getRt_rcd_mid());
		String jsonData = null;

		if (rDetails.getRt_id() != null) {
			rd.setData("Update");
			rDetails.setRt_mod_by(user.getUm_id());
			rDetails.setRt_mod_date((new Date()));
		} else {
			rDetails.setRt_cr_by(user.getUm_id());
			rDetails.setRt_cr_date((new Date()));
			rDetails.setRt_rec_status(1);
			seq++;
			rDetails.setRt_sequence(seq);
			rd.setResponse("TRUE");
		}
		response = ecourtAddCaseService.addRespondent(rDetails);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}

	@RequestMapping(value = "/deleteRespondent/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteRespondent(@PathVariable Long id, HttpSession session) {

		RespondentDetails response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<RespondentDetails> pd = new ActionResponse<RespondentDetails>();
		String jsonData = null;

		response = ecourtAddCaseService.deleteRespondent(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getImpugnedOrder", method = RequestMethod.GET)
	@ResponseBody
	public String getImpugnedOrder() {
		String jsonData = null;

		List<ImpugnedOrder> impugnedOrderList = ecourtAddCaseService.getImpugnedOrder();
		ActionResponse<ImpugnedOrder> response = new ActionResponse<ImpugnedOrder>();
		List<ImpugnedOrder> newImpugnedOrderList = new ArrayList<ImpugnedOrder>();
		for(ImpugnedOrder io:impugnedOrderList){
			ImpugnedOrder temp=new ImpugnedOrder();
			if(temp.getIo_hc_case_type()!=null){
				CaseType ct=ecourtAddCaseService.getCaseTypeById(temp.getIo_hc_case_type());
				temp.setHcCaseType(ct);
			}
			if(temp.getIo_hc_case_type()!=null){
				LowerCourtCaseType lc=ecourtAddCaseService.getLCCaseTypeById(temp.getIo_hc_case_type());
				temp.setLcCaseType(lc);
			}
			newImpugnedOrderList.add(temp);
		}
		response.setModelList(newImpugnedOrderList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/addImpugnedOrder", method = RequestMethod.POST)
	@ResponseBody
	public String addImpugnedOrder(@RequestBody ImpugnedOrder imOrder,
			HttpSession session) {

		ImpugnedOrder response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<ImpugnedOrder> rd = new ActionResponse<ImpugnedOrder>();
		String jsonData = null;

		if (imOrder.getIo_id() != null) {
			rd.setData("Update");
			imOrder.setIo_mod_by(user.getUm_id());
			imOrder.setIo_mod_date((new Date()));
		} else {
			imOrder.setIo_cr_by(user.getUm_id());
			imOrder.setIo_cr_date((new Date()));
			imOrder.setIo_rec_status(1);
			rd.setResponse("TRUE");

		}
		response = ecourtAddCaseService.addImpugnedOrder(imOrder);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}
	
	@RequestMapping(value = "/addCrimeDetail", method = RequestMethod.POST)
	@ResponseBody
	public String addCrimeDetail(@RequestBody CrimeDetails crimeDetails,
			HttpSession session) {

		CrimeDetails response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<CrimeDetails> rd = new ActionResponse<CrimeDetails>();
		String jsonData = null;

		if (crimeDetails.getCd_id() != null) {
			rd.setData("Update");
			crimeDetails.setCd_mod_by(user.getUm_id());
			crimeDetails.setCd_mod_date((new Date()));
		} else {
			crimeDetails.setCd_cr_by(user.getUm_id());
			crimeDetails.setCd_cr_date((new Date()));
			crimeDetails.setCd_rec_status(1);
			rd.setResponse("TRUE");

		}
		response = ecourtAddCaseService.addCrimeFetails(crimeDetails);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}
	
	@RequestMapping(value = "/addSessionTrack", method = RequestMethod.POST)
	@ResponseBody
	public String addSessionTrack(@RequestBody StNoDetails imOrder,
			HttpSession session) {

		StNoDetails response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<StNoDetails> rd = new ActionResponse<StNoDetails>();
		String jsonData = null;

		if (imOrder.getsnd_id() != null) {
			rd.setData("Update");
			imOrder.setsnd_mod_by(user.getUm_id());
			imOrder.setsnd_mod_date((new Date()));
		} else {
			imOrder.setsnd_cr_by(user.getUm_id());
			imOrder.setsnd_cr_date((new Date()));
			imOrder.setsnd_rec_status(1);
			rd.setResponse("TRUE");

		}
		response = ecourtAddCaseService.addSessionTrack(imOrder);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}
	
	
	@RequestMapping(value = "/addAdvanceNotice", method = RequestMethod.POST)
	@ResponseBody
	public String addAdvanceNotice(@RequestBody List<Notice> nt,
			HttpSession session) {

		Notice response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<Notice> rd = new ActionResponse<Notice>();
		String jsonData = null;

		NoticeDepartmentMaster ndm=new NoticeDepartmentMaster();
		
		for(Notice notice :nt) {
			
			if(notice.getNt_dept_mid()==99991L) {
				ndm.setNdm_department_name(notice.getDeptName());
				ndm.setNdm_email(notice.getNt_res_dept_email());
				ndm.setNdm_mobile(notice.getNt_res_dept_mobile());
				ndm=ecourtAddCaseService.save(ndm);
				notice.setNt_dept_mid(ndm.getNdm_id());
			}
			
			
			
		if (notice.getNt_id() != null) {
			rd.setData("Update");/*
			imOrder.setsnd_mod_by(user.getUm_id());
			imOrder.setsnd_mod_date((new Date()));*/
		} else {
			rd.setResponse("TRUE");

		}
		response = ecourtAddCaseService.addAdvanceNotice(notice);
		}

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}

	@RequestMapping(value = "/deleteImpugnedOrder/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteImpugnedOrder(@PathVariable Long id, HttpSession session) {

		ImpugnedOrder response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<ImpugnedOrder> pd = new ActionResponse<ImpugnedOrder>();
		String jsonData = null;

		response = ecourtAddCaseService.deleteImpugnedOrder(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/deleteCrimeDetail/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCrimeDetail(@PathVariable Long id, HttpSession session) {

		CrimeDetails response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<CrimeDetails> pd = new ActionResponse<CrimeDetails>();
		String jsonData = null;

		response = ecourtAddCaseService.deleteCrimeDetails(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/deleteSessionTrack/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteSessionTrack(@PathVariable Long id, HttpSession session) {

		StNoDetails response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<StNoDetails> pd = new ActionResponse<StNoDetails>();
		String jsonData = null;

		response = ecourtAddCaseService.deleteSesstionTrack(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getActDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getActDetails() {
		String jsonData = null;

		List<ActDetails> respondentList = ecourtAddCaseService.getActDetails();
		ActionResponse<ActDetails> response = new ActionResponse<ActDetails>();

		response.setModelList(respondentList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/addActDetails", method = RequestMethod.POST)
	@ResponseBody
	public String addActDetails(@RequestBody ActDetails actDetails,
			HttpSession session) {
		ActDetails response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<ActDetails> rd = new ActionResponse<ActDetails>();
		String jsonData = null;

		if (actDetails.getAct_id() != null) {
			rd.setData("Update");
			actDetails.setAct_mod_by(user.getUm_id());
			actDetails.setAct_mod_date((new Date()));
		} else {
			actDetails.setAct_cr_by(user.getUm_id());
			actDetails.setAct_cr_date((new Date()));
			actDetails.setAct_rec_status(1);
			rd.setResponse("TRUE");
		}
		response = ecourtAddCaseService.addActDetails(actDetails);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}

	@RequestMapping(value = "/deleteActDetails/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteActDetails(@PathVariable Long id, HttpSession session) {

		ActDetails response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<ActDetails> pd = new ActionResponse<ActDetails>();
		String jsonData = null;

		response = ecourtAddCaseService.deleteActDetails(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/submitRegisterCase", method = RequestMethod.POST)
	@ResponseBody
	public String submitRegisterCase(@RequestBody RegisteredCaseDetails resCaseDetails,HttpSession session,HttpServletRequest request) {
		String jsonData="";
		ActionResponse<RegisteredCaseDetails> response=new ActionResponse<RegisteredCaseDetails>();
		User user = (User) session.getAttribute("USER");
		boolean submit=true;
		boolean submitCd=true;
		PetitionerDetails pet= ecourtHomeService.getFirstPetitioner(resCaseDetails.getRcd_id());
		RespondentDetails rDetails = ecourtHomeService.getFirstRespondent(resCaseDetails.getRcd_id());
		List<CourtFee> courtFees = null;
		courtFees = ecourtHomeService.getCourtFee(resCaseDetails.getRcd_id());
		
		List<PetitionerDetails> ptDetails = null;


		ptDetails = ecourtHomeService.getPetitioner(resCaseDetails.getRcd_id());
		
		List<ImpugnedOrder> impDetails = ecourtHomeService.getImpugnedOrder(resCaseDetails.getRcd_id());
		
		List<RespondentDetails> resDetails = null;


		resDetails = ecourtHomeService.getRespondent(resCaseDetails.getRcd_id());
		/*AllowEfiling1 allowEfiling =ecourtAddCaseService.getAllowEfilingForRcdId(resCaseDetails.getRcd_id());
		
		if(allowEfiling != null) {*/
		List<PetitionUploaded> documentList=ecourtAddCaseService.getUploadedPetition(resCaseDetails.getRcd_id());
		List<Notice> nt=ecourtAddCaseService.getNotice(resCaseDetails.getRcd_id());
		
		if(documentList.isEmpty() || pet.getPt_id()==null || rDetails.getRt_id()==null || courtFees == null || (resCaseDetails.getActDetails().size()==0 && 
				resCaseDetails.getCaseType().getCt_type().equals("criminal")))
		/*if(documentList.isEmpty() || pet.getPt_id()==null || rDetails.getRt_id()==null || courtFees == null )*/
		{
			submit=false;
		}
		if(submit)
		{
			/*if(resCaseDetails.getCrimeDetails().size()==0 && 
					(resCaseDetails.getCaseType().getCt_label().equals("BAIL") || resCaseDetails.getCaseType().getCt_label().equals("ABAIL"))) {
				response.setResponse("FALSE");
				response.setData("Please Fill Crime Details");
				jsonData=cm.convert_to_json(response);
			}
			else */{
			if(resCaseDetails.getRcd_diary_no()==null)
			{
			Long diary=ecourtAddCaseService.getDiarySequence();
			int year = Calendar.getInstance().get(Calendar.YEAR);
			resCaseDetails.setRcd_diary_no(diary+"_"+year);
			}
			
			UserRole ur=null;
			
			if(resCaseDetails.getCaseType().getCt_type()!=null) {
			   if(resCaseDetails.getCaseType().getCt_type().equals("civil")) {
				   ur=userRoleService.getByUserRole("CivilCaseSupervisor");
			   }
				else{
					 ur=userRoleService.getByUserRole("CriminalCaseSupervisor");
					}
				
			}
			else {
				if(resCaseDetails.getRcd_case_type().equals("civil")) {
					 ur=userRoleService.getByUserRole("CivilCaseSupervisor");
					}
					else{
						 ur=userRoleService.getByUserRole("CriminalCaseSupervisor");
						}
			}
			
			
			if(ur.getUr_id()!=null)
			{
				 if(resCaseDetails.getCaseStage().getLk_longname().equals("DRAFT"))
				 {
					Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DIARY_NO_GENERATED");
					CaseFileStage caseStage=new CaseFileStage();
					caseStage.setRcs_rcd_mid(resCaseDetails.getRcd_id());
					caseStage.setRcs_cr_by(user.getUm_id());
					caseStage.setRcs_cr_date(new Date());
					caseStage.setRcs_stage_lid(lkStage.getLk_id());
					caseFileStageService.save(caseStage);
					
					resCaseDetails.setRcd_assign_to(ur.getUr_um_mid());
					resCaseDetails.setRcd_stage_lid(lkStage.getLk_id());
					resCaseDetails=ecourtAddCaseService.saveCaseDetails(resCaseDetails);
					
					
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
				            	otpTmpId ="1107163490512028125";
				            }
				            else if(hostname.equals("127.0.0.1")) {
				            	/*otpTmpId ="1107160793982323688";
				            	 extraLko="-Lko. Bench ";*/
				            	otpTmpId ="1107163490512028125";
					            }
				            else {
				            	System.out.println("In Local");
				            	otpTmpId ="1107163490512028125";
				            	/* extraLko="-Lko. Bench ";*/
				            }
				 
				        } catch (UnknownHostException e) {
				 
				            e.printStackTrace();
				        }
				    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				        if(user.getUm_id() != null)
						{
							 /*Integer otp=cm.generateOTP();
							 user.setUm_otp(otp);
							 user=userService.save(user);*/
							 Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
							 String sms_url=urlLookup.getLk_longname();
							 String mob_no=user.getUm_mobile();
							 String smstext="Your e-filed case "+resCaseDetails.getCaseType().getCt_label()+" having Diary_No.  "+resCaseDetails.getRcd_diary_no()+" received on  "+formatter.format(caseStage.getRcs_cr_date())+", First Party name -  "
							 		+ (pet.getPt_name().length() >=30 ? pet.getPt_name().substring(0,26)+"..." :pet.getPt_name())+
							 		", is pending at reporting"+extraLko;
							 smstext=smstext.replace("&", "and");
							 String otpresponse=cm.sendBSNLSMS(sms_url, mob_no, smstext+" - Allahabad High Court",otpTmpId);
							String otpresponse1=cm.sendSMS(sms_url, mob_no, smstext,otpTmpId);
							// String otpresponse="1";
							 if(otpresponse.equals("1"))
							 {
								 user.setUm_otp(null);
								 
								
								 
								 SmsLog smslog = new SmsLog();
								 smslog.setSl_mobile_no(mob_no);
								 smslog.setSl_um_mid(user.getUm_id());
								 smslog.setSl_text(smstext+"    BSNL");
								 smslog.setSl_cr_date(new Date());
								 smslog.setSl_status(1);
								 smslog.setSl_ip_address(request.getRemoteAddr());
								 userService.saveSMSlog(smslog);
								 
							 }else{
								 
								 SmsLog smslog = new SmsLog();
								 smslog.setSl_mobile_no(mob_no);
								 smslog.setSl_um_mid(user.getUm_id());
								 smslog.setSl_text(smstext);
								 smslog.setSl_cr_date(new Date());
								 smslog.setSl_status(0);
								 smslog.setSl_ip_address(request.getRemoteAddr());
								 userService.saveSMSlog(smslog);
								 
									response.setResponse("FALSE");
									response.setData("Unable to send OTP, please try again");
								}
									
						}
					
					if(nt!=null) {
						for(Notice n :nt) {
				//mail sending	
					//Resource r=new ClassPathResource("dms-context.xml"); 
					//Resource r=new ClassPathResource("dms-context.xml"); 
					  
				@SuppressWarnings("resource")
				ApplicationContext ctx = new ClassPathXmlApplicationContext("mail-context.xml"); 

				System.setProperty("http.protocols", "TLSv1,TLSv1.1,TLSv1.2");

				MailMail m = (MailMail) ctx.getBean("mailMail");

				String msg="A petition with following details has been presented by "+resCaseDetails.getUserFiled().getUm_fullname()+" before the High Court of Judicature at Allahabad wherein you are a Respondent / Opposite Party.\r\n" + 
						" Kindly take notice of it.The Petition alongwith its annexures is also available on the link specified below :  ";
				/*String msg1="<br><br>Please find mention below details of notice served to you by "+resCaseDetails.getUserFiled().getUm_fullname();*/
				String petMsg="<br><b>Petitioner(s)<b><br>";
				String petSMS=System.lineSeparator()+"Petitioner(s)"+System.lineSeparator();
				int petCount=1;
				int resCount=1;
				int impCount=1;
				for(PetitionerDetails pt :ptDetails)
				{
					petMsg+=System.lineSeparator()+petCount+" : "+pt.getPt_name();
					petSMS+=System.lineSeparator()+petCount+" : "+pt.getPt_name();
					petCount++;
				}
				
              String resMsg="<br><b>Respondent(s)</b><br>";
              String resSMS=System.lineSeparator()+"Respondent(s)"+System.lineSeparator();
				
				for(RespondentDetails rt :resDetails)
				{
					resMsg+=System.lineSeparator()+resCount+" : "+rt.getRt_name();
					resSMS+=System.lineSeparator()+resCount+" : "+rt.getRt_name();
					resCount++;
				}
				
				  String impMsg="<br><b>Impugned Order(s) Details</b><br>";
				  String impSMS=System.lineSeparator()+"Impugned Order(s) Details"+System.lineSeparator();
					
					for(ImpugnedOrder imp :impDetails)
					{
						impMsg+=impCount+" :  Subordinate Court :"+imp.getCourtType().getLct_name()+" Case No :"+imp.getIo_case_no()
						+" Case year :"+imp.getIo_case_year() ;
						impSMS+=impCount+" :  Subordinate Court :"+imp.getCourtType().getLct_name()+" Case No :"+imp.getIo_case_no()
						+" Case year :"+imp.getIo_case_year() ;
						impCount++;
					}
				
				/*String linkMsg="<br>For viewing the complete file, please click on the link below : "+System.lineSeparator()+
						"<a href='https://efiling-alld.allahabadhighcourt.in/onlinecasefiling/validateNotice?id="+n.getNt_id()+"'>Click Here</a>";*/
					String linkMsg="<br>For viewing the complete file, please click on the link below : "+System.lineSeparator()+
							"<a href='https://efiling-alld.allahabadhighcourt.in/onlinecasefiling/validateNotice?id="+n.getNt_id()+"'>Click Here</a>";
					/*String linkMsg="<a href='http://localhost:8082/onlinecasefiling/validateNotice?id="+n.getNt_id()+"'>Click Here</a>";	*/
				
				/*String linkSMS=System.lineSeparator()+"For viewing the complete file, please click on the link below : "+System.lineSeparator()+
						"http://192.168.0.242:8082/onlinecasefiling/validateNotice?id="+n.getNt_id();*/
				
				String linkSMS=System.lineSeparator()+"For viewing the complete file, please click on the link below : "+System.lineSeparator()+
						"https://efiling-alld.allahabadhighcourt.in/onlinecasefiling/validateNotice?id="+n.getNt_id();

				msg+=petMsg+resMsg+impMsg+linkMsg;
				String sender="enotice@allahabadhighcourt.in";//write here sender gmail id  
				String receiver=n.getNt_res_adv_email();//write here receiver id  
				m.sendMail(sender,receiver,"Notice Regrading Case",msg);  
				if(n.getNt_res_dept_email()!=null) {
					m.sendMail(sender,n.getNt_res_dept_email(),"Notice Regrading Case",msg);
				}
				      
				System.out.println("success");  
					
					
				//mail ending
					
					
					//sms start
					
					
					 
				        try {
				            ip = InetAddress.getLocalHost();
				            hostname = ip.getHostAddress();
				            System.out.println("Your current IP address : " + ip);
				            System.out.println("Your current Hostname : " + hostname);
				            
				            if(hostname.equals("172.16.0.6")) {
				            	otpTmpId ="1107160762863085549";
				            }
				            else if(hostname.equals("127.0.0.1")) {
				            	otpTmpId ="1107160793982323688";
				            	 extraLko="-Lko. Bench ";
					            }
				            else {
				            	System.out.println("In Local");
				            	otpTmpId ="1107160762863085549";
				            }
				 
				        } catch (UnknownHostException e) {
				 
				            e.printStackTrace();
				        }
				        
				        Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
						 String sms_url=urlLookup.getLk_longname();
						 //String mob_no=user.getUm_mobile();
						 /*String smstext="A notice has been sent in your email "+n.getNt_res_adv_email()+"  by Petitioner Councel  " +resCaseDetails.getUserFiled().getUm_fullname()
								 +"("+resCaseDetails.getUserFiled().getUsername()+")."+"\n Please check you mail for details"+extraLko+petSMS+resSMS+impSMS+linkSMS;
						 String otpresponse=cm.sendSMS(sms_url, n.getNt_res_adv_mobile(), smstext);*/
					
						 String smstext="A petition has been presented by "+resCaseDetails.getUserFiled().getUm_fullname()+" (Counsel/ Litigant-In-Person) before The High Court of Judicature at "
						 		+ "Allahabad wherein you are a respondent/opposite party. Please check your email "+n.getNt_res_adv_email()+" for details ";
						 String otpresponse=cm.sendSMS(sms_url, n.getNt_res_adv_mobile(), smstext,"1107161651988774250");
						 
						 if(n.getNt_res_dept_mobile()!=null) {
							 String otpresponse1=cm.sendSMS(sms_url, n.getNt_res_dept_mobile(), smstext,"1107161651988774250");
							 
							 String otpresp=cm.sendBSNLSMS(sms_url, n.getNt_res_dept_mobile(), smstext,"1107161651988774250");
						 }
						 //sms end
					}
					}
					response.setResponse("TRUE");
					response.setModelData(resCaseDetails);
					jsonData=cm.convert_to_json(response);
				 }
				 else if(resCaseDetails.getCaseStage().getLk_id()==1000041L)
				 {
					 boolean flag=caseFileService.checkDateValidity(resCaseDetails.getRcd_id());
						if(flag)
						{
							Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DIARY_NO_GENERATED");
							CaseFileStage caseStage=new CaseFileStage();
							caseStage.setRcs_rcd_mid(resCaseDetails.getRcd_id());
							caseStage.setRcs_cr_by(user.getUm_id());
							caseStage.setRcs_cr_date(new Date());
							caseStage.setRcs_stage_lid(lkStage.getLk_id());
							caseFileStageService.save(caseStage);
							
							resCaseDetails.setRcd_assign_to(ur.getUr_um_mid());
							resCaseDetails.setRcd_stage_lid(lkStage.getLk_id());
							resCaseDetails=ecourtAddCaseService.saveCaseDetails(resCaseDetails);
							
							response.setResponse("TRUE");
							response.setModelData(resCaseDetails);
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
		}
		else
		{
			response.setResponse("FALSE");
			response.setData("Please submit all the required fields");
			jsonData=cm.convert_to_json(response);
		}
	
		/*else {
			response.setResponse("FALSE");
			response.setData("No urgency Code Provided For this Case");
			jsonData=cm.convert_to_json(response);
		}*/
		return jsonData;
	}
	@RequestMapping(value = "/addRegisterCase", method = RequestMethod.POST)
	@ResponseBody
	public String addRegisterCase(@RequestBody RegisteredCaseDetails resCaseDetails,HttpSession session) 
	{

		RegisteredCaseDetails rcs = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<RegisteredCaseDetails> rd = new ActionResponse<RegisteredCaseDetails>();
		String jsonData = null;

		int year = Calendar.getInstance().get(Calendar.YEAR);
		Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DRAFT");
		System.out.println(year);

		if (resCaseDetails.getRcd_cr_by() == null) {
			resCaseDetails.setRcd_cr_by(user.getUm_id());
			resCaseDetails.setRcd_cr_date(new Date());
			resCaseDetails.setRcd_stage_lid(lkStage.getLk_id());
			resCaseDetails.setRcd_assign_to(user.getUm_id());
			rd.setResponse("TRUE");	
			
			rcs = ecourtAddCaseService.addRegisterCase(resCaseDetails);
			rcs=ecourtAddCaseService.getRegisterCase(rcs.getRcd_id());
			
			
			
			//AllowEfiling1 allowEfiling =ecourtAddCaseService.getAllowEfiling(resCaseDetails.getCode(),'C');
			
			/*rcs.setPtName(allowEfiling.getAe_pet_name());
			
			allowEfiling.setAe_reference_mid(rcs.getRcd_id());
			ecourtAddCaseService.addAllowEfiling(allowEfiling);*/
			
			rd.setModelData(rcs);
			
			
			CaseFileStage caseStage=new CaseFileStage();
			caseStage.setRcs_rcd_mid(rcs.getRcd_id());
			caseStage.setRcs_cr_by(user.getUm_id());
			caseStage.setRcs_cr_date(new Date());
			caseStage.setRcs_stage_lid(lkStage.getLk_id());
			caseFileStageService.save(caseStage);
		}
		else {
			rd.setData("Update");
			resCaseDetails.setRcd_mod_by(user.getUm_id());
			resCaseDetails.setRcd_mod_date(new Date());
			
			rcs = ecourtAddCaseService.addRegisterCase(resCaseDetails);
			rd.setModelData(rcs);
		}
				
		
		if (rcs != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}
	
	
	
	
	@RequestMapping(value = "/validateCode/{id},{appno}", method =  RequestMethod.GET)
	public String validateCode(Model model, @PathVariable Integer id, @PathVariable String appno,HttpSession session) 
	{

		AllowEfiling1 ae = null;
		String viewname="/content/access_denied";
		String result = "false";
		String message="";

		User user = (User) session.getAttribute("USER");
		ActionResponse<AllowEfiling1> rd = new ActionResponse<AllowEfiling1>();
		String jsonData = null;

			
		
			
			ae = ecourtAddCaseService.codeValidation(id,appno,'C');
	
				if(ae==null) {
					message="Not A valid Code ";
					viewname="/content/access_denied";
					model.addAttribute("message", message);
				}
				else if(ae.getAe_reference_mid()!=null){
					message="Used Code ";
					viewname="/content/access_denied";
					model.addAttribute("message", message);
				}
				else if(user.getUserroles().get(0).getLk().getLk_longname().equals("InPerson") && !user.getUm_fullname().equals(ae.getAe_pet_name()) && !user.getUm_mobile().equals(ae.getAe_aor())){
					message="Not valid person to File ";
					viewname="/content/access_denied";
					model.addAttribute("message", message);
				}
				else if(user.getUm_mobile().equals(ae.getAe_aor())){
					viewname="/ecourt/addCase";
					/*	rd.setResponse("TRUE");	
						rd.setModelData(ae);*/
					CaseType ct=ecourtAddCaseService.getCaseTypeByLabel(ae.getAe_case_types());
						model.addAttribute("caseType", ct.getCt_id());
						model.addAttribute("code", ae.getAe_code());
				}
				else
				{
					if(user.getUsername().equals(ae.getAe_aor())) {
						viewname="/ecourt/addCase";
						/*	rd.setResponse("TRUE");	
							rd.setModelData(ae);*/
						CaseType ct=ecourtAddCaseService.getCaseTypeByLabel(ae.getAe_case_types());
							model.addAttribute("caseType", ct.getCt_id());
							model.addAttribute("code", ae.getAe_code());	
								
					}
					else {
						message="This uregency code not belongs to you";
						viewname="/content/access_denied";
						model.addAttribute("message", message);
					}
					
					
				}
		
		if (ae != null)
			jsonData = cm.convert_to_json(rd);

		return viewname;
	}

	@RequestMapping(value = "/getRegisterCase", method = RequestMethod.GET)
	@ResponseBody
	public String getRegisterCase(HttpServletRequest request) {

		String id = request.getParameter("docId");
		RegisteredCaseDetails response = null;

		Long doc = new Long(id);
		ActionResponse<RegisteredCaseDetails> pd = new ActionResponse<RegisteredCaseDetails>();
		String jsonData = null;
		
		StampReporterData srd = ecourtHomeService.getStampReporterData(doc);

		PetitionerDetails pDetails = new PetitionerDetails();

		RespondentDetails rDetails = new RespondentDetails();

		pDetails = ecourtHomeService.getFirstPetitioner(doc);
		rDetails = ecourtHomeService.getFirstRespondent(doc);

		response = ecourtAddCaseService.getRegisterCase(doc);
		
		
		
		/*AllowEfiling ae=ecourtAddCaseService.getAllowEfilingByRcd(response.getRcd_id(),'C');
		
		response.setPtName(ae.getAe_pet_name());*/

		if (pDetails.getPt_id() != null) {
			response.setPetitionerDetails(pDetails);
		}
		if (rDetails.getRt_id() != null) {
			response.setRespondentDetails(rDetails);
		}
		
		if (srd != null) {
			response.setStampReporterData(srd);;
		}
		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelData(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getTrialCourt", method = RequestMethod.GET)
	@ResponseBody
	public String getTrialcourt() {
		String jsonData = null;

		List<TrialCourt> respondentList = ecourtAddCaseService.getTrialcourt();
		ActionResponse<TrialCourt> response = new ActionResponse<TrialCourt>();

		response.setModelList(respondentList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/addTrialCourt", method = RequestMethod.POST)
	@ResponseBody
	public String addTrialCourt(@RequestBody TrialCourt tCourt,
			HttpSession session) {

		TrialCourt response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<TrialCourt> rd = new ActionResponse<TrialCourt>();
		String jsonData = null;

		if (tCourt.getTr_id() != null) {
			rd.setData("Update");
			tCourt.setTr_mod_by(user.getUm_id());
			tCourt.setTr_mod_date((new Date()));
		} else {
			tCourt.setTr_cr_by(user.getUm_id());
			tCourt.setTr_cr_date((new Date()));
			tCourt.setTr_rec_status(1);
			seq++;
			tCourt.setTr_sequence(seq);
			rd.setResponse("TRUE");
		}
		response = ecourtAddCaseService.addTrialCourt(tCourt);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}

	@RequestMapping(value = "/deleteTrialCourt/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteTrialCourt(@PathVariable Long id, HttpSession session) {

		TrialCourt response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<TrialCourt> pd = new ActionResponse<TrialCourt>();
		String jsonData = null;

		response = ecourtAddCaseService.deleteTrialCourt(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/addCourtFee", method = RequestMethod.POST)
	@ResponseBody
	public String addCourtFee(@RequestBody CourtFee courtFee,
			HttpSession session) {

		CourtFee response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<CourtFee> rd = new ActionResponse<CourtFee>();
		String jsonData = null;
		
		RegisteredCaseDetails rcd= caseFileService.getRegisterCase(courtFee.getCf_rcd_mid());

		if (courtFee.getCf_id() != null) {
			rd.setData("Update");
			courtFee.setCf_mod_by(user.getUm_id());
			courtFee.setCf_mod_date((new Date()));
			courtFee.setCf_receipt_no(courtFee.getCf_receipt_no().toUpperCase());
			
			List<CourtFee> c1=ecourtAddCaseService.getReceiptIfExistsInCourtFee(courtFee);
			boolean checkCaseFee =false;
			boolean checkAppFee =false;
			if(c1 != null) {
			for(CourtFee cf :c1) {
				
			if(cf.getCf_receipt_no().equals(courtFee.getCf_receipt_no()) && !cf.getCf_id().equals(courtFee.getCf_id())) {
				RegisteredCaseDetails rcdCheck= caseFileService.getRegisterCase(cf.getCf_rcd_mid());
				if(rcdCheck.getRcd_stage_lid() ==1000041L) {
					checkCaseFee =false;
				}
				else {
					checkCaseFee =true;
					
					rd.setData("Exists");
					rd.setResponse("FALSE");
					
					jsonData = cm.convert_to_json(rd);
					
					return jsonData;
				}
				
				
				
			}
			}
		}
			
			 if(!checkCaseFee) {
					ApplicationCourtFee a1 =ecourtAddCaseService.getReceiptIfExistsInCourtFeeInApplicationCourtFee(courtFee);
					
					if(a1 != null) {
						checkAppFee =true;
						rd.setData("Exists");
						rd.setResponse("FALSE");
						
						jsonData = cm.convert_to_json(rd);
						
						return jsonData;
					}
					
				}
				 if(!checkAppFee) {
					
					CaveatCourtFee ccf =ecourtAddCaseService.getReceiptIfExistsInCourtFeeInCaveatCourtFee(courtFee);
					
					if(ccf != null) {
						rd.setData("Exists");
						rd.setResponse("FALSE");
						
						jsonData = cm.convert_to_json(rd);
						
						return jsonData;
					}
				}
			
			
			
			
		} else {

			courtFee.setCf_cr_by(user.getUm_id());
			courtFee.setCf_cr_date((new Date()));
			courtFee.setCf_rec_status(1);
			courtFee.setCf_receipt_no(courtFee.getCf_receipt_no().toUpperCase());
			List<CourtFee> c1=ecourtAddCaseService.getReceiptIfExistsInCourtFee(courtFee);
			
			boolean checkCaseFee =false;
			boolean checkAppFee =false;
			if(c1 != null) {
			for(CourtFee cf :c1) {
				RegisteredCaseDetails rcdcheck=ecourtAddCaseService.getByPk(cf.getCf_rcd_mid());
			if(cf.getCf_receipt_no().equals(courtFee.getCf_receipt_no()) && rcdcheck.getRcd_stage_lid() !=1000041L) {
				
				checkCaseFee =true;
				
				rd.setData("Exists");
				rd.setResponse("FALSE");
				
				jsonData = cm.convert_to_json(rd);
				
				return jsonData;
			}
			}
		}
			
			
			
			
		 if(!checkCaseFee) {
				ApplicationCourtFee a1 =ecourtAddCaseService.getReceiptIfExistsInCourtFeeInApplicationCourtFee(courtFee);
				
				if(a1 != null) {
					checkAppFee =true;
					rd.setData("Exists");
					rd.setResponse("FALSE");
					
					jsonData = cm.convert_to_json(rd);
					
					return jsonData;
				}
				
			}
			 if(!checkAppFee) {
				
				CaveatCourtFee ccf =ecourtAddCaseService.getReceiptIfExistsInCourtFeeInCaveatCourtFee(courtFee);
				
				if(ccf != null) {
					rd.setData("Exists");
					rd.setResponse("FALSE");
					
					jsonData = cm.convert_to_json(rd);
					
					return jsonData;
				}
			}
			rd.setResponse("TRUE");
		}
		response = ecourtAddCaseService.addCourtFee(courtFee);

		if (response != null)
			jsonData = cm.convert_to_json(rd);

		return jsonData;
	}

	
@RequestMapping(value="/getDocumentTypes", method=RequestMethod.GET)
@ResponseBody
public String getDocumentTypes()
{
	String jsonData=null;
	
	List<IndexField> documentList=ecourtAddCaseService.getDocumentTypes();
	ActionResponse<IndexField> response=new ActionResponse<IndexField>();
	
	   response.setModelList(documentList);
	
     jsonData=cm.convert_to_json(response);
    
     return jsonData;
}

@RequestMapping(value="/getUploadedDocuments", method=RequestMethod.GET)
@ResponseBody
public String getUploadedDocuments(HttpServletRequest request)
{
	String jsonData=null;
	String file_id=request.getParameter("rcd_id");
	Long rcd_id=Long.valueOf(file_id);
	
	List<PetitionUploaded> documentList=ecourtAddCaseService.getUploadedPetition(rcd_id);
	ActionResponse<PetitionUploaded> response=new ActionResponse<PetitionUploaded>();
	
	response.setModelList(documentList);
	response.setResponse("TRUE");
	
     jsonData=cm.convert_to_json(response);
     return jsonData;
}

@RequestMapping(value = "/upload_file",method = RequestMethod.POST)
public @ResponseBody String create(MultipartHttpServletRequest request,HttpSession session,HttpServletRequest req) throws DocumentException 
{
	
	String jsonData="";

	ActionResponse<PetitionUploaded>response=new ActionResponse<PetitionUploaded>();
	response.setResponse("TRUE");
	String ipaddress = request.getRemoteAddr();
	RegisteredCaseDetails rcd= new RegisteredCaseDetails();
		
	User user=new User();
	user=(User) session.getAttribute("USER");
	
	String file_id=req.getParameter("rcd_id");
	Long rcd_id=Long.valueOf(file_id);
	
	
	rcd=ecourtAddCaseService.getByPk(rcd_id);

	String documentname = rcd.getRcd_draft_no();

	 MultipartFile mpf = null;
	 Iterator<String> itr = request.getFileNames();
	     
	     
	 String basePath="";
	 List<Lookup> lookupForRaw = lkservice.getAll("DRAFT_PATH");
	 String draftBasepath =lookupForRaw.get(0).getLk_longname();	
		
		
		List <Object> errorList=new ArrayList();

			while (itr.hasNext()) 
			{
				try
				{
				mpf = request.getFile(itr.next());
		        
				
				
				PetitionUploaded pu = new PetitionUploaded();
			
				String filename = mpf.getOriginalFilename();  
				
		        String temppath=draftBasepath + File.separator+documentname+".pdf";
                
				String ext = FilenameUtils.getExtension(filename);
				

				  if(ext.equalsIgnoreCase("pdf"))
					{	
					  	FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(temppath));
					    
					    PdfReader readernewFile = new PdfReader(temppath);
						Integer newPageCount =readernewFile.getNumberOfPages();
						readernewFile.close();
				    
				        pu.setPu_rcd_mid(rcd.getRcd_id());
				       	pu.setPu_document_name(documentname+".pdf");
				       	pu.setPu_no_of_pages(newPageCount);
				       	pu.setPu_uploaded_date(new Date());
				       	pu.setPu_uploaded_by(user.getUm_id());
				       	pu.setPu_rec_status(1);
				       	
				       	
				       	pu=ecourtAddCaseService.savePetitionUploaded(pu);
					     
				       	response.setResponse("TRUE");
				       	response.setData(pu);

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

	@RequestMapping(value ="/deleteDocument/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public  String deleteDocument(@PathVariable Long id,HttpSession session)
	{	
			User user = (User) session.getAttribute("USER");
			ActionResponse<PetitionUploaded> pd =new ActionResponse<PetitionUploaded>();
			String jsonData=null;
			SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
			String currentDate=sdf.format(new Date());
				PetitionUploaded pu=ecourtAddCaseService.getPetitionUploaded(id);
				List<Lookup> lookupForRaw = lkservice.getAll("DRAFT_PATH");
				String draftBasepath =lookupForRaw.get(0).getLk_longname();
				String oldpath=draftBasepath + File.separator+pu.getPu_document_name();
				File oldfile=new File(oldpath);
				String newname=FilenameUtils.getBaseName(oldpath);
				String extension=FilenameUtils.getExtension(oldpath);
				String newpath=draftBasepath + File.separator+newname+"_"+currentDate+"."+extension;
				File newfile=new File(newpath);
				boolean response=false; 
				if(oldfile.renameTo(newfile))
				{
					response=ecourtAddCaseService.deletePetition(id);
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
	
	@RequestMapping(value = "/deleteCourtFee/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteCourtFee(@PathVariable Long id, HttpSession session) {
		CourtFee response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<CourtFee> pd = new ActionResponse<CourtFee>();
		String jsonData = null;

		response = ecourtAddCaseService.deleteCourtFee(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	
	@RequestMapping(value = "/addLinkedCase", method = RequestMethod.POST)
	@ResponseBody
	public String addLinkedCase(@RequestBody LinkedCaseDetails lCaseDetails,
			HttpSession session) {

		LinkedCaseDetails response = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<LinkedCaseDetails> rd = new ActionResponse<LinkedCaseDetails>();
		String jsonData = null;
           lCaseDetails.setLcd_cr_date(new Date());
           lCaseDetails.setLcd_cr_by(user.getUm_id());
           lCaseDetails.setLcd_rec_status(1);
		
		
		response = ecourtAddCaseService.addLinkedCase(lCaseDetails);

		if (response != null)
			rd.setResponse("TRUE");
		  rd.setModelData(response);
			jsonData = cm.convert_to_json(rd);
		   

		return jsonData;
	}
	
	/*@RequestMapping(value="/getLinkedCase", method=RequestMethod.GET)
	@ResponseBody
	public String getLinkedCase(HttpServletRequest request)
	{
		String jsonData=null;
		String file_id=request.getParameter("rcd_id");
		Long rcd_id=Long.valueOf(file_id);
		List<LinkedCaseDetails> lCaseDetails=null;
	    lCaseDetails=ecourtAddCaseService.getLinkedCase(rcd_id);
		ActionResponse<LinkedCaseDetails> response=new ActionResponse<LinkedCaseDetails>();
		response.setModelList(lCaseDetails);
		response.setResponse("TRUE");
		
	     jsonData=cm.convert_to_json(response);
	     return jsonData;
	}*/

	
	/*@RequestMapping(value = "/delete_linkCase/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete_linkCase(@PathVariable Long id, HttpSession session) {
		LinkedCaseDetails response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<LinkedCaseDetails> pd = new ActionResponse<LinkedCaseDetails>();
		String jsonData = null;

		response = ecourtAddCaseService.delete_linkCase(user, id);

		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
*/	
	
	@RequestMapping(value = "/upload_fileExcel", method = RequestMethod.POST)
	public String createExcel(Model model, MultipartHttpServletRequest request, MultipartFile file, HttpSession session,
			HttpServletRequest req) throws DocumentException, InvalidFormatException, Exception 
	{
		User user = (User) session.getAttribute("USER");
		String file_id = req.getParameter("rcd_id");
		Long rcd_id = Long.valueOf(file_id);
		RegisteredCaseDetails rcd = ecourtAddCaseService.getRegisterCase(rcd_id);
		String draft_no = rcd.getRcd_draft_no();
		String fileLocation;
		InputStream in = file.getInputStream();
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
		FileOutputStream f = new FileOutputStream(fileLocation);
		int ch = 0;
		while ((ch = in.read()) != -1)
		{
			f.write(ch);
		}
		f.flush();
		f.close();
		model.addAttribute("message", "File: " + file.getOriginalFilename() + " has been uploaded successfully!");
		String fname = file.getOriginalFilename();
		Workbook workbook = WorkbookFactory.create(new File(fname));
		//Long seqcount = ecourtAddCaseService.getSequenceCount(pd.getPt_rcd_mid());
		Sheet sheet = workbook.getSheetAt(0);
		String petitionersheet = sheet.getSheetName();
		if(petitionersheet.equals("Petitioner")) 
		{
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Row> rowIterator = sheet.rowIterator();
			PetitionerDetails petitioner = new PetitionerDetails();
			List<PetitionerDetails> ptList = new ArrayList<>();
			while (rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				List list = new ArrayList();
				while (cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					if(!cellValue.equals("Complainant") && !cellValue.equals("Petitioner Name") && !cellValue.equals("Petitioner Email") && !cellValue.equals("Petitioner Address") && !cellValue.equals("Gender") && !cellValue.equals("Age") && !cellValue.equals("Mobile No"))
					{
						//Long i=0l;
						list.add(cellValue);
						//System.out.println("-------cellValue--------"+cellValue);
					}
					else 
					{
					}
				}	
				int i;
				//Long seqcount=ecourtAddCaseService.getSequenceCount(petitioner.getPt_rcd_mid());
				for (i = 0; i<list.size(); i++) 
				{
					if(list.size()>2) 
					{
						if((String)list.get(0)!="")
						{
				//	petitioner.setPt_complainant((String)list.get(0));
					petitioner.setPt_name((String)list.get(1));
					if(list.get(2)==null)
					{
						
					}
					else
					{
						petitioner.setPt_email((String)list.get(2));	
					}
					petitioner.setPt_address((String)list.get(3));
				//	petitioner.setPt_gender((String)list.get(4));
					Long page =  Long.parseLong((String) list.get(5));
				//	petitioner.setPt_age(page);
					Long p_mobile =  Long.parseLong((String) list.get(6));
					petitioner.setPt_mobile(p_mobile);
					//ptList.add(petitioner);
					}					
				}
				}
				if(i>0)
				{
					petitioner.setPt_rcd_mid(rcd_id);
					petitioner.setPt_cr_by(user.getUm_id());
					petitioner.setPt_cr_date((new Date()));
					petitioner.setPt_rec_status(1);
					//seqcount++;
					//petitioner.setPt_sequence(seqcount);
					//System.out.println("---seqcount +++++++++---"+seqcount);
					
					if(list.size()>2)
					{
						if((String)list.get(0)!="")
						{
						ecourtAddCaseService.addPetitioner(petitioner);
						}
					}	
				}
			}	
		}
		else
		{
			System.out.println("sheet name is not match");
		}
		Sheet sheet1 = workbook.getSheetAt(1);
		String respondentsheet = sheet1.getSheetName();
		if(respondentsheet.equals("Respondent")) 
		{
			DataFormatter dataFormatter1 = new DataFormatter();
			Iterator<Row> rowIterator1 = sheet1.rowIterator();
			RespondentDetails respondent = new RespondentDetails();
			List<RespondentDetails> resplist = new ArrayList<>();
			while (rowIterator1.hasNext()) 
			{
				Row row1 = rowIterator1.next();
				Iterator<Cell> cellIterator1 = row1.cellIterator();
				List rlist = new ArrayList();
				while (cellIterator1.hasNext()) 
				{
					Cell cell1 = cellIterator1.next();
					String cellValue1 = dataFormatter1.formatCellValue(cell1);
					if(!cellValue1.equals("Accused") && !cellValue1.equals("Respondent Name") && !cellValue1.equals("Respondent Email") && !cellValue1.equals("Respondent Address") && !cellValue1.equals("Gender") && !cellValue1.equals("Age") && !cellValue1.equals("Mobile No"))
					{
						//rlist.add(cellValue1);
						//System.out.println("----resp---cellValue1--------"+cellValue1);
					}
				}
				int r;
				for (r = 0; r<rlist.size(); r++) 
				{
					if(rlist.size()>2)
					{
						if((String)rlist.get(0)!="")
						{
					//respondent.setRt_accused((String)rlist.get(0));
					respondent.setRt_name((String)rlist.get(1));
					if(rlist.get(2)==null)
					{
					}
					else
					{
						respondent.setRt_email((String)rlist.get(2));
					}
					respondent.setRt_address((String)rlist.get(3));
					//respondent.setRt_gender((String)rlist.get(4));
					Long rage =  Long.parseLong((String) rlist.get(5));
					//respondent.setRt_age(rage);
					Long r_mobile =  Long.parseLong((String) rlist.get(6));
					respondent.setRt_mobile(r_mobile);
					//resplist.add(respondent);
						}
				}
				}
				if(r>0)
				{
					respondent.setRt_rcd_mid(rcd_id);
					respondent.setRt_cr_by(user.getUm_id());
					respondent.setRt_cr_date((new Date()));
					respondent.setRt_rec_status(1);
					if(rlist.size()>2)
					{
						if((String)rlist.get(0)!="")
						{
							ecourtAddCaseService.addRespondent(respondent);
						}
						
					}
				}
			}
		}
		else
		{
			System.out.println("not valis respondent sheet");
		}
				return "excel";
	}
}
