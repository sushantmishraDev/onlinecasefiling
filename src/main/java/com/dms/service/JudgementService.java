package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Document;
import com.dms.model.DocumentFileDetails;
import com.dms.model.JudgementFileDetail;
import com.dms.model.MetaData;
import com.dms.model.ReportsView;

@Service
public class JudgementService {
	
/*	@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	 
	@Transactional
	public JudgementFileDetail getDocumentById(Long id) {
		
		JudgementFileDetail r= new JudgementFileDetail();
		try {
			Query query  =  em.createQuery("SELECT r from JudgementFileDetail r WHERE r.jfd_id =:id");
			query.setParameter("id", id);
			r= (JudgementFileDetail) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	@Transactional
	public List<Document> getAll() {
		List<Document> result = em.createQuery("SELECT r FROM Document r").getResultList();
		return result;
	}

	
	
	public Document getDocument(Long id) {		   
		Document r= em.find(Document.class, id);
		return r;
	}
	
	@Transactional
    public Document update(Document d)
    {
		Document master = null;
    	try {	
    		master= em.merge(d);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
    }
	
 	@Transactional
	public void delete(Long id) {
 		Document r2 = em.find(Document.class, id);		   
		  em.remove(r2);
	}

	@Transactional
	public Document save(Document d) {
		// TODO Auto-generated method stub

		Document master = null;
    	try {	
    		master= em.merge(d);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}

	public List<MetaData> getDocumentByFolderID(Long folder_id) {
		// TODO Auto-generated method stub
		//List<Document> documents = em.createQuery("SELECT d FROM Document d WHERE d.folder_id=:folder_id").setParameter("folder_id",folder_id).getResultList();
		List<MetaData> metadatas=em.createQuery("SELECT m FROM MetaData m WHERE m.md_fd_mid IN (SELECT d.id FROM Document d WHERE d.folder_id =:folder_id) AND md_mf_mid=3").setParameter("folder_id",folder_id).getResultList();
		return metadatas;
	}

	public List<Document> getDocumentsByStage(Long fd_stage_lid,List<Long> folderids) {
		// TODO Auto-generated method stub
		List<Document> documentDetails = new ArrayList<Document>();	
		Query query = em.createQuery("SELECT d FROM Document d WHERE d.fd_stage_lid=:fd_stage_lid AND d.folder_id IN (:folderids)").setParameter("fd_stage_lid", fd_stage_lid).setParameter("folderids", folderids);
		
		try {
			documentDetails = query.getResultList();		
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity Does Not Exit");
		} finally {
			return documentDetails;
		}
	}

	@Transactional
	public List<DocumentFileDetails> getByDfdPk(Long id) {
		List<DocumentFileDetails> result = em.createQuery(
						"SELECT d FROM DocumentFileDetails d WHERE d.dfd_fd_mid=:fd_id ")
				.setParameter("fd_id", id).getResultList();
		
		return result;
	}

	public List<Document> getByParentID(Long parent_id) {
		// TODO Auto-generated method stub
		List<Document> result = em.createQuery("SELECT d FROM Document d WHERE d.parent_id=:parent_id")
								.setParameter("parent_id", parent_id).getResultList();

		return result;
	}
	
	@Transactional
	public List<ReportsView> getcaseFilesBySearchquery(String querystring,Long stagelid) {
		List<ReportsView> caseFileDetails = new ArrayList<ReportsView>();	
		//Query query = em.createNativeQuery("SELECT * FROM case_file_details WHERE fd_stage_lid IS NOT NULL AND fd_id IN (SELECT md_fd_mid FROM MetaDataView WHERE md_id IS NOT NULL "+querystring+") ",CaseFileDetail.class);
		//Query query = em.createNativeQuery("SELECT d. FROM documents  WHERE id IN (SELECT md_fd_mid FROM metadataview where "+querystring+") and fd_stage_lid=:stagelid limit 1000",Document.class).setParameter("stagelid", stagelid);
		//String q="SELECT * FROM reportsview r where fileId In( SELECT d.id FROM Document  WHERE d.id IN (SELECT md.md_fd_mid FROM MetaDataView md where "+querystring+" ))";
		//Query query = em.createNativeQuery("SELECT d. FROM documents  WHERE id IN (SELECT md_fd_mid FROM metadataview where "+querystring+") and fd_stage_lid=:stagelid limit 1000",Document.class).setParameter("stagelid", stagelid);
		
		Query query = em.createQuery("SELECT r FROM ReportsView r WHERE r.fileId IN (SELECT md_fd_mid FROM MetaDataView where "+querystring+")");
		System.out.println("advanced search query=");
		try {
			caseFileDetails = query.getResultList();		
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity Does Not Exit");
		} finally {
			return caseFileDetails;
		}

	}
	
	@Transactional
	public JudgementFileDetail checkJudgementFileExist(Long folder_id, String document_name) {
		// TODO Auto-generated method stub
		JudgementFileDetail d= new JudgementFileDetail();
		try {
			Query query  =  em.createQuery("SELECT d FROM JudgementFileDetail d WHERE d.jfd_folder_id =:folder_id AND d.jfd_encrypted_name=:document_name");
			query.setParameter("folder_id", folder_id);
			query.setParameter("document_name", document_name);
			d=(JudgementFileDetail) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	public List<Document> getUsingFolderIds(List<Long> folderIds) {
		
		List<Document> result = em.createQuery("SELECT r FROM Document r where r.folder_id IN(:folderIds) ").setParameter("folderIds", folderIds).getResultList();
		return result;
	}

	public List<Document> getAllWithoutIndex() {
		List<Document> result = em.createQuery("SELECT r FROM Document r where r.index=0").getResultList();
		return result;
	}
	
	@Transactional
	public JudgementFileDetail save(JudgementFileDetail jfd) {
		// TODO Auto-generated method stub
		JudgementFileDetail master = null;
    	try {	
    		master= em.merge(jfd);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;

	}

	public List<JudgementFileDetail> getJudgementFilesByDocId(Long id) {
		// TODO Auto-generated method stub
		List<JudgementFileDetail> result = em.createQuery("SELECT d FROM JudgementFileDetail d WHERE d.jfd_fd_mid=:jfd_fd_mid and d.jfd_reject_status='N' and d.jfd_rec_status=1")
				.setParameter("jfd_fd_mid", id).getResultList();

		return result;
	}

	public List<JudgementFileDetail> getRejectedjudgementsByStage(Long stagelid, List<Long> childfolderids,
			Long um_id) {
		// TODO Auto-generated method stub
		List<JudgementFileDetail> documentDetails = new ArrayList<JudgementFileDetail>();	
		Query query = em.createQuery("SELECT d FROM JudgementFileDetail d WHERE d.jfd_stage_lid=:stagelid AND d.jfd_folder_id IN (:folderids) and d.jfd_reject_status='Y' and d.jfd_rec_status=1 and d.jfd_assign_to=:fd_assign_to").setParameter("stagelid", stagelid).setParameter("folderids", childfolderids).setParameter("fd_assign_to", um_id);
		
		try {
			documentDetails = query.getResultList();		
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity Does Not Exit");
		} finally {
			return documentDetails;
		}
	}
	  

}
