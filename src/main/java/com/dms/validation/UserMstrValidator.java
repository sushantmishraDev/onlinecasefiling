package com.dms.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.ActionResponse;
import com.dms.model.Lookup;
import com.dms.model.User;
import com.dms.service.LookupService;
import com.dms.service.UserService;

@Component
public class UserMstrValidator {
	
	@Autowired
	UserService urService;
	
	@Autowired
	LookupService lookupservice;
	
public ActionResponse doValidation(User ur){
		
		ActionResponse response = new ActionResponse();
		Validator validation=new Validator();
		

		List<String> errorList = new ArrayList<String>();
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";
		
		validation.isRequired("um_fullname",ur.getUm_fullname());
		validation.isRequired("username",ur.getUsername());
		
		if(ur.getUm_id()==null)
		{
			validation.isRequired("password",ur.getPassword());
			validation.isRequired("confirmpassword",ur.getConfirmpassword());
			//validation.isRequired("role_id",ur.getUserroles().getRole_id());
			
			/*if(ur.getRole_id()!=null)
			{
				if(ur.getRole_id().equals("2") || ur.getRole_id().equals("3"))
				{
					if(ur.getUm_vendor_id()!=null)
						validation.isRequired("um_vendor_id",ur.getUm_vendor_id());
				}  
			}*/
			error=validation.getError();
			
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
					List<String> passworderrorList = error.get("passwordnotmatched");
					if(passworderrorList == null ) {
						passworderrorList = new ArrayList<String>();
					}
					passworderrorList.add("Password and Confirm Password not matched");
					error.put("passwordnotmatched", passworderrorList);
				}
			}
		}
			
		}
		
		if(ur.getUm_id()!=null)
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
