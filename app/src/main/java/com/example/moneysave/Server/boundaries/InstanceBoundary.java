package com.example.moneysave.Server.boundaries;

import java.util.Date;
import java.util.Map;

public class InstanceBoundary {
	
	private InstanceId instanceId; //typename
	private String type;
	private String name;
	private Boolean active;
	private Date createdTimestamp;
	private CreatedBy createdBy;
	private Location location;
	private Map<String, Object> instanceAttributes;
	
	public InstanceBoundary() {
	}


	public InstanceBoundary(InstanceId instanceId, String type, String name, boolean active, Date timeStamp,
			CreatedBy creator, Location myLocation, Map<String, Object> flexibleField) {
		this();
		this.instanceId = instanceId;
		this.type = type;
		this.name = name;
		this.active = active;
		this.createdTimestamp = timeStamp;
		this.createdBy = creator;
		this.location = myLocation;
		this.instanceAttributes = flexibleField;
	}


	public InstanceId getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(InstanceId instanceId) {
		this.instanceId = instanceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public CreatedBy getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(CreatedBy creator) {
		this.createdBy = creator;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location myLocation) {
		this.location = myLocation;
	}

	public Map<String, Object> getInstanceAttributes() {
		return instanceAttributes;
	}

	public void setInstanceAttributes(Map<String, Object> flexibleField) {
		this.instanceAttributes = flexibleField;
	}


	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}


	public void setCreatedTimestamp(Date createdTimestemp) {
		this.createdTimestamp = createdTimestemp;
	}


	public Boolean getActive() {
		return active;
	}


	@Override
	public String toString() {
		return "InstanceBoundary [instanceId=" + instanceId + ", type=" + type + ", name=" + name + ", active=" + active
				+ ", timeStamp=" + createdTimestamp + ", creator=" + createdBy + ", myLocation=" + location
				+ ", flexibleField=" + instanceAttributes + "]";
	}
	
	
}
