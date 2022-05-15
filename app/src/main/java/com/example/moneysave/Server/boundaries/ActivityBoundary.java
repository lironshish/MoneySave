package com.example.moneysave.Server.boundaries;

import java.util.Date;
import java.util.Map;

public class ActivityBoundary { //MyUser
	private ActivityId activityId;
	private String type;
	private Instance instance;
	private Date createdTimestamp;
	private CreatedBy invokedBy;
	private Map<String, Object> activityAttributes;
	
	public ActivityBoundary() {
	}
	
	public ActivityBoundary(ActivityId activityId, String type, Instance instance, Date timeStamp, CreatedBy creator,Map<String, Object> activityAttributes) {
		this.activityId = activityId;
		this.type = type;
		this.instance = instance;
		this.createdTimestamp = timeStamp;
		this.invokedBy = creator;
		this.activityAttributes = activityAttributes;
	}	
	
	
	public ActivityId getActivityId() {
		return activityId;
	}
	public void setActivityId(ActivityId activityId) {
		this.activityId = activityId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Instance getInstance() {
		return instance;
	}
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public CreatedBy getInvokedBy() {
		return invokedBy;
	}
	public void setInvokedBy(CreatedBy invokeBy) {
		this.invokedBy = invokeBy;
	}
	public Map<String, Object> getActivityAttributes() {
		return activityAttributes;
	}
	public void setActivityAttributes(Map<String, Object> activityAttributes) {
		this.activityAttributes = activityAttributes;
	}
}
