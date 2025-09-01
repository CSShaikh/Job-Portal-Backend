package com.jobPortal.Dto;

import java.time.LocalDateTime;

import com.jobPortal.Enums.ApplicationStatus;
import com.jobPortal.Enums.JobType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicationDTO {
	private Long jobId;
	private String jobseekerName;
	private String jobSeekerEmail;
	private String recruiterEmail;
	private String jobTitle;
	private JobType jobType;
	private ApplicationStatus status;
	private LocalDateTime appliedAt;
}