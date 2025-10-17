package com.dms.model;

import java.io.Serializable;

public class LowerCourtTypePk implements Serializable {

	//private lower_trial lt;
	private Long lct_dt_mid;
	private Integer lct_id;
	/*public lower_trial getLt() {
		return lt;
	}
	public void setLt(lower_trial lt) {
		this.lt = lt;
	}*/
	
	
	
	
	
	

	public Long getLct_dt_mid() {
		return lct_dt_mid;
	}

	public Integer getLct_id() {
		return lct_id;
	}

	public void setLct_id(Integer lct_id) {
		this.lct_id = lct_id;
	}

	public void setLct_dt_mid(Long lct_dt_mid) {
		this.lct_dt_mid = lct_dt_mid;
	}

	

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LowerCourtTypePk)) return false;
        LowerCourtTypePk that = (LowerCourtTypePk) o;
        return this.lct_dt_mid.equals(that.lct_dt_mid) && this.lct_id.equals(that.lct_id);
        }
	@Override
	  public int hashCode() {
	    int hsCode;
	    hsCode = lct_dt_mid.hashCode();
	    hsCode = 19 * hsCode+ lct_id.hashCode();
	    return hsCode;
	
}
}
