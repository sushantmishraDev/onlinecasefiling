package com.CIS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "civil_t_a")
public class CivilTA {
	


	@Id
	@Column(name = "cino")
	private String cino;
	
	@Column(name = "res_name")
	private String res_name;
	
	
	@Column(name = "pet_name")
	private String pet_name;
	
	@Column(name = "efilno")
	private String efilno;
	
	@Column(name = "regcase_type")
	private Short regcase_type;
	
	@Column(name = "reg_no")
	private Integer reg_no;
	
	@Column(name = "reg_year")
	private Short reg_year;
	
	@Column(name = "case_no")
	private String case_no;

	
	
	public String getCase_no() {
		return case_no;
	}
	
	

	public Short getRegcase_type() {
		return regcase_type;
	}



	public Integer getReg_no() {
		return reg_no;
	}

	public Short getReg_year() {
		return reg_year;
	}

	public String getEfilno() {
		return efilno;
	}

	public String getCino() {
		return cino;
	}

	public String getRes_name() {
		return res_name;
	}

	public String getPet_name() {
		return pet_name;
	}



	
	
	
	

}
