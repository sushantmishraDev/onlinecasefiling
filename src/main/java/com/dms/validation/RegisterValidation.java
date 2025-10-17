package com.dms.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.model.Advocate;
import com.dms.model.ActionResponse;
import com.dms.model.AdvocateEfiling;
import com.dms.model.Register;
import com.dms.model.User;
import com.dms.service.AdvocateService;
import com.dms.service.UserService;

@Component
public class RegisterValidation {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private AdvocateService advocateService;
	
	@SuppressWarnings("deprecation")
	public ActionResponse doValidation(Register r){
		
		ActionResponse response = new ActionResponse();
		Validator validation=new Validator();
		
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";
		error=validation.getError();
		
		validation.isRequired("Username",r.getUsername());
		validation.isRequired("Name",r.getName());
		validation.isRequired("Email",r.getEmail());
		validation.isRequired("Mobile",r.getMobile());
		validation.isRequired("Gender",r.getGender());
		validation.isRequired("Password",r.getPassword());
		validation.isRequired("Confirm Password",r.getConfirmPassword());
		if(r.getUsername()!=""){
			validation.maxLength("Username", r.getUsername(), 12);
		}
		error=validation.getError();
		if(r.getPassword()!="" && r.getConfirmPassword()!="")
		{
			if(r.getPassword()!=null && r.getConfirmPassword()!=null)
			{
				if(!r.getPassword().equals(r.getConfirmPassword()))
				{
					List<String> passworderrorList = error.get("Confirm Password");
					if(passworderrorList == null ) {
						passworderrorList = new ArrayList<String>();
					}
					passworderrorList.add("Password and Confirm Password not matched");
					error.put("Confirm Password", passworderrorList);
				}
			}
		}
		if(r.getUsername()!=null)
		{
			User u=userService.getUserByUsername(r.getUsername());
			if(u.getUm_id()!=null){
				List<String> usernameErrorList = error.get("Username");
				if(usernameErrorList == null ) {
					usernameErrorList = new ArrayList<String>();
				}
				usernameErrorList.add("Username already exist");
				error.put("Username", usernameErrorList);
			}
		}
		
		if(r.getMobile()!=null)
		{
			List<String> mobileErrorList = error.get("Mobile");
			if(r.getMobile().length()<10) {
				if(mobileErrorList == null ) {
					mobileErrorList = new ArrayList<String>();
				}
				mobileErrorList.add("Mobile No not valid");
				error.put("Mobile", mobileErrorList);
			}else {
			User u=userService.getUserByMobile(r.getMobile());
			if(u.getUm_id()!=null){
				
				if(mobileErrorList == null ) {
					mobileErrorList = new ArrayList<String>();
				}
				mobileErrorList.add("Mobile No already exist");
				error.put("Mobile", mobileErrorList);
			}
			AdvocateEfiling adv=advocateService.getByMobile(r.getMobile());
			if(adv.getAdv_id()!=null && r.getType().equals("inperson")) {
				if(mobileErrorList == null ) {
					mobileErrorList = new ArrayList<String>();
				}
				mobileErrorList.add("This Mobile No Is Already Registerd With AOR, Please Register with AOR");
				error.put("Mobile", mobileErrorList);
			}
			}
		}
		
		if(r.getAdhar()!=null)
		{
			List<String> adharErrorList = error.get("Adhar");
			
			if(String.valueOf(r.getAdhar()).length() <12) {
				if(adharErrorList == null ) {
					adharErrorList = new ArrayList<String>();
				}
				adharErrorList.add("Adhar No not valid");
				error.put("Adhar", adharErrorList);
			}
			
		}
		
		
		if(!error.isEmpty())
		{
			status = "FALSE";
		}
		
		
		response.setResponse(status);
		response.setDataMapLists(error);
		return response;
	}
}
