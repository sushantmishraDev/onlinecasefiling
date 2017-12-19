package com.dms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.queryParser.ParseException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dms.model.ActionResponse;
import com.dms.model.Document;
import com.dms.model.DocumentFileStage;
import com.dms.model.Folder;
import com.dms.model.JudgementFileDetail;
import com.dms.model.JudgementFileStage;
import com.dms.model.Lookup;
import com.dms.model.MediaFile;
import com.dms.model.MediaFileStage;
import com.dms.model.ReportsView;
import com.dms.model.Repository;
import com.dms.model.SearchCriteria;
import com.dms.model.SearchForm;
import com.dms.model.SearchQuery;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.CaseFileStageService;
import com.dms.service.DocumentFileDetailsService;
import com.dms.service.DocumentFileStageService;
import com.dms.service.DocumentService;
import com.dms.service.FolderService;
import com.dms.service.JudgementFileStageService;
import com.dms.service.JudgementService;
import com.dms.service.LookupService;
import com.dms.service.MediaFileService;
import com.dms.service.PermissionService;
import com.dms.service.RepositoryService;
import com.dms.service.SearchQueryService;
import com.dms.utility.GlobalFunction;
import com.dms.utility.IndexItem;
import com.dms.utility.Indexer;
import com.dms.utility.Searcher;
import com.dms.validation.DocumentValidation;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class DocumentController {
	@Autowired 
	private DocumentService documentService;
	
	@Autowired 
	private RepositoryService repositoryService;
	
	@Autowired 
	private FolderService folderService;
	
	@Autowired
	private DocumentValidation documentValidation;
	
	@Autowired 
	private DocumentFileStageService documentFileStageService;
	
	@Autowired 
	private JudgementFileStageService judgementFileStageService;
	
	@Autowired 
	private JudgementService judgementService;
	
	@Autowired 
	private SearchQueryService searchqueryService;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private MediaFileService mediafileService;
	
	@Autowired
	private DocumentFileDetailsService dfdService;
	
	@Autowired
	private CaseFileStageService caseFileStageService;
	
	@Autowired
	private PermissionService permissionService;
	
	private GlobalFunction globalfunction;
	
	private String metafieldsquery="";

	private String query="";
	private List<Long> doc_ids=new ArrayList<Long>();
	
	@Autowired
	ServletContext context;
	//private static final String INDEX_DIR = "/home/administrator/java/dms/src/main/java/main/resources/index";
	private static final String INDEX_DIR = "E:\\index";
    private static final int DEFAULT_RESULT_SIZE = 100;
    
    private List<Long> childfolders=new ArrayList<Long>();
    
	public DocumentController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/document/createindex", method = RequestMethod.GET)
	public @ResponseBody String createIndex() throws IOException{
		String jsonData="";
		//File pdfFile = new File("/home/administrator/java/SimplePDFSearch/src/resources/xNUl6DqPsLp4fmZpkbNZ.pdf");
		List <Document> documents=new ArrayList<Document>();
		documents=documentService.getAllWithoutIndex();
		
		Lookup lk=lookupService.getLookUpObject("LUCENE_DIR");

		Lookup lucene_temp=lookupService.getLookUpObject("LUCENE_TEMP_DIR");

		
		for(Document document:documents)
		{
			Repository repository=repositoryService.getRepository(document.getRep_id());
			Folder folder=folderService.getFolderById(document.getFolder_id());
			String repBasepath=repository.getBasepath()+File.separator+repository.getName();
			String folderPath="";
			if(folder.getParent_id()!=null){
				Long parent_id=folder.getParent_id();
			    while(parent_id !=null){
			    	Folder fd=folderService.getFolderById(parent_id);
			    	folderPath = fd.getFolder_name()+File.separator+folderPath;
				    parent_id   = fd.getParent_id();							
			    } 	
			}
			String basePath=repBasepath+File.separator+folderPath+folder.getFolder_name();
			System.out.println("basepath="+basePath);
			
			//File pdfFile = new File(basePath+File.separator+document.getFile_name());
			
			String sourcefilename=basePath+File.separator+document.getDocument_name();
			String destfilename=lucene_temp.getLk_longname()+File.separator+File.separator+document.getDocument_name();
			
			try {
				FileInputStream fis = new FileInputStream(sourcefilename);
				FileOutputStream fos = new FileOutputStream(destfilename);
				try {
					globalfunction.decrypt(globalfunction.encryptedCode, fis, fos);
					
					File pdfFile = new File(destfilename);
					if(pdfFile.exists())
					{
				        IndexItem pdfIndexItem = index(pdfFile,document.getId());
				        Indexer indexer = new Indexer(lk.getLk_longname());
				        indexer.index(pdfIndexItem);
				        indexer.close();
				        
				        document.setIndex(1);
				        documentService.save(document);
					}
					
				} catch (Throwable e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			
			
		}
		return jsonData;
	}
	//search content
	@RequestMapping(value = "/document/searchincontent", method = RequestMethod.GET)
	public @ResponseBody String searchInContent(SearchCriteria searchcriteria) throws IOException, ParseException {
			String jsonData="";
			String operator=searchcriteria.getOperator();
//			SearchCriteria searchcriteria=new SearchCriteria();
//	        searchcriteria.setSearchtext("birt,workflow");
	        System.out.println("=================");
	        String searchtext=searchcriteria.getSearchtext();
	        String[] searchkeywords=searchtext.split(",");
	        List<Long> documentids=new ArrayList<Long>();
	       
	        Lookup lk=lookupService.getLookUpObject("LUCENE_DIR");
	        for(String str:searchkeywords)
	        {
	        	 List<IndexItem> results = new ArrayList<IndexItem>();
	        	try {
	        		Searcher searcher = new Searcher(lk.getLk_longname());
	        		results = searcher.findByContent(str, DEFAULT_RESULT_SIZE);
	        		 searcher.close();
				} catch (org.apache.lucene.queryParser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
		        if(!results.isEmpty())
		        {
		        	for(IndexItem item:results){
		        		//doc_ids.add(item.getId());
		        		documentids.add(item.getId());
		        		
		        	}
		        }
	        }
	        System.out.println("Docids1="+globalfunction.convert_to_json(doc_ids));
	        if(!documentids.isEmpty()){
		    	if(this.query.equals(""))
		   		{
		   			operator="";
		   		}
		    	String str="";
				
		    	for(Long id:documentids){
					str=str+""+id+",";
				}
				str = str.substring(0, str.length()-1);
		   		this.query+=" "+operator+" md_fd_mid in ("+str+")";
		   		
		   		System.out.println("searchincontentquery="+this.query);
	        }
	       
	    return jsonData;
	}
	 //Extract text from PDF document
    public static IndexItem index(File file,Long doc_id) throws IOException {
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);
        doc.close();
        return new IndexItem(doc_id, file.getName(), content);
    }

   //Print the results
    private static void print(int result) {
    	if(result==1)
        System.out.println("The document contains the search keyword");
    	else
    	System.out.println("The document does not contain the search keyword");

    }
	
    @RequestMapping(value = "/document/getalldocuments", method = RequestMethod.GET)
	public @ResponseBody String getFolders(HttpSession session) {
		String jsonData="";
		User user=new User();
		user=(User) session.getAttribute("USER");
		
		List<Long> folderIds=permissionService.getAssignedFolders(user.getUm_id());
		List<Document> documents=new ArrayList<Document>();
		
		if(!folderIds.isEmpty()){
			documents=documentService.getUsingFolderIds(folderIds);	
		}
	    
	    jsonData=globalfunction.convert_to_json(documents);
	    return jsonData;
	}
	
	@RequestMapping(value = "/document/getsearchqueries", method = RequestMethod.GET)
	public @ResponseBody String getsearchqueries() {
		String jsonData="";
		ActionResponse<SearchQuery> response= new ActionResponse<SearchQuery>();
	    List<SearchQuery> searchqueries=searchqueryService.getAll();
	    response.setResponse("true");
	    response.setModelList(searchqueries);
	    jsonData=globalfunction.convert_to_json(response);
	    return jsonData;
	}
	
	@RequestMapping(value = "/document/manage")
	public String manage(Model model) {
		
		return "/document/manage";
	}
	@RequestMapping(value = "/document/search")
	public String advancedsearch(Model model) {
	
		return "/document/search";
	}
	@RequestMapping(value = "/document/basicsearch")
	public String search(Model model) {
//		List<Lookup> lookups=lookupService.getAll("CASE_TYPE");
//		
//		for(Lookup lookup:lookups){
//			String long_name=toTitleCase(lookup.getLk_longname());
//			
//			long_name=long_name.replaceAll("[^a-zA-Z0-9 .,)&(-]+","");
//			
//			lookup.setLk_longname(long_name);
//			
//			lookupService.save(lookup);
//			
//			String foldername = long_name;
//			
//			String basePath="D:\\High Court"+File.separator+"Allahabad"+File.separator+foldername;
//			
//			Folder folder=new Folder();
//			folder.setCreated(new Date());
//			folder.setName(foldername);
//			folder.setDescription(foldername);
//			folder.setCreated_by(1L);
//			folder.setRep_id(2L);
//			folder.setStatus(1);
//			folder.setParent_id(1L);
//			folderService.save(folder);
//			System.out.println("basepath="+basePath);
//			
//			
//			globalfunction.createfolder(basePath);
//			
//		}
		
		return "/document/basicsearch";
	}
	
	@RequestMapping(value = "/document/documentlist")
	public String freshlistdocumentlist(Model model) {
		return "/document/freshdocumentlist";
	}
	
	@RequestMapping(value = "/document/saveddocumentlist")
	public String savedocumentlist(Model model) {
		return "/document/saveddocumentlist";
	}
	
	@RequestMapping(value = "/document/rejecteddocumentlist")
	public String rejecteddocumentlist(Model model) {
		return "/document/rejecteddocumentlist";
	}
	
	@RequestMapping(value = "/document/reuploaddocumentlist")
	public String reuploaddocumentlist(Model model) {
		return "/document/reuploaddocumentlist";
	}
	
	@RequestMapping(value = "/document/reuploadjudgementlist")
	public String reuploadjudgementlist(Model model) {
		return "/document/reuploadjudgementlist";
	}
	
	@RequestMapping(value = "/document/updatedocumentlist")
	public String updatedocumentlist(Model model) {
		return "/document/updatedocumentlist";
	}
	public static String toTitleCase(String input) {
	    StringBuilder titleCase = new StringBuilder();
	    boolean nextTitleCase = true;
	    input=input.toLowerCase();
	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toTitleCase(c);
	            nextTitleCase = false;
	        }

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}
	
	@RequestMapping(value = "/document/create",method = RequestMethod.POST)
    public @ResponseBody String create(MultipartHttpServletRequest request,HttpSession session) throws DocumentException {    	
    	String jsonData="";
    	ActionResponse<Repository> response = new ActionResponse();
    	String ipaddress = request.getRemoteAddr();
    	
    	User user=new User();
		user=(User) session.getAttribute("USER");

    	Document documenttemp=new Document();
    	
    	if(request.getParameter("parent_id")!=null)
    		documenttemp.setParent_id(Long.parseLong(request.getParameter("parent_id"), 10));
    	
    	if(request.getParameter("rep_id")!=null)
    		documenttemp.setRep_id(Long.parseLong(request.getParameter("rep_id"), 10));
    	
    	if(request.getParameter("folder_id")!=null)
    		documenttemp.setFolder_id(Long.parseLong(request.getParameter("folder_id"), 10));
    	
    	documenttemp.setDoc_type(Integer.parseInt(request.getParameter("doc_type"), 10));
    	String judgement_date=request.getParameter("jfd_judgement_date");
    	if(judgement_date!=null)
    	{
    		
    	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
    	    Date startDate;
    	    try {
				startDate = df.parse(judgement_date);
				documenttemp.setJudgement_date(startDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	        
    	}
    	MultipartFile mpf = null;
    	System.out.println("description: " + request.getParameter("description"));
    	Iterator<String> itr = request.getFileNames();
    	Folder folder=new Folder();
		 
		 if(documenttemp.getParent_id()!=null){
			 Document parentdocument=documentService.getDocument(documenttemp.getParent_id());
			 folder=folderService.getFolderById(parentdocument.getFolder_id());
			 documenttemp.setFolder_id(parentdocument.getFolder_id());
		 }else{
			 folder=folderService.getFolderById(documenttemp.getFolder_id());
		 }
		 
    	response = documentValidation.doValidation(documenttemp,request);
		
    	Lookup lk=lookupService.getLookup("DMS_STAGE", "MAKER");
    	Lookup lkstage=lookupService.getLookup("DMS_STAGE", "UPLOAD");
    	
    	if(response.getResponse()=="TRUE"){
			 String basePath="";
			 String folderPath="";
				
			 Repository repository=repositoryService.getRepository(documenttemp.getRep_id());
			 
			 
			 String repBasepath=repository.getBasepath()+File.separator+repository.getName();
			 if(folder.getParent_id()!=null)
			 {
				 Long parent_id=folder.getParent_id();
				 while(parent_id !=null){
					 Folder fd=folderService.getFolderById(parent_id);
					 folderPath = fd.getFolder_name()+File.separator+folderPath;
					 parent_id   = fd.getParent_id();							
				    } 	  
			 }
			basePath=repBasepath+File.separator+folderPath+folder.getFolder_name();
			File theDir = new File(basePath);
				
				while (itr.hasNext()) 
				{
					mpf = request.getFile(itr.next());
					//String imagepath = theDir + File.separator + mpf.getOriginalFilename();
					//String encryptedpdfpath = theDir + File.separator + "en_"+mpf.getOriginalFilename();
										String temppath=theDir + File.separator +mpf.getOriginalFilename();
					String ext = FilenameUtils.getExtension(temppath);
					String encryptedpdfname=globalfunction.md5encryption(mpf.getOriginalFilename())+"."+ext;
					//String encryptedpdfname=mpf.getOriginalFilename();
					String encryptedpdfpath=theDir + File.separator +encryptedpdfname;

					try {
						if(documenttemp.getDoc_type()==4){
							// media file
							FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(encryptedpdfpath));
							
						}else{
							
							FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(temppath));
							PdfReader reader = new PdfReader(temppath);	
							PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(encryptedpdfpath));
								//stamper.setEncryption("Hello".getBytes(), "World".getBytes(),PdfWriter.DirectionL2R, PdfWriter.STRENGTH128BITS);
							stamper.setEncryption("stockholding".getBytes(), "allahabad".getBytes(),
							PdfWriter.ALLOW_ASSEMBLY, PdfWriter.ENCRYPTION_AES_128);
							stamper.close();
							reader.close();
							File f=new File(temppath);
							f.delete();
						}
						//FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(imagepath));
						
						
						//FileInputStream fis = new FileInputStream(imagepath);
						//File f=new File(imagepath);
						//FileOutputStream fos = new FileOutputStream(encryptedpdfpath);
						
						try {
							
							//globalfunction.encrypt(globalfunction.encryptedCode, fis, fos);
							//delete uploaded file and save this file in encrypted format
							//f.delete();

							
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if(documenttemp.getDoc_type()!=null){
							if(documenttemp.getDoc_type()==2){
								// save record as judgement document
								JudgementFileDetail  jfd=new JudgementFileDetail();
								jfd.setJfd_document_name(mpf.getOriginalFilename());
								jfd.setJfd_encrypted_name(encryptedpdfname);
								jfd.setJfd_fd_mid(documenttemp.getParent_id());
								jfd.setJfd_judgement_date(documenttemp.getJudgement_date());
								jfd.setJfd_folder_id(documenttemp.getFolder_id());
								jfd.setJfd_stage_lid(lk.getLk_id());
								jfd.setJfd_created(new Date());
								jfd.setJfd_reject_status("N");
								jfd.setJfd_rec_status(1);
								
								jfd=judgementService.save(jfd);
								
								JudgementFileStage jfs=new JudgementFileStage();
								jfs.setJfs_cr_by(user.getUm_id());
								jfs.setJfs_jfd_mid(jfd.getJfd_id());
								jfs.setJfs_ip_address(ipaddress);
								jfs.setJfs_reject_status("N");
								jfs.setJfs_stage_date(new Date());
								jfs.setJfs_stage_lid(lkstage.getLk_id());
								judgementFileStageService.save(jfs);
								
								response.setData(jfd);
								
							}else if(documenttemp.getDoc_type()==1 || documenttemp.getDoc_type()==3){
								Document document=new Document();
								
								document.setDoc_type(documenttemp.getDoc_type());
								document.setRep_id(documenttemp.getRep_id());
								document.setParent_id(documenttemp.getParent_id());
								document.setFolder_id(documenttemp.getFolder_id());
								document.setCreated(new Date());
								document.setIndex(0);
								document.setDocument_name(mpf.getOriginalFilename());
								document.setEncrypted_name(encryptedpdfname);
								document.setFd_stage_lid(lk.getLk_id()); 
								document.setFd_rec_status(1);
								document.setFd_edit_mode(0);
								document.setFd_reject_status("N");
								if(document.getDoc_type()==null || document.getDoc_type()!=3)
									document.setParent_id(null);
								
								document=documentService.save(document);
								
								// save in casefilestage
								DocumentFileStage dfs=new DocumentFileStage();
								dfs.setDfs_fd_mid(document.getId());
								dfs.setDfs_cr_by(user.getUm_id());
								dfs.setDfs_ip_address(ipaddress);
								dfs.setDfs_reject_status("N");
								dfs.setDfs_stage_date(new Date());
								dfs.setDfs_stage_lid(lkstage.getLk_id());
								documentFileStageService.save(dfs);
								
								response.setData(document);
							}else if(documenttemp.getDoc_type()==4){
								
								MediaFile mediafile=new MediaFile();
								mediafile.setMf_created(new Date());
								mediafile.setMf_created_by(user.getCr_by());
								mediafile.setMf_fd_mid(documenttemp.getParent_id());
								mediafile.setMf_folder_id(documenttemp.getFolder_id());
								mediafile.setMf_file_name(mpf.getOriginalFilename());
								mediafile.setMf_rec_status(0);
								mediafile.setMf_encrypted_name(encryptedpdfname);
								mediafile=mediafileService.save(mediafile);
								
								MediaFileStage mfs=new MediaFileStage();
								mfs.setMfs_ip_address(ipaddress);
								mfs.setMfs_mf_mid(mediafile.getMf_id());
								mfs.setMfs_reject_status("N");
								mfs.setMfs_cr_by(user.getCr_by());
								mfs.setMfs_cr_date(new Date());
								mediafileService.saveStage(mfs);
							}
								
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				
				}
		 }
    	//FileUpload itr =request.getFileUpload();
		 jsonData = globalfunction.convert_to_json(response);
		return jsonData; 
	}
	
	@RequestMapping(value = "/document/reupload",method = RequestMethod.POST)
    public @ResponseBody String reupload(MultipartHttpServletRequest request,HttpSession session) {    	
    	String jsonData="";
    	ActionResponse<Repository> response = new ActionResponse();
    	String ipaddress = request.getRemoteAddr();
    	
    	User user=new User();
		user=(User) session.getAttribute("USER");
		
		Document document=documentService.getDocumentById(Long.parseLong(request.getParameter("id"), 10));
    	
		Document documenttemp=document;
    	    	
    	documenttemp.setDoc_type(Integer.parseInt(request.getParameter("doc_type"), 10));
    	
    	MultipartFile mpf = null;
    	System.out.println("description: " + request.getParameter("description"));
    	Iterator<String> itr = request.getFileNames();
    	
    	Folder folder=new Folder(); 
    	
    	if(documenttemp.getParent_id()!=null){
			 Document parentdocument=documentService.getDocument(documenttemp.getParent_id());
			 folder=folderService.getFolderById(parentdocument.getFolder_id());
			 documenttemp.setFolder_id(parentdocument.getFolder_id());
		 }else{
			 folder=folderService.getFolderById(documenttemp.getFolder_id());
		 }
		 
    	response = documentValidation.doValidation(documenttemp,request);
		
    	Lookup lk=lookupService.getLookup("DMS_STAGE", "MAKER");
    	Lookup lkstage=lookupService.getLookup("DMS_STAGE", "UPLOAD");
    	if(response.getResponse()=="TRUE"){
			 String basePath="";
			 String folderPath="";
				
			 Repository repository=repositoryService.getRepository(documenttemp.getRep_id());
			 			 
			 String repBasepath=repository.getBasepath()+File.separator+repository.getName();
			 if(folder.getParent_id()!=null)
			 {
				 Long parent_id=folder.getParent_id();
				 while(parent_id !=null){
					 Folder fd=folderService.getFolderById(parent_id);
					 folderPath = fd.getFolder_name()+File.separator+folderPath;
					 parent_id   = fd.getParent_id();							
				    } 	
			 }
			basePath=repBasepath+File.separator+folderPath+folder.getFolder_name();
			File theDir = new File(basePath);
				
				while (itr.hasNext()) 
				{
					mpf = request.getFile(itr.next());
					String temppath=theDir + File.separator +mpf.getOriginalFilename();
					String ext = FilenameUtils.getExtension(temppath);
					String encryptedpdfname=globalfunction.md5encryption(mpf.getOriginalFilename())+"."+ext;
					//String encryptedpdfname=mpf.getOriginalFilename();
					String encryptedpdfpath=theDir + File.separator +encryptedpdfname;
					
					try {
						if(documenttemp.getDoc_type()==4){
							// media file
							FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(encryptedpdfpath));
							
						}else{
							
							FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(temppath));
							PdfReader reader = new PdfReader(temppath);	
							
							try {
								PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(encryptedpdfpath));
								stamper.setEncryption("stockholding".getBytes(), "allahabad".getBytes(),
								PdfWriter.ALLOW_ASSEMBLY, PdfWriter.ENCRYPTION_AES_128);
								stamper.close();
							} catch (DocumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							reader.close();
							File f=new File(temppath);
							f.delete();
						}

						if(documenttemp.getDoc_type()!=null){
							if(documenttemp.getDoc_type()==1 || documenttemp.getDoc_type()==3){
								document.setDoc_type(documenttemp.getDoc_type());
								document.setRep_id(documenttemp.getRep_id());
								document.setUpdated(new Date());
								document.setIndex(0);
								document.setDocument_name(mpf.getOriginalFilename());
								document.setEncrypted_name(encryptedpdfname);
								document.setFd_stage_lid(lk.getLk_id()); 
								document.setFd_rec_status(1);
								document.setFd_edit_mode(0);
								document.setFd_assign_to(null);
								document.setFd_reject_status("N");
								if(document.getDoc_type()==null || document.getDoc_type()!=3)
									document.setParent_id(null);
								
								document=documentService.save(document);
								
								// save in casefilestage
								DocumentFileStage dfs=new DocumentFileStage();
								dfs.setDfs_fd_mid(document.getId());
								dfs.setDfs_cr_by(user.getUm_id());
								dfs.setDfs_ip_address(ipaddress);
								dfs.setDfs_reject_status("N");
								dfs.setDfs_stage_date(new Date());
								dfs.setDfs_stage_lid(lkstage.getLk_id());
								dfs.setDfs_remark("Reuploaded Document");
								documentFileStageService.save(dfs);
								
								response.setData(document);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				}
		 }
    	//FileUpload itr =request.getFileUpload();
		 jsonData = globalfunction.convert_to_json(response);
		return jsonData; 
	}
	@RequestMapping(value = "/document/reuploadjudgement",method = RequestMethod.POST)
    public @ResponseBody String reuploadjudgement(MultipartHttpServletRequest request,HttpSession session) {    	
    	String jsonData="";
    	ActionResponse<Repository> response = new ActionResponse();
    	String ipaddress = request.getRemoteAddr();
    	
    	User user=new User();
		user=(User) session.getAttribute("USER");
		
		
    	
		JudgementFileDetail jfd=judgementService.getDocumentById(Long.parseLong(request.getParameter("jfd_id"), 10));
		Document document=documentService.getDocumentById(jfd.getJfd_fd_mid());
		Document documenttemp=document;
		documenttemp.setDoc_type(2);  	
    	MultipartFile mpf = null;
    	System.out.println("description: " + request.getParameter("description"));
    	Iterator<String> itr = request.getFileNames();
    	
    	Folder folder=new Folder(); 
    	
    	documenttemp.setFolder_id(documenttemp.getFolder_id());
    	folder=folderService.getFolderById(documenttemp.getFolder_id());
    	
    	response = documentValidation.doValidationOnUpdate(documenttemp,request);
		
    	Lookup lk=lookupService.getLookup("DMS_STAGE", "MAKER");
    	Lookup lkstage=lookupService.getLookup("DMS_STAGE", "UPLOAD");
    	if(response.getResponse()=="TRUE"){
			 String basePath="";
			 String folderPath="";
				
			 Repository repository=repositoryService.getRepository(documenttemp.getRep_id());
			 			 
			 String repBasepath=repository.getBasepath()+File.separator+repository.getName();
			 if(folder.getParent_id()!=null)
			 {
				 Long parent_id=folder.getParent_id();
				 while(parent_id !=null){
					 Folder fd=folderService.getFolderById(parent_id);
					 folderPath = fd.getFolder_name()+File.separator+folderPath;
					 parent_id   = fd.getParent_id();							
				    } 	
			 }
			basePath=repBasepath+File.separator+folderPath+folder.getFolder_name();
			File theDir = new File(basePath);
				
				while (itr.hasNext()) 
				{
					mpf = request.getFile(itr.next());
					String temppath=theDir + File.separator +mpf.getOriginalFilename();
					String ext = FilenameUtils.getExtension(temppath);
					String encryptedpdfname=globalfunction.md5encryption(mpf.getOriginalFilename())+"."+ext;
					String encryptedpdfpath=theDir + File.separator +encryptedpdfname;
					
					try {
							FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(temppath));
							PdfReader reader = new PdfReader(temppath);	
							
							try {
								PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(encryptedpdfpath));
								stamper.setEncryption("stockholding".getBytes(), "allahabad".getBytes(),
								PdfWriter.ALLOW_ASSEMBLY, PdfWriter.ENCRYPTION_AES_128);
								stamper.close();
							} catch (DocumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							reader.close();
							File f=new File(temppath);
							f.delete();
								
								// save record as judgement document
								jfd.setJfd_document_name(mpf.getOriginalFilename());
								jfd.setJfd_encrypted_name(encryptedpdfname);
								jfd.setJfd_stage_lid(lk.getLk_id());
								jfd.setJfd_rec_status(1);
								jfd.setJfd_reject_status("N");
								jfd.setJfd_assign_to(null);
								jfd=judgementService.save(jfd);
								
								JudgementFileStage jfs=new JudgementFileStage();
								jfs.setJfs_cr_by(user.getUm_id());
								jfs.setJfs_ip_address(ipaddress);
								jfs.setJfs_jfd_mid(jfd.getJfd_id());
								jfs.setJfs_reject_status("N");
								jfs.setJfs_stage_date(new Date());
								jfs.setJfs_stage_lid(lkstage.getLk_id());
								jfs.setJfs_remark("Reuploaded Document");
								judgementFileStageService.save(jfs);
								
								response.setData(jfd);
							
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				}
		 }
    	//FileUpload itr =request.getFileUpload();
		 jsonData = globalfunction.convert_to_json(response);
		return jsonData; 
	}

	@RequestMapping(value = "document/getcasefileusingcaseno", method = RequestMethod.POST)
	public @ResponseBody String getcasefileusingcaseno(@RequestBody Document document) throws ParseException {
		String jsonData="";
		List<Document> documents=new ArrayList<Document>();
		documents=documentService.getDocumentsByCaseNo(document);
		jsonData=globalfunction.convert_to_json(documents);
		return jsonData;
	}
	
	@RequestMapping(value = "document/searchdocument", method = RequestMethod.POST)
	public @ResponseBody String searchdocument(@RequestBody SearchForm searchForm) throws ParseException {
		String jsonData="";
		this.query="";
		this.doc_ids=new ArrayList<Long>();
		ActionResponse<ReportsView> response= new ActionResponse<ReportsView>();
		Lookup lk=lookupService.getLookup("DMS_STAGE", "VERIFIED");
		Long stagelid=lk.getLk_id();
		List<SearchCriteria> searchlist= searchForm.getSearchlist();
		String querystring="";
		
		if(null != searchlist && searchlist.size() > 0) {
			
			for(SearchCriteria searchcriteria:searchlist){
				if(searchcriteria.getType().equals("metafields"))
					addquery(searchcriteria);
				
				if(searchcriteria.getType().equals("searchincontent"))
				{
					try {
						searchInContent(searchcriteria);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		querystring+=this.query;
		System.out.println("Querystring="+querystring);
		System.out.println("Docids="+globalfunction.convert_to_json(this.doc_ids));
		
		
		List <ReportsView> reportsData=documentService.getcaseFilesBySearchquery(querystring,stagelid);
		
	    response.setResponse("TRUE");
	    response.setModelList(reportsData);
	    
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;
	}

	@RequestMapping(value = "document/getfreshdocumentlist", method = RequestMethod.POST)
	public @ResponseBody String getfreshdocumentlist(@RequestBody Document document,HttpSession session) throws ParseException {
		String jsonData="";
		this.childfolders.clear();
		ActionResponse<Document> response= new ActionResponse<Document>();
		
		User user=new User();
		user=(User) session.getAttribute("USER");
		List<UserRole> userrole=user.getUserroles();
		String rolename="";
		Long stagelid=0L;
		
		for(UserRole ur:userrole){
		 rolename=ur.getLk().getLk_longname();
		}
		
		if(rolename.equals("DMSMaker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "MAKER");
			stagelid=lk.getLk_id();
		}
		
		if(rolename.equals("DMSChecker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "CHECKER");
			stagelid=lk.getLk_id();
		}
		
		if(rolename.equals("DMSVerifier")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "VERIFICATION");
			stagelid=lk.getLk_id();
		}
		
		Folder f=folderService.getFolder(document.getFolder_id());
		
		List<Long> childfolderids=getallchildrens(f.getId());
		
		List<Long> assignfolderids=permissionService.getAssignedFolders(user.getUm_id());
		
		// search documents in folders to which user has access(search for unique folders in both arrays)
		childfolderids.retainAll(assignfolderids);
		Long fd_assign_to=user.getUm_id();
		List<Document> documents=documentService.getFreshDocumentsByStage(stagelid,childfolderids,fd_assign_to,document);
		
	    response.setResponse("TRUE");
	    response.setModelList(documents);
	    
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;
	}
	
	@RequestMapping(value = "document/getsaveddocumentlist", method = RequestMethod.POST)
	public @ResponseBody String getsaveddocument(@RequestBody Document document,HttpSession session) throws ParseException {
		String jsonData="";
		this.childfolders.clear();
		ActionResponse<Document> response= new ActionResponse<Document>();
		
		User user=new User();
		user=(User) session.getAttribute("USER");
		List<UserRole> userrole=user.getUserroles();
		String rolename="";
		Long stagelid=0L;
		
		for(UserRole ur:userrole){
		 rolename=ur.getLk().getLk_longname();
		}
		
		if(rolename.equals("DMSMaker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "MAKER");
			stagelid=lk.getLk_id();
		}
		
		if(rolename.equals("DMSChecker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "CHECKER");
			stagelid=lk.getLk_id();
		}
		
		if(rolename.equals("DMSVerifier")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "VERIFICATION");
			stagelid=lk.getLk_id();
		}
		
		Folder f=folderService.getFolder(document.getFolder_id());
		
		List<Long> childfolderids=getallchildrens(f.getId());
		List<Long> assignfolderids=permissionService.getAssignedFolders(user.getUm_id());
		
		// search documents in folders to which user has access(search for unique folders in both arrays)
		childfolderids.retainAll(assignfolderids);
		
		List<Document> documents=documentService.getSavedDocumentsByStage(stagelid,childfolderids,user.getUm_id());
		
	    response.setResponse("TRUE");
	    response.setModelList(documents);
	    
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;
	}
	
	@RequestMapping(value = "document/getrejecteddocumentlist", method = RequestMethod.POST)
	public @ResponseBody String getrejecteddocument(@RequestBody Document document,HttpSession session) throws ParseException {
		String jsonData="";
		this.childfolders.clear();
		ActionResponse<Document> response= new ActionResponse<Document>();
		
		User user=new User();
		user=(User) session.getAttribute("USER");
		List<UserRole> userrole=user.getUserroles();
		String rolename="";
		Long stagelid=0L;
		
		for(UserRole ur:userrole){
		 rolename=ur.getLk().getLk_longname();
		}
		if(rolename.equals("DMSUploader")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "UPLOAD");
			stagelid=lk.getLk_id();
		}
		if(rolename.equals("DMSMaker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "MAKER");
			stagelid=lk.getLk_id();
		} 
		
		if(rolename.equals("DMSChecker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "CHECKER");
			stagelid=lk.getLk_id();
		}
		
		if(rolename.equals("DMSVerifier")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "VERIFICATION");
			stagelid=lk.getLk_id();
		}
		
		Folder f=folderService.getFolder(document.getFolder_id());
		
		List<Long> childfolderids=getallchildrens(f.getId());
		
		List<Long> assignfolderids=permissionService.getAssignedFolders(user.getUm_id());
		
		// search documents in folders to which user has access(search for unique folders in both arrays)
		childfolderids.retainAll(assignfolderids);
		
		List<Document> documents=documentService.getRejectedDocumentsByStage(stagelid,childfolderids,user.getUm_id());
		
	    response.setResponse("TRUE");
	    response.setModelList(documents);
	    
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;
	}
	@RequestMapping(value = "document/getrejectedjudgementlist", method = RequestMethod.POST)
	public @ResponseBody String getrejectedjudgementlist(@RequestBody JudgementFileDetail jfd,HttpSession session) throws ParseException {
		String jsonData="";
		this.childfolders.clear();
		ActionResponse<JudgementFileDetail> response= new ActionResponse<JudgementFileDetail>();
		
		User user=new User();
		user=(User) session.getAttribute("USER");
		List<UserRole> userrole=user.getUserroles();
		String rolename="";
		Long stagelid=0L;
		
		for(UserRole ur:userrole){
		 rolename=ur.getLk().getLk_longname();
		}
		if(rolename.equals("DMSUploader")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "UPLOAD");
			stagelid=lk.getLk_id();
		}
		if(rolename.equals("DMSMaker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "MAKER");
			stagelid=lk.getLk_id();
		} 
		
		if(rolename.equals("DMSChecker")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "CHECKER");
			stagelid=lk.getLk_id();
		}
		
		if(rolename.equals("DMSVerifier")){
			Lookup lk=lookupService.getLookup("DMS_STAGE", "VERIFICATION");
			stagelid=lk.getLk_id();
		}
		
		Folder f=folderService.getFolder(jfd.getJfd_folder_id());
		
		List<Long> childfolderids=getallchildrens(f.getId());
		
		List<Long> assignfolderids=permissionService.getAssignedFolders(user.getUm_id());
		
		// search documents in folders to which user has access(search for unique folders in both arrays)
		childfolderids.retainAll(assignfolderids);
		
	//	List<Document> documents=documentService.getRejectedDocumentsByStage(stagelid,childfolderids,user.getUm_id());
		List<JudgementFileDetail> jfds=judgementService.getRejectedjudgementsByStage(stagelid,childfolderids,user.getUm_id());
	    response.setResponse("TRUE");
	    response.setModelList(jfds);
	    
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;
	}
	
	@RequestMapping(value = "document/getsubdocuments", method = RequestMethod.POST)
	public @ResponseBody String getsubdocuments(@RequestBody Document document,HttpSession session) throws ParseException {
		String jsonData="";
		this.childfolders.clear();
		if(document.getDoc_type()==2){	
		ActionResponse<JudgementFileDetail> response= new ActionResponse<JudgementFileDetail>();
		
		List<JudgementFileDetail> judgements=judgementService.getJudgementFilesByDocId(document.getId());
		
	    response.setResponse("TRUE");
	    response.setModelList(judgements);
	    
		jsonData=globalfunction.convert_to_json(response);
		}
		if(document.getDoc_type()==3){
		ActionResponse<Document> response= new ActionResponse<Document>();
		
		List<Document> documents=documentService.getByParentID(document.getId());
		
	    response.setResponse("TRUE");
	    response.setModelList(documents);
	    
		jsonData=globalfunction.convert_to_json(response);
		}
		if(document.getDoc_type()==4){
		ActionResponse<MediaFile> response= new ActionResponse<MediaFile>();
		
		List<MediaFile> mediafiles=mediafileService.getMediaFilesByDocId(document.getId());
		
	    response.setResponse("TRUE");
	    response.setModelList(mediafiles);
	    
		jsonData=globalfunction.convert_to_json(response);
		}
		return jsonData;
	}
	@RequestMapping(value="/document/deletejudgementfile",method=RequestMethod.POST)
	public @ResponseBody String deletejudgementfile(@RequestBody JudgementFileDetail judgementfile,HttpSession session,HttpServletRequest request){
		User user=new User();
		user=(User) session.getAttribute("USER");
			
		String result = null;	
		ActionResponse<JudgementFileDetail> response = new ActionResponse();
		response.setResponse("TRUE");
    	String ipaddress = request.getRemoteAddr();
    	judgementfile=judgementService.getDocumentById(judgementfile.getJfd_id());
    	judgementfile.setJfd_rec_status(0);
    	judgementfile = judgementService.save(judgementfile);
		
		result = globalfunction.convert_to_json(response);	
		
		return result;
	}
	
	@RequestMapping(value="/document/deletemediafile",method=RequestMethod.POST)
	public @ResponseBody String deletemediafile(@RequestBody MediaFile mediafile,HttpSession session,HttpServletRequest request){
		User user=new User();
		user=(User) session.getAttribute("USER");
			
		String result = null;	
		ActionResponse<MediaFile> response = new ActionResponse();
		response.setResponse("TRUE");
    	String ipaddress = request.getRemoteAddr();
    	mediafile=mediafileService.getDocumentById(mediafile.getMf_id());
    	mediafile.setMf_rec_status(0);
    	mediafile = mediafileService.save(mediafile);
		
		result = globalfunction.convert_to_json(response);	
		
		return result;
	}
	
	@RequestMapping(value="/document/deletereopencasefile",method=RequestMethod.POST)
	public @ResponseBody String deletereopencasefile(@RequestBody Document document,HttpSession session,HttpServletRequest request){
		User user=new User();
		user=(User) session.getAttribute("USER");
			
		String result = null;	
		ActionResponse<Document> response = new ActionResponse();
		response.setResponse("TRUE");
    	String ipaddress = request.getRemoteAddr();
    	document=documentService.getDocumentById(document.getId());
    	document.setFd_rec_status(0);
    	document = documentService.save(document);
		
		result = globalfunction.convert_to_json(response);	
		
		return result;
	}

	@RequestMapping(value = "document/deletequery", method = RequestMethod.POST)
	public @ResponseBody String deletequery(@RequestBody SearchQuery searchquery) {
		String jsonData="";
		List<SearchCriteria> searchlist=searchquery.getCriterias();
		if(null != searchlist && searchlist.size() > 0) {
			for(SearchCriteria searchcriteria:searchlist){
				searchqueryService.deleteCriteria(searchcriteria.getId());
			}
			
			searchqueryService.deleteQuery(searchquery.getId());
		}
		jsonData = "{\"status\":\"true\",\"msg\":\"Query Successfully Deleted\"}";
		return jsonData;
	}
	
	@RequestMapping(value = "document/savequery", method = RequestMethod.POST)
	public @ResponseBody String savequery(@RequestBody SearchQuery searchquery) {
		String jsonData="";
		this.query="";
		ActionResponse<SearchQuery> response= new ActionResponse<SearchQuery>();
		List<SearchCriteria> searchlist=searchquery.getCriterias();
		searchquery.setCriterias(null);
		String querystring="";
		if(null != searchlist && searchlist.size() > 0) {
			
			for(SearchCriteria searchcriteria:searchlist){
				if(!searchcriteria.getType().equals("tags"))
					addquery(searchcriteria);
			}
			querystring+="SELECT * FROM case_file_details WHERE fd_id IN (SELECT md_fd_mid FROM metadataview where ";
			querystring+=this.query+")";
			searchquery.setQuery(querystring);
			searchquery=searchqueryService.save(searchquery);
			List<SearchCriteria> tempsearchcriterias=new ArrayList<SearchCriteria>();
			
			for(SearchCriteria searchcriteria:searchlist){
				if(searchcriteria.getId()!=null)
					searchqueryService.deleteCriteria(searchcriteria.getId());
			
			searchcriteria.setSq_id(searchquery.getId());
			searchcriteria=searchqueryService.saveCriteria(searchcriteria);
			tempsearchcriterias.add(searchcriteria);
			}
			searchquery.setCriterias(tempsearchcriterias);
			
			System.out.println("Querystring="+querystring);
		}
		response.setResponse("TRUE");
	    response.setModelData(searchquery);
	    jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	private void addquery(SearchCriteria searchcriteria) {
		// TODO Auto-generated method stub
		String operator=searchcriteria.getOperator();
		
		if(this.query.equals(""))
		{
			operator="";
		}
		
		if(searchcriteria.getCriteria().equals("equalto")){
			this.query+=" "+operator+" md_fd_mid in (select md_fd_mid from MetaDataView where (md_mf_mid="+searchcriteria.getMetafield()+" and lower(value) = lower('"+searchcriteria.getSearchtext()+"')))";
		}
		else if(searchcriteria.getCriteria().equals("%like%")){
			this.query+=" "+operator+" md_fd_mid in (select md_fd_mid from MetaDataView where (md_mf_mid="+searchcriteria.getMetafield()+" and lower(value) like lower('%"+searchcriteria.getSearchtext()+"%')))";
		}
		else if(searchcriteria.getCriteria().equals("%like"))
		{
			this.query+=" "+operator+" md_fd_mid in (select md_fd_mid from MetaDataView where (md_mf_mid="+searchcriteria.getMetafield()+" and lower(value) like lower('%"+searchcriteria.getSearchtext()+"')))";
		}
		else if(searchcriteria.getCriteria().equals("like%"))
		{
			this.query+=" "+operator+" md_fd_mid in (select md_fd_mid from MetaDataView where (md_mf_mid="+searchcriteria.getMetafield()+" and lower(value) like lower('"+searchcriteria.getSearchtext()+"%')))";
		}
		else if(searchcriteria.getCriteria().equals("in"))
		{
			String searchtext=searchcriteria.getSearchtext();
			searchtext=searchtext.replace(",", "','");
			this.query+=" "+operator+" md_fd_mid in (select md_fd_mid from MetaDataView where (md_mf_mid="+searchcriteria.getMetafield()+" and value IN ('"+searchtext+"')))";
		}
		else if(searchcriteria.getCriteria().equals("notin"))
		{
			String searchtext=searchcriteria.getSearchtext();
			searchtext=searchtext.replace(",", "','");
			this.query+=" "+operator+" md_fd_mid in (select md_fd_mid from metadataview where (md_mf_mid="+searchcriteria.getMetafield()+" and value NOT IN ('"+searchtext+"')))";
		}
		else if(searchcriteria.getCriteria().equals("between"))
		{
			String searchtext=searchcriteria.getSearchtext();
			//searchtext=searchtext.replace(",", "','");
			String[] str=searchtext.split(",");
			String date1=str[0];
			String date2=str[1];
			this.query+=" "+operator+" md_fd_mid in (select md_fd_mid from metadataview where (md_mf_mid="+searchcriteria.getMetafield()+" and (to_date(value,'dd/mm/yyyy') Between to_date('"+date1+"','dd/mm/yyyy') AND to_date('"+date2+"','dd/mm/yyyy')) ))";
		}
		//return this.query;
	}
	
	@RequestMapping(value = "/document/downloadfile",method = RequestMethod.GET)	  
	 public void downloadfile(HttpServletRequest request ,HttpServletResponse response) throws IOException 
	 {
		 Long documentId=Long.parseLong(request.getParameter("id"));
		 Integer doc_type=Integer.parseInt(request.getParameter("doc_type"));
		 
		 if(doc_type==1){
			 Document document = documentService.getDocumentById(documentId);
			 Repository repository=repositoryService.getRepository(document.getRep_id());
				Folder folder=folderService.getFolderById(document.getFolder_id());
				String repBasepath=repository.getBasepath()+File.separator+repository.getName();
				String folderPath="";
				if(folder.getParent_id()!=null){
					Long parent_id=folder.getParent_id();
				    while(parent_id !=null){
				    	Folder fd=folderService.getFolderById(parent_id);
				    	folderPath = fd.getFolder_name()+File.separator+folderPath;
					    parent_id   = fd.getParent_id();							
				    } 	
				}
				
				String basePath=repBasepath+File.separator+folderPath+folder.getFolder_name();
				System.out.println("basepath="+basePath);
				String encryptedpdfname=globalfunction.md5encryption(document.getDocument_name())+".pdf";
				File source = new File(basePath+File.separator+encryptedpdfname);
				
				String uploadPath = context.getRealPath("");		
				File dest = new File(uploadPath+File.separator+"uploads"+File.separator+document.getDocument_name());
				
				try 
				{
					PdfReader reader = new PdfReader(basePath+File.separator+document.getEncrypted_name(), "World".getBytes());
					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(uploadPath+File.separator+"uploads"+File.separator+document.getDocument_name()));
					stamper.close();
					reader.close();
				} catch (Throwable e) {
						e.printStackTrace();
				}
				
				InputStream is=new FileInputStream (dest);
				
				response.setContentType("application/octet-stream");
				
				response.setHeader("Content-Disposition", "attachment; filename=\""+dest.getName()+"\"");
				
				OutputStream os= response.getOutputStream();
				
				byte[] buffer=new byte [4096];
				int count;
				while((count= is.read(buffer))!=1)
				{
					os.write(buffer,0,count);
				}
				os.flush();
				os.close();
				is.close();

		 }
		 if(doc_type==2)
		 {
			 JudgementFileDetail jfd=judgementService.getDocumentById(documentId);
			 Document document = documentService.getDocumentById(jfd.getJfd_fd_mid());
			 
			 Repository repository=repositoryService.getRepository(document.getRep_id());
				Folder folder=folderService.getFolderById(document.getFolder_id());
				String repBasepath=repository.getBasepath()+File.separator+repository.getName();
				String folderPath="";
				if(folder.getParent_id()!=null){
					Long parent_id=folder.getParent_id();
				    while(parent_id !=null){
				    	Folder fd=folderService.getFolderById(parent_id);
				    	folderPath = fd.getFolder_name()+File.separator+folderPath;
					    parent_id   = fd.getParent_id();							
				    } 	
				}
				
				String basePath=repBasepath+File.separator+folderPath+folder.getFolder_name();
				System.out.println("basepath="+basePath);
				String encryptedpdfname=globalfunction.md5encryption(jfd.getJfd_document_name())+".pdf";
				File source = new File(basePath+File.separator+encryptedpdfname);
				
				String uploadPath = context.getRealPath("");		
				File dest = new File(uploadPath+File.separator+"uploads"+File.separator+jfd.getJfd_document_name());
				
				try 
				{
					PdfReader reader = new PdfReader(basePath+File.separator+jfd.getJfd_encrypted_name(), "World".getBytes());
					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(uploadPath+File.separator+"uploads"+File.separator+jfd.getJfd_document_name()));
					stamper.close();
					reader.close();
				} catch (Throwable e) {
						e.printStackTrace();
				}
				
				InputStream is=new FileInputStream (dest);
				
				response.setContentType("application/octet-stream");
				
				response.setHeader("Content-Disposition", "attachment; filename=\""+dest.getName()+"\"");
				
				OutputStream os= response.getOutputStream();
				
				byte[] buffer=new byte [4096];
				int count;
				while((count= is.read(buffer))!=1)
				{
					os.write(buffer,0,count);
				}
				os.flush();
				os.close();
				is.close();


		 }
	}

	public List<Long> getallchildrens(Long folderId){
		this.childfolders.add(folderId);
		List<Folder> flist=folderService.getFoldersByParentId(folderId);
		
		if(!flist.isEmpty()){
			for(Folder f:flist)
			{
				this.getallchildrens(f.getId());
			}
		}
		return this.childfolders;
	}
}
