package com.dms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CategoryCode;
import com.dms.model.Judge;
import com.dms.model.Lookup;
import com.dms.model.MetaData;
import com.dms.model.MetaField;
import com.dms.model.PoliceStation;
import com.dms.model.ReportsView;


@Service
public class MetaDataService {	

	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	@Transactional
	public List<MetaField> getAll() {
		List<MetaField> result = new ArrayList<MetaField>() ;
		try{
			result = em.createQuery("SELECT m FROM MetaField m where mf_rec_status =1 ORDER BY m.mf_sequence ").getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public List<Object> getDropDownList(String query) {
		List<Object> result = new ArrayList<Object>() ;
		try{
			result = em.createQuery(query).getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}	
	
	@Transactional
	public List<MetaData> getAllByfdid(Long fd_id) {
		List<MetaData> result = new ArrayList<MetaData>() ;
		try{
			result = em.createQuery("SELECT md FROM MetaData md where md_rec_status =1 AND md where md_fd_mid = "+fd_id).getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}
	@Transactional
	public MetaData getByPk(Long md_id) {
		MetaData result = new MetaData() ;
		try{
			result = (MetaData) em.createQuery("SELECT md FROM MetaData md where md_id = "+md_id).getSingleResult();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public List<MetaData> getAllData(Long md_fd_mid) {
		List<MetaData> result = new ArrayList<MetaData>() ;
		try{
			Query query = em
					.createQuery("SELECT m FROM MetaData m WHERE md_rec_status =1 AND md_fd_mid=:md_fd_mid");
			query.setParameter("md_fd_mid", md_fd_mid);			
			result = (List<MetaData>) query.getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public MetaData save(MetaData metaData) {
		MetaData result = new MetaData();		
		try {
			result = em.merge(metaData);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	
	@Transactional
	public String getById(MetaData mdl) {
		
		Long[] list1= {11L, 25L, 31L};
    	Long[] list2= {21L, 27L,10L,17L,32L,26L,20L,1L};
    	Long[] list3= {19L};
    	Long[] list4= {5L};
    	String name=mdl.getMd_value();    	
    	System.out.println(Arrays.asList(list1).contains(mdl.getMd_mf_mid()));
    	System.out.println(Arrays.binarySearch(list2, mdl.getMd_mf_mid()));
    	System.out.println(mdl.getMd_mf_mid());
    	if(Arrays.asList(list1).contains(mdl.getMd_mf_mid())){ 
    		Long id = Long.parseLong(mdl.getMd_value(), 10);
    		Judge r2 = em.find(Judge.class, id);
    		name = r2.getJg_name();
    	}else if(Arrays.asList(list2).contains(mdl.getMd_mf_mid())){
    		Long id = Long.parseLong(mdl.getMd_value(), 10);
    		Lookup r2 = em.find(Lookup.class, id);
    		name = r2.getLk_longname();
    	}else if(Arrays.asList(list3).contains(mdl.getMd_mf_mid())){
    		Long id = Long.parseLong(mdl.getMd_value(), 10);
    		PoliceStation r2 = em.find(PoliceStation.class, id);
    		name = r2.getPs_name();
    	}else if(Arrays.asList(list4).contains(mdl.getMd_mf_mid())){
    		Long id = Long.parseLong(mdl.getMd_value(), 10);
    		CategoryCode r2 = em.find(CategoryCode.class, id);
    		name = r2.getCc_code();
    	}
		return name;
	}
	
	@Transactional
	public void deleteByPk(Long id) {
		MetaData r2 = em.find(MetaData.class, id);
		em.remove(r2);
	}
	
	@Transactional
	public List<MetaData> getByfd_mfid(Long md_fd_mid,Long md_mf_mid) {
		List<MetaData> result = new ArrayList<MetaData>() ;
		try{
			Query query = em
					.createQuery("SELECT m FROM MetaData m WHERE md_rec_status =1 AND md_fd_mid=:md_fd_mid and md_mf_mid=:md_mf_mid");
			query.setParameter("md_fd_mid", md_fd_mid).setParameter("md_mf_mid", md_mf_mid);			
			result = (List<MetaData>) query.getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public ReportsView checkUniqueCaseNoWithId(String caseNo,String caseType,String benchCode,String caseYear) {
		ReportsView reportsView =  new ReportsView();
		try
		{	
			
//            Query query = em.createQuery("SELECT d FROM MetaData d where d.md_mf_mid=:metaFieldId AND d.md_value=:caseNo AND d.md_fd_mid!=:md_fd_mid");
//			query.setParameter("metaFieldId", metaFieldId);
//			query.setParameter("caseNo", caseNo);
//			query.setParameter("md_fd_mid", md_fd_mid);
			Query query=em.createQuery("select r from ReportsView r where r.caseType=:caseType and r.caseNo=:caseNo and r.benchCode=:benchCode and r.caseYear=:caseYear").setParameter("caseType", caseType).setParameter("caseNo", caseNo).setParameter("benchCode", benchCode).setParameter("caseYear", caseYear);
			
			reportsView = (ReportsView) query.getSingleResult();
		}catch(Exception e) {			
			//e.printStackTrace();
			System.out.println("No result return");
		}
		finally{
			return reportsView;
		}
		
	}

}
