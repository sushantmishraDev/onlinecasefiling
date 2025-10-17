package com.dms.controller;

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
import com.dms.model.Judge;
import com.dms.model.Lookup;
import com.dms.model.PoliceStation;
import com.dms.model.PoliceStation2024;
import com.dms.model.PoliceStationNew;
import com.dms.model.User;
import com.dms.service.LookupService;
import com.dms.service.PoliceStationService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.PoliceStationMstrValidator;

@Controller
public class PoliceStationController {
	public static final String police_station_master_view = "policestationmaster/policeStationMaster";
	
	@Autowired
	private LookupService lookUpService;
	
	@Autowired
	PoliceStationService policeStationService;

	@Autowired
	private PoliceStationMstrValidator policeStationMstrValidator ;

	public void setLookUpService(LookupService lookUpService) {
		this.lookUpService = lookUpService;
	}

	

	public void setPoliceStationMstrValidator(
			PoliceStationMstrValidator policeStationMstrValidator) {
		this.policeStationMstrValidator = policeStationMstrValidator;
	}



	private GlobalFunction commonMethods;

	public PoliceStationController() {
		commonMethods = new GlobalFunction();
	}

	@RequestMapping(value = "police_station_master", method = RequestMethod.GET)
	public String index(Model model) {

		return police_station_master_view;
	}

	@RequestMapping(value="police_station_master/getpoliceStationDetails",method=RequestMethod.GET)
	public @ResponseBody String getPoliceStationData(Model model)
	{
		String jsonData=null;
		List<PoliceStation> getDetails=policeStationService.getAll();
		if(getDetails!=null)
		{
			jsonData=commonMethods.convert_to_json(getDetails);
		}
		System.out.println(jsonData);
		return jsonData;

	}
	
	
	@RequestMapping(value="police_station_master/getpoliceStationNewDetails",method=RequestMethod.GET)
	public @ResponseBody String getPoliceStationNewData(Model model)
	{
		String jsonData=null;
		List<PoliceStationNew> getDetails=policeStationService.getAllNew();
		if(getDetails!=null)
		{
			jsonData=commonMethods.convert_to_json(getDetails);
		}
		System.out.println(jsonData);
		return jsonData;

	}
	
	@RequestMapping(value="police_station_master/getpoliceStationNewDetails2024",method=RequestMethod.GET)
	public @ResponseBody String getPoliceStation2024(Model model)
	{
		String jsonData=null;
		List<PoliceStation2024> getDetails=policeStationService.getAllNew2024();
		if(getDetails!=null)
		{
			jsonData=commonMethods.convert_to_json(getDetails);
		}
		System.out.println(jsonData);
		return jsonData;

	}
	
	// [END]To JSON GET Method List of bundle_Alloction Model  

	@RequestMapping(value="police_station_master/getpoliceStationLocationList",method=RequestMethod.GET)
	public @ResponseBody String getpsLocation(Model model)
	{
		String jsonData=null;
		List<Lookup> getpsLocation=lookUpService.getAll("DISTRICT");
		
		if(getpsLocation!=null)
		{
			jsonData=commonMethods.convert_to_json(getpsLocation);
		}
		System.out.println(jsonData);
		return jsonData;

	}
	
	@RequestMapping(value = "police_station_master/save", method = RequestMethod.POST)	
	public @ResponseBody String save(@RequestBody PoliceStation policeStation,HttpSession session)
	{	
		ActionResponse<Judge> response = policeStationMstrValidator.doValidation(policeStation);
		System.out.println("== USER SAVE ==");
		String jsonData = null;		
		if(response.getResponse() == "FALSE")
		{
			jsonData = commonMethods.convert_to_json(response);	
		}
		else
		{
			User user = (User) session.getAttribute("USER");
			if(policeStation.getCr_by()==null){
				policeStation.setCr_by(user.getUm_id());
				policeStation.setCr_date(new Date());}
				else
				{
					policeStation.setMod_by(user.getUm_id());
					policeStation.setMod_date(new Date());
				}
			policeStationService.save(policeStation);
		}
	
	//	jsonData=commonMethods.convert_to_json(judgeService.getj(inwardBundle.getIb_id()));
		jsonData = commonMethods.convert_to_json(response);

	return jsonData;
	}


}
