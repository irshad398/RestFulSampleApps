package com.irshad.userportal.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.irshad.userportal.delegate.UserDelegate;
import com.irshad.userportal.model.User;

@Path("user")
public class UserService {
	private static final Logger log = Logger.getLogger(UserService.class);
	
	@GET
	@Path("test")
	public Response getMessage() {
		
		return Response.status(200).entity("Hi").build();
	}
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		log.debug("Creating the user for --> "+user.getName());
		UserDelegate delegate = new UserDelegate();
		delegate.createUser(user);
		
		return Response.status(200).entity(user).build();
	}
}
