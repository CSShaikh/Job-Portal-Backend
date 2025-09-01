package com.jobPortal.Dto;

import com.jobPortal.Enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class RegisterRequestDTO {

	@NotBlank
	private String username;

	@Email
	private String email;

	@NotBlank
	private String password;

	private Role role;

}
