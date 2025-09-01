package com.jobPortal.Models;

import java.time.LocalDateTime;

import com.jobPortal.Enums.ApplicationStatus;
import com.jobPortal.Enums.JobType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String jobSeekerName;

	private String jobSeekerEmail;

	private Long jobId;

	private String jobTitle;

	@Enumerated(EnumType.STRING)
	private JobType jobType;

	private String recruiterEmail;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;

	private LocalDateTime appliedAt;

}
