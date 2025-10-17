	package com.dms.service.report;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CaseFileTemp;
import com.dms.model.DailyActivityReport;
import com.dms.model.ReportsView;
import com.dms.utility.GlobalFunction;

@Service
public class CommonReportsService {
	/*
	@PersistenceContext
	private EntityManager em;*/
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	private GlobalFunction globalfunction;
	
	public CommonReportsService()
	{
		globalfunction = new GlobalFunction();
	}
	
	//Document Search 
	

    @Transactional
	public List<Object> getScanningProductivitySearch(String ib_inward_date,String ib_inwrd_date) {
		List<Object> result = new ArrayList<Object>() ;
		
		java.util.Date date = new Date(ib_inward_date);
		java.util.Date date1 = new Date(ib_inwrd_date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		String format1=formatter.format(date1);
		System.out.println(format);
		
		try
		{
			System.out.println("Get Scanning Productivity Report data list");
				
			/*String sql = "select (select um_fullname from User where um_id = fd_assign_to),fd_reject_status,count(cfs.fd_id)"
					+ " from CaseFileDetail cfs  where fd_stage_lid = 26 and  to_date(fd_cr_date,'yyyy-MM-dd') between to_date('"+format+"','yyyy-MM-dd') AND 
					to_date('"+format1+"','yyyy-MM-dd')"
					+ "group by fd_reject_status,fd_assign_to order by fd_assign_to,fd_reject_status"; */
					
			/*String sql ="select (select user1_.um_fullname from User user1_ where user1_.um_id=casefilede0_.fd_assign_to) as username," 
			             +" (select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'Y' and cfd.fd_stage_lid=26 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ycnt,"
			             +"(select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'N' and cfd.fd_stage_lid=26 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ncnt," 
			             +" casefilede0_.fd_cr_date, casefilede0_.fd_assign_to"
			             +" from CaseFileDetail casefilede0_" 
	                     +"where casefilede0_.fd_stage_lid=26 and to_date(casefilede0_.fd_cr_date, 'yyyy-MM-dd')" 
	                     +"between to_date('"+format+"','yyyy-MM-dd') AND to_date('"+format1+"','yyyy-MM-dd')"
			             +"group by casefilede0_.fd_cr_date,casefilede0_.fd_assign_to" 
		                 +"order by casefilede0_.fd_assign_to";
			*/
			
			
			String sql = "select (select user1_.um_fullname from User user1_ where user1_.um_id=casefilede0_.fd_assign_to) as username, "
						+" (select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'Y' and cfd.fd_stage_lid=26 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ycnt,"
						+" (select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'N' and cfd.fd_stage_lid=26 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ncnt,"
						+" to_char(casefilede0_.fd_cr_date,'yyyy/MM/dd'), casefilede0_.fd_assign_to "
						+" from CaseFileDetail casefilede0_ "
						+" where casefilede0_.fd_stage_lid=26 and (to_date(casefilede0_.fd_cr_date, 'yyyy-MM-dd') "
						+" between to_date('"+format+"', 'yyyy-MM-dd') and to_date('"+format+"', 'yyyy-MM-dd')) "
						+" group by casefilede0_.fd_cr_date,casefilede0_.fd_assign_to  "
						+" order by casefilede0_.fd_assign_to ";	
			
			
			
			
			
			
			
			
			/*String sql = "SELECT (SELECT um_fullname FROM User where um_id = cfs.fd_assign_to) as assign_to  ,"
					+ " fd_reject_status,count(cfs.fd_id) FROM CaseFileDetail cfs  WHERE fd_stage_lid = 26 AND "
					+ " to_date(fd_cr_date,'yyyy-MM-dd') between to_date('"+format+"','yyyy-MM-dd') AND to_date('"+format1+"','yyyy-MM-dd') "  //
					+ " GROUP BY cfs.fd_reject_status, "
					+ " cfs.fd_assign_to ORDER BY cfs.fd_assign_to, cfs.fd_reject_status ";
			
			*/
			
			
					
			System.out.println("Query is:" +sql);		                  
					                  
	
			result = em.createQuery(sql).getResultList();
			
		System.out.println("get list size ==  "+result.size());
		
		}
		catch(Exception e)
		{
		 	e.printStackTrace();
		}
		return result;
	}
	
	
    
    @Transactional
	public List<Object> getFirstDEData(String ib_inward_date,String ib_inwrd_date) {
		List<Object> result = new ArrayList<Object>() ;
		
		java.util.Date date = new Date(ib_inward_date);
		java.util.Date date1 = new Date(ib_inwrd_date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		String format1=formatter.format(date1);
		System.out.println(format);

		try
		{
			System.out.println("Get FirstDE Report data list");
			
			String sql = "select (select user1_.um_fullname from User user1_ where user1_.um_id=casefilede0_.fd_assign_to) as username, "
						 +" (select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'Y' and cfd.fd_stage_lid=19 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ycnt, "
						 +" (select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'N' and cfd.fd_stage_lid=19 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ncnt, "
						 +" to_char(casefilede0_.fd_cr_date,'yyyy/MM/dd'), casefilede0_.fd_assign_to "
						 +" from CaseFileDetail casefilede0_ "
						 +" where casefilede0_.fd_stage_lid=19 and (to_date(casefilede0_.fd_cr_date, 'yyyy-MM-dd') "
						 +" between to_date('"+format+"', 'yyyy-MM-dd') and to_date('"+format1+"', 'yyyy-MM-dd')) "
						 +" group by casefilede0_.fd_cr_date,casefilede0_.fd_assign_to  "
						 +" order by casefilede0_.fd_assign_to ";
			
System.out.println("query "+sql);
			
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public List<Object> getSecondDEData(String ib_inward_date,String ib_inwrd_date) {
		List<Object> result = new ArrayList<Object>() ;
		
		java.util.Date date = new Date(ib_inward_date);
		java.util.Date date1 = new Date(ib_inwrd_date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		String format1=formatter.format(date1);
		System.out.println(format);

		try
		{
			System.out.println("Get SecondDE Report data list");
			
			String sql = "select (select user1_.um_fullname from User user1_ where user1_.um_id=casefilede0_.fd_assign_to) as username, "
                        +" (select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'Y' and cfd.fd_stage_lid=20 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ycnt, "
                        +" (select count(*) from CaseFileDetail cfd where cfd.fd_reject_status = 'N' and cfd.fd_stage_lid=20 and cfd.fd_assign_to = casefilede0_.fd_assign_to) as ncnt, "
                        +" to_char(casefilede0_.fd_cr_date,'yyyy/MM/dd'), casefilede0_.fd_assign_to "
                        +" from CaseFileDetail casefilede0_ "
                        +" where casefilede0_.fd_stage_lid=20 and (to_date(casefilede0_.fd_cr_date, 'yyyy-MM-dd') "
                        +" between to_date('"+format+"', 'yyyy-MM-dd') and to_date('"+format1+"', 'yyyy-MM-dd')) "
                        +" group by casefilede0_.fd_cr_date,casefilede0_.fd_assign_to  "
                        +" order by casefilede0_.fd_assign_to ";
			
			System.out.println("query "+sql);
			
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	
	
/*	@Transactional
	public List<InwardBundle> getFiles(Long ib_mid) {
		List<InwardBundle> result = em.createQuery("SELECT c FROM CaseFileDetail c where ib_mid = "+ib_mid).getResultList();
		return result;
	}*/
	
	@Transactional
	public List<DailyActivityReport> getReportData(Long  ib_branch,String ib_inward_date) {
		List<DailyActivityReport> result = new ArrayList<DailyActivityReport>() ;
		
		java.util.Date date = new Date(ib_inward_date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		System.out.println(format);
		
		try
		{
			System.out.println("Get Daily Report data list");
			String sql = "SELECT c.fd_case_type,(SELECT lk1.lk_longname FROM Lookup lk1 WHERE lk1.lk_id = c.fd_case_type) ,"
					+ " c.fd_stage_lid  , "
					+ "( SELECT lk2.lk_longname FROM Lookup lk2 WHERE lk2.lk_id = c.fd_stage_lid ) as stage,(SELECT lk3.lk_serial_no FROM Lookup lk3"
					+ " WHERE lk3.lk_id = c.fd_stage_lid ) as serial, count(c) as count FROM CaseFileDetail c " 
					+"where ib_mid IN (SELECT ib_id from InwardBundle WHERE ib_branch =:ib_branch  AND to_date(ib_inward_date,'yyyy-MM-dd') =to_date('"+format+"','yyyy-MM-dd')) "  //
					+ "group by c.fd_case_type,"
					+ "c.fd_stage_lid  order by c.fd_case_type ";
		result = em.createQuery(sql).setParameter("ib_branch", ib_branch).getResultList();
		System.out.println("get list size ==  "+result.size());
		/*Object[] o = result.toArray();
		
		for (int i=0; i<result.size(); i++){
			
			DailyActivityReport dailyAct=(DailyActivityReport)result.get(i);
			System.out.println(".........." +dailyAct.getParameter1());
			
			}
		*/
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public List<Object> getdatewiseData(Long  ib_branch,String ib_inward_date,String ib_inwrd_date) {
		List<Object> result = new ArrayList<Object>() ;
		
		java.util.Date date = new Date(ib_inward_date);
		java.util.Date date1 = new Date(ib_inwrd_date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		String format1=formatter.format(date1);
		System.out.println(format);
		
		
		
		try
		{
			System.out.println("Get Daily Report data list");
			String sql = "SELECT c.fd_case_type,(SELECT lk1.lk_longname FROM Lookup lk1 WHERE lk1.lk_id = c.fd_case_type) ,"
					+ " c.fd_stage_lid  , "
					+ "( SELECT lk2.lk_longname FROM Lookup lk2 WHERE lk2.lk_id = c.fd_stage_lid ) as stage,(SELECT lk3.lk_serial_no FROM Lookup lk3"
					+ " WHERE lk3.lk_id = c.fd_stage_lid ) as serial, count(c) as count FROM CaseFileDetail c " 
					+"where ib_mid IN (SELECT ib_id from InwardBundle WHERE ib_branch =:ib_branch  AND to_date(ib_inward_date,'yyyy-MM-dd') between to_date('"+format+"','yyyy-MM-dd') AND to_date('"+format1+"','yyyy-MM-dd'))"  //
					+ "group by c.fd_case_type,"
					+ "c.fd_stage_lid  order by c.fd_case_type ";
		result = em.createQuery(sql).setParameter("ib_branch", ib_branch).getResultList();
		System.out.println("get list size ==  "+result.size());
		/*Object[] o = result.toArray();
		
		for (int i=0; i<result.size(); i++){
			
			DailyActivityReport dailyAct=(DailyActivityReport)result.get(i);
			System.out.println(".........." +dailyAct.getParameter1());
			
			}
		*/
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public List<Object> getLocationwiseData(String ib_inward_date,String ib_inwrd_date) {
		List<Object> result = new ArrayList<Object>() ;
		
		java.util.Date date = new Date(ib_inward_date);
		java.util.Date date1 = new Date(ib_inwrd_date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		String format1=formatter.format(date1);
		System.out.println(format);
		
		
		
		try
		{
			System.out.println("Get Daily Report data list");
			String sql = "SELECT c.fd_case_type,(SELECT lk1.lk_longname FROM Lookup lk1 WHERE lk1.lk_id = c.fd_case_type) ,"
					+ " c.fd_stage_lid  , "
					+ "( SELECT lk2.lk_longname FROM Lookup lk2 WHERE lk2.lk_id = c.fd_stage_lid ) as stage,(SELECT lk3.lk_serial_no FROM Lookup lk3"
					+ " WHERE lk3.lk_id = c.fd_stage_lid ) as serial, count(c) as count FROM CaseFileDetail c " 
					+"where ib_mid IN (SELECT ib_id from InwardBundle WHERE to_date(ib_inward_date,'yyyy-MM-dd') between to_date('"+format+"','yyyy-MM-dd') AND to_date('"+format1+"','yyyy-MM-dd'))"  //
					+ "group by c.fd_case_type,"
					+ "c.fd_stage_lid  order by c.fd_case_type ";
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		/*Object[] o = result.toArray();
		
		for (int i=0; i<result.size(); i++){
			
			DailyActivityReport dailyAct=(DailyActivityReport)result.get(i);
			System.out.println(".........." +dailyAct.getParameter1());
			
			}
		*/
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public List<Object> getWorkProgressData() {
		List<Object> result = new ArrayList<Object>() ;
		
		try
		{
			System.out.println("Get WorkProgress Report data list");
			
			String sql =" "; 
		
			/*result = em.createQuery(sql).setParameter("ib_branch", ib_branch).getResultList();*/
		 
			System.out.println("get list size ==  "+result.size());
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<ReportsView> getSearchByCaseNumberData(String year,String bench,String casetype,String casenumber) {
		List<ReportsView> result = new ArrayList<ReportsView>() ;
		
		System.out.println(year+" "+bench+" "+casetype+" "+casenumber);

		try
		{
			System.out.println("Get search by case no data list");
			
			String sql = "SELECT r from ReportsView r where benchCode='"+bench+"'" ;
			
			if(casetype!=null  && casetype.length()>0)
				sql+=" and caseType='"+casetype+"'";
			
			if(year!=null  && year.length()>0)
				sql+=" and caseYear='"+year+"'";
			
			if(casenumber!=null  && casenumber.length()>0)				
				sql+=" and lower(caseNo)  like lower('%"+casenumber+"%')";
			
			System.out.println("query "+sql);
			
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Transactional
	public List<Object> getsearchByfullBenchJudgmentOrder(String fromDate,String toDate,String firstJudge,String secondJudge,String thirdJudge,String judgeType,String judgmentType) {
		List<Object> result = new ArrayList<Object>() ;
		java.util.Date date = new Date(fromDate);
		java.util.Date date1 = new Date(toDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date);
		String format1=formatter.format(date1);
		System.out.println(format);
		System.out.println(format+" "+format1+" "+judgmentType+" "+judgeType+" "+firstJudge+" "+secondJudge+" "+thirdJudge);

		try
		{
			System.out.println("getsearchByfullBenchJudgmentOrder Data list");
			
			String sql = "";
			
			System.out.println("query "+sql);
			
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public List<ReportsView> getSearchByJudgementDateData(String fromDate,String toDate,String bench) {
		List<ReportsView> result = new ArrayList<ReportsView>() ;
//		java.util.Date date = new Date(fromDate);
//		java.util.Date date1 = new Date(toDate);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String format = formatter.format(date);
//		String format1=formatter.format(date1);
//		System.out.println(format);
//		System.out.println(format+" "+format1+" "+bench);

		try
		{
			System.out.println("getSearchByJudgementDate Data list");
			
			
			String sql = "SELECT r from ReportsView r where benchCode='"+bench+"'" ;
			
			
//			if(fromDate !=null  && fromDate.length()>0)
//				sql+=" and judgementDate >='"+fromDate+"'";
//			
//			if(toDate!=null  && toDate.length()>0)				
//				sql+=" and judgementDate <='"+toDate+"'";
//			
			if(fromDate !=null && fromDate.length()>0 && toDate!=null && toDate.length()>0)
                sql+=" and to_date(judgementDate,'dd/mm/yyyy') between to_date('"+fromDate+"','dd/mm/yyyy') and to_date('"+toDate+"' ,'dd/mm/yyyy')";
			
			System.out.println("query "+sql);
			
		result = em.createQuery(sql).setFirstResult(0).setMaxResults(100).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Transactional
	public List<ReportsView> getSearchByTitle(String counsel,String counselName,String benchCodeId,String fromDate,String toDate) {
		List<ReportsView> result = new ArrayList<ReportsView>() ;
		
		try
		{
			System.out.println("getSearchByTitle Data list");
			
			String sql = "SELECT r from ReportsView r where benchCode='"+benchCodeId+"'" ;
			
			if(counsel==null || counsel.length()==0)
				sql+=" and ( lower(firstPetitioner) like lower('%"+counselName+"%')  OR lower(petitioner) like lower('%"+counselName+"%') OR lower(respondent) like lower('%"+counselName+"%')  OR lower(firstRespondent) like lower('%"+counselName+"%'))";
			
			if(counsel!=null && counsel.equals("6"))
				sql+=" and ( lower(firstPetitioner) like lower('%"+counselName+"%') OR lower(petitioner) like lower('%"+counselName+"%') )";
			
			if(counsel!=null && counsel.equals("7"))
				sql+=" and ( lower(firstRespondent) like lower('%"+counselName+"%') OR  lower(respondent) like lower('%"+counselName+"%') )";
			
//			if(fromDate !=null  && fromDate.length()>0)
//				sql+=" and judgementDate >='"+fromDate+"'";
//			
//			if(toDate!=null  && toDate.length()>0)				
//				sql+=" and judgementDate <=to_date("+toDate+",'dd/mm/yyyy')";
//			
			if(fromDate !=null && fromDate.length()>0 && toDate!=null && toDate.length()>0)
                sql+=" and to_date(judgementDate,'dd/mm/yyyy') between to_date('"+fromDate+"','dd/mm/yyyy') and to_date('"+toDate+"' ,'dd/mm/yyyy')";
			
			System.out.println("query "+sql);
			
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
    public List<ReportsView> getSearchByJudgeNameData(List<CaseFileTemp> fileIds, String  bench,String fromDate,String toDate) {
        List<ReportsView> result = new ArrayList<ReportsView>() ;
        String files="";
        Boolean flag=false;
        try
        {
            System.out.println("Get JudgeName Report data list");
            String sql = "SELECT r from ReportsView r where r.benchCode='"+bench+"'" ;
            
            
            for(CaseFileTemp id:fileIds){
            	files=files+id.getFileId()+",";
            }
            files=files.substring(0, files.length()-1);
            
            System.out.println("files="+files);
            
            sql+=" and r.fileId In ("+files+")";
             
            if(fromDate !=null && fromDate.length()>0 && toDate!=null && toDate.length()>0)
                sql+=" and to_date(judgementDate,'dd/mm/yyyy') between to_date('"+fromDate+"','dd/mm/yyyy') and to_date('"+toDate+"' ,'dd/mm/yyyy')";
            
            System.out.println("sql query"+sql);
            
            result = em.createQuery(sql).getResultList();
            
            System.out.println("get list size ==  "+result.size());
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
//        Query query = em.createNativeQuery("select * from getJudgeNameWiseCaseFile('12813,12299')",ReportsView.class);      
////      List<Long> ids =storedprocedurequery.getResultList();
//		result=query.getResultList();
//		
        return result;
    }   
	
	public List<CaseFileTemp> getJudgeNameWiseCaseFileList(String judgeids,Integer counter) {
	
		List<CaseFileTemp> casefileids=new ArrayList<CaseFileTemp>();
		Query query = em.createNativeQuery("select * from getjudgenamewisecasefile('{"+judgeids+"}',"+counter+")",CaseFileTemp.class);      
		         
		//   
		casefileids=query.getResultList();
		
		return casefileids;	    
        
	}

	@Transactional
	public List<ReportsView> getSearchByCounselNameData(String counsel, String  benchCodeId,String fromDate,String toDate,String counselName) {
		List<ReportsView> result = new ArrayList<ReportsView>() ;
		
//		java.util.Date date = new Date(fromdate);
//		java.util.Date date1 = new Date(todate);
	//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String format = formatter.format(date);
//		String format1=formatter.format(date1);
		//System.out.println(format);
		
		
		
		try
		{
			System.out.println("Get Counsel Report data list");
			String sql = "SELECT r from ReportsView r where benchCode='"+benchCodeId+"'" ;
			
			if(counsel==null || counsel.length()==0)
				sql+=" and ( lower(petitionerCounsel) like lower('%"+counselName+"%') OR lower(respondentCounsel) like lower('%"+counselName+"%'))";
			
			if(counsel!=null && counsel.equals("8"))
				sql+=" and  lower(petitionerCounsel) like lower('%"+counselName+"%')";
			
			if(counsel!=null && counsel.equals("9"))
				sql+=" and  lower(respondentCounsel) like lower('%"+counselName+"%')";
			
//			if(fromDate !=null && fromDate.length()>0)
//				sql+=" and to_date(judgementDate,'dd/mm/yyyy') >=to_date("+fromDate+",'dd/mm/yyyy')";
//			
//			if(toDate!=null && toDate.length()>0)				
//				sql+=" and to_date(judgementDate,'dd/mm/yyyy') <=to_date("+toDate+",'dd/mm/yyyy')";
			if(fromDate !=null && fromDate.length()>0 && toDate!=null && toDate.length()>0)
                sql+=" and to_date(judgementDate,'dd/mm/yyyy') between to_date('"+fromDate+"','dd/mm/yyyy') and to_date('"+toDate+"' ,'dd/mm/yyyy')";
			
			result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
    
	
	
	

	@Transactional
	public List<Object> getAdvanceSearchData(Long branchCode, Long casetype,String fromYear,String toYear, String judgeType,String firstJudgeName,String secondJudgeName,String thirdJudgeName,String fourthJudgeName,String fifthJudgeName) {
		List<Object> result = new ArrayList<Object>() ;
		
		try
		{
			System.out.println("Get AdvanceSearch Report data list");
			System.out.println(branchCode+" "+casetype +" " +fromYear+" "+toYear +" "+judgeType +" "+firstJudgeName +" "+secondJudgeName +" "+thirdJudgeName +" "+fourthJudgeName+" "+fifthJudgeName);
			
			
			String sql = "";
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		/*Object[] o = result.toArray();
		
		for (int i=0; i<result.size(); i++){
			
			DailyActivityReport dailyAct=(DailyActivityReport)result.get(i);
			System.out.println(".........." +dailyAct.getParameter1());
			
			}
		*/
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public Integer getOpeningBalance(String fromDt) {
		// TODO Auto-generated method stub
		Integer count=0;
		
		String query = "select count(*) as opnBal from CaseFileDetail where to_date(fd_cr_date,'yyyy-mm-dd') < to_date('"+fromDt+"','yyyy-mm-dd') and fd_stage_lid = 242";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}


	public Integer getBundleInwardCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		
		String query = "select count(*) as bunInw from InwardBundle where to_date(ib_inward_date,'yyyy-mm-dd') = to_date('"+fromDate+"','yyyy-mm-dd')";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}


	public Integer getReceiveFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') = to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 26" 
					+" group by cfs_fd_mid,cfs_stage_lid) as received";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}


	public Integer getScannedFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') = to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 19" 
					+" group by cfs_fd_mid,cfs_stage_lid) as scanned";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}


	public Integer getMakerFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') = to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 20" 
						+" group by cfs_fd_mid,cfs_stage_lid) as maker ";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}


	public Integer getCheckerFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') = to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 242" 
						+" group by cfs_fd_mid,cfs_stage_lid) as checker ";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}


	public Integer getVerifiedFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') = to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 14" 
						+" group by cfs_fd_mid,cfs_stage_lid) as verified ";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}


	public Integer getCumReceiveFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') <= to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 26" 
					+" group by cfs_fd_mid,cfs_stage_lid) as received";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}
	public Integer getCumScannedFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') <= to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 19" 
					+" group by cfs_fd_mid,cfs_stage_lid) as scanned";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}
	public Integer getCumMakerFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') <= to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 20" 
						+" group by cfs_fd_mid,cfs_stage_lid) as maker ";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}
	
	public Integer getCumCheckerFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') <= to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 242" 
					+" group by cfs_fd_mid,cfs_stage_lid) as checked";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}

	public Integer getCumVerifiedFileCount(String fromDate) {
		// TODO Auto-generated method stub
		Integer count=0;
		String query = "select count(*) from (select count(*) from CaseFileStage where to_date(cfs_stage_date,'yyyy-mm-dd') <= to_date('"+fromDate+"','yyyy-mm-dd') and cfs_stage_lid = 14" 
						+" group by cfs_fd_mid,cfs_stage_lid) as verified ";
		
		
		count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());
		
		System.out.println("Result="+count);
		
		return count;
	}

	public List<ReportsView> getBasicSearchData(String caseTypeId,String benchCodeId, String year, String judgeId, String caseNumber) {
			List<ReportsView> result = new ArrayList<ReportsView>() ;
		
//		java.util.Date date = new Date(fromdate);
//		java.util.Date date1 = new Date(todate);
	//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String format = formatter.format(date);
//		String format1=formatter.format(date1);
		//System.out.println(format);
		
		
		
		try
		{
			System.out.println("Get Counsel Report data list");
			String sql = "SELECT r from ReportsView r where benchCode='"+benchCodeId+"'" ;
			
			if(caseTypeId != null && caseTypeId.length()>0)
				sql+=" and casetype ='"+caseTypeId+"'";
			
			if(year!=null && year.length()>0)
				sql+=" and caseyear ='"+year+"'";
			
			if(judgeId!=null && judgeId.length()>0)
				sql+=" and judgeId ='"+judgeId+"'";
				
			if(caseNumber!=null && caseNumber.length()>0)				
				sql+=" and lower(caseno) like lower('%"+caseNumber+"%')";
			
		result = em.createQuery(sql).getResultList();
		System.out.println("get list size ==  "+result.size());
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	


	

   
	
	
}
