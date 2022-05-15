package com.example.moneysave.Server.boundaries;

public class ActivityId {
	private String domain;
	private String id;
	


	public ActivityId() {
	}
	public ActivityId(String domain, String id) {
		this.domain = domain;
		this.id = id;
	}
	
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	
	
	public String getId() {
		return id;
	}
}
