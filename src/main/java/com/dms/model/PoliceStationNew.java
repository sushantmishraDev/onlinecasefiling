

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
@Table(name = "police_station_new")
public class PoliceStationNew {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "police_station_new_seq")
	@SequenceGenerator(name = "police_station_new_seq", sequenceName = "police_station_new_seq", allocationSize = 1)
	@Column(name = "pt_id")
	private Long pt_id;

	@Column(name = "pt_name")
	private String pt_name;

	@Column(name = "pt_dist_mid")
	private Long pt_dist_mid;

	public Long getPt_id() {
		return pt_id;
	}

	public void setPt_id(Long pt_id) {
		this.pt_id = pt_id;
	}

	public String getPt_name() {
		return pt_name;
	}

	public void setPt_name(String pt_name) {
		this.pt_name = pt_name;
	}

	public Long getPt_dist_mid() {
		return pt_dist_mid;
	}

	public void setPt_dist_mid(Long pt_dist_mid) {
		this.pt_dist_mid = pt_dist_mid;
	}


}

