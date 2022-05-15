package com.example.moneysave.Server.boundaries;

public class CreatedBy {
	private UserId userId;
	
	public CreatedBy() {
	}

	public CreatedBy(UserId userId) {
		this.userId = userId;
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}
}
