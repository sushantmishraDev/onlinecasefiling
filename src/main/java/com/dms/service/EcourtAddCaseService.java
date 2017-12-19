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

import com.dms.model.ActDetails;
import com.dms.model.CaseType;
import com.dms.model.CourtFee;
import com.dms.model.ImpugnedOrder;
import com.dms.model.IndexField;
import com.dms.model.LowerCourtCaseType;
import com.dms.model.PetitionUploaded;
import com.dms.model.PetitionerDetails;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.RespondentDetails;
import com.dms.model.TrialCourt;
import com.dms.model.User;

@Service
public class EcourtAddCaseService 
{
	@PersistenceContext
	EntityManager em;
	
	
	
	@Transactional
	public List<IndexField> getDocumentTypes() {
		
		List<IndexField> result=new ArrayList<IndexField>();
		
		try{
			result = em.createQuery("SELECT idf FROM IndexField idf where idf.if_id =4 ").getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	@Transactional
	public List<PetitionerDetails> getPetionerDetails() {
		
		List<PetitionerDetails> result=new ArrayList<PetitionerDetails>();
		
		try{
			result = em.createQuery("SELECT pt FROM PetitionerDetails pt where pt.pt_rec_status =1").getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}

    @Transactional
	public PetitionerDetails addPetitioner(PetitionerDetails pDetails) {
    	
    	PetitionerDetails pd=null;
    	 try {
    		
			pd=em.merge(pDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return pd;
	}
    @Transactional
	public PetitionerDetails deletePetitioner(User user, Long id) {
		PetitionerDetails oldPetitioner=null;
		PetitionerDetails pDetails=null;
	    user.setMod_by(id);
	    user.setMod_date(new Date());
	    oldPetitioner=em.find(PetitionerDetails.class, id);
	    oldPetitioner.setPt_rec_status(2);
	    pDetails =em.merge(oldPetitioner);
		
		return pDetails;
	}
@Transactional
public List<RespondentDetails> getRespondentDetails() {
	List<RespondentDetails> result=new ArrayList<RespondentDetails>();
	
	try{
		result = em.createQuery("SELECT rt FROM RespondentDetails rt where rt.rt_rec_status =1").getResultList();
	}catch(Exception e)	{
		e.printStackTrace();
	}
	return result;
}

@Transactional
public RespondentDetails addRespondent(RespondentDetails rDetails) {
	RespondentDetails rd=null;
	 try {
		
		rd=em.merge(rDetails);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return rd;
}
@Transactional
public RespondentDetails deleteRespondent(User user, Long id) {
	RespondentDetails oldRespondent=null;
	RespondentDetails rDetails=null;
	user.setMod_by(id);
    user.setMod_date(new Date());
    oldRespondent=em.find(RespondentDetails.class, id);
    oldRespondent.setRt_rec_status(2);
    rDetails =em.merge(oldRespondent);
	
	return rDetails;
}



@Transactional
public List<ImpugnedOrder> getImpugnedOrder() {
	List<ImpugnedOrder> result=new ArrayList<ImpugnedOrder>();
	
	try{
		result = em.createQuery("SELECT io FROM ImpugnedOrder io  where io.io_rec_status=1").getResultList();
	}catch(Exception e)	{
		e.printStackTrace();
	}
	return result;
}



@Transactional
public ImpugnedOrder deleteImpugnedOrder(User user, Long id) {
	ImpugnedOrder oldOrder=null;
	ImpugnedOrder newOrder=null;
	user.setMod_by(id);
    user.setMod_date(new Date());
    oldOrder=em.find(ImpugnedOrder.class, id);
    oldOrder.setIo_rec_status(2);
    newOrder =em.merge(oldOrder);
	
	return newOrder;
}



@Transactional
public ImpugnedOrder addImpugnedOrder(ImpugnedOrder imOrder) {
	ImpugnedOrder io=null;
	 try {
		
		io=em.merge(imOrder);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return io;
}



@Transactional
public List<ActDetails> getActDetails() {
	List<ActDetails> result=new ArrayList<ActDetails>();
	
	try{
		result = em.createQuery("SELECT act FROM ActDetails act  where act.act_rec_status=1").getResultList();
	}catch(Exception e)	{
		e.printStackTrace();
	}
	return result;
}

@Transactional
public ActDetails addActDetails(ActDetails actDetails) {
	ActDetails act=null;
	 try {
		
		act=em.merge(actDetails);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return act;
}

@Transactional
public ActDetails deleteActDetails(User user, Long id) {
	ActDetails oldAct=null;
	ActDetails newAct=null;
	user.setMod_by(id);
    user.setMod_date(new Date());
    oldAct=em.find(ActDetails.class, id);
    oldAct.setAct_rec_status(2);
    newAct =em.merge(oldAct);
	return newAct;
}



@Transactional
public RegisteredCaseDetails addRegisterCase(RegisteredCaseDetails resCaseDetails) {
	RegisteredCaseDetails rcd=null;
	 try {
		 RegisteredCaseDetails rcdOld=null;
	
		 int year = Calendar.getInstance().get(Calendar.YEAR);
		rcd=em.merge(resCaseDetails);
		
		rcdOld=em.find(RegisteredCaseDetails.class,rcd.getRcd_id());
		
		rcdOld.setRcd_draft_no(rcd.getRcd_id()+"_"+year);
		
		  rcd= em.merge(rcdOld);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return rcd;
}



@Transactional
public RegisteredCaseDetails getRegisterCase(Long id) {

	RegisteredCaseDetails result=null;
    Query query=null;
	try {
		query = em.createQuery(" SELECT rcd from RegisteredCaseDetails rcd where rcd.rcd_id=:id").setParameter("id", id);
		result=(RegisteredCaseDetails) query.getSingleResult();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return result;
}



@Transactional
public List<TrialCourt> getTrialcourt() {
List<TrialCourt> result=new ArrayList<TrialCourt>();
	
	try{
		result = em.createQuery("SELECT tr FROM TrialCourt tr  where tr.tr_rec_status=1").getResultList();
	}catch(Exception e)	{
		e.printStackTrace();
	}
	return result;
}

	
@Transactional
public TrialCourt addTrialCourt(TrialCourt tCourt) {
	TrialCourt tc=null;
	 try {
		
		tc=em.merge(tCourt);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return tc;
}



@Transactional
public TrialCourt deleteTrialCourt(User user, Long id) {
	TrialCourt oldAct=null;
	TrialCourt newAct=null;
	user.setMod_by(id);
    user.setMod_date(new Date());
    oldAct=em.find(TrialCourt.class, id);
    oldAct.setTr_rec_status(2);
    newAct =em.merge(oldAct);
	return newAct;
}



@Transactional
public Long getSequenceCount(Long id) {

	Long result=null;
	 Query query=null;
	try{
		query = em.createQuery("SELECT count(pt.pt_id) FROM PetitionerDetails pt where pt.pt_rcd_mid=:id").setParameter("id", id);
	   result= (Long) query.getSingleResult();
	}catch(Exception e)	{
		e.printStackTrace();
	}
	return result;
}

@Transactional
public Long getSeqCount(Long id) {

	Long result=null;
	
	try{
		result = (Long) em.createQuery("SELECT count(rt.rt_id) FROM RespondentDetails rt where rt.rt_rcd_mid=:id").setParameter("id", id).getSingleResult();
	}catch(Exception e)	{
		e.printStackTrace();
	}
	return result;
}



@Transactional
public CourtFee addCourtFee(CourtFee courtFee) {
	CourtFee result=null;
    
	  result=em.merge(courtFee);
	
	return result;
}

@Transactional
public RegisteredCaseDetails saveCaseDetails(RegisteredCaseDetails r) {
	RegisteredCaseDetails result=null;
    
	  result=em.merge(r);
	
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

	@Transactional
	public CourtFee deleteCourtFee(User user, Long id) {
		CourtFee oldCourt=null;
		CourtFee newCourt=null;
		user.setMod_by(id);
	    user.setMod_date(new Date());
	    oldCourt=em.find(CourtFee.class, id);
	    oldCourt.setCf_rec_status(2);
	    newCourt =em.merge(oldCourt);
		return newCourt;
	}

	@Transactional
	public RegisteredCaseDetails getByPk(Long id) {
		RegisteredCaseDetails result = (RegisteredCaseDetails) em.createQuery("select rcd from RegisteredCaseDetails rcd where rcd.rcd_id = :rcd_id").setParameter("rcd_id", id).getSingleResult();
		return result;
	}
	
	@Transactional
	public Long getDocumentCount(Long rcd_id,Long if_id) {
		Long count=0L;
		count= (Long) em.createQuery("select count(*) from TmpDocumentSubParts tdsp where tdsp.dsp_fd_mid = :rcd_id and tdsp.dsp_if_mid= :if_id ").setParameter("rcd_id", rcd_id).setParameter("if_id", if_id).getSingleResult();
		return count;
	}
	
	@Transactional
	public IndexField getIndexField(Long if_id) {
		IndexField idf = new IndexField() ;
		try{
			idf = (IndexField) em.createQuery("SELECT idf FROM IndexField idf where idf.if_id ="+if_id).getSingleResult();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return idf;
	}
	
	@Transactional
	public List<PetitionUploaded> getUploadedPetition(Long rcd_id) {
		List<PetitionUploaded> result=new ArrayList<PetitionUploaded>();
		
		try{
			result = em.createQuery("SELECT pu FROM PetitionUploaded pu  where pu.pu_rcd_mid="+rcd_id+" and pu.pu_rec_status=1 ").getResultList();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		return result;
	}
	
	 @Transactional
	 public boolean deletePetition(Long id) 
	 {
		 boolean flag=false;
		 	PetitionUploaded oldDocument=null;
		 	oldDocument=em.find(PetitionUploaded.class, id);
		    em.remove(oldDocument);
		    flag= true;
		    return flag;
	 }
	 @Transactional
		public PetitionUploaded savePetitionUploaded(PetitionUploaded pu) {
			// TODO Auto-generated method stub

			PetitionUploaded master = null;
	    	try {	
	    		master= em.merge(pu);	    	
		    }catch (Exception e) {		
		    	e.printStackTrace();
			}
	    	return master;
		}
		
		@Transactional
		public PetitionUploaded getPetitionUploaded(Long pu_id) 
		{
			PetitionUploaded result=new PetitionUploaded();
			
			try{
				result = (PetitionUploaded) em.createQuery("SELECT pu FROM PetitionUploaded pu  where pu.pu_id="+pu_id).getSingleResult();
			}catch(Exception e)	{
				e.printStackTrace();
			}
			return result;
		}
		
		@Transactional
		public CaseType getCaseTypeById(Long id) {
			
			CaseType r= new CaseType();
			try {
				Query query  =  em.createQuery("SELECT r from CaseType r WHERE r.ct_id =:id");
				query.setParameter("id", id);
				r= (CaseType) query.getSingleResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return r;
		}
		@Transactional
		public LowerCourtCaseType getLCCaseTypeById(Long id) {
			
			LowerCourtCaseType r= new LowerCourtCaseType();
			try {
				Query query  =  em.createQuery("SELECT r from LowerCourtCaseType r WHERE r.ct_id =:id");
				query.setParameter("id", id);
				r= (LowerCourtCaseType) query.getSingleResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return r;
		}



}







