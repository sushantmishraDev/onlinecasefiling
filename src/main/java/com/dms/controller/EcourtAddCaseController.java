package com.dms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActDetails;
import com.dms.model.ActionResponse;
import com.dms.model.CaseFileStage;
import com.dms.model.CaseType;
import com.dms.model.CourtFee;
import com.dms.model.ImpugnedOrder;
import com.dms.model.IndexField;
import com.dms.model.LinkedCaseDetails;
import com.dms.model.Lookup;
import com.dms.model.LowerCourtCaseType;
import com.dms.model.PetitionUploaded;
import com.dms.model.PetitionerDetails;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.RespondentDetails;
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
				}else if(rcd.getCaseStage().getLk_longname().equals("SUPERVISIOR_DEFECTS"))
				{
					boolean flag=caseFileService.checkDateValidity(rcd.getRcd_id());
					if(flag)
					{
						viewname="/ecourt/addCase";
					}
					else
					{
						message="Permission Denied";
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
		Long seqcount = ecourtAddCaseService.getSequenceCount(pDetails
				.getPt_rcd_mid());
		String jsonData = null;
		if (pDetails.getPt_id() != null) {
			pd.setData("Update");
			pDetails.setPt_mod_by(user.getUm_id());
			pDetails.setPt_mod_date((new Date()));

		} else {
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

		List<RespondentDetails> respondentList = ecourtAddCaseService
				.getRespondentDetails();
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
	public String submitRegisterCase(@RequestBody RegisteredCaseDetails resCaseDetails,HttpSession session) {
		String jsonData="";
		ActionResponse<RegisteredCaseDetails> response=new ActionResponse<RegisteredCaseDetails>();
		User user = (User) session.getAttribute("USER");
		boolean submit=true;
		PetitionerDetails pet= ecourtHomeService.getFirstPetitioner(resCaseDetails.getRcd_id());
		RespondentDetails rDetails = ecourtHomeService.getFirstRespondent(resCaseDetails.getRcd_id());
//		List<CourtFee> courtFees = null;
//		courtFees = ecourtHomeService.getCourtFee(resCaseDetails.getRcd_id());
		List<PetitionUploaded> documentList=ecourtAddCaseService.getUploadedPetition(resCaseDetails.getRcd_id());
		if(documentList.isEmpty() || pet.getPt_id()==null || rDetails.getRt_id()==null)
		{
			submit=false;
		}
		if(submit)
		{
			if(resCaseDetails.getRcd_diary_no()==null){
			Long diary=ecourtAddCaseService.getDiarySequence();
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			
			resCaseDetails.setRcd_diary_no(diary+"_"+year);
			}
			
			UserRole ur=userRoleService.getByUserRole("CaseScrutiny");
			
			
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
					
					response.setResponse("TRUE");
					response.setModelData(resCaseDetails);
					jsonData=cm.convert_to_json(response);
				 }
				 else if(resCaseDetails.getCaseStage().getLk_longname().equals("SUPERVISIOR_DEFECTS"))
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
		else
		{
			response.setResponse("FALSE");
			response.setData("Please submit all the required fields");
			jsonData=cm.convert_to_json(response);
		}
		return jsonData;
	}
	@RequestMapping(value = "/addRegisterCase", method = RequestMethod.POST)
	@ResponseBody
	public String addRegisterCase(
			@RequestBody RegisteredCaseDetails resCaseDetails,
			HttpSession session) {

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

	@RequestMapping(value = "/getRegisterCase", method = RequestMethod.GET)
	@ResponseBody
	public String getRegisterCase(HttpServletRequest request) {

		String id = request.getParameter("docId");
		RegisteredCaseDetails response = null;

		Long doc = new Long(id);
		ActionResponse<RegisteredCaseDetails> pd = new ActionResponse<RegisteredCaseDetails>();
		String jsonData = null;

		PetitionerDetails pDetails = new PetitionerDetails();

		RespondentDetails rDetails = new RespondentDetails();

		pDetails = ecourtHomeService.getFirstPetitioner(doc);
		rDetails = ecourtHomeService.getFirstRespondent(doc);

		response = ecourtAddCaseService.getRegisterCase(doc);

		if (pDetails.getPt_id() != null) {
			response.setPetitionerDetails(pDetails);
		}
		if (rDetails.getRt_id() != null) {
			response.setRespondentDetails(rDetails);
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

		if (courtFee.getCf_id() != null) {
			rd.setData("Update");
			courtFee.setCf_mod_by(user.getUm_id());
			courtFee.setCf_mod_date((new Date()));
			courtFee.setCf_receipt_no(courtFee.getCf_receipt_no().toUpperCase());
		} else {

			courtFee.setCf_cr_by(user.getUm_id());
			courtFee.setCf_cr_date((new Date()));
			courtFee.setCf_rec_status(1);
			courtFee.setCf_receipt_no(courtFee.getCf_receipt_no().toUpperCase());
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
				
				if(oldfile.renameTo(newfile)){
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
	
	@RequestMapping(value="/getLinkedCase", method=RequestMethod.GET)
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
	}

	
	@RequestMapping(value = "/delete_linkCase/{id}/", method = RequestMethod.DELETE)
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
	
	
	
	
	

}
