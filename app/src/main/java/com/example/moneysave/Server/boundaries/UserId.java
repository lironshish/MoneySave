package com.example.moneysave.Server.boundaries;

public class UserId {

	private String domain;
	private String email;
	
	
	public UserId() {

	}
	
	
	public UserId(String email, String domain) {
		setDomain(domain);
		setEmail(email);
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getDomain() {
		return domain;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
