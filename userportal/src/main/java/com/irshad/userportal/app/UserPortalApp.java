package com.irshad.userportal.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.irshad.userportal.service.UserService;

public class UserPortalApp extends Application{

	private Set<Object> singletons = new HashSet<Object>();
	
	public UserPortalApp() {
		singletons.add(new UserService());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
