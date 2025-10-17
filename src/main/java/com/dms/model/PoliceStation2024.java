package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="police_station_2024")
public class PoliceStation2024 {
	
	@Id
	 @Column(name = "pst_id")
	private Long pst_id;
	
	 @Column(name = "police_st_code")
	 private Integer police_st_code;
	 
	 @Column(name = "uniform_code")
	 private Integer uniform_code;
	 
	 @Column(name = "CCTNS_DISTRICT")
	 private String CCTNS_DISTRICT;
	 
	 @Column(name = "CCTNS_PSCODE")
	 private String CCTNS_PSCODE;
	 
	 @Column(name = "police_st_name")
	 private String police_st_name;
	 
	 @Column(name = "phone")
	 private String phone;
	 
	 @Column(name = "email")
	 private String email;
	 
	 @Column(name = "mobileno")
	 private String mobileno;
	 
	 @Column(name = "display")
	 private String display ;
	 
	 @Column(name = "dist_code")
	 private Integer dist_code;
	 
	 @Column(name = "dist_name")
	 private String dist_name;
	 
	 @Column(name = "taluka_code")
	 private Integer taluka_code;
	 
	 @Column(name = "unique_code")
	 private Integer unique_code;
	 
	 @Column(name = "zonecode")
	 private String zonecode;
	 
	 @Column(name = "state_id")
	 private String state_id;
	 
	 @Column(name = "est_code_src")
	 private String est_code_src;
	 
	 @Column(name = "last_fir_no")
	 private Integer last_fir_no;
	 
	 @Column(name = "last_fir_year")
	 private Integer last_fir_year;
	 
	 @Column(name = "last_chargesheet_date")
	 private String last_chargesheet_date;
	 
	 @Column(name = "active")
	 private String active;

	public Long getPst_id() {
		return pst_id;
	}

	public void setPst_id(Long pst_id) {
		this.pst_id = pst_id;
	}

	public Integer getPolice_st_code() {
		return police_st_code;
	}

	public void setPolice_st_code(Integer police_st_code) {
		this.police_st_code = police_st_code;
	}

	public Integer getUniform_code() {
		return uniform_code;
	}

	public void setUniform_code(Integer uniform_code) {
		this.uniform_code = uniform_code;
	}

	public String getCCTNS_DISTRICT() {
		return CCTNS_DISTRICT;
	}

	public void setCCTNS_DISTRICT(String cCTNS_DISTRICT) {
		CCTNS_DISTRICT = cCTNS_DISTRICT;
	}

	public String getCCTNS_PSCODE() {
		return CCTNS_PSCODE;
	}

	public void setCCTNS_PSCODE(String cCTNS_PSCODE) {
		CCTNS_PSCODE = cCTNS_PSCODE;
	}

	public String getPolice_st_name() {
		return police_st_name;
	}

	public void setPolice_st_name(String police_st_name) {
		this.police_st_name = police_st_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Integer getDist_code() {
		return dist_code;
	}

	public void setDist_code(Integer dist_code) {
		this.dist_code = dist_code;
	}

	public String getDist_name() {
		return dist_name;
	}

	public void setDist_name(String dist_name) {
		this.dist_name = dist_name;
	}

	public Integer getTaluka_code() {
		return taluka_code;
	}

	public void setTaluka_code(Integer taluka_code) {
		this.taluka_code = taluka_code;
	}

	public Integer getUnique_code() {
		return unique_code;
	}

	public void setUnique_code(Integer unique_code) {
		this.unique_code = unique_code;
	}

	public String getZonecode() {
		return zonecode;
	}

	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getEst_code_src() {
		return est_code_src;
	}

	public void setEst_code_src(String est_code_src) {
		this.est_code_src = est_code_src;
	}

	public Integer getLast_fir_no() {
		return last_fir_no;
	}

	public void setLast_fir_no(Integer last_fir_no) {
		this.last_fir_no = last_fir_no;
	}

	public Integer getLast_fir_year() {
		return last_fir_year;
	}

	public void setLast_fir_year(Integer last_fir_year) {
		this.last_fir_year = last_fir_year;
	}

	public String getLast_chargesheet_date() {
		return last_chargesheet_date;
	}

	public void setLast_chargesheet_date(String last_chargesheet_date) {
		this.last_chargesheet_date = last_chargesheet_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	 
	 

}
