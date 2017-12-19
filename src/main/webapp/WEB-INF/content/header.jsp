<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dms.model.ObjectMaster"%>
<%@ page import="com.dms.model.User"%>	
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="EDMSApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EDMS</title>

 <%@ include file="style.jsp"%>
<%@ include file="script.jsp"%>
</head>
<body>

<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");

%>
<!-- begin #header -->
		 <div id="header" class="header navbar navbar-default navbar-fixed-top">
		 <!-- <div id="header" ng-controller="editProfileCtrl" class="header navbar navbar-default navbar-fixed-top"> --> 
			<!-- begin container-fluid -->
			<div class="container-fluid">
			<div class="row">
				<!-- begin mobile sidebar expand / collapse button -->
				
				<div class="col-md-1">
				<div class="row">
                    <!-- <img  class="navbar-brand" style="width: 150px; height: 65px;"  alt="" src="/dms/assets/img/highcourt1.png"></img> -->
                    <img  class="navbar-brand" style="width: 170px; height: 74px; margin-left: -12px; padding: 0px 26px;"  alt="" src="/dms/assets/img/highcourt1.png"></img>
                    
                </div>
                </div>
				
				<%-- <div class="col-md-1" style="padding: 19px 0 0 16px;">
				<a class="" href="${pageContext.request.contextPath}/views/landingPage"  style="padding: 0px 18px;">  <span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home </a>
				</div> --%>
				
				<!-- begin header navigation right -->
				
				<div class="col-md-2">
				<ul class="nav navbar-nav navbar-right">
					<!-- <li>
						<form class="navbar-form full-width">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Enter keyword">
								<button type="submit" class="btn btn-search"><i class="fa fa-search"></i></button>
							</div>
						</form>
					</li> -->
					
					<li class="dropdown navbar-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${pageContext.request.contextPath}/assets/img/user-13.jpg" alt=""> 
							<span class="hidden-xs"><%=user.getUm_fullname() %> (<%=user.getUserroles().get(0).getLk().getLk_longname() %>) </span> <b class="caret"></b>
							
							<span ><b><%=user.getUm_fullname() %> </b></span>
							
						</a>
					    <ul class="dropdown-menu animated fadeInLeft">
							<li class="divider"></li>
							  <li><a href="${pageContext.request.contextPath}/user/edit">Edit Profile</a></li> 
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/dms/logout">Log Out</a></li>
						</ul>
					</li>
				</ul>
				
				
				
				
				
				
				
				</div>
				<!-- end header navigation right -->
				</div>
			</div>
			<!-- end container-fluid -->
		</div>
	   	
		
</br>
</br>
</br>


			