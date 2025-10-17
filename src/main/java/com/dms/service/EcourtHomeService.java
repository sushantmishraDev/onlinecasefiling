package com.dms.service;

import java.text.SimpleDateFormat;
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
import com.dms.model.ActMaster;
import com.dms.model.ActsectionMaster;
import com.dms.model.CaseCheckListMapping;
import com.dms.model.CaseNoticeMaster;
import com.dms.model.CaseType;
import com.dms.model.Caveat;
import com.dms.model.CaveatCheckListMapping;
import com.dms.model.CaveatOld;
import com.dms.model.CourtFee;
import com.dms.model.CrimeDetails;
import com.dms.model.District;
import com.dms.model.Establishment;
import com.dms.model.ImpugnedOrder;
import com.dms.model.LowerCourtCaseType;
import com.dms.model.LowerCourtTypes;
import com.dms.model.Notice;
import com.dms.model.NoticeDepartmentMaster;
import com.dms.model.PetitionerDetails;
import com.dms.model.PoliceStation2024;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.RespondentDetails;
import com.dms.model.ScrutionRemark;
import com.dms.model.StNoDetails;
import com.dms.model.StampReporterData;
import com.dms.model.State;
import com.dms.model.TrialCourt;
import com.dms.model.User;

@Service
public class EcourtHomeService {

	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;

	// Session session=em.unwrap(Session.class);

