package com.jobPortal.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobPostStatusDTO {

	private int totalJobs;
	private int totalInternship;
	private int totalFullTimeJobs;
	private int totalPartTimeJobs;
	private int totalContractJobs;
	private int totalFreelanceJobs;

}
