package com.dms.model;



import java.util.Date;

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
@Table(name="allow_efiling_1")
public class AllowEfiling1 {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="allow_efiling_1_seq")
	@SequenceGenerator(name="allow_efiling_1_seq", sequenceName="allow_efiling_1_seq", allocationSize=1)
	@Column(name="ae1_id")
	private Long ae1_id;
	
	@Column(name="ae_reference_mid")
	private Long ae_reference_mid;
	
	@Column(name="ae_fd_mid")
	private Long ae_fd_mid;
	
	@Column(name="ae_code")
	private Integer ae_code;	
	
	@Column(name="ae_status")
	private Integer ae_status;
	
	@Column(name="ae_allow_for")
	private Character ae_allow_for;
	
	@Column(name="ae_ex_affidavit_status")
	private Character ae_ex_affidavit_status;
	
	@Column(name="ae_case_type")
	private Long ae_case_type;
	
	@Column(name="ae_case_year")
	private Integer ae_case_year;
	
	@Column(name="ae_case_types")
	private  String ae_case_types;
	
	@Column(name="ae_case_no")
	private  String ae_case_no;
	
	
	@Column(name="ae_aor")
	private  String ae_aor;
	
	@Column(name="ae_appno")
	private  String ae_appno;
	
	@Column(name="ae_pet_name")
	private  String ae_pet_name;
	
	
	



	public String getAe_appno() {
		return ae_appno;
	}

	public void setAe_appno(String ae_appno) {
		this.ae_appno = ae_appno;
	}

	public String getAe_pet_name() {
		return ae_pet_name;
	}

	public void setAe_pet_name(String ae_pet_name) {
		this.ae_pet_name = ae_pet_name;
	}

	public Character getAe_ex_affidavit_status() {
		return ae_ex_affidavit_status;
	}

	public void setAe_ex_affidavit_status(Character ae_ex_affidavit_status) {
		this.ae_ex_affidavit_status = ae_ex_affidavit_status;
	}

	public String getAe_aor() {
		return ae_aor;
	}

	public void setAe_aor(String ae_aor) {
		this.ae_aor = ae_aor;
	}

	public Integer getAe_case_year() {
		return ae_case_year;
	}

	public void setAe_case_year(Integer ae_case_year) {
		this.ae_case_year = ae_case_year;
	}

	public String getAe_case_types() {
		return ae_case_types;
	}

	public void setAe_case_types(String ae_case_types) {
		this.ae_case_types = ae_case_types;
	}

	public String getAe_case_no() {
		return ae_case_no;
	}

	public void setAe_case_no(String ae_case_no) {
		this.ae_case_no = ae_case_no;
	}

	public Long getAe_case_type() {
		return ae_case_type;
	}

	public void setAe_case_type(Long ae_case_type) {
		this.ae_case_type = ae_case_type;
	}

	public Character getAe_allow_for() {
		return ae_allow_for;
	}

	public void setAe_allow_for(Character ae_allow_for) {
		this.ae_allow_for = ae_allow_for;
	}



	public Long getAe_reference_mid() {
		return ae_reference_mid;
	}

	public void setAe_reference_mid(Long ae_reference_mid) {
		this.ae_reference_mid = ae_reference_mid;
	}

	public Long getAe_fd_mid() {
		return ae_fd_mid;
	}

	public void setAe_fd_mid(Long ae_fd_mid) {
		this.ae_fd_mid = ae_fd_mid;
	}

	public Integer getAe_code() {
		return ae_code;
	}

	public void setAe_code(Integer ae_code) {
		this.ae_code = ae_code;
	}

	public Integer getAe_status() {
		return ae_status;
	}

	public void setAe_status(Integer ae_status) {
		this.ae_status = ae_status;
	}

	public Long getAe1_id() {
		return ae1_id;
	}

	public void setAe1_id(Long ae1_id) {
		this.ae1_id = ae1_id;
	}

	
	
	
	
	
}