package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.OLReport;
import com.dms.model.ObjectTree;
@Service
public class OLReportService {
	
	@PersistenceContext
	public EntityManager em;
	
	@Transactional
	public OLReport save(OLReport s) {

		OLReport master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	public List<OLReport> getAll() {
		List<OLReport> result =  new ArrayList<OLReport>();
		Query query = em.createQuery("select ol from OLReport ol order by ol.ol_id");		
		try {
			result = query.getResultList();
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return result;
		}
		
	}

	public OLReport getByPK(Long ol_id) {
		// TODO Auto-generated method stub
		OLReport olReport =  new OLReport();
		Query query = em.createQuery("select ol from OLReport ol where ol.ol_id=:ol_id").setParameter("ol_id", ol_id);		
		try {
			olReport = (OLReport) query.getSingleResult();
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity does not exist.");
		} 
		
			return olReport;
	}
}
