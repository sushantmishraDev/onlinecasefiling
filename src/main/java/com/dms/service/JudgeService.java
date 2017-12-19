package com.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.Judge;



@Service
public class JudgeService {
	
	@PersistenceContext
	private EntityManager em;
	

	@Transactional
	public List<Judge> getAll() {
		List<Judge> result = em.createQuery("Select j from Judge j order by j.jg_name").getResultList();
		return result;
	}

	@Transactional
	public Judge save(Judge j) {
		Judge judge = null;
		try {
			judge = em.merge(j);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return judge;
	}
}
