package com.dms.service;

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

import com.dms.model.MetaField;
import com.dms.model.MetaTemplate;


@Service
public class MetaFieldService {
	
	/*@PersistenceContext
	private EntityManager em;*/
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	 
	
	@Transactional
	public List<MetaField> getAll() {
		List<MetaField> result = em.createQuery("SELECT r FROM MetaTemplate r").getResultList();
		return result;
	}
	
	@Transactional
	public List<MetaField> getByfk(Long id) {
		List<MetaField> result = em.createQuery("select m from MetaField m where mf_mt_mid = :mf_mt_mid").setParameter("mf_mt_mid", id).getResultList();
		return result;
	}
	
	@Transactional
	public List<MetaField> getAllMetaField() {
		List<MetaField> result = em.createQuery("SELECT m FROM MetaField m").getResultList();
		return result;
	}
	
	@Transactional
    public MetaField update(MetaField d)
    {
		MetaField master = null;
    	try {	
    		master= em.merge(d);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
    }
	
 	@Transactional
	public void delete(Long id) {
 		MetaField r2 = em.find(MetaField.class, id);		   
		  em.remove(r2);
	}

	@Transactional
	public MetaField save(MetaField d) {
		// TODO Auto-generated method stub

		MetaField master = null;
    	try {	
    		master= em.merge(d);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}

	
	
	@Transactional
	public List<MetaField> getMetaFields(Long metaTemplateId) {
		List<MetaField> r=new ArrayList<MetaField>();
		try {
			Query query  =  em.createQuery("SELECT r from MetaField r WHERE r.mf_mt_mid =:metaTemplateId");
			query.setParameter("metaTemplateId", metaTemplateId);
			r=query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	@Transactional
	public List getAlltable() {
		 List result;
		 result = em.createNativeQuery("SELECT table_name FROM information_schema.tables "
				+ " WHERE table_schema='dms' AND table_type='BASE TABLE'").getResultList();
		System.out.println("table count"+result);
		return result;
	}
//select column_name from information_schema.columns where table_name='case_file_details';
		@Transactional
		public List getColumn(String table) {
			 List result=new ArrayList();
			 Query query = em.createNativeQuery("select column_name from information_schema.columns where table_name =:table");
			 query.setParameter("table", table);
			 result=query.getResultList();
			System.out.println("table count"+result);
			return result;
		}
		 
/*		SELECT Col.Column_Name from 
	    INFORMATION_SCHEMA.TABLE_CONSTRAINTS Tab, 
	    INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE Col 
	WHERE 
	    Col.Constraint_Name = Tab.Constraint_Name
	    AND Col.Table_Name = Tab.Table_Name
	    AND Constraint_Type = 'PRIMARY KEY'
	    AND Col.Table_Name = 'case_file_details'*/

		
		@Transactional
		public String  gettablepk(String table) {
			String result;
			 Query query = em.createNativeQuery("SELECT Col.Column_Name from INFORMATION_SCHEMA.TABLE_CONSTRAINTS Tab, "
			 		+ "INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE Col  WHERE  Col.Constraint_Name = Tab.Constraint_Name "
			 		+ "   AND Col.Table_Name = Tab.Table_Name  AND Constraint_Type = 'PRIMARY KEY'  "
			 		+ " AND Col.Table_Name =:table");
			 query.setParameter("table", table);
			 result= (String) query.getSingleResult();
			System.out.println("table count"+result);
			return result;
		}
}
