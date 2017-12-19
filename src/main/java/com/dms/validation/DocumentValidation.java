package com.dms.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActionResponse;
import com.dms.model.Document;
import com.dms.model.JudgementFileDetail;
import com.dms.model.MediaFile;
import com.dms.model.MetaData;
import com.dms.model.ReportsView;
import com.dms.model.Repository;
import com.dms.service.DocumentService;
import com.dms.service.FolderService;
import com.dms.service.JudgementService;
import com.dms.service.MediaFileService;
import com.dms.service.MetaDataService;
import com.dms.service.RepositoryService;
import com.dms.utility.GlobalFunction;


@Component
public class DocumentValidation {
	
	@Autowired 
	private FolderService folderService;
	
	@Autowired 
	private RepositoryService repositoryService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private MetaDataService metaDataService;
	

	private GlobalFunction globalfunction;
	
	@Autowired 
	private JudgementService judgementService;
	
	@Autowired
	private MediaFileService mediafileService;
	
	public DocumentValidation()
	{
		globalfunction=new GlobalFunction();
	}
	
	public ActionResponse<Repository> doValidation(Document documenttemp,MultipartHttpServletRequest request) {
	// TODO Auto-generated method stub
		MultipartFile mpf = null;
		ActionResponse response = new ActionResponse();
		Validator validation=new Validator();
		
		List<String> errorList = new ArrayList<String>();
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";
		Iterator<String> itr = request.getFileNames();
		validation.isRequired("rep_id", documenttemp.getRep_id());
		if(documenttemp.getDoc_type()==1)
		{
			validation.isRequired("folder_id", documenttemp.getFolder_id());
		}
		validation.isRequired("doc_type", documenttemp.getDoc_type());
		
		if(documenttemp.getDoc_type()==2 || documenttemp.getDoc_type()==3)
		{
			validation.isRequired("parent_id", documenttemp.getParent_id());
		}
		
		error=validation.getError();
		while (itr.hasNext()) 
		{
			mpf = request.getFile(itr.next());
			String filename=mpf.getOriginalFilename();
			
			String ext = FilenameUtils.getExtension(mpf.getOriginalFilename());
			String encryptedfilename=globalfunction.md5encryption(mpf.getOriginalFilename())+"."+ext;
			if(documenttemp.getDoc_type()==1 || documenttemp.getDoc_type()==3){
				Document d=documentService.checkCaseFileExist(documenttemp.getFolder_id(),encryptedfilename);
				if(d.getId()!=null && documenttemp.getId()==null)
				{
					errorList=error.get("document_name");
					
					if(errorList==null)
						errorList=new ArrayList<String>();
					
					errorList.add("Document with name "+filename+" already exist");
					error.put("document_name", errorList);
				}
				if(documenttemp.getId()!=null && d.getId()!=null)
				{
					if(documenttemp.getId()!=d.getId())
					{
						errorList=error.get("document_name");
						
						if(errorList==null)
							errorList=new ArrayList<String>();
						
						errorList.add("Document with name "+filename+" already exist");
						error.put("document_name", errorList);
					}
				}
			}
			if(documenttemp.getDoc_type()==2){
				JudgementFileDetail d=judgementService.checkJudgementFileExist(documenttemp.getFolder_id(),encryptedfilename);
				if(d.getJfd_id()!=null)
				{
					errorList=error.get("document_name");
					
					if(errorList==null)
						errorList=new ArrayList<String>();
					
					errorList.add("Judgement File with name "+filename+" already exist");
					error.put("document_name", errorList);
				}
			}
			if(documenttemp.getDoc_type()==4){
				MediaFile d=mediafileService.checkMediaFileExist(documenttemp.getFolder_id(),encryptedfilename);
				if(d.getMf_id()!=null)
				{
					errorList=error.get("document_name");
					
					if(errorList==null)
						errorList=new ArrayList<String>();
					
					errorList.add("Media File with name "+filename+" already exist");
					error.put("document_name", errorList);
				}
			}

		}
		
		if(!error.isEmpty())
		{
			status = "FALSE";
		}
		response.setResponse(status);
		response.setDataMapList(error);
		return response;
	
	}
	public ActionResponse<Repository> doValidationOnUpdate(Document documenttemp,MultipartHttpServletRequest request) {
		// TODO Auto-generated method stub
			MultipartFile mpf = null;
			ActionResponse response = new ActionResponse();
			Validator validation=new Validator();
			
			List<String> errorList = new ArrayList<String>();
			Map<String, List> error = new HashMap<String, List>();
			String status = "TRUE";
			Iterator<String> itr = request.getFileNames();
						
			error=validation.getError();
			while (itr.hasNext()) 
			{
				mpf = request.getFile(itr.next());
				String filename=mpf.getOriginalFilename();
				
				String ext = FilenameUtils.getExtension(mpf.getOriginalFilename());
				String encryptedfilename=globalfunction.md5encryption(mpf.getOriginalFilename())+"."+ext;
				if(documenttemp.getDoc_type()==1 || documenttemp.getDoc_type()==3){
					Document d=documentService.checkCaseFileExist(documenttemp.getFolder_id(),encryptedfilename);
					if(d.getId()!=null && documenttemp.getId()==null)
					{
						errorList=error.get("document_name");
						
						if(errorList==null)
							errorList=new ArrayList<String>();
						
						errorList.add("Document with name "+filename+" already exist");
						error.put("document_name", errorList);
					}
					if(documenttemp.getId()!=null && d.getId()!=null)
					{
						if(documenttemp.getId()!=d.getId())
						{
							errorList=error.get("document_name");
							
							if(errorList==null)
								errorList=new ArrayList<String>();
							
							errorList.add("Document with name "+filename+" already exist");
							error.put("document_name", errorList);
						}
					}
				}
				if(documenttemp.getDoc_type()==2){
					JudgementFileDetail d=judgementService.checkJudgementFileExist(documenttemp.getFolder_id(),encryptedfilename);
					if(d.getJfd_id()!=null)
					{
						errorList=error.get("document_name");
						
						if(errorList==null)
							errorList=new ArrayList<String>();
						
						errorList.add("Judgement File with name "+filename+" already exist");
						error.put("document_name", errorList);
					}
				}
				if(documenttemp.getDoc_type()==4){
					MediaFile d=mediafileService.checkMediaFileExist(documenttemp.getFolder_id(),encryptedfilename);
					if(d.getMf_id()!=null)
					{
						errorList=error.get("document_name");
						
						if(errorList==null)
							errorList=new ArrayList<String>();
						
						errorList.add("Media File with name "+filename+" already exist");
						error.put("document_name", errorList);
					}
				}

			}
			
			if(!error.isEmpty())
			{
				status = "FALSE";
			}
			response.setResponse(status);
			response.setDataMapList(error);
			return response;
		
		}
	
