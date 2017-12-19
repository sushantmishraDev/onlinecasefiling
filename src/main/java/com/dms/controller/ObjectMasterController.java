package com.dms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.Lookup;
import com.dms.model.ObjectMaster;
import com.dms.model.ObjectTree;
import com.dms.model.RoleObject;
import com.dms.model.User;
import com.dms.service.LookupService;
import com.dms.service.ObjectMasterService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.ObjectMstrValidator;

@Controller
public class ObjectMasterController {
	
	@Autowired
	LookupService lookupService;
	
	@Autowired
	private ObjectMstrValidator objvalidation;

	public ObjectMstrValidator getObjvalidation() {
		return objvalidation;
	}

	public void setObjvalidation(ObjectMstrValidator objvalidation) {
		this.objvalidation = objvalidation;
	}

	@Autowired
	ObjectMasterService objectMasterService;

	GlobalFunction globalfunction;

	public ObjectMasterController() {
		globalfunction = new GlobalFunction();
	}

	public static final String Object_Master_View = "objectmaster/objectMaster";

	@RequestMapping(value = "/objectMaster/index", method = RequestMethod.GET)
	public String getObjectMaster() {
		return Object_Master_View;
	}

	@RequestMapping(value = "/objectMaster/getDataByRoleID", method = RequestMethod.GET)
	public @ResponseBody String getDataByRoleID(
			@RequestParam(value = "roleID") Long roleID) {
		String json = null;
		ActionResponse<RoleObject> response = new ActionResponse();
		response.setResponse("FALSE");
		List<RoleObject> roleList = objectMasterService.getDataByRoleID(roleID);

		response.setModelList(roleList);
		if (roleList != null)
			response.setResponse("TRUE");
		json = globalfunction.convert_to_json(roleList);

		return json;
	}

	@RequestMapping(value = "/objectMaster/getRole", method = RequestMethod.GET)
	public @ResponseBody String getRole() {
		String jsonData = null;
 
		List<ObjectTree> treeList = objectMasterService.getRoleTree();
		/*
		 * List<Tree> treeList=new ArrayList<Tree>();
		 * 
		 * for(ObjectMaster folder:folders){ Tree tree=new Tree();
		 * tree.setId(folder.getOm_id());
		 * tree.setName(folder.getOm_object_name()); tree.setParentid(0L);
		 * treeList.add(tree); }
		 */

		jsonData = globalfunction.convert_to_json(treeList);
		return jsonData;
	}

	@RequestMapping(value = "/objectMaster/getRoleList", method = RequestMethod.GET)
	public @ResponseBody String getCaseTypeList(HttpSession session) {
		String json = null;
		ActionResponse<Lookup> response = new ActionResponse();
		response.setResponse("FALSE");
		List<Lookup> list = lookupService.getAllCaseType("DMS_ROLE");
		System.out.println("role list in contoller" + list.size());
		response.setModelList(list);
		if (list != null)
			response.setResponse("TRUE");
		json = globalfunction.convert_to_json(list);
		return json;

	}

	@RequestMapping(value = "/objectMaster/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody ObjectMaster objectMaster,HttpSession session) {
		ObjectMaster objmaster = new ObjectMaster();
	ActionResponse<ObjectMaster> response =objvalidation.doValidation(objectMaster);
	//	ActionResponse<ObjectMaster> response = new ActionResponse();
		User u = (User) session.getAttribute("USER");

		String jsonData = null;
		if (response.getResponse() == "TRUE") {

			objectMaster.setOm_cr_by(u.getUm_id());
			objectMaster.setOm_cr_date(new Date());

			if (objectMaster != null) {
				if(objectMaster.getOm_parent_id()==null)
				{
					objectMaster.setOm_parent_id(0L);
				}
				objectMaster = objectMasterService.save(objectMaster);
			}

			if (objectMaster != null)
				jsonData = globalfunction.convert_to_json(objectMaster);
				} 
	else {
			jsonData = globalfunction.convert_to_json(response);
		 }

		return jsonData;

	}

}
