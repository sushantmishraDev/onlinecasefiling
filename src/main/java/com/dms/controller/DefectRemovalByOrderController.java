package com.dms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActionResponse;
import com.dms.model.Amendment;
import com.dms.model.CaseFileStage;
import com.dms.model.CaseType;
import com.dms.model.DefectRemovalByOrder;
import com.dms.model.Lookup;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.DefectRemovalByOrderService;
import com.dms.service.LookupService;
import com.dms.service.UserRoleService;
import com.dms.service.UserService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/defectRemovalByOrder")
public class DefectRemovalByOrderController {
	private GlobalFunction cm;
	@Autowired
	ServletContext context;
	@Autowired
	private DefectRemovalByOrderService defectRemovalByOrderService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private LookupService lookupService;

	@Autowired
	private UserService userService;

	public DefectRemovalByOrderController() {
		cm = new GlobalFunction();
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String mylist(HttpSession session, Model model) {
		User u = (User) session.getAttribute("USER");
		model.addAttribute("user_id", u.getUm_id());
		return "/defectRemoveByOrder/manage";
	}

	@RequestMapping(value = "/getCreated/{id}", method = RequestMethod.GET)
	public @ResponseBody String getAmendmentUserWise(@PathVariable Long id, HttpSession session) {
		String jsonData = null;

		ActionResponse<DefectRemovalByOrder> response = new ActionResponse<DefectRemovalByOrder>();
		Lookup lkCreated = lookupService.getLookup("AMENDMENT_STATUS", "Amendment created");
		List<DefectRemovalByOrder> amendments = defectRemovalByOrderService.getCreatedByUser(id, 1000076L);

		if (amendments.size() > 0) {
			response.setModelList(amendments);
			response.setResponse("TRUE");
		} else {
			response.setResponse("FALSE");
			response.setData("No Records found");
		}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/gethistory/{id}", method = RequestMethod.GET)
	public @ResponseBody String getHistory(@PathVariable Long id, HttpSession session) {
		String jsonData = null;

		ActionResponse<DefectRemovalByOrder> response = new ActionResponse<DefectRemovalByOrder>();
		Lookup lkCreated = lookupService.getLookup("AMENDMENT_STATUS", "Amendment uploaded");
		List<DefectRemovalByOrder> amendments = defectRemovalByOrderService.getCreatedByUser(id,1000077L);

		if (amendments.size() > 0) {
			response.setModelList(amendments);
			response.setResponse("TRUE");
		} else {
			response.setResponse("FALSE");
			response.setData("No Records found");
		}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}

	/*@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpSession session)
			throws PersistenceException, ValidationException {
		String jsonData = null;
		ActionResponse<Amendment> response = new ActionResponse<Amendment>();
		User u = (User) session.getAttribute("USER");
		Lookup lookup = lookupService.getLookUpDMSObject("REPOSITORYPATH");
		Lookup lkUploaded = lookupService.getLookup("AMENDMENT_STATUS", "Amendment uploaded");
		Integer am_document_no = Integer.parseInt(request.getParameter("am_document_no"));
		Integer am_document_year = Integer.parseInt(request.getParameter("am_document_year"));
		Long am_id = Long.parseLong(request.getParameter("am_id"), 10);
		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		Amendment amendment = defectRemovalByOrderService.getAmendment(am_id);
		String fileName = null;
		BigInteger fileCount=null;

		if (amendment.getAm_document_no() != null && amendment.getAm_document_year() != null) {
			fileName = defectRemovalByOrderService.getFileName(amendment.getAm_at_mid(), amendment.getAm_document_no(),
					amendment.getAm_document_year(), amendment.getAm_fd_mid());
			
			fileCount=defectRemovalByOrderService.getFileCount(amendment.getAm_at_mid(), amendment.getAm_document_no(),
					amendment.getAm_document_year(), amendment.getAm_fd_mid());
		} else {
			amendment.setAm_document_no(0);
			amendment.setAm_document_year(0);
			fileName = defectRemovalByOrderService.getFileName(amendment.getAm_at_mid(), amendment.getAm_document_no(),
					amendment.getAm_document_year(), amendment.getAm_fd_mid());
			
			fileCount=defectRemovalByOrderService.getFileCount(amendment.getAm_at_mid(), amendment.getAm_document_no(),
					amendment.getAm_document_year(), amendment.getAm_fd_mid());
		}

		String label = defectRemovalByOrderService.getLabel(amendment.getAm_fd_mid());
		String path = lookup.getLk_longname() + File.separator + label;

		if (amendment.getAm_at_mid() == null) {
			path += File.separator + "petition";
		} else {
			path += File.separator + "application";
		}
		if (amendment.getAm_status().longValue() != lkUploaded.getLk_id().longValue()) {
			while (itr.hasNext()) {
				mpf = request.getFile(itr.next());
				String filepath = path + File.separator + fileName + ".pdf";
				// String
				// filepath=lookup.getLk_longname()+File.separator+amendment.getAm_id()+".pdf";
				File file = new File(path + File.separator + fileName + ".pdf");
				File newFile = new File(path + File.separator + fileName + "_old_"+fileCount+".pdf");
				if (file.renameTo(newFile)) {
					System.out.println("File rename success");
					;
				} else {
					System.out.println("File rename failed");
				}
				try {
					FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filepath));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				amendment.setAm_document_no(am_document_no);
				amendment.setAm_document_year(am_document_year);
				amendment.setAm_status(lkUploaded.getLk_id());
				amendment.setAm_uploaded_date(new Date());
				amendment = defectRemovalByOrderService.saveDrp(amendment);
				defectRemovalByOrderService.updateSubDocument(amendment.getAm_fd_mid(), fileName);
				
				
			}
			response.setResponse("TRUE");
			response.setData("Amendment uploaded successfully");
		} else {
			response.setResponse("FALSE");
			response.setData("Amendment not found");
		}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}*/
	
	// created by afnan
	
	@RequestMapping(value = "/uploadPetition", method = RequestMethod.POST)
	public @ResponseBody String uploadAmendment(MultipartHttpServletRequest request, HttpSession session) 
			throws PersistenceException, ValidationException 
	{
		String jsonData = null;
		ActionResponse<DefectRemovalByOrder> response = new ActionResponse<DefectRemovalByOrder>();
		User u = (User) session.getAttribute("USER");
		Lookup lookup = lookupService.getLookUpObject("DEFECT_REMOVAL_PATH");
		//Integer am_document_no = Integer.parseInt(request.getParameter("am_document_no"));
		//Integer am_document_year = Integer.parseInt(request.getParameter("am_document_year"));
		Long am_id = Long.parseLong(request.getParameter("drp_id"), 10);
		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		DefectRemovalByOrder defectRemovalByOrder = defectRemovalByOrderService.getDrp(am_id);
		
		RegisteredCaseDetails rcd=defectRemovalByOrderService.getByCaseTypeNoYear(defectRemovalByOrder.getCaseFileDetail().getFd_case_type(),defectRemovalByOrder.getCaseFileDetail().getFd_case_no() , defectRemovalByOrder.getCaseFileDetail().getFd_case_year());
		String amendBasepath =lookup.getLk_longname();
		
		CaseFileStage cfs=defectRemovalByOrderService.getStampReporter(rcd.getRcd_id());
		
		//UserRole ur=null;
			   /*if(defectRemovalByOrder.getCaseFileDetail().getCaseType().getCt_type().equals("civil")) {
				   ur=userRoleService.getByUserRole("CivilCaseSupervisor");
			   }
				else{
					 ur=userRoleService.getByUserRole("CriminalCaseSupervisor");
					}*/
		
		
				
			

		if (defectRemovalByOrder.getDrp_stage_lid() == 1000076L ) 
		{
			while (itr.hasNext()) 
			{
				mpf = request.getFile(itr.next());
				File file = new File(amendBasepath + File.separator + defectRemovalByOrder.getDrp_id()+".pdf");
				
				try {
					FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(file));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				defectRemovalByOrder.setDrp_stage_lid(1000077L);
				defectRemovalByOrder.setDrp_assign_to(cfs.getRcs_cr_by());
				defectRemovalByOrder.setDrp_uploaded_date(new Date());
				defectRemovalByOrder.setDrp_advUm_mid(u.getUm_id());
				defectRemovalByOrder = defectRemovalByOrderService.saveDrp(defectRemovalByOrder);
			}
				response.setResponse("TRUE");
				response.setData("File uploaded successfully");
		} 
		else 
		{
			response.setResponse("FALSE");
			response.setData("Amendment not found");
		}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}
	
}


