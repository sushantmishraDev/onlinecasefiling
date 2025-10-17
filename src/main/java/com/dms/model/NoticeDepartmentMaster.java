package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="notice_department_master")
public class NoticeDepartmentMaster {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="notice_department_seq")
	@SequenceGenerator(name="notice_department_seq", sequenceName="notice_department_seq", allocationSize=1)
	@Column(name="ndm_id")
	private Long ndm_id;
	
	@Column(name="ndm_department_name")
	private String ndm_department_name;
	
	@Column(name="ndm_dist_mid")
	private Long ndm_dist_mid;
	
	
	@Column(name="ndm_mobile")
	private String ndm_mobile;
	
	@Column(name="ndm_email")
	private String ndm_email;
	
	
	

	public String getNdm_mobile() {
		return ndm_mobile;
	}

	public void setNdm_mobile(String ndm_mobile) {
		this.ndm_mobile = ndm_mobile;
	}

	

	public String getNdm_email() {
		return ndm_email;
	}

	public void setNdm_email(String ndm_email) {
		this.ndm_email = ndm_email;
	}

	public Long getNdm_id() {
		return ndm_id;
	}

	public void setNdm_id(Long ndm_id) {
		this.ndm_id = ndm_id;
	}

	public String getNdm_department_name() {
		return ndm_department_name;
	}

	public void setNdm_department_name(String ndm_department_name) {
		this.ndm_department_name = ndm_department_name;
	}

	public Long getNdm_dist_mid() {
		return ndm_dist_mid;
	}

	public void setNdm_dist_mid(Long ndm_dist_mid) {
		this.ndm_dist_mid = ndm_dist_mid;
	}

	
}
