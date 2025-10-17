package com.dms.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.Lookup;
import com.dms.model.MetaField;
import com.dms.model.MetaTemplate;
import com.dms.model.User;
import com.dms.service.MetaFieldService;
import com.dms.service.MetaTemplateService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.MetaFieldvalidator;
import com.dms.validation.MetaTemplateValidator;


@Controller
public class MetaTemplateController {
	
	@Autowired 
	private MetaTemplateService metaTemplateService;
	
	@Autowired 
	private MetaFieldService metaFieldService;
	
	private GlobalFunction globalfunction;
	
	@Autowired
	MetaFieldvalidator  metaFieldvalidator;
	
	@Autowired
	MetaTemplateValidator metaTemplateValidator;
	
	public MetaTemplateController() {
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/metatemplate/getall", method = RequestMethod.GET)
	public @ResponseBody String getAll() {
		String jsonData="";
		ActionResponse<MetaTemplate> response= new ActionResponse<MetaTemplate>();
	    List<MetaTemplate> metaTemplateList = metaTemplateService.getAll();	    
	    response.setResponse("TRUE");
	    response.setModelList(metaTemplateList);
	    jsonData=globalfunction.convert_to_json(response);
	    return jsonData;
	}
	
	
	
	@RequestMapping(value = "/metatemplate/getallmetafield", method = RequestMethod.GET)
	public @ResponseBody String getAllMetaField() {
		String jsonData="";
		ActionResponse<MetaField> response= new ActionResponse<MetaField>();
	    List<MetaField> metaFiledList = metaFieldService.getAllMetaField();	    
	    response.setResponse("TRUE");
	    response.setModelList(metaFiledList);
	    jsonData=globalfunction.convert_to_json(response);
	    return jsonData;
	}
	
	@RequestMapping(value = "/metatemplate/getmetafields", method = RequestMethod.GET)
	public @ResponseBody String getmetafields() {
		String jsonData="";
		ActionResponse<MetaField> response= new ActionResponse<MetaField>();
		List<MetaField> metafieldList =metaFieldService.getMetaFields(1L);	    
	    response.setResponse("TRUE");
	    response.setModelList(metafieldList);
	    jsonData=globalfunction.convert_to_json(response);
	    return jsonData;
	}
	
	@RequestMapping(value = "/metatemplate/manage")
	public String manage(Model model) {
		return "/metatemplate/manage";
	}
	
//consumes = {"application/xml", "application/json"}
	@RequestMapping(value ="/metatemplate/save", method = RequestMethod.POST)
	public @ResponseBody String Save(@RequestBody MetaTemplate metaTemplate,HttpSession session) {
		String jsonData="";
		ActionResponse response = metaTemplateValidator.doValidation(metaTemplate);
		//ActionResponse<MetaTemplate> response= new ActionResponse<MetaTemplate>();
		if(response.getResponse() == "FALSE"){
			jsonData = globalfunction.convert_to_json(response);
		}
		else
		{	User u = (User) session.getAttribute("USER");
		//metaField.setMf_cr_by(u.getUm_id());
		metaTemplate.setMt_rec_deleted(0);
		metaTemplate = metaTemplateService.save(metaTemplate);
		if(metaTemplate != null){
			jsonData = globalfunction.convert_to_json(metaTemplate);
		}
		}
	    return jsonData;
	}
	
	@RequestMapping(value ="/metatemplate/savemetafield", method = RequestMethod.POST)
	public @ResponseBody String SavemetaFieldMaster(@RequestBody MetaField metaField,HttpSession session) {
		String jsonData="";
		ActionResponse response = metaFieldvalidator.doValidation(metaField);
		//ActionResponse<MetaField> response= new ActionResponse<MetaField>();
		
		if(response.getResponse() == "FALSE"){
			jsonData = globalfunction.convert_to_json(response);
		}
		else
		{	User u = (User) session.getAttribute("USER");
		//metaField.setMf_cr_by(u.getUm_id());
		metaField.setMf_add_multiple(0);
	//	metaField.setMf_rec_deleted(0);
		metaField.setMf_rec_status(1);
		metaField = metaFieldService.save(metaField);
		if(metaField != null){
			jsonData = globalfunction.convert_to_json(metaField);
		}
		}
		
//	    response.setResponse("TRUE");
//	    response.setModelData(metaField);
//	    jsonData=globalfunction.convert_to_json(response);
	    return jsonData;
	}
	
	
	@RequestMapping(value = "/metafield/delete", method = RequestMethod.POST)	
	public @ResponseBody String deletemappingentity(@RequestBody MetaField m) {	
	//	Long id=m.getMf_id();
		//metaFieldService.delete(id);			
		String jsonData = null;
		if(m != null){
			jsonData = 	"[{\"status \":\"true\",\"msg\":\"Deleted\"}]";
		}
		return jsonData;		
	}
	
	@RequestMapping(value = "/metatemplate/dataentry")
	public String dataentry(Model model) {
		return "/metatemplate/dataentry";
	}
	
	@RequestMapping(value = "/metatemplate/masterform")
	public String masterform(Model model) {
		return "/metatemplate/_master_form";
	}
	

	@RequestMapping(value="/metatemplate/addfield/{mt_id}",method=RequestMethod.GET)
	public String addfield(Model model,@ModelAttribute MetaTemplate metaTemplate)
	{
		System.out.println("meta tamplate id"+metaTemplate.getMt_id());
		model.addAttribute("mt_id", metaTemplate.getMt_id());
		
		return "/metatemplate/_manage_fields";
	}
	

	@RequestMapping(value="/metatemplate/getallmetafield/{mt_id}",method=RequestMethod.GET)
	public @ResponseBody String getallmetafield(Model model,@ModelAttribute MetaTemplate metaTemplate)
	{
		String jsonData="";
		ActionResponse<MetaField> response= new ActionResponse<MetaField>();
		List<MetaField> metafieldList =metaFieldService.getMetaFields(metaTemplate.getMt_id());	    
	    response.setResponse("TRUE");
	    response.setModelList(metafieldList);
	    jsonData=globalfunction.convert_to_json(response);
	    return jsonData;
	}
	 
	@RequestMapping(value = "/metatemplate/getbyfk", method = RequestMethod.GET)
	public @ResponseBody String getbyfk(@RequestParam(value ="id") Long id) {
		String jsonData="";
		ActionResponse response= new ActionResponse();
		List tablefk = metaFieldService.getByfk(id);
		response.setResponse("TRUE");
		response.setModelList(tablefk);
		jsonData=globalfunction.convert_to_json(response);
		// System.out.println("-------table list-----\n"+tableList);
	    return jsonData;
	}
	
	
	
	

	@RequestMapping(value = "/metatemplate/getalltable", method = RequestMethod.GET)
	public @ResponseBody String getAlltable() {
		String jsonData="";
		ActionResponse response= new ActionResponse();
		
		 List tableList = metaFieldService.getAlltable();	
		 response.setResponse("TRUE");
		 response.setModelList(tableList);
		 jsonData=globalfunction.convert_to_json(response);
		// System.out.println("-------table list-----\n"+tableList);
	    return jsonData;
	}
	
	 
	@RequestMapping(value = "/metatemplate/getcolumns", method = RequestMethod.GET)
	public @ResponseBody String getcolumns(@RequestParam(value ="table") String table) {
		String jsonData="";
		ActionResponse response= new ActionResponse();
		
		 @SuppressWarnings("rawtypes")
		List tableList = metaFieldService.getColumn(table);
		 response.setResponse("TRUE");
		 response.setModelList(tableList);
		 jsonData=globalfunction.convert_to_json(response);
		// System.out.println("-------table list-----\n"+tableList);
	    return jsonData;
	}
	
	
	
	
	@RequestMapping(value = "/metatemplate/gettablepk", method = RequestMethod.GET)
	public @ResponseBody String gettablepk(@RequestParam(value ="table") String table) {
		String jsonData="";
		ActionResponse response= new ActionResponse();
		String tableList = metaFieldService.gettablepk(table);
		 response.setResponse("TRUE");
		 response.setModelData(tableList);
		 jsonData=globalfunction.convert_to_json(response);
		// System.out.println("-------table list-----\n"+tableList);
	    return jsonData;
	}
	
	
	
	
	
	
	
	
	
}



	

