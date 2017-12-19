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
@Table(name="petition_uploaded")
public class PetitionUploaded {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="petition_uploaded_seq")
	@SequenceGenerator(name="petition_uploaded_seq", sequenceName="petition_uploaded_seq", allocationSize=1)
	@Column(name = "pu_id") 
	private Long pu_id;
	
	@Column(name = "pu_rcd_mid")
	private Long pu_rcd_mid;
		
	@Column (name="pu_document_name")
	private String pu_document_name;
	
	@Column (name = "pu_no_of_pages")
	private Integer pu_no_of_pages;
	
	@Column (name = "pu_uploaded_date")
	private Date pu_uploaded_date;
	
	@Column (name = "pu_uploaded_by")
	private Long pu_uploaded_by;
	
	@Column (name= "pu_rec_status")
	private Integer pu_rec_status;

	public Long getPu_id() {
		return pu_id;
	}

	public void setPu_id(Long pu_id) {
		this.pu_id = pu_id;
	}

	public Long getPu_rcd_mid() {
		return pu_rcd_mid;
	}

	public void setPu_rcd_mid(Long pu_rcd_mid) {
		this.pu_rcd_mid = pu_rcd_mid;
	}

	public String getPu_document_name() {
		return pu_document_name;
	}

	public void setPu_document_name(String pu_document_name) {
		this.pu_document_name = pu_document_name;
	}

	public Integer getPu_no_of_pages() {
		return pu_no_of_pages;
	}

	public void setPu_no_of_pages(Integer pu_no_of_pages) {
		this.pu_no_of_pages = pu_no_of_pages;
	}

	public Date getPu_uploaded_date() {
		return pu_uploaded_date;
	}

	public void setPu_uploaded_date(Date pu_uploaded_date) {
		this.pu_uploaded_date = pu_uploaded_date;
	}

	public Long getPu_uploaded_by() {
		return pu_uploaded_by;
	}

	public void setPu_uploaded_by(Long pu_uploaded_by) {
		this.pu_uploaded_by = pu_uploaded_by;
	}

	public Integer getPu_rec_status() {
		return pu_rec_status;
	}

	public void setPu_rec_status(Integer pu_rec_status) {
		this.pu_rec_status = pu_rec_status;
	}


	
}
