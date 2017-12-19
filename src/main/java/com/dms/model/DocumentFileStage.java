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
@Table(name="document_file_stage")
public class DocumentFileStage {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="documentfilestageseq")
	@SequenceGenerator(name="documentfilestageseq", sequenceName="documentfilestageseq", allocationSize=1)
	@Column(name="dfs_id")
	private Long dfs_id;
	
	@Column(name="dfs_fd_mid")
	private Long dfs_fd_mid;
	 
	@Column(name="dfs_stage_lid")
	private Long dfs_stage_lid;
	
	@Column(name="dfs_stage_date")
	private Date dfs_stage_date;
	
	@Column(name="dfs_cr_by")
	private Long dfs_cr_by;
	
	@Column(name="dfs_remark")
	private String dfs_remark;
	
	@Column(name="dfs_reject_status")
	private String dfs_reject_status;
	
	@Column(name="dfs_assign_to")
	private Long dfs_assign_to;
	
	@Column(name="dfs_ip_address")
	private String dfs_ip_address;

	public Long getDfs_id() {
		return dfs_id;
	}

	public void setDfs_id(Long dfs_id) {
		this.dfs_id = dfs_id;
	}

	public Long getDfs_fd_mid() {
		return dfs_fd_mid;
	}

	public void setDfs_fd_mid(Long dfs_fd_mid) {
		this.dfs_fd_mid = dfs_fd_mid;
	}

	public Long getDfs_stage_lid() {
		return dfs_stage_lid;
	}

	public void setDfs_stage_lid(Long dfs_stage_lid) {
		this.dfs_stage_lid = dfs_stage_lid;
	}

	public Date getDfs_stage_date() {
		return dfs_stage_date;
	}

	public void setDfs_stage_date(Date dfs_stage_date) {
		this.dfs_stage_date = dfs_stage_date;
	}

	public Long getDfs_cr_by() {
		return dfs_cr_by;
	}

	public void setDfs_cr_by(Long dfs_cr_by) {
		this.dfs_cr_by = dfs_cr_by;
	}

	public String getDfs_remark() {
		return dfs_remark;
	}

	public void setDfs_remark(String dfs_remark) {
		this.dfs_remark = dfs_remark;
	}

	public String getDfs_reject_status() {
		return dfs_reject_status;
	}

	public void setDfs_reject_status(String dfs_reject_status) {
		this.dfs_reject_status = dfs_reject_status;
	}

	public Long getDfs_assign_to() {
		return dfs_assign_to;
	}

	public void setDfs_assign_to(Long dfs_assign_to) {
		this.dfs_assign_to = dfs_assign_to;
	}

	public String getDfs_ip_address() {
		return dfs_ip_address;
	}

	public void setDfs_ip_address(String dfs_ip_address) {
		this.dfs_ip_address = dfs_ip_address;
	}
	
		
}
