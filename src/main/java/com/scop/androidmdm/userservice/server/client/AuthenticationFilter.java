package com.scop.androidmdm.userservice.server.client;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scop.androidmdm.userservice.server.constant.Token;

@Configuration
@Order(0)
public class AuthenticationFilter implements Filter {

	@Value("${application.api.exludePaths}")
	private String execludePath;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session = httpServletRequest.getSession();

//		String endPoints = getURLEnpoints(httpServletRequest);
//		System.out.println(endPoints);

		if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute(Token.UTOKEN.toString()))) {
			String sessionValue = SessionUtils.parseToken(session.getAttribute(Token.UTOKEN.toString()).toString());
			ObjectMapper mapper = new ObjectMapper();
			SessionForm sessionContextValue = mapper.readValue(sessionValue, SessionForm.class);
			UsermanagerContext.setSessionContext(sessionContextValue);
			System.out.println(sessionValue);
		}

		chain.doFilter(request, response);
	}

//	private String getURLEnpoints(HttpServletRequest httpServletRequest) {
//
//		return httpServletRequest.getRequestURI();
//	}
}