package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="category_code")
public class CategoryCode {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="inwardbundleSeq")
	@SequenceGenerator(name="inwardbundleSeq", sequenceName="inwardbundleSeq", allocationSize=1)
	@Column(name="cc_id")
	private Long cc_id ;	
	
	@Column(name="cc_code")
	private String  cc_code;
	
	@Column(name="cc_description")
	private String  cc_description;
	
	@Column(name="cc_rec_status")
	private Integer  cc_rec_status;

	public Long getCc_id() {
		return cc_id;
	}

	public void setCc_id(Long cc_id) {
		this.cc_id = cc_id;
	}

	public String getCc_code() {
		return cc_code;
	}

	public void setCc_code(String cc_code) {
		this.cc_code = cc_code;
	}

	public String getCc_description() {
		return cc_description;
	}

	public void setCc_description(String cc_description) {
		this.cc_description = cc_description;
	}

	public Integer getCc_rec_status() {
		return cc_rec_status;
	}

	public void setCc_rec_status(Integer cc_rec_status) {
		this.cc_rec_status = cc_rec_status;
	}
	
	
	
}
