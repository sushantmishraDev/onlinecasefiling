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
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

@Entity
@DynamicUpdate
@Table(name = "crime_details")
public class CrimeDetails {
	
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="crime_details_seq")
	@SequenceGenerator(name="crime_details_seq", sequenceName="crime_details_seq", allocationSize=1)
	@Column(name = "cd_id")
	private Long cd_id;
	
	   
	@Column(name = "cd_district")
	private Long  cd_district;
	
	@Column(name = "cd_police_stn")
	private Long  cd_police_stn;

	   
	@Column(name = "cd_crime_no") 
	private String cd_crime_no;
	   

	   
	@Column(name = "cd_cr_by") 
	private  Long cd_cr_by; 
	
	   
	@Column(name = "cd_cr_date") 
	private  Date cd_cr_date;
	   
	@Column(name = "cd_mod_by") 
	private  Long cd_mod_by;
	   
	@Column(name = "cd_mod_date") 
	private  Date cd_mod_date;
	   
	
	 
	@Column(name = "cd_crime_year") 
	private  Integer cd_crime_year;
	  
	
	  
	@Column(name="cd_rcd_mid")
	private Long cd_rcd_mid;
	
	@Column(name = "cd_rec_status") 
	private  Integer cd_rec_status;
  
	
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cd_district",insertable = false, updatable = false)
	private District district;
	
	/*@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cd_police_stn",insertable = false, updatable = false)
	private PoliceStationNew policeStation;*/
	
	
	@Transient
	private PoliceStation2024 policeStation;
	

	public Long getCd_id() {
		return cd_id;
	}

	public void setCd_id(Long cd_id) {
		this.cd_id = cd_id;
	}



	public Long getCd_district() {
		return cd_district;
	}

	public void setCd_district(Long cd_district) {
		this.cd_district = cd_district;
	}

	public String getCd_crime_no() {
		return cd_crime_no;
	}

	public void setCd_crime_no(String cd_crime_no) {
		this.cd_crime_no = cd_crime_no;
	}

	public Long getCd_cr_by() {
		return cd_cr_by;
	}

	public void setCd_cr_by(Long cd_cr_by) {
		this.cd_cr_by = cd_cr_by;
	}

	public Date getCd_cr_date() {
		return cd_cr_date;
	}

	public void setCd_cr_date(Date cd_cr_date) {
		this.cd_cr_date = cd_cr_date;
	}

	public Long getCd_mod_by() {
		return cd_mod_by;
	}

	public void setCd_mod_by(Long cd_mod_by) {
		this.cd_mod_by = cd_mod_by;
	}

	public Date getCd_mod_date() {
		return cd_mod_date;
	}

	public Long getCd_police_stn() {
		return cd_police_stn;
	}

	public void setCd_police_stn(Long cd_police_stn) {
		this.cd_police_stn = cd_police_stn;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	

	/*public PoliceStationNew getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(PoliceStationNew policeStation) {
		this.policeStation = policeStation;
	}*/

	public void setCd_mod_date(Date cd_mod_date) {
		this.cd_mod_date = cd_mod_date;
	}

	public Integer getCd_crime_year() {
		return cd_crime_year;
	}

	public PoliceStation2024 getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(PoliceStation2024 policeStation) {
		this.policeStation = policeStation;
	}

	public void setCd_crime_year(Integer cd_crime_year) {
		this.cd_crime_year = cd_crime_year;
	}

	public Long getCd_rcd_mid() {
		return cd_rcd_mid;
	}

	public void setCd_rcd_mid(Long cd_rcd_mid) {
		this.cd_rcd_mid = cd_rcd_mid;
	}

	public Integer getCd_rec_status() {
		return cd_rec_status;
	}

	public void setCd_rec_status(Integer cd_rec_status) {
		this.cd_rec_status = cd_rec_status;
	}




	
}
