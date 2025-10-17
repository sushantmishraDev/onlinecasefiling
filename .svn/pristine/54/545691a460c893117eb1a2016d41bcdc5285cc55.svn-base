package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Document;
import com.dms.model.JudgementFileDetail;
import com.dms.model.ReportsView;

@Service
public class DocumentService {
	
	@PersistenceContext
	private EntityManager em;
	 
	@Transactional
	public Document getDocumentById(Long id) {
		
		Document r= new Document();
		try {
			Query query  =  em.createQuery("SELECT r from Document r WHERE r.id =:id");
			query.setParameter("id", id);
			r= (Document) query.getSingleResult();
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

//	public List<MetaData> getDocumentByFolderID(Long folder_id) {
//		// TODO Auto-generated method stub
//		//List<Document> documents = em.createQuery("SELECT d FROM Document d WHERE d.folder_id=:folder_id").setParameter("folder_id",folder_id).getResultList();
//		List<MetaData> metadatas=em.createQuery("SELECT m FROM MetaData m WHERE m.md_fd_mid IN (SELECT d.id FROM Document d WHERE d.folder_id =:folder_id) AND md_mf_mid=3").setParameter("folder_id",folder_id).getResultList();
//		return metadatas;
//	}
	
	public List<Document> getDocumentsByCaseNo(Document d) {
		// TODO Auto-generated method stub
		String query="";
		if(d.getBenchCode()!=null)
		{
		query+=" AND r.benchCode='"+d.getBenchCode()+"'";	
		}
		if(d.getCaseType()!=null)
		{
			query+=" AND r.caseType='"+d.getCaseType()+"'";	
		}
		if(d.getCaseYear()!=null)
		{
			query+=" AND r.caseYear='"+d.getCaseYear()+"'";
		}
		System.out.println("Query="+query);
		//List<Document> documents = em.createQuery("SELECT d FROM Document d WHERE d.folder_id=:folder_id").setParameter("folder_id",folder_id).getResultList();
		List<Document> documents=em.createQuery("SELECT d FROM Document d WHERE d.id IN (select r.fileId from ReportsView r where r.caseNo='"+d.getCaseNo()+"'"+query+") and d.fd_rec_status=1").getResultList();
		return documents;
	}

	public List<Document> getFreshDocumentsByStage(Long fd_stage_lid,List<Long> folderids,Long fd_assign_to,Document d) {
		// TODO Auto-generated method stub
		String query="";
		if(d.getBenchCode()!=null)
		{
		query+=" r.benchCode='"+d.getBenchCode()+"'";	
		}
		if(d.getCaseType()!=null)
		{
			query+=" AND r.caseType='"+d.getCaseType()+"'";	
		}
		if(d.getCaseYear()!=null)
		{
			query+=" AND r.caseYear='"+d.getCaseYear()+"'";
		}
		if(d.getCaseNo()!=null)
		{
			query+=" AND r.caseNo='"+d.getCaseNo()+"'";
		}
		if(query!="")
		{
		query=" and d.id IN (select r.fileId from ReportsView r where "+query+")";
		}
		System.out.println("Query="+query);
		List<Document> documentDetails = new ArrayList<Document>();	
		Query q = em.createQuery("SELECT d FROM Document d WHERE d.fd_stage_lid=:fd_stage_lid AND d.folder_id IN (:folderids) and d.fd_rec_status=1 and d.fd_edit_mode=0 or (fd_assign_to=:fd_assign_to and fd_reject_status !='Y') "+query).setParameter("fd_stage_lid", fd_stage_lid).setParameter("folderids", folderids).setParameter("fd_assign_to", fd_assign_to);
		
		try {
			documentDetails = q.getResultList();		
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity Does Not Exit");
		} finally {
			return documentDetails;
		}
	}

	@Transactional
	public List<JudgementFileDetail> getByDfdPk(Long id) {
		List<JudgementFileDetail> result = em.createQuery(
						"SELECT d FROM JudgementFileDetail d WHERE d.jfd_fd_mid=:fd_id and d.jfd_rec_status=1")
				.setParameter("fd_id", id).getResultList();
		
		return result;
	}

	public List<Document> getByParentID(Long parent_id) {
		// TODO Auto-generated method stub
		List<Document> result = em.createQuery("SELECT d FROM Document d WHERE d.parent_id=:parent_id and d.fd_rec_status=1")
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
	public Document checkCaseFileExist(Long folder_id, String document_name) {
		// TODO Auto-generated method stub
		Document d= new Document();
		try {
			Query query  =  em.createQuery("SELECT d FROM Document d WHERE d.folder_id =:folder_id AND d.encrypted_name=:document_name");
			query.setParameter("folder_id", folder_id);
			query.setParameter("document_name", document_name);
			d=(Document) query.getSingleResult();
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

	public List<Document> getSavedDocumentsByStage(Long stagelid, List<Long> childfolderids,Long userid) {
		List<Document> documentDetails = new ArrayList<Document>();	
		Query query = em.createQuery("SELECT d FROM Document d WHERE d.fd_stage_lid=:fd_stage_lid AND d.folder_id IN (:folderids) and d.fd_edit_mode=1 and d.fd_assign_to=:fd_assign_to").setParameter("fd_stage_lid", stagelid).setParameter("folderids", childfolderids).setParameter("fd_assign_to", userid);
		
		try {
			documentDetails = query.getResultList();		
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity Does Not Exit");
		} finally {
			return documentDetails;
		}
	}
	
	public List<Document> getRejectedDocumentsByStage(Long stagelid, List<Long> childfolderids,Long fd_assign_to) {
		List<Document> documentDetails = new ArrayList<Document>();	
		Query query = em.createQuery("SELECT d FROM Document d WHERE d.fd_stage_lid=:fd_stage_lid AND d.folder_id IN (:folderids) and d.fd_reject_status='Y' and d.fd_rec_status=1 and d.fd_assign_to=:fd_assign_to").setParameter("fd_stage_lid", stagelid).setParameter("folderids", childfolderids).setParameter("fd_assign_to", fd_assign_to);
		
		try {
			documentDetails = query.getResultList();		
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity Does Not Exit");
		} finally {
			return documentDetails;
		}
	}

	public Integer filesPendingAtUploadStage(Long fd_stage_lid) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Document d where d.fd_stage_lid=:fd_stage_lid and d.fd_rec_status = 1").setParameter("fd_stage_lid", fd_stage_lid);
		result= Integer.parseInt(query.getSingleResult().toString());
	
		return result;
	}
	
	public Integer filesPendingAtMakerStage(Long fd_stage_lid) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Document d where d.fd_stage_lid=:fd_stage_lid and d.fd_rec_status = 1").setParameter("fd_stage_lid", fd_stage_lid);
		result= Integer.parseInt(query.getSingleResult().toString());
		return result;
	}
	
	public Integer filesPendingAtCheckerStage(Long fd_stage_lid) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Document d where d.fd_stage_lid=:fd_stage_lid and d.fd_rec_status = 1").setParameter("fd_stage_lid", fd_stage_lid);
		result= Integer.parseInt(query.getSingleResult().toString());
		return result;
	}
	
	public Integer filesPendingAtVerifierStage(Long fd_stage_lid) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Document d where d.fd_stage_lid=:fd_stage_lid and d.fd_rec_status = 1").setParameter("fd_stage_lid", fd_stage_lid);
		result= Integer.parseInt(query.getSingleResult().toString());
		return result;
	}
	
	public Integer filesCountAtVerifiedStage(Long fd_stage_lid) {
		// TODO Auto-generated method stub
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Document d where d.fd_stage_lid=:fd_stage_lid and d.fd_rec_status = 1").setParameter("fd_stage_lid", fd_stage_lid);
		result= Integer.parseInt(query.getSingleResult().toString());
		return result;
	}

	public Integer filesCountAtDeletedStage() {
		Integer result=0;
		Query query=em.createQuery("SELECT Count(d) FROM Document d where d.fd_rec_status = 0");
		result= Integer.parseInt(query.getSingleResult().toString());
		return result;
	}
	
}
