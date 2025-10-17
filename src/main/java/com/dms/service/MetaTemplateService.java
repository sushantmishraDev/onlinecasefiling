package com.dms.service;

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
public class MetaTemplateService {
	
	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	 
	@Transactional
	public MetaTemplate getDocumentById(Long id) {
		
		MetaTemplate r= new MetaTemplate();
		try {
			Query query  =  em.createQuery("SELECT r from MetaTemplate r WHERE r.id =:id");
			query.setParameter("id", id);
			r= (MetaTemplate) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	@Transactional
	public List<MetaTemplate> getAll() {
		List<MetaTemplate> result = em.createQuery("SELECT r FROM MetaTemplate r").getResultList();
		return result;
	}
	
	
	
	
	public MetaTemplate getDocument(Long id) {		   
		MetaTemplate r= em.find(MetaTemplate.class, id);
		return r;
	}
	
	@Transactional
    public MetaTemplate update(MetaTemplate d)
    {
		MetaTemplate master = null;
    	try {	
    		master= em.merge(d);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
    }
	
 	@Transactional
	public void delete(Long id) {
 		MetaTemplate r2 = em.find(MetaTemplate.class, id);		   
		  em.remove(r2);
	}

	@Transactional
	public MetaTemplate save(MetaTemplate d) {
		// TODO Auto-generated method stub

		MetaTemplate master = null;
    	try {	
    		master= em.merge(d);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}
	
	  
}
