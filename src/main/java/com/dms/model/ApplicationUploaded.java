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
@Table(name="application_uploaded")
public class ApplicationUploaded {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="application_uploaded_seq")
	@SequenceGenerator(name="application_uploaded_seq", sequenceName="application_uploaded_seq", allocationSize=1)
	@Column(name = "au_id") 
	private Long au_id;
	
	@Column(name = "au_ap_mid")
	private Long au_ap_mid;
		
	@Column (name="au_document_name")
	private String au_document_name;
	
	@Column (name = "au_no_of_pages")
	private Integer au_no_of_pages;
	
	@Column (name = "au_uploaded_date")
	private Date au_uploaded_date;
		
	@Column (name= "au_rec_status")
	private Integer au_rec_status;

	public Long getAu_id() {
		return au_id;
	}

	public void setAu_id(Long au_id) {
		this.au_id = au_id;
	}

	public Long getAu_ap_mid() {
		return au_ap_mid;
	}

	public void setAu_ap_mid(Long au_ap_mid) {
		this.au_ap_mid = au_ap_mid;
	}

	public String getAu_document_name() {
		return au_document_name;
	}

	public void setAu_document_name(String au_document_name) {
		this.au_document_name = au_document_name;
	}

	public Integer getAu_no_of_pages() {
		return au_no_of_pages;
	}

	public void setAu_no_of_pages(Integer au_no_of_pages) {
		this.au_no_of_pages = au_no_of_pages;
	}

	public Date getAu_uploaded_date() {
		return au_uploaded_date;
	}

	public void setAu_uploaded_date(Date au_uploaded_date) {
		this.au_uploaded_date = au_uploaded_date;
	}

	public Integer getAu_rec_status() {
		return au_rec_status;
	}

	public void setAu_rec_status(Integer au_rec_status) {
		this.au_rec_status = au_rec_status;
	}



	
}
