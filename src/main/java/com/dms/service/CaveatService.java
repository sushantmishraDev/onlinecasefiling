package com.dms.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.ActDetails;
import com.dms.model.CActDetails;
import com.dms.model.CaseType;
import com.dms.model.Caveat;
import com.dms.model.CaveatAuthority;
import com.dms.model.CaveatCheckListMapping;
import com.dms.model.CaveatCourtFee;
import com.dms.model.CaveatCrimeDetails;
import com.dms.model.CaveatDocuments;
import com.dms.model.CaveatExtraAdvocate;
import com.dms.model.CaveatPetitionerDetails;
import com.dms.model.CaveatRespondentDetails;
import com.dms.model.CaveatStage;
import com.dms.model.CrimeDetails;
import com.dms.model.District;
import com.dms.model.Document;
import com.dms.model.ExtarCaveatImpugnedOrder;
import com.dms.model.ExtraCaveatorDetails;
import com.dms.model.ImpugnedOrder;
import com.dms.model.LowerCourtCaseType;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.User;

@Service
public class CaveatService {
	/*
	 * @PersistenceContext private EntityManager em;
	 */

	@PersistenceContext(unitName = "persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;

	
	@Transactional
	public ExtarCaveatImpugnedOrder deleteExtraImpugnedOrder(User user, Long id) {
		ExtarCaveatImpugnedOrder oldOrder=null;
		ExtarCaveatImpugnedOrder newOrder=null;
		user.setMod_by(id);
	    user.setMod_date(new Date());
	    oldOrder=em.find(ExtarCaveatImpugnedOrder.class, id);
	    oldOrder.setEc_io_rec_status(2);
	    newOrder =em.merge(oldOrder);
		
		return newOrder;
	}
	
	@Transactional
	public List<CActDetails> getActDetails(Long id) {
		
		List<CActDetails> result=null;
		Query query=null;
		query = em.createQuery(" SELECT act from CActDetails act where act.cact_rec_status=1 and act.cact_cav_mid=:id").setParameter("id", id);
		result=query.getResultList();
		return result;
	}
	
	@Transactional
	public CActDetails addActDetails(CActDetails actDetails) {
		CActDetails act=null;
		 try {
			
			act=em.merge(actDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return act;
	}
	
	@Transactional
	public District getDistrictById(Long id) {
		District result=null;
		result = (District) em.createQuery(" SELECT dt from District dt where dt.dt_id=:id").setParameter("id", id).getSingleResult();
	   return result;
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
	public List<ExtarCaveatImpugnedOrder> getExtraImpugnedOrder(Long id) {
		List<ExtarCaveatImpugnedOrder> result=null;
		Query query=null;
		query = em.createQuery(" SELECT io from ExtarCaveatImpugnedOrder io where io.ec_io_rec_status=1 and io.ec_io_cav_mid=:id").setParameter("id", id);
		result=query.getResultList();
		return result;
}
	
	@Transactional
	public ExtarCaveatImpugnedOrder addExtraImpugnedOrder(ExtarCaveatImpugnedOrder ecio) {

		ExtarCaveatImpugnedOrder ecImpugnedorder = null;
		try {

			ecImpugnedorder = em.merge(ecio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ecImpugnedorder;
	}
	
	@Transactional
	public List<Caveat> getCaveatDetails(Long um_id) {
		List<Caveat> rcdDetails = null;
		rcdDetails = em
				.createQuery("SELECT cav FROM Caveat cav where cav.cav_cr_by =" + um_id + " order by cav.cav_id ")
				.getResultList();
		return rcdDetails;
	}

	@Transactional
	public Caveat getRegisterCaveat(Long id) {
		Caveat result = null;
		Query query = null;
		try {
			query = em.createQuery(" SELECT cav from Caveat cav where cav.cav_id=:id").setParameter("id", id);
			result = (Caveat) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Transactional
	public List<CaveatPetitionerDetails> getPetitioner(Long id) {

		List<CaveatPetitionerDetails> result = null;
		Query query = null;
		query = em.createQuery(
				" SELECT pt from CaveatPetitionerDetails pt where pt.cpt_rec_status=1 and pt.cpt_cav_mid=:id order by pt.cpt_sequence asc")
				.setParameter("id", id);
		result = query.getResultList();
		return result;
	}

	@Transactional
	public List<CaveatRespondentDetails> getRespondent(Long id) {
		List<CaveatRespondentDetails> result = null;
		Query query = null;
		query = em.createQuery(
				" SELECT rt from CaveatRespondentDetails rt where rt.crt_rec_status=1 and rt.crt_cav_mid=:id order by rt.crt_sequence asc")
				.setParameter("id", id);
		result = query.getResultList();
		return result;
	}
	
	@Transactional
	public List<CaveatExtraAdvocate> getExtraAdvocate(Long id) {
		List<CaveatExtraAdvocate> result = null;
		Query query = null;
		query = em.createQuery(
				" SELECT rt from CaveatExtraAdvocate rt where rt.cea_rec_status=1 and rt.cea_cav_mid=:id ")
				.setParameter("id", id);
		result = query.getResultList();
		return result;
	}
	@Transactional
	public List<ExtraCaveatorDetails> getExtraCaveator(Long id) {
		List<ExtraCaveatorDetails> result = null;
		Query query = null;
		query = em.createQuery(
				" SELECT ect from ExtraCaveatorDetails ect where ect.ect_rec_status=1 and ect.ect_cav_mid=:id order by ect.ect_sequence asc")
				.setParameter("id", id);
		result = query.getResultList();
		return result;
	}
	
	@Transactional
	public Caveat save(Caveat c) {

		Caveat caveat = null;
		try {
			caveat = em.merge(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caveat;
	}
	
	@Transactional
	public CaveatCrimeDetails deleteCrimeDetails(User user, Long id) {
		CaveatCrimeDetails oldOrder=null;
		CaveatCrimeDetails newOrder=null;
	    oldOrder=em.find(CaveatCrimeDetails.class, id);
		oldOrder.setCcd_mod_by(user.getUm_id());
	    oldOrder.setCcd_mod_date(new Date());
	    oldOrder.setCcd_rec_status(2);
	    newOrder =em.merge(oldOrder);
		
		return newOrder;
	}
	
	@Transactional
	public List<CaveatCrimeDetails> getCrimeDetails(Long id) {
		List<CaveatCrimeDetails> result=null;
		Query query=null;
		query = em.createQuery(" SELECT io from CaveatCrimeDetails io where io.ccd_rec_status=1 and io.ccd_cav_mid=:id").setParameter("id", id);
		result=query.getResultList();
		return result;
	}
	
	
	@Transactional
	public List<CaveatAuthority> getAuthorityDetails(Long id) {
		List<CaveatAuthority> result=null;
		Query query=null;
		query = em.createQuery("SELECT io from CaveatAuthority io where io.ca_rec_status=1 and io.ca_cav_mid=:id order by io.ca_id").setParameter("id", id);
		result=query.getResultList();
		return result;
	}
	
	@Transactional
	public CaveatCrimeDetails addCrimeFetails(CaveatCrimeDetails imOrder) {
		CaveatCrimeDetails io=null;
		 try {
			
			io=em.merge(imOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return io;
	}
	
	@Transactional
	public CaveatAuthority addCaveatAuthority(CaveatAuthority imOrder) {
		CaveatAuthority io=null;
		 try {
			
			io=em.merge(imOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return io;
	}

	@Transactional
	public Caveat addCaveat(Caveat caveat) {
		Caveat cav = null;
		try {

			int year = Calendar.getInstance().get(Calendar.YEAR);
			cav = em.merge(caveat);
			cav.setCav_draft_no(cav.getCav_id() + "_" + year);
			cav = em.merge(cav);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cav;
	}

	@Transactional
	public CaveatStage saveCaveatStage(CaveatStage cs) {

		CaveatStage caveatstage = null;
		try {
			caveatstage = em.merge(cs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caveatstage;
	}

	@Transactional
	public CaveatDocuments saveCaveatDocument(CaveatDocuments cs) {

		CaveatDocuments caveatdocument = null;
		try {
			caveatdocument = em.merge(cs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caveatdocument;
	}

	@Transactional
	public Long getCPSequenceCount(Long id) {

		Long result = 0L;
		Query query = null;
		try {
			query = em.createQuery("SELECT count(pt.cpt_id) FROM CaveatPetitionerDetails pt where pt.cpt_cav_mid=:id")
					.setParameter("id", id);
			result = (Long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public CaveatPetitionerDetails addPetitioner(CaveatPetitionerDetails pDetails) {

		CaveatPetitionerDetails pd = null;
		try {

			pd = em.merge(pDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pd;
	}

	@Transactional
	public CaveatPetitionerDetails deletePetitioner(User user, Long id) {
		CaveatPetitionerDetails oldPetitioner = null;
		CaveatPetitionerDetails pDetails = null;
		
		oldPetitioner = em.find(CaveatPetitionerDetails.class, id);
		
		oldPetitioner.setCpt_mod_by(user.getUm_id());
		oldPetitioner.setCpt_mod_date(new Date());
		oldPetitioner.setCpt_rec_status(2);
		
		pDetails = em.merge(oldPetitioner);

		return pDetails;
	}

	@Transactional
	public Long getCRSeqCount(Long id) {

		Long result = 0L;

		try {
			result = (Long) em
					.createQuery("SELECT count(rt.crt_id) FROM CaveatRespondentDetails rt where rt.crt_cav_mid=:id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public CaveatRespondentDetails addRespondent(CaveatRespondentDetails rDetails) {
		CaveatRespondentDetails rd = null;
		try {

			rd = em.merge(rDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rd;
	}
	
	@Transactional
	public CaveatExtraAdvocate addExtraAdvocate(CaveatExtraAdvocate rDetails) {
		CaveatExtraAdvocate rd = null;
		try {

			rd = em.merge(rDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rd;
	}

	@Transactional
	public CaveatRespondentDetails deleteRespondent(Long um_id, Long id) {
		
		CaveatRespondentDetails rDetails = null;
		
		rDetails = em.find(CaveatRespondentDetails.class, id);
		
		rDetails.setCrt_mod_by(um_id);
		rDetails.setCrt_mod_date(new Date());
		rDetails.setCrt_rec_status(2);
		
		rDetails = em.merge(rDetails);

		return rDetails;
	}
	
	
	@Transactional
	public CaveatExtraAdvocate deleteExtraAdvocatge(Long id) {
		
		CaveatExtraAdvocate rDetails = null;
		
		rDetails = em.find(CaveatExtraAdvocate.class, id);
		

		rDetails.setCea_rec_status(2);
		
		rDetails = em.merge(rDetails);

		return rDetails;
	}

	@Transactional
	public ExtraCaveatorDetails addExtraCaveatorDetails(ExtraCaveatorDetails ectDetails) {

		ExtraCaveatorDetails pd = null;
		try {

			pd = em.merge(ectDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pd;
	}
	
	@Transactional
	public ExtraCaveatorDetails deleteExtraCaveator(User user, Long id) {
		ExtraCaveatorDetails oldExtraCaveator = null;
		ExtraCaveatorDetails ectDetails = null;
		
		oldExtraCaveator = em.find(ExtraCaveatorDetails.class, id);
		
		oldExtraCaveator.setEct_mod_by(user.getUm_id());
		oldExtraCaveator.setEct_mod_date(new Date());
		oldExtraCaveator.setEct_rec_status(2);
		
		ectDetails = em.merge(oldExtraCaveator);

		return ectDetails;
	}
	
	@Transactional
	public CaveatCourtFee addCourtFee(CaveatCourtFee courtFee) {
		CaveatCourtFee result = null;

		result = em.merge(courtFee);

		return result;
	}

	@Transactional
	public Long getECTsequenceCount(Long id) {

		Long result = 0L;
		Query query = null;
		try {
			query = em.createQuery("SELECT count(ect.ect_id) FROM ExtraCaveatorDetails ect where ect.ect_cav_mid=:id")
					.setParameter("id", id);
			result = (Long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Transactional
	public List<CaveatCourtFee> getCaveatCourtFee(Long id) {

		List<CaveatCourtFee> result = null;
		Query query = null;
		query = em.createQuery(" SELECT cf from CaveatCourtFee cf where cf.ccf_rec_status=1 and cf.ccf_cav_mid=:id")
				.setParameter("id", id);
		result = query.getResultList();
		return result;
	}

	@Transactional
	public List<CaveatDocuments> getUploadedCaveates(Long cav_id) {
		List<CaveatDocuments> result = new ArrayList<CaveatDocuments>();

		try {
			result = em.createQuery(
					"SELECT cd FROM CaveatDocuments cd  where cd.cd_cav_mid=" + cav_id + " and cd.cd_rec_status=1 ")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public Caveat getByPk(Long id) {
		Caveat result = null;
		try {
			result = (Caveat) em.createQuery("select c from Caveat c where c.cav_id = :cav_id")
					.setParameter("cav_id", id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public CaveatPetitionerDetails getFirstPetitioner(Long id) {
		CaveatPetitionerDetails result = new CaveatPetitionerDetails();
		try {
			Query query = null;
			query = em.createQuery(
					"SELECT pt from CaveatPetitionerDetails pt where pt.cpt_rec_status=1 and pt.cpt_cav_mid=:id order by cpt_id asc")
					.setParameter("id", id);
			result = (CaveatPetitionerDetails) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public CaveatRespondentDetails getFirstRespondent(Long id) {
		CaveatRespondentDetails result = new CaveatRespondentDetails();
		try {
			Query query = null;
			query = em.createQuery(
					" SELECT rt from CaveatRespondentDetails rt where rt.crt_rec_status=1 and rt.crt_cav_mid=:id order by crt_id asc")
					.setParameter("id", id);
			result = (CaveatRespondentDetails) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<CaveatCourtFee> getCourtFee(Long id) {

		List<CaveatCourtFee> result = null;
		Query query = null;
		query = em.createQuery(" SELECT cf from CaveatCourtFee cf where cf.ccf_rec_status=1 and cf.ccf_cav_mid=:id")
				.setParameter("id", id);
		result = query.getResultList();
		return result;
	}

	@Transactional
	public Long getDiarySequence() {

		Long sequence = 0L;

		String query = "select nextval('diary_sequence')";

		try {
			sequence = ((BigInteger) em.createNativeQuery(query).getResultList().get(0)).longValue();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sequence;
	}

	public List<Caveat> getDraftDetails(Long um_id) {
		// TODO Auto-generated method stub
		List<Caveat> cavDetails = null;
		cavDetails = em
				.createQuery("SELECT cav FROM Caveat cav where cav.cav_assign_to =" + um_id + " order by cav.cav_id ")
				.getResultList();
		return cavDetails;
	}

	@Transactional
	public CaveatDocuments getCaveatDocuments(Long cd_id) {
		CaveatDocuments result = new CaveatDocuments();

		try {
			result = (CaveatDocuments) em.createQuery("SELECT cd FROM CaveatDocuments cd  where cd.cd_id=" + cd_id)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean deleteCaveatDocuments(Long id) {
		boolean flag = false;
		CaveatDocuments oldDocument = null;
		oldDocument = em.find(CaveatDocuments.class, id);
		em.remove(oldDocument);
		flag = true;
		return flag;
	}

	public List<CaveatCheckListMapping> getCaveatCheckList(Long doc) {
		// TODO Auto-generated method stub
		List<CaveatCheckListMapping> result = null;
		Query query = null;
		query = em.createQuery("SELECT c from CaveatCheckListMapping c where c.cm_rec_status=1 and c.cm_cav_mid=:id")
				.setParameter("id", doc);
		result = query.getResultList();
		return result;
	}

	public List<CaveatCheckListMapping> getCaveatReportingHistory(Long doc) {
		// TODO Auto-generated method stub
		List<CaveatCheckListMapping> result = null;
		Query query = null;
		query = em.createQuery("SELECT c from CaveatCheckListMapping c where c.cm_cav_mid=:id").setParameter("id", doc);
		result = query.getResultList();
		return result;
	}

	@Transactional
	public boolean checkDateValidity(Long id) {
		boolean flag = false;

		Date scrutinydate = (Date) em
				.createQuery("SELECT cm_cr_date from CaveatCheckListMapping  where cm_cav_mid =:id order by cm_id desc")
				.setParameter("id", id).setMaxResults(1).getSingleResult();

		long difference = (scrutinydate.getTime() - new Date().getTime()) / 86400000;
		long days = Math.abs(difference);
		if (days <= 15)
			flag = true;

		return flag;
	}

}
