package com.jobPortal.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.Dto.ApplicationDTO;
import com.jobPortal.Enums.ApplicationStatus;
import com.jobPortal.Enums.JobType;
import com.jobPortal.Service.ApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

	private final ApplicationService appService;

	// Apply for a job
	@PostMapping("/apply")
	public ResponseEntity<ApplicationDTO> apply(@RequestBody ApplicationDTO dto) {
		ApplicationDTO savedApp = appService.apply(dto);
		return ResponseEntity.ok(savedApp);
	}

	// Jobseeker applications
	@GetMapping("/jobSeeker")
	public ResponseEntity<List<ApplicationDTO>> getJobSeekerApplications(@RequestParam String jobSeekerEmail) {
		return ResponseEntity.ok(appService.getByJobSeekerEmail(jobSeekerEmail));
	}

	// Recruiter applications
	@GetMapping("/recruiter")
	public ResponseEntity<List<ApplicationDTO>> getRecruiterApplications(@RequestParam String recruiterEmail) {
		return ResponseEntity.ok(appService.getByRecruiterEmail(recruiterEmail));
	}

	// Search by Job Title
	@GetMapping("/search/{jobTitle}")
	public ResponseEntity<List<ApplicationDTO>> getByJobTitle(@PathVariable String jobTitle) {
		return ResponseEntity.ok(appService.getByJobTitle(jobTitle));
	}

	// Filter by Job Type
	@GetMapping("/jobType")
	public ResponseEntity<List<ApplicationDTO>> getByJobType(@RequestParam JobType jobType) {
		return ResponseEntity.ok(appService.getByJobType(jobType));
	}

	// Update application status
	@PutMapping("/update-status")
	public ResponseEntity<String> updateStatus(@RequestParam Long id, @RequestParam ApplicationStatus status) {
		appService.updateStatus(id, status);
		return ResponseEntity.ok("Status updated successfully");
	}
}