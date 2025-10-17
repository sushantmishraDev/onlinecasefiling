package com.dms.model;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "scrution_remarks")
public class ScrutionRemark {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scrution_remark_seq")
	@SequenceGenerator(name = "scrution_remark_seq", sequenceName = "scrution_remark_seq", allocationSize = 1)
	@Column(name = "id")
	private Long sr_id;
	
	@Column(name = "sr_rcd_mid")
	private Long sr_rcd_mid;

	@Column(name = "sr_remrk")
	private String sr_remrk;
	
	
	@Column(name = "sr_cr_date")
	private Date sr_cr_date;

	@Column(name = "sr_stage")
	private Long sr_stage;

	public Long getSr_id() {
		return sr_id;
	}

	public void setSr_id(Long sr_id) {
		this.sr_id = sr_id;
	}

	public Long getSr_rcd_mid() {
		return sr_rcd_mid;
	}

	public void setSr_rcd_mid(Long sr_rcd_mid) {
		this.sr_rcd_mid = sr_rcd_mid;
	}

	

	public Date getSr_cr_date() {
		return sr_cr_date;
	}

	public void setSr_cr_date(Date sr_cr_date) {
		this.sr_cr_date = sr_cr_date;
	}

	public Long getSr_stage() {
		return sr_stage;
	}

	public void setSr_stage(Long sr_stage) {
		this.sr_stage = sr_stage;
	}

	public String getSr_remrk() {
		return sr_remrk;
	}

	public void setSr_remrk(String sr_remrk) {
		this.sr_remrk = sr_remrk;
	}

	

	
}
