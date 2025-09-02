package com.jobPortal.Dto;

public class UserStatusDTO {

	public int totalJobSeekers;
	public int totalRecruiter;
	public int totalBlockUsers;

	public UserStatusDTO() {
		super();
	}

	public UserStatusDTO(int totalJobSeekers, int totalRecruiter, int totalBlockUsers) {
		super();
		this.totalJobSeekers = totalJobSeekers;
		this.totalRecruiter = totalRecruiter;
		this.totalBlockUsers = totalBlockUsers;
	}

}