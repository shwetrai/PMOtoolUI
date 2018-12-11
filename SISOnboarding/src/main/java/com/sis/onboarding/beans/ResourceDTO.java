package com.sis.onboarding.beans;

import java.util.Date;


public class ResourceDTO {
	
    private String id;
	private String resourceId;
	private String name;
	private Date creationDate;
	private ToolingActivity toolingActivities = new ToolingActivity();
	private InductionStatus inductionStatus = new InductionStatus();
	private AssetsOverview assetOverview = new AssetsOverview();
	private Roles resourceRole;
	private Cells resourceCell;	
	private String band;
	private String baseLocation;
	private String currentLocation;
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public Cells getResourceCell() {
		return resourceCell;
	}
	public void setResourceCell(Cells resourceCell) {
		this.resourceCell = resourceCell;
	}
	public Roles getResourceRole() {
		return resourceRole;
	}
	public void setResourceRole(Roles resourceRole) {
		this.resourceRole = resourceRole;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getRoleId() {
//		return roleId;
//	}
//	public void setRoleId(String roleId) {
//		this.roleId = roleId;
//	}
//	public String getCellId() {
//		return cellId;
//	}
//	public void setCellId(String cellId) {
//		this.cellId = cellId;
//	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public ToolingActivity getToolingActivities() {
		
		return toolingActivities;
	}
	public void setToolingActivities(ToolingActivity toolingActivities) {
		this.toolingActivities = toolingActivities;
	}
	public InductionStatus getInductionStatus() {
		return inductionStatus;
	}
	public void setInductionStatus(InductionStatus inductionStatus) {
		this.inductionStatus = inductionStatus;
	}
	public AssetsOverview getAssetOverview() {
		return assetOverview;
	}
	public void setAssetOverview(AssetsOverview assetOverview) {
		this.assetOverview = assetOverview;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public String getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	

}