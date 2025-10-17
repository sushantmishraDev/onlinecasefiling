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

import org.hibernate.annotations.Where;

@Entity
@Table(name = "DOCUMENTS")
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="casefileseq")
	@SequenceGenerator(name="casefileseq", sequenceName="casefileseq", allocationSize=1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DOCUMENT_NAME")
	private String document_name;
	
	@Column(name = "ENCRYPTED_NAME")
	private String encrypted_name;

	@Column(name = "CREATED_BY")
	private Long created_by;
	
	@Column(name = "UPDATED_BY")
	private Long updated_by;
	
	@Column(name = "CREATED")
	private Date created;
	
	@Column(name = "UPDATED")
	private Date updated;
	
	@Column(name = "REP_ID")
	private Long rep_id;
	
	@Column(name = "FOLDER_ID")
	private Long folder_id;
	
	@Column(name = "PARENT_ID")
	private Long parent_id;
	
	@Column(name="FD_STAGE_LID")
	private Long fd_stage_lid;
	
	@Column(name="FD_REC_STATUS")
	private int fd_rec_status;
	
	@Column(name="fd_edit_mode")
	private Integer fd_edit_mode;
	
	@Column(name="fd_reject_status")
	private String fd_reject_status;
	
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@Where(clause = "md_rec_status = 1")
	@JoinColumn(name = "md_fd_mid",referencedColumnName="id",insertable = false, updatable = false)	
	private List<MetaData> metaDataList;
	
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "jfd_fd_mid",referencedColumnName="id",insertable = false, updatable = false)	
	private List<JudgementFileDetail> judgementfileList;
	
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id",referencedColumnName="id",insertable = false, updatable = false)	
	private List<Document> reopencasefileList;
	
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "cfs_fd_mid",referencedColumnName="id",insertable = false, updatable = false)	
	private List<CaseFileStage> caseFileStages;
	
	@Column(name = "index")
	private Integer index;
	
	@Column(name = "fd_assign_to")
	private Long fd_assign_to;
	
	@Transient
	private Integer doc_type;
	
	@Transient
	private Boolean submit;
	
	@Transient
	private Date judgement_date;

	@Transient
	private String caseNo;
	
	@Transient
	private Long caseType;
	
	@Transient
	private String caseYear;
	
	@Transient
	private String benchCode;
	
	@Transient
	private Integer noofdocs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Long getRep_id() {
		return rep_id;
	}

	public void setRep_id(Long rep_id) {
		this.rep_id = rep_id;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public Long getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(Long folder_id) {
		this.folder_id = folder_id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Long getFd_stage_lid() {
		return fd_stage_lid;
	}

	public void setFd_stage_lid(Long fd_stage_lid) {
		this.fd_stage_lid = fd_stage_lid;
	}

	public int getFd_rec_status() {
		return fd_rec_status;
	}

	public void setFd_rec_status(int fd_rec_status) {
		this.fd_rec_status = fd_rec_status;
	}

	public Integer getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(Integer doc_type) {
		this.doc_type = doc_type;
	}

	public Integer getFd_edit_mode() {
		return fd_edit_mode;
	}

	public void setFd_edit_mode(Integer fd_edit_mode) {
		this.fd_edit_mode = fd_edit_mode;
	}

	public List<MetaData> getMetaDataList() {
		return metaDataList;
	}

	public void setMetaDataList(List<MetaData> metaDataList) {
		this.metaDataList = metaDataList;
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

	public List<JudgementFileDetail> getJudgementfileList() {
		return judgementfileList;
	}

	public void setJudgementfileList(List<JudgementFileDetail> judgementfileList) {
		this.judgementfileList = judgementfileList;
	}

	public List<Document> getReopencasefileList() {
		return reopencasefileList;
	}

	public void setReopencasefileList(List<Document> reopencasefileList) {
		this.reopencasefileList = reopencasefileList;
	}

	public String getFd_reject_status() {
		return fd_reject_status;
	}

	public void setFd_reject_status(String fd_reject_status) {
		this.fd_reject_status = fd_reject_status;
	}

	public List<CaseFileStage> getCaseFileStages() {
		return caseFileStages;
	}

	public void setCaseFileStages(List<CaseFileStage> caseFileStages) {
		this.caseFileStages = caseFileStages;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Date getJudgement_date() {
		return judgement_date;
	}

	public void setJudgement_date(Date judgement_date) {
		this.judgement_date = judgement_date;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public Long getFd_assign_to() {
		return fd_assign_to;
	}

	public void setFd_assign_to(Long fd_assign_to) {
		this.fd_assign_to = fd_assign_to;
	}

	public String getEncrypted_name() {
		return encrypted_name;
	}

	public void setEncrypted_name(String encrypted_name) {
		this.encrypted_name = encrypted_name;
	}

	public Long getCaseType() {
		return caseType;
	}

	public void setCaseType(Long caseType) {
		this.caseType = caseType;
	}

	public String getCaseYear() {
		return caseYear;
	}

	public void setCaseYear(String caseYear) {
		this.caseYear = caseYear;
	}

	public String getBenchCode() {
		return benchCode;
	}

	public void setBenchCode(String benchCode) {
		this.benchCode = benchCode;
	}

	public Integer getNoofdocs() {
		return noofdocs;
	}

	public void setNoofdocs(Integer noofdocs) {
		this.noofdocs = noofdocs;
	}

	
	
	
	
}
