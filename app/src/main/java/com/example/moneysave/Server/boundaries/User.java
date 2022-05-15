package com.example.moneysave.Server.boundaries;


public abstract class User {


	protected UserRole role;
	protected String username;
	protected String avatar;


	public User() {
	}

	
	public User( UserRole role, String userName, String avatar) {
		setRole(role);
		setUsername(userName);
		setAvatar(avatar);
	}


	public UserRole getRole() {
		return role;
	}



	public void setRole(UserRole role) {
		this.role = role;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String userName) {
		this.username = userName;
	}



	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


}



