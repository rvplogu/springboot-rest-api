package com.scop.androidmdm.userservice.server.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {

	private String emailId;
	private String mobileNumber;
	private String password;
	private String firstName;
	private String lastName;
	private String businessType;
	
}
