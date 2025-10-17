package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="case_types")
public class CaseType {
	
	@Id
	@Column(name="ct_id")
	private Long ct_id;
	
	@Column(name="ct_label")
	private  String ct_label;
	
	@Column(name="ct_name")
	private String   ct_name;
	
	@Column(name="ct_bench_code")
	 private Long ct_bench_code;
	
	@Column(name="ct_lk_mid")
	 private Long ct_lk_mid;
	
	@Column(name="ct_status")
	 private Integer ct_status;
	
	@Column(name="ct_type")
	private String ct_type;
	
	
	@Column(name="ct_flag")
	private String ct_flag;

    /* @OneToOne
	@JoinColumn(name="ct_id")
	private RegisteredCaseDetails registeredCaseDetails;
	*/
	
	
	
	
	public String getCt_type() {
		return ct_type;
	}

	public String getCt_flag() {
		return ct_flag;
	}

	public void setCt_flag(String ct_flag) {
		this.ct_flag = ct_flag;
	}

	public void setCt_type(String ct_type) {
		this.ct_type = ct_type;
	}

	public Long getCt_id() {
		return ct_id;
	}

	public void setCt_id(Long ct_id) {
		this.ct_id = ct_id;
	}

	public String getCt_label() {
		return ct_label;
	}

	public void setCt_label(String ct_label) {
		this.ct_label = ct_label;
	}

	public String getCt_name() {
		return ct_name;
	}

	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}

	public Long getCt_bench_code() {
		return ct_bench_code;
	}

	public void setCt_bench_code(Long ct_bench_code) {
		this.ct_bench_code = ct_bench_code;
	}

	public Long getCt_lk_mid() {
		return ct_lk_mid;
	}

	public void setCt_lk_mid(Long ct_lk_mid) {
		this.ct_lk_mid = ct_lk_mid;
	}

	public Integer getCt_status() {
		return ct_status;
	}

	public void setCt_status(Integer ct_status) {
		this.ct_status = ct_status;
	}

/*	public RegisteredCaseDetails getRegisteredCaseDetails() {
		return registeredCaseDetails;
	}

	public void setRegisteredCaseDetails(RegisteredCaseDetails registeredCaseDetails) {
		this.registeredCaseDetails = registeredCaseDetails;
	}
	
	*/
	
	
	

}
