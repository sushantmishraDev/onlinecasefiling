package com.dms.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Amendment;
import com.dms.model.CaseFileDetail;
import com.dms.model.RegisteredCaseDetails;
import com.dms.model.User;

@Service
public class AmendmentService {
	
	/*@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em2;*/
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	@PersistenceContext(unitName="persistenceUnitCMS")
	@Qualifier(value = "entityManagerFactoryCMS")
	private EntityManager em3;
	

	public RegisteredCaseDetails getRegisterCase(Long fd_case_type, Integer fd_case_no, Integer fd_case_year) {
		RegisteredCaseDetails result=new RegisteredCaseDetails();
		try{
		Query query=null;
		query = em.createQuery("SELECT rcd from RegisteredCaseDetails rcd where rcd.rcd_ct_id=:fd_case_type and rcd.rcd_case_no=:fd_case_no and rcd.rcd_case_year=:fd_case_year").setParameter("fd_case_type", fd_case_type).setParameter("fd_case_no", fd_case_no).setParameter("fd_case_year", fd_case_year);
		result=(RegisteredCaseDetails)query.setMaxResults(1).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;		
	}

	public List<User> getApplicationUsers(Long fd_id,Long ap_stage_lid) {
		// TODO Auto-generated method stub
		List<User> result=new ArrayList<User>();
		try{
		Query query=null;
		query = em.createQuery("SELECT u from User u where u.um_id IN (select distinct(ap_cr_by) from Application where ap_fd_mid=:ap_fd_mid and ap_stage_lid=:ap_stage_lid)").setParameter("ap_fd_mid", fd_id).setParameter("ap_stage_lid", ap_stage_lid);
		result=query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public CaseFileDetail getCaseFile(Long fd_id) {
		CaseFileDetail result=null;
	    try {
			String query=" SELECT cfd from CaseFileDetail cfd where cfd.fd_id=:fd_id";
			result= (CaseFileDetail) em.createQuery(query).setParameter("fd_id", fd_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public List<User> searchUser(String name) {
		// TODO Auto-generated method stub
		List<User> result=new ArrayList<>();
	    try {
			String query="SELECT u from User u where lower(u.um_fullname) like '%"+name.toLowerCase()+"%' ";
			result= em.createQuery(query).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	@Transactional
	public Amendment saveAmendment(Amendment amendment) {
		// TODO Auto-generated method stub
		Amendment master = null;
		try {
			master = em.merge(amendment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	public Amendment getAmendment(Long am_id) {
		// TODO Auto-generated method stub
		Amendment master = null;
	    try {
			String query="SELECT a from Amendment a where a.am_id=:am_id";
			master= (Amendment) em.createQuery(query).setParameter("am_id", am_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}
	
	
	/////////// Sushant//////////////
	
	public String getLabel(Long am_fd_mid) {
		// TODO Auto-generated method stub
		String master = null;
	    try {
			String query="select ct.ct_label from CaseType ct  where ct.ct_id =(select cfd.fd_case_type from CaseFileDetail cfd  where cfd.fd_id=:am_fd_mid)";
			master= (String) em.createQuery(query).setParameter("am_fd_mid", am_fd_mid).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}
	/*public String getFileName(Long am_at_mid,int am_document_no,int am_document_year,Long am_fd_mid) {
		// TODO Auto-generated method stub
		int at_id=(am_at_mid !=null ? am_at_mid.intValue() : 0);
		CaseFileDetail cfd=getCaseFile(am_fd_mid);
		String master = null;
	    try {
			String query="select sd_document_name from sub_documents  where " + 
					" sd_fd_mid =(select fd_id from case_file_details  where fd_case_no  =:fd_case_no and fd_case_type=:fd_case_type and fd_case_year=:fd_case_year) and";
			
			if(at_id ==0) {
				query+=" sd_if_mid  =1";
				
				master= (String) em2.createNativeQuery(query).setParameter("fd_case_no", cfd.getFd_case_no()).setParameter("fd_case_type", cfd.getCaseType()).setParameter("fd_case_year", cfd.getFd_case_year()).getSingleResult();
			}
			else {
				query+=" sd_if_mid  =14 and sd_document_id=:am_at_mid and sd_document_no=:am_document_no and sd_document_year=:am_document_year";
			    
				master= (String) em2.createNativeQuery(query).setParameter("am_at_mid", am_at_mid).setParameter("am_document_no", am_document_no).setParameter("am_document_year", am_document_year).setParameter("fd_case_no", cfd.getFd_case_no()).setParameter("fd_case_type", cfd.getCaseType()).setParameter("fd_case_year", cfd.getFd_case_year()).getSingleResult();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}*/
	
	public BigInteger getFileCount(Long am_at_mid,int am_document_no,int am_document_year,Long am_fd_mid) {
		// TODO Auto-generated method stub
		int at_id=(am_at_mid !=null ? am_at_mid.intValue() : 0);
		BigInteger master = null;
	    try {
			String query="select count(am_id) from amendments  where " + 
					" am_fd_mid =:am_fd_mid";
			
			if(at_id ==0) {
				
				master= (BigInteger) em.createNativeQuery(query).setParameter("am_fd_mid", am_fd_mid).getSingleResult();
			}
			else {
				query+=" and am_at_mid  =:am_at_mid and  am_document_no=:am_document_no and am_document_year=:am_document_year";
			    
				master= (BigInteger) em.createNativeQuery(query).setParameter("am_at_mid", am_at_mid).setParameter("am_document_no", am_document_no).setParameter("am_document_year", am_document_year).setParameter("am_fd_mid", am_fd_mid).getSingleResult();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}
	

	/*@Transactional("transactionManagerDMS")
    public void updateSubDocument(Long am_fd_mid,String filename) {
		// TODO Auto-generated method stub
		int master = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		CaseFileDetail cfd=getCaseFile(am_fd_mid);
		Date nDate=new Date();
		try {
			 nDate=sdf.parse(sdf.format(new Date()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
	    try {
			String query="update sub_documents SET sd_mod_date=:sd_mod_date where  sd_document_name=:sd_document_name and sd_fd_mid =(select fd_id from case_file_details  where fd_case_no  =:fd_case_no and fd_case_type=:fd_case_type and fd_case_year=:fd_case_year)";
			master= em2.createNativeQuery(query).setParameter("sd_mod_date", nDate).setParameter("sd_document_name", filename).setParameter("fd_case_no", cfd.getFd_case_no()).setParameter("fd_case_type", cfd.getCaseType()).setParameter("fd_case_year", cfd.getFd_case_year()).executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return master;
	}*/
	
	///////////////////////

	public Amendment getAmendment(Long am_fd_mid, String am_type, Long am_status) {
		// TODO Auto-generated method stub
		Amendment master = null;
	    try {
			String query="SELECT a from Amendment a where a.am_fd_mid=:am_fd_mid and a.am_type=:am_type and am_status=:am_status";
			master= (Amendment) em.createQuery(query).setParameter("am_fd_mid", am_fd_mid).setParameter("am_type", am_type).setParameter("am_status", am_status).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}

	public List<Amendment> getAmendments(Long am_fd_mid) {
		// TODO Auto-generated method stub
		List<Amendment> result=new ArrayList<>();
	    try {
			String query="SELECT a from Amendment a where a.am_fd_mid=:am_fd_mid";
			result= em.createQuery(query).setParameter("am_fd_mid",am_fd_mid).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}

	public List<Amendment> getAmendmentsByUser(Long user_id, Long lk_id) {
		// TODO Auto-generated method stub
		List<Amendment> result=new ArrayList<>();
	    try {
			String query="SELECT a from Amendment a where a.am_um_mid=:am_um_mid and a.am_status=:am_status";
			result= em.createQuery(query).setParameter("am_um_mid",user_id).setParameter("am_status", lk_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	public List<Amendment> getAmendmentsByUserId(Long user_id, Long lk_id) {
		// TODO Auto-generated method stub
		List<Amendment> result=new ArrayList<>();
	    try {
			String query="SELECT a from Amendment a where a.am_um_mid=:am_um_mid";
			result= em.createQuery(query).setParameter("am_um_mid",user_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
}
