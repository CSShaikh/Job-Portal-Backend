package com.jobPortal.Service;

import org.springframework.stereotype.Service;

import com.jobPortal.Dto.ApplicationStatusDTO;
import com.jobPortal.Dto.JobPostStatusDTO;
import com.jobPortal.Dto.UserStatusDTO;

@Service
public class DashBoardService {

	public JobPostStatusDTO fetchJobStatus() {
		JobPostStatusDTO jobs = new JobPostStatusDTO();

		jobs.setTotalJobs(120);
		jobs.setTotalInternship(70);
		jobs.setTotalPartTimeJobs(70);
		jobs.setTotalFullTimeJobs(50);
		jobs.setTotalContractJobs(50);
		jobs.setTotalFreelanceJobs(20);

		return jobs;
	}

	public ApplicationStatusDTO fetchApplicationStatus() {

		ApplicationStatusDTO applicant = new ApplicationStatusDTO();

		applicant.setTotalApplications(500);
		applicant.setTotalShortlisted(100);
		applicant.setTotalRejected(150);
//		applicant.totalPending(200);

		return applicant;

	}

	public UserStatusDTO fetchUsersStatus() {

		UserStatusDTO users = new UserStatusDTO();

		users.setTotalJobSeekers(10000);
		users.setTotalRecruiters(500);
		users.setTotalBlockUsers(1000);
//		users.totalPaidUsers(500);

		return users;
	}

}