		public ActionResponse<Document> validateMetadata(Document document) {
		// TODO Auto-generated method stub
			MultipartFile mpf = null;
			Validator validation=new Validator();
			
			ActionResponse response = new ActionResponse();
			List<String> errorList = new ArrayList<String>();
			Map<String, List> error = new HashMap<String, List>();
			String status = "TRUE";
			boolean judgeFlag = false;
			MetaData md=new MetaData();
			String caseYear="";
			String benchCode="";
			String caseType="";		

			for (MetaData metaData : document.getMetaDataList()) {
				if(metaData.getMd_mf_mid()==33 && metaData.getMd_value().trim().equals("3"))
				{
					judgeFlag = true;
				}
				if(metaData.getMd_mf_mid().longValue()==1L){
					//get bench code
						benchCode=metaData.getMd_value();
					}
					if(metaData.getMd_mf_mid().longValue()==2L){
						//get casetype
						caseType=metaData.getMd_value();
					}
					if(metaData.getMd_mf_mid().longValue()==4L){
						//get case year
						caseYear=metaData.getMd_value();
					}

			}
			
			if(judgeFlag)
			{
				int judgeCnt = 0;
				for(MetaData metaData : document.getMetaDataList()) {
					if(metaData.getMd_mf_mid()==11)
					{
						judgeCnt = judgeCnt+1;
					}

				}
				
				if((judgeCnt%2)==0)
				{
					errorList=error.get("Judge_name");
					if(errorList==null)
						errorList=new ArrayList<String>();
					
			    	errorList.add("In Full Division Judge count should be odd in number.");
					error.put("Judge_name", errorList);
			    	
				}
			}
			for ( MetaData metaData : document.getMetaDataList()) {
				//12 24 30 Check all date are correct or not
				
				final String[] DateList = new String[] {"12","24","30"};
				
				String md_mfMid = ""+metaData.getMd_mf_mid();
				if(metaData.getMd_mf_mid().longValue()==3L){
					// check unique case number
					
					ReportsView reportsview=metaDataService.checkUniqueCaseNoWithId(metaData.getMd_value(),caseType,benchCode,caseYear);
					
					if(reportsview.getFileId()!=null && document.getId()!=reportsview.getFileId()){
						
						errorList=error.get("caseno");
						if(errorList==null)
							errorList=new ArrayList<String>();

						errorList.add("Duplicate "+metaData.getMetaField().getMf_lable());
						error.put("caseno", errorList);
					}
					validation.setError(error);
					validation.maxLength("caseno", metaData.getMd_value(), 7);
					String msg="Case No is invalid";
					validation.checkRegEx("caseno", metaData.getMd_value(), "^[1-9][0-9]{0,5}[0-9A-Z]{0,1}$",msg);
					error=validation.getError();
				}

				if(Arrays.asList(DateList).contains(md_mfMid))
				{
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					if(metaData.getMd_value()!=null && metaData.getMd_value().length() > 0)
					{
					    try {
					    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					        Date ip_date = df.parse(metaData.getMd_value());
					        
					        Date currDt = new Date();
					        
					        Date olddate = sdf.parse("01/01/1840");
					        
					        if(ip_date.compareTo(currDt)>0 || ip_date.compareTo(olddate)<0)
					        {
					        	
					        	errorList=error.get(metaData.getMetaField().getMf_name());
								if(errorList==null)
									errorList=new ArrayList<String>();

								errorList.add("Invalid "+metaData.getMetaField().getMf_lable());
								error.put(metaData.getMetaField().getMf_name(), errorList);
					        }
					        
					    } catch (ParseException e) {
					    	response.setResponse("FALSE");
					    	
					    	errorList=error.get(metaData.getMetaField().getMf_name());
					    	
					    	if(errorList==null)
								errorList=new ArrayList<String>();
					    	
					    	errorList.add("Invalid "+metaData.getMetaField().getMf_lable());
							error.put(metaData.getMetaField().getMf_name(), errorList);
					    	
					    }
					}
				}
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
