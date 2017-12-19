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
import com.dms.model.RoleObject;
import com.dms.model.Tree;
import com.dms.model.User;
import com.dms.service.LookupService;
import com.dms.service.RoleMappingService;
import com.dms.utility.GlobalFunction;

@Controller
public class RoleMappingController {

	@Autowired
	RoleMappingService mappingService;
	
	@Autowired
	LookupService lookupService;

	GlobalFunction globalfunction;

	public RoleMappingController() {
		globalfunction = new GlobalFunction();
	}

	public static final String Role_Mapping_View = "rolemapping/roleMapping";
	
	@RequestMapping(value = "/roleMapping/index", method = RequestMethod.GET)
	public String getRoleIndex() {
		return Role_Mapping_View;
	}

	
	@RequestMapping(value = "/roleMapping/getDataByRoleID", method = RequestMethod.GET)
	public @ResponseBody String getDataByRoleID(
			@RequestParam(value = "roleID") Long roleID) {
		String json = null;
		ActionResponse<Long> response = new ActionResponse();
		response.setResponse("FALSE");
		List<Long> objectIdList = mappingService.getDataByRoleID(roleID);

		response.setModelList(objectIdList);
		if (objectIdList != null)
			response.setResponse("TRUE");
		json = globalfunction.convert_to_json(response);

		return json;
	}

	@RequestMapping(value = "/roleMapping/getRole", method = RequestMethod.GET)
	public @ResponseBody String getRole() {
		String jsonData = null;

		List<Tree> treeList = mappingService.getRoleTree();
		jsonData = globalfunction.convert_to_json(treeList);
		return jsonData;
	}

	@RequestMapping(value = "/roleMapping/getRoleList", method = RequestMethod.GET)
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

	@RequestMapping(value = "/roleMapping/create", method = RequestMethod.POST)
	public @ResponseBody String create(@RequestBody List<RoleObject> roleObject, HttpSession session) {
		RoleObject robject = new RoleObject();
		ActionResponse<RoleObject> response = new ActionResponse();
		User u = (User) session.getAttribute("USER");
		Long role_id = roleObject.get(0).getRo_role_id();
		// update service
		// List<RoleObject> roleObj=mappingService.updateRecStatus(role_id);

		System.out.println("Called RoleObject/create");
		String jsonData = null;

		for (RoleObject rob : roleObject) {
			RoleObject roleobject = mappingService.getByRoleAndObject(role_id,rob.getRo_om_mid());
			if (roleobject.getRo_id() == null) {
				if (rob.getCheckbox() == true) {
					rob.setRo_rec_status(1);
					rob.setRo_cr_by(u.getUm_id());
					rob.setRo_cr_date(new Date());
					mappingService.save(rob);
				}
			} else {
				if (rob.getCheckbox() == true) {
					roleobject.setRo_mod_by(u.getUm_id());
					roleobject.setRo_mod_date(new Date());
					roleobject.setRo_rec_status(1);
					mappingService.save(roleobject);
				} else if (rob.getCheckbox() == false) {
					roleobject.setRo_mod_by(u.getUm_id());
					roleobject.setRo_mod_date(new Date());
					roleobject.setRo_rec_status(2);
					mappingService.save(roleobject);

				}
			}
		}
		return "";
	}
}
