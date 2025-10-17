package com.dms.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.Application;
import com.dms.model.ApplicationCheckListMapping;
import com.dms.model.ApplicationStage;
import com.dms.model.ApplicationUploaded;
import com.dms.model.CaseCheckListMapping;
import com.dms.model.CaseFileStage;
import com.dms.model.Caveat;
import com.dms.model.CaveatCheckListMapping;
import com.dms.model.CaveatDocuments;
import com.dms.model.CaveatPetitionerDetails;
import com.dms.model.CaveatRespondentDetails;
import com.dms.model.CaveatStage;
import com.dms.model.CheckList;
import com.dms.model.IndexField;
import com.dms.model.Lookup;
import com.dms.model.PetitionUploaded;
import com.dms.model.PetitionerDetails;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.RespondentDetails;
import com.dms.model.StampReporterData;
import com.dms.model.User;
import com.dms.service.ApplicationService;
import com.dms.service.CaseFileService;
import com.dms.service.CaseFileStageService;
import com.dms.service.CaveatService;
import com.dms.service.LookupService;
import com.dms.service.ScrutinyService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/scrutiny")
public class ScrutinyController 
{
	private GlobalFunction cm;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private ScrutinyService scrutinyService;
	
	@Autowired
	private CaveatService caveatService;
	@Autowired
	private CaseFileService caseFileService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private CaseFileStageService caseFileStageService;
	
	public ScrutinyController()
	{
		cm = new GlobalFunction();
	}
	
	@RequestMapping(value = "/caveats", method = RequestMethod.GET)
	public String caveats() {		
		return "/scrutiny/caveats";
	}
	
	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public String appliations() {		
		return "/scrutiny/applications";
	}
	
	@RequestMapping(value = "/cases", method = RequestMethod.GET)
	public String cases() {		
		return "/scrutiny/cases";
	}
	
	@RequestMapping(value = "/caveat/{id}", method = RequestMethod.GET)
	public String caveatView(Model model, @PathVariable Integer id) {

		model.addAttribute("caseId", id);
		return "/scrutiny/caveatView";
	}
	
	@RequestMapping(value = "/case/{id}", method = RequestMethod.GET)
	public String caseView(Model model, @PathVariable Integer id) {

		model.addAttribute("caseId", id);
		return "/scrutiny/caseView";
	}
	
	@RequestMapping(value = "/application/{id}", method = RequestMethod.GET)
	public String applicationView(Model model, @PathVariable Integer id) {

		model.addAttribute("caseId", id);
		return "/scrutiny/applicationView";
	}
	
