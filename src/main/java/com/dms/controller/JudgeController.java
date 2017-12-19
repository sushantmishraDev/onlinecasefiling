package com.dms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.Judge;
import com.dms.model.Lookup;
import com.dms.model.User;
import com.dms.service.JudgeService;
import com.dms.service.LookupService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.JudgeMstrValidator;

@Controller
public class JudgeController {
	public static final String judge_master_view = "judgemaster/judgeMaster";
	
	@Autowired
	private LookupService lookUpService;
	
	@Autowired
	JudgeService judgeService;

	@Autowired
	private JudgeMstrValidator judgeMstrValidator;
	

	public void setJudgeMstrValidator(JudgeMstrValidator judgeMstrValidator) {
		this.judgeMstrValidator = judgeMstrValidator;
	}

	public void setLookUpService(LookupService lookUpService) {
		this.lookUpService = lookUpService;
	}

	public void setJudgeService(JudgeService judgeService) {
		this.judgeService = judgeService;
	}

	private GlobalFunction commonMethods;

	public JudgeController() {
		commonMethods = new GlobalFunction();
	}

	@RequestMapping(value = "judge_master", method = RequestMethod.GET)
	public String index(Model model) {

		return judge_master_view;
	}

	@RequestMapping(value="judge_master/getjudgeDetails",method=RequestMethod.GET)
	public @ResponseBody String getJudgeData(Model model)
	{
		String jsonData=null;
		List<Judge> getDetails=judgeService.getAll();
		if(getDetails!=null)
		{
			jsonData=commonMethods.convert_to_json(getDetails);
		}
		System.out.println(jsonData);
		return jsonData;

	}
	/* [END]To JSON GET Method List of bundle_Alloction Model  */

	@RequestMapping(value="judge_master/getjudgeList",method=RequestMethod.GET)
	public @ResponseBody String getjudge(Model model)
	{
		String jsonData=null;
		List<Lookup> getjudge=lookUpService.getAll("JUDGE_TYPE");
		
		if(getjudge!=null)
		{
			jsonData=commonMethods.convert_to_json(getjudge);
		}
		System.out.println(jsonData);
		return jsonData;

	}
	
	
	@RequestMapping(value = "judge_master/getbranchDetails")
	public @ResponseBody String getmasterdata(Model model) {
		String jsonData=null;

		String branchData=null;
			
		
		List<Lookup> lkData=lookUpService.getAll("BRANCH");		
		if(lkData != null){
			branchData = commonMethods.convert_to_json(lkData);
		}		
		jsonData = "{\"branchData\":"+branchData+"}";
		return jsonData;
	}
	
	
	@RequestMapping(value = "judge_master/save", method = RequestMethod.POST)	
	public @ResponseBody String save(@RequestBody Judge judge,HttpSession session)
	{	
		ActionResponse<Judge> response = judgeMstrValidator.doValidation(judge);
		System.out.println("== USER SAVE ==");
		String jsonData = null;
	//	InwardBundle ib=new InwardBundle();
		
		//System.out.println("user.getUm_id()  "+user.getUm_id());		
		if(response.getResponse() == "FALSE")
		{
			jsonData = commonMethods.convert_to_json(response);	
		}
		else
		{
			User user = (User) session.getAttribute("USER");	
			if(judge.getCr_by()==null){
				judge.setCr_by(user.getUm_id());
				judge.setCr_date(new Date());}
				else
				{
					judge.setMod_by(user.getUm_id());
					judge.setMod_date(new Date());
				}
			
			judgeService.save(judge);
		}
	
	//	jsonData=commonMethods.convert_to_json(judgeService.getj(inwardBundle.getIb_id()));
		jsonData = commonMethods.convert_to_json(response);

	return jsonData;
	}

}
