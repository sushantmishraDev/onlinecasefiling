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
@Table(name="judgement_file_details")
public class JudgementFileDetail {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="judgementfileseq")
	@SequenceGenerator(name="judgementfileseq", sequenceName="judgementfileseq", allocationSize=1)
	@Column(name="jfd_id")
	private Long jfd_id;
	
	@Column(name="jfd_document_name")
	private String jfd_document_name;
	
	@Column(name="jfd_encrypted_name")
	private String jfd_encrypted_name;
	
	@Column(name="jfd_folder_id")
	private Long jfd_folder_id;
	
	@Column(name="jfd_fd_mid")
	private Long jfd_fd_mid;
	
	@Column(name="jfd_created")
	private Date jfd_created;
	
	@Column(name="jfd_created_by")
	private Long jfd_created_by;
	
	@Column(name="jfd_updated")
	private Date jfd_updated;
	
	@Column(name="jfd_updated_by")
	private Long jfd_updated_by;
	
	@Column(name="jfd_stage_lid")
	private Long jfd_stage_lid;
	
	@Column(name="jfd_rec_status")
	private Integer jfd_rec_status;
	
	@Column(name="index")
	private Integer index;
	
	@Column(name="jfd_reject_status")
	private String jfd_reject_status;

	@Column(name="jfd_judgement_date")
	private Date jfd_judgement_date;
	
	@Column(name="jfd_assign_to")
	private Long jfd_assign_to;

	public Long getJfd_id() {
		return jfd_id;
	}

	public void setJfd_id(Long jfd_id) {
		this.jfd_id = jfd_id;
	}

	public String getJfd_document_name() {
		return jfd_document_name;
	}

	public void setJfd_document_name(String jfd_document_name) {
		this.jfd_document_name = jfd_document_name;
	}

	public Long getJfd_fd_mid() {
		return jfd_fd_mid;
	}

	public void setJfd_fd_mid(Long jfd_fd_mid) {
		this.jfd_fd_mid = jfd_fd_mid;
	}

	public Date getJfd_created() {
		return jfd_created;
	}

	public void setJfd_created(Date jfd_created) {
		this.jfd_created = jfd_created;
	}

	public Long getJfd_created_by() {
		return jfd_created_by;
	}

	public void setJfd_created_by(Long jfd_created_by) {
		this.jfd_created_by = jfd_created_by;
	}

	public Date getJfd_updated() {
		return jfd_updated;
	}

	public void setJfd_updated(Date jfd_updated) {
		this.jfd_updated = jfd_updated;
	}

	public Long getJfd_updated_by() {
		return jfd_updated_by;
	}

	public void setJfd_updated_by(Long jfd_updated_by) {
		this.jfd_updated_by = jfd_updated_by;
	}

	public Long getJfd_stage_lid() {
		return jfd_stage_lid;
	}

	public void setJfd_stage_lid(Long jfd_stage_lid) {
		this.jfd_stage_lid = jfd_stage_lid;
	}

	public Integer getJfd_rec_status() {
		return jfd_rec_status;
	}

	public void setJfd_rec_status(Integer jfd_rec_status) {
		this.jfd_rec_status = jfd_rec_status;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getJfd_reject_status() {
		return jfd_reject_status;
	}

	public void setJfd_reject_status(String jfd_reject_status) {
		this.jfd_reject_status = jfd_reject_status;
	}

	public Date getJfd_judgement_date() {
		return jfd_judgement_date;
	}

	public void setJfd_judgement_date(Date jfd_judgement_date) {
		this.jfd_judgement_date = jfd_judgement_date;
	}

	public String getJfd_encrypted_name() {
		return jfd_encrypted_name;
	}

	public void setJfd_encrypted_name(String jfd_encrypted_name) {
		this.jfd_encrypted_name = jfd_encrypted_name;
	}

	public Long getJfd_folder_id() {
		return jfd_folder_id;
	}

	public void setJfd_folder_id(Long jfd_folder_id) {
		this.jfd_folder_id = jfd_folder_id;
	}

	public Long getJfd_assign_to() {
		return jfd_assign_to;
	}

	public void setJfd_assign_to(Long jfd_assign_to) {
		this.jfd_assign_to = jfd_assign_to;
	}
	
	
}
