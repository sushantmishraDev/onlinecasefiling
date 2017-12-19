package com.dms.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.ActionResponse;
import com.dms.model.Folder;
import com.dms.model.Permission;
import com.dms.model.Repository;
import com.dms.service.FolderService;
import com.dms.service.PermissionService;

@Component
public class PermissionValidator {
	
	@Autowired 
	private PermissionService permissionService;
	
	@Autowired 
	private FolderService folderService;
	
	@SuppressWarnings("deprecation")
	public ActionResponse doValidation(Permission p){
		
		
		ActionResponse response = new ActionResponse();
		Validator validation=new Validator();
		
		List<String> errorList = new ArrayList<String>();
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";
		
				
		error=validation.getError();
		errorList =  error.get("name");
		if(errorList == null ) {
			errorList = new ArrayList<String>();
		}
			//check if repository has permission
		if(p.getType()==2){
			Folder f=folderService.getFolderById(p.getValue());
			Permission repperm=permissionService.checkPermissionexist(f.getRep_id(), p.getUserId(),1);
			if(repperm.getId()==null)
			{
				List<String> reperrorList = error.get("rep_id");
				if(reperrorList == null ) {
					reperrorList = new ArrayList<String>();
				}
				reperrorList.add("Parent Repository is not assigned");
				error.put("rep_id", reperrorList);
			}
			//check if parent folder has permission
			if(f.getParent_id()!=null)
			{
				Permission parentfolderperm=permissionService.checkPermissionexist(f.getParent_id(), p.getUserId(), p.getType());
				if(parentfolderperm.getId()==null)
				{
					List<String> reperrorList = error.get("rep_id");
					if(reperrorList == null ) {
						reperrorList = new ArrayList<String>();
					}
					reperrorList.add("Parent folder is not assigned");
					error.put("rep_id", reperrorList);
				}
			}
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