	@RequestMapping(value="/copyFile",method=RequestMethod.GET)
	@ResponseBody
	public String copyFiles(HttpServletRequest request) throws ParseException
	{
		String jsonData = null;
		
		String doc_name=request.getParameter("pu_document_name");
		
		ActionResponse<IndexField> response= new ActionResponse<IndexField>();
		
	
		Lookup lookUp=lookupService.getLookUpObject("DRAFT_PATH");	
		
		Lookup lookUpBck=lookupService.getLookUpObject("DRAFT_PATH_BCKUP");	
		
		String draft_path=lookUp.getLk_longname();
		
		String draft_path_bck=lookUpBck.getLk_longname();

		File source =null;
		
        PetitionUploaded pu=scrutinyService.getPetitionUploaded(doc_name);
		
		//RegisteredCaseDetails rcd=scrutinyService.getRegisterCase(pu.getPu_rcd_mid());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	//	 String bef=sdf.format("2020-01-01 00:00:00");
	        
	        Date dateBef = sdf.parse("2020-07-01 00:00:00");
			source = new File(draft_path+File.separator+doc_name);	
		
		
		if(!source.exists()) {
			source = new File(draft_path_bck + File.separator + doc_name);
		}
		
		String uploadPath = context.getRealPath("");
		
		doc_name="case_"+doc_name;
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
	@RequestMapping(value="/copyCaveatFile",method=RequestMethod.GET)
	@ResponseBody
	public String copyCaveatFile(HttpServletRequest request) throws ParseException
	{
		String jsonData = null;
		
		String doc_name=request.getParameter("cd_document_name");
		
		CaveatDocuments cd=scrutinyService.getCaveatUploaded(doc_name);
		
		//Caveat cav=scrutinyService.getCaveatById(cd.getCd_cav_mid());
		
		ActionResponse<IndexField> response= new ActionResponse<IndexField>();
		
	
		Lookup lookUp=lookupService.getLookUpObject("CAVEAT_PATH");	
		String draft_path=lookUp.getLk_longname();	
		
		Lookup lookUpBck=lookupService.getLookUpObject("CAVEAT_PATH_BCKUP");	
		
		String draft_path_bck=lookUpBck.getLk_longname();

		File source =null;
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//	 String bef=sdf.format("2020-01-01 00:00:00");
		        
		        Date dateBef = sdf.parse("2020-07-01 00:00:00");
			
			if(cd.getCd_uploaded_date().before(dateBef)) {
				source = new File(draft_path_bck+File.separator+doc_name);	
			}
			else {
				source = new File(draft_path+File.separator+doc_name);	
			}

		
		String uploadPath = context.getRealPath("");
		
		doc_name="cav_"+doc_name;
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
	@RequestMapping(value="/copyApplicationFile",method=RequestMethod.GET)
	@ResponseBody
	public String copyApplicationFile(HttpServletRequest request) throws ParseException
	{
		String jsonData = null;
		
		String doc_name=request.getParameter("au_document_name");
		
		ActionResponse<IndexField> response= new ActionResponse<IndexField>();
		
		ApplicationUploaded au=scrutinyService.getApplicationUploaded(doc_name);
	
		/*Lookup lookUp=lookupService.getLookUpObject("APPLICATION_PATH");	
		String draft_path=lookUp.getLk_longname();

		File source = new File(draft_path+File.separator+doc_name);	*/
		String uploadPath = context.getRealPath("");
		doc_name="appl_"+doc_name;
		
		
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

	@RequestMapping(value = "/getCheckList", method = RequestMethod.GET)
	@ResponseBody
	public String getCheckList(HttpServletRequest request) 
	{
		String jsonData = null;
		String type=request.getParameter("type");
		ActionResponse<CheckList> response = new ActionResponse<CheckList>();
		
		List<CheckList> cl = scrutinyService.getCheckList(type);
		response.setResponse("TRUE");

		if (response != null) {
			response.setModelList(cl);
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/submit_case_file", method = RequestMethod.POST,consumes = {"application/xml", "application/json"})
	public @ResponseBody String submitCaseFile(@RequestBody RegisteredCaseDetails rcdetails,HttpSession session)
	 {
		User u = (User) session.getAttribute("USER");

		String jsonData = null;
		ActionResponse<RegisteredCaseDetails> response = new ActionResponse();
		
		RegisteredCaseDetails rcd=caseFileService.getRegisterCase(rcdetails.getRcd_id());
		
		List<CaseCheckListMapping> mappingold = scrutinyService.getCaseCheckList(rcdetails.getRcd_id());
		for(CaseCheckListMapping mapping:mappingold){
			mapping.setCm_rec_status(2);
			scrutinyService.saveCheckList(mapping);
		}
		// save stamp reporter data
		StampReporterData srd=rcdetails.getStampReporterData();
		if(srd!=null){
			srd.setSrd_rcd_mid(rcd.getRcd_id());
			srd.setSrd_cr_by(u.getUm_id());
			srd.setSrd_cr_date(new Date());
			srd=scrutinyService.saveStampReporterData(srd);
		}
		
		if(rcdetails.getCheckList().isEmpty())
		{
			rcd.setRcd_assign_to(rcd.getRcd_cr_by());
			rcd.setRcd_stage_lid(1000042L);
			
			caseFileService.saveCaseDetails(rcd);
			
			CaseFileStage cfs=new CaseFileStage();
			
			cfs.setRcs_rcd_mid(rcd.getRcd_id());
			cfs.setRcs_stage_lid(1000042L);
			cfs.setRcs_cr_by(u.getUm_id());
			cfs.setRcs_cr_date(new Date());
			
			caseFileStageService.save(cfs);
			
			response.setResponse("SUBMIT");
			
		}
		else
		{
			for(CheckList obj:rcdetails.getCheckList())
			{
				if(obj.getCheckbox()==true)
				   {
					CaseCheckListMapping cclp=new CaseCheckListMapping();
					
					cclp.setCm_rcd_mid(rcd.getRcd_id());
					cclp.setCm_checklist_id(obj.getC_id());
					cclp.setCm_cr_by(u.getUm_id());
					cclp.setCm_cr_date(new Date());
					cclp.setCm_rec_status(1);
					cclp.setCm_remark(obj.getC_remark());
					scrutinyService.saveCheckList(cclp);
					
				   }
			  }	
			
			rcd.setRcd_assign_to(rcd.getRcd_cr_by());
			rcd.setRcd_stage_lid(1000041L);
			
			caseFileService.saveCaseDetails(rcd);
			
			CaseFileStage cfs=new CaseFileStage();
			
			cfs.setRcs_rcd_mid(rcd.getRcd_id());
			cfs.setRcs_stage_lid(1000041L);
			cfs.setRcs_cr_by(u.getUm_id());
			cfs.setRcs_cr_date(new Date());
			
			caseFileStageService.save(cfs);
			
			response.setResponse("REJECT");
		}
		  
		jsonData=cm.convert_to_json(response);

		return jsonData;
	 }
	
	@RequestMapping(value = "/submit_caveat_file", method = RequestMethod.POST,consumes = {"application/xml", "application/json"})
	public @ResponseBody String submitCaveatFile(@RequestBody Caveat cavDetails,HttpSession session)
	 {
		User u = (User) session.getAttribute("USER");

		String jsonData = null;
		ActionResponse<Caveat> response = new ActionResponse();
		
		Caveat cav=caveatService.getByPk(cavDetails.getCav_id());
		
		List<CaveatCheckListMapping> mappingold = scrutinyService.getCaveatCheckList(cavDetails.getCav_id());
		for(CaveatCheckListMapping mapping:mappingold){
			mapping.setCm_rec_status(2);
			scrutinyService.saveCaveatCheckList(mapping);
		}
		
		if(cavDetails.getCheckList().isEmpty())
		{
			Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "REPORTING_DONE");
			
			cav.setCav_assign_to(cav.getCav_cr_by());
			cav.setCav_stage_lid(lkStage.getLk_id());//scrutiny done
			
			caveatService.save(cav);
			
			CaveatStage cs=new CaveatStage();
			cs.setCs_cav_mid(cav.getCav_id());
			cs.setCs_stage_lid(lkStage.getLk_id());
			cs.setCs_cr_by(u.getUm_id());
			cs.setCs_cr_date(new Date());
			
			caveatService.saveCaveatStage(cs);
			
			response.setResponse("SUBMIT");
			
		}
		else
		{
			for(CheckList obj:cavDetails.getCheckList())
			{
				if(obj.getCheckbox()==true)
				   {
					CaveatCheckListMapping cclp=new CaveatCheckListMapping();
					
					cclp.setCm_cav_mid(cav.getCav_id());
					cclp.setCm_checklist_id(obj.getC_id());
					cclp.setCm_cr_by(u.getUm_id());
					cclp.setCm_cr_date(new Date());
					cclp.setCm_rec_status(1);
					cclp.setCm_remark(obj.getC_remark());
					scrutinyService.saveCaveatCheckList(cclp);
					
				   }
			  }	
			Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "SUPERVISIOR_DEFECTS");
			cav.setCav_assign_to(cav.getCav_cr_by());
			cav.setCav_stage_lid(lkStage.getLk_id());
			
			caveatService.save(cav);
			
			CaveatStage cs=new CaveatStage();
			cs.setCs_cav_mid(cav.getCav_id());
			cs.setCs_stage_lid(lkStage.getLk_id());
			cs.setCs_cr_by(u.getUm_id());
			cs.setCs_cr_date(new Date());
			
			caveatService.saveCaveatStage(cs);
			
			response.setResponse("REJECT");
		}
		  
		jsonData=cm.convert_to_json(response);

		return jsonData;
	 }

	@RequestMapping(value = "/submit_application_file", method = RequestMethod.POST,consumes = {"application/xml", "application/json"})
	public @ResponseBody String submitApplicationFile(@RequestBody Application application,HttpSession session)
	 {
		User u = (User) session.getAttribute("USER");

		String jsonData = null;
		ActionResponse<Caveat> response = new ActionResponse();
		
		Application app=applicationService.getByPk(application.getAp_id());
		
		List<ApplicationCheckListMapping> mappingold = applicationService.getApplicationCheckList(application.getAp_id());
		for(ApplicationCheckListMapping mapping:mappingold){
			mapping.setCm_rec_status(2);
			scrutinyService.saveApplicationCheckList(mapping);
		}
		
		if(application.getCheckList().isEmpty())
		{
			Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "REPORTING_DONE");
			
			app.setAp_assign_to(app.getAp_cr_by());
			app.setAp_stage_lid(lkStage.getLk_id());//scrutiny done
			
			applicationService.save(app);
			
			ApplicationStage cfs=new ApplicationStage();
			
			cfs.setAs_ap_mid(app.getAp_id());
			cfs.setAs_stage_lid(lkStage.getLk_id());
			cfs.setAs_cr_by(u.getUm_id());
			cfs.setAs_cr_date(new Date());
			
			caseFileStageService.saveApplication(cfs);
			
			response.setResponse("SUBMIT");
			
		}
		else
		{
			for(CheckList obj:application.getCheckList())
			{
				if(obj.getCheckbox()==true)
				   {
					ApplicationCheckListMapping cclp=new ApplicationCheckListMapping();
					
					cclp.setCm_ap_mid(app.getAp_id());
					cclp.setCm_checklist_id(obj.getC_id());
					cclp.setCm_cr_by(u.getUm_id());
					cclp.setCm_cr_date(new Date());
					cclp.setCm_rec_status(1);
					cclp.setCm_remark(obj.getC_remark());
					scrutinyService.saveApplicationCheckList(cclp);					
				   }
			  }	
			Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "SUPERVISIOR_DEFECTS");
			app.setAp_assign_to(app.getAp_cr_by());
			app.setAp_stage_lid(lkStage.getLk_id());
			
			applicationService.save(app);
			
			ApplicationStage cfs=new ApplicationStage();
			
			cfs.setAs_ap_mid(app.getAp_id());
			cfs.setAs_stage_lid(lkStage.getLk_id());
			cfs.setAs_cr_by(u.getUm_id());
			cfs.setAs_cr_date(new Date());
			
			caseFileStageService.saveApplication(cfs);
			
			response.setResponse("REJECT");
		}
		  
		jsonData=cm.convert_to_json(response);

		return jsonData;
	 }
	
	@RequestMapping(value = "/getCaseList", method = RequestMethod.GET)
	public @ResponseBody String getCaseList(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<RegisteredCaseDetails> newDraftList = new ArrayList<RegisteredCaseDetails>();

		List<RegisteredCaseDetails> draftDetails = scrutinyService.getCaseDetails(user.getUm_id());
		ActionResponse<RegisteredCaseDetails> response = new ActionResponse<RegisteredCaseDetails>();

		PetitionerDetails pDetails =null;

		RespondentDetails rDetails = null;		

		for (RegisteredCaseDetails rcd : draftDetails) {
			pDetails= new PetitionerDetails();
			rDetails= new RespondentDetails();
			pDetails =caseFileService.getFirstPetitioner(rcd.getRcd_id());
			rDetails=caseFileService.getFirstRespondent(rcd.getRcd_id());
			        if(pDetails.getPt_id()!=null){
			         rcd.setPetitionerDetails(pDetails);
			        }
			        if(rDetails.getRt_id()!=null) {
			          rcd.setRespondentDetails(rDetails);
			        }		
			newDraftList.add(rcd);
		}

		int draftcount = 0;
		draftcount = draftDetails.size();

		System.out.println(draftcount);

		if (newDraftList != null && newDraftList.size() != 0)
			response.setResponse("TRUE");
		response.setData(draftcount);
		response.setModelList(newDraftList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/getCaveatList", method = RequestMethod.GET)
	public @ResponseBody String getCaveatList(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<Caveat> newDraftList = new ArrayList<Caveat>();

		List<Caveat> draftDetails = scrutinyService.getCaveatDetails(user.getUm_id());
		ActionResponse<Caveat> response = new ActionResponse<Caveat>();

		CaveatPetitionerDetails pDetails =null;

		CaveatRespondentDetails rDetails = null;

		

		for (Caveat cav : draftDetails) {
			pDetails= new CaveatPetitionerDetails();
			rDetails= new CaveatRespondentDetails();
			pDetails =caveatService.getFirstPetitioner(cav.getCav_id());
			rDetails=caveatService.getFirstRespondent(cav.getCav_id());
			        if(pDetails.getCpt_id()!=null){
			         cav.setPetitionerDetails(pDetails);
			        }
			        if(rDetails.getCrt_id()!=null) {
			          cav.setRespondentDetails(rDetails);
			        }
		
			newDraftList.add(cav);
		}

		int draftcount = 0;
		draftcount = draftDetails.size();

		System.out.println(draftcount);

		if (newDraftList != null && newDraftList.size() != 0)
			response.setResponse("TRUE");
		response.setData(draftcount);
		response.setModelList(newDraftList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/getApplicationList", method = RequestMethod.GET)
	public @ResponseBody String getApplicationList(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<Application> newApplicationList= scrutinyService.getApplicationDetails(user.getUm_id());
		ActionResponse<Application> response = new ActionResponse<Application>();

		if (newApplicationList != null && newApplicationList.size() != 0)
			response.setResponse("TRUE");

		response.setModelList(newApplicationList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/getStampReporterData", method = RequestMethod.GET)
	@ResponseBody
	public String getStampReporterData(HttpServletRequest request) {

		String id = request.getParameter("docId");
		StampReporterData response = null;

		Long doc = new Long(id);
		ActionResponse<StampReporterData> pd = new ActionResponse<StampReporterData>();
		String jsonData = null;
		
		
		response = scrutinyService.getStampReporterData(doc);
		 
		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelData(response);
			jsonData = cm.convert_to_json(pd);
		}
		return jsonData;
	}
	@RequestMapping(value = "/getCaseHistory", method = RequestMethod.GET)
	@ResponseBody
	public String getCaseHistory(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaseCheckListMapping> mapping = null;

		Long doc = new Long(id);
		ActionResponse<CaseCheckListMapping> response = new ActionResponse<CaseCheckListMapping>();
		String jsonData = null;

		mapping = caseFileService.getCaseReportingHistory(doc);

		if (mapping != null) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	@RequestMapping(value = "/getCaveatHistory", method = RequestMethod.GET)
	@ResponseBody
	public String getCaveatHistory(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaveatCheckListMapping> mapping = null;

		Long doc = new Long(id);
		ActionResponse<CaveatCheckListMapping> response = new ActionResponse<CaveatCheckListMapping>();
		String jsonData = null;

		mapping = caveatService.getCaveatReportingHistory(doc);

		if (mapping != null) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	@RequestMapping(value = "/getApplicationHistory", method = RequestMethod.GET)
	@ResponseBody
	public String getApplicationHistory(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<ApplicationCheckListMapping> mapping = null;

		Long doc = new Long(id);
		ActionResponse<ApplicationCheckListMapping> response = new ActionResponse<ApplicationCheckListMapping>();
		String jsonData = null;

		mapping = applicationService.getApplicationReportingHistory(doc);

		if (mapping != null) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}


}
