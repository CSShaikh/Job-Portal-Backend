package com.jobPortal.Dto;

public class ApplicationStatusDTO {

	public int totalApplications;
	public int totalShortlisted;
	public int totalRejected;
	public int totalPending;

	public ApplicationStatusDTO() {
	}

	public ApplicationStatusDTO(int totalApplications,int totalShortlisted,int totalRejected,int totalPending) {

			this.totalApplications=totalApplications,
			this.totalShortlisted=totalShortlisted,
			this.totalRejected=totalRejected,
			this.totalPending=totalPending
	}
}
