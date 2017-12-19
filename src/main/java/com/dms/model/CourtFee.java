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
@Table(name="court_fee")
public class CourtFee {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="courtfee_seq")
	@SequenceGenerator(name="courtfee_seq", sequenceName="courtfee_seq", allocationSize=1)
	@Column(name="cf_id")
    private Long cf_id;
	
	
   @Column(name="cf_receipt_no")
   private String cf_receipt_no;
   
   @Column(name="cf_amount")
    private Long  cf_amount;
   
   @Column(name="cf_date")
	private Date  cf_date;
	
   
	@Column(name="cf_rcd_mid")
	private Long  cf_rcd_mid;


	@Column(name="cf_rec_status")
	private Integer cf_rec_status;
	
	@Column(name="cf_cr_by")
    private Long cf_cr_by;
	
	@Column(name="cf_cr_date")
	private Date  cf_cr_date;
	
	
	@Column(name="cf_mod_by")
	private Long  cf_mod_by;
	
	
	@Column(name="cf_mod_date")
	private Date cf_mod_date;
	
	

	
	
	public Long getCf_id() {
		return cf_id;
	}


	public void setCf_id(Long cf_id) {
		this.cf_id = cf_id;
	}


	public String getCf_receipt_no() {
		return cf_receipt_no;
	}


	public void setCf_receipt_no(String cf_receipt_no) {
		this.cf_receipt_no = cf_receipt_no;
	}

	public Long getCf_amount() {
		return cf_amount;
	}


	public void setCf_amount(Long cf_amount) {
		this.cf_amount = cf_amount;
	}


	public Date getCf_date() {
		return cf_date;
	}


	public void setCf_date(Date cf_date) {
		this.cf_date = cf_date;
	}


	public Long getCf_rcd_mid() {
		return cf_rcd_mid;
	}


	public void setCf_rcd_mid(Long cf_rcd_mid) {
		this.cf_rcd_mid = cf_rcd_mid;
	}


	public Integer getCf_rec_status() {
		return cf_rec_status;
	}


	public void setCf_rec_status(Integer cf_rec_status) {
		this.cf_rec_status = cf_rec_status;
	}


	public Long getCf_cr_by() {
		return cf_cr_by;
	}


	public void setCf_cr_by(Long cf_cr_by) {
		this.cf_cr_by = cf_cr_by;
	}


	public Date getCf_cr_date() {
		return cf_cr_date;
	}


	public void setCf_cr_date(Date cf_cr_date) {
		this.cf_cr_date = cf_cr_date;
	}


	public Long getCf_mod_by() {
		return cf_mod_by;
	}


	public void setCf_mod_by(Long cf_mod_by) {
		this.cf_mod_by = cf_mod_by;
	}


	public Date getCf_mod_date() {
		return cf_mod_date;
	}


	public void setCf_mod_date(Date cf_mod_date) {
		this.cf_mod_date = cf_mod_date;
	}
	
	
	
	
	
	
	
	
	

}
