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

@Table(name="linked_case_details")
public class LinkedCaseDetails {
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="linkedcaseseq")
	@SequenceGenerator(name="linkedcaseseq", sequenceName="linkedcaseseq", allocationSize=1)
	@Column(name="lcd_id")
	private Long lcd_id;
	
	@Column(name="lcd_case_no")
	private String lcd_case_no="";
	
	@Column(name="lcd_case_year")
	private Integer lcd_case_year;	
	
	@Column(name="lcd_first_petitioner")
	private String lcd_first_petitioner="";
	
	@Column(name="lcd_first_respondent")
	private String lcd_first_respondent="";
	
	@Column(name="lcd_cr_date")
	private Date lcd_cr_date;	
	
	@Column(name="lcd_cr_by")
	private Long lcd_cr_by;	
		
	@Column(name="lcd_case_type")
	private Long lcd_case_type;
	
	@Column(name="lcd_rec_status")
	private int lcd_rec_status;

	@Column(name="lcd_rcd_mid")
	private Long lcd_rcd_mid;
	
	@OneToOne
	@JoinColumn(name="lcd_case_type",referencedColumnName="ct_id",insertable=false,updatable=false)
	private CaseType caseType;
	
	
	
	
	
	public Long getLcd_id() {
		return lcd_id;
	}

	public void setLcd_id(Long lcd_id) {
		this.lcd_id = lcd_id;
	}

	public String getLcd_case_no() {
		return lcd_case_no;
	}

	public void setLcd_case_no(String lcd_case_no) {
		this.lcd_case_no = lcd_case_no;
	}

	public Integer getLcd_case_year() {
		return lcd_case_year;
	}

	public void setLcd_case_year(Integer lcd_case_year) {
		this.lcd_case_year = lcd_case_year;
	}

	public String getLcd_first_petitioner() {
		return lcd_first_petitioner;
	}

	public void setLcd_first_petitioner(String lcd_first_petitioner) {
		this.lcd_first_petitioner = lcd_first_petitioner;
	}

	public String getLcd_first_respondent() {
		return lcd_first_respondent;
	}

	public void setLcd_first_respondent(String lcd_first_respondent) {
		this.lcd_first_respondent = lcd_first_respondent;
	}

	public Date getLcd_cr_date() {
		return lcd_cr_date;
	}

	public void setLcd_cr_date(Date lcd_cr_date) {
		this.lcd_cr_date = lcd_cr_date;
	}

	public Long getLcd_case_type() {
		return lcd_case_type;
	}

	public void setLcd_case_type(Long lcd_case_type) {
		this.lcd_case_type = lcd_case_type;
	}

	public int getLcd_rec_status() {
		return lcd_rec_status;
	}

	public void setLcd_rec_status(int lcd_rec_status) {
		this.lcd_rec_status = lcd_rec_status;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	public Long getLcd_cr_by() {
		return lcd_cr_by;
	}

	public void setLcd_cr_by(Long lcd_cr_by) {
		this.lcd_cr_by = lcd_cr_by;
	}

	public Long getLcd_rcd_mid() {
		return lcd_rcd_mid;
	}

	public void setLcd_rcd_mid(Long lcd_rcd_mid) {
		this.lcd_rcd_mid = lcd_rcd_mid;
	}

	
	
	
	
	

}
