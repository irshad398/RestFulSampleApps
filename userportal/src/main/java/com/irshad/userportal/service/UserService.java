package com.irshad.userportal.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.irshad.userportal.delegate.UserDelegate;
import com.irshad.userportal.model.User;

@Path("user")
public class UserService {
	
	@GET
	@Path("test")
	public Response createUser() {
		
		return Response.status(200).entity("Hi").build();
	}
	
	@POST
	@Path("")
	public Response createUser(User user) {
		
		UserDelegate delegate = new UserDelegate();
		delegate.createUser(user);
		
		return Response.status(200).entity("Hi").build();
	}
}
