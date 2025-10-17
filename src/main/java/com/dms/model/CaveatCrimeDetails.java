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
@Table(name = "caveat_crime_details")
public class CaveatCrimeDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caveat_crime_details_seq")
	@SequenceGenerator(name = "caveat_crime_details_seq", sequenceName = "caveat_crime_details_seq", allocationSize = 1)
	@Column(name = "ccd_id")
	private Long ccd_id;
	
	
	@Column(name = "ccd_cav_mid")
	private Long ccd_cav_mid;
	
	@Column(name = "ccd_cr_by")
	private Long ccd_cr_by;
	
	@Column(name = "ccd_mod_by")
	private Long ccd_mod_by;
	
	@Column(name = "ccd_mod_date")
	private Date ccd_mod_date;
	
	@Column(name = "ccd_cr_date")
	private Date ccd_cr_date;
	
	@Column(name = "ccd_district")
	private Long ccd_district;
	
	@Column(name = "ccd_police_stn")
	private Long ccd_police_stn;
	
	@Column(name = "ccd_crime_number")
	private String ccd_crime_number;
	
	@Column(name = "ccd_accused_name")
	private String ccd_accused_name;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ccd_district",insertable = false, updatable = false)
	private District district;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ccd_police_stn",insertable = false, updatable = false)
	private PoliceStationNew policeStation;
	
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public PoliceStationNew getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(PoliceStationNew policeStation) {
		this.policeStation = policeStation;
	}

	public String getCcd_accused_name() {
		return ccd_accused_name;
	}

	public void setCcd_accused_name(String ccd_accused_name) {
		this.ccd_accused_name = ccd_accused_name;
	}

	@Column(name="ccd_crime_year")
	private Integer ccd_crime_year;
	
	@Column(name="ccd_rec_status")
	private Integer ccd_rec_status;
	
	
	

	
	public Long getCcd_cr_by() {
		return ccd_cr_by;
	}

	public void setCcd_cr_by(Long ccd_cr_by) {
		this.ccd_cr_by = ccd_cr_by;
	}

	public Long getCcd_mod_by() {
		return ccd_mod_by;
	}

	public void setCcd_mod_by(Long ccd_mod_by) {
		this.ccd_mod_by = ccd_mod_by;
	}

	public Date getCcd_mod_date() {
		return ccd_mod_date;
	}

	public void setCcd_mod_date(Date ccd_mod_date) {
		this.ccd_mod_date = ccd_mod_date;
	}

	public Date getCcd_cr_date() {
		return ccd_cr_date;
	}

	public void setCcd_cr_date(Date ccd_cr_date) {
		this.ccd_cr_date = ccd_cr_date;
	}

	public Integer getCcd_rec_status() {
		return ccd_rec_status;
	}

	public void setCcd_rec_status(Integer ccd_rec_status) {
		this.ccd_rec_status = ccd_rec_status;
	}

	public Long getCcd_id() {
		return ccd_id;
	}

	public void setCcd_id(Long ccd_id) {
		this.ccd_id = ccd_id;
	}
	
	public Long getCcd_cav_mid() {
		return ccd_cav_mid;
	}

	public void setCcd_cav_mid(Long ccd_cav_mid) {
		this.ccd_cav_mid = ccd_cav_mid;
	}

	public Long getCcd_district() {
		return ccd_district;
	}

	public void setCcd_district(Long ccd_district) {
		this.ccd_district = ccd_district;
	}

	public Long getCcd_police_stn() {
		return ccd_police_stn;
	}

	public void setCcd_police_stn(Long ccd_police_stn) {
		this.ccd_police_stn = ccd_police_stn;
	}

	public String getCcd_crime_number() {
		return ccd_crime_number;
	}

	public void setCcd_crime_number(String ccd_crime_number) {
		this.ccd_crime_number = ccd_crime_number;
	}

	public Integer getCcd_crime_year() {
		return ccd_crime_year;
	}

	public void setCcd_crime_year(Integer ccd_crime_year) {
		this.ccd_crime_year = ccd_crime_year;
	}


	
	
}
