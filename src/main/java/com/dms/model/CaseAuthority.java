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
@Table(name = "case_authority")
public class CaseAuthority {
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caveat_authority_seq")
	@SequenceGenerator(name = "caveat_authority_seq", sequenceName = "caveat_authority_seq", allocationSize = 1)
	@Column(name = "cau_id")
	private Long cau_id;
	
	
	@Column(name = "cau_rcd_mid")
	private Long cau_rcd_mid;
	
	@Column(name = "cau_cr_by")
	private Long cau_cr_by;
	
	@Column(name = "cau_mod_by")
	private Long cau_mod_by;
	
	@Column(name = "cau_order_date")
	private Date cau_order_date;
	
	@Column(name = "cau_cr_date")
	private Date cau_cr_date;
	
	@Column(name = "cau_mod_date")
	private Date cau_mod_date;
	
	@Column(name = "cau_order_year")
	private Integer cau_order_year;
	
	@Column(name = "cau_order_no")
	private String cau_order_no;
	
	@Column(name = "cau_authority_name")
	private String cau_authority_name;
	
	@Column(name = "cau_rec_status")
	private Integer cau_rec_status;

	public Long getCau_id() {
		return cau_id;
	}

	public void setCau_id(Long cau_id) {
		this.cau_id = cau_id;
	}

	


	public Long getCau_rcd_mid() {
		return cau_rcd_mid;
	}

	public void setCau_rcd_mid(Long cau_rcd_mid) {
		this.cau_rcd_mid = cau_rcd_mid;
	}

	public Long getCau_cr_by() {
		return cau_cr_by;
	}

	public void setCau_cr_by(Long cau_cr_by) {
		this.cau_cr_by = cau_cr_by;
	}

	public Long getCau_mod_by() {
		return cau_mod_by;
	}

	public void setCau_mod_by(Long cau_mod_by) {
		this.cau_mod_by = cau_mod_by;
	}

	public Date getCau_order_date() {
		return cau_order_date;
	}

	public void setCau_order_date(Date cau_order_date) {
		this.cau_order_date = cau_order_date;
	}

	public Date getCau_cr_date() {
		return cau_cr_date;
	}

	public void setCau_cr_date(Date cau_cr_date) {
		this.cau_cr_date = cau_cr_date;
	}

	public Date getCau_mod_date() {
		return cau_mod_date;
	}

	public void setCau_mod_date(Date cau_mod_date) {
		this.cau_mod_date = cau_mod_date;
	}

	public Integer getCau_order_year() {
		return cau_order_year;
	}

	public void setCau_order_year(Integer cau_order_year) {
		this.cau_order_year = cau_order_year;
	}

	public String getCau_order_no() {
		return cau_order_no;
	}

	public void setCau_order_no(String cau_order_no) {
		this.cau_order_no = cau_order_no;
	}

	public String getCau_authority_name() {
		return cau_authority_name;
	}

	public void setCau_authority_name(String cau_authority_name) {
		this.cau_authority_name = cau_authority_name;
	}

	public Integer getCau_rec_status() {
		return cau_rec_status;
	}

	public void setCau_rec_status(Integer cau_rec_status) {
		this.cau_rec_status = cau_rec_status;
	} 
	
	
	
	

}
