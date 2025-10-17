package com.dms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "st_no_details")
public class StNoDetails {
	
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="st_no_details_seq")
	@SequenceGenerator(name="st_no_details_seq", sequenceName="st_no_details_seq", allocationSize=1)
	@Column(name = "snd_id")
	private Long snd_id;
	
	@Column(name = "snd_court_type")
	private Integer snd_court_type;
	   
	@Column(name = "snd_lct_mid")
	private Integer  snd_lct_mid;
	   
	@Column(name = "snd_hc_case_type")
	private Long  snd_hc_case_type;
	
	@Column(name = "snd_lc_case_type")
	private Long  snd_lc_case_type;
	   
	@Column(name = "snd_cnr_no") 
	private String snd_cnr_no;
	   
	@Column(name = "snd_judge_name") 
	private  String snd_judge_name;
	   
	@Column(name = "snd_case_no")   
	private  String snd_case_no;
	   
	@Column(name = "snd_district")  
	private Long snd_district;
	   
	@Column(name = "snd_cr_by") 
	private  Long snd_cr_by; 
	
	@Column(name = "snd_s_id") 
	private Long  snd_s_id;
	   
	@Column(name = "snd_cr_date") 
	private  Date snd_cr_date;
	   
	@Column(name = "snd_mod_by") 
	private  Long snd_mod_by;
	   
	@Column(name = "snd_mod_date") 
	private  Date snd_mod_date;
	   
	@Column(name = "snd_decision_date")   
	private Date  snd_decision_date;
	 
	@Column(name = "snd_case_year") 
	private  Long snd_case_year;
	  
	@Column(name = "snd_sequence") 
	private  Integer snd_sequence;
	  
	@Column(name="snd_rcd_mid")
	private Long snd_rcd_mid;
	
	@Column(name = "snd_rec_status") 
	private  Integer snd_rec_status;
	
	/*@Column(name = "snd_establishment")
	private Long snd_establishment;*/
  
/*	public Long getSnd_establishment() {
		return snd_establishment;
	}

	public void setSnd_establishment(Long snd_establishment) {
		this.snd_establishment = snd_establishment;
	}*/

	@Transient
	private District district;
	
	@Transient
	private CaseType  hcCaseType;
	
	@Transient
	private LowerCourtCaseType  lcCaseType;
	  
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
	    @JoinColumn(name="snd_lct_mid", referencedColumnName = "lct_id", insertable = false, updatable = false ),
	    @JoinColumn(name="snd_district", referencedColumnName = "lct_dt_mid", insertable = false, updatable = false)
	})
	/*@JoinColumn(name = "snd_lct_mid",insertable = false, updatable = false)*/
	private LowerCourtTypes courtType;
	  
	public Long getsnd_id() {
		return snd_id;
	}

	public void setsnd_id(Long snd_id) {
		this.snd_id = snd_id;
	}

	public Integer getsnd_court_type() {
		return snd_court_type;
	}

	public void setsnd_court_type(Integer snd_court_type) {
		this.snd_court_type = snd_court_type;
	}

	public Integer getsnd_lct_mid() {
		return snd_lct_mid;
	}

	public void setsnd_lct_mid(Integer snd_lct_mid) {
		this.snd_lct_mid = snd_lct_mid;
	}

	public Long getsnd_hc_case_type() {
		return snd_hc_case_type;
	}

	public void setsnd_hc_case_type(Long snd_hc_case_type) {
		this.snd_hc_case_type = snd_hc_case_type;
	}

	public Long getsnd_lc_case_type() {
		return snd_lc_case_type;
	}

	public void setsnd_lc_case_type(Long snd_lc_case_type) {
		this.snd_lc_case_type = snd_lc_case_type;
	}

	public String getsnd_cnr_no() {
		return snd_cnr_no;
	}

	public void setsnd_cnr_no(String snd_cnr_no) {
		this.snd_cnr_no = snd_cnr_no;
	}

	public String getsnd_judge_name() {
		return snd_judge_name;
	}

	public void setsnd_judge_name(String snd_judge_name) {
		this.snd_judge_name = snd_judge_name;
	}

	public String getsnd_case_no() {
		return snd_case_no;
	}

	public void setsnd_case_no(String snd_case_no) {
		this.snd_case_no = snd_case_no;
	}

	public Long getsnd_district() {
		return snd_district;
	}

	public void setsnd_district(Long snd_district) {
		this.snd_district = snd_district;
	}

	public Long getsnd_cr_by() {
		return snd_cr_by;
	}

	public void setsnd_cr_by(Long snd_cr_by) {
		this.snd_cr_by = snd_cr_by;
	}

	public Long getsnd_s_id() {
		return snd_s_id;
	}

	public void setsnd_s_id(Long snd_s_id) {
		this.snd_s_id = snd_s_id;
	}

	public Date getsnd_cr_date() {
		return snd_cr_date;
	}

	public void setsnd_cr_date(Date snd_cr_date) {
		this.snd_cr_date = snd_cr_date;
	}

	public Long getsnd_mod_by() {
		return snd_mod_by;
	}

	public void setsnd_mod_by(Long snd_mod_by) {
		this.snd_mod_by = snd_mod_by;
	}

	public Date getsnd_mod_date() {
		return snd_mod_date;
	}

	public void setsnd_mod_date(Date snd_mod_date) {
		this.snd_mod_date = snd_mod_date;
	}

	public Date getsnd_decision_date() {
		return snd_decision_date;
	}

	public void setsnd_decision_date(Date snd_decision_date) {
		this.snd_decision_date = snd_decision_date;
	}

	public Long getsnd_case_year() {
		return snd_case_year;
	}

	public void setsnd_case_year(Long snd_case_year) {
		this.snd_case_year = snd_case_year;
	}

	public Integer getsnd_sequence() {
		return snd_sequence;
	}

	public void setsnd_sequence(Integer snd_sequence) {
		this.snd_sequence = snd_sequence;
	}

	public Long getsnd_rcd_mid() {
		return snd_rcd_mid;
	}

	public void setsnd_rcd_mid(Long snd_rcd_mid) {
		this.snd_rcd_mid = snd_rcd_mid;
	}

	public CaseType getHcCaseType() {
		return hcCaseType;
	}

	public void setHcCaseType(CaseType hcCaseType) {
		this.hcCaseType = hcCaseType;
	}

	public LowerCourtCaseType getLcCaseType() {
		return lcCaseType;
	}

	public void setLcCaseType(LowerCourtCaseType lcCaseType) {
		this.lcCaseType = lcCaseType;
	}

	public LowerCourtTypes getCourtType() {
		return courtType;
	}

	public void setCourtType(LowerCourtTypes courtType) {
		this.courtType = courtType;
	}

	public Integer getsnd_rec_status() {
		return snd_rec_status;
	}

	public void setsnd_rec_status(Integer snd_rec_status) {
		this.snd_rec_status = snd_rec_status;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	
}
