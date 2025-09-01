package com.jobPortal.Service;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jobPortal.Dto.JobseekerRequestDTO;
import com.jobPortal.Dto.JobseekerResponseDTO;
import com.jobPortal.Exception.ResourceNotFoundException;
import com.jobPortal.Models.Jobseeker;
import com.jobPortal.Models.User;
import com.jobPortal.Repository.JobseekerRepository;
import com.jobPortal.Repository.UserRepository;
import com.jobPortal.Storage.FileStorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobseekerService {

	private final JobseekerRepository jobseekerRepo;
	private final UserRepository userRepo;
	private final FileStorageService storage;

	// get current logged-in user (email used as username in security)
	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth.getName() == null)
			throw new ResourceNotFoundException("Unauthenticated");
		return userRepo.findByEmail(auth.getName())
				.orElseThrow(() -> new ResourceNotFoundException("User not found: " + auth.getName()));
	}

	private JobseekerResponseDTO toResponse(Jobseeker e) {
		return JobseekerResponseDTO.builder().id(e.getId()).name(e.getName())
				.email(e.getUser() != null ? e.getUser().getEmail() : null).phone(e.getPhone()).skill(e.getSkill())
				.education(e.getEducation()).experience(e.getExperience()).resumeFilePath(e.getResumeFilePath())
				.build();
	}

	@Transactional
	public JobseekerResponseDTO upsertMyProfile(JobseekerRequestDTO req, MultipartFile resume) throws IOException {
		User me = getCurrentUser();
		Jobseeker entity = jobseekerRepo.findByUser_Id(me.getId())
				.orElseGet(() -> Jobseeker.builder().user(me).build());

		entity.setName(req.getName());
		entity.setPhone(req.getPhone());
		entity.setSkill(req.getSkill());
		entity.setEducation(req.getEducation());
		entity.setExperience(req.getExperience());

		if (resume != null && !resume.isEmpty()) {
			// delete old
			storage.deleteIfExists(entity.getResumeFilePath());
			String path = storage.store(resume);
			entity.setResumeFilePath(path);
		}

		Jobseeker saved = jobseekerRepo.save(entity);
		return toResponse(saved);
	}

	@Transactional(readOnly = true)
	public JobseekerResponseDTO getMyProfile() {
		User me = getCurrentUser();
		Jobseeker e = jobseekerRepo.findByUser_Id(me.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
		return toResponse(e);
	}

	@Transactional
	public void deleteMyProfile() {
		User me = getCurrentUser();
		Jobseeker e = jobseekerRepo.findByUser_Id(me.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
		storage.deleteIfExists(e.getResumeFilePath());
		jobseekerRepo.delete(e);
	}
}