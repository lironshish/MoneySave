package com.example.moneysave.Server.boundaries;


public class UserBoundary extends User {
	protected UserId userId;
	
	public UserBoundary () {
		super();
		userId = new UserId();
	}
	public UserBoundary (UserRole role, String userName, String avatar,UserId userId) {
		super(role,userName,avatar);
		this.userId = new UserId(userId.getEmail(), userId.getDomain());
	}
	public UserId getUserId() {
		return userId;
	}
	public void setUserId(UserId userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserBoundry role= " + role + ", userName= " + username + ", avatar= " + avatar + " email= " + userId.getEmail() + " domain name= " + userId.getDomain() +  "]";
	}
	
}
