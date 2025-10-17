package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Folder;
import com.dms.model.Permission;
import com.dms.model.Repository;

@Service
public class PermissionService {
	
	/*@PersistenceContext
	private EntityManager em;*/
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	 
	@Transactional
	public Permission getFolderById(Long id) {
		
		Permission r= new Permission();
		try {
			Query query  =  em.createQuery("SELECT r from Permission r WHERE r.id =:id");
			query.setParameter("id", id);
			r= (Permission) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	@Transactional
	public List<Permission> getAll() {
		List<Permission> result = em.createQuery("SELECT r FROM Permission r order by r.id ").getResultList();
		return result;
	}
	
	public Permission getPermission(int id) {		   
		Permission r= em.find(Permission.class, id);
		return r;
	}
	
	@Transactional
    public Permission update(Permission r)
    {
		Permission r1 = em.merge(r);
		return r1;
    }
	
    	@Transactional
	public Permission save(Permission p) {
		// TODO Auto-generated method stub

    	Permission master = null;
    	try {	
    		master= em.merge(p);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}
    	
	public List<Permission> getPermissionByUser(Long userId) {
		// TODO Auto-generated method stub
		List<Permission> permissions= new ArrayList<Permission>();
		try {
			Query query  =  em.createQuery("SELECT p from Permission p where p.userId=:userId order by p.id)");
			query.setParameter("userId", userId);
			permissions= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissions;
	}
	public List<Folder> getNotAssignedFolders(Long userId) {
			// TODO Auto-generated method stub
			List<Folder> folders= new ArrayList<Folder>();
			try {
				Query query  =  em.createQuery("SELECT f from Folder f where f.id Not IN (SELECT p.value from Permission p where p.userId=:userId AND p.type=2) order by f.id");
				query.setParameter("userId", userId);
				folders= query.getResultList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return folders;
		}
	
	public List<Repository> getNotAssignedRepositories(Long userId) {
		// TODO Auto-generated method stub
		List<Repository> repositories= new ArrayList<Repository>();
		try {
			Query query  =  em.createQuery("SELECT r from Repository r where r.id Not IN (SELECT p.value from Permission p where p.userId=:userId AND p.type=1) order by r.id");
			query.setParameter("userId", userId);
			repositories= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repositories;
	}
	
	public List<Permission> getFolderPermission(Long userId) {
		// TODO Auto-generated method stub
		List<Permission> permissions= new ArrayList<Permission>();
		try {
			Query query  =  em.createQuery("SELECT p from Permission p where p.userId=:userId AND type=2 order by p.id");
			query.setParameter("userId", userId);
			permissions= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissions;
	}
	
	public List<Permission> getRepositoryPermission(Long userId) {
		// TODO Auto-generated method stub
		List<Permission> permissions= new ArrayList<Permission>();
		try {
			Query query  =  em.createQuery("SELECT p from Permission p where p.userId=:userId AND type=1 order by p.id");
			query.setParameter("userId", userId);
			permissions= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissions;
	}

	public Permission checkRepositoryexist(Repository r, Long userId) {
		// TODO Auto-generated method stub
		Permission permission=null;
		try {
			Query query  =  em.createQuery("SELECT p from Permission p where p.userId=:userId AND p.value=:value AND type=1");
			query.setParameter("userId", userId);
			query.setParameter("value", r.getId());
			permission= (Permission) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permission;
	}
	
	public Permission checkFolderexist(Folder f, Long userId) {
		// TODO Auto-generated method stub
		Permission permission=null;
		try {
			Query query  =  em.createQuery("SELECT p from Permission p where p.userId=:userId AND p.value=:value AND type=2");
			query.setParameter("userId", userId);
			query.setParameter("value", f.getId());
			permission= (Permission) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permission;
	}
	
	@Transactional
	public void updateChildFolders(List<Long> folderIds,Long userId,Integer status) {
		// TODO Auto-generated method stub
			Query query  =  em.createQuery("UPDATE Permission p set p.status=:status where p.value In(:values) AND p.userId=:userId AND type=2");
			query.setParameter("userId", userId);
			query.setParameter("status", status);
			query.setParameter("values", folderIds);
			query.executeUpdate();
		
	}
	
	public Permission checkPermissionexist(Long value, Long userId, Integer type) {
		// TODO Auto-generated method stub
		Permission permission=new Permission();
		try {
			Query query  =  em.createQuery("SELECT p from Permission p where p.userId=:userId AND p.value=:value AND type=:type and status=1");
			query.setParameter("userId", userId);
			query.setParameter("value", value);
			query.setParameter("type", type);
			permission= (Permission) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permission;
	}
	
	public List<Long> getAssignedFolders(Long userId) {
		// TODO Auto-generated method stub
		List<Long> permissions= new ArrayList<Long>();
		try {
			Query query  =  em.createQuery("SELECT p.value from Permission p where p.userId=:userId AND type=2 AND status=1 order by p.id");
			query.setParameter("userId", userId);
			permissions= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissions;
	}
}
