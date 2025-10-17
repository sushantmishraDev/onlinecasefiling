package com.dms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.cms.model.Advocate;


@Entity
@DynamicUpdate
@Table(name = "caveat_extra_advocate")
public class CaveatExtraAdvocate {


		@Id
		@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="caveat_extra_advocate_seq")
		@SequenceGenerator(name="caveat_extra_advocate_seq", sequenceName="caveat_extra_advocate_seq", allocationSize=1)
		@Column(name = "cea_id")
		private Long cea_id;
		
		@Column(name="cea_cav_mid")
		private Long cea_cav_mid;
		
		@Column(name="cea_adv_mid")
		private Long cea_adv_mid;
		
		@Column(name = "cea_rec_status")
		private Integer cea_rec_status;
		
		
		@OneToOne(cascade = CascadeType.PERSIST)
		@JoinColumn(name="cea_adv_mid",insertable=false,updatable=false)
		private AdvocateEfiling advocate;

		public AdvocateEfiling getAdvocate() {
			return advocate;
		}

		public void setAdvocate(AdvocateEfiling advocate) {
			this.advocate = advocate;
		}

		public Long getCea_id() {
			return cea_id;
		}

		public void setCea_id(Long cea_id) {
			this.cea_id = cea_id;
		}

		public Long getCea_cav_mid() {
			return cea_cav_mid;
		}

		public void setCea_cav_mid(Long cea_cav_mid) {
			this.cea_cav_mid = cea_cav_mid;
		}

		public Long getCea_adv_mid() {
			return cea_adv_mid;
		}

		public void setCea_adv_mid(Long cea_adv_mid) {
			this.cea_adv_mid = cea_adv_mid;
		}

		public Integer getCea_rec_status() {
			return cea_rec_status;
		}

		public void setCea_rec_status(Integer cea_rec_status) {
			this.cea_rec_status = cea_rec_status;
		}
		
		
		
		

		
}
