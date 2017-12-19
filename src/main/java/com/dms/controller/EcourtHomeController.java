package com.dms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActDetails;
import com.dms.model.ActMaster;
import com.dms.model.ActionResponse;
import com.dms.model.CaseCheckListMapping;
import com.dms.model.CaseType;
import com.dms.model.Caveat;
import com.dms.model.CaveatOld;
import com.dms.model.CourtFee;
import com.dms.model.DailyActivityReport;
import com.dms.model.District;
import com.dms.model.ImpugnedOrder;
import com.dms.model.Lookup;
import com.dms.model.LowerCourtCaseType;
import com.dms.model.LowerCourtTypes;
import com.dms.model.PetitionerDetails;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.RespondentDetails;
import com.dms.model.State;
import com.dms.model.TrialCourt;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.EcourtAddCaseService;
import com.dms.service.EcourtHomeService;
import com.dms.service.LookupService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/ecourt")
public class EcourtHomeController {

	static int seq = 0;
	private GlobalFunction cm;

	@Autowired
	private EcourtHomeService ecourtHomeService;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private EcourtAddCaseService ecourtAddCaseService;
	
	public EcourtHomeController() {
		// TODO Auto-generated constructor stub
		cm = new GlobalFunction();
	}

	@RequestMapping(value = "/ecourtHome", method = RequestMethod.GET)
	public String ecourtHome() {
		
		return "/ecourt/ecourtHome";

	}

