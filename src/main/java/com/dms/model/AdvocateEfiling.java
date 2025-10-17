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
@Table(name="advocates")
public class AdvocateEfiling {
		
	@Id
	/*@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="advocate_seq")
	@SequenceGenerator(name="advocate_seq", sequenceName="advocate_seq", allocationSize=1)*/
	@Column(name = "adv_id")
	private Long  adv_id;
	
	@Column(name="rollno")
	private String rollNo;
	
	@Column(name="rollyr")
	private Integer rollYear;
	
	@Column(name="title")
	private String title;
	
	@Column(name="name")
	private String name;
	
	@Column(name="clname")
	private String clName;
	
	@Column(name="fhname")
	private String fhName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="enrollcouncil")
	private String enrollCouncil;
	
	@Column(name="enrollNO")
	private Long enrollNo;
	
	@Column(name="enrollyear")
	private Integer enrollYear;
	
/*	@Column(name="enrolldate")
	private String enrollDate;*/
	
	@Column(name="addressr1")
	private String address1;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="location")
	private String location;
	
	@Column(name="otp")
	private Integer otp;

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public Long getAdv_id() {
		return adv_id;
	}

	public void setAdv_id(Long adv_id) {
		this.adv_id = adv_id;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Integer getRollYear() {
		return rollYear;
	}

	public void setRollYear(Integer rollYear) {
		this.rollYear = rollYear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClName() {
		return clName;
	}

	public void setClName(String clName) {
		this.clName = clName;
	}


	public String getFhName() {
		return fhName;
	}

	public void setFhName(String fhName) {
		this.fhName = fhName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEnrollCouncil() {
		return enrollCouncil;
	}

	public void setEnrollCouncil(String enrollCouncil) {
		this.enrollCouncil = enrollCouncil;
	}

	public Long getEnrollNo() {
		return enrollNo;
	}

	public void setEnrollNo(Long enrollNo) {
		this.enrollNo = enrollNo;
	}

	public Integer getEnrollYear() {
		return enrollYear;
	}

	public void setEnrollYear(Integer enrollYear) {
		this.enrollYear = enrollYear;
	}

	/*public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}*/

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
}
