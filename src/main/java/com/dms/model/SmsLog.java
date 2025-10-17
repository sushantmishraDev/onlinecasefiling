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
@Table(name="sms_log")
public class SmsLog {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="smslog_seq")
	@SequenceGenerator(name="smslog_seq", sequenceName="smslog_seq", allocationSize=1)
	@Column(name="sl_id")
	private Long sl_id;
	
	@Column(name="sl_mobile_no")
	private String sl_mobile_no;
	
	@Column(name="sl_um_mid")
	private Long sl_um_mid;
	
	@Column(name="sl_text")
	private String sl_text;
	
	@Column(name="sl_cr_date")
	private Date sl_cr_date;
	
	@Column(name="sl_status")
	private int sl_status;

	@Column(name="sl_ip_address")
	private String sl_ip_address;
	

	
	
	public Long getSl_um_mid() {
		return sl_um_mid;
	}

	public void setSl_um_mid(Long sl_um_mid) {
		this.sl_um_mid = sl_um_mid;
	}

	public Long getSl_id() {
		return sl_id;
	}

	public void setSl_id(Long sl_id) {
		this.sl_id = sl_id;
	}

	public String getSl_mobile_no() {
		return sl_mobile_no;
	}

	public void setSl_mobile_no(String sl_mobile_no) {
		this.sl_mobile_no = sl_mobile_no;
	}

	public String getSl_text() {
		return sl_text;
	}

	public void setSl_text(String sl_text) {
		this.sl_text = sl_text;
	}

	public Date getSl_cr_date() {
		return sl_cr_date;
	}

	public void setSl_cr_date(Date sl_cr_date) {
		this.sl_cr_date = sl_cr_date;
	}

	public int getSl_status() {
		return sl_status;
	}

	public void setSl_status(int sl_status) {
		this.sl_status = sl_status;
	}

	public String getSl_ip_address() {
		return sl_ip_address;
	}

	public void setSl_ip_address(String sl_ip_address) {
		this.sl_ip_address = sl_ip_address;
	}

	@Override
	public String toString() {
		return "SmsLog [sl_id=" + sl_id + ", sl_mobile_no=" + sl_mobile_no + ", sl_um_mid=" + sl_um_mid + ", sl_text="
				+ sl_text + ", sl_cr_date=" + sl_cr_date + ", sl_status=" + sl_status + ", sl_ip_address="
				+ sl_ip_address + "]";
	}	
	
}
