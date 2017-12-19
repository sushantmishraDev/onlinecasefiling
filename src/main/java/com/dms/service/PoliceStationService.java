package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.MetaData;
import com.dms.model.PoliceStation;



@Service
public class PoliceStationService {
	
	@PersistenceContext
	private EntityManager em;
	

	@Transactional
	public List<PoliceStation> getAll() {
		List<PoliceStation> result = em.createQuery("Select p from PoliceStation p  order by p.ps_name").getResultList();
		return result;
	}
	
	@Transactional
	public List<Object> getByLocation(MetaData metadata) {
		List<Object> result = new ArrayList<Object>();
		if(metadata.getMd_value().equals(null)){
			Query query = em.createQuery("Select p.ps_id as id,p.ps_name from PoliceStation p where p.ps_rec_status=1" );
			result = query.getResultList();
			
			return result;
		}else{
			Query query = em.createQuery("Select p.ps_id as id,p.ps_name from PoliceStation p where p.ps_rec_status=1 AND p.ps_location=:ps_location" );
			query.setParameter("ps_location", Long.parseLong(metadata.getMd_value()));
			result = query.getResultList();
			
			return result;
		}
		
				
	}
	
	@Transactional
	public PoliceStation checkDuplicate(String ps_name,Long ps_location,Long ps_id) {
	PoliceStation result = new PoliceStation();
	if(ps_id==null)
	{
	try{
		Query query=em.createQuery("Select p from PoliceStation p where p.ps_name= '"+ps_name+"'  and p.ps_location= "+ps_location);
		System.out.println(query);
		 result = (PoliceStation) query.getSingleResult();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	}
	else
	{
	try{
		Query query  =  em.createQuery("Select p from PoliceStation p where p.ps_name= '"+ps_name+"'  and p.ps_location= "+ps_location+" and p.ps_id != "+ps_id);
		result=(PoliceStation) query.getSingleResult();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	}
		
		return result;
	}

@Transactional
	public PoliceStation save(PoliceStation p) {
		PoliceStation policeStation = null;
		try {
			policeStation = em.merge(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return policeStation;
	}
}
