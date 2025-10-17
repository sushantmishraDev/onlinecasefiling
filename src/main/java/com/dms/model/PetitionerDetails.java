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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "petitioner_details")
public class PetitionerDetails {


		@Id
		@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="petitionerdetails_seq")
		@SequenceGenerator(name="petitionerdetails_seq", sequenceName="petitionerdetails_seq", allocationSize=1)
		@Column(name = "pt_id")
		private Long pt_id;
		
		@Column(name="pt_rcd_mid")
		private Long pt_rcd_mid;
		
		public Long getPt_rcd_mid() {
			return pt_rcd_mid;
		}

		public void setPt_rcd_mid(Long pt_rcd_mid) {
			this.pt_rcd_mid = pt_rcd_mid;
		}

		@Column(name = "pt_name")
		private String pt_name;
		
		@Column(name = "pt_email")
		private String pt_email;
		
		@Column(name = "pt_mobile")
		private Long  pt_mobile;
		
		@Column(name = "pt_address")
		private String pt_address;
		
		@Column(name = "pt_pincode")
		private Long  pt_pincode;
		
		@Column(name = "pt_other_contact")
		private String pt_other_contact;
		
		@Column(name="pt_cr_by")
		private Long pt_cr_by;
		
		@Column(name="pt_cr_date")
		private Date pt_cr_date;	
		
		@Column(name="pt_mod_by")
		private Long pt_mod_by;	
		
		@Column(name="pt_mod_date")
		private Date pt_mod_date;
		
		@Column(name = "pt_rec_status")
		private Integer pt_rec_status;
		
		
		@Column(name = "pt_sequence")
		private Long pt_sequence;
		
		@Column(name="pt_s_id")
		private Long pt_s_id;
		
		

		@Column(name="pt_city")
		private String pt_city;
		
		@Column(name = "pt_parantage_name")
		private String pt_parantage_name;
		
		@Column(name="pt_adhar")
		private Long pt_adhar;
		
		@Column(name = "pt_parantage")
		private String pt_parantage;
		
		@Column(name = "pt_title")
		private String pt_title;
		
	/*	@Column(name="pt_complainant")
		private String pt_complainant;
		
		@Column(name ="pt_gender")
		private String pt_gender;
		
		@Column(name="pt_age")
		private Long pt_age;*/
	
		
		/*@ManyToOne
		@JoinColumn(name="pt_rcd_mid",referencedColumnName="rcd_id",insertable=false,updatable=false)
		private RegisteredCaseDetails registeredCase;
		*/
		
		@Column(name = "pt_age")
		private Integer pt_age;

		public Integer getPt_age() {
			return pt_age;
		}

		public void setPt_age(Integer pt_age) {
			this.pt_age = pt_age;
		}

		public String getPt_title() {
			return pt_title;
		}

		public void setPt_title(String pt_title) {
			this.pt_title = pt_title;
		}

		public String getPt_parantage_name() {
			return pt_parantage_name;
		}

		public void setPt_parantage_name(String pt_parantage_name) {
			this.pt_parantage_name = pt_parantage_name;
		}

		public Long getPt_adhar() {
			return pt_adhar;
		}

		public void setPt_adhar(Long pt_adhar) {
			this.pt_adhar = pt_adhar;
		}

		public String getPt_parantage() {
			return pt_parantage;
		}

		public void setPt_parantage(String pt_parantage) {
			this.pt_parantage = pt_parantage;
		}

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

		public String getPt_email() {
			return pt_email;
		}

		public void setPt_email(String pt_email) {
			this.pt_email = pt_email;
		}

		public Long getPt_mobile() {
			return pt_mobile;
		}

		public void setPt_mobile(Long pt_mobile) {
			this.pt_mobile = pt_mobile;
		}

		public String getPt_address() {
			return pt_address;
		}

		public void setPt_address(String pt_address) {
			this.pt_address = pt_address;
		}

		public Long getPt_pincode() {
			return pt_pincode;
		}

		public void setPt_pincode(Long pt_pincode) {
			this.pt_pincode = pt_pincode;
		}

		public String getPt_other_contact() {
			return pt_other_contact;
		}

		public void setPt_other_contact(String pt_other_contact) {
			this.pt_other_contact = pt_other_contact;
		}

		public Long getPt_cr_by() {
			return pt_cr_by;
		}

		public void setPt_cr_by(Long pt_cr_by) {
			this.pt_cr_by = pt_cr_by;
		}

		public Date getPt_cr_date() {
			return pt_cr_date;
		}

		public void setPt_cr_date(Date pt_cr_date) {
			this.pt_cr_date = pt_cr_date;
		}

		public Long getPt_mod_by() {
			return pt_mod_by;
		}

		public void setPt_mod_by(Long pt_mod_by) {
			this.pt_mod_by = pt_mod_by;
		}

		public Date getPt_mod_date() {
			return pt_mod_date;
		}

		public void setPt_mod_date(Date pt_mod_date) {
			this.pt_mod_date = pt_mod_date;
		}

		public Integer getPt_rec_status() {
			return pt_rec_status;
		}

		public void setPt_rec_status(Integer pt_rec_status) {
			this.pt_rec_status = pt_rec_status;
		}

	

	/*	public RegisteredCaseDetails getRegisteredCase() {
			return registeredCase;
		}

		public void setRegisteredCase(RegisteredCaseDetails registeredCase) {
			this.registeredCase = registeredCase;
		}*/

		public Long getPt_s_id() {
			return pt_s_id;
		}

		public void setPt_s_id(Long pt_s_id) {
			this.pt_s_id = pt_s_id;
		}

		public String getPt_city() {
			return pt_city;
		}

		public void setPt_city(String pt_city) {
			this.pt_city = pt_city;
		}

		public Long getPt_sequence() {
			return pt_sequence;
		}

		public void setPt_sequence(Long pt_sequence) {
			this.pt_sequence = pt_sequence;
		}

/*		public String getPt_complainant() {
			return pt_complainant;
		}

		public void setPt_complainant(String pt_complainant) {
			this.pt_complainant = pt_complainant;
		}

		public String getPt_gender() {
			return pt_gender;
		}

		public void setPt_gender(String pt_gender) {
			this.pt_gender = pt_gender;
		}

		public Long getPt_age() {
			return pt_age;
		}

		public void setPt_age(Long pt_age) {
			this.pt_age = pt_age;
		}
*/
		
		
		
		
		
	
		

}
