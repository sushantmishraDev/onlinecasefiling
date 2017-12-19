package com.dms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.Document;
import com.dms.model.Folder;
import com.dms.model.FolderCaseTypeMapping;
import com.dms.model.Lookup;
import com.dms.model.Permission;
import com.dms.model.Repository;
import com.dms.model.Tree;
import com.dms.model.User;
import com.dms.service.FolderService;
import com.dms.service.LookupService;
import com.dms.service.PermissionService;
import com.dms.service.RepositoryService;
import com.dms.service.UserRoleService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.FolderValidation;

@Controller
public class FolderController {
	@Autowired 
	private FolderService folderService;
	
	@Autowired 
	private RepositoryService repositoryService;
	
	@Autowired 
	private PermissionService permissionService;
	
	@Autowired 
	private UserRoleService urService;
	
	@Autowired 
	private LookupService lookupService;
	
	@Autowired
	private FolderValidation folderValidation;
	
	private GlobalFunction globalfunction;
	
	public FolderController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/folder/getallfolders", method = RequestMethod.GET)
	public @ResponseBody String getFolders() {
		String jsonData="";
	    List<Folder> folders=folderService.getAll();
	    jsonData=globalfunction.convert_to_json(folders);
	    return jsonData;
	}
	@RequestMapping(value = "/folder/getfoldersbysize", method = RequestMethod.GET)
	public @ResponseBody String getFolderswithsize() {
		String jsonData="";
	    List<Folder> tempfolders=folderService.getAllByCaseType();
//	    List<Document> documents=folderService.getDocumentCountByFolder();
//	    System.out.println("document data="+globalfunction.convert_to_json(documents));
	    List<Folder> newfolders=new ArrayList<Folder>(); 
	    Repository repository=repositoryService.getActiveRepository();
		String repBasepath=repository.getBasepath()+File.separator+repository.getName();

	    for(Folder f:tempfolders)
	    {
	    	String folderPath="";
			if(f.getParent_id()!=null){
				Long parent_id=f.getParent_id();
			    while(parent_id !=null){
			    	Folder fd=folderService.getFolderById(parent_id);
			    	folderPath = fd.getFolder_name()+File.separator+folderPath;
				    parent_id   = fd.getParent_id();							
			    } 	
			}
			String basePath=repBasepath+File.separator+folderPath+f.getFolder_name();
			System.out.println("basepath="+basePath);
			File basepahfolder=new File(basePath);
			String sizeondisk="0 GiB";
			if(basepahfolder.exists()){
				long size = FileUtils.sizeOfDirectory(new File(basePath));
				sizeondisk=globalfunction.getFolderSize(size,false);
			}
			Integer count=folderService.getDocumentCountByFolder(f.getId());
			f.setNoofdocs(count);
			f.setSizeondisk(sizeondisk);
			newfolders.add(f);
			
			
	    }
	    jsonData=globalfunction.convert_to_json(newfolders);
	    return jsonData;
	}
	
	@RequestMapping(value = "/folder/getcasetypes", method = RequestMethod.POST)
	public @ResponseBody String getCasetypes(@RequestBody Folder f) {
		String jsonData="";
	    List<Lookup> casetypes=folderService.getCaseTypesWithNoFolder(f.getBenchCodeId());
	    jsonData=globalfunction.convert_to_json(casetypes);
	    return jsonData;
	}

	
	@RequestMapping(value = "/folder/gettree", method = RequestMethod.POST)
	 public @ResponseBody String gettree(@RequestBody Folder f,HttpSession session) { 
		String jsonData="";
		User user = (User) session.getAttribute("USER");
		List<Folder> folders=folderService.getParentfolders(f);
		List<Long> folderids=permissionService.getAssignedFolders(user.getUm_id());
		folders=folderService.getUserwisefolders(f,folderids);
	    
//	    List<Tree> treemodellist=new ArrayList<Tree>();
//	    for(Folder folder:folders){
//	    	Tree tree=new Tree();
//	    	tree.setId(folder.getId());
//	    	tree.setName(folder.getName());
//	    	tree.setParentid(0L);
//	    	treemodellist.add(tree);
//	    }
		
		List<Folder> temp=new ArrayList<Folder>();
	    for(Folder fd:folders){
	    	
	    	List<Folder> level1childs=new ArrayList<Folder>();
		    	
	    	if(!fd.getChildrens().isEmpty())
	    	{
	    		// first level
	    		for(Folder f1:fd.getChildrens()){
	    			
	    			if(!f1.getChildrens().isEmpty()){
	    				if(folderids.contains(f1.getId())){
	    					level1childs.add(f1);
	    					
	    					// level 2 begins
	    						List<Folder> level2childs=new ArrayList<Folder>();
	    						for(Folder f2:f1.getChildrens())
	    						{
	    		    			
		    		    			if(!f2.getChildrens().isEmpty()){
		    		    				if(folderids.contains(f2.getId())){
		    		    					level2childs.add(f2);
		    		    					
		    		    					// level3 begins
		    		    					List<Folder> level3childs=new ArrayList<Folder>();
		    	    						for(Folder f3:f2.getChildrens())
		    	    						{
		    	    		    			
		    		    		    			if(!f3.getChildrens().isEmpty()){
		    		    		    				if(folderids.contains(f3.getId())){
		    		    		    					level3childs.add(f3);
		    		    		    				}
		    		    		    			}else{
		    		    		    				if(folderids.contains(f3.getId())){
		    		    		    					level3childs.add(f3);
		    		    		    				}
		    		    		    			}
		    	    						}
		    	    						f2.setChildrens(level3childs);
		    		    					//level3 ends
		    		    				}
		    		    			}else{
		    		    				if(folderids.contains(f2.getId())){
		    		    					level2childs.add(f2);
		    		    				}
		    		    			}
	    						}
	    						f1.setChildrens(level2childs);
	    					 // level2 ends
	    				}
	    			}else{
	    				if(folderids.contains(f1.getId())){
	    					level1childs.add(f1);
	    				}
	    			}
	    		}			
	    		
		    }
		    	
	    	fd.setChildrens(level1childs);
	    	temp.add(fd);
	    }
	    jsonData=globalfunction.convert_to_json(temp);
	    
//	    Long folder_id=8L;
//	    Folder folder=folderService.getFolderById(folder_id);
//	    String folderpath=folder.getName();
//	    Long parent_id=folder.getId();
//	    while(parent_id !=null){
//	    	Folder fd=folderService.getFolderById(parent_id);
//		    folderpath = fd.getName()+"/"+folderpath;
//		    parent_id   = fd.getParent_id();							
//	    } 
	    String response = "{\"treedata\":" + jsonData+"}";
	    return response;
	}
	
