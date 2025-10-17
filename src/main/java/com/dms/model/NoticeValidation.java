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
@Table(name = "notice_validation")
public class NoticeValidation {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="notice_valiadate_seq")
	@SequenceGenerator(name="notice_valiadate_seq", sequenceName="notice_valiadate_seq", allocationSize=1)
	@Column(name = "nv_id")
	private Long nv_id;
	
	@Column(name = "nv_req")
	private String nv_req;
	
	@Column(name = "nv_res")
	private String nv_res;
	
	@Column(name = "nv_cr_by")
	private Long nv_cr_by;
	
	@Column(name = "nv_cr_date")
	private Date nv_cr_date;

	public Long getNv_id() {
		return nv_id;
	}

	public void setNv_id(Long nv_id) {
		this.nv_id = nv_id;
	}

	public String getNv_req() {
		return nv_req;
	}

	public void setNv_req(String nv_req) {
		this.nv_req = nv_req;
	}

	public String getNv_res() {
		return nv_res;
	}

	public void setNv_res(String nv_res) {
		this.nv_res = nv_res;
	}

	public Long getNv_cr_by() {
		return nv_cr_by;
	}

	public void setNv_cr_by(Long nv_cr_by) {
		this.nv_cr_by = nv_cr_by;
	}

	public Date getNv_cr_date() {
		return nv_cr_date;
	}

	public void setNv_cr_date(Date nv_cr_date) {
		this.nv_cr_date = nv_cr_date;
	}
	
	
	
	
}
