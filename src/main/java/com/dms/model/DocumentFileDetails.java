package com.dms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="document_file_details")
public class DocumentFileDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="documentfiledetails_seq")
	@SequenceGenerator(name="documentfiledetails_seq",sequenceName="documentfiledetails_seq",allocationSize=1)
	@Column(name="dfd_id")
	private Long dfd_id;
	
	@Column(name="dfd_fd_mid")
	private Long dfd_fd_mid;
	
	@Column(name="dfd_barcode")
	private String dfd_barcode;
	
	@Column(name="dfd_file_type")
	private Long dfd_file_type;
	
	@Column(name="dfd_stage_lid")
	private Long dfd_stage_lid;
	
	@Column(name="dfd_rec_status")
	private int dfd_rec_status;

	
	
	public Long getDfd_id() {
		return dfd_id;
	}

	public void setDfd_id(Long dfd_id) {
		this.dfd_id = dfd_id;
	}

	public Long getDfd_fd_mid() {
		return dfd_fd_mid;
	}

	public void setDfd_fd_mid(Long dfd_fd_mid) {
		this.dfd_fd_mid = dfd_fd_mid;
	}

	public String getDfd_barcode() {
		return dfd_barcode;
	}

	public void setDfd_barcode(String dfd_barcode) {
		this.dfd_barcode = dfd_barcode;
	}

	public Long getDfd_file_type() {
		return dfd_file_type;
	}

	public void setDfd_file_type(Long dfd_file_type) {
		this.dfd_file_type = dfd_file_type;
	}

	public Long getDfd_stage_lid() {
		return dfd_stage_lid;
	}

	public void setDfd_stage_lid(Long dfd_stage_lid) {
		this.dfd_stage_lid = dfd_stage_lid;
	}

	public int getDfd_rec_status() {
		return dfd_rec_status;
	}

	public void setDfd_rec_status(int dfd_rec_status) {
		this.dfd_rec_status = dfd_rec_status;
	}


	
}
