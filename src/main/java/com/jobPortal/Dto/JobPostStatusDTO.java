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

	public int totalJobs;
	public int totalInternship;
	public int totalFullTimeJobs;
	public int totalPartTimeJobs;
	public int totalContractJobs;

}
