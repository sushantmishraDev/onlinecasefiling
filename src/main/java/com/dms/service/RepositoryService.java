package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Repository;

@Service
public class RepositoryService {

	/*@PersistenceContext
	private EntityManager em;*/
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	   
	@Transactional
	public List<Repository> getAll() {
		List<Repository> result = em.createQuery("SELECT r FROM Repository r").getResultList();
		return result;
	}
	
	
	public Repository getRepository(Long id) {		   
		Repository r= em.find(Repository.class, id);
		return r;
	}
	
	@Transactional
    public void update(Repository r)
    {
	   Repository r1 = em.merge(r);
    }
	
	@Transactional
    public Repository save(Repository r) {
    
		Repository master = null;
    	try {	
    		master= em.merge(r);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
    }
    
	@Transactional
	public void delete(Integer id) {
		  Repository r2 = em.find(Repository.class, id);		   
		  em.remove(r2);
	}

	@Transactional
	public Repository checkRepositoryExist(Repository r) {
		// TODO Auto-generated method stub
		Repository repository =new Repository();
		try {
			repository=(Repository) em.createQuery("SELECT r FROM Repository r WHERE r.name =:name AND r.id !=:id").setParameter("name", r.getName()).setParameter("id", r.getId()).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repository;
	}
	
	@Transactional
	public Repository getActiveRepository() {
		// TODO Auto-generated method stub
		Repository repository =new Repository();
		try {
			repository=(Repository) em.createQuery("SELECT r FROM Repository r WHERE r.status=1").getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repository;
	}
 

}
