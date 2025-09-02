package com.jobPortal.Dto;

public class JobPostStatusDTO {

	public int totalJobs;
	public int totalInternship;
	public int totalFullTimeJobs;
	public int totalPartTimeJobs;
	public int totalContractJobs;

	public JobPostStatusDTO() {
		super();
	}

	public JobPostStatusDTO(int totalJobs, int totalInternship, int totalFullTimeJobs, int totalPartTimeJobs,
			int totalContractJobs) {
		super();
		this.totalJobs = totalJobs;
		this.totalInternship = totalInternship;
		this.totalFullTimeJobs = totalFullTimeJobs;
		this.totalPartTimeJobs = totalPartTimeJobs;
		this.totalContractJobs = totalContractJobs;
	}

}
