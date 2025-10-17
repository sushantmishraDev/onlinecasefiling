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

import com.dms.model.ObjectMaster;
import com.dms.model.SecurityQuestion;
import com.dms.model.User;

@Service
public class SecurityQuestionService
{
	
	/*@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	@Transactional
	public SecurityQuestion saveAnswer(SecurityQuestion s) {
		System.out.println("test");
		SecurityQuestion answer = null;
		try {
			answer = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public SecurityQuestion getByUserId(Long u_id,Long q_id) {
		SecurityQuestion sq = null;
		try {
			sq = (SecurityQuestion) em
					.createQuery("SELECT sq from SecurityQuestion sq where pra_user_mid="+ u_id +"AND pra_question_lid="+q_id)
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sq;
	}
	
	
	public SecurityQuestion getByUserRandomId(Long u_id,Long q_id) {
		SecurityQuestion sq = null;
		try {
			sq = (SecurityQuestion) em
					.createQuery("SELECT sq from SecurityQuestion sq where pra_user_mid="+ u_id +"AND pra_question_lid="+q_id)
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sq;
	}
	
}