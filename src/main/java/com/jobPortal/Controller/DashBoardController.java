package com.jobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.Dto.ApplicationStatusDTO;
import com.jobPortal.Dto.JobPostStatusDTO;
import com.jobPortal.Dto.UserStatusDTO;
import com.jobPortal.Service.DashBoardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {

	@Autowired
	private DashBoardService dashBoardService;

	@GetMapping("/jobs")
	public ResponseEntity<JobPostStatusDTO> fetchJobs() {
		return ResponseEntity.ok(dashBoardService.fetchJobStatus());
	}

	@GetMapping("/applications")
	public ResponseEntity<ApplicationStatusDTO> fetchApplication() {
		return ResponseEntity.ok(dashBoardService.fetchApplicationStatus());
	}

	@GetMapping("/users")
	public ResponseEntity<UserStatusDTO> fetchUsers() {
		return ResponseEntity.ok(dashBoardService.fetchUsersStatus());
	}

}
