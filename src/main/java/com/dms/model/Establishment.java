package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="establishment")
public class Establishment {
	
	@Id
	@Column(name = "est_id")
	private Long est_id;
	
	@Column(name = "est_dt_mid")
	private Long est_dt_mid;
	
	@Column(name="est_name")
	private String est_name;

	public Long getEst_id() {
		return est_id;
	}

	public void setEst_id(Long est_id) {
		this.est_id = est_id;
	}

	public Long getEst_dt_mid() {
		return est_dt_mid;
	}

	public void setEst_dt_mid(Long est_dt_mid) {
		this.est_dt_mid = est_dt_mid;
	}

	public String getEst_name() {
		return est_name;
	}

	public void setEst_name(String est_name) {
		this.est_name = est_name;
	}
	
	

}
