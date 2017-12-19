package com.dms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="judgement_file_stage")
public class JudgementFileStage {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="judgementfilestageseq")
	@SequenceGenerator(name="judgementfilestageseq", sequenceName="judgementfilestageseq", allocationSize=1)
	@Column(name="jfs_id")
	private Long jfs_id;
	
	@Column(name="jfs_jfd_mid")
	private Long jfs_jfd_mid;
	 
	@Column(name="jfs_stage_lid")
	private Long jfs_stage_lid;
	
	@Column(name="jfs_stage_date")
	private Date jfs_stage_date;
	
	@Column(name="jfs_cr_by")
	private Long jfs_cr_by;
	
	@Column(name="jfs_remark")
	private String jfs_remark;

	@Column(name="jfs_assign_to")
	private Long jfs_assign_to;

	@Column(name="jfs_reject_status")
	private String jfs_reject_status;
	
	@Column(name="jfs_ip_address")
	private String jfs_ip_address;

	public Long getJfs_id() {
		return jfs_id;
	}

	public void setJfs_id(Long jfs_id) {
		this.jfs_id = jfs_id;
	}

	public Long getJfs_jfd_mid() {
		return jfs_jfd_mid;
	}

	public void setJfs_jfd_mid(Long jfs_jfd_mid) {
		this.jfs_jfd_mid = jfs_jfd_mid;
	}

	public Long getJfs_stage_lid() {
		return jfs_stage_lid;
	}

	public void setJfs_stage_lid(Long jfs_stage_lid) {
		this.jfs_stage_lid = jfs_stage_lid;
	}

	public Date getJfs_stage_date() {
		return jfs_stage_date;
	}

	public void setJfs_stage_date(Date jfs_stage_date) {
		this.jfs_stage_date = jfs_stage_date;
	}

	public Long getJfs_cr_by() {
		return jfs_cr_by;
	}

	public void setJfs_cr_by(Long jfs_cr_by) {
		this.jfs_cr_by = jfs_cr_by;
	}

	public String getJfs_remark() {
		return jfs_remark;
	}

	public void setJfs_remark(String jfs_remark) {
		this.jfs_remark = jfs_remark;
	}

	public Long getJfs_assign_to() {
		return jfs_assign_to;
	}

	public void setJfs_assign_to(Long jfs_assign_to) {
		this.jfs_assign_to = jfs_assign_to;
	}

	public String getJfs_reject_status() {
		return jfs_reject_status;
	}

	public void setJfs_reject_status(String jfs_reject_status) {
		this.jfs_reject_status = jfs_reject_status;
	}

	public String getJfs_ip_address() {
		return jfs_ip_address;
	}

	public void setJfs_ip_address(String jfs_ip_address) {
		this.jfs_ip_address = jfs_ip_address;
	}
	
}
