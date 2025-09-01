package com.jobPortal.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPortal.Dto.ApplicationDTO;
import com.jobPortal.Enums.ApplicationStatus;
import com.jobPortal.Enums.JobType;
import com.jobPortal.Models.Application;
import com.jobPortal.Repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {

	@Autowired
	private ApplicationRepository appRepo;// Apply for a job

	public ApplicationDTO apply(ApplicationDTO dto) {
		if (appRepo.findByJobSeekerEmailAndJobId(dto.getJobSeekerEmail(), dto.getJobId()).isPresent()) {
			throw new RuntimeException("You already applied for this job");
		}

		Application app = Application.builder().jobId(dto.getJobId()).jobSeekerName(dto.getJobseekerName())
				.jobSeekerEmail(dto.getJobSeekerEmail()).jobTitle(dto.getJobTitle()).jobType(dto.getJobType())
				.recruiterEmail(dto.getRecruiterEmail()).status(ApplicationStatus.APPLIED)
				.appliedAt(LocalDateTime.now()).build();

		Application saved = appRepo.save(app);
		return mapToDTO(saved);
	}

	// Fetch Applications by Jobseeker
	public List<ApplicationDTO> getByJobSeekerEmail(String jobSeekerEmail) {
		return appRepo.findByJobSeekerEmail(jobSeekerEmail).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// Fetch Applications by Recruiter
	public List<ApplicationDTO> getByRecruiterEmail(String recruiterEmail) {
		return appRepo.findByRecruiterEmail(recruiterEmail).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// Fetch by Job Title
	public List<ApplicationDTO> getByJobTitle(String jobTitle) {
		return appRepo.findByJobTitleContainingIgnoreCase(jobTitle).stream().map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	// Fetch by Job Type
	public List<ApplicationDTO> getByJobType(JobType jobType) {
		return appRepo.findByJobType(jobType).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// Update status
	public void updateStatus(Long id, ApplicationStatus status) {
		Application app = appRepo.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
		app.setStatus(status);
		appRepo.save(app);
	}

	private ApplicationDTO mapToDTO(Application app) {
		return ApplicationDTO.builder().jobId(app.getJobId()).jobseekerName(app.getJobSeekerName())
				.jobSeekerEmail(app.getJobSeekerEmail()).recruiterEmail(app.getRecruiterEmail())
				.jobTitle(app.getJobTitle()).jobType(app.getJobType()).status(app.getStatus())
				.appliedAt(app.getAppliedAt()).build();
	}
}