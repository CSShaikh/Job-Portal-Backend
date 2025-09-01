package com.jobPortal.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobPortal.Dto.JobseekerRequestDTO;
import com.jobPortal.Dto.JobseekerResponseDTO;
import com.jobPortal.Service.JobseekerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobseeker")
@RequiredArgsConstructor
public class JobseekerController {

	private final JobseekerService service;

	/**
	 * Create or update (upsert) profile - multipart/form-data - part "data" : JSON
	 * string of JobseekerRequest - part "resume": file (optional)
	 */
	@PreAuthorize("hasRole('JOBSEEKER')")
	@PostMapping(value = "/me", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<JobseekerResponseDTO> upsertProfile(@RequestPart("data") JobseekerRequestDTO data,
			@RequestPart(value = "resume", required = false) MultipartFile resume) throws IOException {

		JobseekerResponseDTO resp = service.upsertMyProfile(data, resume);
		return ResponseEntity.ok(resp);
	}

	@PreAuthorize("hasRole('JOBSEEKER')")
	@GetMapping("/me")
	public ResponseEntity<JobseekerResponseDTO> getMyProfile() {
		return ResponseEntity.ok(service.getMyProfile());
	}

	@PreAuthorize("hasRole('JOBSEEKER')")
	@DeleteMapping("/me")
	public ResponseEntity<Void> deleteMyProfile() {
		service.deleteMyProfile();
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasRole('JOBSEEKER')")
	@GetMapping("/me/resume")
	public ResponseEntity<InputStreamResource> downloadResume() throws IOException {
		JobseekerResponseDTO me = service.getMyProfile();
		if (me.getResumeFilePath() == null)
			return ResponseEntity.notFound().build();
		File f = new File(me.getResumeFilePath());
		if (!f.exists())
			return ResponseEntity.notFound().build();

		InputStreamResource resource = new InputStreamResource(new FileInputStream(f));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.attachment().filename(f.getName()).build());
		headers.setContentType(MediaType.APPLICATION_PDF);

		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}