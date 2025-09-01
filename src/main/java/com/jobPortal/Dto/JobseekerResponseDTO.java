package com.jobPortal.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data

public class JobseekerResponseDTO {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String skill;
	private String education;
	private String experience;
	private String resumeFilePath;
}