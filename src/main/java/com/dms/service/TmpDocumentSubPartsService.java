package com.dms.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.RegisteredCaseDetails;
import com.dms.model.TmpDocumentSubParts;



@Service
public class TmpDocumentSubPartsService 
{
	/*@PersistenceContext
	EntityManager em;*/
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	@Transactional
	public TmpDocumentSubParts save(TmpDocumentSubParts tdsp) {
		// TODO Auto-generated method stub

		TmpDocumentSubParts master = null;
    	try {	
    		master= em.merge(tdsp);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}
	
	@Transactional
	public TmpDocumentSubParts getDocument(Long dsp_id) 
	{
		TmpDocumentSubParts result=new TmpDocumentSubParts();
		
		try{
			result = (TmpDocumentSubParts) em.createQuery("SELECT tdsp FROM TmpDocumentSubParts tdsp  where tdsp.dsp_id="+dsp_id).getSingleResult();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}

}
