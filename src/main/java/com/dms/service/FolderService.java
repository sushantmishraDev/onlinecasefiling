package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Document;
import com.dms.model.Folder;
import com.dms.model.FolderCaseTypeMapping;
import com.dms.model.Lookup;

@Service
public class FolderService {
	
//	@PersistenceContext
//	private EntityManager em;
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	private DataSource datasource;
	
	@Transactional
	public Folder getFolderById(Long id) {
		
		Folder r= new Folder();
		try {
			Query query  =  em.createQuery("SELECT r from Folder r WHERE r.id =:id");
			query.setParameter("id", id);
			r= (Folder) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	@Transactional
	public List<Folder> getAll() {
		List<Folder> result = em.createQuery("SELECT r FROM Folder r").getResultList();
		return result;
	}
	
	@Transactional
	public List<Folder> getParentfolders(Folder f) {
		List<Folder> folders= new ArrayList<Folder>();
		try {
			Query query  =  em.createQuery("SELECT r from Folder r WHERE parent_id is NULL AND r.rep_id =:rep_id");
			query.setParameter("rep_id", f.getRep_id());
			folders= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return folders;
	}
	
	
	public Folder getFolder(Long long1) {		   
		Folder r= em.find(Folder.class, long1);
		return r;
	}
	
	@Transactional
    public void update(Folder r)
    {
		Folder r1 = em.merge(r);
    }
	
    @Transactional
    public void add(Folder r) {
	    try {	
	    	em.persist(r);
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    }
    
	@Transactional
	public void delete(Integer id) {
		Folder r2 = em.find(Folder.class, id);		   
		  em.remove(r2);
	}

	@Transactional
	public Folder save(Folder folder) {
		// TODO Auto-generated method stub

		Folder master = null;
    	try {	
    		master= em.merge(folder);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}

	public List<Folder> getFoldersByParentId(Long folderId) {
		// TODO Auto-generated method stub
		List<Folder> folders= new ArrayList<Folder>();
		try {
			Query query  =  em.createQuery("SELECT r from Folder r WHERE r.parent_id =:folderId");
			query.setParameter("folderId", folderId);
			folders= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return folders;
	}

	public Folder checkExist(Folder f) {
		// TODO Auto-generated method stub
		Folder folder= new Folder();
		try {
			String parentcond="";
			if(f.getParent_id()==null)
				parentcond=" IS NULL";
			else
				parentcond=" = "+f.getParent_id();
			
			Query query  =  em.createQuery("SELECT r from Folder r WHERE r.parent_id "+parentcond+" AND r.rep_id=:rep_id AND r.folder_name=:name");
			//query.setParameter("parentId", f.getParent_id());
			query.setParameter("rep_id", f.getRep_id());
			query.setParameter("name", f.getFolder_name());
			folder= (Folder) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return folder;
	}

	public List<Folder> getUserwisefolders(Folder f, List<Long>folderids) {
		// TODO Auto-generated method stub
		List<Folder> folders= new ArrayList<Folder>();
		try {
			Query query  =  em.createQuery("SELECT r from Folder r WHERE parent_id is NULL AND r.rep_id =:rep_id AND r.id IN(:folderids)");
			query.setParameter("rep_id", f.getRep_id());
			query.setParameter("folderids", folderids);
			folders= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return folders;
	}

	public List<Lookup> getCaseTypesWithNoFolder(Long benchCodeId) {
		// TODO Auto-generated method stub
		List<Lookup> result=em.createQuery("SELECT l FROM Lookup l where l.lk_id not in (select distinct(case_type_id) from FolderCaseTypeMapping where bench_type_id =:benchCodeId) "
				+ "							and lk_setname='CASE_TYPE'").setParameter("benchCodeId", benchCodeId).getResultList();
		 
		return result;
	}
	
	@Transactional
	public FolderCaseTypeMapping saveMapping(FolderCaseTypeMapping mapping) {
		// TODO Auto-generated method stub
		FolderCaseTypeMapping master = null;
    	try {	
    		master= em.merge(mapping);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}

	public List<Folder> getAllByCaseType() {
		// TODO Auto-generated method stub
		List<Folder> result = em.createQuery("SELECT r FROM Folder r where r.level In (3,4)").getResultList();
		return result;
	}

	public Integer getDocumentCountByFolder(Long folder_id) {
		// TODO Auto-generated method stub
		Integer count= 0;
		try {
			Query query=em.createQuery("select count(*) from Document d where d.folder_id=:folder_id").setParameter("folder_id", folder_id);
			count=Integer.parseInt(query.getSingleResult().toString());  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
