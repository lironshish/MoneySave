package com.example.moneysave.Server.boundaries;



public class NewUserBoundary extends User {
	private String email;

	public NewUserBoundary() {
		super();
	}
	public NewUserBoundary(UserRole role, String userName, String avatar,String email) {
		super(role,userName,avatar);
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "NewUserBoundry role= " + role + ", userName= " + username + ", avatar= " + avatar + " email= " + email + "]";
	}

}
