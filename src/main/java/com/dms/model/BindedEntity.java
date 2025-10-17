package com.dms.model;


import java.util.List;

public class BindedEntity {
	private Application application;

	private List<OtherAppNo> otherAppNos;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public List<OtherAppNo> getOtherAppNos() {
		return otherAppNos;
	}

	public void setOtherAppNos(List<OtherAppNo> otherAppNos) {
		this.otherAppNos = otherAppNos;
	}

	@Override
	public String toString() {
		return "Demo [application=" + application + ", otherAppNos=" + otherAppNos + "]";
	}

}
