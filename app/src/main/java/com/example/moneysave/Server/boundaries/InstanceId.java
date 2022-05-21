package com.example.moneysave.Server.boundaries;

import com.google.gson.internal.LinkedTreeMap;

public class InstanceId {
	private String domain;
	private String id;
	
	public InstanceId() {

	}

	public InstanceId(String domain, String id) {
		this.domain = domain;
		this.id = id;
	}
	public InstanceId(LinkedTreeMap<String , String> domainAndId) {
		this.domain = domainAndId.get("domain");
		this.id = domainAndId.get("id");
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
