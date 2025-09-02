package com.jobPortal.Dto;

import java.security.PublicKey;

public class JobPostStatusDTO {

	public int totalJobs;
	public int totalInternship;
	public int totalFullTimeJobs;
	public int totalPartTimeJobs;
	public int totalContractJobs;

	public JobPostStatusDTO() {
	Public JobPostStatusDTO(int totalJobs
			,int totalInternships
			,int totalFullTimeJobs
			,int totalPartTimeJobs
			,int totalContractJobs) {
		this.totalJobs=totalJobs;
		this.totalInternship=totalInternships;
		this.totalFullTimeJobs=totalFullTimeJobs;
		this.totalPartTimeJobs=totalPartTimeJobs;
		this.totalContractJobs=totalContractJobs;
	}
	}
}
