package com.dms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dms.model.User;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {

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
		if(user!=null || url.equals(request.getContextPath()+"/dms/userlogin")  || url.equals(request.getContextPath()+"/user/register") || url.equals(request.getContextPath()+"/user/validateAdvocate") || url.equals(request.getContextPath()+"/genearteOTP") || url.equals(request.getContextPath()+"/validateOtp"))
		{
			return true;
		}else{
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		
		//if returned false, we need to make sure 'response' is sent
		//return true;
	}

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