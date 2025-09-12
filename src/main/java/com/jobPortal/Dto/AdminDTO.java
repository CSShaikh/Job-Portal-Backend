package com.jobPortal.Dto;

import com.jobPortal.Enums.Action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminDTO {

	private Long adminId;

	private Long userId;

	private Action action;
}
