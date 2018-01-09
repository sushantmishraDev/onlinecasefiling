package com.dms.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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





@Service
public class ApplicationService 
{
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Application> getApplicationDetails(Long um_id) {
	List<Application> rcdDetails=null;
		rcdDetails= em.createQuery("SELECT ap FROM Application ap where ap.ap_cr_by ="+um_id+" order by ap.ap_id ").getResultList();
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
    public Application save(Application c) {
    
		Application application = null;
    	try 
    	{	
    		application= em.merge(c);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return application;
    }
	
	@Transactional
	public Application addApplication(Application application) {
		Application ap=null;
		 try {
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			ap=em.merge(application);
			ap.setAp_draft_no(ap.getAp_id()+"_"+year);
			ap= em.merge(ap);
			
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
		
		


}
