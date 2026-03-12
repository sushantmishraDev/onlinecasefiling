package com.dms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="case_file_details")
public class CaseFileDetail {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="casefileseq")
	@SequenceGenerator(name="casefileseq", sequenceName="casefileseq", allocationSize=1)
	@Column(name="fd_id")
	private Long fd_id;
	
	@Column(name="fd_case_no")
	private String fd_case_no="";
	
	@Column(name="fd_case_year")
	private Integer fd_case_year;	
	
	@Column(name="fd_first_petitioner")
	private String fd_first_petitioner="";
	
	@Column(name="fd_first_respondent")
	private String fd_first_respondent="";
	
	@Column(name="fd_cr_date")
	private Date fd_cr_date;	
		
	@Column(name="fd_case_type")
	private Long fd_case_type;
	
	@Column(name="fd_rec_status")
	private int fd_rec_status;
	
	@Column(name="fd_cr_by")
	private Long fd_cr_by;
	
	@Column(name="fd_cino")
	private String fd_cino;
	
	@Column(name="fd_id_old")
	private Long fd_id_old;
	
	@Column(name="fd_old_case_no")
	private String fd_old_case_no="";
	
	@Column(name="fd_old_case_year")
	private Integer fd_old_case_year;	
	
	@Column(name="fd_old_case_type")
	private Long fd_old_case_type;
	
	
	@Column(name="fd_ccms_case_id")
	private Long fd_ccms_case_id;
	
	
	
	
	
	public Long getFd_ccms_case_id() {
		return fd_ccms_case_id;
	}

	public void setFd_ccms_case_id(Long fd_ccms_case_id) {
		this.fd_ccms_case_id = fd_ccms_case_id;
	}

	public Long getFd_id_old() {
		return fd_id_old;
	}

	public void setFd_id_old(Long fd_id_old) {
		this.fd_id_old = fd_id_old;
	}

	public String getFd_old_case_no() {
		return fd_old_case_no;
	}

	public void setFd_old_case_no(String fd_old_case_no) {
		this.fd_old_case_no = fd_old_case_no;
	}

	public Integer getFd_old_case_year() {
		return fd_old_case_year;
	}

	public void setFd_old_case_year(Integer fd_old_case_year) {
		this.fd_old_case_year = fd_old_case_year;
	}

	public Long getFd_old_case_type() {
		return fd_old_case_type;
	}

	public void setFd_old_case_type(Long fd_old_case_type) {
		this.fd_old_case_type = fd_old_case_type;
	}

	public String getFd_cino() {
		return fd_cino;
	}

	public void setFd_cino(String fd_cino) {
		this.fd_cino = fd_cino;
	}

	public Long getFd_cr_by() {
		return fd_cr_by;
	}

	public void setFd_cr_by(Long fd_cr_by) {
		this.fd_cr_by = fd_cr_by;
	}

	@OneToOne
	@JoinColumn(name="fd_case_type",referencedColumnName="ct_id",insertable=false,updatable=false)
	private CaseType caseType;

	public Long getFd_id() {
		return fd_id;
	}

	public void setFd_id(Long fd_id) {
		this.fd_id = fd_id;
	}

	public String getFd_case_no() {
		return fd_case_no;
	}

	public void setFd_case_no(String fd_case_no) {
		this.fd_case_no = fd_case_no;
	}

	public Integer getFd_case_year() {
		return fd_case_year;
	}

	public void setFd_case_year(Integer fd_case_year) {
		this.fd_case_year = fd_case_year;
	}

	public String getFd_first_petitioner() {
		return fd_first_petitioner;
	}

	public void setFd_first_petitioner(String fd_first_petitioner) {
		this.fd_first_petitioner = fd_first_petitioner;
	}

	public String getFd_first_respondent() {
		return fd_first_respondent;
	}

	public void setFd_first_respondent(String fd_first_respondent) {
		this.fd_first_respondent = fd_first_respondent;
	}

	public Date getFd_cr_date() {
		return fd_cr_date;
	}

	public void setFd_cr_date(Date fd_cr_date) {
		this.fd_cr_date = fd_cr_date;
	}

	public Long getFd_case_type() {
		return fd_case_type;
	}

	public void setFd_case_type(Long fd_case_type) {
		this.fd_case_type = fd_case_type;
	}

	public int getFd_rec_status() {
		return fd_rec_status;
	}

	public void setFd_rec_status(int fd_rec_status) {
		this.fd_rec_status = fd_rec_status;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}   
	
	
	
}