package com.jobPortal.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jobseeker_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jobseeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;

	private String name;

	private String phone;

	@Column(length = 2000)
	private String skill;

	@Column(length = 4000)
	private String education;

	private String experience;

	private String resumeFilePath;

}
