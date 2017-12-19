

package com.dms.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.ActionResponse;
import com.dms.model.Lookup;
import com.dms.model.SecurityQuestion;
import com.dms.model.User;
import com.dms.service.LookupService;
import com.dms.service.SecurityQuestionService;


@Component
public class SecurityQuestionValidator {
	
	@Autowired
	SecurityQuestionService securityQuestionService;
	
	@Autowired
	LookupService lookupservice;

public ActionResponse doValidation(SecurityQuestion sq){
		
		ActionResponse response = new ActionResponse();
		Validator validation=new Validator();
	
		List<String> errorList = new ArrayList<String>();
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";
		
		if (sq.getPra_answer().equals(""))
			sq.setPra_answer(null);
		validation.isRequired("Answer",sq.getPra_answer());
		validation.isRequired("New Password",sq.getPassword());
		
		
		if(sq.getPassword()!=null && sq.getPasswordc()!=null)
			validation.isMatch("Password",sq.getPassword(),sq.getPasswordc());
		      
			
		error=validation.getError();
		
		if(sq.getPassword()!=null)
		{
			Lookup lk =new Lookup();
			List<Lookup> lkup=lookupservice.CheckRegex("REGEX_COMPLEXITY");
			String regex=lkup.get(0).getLk_longname();
			String msg=lkup.get(0).getLk_value();
			validation.checkRegEx("password", sq.getPassword(),regex, msg);
		}
		
		if(!error.isEmpty())
		{
			status = "FALSE";
		}
				
		response.setResponse(status);
		response.setDataMapList(error);
		
		
		return response;
	}
public ActionResponse doValidationForChanePwd(SecurityQuestion sq){
	
	ActionResponse response = new ActionResponse();
	Validator validation=new Validator();

	List<String> errorList = new ArrayList<String>();
	Map<String, List> error = new HashMap<String, List>();
	String status = "TRUE";
	
	validation.isRequired("New Password",sq.getPassword());
	validation.isRequired("New Password",sq.getPasswordc());
	
	if(sq.getPassword()!="" && sq.getPasswordc()!="")
		validation.isMatch("Password",sq.getPassword(),sq.getPasswordc());
	      
	if(sq.getPassword()!="")
	{
		Lookup lk =new Lookup();
		List<Lookup> lkup=lookupservice.CheckRegex("REGEX_COMPLEXITY");
		String regex=lkup.get(0).getLk_longname();
		String msg=lkup.get(0).getLk_value();
		validation.checkRegEx("password", sq.getPassword(),regex, msg);
	}
	
	error=validation.getError();
	
	
	
	
	if(!error.isEmpty())
	{
		status = "FALSE";
	}
			
	response.setResponse(status);
	response.setDataMapList(error);
	
	
	return response;
}

public ActionResponse doValidationForPassword(User ur){
	
	
	ActionResponse response = new ActionResponse();
	Validator validation=new Validator();
	

	List<String> errorList = new ArrayList<String>();
	Map<String, List> error = new HashMap<String, List>();
	String status = "TRUE";
	
	validation.isRequired("password",ur.getPassword());
	validation.isRequired("confirmpassword",ur.getConfirmpassword());
	
	boolean passwordcompare=false;
	if(ur.getPassword()!="")
	{
		Lookup lk =new Lookup();
		List<Lookup> lkup=lookupservice.CheckRegex("REGEX_COMPLEXITY");
		String regex=lkup.get(0).getLk_longname();
		String msg=lkup.get(0).getLk_value();
		validation.checkRegEx("password", ur.getPassword(),regex, msg);
	}
	
	if(ur.getPassword()!="" && ur.getConfirmpassword()!="")
	{
		if(ur.getPassword()!=null && ur.getConfirmpassword()!=null)
		{
		if(!ur.getPassword().equals(ur.getConfirmpassword()))
		{
			passwordcompare=true;
			error=validation.getError();			
			List<String> passworderrorList = error.get("passwordnotmatched");
			
			if(passworderrorList == null ) {                 
				passworderrorList = new ArrayList<String>();
			}
			passworderrorList.add("Password and Confirm Password not matched");
			System.out.println(passworderrorList+"sssssssss");
		    error.put("passwordnotmatched", passworderrorList);
			//error.put("passwordnotmatched", passworderrorList);
		}
	}
	}
	if(!passwordcompare)
	{
		error=validation.getError();
	}
	if(!error.isEmpty())
	{
		status = "FALSE";
	}
			
	response.setResponse(status);
	response.setDataMapList(error);
	
	
	return response;
}

}