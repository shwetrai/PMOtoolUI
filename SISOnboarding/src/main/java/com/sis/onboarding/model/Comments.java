package com.sis.onboarding.model;

import java.util.UUID;

/**
 * This bean is to handle comments
 * @author shwetrai
 *
 */
public class Comments {
	
	
	private String commentsText;
	private String commentsDate;
	private String commentsBy;
	private String resourceId;
	private UUID id;
	
	public String getCommentsDate() {
		return commentsDate;
	}
	public void setCommentsDate(String commentsDate) {
		this.commentsDate = commentsDate;
	}
	public String getCommentsBy() {
		return commentsBy;
	}
	public void setCommentsBy(String commentsBy) {
		this.commentsBy = commentsBy;
	}
	public String getCommentsText() {
		return commentsText;
	}
	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	
	

}
