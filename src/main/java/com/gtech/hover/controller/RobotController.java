package com.gtech.hover.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.inject.Inject;

@Path("/rest/mars/{comando}")
public class RobotController {

	@Inject
	RobotService service;
	
	@POST
	@Produces("text/plain")
	public String getPosition(@PathParam("comando") String comando) {
		RobotService manager = new RobotService(comando);
		return manager.getPosition();
	}
}
