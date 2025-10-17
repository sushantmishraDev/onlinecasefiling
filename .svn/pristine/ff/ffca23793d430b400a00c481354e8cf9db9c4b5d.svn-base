package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.TransactionScoped;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Folder;
import com.dms.model.LoginLog;
import com.dms.model.ObjectMaster;
import com.dms.model.Permission;
import com.dms.model.Repository;
import com.dms.model.User;
import com.dms.utility.GlobalFunction;

@Service
public class UserService {
	
	@PersistenceContext
	private EntityManager em;
	
	GlobalFunction globalfunction=new GlobalFunction();
	
	@Transactional
	public List<User> getAll() {
		List<User> result = em.createQuery("SELECT r FROM User r").getResultList();
		return result;
	}
	
	@Transactional
	public void update(User u) {
		User r1 = em.merge(u);
	}
	@Transactional
	public LoginLog saveLog(LoginLog s) {

		LoginLog master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	@Transactional
	public User validateLogin(String username, String password) {
		User user = new User();
		String pwd = globalfunction.md5encryption(password);
		System.out.println("Password="+pwd);
		try {
			Query query = em
					.createQuery("SELECT u FROM User u WHERE (username =:username AND password=:password)");
			query.setParameter("username", username);
			query.setParameter("password", pwd);
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return user;
		}
	}

	public User getByuserid(Long userid) {
		User u = null;
		try {
			u = (User) em
					.createQuery("SELECT u from User u where id=" + userid)
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public List<ObjectMaster> getUserObjects(Long um_id) {
		//System.out.println("call getUserObjects");
		// TODO Auto-generated method stub
		String sql = "select o from ObjectMaster o "
				+ " where o.om_id in (select ro_om_mid from  RoleObject where ro_role_id in (select ur_role_id from UserRole where ur_um_mid = "+um_id+") and ro_rec_status = 1)";
		List<ObjectMaster> l1 = (List<ObjectMaster>) em.createQuery(sql)
				.getResultList();

		for (int i = 0; i < l1.size(); i++) {
			//System.out.println(l1.get(i));
			ObjectMaster om = l1.get(i);
			//System.out.println(om.getOm_object_name());
		}
		return l1;
	}
	
	@Transactional
	public User save(User user) {
		// TODO Auto-generated method stub
		User master = null;
    	try {	
    		master= em.merge(user);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}
	
	@Transactional
	public User getUser(Long id) {
		User r = em.find(User.class, id);
		return r;
	}

	public List<User> getUsersByBechcode(Long um_bench_code,Long um_id) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		users=em.createQuery("SELECT u FROM User u where u.um_bench_code=:um_bench_code AND u.um_id != :um_id").setParameter("um_bench_code", um_bench_code).setParameter("um_id",um_id).getResultList();
		return users;
	}

	@Transactional
	public List<User> getAllByRole() {
		// TODO Auto-generated method stub
		List<User> result = em.createQuery("SELECT u from User u where u.um_id In (select ur.ur_um_mid from UserRole ur where ur.ur_role_id IN (select lk.lk_id from Lookup lk where lk.lk_setname='DMS_ROLE'))order by um_account_activation").getResultList();
		return result;
	}
	@Transactional
	public User validateUser(String username) {
		User user = new User();
		try {
			Query query = em
					.createQuery("SELECT u FROM User u WHERE (username =:username)");
			query.setParameter("username", username);
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return user;
		}
	}

	@Transactional
	public User getUserDetail(Long um_id) 
	{
		User result = new User();
		
		String query = "select um from User um where um.um_id="+um_id;
		
		result = (User) em.createQuery(query).getSingleResult(); 
		
		System.out.println("Result is :" +result);
		
		return result;
	}
	
	@Transactional
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		User result = new User();
		
		String query = "select um from User um where um.username=:username";
		
		try {
			result = (User) em.createQuery(query).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}
	
	

	@Transactional
	public User checkPassExist(String password, Long um_id) {
		User user = new User();
		String pwd = globalfunction.md5encryption(password);
		System.out.println("Password="+pwd);
		try {
					Query query = em
					.createQuery("SELECT u FROM User u WHERE (um_id =:um_id AND password=:password)");
			query.setParameter("um_id", um_id);
			query.setParameter("password", pwd);
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} 
			return user;
		}
	 
}
