package com.scop.androidmdm.userservice.server.client;

public class UsermanagerContext {

	private static final ThreadLocal<SessionForm> CONTEXT = new ThreadLocal<>();

	public static void setSessionContext(SessionForm sessionForm) {
		CONTEXT.set(sessionForm);
	}

	public static SessionForm getSessionContext() {

		return CONTEXT.get();
	}

}
