package com.dms.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "meta_template")
public class MetaTemplate {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="MstrSeq")
	@SequenceGenerator(name="MstrSeq", sequenceName="MstrSeq", allocationSize=1)
	@Column(name = "mt_id")
	private Long mt_id;
	
	@Column(name = "mt_name")
	private String mt_name;

	
	 @Column(name="mt_cr_by")
	 private Long mt_cr_by;
	 
	 @Column(name="mt_cr_date ")
	 private Date mt_cr_date ;
	 
	 @Column(name="mt_mod_by")
	 private Long mt_mod_by;
	 
	 @Column(name="mt_mod_date")
	 private Date mt_mod_date ;
	 
	@Column(name = "mt_rec_status")
	private Integer mt_rec_status;
	
	@Column(name = "mt_rec_deleted")
	private Integer mt_rec_deleted;
	
	@Column(name="mt_description")
	private String  mt_description;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name = "mf_mt_mid",referencedColumnName="mt_id",insertable = false, updatable = false)	
	private List<MetaField> metaFieldList;

	public Long getMt_id() {
		return mt_id;
	}

	public void setMt_id(Long mt_id) {
		this.mt_id = mt_id;
	}

	public String getMt_name() {
		return mt_name;
	}

	public void setMt_name(String mt_name) {
		this.mt_name = mt_name;
	}

	public Long getMt_cr_by() {
		return mt_cr_by;
	}

	public void setMt_cr_by(Long mt_cr_by) {
		this.mt_cr_by = mt_cr_by;
	}

	public Date getMt_cr_date() {
		return mt_cr_date;
	}

	public void setMt_cr_date(Date mt_cr_date) {
		this.mt_cr_date = mt_cr_date;
	}

	public Long getMt_mod_by() {
		return mt_mod_by;
	}

	public void setMt_mod_by(Long mt_mod_by) {
		this.mt_mod_by = mt_mod_by;
	}

	public Date getMt_mod_date() {
		return mt_mod_date;
	}

	public void setMt_mod_date(Date mt_mod_date) {
		this.mt_mod_date = mt_mod_date;
	}

	public Integer getMt_rec_status() {
		return mt_rec_status;
	}

	public void setMt_rec_status(Integer mt_rec_status) {
		this.mt_rec_status = mt_rec_status;
	}

	public Integer getMt_rec_deleted() {
		return mt_rec_deleted;
	}

	public void setMt_rec_deleted(Integer mt_rec_deleted) {
		this.mt_rec_deleted = mt_rec_deleted;
	}



	public String getMt_description() {
		return mt_description;
	}

	public void setMt_description(String mt_description) {
		this.mt_description = mt_description;
	}

	public List<MetaField> getMetaFieldList() {
		return metaFieldList;
	}

	public void setMetaFieldList(List<MetaField> metaFieldList) {
		this.metaFieldList = metaFieldList;
	}	
	
	
}
