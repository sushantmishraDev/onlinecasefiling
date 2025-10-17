package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="lower_court_types1")
@IdClass(LowerCourtTypePk.class)
public class LowerCourtTypes {
	  
	/*@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="lctypes1_seq")
	@SequenceGenerator(name="lctypes1_seq", sequenceName="lctypes1_seq", allocationSize=1)*/
	@Id
	@Column(name="lct_id")
	private Integer lct_id;
	@Id
	@Column(name="lct_dt_mid")
	private Long lct_dt_mid;
	
	
	@Column(name="lct_name")
	private String lct_name;
	
	





	public Long getLct_dt_mid() {
		return lct_dt_mid;
	}


	public void setLct_dt_mid(Long lct_dt_mid) {
		this.lct_dt_mid = lct_dt_mid;
	}


	public Integer getLct_id() {
		return lct_id;
	}


	public void setLct_id(Integer lct_id) {
		this.lct_id = lct_id;
	}


	public String getLct_name() {
		return lct_name;
	}


	public void setLct_name(String lct_name) {
		this.lct_name = lct_name;
	}
	
}
