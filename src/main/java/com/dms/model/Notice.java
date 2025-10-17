package com.dms.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.transaction.TransactionScoped;

@Entity
@Table(name="notice")
public class Notice {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="notice_seq")
	@SequenceGenerator(name="notice_seq", sequenceName="notice_seq", allocationSize=1)
	@Column(name="nt_id")
	private Long nt_id;
	
	@Column(name="nt_rcd_mid")
	private Long nt_rcd_mid;
	
	
	@Column(name="nt_cr_by")
	private Long nt_cr_by;
	
	public Long getNt_cr_by() {
		return nt_cr_by;
	}

	public void setNt_cr_by(Long nt_cr_by) {
		this.nt_cr_by = nt_cr_by;
	}

	@Column(name="nt_otp")
	private Integer nt_otp;
	
	/*@Column(name="ae_fd_mid")
	private Long ae_fd_mid;*/
	
	@Column(name="nt_res_adv_email")
	private  String nt_res_adv_email;
	
	@Column(name="nt_res_adv_mobile")
	private  String nt_res_adv_mobile;
	
	@Column(name="nt_res_dept_email")
	private  String nt_res_dept_email;
	
	@Column(name="nt_dept_mid")
	private Long nt_dept_mid;
	
	@Column(name="nt_res_dept_mobile")
	private  String nt_res_dept_mobile;
	
	@Column(name="nt_adv_name")
	private  String nt_adv_name;
	
	@Column(name="nt_res_name")
	private  String nt_res_name;
	
	
	
	
		
	public Boolean getAllowInperson() {
		return allowInperson;
	}

	public void setAllowInperson(Boolean allowInperson) {
		this.allowInperson = allowInperson;
	}

	public String getNt_res_dept_email() {
		return nt_res_dept_email;
	}

	public void setNt_res_dept_email(String nt_res_dept_email) {
		this.nt_res_dept_email = nt_res_dept_email;
	}

	public Long getNt_dept_mid() {
		return nt_dept_mid;
	}

	public void setNt_dept_mid(Long nt_dept_mid) {
		this.nt_dept_mid = nt_dept_mid;
	}

	public String getNt_res_dept_mobile() {
		return nt_res_dept_mobile;
	}

	public void setNt_res_dept_mobile(String nt_res_dept_mobile) {
		this.nt_res_dept_mobile = nt_res_dept_mobile;
	}

	public String getNt_res_name() {
		return nt_res_name;
	}

	public void setNt_res_name(String nt_res_name) {
		this.nt_res_name = nt_res_name;
	}

	private transient String doc_name;
	
	private transient Boolean allowOther;
	
	private transient Boolean allowInperson;
	
	private transient Boolean allowDepartment;
	
	private transient Boolean allowEdit;
	
	
	private transient String deptName;
	
	private transient String advName;
	
	private transient Long nt_adv_id;
	
	
	
	
	
	
	
	

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Boolean getAllowDepartment() {
		return allowDepartment;
	}

	public void setAllowDepartment(Boolean allowDepartment) {
		this.allowDepartment = allowDepartment;
	}

	public Boolean getAllowEdit() {
		return allowEdit;
	}

	public void setAllowEdit(Boolean allowEdit) {
		this.allowEdit = allowEdit;
	}

	public Long getNt_adv_id() {
		return nt_adv_id;
	}

	public void setNt_adv_id(Long nt_adv_id) {
		this.nt_adv_id = nt_adv_id;
	}

	public Boolean getAllowOther() {
		return allowOther;
	}

	public void setAllowOther(Boolean allowOther) {
		this.allowOther = allowOther;
	}

	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getNt_adv_name() {
		return nt_adv_name;
	}

	public void setNt_adv_name(String nt_adv_name) {
		this.nt_adv_name = nt_adv_name;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public Long getNt_id() {
		return nt_id;
	}

	public void setNt_id(Long nt_id) {
		this.nt_id = nt_id;
	}

	public Long getNt_rcd_mid() {
		return nt_rcd_mid;
	}

	public void setNt_rcd_mid(Long nt_rcd_mid) {
		this.nt_rcd_mid = nt_rcd_mid;
	}

	public Integer getNt_otp() {
		return nt_otp;
	}

	public void setNt_otp(Integer nt_otp) {
		this.nt_otp = nt_otp;
	}

	public String getNt_res_adv_email() {
		return nt_res_adv_email;
	}

	public void setNt_res_adv_email(String nt_res_adv_email) {
		this.nt_res_adv_email = nt_res_adv_email;
	}

	public String getNt_res_adv_mobile() {
		return nt_res_adv_mobile;
	}

	public void setNt_res_adv_mobile(String nt_res_adv_mobile) {
		this.nt_res_adv_mobile = nt_res_adv_mobile;
	}
	
	
	
	
}
