package com.dms.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dms.model.ActionResponse;
import com.dms.model.Repository;
import com.dms.service.RepositoryService;

@Component
public class RepositoryValidation {
	
	@Autowired 
	private RepositoryService repositoryService;
	
	@SuppressWarnings("deprecation")
	public ActionResponse doValidation(Repository r){
		
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
		
		File baseDir = new File(r.getBasepath());
		if (!baseDir.exists()) {
			List<String> basepatherrorList = error.get("basepath");
			if(basepatherrorList == null ) {
				basepatherrorList = new ArrayList<String>();
			}
			basepatherrorList.add("Basepath is incorrect.");
			error.put("basepath", basepatherrorList);
		}
		if(r.getName()!=null)
		{
			if(r.getId()==null){
				File folderDir = new File(r.getBasepath() + File.separator + r.getName());
				if (folderDir.exists()) {
					List<String> foldererrorList = error.get("name");
					if(foldererrorList == null ) {
						foldererrorList = new ArrayList<String>();
					}
					foldererrorList.add("Repository with "+r.getName()+" already exist.");
					error.put("name", foldererrorList);
				}
			}else{
				Repository repository=repositoryService.checkRepositoryExist(r);
				if(repository.getId()!=null){
					List<String> foldererrorList = error.get("name");
					if(foldererrorList == null ) {
						foldererrorList = new ArrayList<String>();
					}
					foldererrorList.add("Repository with "+r.getName()+" already exist.");
					error.put("name", foldererrorList);
				}
			}
		}else
		{
			errorList = error.get("name");
			if(errorList == null ) {
				errorList = new ArrayList<String>();
			}
			errorList.add("Repository name is required.");
			error.put("name", errorList);
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
