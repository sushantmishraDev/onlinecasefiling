package com.dms.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.TransactionScoped;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.AdvocateEfiling;
import com.dms.model.AdvocateEfiling;
import com.dms.model.Folder;
import com.dms.model.LoginLog;
import com.dms.model.Notice;
import com.dms.model.ObjectMaster;
import com.dms.model.Permission;
import com.dms.model.Repository;
import com.dms.model.SmsLog;
import com.dms.model.User;
import com.dms.utility.GlobalFunction;

@Service
public class UserService {
	
	/*@PersistenceContext
	private EntityManager em;*/
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	

	/*@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em2;*/
	
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
	public Notice saveNotice(Notice s) {

		Notice master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	@Transactional
	public AdvocateEfiling getAdvocateByRollNo(String enroll) {
		
		AdvocateEfiling a= new AdvocateEfiling();
		try {
			Query query  =  em.createQuery("SELECT a from AdvocateEfiling a WHERE a.rollNo =:rollNo");
			query.setParameter("rollNo", enroll);
			a= (AdvocateEfiling) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	@Transactional
	public AdvocateEfiling saveAdvocate(AdvocateEfiling s) {

		AdvocateEfiling master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	@Transactional
	public Notice validateNotice(Long username, Integer password) {
		Notice nt=null;
		
		
		
		
		
		
		
		
		try {
			Query query = em
					.createQuery("SELECT u FROM Notice u WHERE u.nt_id =:username AND u.nt_otp=:password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			nt = (Notice) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return nt;
		}
	}
	
	@Transactional
	public User validateLogin(String username, String password) {
		User user = new User();
		
		
		
		String pwd = globalfunction.md5encryption(password);
		System.out.println("Password="+pwd);
		
		
		
		
		try {
			Query query = em
					.createQuery("SELECT u FROM User u WHERE (username =:username AND password=:password) and u.um_rec_status=1");
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
	
	// validate login with otp
	@Transactional
	public User validateLoginWithOtp(String username, int otp) {
		User user = new User();
		
		try {
			Query query = em
					.createQuery("SELECT u FROM User u WHERE (username =:username AND um_otp=:otp) and u.um_rec_status=1");
			query.setParameter("username", username);
			query.setParameter("otp", otp);
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
	public SmsLog saveSMSlog(SmsLog smslog) {
		SmsLog sms = null;
    	try {	
    		sms= em.merge(smslog);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return sms;
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
	public Notice getNoticeById(Long id) {
		// TODO Auto-generated method stub
		Notice result = null;
		
		String query = "select nt from Notice nt where nt.nt_id=:nt_id";
		
		try {
			result = (Notice) em.createQuery(query).setParameter("nt_id", id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
	}
	
	@Transactional
	public User getUserByMobile(String username) {
		// TODO Auto-generated method stub
		User result = new User();
		
		String query = "select um from User um where um.um_mobile=:username";
		
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