	public String  makeTree(List<Tree> treemodellist) {
		String str="";
			
		return str;
	}
	
	@RequestMapping(value = "/folder/manage")
	public String manage(Model model) {
	
		
		return "/folder/manage";
	}
	
	@RequestMapping(value = "/folder/create",method = RequestMethod.POST)
    public @ResponseBody String create(@RequestBody Folder tempfolder,HttpSession session) {    	
    	String jsonData="";
    	ActionResponse<Repository> response = new ActionResponse();
		response = folderValidation.doValidation(tempfolder);
		User user=new User();
    	user=(User) session.getAttribute("USER");
    	
		 if(response.getResponse()=="TRUE"){
			 	String basePath="";
			 	String folderPath="";
				Repository repository=repositoryService.getActiveRepository();
				String repBasepath=repository.getBasepath()+File.separator+repository.getName();
				if(tempfolder.getParent_id()!=null){
					Long parent_id=tempfolder.getParent_id();
				    while(parent_id !=null){
				    	Folder fd=folderService.getFolderById(parent_id);
				    	folderPath = fd.getFolder_name()+File.separator+folderPath;
					    parent_id   = fd.getParent_id();							
				    } 	
				}
				basePath=repBasepath+File.separator+folderPath+tempfolder.getFolder_name();
				System.out.println("basepath="+basePath);
			 globalfunction.createfolder(basePath);
			 tempfolder.setCreated_by(user.getUm_id());
			 tempfolder.setCreated(new Date());
			 
			 Folder folder=folderService.save(tempfolder);
			 
			 response.setData(folder);
			 
			 // save in folder casetype mapping
			 FolderCaseTypeMapping mapping=new FolderCaseTypeMapping();
			 mapping.setBench_type_id(tempfolder.getBenchCodeId());
			 mapping.setCase_type_id(tempfolder.getCaseTypeId());
			 mapping.setFolder_id(folder.getId());
			 mapping=folderService.saveMapping(mapping);
			 
			 // add permission for system administrator
			 
			List<Long> userids= urService.getSytemAdminUsers();
			for(Long userid:userids){
				Permission p=new Permission();
				p.setStatus(1);
				p.setType(2);
				p.setUserId(userid);
				p.setValue(folder.getId());
				permissionService.save(p);
			}
			
		 }
			 jsonData = globalfunction.convert_to_json(response);
		 
		return jsonData; 
    }
	
    @RequestMapping(value = "/folder/update",method = RequestMethod.POST)
    public @ResponseBody String update(@RequestBody Folder folder,HttpSession session) {
    	System.out.println("ID----"+folder.getId());
    	String jsonData="";
    	User user=new User();
    	user=(User) session.getAttribute("USER");
    	ActionResponse<Repository> response = new ActionResponse();
		response = folderValidation.doValidation(folder);
		 if(response.getResponse()=="TRUE"){
			 String basePath="";
				String folderPath="";
				Repository repository=repositoryService.getRepository(folder.getRep_id());
				String repBasepath=repository.getBasepath()+File.separator+repository.getName();
				if(folder.getParent_id()!=null){
					Long parent_id=folder.getParent_id();
				    while(parent_id !=null){
				    	Folder fd=folderService.getFolderById(parent_id);
				    	folderPath = fd.getFolder_name()+File.separator+folderPath;
					    parent_id   = fd.getParent_id();							
				    } 	
				}
				
				System.out.println("basepath="+basePath);
				Folder oldfolder=folderService.getFolder(folder.getId());
				basePath=repBasepath+File.separator+folderPath+oldfolder.getFolder_name();
				String newbasePath=repBasepath+File.separator+folderPath+folder.getFolder_name();
				File folderDir=new File(basePath);
				File newfolderDir=new File(newbasePath);
				if(folderDir.exists()){
					folderDir.renameTo(newfolderDir);
				}
			 //globalfunction.createfolder(basePath);
			 folder.setRepository(null);
			 folder.setUpdated_by(user.getUm_id());
			 folder.setUpdated(new Date());
			 folderService.save(folder);
//			 folder=folderService.save(folder);
			 response.setData(folder);
		 }
//    	folderService.update(folder);
		 jsonData = globalfunction.convert_to_json(response);
		return jsonData;
    }
  
    
    
    @RequestMapping(value = "/folder/getbenchcodes")
    public @ResponseBody String getallcasetypes(){
    	String jsonData="";
	    List<Lookup> casetypes=lookupService.getAll("BRANCH");
	    jsonData=globalfunction.convert_to_json(casetypes);
	    return jsonData;	
    }
    
    @RequestMapping(value = "/folder/delete/{id}")
    public String delete(@PathVariable("id")Integer id, Model model,HttpServletRequest request) {
    	folderService.delete(id);
    	 return "/folder/manage";
    }

}
