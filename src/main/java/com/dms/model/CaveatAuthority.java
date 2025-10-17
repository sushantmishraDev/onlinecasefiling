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
@Table(name = "caveat_authority")
public class CaveatAuthority {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caveat_authority_seq")
	@SequenceGenerator(name = "caveat_authority_seq", sequenceName = "caveat_authority_seq", allocationSize = 1)
	@Column(name = "ca_id")
	private Long ca_id;
	
	
	@Column(name = "ca_cav_mid")
	private Long ca_cav_mid;
	
	@Column(name = "ca_cr_by")
	private Long ca_cr_by;
	
	@Column(name = "ca_mod_by")
	private Long ca_mod_by;
	
	@Column(name = "ca_order_date")
	private Date ca_order_date;
	
	@Column(name = "ca_cr_date")
	private Date ca_cr_date;
	
	@Column(name = "ca_mod_date")
	private Date ca_mod_date;
	
	@Column(name = "ca_order_year")
	private Integer ca_order_year;
	
	@Column(name = "ca_order_no")
	private String ca_order_no;
	
	@Column(name = "ca_authority_name")
	private String ca_authority_name;
	
	@Column(name = "ca_rec_status")
	private Integer ca_rec_status;
	
	

	public Long getCa_cr_by() {
		return ca_cr_by;
	}

	public void setCa_cr_by(Long ca_cr_by) {
		this.ca_cr_by = ca_cr_by;
	}

	public Long getCa_mod_by() {
		return ca_mod_by;
	}

	public void setCa_mod_by(Long ca_mod_by) {
		this.ca_mod_by = ca_mod_by;
	}

	public Date getCa_cr_date() {
		return ca_cr_date;
	}

	public void setCa_cr_date(Date ca_cr_date) {
		this.ca_cr_date = ca_cr_date;
	}

	public Date getCa_mod_date() {
		return ca_mod_date;
	}

	public void setCa_mod_date(Date ca_mod_date) {
		this.ca_mod_date = ca_mod_date;
	}

	public Long getCa_id() {
		return ca_id;
	}

	public void setCa_id(Long ca_id) {
		this.ca_id = ca_id;
	}

	public Long getCa_cav_mid() {
		return ca_cav_mid;
	}

	public void setCa_cav_mid(Long ca_cav_mid) {
		this.ca_cav_mid = ca_cav_mid;
	}

	public Date getCa_order_date() {
		return ca_order_date;
	}

	public void setCa_order_date(Date ca_order_date) {
		this.ca_order_date = ca_order_date;
	}

	public Integer getCa_order_year() {
		return ca_order_year;
	}

	public void setCa_order_year(Integer ca_order_year) {
		this.ca_order_year = ca_order_year;
	}

	public String getCa_order_no() {
		return ca_order_no;
	}

	public void setCa_order_no(String ca_order_no) {
		this.ca_order_no = ca_order_no;
	}

	public String getCa_authority_name() {
		return ca_authority_name;
	}

	public void setCa_authority_name(String ca_authority_name) {
		this.ca_authority_name = ca_authority_name;
	}

	public Integer getCa_rec_status() {
		return ca_rec_status;
	}

	public void setCa_rec_status(Integer ca_rec_status) {
		this.ca_rec_status = ca_rec_status;
	}
	
	

}
