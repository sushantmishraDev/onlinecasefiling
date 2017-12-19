package com.dms.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dms.model.CaseFileStage;
import com.dms.model.DocumentFileStage;
import com.dms.model.JudgementFileStage;

@Service
public class JudgementFileStageService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public CaseFileStage save(CaseFileStage s) {

		CaseFileStage master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	@Transactional
	public JudgementFileStage save(JudgementFileStage jfs) {
		// TODO Auto-generated method stub
		JudgementFileStage master = null;
		try {
			master = em.merge(jfs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public JudgementFileStage getUserEntry(Long jfd_id, Long nextstagelid) {
		// TODO Auto-generated method stub
		JudgementFileStage jfs=new JudgementFileStage();
		Query query = em.createQuery("select t from JudgementFileStage t where jfs_jfd_mid =:jfs_jfd_mid  and jfs_stage_lid = :jfs_stage_lid order by jfs_id desc");
		query.setParameter("jfs_jfd_mid", jfd_id).setParameter("jfs_stage_lid", nextstagelid).setMaxResults(1);
		jfs =  (JudgementFileStage) query.getSingleResult();
		return jfs;
	}

} 
