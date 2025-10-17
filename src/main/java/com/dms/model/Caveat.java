package com.dms.model;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

@Entity
@Table(name = "caveat")
public class Caveat {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caveat_seq")
	@SequenceGenerator(name = "caveat_seq", sequenceName = "caveat_seq", allocationSize = 1)
	@Column(name = "cav_id")
	private Long cav_id;

	@Column(name = "cav_no")
	private Integer cav_no;
	
	@Column(name="cav_year")
	private Integer cav_year;
	
	@Column(name = "cav_type")
	private Long cav_type;

	@Column(name = "cav_dist_mid")
	private Long cav_dist_mid;

	@Column(name = "cav_caveator_name")
	private String cav_caveator_name;
	
	@Column(name = "cav_case_type")
	private String cav_case_type;

	public String getCav_case_type() {
		return cav_case_type;
	}

	public void setCav_case_type(String cav_case_type) {
		this.cav_case_type = cav_case_type;
	}

	@Column(name = "cav_court")
	private Long cav_court;
	
	@Column(name = "cav_court_mid")
	private Long cav_court_mid;
	
	
	@Column(name = "cav_lc_case_type")
	private Long cav_lc_case_type;
	
	@Column(name = "cav_hc_case_type")
	private Long cav_hc_case_type;

	@Column(name = "cav_lc_case_no")
	private String cav_lc_case_no;
	
	@Column(name = "cav_lc_case_year")
	private Integer cav_lc_case_year;

	@Column(name = "cav_ord_psd_by")
	private String cav_ord_psd_by;

	@Column(name = "cav_ord_dist")
	private Long cav_ord_dist;

	@Column(name = "cav_judgmnt_date")
	private Date cav_judgmnt_date;

	@Column(name = "cav_cr_by")
	private Long cav_cr_by;
	
	@Column(name = "cav_cr_date")
	private Date cav_cr_date;

	@Column(name = "cav_stage_lid")
	private Long cav_stage_lid;

	@Column(name = "cav_draft_no")
	private String cav_draft_no;
	
	@Column(name = "cav_assign_to")
	private Long cav_assign_to;
	
	@Column(name = "cav_state_mid")
	private Long cav_state_mid;
	
	@Column(name = "cav_email")
	private String cav_email;
	
	@Column(name = "cav_mobile")
	private String cav_mobile;
	
	@Column(name = "cav_diary_no")
	private String cav_diary_no;
	
	@Column(name = "cav_cnr_no")
	private String cav_cnr_no;
	
	@Column(name="cav_mandamus_order")
	private String  cav_mandamus_order;
	
	@Transient
	private CaveatPetitionerDetails petitionerDetails; 
	
	@Transient
	private CaveatRespondentDetails  respondentDetails;
	
	@Transient
	private CaseType  hcCaseType;
	
