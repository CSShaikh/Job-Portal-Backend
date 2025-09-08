package com.jobPortal.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.Dto.ApplicationDTO;
import com.jobPortal.Dto.JobPostDTO;
import com.jobPortal.Enums.ApplicationStatus;
import com.jobPortal.Service.ApplicationService;
import com.jobPortal.Service.JobPostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recruiter")
@RequiredArgsConstructor
public class RecruiterController {

	private JobPostService jobService;
	private ApplicationService appService;

	// Post a new job
	@PostMapping("/post-job")
	public ResponseEntity<JobPostDTO> postJob(@RequestBody JobPostDTO dto) {
		return ResponseEntity.ok(jobService.postJob(dto));
	}

	// Get all jobs posted by recruiter
	@GetMapping("/jobs")
	public ResponseEntity<List<JobPostDTO>> getJobs(@RequestParam String recruiterEmail) {
		return ResponseEntity.ok(jobService.getJobsByRecruiter(recruiterEmail));
	}

	// Get all applications for recruiter's jobs
	@GetMapping("/applications")
	public ResponseEntity<List<ApplicationDTO>> getApplications(@RequestParam String recruiterEmail) {
		return ResponseEntity.ok(appService.getByRecruiterEmail(recruiterEmail));
	}

	// Shortlist or reject a candidate
	@PostMapping("/applications/update-status")
	public ResponseEntity<String> updateStatus(@RequestParam Long id, @RequestParam ApplicationStatus status) {
		appService.updateStatus(id, status);
		return ResponseEntity.ok("Application status updated to " + status);
	}
}