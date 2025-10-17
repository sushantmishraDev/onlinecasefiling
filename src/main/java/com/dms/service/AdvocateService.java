package com.dms.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cms.model.Advocate;
import com.dms.model.AdvocateEfiling;
import com.dms.model.Register;
import com.dms.model.User;

@Service
public class AdvocateService {

	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	private DataSource datasource;
	
	@PersistenceContext(unitName="persistenceUnitCMS")
	@Qualifier(value = "entityManagerFactoryCMS")
	private EntityManager emCMS;
	
	@Transactional("transactionManagerCMS")
	public Advocate getByRollNo(Register register) {
		
		Advocate a= new Advocate();
		try {
			Query query  =  emCMS.createQuery("SELECT a from Advocate a WHERE a.rollNo =:rollNo and a.enrollNo=:enrollNo and a.enrollYear=:enrollYear");
			query.setParameter("rollNo", register.getRollNo());
			query.setParameter("enrollNo", register.getEnrollNo());
			query.setParameter("enrollYear", register.getEnrollYear());
			a= (Advocate) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	
	@Transactional
	public AdvocateEfiling getByMobile(String mob) {
		
		AdvocateEfiling a= new AdvocateEfiling();
		try {
			Query query  =  em.createQuery("SELECT a from AdvocateEfiling a WHERE a.mobile =:mob");
			query.setParameter("mob", mob);
			a= (AdvocateEfiling) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	@Transactional
	public AdvocateEfiling save(AdvocateEfiling user) {
		// TODO Auto-generated method stub
		AdvocateEfiling master = null;
    	try {	
    		master= em.merge(user);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}
	
	@Transactional
	public Advocate getAdvocateByRollNo(String enroll) {
		
		Advocate a= null;
		try {
			Query query  =  emCMS.createQuery("SELECT a from Advocate a WHERE a.rollNo =:rollNo");
			query.setParameter("rollNo", enroll);
			a= (Advocate) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
}
