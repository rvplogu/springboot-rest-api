package com.scop.androidmdm.userservice.server.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOTP {

	private String emailId;
	private String mobileNumber;
	private String otp;

}
