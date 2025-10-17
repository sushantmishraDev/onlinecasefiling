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
import com.dms.model.Repository;
import com.dms.service.FolderService;
import com.dms.service.RepositoryService;

@Component
public class FolderValidation {
	
	@Autowired 
	private FolderService folderService;
	
	@Autowired 
	private RepositoryService repositoryService;
	
	@SuppressWarnings("deprecation")
	public ActionResponse doValidation(Folder f){
		
		ActionResponse response = new ActionResponse();
		Validator validation=new Validator();
		
		List<String> errorList = new ArrayList<String>();
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";
		
				
		error=validation.getError();
		
		String basePath="";
		String folderPath="";
		
		Boolean folderExist=false;
		Boolean basePathExist=true;
		Boolean repbasepatherror=false;
		
		Folder folderexistmodel=folderService.checkExist(f);
		if(f.getId()==null)
		{
			if(folderexistmodel.getId()==null)
			{
				Repository repository=repositoryService.getRepository(f.getRep_id());
				String repBasepath=repository.getBasepath()+File.separator+repository.getName();
				if(f.getParent_id()!=null){
					Long parent_id=f.getParent_id();
				    while(parent_id !=null){
				    	Folder fd=folderService.getFolderById(parent_id);
				    	folderPath = fd.getFolder_name()+File.separator+folderPath;
					    parent_id   = fd.getParent_id();							
				    } 	
				}
				basePath=repBasepath+File.separator+folderPath+f.getFolder_name();
				System.out.println("basepath1="+basePath);
				File repbasetpath=new File(repBasepath+File.separator+folderPath);
				if(!repbasetpath.exists()){
					repbasepatherror=true;
				}
					File folderDir = new File(basePath);
					if (folderDir.exists()) {
						folderExist=true;
					}
			}else{
				folderExist=true;
			}
		}else{
			Folder oldfolder=folderService.getFolder(f.getId());
			if(folderexistmodel.getId()==null){
				Repository repository=repositoryService.getRepository(f.getRep_id());
				String repBasepath=repository.getBasepath()+File.separator+repository.getName();
				if(f.getParent_id()!=null){
					Long parent_id=f.getParent_id();
				    while(parent_id !=null){
				    	Folder fd=folderService.getFolderById(parent_id);
				    	folderPath = fd.getFolder_name()+File.separator+folderPath;
					    parent_id   = fd.getParent_id();							
				    } 	
				}
				
				basePath=repBasepath+File.separator+folderPath+oldfolder.getFolder_name();
				String newbasePath=repBasepath+File.separator+folderPath+f.getFolder_name();
				File folderDir=new File(basePath);
				File newfolderDir=new File(newbasePath);
				if(!folderDir.exists()){
					basePathExist=false;
					//folderDir.renameTo(newfolderDir);
				}
			}else{
				folderExist=true;	
			}
		}
		
		if(folderExist)
		{
			List<String> foldererrorList = error.get("name");
			if(foldererrorList == null ) {
				foldererrorList = new ArrayList<String>();
			}
			foldererrorList.add("Folder with "+f.getFolder_name()+" already exist.");
			error.put("name", foldererrorList);
		}
		if(!basePathExist)
		{
			List<String> foldererrorList = error.get("basePathExist");
			if(foldererrorList == null ) {
				foldererrorList = new ArrayList<String>();
			}
			foldererrorList.add("Path doesn't exist");
			error.put("basePathExist", foldererrorList);
		}
		if(repbasepatherror)
		{
			List<String> foldererrorList = error.get("basePathExist");
			if(foldererrorList == null ) {
				foldererrorList = new ArrayList<String>();
			}
			foldererrorList.add("Base Folder path doesn't exist");
			error.put("basePathExist", foldererrorList);
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