	@Transient
	private LowerCourtCaseType  lcCaseType;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cav_type",insertable=false,updatable=false)
	private CaseType caseType;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="cav_ord_dist",insertable=false,updatable=false)
	private District district;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cav_stage_lid",insertable = false, updatable = false)
	private Lookup caseStage;
	
	private transient List<CheckList> checkList;
	
	public Long getCav_id() {
		return cav_id;
	}

	public void setCav_id(Long cav_id) {
		this.cav_id = cav_id;
	}

	public Integer getCav_no() {
		return cav_no;
	}

	public void setCav_no(Integer cav_no) {
		this.cav_no = cav_no;
	}

	public Integer getCav_year() {
		return cav_year;
	}

	public void setCav_year(Integer cav_year) {
		this.cav_year = cav_year;
	}

	public Long getCav_type() {
		return cav_type;
	}

	public void setCav_type(Long cav_type) {
		this.cav_type = cav_type;
	}

	public Long getCav_dist_mid() {
		return cav_dist_mid;
	}

	public void setCav_dist_mid(Long cav_dist_mid) {
		this.cav_dist_mid = cav_dist_mid;
	}

	public String getCav_caveator_name() {
		return cav_caveator_name;
	}

	public void setCav_caveator_name(String cav_caveator_name) {
		this.cav_caveator_name = cav_caveator_name;
	}

	public Long getCav_court() {
		return cav_court;
	}

	public void setCav_court(Long cav_court) {
		this.cav_court = cav_court;
	}

	public Long getCav_lc_case_type() {
		return cav_lc_case_type;
	}

	public void setCav_lc_case_type(Long cav_lc_case_type) {
		this.cav_lc_case_type = cav_lc_case_type;
	}

	public String getCav_lc_case_no() {
		return cav_lc_case_no;
	}

	public void setCav_lc_case_no(String cav_lc_case_no) {
		this.cav_lc_case_no = cav_lc_case_no;
	}

	public Integer getCav_lc_case_year() {
		return cav_lc_case_year;
	}

	public void setCav_lc_case_year(Integer cav_lc_case_year) {
		this.cav_lc_case_year = cav_lc_case_year;
	}


	public String getCav_ord_psd_by() {
		return cav_ord_psd_by;
	}

	public void setCav_ord_psd_by(String cav_ord_psd_by) {
		this.cav_ord_psd_by = cav_ord_psd_by;
	}

	public Long getCav_ord_dist() {
		return cav_ord_dist;
	}

	public void setCav_ord_dist(Long cav_ord_dist) {
		this.cav_ord_dist = cav_ord_dist;
	}

	public Date getCav_judgmnt_date() {
		return cav_judgmnt_date;
	}

	public void setCav_judgmnt_date(Date cav_judgmnt_date) {
		this.cav_judgmnt_date = cav_judgmnt_date;
	}

	public Long getCav_cr_by() {
		return cav_cr_by;
	}

	public void setCav_cr_by(Long cav_cr_by) {
		this.cav_cr_by = cav_cr_by;
	}

	public Date getCav_cr_date() {
		return cav_cr_date;
	}

	public void setCav_cr_date(Date cav_cr_date) {
		this.cav_cr_date = cav_cr_date;
	}

	public Long getCav_stage_lid() {
		return cav_stage_lid;
	}

	public void setCav_stage_lid(Long cav_stage_lid) {
		this.cav_stage_lid = cav_stage_lid;
	}

	public String getCav_draft_no() {
		return cav_draft_no;
	}

	public void setCav_draft_no(String cav_draft_no) {
		this.cav_draft_no = cav_draft_no;
	}

	public Long getCav_assign_to() {
		return cav_assign_to;
	}

	public void setCav_assign_to(Long cav_assign_to) {
		this.cav_assign_to = cav_assign_to;
	}

	public Long getCav_state_mid() {
		return cav_state_mid;
	}

	public void setCav_state_mid(Long cav_state_mid) {
		this.cav_state_mid = cav_state_mid;
	}

	public String getCav_email() {
		return cav_email;
	}

	public void setCav_email(String cav_email) {
		this.cav_email = cav_email;
	}

	public String getCav_mobile() {
		return cav_mobile;
	}

	public void setCav_mobile(String cav_mobile) {
		this.cav_mobile = cav_mobile;
	}

	public Long getCav_hc_case_type() {
		return cav_hc_case_type;
	}

	public void setCav_hc_case_type(Long cav_hc_case_type) {
		this.cav_hc_case_type = cav_hc_case_type;
	}

	public String getCav_diary_no() {
		return cav_diary_no;
	}

	public void setCav_diary_no(String cav_diary_no) {
		this.cav_diary_no = cav_diary_no;
	}

	public CaveatPetitionerDetails getPetitionerDetails() {
		return petitionerDetails;
	}

	public void setPetitionerDetails(CaveatPetitionerDetails petitionerDetails) {
		this.petitionerDetails = petitionerDetails;
	}

	public CaveatRespondentDetails getRespondentDetails() {
		return respondentDetails;
	}

	public void setRespondentDetails(CaveatRespondentDetails respondentDetails) {
		this.respondentDetails = respondentDetails;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	public Lookup getCaseStage() {
		return caseStage;
	}

	public void setCaseStage(Lookup caseStage) {
		this.caseStage = caseStage;
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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Long getCav_court_mid() {
		return cav_court_mid;
	}

	public void setCav_court_mid(Long cav_court_mid) {
		this.cav_court_mid = cav_court_mid;
	}

	public List<CheckList> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<CheckList> checkList) {
		this.checkList = checkList;
	}

	public String getCav_cnr_no() {
		return cav_cnr_no;
	}

	public void setCav_cnr_no(String cav_cnr_no) {
		this.cav_cnr_no = cav_cnr_no;
	}

	public String getCav_mandamus_order() {
		return cav_mandamus_order;
	}

	public void setCav_mandamus_order(String cav_mandamus_order) {
		this.cav_mandamus_order = cav_mandamus_order;
	}

	
	
	
	
}
