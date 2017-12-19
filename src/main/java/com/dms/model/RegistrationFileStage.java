package com.dms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="registration_file_stage")
public class RegistrationFileStage {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="registration_stage_seq")
	@SequenceGenerator(name="registration_stage_seq", sequenceName="registration_stage_seq", allocationSize=1)
	@Column(name="rfs_id")
	private Long rfs_id;
	
	@Column(name="rfs_rcd_mid")
	private Long rfs_rcd_mid;
	 
	@Column(name="rfs_stage_lid")
	private Long rfs_stage_lid;
	
	@Column(name="rfs_cr_by")
	private Long rfs_cr_by;
	
	@Column(name="rfs_cr_date")
	private Date rfs_cr_date;

	public Long getRfs_id() {
		return rfs_id;
	}

	public void setRfs_id(Long rfs_id) {
		this.rfs_id = rfs_id;
	}

	public Long getRfs_rcd_mid() {
		return rfs_rcd_mid;
	}

	public void setRfs_rcd_mid(Long rfs_rcd_mid) {
		this.rfs_rcd_mid = rfs_rcd_mid;
	}

	public Long getRfs_stage_lid() {
		return rfs_stage_lid;
	}

	public void setRfs_stage_lid(Long rfs_stage_lid) {
		this.rfs_stage_lid = rfs_stage_lid;
	}

	public Long getRfs_cr_by() {
		return rfs_cr_by;
	}

	public void setRfs_cr_by(Long rfs_cr_by) {
		this.rfs_cr_by = rfs_cr_by;
	}

	public Date getRfs_cr_date() {
		return rfs_cr_date;
	}

	public void setRfs_cr_date(Date rfs_cr_date) {
		this.rfs_cr_date = rfs_cr_date;
	}
	
}
