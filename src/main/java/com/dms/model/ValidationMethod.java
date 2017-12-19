package com.dms.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="validation_methods")
public class ValidationMethod {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="MstrSeq")
	@SequenceGenerator(name="MstrSeq", sequenceName="MstrSeq", allocationSize=1)
	@Column(name = "vm_id")
	private Long vm_id;
	
	@Column(name="vm_name")
	private String vm_name;
	
	@Column(name="title")
	private String title ;
	
	
	@Column(name="vm_value")
	private  String vm_value;
	
	@Column(name="vm_attribute")
	private String vm_attribute ;
	
	@Column(name="vm_cr_by")
	private  Long vm_cr_by ;
	
	@Column(name="vm_cr_date")
	private  Date vm_cr_date;
	
	@Column(name="vm_mod_by")
	private Long vm_mod_by ;
	
	
	@Column(name="vm_mod_date")
	private Date vm_mod_date ;
	
	@Column(name="vm_rec_status")
	private Integer vm_rec_status ;

	public Long getVm_id() {
		return vm_id;
	}

	public void setVm_id(Long vm_id) {
		this.vm_id = vm_id;
	}

	public String getVm_name() {
		return vm_name;
	}

	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVm_value() {
		return vm_value;
	}

	public void setVm_value(String vm_value) {
		this.vm_value = vm_value;
	}

	public String getVm_attribute() {
		return vm_attribute;
	}

	public void setVm_attribute(String vm_attribute) {
		this.vm_attribute = vm_attribute;
	}

	public Long getVm_cr_by() {
		return vm_cr_by;
	}

	public void setVm_cr_by(Long vm_cr_by) {
		this.vm_cr_by = vm_cr_by;
	}

	public Date getVm_cr_date() {
		return vm_cr_date;
	}

	public void setVm_cr_date(Date vm_cr_date) {
		this.vm_cr_date = vm_cr_date;
	}

	public Long getVm_mod_by() {
		return vm_mod_by;
	}

	public void setVm_mod_by(Long vm_mod_by) {
		this.vm_mod_by = vm_mod_by;
	}

	public Date getVm_mod_date() {
		return vm_mod_date;
	}

	public void setVm_mod_date(Date vm_mod_date) {
		this.vm_mod_date = vm_mod_date;
	}

	public Integer getVm_rec_status() {
		return vm_rec_status;
	}

	public void setVm_rec_status(Integer vm_rec_status) {
		this.vm_rec_status = vm_rec_status;
	}
	

	
	
	
	
	
}
