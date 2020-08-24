package com.scop.androidmdm.userservice.server.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionForm {

	private String emailId;
	private String mobileNumber;
	private String role;
}
