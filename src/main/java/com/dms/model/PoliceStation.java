package com.dms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "police_station")
public class PoliceStation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "police_stationseq")
	@SequenceGenerator(name = "police_stationseq", sequenceName = "police_stationseq", allocationSize = 1)
	@Column(name = "ps_id")
	private Long ps_id;

	@Column(name = "ps_name")
	private String ps_name;

	@Column(name = "ps_address")
	private String ps_address;

	@Column(name = "ps_location")
	private Long ps_location;

	@Column(name = "ps_cr_by")
	private Long cr_by;

	@Column(name = "ps_cr_date")
	private Date cr_date;

	@Column(name = "ps_mod_by")
	private Long mod_by;

	@Column(name = "ps_mod_date")
	private Date mod_date;

	@Column(name = "ps_rec_status")
	private Long ps_rec_status;
	
	@ManyToOne
    @JoinColumn(name="ps_location",referencedColumnName="lk_id",insertable = false, updatable = false)
    private Lookup lk_branch;
	

	public Lookup getLk_branch() {
		return lk_branch;
	}

	public void setLk_branch(Lookup lk_branch) {
		this.lk_branch = lk_branch;
	}

	public Long getPs_id() {
		return ps_id;
	}

	public void setPs_id(Long ps_id) {
		this.ps_id = ps_id;
	}

	public String getPs_name() {
		return ps_name;
	}

	public void setPs_name(String ps_name) {
		this.ps_name = ps_name;
	}

	public String getPs_address() {
		return ps_address;
	}

	public void setPs_address(String ps_address) {
		this.ps_address = ps_address;
	}

	public Long getPs_location() {
		return ps_location;
	}

	public void setPs_location(Long ps_location) {
		this.ps_location = ps_location;
	}

	public Long getCr_by() {
		return cr_by;
	}

	public void setCr_by(Long cr_by) {
		this.cr_by = cr_by;
	}

	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public Long getMod_by() {
		return mod_by;
	}

	public void setMod_by(Long mod_by) {
		this.mod_by = mod_by;
	}

	public Date getMod_date() {
		return mod_date;
	}

	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}

	public Long getPs_rec_status() {
		return ps_rec_status;
	}

	public void setPs_rec_status(Long ps_rec_status) {
		this.ps_rec_status = ps_rec_status;
	}

}
