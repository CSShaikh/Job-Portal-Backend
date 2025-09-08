package com.jobPortal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPortal.Models.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
	List<JobPost> findByRecruiterEmail(String recruiterEmail);

	List<JobPost> findByJobType(String jobType);

	List<JobPost> findByTitleContaining(String title);
}