package com.dms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "extra_caveator_details")
public class ExtraCaveatorDetails {


	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="caveat_ect_seq")
	@SequenceGenerator(name="caveat_ect_seq", sequenceName="caveat_ect_seq", allocationSize=1)
	@Column(name = "ect_id")
	private Long ect_id;
	
	@Column(name="ect_cav_mid")
	private Long ect_cav_mid;
	
	@Column(name = "ect_name")
	private String ect_name;
	
	@Column(name = "ect_email")
	private String ect_email;
	
	@Column(name = "ect_mobile")
	private Long  ect_mobile;
	
	@Column(name = "ect_address")
	private String ect_address;
	
	@Column(name = "ect_pincode")
	private Long  ect_pincode;
	
	@Column(name = "ect_other_contact")
	private String ect_other_contact;
	
	@Column(name="ect_cr_by")
	private Long ect_cr_by;
	
	@Column(name="ect_cr_date")
	private Date ect_cr_date;	
	
	@Column(name="ect_mod_by")
	private Long ect_mod_by;	
	
	@Column(name="ect_mod_date")
	private Date ect_mod_date;
	
	@Column(name = "ect_rec_status")
	private Integer ect_rec_status;
	
	@Column(name = "ect_sequence")
	private Long ect_sequence;
	
	@Column(name="ect_s_id")
	private Long ect_s_id;
	
	@Column(name="ect_city")
	private String ect_city;

	public Long getEct_id() {
		return ect_id;
	}

	public void setEct_id(Long ect_id) {
		this.ect_id = ect_id;
	}

	public Long getEct_cav_mid() {
		return ect_cav_mid;
	}

	public void setEct_cav_mid(Long ect_cav_mid) {
		this.ect_cav_mid = ect_cav_mid;
	}

	public String getEct_name() {
		return ect_name;
	}

	public void setEct_name(String ect_name) {
		this.ect_name = ect_name;
	}

	public String getEct_email() {
		return ect_email;
	}

	public void setEct_email(String ect_email) {
		this.ect_email = ect_email;
	}

	public Long getEct_mobile() {
		return ect_mobile;
	}

	public void setEct_mobile(Long ect_mobile) {
		this.ect_mobile = ect_mobile;
	}

	public String getEct_address() {
		return ect_address;
	}

	public void setEct_address(String ect_address) {
		this.ect_address = ect_address;
	}

	public Long getEct_pincode() {
		return ect_pincode;
	}

	public void setEct_pincode(Long ect_pincode) {
		this.ect_pincode = ect_pincode;
	}

	public String getEct_other_contact() {
		return ect_other_contact;
	}

	public void setEct_other_contact(String ect_other_contact) {
		this.ect_other_contact = ect_other_contact;
	}

	public Long getEct_cr_by() {
		return ect_cr_by;
	}

	public void setEct_cr_by(Long ect_cr_by) {
		this.ect_cr_by = ect_cr_by;
	}

	public Date getEct_cr_date() {
		return ect_cr_date;
	}

	public void setEct_cr_date(Date ect_cr_date) {
		this.ect_cr_date = ect_cr_date;
	}

	public Long getEct_mod_by() {
		return ect_mod_by;
	}

	public void setEct_mod_by(Long ect_mod_by) {
		this.ect_mod_by = ect_mod_by;
	}

	public Date getEct_mod_date() {
		return ect_mod_date;
	}

	public void setEct_mod_date(Date ect_mod_date) {
		this.ect_mod_date = ect_mod_date;
	}

	public Integer getEct_rec_status() {
		return ect_rec_status;
	}

	public void setEct_rec_status(Integer ect_rec_status) {
		this.ect_rec_status = ect_rec_status;
	}

	public Long getEct_sequence() {
		return ect_sequence;
	}

	public void setEct_sequence(Long ect_sequence) {
		this.ect_sequence = ect_sequence;
	}

	public Long getEct_s_id() {
		return ect_s_id;
	}

	public void setEct_s_id(Long ect_s_id) {
		this.ect_s_id = ect_s_id;
	}

	public String getEct_city() {
		return ect_city;
	}

	public void setEct_city(String ect_city) {
		this.ect_city = ect_city;
	}
		
		
	

}
