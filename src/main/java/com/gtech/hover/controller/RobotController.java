package com.gtech.hover.controller;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/*
 * Projeto B
 */
@Path("/rest/mars/{command}")
public class RobotController {

	@Inject
	RobotService service;
	
	@POST
	@Produces("text/plain")
	public String getPosition(@PathParam("command") String command) {
		RobotService manager = new RobotService(command);
		return manager.getPosition();
	}
}
