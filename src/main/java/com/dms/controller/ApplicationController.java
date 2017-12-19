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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
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

import com.dms.model.ActionResponse;
import com.dms.model.Application;
import com.dms.model.ApplicationCheckListMapping;
import com.dms.model.ApplicationCourtFee;
import com.dms.model.ApplicationStage;
import com.dms.model.ApplicationTypes;
import com.dms.model.ApplicationUploaded;
import com.dms.model.CaseFileStage;
import com.dms.model.IndexField;
import com.dms.model.Lookup;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.ApplicationService;
import com.dms.service.CaseFileStageService;
import com.dms.service.LookupService;
import com.dms.service.UserRoleService;
import com.dms.utility.GlobalFunction;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

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
	private LookupService lookupService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private CaseFileStageService caseFileStageService;
	
	
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
	
	@RequestMapping(value = "/applicationDraftForm", method = RequestMethod.GET)
	public String applicationDraftForm() {

		return "/application/applicationDraftForm";
	}
	
	@RequestMapping(value = "/applicationDraftView/{id}", method = RequestMethod.GET)
	public String draftView(Model model, @PathVariable Long id,HttpSession session) {

		User u=(User) session.getAttribute("USER");
		
		String viewname="/content/access_denied";
		String message="";
		Application app = applicationService.getByPk(id);
		if(app!=null){
			if(u.getUm_id().longValue()==app.getAp_cr_by().longValue())
			{
				if(app.getCaseStage().getLk_longname().equals("DRAFT"))
				{
					viewname="/application/addApplication";					
				}
				else if(app.getCaseStage().getLk_longname().equals("SUPERVISIOR_DEFECTS"))
				{
					boolean flag=applicationService.checkDateValidity(app.getAp_id());
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
	

	
	@RequestMapping(value = "/addApplication", method = RequestMethod.POST)
	@ResponseBody
	public String addRegisterCase(@RequestBody Application application,HttpSession session) 
	{
		User user = (User) session.getAttribute("USER");
		ActionResponse<Application> rd = new ActionResponse<Application>();
		String jsonData = null;
		
		Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DRAFT");
		
		if(application.getAp_id()==null)
		{			
			application.setAp_cr_by(user.getUm_id());
			application.setAp_assign_to(user.getUm_id());
			application.setAp_cr_date(new Date());
			application.setAp_stage_lid(lkStage.getLk_id());
			
			application=applicationService.addApplication(application);
			
			ApplicationStage as=new ApplicationStage();
			as.setAs_ap_mid(application.getAp_id());
			as.setAs_stage_lid(lkStage.getLk_id());
			as.setAs_cr_by(user.getUm_id());
			as.setAs_cr_date(new Date());
			rd.setResponse("ADD");
			applicationService.saveApplicationStage(as);
		}else
			{
			rd.setResponse("UPDATE");
				application=applicationService.save(application);				
			}
		rd.setModelData(application);
		jsonData = cm.convert_to_json(rd);

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
	
	@RequestMapping(value = "/addCourtFee", method = RequestMethod.POST)
	@ResponseBody
	public String addCourtFee(@RequestBody ApplicationCourtFee courtFee,
			HttpSession session) {

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
	public String submitRegisterApplication(@RequestBody Application application,HttpSession session) {
		String jsonData="";
		ActionResponse<Application> response=new ActionResponse<Application>();
		User user = (User) session.getAttribute("USER");
		boolean submit=true;
	
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
			
			UserRole ur=userRoleService.getByUserRole("ApplicationScrutiny");
			
			if(ur.getUr_id()!=null)
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
					
					application.setAp_assign_to(ur.getUr_um_mid());
					application.setAp_stage_lid(lkStage.getLk_id());
					application=applicationService.save(application);
					
					response.setResponse("TRUE");
					response.setModelData(application);
					jsonData=cm.convert_to_json(response);
				 }
				 else if(application.getCaseStage().getLk_longname().equals("SUPERVISIOR_DEFECTS"))
				 {
					 boolean flag=applicationService.checkDateValidity(application.getAp_id());
						if(flag)
						{
							Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DIARY_NO_GENERATED");
							
							ApplicationStage cs=new ApplicationStage();
							cs.setAs_ap_mid(application.getAp_id());
							cs.setAs_stage_lid(lkStage.getLk_id());
							cs.setAs_cr_by(user.getUm_id());
							cs.setAs_cr_date(new Date());
							
							applicationService.saveApplicationStage(cs);
							
							application.setAp_assign_to(ur.getUr_um_mid());
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
	@RequestMapping(value="/copyApplicationFile",method=RequestMethod.GET)
	@ResponseBody
	public String copyApplicationFile(HttpServletRequest request)
	{
		String jsonData = null;
		
		String doc_name=request.getParameter("au_document_name");
		
		ActionResponse<IndexField> response= new ActionResponse<IndexField>();
		
	
		Lookup lookUp=lookupService.getLookUpObject("APPLICATION_PATH");	
		String draft_path=lookUp.getLk_longname();

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
		return jsonData;
	}

}
