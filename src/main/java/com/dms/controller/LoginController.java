package com.dms.controller;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms.model.ActionResponse;
import com.dms.model.AdvocateEfiling;
import com.cms.model.Advocate;
import com.dms.model.LoginLog;
import com.dms.model.Lookup;
import com.dms.model.Notice;
import com.dms.model.ObjectMaster;
import com.dms.model.PetitionUploaded;
import com.dms.model.Register;
import com.dms.model.SmsLog;
import com.dms.model.User;
import com.dms.model.UserRole;
import com.dms.service.AdvocateService;
import com.dms.service.EcourtAddCaseService;
import com.dms.service.LookupService;
import com.dms.service.UserRoleService;
import com.dms.service.UserService;
import com.dms.utility.GlobalFunction;
import com.dms.validation.RegisterValidation;
import com.dms.validation.Validator;


@Controller
public class LoginController extends HttpServlet {

	@Autowired 
	private UserService userService;
	
	@Autowired
    private ServletContext context;
	
	@Autowired 
	private AdvocateService advocateService;
	
	@Autowired 
	private UserRoleService urService;
	
	@Autowired
	private EcourtAddCaseService ecourtAddCaseService;
	
	@Autowired 
	private LookupService lookupService;
	
	@Autowired  
	private MessageSource messageSource;
	
	@Autowired
	private RegisterValidation registerValidation;
	@Autowired
	private UserRoleService userRoleService;
	GlobalFunction cm;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    private String filePath;
    public void init() throws ServletException {
        this.filePath = "/pics/latest";
    }
	public LoginController()
	{
		userService = new UserService();
		cm = new GlobalFunction();
	}
	
	
/*	@RequestMapping(value = "login")
	public String ecourt() {	    	
		
		return "/ecourt/login";
	}*/
	
	
	
	
	@RequestMapping(value = "/dms/login")
	public String login() {	    	
		
		return "/dms/login";
	}
	
