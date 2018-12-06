package com.example.smartkitchenbackend.DTOs.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
	private Boolean success;
	private String message;
}