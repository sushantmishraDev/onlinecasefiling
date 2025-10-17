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
@Table(name = "extra_caveat_impugned_order")
public class ExtarCaveatImpugnedOrder {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="ecimpugnedorder_seq")
	@SequenceGenerator(name="ecimpugnedorder_seq", sequenceName="ecimpugnedorder_seq", allocationSize=1)
	@Column(name = "ec_io_id")
	private Long ec_io_id;
	
	@Column(name = "ec_io_court_type")
	private Integer ec_io_court_type;
	   
	@Column(name = "ec_io_lct_mid")
	private Integer  ec_io_lct_mid;
	   
	@Column(name = "ec_io_hc_case_type")
	private Long  ec_io_hc_case_type;
	
	@Column(name = "ec_io_lc_case_type")
	private Long  ec_io_lc_case_type;
	   
	@Column(name = "ec_io_cnr_no") 
	private String ec_io_cnr_no;
	   
	@Column(name = "ec_io_judge_name") 
	private  String ec_io_judge_name;
	   
	@Column(name = "ec_io_case_no")   
	private  String ec_io_case_no;
	   
	@Column(name = "ec_io_district")  
	private Long ec_io_district;
	   
	@Column(name = "ec_io_cr_by") 
	private  Long ec_io_cr_by; 
	
	@Column(name = "ec_io_s_id") 
	private Long  ec_io_s_id;
	   
	@Column(name = "ec_io_cr_date") 
	private  Date ec_io_cr_date;
	   
	@Column(name = "ec_io_mod_by") 
	private  Long ec_io_mod_by;
	   
	@Column(name = "ec_io_mod_date") 
	private  Date ec_io_mod_date;
	   
	@Column(name = "ec_io_decision_date")   
	private Date  ec_io_decision_date;
	 
	@Column(name = "ec_io_case_year") 
	private  Long ec_io_case_year;
	  
	@Column(name = "ec_io_sequence") 
	private  Integer ec_io_sequence;
	  
	@Column(name="ec_io_cav_mid")
	private Long ec_io_cav_mid;
	
	@Column(name = "ec_io_rec_status") 
	private  Integer ec_io_rec_status;
	
	@Column(name="ec_io_mandamus_order")
	private String  ec_io_mandamus_order;
  
	@Transient
	private District district;
	
	@Transient
	private CaseType  hcCaseType;
	
	@Transient
	private LowerCourtCaseType  lcCaseType;
	  
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumns({
	    @JoinColumn(name="ec_io_lct_mid", referencedColumnName = "lct_id", insertable = false, updatable = false ),
	    @JoinColumn(name="ec_io_district", referencedColumnName = "lct_dt_mid", insertable = false, updatable = false)
	})
	/*@JoinColumn(name = "ec_io_lct_mid",insertable = false, updatable = false)*/
	private LowerCourtTypes courtType;

	public Long getEc_io_id() {
		return ec_io_id;
	}

	public void setEc_io_id(Long ec_io_id) {
		this.ec_io_id = ec_io_id;
	}

	public Integer getEc_io_court_type() {
		return ec_io_court_type;
	}

	public void setEc_io_court_type(Integer ec_io_court_type) {
		this.ec_io_court_type = ec_io_court_type;
	}

	public Integer getEc_io_lct_mid() {
		return ec_io_lct_mid;
	}

	public void setEc_io_lct_mid(Integer ec_io_lct_mid) {
		this.ec_io_lct_mid = ec_io_lct_mid;
	}

	public Long getEc_io_hc_case_type() {
		return ec_io_hc_case_type;
	}

	public void setEc_io_hc_case_type(Long ec_io_hc_case_type) {
		this.ec_io_hc_case_type = ec_io_hc_case_type;
	}

	public Long getEc_io_lc_case_type() {
		return ec_io_lc_case_type;
	}

	public void setEc_io_lc_case_type(Long ec_io_lc_case_type) {
		this.ec_io_lc_case_type = ec_io_lc_case_type;
	}

	public String getEc_io_cnr_no() {
		return ec_io_cnr_no;
	}

	public void setEc_io_cnr_no(String ec_io_cnr_no) {
		this.ec_io_cnr_no = ec_io_cnr_no;
	}

	public String getEc_io_judge_name() {
		return ec_io_judge_name;
	}

	public void setEc_io_judge_name(String ec_io_judge_name) {
		this.ec_io_judge_name = ec_io_judge_name;
	}

	public String getEc_io_case_no() {
		return ec_io_case_no;
	}

	public void setEc_io_case_no(String ec_io_case_no) {
		this.ec_io_case_no = ec_io_case_no;
	}

	public Long getEc_io_district() {
		return ec_io_district;
	}

	public void setEc_io_district(Long ec_io_district) {
		this.ec_io_district = ec_io_district;
	}

	public Long getEc_io_cr_by() {
		return ec_io_cr_by;
	}

	public void setEc_io_cr_by(Long ec_io_cr_by) {
		this.ec_io_cr_by = ec_io_cr_by;
	}

	public Long getEc_io_s_id() {
		return ec_io_s_id;
	}

	public void setEc_io_s_id(Long ec_io_s_id) {
		this.ec_io_s_id = ec_io_s_id;
	}

	public Date getEc_io_cr_date() {
		return ec_io_cr_date;
	}

	public void setEc_io_cr_date(Date ec_io_cr_date) {
		this.ec_io_cr_date = ec_io_cr_date;
	}

	public Long getEc_io_mod_by() {
		return ec_io_mod_by;
	}

	public void setEc_io_mod_by(Long ec_io_mod_by) {
		this.ec_io_mod_by = ec_io_mod_by;
	}

	public Date getEc_io_mod_date() {
		return ec_io_mod_date;
	}

	public void setEc_io_mod_date(Date ec_io_mod_date) {
		this.ec_io_mod_date = ec_io_mod_date;
	}

	public Date getEc_io_decision_date() {
		return ec_io_decision_date;
	}

	public void setEc_io_decision_date(Date ec_io_decision_date) {
		this.ec_io_decision_date = ec_io_decision_date;
	}

	public Long getEc_io_case_year() {
		return ec_io_case_year;
	}

	public void setEc_io_case_year(Long ec_io_case_year) {
		this.ec_io_case_year = ec_io_case_year;
	}

	public Integer getEc_io_sequence() {
		return ec_io_sequence;
	}

	public void setEc_io_sequence(Integer ec_io_sequence) {
		this.ec_io_sequence = ec_io_sequence;
	}

	public Long getEc_io_cav_mid() {
		return ec_io_cav_mid;
	}

	public void setEc_io_cav_mid(Long ec_io_cav_mid) {
		this.ec_io_cav_mid = ec_io_cav_mid;
	}

	public Integer getEc_io_rec_status() {
		return ec_io_rec_status;
	}

	public void setEc_io_rec_status(Integer ec_io_rec_status) {
		this.ec_io_rec_status = ec_io_rec_status;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
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

	public String getEc_io_mandamus_order() {
		return ec_io_mandamus_order;
	}

	public void setEc_io_mandamus_order(String ec_io_mandamus_order) {
		this.ec_io_mandamus_order = ec_io_mandamus_order;
	}

	

	
	
}