	@RequestMapping(value = "/validateNotice")
	public String validateNotice(HttpServletRequest req) {
		
		String ntId=req.getParameter("id");
		String otpTmpId=null;
		Notice nt=userService.getNoticeById(Long.parseLong(ntId));
		
		InetAddress ip;
		 String hostname;
		 String  extraLko="";
		 
	        try {
	            ip = InetAddress.getLocalHost();
	            hostname = ip.getHostAddress();
	            System.out.println("Your current IP address : " + ip);
	            System.out.println("Your current Hostname : " + hostname);
	            
	            if(hostname.equals("172.16.0.6")) {
	            	otpTmpId ="1107168931320671929";
	            }
	            else if(hostname.equals("127.0.0.1")) {
	            	otpTmpId ="1107168931320671929";
	            	 extraLko="-Lko. Bench ";
		            }
	            else {
	            	System.out.println("In Local");
	            	otpTmpId ="1107168931320671929";
	            }
	 
	        } catch (UnknownHostException e) {
	 
	            e.printStackTrace();
	        }
			
			
			 Integer otp=cm.generateOTP();
			 nt.setNt_otp(otp);
			 nt= userService.saveNotice(nt);
			 Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
			 String sms_url=urlLookup.getLk_longname();
			 String mob_no=nt.getNt_res_adv_mobile();
			 String smstext=" OTP for viewing the complete file is "+otp+" which is valid for 5 minutes. Please do not share the OTP with anyone.-AHC";
			
			 String otpresponse=cm.sendSMS(sms_url, mob_no, smstext,otpTmpId);
			 String otp1=cm.sendBSNLSMS(sms_url, mob_no, smstext,otpTmpId);
			// String otpresponse=cm.sendSMS(sms_url, mob_no, smstext);
			// String otpresponse="1";			
				
		
		String res=null;
		if(nt!=null) {		
			res= "/ecourt/noticeOtp";
		}
		else {
			
		}
		return res;
	}
	
	
	@RequestMapping(value = "/dms/generateCaptcha", method = RequestMethod.GET)
	@ResponseBody
	public String generateCaptcha() {
		String jsonData="";
		ActionResponse<String> response=new ActionResponse<String>();
		
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    
	    String rand=String.format("%06d", number);

	    response.setResponse("TRUE");
		response.setModelData(rand);
	    // this will convert any number sequence into 6 character.
	   
			jsonData=cm.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/user/validateAdvocate", method = {RequestMethod.POST})
	public @ResponseBody String validateAdvocate(@RequestBody Register register) {
		String jsonData="";
		ActionResponse<Advocate> response=new ActionResponse<Advocate>();
		ActionResponse<AdvocateEfiling> response1=new ActionResponse<AdvocateEfiling>();
			//check if aor already exist in advocates
				// if false return advocate does not exist
				// if true return details of advocate
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date current=null;Date date3=null;Date date2=null;
		

		try {
			current=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
		/*	 date3 = sdf.parse("2020-07-14 00:00:45");
	         date2 = sdf.parse("2020-07-15 00:00:01");*/
			
			 date3 = sdf.parse(lookupService.getLookUpObject("CLOSING_START").getLk_longname());
		        date2 = sdf.parse(lookupService.getLookUpObject("CLOSING_END").getLk_longname());
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
      /*  if(current.after(date3) && current.before(date2)) {*/
		  if(false) {
        	response.setResponse("FALSE");
        	response.setData("E-Filing Closed According To High Court Order");
        }
        else {
		
		
			Advocate adv=advocateService.getByRollNo(register);
			com.dms.model.AdvocateEfiling adv1=userService.getAdvocateByRollNo(register.getRollNo());
			User u=userService.getUserByUsername(register.getRollNo());
			if(u.getUm_id()!=null){
				Map<String, List> error = new HashMap<String, List>();
				List<String> errorList = new ArrayList<String>();
				Validator validation=new Validator();
				error=validation.getError();
				errorList.add("Record already exist");
				error.put("advocate", errorList);
				response.setDataMapLists(error);
				response.setResponse("FALSE");
				response.setData("Record already exist");
			}else
			{
				if(adv.getAdv_id()==null)
				{
					if(adv1.getRollNo().equals("A/A9999/2001")) {
						response1.setResponse("TRUE");
						response1.setModelData(adv1);
						jsonData=cm.convert_to_json(response1);
						return jsonData;
					}
					else {
					Map<String, List> error = new HashMap<String, List>();
					List<String> errorList = new ArrayList<String>();
					Validator validation=new Validator();
					error=validation.getError();
					errorList.add("Record does not exist");
					error.put("advocate", errorList);
	
					response.setDataMapLists(error);
					response.setResponse("FALSE");
					response.setData("Record does not exist");
					}
				}else{
					response.setResponse("TRUE");
					response.setModelData(adv);
				}
			}
        }
			jsonData=cm.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/user/searchAdvocate", method = {RequestMethod.POST})
	public @ResponseBody String searchAdvocate(@RequestBody String aor) {
		String jsonData="";
		ActionResponse<Advocate> response=new ActionResponse<Advocate>();
			//check if aor already exist in advocates
				// if false return advocate does not exist
				// if true return details of advocate
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date current=null;Date date3=null;Date date2=null;
		

		try {
			current=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
		/*	 date3 = sdf.parse("2020-07-14 00:00:45");
	         date2 = sdf.parse("2020-07-15 00:00:01");*/
			
			 date3 = sdf.parse(lookupService.getLookUpObject("CLOSING_START").getLk_longname());
		        date2 = sdf.parse(lookupService.getLookUpObject("CLOSING_END").getLk_longname());
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
        if(current.after(date3) && current.before(date2)) {
        	response.setResponse("FALSE");
        	response.setData("E-Filing Closed According To High Court Order");
        }
        else {
		
		
			Advocate adv=advocateService.getAdvocateByRollNo(aor);
			
				if(adv.getAdv_id()==null)
				{
					Map<String, List> error = new HashMap<String, List>();
					List<String> errorList = new ArrayList<String>();
					Validator validation=new Validator();
					error=validation.getError();
					errorList.add("Record does not exist");
					error.put("advocate", errorList);
	
					response.setDataMapLists(error);
					response.setResponse("FALSE");
					response.setData("Record does not exist");
				}else{
					adv.setMobile("7309752139");
					adv.setEmail("anoopsrivastava@allahabadhighcourt.in");
					response.setResponse("TRUE");
					response.setModelData(adv);
				}
			
        }
			jsonData=cm.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/user/register", method = {RequestMethod.POST})
	public @ResponseBody String register(@RequestBody Register register,HttpServletRequest request) {	 
		String jsonData="";
		ActionResponse<Register> response=new ActionResponse<Register>();
		response = registerValidation.doValidation(register);
		 if(response.getResponse()=="TRUE"){
			 User user=new User();
			 user.setPassword(cm.md5encryption(register.getPassword()));
			 user.setUm_fullname(register.getName());
			 user.setUsername(register.getUsername());
			 user.setUm_email(register.getEmail());
			 user.setUm_mobile(register.getMobile());
			 user.setUm_gender(register.getGender());
			 user.setUm_adhar(register.getAdhar());
			 user.setCr_by(1L);
			 if(register.getType().equals("aor")) {
			 user.setUm_rec_status(1);
			 }
			 user.setCr_date(new Date());
			 String ipaddress = request.getRemoteAddr();
			 user.setUm_ipaddress(ipaddress);
			 user = userService.save(user);
			 if(user.getUm_id()!=null){
				 String role="";
				 if(register.getType().equals("aor"))
					 role="Advocate";
				 if(register.getType().equals("inperson"))
					 role="InPerson";
				 
				 Lookup lkRole=lookupService.getAllByLongname(role);
					 
				UserRole ur = new UserRole();			
				
				ur.setUr_um_mid(user.getUm_id());
				ur.setUr_role_id(lkRole.getLk_id());
				ur.setUr_cr_date(new Date());
				ur.setUr_rec_status(1);
				
				urService.save(ur);
			 }
			 response.setData("Your account has been created, please click on below link to Sign In.");
		 }
		// check type of user
			// if true check in users table
					//if true then return user already exist
					//if false then return details of advocate
		// if inperson check if same username exist in db
		
		jsonData=cm.convert_to_json(response);	
		return jsonData;
	}
	
	@RequestMapping(value = "/dms/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {	 
		session.removeAttribute("USER");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/noticelogin", method = RequestMethod.POST)
	@ResponseBody
	public String noticelogin(@RequestBody Notice nt,HttpSession session) throws ParseException 
	{
		
		// System.out.println("call user login");
		String result = " ";
		
		//UserRole ur=userRoleService.getByUserRole("Scrutiny");
		//System.out.println("User Role"+ur.getUser().getUsername());
		
		nt = userService.validateNotice(nt.getNt_id(),nt.getNt_otp());
		ActionResponse<Notice> response = new ActionResponse();
		//ActionResponse<ObjectMaster> response2 = new ActionResponse();
		
		
		 
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    Date current=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
		    Date date3 = sdf.parse(lookupService.getLookUpObject("CLOSING_START").getLk_longname());
	        Date date2 = sdf.parse(lookupService.getLookUpObject("CLOSING_END").getLk_longname());
		    
	       /* Date date3 = sdf.parse("2020-07-14 00:00:45");
	        Date date2 = sdf.parse("2020-07-15 00:00:01");*/
	        if(current.after(date3) && current.before(date2)) {
	        	response.setResponse("FALSE");
				response.setData("E-Filing Closed According To High Court Order");
	        }
	        else {
	
		if (nt != null)
		{
			
				List <PetitionUploaded> ptFile=ecourtAddCaseService.getUploadedPetition(nt.getNt_rcd_mid());
				
				String ntFile=ptFile.get(0).getPu_document_name();
				
				String path=lookupService.getLookUpObject("DRAFT_PATH").getLk_longname()+File.separator+ntFile;
				
				File source=new File(path);
				
				String destPath=context.getRealPath("")+File.separator+"uploads"+File.separator+nt.getNt_id()+".pdf";
				
				nt.setDoc_name(nt.getNt_id()+".pdf");
				
				File dest=new File(destPath);
				
				try {
					
					FileUtils.copyFile(source, dest);
					response.setResponse("TRUE");
					response.setModelData(nt);
					
				} catch (Exception e) {
					response.setResponse("FALSE");
				}
				
				
				
				
				
				
				
			
				response.setData("User logged in successfully");
//				
//				 logger.info("User logged in successfully"+user.getUm_fullname());
//				 logger.error("This is an error log entry"+user.getUm_fullname());

		}
		else
		{
			response.setResponse("FALSE");
			response.setData("OTP is invalid");
		}
	        }

		result = cm.convert_to_json(response);
		// System.out.println(result);
		return result;
		
	}
	

	
	@RequestMapping(value = "/dms/userlogin", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody String userlogin(@RequestBody User user, HttpSession session,HttpServletRequest request) throws ParseException {
		// System.out.println("call user login");
		String result = " ";
		
		//UserRole ur=userRoleService.getByUserRole("Scrutiny");
		//System.out.println("User Role"+ur.getUser().getUsername());
		
		user = userService.validateLogin(user.getUsername(), user.getPassword());
		ActionResponse<User> response = new ActionResponse();
		ActionResponse<ObjectMaster> response2 = new ActionResponse();
		
		
		 
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		    Date current=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
		    Date date3 = sdf.parse(lookupService.getLookUpObject("CLOSING_START").getLk_longname());
	        Date date2 = sdf.parse(lookupService.getLookUpObject("CLOSING_END").getLk_longname());
	        
	        String bef=sdf1.format(new Date())+" 11:00:00";
	        String after=sdf1.format(new Date())+" 15:00:00";
	        
	        Date dateBef = sdf.parse(bef);
	        Date dateAft = sdf.parse(after);
		    
	       /* Date date3 = sdf.parse("2020-07-14 00:00:45");
	        Date date2 = sdf.parse("2020-07-15 00:00:01");*/
	        if(current.after(date3) && current.before(date2)) {
	        	response.setResponse("FALSE");
				response.setData("E-Filing Is Closed As Per High Court Order");
	        }/*
	        else if(current.after(dateAft) || current.before(dateBef)) {
	        	response.setResponse("FALSE");
				response.setData("E-Filing Can Be Done Between 10 Am To 4 PM, As Per Guidelines of Allahabad High Court");
	        }*/
	        else {
	
		if (user.getUm_id() != null)
		{
			
				Date date1=new Date();
				
				String ipaddress = request.getRemoteAddr();
				
				//String date = cm.dateToString(date1,"dd-MM-yyyy");
				LoginLog loginLog=new LoginLog();
				 
				loginLog.setLl_login_time(date1);
				loginLog.setLl_user_mid(user.getUm_id());
				loginLog.setLl_ip_address(ipaddress);
				
				userService.saveLog(loginLog);
				
				user = userService.getByuserid(user.getUm_id());

				List<ObjectMaster> ob_list = userService.getUserObjects(user.getUm_id());

				session.setAttribute("USER", user);
				response.setResponse("TRUE");
				response.setModelData(user);
				response2.setModelList(ob_list);
				session.setAttribute("ob_list", ob_list);
			
				response.setData("User logged in successfully");
//				
//				 logger.info("User logged in successfully"+user.getUm_fullname());
//				 logger.error("This is an error log entry"+user.getUm_fullname());

		}
		else
		{
			response.setResponse("FALSE");
			response.setData("Username or Password is invalid");
		}
	        }

		result = cm.convert_to_json(response);
		// System.out.println(result);
		return result;
	}
	
	
	@RequestMapping(value = "/views/landingPage")
	public String index(Model model, HttpSession session) {
		User user = new User();
		return "/views/dashboard";
	}
	
	@RequestMapping(value = "/views/securityQuestion")
	public String index2(Model model, HttpSession session) {
	//	User user = new User();

		return "/views/securityQuestion";
	}
	@RequestMapping(value = "/dms/userFagPass", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })	
	public @ResponseBody String userFagPass(@RequestBody User user,HttpSession session) {
		//System.out.println("call user login");
		String result = " ";

		user = userService.validateUser(user.getUsername());	
		ActionResponse <User> response = new ActionResponse();		
		ActionResponse <ObjectMaster> response2 = new ActionResponse();
		if(user.getUm_id() != null){	
			user = userService.getByuserid(user.getUm_id());
			
		 List<ObjectMaster>	ob_list = userService.getUserObjects(user.getUm_id());
			
			session.setAttribute("USER", user);	
			response.setResponse("TRUE");
			response.setModelData(user);
			response2.setModelList(ob_list);
			session.setAttribute("ob_list", ob_list);
			response.setData("User logged in successfully");
		}else{
			response.setResponse("FALSE");
			response.setData("Please Enter correct Username");
		}		
		result = cm.convert_to_json(response);
		//System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/views/forgetPassword")
	public String index3(Model model, HttpSession session) {
	//	User user = new User();

		return "/views/forgetPassword";
	}
	
	
	//ecourt
	@RequestMapping(value="/ecourt/login")
	public String ecourtlogin() {	    	
		
		return "/ecourt/login";
	}

	
	@RequestMapping(value="/ecourt/createaccountinperson")
	public String ecourtcreateinpersonaccount() {	    	
		
		return "/ecourt/createaccountinperson";
	}
	
	@RequestMapping(value = "/genearteOTP", method = RequestMethod.POST)
	@ResponseBody
	public String genearteOTP(@RequestBody User user1,HttpServletRequest request,HttpSession session) 
	{
		String result = " ";
	User user = userService.validateUser(user1.getUsername());	
	String otpTmpId =" ";
		ActionResponse <User> response = new ActionResponse<User>();
		ActionResponse <com.dms.model.AdvocateEfiling> response1 = new ActionResponse<com.dms.model.AdvocateEfiling>();
		com.dms.model.AdvocateEfiling adv=userService.getAdvocateByRollNo(user1.getUsername());
		Advocate adv1=advocateService.getAdvocateByRollNo(user1.getUsername());
		if(adv.getAdv_id()==null) {
			
			if(adv1 !=null) {
			adv.setAdv_id(adv1.getAdv_id());
			adv.setRollNo(adv1.getRollNo());
			adv.setRollYear(adv.getRollYear());
			adv.setAddress1(adv1.getAddress1());
			adv.setFhName(adv1.getFhName());
			adv.setEmail(adv1.getEmail());
			adv.setEnrollCouncil(adv1.getEnrollCouncil());
			adv.setEnrollNo(adv1.getEnrollNo());
			adv.setEnrollYear(adv1.getEnrollYear());
			adv.setMobile(adv1.getMobile());
			adv=userService.saveAdvocate(adv);
			}
		}
		/*if(user!=null && user.getUserroles().get(0).getUr_role_id()!=1000008) {*/
	/*	if(adv1!=null && !adv1.getMobile().equals(adv.getMobile())) {
			adv.setMobile(adv1.getMobile());
			adv=userService.saveAdvocate(adv);
		}*/
		/*}*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date current=null;Date date3=null;Date date2=null;

		
		InetAddress ip;
		 String hostname;
		 String  extraLko="";
		 
	        try {
	            ip = InetAddress.getLocalHost();
	            hostname = ip.getHostAddress();
	            System.out.println("Your current IP address : " + ip);
	            System.out.println("Your current Hostname : " + hostname);
	            
	            if(hostname.equals("172.16.0.6")) {
	            	otpTmpId ="1107160736261112296";
	            }
	            else if(hostname.equals("127.0.0.1")) {
	            	/*otpTmpId ="1107160793982323688";
	            	 extraLko="-Lko. Bench ";*/
	            	otpTmpId ="1107160736261112296";
		            }
	            else {
	            	System.out.println("In Local");
	            	otpTmpId ="1107160736261112296";
	            	/* extraLko="-Lko. Bench ";*/
	            }
	 
	        } catch (UnknownHostException e) {
	 
	            e.printStackTrace();
	        }

		try {
			current=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
			 /*date3 = sdf.parse("2020-07-14 00:00:45");
	         date2 = sdf.parse("2020-07-15 00:00:01");*/
			
			date3 = sdf.parse(lookupService.getLookUpObject("CLOSING_START").getLk_longname());
		        date2 = sdf.parse(lookupService.getLookUpObject("CLOSING_END").getLk_longname());
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
        if(current.after(date3) && current.before(date2)) {
        	response.setResponse("FALSE");
        	response.setData("E-Filing Closed According To High Court Order");
        	result = cm.convert_to_json(response);
        }
        else {
		
		
		if(user.getUm_id() != null)
		{
			 Integer otp=cm.generateOTP();
			 user.setUm_otp(otp);
			 user=userService.save(user);
			 Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
			 String sms_url=urlLookup.getLk_longname();
			 String mob_no=user.getUm_mobile();
			 String smstext="Your OTP is "+otp+" for eFiling password change. Don't disclose to anyone"+extraLko;
			 String otpresponse=cm.sendBSNLSMS(sms_url, mob_no, smstext+" - Allahabad High Court",otpTmpId);
			 String otpresponse1=cm.sendSMS(sms_url, mob_no, smstext,otpTmpId);
			// String otpresponse="1";
			 if(otpresponse1.equals("1") || otpresponse.equals("1"))
			 {
				 user.setUm_otp(null);
				 response.setModelData(user);
				 response.setData("OTP sent successfully");
				 response.setResponse("TRUE");
				 
				 SmsLog smslog = new SmsLog();
				 smslog.setSl_mobile_no(mob_no);
				 smslog.setSl_um_mid(user.getUm_id());
				 smslog.setSl_text(smstext+"  BSNL");
				 smslog.setSl_cr_date(new Date());
				 smslog.setSl_status(1);
				 smslog.setSl_ip_address(request.getRemoteAddr());
				 userService.saveSMSlog(smslog);
				 
			 }else{
					response.setResponse("FALSE");
					response.setData("Unable to send OTP, please try again");
				}
					
		}
		else if(adv.getAdv_id() != null) {

			 Integer otp=cm.generateOTP();
			 adv.setOtp(otp);
			 adv=userService.saveAdvocate(adv);
			 Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
			 String sms_url=urlLookup.getLk_longname();
			 String mob_no=adv.getMobile();
			 String smstext="Your OTP is "+otp+" for eFiling password change. Don't disclose to anyone";
			 //String otpresponse=cm.sendSMS(sms_url, mob_no, smstext);
			 
			 String otpresponse=cm.sendSMS(sms_url, mob_no, smstext,otpTmpId);
			 String otpresponse1=cm.sendBSNLSMS(sms_url, mob_no, smstext+" - Allahabad High Court",otpTmpId);
			// String otpresponse="1";
			 if(otpresponse.equals("1") || otpresponse1.equals("1"))
			 {
				 user.setUm_otp(null);
				 response1.setModelData(adv);
				 response1.setData("OTP sent successfully");
				 response1.setResponse("TRUE");
				 
				 SmsLog smslog = new SmsLog();
				 smslog.setSl_mobile_no(mob_no);
				 smslog.setSl_um_mid(user.getUm_id());
				 smslog.setSl_text(smstext);
				 smslog.setSl_cr_date(new Date());
				 smslog.setSl_status(1);
				 smslog.setSl_ip_address(request.getRemoteAddr());
				 userService.saveSMSlog(smslog);
				 
			 }else{
					response1.setResponse("FALSE");
					response1.setData("Unable to send OTP, please try again");
				}
					
		
		}
		else{
			response.setResponse("FALSE");
			response.setData("Please Enter correct Username");
		}		
		if(user.getUm_id() != null) {
		result = cm.convert_to_json(response);
		}
		else if(adv.getAdv_id()!=null) {
			result = cm.convert_to_json(response1);
		}
		else {
			result = cm.convert_to_json(response);
		}
        }

		return result;
		
	}
	
	@RequestMapping(value = "/validateOtp", method = RequestMethod.POST)
	@ResponseBody
	public String validateOtp(@RequestBody User u,HttpSession session) 
	{
		String result = " ";
		User user = userService.validateUser(u.getUsername());	
		AdvocateEfiling adv=userService.getAdvocateByRollNo(u.getUsername());
		ActionResponse <User> response = new ActionResponse();		
		if(user.getUm_id() != null)
		{
			 if(u.getUm_otp().equals(user.getUm_otp()))
			 {
				 user.setUm_rec_status(1);
				 user=userService.save(user);
				 response.setData(user);
				 response.setResponse("TRUE");
				 
				/* String pass=cm.generatePassword();
				 Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
				 String sms_url=urlLookup.getLk_longname();
				 String mob_no=user.getUm_mobile();
				 String smstext="Dear "+user.getUm_fullname()+" your new Password for eFiling is "+pass+" Please do not share this password with anybody.";
				 
				 String pwd = cm.md5encryption(pass);
				 user.setPassword(pwd);
				 user.setUm_otp(null);
				 
				 user=userService.save(user);
				 cm.sendSMS(sms_url, mob_no, smstext);
				 response.setResponse("TRUE");
				 response.setData("Please login with password received on your mobile");*/
			 }			
			 else
			 {
				response.setResponse("FALSE");
				response.setData("Please Enter correct OTP");
			 }		
			
		}
		 else if(adv!=null) {

			 if(u.getUm_otp().equals(adv.getOtp()))
			 {
				 response.setData(adv);
				 response.setResponse("TRUE");
				 
				/* String pass=cm.generatePassword();
				 Lookup urlLookup=lookupService.getLookUpObject("SMS_URL");
				 String sms_url=urlLookup.getLk_longname();
				 String mob_no=user.getUm_mobile();
				 String smstext="Dear "+user.getUm_fullname()+" your new Password for eFiling is "+pass+" Please do not share this password with anybody.";
				 
				 String pwd = cm.md5encryption(pass);
				 user.setPassword(pwd);
				 user.setUm_otp(null);
				 
				 user=userService.save(user);
				 cm.sendSMS(sms_url, mob_no, smstext);
				 response.setResponse("TRUE");
				 response.setData("Please login with password received on your mobile");*/
			 }			
			 else
			 {
				response.setResponse("FALSE");
				response.setData("Please Enter correct OTP");
			 }		
			
		
			 
		 }
		else{
			response.setResponse("FALSE");
			response.setData("Error");
		}		
		result = cm.convert_to_json(response);
		//System.out.println(result);
		return result;
		
	}
	
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public @ResponseBody String updatepassword(@RequestBody User u,HttpServletRequest request, HttpSession session) {	 
		String jsonData="";
		User user = userService.validateUser(u.getUsername());	
		ActionResponse <User> response = new ActionResponse<User>();		
		if(user.getUm_id() != null )
		{
			 if(u.getUm_otp().equals(user.getUm_otp()) && u.getNewpassword().equals(u.getConfirmpassword()))
			 {
				 String ipaddress = request.getRemoteAddr();
				 user.setPassword(cm.md5encryption(u.getNewpassword()));
				 user.setMod_date(new Date());
				 user.setUm_ipaddress(ipaddress);
				 user = userService.save(user);
				 response.setData(user);
				 response.setResponse("TRUE");
			 }
			 else
			 {
				response.setResponse("FALSE");
				response.setData("Please Enter correct OTP");
			 }		
		}
		else{
			response.setResponse("FALSE");
			response.setData("Error");
		}
		jsonData=cm.convert_to_json(response);	
		return jsonData;
	}
}
