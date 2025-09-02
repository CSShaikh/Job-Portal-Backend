package com.jobPortal.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserStatusDTO {

	public int totalJobSeekers;
	public int totalRecruiter;
	public int totalBlockUsers;

}