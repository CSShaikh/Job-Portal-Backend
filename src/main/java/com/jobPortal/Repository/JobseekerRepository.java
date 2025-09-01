package com.jobPortal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobPortal.Models.Jobseeker;

public interface JobseekerRepository extends JpaRepository<Jobseeker, Long> {
	Optional<Jobseeker> findByUser_Id(Long userId);

	boolean existsByUser_Id(Long userId);
}
