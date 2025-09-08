package com.jobPortal.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPortal.Dto.JobPostDTO;
import com.jobPortal.Models.JobPost;
import com.jobPortal.Repository.JobPostRepository;

@Service

public class JobPostService {

	@Autowired
	private JobPostRepository jobRepo;

	public JobPostDTO postJob(JobPostDTO dto) {
		JobPost job = JobPost.builder().title(dto.getTitle()).description(dto.getDescription())
				.recruiterEmail(dto.getRecruiterEmail()).jobType(dto.getJobType()).postedAt(LocalDateTime.now())
				.build();

		JobPost saved = jobRepo.save(job);
		dto.setPostedAt(saved.getPostedAt());
		return dto;
	}

	public List<JobPostDTO> getJobsByRecruiter(String recruiterEmail) {
		return jobRepo.findByRecruiterEmail(recruiterEmail).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	private JobPostDTO mapToDTO(JobPost job) {
		return JobPostDTO.builder().title(job.getTitle()).description(job.getDescription())
				.recruiterEmail(job.getRecruiterEmail()).jobType(job.getJobType()).postedAt(job.getPostedAt()).build();
	}
}