package com.dms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CIS.model.CivilT;
import com.CIS.model.CivilTA;
import com.dms.model.ActionResponse;
import com.dms.model.AllowEfiling;
import com.dms.model.CaseFileDetail;
import com.dms.model.CaseType;
import com.dms.model.User;
import com.dms.service.EcourtAddCaseService;
import com.dms.service.SearchFileService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/searchcasefile")
public class SearchFileController 
{
private GlobalFunction cm;
	
	@Autowired
	private SearchFileService searchFileService;
	@Autowired
	private EcourtAddCaseService ecourtAddCaseService;
	
	public SearchFileController()
	{
		cm = new GlobalFunction();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String addCaveat() {

		return "/application/searchCaseFile";
	}
	
	@RequestMapping(value = "/validateApplicationCode/{id},{appno}", method =  RequestMethod.GET)
	public String validateApplicationCode(Model model, @PathVariable Integer id, @PathVariable String appno,HttpSession session) 
	{

		AllowEfiling ae = null;
		String viewname="/content/access_denied";
		String result = "false";
		String message="";
		CaseFileDetail cfd=null;
		CaseFileDetail cfd1=new CaseFileDetail();

		
		User user = (User) session.getAttribute("USER");
		ActionResponse<AllowEfiling> rd = new ActionResponse<AllowEfiling>();
		String jsonData = null;

			
		
			
			ae = searchFileService.codeValidation(id,appno,'A');
	
				if(ae==null) {
					message="Not A valid Code ";
					viewname="/content/access_denied";
					model.addAttribute("message", message);
				}
				else if(ae.getAe_reference_mid()!=null){
					message="Code is already in Use ";
					viewname="/content/access_denied";
					model.addAttribute("message", message);
				}
				else
				{
					
					if(user.getUsername().equals(ae.getAe_aor())) {
					
					viewname="application/addApplication";
					
			        cfd=searchFileService.getCaseFile(ae.getAe_case_year(), ae.getAe_case_no(), ae.getAe_case_types());
					//viewname="/ecourt/addCase";
					/*	rd.setResponse("TRUE");	
						rd.setModelData(ae);*/
			        if(cfd==null) {
			        	CaseType ct=ecourtAddCaseService.getCaseTypeByLabel(ae.getAe_case_types());
			        	
			        	cfd1.setFd_case_no(ae.getAe_case_no());
			        	cfd1.setFd_case_year(ae.getAe_case_year());
			        	cfd1.setFd_case_type(ct.getCt_id());
			        	cfd1.setFd_cr_by(90009L);
			        	cfd1.setFd_cr_date(new Date());
			        	cfd1.setFd_rec_status(1);
			        	cfd=searchFileService.saveCaseDetails(cfd1);
			        	 cfd=searchFileService.getCaseFile(ae.getAe_case_year(), ae.getAe_case_no(), ae.getAe_case_types());
			        }
						model.addAttribute("fd_id", cfd.getFd_id());
						model.addAttribute("caseType", cfd.getCaseType().getCt_name());
						model.addAttribute("caseNo", cfd.getFd_case_no());
						model.addAttribute("caseYear", cfd.getFd_case_year());
						model.addAttribute("code", ae.getAe_code());
						model.addAttribute("appno", ae.getAe_appno());
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
	
	@RequestMapping(value = "/searchCaseFile", method = RequestMethod.GET)
	@ResponseBody
	public String searchCaseFile(HttpServletRequest request) {

		String case_year = request.getParameter("case_year");
		String case_type = request.getParameter("case_type");
		String caseno = request.getParameter("case_no");
		
		Integer year =new Integer(case_year);
		
		Long caseyear = new Long(case_year);
		Long casetype = new Long(case_type);
		CaseFileDetail cfd1=new CaseFileDetail();
		
		CivilT civilT=null;
		CivilTA civilTA=null;
		
		List<CaseFileDetail> cfd=searchFileService.getCaseFile(caseyear,casetype,caseno);
		
		CaseType ct=searchFileService.getCaseTypeById(casetype);

		ActionResponse<CaseFileDetail> response = new ActionResponse<CaseFileDetail>();
		String jsonData = null;


		if (cfd.size() != 0) {
			response.setResponse("TRUE");
			response.setModelList(cfd);
			jsonData = cm.convert_to_json(response);

		}else if(cfd.size() == 0) {/*
        	
			Short caseId=searchFileService.getCaseTypes(ct.getCt_label());
			civilT=searchFileService.getCaseNoForPendingFfromCis(caseId, Integer.parseInt(caseno), caseyear.shortValue());
			civilTA=searchFileService.getCaseNoForPendingFfromCisDisposed(caseId, Integer.parseInt(caseno), caseyear.shortValue());
			
			if(civilT==null) {
			if(true) {
        	cfd1.setFd_case_no(caseno);
        	cfd1.setFd_case_year(year);
        	cfd1.setFd_case_type(casetype);
        	cfd1.setFd_first_petitioner(civilT.getPet_name());
        	cfd1.setFd_first_respondent(civilT.getRes_name());
        	cfd1.setFd_cr_by(90009L);
        	cfd1.setFd_cr_date(new Date());
        	cfd1.setFd_rec_status(1);
        	cfd1=searchFileService.saveCaseDetails(cfd1);
        	 cfd=searchFileService.getCaseFile(caseyear,casetype,caseno);
        	 response.setResponse("TRUE");
 			response.setModelList(cfd);
 			jsonData = cm.convert_to_json(response);
			}
			else if(civilTA!=null) {
				cfd1.setFd_case_no(civilTA.getReg_no()+"");
	        	cfd1.setFd_case_year(year);
	        	cfd1.setFd_case_type(casetype);
	        	cfd1.setFd_first_petitioner(civilTA.getPet_name());
	        	cfd1.setFd_first_respondent(civilTA.getRes_name());
	        	cfd1.setFd_cr_by(90009L);
	        	cfd1.setFd_cr_date(new Date());
	        	cfd1.setFd_rec_status(1);
	        	cfd1=searchFileService.saveCaseDetails(cfd1);
	        	 cfd=searchFileService.getCaseFile(caseyear,casetype,caseno);
	        	 response.setResponse("TRUE");
	 			response.setModelList(cfd);
	 			jsonData = cm.convert_to_json(response);
			}
			else{
				response.setResponse("FALSE");
				jsonData = cm.convert_to_json(response);
			}
        */
		

        	cfd1.setFd_case_no(caseno);
        	cfd1.setFd_case_year(year);
        	cfd1.setFd_case_type(casetype);
        /*	cfd1.setFd_first_petitioner(civilT.getPet_name());
        	cfd1.setFd_first_respondent(civilT.getRes_name());*/
        	cfd1.setFd_cr_by(90009L);
        	cfd1.setFd_cr_date(new Date());
        	cfd1.setFd_rec_status(1);
        	cfd1=searchFileService.saveCaseDetails(cfd1);
        	 cfd=searchFileService.getCaseFile(caseyear,casetype,caseno);
        	 response.setResponse("TRUE");
 			response.setModelList(cfd);
 			jsonData = cm.convert_to_json(response);
			
			
			
		}
		else{
			response.setResponse("FALSE");
			jsonData = cm.convert_to_json(response);
		}
		return jsonData;

	}
	

}
