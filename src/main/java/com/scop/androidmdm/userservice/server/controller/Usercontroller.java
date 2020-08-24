package com.scop.androidmdm.userservice.server.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scop.androidmdm.userservice.server.constant.Token;
import com.scop.androidmdm.userservice.server.entity.UserIdentity;
import com.scop.androidmdm.userservice.server.service.UserIdentityService;
import com.scop.androidmdm.userservice.server.vo.LoginVo;
import com.scop.androidmdm.userservice.server.vo.RegisterVo;
import com.scop.androidmdm.userservice.server.vo.ResponseVo;
import com.scop.androidmdm.userservice.server.vo.UserOTP;

@RestController
public class Usercontroller {

	@Autowired
	UserIdentityService service;

	@PostMapping(value = "login")
	public ResponseVo loginSuccess(HttpServletRequest request, HttpServletResponse response, LoginVo loginVo) {
		ResponseVo responseVo = service.login(loginVo, request);

		return responseVo;
	}

	@PostMapping(value = "/domestic/register")
	public ResponseVo registerDomesticUser(HttpServletRequest request, HttpServletResponse response,
			RegisterVo register) {
		return service.regsiterDomesticUser(register);
	}

	@PostMapping(value = "/loginStatus")
	public ResponseVo loginStatus(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute(Token.UTOKEN.toString()))) {
			return ResponseVo.builder().status(true).build();
		}
		return ResponseVo.builder().status(false).build();
	}

	@PostMapping(value = "/logout")
	public ResponseVo logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute(Token.UTOKEN.toString()))) {
			session.removeAttribute(Token.UTOKEN.toString());
			session.invalidate();
		}
		return ResponseVo.builder().status(true).build();
	}

	@PostMapping(value = "/validateOTP")
	public ResponseVo validateOTP(HttpServletRequest request, HttpServletResponse response, UserOTP validateOTP) {
		return service.validateUserOTP(validateOTP);
	}

	@PostMapping(value = "/commercial/register")
	public boolean registerCommercialUser(HttpServletRequest request, HttpServletResponse response,
			UserIdentity userIdentity) {

		return false;
	}

	@GetMapping(value = "/user/profile")
	public UserIdentity getUserProfile(HttpServletRequest request, HttpServletResponse response) {
		return service.getUserProfileInformation();
	}
}