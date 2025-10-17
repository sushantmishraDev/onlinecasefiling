package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CategoryCode;



@Service
public class CategoryCodeService {

	
	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;

	@Transactional
	public List<CategoryCode> getAll(){
		List<CategoryCode> result=em.createQuery("SELECT s FROM CategoryCode s").getResultList();
		return result;		
	}
	
	@Transactional
	public CategoryCode getLookUp(Long id){
		CategoryCode m= em.find(CategoryCode.class, id);
		return m;
	}

	
	@Transactional
    public CategoryCode save(CategoryCode s) {
    
		CategoryCode lookUp = null;
    	try {	
    		lookUp= em.merge(s);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return lookUp;
    }
	
	@Transactional
    public void delete(Long id) {    
		CategoryCode m = em.find(CategoryCode.class, id);		   
		  em.remove(m);
    }

}