	@Transactional
	public List<PetitionerDetails> getPetionerDetails() {

		List<PetitionerDetails> result = new ArrayList<PetitionerDetails>();

		try {
			result = em
					.createQuery(
							"SELECT pt FROM PetitionerDetails pt where pt.pt_rec_status =1")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public PetitionerDetails addPetitioner(PetitionerDetails pDetails) {

		PetitionerDetails pd = null;
		try {

			pd = em.merge(pDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pd;
	}

	@Transactional
	public PetitionerDetails deletePetitioner(User user, Long id) {
		PetitionerDetails oldPetitioner = null;
		PetitionerDetails pDetails = null;
		user.setMod_by(id);
		user.setMod_date(new Date());
		oldPetitioner = em.find(PetitionerDetails.class, id);
		oldPetitioner.setPt_rec_status(2);
		pDetails = em.merge(oldPetitioner);

		return pDetails;
	}

	@Transactional
	public List<RespondentDetails> getRespondentDetails() {
		List<RespondentDetails> result = new ArrayList<RespondentDetails>();

		try {
			result = em
					.createQuery(
							"SELECT rt FROM RespondentDetails rt where rt.rt_rec_status =1")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public RespondentDetails addRespondent(RespondentDetails rDetails) {
		RespondentDetails rd = null;
		try {

			rd = em.merge(rDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rd;
	}

	@Transactional
	public RespondentDetails deleteRespondent(User user, Long id) {
		RespondentDetails oldRespondent = null;
		RespondentDetails rDetails = null;
		user.setMod_by(id);
		user.setMod_date(new Date());
		oldRespondent = em.find(RespondentDetails.class, id);
		oldRespondent.setRt_rec_status(2);
		rDetails = em.merge(oldRespondent);

		return rDetails;
	}
	
	@Transactional
	public List<RegisteredCaseDetails> getDraftDetails(Long um_id) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -16);
		System.out.println("Date = "+ cal);
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nDate=s.format(cal.getTime());
	List<RegisteredCaseDetails> rcdDetails=null;
		
	rcdDetails= em.createQuery("SELECT rcd FROM RegisteredCaseDetails rcd where rcd.rcd_cr_by ="+um_id+" and rcd.rcd_stage_lid !=1000049L"
				+ " and rcd.rcd_id not in(\r\n" + 
				"select cm_rcd_mid from CaseCheckListMapping  where cm_cr_date  < '"+ nDate+"' and cm_rec_status=1) order by rcd.rcd_id ").getResultList();
/*	rcdDetails= em.createQuery("SELECT rcd FROM RegisteredCaseDetails rcd where rcd.rcd_cr_by ="+um_id+" "
			+ "and rcd.rcd_stage_lid in (1000042L,1000065L,1000036L,1000066L,1000067L)"
			+ " order by rcd.rcd_id ").getResultList();	*/
	
	return rcdDetails;
	}
	
	@Transactional
	public List<RegisteredCaseDetails> getDefectedDraftDetails(Long um_id) {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -16);
		System.out.println("Date = "+ cal);
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nDate=s.format(cal.getTime());
	List<RegisteredCaseDetails> rcdDetails=null;
		rcdDetails= em.createQuery("SELECT rcd FROM RegisteredCaseDetails rcd where rcd.rcd_cr_by ="+um_id+" and rcd.rcd_stage_lid =1000041L"
				+ " and rcd.rcd_id in(\r\n" + 
				"select cm_rcd_mid from CaseCheckListMapping  where cm_cr_date  < '"+ nDate+"' and cm_rec_status=1) order by rcd.rcd_id ").getResultList();
		return rcdDetails;
	}
	
	@Transactional
	public List<RegisteredCaseDetails> getPassedDraftDetails(Long um_id) {
	List<RegisteredCaseDetails> rcdDetails=null;
		rcdDetails= em.createQuery("SELECT rcd FROM RegisteredCaseDetails rcd where rcd.rcd_cr_by ="+um_id+" and rcd.rcd_stage_lid =1000049L  order by rcd.rcd_id ").getResultList();
		return rcdDetails;
	}

	
	@Transactional
	public List<PetitionerDetails> getPetitioner(Long id) {
		
		List<PetitionerDetails> result=null;
		Query query=null;
		query = em.createQuery(" SELECT pt from PetitionerDetails pt where pt.pt_rec_status=1 and pt.pt_rcd_mid=:id order by pt.pt_sequence asc").setParameter("id", id);
		result=query.getResultList();
		return result;
	}
	
	@Transactional
	public List<Notice> getNotice(Long id) {
		
		List<Notice> result=null;
		Query query=null;
		query = em.createQuery(" SELECT nt from Notice nt where nt.nt_rcd_mid=:id").setParameter("id", id);
		result=query.getResultList();
		return result;
	}


	@Transactional
	public List<RespondentDetails> getRespondent(Long id) {
		List<RespondentDetails> result=null;
		Query query=null;
		query = em.createQuery(" SELECT rt from RespondentDetails rt where rt.rt_rec_status=1 and rt.rt_rcd_mid=:id order by rt.rt_sequence asc").setParameter("id", id);
		result=query.getResultList();
		return result;
	}
       @Transactional
	public List<District> getDistrictList() {
		List<District> result=null;
		Query query=null;
		query = em.createQuery(" SELECT dt from District dt");
		result=query.getResultList();
		return result;
	}
       
       
       @Transactional
   	public List<CaseNoticeMaster> getCaseNoticeMasterList(String caseFlag) {
   		List<CaseNoticeMaster> result=null;
   		Query query=null;
   		query = em.createQuery(" SELECT cnm from CaseNoticeMaster cnm where cnm.cnm_flag in ('"+caseFlag+"','O')");
   		result=query.getResultList();
   		return result;
   	}
       
       @Transactional
      	public List<NoticeDepartmentMaster> getNoticeDeptMasterList() {
      		List<NoticeDepartmentMaster> result=null;
      		Query query=null;
      		query = em.createQuery(" SELECT cnm from NoticeDepartmentMaster cnm");
      		result=query.getResultList();
      		return result;
      	}
       
       @Transactional
   	public List<Establishment> getEstablishmentList() {
   		List<Establishment> result=null;
   		Query query=null;
   		query = em.createQuery(" SELECT dt from Establishment dt");
   		result=query.getResultList();
   		return result;
   	}

      @Transactional
	public List<State> getStateList() {
		List<State> result=null;
		Query query=null;
		query = em.createQuery(" SELECT st from State st");
		result=query.getResultList();
		return result;
	}

	public List<CaseType> getCasetypes() {
		List<CaseType> result=null;
		Query query=null;
		query = em.createQuery(" SELECT ct from CaseType ct where ct.ct_status=1");
		result=query.getResultList();
		return result;
	}
	
	@Transactional
	public StampReporterData getStampReporterData(Long id) {

		StampReporterData result=null;
	    Query query=null;
		query = em.createQuery("SELECT srd from StampReporterData srd where srd.srd_rcd_mid=:id  ORDER BY srd.srd_id DESC").setParameter("id", id).setMaxResults(1);
		try {
			result=(StampReporterData) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
     @Transactional
	public PetitionerDetails getFirstPetitioner(Long id) {
		PetitionerDetails result=new PetitionerDetails();
		try{
		Query query=null;
		query = em.createQuery("SELECT pt from PetitionerDetails pt where pt.pt_rec_status=1 and pt.pt_rcd_mid=:id order by pt_id asc").setParameter("id", id);
		result=(PetitionerDetails)query.setMaxResults(1).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
     
     @Transactional
 	public PoliceStation2024 getPoliceStn(Long id) {
 		PoliceStation2024 result=new PoliceStation2024();
 		try{
 		Query query=null;
 		query = em.createQuery("SELECT pt from PoliceStation2024 pt where  pt.pst_id=:id").setParameter("id", id);
 		result=(PoliceStation2024)query.getSingleResult();
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return result;

 	}
       @Transactional
	public RespondentDetails getFirstRespondent(Long id) {
		RespondentDetails result=new RespondentDetails();
		try{
		Query query=null;
		query = em.createQuery(" SELECT rt from RespondentDetails rt where rt.rt_rec_status=1 and rt.rt_rcd_mid=:id order by rt_id asc").setParameter("id", id);
		result=(RespondentDetails) query.setMaxResults(1).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
           return result;
}
@Transactional
	public List<ActDetails> getActDetails(Long id) {
		
		List<ActDetails> result=null;
		Query query=null;
		query = em.createQuery(" SELECT act from ActDetails act where act.act_rec_status=1 and act.act_rcd_mid=:id").setParameter("id", id);
		result=query.getResultList();
		return result;
	}
@Transactional
	public List<ImpugnedOrder> getImpugnedOrder(Long id) {
		List<ImpugnedOrder> result=null;
		Query query=null;
		query = em.createQuery(" SELECT io from ImpugnedOrder io where io.io_rec_status=1 and io.io_rcd_mid=:id").setParameter("id", id);
		result=query.getResultList();
		return result;
}

@Transactional
public List<CrimeDetails> getCrimeDetails(Long id) {
	List<CrimeDetails> result=null;
	Query query=null;
	query = em.createQuery(" SELECT io from CrimeDetails io where io.cd_rec_status=1 and io.cd_rcd_mid=:id").setParameter("id", id);
	result=query.getResultList();
	return result;
}

@Transactional
public List<StNoDetails> getSessionTrack(Long id) {
	List<StNoDetails> result=null;
	Query query=null;
	query = em.createQuery(" SELECT io from StNoDetails io where io.snd_rec_status=1 and io.snd_rcd_mid=:id").setParameter("id", id);
	result=query.getResultList();
	return result;
}
@Transactional
	public List<TrialCourt> getTrialCourt(Long id) {
		List<TrialCourt> result=null;
		Query query=null;
		query = em.createQuery(" SELECT tr from TrialCourt tr where tr.tr_rec_status=1 and tr.tr_rcd_mid=:id").setParameter("id", id);
		result=query.getResultList();
		return result;
}
   @Transactional
	public List<CourtFee> getCourtFee(Long id) {
		
		List<CourtFee> result=null;
		Query query=null;
		query = em.createQuery(" SELECT cf from CourtFee cf where cf.cf_rec_status=1 and cf.cf_rcd_mid=:id").setParameter("id", id);
		try {
		result=query.getResultList();
		}
		catch(Exception e) {
			
		}
		return result;
	}
   

   public List<ActMaster> getActMaster() {
	// TODO Auto-generated method stub
		List<ActMaster> result=null;
		Query query=null;
		query = em.createQuery("SELECT a from ActMaster a");
		result=query.getResultList();
		return result;
   }
   
   public List<ActsectionMaster> getActMasterNew(String type) {
		// TODO Auto-generated method stub
			List<ActsectionMaster> result=null;
			Query query=null;
			query = em.createQuery("SELECT distinct a.act_name_eng,act_code from ActsectionMaster a where a.old_new='"+type+"'");
			result=query.getResultList();
			return result;
	   }
   
   
   public List<ActsectionMaster> getSecMasterNew(Integer type) {
 		// TODO Auto-generated method stub
 			List<ActsectionMaster> result=null;
 			Query query=null;
 			query = em.createQuery("SELECT a from ActsectionMaster a where a.act_code="+type);
 			result=query.getResultList();
 			return result;
 	   }
   
   
   
   public Integer getDraftsByUserId(Long um_id) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM RegisteredCaseDetails d where  d.rcd_cr_by=:cr_by").setParameter("cr_by",um_id );
		result= Integer.parseInt(query.getSingleResult().toString());
	
		return result;
	
	}
   
   
   public List<CaseCheckListMapping> getCheckList(Long doc) {
		// TODO Auto-generated method stub
		List<CaseCheckListMapping> result=null;
		Query query=null;
		query = em.createQuery("SELECT c from CaseCheckListMapping c where c.cm_rec_status=1 and c.cm_rcd_mid=:id").setParameter("id", doc);
		result=query.getResultList();
		return result;
	}
   public List<CaveatCheckListMapping> getCaveatCheckList(Long doc) {
		// TODO Auto-generated method stub
		List<CaveatCheckListMapping> result=null;
		Query query=null;
		query = em.createQuery("SELECT c from CaveatCheckListMapping c where c.cm_rec_status=1 and c.cm_cav_mid=:id").setParameter("id", doc);
		result=query.getResultList();
		return result;
	}
   public List<LowerCourtCaseType> getLowerCourtCaseTypes() {
		// TODO Auto-generated method stub
		List<LowerCourtCaseType> result=null;
		Query query=null;
		query = em.createQuery("SELECT l from LowerCourtCaseType l");
		result=query.getResultList();
		return result;
	}

	public List<LowerCourtTypes> getLowerCourtList() {
		// TODO Auto-generated method stub
		List<LowerCourtTypes> result=null;
		Query query=null;
		query = em.createQuery("SELECT l from LowerCourtTypes l");
		result=query.getResultList();
		return result;
	}
	
	
	public Integer getCountByStage(Long lk_id, Long um_id) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM RegisteredCaseDetails d where d.rcd_stage_lid=:rcd_stage_lid and d.rcd_cr_by = :cr_by").setParameter("rcd_stage_lid", lk_id).setParameter("cr_by", um_id);
		result= Integer.parseInt(query.getSingleResult().toString());
	
		return result;
	}
	public Integer getDraftCount(Long um_id){
		Integer count=0;
		Query query=em.createQuery("SELECT Count(d) FROM RegisteredCaseDetails d where d.rcd_cr_by = :cr_by").setParameter("cr_by", um_id);
		count= Integer.parseInt(query.getSingleResult().toString());
		return count;
	}
	public Integer getCaveatCount(Long um_id){
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Caveat d where d.cav_cr_by = :cr_by").setParameter("cr_by", um_id);
		result= Integer.parseInt(query.getSingleResult().toString());
	
		return result;
	}
	public Integer getApplicationCount(Long um_id){
		Integer count=0;
		Query query=em.createQuery("SELECT Count(d) FROM Application d where d.ap_cr_by = :cr_by").setParameter("cr_by", um_id);
		count= Integer.parseInt(query.getSingleResult().toString());
		return count;
	}
	
	public Integer getCountByStageCaveat(Long lk_id, Long um_id) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Caveat d where d.cav_stage_lid=:rcd_stage_lid and d.cav_cr_by = :cr_by").setParameter("rcd_stage_lid", lk_id).setParameter("cr_by", um_id);
		result= Integer.parseInt(query.getSingleResult().toString());
	
		return result;
	}
	
	public Integer getCountByStageApplication(Long lk_id, Long um_id) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Application d where d.ap_stage_lid=:rcd_stage_lid and d.ap_cr_by = :cr_by").setParameter("rcd_stage_lid", lk_id).setParameter("cr_by", um_id);
		result= Integer.parseInt(query.getSingleResult().toString());
	
		return result;
	}
	
	@Transactional
	public List<Caveat> searchCaveat2(Long ct_type, String caseNo, Integer caseYear,String dates) {
		// TODO Auto-generated method stub
			List<Caveat> result = new ArrayList<Caveat>() ;
			
			Date d = new Date();//intialize your date to any date 
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.DATE, -90);
			Date dateBefore30Days = cal.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String format = formatter.format(dateBefore30Days);	
			
			try
			{
	
				Query query = em.createQuery(" SELECT c from Caveat c where c.cav_lc_case_type=:cav_lc_case_type and c.cav_lc_case_no=:cav_lc_case_no "
						+ "and c.cav_lc_case_year=:cav_lc_case_year and c.cav_cr_date > '"+format+"' and c.cav_judgmnt_date In ("+dates+") ")
				.setParameter("cav_lc_case_type", ct_type).setParameter("cav_lc_case_no", caseNo).setParameter("cav_lc_case_year", caseYear);
				
				result=	query.getResultList();	
			
			}
			catch(Exception e)
			{
			 	e.printStackTrace();
			}
			return result;
		
	}
	
	@Transactional
	public List<Caveat> searchCaveat(Long ct_type, Long dist_id, String dates) {
		// TODO Auto-generated method stub
			List<Caveat> result = new ArrayList<Caveat>() ;
			
			Date d = new Date();//intialize your date to any date 
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.DATE, -90);
			Date dateBefore30Days = cal.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String format = formatter.format(dateBefore30Days);			
			try
			{
				String dateQuery="";
				if(dates!="")
					dateQuery=" and c.cav_judgmnt_date In ("+dates+")";
				
				String sql="select c from Caveat c where c.cav_dist_mid="+dist_id
						+" and c.cav_cr_date > '"+format+"' "+dateQuery;
				
				System.out.println("new caveat sql="+sql);
				result = em.createQuery(sql).getResultList();
			
			}
			catch(Exception e)
			{
			 	e.printStackTrace();
			}
			return result;
		
	}

	public List<CaveatOld> searchCaveatOld(Long ct_type, Long dist_id, String dates) {
		// TODO Auto-generated method stub
		List<CaveatOld> result = new ArrayList<CaveatOld>() ;
		Date d = new Date();//intialize your date to any date 
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DATE, -90);
		Date dateBefore30Days = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(dateBefore30Days);
		try
		{
			String dateQuery="";
			if(dates!="")
				dateQuery=" and c.cav_judgmnt_date In ("+dates+")";
			
			
			String sql="select c from CaveatOld c where c.cav_dist_mid="+dist_id
					+" and c.cav_cr_date > '"+format+"' "+dateQuery;
			System.out.println("old caveat sql="+sql);
			result = em.createQuery(sql).getResultList();
		
		}
		catch(Exception e)
		{
		 	e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<RegisteredCaseDetails> getCaseDetailsByStage(Long um_id,Long stageId) {
		// TODO Auto-generated method stub
		List<RegisteredCaseDetails> rcdDetails=new ArrayList<RegisteredCaseDetails>();
		rcdDetails= em.createQuery("SELECT rcd FROM RegisteredCaseDetails rcd where rcd.rcd_assign_to =:um_id and rcd.rcd_stage_lid=:stageId order by rcd.rcd_id ").setParameter("um_id", um_id).setParameter("stageId", "stageId").getResultList();
		return rcdDetails;
	}

	@Transactional
	public District getDistrictById(Long id) {
		District result=null;
		result = (District) em.createQuery(" SELECT dt from District dt where dt.dt_id=:id").setParameter("id", id).getSingleResult();
	   return result;
	}
	  @Transactional 
	  public ScrutionRemark getRemark(Long id)
	  {
		  ScrutionRemark result=null;
		  try{
			  //Query query=null;
			  result =(ScrutionRemark) em.createQuery(" SELECT rt from ScrutionRemark rt where  rt.sr_rcd_mid=:id").setParameter("id", id).getSingleResult();
		  }
		  catch(Exception e){
	 
	  } return result;
	  }
	
}
