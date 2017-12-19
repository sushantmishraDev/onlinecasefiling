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

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "caveat_petitioner_details")
public class CaveatPetitionerDetails {


		@Id
		@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="caveat_pet_seq")
		@SequenceGenerator(name="caveat_pet_seq", sequenceName="caveat_pet_seq", allocationSize=1)
		@Column(name = "cpt_id")
		private Long cpt_id;
		
		@Column(name="cpt_cav_mid")
		private Long cpt_cav_mid;
		
		@Column(name = "cpt_name")
		private String cpt_name;
		
		@Column(name = "cpt_email")
		private String cpt_email;
		
		@Column(name = "cpt_mobile")
		private Long  cpt_mobile;
		
		@Column(name = "cpt_address")
		private String cpt_address;
		
		@Column(name = "cpt_pincode")
		private Long  cpt_pincode;
		
		@Column(name = "cpt_other_contact")
		private String cpt_other_contact;
		
		@Column(name="cpt_cr_by")
		private Long cpt_cr_by;
		
		@Column(name="cpt_cr_date")
		private Date cpt_cr_date;	
		
		@Column(name="cpt_mod_by")
		private Long cpt_mod_by;	
		
		@Column(name="cpt_mod_date")
		private Date cpt_mod_date;
		
		@Column(name = "cpt_rec_status")
		private Integer cpt_rec_status;
		
		@Column(name = "cpt_sequence")
		private Long cpt_sequence;
		
		@Column(name="cpt_s_id")
		private Long cpt_s_id;
		
		

		@Column(name="cpt_city")
		private String cpt_city;

		public Long getCpt_id() {
			return cpt_id;
		}

		public void setCpt_id(Long cpt_id) {
			this.cpt_id = cpt_id;
		}

		

		public Long getCpt_cav_mid() {
			return cpt_cav_mid;
		}

		public void setCpt_cav_mid(Long cpt_cav_mid) {
			this.cpt_cav_mid = cpt_cav_mid;
		}

		public String getCpt_name() {
			return cpt_name;
		}

		public void setCpt_name(String cpt_name) {
			this.cpt_name = cpt_name;
		}

		public String getCpt_email() {
			return cpt_email;
		}

		public void setCpt_email(String cpt_email) {
			this.cpt_email = cpt_email;
		}

		public Long getCpt_mobile() {
			return cpt_mobile;
		}

		public void setCpt_mobile(Long cpt_mobile) {
			this.cpt_mobile = cpt_mobile;
		}

		public String getCpt_address() {
			return cpt_address;
		}

		public void setCpt_address(String cpt_address) {
			this.cpt_address = cpt_address;
		}

		public Long getCpt_pincode() {
			return cpt_pincode;
		}

		public void setCpt_pincode(Long cpt_pincode) {
			this.cpt_pincode = cpt_pincode;
		}

		public String getCpt_other_contact() {
			return cpt_other_contact;
		}

		public void setCpt_other_contact(String cpt_other_contact) {
			this.cpt_other_contact = cpt_other_contact;
		}

		public Long getCpt_cr_by() {
			return cpt_cr_by;
		}

		public void setCpt_cr_by(Long cpt_cr_by) {
			this.cpt_cr_by = cpt_cr_by;
		}

		public Date getCpt_cr_date() {
			return cpt_cr_date;
		}

		public void setCpt_cr_date(Date cpt_cr_date) {
			this.cpt_cr_date = cpt_cr_date;
		}

		public Long getCpt_mod_by() {
			return cpt_mod_by;
		}

		public void setCpt_mod_by(Long cpt_mod_by) {
			this.cpt_mod_by = cpt_mod_by;
		}

		public Date getCpt_mod_date() {
			return cpt_mod_date;
		}

		public void setCpt_mod_date(Date cpt_mod_date) {
			this.cpt_mod_date = cpt_mod_date;
		}

		public Integer getCpt_rec_status() {
			return cpt_rec_status;
		}

		public void setCpt_rec_status(Integer cpt_rec_status) {
			this.cpt_rec_status = cpt_rec_status;
		}

		public Long getCpt_sequence() {
			return cpt_sequence;
		}

		public void setCpt_sequence(Long cpt_sequence) {
			this.cpt_sequence = cpt_sequence;
		}

		public Long getCpt_s_id() {
			return cpt_s_id;
		}

		public void setCpt_s_id(Long cpt_s_id) {
			this.cpt_s_id = cpt_s_id;
		}

		public String getCpt_city() {
			return cpt_city;
		}

		public void setCpt_city(String cpt_city) {
			this.cpt_city = cpt_city;
		}
		
		
		
}
