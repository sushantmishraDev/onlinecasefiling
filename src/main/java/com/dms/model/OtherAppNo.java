package com.dms.model;

public class OtherAppNo {

	private String colId;

	private Long at_id;
	private Integer no;
	private Integer year;
	private Long sb_ap_from_page;
	private Long sb_ap_to_page;

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getColId() {
		return colId;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public Long getAt_id() {
		return at_id;
	}

	public void setAt_id(Long at_id) {
		this.at_id = at_id;
	}

	public Long getSb_ap_from_page() {
		return sb_ap_from_page;
	}

	public void setSb_ap_from_page(Long sb_ap_from_page) {
		this.sb_ap_from_page = sb_ap_from_page;
	}

	public Long getSb_ap_to_page() {
		return sb_ap_to_page;
	}

	public void setSb_ap_to_page(Long sb_ap_to_page) {
		this.sb_ap_to_page = sb_ap_to_page;
	}

	@Override
	public String toString() {
		return "OtherAppNo [colId=" + colId + ", at_id=" + at_id + ", no=" + no + ", year=" + year
				+ ", sb_ap_from_page=" + sb_ap_from_page + ", sb_ap_to_page=" + sb_ap_to_page + "]";
	}

}

