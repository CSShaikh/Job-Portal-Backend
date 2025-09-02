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
public class ApplicationStatusDTO {

	public int totalApplications;
	public int totalShortlisted;
	public int totalRejected;
	public int totalPending;

}
