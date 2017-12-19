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
import com.dms.model.CaseCheckListMapping;
import com.dms.model.CaseFileStage;
import com.dms.model.Caveat;
import com.dms.model.CaveatCheckListMapping;
import com.dms.model.CaveatCourtFee;
import com.dms.model.CaveatDocuments;
import com.dms.model.CaveatPetitionerDetails;
import com.dms.model.CaveatRespondentDetails;
import com.dms.model.CaveatStage;
import com.dms.model.IndexField;
import com.dms.model.Lookup;
import com.dms.model.PetitionerDetails;
import com.dms.model.RespondentDetails;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.CaseFileStageService;
import com.dms.service.CaveatService;
import com.dms.service.LookupService;
import com.dms.service.UserRoleService;
import com.dms.utility.GlobalFunction;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

@Controller
@RequestMapping("/caveat")
public class CaveatController 
{
	private GlobalFunction cm;
	
	@Autowired
	private CaveatService caveatService;
	@Autowired
	ServletContext context;
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private CaseFileStageService caseFileStageService;
	
	
	public CaveatController()
	{
		cm = new GlobalFunction();
	}
	
	@RequestMapping(value = "/caveatDraftView/{id}", method = RequestMethod.GET)
	public String draftView(Model model, @PathVariable Long id, HttpSession session) {
	
		User u=(User) session.getAttribute("USER");
		
		String viewname="/content/access_denied";
		String message="";
		Caveat cav = caveatService.getByPk(id);
			
		
		if(cav!=null){
			if(u.getUm_id().longValue()==cav.getCav_cr_by().longValue())
			{
				if(cav.getCaseStage().getLk_longname().equals("DRAFT"))
				{
					viewname="/ecourt/addCaveat";					
				}
				else if(cav.getCaseStage().getLk_longname().equals("SUPERVISIOR_DEFECTS"))
				{
					boolean flag=caveatService.checkDateValidity(cav.getCav_id());
					if(flag)
					{
						viewname="/ecourt/addCaveat";
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

		return "/ecourt/caveatpreviewList";
	}
	@RequestMapping(value = "/getRegisterCaveat", method = RequestMethod.GET)
	@ResponseBody
	public String getRegisterCaveat(HttpServletRequest request) {

		String id = request.getParameter("docId");
		Caveat cav = null;

		Long doc = new Long(id);
		ActionResponse<Caveat> response = new ActionResponse<Caveat>();
		String jsonData = null;

		CaveatRespondentDetails caveatRespondentDetails=null;
		CaveatPetitionerDetails caveatPetitionerDetails=null;
		
		cav = caveatService.getRegisterCaveat(doc);
		caveatPetitionerDetails=caveatService.getFirstPetitioner(doc);
		caveatRespondentDetails=caveatService.getFirstRespondent(doc);
		
		    if(caveatPetitionerDetails.getCpt_id()!=null){
		    	cav.setPetitionerDetails(caveatPetitionerDetails);
		    }
		    if(caveatRespondentDetails.getCrt_id()!=null){
		    	cav.setRespondentDetails(caveatRespondentDetails);
		    	
		    }
		    
		if (cav != null) {
			response.setResponse("TRUE");
			response.setModelData(cav);
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/getCaveatPetitioner", method = RequestMethod.GET)
	@ResponseBody
	public String getCaveatPetitioner(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaveatPetitionerDetails> response = null;

		Long doc = new Long(id);
		ActionResponse<CaveatPetitionerDetails> pd = new ActionResponse<CaveatPetitionerDetails>();
		String jsonData = null;

		response = caveatService.getPetitioner(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getCaveatRespondent", method = RequestMethod.GET)
	@ResponseBody
	public String getCaveatRespondent(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaveatRespondentDetails> response = null;

		Long doc = new Long(id);
		ActionResponse<CaveatRespondentDetails> pd = new ActionResponse<CaveatRespondentDetails>();
		String jsonData = null;

		response = caveatService.getRespondent(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	
	@RequestMapping(value = "/addCaveat", method = RequestMethod.POST)
	@ResponseBody
	public String addRegisterCase(@RequestBody Caveat caveat,HttpSession session) 
	{
		User user = (User) session.getAttribute("USER");
		ActionResponse<Caveat> rd = new ActionResponse<Caveat>();
		String jsonData = null;
		
		Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DRAFT");
		
		if(caveat.getCav_id()==null)
		{			
			caveat.setCav_cr_by(user.getUm_id());
			caveat.setCav_assign_to(user.getUm_id());
			caveat.setCav_cr_date(new Date());
			caveat.setCav_stage_lid(lkStage.getLk_id());
			
			caveat=caveatService.addCaveat(caveat);
			
			CaveatStage cs=new CaveatStage();
			cs.setCs_cav_mid(caveat.getCav_id());
			cs.setCs_stage_lid(lkStage.getLk_id());
			cs.setCs_cr_by(user.getUm_id());
			cs.setCs_cr_date(new Date());
			rd.setResponse("ADD");
			caveatService.saveCaveatStage(cs);
		}else
			{
			rd.setResponse("UPDATE");
				caveat=caveatService.save(caveat);				
			}
		rd.setModelData(caveat);
		jsonData = cm.convert_to_json(rd);

		return jsonData;
	}
	
	@RequestMapping(value = "/addPetitioner", method = RequestMethod.POST)
	@ResponseBody
	public String addPetitioner(@RequestBody CaveatPetitionerDetails pDetails,
			HttpSession session) {

		CaveatPetitionerDetails response = null;
		String result = "false";
		User user = (User) session.getAttribute("USER");
		ActionResponse<CaveatPetitionerDetails> pd = new ActionResponse<CaveatPetitionerDetails>();
		Long seqcount = caveatService.getCPSequenceCount(pDetails.getCpt_cav_mid());
		String jsonData = null;
		if (pDetails.getCpt_id() != null) {
			pd.setResponse("UPDATE");
			pDetails.setCpt_mod_by(user.getUm_id());
			pDetails.setCpt_mod_date((new Date()));

		} else {
			pDetails.setCpt_cr_by(user.getUm_id());
			pDetails.setCpt_cr_date((new Date()));
			pDetails.setCpt_rec_status(1);
			seqcount++;
			pDetails.setCpt_sequence(seqcount);
			pd.setResponse("CREATE");

		}
		response = caveatService.addPetitioner(pDetails);

		if (response != null)
			jsonData = cm.convert_to_json(pd);

		return jsonData;
	}
	
	@RequestMapping(value = "/deletePetitioner/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deletePetitioner(@PathVariable Long id, HttpSession session) {

		CaveatPetitionerDetails cpd = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<CaveatPetitionerDetails> response = new ActionResponse<CaveatPetitionerDetails>();
		String jsonData = null;

		cpd = caveatService.deletePetitioner(user, id);

		if (cpd != null) {
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}

	@RequestMapping(value = "/addRespondent", method = RequestMethod.POST)
	@ResponseBody
	public String addRespondent(@RequestBody CaveatRespondentDetails rDetails,
			HttpSession session) {

		CaveatRespondentDetails crd = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<CaveatRespondentDetails> response = new ActionResponse<CaveatRespondentDetails>();
		Long seq = caveatService.getCRSeqCount(rDetails.getCrt_cav_mid());
		String jsonData = null;

		if (rDetails.getCrt_id() != null) {
			response.setResponse("UPDATE");
			rDetails.setCrt_mod_by(user.getUm_id());
			rDetails.setCrt_mod_date((new Date()));
		} else {
			rDetails.setCrt_cr_by(user.getUm_id());
			rDetails.setCrt_cr_date((new Date()));
			rDetails.setCrt_rec_status(1);
			seq++;
			rDetails.setCrt_sequence(seq);
			response.setResponse("CREATE");
		}
		crd = caveatService.addRespondent(rDetails);

		if (crd != null)
			jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/deleteRespondent/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteRespondent(@PathVariable Long id, HttpSession session) {

		CaveatRespondentDetails crd = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<CaveatRespondentDetails> pd = new ActionResponse<CaveatRespondentDetails>();
		String jsonData = null;

		crd = caveatService.deleteRespondent(user.getUm_id(), id);

		if (crd != null) {
			pd.setResponse("TRUE");
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	
	@RequestMapping(value = "/getCaveatDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getCaveatDetails(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<Caveat> newCaveatList= caveatService.getCaveatDetails(user.getUm_id());
		ActionResponse<Caveat> response = new ActionResponse<Caveat>();

		/*
		CaveatPetitionerDetails pDetails =null;

		CaveatRespondentDetails rDetails = null;

		for (Caveat rcd : draftDetails) {
			pDetails= new CaveatPetitionerDetails();
			rDetails= new CaveatRespondentDetails();
			pDetails =ecourtHomeService.getFirstPetitioner(rcd.getRcd_id());
			rDetails=ecourtHomeService.getFirstRespondent(rcd.getRcd_id());
			        if(pDetails.getPt_id()!=null){
			         rcd.setPetitionerDetails(pDetails);
			        }
			        if(rDetails.getRt_id()!=null) {
			          rcd.setRespondentDetails(rDetails);
			        }
		
			newCaveatList.add(rcd);
		}*/

		/*int draftcount = 0;
		draftcount = draftDetails.size();

		System.out.println(draftcount);*/

		if (newCaveatList != null && newCaveatList.size() != 0)
			response.setResponse("TRUE");
		//response.setData(draftcount);
		response.setModelList(newCaveatList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/addCourtFee", method = RequestMethod.POST)
	@ResponseBody
	public String addCourtFee(@RequestBody CaveatCourtFee courtFee,
			HttpSession session) {

		CaveatCourtFee ccf = null;
		String result = "false";

		User user = (User) session.getAttribute("USER");
		ActionResponse<CaveatCourtFee> response = new ActionResponse<CaveatCourtFee>();
		String jsonData = null;

		if (courtFee.getCcf_id() != null) {
			response.setResponse("UPDATE");
			courtFee.setCcf_mod_by(user.getUm_id());
			courtFee.setCcf_mod_date((new Date()));
		} else {

			courtFee.setCcf_cr_by(user.getUm_id());
			courtFee.setCcf_cr_date((new Date()));
			courtFee.setCcf_rec_status(1);
			response.setResponse("CREATE");
		}
		ccf = caveatService.addCourtFee(courtFee);

		if (ccf != null)
			jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/getCourtFee", method = RequestMethod.GET)
	@ResponseBody
	public String getCourtFee(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaveatCourtFee> response = null;

		Long doc = new Long(id);
		ActionResponse<CaveatCourtFee> pd = new ActionResponse<CaveatCourtFee>();
		String jsonData = null;

		response = caveatService.getCaveatCourtFee(doc);

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
		String file_id=request.getParameter("rcd_id");
		Long rcd_id=Long.valueOf(file_id);
		
		List<CaveatDocuments> documentList=caveatService.getUploadedCaveates(rcd_id);
		ActionResponse<CaveatDocuments> response=new ActionResponse<CaveatDocuments>();
		
		response.setModelList(documentList);
		response.setResponse("TRUE");
		
	     jsonData=cm.convert_to_json(response);
	     return jsonData;
	}

	@RequestMapping(value = "/upload_file",method = RequestMethod.POST)
	public @ResponseBody String create(MultipartHttpServletRequest request,HttpSession session,HttpServletRequest req) throws DocumentException 
	{
		
		String jsonData="";

		ActionResponse<CaveatDocuments>response=new ActionResponse<CaveatDocuments>();
		response.setResponse("TRUE");
		String ipaddress = request.getRemoteAddr();
		Caveat rcd= new Caveat();
		
		
		User user=new User();
		user=(User) session.getAttribute("USER");
		
		String file_id=req.getParameter("rcd_id");
		Long rcd_id=Long.valueOf(file_id);
		
		rcd=caveatService.getByPk(rcd_id);
		
		String documentname = rcd.getCav_draft_no();
		
		 MultipartFile mpf = null;
		 Iterator<String> itr = request.getFileNames();
		     
		     
		 String basePath="";
		 List<Lookup> lookupForRaw = lookupService.getAll("CAVEAT_PATH");
		 String draftBasepath =lookupForRaw.get(0).getLk_longname();	
			
		List <Object> errorList=new ArrayList();

				while (itr.hasNext()) 
				{
					try
					{
					mpf = request.getFile(itr.next());
			        
					
					
					CaveatDocuments c = new CaveatDocuments();
				
					String filename = mpf.getOriginalFilename();  
					
			        String temppath=draftBasepath + File.separator+documentname+".pdf";
	                
					String ext = FilenameUtils.getExtension(filename);
					
			
					  if(ext.equalsIgnoreCase("pdf"))
						{	
						  	FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(temppath));
						    
						    PdfReader readernewFile = new PdfReader(temppath);
							Integer newPageCount =readernewFile.getNumberOfPages();
							readernewFile.close();
					    
					        c.setCd_cav_mid(rcd.getCav_id());
					       	c.setCd_document_name(documentname+".pdf");
					       	c.setCd_no_of_pages(newPageCount);
					       	c.setCd_uploaded_date(new Date());
					       	c.setCd_uploaded_by(user.getUm_id());
					       	c.setCd_rec_status(1);
					       	
					       	
					       	c=caveatService.saveCaveatDocument(c);
						     
					       	response.setResponse("TRUE");
					       	response.setData(c);

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
			ActionResponse<CaveatDocuments> pd =new ActionResponse<CaveatDocuments>();
			String jsonData=null;
			SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
			String currentDate=sdf.format(new Date());
			
				CaveatDocuments au=caveatService.getCaveatDocuments(id);
						
				List<Lookup> lookupForRaw = lookupService.getAll("CAVEAT_PATH");
				String caveatBasepath =lookupForRaw.get(0).getLk_longname();
				
				String oldpath=caveatBasepath + File.separator+au.getCd_document_name();
				
				File oldfile=new File(oldpath);
				String newname=FilenameUtils.getBaseName(oldpath);
				String extension=FilenameUtils.getExtension(oldpath);
				String newpath=caveatBasepath + File.separator+newname+"_"+currentDate+"."+extension;
				
				File newfile=new File(newpath);
				boolean response=false; 
				
				if(oldfile.renameTo(newfile)){
					response=caveatService.deleteCaveatDocuments(id);
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
	
	@RequestMapping(value = "/submitCaveatCase", method = RequestMethod.POST)
	@ResponseBody
	public String submitCaveatCase(@RequestBody Caveat cav,HttpSession session) {
		String jsonData="";
		ActionResponse<Caveat> response=new ActionResponse<Caveat>();
		User user = (User) session.getAttribute("USER");
		boolean submit=true;
		CaveatPetitionerDetails pet= caveatService.getFirstPetitioner(cav.getCav_id());
		CaveatRespondentDetails rDetails = caveatService.getFirstRespondent(cav.getCav_id());
		//List<CaveatCourtFee> courtFees = null;
		//courtFees = caveatService.getCourtFee(cav.getCav_id());
		List<CaveatDocuments> documentList=caveatService.getUploadedCaveates(cav.getCav_id());
		if(documentList.isEmpty() || pet.getCpt_id()==null || rDetails.getCrt_id()==null)
		{
			submit=false;
		}
		if(submit)
		{
			if(cav.getCav_diary_no()==null){
			Long diary=caveatService.getDiarySequence();
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			
			cav.setCav_diary_no(diary+"_"+year);
			}
			
			UserRole ur=userRoleService.getByUserRole("CaseScrutiny");
			
			if(ur.getUr_id()!=null)
			{
				if(cav.getCaseStage().getLk_longname().equals("DRAFT"))
				 {
					Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DIARY_NO_GENERATED");
								
					CaveatStage cs=new CaveatStage();
					cs.setCs_cav_mid(cav.getCav_id());
					cs.setCs_stage_lid(lkStage.getLk_id());
					cs.setCs_cr_by(user.getUm_id());
					cs.setCs_cr_date(new Date());
					
					caveatService.saveCaveatStage(cs);
					
					cav.setCav_assign_to(ur.getUr_um_mid());
					cav.setCav_stage_lid(lkStage.getLk_id());
					cav=caveatService.save(cav);
					
					response.setResponse("TRUE");
					response.setModelData(cav);
					jsonData=cm.convert_to_json(response);
				 }
				 else if(cav.getCaseStage().getLk_longname().equals("SUPERVISIOR_DEFECTS"))
				 {
					 boolean flag=caveatService.checkDateValidity(cav.getCav_id());
						if(flag)
						{
							Lookup lkStage=lookupService.getLookup("ECOURT_STAGE", "DIARY_NO_GENERATED");
							
							CaveatStage cs=new CaveatStage();
							cs.setCs_cav_mid(cav.getCav_id());
							cs.setCs_stage_lid(lkStage.getLk_id());
							cs.setCs_cr_by(user.getUm_id());
							cs.setCs_cr_date(new Date());
							
							caveatService.saveCaveatStage(cs);
							
							cav.setCav_assign_to(ur.getUr_um_mid());
							cav.setCav_stage_lid(lkStage.getLk_id());
							cav=caveatService.save(cav);
							
							response.setResponse("TRUE");
							response.setModelData(cav);
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
	@RequestMapping(value = "/getCaveatCheckList", method = RequestMethod.GET)
	@ResponseBody
	public String getCaveatCheckList(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaveatCheckListMapping> mapping = null;

		Long doc = new Long(id);
		ActionResponse<CaveatCheckListMapping> response = new ActionResponse<CaveatCheckListMapping>();
		String jsonData = null;

		mapping = caveatService.getCaveatCheckList(doc);

		if (mapping != null) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	@RequestMapping(value = "/getCaveatList", method = RequestMethod.GET)
	public @ResponseBody String getCaveatList(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<Caveat> newDraftList = new ArrayList<Caveat>();

		List<Caveat> draftDetails = caveatService.getCaveatDetails(user.getUm_id());
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
	@RequestMapping(value = "/getCheckList", method = RequestMethod.GET)
	@ResponseBody
	public String getCheckList(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaveatCheckListMapping> mapping = null;

		Long doc = new Long(id);
		ActionResponse<CaveatCheckListMapping> response = new ActionResponse<CaveatCheckListMapping>();
		String jsonData = null;

		mapping = caveatService.getCaveatCheckList(doc);

		if (mapping != null) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	
	@RequestMapping(value="/copyCaveatFile",method=RequestMethod.GET)
	@ResponseBody
	public String copyCaveatFile(HttpServletRequest request)
	{
		String jsonData = null;
		
		String doc_name=request.getParameter("cd_document_name");
		
		ActionResponse<IndexField> response= new ActionResponse<IndexField>();
		
	
		Lookup lookUp=lookupService.getLookUpObject("CAVEAT_PATH");	
		String draft_path=lookUp.getLk_longname();

		File source = new File(draft_path+File.separator+doc_name);	
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


}
