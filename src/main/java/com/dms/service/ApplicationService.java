package com.dms.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Application;
import com.dms.model.ApplicationCheckListMapping;
import com.dms.model.ApplicationCourtFee;
import com.dms.model.ApplicationStage;
import com.dms.model.ApplicationTypes;
import com.dms.model.ApplicationUploaded;
import com.dms.model.BSApplicationCheckListMapping;
import com.dms.model.CaseCheckListMapping;
import com.dms.model.CaseFileDetail;
import com.dms.model.OtherAppNo;
import com.dms.model.SubApplication;





@Service
public class ApplicationService 
{
	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	

	@PersistenceContext(unitName="persistenceUnitCIS")
	@Qualifier(value = "entityManagerFactoryCIS")
	private EntityManager emCIS;
	
	@Transactional
	public List<Application> getApplicationDetails(Long um_id) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -16);
		System.out.println("Date = "+ cal);
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nDate=s.format(cal.getTime());
	List<Application> rcdDetails=null;
		rcdDetails= em.createQuery("SELECT ap FROM Application ap where ap.ap_cr_by ="+um_id+" and ap.ap_stage_lid !=1000049L" + 
			 " and ap.ap_id not in(" + 
				" select cm_ap_mid from ApplicationCheckListMapping  where cm_cr_date  < '"+ nDate+"' and cm_rec_status=1)" + 
			" order by ap.ap_id ").getResultList();
		return rcdDetails;
	}
	
	@Transactional
	public List<Application> getPassedApplicationDetails(Long um_id) {
		
		
		
	List<Application> rcdDetails=null;
		rcdDetails= em.createQuery("SELECT ap FROM Application ap where ap.ap_cr_by ="+um_id+" and ap.ap_stage_lid =1000049 order by ap.ap_id ").getResultList();
		return rcdDetails;
	}
	
	
	@Transactional
	public List<Application> getDefectApplicationDetails(Long um_id) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -16);
		System.out.println("Date = "+ cal);
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nDate=s.format(cal.getTime());
		
	List<Application> rcdDetails=null;
		rcdDetails= em.createQuery("SELECT ap FROM Application ap where ap.ap_cr_by ="+um_id+" and ap.ap_stage_lid =1000041L"
				+ " and ap.ap_id in("
				+ "select cm_ap_mid from ApplicationCheckListMapping  where cm_cr_date  < '"+ nDate+"' and cm_rec_status=1)"
				+ " order by ap.ap_id ").getResultList();
		return rcdDetails;
	}
	
	@Transactional
	public Application getRegisterApplication(Long id) {
		Application result=null;
	    Query query=null;
		query = em.createQuery(" SELECT ap from Application ap where ap.ap_id=:id").setParameter("id", id);
		result=(Application) query.getSingleResult();
		
		return result;
	}
	
	@Transactional
	public CaseFileDetail getCaseFile(Long fdId) {
		CaseFileDetail result=null;
	    String query=" SELECT cfd from CaseFileDetail cfd where cfd.fd_id="+fdId;
		result= (CaseFileDetail) em.createQuery(query).getSingleResult();
		
		return result;
	}
	

	@Transactional("transactionManager")
	public  List<Object[]>   getPetCivic(String label) {
		
		 List<Object[]>  data =null;
		 List<Map<String,Object>> res=null;
		
		String q= "select party_no,name from civ_address_t where type=1 and cino='"+label+"' order by party_no";
		
		try {
		Query query =emCIS.createNativeQuery(q);
		
		data = query.getResultList();
		
		if(data.size()==0) {
			 q= "select party_no,name from civ_address_t_a where type=1 and cino='"+label+"' order by party_no";
			 query =emCIS.createNativeQuery(q);
				
				data = query.getResultList();
		}
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	
	@Transactional("transactionManager")
	public  List<Object[]>   getResCivic(String label) {
		
		 List<Object[]>  data =null;
		 List<Map<String,Object>> res=null;
		
		String q= "select party_no,name from civ_address_t where type=2 and cino='"+label+"' order by party_no";
		
		try {
		Query query =emCIS.createNativeQuery(q);
		
		data = query.getResultList();
		
		
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	@Transactional("transactionManager")
	public  Object[]   getCaseDetails(String label,Integer caseNo,Integer caseYear) {
		
		 Object[]  data =null;
		 List<Map<String,Object>> res=null;
		
		String q= "select c.cino,(select full_form from case_type_t where  case_type  = c.regcase_type) ,c.reg_no,c.reg_year,c.pet_name,c.res_name,c.date_of_filing,"
				+ "(select (select dist_name from district_t  where dist_code  =case_dist_code) from case_info  where    cino =c.cino) "+
				"from civil_t c where c.reg_year=:caseYear and c.reg_no=:caseNo and c.regcase_type= (select case_type  from case_type_t where  type_name  =:caseType)";
		
		try {
		Query query =emCIS.createNativeQuery(q).setParameter("caseType", label).setParameter("caseNo", caseNo).setParameter("caseYear", caseYear);
		
		data = (Object[]) query.getSingleResult();
		
		
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	
	
	@Transactional
    public Application save(Application c) {
    
		Application application = null;
		Application application1 = null;
    	try 
    	{	
    		application= em.merge(c);	
    		application1=em.find(Application.class, application.getAp_id());
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return application1;
    }
	
	
	
	
	@Transactional
	public Application addApplication(Application application) {
		Application ap=null;
		 try {
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			ap=em.merge(application);
			ap.setAp_draft_no(ap.getAp_id()+"_"+year);
			ap= em.merge(ap);
			ap=em.find(Application.class, ap.getAp_id());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ap;
	}
	
	@Transactional
    public ApplicationStage saveApplicationStage(ApplicationStage cs) {
    
		ApplicationStage appstage = null;
    	try 
    	{	
    		appstage= em.merge(cs);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return appstage;
    }
	
	@Transactional
    public ApplicationUploaded saveApplicationDocument(ApplicationUploaded cs) {
    
		ApplicationUploaded appdocument = null;
    	try 
    	{	
    		appdocument= em.merge(cs);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return appdocument;
    }
	

	 
	 @Transactional
	 public ApplicationCourtFee addCourtFee(ApplicationCourtFee courtFee) {
	 	ApplicationCourtFee result=null;
	     
	 	  result=em.merge(courtFee);
	 	
	 	return result;
	 }
	 
	 @Transactional
		public List<ApplicationCourtFee> getApplicationCourtFee(Long id) {
			
			List<ApplicationCourtFee> result=null;
			Query query=null;
			query = em.createQuery(" SELECT cf from ApplicationCourtFee cf where cf.acf_rec_status=1 and cf.acf_ap_mid=:id").setParameter("id", id);
			result=query.getResultList();
			return result;
		}
	 
	 @Transactional
	 public List<ApplicationUploaded> getUploadedApplications(Long ap_id) 
	 {
			List<ApplicationUploaded> result=new ArrayList<ApplicationUploaded>();
			
			try{
				result = em.createQuery("SELECT cd FROM ApplicationUploaded cd  where cd.au_ap_mid="+ap_id+" and cd.au_rec_status=1 ").getResultList();
			}catch(Exception e)	{
				e.printStackTrace();
			}
			return result;
	}
	 
	 @Transactional
		public Application getByPk(Long id) {
		 Application result=null;
		 try {
			result = (Application) em.createQuery("select c from Application c where c.ap_id = :ap_id").setParameter("ap_id", id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return result;
		}
	 

	    
	    @Transactional
		public List<ApplicationCourtFee> getCourtFee(Long id) {
			
			List<ApplicationCourtFee> result=null;
			Query query=null;
			query = em.createQuery(" SELECT cf from ApplicationCourtFee cf where cf.acf_rec_status=1 and cf.acf_ap_mid=:id").setParameter("id", id);
			result=query.getResultList();
			return result;
		}
	    
	    @Transactional
		public Long  getDiarySequence() {
			
			Long  sequence = 0L;
			
			String query = "select nextval('diary_sequence')";
			
			try {
				sequence= ((BigInteger) em.createNativeQuery(query).getResultList().get(0)).longValue();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			return sequence;
		}

		public List<Application> getDraftDetails(Long um_id) {
			// TODO Auto-generated method stub
			List<Application> apDetails=null;
			apDetails= em.createQuery("SELECT ap FROM Application ap where ap.ap_assign_to ="+um_id+" order by ap.ap_id ").getResultList();
			return apDetails;
		}
		
		@Transactional
		public List<ApplicationTypes> getApplicationTypes() 
		{
			List<ApplicationTypes> result=null;
			Query query=null;
			query = em.createQuery(" SELECT app from ApplicationTypes app where app.at_rec_status=1 ");
			result=query.getResultList();
			return result;
		}
		
		@Transactional
		public ApplicationUploaded getApplicationUploaded(Long au_id) 
		{
			ApplicationUploaded result=new ApplicationUploaded();
			
			try{
				result = (ApplicationUploaded) em.createQuery("SELECT au FROM ApplicationUploaded au  where au.au_id="+au_id).getSingleResult();
			}catch(Exception e)	{
				e.printStackTrace();
			}
			return result;
		}
		
		@Transactional
		 public boolean deleteApplication(Long id) 
		 {
			 boolean flag=false;
			 	ApplicationUploaded oldDocument=null;
			 	oldDocument=em.find(ApplicationUploaded.class, id);
			    em.remove(oldDocument);
			    flag= true;
			    return flag;
		 }
		
		@Transactional
		 public boolean deleteSubApplication(Long id) 
		 {
			 boolean flag=false;
			 	SubApplication subApp=null;
			 	subApp=em.find(SubApplication.class, id);
			    em.remove(subApp);
			    flag= true;
			    return flag;
		 }
		
		@Transactional
		public List<ApplicationCheckListMapping> getApplicationCheckList(Long ap_id) {
			// TODO Auto-generated method stub
			List<ApplicationCheckListMapping> result=null;
			Query query=null;
			query = em.createQuery("SELECT a from ApplicationCheckListMapping a where a.cm_rec_status=1 and a.cm_ap_mid=:id").setParameter("id", ap_id);
			result=query.getResultList();
			return result;
		}
		
		@Transactional
		public List<BSApplicationCheckListMapping> getBSApplicationCheckList(Long ap_id) {
			// TODO Auto-generated method stub
			List<BSApplicationCheckListMapping> result=null;
			Query query=null;
			query = em.createQuery("SELECT a from BSApplicationCheckListMapping a where a.cm_rec_status=1 and a.cm_ap_mid=:id").setParameter("id", ap_id);
			result=query.getResultList();
			return result;
		}
		
		
		
		
		@Transactional
		public List<ApplicationCheckListMapping> getApplicationReportingHistory(Long ap_id) {
			// TODO Auto-generated method stub
			List<ApplicationCheckListMapping> result=null;
			Query query=null;
			query = em.createQuery("SELECT a from ApplicationCheckListMapping a where a.cm_ap_mid=:id").setParameter("id", ap_id);
			result=query.getResultList();
			return result;
		}
		
		@Transactional
		public boolean checkDateValidity(Long id)
		{
			boolean flag=false;
			
			Date scrutinydate= (Date) em.createQuery("SELECT cm_cr_date from ApplicationCheckListMapping  where cm_ap_mid =:id order by cm_id desc").setParameter("id", id).setMaxResults(1).getSingleResult();
			long difference = (scrutinydate.getTime()-new Date().getTime())/86400000; 
			long days= Math.abs(difference); 
			if(days<=15)flag=true;
			
			return flag;
		}
		
		
		@Transactional
		public boolean checkBsDateValidity(Long id)
		{
			boolean flag=false;
			
			Date scrutinydate= (Date) em.createQuery("SELECT cm_cr_date from BSApplicationCheckListMapping  where cm_ap_mid =:id order by cm_id desc").setParameter("id", id).setMaxResults(1).getSingleResult();
			
			long difference = (scrutinydate.getTime()-new Date().getTime())/86400000; 
			long days= Math.abs(difference); 
			if(days<=15)flag=true;
			
			return flag;
		}
		
	       @Transactional
	    	public SubApplication saveSubApplication(Application application, SubApplication otherAppNo) {
	    		SubApplication subApplication = null;

	    		try {

	    			SubApplication application2 = new SubApplication();
	    			application2.setSb_ap_mid(application.getAp_id());
	    			application2.setSb_ap_no(otherAppNo.getSb_ap_no());
	    			application2.setSb_ap_year(otherAppNo.getSb_ap_year());
	    			application2.setSb_ap_at_mid(otherAppNo.getSb_ap_at_mid());
	    			application2.setSb_ap_rec_status(1);
	    			application2.setSb_ap_cr_date(new Date());

	    			subApplication = em.merge(application2);

	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		return subApplication;

	    	}
	       
	       @Transactional
	       public Integer deleteNullSubApplication(Long ap_id) {
	   		// TODO Auto-generated method stub
	    	Integer result=0;
	   		Query query=em.createNativeQuery("delete FROM sub_applications where sb_ap_mid="+ap_id+" and sb_ap_at_mid IS NULL");
	   		System.out.println("query-----------"+query.executeUpdate());
	   		result= query.executeUpdate();
	   	
	   		return result;
	   	}
	       
	       public List<ApplicationStage> getStages(Long docId) {
				// TODO Auto-generated method stub
				List<ApplicationStage> result=null;
				Query query=null;
				query = em.createQuery("SELECT a from ApplicationStage a where a.as_ap_mid=:id").setParameter("id", docId);
				result=query.getResultList();
				return result;
			}
	       
	   	
	/*   	@Transactional
	       public SubApplication saveSubApp(SubApplication c) {
	       
	   		SubApplication application = null;
	       	try 
	       	{	
	       		application= em.merge(c);	    	
	   	    }catch (Exception e) {		
	   	    	e.printStackTrace();
	   		}
	       	return application;
	       }*/
		
		


}
