package com.sis.onboarding.model;


public class ResourceStatus {
	
	private String toolingStatus;
	private String inductionStatus;
	private String assetOverview;
	private String name;
	private String cid;
	public String getToolingStatus() {
		return toolingStatus;
	}
	public void setToolingStatus(String toolingStatus) {
		this.toolingStatus = toolingStatus;
	}
	public String getInductionStatus() {
		return inductionStatus;
	}
	public void setInductionStatus(String inductionStatus) {
		this.inductionStatus = inductionStatus;
	}
	public String getAssetOverview() {
		return assetOverview;
	}
	public void setAssetOverview(String assetOverview) {
		this.assetOverview = assetOverview;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}

}
