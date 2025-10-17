<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dms.model.ObjectMaster"%>
<%@ page import="com.dms.model.User"%>	
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="EDMSApp">
<head>

<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/bootstrap/angular-datepicker.css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Allahabad High Court e-Filing</title>

 <%@ include file="style.jsp"%>
<%@ include file="script.jsp"%>
</head>
<body>
<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");

String role=user.getUserroles().get(0).getLk().getLk_longname();
String url=request.getRequestURL()+"";
String home="";
String newcase="";
String newcaveat="";
String newapplication="";
String scrutinycase="";
String scrutinycaveat="";
String scrutinyapplication="";
String olreport="";
String amendmenthistory="";
String uri=(String) request.getAttribute("javax.servlet.forward.request_uri");
if(uri.equals("/onlinecasefiling/ecourt/ecourtHome"))
	home="active";
if(uri.equals("/onlinecasefiling/ecourt/addCase"))
	newcase="active";
if(uri.equals("/onlinecasefiling/ecourt/addCaveat"))
	newcaveat="active";
if(uri.equals("/onlinecasefiling/searchcasefile/search"))
	newapplication="active";
if(uri.equals("/onlinecasefiling/scrutiny/cases"))
	scrutinycase="active";
if(uri.equals("/onlinecasefiling/scrutiny/caveats"))
	scrutinycaveat="active";
if(uri.equals("/onlinecasefiling/scrutiny/applications"))
	scrutinyapplication="active";
if(uri.equals("/onlinecasefiling/olreport/manage"))
	olreport="active";
if(uri.equals("/onlinecasefiling/amendmenthistory/manage"))
	amendmenthistory="active";

%>
<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
		<!-- begin #header -->
		<div id="header" class="header navbar navbar-default navbar-fixed-top">
			<!-- begin container-fluid -->
			<div class="container-fluid">
				<!-- begin mobile sidebar expand / collapse button -->
				<div class="navbar-header">
					<a href="index.html" class="navbar-brand"><span class="navbar-logo"></span>Allahabad High Court e-Filing</a>
				</div>
				<!-- end mobile sidebar expand / collapse button -->
				
				<!-- begin header navigation right -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown navbar-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${pageContext.request.contextPath}/assets/img/user-13.jpg" alt="" /> 
							<span class="hidden-xs"><%=user.getUm_fullname() %></span> <b class="caret"></b>
						</a>
						  <ul class="dropdown-menu animated fadeInLeft">
							<li class="arrow"></li>
							  <li><a href="${pageContext.request.contextPath}/user/edit">Edit Profile</a></li> 
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/dms/logout">Log Out</a></li>
						</ul>
					</li>
				</ul>
				<!-- end header navigation right -->
			</div>
			<!-- end container-fluid -->
		</div>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				
				<ul class="nav">
					<li class="has-sub <%=home %>">
						<a href="${pageContext.request.contextPath}/ecourt/ecourtHome">
						<i class="fa fa-home"></i>
							<span>Home</span>
					    </a>
					</li>
					<% if(role.equals("OLAdmin")){ %>
					<li class="has-sub <%=olreport %>">
						<a href="${pageContext.request.contextPath}/olreport/manage">
						<i class="fa fa-file-o"></i>
							<span>Office Liquidator Report</span>
					    </a>
					</li>
					<% } %>
					<% if(role.equals("Advocate") || role.equals("InPerson")){ %>
					<li class="has-sub <%=newcase %>">
						<a href="${pageContext.request.contextPath}/ecourt/addCase">
						<i class="fa fa-file-o"></i>
							<span>Fresh Case</span>
					    </a>
					</li>
					<li class="has-sub <%=newcaveat %>">
						<a href="${pageContext.request.contextPath}/ecourt/addCaveat">
						<i class="fa fa-file-o"></i>
							<span>Caveat</span>
							</a>
					</li>
					<li class="has-sub <%=newapplication %>">
						<a href="${pageContext.request.contextPath}/searchcasefile/search">
						<i class="fa fa-file-o"></i>
							<span>Pending Cases(Misc. Appl./Docs.)</span>
							</a>
					</li>
					<li class="has-sub <%=amendmenthistory %>">
						<a href="${pageContext.request.contextPath}/amendmenthistory/manage">
						<i class="fa fa-file-o"></i>
							<span>Amendments</span>
					    </a>
					</li>
					<% } %>
					<%-- <% }else{ %>
					<% if(role.equals("CaseScrutiny")){ %>
					<li class="has-sub <%=scrutinycase %>">
						<a href="${pageContext.request.contextPath}/scrutiny/cases">
						<i class="fa fa-file-o"></i>
							<span>Case</span>
					    </a>
					</li>
					<li class="has-sub <%=scrutinycaveat %>">
						<a href="${pageContext.request.contextPath}/scrutiny/caveats">
						<i class="fa fa-file-o"></i>
							<span>Caveat</span>
							</a>
					</li>
					<% }else{ %>
					<li class="has-sub <%=scrutinyapplication %>">
						<a href="${pageContext.request.contextPath}/scrutiny/applications">
						<i class="fa fa-file-o"></i>
							<span>Application</span>
							</a>
					</li>
					<% }} %> --%>
					<li class="has-sub ">
						<a href="javascript:;">
						<i class="fa fa-info-circle"></i>
							<span>Help</span>
					    </a>
					</li>					
					<!-- end sidebar minify button -->
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		
		<!-- end #sidebar -->
		
		<!-- begin #content -->
			
	<!-- ================== END PAGE LEVEL JS ================== -->

