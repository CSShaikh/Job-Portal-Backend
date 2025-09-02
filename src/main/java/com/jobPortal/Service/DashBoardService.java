package com.jobPortal.Service;

import org.springframework.stereotype.Service;

import com.jobPortal.Dto.ApplicationStatusDTO;
import com.jobPortal.Dto.JobPostStatusDTO;
import com.jobPortal.Dto.UserStatusDTO;

@Service
public class DashBoardService {

	public JobPostStatusDTO fetchJobStatus() {
		JobPostStatusDTO jobs = new JobPostStatusDTO();

		jobs.totalJobs(120);
		jobs.totalInternship(70);
		jobs.totalPartTimeJobs(50);
		jobs.totalFullTimeJobs(50);
		jobs.totalContractJobs(50);

		return jobs;
	}

	public ApplicationStatusDTO fetchApplicationStatus() {

		ApplicationStatusDTO applicant = new ApplicationStatusDTO();

		applicant.totalApplications(500);
		applicant.totalShortlisted(100);
		applicant.totalRejected(150);
		applicant.totalPending(200);

		return applicant;
	}

	public UserStatusDTO fetchUserStatus() {

		UserStatusDTO users = new UserStatusDTO();

		users.totalJobSeekers(10000);
		users.totalRecruiter(500);
		users.totalBlockUsers(1000);

		return users;
	}
}
