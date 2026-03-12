package com.dms.model;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CaseConversionCCmc {
	
	@JsonProperty("oldregcase_type")
	 String oldregcase_type;
	@JsonProperty("oldreg_no")
	 String oldreg_no;
	@JsonProperty("oldreg_year")
		String oldreg_year;	
	@JsonProperty("newregcase_type")
		String newregcase_type;
	@JsonProperty("newreg_no")
		String newreg_no;
	@JsonProperty("newreg_year")
		String newreg_year;
	@JsonProperty("oldcaseNo")
		String oldcaseNo;
	@JsonProperty("newcaseNo")
		String newcaseNo;
	@JsonProperty("conversionDate")
		String conversionDate;
	public String getOldregcase_type() {
		return oldregcase_type;
	}
	public void setOldregcase_type(String oldregcase_type) {
		this.oldregcase_type = oldregcase_type;
	}
	public String getOldreg_no() {
		return oldreg_no;
	}
	public void setOldreg_no(String oldreg_no) {
		this.oldreg_no = oldreg_no;
	}
	public String getOldreg_year() {
		return oldreg_year;
	}
	public void setOldreg_year(String oldreg_year) {
		this.oldreg_year = oldreg_year;
	}
	public String getNewregcase_type() {
		return newregcase_type;
	}
	public void setNewregcase_type(String newregcase_type) {
		this.newregcase_type = newregcase_type;
	}
	public String getNewreg_no() {
		return newreg_no;
	}
	public void setNewreg_no(String newreg_no) {
		this.newreg_no = newreg_no;
	}
	public String getNewreg_year() {
		return newreg_year;
	}
	public void setNewreg_year(String newreg_year) {
		this.newreg_year = newreg_year;
	}
	public String getOldcaseNo() {
		return oldcaseNo;
	}
	public void setOldcaseNo(String oldcaseNo) {
		this.oldcaseNo = oldcaseNo;
	}
	public String getNewcaseNo() {
		return newcaseNo;
	}
	public void setNewcaseNo(String newcaseNo) {
		this.newcaseNo = newcaseNo;
	}
	public String getConversionDate() {
		return conversionDate;
	}
	public void setConversionDate(String conversionDate) {
		this.conversionDate = conversionDate;
	}
	
	

}
