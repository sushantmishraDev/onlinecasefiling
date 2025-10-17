package com.dms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dms.model.User;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import com.dms.service.LookupService;

public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired 
	private LookupService lookupService;
	
	private LoginController login;

	//private static final Logger logger = LoggerFactory.getLogger(RequestProcessingTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		//logger.info("Request URL::" + request.getRequestURL().toString()+ ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		
		
		
		System.out.println("Pre-handle"+request.getContextPath());
		User user=new User();
		user=(User) request.getSession().getAttribute("USER");
		String url=request.getRequestURI();
		System.out.println("URL="+url);
		
		user=(User) request.getSession().getAttribute("USER");
		if(user!=null || url.equals(request.getContextPath()+"/dms/userlogin") || url.equals(request.getContextPath()+"/dms/generateCaptcha") 
				|| url.equals(request.getContextPath()+"/user/register") || url.equals(request.getContextPath()+"/user/validateAdvocate") 
				|| url.equals(request.getContextPath()+"/genearteOTP") || url.equals(request.getContextPath()+"/validateOtp")  || url.equals(request.getContextPath()+"/noticelogin")
				|| url.equals(request.getContextPath()+"/updatepassword") || url.equals(request.getContextPath()+"/validateNotice"))
		{
			if(url.equals(request.getContextPath()+"/dms/userlogin")  || url.equals(request.getContextPath()+"/user/register") 
					|| url.equals(request.getContextPath()+"/user/validateAdvocate") || url.equals(request.getContextPath()+"/genearteOTP") || url.equals(request.getContextPath()+"/noticelogin")
					|| url.equals(request.getContextPath()+"/validateOtp") || url.equals(request.getContextPath()+"/updatepassword") || url.equals(request.getContextPath()+"/validateNotice")
					|| url.equals(request.getContextPath()+"/dms/generateCaptcha"))
			{
		
				return true;
			
			
		
		}
		else {
			
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

			    Date current=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
			    Date date3 = sdf.parse(lookupService.getLookUpObject("CLOSING_START").getLk_longname());
		        Date date2 = sdf.parse(lookupService.getLookUpObject("CLOSING_END").getLk_longname());
		        System.out.println(sdf1.format(new Date()));
		        
		        String bef=sdf1.format(new Date())+" 11:00:00";
		        String after=sdf1.format(new Date())+" 15:00:00";
		        
		        Date dateBef = sdf.parse(bef);
		        Date dateAft = sdf.parse(after);
			    
		       /* Date date3 = sdf.parse("2020-07-14 00:00:45");
		        Date date2 = sdf.parse("2020-07-15 00:00:01");*/
		        if(current.after(date3) && current.before(date2)) {	
		        	request.getSession().removeAttribute("USER");
		        	response.sendRedirect(request.getContextPath());
		        	return false;
		        }/*
		        else if(current.after(dateAft) || current.before(dateBef)) {
		        	request.getSession().removeAttribute("USER");
		        	response.sendRedirect(request.getContextPath());
		        	return false;
		        }*/
		        else {
		        return true;
		        }
		        
			
		}
			}else{
			
			response.sendRedirect(request.getContextPath());
			return false;
		      
		}
			}
		
		
		//if returned false, we need to make sure 'response' is sent
		//return true;
	

	/*@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		//we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	}
*/
}