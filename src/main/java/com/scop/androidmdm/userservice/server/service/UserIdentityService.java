package com.scop.androidmdm.userservice.server.service;

import java.util.Arrays;
import java.util.Objects;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scop.androidmdm.userservice.server.client.SessionForm;
import com.scop.androidmdm.userservice.server.client.SessionUtils;
import com.scop.androidmdm.userservice.server.client.UsermanagerContext;
import com.scop.androidmdm.userservice.server.constant.Token;
import com.scop.androidmdm.userservice.server.dao.RoleIdentityRepository;
import com.scop.androidmdm.userservice.server.dao.UserIdentityRepository;
import com.scop.androidmdm.userservice.server.entity.UserIdentity;
import com.scop.androidmdm.userservice.server.util.PasswordCrypto;
import com.scop.androidmdm.userservice.server.utils.DateUtils;
import com.scop.androidmdm.userservice.server.vo.LoginVo;
import com.scop.androidmdm.userservice.server.vo.RegisterVo;
import com.scop.androidmdm.userservice.server.vo.ResponseVo;
import com.scop.androidmdm.userservice.server.vo.UserOTP;

@Service
public class UserIdentityService {

	@Autowired
	UserIdentityRepository userIdentityRepository;

	@Autowired
	RoleIdentityRepository roleIdentityRepository;

	public ResponseVo regsiterDomesticUser(RegisterVo register) {

		ResponseVo response = validateUserByMailAndNumber(register.getEmailId(), register.getMobileNumber());

		if (response.isStatus()) {
			SecretKey secretKey = PasswordCrypto.generateSalt(register.getPassword());
			byte[] hash = PasswordCrypto.encrypt(register.getPassword(), secretKey);
			UserIdentity userIdentity = UserIdentity.builder().emailId(register.getEmailId())
					.firstName(register.getFirstName()).lastName(register.getLastName()).salt(secretKey.getEncoded())
					.hash(hash).businessType(register.getBusinessType()).mailValidated(false).otpValidated(false)
					.mobileNumber(register.getMobileNumber()).updatedBy(register.getEmailId())
					.roleEntity(roleIdentityRepository.findById(Long.parseLong("2")).get())
					.createdOn(DateUtils.getCurrentTime()).updatedOn(DateUtils.getCurrentTime())
					.createdBy(register.getEmailId()).updatedBy(register.getEmailId()).build();
			userIdentityRepository.save(userIdentity);
			response.setMessage("User registered successfully");
			return response;
		}
		return response;
	}

	private ResponseVo validateUserByMailAndNumber(String emailId, String mobileNumber) {

		UserIdentity userIdentity = userIdentityRepository.findByEmailIdAndMobileNumber(emailId, mobileNumber);
		if (Objects.isNull(userIdentity)) {
			return ResponseVo.builder().status(true).build();
		}
		return ResponseVo.builder().status(false).message("User Already exist").build();
	}

	public ResponseVo validateUserOTP(UserOTP validateOTP) {
		if (validateOTP.getOtp().equals("0000"))
			return ResponseVo.builder().status(true).message("OTP verified").build();

		return ResponseVo.builder().status(false).message("OTP is invalid").build();
	}

	public ResponseVo login(LoginVo loginVo, HttpServletRequest request) {
		UserIdentity userIdentity = userIdentityRepository.findByEmailIdOrMobileNumber(loginVo.getUserId(),
				loginVo.getUserId());
		if (Objects.nonNull(userIdentity)) {
			SecretKey secretKey = PasswordCrypto.generateSalt(loginVo.getPassword());
			byte[] hash = PasswordCrypto.encrypt(loginVo.getPassword(), secretKey);
			boolean authStatus = Arrays.equals(hash, userIdentity.getHash());
			if (authStatus) {
				HttpSession session = request.getSession();
				String token = SessionUtils.generateToken(SessionForm.builder().emailId(userIdentity.getEmailId())
						.mobileNumber(userIdentity.getMobileNumber()).role(userIdentity.getRoleEntity().getName())
						.build());
				session.setAttribute(Token.UTOKEN.toString(), token);
				return ResponseVo.builder().status(authStatus).build();
			}
			return ResponseVo.builder().status(authStatus).message("Invalid username or password").build();
		}
		return ResponseVo.builder().status(false).message("Invalid username").build();
	}

	public UserIdentity getUserProfileInformation() {
		SessionForm sessionForm = UsermanagerContext.getSessionContext();
		return userIdentityRepository.findByEmailIdAndMobileNumber(sessionForm.getEmailId(),
				sessionForm.getMobileNumber());
	}

}
