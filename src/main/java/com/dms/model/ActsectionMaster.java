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
@Table(name="actsectionmaster")
public class ActsectionMaster {
	
	@Id
	@Column(name = "asm_id")
	private Long asm_id; 
	
	@Column(name = "act_name_hindi")
	private String act_name_hindi;
	
	@Column(name = "act_name_eng")
	private String act_name_eng;
	
	@Column(name = "act_code")
	private Integer act_code;
	
	@Column(name = "section_code")
	private String section_code;
	
	@Column(name = "section")
	private String section;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "acttype")
	private String acttype;
	
	@Column(name = "old_new")
	private String old_new;

	
	
	
	public String getOld_new() {
		return old_new;
	}

	public void setOld_new(String old_new) {
		this.old_new = old_new;
	}

	public Long getAsm_id() {
		return asm_id;
	}

	public void setAsm_id(Long asm_id) {
		this.asm_id = asm_id;
	}

	public String getAct_name_hindi() {
		return act_name_hindi;
	}

	public void setAct_name_hindi(String act_name_hindi) {
		this.act_name_hindi = act_name_hindi;
	}

	public String getAct_name_eng() {
		return act_name_eng;
	}

	public void setAct_name_eng(String act_name_eng) {
		this.act_name_eng = act_name_eng;
	}

	public Integer getAct_code() {
		return act_code;
	}

	public void setAct_code(Integer act_code) {
		this.act_code = act_code;
	}

	public String getSection_code() {
		return section_code;
	}

	public void setSection_code(String section_code) {
		this.section_code = section_code;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActtype() {
		return acttype;
	}

	public void setActtype(String acttype) {
		this.acttype = acttype;
	}
	
	
	

}
