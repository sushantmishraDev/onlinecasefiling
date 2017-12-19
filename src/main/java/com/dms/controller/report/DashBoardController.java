package com.dms.controller.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.DailyActivityReport;
import com.dms.model.Lookup;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.DocumentService;
import com.dms.service.LookupService;
import com.dms.service.report.CommonReportsService;
import com.dms.utility.GlobalFunction;


@Controller
public class DashBoardController {
	private GlobalFunction globalfunction;
		
	@Autowired
	CommonReportsService reportsService;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	LookupService lookupService;
	
		//ccc
	@Autowired
	private LookupService lkService;
	
	public DashBoardController()
	{
		globalfunction = new GlobalFunction();
	}
	
	
	
	
	@RequestMapping(value="/dashboard/getreport",method = RequestMethod.GET)
	public  @ResponseBody String  getDashBoardReport(HttpSession session,HttpServletRequest request){
	
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
	
	Lookup uploadstage=lookupService.getLookup("DMS_STAGE", "UPLOAD");
	Lookup makerstage=lookupService.getLookup("DMS_STAGE", "MAKER");
	Lookup checkerstage=lookupService.getLookup("DMS_STAGE", "CHECKER");
	Lookup verifierstage=lookupService.getLookup("DMS_STAGE", "VERIFIER");
	Lookup verifiedstage=lookupService.getLookup("DMS_STAGE", "VERIFIED");
		
	if(rolename.equals("DMSAdmin")){ 

		dar.setFlag1(1);
		dar.setFlag2(1);
		dar.setFlag3(1);
		dar.setFlag4(1);
		dar.setFlag5(1);
		dar.setFlag6(1);
		
		dar.setParameter1("Pending At Upload Level");
		dar.setParameter2("Pending At Maker Level");
		dar.setParameter3("Pending At Checker Level");
		dar.setParameter4("Pending At Verifier Level");
		dar.setParameter5("Total Verified Files");
		dar.setParameter6("Total Deleted Files");
		
		Integer uploadcount=documentService.filesPendingAtUploadStage(uploadstage.getLk_id());
		Integer makercount=documentService.filesPendingAtMakerStage(makerstage.getLk_id());
		Integer checkercount=documentService.filesPendingAtCheckerStage(checkerstage.getLk_id());
		Integer verifiercount=documentService.filesPendingAtVerifierStage(verifierstage.getLk_id());
		Integer verifiedcount=documentService.filesCountAtVerifiedStage(verifiedstage.getLk_id());
		Integer deletedcount=documentService.filesCountAtDeletedStage();
				
		dar.setValue1(""+uploadcount);
		dar.setValue2(""+makercount);
		dar.setValue3(""+checkercount);
		dar.setValue4(""+verifiercount);
		dar.setValue5(""+verifiedcount);
		dar.setValue6(""+deletedcount);
		
	}
	darList.add(dar);
	
	response.setModelList(darList);
	
	if (dar != null) {
		response.setResponse("TRUE");
		result = globalfunction.convert_to_json(response);
	} else {
		response.setResponse("FALSE");
	}

	
	return result;
	
}

/*@RequestMapping(value="/report/getcartonReportData",method = RequestMethod.GET)
	public  @ResponseBody String  getcartonData(){
	
	String result = null;

	ActionResponse<BundleAllocation> response = new ActionResponse();
	
	List<BundleAllocation> catrondata=bundleAllocationService.getCartonSearchData();
	
	response.setModelList(catrondata);
	
	if (catrondata != null) {
		response.setResponse("TRUE");
		result = commenMethod.convert_to_json(response);
	} else {
		response.setResponse("FALSE");
	}

	return result;
	
}*/
	
	

}
