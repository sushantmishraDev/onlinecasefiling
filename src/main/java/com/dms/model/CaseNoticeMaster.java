package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="case_notice_master")
public class CaseNoticeMaster {

	@Id
	@Column(name="cnm_id")
	private Long cnm_id;
	
	@Column(name="cnm_name")
	private String cnm_name;
	
	@Column(name="cnm_department")
	private String cnm_department;
	
	@Column(name="cnm_email")
	private String cnm_email;
	
	@Column(name="cnm_mobile")
	private String cnm_mobile;
	
	@Column(name="cnm_dt_mid")
	private Long cnm_dt_mid;
	
	
	@Column(name="cnm_flag")
	private String cnm_flag;
	
	
	
	

	public String getCnm_flag() {
		return cnm_flag;
	}


	public void setCnm_flag(String cnm_flag) {
		this.cnm_flag = cnm_flag;
	}


	public Long getCnm_dt_mid() {
		return cnm_dt_mid;
	}


	public void setCnm_dt_mid(Long cnm_dt_mid) {
		this.cnm_dt_mid = cnm_dt_mid;
	}


	public Long getCnm_id() {
		return cnm_id;
	}
	

	public void setCnm_id(Long cnm_id) {
		this.cnm_id = cnm_id;
	}

	public String getCnm_name() {
		return cnm_name;
	}

	public void setCnm_name(String cnm_name) {
		this.cnm_name = cnm_name;
	}

	public String getCnm_department() {
		return cnm_department;
	}

	public void setCnm_department(String cnm_department) {
		this.cnm_department = cnm_department;
	}

	public String getCnm_email() {
		return cnm_email;
	}

	public void setCnm_email(String cnm_email) {
		this.cnm_email = cnm_email;
	}

	public String getCnm_mobile() {
		return cnm_mobile;
	}

	public void setCnm_mobile(String cnm_mobile) {
		this.cnm_mobile = cnm_mobile;
	}
	
	
}
