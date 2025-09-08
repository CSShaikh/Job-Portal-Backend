package com.jobPortal.Dto;

import java.time.LocalDateTime;

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
public class JobPostDTO {

	private String title;
	private String description;
	private String recruiterEmail;
	private JobType jobType;
	private LocalDateTime postedAt;
}
