package com.dms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActDetails;
import com.dms.model.ActionResponse;
import com.dms.model.CaseType;
import com.dms.model.Caveat;
import com.dms.model.CaveatOld;
import com.dms.model.CourtFee;
import com.dms.model.District;
import com.dms.model.ImpugnedOrder;
import com.dms.model.LowerCourtCaseType;
import com.dms.model.PetitionUploaded;
import com.dms.model.PetitionerDetails;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.RespondentDetails;
import com.dms.model.TrialCourt;
import com.dms.service.CaseFileService;
import com.dms.service.LookupService;
import com.dms.utility.GlobalFunction;

@Controller
@RequestMapping("/casefile")
public class CaseFileController {
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private CaseFileService caseFileService;
	
	private GlobalFunction globalfunction;	
	
	public CaseFileController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/getCaseDetail", method = RequestMethod.GET)
	@ResponseBody
	public String getCaseDetail(HttpServletRequest request) {

		String id = request.getParameter("docId");
		RegisteredCaseDetails response = null;

		Long doc = new Long(id);
		ActionResponse<RegisteredCaseDetails> pd = new ActionResponse<RegisteredCaseDetails>();
		String jsonData = null;
		PetitionerDetails pDetails=null;
		RespondentDetails rDetails=null;
		response = caseFileService.getRegisterCase(doc);
        pDetails=caseFileService.getFirstPetitioner(doc);
		rDetails=caseFileService.getFirstRespondent(doc);
		if(pDetails.getPt_id()!=null){
			response.setPetitionerDetails(pDetails);
		}
          if(rDetails.getRt_id()!=null){
        	  response.setRespondentDetails(rDetails);
          }
		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelData(response);
			jsonData = globalfunction.convert_to_json(pd);
		}
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

		response = caseFileService.getPetitioner(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = globalfunction.convert_to_json(pd);

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

		response = caseFileService.getRespondent(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = globalfunction.convert_to_json(pd);

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

		response = caseFileService.getActDetails(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = globalfunction.convert_to_json(pd);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/getImpugnedOrder", method = RequestMethod.GET)
	@ResponseBody
	public String getImpugnedOrder(HttpServletRequest request) {
		String jsonData = "";
		String id = request.getParameter("docId");
		Long doc = new Long(id);
		List<ImpugnedOrder> impugnedOrderList = caseFileService.getImpugnedOrder(doc);
		ActionResponse<ImpugnedOrder> response = new ActionResponse<ImpugnedOrder>();
		List<ImpugnedOrder> newImpugnedOrderList = new ArrayList<ImpugnedOrder>();
		for(ImpugnedOrder io:impugnedOrderList){
			ImpugnedOrder temp=new ImpugnedOrder();
			temp=io;
			if(io.getIo_hc_case_type()!=null){
				CaseType ct=caseFileService.getCaseTypeById(temp.getIo_hc_case_type());
				temp.setHcCaseType(ct);
			}
			if(io.getIo_lc_case_type()!=null){
				LowerCourtCaseType lc=caseFileService.getLCCaseTypeById(temp.getIo_lc_case_type());
				temp.setLcCaseType(lc);
			}
			if(io.getIo_district()!=null){
				District dt=caseFileService.getDistrictById(temp.getIo_district());
				temp.setDistrict(dt);
			}
			newImpugnedOrderList.add(temp);
		}
		if (impugnedOrderList != null) {
			response.setResponse("TRUE");
			response.setModelList(newImpugnedOrderList);
			jsonData = globalfunction.convert_to_json(response);

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

		response = caseFileService.getTrialCourt(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = globalfunction.convert_to_json(pd);

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

		response = caseFileService.getCourtFee(doc);

		if (response != null) {
			pd.setResponse("TRUE");
			pd.setModelList(response);
			jsonData = globalfunction.convert_to_json(pd);

		}
		return jsonData;
	}
	@RequestMapping(value="/getUploadedDocuments", method=RequestMethod.GET)
	@ResponseBody
	public String getUploadedDocuments(HttpServletRequest request)
	{
		String jsonData=null;
		String file_id=request.getParameter("rcd_id");
		Long rcd_id=Long.valueOf(file_id);
		
		List<PetitionUploaded> documentList=caseFileService.getUploadedPetition(rcd_id);
		ActionResponse<PetitionUploaded> response=new ActionResponse<PetitionUploaded>();
		
		response.setModelList(documentList);
		response.setResponse("TRUE");
		
	     jsonData=globalfunction.convert_to_json(response);
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
		
		caseDetail = caseFileService.getRegisterCase(doc);
		List<ImpugnedOrder> impungedOrders = caseFileService.getImpugnedOrder(doc);
	
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
		caveatList=caseFileService.searchCaveat(ct_type,dist_id,dates);
		
		
		List<Caveat> newCaveatList=new ArrayList<Caveat>();
		for(Caveat c :caveatList){
			Caveat temp=new Caveat();
			temp=c;
			if(c.getCav_hc_case_type()!=null){
				CaseType ct=caseFileService.getCaseTypeById(c.getCav_hc_case_type());
				temp.setHcCaseType(ct);
			}
			if(c.getCav_lc_case_type()!=null){
				LowerCourtCaseType ct=caseFileService.getLCCaseTypeById(c.getCav_lc_case_type());
				temp.setLcCaseType(ct);
			}
			newCaveatList.add(temp);
			
		}
		response.setModelList(newCaveatList);
		jsonData=globalfunction.convert_to_json(response);
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
		
		caseDetail = caseFileService.getRegisterCase(doc);
		List<ImpugnedOrder> impungedOrders = caseFileService.getImpugnedOrder(doc);
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
		
		if(dates!=""){
			dates=dates.substring(0, dates.length() - 1);
		}
		caveatList=caseFileService.searchCaveatOld(ct_type,dist_id,dates);
		
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
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;

	}

	
	
}
