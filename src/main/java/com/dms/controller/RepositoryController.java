package com.dms.controller;

import java.io.File;
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
import com.dms.model.Repository;
import com.dms.model.User;
import com.dms.service.RepositoryService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.RepositoryValidation;

@Controller
public class RepositoryController {
	
	@Autowired 
	private RepositoryService repositoryService;
	
	@Autowired
	private RepositoryValidation repositoryValidation;
	
	private GlobalFunction globalfunction;
	
	public RepositoryController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/repository/getrepositories", method = RequestMethod.GET)
	public @ResponseBody String getRepositories() {
		String jsonData="";
	    List<Repository> repositories=repositoryService.getAll();
	    jsonData=globalfunction.convert_to_json(repositories);
	    return jsonData;
	}
	
	@RequestMapping(value = "/repository/manage")
	public String manage(Model model) {
		
		 
		return "/repository/manage";
	}
	
	@RequestMapping(value = "/repository/create",method = RequestMethod.POST)
    public @ResponseBody String create(@RequestBody Repository repository,HttpSession session) {    	
    	String jsonData="";
    	ActionResponse<Repository> response = new ActionResponse();
    	User user=new User();
    	user=(User) session.getAttribute("USER");
		response = repositoryValidation.doValidation(repository);
		 if(response.getResponse()=="TRUE"){
			 globalfunction.createfolder(repository.getBasepath()+File.separator+repository.getName());
			 repository.setCreated_by(user.getUm_id());
			 repository.setCreated(new Date());
			 repository=repositoryService.save(repository);
			 response.setData(repository);
		 }
			 jsonData = globalfunction.convert_to_json(response);
		 
		return jsonData; 
    }
	
	@RequestMapping(value = "/repository/update",method = RequestMethod.POST)
    public @ResponseBody String update(@RequestBody Repository repository,HttpSession session) {    	
    	String jsonData="";
    	ActionResponse<Repository> response = new ActionResponse();
		response = repositoryValidation.doValidation(repository);
		User user=new User();
    	user=(User) session.getAttribute("USER");
    	
		 if(response.getResponse()=="TRUE"){
			// globalfunction.createfolder(repository.getBasepath()+File.separator+repository.getName());
			repository.setUpdated_by(user.getUm_id());
			repository.setUpdated(new Date());
			
			Repository oldrep=repositoryService.getRepository(repository.getId());
			String basePath=repository.getBasepath()+File.separator+oldrep.getName();
			String newbasePath=repository.getBasepath()+File.separator+repository.getName();
			File repDir=new File(basePath);
			File newrepDir=new File(newbasePath);
			
			if(repDir.exists()){
				repDir.renameTo(newrepDir);
			}
			repository=repositoryService.save(repository);
			response.setData(repository);
		 }
		 	jsonData = globalfunction.convert_to_json(response);
		 
		return jsonData; 
    }

}
