package com.dms.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="cact_details")
public class CActDetails {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="cactdetails_seq")
	@SequenceGenerator(name="cactdetails_seq", sequenceName="cactdetails_seq", allocationSize=1)
	  @Column(name = "cact_id")
	   private Long  cact_id;
	   
	  @Column(name = "cact_code")
	  private Long cact_code;
	  
	  @Column(name = "cact_cr_by")
	  private Long cact_cr_by;
	  
	  
	  @Column(name = "cact_cr_date")
	  private Date cact_cr_date;
	  
	  @Column(name = "cact_mod_by")
	  private Long cact_mod_by;
	  
	  @Column(name = "cact_mod_date")
	  private Date cact_mod_date;
	  
	  @Column(name = "cact_rec_status")
	 private  Integer cact_rec_status;
	  
	  @Column(name = "cact_type")
		 private  String cact_type;
	  
	  
	  @Column(name="cact_cav_mid")
	  private Long cact_cav_mid;
	  
	  @OneToOne(cascade = CascadeType.PERSIST)
	  @JoinColumn(name = "cact_code",insertable = false, updatable = false)
	  private ActMaster actMaster;
	  
	  

	public String getCact_type() {
		return cact_type;
	}

	public void setCact_type(String cact_type) {
		this.cact_type = cact_type;
	}

	public Long getCact_id() {
		return cact_id;
	}

	public void setCact_id(Long cact_id) {
		this.cact_id = cact_id;
	}

	public Long getCact_code() {
		return cact_code;
	}

	public void setCact_code(Long cact_code) {
		this.cact_code = cact_code;
	}

	public Long getCact_cr_by() {
		return cact_cr_by;
	}

	public void setCact_cr_by(Long cact_cr_by) {
		this.cact_cr_by = cact_cr_by;
	}

	public Date getCact_cr_date() {
		return cact_cr_date;
	}

	public void setCact_cr_date(Date cact_cr_date) {
		this.cact_cr_date = cact_cr_date;
	}

	public Long getCact_mod_by() {
		return cact_mod_by;
	}

	public void setCact_mod_by(Long cact_mod_by) {
		this.cact_mod_by = cact_mod_by;
	}

	public Date getCact_mod_date() {
		return cact_mod_date;
	}

	public void setCact_mod_date(Date cact_mod_date) {
		this.cact_mod_date = cact_mod_date;
	}

	public Integer getCact_rec_status() {
		return cact_rec_status;
	}

	public void setCact_rec_status(Integer cact_rec_status) {
		this.cact_rec_status = cact_rec_status;
	}

	public Long getCact_cav_mid() {
		return cact_cav_mid;
	}

	public void setCact_cav_mid(Long cact_cav_mid) {
		this.cact_cav_mid = cact_cav_mid;
	}

	public ActMaster getActMaster() {
		return actMaster;
	}

	public void setActMaster(ActMaster actMaster) {
		this.actMaster = actMaster;
	}
	  
	  
	
	
	
	

}