	@RequestMapping(value = "/ecourtHome2", method = RequestMethod.GET)
	public String ecourtHome2() {
		return "/ecourt/ecourtHome2";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {

		return "/ecourt/index";

	}

	@RequestMapping(value = "/addCase", method = RequestMethod.GET)
	public String addCaseDetail() {

		return "/ecourt/addCase";
	}
	
	@RequestMapping(value = "/addCaveat", method = RequestMethod.GET)
	public String addCaveat() {

		return "/ecourt/addCaveat";
	}

	@RequestMapping(value = "/draftForm", method = RequestMethod.GET)
	public String draftForm() {

		return "/ecourt/draftForm";
	}
	
	@RequestMapping(value = "/caveatDraftForm", method = RequestMethod.GET)
	public String caveatDraftForm() {

		return "/ecourt/caveatDraftForm";
	}

	@RequestMapping(value = "/previewList/{id}", method = RequestMethod.GET)
	public String draftView(Model model, @PathVariable Integer id) {

		model.addAttribute("caseId", id);

		return "/ecourt/previewList";
	}
	
	@RequestMapping(value = "/scrutinyView/{id}", method = RequestMethod.GET)
	public String scrutinyView(Model model, @PathVariable Integer id) {

		model.addAttribute("caseId", id);

		return "/ecourt/scrutinyView";
	}

	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public String captcha() {

		return "/ecourt/index";
	}

	@RequestMapping(value = "/getDashboard", method = RequestMethod.GET)
	@ResponseBody
	public String getDashboard(HttpSession session) {
		String result = null;

		ActionResponse<DailyActivityReport> response = new ActionResponse();
		
		DailyActivityReport dar = new DailyActivityReport();
		
		List<DailyActivityReport>  darList= new ArrayList<DailyActivityReport>() ;
		
		User user = (User) session.getAttribute("USER");
		List<UserRole> userrole=user.getUserroles();
		String rolename="";
		for(UserRole ur:userrole){
		 rolename=ur.getLk().getLk_longname();
		}
				 
			/////case file start////
			Lookup draft=lookupService.getLookup("ECOURT_STAGE", "Draft");
			Lookup draftsubmit=lookupService.getLookup("ECOURT_STAGE", "Draft Submit");
			Lookup scrutintydone=lookupService.getLookup("ECOURT_STAGE", "Scrutiny Done");
			Lookup scrutintyreject=lookupService.getLookup("ECOURT_STAGE", "Scrutiny Reject");
		
			dar.setFlag1(1);
			dar.setFlag2(1);
			dar.setFlag3(1);
			dar.setFlag4(1);
			
			dar.setParameter1("Cases");
			dar.setParameter2("Pending Reporting");
			dar.setParameter3("Defective");
			dar.setParameter4("e-Filed Case");
			Integer totalCount=ecourtHomeService.getDraftCount(user.getUm_id());
			//Integer totalCount=ecourtHomeService.getCountByStage(draft.getLk_id(),user.getUm_id());
			Integer draftCount=ecourtHomeService.getCountByStage(draftsubmit.getLk_id(),user.getUm_id());
			Integer scrutinyDoneCount=ecourtHomeService.getCountByStage(scrutintydone.getLk_id(),user.getUm_id());
			Integer scrutinyRejectCount=ecourtHomeService.getCountByStage(scrutintyreject.getLk_id(),user.getUm_id());
					
			dar.setValue1(""+totalCount);
			dar.setValue2(""+draftCount);
			dar.setValue3(""+scrutinyRejectCount);
			dar.setValue4(""+scrutinyDoneCount);
			
			////////case file end//////
			
			/////caveat file start////
			Lookup caveat_draft=lookupService.getLookup("ECOURT_STAGE", "CAVEAT_DRAFT_CREATED");
			Lookup caveat_draftsubmit=lookupService.getLookup("ECOURT_STAGE", "CAVEAT_SUBMITED");
			Lookup caveat_scrutintydone=lookupService.getLookup("ECOURT_STAGE", "CAVEAT_CREATED");
			Lookup caveat_scrutintyreject=lookupService.getLookup("ECOURT_STAGE", "CAVEAT_REJECTED");
			
			dar.setFlag5(1);
			dar.setFlag6(1);
			dar.setFlag7(1);
			dar.setFlag8(1);
			
			dar.setParameter5("Caveats");
			dar.setParameter6("Caveat Pending Reporting");
			dar.setParameter7("Caveat Defective");
			dar.setParameter8("e-Filed Caveat");
			dar.setLink5("ecourt/caveatDraftForm");
			dar.setLink1("ecourt/draftForm");
			dar.setLink9("application/applicationDraftForm");
			//Integer caveat_totalCount=ecourtHomeService.getCountByStageCaveat(caveat_draft.getLk_id(),user.getUm_id());
			Integer caveat_totalCount=ecourtHomeService.getCaveatCount(user.getUm_id());
			Integer caveat_draftCount=ecourtHomeService.getCountByStageCaveat(caveat_draftsubmit.getLk_id(),user.getUm_id());
			Integer caveat_scrutinyDoneCount=ecourtHomeService.getCountByStageCaveat(caveat_scrutintydone.getLk_id(),user.getUm_id());
			Integer caveat_scrutinyRejectCount=ecourtHomeService.getCountByStageCaveat(caveat_scrutintyreject.getLk_id(),user.getUm_id());
					
			dar.setValue5(""+caveat_totalCount);
			dar.setValue6(""+caveat_draftCount);
			dar.setValue7(""+caveat_scrutinyDoneCount);
			dar.setValue8(""+caveat_scrutinyRejectCount);
			////////caveat file end//////
			
			/////application file start////
			Lookup application_draft=lookupService.getLookup("ECOURT_STAGE", "APPLICATION_DRAFT_CREATED");
			Lookup application_draftsubmit=lookupService.getLookup("ECOURT_STAGE", "APPLICATION_SUBMITTED");
			Lookup application_scrutintydone=lookupService.getLookup("ECOURT_STAGE", "APPLICATION_CREATED");
			Lookup application_scrutintyreject=lookupService.getLookup("ECOURT_STAGE", "APPLICATION_REJECTED");
			
			dar.setFlag9(1);
			dar.setFlag10(1);
			dar.setFlag11(1);
			dar.setFlag12(1);
			
			dar.setParameter9("Applications");
			dar.setParameter10("Application Pending Reporting");
			dar.setParameter11("Application Defective");
			dar.setParameter12("e-Filed Application");
			
			//Integer application_totalCount=ecourtHomeService.getCountByStageApplication(application_draft.getLk_id(),user.getUm_id());
			Integer application_totalCount=ecourtHomeService.getApplicationCount(user.getUm_id());
			Integer application_draftCount=ecourtHomeService.getCountByStageApplication(application_draftsubmit.getLk_id(),user.getUm_id());
			Integer application_scrutinyDoneCount=ecourtHomeService.getCountByStageApplication(application_scrutintydone.getLk_id(),user.getUm_id());
			Integer application_scrutinyRejectCount=ecourtHomeService.getCountByStageApplication(application_scrutintyreject.getLk_id(),user.getUm_id());
					
			dar.setValue9(""+application_totalCount);
			dar.setValue10(""+application_draftCount);
			dar.setValue11(""+application_scrutinyDoneCount);
			dar.setValue12(""+application_scrutinyRejectCount);
			////////application file end//////
			
			
		//darList.add(dar);
		
		response.setModelData(dar);
		
		if (dar != null) {
			response.setResponse("TRUE");
			result = cm.convert_to_json(response);
		} else {
			response.setResponse("FALSE");
		}

		
		return result;
	}
	@RequestMapping(value = "/getDraftDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getDraftDetails(HttpSession session) {
		String jsonData = null;
		
		User user=(User) session.getAttribute("USER");

		List<RegisteredCaseDetails> newDraftList = new ArrayList<RegisteredCaseDetails>();

		List<RegisteredCaseDetails> draftDetails = ecourtHomeService.getDraftDetails(user.getUm_id());
		ActionResponse<RegisteredCaseDetails> response = new ActionResponse<RegisteredCaseDetails>();

		PetitionerDetails pDetails =null;

		RespondentDetails rDetails = null;

		

		for (RegisteredCaseDetails rcd : draftDetails) {
			pDetails= new PetitionerDetails();
			rDetails= new RespondentDetails();
			pDetails =ecourtHomeService.getFirstPetitioner(rcd.getRcd_id());
			rDetails=ecourtHomeService.getFirstRespondent(rcd.getRcd_id());
			        if(pDetails.getPt_id()!=null){
			         rcd.setPetitionerDetails(pDetails);
			        }
			        if(rDetails.getRt_id()!=null) {
			          rcd.setRespondentDetails(rDetails);
			        }
		
			newDraftList.add(rcd);
		}

		int draftcount = 0;
		draftcount = draftDetails.size();

		System.out.println(draftcount);

		if (newDraftList != null && newDraftList.size() != 0)
			response.setResponse("TRUE");
		response.setData(draftcount);
		response.setModelList(newDraftList);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/getPetitioner", method = RequestMethod.GET)
	@ResponseBody
	public String getRegisterCase(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<PetitionerDetails> response = null;

		Long doc = new Long(id);
		ActionResponse<PetitionerDetails> pd = new ActionResponse<PetitionerDetails>();
		String jsonData = null;

		response = ecourtHomeService.getPetitioner(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getRespondent", method = RequestMethod.GET)
	@ResponseBody
	public String getRespondent(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<RespondentDetails> response = null;

		Long doc = new Long(id);
		ActionResponse<RespondentDetails> pd = new ActionResponse<RespondentDetails>();
		String jsonData = null;

		response = ecourtHomeService.getRespondent(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	
	
	@RequestMapping(value = "/getActDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getActDetails(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<ActDetails> response = null;

		Long doc = new Long(id);
		ActionResponse<ActDetails> pd = new ActionResponse<ActDetails>();
		String jsonData = null;

		response = ecourtHomeService.getActDetails(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/getImpugnedOrder", method = RequestMethod.GET)
	@ResponseBody
	public String getImpugnedOrder(HttpServletRequest request) {
		String jsonData = "";
		String id = request.getParameter("docId");
		Long doc = new Long(id);
		List<ImpugnedOrder> impugnedOrderList = ecourtHomeService.getImpugnedOrder(doc);
		ActionResponse<ImpugnedOrder> response = new ActionResponse<ImpugnedOrder>();
		List<ImpugnedOrder> newImpugnedOrderList = new ArrayList<ImpugnedOrder>();
		for(ImpugnedOrder io:impugnedOrderList){
			ImpugnedOrder temp=new ImpugnedOrder();
			temp=io;
			if(io.getIo_hc_case_type()!=null){
				CaseType ct=ecourtAddCaseService.getCaseTypeById(temp.getIo_hc_case_type());
				temp.setHcCaseType(ct);
			}
			if(io.getIo_lc_case_type()!=null){
				LowerCourtCaseType lc=ecourtAddCaseService.getLCCaseTypeById(temp.getIo_lc_case_type());
				temp.setLcCaseType(lc);
			}
			if(io.getIo_district()!=null){
				District dt=ecourtHomeService.getDistrictById(temp.getIo_district());
				temp.setDistrict(dt);
			}
			newImpugnedOrderList.add(temp);
		}
		if (impugnedOrderList != null) {
			response.setResponse("TRUE");
			response.setModelList(newImpugnedOrderList);
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;
	}
	
	
	@RequestMapping(value = "/getTrialCourt", method = RequestMethod.GET)
	@ResponseBody
	public String getTrialCourt(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<TrialCourt> response = null;

		Long doc = new Long(id);
		ActionResponse<TrialCourt> pd = new ActionResponse<TrialCourt>();
		String jsonData = null;

		response = ecourtHomeService.getTrialCourt(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	
	@RequestMapping(value = "/getCourtFee", method = RequestMethod.GET)
	@ResponseBody
	public String getCourtFee(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CourtFee> response = null;

		Long doc = new Long(id);
		ActionResponse<CourtFee> pd = new ActionResponse<CourtFee>();
		String jsonData = null;

		response = ecourtHomeService.getCourtFee(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	
	

	@RequestMapping(value = "/getDistrictList", method = RequestMethod.GET)
	@ResponseBody
	public String getDistrictList() {

		List<District> response = null;

		ActionResponse<District> pd = new ActionResponse<District>();
		String jsonData = null;
System.out.println("");
		response = ecourtHomeService.getDistrictList();

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}
	@RequestMapping(value = "/getActMasterList", method = RequestMethod.GET)
	@ResponseBody
	public String getActMasterList() {

		List<ActMaster> response = null;

		ActionResponse<ActMaster> pd = new ActionResponse<ActMaster>();
		String jsonData = null;

		response = ecourtHomeService.getActMaster();

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = cm.convert_to_json(pd);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getStateList", method = RequestMethod.GET)
	@ResponseBody
	public String getStateList() {
		String jsonData = null;

		ActionResponse<State> response = new ActionResponse<State>();

		List<State> state = ecourtHomeService.getStateList();

		response.setModelList(state);
		if (response != null) {
			jsonData = cm.convert_to_json(response);
		}

		return jsonData;
	}

	@RequestMapping(value = "/getCaseTypes", method = RequestMethod.GET)
	@ResponseBody
	public String getCasetypes() {
		String jsonData = null;

		ActionResponse<CaseType> response = new ActionResponse<CaseType>();

		List<CaseType> state = ecourtHomeService.getCasetypes();

		response.setModelList(state);
		if (response != null) {
			jsonData = cm.convert_to_json(response);
		}

		return jsonData;
	}
	@RequestMapping(value = "/getLowerCourtCaseTypes", method = RequestMethod.GET)
	@ResponseBody
	public String getLowerCourtCaseTypes() {
		String jsonData = null;

		ActionResponse<LowerCourtCaseType> response = new ActionResponse<LowerCourtCaseType>();

		List<LowerCourtCaseType> caseTypes = ecourtHomeService.getLowerCourtCaseTypes();

		response.setModelList(caseTypes);
		if (response != null) {
			jsonData = cm.convert_to_json(response);
		}

		return jsonData;
	}

	@RequestMapping(value = "/getLowerCourtList", method = RequestMethod.GET)
	@ResponseBody
	public String getLowerCourtList() {
		String jsonData = null;

		ActionResponse<LowerCourtTypes> response = new ActionResponse<LowerCourtTypes>();

		List<LowerCourtTypes> courtList = ecourtHomeService.getLowerCourtList();

		response.setModelList(courtList);
		if (response != null) {
			jsonData = cm.convert_to_json(response);
		}

		return jsonData;
	}
	@RequestMapping(value = "/getCheckList", method = RequestMethod.GET)
	@ResponseBody
	public String getCheckList(HttpServletRequest request) {

		String id = request.getParameter("docId");
		List<CaseCheckListMapping> mapping = null;

		Long doc = new Long(id);
		ActionResponse<CaseCheckListMapping> response = new ActionResponse<CaseCheckListMapping>();
		String jsonData = null;

		mapping = ecourtHomeService.getCheckList(doc);

		if (mapping != null) {
			response.setModelList(mapping);
			response.setResponse("TRUE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/searchCaveat", method = RequestMethod.GET)
	@ResponseBody
	public String searchCaveat(HttpServletRequest request) {

		String id = request.getParameter("docId");
		RegisteredCaseDetails caseDetail = new RegisteredCaseDetails();

		Long doc = new Long(id);
		ActionResponse<Caveat> response = new ActionResponse<Caveat>();
		String jsonData = null;
		
		caseDetail = ecourtAddCaseService.getRegisterCase(doc);
		List<ImpugnedOrder> impungedOrders = ecourtHomeService.getImpugnedOrder(doc);
		List<TrialCourt> trialCourts = ecourtHomeService.getTrialCourt(doc);
		Long ct_type=caseDetail.getRcd_ct_id();
		Long dist_id=caseDetail.getRcd_dt_id();
		String dates="";
		List<Caveat> caveatList = new ArrayList<Caveat>() ;
		
		for(ImpugnedOrder im:impungedOrders){
			if(im.getIo_decision_date()!=null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(im.getIo_decision_date());
				date="'"+date+"'";
				dates+=date+",";
			}
		}
		
		if(dates!=""){
			dates=dates.substring(0, dates.length() - 1);
		}
		caveatList=ecourtHomeService.searchCaveat(ct_type,dist_id,dates);
		
		
		List<Caveat> newCaveatList=new ArrayList<Caveat>();
		for(Caveat c :caveatList){
			Caveat temp=new Caveat();
			temp=c;
			if(c.getCav_hc_case_type()!=null){
				CaseType ct=ecourtAddCaseService.getCaseTypeById(c.getCav_hc_case_type());
				temp.setHcCaseType(ct);
			}
			if(c.getCav_lc_case_type()!=null){
				LowerCourtCaseType ct=ecourtAddCaseService.getLCCaseTypeById(c.getCav_lc_case_type());
				temp.setLcCaseType(ct);
			}
			newCaveatList.add(temp);
			
		}
		response.setModelList(newCaveatList);
		jsonData=cm.convert_to_json(response);
		return jsonData;

	}
	@RequestMapping(value = "/searchCaveatold", method = RequestMethod.GET)
	@ResponseBody
	public String searchCaveatold(HttpServletRequest request) {

		String id = request.getParameter("docId");
		RegisteredCaseDetails caseDetail = new RegisteredCaseDetails();

		Long doc = new Long(id);
		ActionResponse<CaveatOld> response = new ActionResponse<CaveatOld>();
		String jsonData = null;
		
		caseDetail = ecourtAddCaseService.getRegisterCase(doc);
		List<ImpugnedOrder> impungedOrders = ecourtHomeService.getImpugnedOrder(doc);
		List<TrialCourt> trialCourts = ecourtHomeService.getTrialCourt(doc);
		Long ct_type=caseDetail.getRcd_ct_id();
		Long dist_id=caseDetail.getRcd_dt_id();
		String dates="";
		List<CaveatOld> caveatList = new ArrayList<CaveatOld>() ;
		
		for(ImpugnedOrder im:impungedOrders){
			if(im.getIo_decision_date()!=null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(im.getIo_decision_date());
				date="'"+date+"'";
				dates+=date+",";
			}
		}
		for(TrialCourt tc:trialCourts){
			if(tc.getTr_decision_date()!=null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(tc.getTr_decision_date());
				date="'"+date+"'";
				dates+=date+",";
			}
		}
		if(dates!=""){
			dates=dates.substring(0, dates.length() - 1);
		}
		caveatList=ecourtHomeService.searchCaveatOld(ct_type,dist_id,dates);
		
		response.setModelList(caveatList);
		
		List<CaveatOld> newCaveatList=new ArrayList<CaveatOld>();
		/*for(CaveatOld c :caveatList){
			CaveatOld temp=new CaveatOld();
			temp=c;
			if(c.getCav_hc_case_type()!=null){
				CaseType ct=ecourtAddCaseService.getCaseTypeById(c.getCav_hc_case_type());
				temp.setHcCaseType(ct);
			}
			if(c.getCav_lc_case_type()!=null){
				LowerCourtCaseType ct=ecourtAddCaseService.getLCCaseTypeById(c.getCav_lc_case_type());
				temp.setLcCaseType(ct);
			}
			newCaveatList.add(temp);
			
		}*/
		jsonData=cm.convert_to_json(response);
		return jsonData;

	}
}
