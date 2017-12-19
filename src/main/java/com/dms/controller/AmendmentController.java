package com.dms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.Amendment;
import com.dms.model.CaseFileDetail;
import com.dms.model.Lookup;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.User;
import com.dms.service.AmendmentService;
import com.dms.service.LookupService;
import com.dms.service.SearchFileService;
import com.dms.service.UserService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/amendment")
public class AmendmentController 
{
	private GlobalFunction cm;
	@Autowired
	ServletContext context;
	@Autowired
	private AmendmentService amendmentService;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SearchFileService searchFileService;
	
	public AmendmentController()
	{
		cm = new GlobalFunction();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String addCaveat() {

		return "/amendment/searchcasefile";
	}
	@RequestMapping(value = "/manage/{id}", method = RequestMethod.GET)
	public String addApplication(Model model,@PathVariable Integer id) 
	{
		
		model.addAttribute("fd_id", id);	

		return "/amendment/manage";
	}
	
	@RequestMapping(value = "/searchCaseFile", method = RequestMethod.GET)
	@ResponseBody
	public String searchCaseFile(HttpServletRequest request) {

		String case_year = request.getParameter("case_year");
		String case_type = request.getParameter("case_type");
		String caseno = request.getParameter("case_no");
		
		Long caseyear = new Long(case_year);
		Long casetype = new Long(case_type);
		
		List<CaseFileDetail> cfd=searchFileService.getCaseFile(caseyear,casetype,caseno);

		ActionResponse<CaseFileDetail> response = new ActionResponse<CaseFileDetail>();
		String jsonData = null;


		if (cfd != null) {
			response.setResponse("TRUE");
			response.setModelList(cfd);
			jsonData = cm.convert_to_json(response);

		}else{
			response.setResponse("FALSE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	@RequestMapping(value = "/getamendments/{id}", method = RequestMethod.GET)
	public @ResponseBody String getAmendmentHistory(@PathVariable Long id,HttpSession session) {
		String jsonData=null;
		
		ActionResponse<Amendment> response= new ActionResponse<Amendment>();
		List<Amendment> amendments=amendmentService.getAmendments(id);
		if(amendments.size()>0){
			response.setModelList(amendments);
			response.setResponse("TRUE");
		}else{
			response.setResponse("FALSE");
			response.setData("No Records found");
		}	
		jsonData=cm.convert_to_json(response);
		return jsonData;
	}
	@RequestMapping(value = "/getadvocates/{id}", method = RequestMethod.GET)
	public @ResponseBody String getAdvocates(@PathVariable Long id,HttpSession session) {
		ActionResponse<User> response = new ActionResponse<User>();
		String jsonData = null;
		CaseFileDetail casefileDetail=amendmentService.getCaseFile(id);
		Integer caseNo=Integer.parseInt(casefileDetail.getFd_case_no());
		Integer caseYear=casefileDetail.getFd_case_year();
		RegisteredCaseDetails rcd=amendmentService.getRegisterCase(casefileDetail.getFd_case_type(),caseNo,caseYear);
		User user=new User();
		if(rcd.getRcd_cr_by()!=null)
			user=userService.getUser(rcd.getRcd_cr_by());
		
		List<User> users=amendmentService.getApplicationUsers(casefileDetail.getFd_id(),1000043L);
		
		if(user.getUm_id()!=null){
			users.add(user);
		}
		response.setModelList(users);
		
		if(users.isEmpty()){
			response.setResponse("FALSE");
			response.setData("No Users Found");
		}else{
			response.setResponse("TRUE");	
		}
		jsonData=cm.convert_to_json(response);
		
		return jsonData;
	}
	@RequestMapping(value = "/searchuser", method = RequestMethod.GET)
	@ResponseBody
	public String searchUser(HttpServletRequest request) {

		String name = request.getParameter("name");
		
		List<User> users=amendmentService.searchUser(name);

		ActionResponse<User> response = new ActionResponse<User>();
		String jsonData = null;

		if (!users.isEmpty()) {
			response.setResponse("TRUE");
			response.setModelList(users);
			jsonData = cm.convert_to_json(response);

		}else{
			response.setResponse("FALSE");
			jsonData = cm.convert_to_json(response);
		}
		return jsonData;

	}
	
	@RequestMapping(value = "/addamendment", method = RequestMethod.POST)
	@ResponseBody
	public String addAmendment(@RequestBody Amendment amendment,HttpSession session) {
		String jsonData=null;
		User u=(User) session.getAttribute("USER");
		
		ActionResponse<Amendment> response=new ActionResponse<>();
		Lookup lkCreated=lookupService.getLookup("AMENDMENT_STATUS", "Amendment created");
		amendment.setAm_status(lkCreated.getLk_id());
		amendment.setAm_created(new Date());
		amendment.setAm_created_by(u.getUm_id());
		if(amendment.getAm_type().equals("P")){
			Amendment amendmentexist=amendmentService.getAmendment(amendment.getAm_fd_mid(),amendment.getAm_type(),lkCreated.getLk_id());
			if(amendmentexist==null){
				amendment=amendmentService.saveAmendment(amendment);
				amendment=amendmentService.getAmendment(amendment.getAm_id());
				if(amendment.getAm_id()!=null){
					response.setResponse("TRUE");
					response.setData("Amendment for petition created successfully");
				}else{
					response.setResponse("FALSE");
					response.setData("Error occurred while adding amendment");
				}
			}else{
				response.setResponse("FALSE");
				response.setData("Amendment for petition already exist in the system");
			}
		}else{
			amendment=amendmentService.saveAmendment(amendment);
			amendment=amendmentService.getAmendment(amendment.getAm_id());
			if(amendment.getAm_id()!=null){
				response.setResponse("TRUE");
				response.setData("Amendment for application created successfully");
			}else{
				response.setResponse("FALSE");
				response.setData("Error occurred while adding amendment");
			}
		}
		response.setModelData(amendment);
		jsonData=cm.convert_to_json(response);
		return jsonData;	
	}
	@RequestMapping(value = "/deleteamendment/{id}", method = RequestMethod.GET)
	public @ResponseBody String deleteAmendment(@PathVariable Long id,HttpSession session) {
		String jsonData=null;
		ActionResponse<Amendment> response=new ActionResponse<>();
		Amendment amendment=amendmentService.getAmendment(id);
		Lookup lkuploaded=lookupService.getLookup("AMENDMENT_STATUS", "Amendment uploaded");
		if(amendment.getAm_status().longValue()==lkuploaded.getLk_id().longValue()){
			response.setResponse("False");
			response.setData("Document for this amendment has been already uploaded");	
		}else{
		Lookup lk=lookupService.getLookup("AMENDMENT_STATUS", "Amendment deleted");
		amendment.setAm_status(lk.getLk_id());
		amendment=amendmentService.saveAmendment(amendment);		
		response.setResponse("TRUE");
		response.setData("Amendment deleted successfully");
		}
		jsonData=cm.convert_to_json(response);
		return jsonData;
	}

	
}
