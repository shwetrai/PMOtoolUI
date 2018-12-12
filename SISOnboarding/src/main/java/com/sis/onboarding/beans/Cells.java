package com.sis.onboarding.beans;

import java.util.Date;



public class Cells {
	
	private String id;
	
	private String cellId;
	
	
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	
	private String cellName;
	
	private Date cellCreationDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public Date getCellCreationDate() {
		return cellCreationDate;
	}
	public void setCellCreationDate(Date cellCreationDate) {
		this.cellCreationDate = cellCreationDate;
	}


}
